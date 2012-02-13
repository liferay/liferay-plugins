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

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.base.CalendarBookingServiceBaseImpl;
import com.liferay.calendar.service.permission.CalendarBookingPermission;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarBookingServiceImpl extends CalendarBookingServiceBaseImpl {

	public CalendarBooking addCalendarBooking(
			long calendarId, long parentCalendarBookingId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> locationMap, String type, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute,
			boolean allDay, String recurrence, int priority,
			boolean outOfOffice, int firstReminder, int secondReminder,
			boolean required, String requestMessage, String responseMessage,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarPermission.check(
			getPermissionChecker(), calendarId, ActionKeys.ADD_BOOKING);

		return calendarBookingLocalService.addCalendarBooking(
			getUserId(), calendarId, parentCalendarBookingId, titleMap,
			descriptionMap, locationMap, type, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, allDay,
			recurrence, priority, outOfOffice, firstReminder, secondReminder,
			required, requestMessage, responseMessage, serviceContext);
	}

	public void deleteCalendarBooking(long calendarBookingId)
		throws PortalException, SystemException {

		CalendarBookingPermission.check(
			getPermissionChecker(), calendarBookingId, ActionKeys.DELETE);

		calendarBookingLocalService.deleteCalendarBooking(calendarBookingId);
	}

	public CalendarBooking getCalendarBooking(long calendarBookingId)
		throws PortalException, SystemException {

		CalendarBookingPermission.check(
			getPermissionChecker(), calendarBookingId, ActionKeys.VIEW);

		return calendarBookingLocalService.getCalendarBooking(
			calendarBookingId);
	}

	public CalendarBooking updateCalendarBooking(
			long calendarBookingId, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> locationMap, String type, int status,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			boolean allDay, String recurrence, int priority,
			boolean outOfOffice, int firstReminder, int secondReminder,
			boolean required, String requestMessage, String responseMessage,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarBookingPermission.check(
			getPermissionChecker(), calendarBookingId, ActionKeys.UPDATE);

		return calendarBookingLocalService.updateCalendarBooking(
			getUserId(), calendarBookingId, calendarId, titleMap,
			descriptionMap, locationMap, type, status, startDateMonth,
			startDateDay, startDateYear, startDateHour, startDateMinute,
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			allDay, recurrence, priority, outOfOffice, firstReminder,
			secondReminder, required, requestMessage, responseMessage,
			serviceContext);
	}

}