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
public class CalendarBookingLocalServiceClp
	implements CalendarBookingLocalService {
	public CalendarBookingLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addCalendarBookingMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addCalendarBooking",
				com.liferay.calendar.model.CalendarBooking.class);

		_createCalendarBookingMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"createCalendarBooking", long.class);

		_deleteCalendarBookingMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteCalendarBooking", long.class);

		_deleteCalendarBookingMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteCalendarBooking",
				com.liferay.calendar.model.CalendarBooking.class);

		_dynamicQueryMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

		_dynamicQueryMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
				int.class, int.class);

		_dynamicQueryMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_dynamicQueryCountMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQueryCount",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

		_fetchCalendarBookingMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"fetchCalendarBooking", long.class);

		_getCalendarBookingMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarBooking", long.class);

		_getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
				"getPersistedModel", java.io.Serializable.class);

		_getCalendarBookingByUuidAndGroupIdMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarBookingByUuidAndGroupId", java.lang.String.class,
				long.class);

		_getCalendarBookingsMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarBookings", int.class, int.class);

		_getCalendarBookingsCountMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarBookingsCount");

		_updateCalendarBookingMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateCalendarBooking",
				com.liferay.calendar.model.CalendarBooking.class);

		_updateCalendarBookingMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateCalendarBooking",
				com.liferay.calendar.model.CalendarBooking.class, boolean.class);

		_getBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
				"getBeanIdentifier");

		_setBeanIdentifierMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
				"setBeanIdentifier", java.lang.String.class);

		_addCalendarBookingMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
				"addCalendarBooking", long.class, long.class, long.class,
				java.util.Map.class, java.util.Map.class, java.util.Map.class,
				java.lang.String.class, int.class, int.class, int.class,
				int.class, int.class, int.class, int.class, int.class,
				int.class, int.class, boolean.class, java.lang.String.class,
				int.class, boolean.class, int.class, int.class, boolean.class,
				java.lang.String.class, java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_deleteCalendarBookingsMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteCalendarBookings", long.class);

		_getCalendarBookingsMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarBookings", long.class);

		_getCalendarBookingsMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarBookings", long.class, java.util.Date.class,
				java.util.Date.class);

		_searchMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
				"search", long.class, long.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.util.Date.class,
				java.util.Date.class, java.lang.Boolean.class, int.class,
				java.lang.Boolean.class, java.lang.Boolean.class, int.class,
				boolean.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_searchCountMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
				"searchCount", long.class, long.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.util.Date.class,
				java.util.Date.class, java.lang.Boolean.class, int.class,
				java.lang.Boolean.class, java.lang.Boolean.class, int.class,
				boolean.class);

		_updateCalendarBookingMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateCalendarBooking", long.class, long.class, long.class,
				java.util.Map.class, java.util.Map.class, java.util.Map.class,
				java.lang.String.class, int.class, int.class, int.class,
				int.class, int.class, int.class, int.class, int.class,
				int.class, int.class, int.class, boolean.class,
				java.lang.String.class, int.class, boolean.class, int.class,
				int.class, boolean.class, java.lang.String.class,
				java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_updateStatusMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateStatus", long.class, long.class, int.class,
				com.liferay.portal.service.ServiceContext.class);
	}

	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		com.liferay.calendar.model.CalendarBooking calendarBooking)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addCalendarBookingMethodKey0,
				ClpSerializer.translateInput(calendarBooking));

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

		return (com.liferay.calendar.model.CalendarBooking)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.calendar.model.CalendarBooking createCalendarBooking(
		long calendarBookingId) {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_createCalendarBookingMethodKey1,
				calendarBookingId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
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

		MethodHandler methodHandler = new MethodHandler(_deleteCalendarBookingMethodKey2,
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

	public com.liferay.calendar.model.CalendarBooking deleteCalendarBooking(
		com.liferay.calendar.model.CalendarBooking calendarBooking)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_deleteCalendarBookingMethodKey3,
				ClpSerializer.translateInput(calendarBooking));

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

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey4,
				ClpSerializer.translateInput(dynamicQuery));

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

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey5,
				ClpSerializer.translateInput(dynamicQuery), start, end);

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

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey6,
				ClpSerializer.translateInput(dynamicQuery), start, end,
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

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryCountMethodKey7,
				ClpSerializer.translateInput(dynamicQuery));

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

		return ((Long)returnObj).longValue();
	}

	public com.liferay.calendar.model.CalendarBooking fetchCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_fetchCalendarBookingMethodKey8,
				calendarBookingId);

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

		return (com.liferay.calendar.model.CalendarBooking)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarBookingMethodKey9,
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

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getPersistedModelMethodKey10,
				ClpSerializer.translateInput(primaryKeyObj));

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

		return (com.liferay.portal.model.PersistedModel)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.calendar.model.CalendarBooking getCalendarBookingByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarBookingByUuidAndGroupIdMethodKey11,
				ClpSerializer.translateInput(uuid), groupId);

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
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarBookingsMethodKey12,
				start, end);

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

	public int getCalendarBookingsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarBookingsCountMethodKey13);

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
		com.liferay.calendar.model.CalendarBooking calendarBooking)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateCalendarBookingMethodKey14,
				ClpSerializer.translateInput(calendarBooking));

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

		return (com.liferay.calendar.model.CalendarBooking)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		com.liferay.calendar.model.CalendarBooking calendarBooking,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateCalendarBookingMethodKey15,
				ClpSerializer.translateInput(calendarBooking), merge);

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

		return (com.liferay.calendar.model.CalendarBooking)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getBeanIdentifier() {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getBeanIdentifierMethodKey16);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		MethodHandler methodHandler = new MethodHandler(_setBeanIdentifierMethodKey17,
				ClpSerializer.translateInput(beanIdentifier));

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		long userId, long calendarId, long parentCalendarBookingId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Map<java.util.Locale, java.lang.String> locationMap,
		java.lang.String type, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, boolean allDay, java.lang.String recurrence,
		int priority, boolean outOfOffice, int firstReminder,
		int secondReminder, boolean required, java.lang.String requestMessage,
		java.lang.String responseMessage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addCalendarBookingMethodKey18,
				userId, calendarId, parentCalendarBookingId,
				ClpSerializer.translateInput(titleMap),
				ClpSerializer.translateInput(descriptionMap),
				ClpSerializer.translateInput(locationMap),
				ClpSerializer.translateInput(type), startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, allDay,
				ClpSerializer.translateInput(recurrence), priority,
				outOfOffice, firstReminder, secondReminder, required,
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

	public void deleteCalendarBookings(long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteCalendarBookingsMethodKey19,
				calendarId);

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

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarBookingsMethodKey20,
				calendarId);

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

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarBookingsMethodKey21,
				calendarId, ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate));

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

	public java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long calendarId, long calendarResourceId, java.lang.String title,
		java.lang.String description, java.lang.String location,
		java.lang.String type, java.util.Date startDate,
		java.util.Date endDate, java.lang.Boolean allDay, int priority,
		java.lang.Boolean outOfOffice, java.lang.Boolean required, int status,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchMethodKey22,
				calendarId, calendarResourceId,
				ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(location),
				ClpSerializer.translateInput(type),
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate),
				ClpSerializer.translateInput(allDay), priority,
				ClpSerializer.translateInput(outOfOffice),
				ClpSerializer.translateInput(required), status, andOperator,
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

	public long searchCount(long calendarId, long calendarResourceId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, java.lang.String type,
		java.util.Date startDate, java.util.Date endDate,
		java.lang.Boolean allDay, int priority, java.lang.Boolean outOfOffice,
		java.lang.Boolean required, int status, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchCountMethodKey23,
				calendarId, calendarResourceId,
				ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(location),
				ClpSerializer.translateInput(type),
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate),
				ClpSerializer.translateInput(allDay), priority,
				ClpSerializer.translateInput(outOfOffice),
				ClpSerializer.translateInput(required), status, andOperator);

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

		return ((Long)returnObj).longValue();
	}

	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long userId, long calendarBookingId, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Map<java.util.Locale, java.lang.String> locationMap,
		java.lang.String type, int status, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, int endDateMonth, int endDateDay, int endDateYear,
		int endDateHour, int endDateMinute, boolean allDay,
		java.lang.String recurrence, int priority, boolean outOfOffice,
		int firstReminder, int secondReminder, boolean required,
		java.lang.String requestMessage, java.lang.String responseMessage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateCalendarBookingMethodKey24,
				userId, calendarBookingId, calendarId,
				ClpSerializer.translateInput(titleMap),
				ClpSerializer.translateInput(descriptionMap),
				ClpSerializer.translateInput(locationMap),
				ClpSerializer.translateInput(type), status, startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, allDay,
				ClpSerializer.translateInput(recurrence), priority,
				outOfOffice, firstReminder, secondReminder, required,
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

	public com.liferay.calendar.model.CalendarBooking updateStatus(
		long userId, long calendarBookingId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateStatusMethodKey25,
				userId, calendarBookingId, status,
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
	private MethodKey _createCalendarBookingMethodKey1;
	private MethodKey _deleteCalendarBookingMethodKey2;
	private MethodKey _deleteCalendarBookingMethodKey3;
	private MethodKey _dynamicQueryMethodKey4;
	private MethodKey _dynamicQueryMethodKey5;
	private MethodKey _dynamicQueryMethodKey6;
	private MethodKey _dynamicQueryCountMethodKey7;
	private MethodKey _fetchCalendarBookingMethodKey8;
	private MethodKey _getCalendarBookingMethodKey9;
	private MethodKey _getPersistedModelMethodKey10;
	private MethodKey _getCalendarBookingByUuidAndGroupIdMethodKey11;
	private MethodKey _getCalendarBookingsMethodKey12;
	private MethodKey _getCalendarBookingsCountMethodKey13;
	private MethodKey _updateCalendarBookingMethodKey14;
	private MethodKey _updateCalendarBookingMethodKey15;
	private MethodKey _getBeanIdentifierMethodKey16;
	private MethodKey _setBeanIdentifierMethodKey17;
	private MethodKey _addCalendarBookingMethodKey18;
	private MethodKey _deleteCalendarBookingsMethodKey19;
	private MethodKey _getCalendarBookingsMethodKey20;
	private MethodKey _getCalendarBookingsMethodKey21;
	private MethodKey _searchMethodKey22;
	private MethodKey _searchCountMethodKey23;
	private MethodKey _updateCalendarBookingMethodKey24;
	private MethodKey _updateStatusMethodKey25;
}