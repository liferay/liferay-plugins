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

package com.liferay.calendar.recurrence;

import com.google.ical.values.DateValue;
import com.google.ical.values.DateValueImpl;
import com.google.ical.values.RRule;
import com.google.ical.values.WeekdayNum;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class RecurrenceSerializer {

	public static Recurrence deserialize(String data) {
		try {
			Recurrence recurrence = new Recurrence();

			RRule rRule = new RRule(data);

			recurrence.setCount(rRule.getCount());
			recurrence.setFrequency(
				Frequency.parse(String.valueOf(rRule.getFreq())));
			recurrence.setInterval(rRule.getInterval());

			DateValue dateValue = rRule.getUntil();

			if (dateValue != null) {
				Calendar jCalendar = CalendarFactoryUtil.getCalendar();

				jCalendar.set(Calendar.DATE, dateValue.day());
				jCalendar.set(Calendar.MONTH, dateValue.month() - 1);
				jCalendar.set(Calendar.YEAR, dateValue.year());

				recurrence.setUntil(jCalendar);
			}

			List<Weekday> weekdays = new ArrayList<Weekday>();

			for (WeekdayNum weekdayNum : rRule.getByDay()) {
				Weekday weekday = Weekday.parse(weekdayNum.wday.toString());

				weekday.setPosition(weekdayNum.num);

				weekdays.add(weekday);
			}

			recurrence.setWeekdays(weekdays);

			return recurrence;
		}
		catch (ParseException pe) {
			_log.error("Unable to parse data " + data , pe);
		}

		return null;
	}

	public static String serialize(Recurrence recurrence) {
		RRule rRule = new RRule();

		List<WeekdayNum> weekdayNums = new ArrayList<WeekdayNum>();

		for (Weekday weekday : recurrence.getWeekdays()) {
			com.google.ical.values.Weekday wday = _weekdaysMap.get(weekday);

			WeekdayNum weekdayNum = new WeekdayNum(weekday.getPosition(), wday);

			weekdayNums.add(weekdayNum);
		}

		rRule.setByDay(weekdayNums);

		rRule.setCount(recurrence.getCount());

		com.google.ical.values.Frequency frequency =
			com.google.ical.values.Frequency.valueOf(
				String.valueOf(recurrence.getFrequency()));

		rRule.setFreq(frequency);

		rRule.setInterval(recurrence.getInterval());

		Calendar jCalendar = recurrence.getUntil();

		if (jCalendar != null) {
			DateValue dateValue = new DateValueImpl(
				jCalendar.get(Calendar.YEAR), jCalendar.get(Calendar.MONTH) + 1,
				jCalendar.get(Calendar.DATE));

			rRule.setUntil(dateValue);
		}

		return rRule.toIcal();
	}

	private static Log _log = LogFactoryUtil.getLog(RecurrenceSerializer.class);

	private static Map<Weekday, com.google.ical.values.Weekday> _weekdaysMap =
		new HashMap<Weekday, com.google.ical.values.Weekday>();

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