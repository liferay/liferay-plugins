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
		long groupId, java.lang.String className, long classPK,
		java.lang.String classUuid, java.lang.String code,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceService.addCalendarResource(groupId, className,
			classPK, classUuid, code, nameMap, descriptionMap, type, active,
			serviceContext);
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

	public java.util.List<com.liferay.calendar.model.CalendarResource> searchByKeywords(
		long groupId, long classNameId, long classPK,
		java.lang.String keywords, java.lang.Boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceService.searchByKeywords(groupId, classNameId,
			classPK, keywords, active, start, end, orderByComparator);
	}

	public java.util.List<com.liferay.calendar.model.CalendarResource> search(
		long groupId, long classNameId, long classPK, java.lang.String code,
		java.lang.String name, java.lang.String description,
		java.lang.String type, java.lang.Boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceService.search(groupId, classNameId, classPK,
			code, name, description, type, active, andOperator, start, end,
			orderByComparator);
	}

	public com.liferay.calendar.model.CalendarResource updateCalendarResource(
		long calendarResourceId, java.lang.String code,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceService.updateCalendarResource(calendarResourceId,
			code, nameMap, descriptionMap, type, active, serviceContext);
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