/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.calendar.util;

import com.google.ical.iter.RecurrenceIterator;
import com.google.ical.iter.RecurrenceIteratorFactory;
import com.google.ical.util.TimeUtils;
import com.google.ical.values.DateValue;
import com.google.ical.values.DateValueImpl;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class RecurrenceUtil {

	public static List<CalendarBooking> expandCalendarBooking(
		CalendarBooking calendarBooking, long startTime, long endTime,
		int maxSize) {

		List<CalendarBooking> expandedCalendarBookings =
			new ArrayList<CalendarBooking>();

		try {
			CalendarBookingIterator calendarBookingIterator =
				new CalendarBookingIterator(calendarBooking);

			while (calendarBookingIterator.hasNext()) {
				CalendarBooking newCalendarBooking =
					calendarBookingIterator.next();

				if (newCalendarBooking.getEndTime() < startTime) {
					continue;
				}

				if (newCalendarBooking.getStartTime() > endTime) {
					break;
				}

				expandedCalendarBookings.add(newCalendarBooking);

				if ((maxSize > 0) &&
					(expandedCalendarBookings.size() >= maxSize)) {

					break;
				}
			}
		}
		catch (ParseException pe) {
			_log.error("Unable to parse data ", pe);
		}

		return expandedCalendarBookings;
	}

	public static List<CalendarBooking> expandCalendarBookings(
		List<CalendarBooking> calendarBookings, long startTime, long endTime) {

		return expandCalendarBookings(calendarBookings, startTime, endTime, 0);
	}

	public static List<CalendarBooking> expandCalendarBookings(
		List<CalendarBooking> calendarBookings, long startTime, long endTime,
		int maxSize) {

		List<CalendarBooking> expandedCalendarBookings =
			new ArrayList<CalendarBooking>();

		for (CalendarBooking calendarBooking : calendarBookings) {
			List<CalendarBooking> expandedCalendarBooking =
				expandCalendarBooking(
					calendarBooking, startTime, endTime, maxSize);

			expandedCalendarBookings.addAll(expandedCalendarBooking);
		}

		return expandedCalendarBookings;
	}

	public static CalendarBooking getCalendarBookingInstance(
		CalendarBooking calendarBooking, int instanceIndex) {

		try {
			CalendarBookingIterator calendarBookingIterator =
				new CalendarBookingIterator(calendarBooking);

			while (calendarBookingIterator.hasNext()) {
				CalendarBooking calendarBookingInstance =
					calendarBookingIterator.next();

				if (calendarBookingInstance.getInstanceIndex() ==
						instanceIndex) {

					return calendarBookingInstance;
				}
			}
		}
		catch (ParseException pe) {
			_log.error("Unable to parse data ", pe);
		}

		return null;
	}

	public static int getIndexOfInstance(
		String recurrence, long recurrenceStartTime, long instanceStartTime) {

		int count = 0;

		DateValue instanceDateValue = _toDateValue(instanceStartTime);

		try {
			RecurrenceIterator recurrenceIterator =
				RecurrenceIteratorFactory.createRecurrenceIterator(
					recurrence, _toDateValue(recurrenceStartTime),
					TimeUtils.utcTimezone());

			while (recurrenceIterator.hasNext()) {
				DateValue dateValue = recurrenceIterator.next();

				if (dateValue.compareTo(instanceDateValue) >= 0) {
					break;
				}

				count++;
			}
		}
		catch (ParseException e) {
			_log.error("Unable to parse data ", e);
		}

		return count;
	}

	private static DateValue _toDateValue(long time) {
		Calendar jCalendar = JCalendarUtil.getJCalendar(time);

		return new DateValueImpl(
			jCalendar.get(Calendar.YEAR), jCalendar.get(Calendar.MONTH) + 1,
			jCalendar.get(Calendar.DAY_OF_MONTH));
	}

	private static Log _log = LogFactoryUtil.getLog(RecurrenceUtil.class);

}