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