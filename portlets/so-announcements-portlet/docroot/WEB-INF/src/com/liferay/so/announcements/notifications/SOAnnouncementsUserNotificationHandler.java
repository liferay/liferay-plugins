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

package com.liferay.so.announcements.notifications;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.announcements.model.AnnouncementsEntry;
import com.liferay.portlet.announcements.service.AnnouncementsEntryLocalServiceUtil;
import com.liferay.so.announcements.util.PortletKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

/**
 * @author Jonathan Lee
 */
public class SOAnnouncementsUserNotificationHandler
	extends BaseUserNotificationHandler {

	public SOAnnouncementsUserNotificationHandler() {
		setPortletId(PortletKeys.SO_ANNOUNCEMENTS);
	}

	@Override
	protected String getBody(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		long announcementEntryId = jsonObject.getLong("classPK");

		AnnouncementsEntry announcementEntry =
			AnnouncementsEntryLocalServiceUtil.fetchAnnouncementsEntry(
				announcementEntryId);

		if (announcementEntry == null) {
			UserNotificationEventLocalServiceUtil.deleteUserNotificationEvent(
				userNotificationEvent.getUserNotificationEventId());

			return null;
		}

		String title = serviceContext.translate(
			"x-sent-a-new-announcement",
			HtmlUtil.escape(
				PortalUtil.getUserName(
					announcementEntry.getUserId(), StringPool.BLANK)));

		return StringUtil.replace(
			getBodyTemplate(), new String[] {"[$BODY$]", "[$TITLE$]"},
			new String[] {
				HtmlUtil.escape(
					StringUtil.shorten(announcementEntry.getTitle(), 70)),
				title
			});
	}

	@Override
	protected String getLink(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		long announcementEntryId = jsonObject.getLong("classPK");

		AnnouncementsEntry announcementEntry =
			AnnouncementsEntryLocalServiceUtil.fetchAnnouncementsEntry(
				announcementEntryId);

		if (announcementEntry == null) {
			UserNotificationEventLocalServiceUtil.deleteUserNotificationEvent(
				userNotificationEvent.getUserNotificationEventId());

			return null;
		}

		Group group = null;

		String entryClassName = announcementEntry.getClassName();

		if (entryClassName.equals(Group.class.getName())) {
			group = GroupLocalServiceUtil.getGroup(
				announcementEntry.getClassPK());
		}
		else {
			ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

			User user = themeDisplay.getUser();

			group = user.getGroup();
		}

		long portletPlid = PortalUtil.getPlidFromPortletId(
			group.getGroupId(), true, PortletKeys.SO_ANNOUNCEMENTS);

		PortletURL portletURL = null;

		if (portletPlid != 0) {
			portletURL = PortletURLFactoryUtil.create(
				serviceContext.getLiferayPortletRequest(),
				PortletKeys.SO_ANNOUNCEMENTS, portletPlid,
				PortletRequest.RENDER_PHASE);
		}
		else {
			LiferayPortletResponse liferayPortletResponse =
				serviceContext.getLiferayPortletResponse();

			portletURL = liferayPortletResponse.createRenderURL(
				PortletKeys.SO_ANNOUNCEMENTS);

			portletURL.setParameter("mvcPath", "/view.jsp");
			portletURL.setWindowState(WindowState.MAXIMIZED);
		}

		return portletURL.toString();
	}

}