/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.calendar.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CalendarEventService}.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarEventService
 * @generated
 */
public class CalendarEventServiceWrapper implements CalendarEventService,
	ServiceWrapper<CalendarEventService> {
	public CalendarEventServiceWrapper(
		CalendarEventService calendarEventService) {
		_calendarEventService = calendarEventService;
	}

	public com.liferay.calendar.model.CalendarEvent addCaledarEvent(
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int durationHour, int durationMinute,
		boolean allDay, java.lang.String recurrence, java.lang.String type,
		int remindBy, int firstReminder, int secondReminder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventService.addCaledarEvent(titleMap, descriptionMap,
			location, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, durationHour,
			durationMinute, allDay, recurrence, type, remindBy, firstReminder,
			secondReminder, serviceContext);
	}

	public void deleteCalendarEvent(long calendarEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarEventService.deleteCalendarEvent(calendarEventId);
	}

	public com.liferay.calendar.model.CalendarEvent getCalendarEvent(
		long calendarEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventService.getCalendarEvent(calendarEventId);
	}

	public com.liferay.calendar.model.CalendarEvent updateCalendarEvent(
		long calendarEventId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int durationHour, int durationMinute,
		boolean allDay, java.lang.String recurrence, java.lang.String type,
		int remindBy, int firstReminder, int secondReminder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarEventService.updateCalendarEvent(calendarEventId,
			titleMap, descriptionMap, location, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, durationHour,
			durationMinute, allDay, recurrence, type, remindBy, firstReminder,
			secondReminder, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CalendarEventService getWrappedCalendarEventService() {
		return _calendarEventService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCalendarEventService(
		CalendarEventService calendarEventService) {
		_calendarEventService = calendarEventService;
	}

	public CalendarEventService getWrappedService() {
		return _calendarEventService;
	}

	public void setWrappedService(CalendarEventService calendarEventService) {
		_calendarEventService = calendarEventService;
	}

	private CalendarEventService _calendarEventService;
}