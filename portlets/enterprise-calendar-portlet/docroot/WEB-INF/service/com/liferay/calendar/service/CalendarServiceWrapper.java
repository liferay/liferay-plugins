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

	public com.liferay.calendar.model.Calendar addCalendar(long groupId,
		long calendarResourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color, boolean defaultCalendar,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.addCalendar(groupId, calendarResourceId,
			nameMap, descriptionMap, color, defaultCalendar, serviceContext);
	}

	public com.liferay.calendar.model.Calendar deleteCalendar(long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.deleteCalendar(calendarId);
	}

	public com.liferay.calendar.model.Calendar getCalendar(long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.getCalendar(calendarId);
	}

	public com.liferay.calendar.model.Calendar updateCalendar(long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color, boolean defaultCalendar,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.updateCalendar(calendarId, nameMap,
			descriptionMap, color, defaultCalendar, serviceContext);
	}

	public com.liferay.calendar.model.Calendar updateCalendar(long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarService.updateCalendar(calendarId, nameMap,
			descriptionMap, color, serviceContext);
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