/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.chat.poller;

import com.liferay.chat.model.Entry;
import com.liferay.chat.model.Status;
import com.liferay.chat.service.EntryLocalServiceUtil;
import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.chat.util.ChatUtil;
import com.liferay.chat.util.PortletPropsValues;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.poller.BasePollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.model.ContactConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ChatPollerProcessor extends BasePollerProcessor {

	protected void addEntry(PollerRequest pollerRequest) throws Exception {
		long toUserId = getLong(pollerRequest, "toUserId");
		String content = getString(pollerRequest, "content");

		if (toUserId > 0) {
			EntryLocalServiceUtil.addEntry(
				pollerRequest.getTimestamp(), pollerRequest.getUserId(),
				toUserId, content);
		}
	}

	protected void getBuddies(
			PollerRequest pollerRequest, PollerResponse pollerResponse)
		throws Exception {

		List<Object[]> buddies = ChatUtil.getBuddies(
			pollerRequest.getCompanyId(), pollerRequest.getUserId());

		JSONArray buddiesJSON = JSONFactoryUtil.createJSONArray();

		for (Object[] buddy : buddies) {
			long userId = (Long)buddy[0];
			String screenName = (String)buddy[1];
			String firstName = (String)buddy[2];
			String middleName = (String)buddy[3];
			String lastName = (String)buddy[4];
			long portraitId = (Long)buddy[5];
			boolean awake = (Boolean)buddy[6];

			String fullName = ContactConstants.getFullName(
				firstName, middleName, lastName);

			if (userId == pollerRequest.getUserId()) {
				continue;
			}

			Status buddyStatus = StatusLocalServiceUtil.getUserStatus(userId);

			awake = buddyStatus.getAwake();
			String statusMessage = buddyStatus.getMessage();

			JSONObject curUserJSON = JSONFactoryUtil.createJSONObject();

			curUserJSON.put("userId", userId);
			curUserJSON.put("screenName", screenName);
			curUserJSON.put("fullName", fullName);
			curUserJSON.put("portraitId", portraitId);
			curUserJSON.put("awake", awake);
			curUserJSON.put("statusMessage", statusMessage);

			buddiesJSON.put(curUserJSON);
		}

		pollerResponse.setParameter("buddies", buddiesJSON);
	}

	protected void getEntries(
			PollerRequest pollerRequest, PollerResponse pollerResponse)
		throws Exception {

		Status status = StatusLocalServiceUtil.getUserStatus(
			pollerRequest.getUserId());

		long createDate = status.getModifiedDate();

		if (pollerRequest.isInitialRequest()) {
			createDate = createDate - Time.DAY;
		}

		List<Entry> entries = EntryLocalServiceUtil.getNewEntries(
			pollerRequest.getUserId(), createDate, 0,
			PortletPropsValues.BUDDY_LIST_MAX_BUDDIES);

		entries = ListUtil.copy(entries);

		Collections.reverse(entries);

		JSONArray entriesJSON = JSONFactoryUtil.createJSONArray();

		for (Entry entry : entries) {
			JSONObject entryJSON = JSONFactoryUtil.createJSONObject();

			entryJSON.put("entryId", entry.getEntryId());
			entryJSON.put("createDate", entry.getCreateDate());
			entryJSON.put("fromUserId", entry.getFromUserId());

			if (entry.getFromUserId() != pollerRequest.getUserId()) {
				try {
					User fromUser = UserLocalServiceUtil.getUserById(
						entry.getFromUserId());

					entryJSON.put("fromFullName", fromUser.getFullName());
					entryJSON.put("fromPortraitId", fromUser.getPortraitId());
				}
				catch (NoSuchUserException nsue) {
					continue;
				}
			}

			entryJSON.put("toUserId", entry.getToUserId());
			entryJSON.put("content", HtmlUtil.escape(entry.getContent()));

			entriesJSON.put(entryJSON);
		}

		pollerResponse.setParameter("entries", entriesJSON);

		if (!entries.isEmpty()) {
			pollerResponse.setParameter(
				PollerResponse.POLLER_HINT_HIGH_CONNECTIVITY,
				Boolean.TRUE.toString());
		}

		boolean updatePresence = getBoolean(pollerRequest, "updatePresence");

		if (updatePresence) {
		}
		else if (!entries.isEmpty()) {
			updatePresence = true;
		}
		else {
			long onlineTimestamp =
				status.getModifiedDate() + ChatUtil.ONLINE_DELTA -
					ChatUtil.MAX_POLL_LATENCY;

			if (onlineTimestamp < pollerRequest.getTimestamp()) {
				updatePresence = true;
			}
		}

		if (updatePresence) {
			StatusLocalServiceUtil.updateStatus(
				pollerRequest.getUserId(), pollerRequest.getTimestamp());
		}
	}

	@Override
	protected void doReceive(
			PollerRequest pollerRequest, PollerResponse pollerResponse)
		throws Exception {

		getBuddies(pollerRequest, pollerResponse);
		getEntries(pollerRequest, pollerResponse);
	}

	@Override
	protected void doSend(PollerRequest pollerRequest) throws Exception {
		addEntry(pollerRequest);
		updateStatus(pollerRequest);
	}

	protected void updateStatus(PollerRequest pollerRequest) throws Exception {
		long timestamp = -1;
		int online = getInteger(pollerRequest, "online");
		int awake = getInteger(pollerRequest, "awake");
		String activePanelId = getString(pollerRequest, "activePanelId");
		String statusMessage = getString(pollerRequest, "statusMessage");
		int playSound = getInteger(pollerRequest, "playSound");

		if ((online != -1) || (awake != -1) || (activePanelId != null) ||
			(statusMessage != null) || (playSound != -1)) {

			StatusLocalServiceUtil.updateStatus(
				pollerRequest.getUserId(), timestamp, online, awake,
				activePanelId, statusMessage, playSound);
		}
	}

}