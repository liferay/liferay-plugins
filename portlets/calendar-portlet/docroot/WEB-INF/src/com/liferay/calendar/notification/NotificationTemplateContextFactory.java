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

import java.util.Date;
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

		long companyId = calendarBooking.getCompanyId();

		Company company = CompanyLocalServiceUtil.getCompany(companyId);

		NotificationTemplateContext notificationTemplateContext =
			new NotificationTemplateContext();

		notificationTemplateContext.setCompanyId(companyId);
		notificationTemplateContext.setGroupId(calendarBooking.getGroupId());

		Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
			user.getLocale(), user.getTimeZone());

		Date endDate = calendarBooking.getEndDate();
		Date startDate = calendarBooking.getStartDate();

		notificationTemplateContext.setAttribute(
			"endDate", dateFormatDateTime.format(endDate.getTime()));
		notificationTemplateContext.setAttribute(
			"location", calendarBooking.getLocation());
		notificationTemplateContext.setAttribute(
			"portalUrl", company.getPortalURL(calendarBooking.getGroupId()));
		notificationTemplateContext.setAttribute(
			"startDate", dateFormatDateTime.format(startDate.getTime()));
		notificationTemplateContext.setAttribute(
			"title", calendarBooking.getTitle(user.getLocale()));
		notificationTemplateContext.setAttribute(
			"toAddress", user.getEmailAddress());
		notificationTemplateContext.setAttribute("toName", user.getFullName());
		notificationTemplateContext.setAttribute(
			"portletName", LanguageUtil.get(
				getPortletConfig(), user.getLocale(),
				"javax.portlet.title.".concat(PortletKeys.CALENDAR)));

		PortletPreferences preferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				companyId, calendarBooking.getGroupId(),
				PortletKeys.PREFS_OWNER_TYPE_GROUP,
				PortletKeys.PREFS_PLID_SHARED, PortletKeys.CALENDAR, null);

		String fromAddress = NotificationUtil.getEmailFromAddress(
			preferences, companyId);

		String fromName = NotificationUtil.getEmailFromName(
			preferences, companyId);

		notificationTemplateContext.setAttribute("fromAddress", fromAddress);
		notificationTemplateContext.setAttribute("fromName", fromName);

		Enumeration<String> enu = preferences.getNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			String value = GetterUtil.getString(
				preferences.getValue(name, StringPool.BLANK));

			notificationTemplateContext.setAttribute(name, value);
		}

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