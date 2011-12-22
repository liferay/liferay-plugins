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

package com.liferay.calendar.service.impl;

import com.liferay.calendar.model.CalendarEvent;
import com.liferay.calendar.service.base.CalendarEventServiceBaseImpl;
import com.liferay.calendar.service.permission.CalendarEventPermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.permission.PortletPermissionUtil;

import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 */
public class CalendarEventServiceImpl extends CalendarEventServiceBaseImpl {

	public CalendarEvent addCaledarEvent(
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, int startDateMonth, int startDateDay,
			int startDateYear, int startDateHour, int startDateMinute,
			int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, int durationHour, int durationMinute,
			boolean allDay, String recurrence, String type, int remindBy,
			int firstReminder, int secondReminder,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		PortletPermissionUtil.check(
			getPermissionChecker(), serviceContext.getPlid(),
			"1_WAR_calendarportlet", ActionKeys.ADD_EVENT);

		return calendarEventLocalService.addCalendarEvent(
			getUserId(), titleMap, descriptionMap, location, startDateMonth,
			startDateDay, startDateYear, startDateHour, startDateMinute,
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			durationHour, durationMinute, allDay, recurrence, type, remindBy,
			firstReminder, secondReminder, serviceContext);
	}

	public void deleteCalendarEvent(long calendarEventId)
		throws PortalException, SystemException {

		CalendarEventPermission.check(
			getPermissionChecker(), calendarEventId, ActionKeys.DELETE);

		calendarEventLocalService.deleteCalendarEvent(calendarEventId);
	}

	public CalendarEvent getCalendarEvent(long calendarEventId)
		throws PortalException, SystemException {

		CalendarEventPermission.check(
			getPermissionChecker(), calendarEventId, ActionKeys.VIEW);

		return calendarEventLocalService.getCalendarEvent(calendarEventId);
	}

	public CalendarEvent updateCalendarEvent(
			long calendarEventId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			int durationHour, int durationMinute, boolean allDay,
			String recurrence, String type, int remindBy, int firstReminder,
			int secondReminder, ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarEventPermission.check(
			getPermissionChecker(), calendarEventId, ActionKeys.UPDATE);

		return calendarEventLocalService.updateCalendarEvent(
			calendarEventId, titleMap, descriptionMap, location, startDateMonth,
			startDateDay, startDateYear, startDateHour, startDateMinute,
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			durationHour, durationMinute, allDay, recurrence, type, remindBy,
			firstReminder, secondReminder, serviceContext);
	}

}