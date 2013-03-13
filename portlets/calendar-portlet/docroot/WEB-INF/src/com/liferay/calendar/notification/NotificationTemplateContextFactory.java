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

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.util.NotificationUtil;
import com.liferay.calendar.util.PortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.text.Format;

import java.util.Enumeration;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

/**
 * @author Eduardo Lundgren
 */
public class NotificationTemplateContextFactory {

	public static NotificationTemplateContext getInstance(
			CalendarBooking calendarBooking)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(calendarBooking.getUserId());

		return getInstance(calendarBooking, user);
	}

	public static NotificationTemplateContext getInstance(
			CalendarBooking calendarBooking, User user)
		throws PortalException, SystemException {

		NotificationTemplateContext notificationTemplateContext =
			new NotificationTemplateContext();

		Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
			user.getLocale(), user.getTimeZone());

		long endTime = calendarBooking.getEndTime();

		notificationTemplateContext.setAttribute(
			"endTime", dateFormatDateTime.format(endTime));

		PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				calendarBooking.getCompanyId(), calendarBooking.getGroupId(),
				PortletKeys.PREFS_OWNER_TYPE_GROUP,
				PortletKeys.PREFS_PLID_SHARED, PortletKeys.CALENDAR, null);

		String fromAddress = NotificationUtil.getEmailFromAddress(
			portletPreferences, calendarBooking.getCompanyId());
		String fromName = NotificationUtil.getEmailFromName(
			portletPreferences, calendarBooking.getCompanyId());

		notificationTemplateContext.setAttribute("fromAddress", fromAddress);
		notificationTemplateContext.setAttribute("fromName", fromName);

		notificationTemplateContext.setAttribute(
			"location", calendarBooking.getLocation());

		Company company = CompanyLocalServiceUtil.getCompany(
			calendarBooking.getCompanyId());

		notificationTemplateContext.setAttribute(
			"portalUrl", company.getPortalURL(calendarBooking.getGroupId()));

		notificationTemplateContext.setAttribute(
			"portletName",
			LanguageUtil.get(
				getPortletConfig(), user.getLocale(),
				"javax.portlet.title.".concat(PortletKeys.CALENDAR)));

		long startTime = calendarBooking.getStartTime();

		notificationTemplateContext.setAttribute(
			"startTime", dateFormatDateTime.format(startTime));

		notificationTemplateContext.setAttribute(
			"title", calendarBooking.getTitle(user.getLocale()));
		notificationTemplateContext.setAttribute(
			"toAddress", user.getEmailAddress());
		notificationTemplateContext.setAttribute("toName", user.getFullName());

		Enumeration<String> enu = portletPreferences.getNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			String value = GetterUtil.getString(
				portletPreferences.getValue(name, StringPool.BLANK));

			notificationTemplateContext.setAttribute(name, value);
		}

		notificationTemplateContext.setCompanyId(
			calendarBooking.getCompanyId());
		notificationTemplateContext.setGroupId(calendarBooking.getGroupId());

		return notificationTemplateContext;
	}

	public static PortletConfig getPortletConfig() {
		return _portletConfig;
	}

	public static void setPortletConfig(PortletConfig portletConfig) {
		_portletConfig = portletConfig;
	}

	private static PortletConfig _portletConfig;

}