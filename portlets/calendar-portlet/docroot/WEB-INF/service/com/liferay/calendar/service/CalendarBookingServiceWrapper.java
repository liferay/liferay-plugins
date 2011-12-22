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

package com.liferay.calendar.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CalendarBookingService}.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarBookingService
 * @generated
 */
public class CalendarBookingServiceWrapper implements CalendarBookingService,
	ServiceWrapper<CalendarBookingService> {
	public CalendarBookingServiceWrapper(
		CalendarBookingService calendarBookingService) {
		_calendarBookingService = calendarBookingService;
	}

	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		long calendarEventId, long calendarResourceId, boolean required,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.addCalendarBooking(calendarEventId,
			calendarResourceId, required, serviceContext);
	}

	public void deleteCalendarBooking(long calendarResourceId,
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarBookingService.deleteCalendarBooking(calendarResourceId,
			calendarBookingId);
	}

	public com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarResourceId, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.getCalendarBooking(calendarResourceId,
			calendarBookingId);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		java.lang.String className, long classPK, long calendarResourceId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.getCalendarBookings(className, classPK,
			calendarResourceId, start, end, orderByComparator);
	}

	public int getCalendarBookingsCount(java.lang.String className,
		long classPK, long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.getCalendarBookingsCount(className,
			classPK, calendarResourceId);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarEventCalendarBookings(
		long calendarEventId, long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.getCalendarEventCalendarBookings(calendarEventId,
			calendarResourceId, start, end, orderByComparator);
	}

	public int getCalendarEventCalendarBookingsCount(long calendarEventId,
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.getCalendarEventCalendarBookingsCount(calendarEventId,
			calendarResourceId);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarResourceCalendarBookings(
		long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.getCalendarResourceCalendarBookings(calendarResourceId,
			start, end, orderByComparator);
	}

	public int getCalendarResourceCalendarBookingsCount(long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.getCalendarResourceCalendarBookingsCount(calendarResourceId);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long calendarResourceId, java.lang.String title,
		java.lang.String description, java.lang.String type,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.search(calendarResourceId, title,
			description, type, andOperator, start, end, orderByComparator);
	}

	public int searchCount(long calendarResourceId, java.lang.String title,
		java.lang.String description, java.lang.String type, boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.searchCount(calendarResourceId, title,
			description, type, andOperator);
	}

	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long calendarResourceId, long calendarBookingId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingService.updateCalendarBooking(calendarResourceId,
			calendarBookingId, status, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CalendarBookingService getWrappedCalendarBookingService() {
		return _calendarBookingService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCalendarBookingService(
		CalendarBookingService calendarBookingService) {
		_calendarBookingService = calendarBookingService;
	}

	public CalendarBookingService getWrappedService() {
		return _calendarBookingService;
	}

	public void setWrappedService(CalendarBookingService calendarBookingService) {
		_calendarBookingService = calendarBookingService;
	}

	private CalendarBookingService _calendarBookingService;
}