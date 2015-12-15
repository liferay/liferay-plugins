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

import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.recurrence.RecurrenceSerializer;
import com.liferay.calendar.recurrence.Weekday;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.CalendarFactoryImpl;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Adam Brandizzi
 */
public class RecurrenceUtilTest {

	@BeforeClass
	public static void setUpClass() {
		CalendarFactoryUtil calendarFactoryUtil = new CalendarFactoryUtil();

		calendarFactoryUtil.setCalendarFactory(new CalendarFactoryImpl());
	}

	@Test
	public void testInTimeZoneUpdatesExceptionJCalendars() {
		Recurrence recurrence = RecurrenceSerializer.deserialize(
			"RRULE:FREQ=DAILY;INTERVAL=1\n" +
				"EXDATE;TZID=\"UTC\";VALUE=DATE:20151225,20151231",
			_utcTimeZone);

		List<Calendar> exceptionJCalendars =
			recurrence.getExceptionJCalendars();

		Calendar exceptionJCalendar = exceptionJCalendars.get(0);

		Assert.assertEquals(2015, exceptionJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.DECEMBER, exceptionJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(25, exceptionJCalendar.get(Calendar.DAY_OF_MONTH));

		exceptionJCalendar = exceptionJCalendars.get(1);

		Assert.assertEquals(2015, exceptionJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.DECEMBER, exceptionJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(31, exceptionJCalendar.get(Calendar.DAY_OF_MONTH));

		Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			2015, Calendar.DECEMBER, 11, 1, 0, 0, 0, _utcTimeZone);

		recurrence = RecurrenceUtil.inTimeZone(
			recurrence, startTimeJCalendar, _losAngelesTimeZone);

		exceptionJCalendars = recurrence.getExceptionJCalendars();

		exceptionJCalendar = exceptionJCalendars.get(0);

		Assert.assertEquals(2015, exceptionJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.DECEMBER, exceptionJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(24, exceptionJCalendar.get(Calendar.DAY_OF_MONTH));

		exceptionJCalendar = exceptionJCalendars.get(1);

		Assert.assertEquals(2015, exceptionJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.DECEMBER, exceptionJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(30, exceptionJCalendar.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testInTimeZoneUpdatesUntilJCalendar() {
		Recurrence recurrence = RecurrenceSerializer.deserialize(
			"RRULE:FREQ=DAILY;INTERVAL=1;UNTIL=20160116", _utcTimeZone);

		Calendar untilJCalendar = recurrence.getUntilJCalendar();

		Assert.assertEquals(2016, untilJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JANUARY, untilJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(16, untilJCalendar.get(Calendar.DAY_OF_MONTH));

		Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			2015, Calendar.DECEMBER, 11, 1, 0, 0, 0, _utcTimeZone);

		recurrence = RecurrenceUtil.inTimeZone(
			recurrence, startTimeJCalendar, _losAngelesTimeZone);

		untilJCalendar = recurrence.getUntilJCalendar();

		Assert.assertEquals(2016, untilJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JANUARY, untilJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(15, untilJCalendar.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testInTimeZoneUpdatesWeekdays() {
		Recurrence recurrence = RecurrenceSerializer.deserialize(
			"RRULE:FREQ=WEEKLY;INTERVAL=1;BYDAY=MO,WE,FR", _utcTimeZone);

		List<Weekday> weekdays = recurrence.getWeekdays();

		Assert.assertTrue(weekdays.contains(Weekday.MONDAY));
		Assert.assertTrue(weekdays.contains(Weekday.WEDNESDAY));
		Assert.assertTrue(weekdays.contains(Weekday.FRIDAY));

		Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			2015, Calendar.DECEMBER, 11, 1, 0, 0, 0, _utcTimeZone);

		recurrence = RecurrenceUtil.inTimeZone(
			recurrence, startTimeJCalendar, _losAngelesTimeZone);

		weekdays = recurrence.getWeekdays();

		Assert.assertTrue(weekdays.contains(Weekday.SUNDAY));
		Assert.assertTrue(weekdays.contains(Weekday.TUESDAY));
		Assert.assertTrue(weekdays.contains(Weekday.THURSDAY));
	}

	private static final TimeZone _losAngelesTimeZone = TimeZone.getTimeZone(
		"America/Los_Angeles");
	private static final TimeZone _utcTimeZone = TimeZone.getTimeZone(
		StringPool.UTC);

}