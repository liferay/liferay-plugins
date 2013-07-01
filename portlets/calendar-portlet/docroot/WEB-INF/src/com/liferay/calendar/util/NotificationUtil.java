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

package com.liferay.calendar.util;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.notification.NotificationField;
import com.liferay.calendar.notification.NotificationRecipient;
import com.liferay.calendar.notification.NotificationSender;
import com.liferay.calendar.notification.NotificationSenderFactory;
import com.liferay.calendar.notification.NotificationTemplateContext;
import com.liferay.calendar.notification.NotificationTemplateContextFactory;
import com.liferay.calendar.notification.NotificationTemplateType;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.ContentUtil;
import com.liferay.util.portlet.PortletProps;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Marcellus Tavares
 */
public class NotificationUtil {

	public static User getDefaultSenderUser(Calendar calendar)
		throws Exception {

		CalendarResource calendarResource = calendar.getCalendarResource();

		User user = UserLocalServiceUtil.getDefaultUser(
			calendarResource.getCompanyId());

		if (calendarResource.isUser()) {
			user = UserLocalServiceUtil.getUser(calendarResource.getClassPK());
		}

		return user;
	}

	public static String getDefaultTemplate(
			NotificationType notificationType,
			NotificationTemplateType notificationTemplateType,
			NotificationField notificationField)
		throws Exception {

		Filter filter = new Filter(
			notificationType.toString(), notificationTemplateType.toString());

		String propertyName =
			PortletPropsKeys.CALENDAR_NOTIFICATION_PREFIX + StringPool.PERIOD +
			notificationField.toString();

		String templatePath = PortletProps.get(propertyName, filter);

		return ContentUtil.get(templatePath);
	}

	public static String getTemplate(
			CalendarNotificationTemplate calendarNotificationTemplate,
			NotificationType notificationType,
			NotificationTemplateType notificationTemplateType,
			NotificationField notificationField)
		throws Exception {

		String defaultTemplate = getDefaultTemplate(
			notificationType, notificationTemplateType, notificationField);

		return BeanPropertiesUtil.getString(
			calendarNotificationTemplate, notificationField.toString(),
			defaultTemplate);
	}

	public static String getTemplatePropertyValue(
		CalendarNotificationTemplate calendarNotificationTemplate,
		String propertyName) {

		return getTemplatePropertyValue(
			calendarNotificationTemplate, propertyName, StringPool.BLANK);
	}

	public static String getTemplatePropertyValue(
		CalendarNotificationTemplate calendarNotificationTemplate,
		String propertyName, String defaultValue) {

		if (calendarNotificationTemplate == null) {
			return defaultValue;
		}

		UnicodeProperties notificationTypeSettingsProperties =
			calendarNotificationTemplate.
				getNotificationTypeSettingsProperties();

		return notificationTypeSettingsProperties.get(propertyName);
	}

	public static void notifyCalendarBookingRecipients(
			CalendarBooking calendarBooking, NotificationType notificationType,
			NotificationTemplateType notificationTemplateType,
			ServiceContext serviceContext)
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
					notificationType, notificationTemplateType, calendarBooking,
					user, serviceContext);

			notificationSender.sendNotification(
				notificationRecipient, notificationTemplateContext);
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

			if (notificationType == null) {
				continue;
			}

			NotificationSender notificationSender =
				NotificationSenderFactory.getNotificationSender(
					notificationType.toString());

			NotificationTemplateContext notificationTemplateContext =
				NotificationTemplateContextFactory.getInstance(
					notificationType, NotificationTemplateType.REMINDER,
					calendarBooking, user, null);

			notificationSender.sendNotification(
				notificationRecipient, notificationTemplateContext);
		}
	}

	public static String processNotificationTemplate(
			String notificationTemplate,
			Map<String, Serializable> notificationContext)
		throws Exception {

		return StringUtil.replace(
			notificationTemplate,
			new String[] {
				"[$BOOKING_END_DATE$]", "[$BOOKING_LOCATION$]",
				"[$BOOKING_START_DATE$]", "[$BOOKING_TITLE$]",
				"[$BOOKING_URL$]", "[$FROM_ADDRESS$]", "[$FROM_NAME$]",
				"[$PORTAL_URL$]", "[$PORTLET_NAME$]", "[$TO_ADDRESS$]",
				"[$TO_NAME$]"
			},
			new String[] {
				GetterUtil.getString(notificationContext.get("endTime")),
				GetterUtil.getString(notificationContext.get("location")),
				GetterUtil.getString(notificationContext.get("startTime")),
				GetterUtil.getString(notificationContext.get("title")),
				GetterUtil.getString(notificationContext.get("url")),
				GetterUtil.getString(notificationContext.get("fromAddress")),
				GetterUtil.getString(notificationContext.get("fromName")),
				GetterUtil.getString(notificationContext.get("portalUrl")),
				GetterUtil.getString(notificationContext.get("portletName")),
				GetterUtil.getString(notificationContext.get("toAddress")),
				GetterUtil.getString(notificationContext.get("toName"))
			});
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