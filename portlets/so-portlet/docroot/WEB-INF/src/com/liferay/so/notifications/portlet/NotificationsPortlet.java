/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.notifications.portlet;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.portlet.social.model.SocialRequest;
import com.liferay.portlet.social.model.SocialRequestConstants;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;
import com.liferay.portlet.social.service.SocialRequestLocalServiceUtil;
import com.liferay.so.MemberRequestAlreadyUsedException;
import com.liferay.so.MemberRequestInvalidUserException;
import com.liferay.so.invitemembers.util.InviteMembersConstants;
import com.liferay.so.model.MemberRequest;
import com.liferay.so.service.MemberRequestLocalServiceUtil;
import com.liferay.so.util.WebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Jonathan Lee
 * @author Ryan Park
 */
public class NotificationsPortlet extends MVCPortlet {

	public void deleteUserNotificationEvent(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String userNotificationEventUuid = ParamUtil.getString(
			actionRequest, "userNotificationEventUuid");

		ChannelHubManagerUtil.deleteUserNotificiationEvent(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(),
			userNotificationEventUuid);
	}

	public void deleteUserNotificationEvents(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String[] userNotificationEventUuids = StringUtil.split(
			ParamUtil.getString(actionRequest, "userNotificationEventUuids"));

		for (String userNotificationEventUuid : userNotificationEventUuids) {
			ChannelHubManagerUtil.deleteUserNotificiationEvent(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(),
				userNotificationEventUuid);
		}
	}

	public void dismissUserNotificationEvents(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String[] userNotificationEventUuids = StringUtil.split(
			ParamUtil.getString(actionRequest, "userNotificationEventUuids"));

		for (String userNotificationEventUuid : userNotificationEventUuids) {
			ChannelHubManagerUtil.confirmDelivery(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(),
				userNotificationEventUuid, true);
		}
	}

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			if (themeDisplay.isSignedIn()) {
				HttpServletRequest request = PortalUtil.getHttpServletRequest(
					renderRequest);

				HttpSession session = request.getSession();

				String memberRequestKey = (String)session.getAttribute(
					WebKeys.MEMBER_REQEUST_KEY);

				if (Validator.isNotNull(memberRequestKey)) {
					MemberRequestLocalServiceUtil.updateMemberRequest(
						memberRequestKey, themeDisplay.getUserId());

					session.removeAttribute(WebKeys.MEMBER_REQEUST_KEY);
				}
			}

			List<SocialRequest> socialRequests =
				SocialRequestLocalServiceUtil.getReceiverUserRequests(
					themeDisplay.getUserId(),
					SocialRequestConstants.STATUS_PENDING, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			List<MemberRequest> memberRequests =
				MemberRequestLocalServiceUtil.getReceiverStatusMemberRequest(
					themeDisplay.getUserId(),
					InviteMembersConstants.STATUS_PENDING, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			if (socialRequests.isEmpty() && memberRequests.isEmpty()) {
				renderRequest.setAttribute(
					WebKeys.PORTLET_DECORATE, Boolean.FALSE);
			}

			include(viewTemplate, renderRequest, renderResponse);
		}
		catch (Exception e) {
			super.doView(renderRequest, renderResponse);
		}
	}

	public void updateMemberRequest(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long memberRequestId = ParamUtil.getLong(
			actionRequest, "memberRequestId");
		int status = ParamUtil.getInteger(actionRequest, "status");

		try {
			MemberRequestLocalServiceUtil.updateMemberRequest(
				themeDisplay.getUserId(), memberRequestId, status);
		}
		catch (Exception e) {
			if ((e instanceof MemberRequestAlreadyUsedException) ||
				(e instanceof MemberRequestInvalidUserException)) {

				SessionErrors.add(actionRequest, e.getClass(), e);
			}
			else {
				throw e;
			}
		}

		String notificationEventUuid = ParamUtil.getString(
			actionRequest, "notificationEventUuid");

		ChannelHubManagerUtil.confirmDelivery(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(),
			notificationEventUuid, false);
	}

	public void updateSocialRequest(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long requestId = ParamUtil.getLong(actionRequest, "requestId");
		int status = ParamUtil.getInteger(actionRequest, "status");

		SocialRequest socialRequest =
			SocialRequestLocalServiceUtil.getSocialRequest(requestId);

		if (SocialRelationLocalServiceUtil.hasRelation(
				socialRequest.getReceiverUserId(), socialRequest.getUserId(),
				SocialRelationConstants.TYPE_UNI_ENEMY)) {

			status = SocialRequestConstants.STATUS_IGNORE;
		}

		SocialRequestLocalServiceUtil.updateRequest(
			requestId, status, themeDisplay);

		String notificationEventUuid = ParamUtil.getString(
			actionRequest, "notificationEventUuid");

		ChannelHubManagerUtil.confirmDelivery(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(),
			notificationEventUuid, false);
	}

}