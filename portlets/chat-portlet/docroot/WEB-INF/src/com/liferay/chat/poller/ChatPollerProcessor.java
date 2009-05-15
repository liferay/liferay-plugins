/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.chat.poller;

import com.liferay.chat.NoSuchStatusException;
import com.liferay.chat.model.Entry;
import com.liferay.chat.model.Status;
import com.liferay.chat.service.EntryLocalServiceUtil;
import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.chat.util.ChatUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.poller.BasePollerProcessor;
import com.liferay.portal.kernel.poller.PollerException;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ContactConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="ChatPollerProcessor.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ChatPollerProcessor extends BasePollerProcessor {

	public void process(
			PollerRequest pollerRequest, PollerResponse pollerResponse)
		throws PollerException {

		try {
			doProcess(pollerRequest, pollerResponse);
		}
		catch (Exception e) {
			throw new PollerException(e);
		}
	}

	protected void getBuddies(
			PollerRequest pollerRequest, PollerResponse pollerResponse,
			Map<Long, Long> latestCreateDates)
		throws Exception {

		List<Object[]> buddies = ChatUtil.getBuddies(pollerRequest.getUserId());

		JSONArray buddiesJSON = JSONFactoryUtil.createJSONArray();

		for (Object[] buddy : buddies) {
			long userId = (Long)buddy[0];
			String firstName = (String)buddy[1];
			String middleName = (String)buddy[2];
			String lastName = (String)buddy[3];
			long portraitId = (Long)buddy[4];
			boolean awake = (Boolean)buddy[5];

			String fullName = ContactConstants.getFullName(
				firstName, middleName, lastName);

			if (userId == pollerRequest.getUserId()) {
				continue;
			}

			String statusMessage = null;

			try {
				Status buddyStatus = StatusLocalServiceUtil.getUserStatus(
					userId);

				awake = buddyStatus.getAwake();
				statusMessage = buddyStatus.getMessage();
			}
			catch (NoSuchStatusException nsse) {
			}

			Long latestCreateDate = latestCreateDates.get(userId);

			if (latestCreateDate == null) {
				latestCreateDate = new Long(0);
			}

			JSONObject curUserJSON = JSONFactoryUtil.createJSONObject();

			curUserJSON.put("userId", userId);
			curUserJSON.put("fullName", fullName);
			curUserJSON.put("portraitId", portraitId);
			curUserJSON.put("awake", awake);
			curUserJSON.put("statusMessage", statusMessage);
			curUserJSON.put("latestCreateDate", latestCreateDate.longValue());

			buddiesJSON.put(curUserJSON);
		}

		pollerResponse.setParameter("buddies", buddiesJSON);
	}

	protected Map<Long, Long> getEntries(
			PollerRequest pollerRequest, PollerResponse pollerResponse)
		throws Exception {

		long statusModifiedDate = 0;

		Map<Long, Long> latestCreateDates = new HashMap<Long, Long>();

		long createDate = getLong(pollerRequest, "createDate");

		if (createDate == -1) {
			try {
				Status status = StatusLocalServiceUtil.getUserStatus(
					pollerRequest.getUserId());

				statusModifiedDate = status.getModifiedDate();

				createDate = statusModifiedDate;
			}
			catch (NoSuchStatusException nsse) {
				createDate = System.currentTimeMillis();
			}

			createDate = createDate - Time.MINUTE;
		}

		List<Entry> entries = EntryLocalServiceUtil.getNewEntries(
			pollerRequest.getUserId(), createDate, 0,
			SearchContainer.DEFAULT_DELTA);

		entries = ListUtil.copy(entries);

		Collections.reverse(entries);

		JSONArray entriesJSON = JSONFactoryUtil.createJSONArray();

		for (Entry entry : entries) {
			JSONObject entryJSON = JSONFactoryUtil.createJSONObject();

			entryJSON.put("entryId", entry.getEntryId());
			entryJSON.put("createDate", entry.getCreateDate());
			entryJSON.put("fromUserId", entry.getFromUserId());

			if (entry.getFromUserId() != pollerRequest.getUserId()) {
				User fromUser = UserLocalServiceUtil.getUserById(
					entry.getFromUserId());

				entryJSON.put("fromFullName", fromUser.getFullName());
				entryJSON.put("fromPortraitId", fromUser.getPortraitId());
			}

			entryJSON.put("toUserId", entry.getToUserId());
			entryJSON.put("content", HtmlUtil.escape(entry.getContent()));

			entriesJSON.put(entryJSON);

			if (Validator.isNotNull(entry.getContent())) {
				latestCreateDates.put(
					entry.getToUserId(), entry.getCreateDate());
			}

			if ((statusModifiedDate > 0) &&
				(entry.getCreateDate() > statusModifiedDate)) {

				pollerResponse.setParameter(
					PollerResponse.POLLER_HINT_HIGH_CONNECTIVITY,
					Boolean.TRUE.toString());
			}
		}

		pollerResponse.setParameter("entries", entriesJSON);

		return latestCreateDates;
	}

	protected void doProcess(
			PollerRequest pollerRequest, PollerResponse pollerResponse)
		throws Exception {

		long createDate = getLong(pollerRequest, "createDate");
		long toUserId = getLong(pollerRequest, "toUserId");
		String content = getString(pollerRequest, "content");

		if (toUserId > 0) {
			EntryLocalServiceUtil.addEntry(
				createDate, pollerRequest.getUserId(), toUserId, content);
		}

		Map<Long, Long> latestCreateDates = getEntries(
			pollerRequest, pollerResponse);

		getBuddies(pollerRequest, pollerResponse, latestCreateDates);

		updateStatus(pollerRequest);
	}

	protected void updateStatus(PollerRequest pollerRequest) throws Exception {
		int online = getInteger(pollerRequest, "online");
		int awake = getInteger(pollerRequest, "awake");
		String activePanelId = getString(pollerRequest, "activePanelId");
		String statusMessage = getString(pollerRequest, "statusMessage");
		int playSound = getInteger(pollerRequest, "playSound");

		StatusLocalServiceUtil.updateStatus(
			pollerRequest.getUserId(), online, awake, activePanelId,
			statusMessage, playSound);
	}

}