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
 * This class is a wrapper for {@link CalendarService}.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarService
 * @generated
 */
public class CalendarServiceWrapper implements CalendarService,
	ServiceWrapper<CalendarService> {
	public CalendarServiceWrapper(CalendarService calendarService) {
		_calendarService = calendarService;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CalendarService getWrappedCalendarService() {
		return _calendarService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCalendarService(CalendarService calendarService) {
		_calendarService = calendarService;
	}

	public CalendarService getWrappedService() {
		return _calendarService;
	}

	public void setWrappedService(CalendarService calendarService) {
		_calendarService = calendarService;
	}

	private CalendarService _calendarService;
}