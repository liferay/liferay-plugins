/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.bbb.admin.portlet;

import com.liferay.bbb.model.BBBMeeting;
import com.liferay.bbb.model.BBBMeetingConstants;
import com.liferay.bbb.model.BBBParticipant;
import com.liferay.bbb.model.BBBServer;
import com.liferay.bbb.service.BBBMeetingServiceUtil;
import com.liferay.bbb.service.BBBServerServiceUtil;
import com.liferay.bbb.util.BBBAPIUtil;
import com.liferay.bbb.util.BBBUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Shinn Lok
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteBBBMeeting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long bbbMeetingId = ParamUtil.getLong(actionRequest, "bbbMeetingId");

		BBBMeetingServiceUtil.deleteBBBMeeting(bbbMeetingId);
	}

	public void deleteBBBServer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long bbbServerId = ParamUtil.getLong(actionRequest, "bbbServerId");

		BBBServerServiceUtil.deleteBBBServer(bbbServerId);
	}

	public void endBBBMeeting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long bbbMeetingId = ParamUtil.getLong(actionRequest, "bbbMeetingId");

		BBBAPIUtil.endMeeting(bbbMeetingId);
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

	public void updateBBBServer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long bbbServerId = ParamUtil.getLong(actionRequest, "bbbServerId");

		String name = ParamUtil.getString(actionRequest, "name");
		String url = ParamUtil.getString(actionRequest, "url");
		String secret = ParamUtil.getString(actionRequest, "secret");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			BBBServer.class.getName(), actionRequest);

		if (bbbServerId <= 0) {
			BBBServerServiceUtil.addBBBServer(
				themeDisplay.getScopeGroupId(), name, url, secret,
				serviceContext);
		}
		else {
			BBBServerServiceUtil.updateBBBServer(
				bbbServerId, name, url, secret, serviceContext);
		}
	}

}