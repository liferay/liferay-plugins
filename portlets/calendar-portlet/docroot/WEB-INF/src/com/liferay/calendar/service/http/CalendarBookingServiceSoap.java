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

package com.liferay.calendar.service.http;

import com.liferay.calendar.service.CalendarBookingServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.calendar.service.CalendarBookingServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.calendar.model.CalendarBookingSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.calendar.model.CalendarBooking}, that is translated to a
 * {@link com.liferay.calendar.model.CalendarBookingSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/api/secure/axis. Set the property
 * <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarBookingServiceHttp
 * @see       com.liferay.calendar.model.CalendarBookingSoap
 * @see       com.liferay.calendar.service.CalendarBookingServiceUtil
 * @generated
 */
public class CalendarBookingServiceSoap {
	public static com.liferay.calendar.model.CalendarBookingSoap addCalendarBooking(
		long calendarEventId, long calendarResourceId, boolean required,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.calendar.model.CalendarBooking returnValue = CalendarBookingServiceUtil.addCalendarBooking(calendarEventId,
					calendarResourceId, required, serviceContext);

			return com.liferay.calendar.model.CalendarBookingSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCalendarBooking(long calendarResourceId,
		long calendarBookingId) throws RemoteException {
		try {
			CalendarBookingServiceUtil.deleteCalendarBooking(calendarResourceId,
				calendarBookingId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBookingSoap getCalendarBooking(
		long calendarResourceId, long calendarBookingId)
		throws RemoteException {
		try {
			com.liferay.calendar.model.CalendarBooking returnValue = CalendarBookingServiceUtil.getCalendarBooking(calendarResourceId,
					calendarBookingId);

			return com.liferay.calendar.model.CalendarBookingSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBooking[] getCalendarBookings(
		java.lang.String className, long classPK, long calendarResourceId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.calendar.model.CalendarBooking> returnValue =
				CalendarBookingServiceUtil.getCalendarBookings(className,
					classPK, calendarResourceId, start, end, orderByComparator);

			return returnValue.toArray(new com.liferay.calendar.model.CalendarBooking[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCalendarBookingsCount(java.lang.String className,
		long classPK, long calendarResourceId) throws RemoteException {
		try {
			int returnValue = CalendarBookingServiceUtil.getCalendarBookingsCount(className,
					classPK, calendarResourceId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBooking[] getCalendarEventCalendarBookings(
		long calendarEventId, long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.calendar.model.CalendarBooking> returnValue =
				CalendarBookingServiceUtil.getCalendarEventCalendarBookings(calendarEventId,
					calendarResourceId, start, end, orderByComparator);

			return returnValue.toArray(new com.liferay.calendar.model.CalendarBooking[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCalendarEventCalendarBookingsCount(
		long calendarEventId, long calendarResourceId)
		throws RemoteException {
		try {
			int returnValue = CalendarBookingServiceUtil.getCalendarEventCalendarBookingsCount(calendarEventId,
					calendarResourceId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBooking[] getCalendarResourceCalendarBookings(
		long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.calendar.model.CalendarBooking> returnValue =
				CalendarBookingServiceUtil.getCalendarResourceCalendarBookings(calendarResourceId,
					start, end, orderByComparator);

			return returnValue.toArray(new com.liferay.calendar.model.CalendarBooking[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCalendarResourceCalendarBookingsCount(
		long calendarResourceId) throws RemoteException {
		try {
			int returnValue = CalendarBookingServiceUtil.getCalendarResourceCalendarBookingsCount(calendarResourceId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBooking[] search(
		long calendarResourceId, java.lang.String title,
		java.lang.String description, java.lang.String type,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.calendar.model.CalendarBooking> returnValue =
				CalendarBookingServiceUtil.search(calendarResourceId, title,
					description, type, andOperator, start, end,
					orderByComparator);

			return returnValue.toArray(new com.liferay.calendar.model.CalendarBooking[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int searchCount(long calendarResourceId,
		java.lang.String title, java.lang.String description,
		java.lang.String type, boolean andOperator) throws RemoteException {
		try {
			int returnValue = CalendarBookingServiceUtil.searchCount(calendarResourceId,
					title, description, type, andOperator);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBookingSoap updateCalendarBooking(
		long calendarResourceId, long calendarBookingId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.calendar.model.CalendarBooking returnValue = CalendarBookingServiceUtil.updateCalendarBooking(calendarResourceId,
					calendarBookingId, status, serviceContext);

			return com.liferay.calendar.model.CalendarBookingSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CalendarBookingServiceSoap.class);
}