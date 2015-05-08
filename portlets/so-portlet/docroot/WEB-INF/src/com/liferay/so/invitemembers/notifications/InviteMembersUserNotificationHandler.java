/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.so.invitemembers.notifications;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.MembershipRequestConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.so.model.MemberRequest;
import com.liferay.so.service.MemberRequestLocalServiceUtil;
import com.liferay.so.util.PortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

/**
 * @author Jonathan Lee
 */
public class InviteMembersUserNotificationHandler
	extends BaseUserNotificationHandler {

	public InviteMembersUserNotificationHandler() {
		setActionable(true);
		setPortletId(PortletKeys.SO_INVITE_MEMBERS);
	}

	@Override
	protected String getBody(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		long memberRequestId = jsonObject.getLong("classPK");

		MemberRequest memberRequest =
			MemberRequestLocalServiceUtil.fetchMemberRequest(memberRequestId);

		Group group = null;

		if (memberRequest != null) {
			group = GroupLocalServiceUtil.fetchGroup(
				memberRequest.getGroupId());
		}

		if ((group == null) || (memberRequest == null)) {
			UserNotificationEventLocalServiceUtil.deleteUserNotificationEvent(
				userNotificationEvent.getUserNotificationEventId());

			return null;
		}

		String title = StringPool.BLANK;

		if (memberRequest.getStatus() ==
				MembershipRequestConstants.STATUS_PENDING) {

			title = serviceContext.translate(
				"x-invited-you-to-join-x",
				new Object[] {
					getUserNameLink(memberRequest.getUserId(), serviceContext),
					getSiteDescriptiveName(
						memberRequest.getGroupId(), serviceContext)
					});
		}

		LiferayPortletResponse liferayPortletResponse =
			serviceContext.getLiferayPortletResponse();

		PortletURL confirmURL = liferayPortletResponse.createActionURL(
			PortletKeys.SO_INVITE_MEMBERS);

		confirmURL.setParameter(
			ActionRequest.ACTION_NAME, "updateMemberRequest");
		confirmURL.setParameter(
			"memberRequestId", String.valueOf(memberRequestId));
		confirmURL.setParameter(
			"status",
			String.valueOf(MembershipRequestConstants.STATUS_APPROVED));
		confirmURL.setParameter(
			"userNotificationEventId",
			String.valueOf(userNotificationEvent.getUserNotificationEventId()));
		confirmURL.setWindowState(WindowState.NORMAL);

		PortletURL ignoreURL = liferayPortletResponse.createActionURL(
			PortletKeys.SO_INVITE_MEMBERS);

		ignoreURL.setParameter(
			ActionRequest.ACTION_NAME, "updateMemberRequest");
		ignoreURL.setParameter(
			"memberRequestId", String.valueOf(memberRequestId));
		ignoreURL.setParameter(
			"status", String.valueOf(MembershipRequestConstants.STATUS_DENIED));
		ignoreURL.setParameter(
			"userNotificationEventId",
			String.valueOf(userNotificationEvent.getUserNotificationEventId()));
		ignoreURL.setWindowState(WindowState.NORMAL);

		return StringUtil.replace(
			getBodyTemplate(),
			new String[] {
				"[$CONFIRM$]", "[$CONFIRM_URL$]", "[$IGNORE$]",
				"[$IGNORE_URL$]", "[$TITLE$]"
			},
			new String[] {
				serviceContext.translate("confirm"), confirmURL.toString(),
				serviceContext.translate("ignore"), ignoreURL.toString(), title
			});
	}

	@Override
	protected String getLink(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		return StringPool.BLANK;
	}

	protected String getSiteDescriptiveName(
			long groupId, ServiceContext serviceContext)
		throws Exception {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		StringBundler sb = new StringBundler(6);

		sb.append("<a");

		if (group.hasPublicLayouts()) {
			sb.append(" href=\"");

			sb.append(
				group.getDisplayURL(serviceContext.getThemeDisplay(), false));

			sb.append("\">");
		}
		else {
			sb.append(">");
		}

		sb.append(
			HtmlUtil.escape(
				group.getDescriptiveName(serviceContext.getLocale())));
		sb.append("</a>");

		return sb.toString();
	}

	protected String getUserNameLink(
		long userId, ServiceContext serviceContext) {

		try {
			if (userId <= 0) {
				return StringPool.BLANK;
			}

			User user = UserLocalServiceUtil.getUserById(userId);

			String userName = user.getFullName();

			String userDisplayURL = user.getDisplayURL(
				serviceContext.getThemeDisplay());

			return "<a href=\"" + userDisplayURL + "\">" +
				HtmlUtil.escape(userName) + "</a>";
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

}