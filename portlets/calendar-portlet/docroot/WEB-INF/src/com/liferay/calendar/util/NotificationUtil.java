/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.calendar.util;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.notification.NotificationRecipient;
import com.liferay.calendar.notification.NotificationSender;
import com.liferay.calendar.notification.NotificationSenderFactory;
import com.liferay.calendar.notification.NotificationTemplateContext;
import com.liferay.calendar.notification.NotificationTemplateContextFactory;
import com.liferay.calendar.notification.NotificationTemplateType;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CamelCaseUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.ContentUtil;
import com.liferay.util.portlet.PortletProps;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Eduardo Lundgren
 */
public class NotificationUtil {

	public static String getEmailFromAddress(
			PortletPreferences preferences, long companyId)
		throws SystemException {

		return PortalUtil.getEmailFromAddress(
			preferences, companyId,
			PortletPropsValues.CALENDAR_NOTIFICATION_FROM_ADDRESS);
	}

	public static String getEmailFromName(
			PortletPreferences preferences, long companyId)
		throws SystemException {

		return PortalUtil.getEmailFromName(
			preferences, companyId,
			PortletPropsValues.CALENDAR_NOTIFICATION_FROM_NAME);
	}

	public static String getNotificationTemplateContent(
		String propertyName, NotificationType notificationType,
		NotificationTemplateType notificationTemplateType) {

		Filter filter = new Filter(
			notificationType.toString(), notificationTemplateType.toString());

		String templatePath = PortletProps.get(propertyName, filter);

		return ContentUtil.get(templatePath);
	}

	public static String getNotificationTemplateContent(
		String propertyName, NotificationType notificationType,
		NotificationTemplateType notificationTemplateType,
		NotificationTemplateContext notificationTemplateContext) {

		String preferenceName = getPreferenceName(
			propertyName, notificationType, notificationTemplateType);

		String value = notificationTemplateContext.getString(preferenceName);

		if (Validator.isNotNull(value)) {
			return value;
		}

		return getNotificationTemplateContent(
			propertyName, notificationType, notificationTemplateType);
	}

	public static String getPreferenceName(
		String propertyName, NotificationType notificationType,
		NotificationTemplateType notificationTemplateType) {

		StringBundler sb = new StringBundler(3);

		sb.append(CamelCaseUtil.toCamelCase(propertyName, CharPool.PERIOD));
		sb.append(StringUtil.upperCaseFirstLetter(notificationType.toString()));
		sb.append(
			StringUtil.upperCaseFirstLetter(
				notificationTemplateType.toString()));

		return sb.toString();
	}

	public static void notifyCalendarBookingInvites(
			CalendarBooking calendarBooking, NotificationType notificationType)
		throws Exception {

		NotificationSender notificationSender =
			NotificationSenderFactory.getNotificationSender(
				notificationType.toString());

		List<NotificationRecipient> notificationRecipients =
			_getNotificationRecipients(calendarBooking);

		for (NotificationRecipient notificationRecipient :
				notificationRecipients) {

			User user = notificationRecipient.getUser();

			NotificationTemplateContext notificationTemplateContext =
				NotificationTemplateContextFactory.getInstance(
					calendarBooking, user);

			notificationSender.sendNotification(
				notificationRecipient, NotificationTemplateType.INVITE,
				notificationTemplateContext);
		}
	}

	public static void notifyCalendarBookingReminders(
			CalendarBooking calendarBooking, long nowTime)
		throws Exception {

		List<NotificationRecipient> notificationRecipients =
			_getNotificationRecipients(calendarBooking);

		for (NotificationRecipient notificationRecipient :
				notificationRecipients) {

			User user = notificationRecipient.getUser();

			long startTime = calendarBooking.getStartTime();

			if (nowTime > startTime) {
				return;
			}

			NotificationTemplateContext notificationTemplateContext =
				NotificationTemplateContextFactory.getInstance(
					calendarBooking, user);

			NotificationType notificationType = null;

			long diff = (startTime - nowTime) / _CHECK_INTERVAL;

			if (diff ==
					(calendarBooking.getFirstReminder() / _CHECK_INTERVAL)) {

				notificationType =
					calendarBooking.getFirstReminderNotificationType();
			}
			else if (diff ==
						(calendarBooking.getSecondReminder() /
							_CHECK_INTERVAL)) {

				notificationType =
					calendarBooking.getSecondReminderNotificationType();
			}

			if (notificationType != null) {
				NotificationSender notificationSender =
					NotificationSenderFactory.getNotificationSender(
						notificationType.toString());

				notificationSender.sendNotification(
					notificationRecipient, NotificationTemplateType.REMINDER,
					notificationTemplateContext);
			}
		}
	}

	private static List<NotificationRecipient> _getNotificationRecipients(
			CalendarBooking calendarBooking)
		throws Exception {

		Calendar calendar = calendarBooking.getCalendar();

		CalendarResource calendarResource =
			calendarBooking.getCalendarResource();

		List<Role> roles = RoleLocalServiceUtil.getResourceBlockRoles(
			calendar.getResourceBlockId(), Calendar.class.getName(),
			ActionKeys.MANAGE_BOOKINGS);

		List<NotificationRecipient> notificationRecipients =
			new ArrayList<NotificationRecipient>();

		for (Role role : roles) {
			String name = role.getName();

			if (name.equals(RoleConstants.OWNER)) {
				User calendarResourceUser = UserLocalServiceUtil.getUser(
					calendarResource.getUserId());

				notificationRecipients.add(
					new NotificationRecipient(calendarResourceUser));

				User calendarUser = UserLocalServiceUtil.getUser(
					calendar.getUserId());

				if (calendarResourceUser.getUserId() !=
						calendarUser.getUserId()) {

					notificationRecipients.add(
						new NotificationRecipient(calendarUser));
				}
			}
			else {
				List<User> roleUsers = UserLocalServiceUtil.getRoleUsers(
					role.getRoleId());

				for (User roleUser : roleUsers) {
					PermissionChecker permissionChecker =
						PermissionCheckerFactoryUtil.create(roleUser);

					if (!CalendarPermission.contains(
							permissionChecker, calendar,
							ActionKeys.MANAGE_BOOKINGS)) {

						continue;
					}

					notificationRecipients.add(
						new NotificationRecipient(roleUser));
				}
			}
		}

		return notificationRecipients;
	}

	private static final long _CHECK_INTERVAL =
		PortletPropsValues.CALENDAR_NOTIFICATION_CHECK_INTERVAL * Time.MINUTE;

}