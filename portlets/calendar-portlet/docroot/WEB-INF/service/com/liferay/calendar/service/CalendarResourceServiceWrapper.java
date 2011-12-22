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
 * This class is a wrapper for {@link CalendarResourceService}.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarResourceService
 * @generated
 */
public class CalendarResourceServiceWrapper implements CalendarResourceService,
	ServiceWrapper<CalendarResourceService> {
	public CalendarResourceServiceWrapper(
		CalendarResourceService calendarResourceService) {
		_calendarResourceService = calendarResourceService;
	}

	public com.liferay.calendar.model.CalendarResource addCalendarResource(
		java.lang.String className, long classPK,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		boolean active, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceService.addCalendarResource(className, classPK,
			nameMap, descriptionMap, active, serviceContext);
	}

	public void deleteCalendarResource(long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarResourceService.deleteCalendarResource(calendarResourceId);
	}

	public com.liferay.calendar.model.CalendarResource getCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceService.getCalendarResource(calendarResourceId);
	}

	public com.liferay.calendar.model.CalendarResource getCalendarResource(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceService.getCalendarResource(className, classPK);
	}

	public java.util.List<com.liferay.calendar.model.CalendarResource> getGroupCalendarResources(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceService.getGroupCalendarResources(groupId,
			active, start, end, orderByComparator);
	}

	public java.util.List<com.liferay.calendar.model.CalendarResource> getGroupCalendarResources(
		long groupId, java.lang.String name, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceService.getGroupCalendarResources(groupId,
			name, active, start, end, orderByComparator);
	}

	public int getGroupCalendarResourcesCount(long groupId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceService.getGroupCalendarResourcesCount(groupId,
			active);
	}

	public int getGroupCalendarResourcesCount(long groupId,
		java.lang.String name, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceService.getGroupCalendarResourcesCount(groupId,
			name, active, start, end, orderByComparator);
	}

	public com.liferay.calendar.model.CalendarResource updateCalendarResource(
		long calendarResourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		boolean active, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceService.updateCalendarResource(calendarResourceId,
			nameMap, descriptionMap, active, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CalendarResourceService getWrappedCalendarResourceService() {
		return _calendarResourceService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCalendarResourceService(
		CalendarResourceService calendarResourceService) {
		_calendarResourceService = calendarResourceService;
	}

	public CalendarResourceService getWrappedService() {
		return _calendarResourceService;
	}

	public void setWrappedService(
		CalendarResourceService calendarResourceService) {
		_calendarResourceService = calendarResourceService;
	}

	private CalendarResourceService _calendarResourceService;
}