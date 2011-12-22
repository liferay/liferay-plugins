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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the calendar event remote service. This utility wraps {@link com.liferay.calendar.service.impl.CalendarEventServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarEventService
 * @see com.liferay.calendar.service.base.CalendarEventServiceBaseImpl
 * @see com.liferay.calendar.service.impl.CalendarEventServiceImpl
 * @generated
 */
public class CalendarEventServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.calendar.service.impl.CalendarEventServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.calendar.model.CalendarEvent addCaledarEvent(
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
		return getService()
				   .addCaledarEvent(titleMap, descriptionMap, location,
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, endDateMonth, endDateDay, endDateYear,
			endDateHour, endDateMinute, durationHour, durationMinute, allDay,
			recurrence, type, remindBy, firstReminder, secondReminder,
			serviceContext);
	}

	public static void deleteCalendarEvent(long calendarEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCalendarEvent(calendarEventId);
	}

	public static com.liferay.calendar.model.CalendarEvent getCalendarEvent(
		long calendarEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCalendarEvent(calendarEventId);
	}

	public static com.liferay.calendar.model.CalendarEvent updateCalendarEvent(
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
		return getService()
				   .updateCalendarEvent(calendarEventId, titleMap,
			descriptionMap, location, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, durationHour,
			durationMinute, allDay, recurrence, type, remindBy, firstReminder,
			secondReminder, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static CalendarEventService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CalendarEventService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					CalendarEventService.class.getName(), portletClassLoader);

			_service = new CalendarEventServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(CalendarEventServiceUtil.class,
				"_service");
			MethodCache.remove(CalendarEventService.class);
		}

		return _service;
	}

	public void setService(CalendarEventService service) {
		MethodCache.remove(CalendarEventService.class);

		_service = service;

		ReferenceRegistry.registerReference(CalendarEventServiceUtil.class,
			"_service");
		MethodCache.remove(CalendarEventService.class);
	}

	private static CalendarEventService _service;
}