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

package com.liferay.calendar.notification;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.service.CalendarNotificationTemplateLocalServiceUtil;
import com.liferay.calendar.util.NotificationUtil;
import com.liferay.calendar.util.PortletKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.PortletURLFactoryUtil;

import java.io.Serializable;

import java.text.Format;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.ActionRequest;
import javax.portlet.PortletConfig;
import javax.portlet.PortletURL;

/**
 * @author Eduardo Lundgren
 */
public class NotificationTemplateContextFactory {

	public static NotificationTemplateContext getInstance(
			NotificationType notificationType,
			NotificationTemplateType notificationTemplateType,
			CalendarBooking calendarBooking, User user,
			ServiceContext serviceContext)
		throws Exception {

		CalendarBooking parentCalendarBooking =
			calendarBooking.getParentCalendarBooking();

		Calendar calendar = parentCalendarBooking.getCalendar();

		NotificationTemplateContext notificationTemplateContext =
			new NotificationTemplateContext();

		notificationTemplateContext.setCompanyId(
			calendarBooking.getCompanyId());
		notificationTemplateContext.setGroupId(calendarBooking.getGroupId());
		notificationTemplateContext.setCalendarId(calendar.getCalendarId());
		notificationTemplateContext.setNotificationType(notificationType);

		// Attributes

		Map<String, Serializable> attributes =
			new HashMap<String, Serializable>();

		TimeZone userTimezone = user.getTimeZone();

		Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
			user.getLocale(), userTimezone);

		String userTimezoneDisplayName = userTimezone.getDisplayName(
			false, TimeZone.SHORT, user.getLocale());

		String endTime =
			dateFormatDateTime.format(calendarBooking.getEndTime()) +
				StringPool.SPACE + userTimezoneDisplayName;

		attributes.put("endTime", endTime);

		attributes.put("location", calendarBooking.getLocation());

		Company company = CompanyLocalServiceUtil.getCompany(
			calendarBooking.getCompanyId());

		attributes.put(
			"portalUrl", company.getPortalURL(calendarBooking.getGroupId()));
		attributes.put(
			"portletName",
			LanguageUtil.get(
				getPortletConfig(), user.getLocale(),
				"javax.portlet.title.".concat(PortletKeys.CALENDAR)));

		String startTime =
			dateFormatDateTime.format(calendarBooking.getStartTime()) +
				StringPool.SPACE + userTimezoneDisplayName;

		attributes.put("startTime", startTime);

		attributes.put("title", calendarBooking.getTitle(user.getLocale()));
		attributes.put("toAddress", user.getEmailAddress());
		attributes.put("toName", user.getFullName());

		String calendarBookingURL = _getCalendarBookingURL(
			calendarBooking.getCalendarBookingId(), serviceContext);

		attributes.put("url", calendarBookingURL);

		notificationTemplateContext.setAttributes(attributes);

		// Content

		CalendarNotificationTemplate calendarNotificationTemplate =
			CalendarNotificationTemplateLocalServiceUtil.
				fetchCalendarNotificationTemplate(
					calendar.getCalendarId(), notificationType,
					notificationTemplateType);

		notificationTemplateContext.setCalendarNotificationTemplate(
			calendarNotificationTemplate);

		String templateSubject = NotificationUtil.getTemplate(
			calendarNotificationTemplate, notificationType,
			notificationTemplateType, NotificationField.SUBJECT);

		String subject = NotificationUtil.processNotificationTemplate(
			templateSubject, attributes);

		notificationTemplateContext.setSubject(subject);

		String templateBody = NotificationUtil.getTemplate(
			calendarNotificationTemplate, notificationType,
			notificationTemplateType, NotificationField.BODY);

		String body = NotificationUtil.processNotificationTemplate(
			templateBody, attributes);

		notificationTemplateContext.setBody(body);

		return notificationTemplateContext;
	}

	public static PortletConfig getPortletConfig() {
		return _portletConfig;
	}

	public static void setPortletConfig(PortletConfig portletConfig) {
		_portletConfig = portletConfig;
	}

	private static String _getCalendarBookingURL(
		long calendarBookingId, ServiceContext serviceContext) {

		if (serviceContext == null) {
			return StringPool.BLANK;
		}

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		if (themeDisplay == null) {
			return StringPool.BLANK;
		}

		PortletURL portletURL = PortletURLFactoryUtil.create(
			serviceContext.getRequest(), PortletKeys.CALENDAR,
			themeDisplay.getPlid(), ActionRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/view_calendar_booking.jsp");
		portletURL.setParameter(
			"calendarBookingId", String.valueOf(calendarBookingId));

		return portletURL.toString();
	}

	private static PortletConfig _portletConfig;

}