/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.invitemembers.portlet;

import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.so.MemberRequestAlreadyUsedException;
import com.liferay.so.MemberRequestInvalidUserException;
import com.liferay.so.invitemembers.util.InviteMembersConstants;
import com.liferay.so.model.MemberRequest;
import com.liferay.so.service.MemberRequestLocalServiceUtil;
import com.liferay.so.util.WebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Ryan Park
 *
 */
public class InviteMembersPortlet extends MVCPortlet {

	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			Group group = GroupLocalServiceUtil.getGroup(
				themeDisplay.getScopeGroupId());

			if (group.isUser() && themeDisplay.isSignedIn()) {
				HttpServletRequest request =
					PortalUtil.getHttpServletRequest(renderRequest);

				HttpSession session = request.getSession();

				String memberRequestKey = (String)session.getAttribute(
					WebKeys.MEMBER_REQEUST_KEY);

				if (Validator.isNotNull(memberRequestKey)) {
					MemberRequestLocalServiceUtil.updateMemberRequest(
						memberRequestKey, themeDisplay.getUserId());

					session.removeAttribute(WebKeys.MEMBER_REQEUST_KEY);
				}
			}

			List<MemberRequest> memberRequests =
				MemberRequestLocalServiceUtil.getReceiverStatusMemberRequest(
					themeDisplay.getUserId(),
					InviteMembersConstants.STATUS_PENDING, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			Iterator<MemberRequest> itr = memberRequests.iterator();

			while (itr.hasNext()) {
				MemberRequest memberRequest = itr.next();

				try {
					GroupLocalServiceUtil.getGroup(memberRequest.getGroupId());
				}
				catch (NoSuchGroupException nsge) {
					MemberRequestLocalServiceUtil.deleteMemberRequest(
						memberRequest);

					itr.remove();
				}
			}

			if (group.isUser() && memberRequests.isEmpty()) {
				renderRequest.setAttribute(
					WebKeys.PORTLET_DECORATE, Boolean.FALSE);
			}

			include(viewJSP, renderRequest, renderResponse);
		}
		catch (Exception e) {
			super.doView(renderRequest, renderResponse);
		}
	}

	public void sendInvites(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			doSendInvite(actionRequest, actionResponse);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	public void updateInvite(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			doUpdateInvite(actionRequest, actionResponse);
		}
		catch (Exception e) {
			if (e instanceof MemberRequestAlreadyUsedException ||
				e instanceof MemberRequestInvalidUserException) {

				SessionErrors.add(actionRequest, e.getClass().getName(), e);
			}
			else {
				throw e;
			}
		}
	}

	protected void doSendInvite(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		long[] receiverUserIds = getLongArray(actionRequest, "receiverUserIds");
		String[] receiverEmailAddresses = getStringArray(
			actionRequest, "receiverEmailAddresses");
		long invitedRoleId = ParamUtil.getLong(actionRequest, "invitedRoleId");
		long invitedTeamId = ParamUtil.getLong(actionRequest, "invitedTeamId");

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!UserLocalServiceUtil.hasGroupUser(
				groupId, themeDisplay.getUserId())) {

			return;
		}

		MemberRequestLocalServiceUtil.addMemberRequests(
			themeDisplay.getUserId(), groupId, receiverUserIds, invitedRoleId,
			invitedTeamId, themeDisplay);

		MemberRequestLocalServiceUtil.addMemberRequests(
			themeDisplay.getUserId(), groupId, receiverEmailAddresses,
			invitedRoleId, invitedTeamId, themeDisplay);
	}

	protected void doUpdateInvite(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long memberRequestId = ParamUtil.getLong(
			actionRequest, "memberRequestId");
		int status = ParamUtil.getInteger(actionRequest, "status");

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		MemberRequestLocalServiceUtil.updateMemberRequest(
			themeDisplay.getUserId(), memberRequestId, status);
	}

	protected long[] getLongArray(PortletRequest portletRequest, String name) {
		String value = portletRequest.getParameter(name);

		if (value == null) {
			return null;
		}

		return StringUtil.split(GetterUtil.getString(value), 0L);
	}

	protected String[] getStringArray(
		PortletRequest portletRequest, String name) {

		String value = portletRequest.getParameter(name);

		if (value == null) {
			return null;
		}

		return StringUtil.split(GetterUtil.getString(value));
	}

	private static Log _log = LogFactoryUtil.getLog(InviteMembersPortlet.class);

}