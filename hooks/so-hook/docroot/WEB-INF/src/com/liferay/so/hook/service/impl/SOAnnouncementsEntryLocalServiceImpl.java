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

package com.liferay.so.hook.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portlet.announcements.model.AnnouncementsEntry;
import com.liferay.portlet.announcements.service.AnnouncementsEntryLocalService;
import com.liferay.portlet.announcements.service.AnnouncementsEntryLocalServiceWrapper;
import com.liferay.portlet.announcements.service.persistence.AnnouncementsEntryFinderUtil;
import com.liferay.so.util.PortletKeys;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

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
			long userId, long classNameId, long classPK, String title,
			String content, String url, String type, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, boolean displayImmediately,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, int priority, boolean alert)
		throws PortalException {

		AnnouncementsEntry announcementEntry = super.addEntry(
			userId, classNameId, classPK, title, content, url, type,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, displayImmediately, expirationDateMonth,
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
	public void checkEntries() throws PortalException {
		super.checkEntries();

		sendNotificationEvent();
	}

	protected void sendNotificationEvent() throws PortalException {
		Date now = new Date();

		if (_previousCheckDate == null) {
			_previousCheckDate = new Date(
				now.getTime() - _ANNOUNCEMENTS_ENTRY_CHECK_INTERVAL);
		}

		List<AnnouncementsEntry> announcementEntries =
			AnnouncementsEntryFinderUtil.findByDisplayDate(
				now, _previousCheckDate);

		if (_log.isDebugEnabled()) {
			_log.debug("Processing " + announcementEntries.size() + " entries");
		}

		for (AnnouncementsEntry announcementEntry : announcementEntries) {
			Date displayDate = announcementEntry.getDisplayDate();

			if (displayDate.after(announcementEntry.getCreateDate())) {
				sendNotificationEvent(announcementEntry);
			}
		}

		_previousCheckDate = now;
	}

	protected void sendNotificationEvent(AnnouncementsEntry announcementEntry)
		throws PortalException {

		JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		notificationEventJSONObject.put(
			"classPK", announcementEntry.getEntryId());
		notificationEventJSONObject.put(
			"userId", announcementEntry.getUserId());

		MessageBusUtil.sendMessage(
			DestinationNames.ASYNC_SERVICE,
			new NotificationProcessCallable(
				announcementEntry, notificationEventJSONObject));
	}

	private static final long _ANNOUNCEMENTS_ENTRY_CHECK_INTERVAL =
		GetterUtil.getInteger(
			PropsUtil.get(
				PropsKeys.ANNOUNCEMENTS_ENTRY_CHECK_INTERVAL)) * Time.MINUTE;

	private static Log _log = LogFactoryUtil.getLog(
		SOAnnouncementsEntryLocalServiceImpl.class);

	private Date _previousCheckDate;

	private static class NotificationProcessCallable
		implements ProcessCallable<Serializable> {

		public NotificationProcessCallable(
			AnnouncementsEntry announcementEntry,
			JSONObject notificationEventJSONObject) {

			_announcementEntry = announcementEntry;
			_notificationEventJSONObject = notificationEventJSONObject;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				sendUserNotifications(
					_announcementEntry, _notificationEventJSONObject);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		protected void sendUserNotifications(
				AnnouncementsEntry announcementEntry,
				JSONObject notificationEventJSONObject)
			throws PortalException {

			int count = 0;
			long teamId = 0;

			LinkedHashMap<String, Object> params = new LinkedHashMap<>();

			if (announcementEntry.getClassNameId() == 0) {
				count = UserLocalServiceUtil.getUsersCount();
			}
			else {
				String className = announcementEntry.getClassName();

				long classPK = announcementEntry.getClassPK();

				if (classPK <= 0) {
					return;
				}

				if (className.equals(Group.class.getName())) {
					params.put("inherit", Boolean.TRUE);
					params.put("usersGroups", classPK);
				}
				else if (className.equals(Organization.class.getName())) {
					Organization organization =
						OrganizationLocalServiceUtil.fetchOrganization(classPK);

					if (organization == null) {
						return;
					}

					params.put(
						"usersOrgsTree",
						ListUtil.fromArray(new Organization[] {organization}));
				}
				else if (className.equals(Role.class.getName())) {
					Role role = RoleLocalServiceUtil.fetchRole(classPK);

					if (role == null) {
						return;
					}

					if (role.getType() == RoleConstants.TYPE_REGULAR) {
						params.put("inherit", Boolean.TRUE);
						params.put("usersRoles", classPK);
					}
					else if (role.isTeam()) {
						teamId = role.getClassPK();

						count = UserLocalServiceUtil.getTeamUsersCount(teamId);
					}
					else {
						params.put(
							"userGroupRole",
							new Long[] {Long.valueOf(0), classPK});
					}
				}
				else if (className.equals(UserGroup.class.getName())) {
					params.put("usersUserGroups", classPK);
				}

				if (!params.isEmpty()) {
					count = UserLocalServiceUtil.searchCount(
						announcementEntry.getCompanyId(), null,
						WorkflowConstants.STATUS_APPROVED, params);
				}
			}

			int pages = count / Indexer.DEFAULT_INTERVAL;

			for (int i = 0; i <= pages; i++) {
				List<User> users = null;

				int start = (i * Indexer.DEFAULT_INTERVAL);
				int end = start + Indexer.DEFAULT_INTERVAL;

				if (announcementEntry.getClassNameId() == 0) {
					users = UserLocalServiceUtil.getUsers(start, end);
				}
				else if (teamId > 0) {
					users = UserLocalServiceUtil.getTeamUsers(
						teamId, start, end);
				}
				else {
					users = UserLocalServiceUtil.search(
						announcementEntry.getCompanyId(), null,
						WorkflowConstants.STATUS_APPROVED, params, start, end,
						(OrderByComparator)null);
				}

				for (User user : users) {
					if (UserNotificationManagerUtil.isDeliver(
							user.getUserId(), PortletKeys.SO_ANNOUNCEMENTS, 0,
							0,
							UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

						UserNotificationEventLocalServiceUtil.
							sendUserNotificationEvents(
								user.getUserId(), PortletKeys.SO_ANNOUNCEMENTS,
								UserNotificationDeliveryConstants.TYPE_WEBSITE,
								notificationEventJSONObject);
					}
				}
			}
		}

		private static final long serialVersionUID = 1L;

		private AnnouncementsEntry _announcementEntry;
		private JSONObject _notificationEventJSONObject;

	}

}