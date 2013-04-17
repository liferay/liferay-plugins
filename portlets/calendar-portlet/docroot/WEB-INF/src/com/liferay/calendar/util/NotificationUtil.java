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
import com.liferay.calendar.service.CalendarNotificationTemplateLocalServiceUtil;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.PortalException;
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
import com.liferay.util.ContentUtil;
import com.liferay.util.portlet.PortletProps;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Eduardo Lundgren
 */
public class NotificationUtil {

	public static String getNotificationSenderEmailAddress(
			CalendarNotificationTemplate calendarNotificationTemplate)
		throws PortalException, SystemException {

		return calendarNotificationTemplate
				.getNotificationSettingsProperties()
				.getProperty(PortletPropsKeys.CALENDAR_NOTIFICATION_SENDER_EMAIL);
	}

	public static String getNotificationSenderName(
			CalendarNotificationTemplate calendarNotificationTemplate)
		throws PortalException, SystemException {

		return calendarNotificationTemplate
				.getNotificationSettingsProperties()
				.getProperty(PortletPropsKeys.CALENDAR_NOTIFICATION_SENDER_NAME);
	}

	public static CalendarNotificationTemplate getNotificationTemplate(
			Calendar calendar, NotificationType notificationType,
			NotificationTemplateType templateType)
		throws PortalException, SystemException {

		try {
			CalendarNotificationTemplate template =
					CalendarNotificationTemplateLocalServiceUtil
					.getCalendarNotificationTemplate(
							calendar.getCalendarId(), notificationType,
							templateType);
			if (Validator.isNotNull(template) &&
				Validator.isNotNull(template.getSubject()) &&
				Validator.isNotNull(template.getBody())) {

				return template;
			}

		} catch (SystemException e) {
			e.printStackTrace();
		}


		return getDefaultNotificationTemplate(
			calendar, notificationType, templateType);
	}

	public static CalendarNotificationTemplate getDefaultNotificationTemplate(
			Calendar calendar,	NotificationType notificationType,
			NotificationTemplateType templateType) {

		Filter filter = new Filter(
			notificationType.toString(), templateType.toString());

		String bodyProperty = PortletPropsKeys.CALENDAR_NOTIFICATION_PREFIX +
				".body";
		String bodyTemplatePath = PortletProps.get(bodyProperty, filter);
		String body = ContentUtil.get(bodyTemplatePath);
		String subjectProperty = PortletPropsKeys.CALENDAR_NOTIFICATION_PREFIX +
				".subject";
		String subjectTemplatePath = PortletProps.get(subjectProperty, filter);
		String subject = ContentUtil.get(subjectTemplatePath);

		String settings = getCalendarDefaultNotificationSettings(calendar);


		CalendarNotificationTemplate template =
				CalendarNotificationTemplateLocalServiceUtil.
				createCalendarNotificationTemplate(
						0, notificationType, templateType,
						subject, body, settings);

		return template;
	}

	public static String getCalendarDefaultNotificationSettings(
			Calendar calendar) {

		String settings = "";
		try {
			User user = UserLocalServiceUtil.getUser(calendar.getUserId());
			Properties properties = new Properties();

			properties.setProperty(
					PortletPropsKeys.CALENDAR_NOTIFICATION_SENDER_EMAIL,
					user.getEmailAddress());
			properties.setProperty(
					PortletPropsKeys.CALENDAR_NOTIFICATION_SENDER_NAME,
					calendar.getUserName());

			Writer writer = new StringWriter();
			properties.list(new PrintWriter(writer));
			settings = writer.toString();
		} catch (Exception e) {
			settings = "";
		}

		return settings;
	}

	public static String getPreferenceName(
		NotificationType notificationType,
		NotificationTemplateType notificationTemplateType,
		NotificationField field) {

		StringBundler sb = new StringBundler(3);

		sb.append(CamelCaseUtil.toCamelCase(field.getValue(), CharPool.PERIOD));
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

	public static void saveNotificationTemplate(
			long calendarId, NotificationType notificationType,
			NotificationTemplateType templateType,
			String subject, String body, String notificationSettings)
		throws SystemException {

		CalendarNotificationTemplate template =
				CalendarNotificationTemplateLocalServiceUtil
				.getCalendarNotificationTemplate(
						calendarId, notificationType, templateType);

		if (Validator.isNotNull(template)) {
			template.setSubject(subject);
			template.setBody(body);
			template.setNotificationSettings(notificationSettings);

			CalendarNotificationTemplateLocalServiceUtil
				.updateCalendarNotificationTemplate(template);
		}
		else {
			CalendarNotificationTemplateLocalServiceUtil
				.addCalendarNotificationTemplate(calendarId,
					notificationType, templateType,
					subject, body, notificationSettings);
		}
	}

	public static Calendar getRootCalendar(CalendarBooking calendarBooking)
		throws PortalException, SystemException {

		long currertId = calendarBooking.getCalendarBookingId();
		long parentId = calendarBooking.getParentCalendarBookingId();
		Calendar calendar;

		if (currertId == parentId || parentId == 0) {
			calendar = calendarBooking.getCalendar();
		}
		else {
			CalendarBooking parent = calendarBooking.getParentCalendarBooking();

			calendar = getRootCalendar(parent);
		}

		return calendar;
	}

	private static final long _CHECK_INTERVAL =
		PortletPropsValues.CALENDAR_NOTIFICATION_CHECK_INTERVAL * Time.MINUTE;

}