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

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * @author Eduardo Lundgren
 */
public class CalendarBookingServiceClp implements CalendarBookingService {
	public CalendarBookingServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addCalendarBookingMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addCalendarBooking", long.class, long.class,
				java.util.Map.class, java.util.Map.class, java.util.Map.class,
				java.lang.String.class, int.class, int.class, int.class,
				int.class, int.class, int.class, int.class, int.class,
				int.class, int.class, boolean.class, java.lang.String.class,
				java.lang.Integer.class, boolean.class, int.class, int.class,
				boolean.class, java.lang.String.class, java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_deleteCalendarBookingMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteCalendarBooking", long.class);

		_getCalendarBookingMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarBooking", long.class);

		_getCalendarBookingsMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarBookings", long.class, java.util.Date.class,
				java.util.Date.class);

		_searchMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
				"search", long.class, long[].class, long[].class, long[].class,
				long.class, java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class,
				java.util.Date.class, java.util.Date.class,
				java.lang.Integer.class, int.class, boolean.class, int.class,
				int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_searchByKeywordsMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
				"searchByKeywords", long.class, long[].class, long[].class,
				java.lang.String.class, long[].class, long.class,
				java.util.Date.class, java.util.Date.class,
				java.lang.Integer.class, int.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_searchCountMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
				"searchCount", long.class, long[].class, long[].class,
				long[].class, long.class, java.lang.String.class,
				java.util.Date.class, java.util.Date.class,
				java.lang.Integer.class, int.class);

		_searchCountMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
				"searchCount", long.class, long[].class, long[].class,
				long[].class, long.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.util.Date.class,
				java.util.Date.class, java.lang.Integer.class, int.class,
				boolean.class);

		_updateCalendarBookingMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateCalendarBooking", long.class, long.class,
				java.util.Map.class, java.util.Map.class, java.util.Map.class,
				java.lang.String.class, int.class, int.class, int.class,
				int.class, int.class, int.class, int.class, int.class,
				int.class, int.class, int.class, boolean.class,
				java.lang.String.class, java.lang.Integer.class, boolean.class,
				int.class, int.class, boolean.class, java.lang.String.class,
				java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);
	}

	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		long calendarId, long parentCalendarBookingId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Map<java.util.Locale, java.lang.String> locationMap,
		java.lang.String type, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, boolean allDay, java.lang.String recurrence,
		java.lang.Integer priority, boolean outOfOffice, int firstReminder,
		int secondReminder, boolean required, java.lang.String requestMessage,
		java.lang.String responseMessage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addCalendarBookingMethodKey0,
				calendarId, parentCalendarBookingId,
				ClpSerializer.translateInput(titleMap),
				ClpSerializer.translateInput(descriptionMap),
				ClpSerializer.translateInput(locationMap),
				ClpSerializer.translateInput(type), startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, allDay,
				ClpSerializer.translateInput(recurrence),
				ClpSerializer.translateInput(priority), outOfOffice,
				firstReminder, secondReminder, required,
				ClpSerializer.translateInput(requestMessage),
				ClpSerializer.translateInput(responseMessage),
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.calendar.model.CalendarBooking)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.calendar.model.CalendarBooking deleteCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_deleteCalendarBookingMethodKey1,
				calendarBookingId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.calendar.model.CalendarBooking)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarBookingMethodKey2,
				calendarBookingId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.calendar.model.CalendarBooking)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarBookingsMethodKey3,
				calendarId, ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.calendar.model.CalendarBooking>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, java.lang.String type,
		java.util.Date startDate, java.util.Date endDate,
		java.lang.Integer priority, int status, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchMethodKey4,
				companyId, ClpSerializer.translateInput(groupIds),
				ClpSerializer.translateInput(calendarIds),
				ClpSerializer.translateInput(calendarResourceIds),
				parentCalendarBookingId, ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(location),
				ClpSerializer.translateInput(type),
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate),
				ClpSerializer.translateInput(priority), status, andOperator,
				start, end, ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.calendar.model.CalendarBooking>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> searchByKeywords(
		long companyId, long[] groupIds, long[] calendarIds,
		java.lang.String keywords, long[] calendarResourceIds,
		long parentCalendarBookingId, java.util.Date startDate,
		java.util.Date endDate, java.lang.Integer priority, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchByKeywordsMethodKey5,
				companyId, ClpSerializer.translateInput(groupIds),
				ClpSerializer.translateInput(calendarIds),
				ClpSerializer.translateInput(keywords),
				ClpSerializer.translateInput(calendarResourceIds),
				parentCalendarBookingId,
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate),
				ClpSerializer.translateInput(priority), status, start, end,
				ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.calendar.model.CalendarBooking>)ClpSerializer.translateOutput(returnObj);
	}

	public int searchCount(long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, java.util.Date startDate,
		java.util.Date endDate, java.lang.Integer priority, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchCountMethodKey6,
				companyId, ClpSerializer.translateInput(groupIds),
				ClpSerializer.translateInput(calendarIds),
				ClpSerializer.translateInput(calendarResourceIds),
				parentCalendarBookingId,
				ClpSerializer.translateInput(keywords),
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate),
				ClpSerializer.translateInput(priority), status);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int searchCount(long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, java.lang.String type,
		java.util.Date startDate, java.util.Date endDate,
		java.lang.Integer priority, int status, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchCountMethodKey7,
				companyId, ClpSerializer.translateInput(groupIds),
				ClpSerializer.translateInput(calendarIds),
				ClpSerializer.translateInput(calendarResourceIds),
				parentCalendarBookingId, ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(location),
				ClpSerializer.translateInput(type),
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate),
				ClpSerializer.translateInput(priority), status, andOperator);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long calendarBookingId, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Map<java.util.Locale, java.lang.String> locationMap,
		java.lang.String type, int status, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, int endDateMonth, int endDateDay, int endDateYear,
		int endDateHour, int endDateMinute, boolean allDay,
		java.lang.String recurrence, java.lang.Integer priority,
		boolean outOfOffice, int firstReminder, int secondReminder,
		boolean required, java.lang.String requestMessage,
		java.lang.String responseMessage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateCalendarBookingMethodKey8,
				calendarBookingId, calendarId,
				ClpSerializer.translateInput(titleMap),
				ClpSerializer.translateInput(descriptionMap),
				ClpSerializer.translateInput(locationMap),
				ClpSerializer.translateInput(type), status, startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, allDay,
				ClpSerializer.translateInput(recurrence),
				ClpSerializer.translateInput(priority), outOfOffice,
				firstReminder, secondReminder, required,
				ClpSerializer.translateInput(requestMessage),
				ClpSerializer.translateInput(responseMessage),
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.calendar.model.CalendarBooking)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
	private MethodKey _addCalendarBookingMethodKey0;
	private MethodKey _deleteCalendarBookingMethodKey1;
	private MethodKey _getCalendarBookingMethodKey2;
	private MethodKey _getCalendarBookingsMethodKey3;
	private MethodKey _searchMethodKey4;
	private MethodKey _searchByKeywordsMethodKey5;
	private MethodKey _searchCountMethodKey6;
	private MethodKey _searchCountMethodKey7;
	private MethodKey _updateCalendarBookingMethodKey8;
}