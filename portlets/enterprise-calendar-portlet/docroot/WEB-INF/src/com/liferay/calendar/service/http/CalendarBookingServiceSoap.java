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
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

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
		long calendarId, long parentCalendarBookingId,
		java.lang.String[] titleMapLanguageIds,
		java.lang.String[] titleMapValues,
		java.lang.String[] descriptionMapLanguageIds,
		java.lang.String[] descriptionMapValues,
		java.lang.String[] locationMapLanguageIds,
		java.lang.String[] locationMapValues, java.lang.String type,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, int endDateMonth,
		int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
		boolean allDay, java.lang.String recurrence, int priority,
		boolean outOfOffice, int firstReminder, int secondReminder,
		boolean required, java.lang.String requestMessage,
		java.lang.String responseMessage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(titleMapLanguageIds,
					titleMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);
			Map<Locale, String> locationMap = LocalizationUtil.getLocalizationMap(locationMapLanguageIds,
					locationMapValues);

			com.liferay.calendar.model.CalendarBooking returnValue = CalendarBookingServiceUtil.addCalendarBooking(calendarId,
					parentCalendarBookingId, titleMap, descriptionMap,
					locationMap, type, startDateMonth, startDateDay,
					startDateYear, startDateHour, startDateMinute,
					endDateMonth, endDateDay, endDateYear, endDateHour,
					endDateMinute, allDay, recurrence, priority, outOfOffice,
					firstReminder, secondReminder, required, requestMessage,
					responseMessage, serviceContext);

			return com.liferay.calendar.model.CalendarBookingSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBookingSoap deleteCalendarBooking(
		long calendarBookingId) throws RemoteException {
		try {
			com.liferay.calendar.model.CalendarBooking returnValue = CalendarBookingServiceUtil.deleteCalendarBooking(calendarBookingId);

			return com.liferay.calendar.model.CalendarBookingSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBookingSoap getCalendarBooking(
		long calendarBookingId) throws RemoteException {
		try {
			com.liferay.calendar.model.CalendarBooking returnValue = CalendarBookingServiceUtil.getCalendarBooking(calendarBookingId);

			return com.liferay.calendar.model.CalendarBookingSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBookingSoap[] getCalendarBookings(
		long calendarId, java.util.Date startDate, java.util.Date endDate)
		throws RemoteException {
		try {
			java.util.List<com.liferay.calendar.model.CalendarBooking> returnValue =
				CalendarBookingServiceUtil.getCalendarBookings(calendarId,
					startDate, endDate);

			return com.liferay.calendar.model.CalendarBookingSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBookingSoap updateCalendarBooking(
		long calendarBookingId, long calendarId,
		java.lang.String[] titleMapLanguageIds,
		java.lang.String[] titleMapValues,
		java.lang.String[] descriptionMapLanguageIds,
		java.lang.String[] descriptionMapValues,
		java.lang.String[] locationMapLanguageIds,
		java.lang.String[] locationMapValues, java.lang.String type,
		int status, int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, int endDateMonth,
		int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
		boolean allDay, java.lang.String recurrence, int priority,
		boolean outOfOffice, int firstReminder, int secondReminder,
		boolean required, java.lang.String requestMessage,
		java.lang.String responseMessage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(titleMapLanguageIds,
					titleMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);
			Map<Locale, String> locationMap = LocalizationUtil.getLocalizationMap(locationMapLanguageIds,
					locationMapValues);

			com.liferay.calendar.model.CalendarBooking returnValue = CalendarBookingServiceUtil.updateCalendarBooking(calendarBookingId,
					calendarId, titleMap, descriptionMap, locationMap, type,
					status, startDateMonth, startDateDay, startDateYear,
					startDateHour, startDateMinute, endDateMonth, endDateDay,
					endDateYear, endDateHour, endDateMinute, allDay,
					recurrence, priority, outOfOffice, firstReminder,
					secondReminder, required, requestMessage, responseMessage,
					serviceContext);

			return com.liferay.calendar.model.CalendarBookingSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CalendarBookingServiceSoap.class);
}