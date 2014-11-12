/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.bbb.meetings.portlet;

import com.liferay.bbb.model.BBBMeeting;
import com.liferay.bbb.model.BBBMeetingConstants;
import com.liferay.bbb.model.BBBParticipant;
import com.liferay.bbb.model.BBBParticipantConstants;
import com.liferay.bbb.service.BBBMeetingLocalServiceUtil;
import com.liferay.bbb.service.BBBMeetingServiceUtil;
import com.liferay.bbb.service.BBBParticipantLocalServiceUtil;
import com.liferay.bbb.util.BBBAPIUtil;
import com.liferay.bbb.util.BBBUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Shinn Lok
 */
public class MeetingsPortlet extends MVCPortlet {

	public void deleteBBBMeeting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long bbbMeetingId = ParamUtil.getLong(actionRequest, "bbbMeetingId");

		BBBMeetingServiceUtil.deleteBBBMeeting(bbbMeetingId);
	}

	public void endBBBMeeting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long bbbMeetingId = ParamUtil.getLong(actionRequest, "bbbMeetingId");

		BBBAPIUtil.endMeeting(bbbMeetingId);
	}

	public void joinBBBMeeting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long bbbMeetingId = ParamUtil.getLong(actionRequest, "bbbMeetingId");

		if (bbbMeetingId > 0) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			BBBParticipant bbbParticipant =
				BBBParticipantLocalServiceUtil.fetchBBBParticipant(
					bbbMeetingId, user.getEmailAddress());

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				BBBMeeting.class.getName(), actionRequest);

			if (bbbParticipant == null) {
				bbbParticipant =
					BBBParticipantLocalServiceUtil.addBBBParticipant(
						user.getUserId(), themeDisplay.getScopeGroupId(),
						bbbMeetingId, user.getFirstName(),
						user.getEmailAddress(),
						BBBParticipantConstants.TYPE_MODERATOR,
						BBBParticipantConstants.STATUS_INVITED, serviceContext);
			}

			actionResponse.sendRedirect(
				BBBUtil.getInvitationURL(
					bbbParticipant, serviceContext.getRequest()));
		}
		else {
			long bbbParticipantId = ParamUtil.getLong(
				actionRequest, "bbbParticipantId");
			String hash = ParamUtil.getString(actionRequest, "hash");
			String name = ParamUtil.getString(actionRequest, "name");
			boolean recordMeeting = ParamUtil.getBoolean(
				actionRequest, "recordMeeting");

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			BBBParticipant bbbParticipant =
				BBBParticipantLocalServiceUtil.getBBBParticipant(
					bbbParticipantId);

			if (!hash.equals(BBBUtil.getHash(bbbParticipant))) {
				jsonObject.put("success", Boolean.FALSE);

				writeJSON(actionRequest, actionResponse, jsonObject);

				return;
			}

			BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
				bbbParticipant.getBbbMeetingId());

			try {
				if ((bbbMeeting.getBbbServerId() ==
						BBBMeetingConstants.BBB_SERVER_ID_DEFAULT) &&
					(bbbParticipant.getType() !=
						BBBParticipantConstants.TYPE_MODERATOR)) {

					jsonObject.put("retry", Boolean.TRUE);
				}
				else {
					if ((bbbParticipant.getType() ==
							BBBParticipantConstants.TYPE_MODERATOR) &&
						!BBBAPIUtil.isMeetingRunning(
							bbbParticipant.getBbbMeetingId())) {

						BBBAPIUtil.startMeeting(
							bbbParticipant.getBbbMeetingId(), recordMeeting);
					}

					String joinURL = BBBAPIUtil.getJoinURL(
						bbbParticipant, name);

					jsonObject.put("joinURL", joinURL);
					jsonObject.put("success", Boolean.TRUE);
				}
			}
			catch (Exception e) {
				jsonObject.putException(e);
			}

			writeJSON(actionRequest, actionResponse, jsonObject);
		}
	}

	public void startBBBMeeting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long bbbMeetingId = ParamUtil.getLong(actionRequest, "bbbMeetingId");

		boolean recordMeeting = ParamUtil.getBoolean(
			actionRequest, "recordMeeting");

		BBBAPIUtil.startMeeting(bbbMeetingId, recordMeeting);
	}

	public void updateBBBMeeting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long bbbMeetingId = ParamUtil.getLong(actionRequest, "bbbMeetingId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");

		List<BBBParticipant> bbbParticipants = BBBUtil.getBBBParticipants(
			actionRequest);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			BBBMeeting.class.getName(), actionRequest);

		if (bbbMeetingId <= 0) {
			String portletId = PortalUtil.getPortletId(actionRequest);

			BBBMeetingServiceUtil.addBBBMeeting(
				themeDisplay.getScopeGroupId(), portletId,
				BBBMeetingConstants.BBB_SERVER_ID_DEFAULT, name, description,
				null, null, BBBMeetingConstants.STATUS_SCHEDULED,
				bbbParticipants, serviceContext);
		}
		else {
			BBBMeeting bbbMeeting = BBBMeetingServiceUtil.getBBBMeeting(
				bbbMeetingId);

			BBBMeetingServiceUtil.updateBBBMeeting(
				bbbMeetingId, bbbMeeting.getBbbServerId(), name, description,
				bbbMeeting.getAttendeePassword(),
				bbbMeeting.getModeratorPassword(), bbbParticipants,
				serviceContext);
		}
	}

}