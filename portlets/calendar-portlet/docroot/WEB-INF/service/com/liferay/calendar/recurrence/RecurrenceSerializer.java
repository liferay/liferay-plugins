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

package com.liferay.calendar.recurrence;

import com.google.ical.values.DateTimeValue;
import com.google.ical.values.DateValue;
import com.google.ical.values.DateValueImpl;
import com.google.ical.values.RDateList;
import com.google.ical.values.RRule;
import com.google.ical.values.WeekdayNum;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Marcellus Tavares
 */
public class RecurrenceSerializer {

	public static Recurrence deserialize(String data) {
		try {
			Recurrence recurrence = new Recurrence();

			int index = data.indexOf(StringPool.NEW_LINE);

			if (index != -1) {
				String exceptionDates = data.substring(
					index + 1, data.length());

				RDateList rDateList = new RDateList(
					exceptionDates, TimeZone.getTimeZone(StringPool.UTC));

				for (DateValue dateValue : rDateList.getDatesUtc()) {
					Calendar jCalendar = _toJCalendar(dateValue);

					recurrence.addExceptionDate(jCalendar);
				}

				data = data.substring(0, index);
			}

			RRule rRule = new RRule(data);

			recurrence.setCount(rRule.getCount());
			recurrence.setFrequency(
				Frequency.parse(String.valueOf(rRule.getFreq())));
			recurrence.setInterval(rRule.getInterval());

			DateValue dateValue = rRule.getUntil();

			if (dateValue != null) {
				Calendar jCalendar = _toJCalendar(dateValue);

				recurrence.setUntilJCalendar(jCalendar);
			}

			List<PositionalWeekday> positionalWeekdays = new ArrayList<>();

			for (WeekdayNum weekdayNum : rRule.getByDay()) {
				Weekday weekday = Weekday.parse(weekdayNum.wday.toString());

				PositionalWeekday positionalWeekday = new PositionalWeekday(
					weekday, weekdayNum.num);

				positionalWeekdays.add(positionalWeekday);
			}

			recurrence.setPositionalWeekdays(positionalWeekdays);

			recurrence.setMonths(ListUtil.toList(rRule.getByMonth()));

			return recurrence;
		}
		catch (ParseException pe) {
			_log.error("Unable to parse data " + data, pe);
		}

		return null;
	}

	public static String serialize(Recurrence recurrence) {
		RRule rRule = new RRule();

		List<WeekdayNum> weekdayNums = new ArrayList<>();

		for (PositionalWeekday positionalWeekday :
				recurrence.getPositionalWeekdays()) {

			com.google.ical.values.Weekday wday = _weekdaysMap.get(
				positionalWeekday.getWeekday());

			WeekdayNum weekdayNum = new WeekdayNum(
				positionalWeekday.getPosition(), wday);

			weekdayNums.add(weekdayNum);
		}

		rRule.setByDay(weekdayNums);

		List<Integer> months = recurrence.getMonths();

		if (months != null) {
			int[] monthsArray = ArrayUtil.toIntArray(months);

			for (int i = 0; i < monthsArray.length; i++) {
				monthsArray[i]++;
			}

			rRule.setByMonth(monthsArray);
		}

		rRule.setCount(recurrence.getCount());

		com.google.ical.values.Frequency frequency =
			com.google.ical.values.Frequency.valueOf(
				String.valueOf(recurrence.getFrequency()));

		rRule.setFreq(frequency);

		rRule.setInterval(recurrence.getInterval());

		Calendar jCalendar = recurrence.getUntilJCalendar();

		if (jCalendar != null) {
			DateValue dateValue = _toDateValue(jCalendar);

			rRule.setUntil(dateValue);
		}

		String data = rRule.toIcal();

		List<Calendar> exceptionJCalendars =
			recurrence.getExceptionJCalendars();

		if (!exceptionJCalendars.isEmpty()) {
			DateValue[] dateValues = new DateValue[exceptionJCalendars.size()];

			for (int i = 0; i < exceptionJCalendars.size(); i++) {
				dateValues[i] = _toDateValue(exceptionJCalendars.get(i));
			}

			RDateList rDateList = new RDateList(
				TimeZone.getTimeZone(StringPool.UTC));

			rDateList.setDatesUtc(dateValues);
			rDateList.setName(_EXDATE);

			data = data.concat(StringPool.NEW_LINE).concat(rDateList.toIcal());
		}

		return data;
	}

	private static DateValue _toDateValue(Calendar jCalendar) {
		DateValue dateValue = new DateValueImpl(
			jCalendar.get(Calendar.YEAR), jCalendar.get(Calendar.MONTH) + 1,
			jCalendar.get(Calendar.DATE));

		return dateValue;
	}

	private static Calendar _toJCalendar(DateValue dateValue) {
		Calendar jCalendar = CalendarFactoryUtil.getCalendar(
			TimeZone.getTimeZone(StringPool.UTC));

		jCalendar.set(Calendar.DATE, dateValue.day());
		jCalendar.set(Calendar.MONTH, dateValue.month() - 1);
		jCalendar.set(Calendar.YEAR, dateValue.year());

		if (dateValue instanceof DateTimeValue) {
			DateTimeValue dateTimeValue = (DateTimeValue)dateValue;

			jCalendar.set(Calendar.HOUR_OF_DAY, dateTimeValue.hour());
			jCalendar.set(Calendar.MINUTE, dateTimeValue.minute());
			jCalendar.set(Calendar.SECOND, dateTimeValue.second());
		}

		return jCalendar;
	}

	private static final String _EXDATE = "EXDATE";

	private static Log _log = LogFactoryUtil.getLog(RecurrenceSerializer.class);

	private static Map<Weekday, com.google.ical.values.Weekday> _weekdaysMap =
		new HashMap<>();

	static {
		_weekdaysMap.put(Weekday.SUNDAY, com.google.ical.values.Weekday.SU);
		_weekdaysMap.put(Weekday.MONDAY, com.google.ical.values.Weekday.MO);
		_weekdaysMap.put(Weekday.TUESDAY, com.google.ical.values.Weekday.TU);
		_weekdaysMap.put(Weekday.WEDNESDAY, com.google.ical.values.Weekday.WE);
		_weekdaysMap.put(Weekday.THURSDAY, com.google.ical.values.Weekday.TH);
		_weekdaysMap.put(Weekday.FRIDAY, com.google.ical.values.Weekday.FR);
		_weekdaysMap.put(Weekday.SATURDAY, com.google.ical.values.Weekday.SA);
	}

}