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

package com.liferay.so.hook.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.announcements.model.AnnouncementsEntry;
import com.liferay.portlet.announcements.service.AnnouncementsEntryLocalServiceUtil;
import com.liferay.portlet.announcements.service.AnnouncementsEntryService;
import com.liferay.portlet.announcements.service.AnnouncementsEntryServiceWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Lee
 */
public class AnnouncementsEntryServiceImpl
	extends AnnouncementsEntryServiceWrapper {

	public AnnouncementsEntryServiceImpl(
			AnnouncementsEntryService announcementsEntryService) {

		super(announcementsEntryService);
	}

	@Override
	public AnnouncementsEntry addEntry(
			long plid, long classNameId, long classPK, String title,
			String content, String url, String type, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, int priority,
			boolean alert)
		throws PortalException, SystemException {

		AnnouncementsEntry announcementEntry = null;

		try {
			announcementEntry = super.addEntry(
				plid, classNameId, classPK, title, content, url, type,
				displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, priority, alert);
		}
		catch (PrincipalException pe) {
			String className = PortalUtil.getClassName(classNameId);

			if (className.equals(Group.class.getName())) {
				PermissionChecker permissionChecker =
					PermissionThreadLocal.getPermissionChecker();

				PortletPermissionUtil.check(
					permissionChecker, plid, PortletKeys.ANNOUNCEMENTS,
					ActionKeys.ADD_ENTRY);

				announcementEntry = AnnouncementsEntryLocalServiceUtil.addEntry(
					permissionChecker.getUserId(), classNameId, classPK, title,
					content, url, type, displayDateMonth, displayDateDay,
					displayDateYear, displayDateHour, displayDateMinute,
					expirationDateMonth, expirationDateDay, expirationDateYear,
					expirationDateHour, expirationDateMinute, priority, alert);
			}
			else {
				throw pe;
			}
		}

		if (announcementEntry != null) {
			sendNotificationEvent(announcementEntry);
		}

		return announcementEntry;
	}

	protected void sendNotificationEvent(AnnouncementsEntry announcementEntry)
		throws PortalException, SystemException {

		JSONObject notificationEventJSON = JSONFactoryUtil.createJSONObject();

		notificationEventJSON.put("body", announcementEntry.getTitle());
		notificationEventJSON.put("entryId", announcementEntry.getEntryId());
		notificationEventJSON.put("groupId", announcementEntry.getClassPK());
		notificationEventJSON.put("portletId", PortletKeys.ANNOUNCEMENTS);
		notificationEventJSON.put("title", "x-sent-a-new-announcement");
		notificationEventJSON.put("userId", announcementEntry.getUserId());

		NotificationEvent notificationEvent =
			NotificationEventFactoryUtil.createNotificationEvent(
				System.currentTimeMillis(), "6_WAR_soportlet",
				notificationEventJSON);

		notificationEvent.setDeliveryRequired(0);

		List<User> users = new ArrayList<User>();

		if (announcementEntry.getClassNameId() == 0) {
			users = UserLocalServiceUtil.getUsers(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		else {
			String className = PortalUtil.getClassName(
				announcementEntry.getClassNameId());

			if (className.equals(Group.class.getName())) {
				users = UserLocalServiceUtil.getGroupUsers(
					announcementEntry.getClassPK());
			}
			else if (className.equals(Organization.class.getName())) {
				users = UserLocalServiceUtil.getOrganizationUsers(
					announcementEntry.getClassPK());
			}
			else if (className.equals(Role.class.getName())) {
				users = UserLocalServiceUtil.getRoleUsers(
					announcementEntry.getClassPK());
			}
			else if (className.equals(UserGroup.class.getName())) {
				users = UserLocalServiceUtil.getUserGroupUsers(
					announcementEntry.getClassPK());
			}
		}

		for (User user : users) {
			ChannelHubManagerUtil.sendNotificationEvent(
				user.getCompanyId(), user.getUserId(), notificationEvent);
		}
	}

}