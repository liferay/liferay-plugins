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
 * The utility for the calendar booking remote service. This utility wraps {@link com.liferay.calendar.service.impl.CalendarBookingServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarBookingService
 * @see com.liferay.calendar.service.base.CalendarBookingServiceBaseImpl
 * @see com.liferay.calendar.service.impl.CalendarBookingServiceImpl
 * @generated
 */
public class CalendarBookingServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.calendar.service.impl.CalendarBookingServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		long calendarEventId, long calendarResourceId, boolean required,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCalendarBooking(calendarEventId, calendarResourceId,
			required, serviceContext);
	}

	public static void deleteCalendarBooking(long calendarResourceId,
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCalendarBooking(calendarResourceId, calendarBookingId);
	}

	public static com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarResourceId, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCalendarBooking(calendarResourceId, calendarBookingId);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		java.lang.String className, long classPK, long calendarResourceId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCalendarBookings(className, classPK, calendarResourceId,
			start, end, orderByComparator);
	}

	public static int getCalendarBookingsCount(java.lang.String className,
		long classPK, long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCalendarBookingsCount(className, classPK,
			calendarResourceId);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarEventCalendarBookings(
		long calendarEventId, long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCalendarEventCalendarBookings(calendarEventId,
			calendarResourceId, start, end, orderByComparator);
	}

	public static int getCalendarEventCalendarBookingsCount(
		long calendarEventId, long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCalendarEventCalendarBookingsCount(calendarEventId,
			calendarResourceId);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarResourceCalendarBookings(
		long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCalendarResourceCalendarBookings(calendarResourceId,
			start, end, orderByComparator);
	}

	public static int getCalendarResourceCalendarBookingsCount(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCalendarResourceCalendarBookingsCount(calendarResourceId);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long calendarResourceId, java.lang.String title,
		java.lang.String description, java.lang.String type,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(calendarResourceId, title, description, type,
			andOperator, start, end, orderByComparator);
	}

	public static int searchCount(long calendarResourceId,
		java.lang.String title, java.lang.String description,
		java.lang.String type, boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(calendarResourceId, title, description, type,
			andOperator);
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long calendarResourceId, long calendarBookingId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCalendarBooking(calendarResourceId,
			calendarBookingId, status, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static CalendarBookingService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CalendarBookingService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					CalendarBookingService.class.getName(), portletClassLoader);

			_service = new CalendarBookingServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(CalendarBookingServiceUtil.class,
				"_service");
			MethodCache.remove(CalendarBookingService.class);
		}

		return _service;
	}

	public void setService(CalendarBookingService service) {
		MethodCache.remove(CalendarBookingService.class);

		_service = service;

		ReferenceRegistry.registerReference(CalendarBookingServiceUtil.class,
			"_service");
		MethodCache.remove(CalendarBookingService.class);
	}

	private static CalendarBookingService _service;
}