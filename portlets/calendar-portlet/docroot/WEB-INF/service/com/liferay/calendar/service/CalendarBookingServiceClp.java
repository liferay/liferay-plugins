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
				"addCalendarBooking", long.class, long.class, boolean.class,
				com.liferay.portal.service.ServiceContext.class);

		_deleteCalendarBookingMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteCalendarBooking", long.class, long.class);

		_getCalendarBookingMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarBooking", long.class, long.class);

		_getCalendarBookingsMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarBookings", java.lang.String.class, long.class,
				long.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getCalendarBookingsCountMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarBookingsCount", java.lang.String.class, long.class,
				long.class);

		_getCalendarEventCalendarBookingsMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarEventCalendarBookings", long.class, long.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getCalendarEventCalendarBookingsCountMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarEventCalendarBookingsCount", long.class, long.class);

		_getCalendarResourceCalendarBookingsMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarResourceCalendarBookings", long.class, int.class,
				int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getCalendarResourceCalendarBookingsCountMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarResourceCalendarBookingsCount", long.class);

		_searchMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
				"search", long.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class, boolean.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_searchCountMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
				"searchCount", long.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class, boolean.class);

		_updateCalendarBookingMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateCalendarBooking", long.class, long.class, int.class,
				com.liferay.portal.service.ServiceContext.class);
	}

	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		long calendarEventId, long calendarResourceId, boolean required,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addCalendarBookingMethodKey0,
				calendarEventId, calendarResourceId, required,
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

	public void deleteCalendarBooking(long calendarResourceId,
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteCalendarBookingMethodKey1,
				calendarResourceId, calendarBookingId);

		try {
			_classLoaderProxy.invoke(methodHandler);
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
	}

	public com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarResourceId, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarBookingMethodKey2,
				calendarResourceId, calendarBookingId);

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
		java.lang.String className, long classPK, long calendarResourceId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarBookingsMethodKey3,
				ClpSerializer.translateInput(className), classPK,
				calendarResourceId, start, end,
				ClpSerializer.translateInput(orderByComparator));

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

	public int getCalendarBookingsCount(java.lang.String className,
		long classPK, long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarBookingsCountMethodKey4,
				ClpSerializer.translateInput(className), classPK,
				calendarResourceId);

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

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarEventCalendarBookings(
		long calendarEventId, long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarEventCalendarBookingsMethodKey5,
				calendarEventId, calendarResourceId, start, end,
				ClpSerializer.translateInput(orderByComparator));

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

	public int getCalendarEventCalendarBookingsCount(long calendarEventId,
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarEventCalendarBookingsCountMethodKey6,
				calendarEventId, calendarResourceId);

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

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarResourceCalendarBookings(
		long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarResourceCalendarBookingsMethodKey7,
				calendarResourceId, start, end,
				ClpSerializer.translateInput(orderByComparator));

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

	public int getCalendarResourceCalendarBookingsCount(long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarResourceCalendarBookingsCountMethodKey8,
				calendarResourceId);

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

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long calendarResourceId, java.lang.String title,
		java.lang.String description, java.lang.String type,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchMethodKey9,
				calendarResourceId, ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(type), andOperator, start, end,
				ClpSerializer.translateInput(orderByComparator));

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

	public int searchCount(long calendarResourceId, java.lang.String title,
		java.lang.String description, java.lang.String type, boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchCountMethodKey10,
				calendarResourceId, ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(type), andOperator);

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

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long calendarResourceId, long calendarBookingId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateCalendarBookingMethodKey11,
				calendarResourceId, calendarBookingId, status,
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
	private MethodKey _getCalendarBookingsCountMethodKey4;
	private MethodKey _getCalendarEventCalendarBookingsMethodKey5;
	private MethodKey _getCalendarEventCalendarBookingsCountMethodKey6;
	private MethodKey _getCalendarResourceCalendarBookingsMethodKey7;
	private MethodKey _getCalendarResourceCalendarBookingsCountMethodKey8;
	private MethodKey _searchMethodKey9;
	private MethodKey _searchCountMethodKey10;
	private MethodKey _updateCalendarBookingMethodKey11;
}