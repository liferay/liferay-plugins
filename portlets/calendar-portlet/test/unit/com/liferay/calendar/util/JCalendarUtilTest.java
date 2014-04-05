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

import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.util.CalendarFactoryImpl;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Adam Brandizzi
 */
public class JCalendarUtilTest {

	@BeforeClass
	public static void setUpClass() {
		CalendarFactoryUtil calendarFactoryUtil = new CalendarFactoryUtil();

		calendarFactoryUtil.setCalendarFactory(new CalendarFactoryImpl());
	}

	@Test
	public void testGetDSTShiftAtLosAngelesDuringDST() {
		Calendar calendar1 = JCalendarUtil.getJCalendar(
			2012, Calendar.MAY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);
		Calendar calendar2 = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 2, 12, 0, 0, 0, TimeZoneUtil.GMT);

		int shift = JCalendarUtil.getDSTShift(
			calendar1, calendar2, _losAngelesTimeZone);

		Assert.assertEquals(0, shift);
	}

	@Test
	public void testGetDSTShiftAtLosAngelesDuringNoDST() {
		Calendar calendar1 = JCalendarUtil.getJCalendar(
			2013, Calendar.DECEMBER, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);
		Calendar calendar2 = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 2, 12, 0, 0, 0, TimeZoneUtil.GMT);

		int shift = JCalendarUtil.getDSTShift(
			calendar1, calendar2, _losAngelesTimeZone);

		Assert.assertEquals(0, shift);
	}

	@Test
	public void testGetDSTShiftAtLosAngelesFromDSTToNoDST() {
		Calendar calendar1 = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);
		Calendar calendar2 = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);

		int shift = JCalendarUtil.getDSTShift(
			calendar1, calendar2, _losAngelesTimeZone);

		Assert.assertEquals(JCalendarUtil.HOUR, shift);
	}

	@Test
	public void testGetDSTShiftAtLosAngelesFromNoDSTToDST() {
		Calendar calendar1 = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);
		Calendar calendar2 = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);

		int shift = JCalendarUtil.getDSTShift(
			calendar1, calendar2, _losAngelesTimeZone);

		Assert.assertEquals(-1 * JCalendarUtil.HOUR, shift);
	}

	private static TimeZone _losAngelesTimeZone = TimeZone.getTimeZone(
		"America/Los_Angeles");

}