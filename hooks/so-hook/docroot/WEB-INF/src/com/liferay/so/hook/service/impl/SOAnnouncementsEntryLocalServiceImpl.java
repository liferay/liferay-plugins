/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

/**
 * @author Jonathan Lee
 * @author Evan Thibodeau
 */
public class SOAnnouncementsEntryLocalServiceImpl
	extends AnnouncementsEntryLocalServiceWrapper {

	public SOAnnouncementsEntryLocalServiceImpl(
		AnnouncementsEntryLocalService announcementsEntryLocalService) {

		super(announcementsEntryLocalService);
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

		AnnouncementsEntry announcementEntry = super.addEntry(
			plid, classNameId, classPK, title, content, url, type,
			displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, priority, alert);

		if (announcementEntry != null) {
			Date displayDate = announcementEntry.getDisplayDate();

			if (!displayDate.after(announcementEntry.getCreateDate())) {
				sendNotificationEvent(announcementEntry);
			}
		}

		return announcementEntry;
	}

	@Override
	public void checkEntries() throws PortalException, SystemException {
		super.checkEntries();
	}

	protected void sendNotificationEvent(AnnouncementsEntry announcementEntry)
		throws PortalException, SystemException {

		JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		notificationEventJSONObject.put("body", announcementEntry.getTitle());
		notificationEventJSONObject.put(
			"entryId", announcementEntry.getEntryId());
		notificationEventJSONObject.put(
			"groupId", announcementEntry.getClassPK());
		notificationEventJSONObject.put("portletId", PortletKeys.ANNOUNCEMENTS);
		notificationEventJSONObject.put("title", "x-sent-a-new-announcement");
		notificationEventJSONObject.put(
			"userId", announcementEntry.getUserId());

		List<User> users = Collections.emptyList();

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
			NotificationEvent notificationEvent =
				NotificationEventFactoryUtil.createNotificationEvent(
					System.currentTimeMillis(), "6_WAR_soportlet",
					notificationEventJSONObject);

			notificationEvent.setDeliveryRequired(0);

			ChannelHubManagerUtil.sendNotificationEvent(
				user.getCompanyId(), user.getUserId(), notificationEvent);
		}
	}

}