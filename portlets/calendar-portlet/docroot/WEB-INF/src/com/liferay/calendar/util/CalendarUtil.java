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

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.comparator.CalendarNameComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author Eduardo Lundgren
 * @author Peter Shin
 * @author Fabio Pezzutto
 */
public class CalendarUtil {

	public static TimeZone getCalendarBookingDisplayTimeZone(
		CalendarBooking calendarBooking, TimeZone defaultTimeZone) {

		if ((calendarBooking != null) && calendarBooking.isAllDay()) {
			return TimeZone.getTimeZone(StringPool.UTC);
		}

		return defaultTimeZone;
	}

	public static JSONObject getCalendarRenderingRules(
			ThemeDisplay themeDisplay, long[] calendarIds, int[] statuses,
			long startTime, long endTime, String ruleName, TimeZone timeZone)
		throws SystemException {

		List<CalendarBooking> calendarBookings =
			CalendarBookingLocalServiceUtil.search(
				themeDisplay.getCompanyId(), null, calendarIds, new long[0], -1,
				null, startTime, endTime, true, statuses, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		Map<Integer, Map<Integer, List<Integer>>> rulesMap =
			new HashMap<Integer, Map<Integer, List<Integer>>>();

		for (CalendarBooking calendarBooking : calendarBookings) {
			TimeZone displayTimeZone = timeZone;

			if (calendarBooking.isAllDay()) {
				displayTimeZone = _utcTimeZone;
			}

			java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
				calendarBooking.getStartTime(), displayTimeZone);
			java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(
				calendarBooking.getEndTime(), displayTimeZone);

			long days = JCalendarUtil.getDaysBetween(
				startTimeJCalendar, endTimeJCalendar);

			for (int i = 0; i <= days; i++) {
				int year = startTimeJCalendar.get(java.util.Calendar.YEAR);

				Map<Integer, List<Integer>> rulesMonth = rulesMap.get(year);

				if (rulesMonth == null) {
					rulesMonth = new HashMap<Integer, List<Integer>>();

					rulesMap.put(year, rulesMonth);
				}

				int month = startTimeJCalendar.get(java.util.Calendar.MONTH);

				List<Integer> rulesDay = rulesMonth.get(month);

				if (rulesDay == null) {
					rulesDay = new ArrayList<Integer>();

					rulesMonth.put(month, rulesDay);
				}

				int day = startTimeJCalendar.get(
					java.util.Calendar.DAY_OF_MONTH);

				if (!rulesDay.contains(day)) {
					rulesDay.add(day);
				}

				startTimeJCalendar.add(java.util.Calendar.DATE, 1);
			}
		}

		Set<Integer> years = rulesMap.keySet();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		for (Integer year : years) {
			Map<Integer, List<Integer>> monthsMap = rulesMap.get(year);

			Set<Integer> months = monthsMap.keySet();

			JSONObject jsonObjectMonth = JSONFactoryUtil.createJSONObject();

			jsonObject.put(String.valueOf(year), jsonObjectMonth);

			for (Integer month : months) {
				List<Integer> days = monthsMap.get(month);

				JSONObject jsonObjectDay = JSONFactoryUtil.createJSONObject();

				jsonObjectDay.put(StringUtil.merge(days), ruleName);

				jsonObjectMonth.put(String.valueOf(month), jsonObjectDay);
			}
		}

		return jsonObject;
	}

	public static Collection<CalendarResource> getCalendarResources(
			List<CalendarBooking> calendarBookings)
		throws PortalException, SystemException {

		Set<CalendarResource> calendarResources =
			new HashSet<CalendarResource>();

		for (CalendarBooking calendarBooking : calendarBookings) {
			CalendarResource calendarResource =
				calendarBooking.getCalendarResource();

			calendarResources.add(calendarResource);
		}

		return calendarResources;
	}

	public static CalendarBooking getNewDurationCalendarBooking(
		CalendarBooking calendarBooking, long duration) {

		calendarBooking.setEndTime(calendarBooking.getStartTime() + duration);

		return calendarBooking;
	}

	public static CalendarBooking getNewStartTimeCalendarBooking(
		CalendarBooking calendarBooking, long offset) {

		calendarBooking.setStartTime(calendarBooking.getStartTime() + offset);
		calendarBooking.setEndTime(calendarBooking.getEndTime() + offset);

		return calendarBooking;
	}

	public static OrderByComparator getOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator orderByComparator = new CalendarNameComparator(
			orderByAsc);

		return orderByComparator;
	}

	public static String[] splitKeywords(String keywords) {
		Set<String> keywordsSet = new LinkedHashSet<String>();

		StringBundler sb = new StringBundler();

		for (char c : keywords.toCharArray()) {
			if (Character.isWhitespace(c)) {
				if (sb.length() > 0) {
					keywordsSet.add(sb.toString());

					sb = new StringBundler();
				}
			}
			else if (Character.isLetterOrDigit(c)) {
				sb.append(c);
			}
			else {
				return new String[] {keywords};
			}
		}

		if (sb.length() > 0) {
			keywordsSet.add(sb.toString());
		}

		return StringUtil.split(StringUtil.merge(keywordsSet));
	}

	public static JSONObject toCalendarBookingJSONObject(
			ThemeDisplay themeDisplay, CalendarBooking calendarBooking,
			TimeZone timeZone)
		throws SystemException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("allDay", calendarBooking.isAllDay());
		jsonObject.put(
			"calendarBookingId", calendarBooking.getCalendarBookingId());
		jsonObject.put("calendarId", calendarBooking.getCalendarId());
		jsonObject.put(
			"description",
			calendarBooking.getDescription(themeDisplay.getLocale()));

		if (calendarBooking.isAllDay()) {
			timeZone = TimeZone.getTimeZone(StringPool.UTC);
		}

		java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(
			calendarBooking.getEndTime(), timeZone);

		_addTimeProperties(jsonObject, "endTime", endTimeJCalendar);

		jsonObject.put("firstReminder", calendarBooking.getFirstReminder());
		jsonObject.put(
			"firstReminderType", calendarBooking.getFirstReminderType());

		List<CalendarBooking> childCalendarBookings =
			calendarBooking.getChildCalendarBookings();

		jsonObject.put(
			"hasChildCalendarBookings", childCalendarBookings.size() > 1);

		jsonObject.put("instanceIndex", calendarBooking.getInstanceIndex());
		jsonObject.put("location", calendarBooking.getLocation());
		jsonObject.put(
			"parentCalendarBookingId",
			calendarBooking.getParentCalendarBookingId());
		jsonObject.put("recurrence", calendarBooking.getRecurrence());
		jsonObject.put("secondReminder", calendarBooking.getSecondReminder());
		jsonObject.put(
			"secondReminderType", calendarBooking.getSecondReminder());

		java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			calendarBooking.getStartTime(), timeZone);

		_addTimeProperties(jsonObject, "startTime", startTimeJCalendar);

		jsonObject.put("status", calendarBooking.getStatus());
		jsonObject.put(
			"title", calendarBooking.getTitle(themeDisplay.getLocale()));

		return jsonObject;
	}

	public static JSONArray toCalendarBookingsJSONArray(
			ThemeDisplay themeDisplay, List<CalendarBooking> calendarBookings)
		throws PortalException, SystemException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (calendarBookings == null) {
			return jsonArray;
		}

		for (CalendarBooking calendarBooking : calendarBookings) {
			JSONObject jsonObject = toCalendarJSONObject(
				themeDisplay, calendarBooking.getCalendar());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public static JSONArray toCalendarBookingsJSONArray(
			ThemeDisplay themeDisplay, List<CalendarBooking> calendarBookings,
			TimeZone timeZone)
		throws PortalException, SystemException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (CalendarBooking calendarBooking : calendarBookings) {
			JSONObject jsonObject = toCalendarBookingJSONObject(
				themeDisplay, calendarBooking, timeZone);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public static JSONObject toCalendarJSONObject(
			ThemeDisplay themeDisplay, Calendar calendar)
		throws SystemException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("calendarId", calendar.getCalendarId());

		CalendarResource calendarResource =
			CalendarResourceLocalServiceUtil.fetchCalendarResource(
				calendar.getCalendarResourceId());

		jsonObject.put(
			"calendarResourceId", calendarResource.getCalendarResourceId());
		jsonObject.put(
			"calendarResourceName",
			calendarResource.getName(themeDisplay.getLocale()));
		jsonObject.put("color", ColorUtil.toHexString(calendar.getColor()));
		jsonObject.put("defaultCalendar", calendar.isDefaultCalendar());
		jsonObject.put("classNameId", calendarResource.getClassNameId());
		jsonObject.put("classPK", calendarResource.getClassPK());
		jsonObject.put("groupId", calendar.getGroupId());
		jsonObject.put("name", calendar.getName(themeDisplay.getLocale()));
		jsonObject.put(
			"permissions",
			_getPermissionsJSONObject(
				themeDisplay.getPermissionChecker(), calendar));
		jsonObject.put("userId", calendar.getUserId());

		return jsonObject;
	}

	public static JSONObject toCalendarResourceJSONObject(
		ThemeDisplay themeDisplay, CalendarResource calendarResource) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"calendarResourceId", calendarResource.getCalendarResourceId());
		jsonObject.put("classNameId", calendarResource.getClassNameId());
		jsonObject.put("classPK", calendarResource.getClassPK());
		jsonObject.put("classUuid", calendarResource.getClassUuid());
		jsonObject.put("code", calendarResource.getCode());
		jsonObject.put("groupId", calendarResource.getGroupId());
		jsonObject.put(
			"name", calendarResource.getName(themeDisplay.getLocale()));
		jsonObject.put("userId", calendarResource.getUserId());

		return jsonObject;
	}

	public static JSONArray toCalendarsJSONArray(
			ThemeDisplay themeDisplay, List<Calendar> calendars)
		throws SystemException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (calendars == null) {
			return jsonArray;
		}

		for (Calendar calendar : calendars) {
			JSONObject jsonObject = toCalendarJSONObject(
				themeDisplay, calendar);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private static void _addTimeProperties(
		JSONObject jsonObject, String prefix, java.util.Calendar jCalendar) {

		jsonObject.put(prefix, jCalendar.getTimeInMillis());
		jsonObject.put(
			prefix + "Day", jCalendar.get(java.util.Calendar.DAY_OF_MONTH));
		jsonObject.put(
			prefix + "Hour", jCalendar.get(java.util.Calendar.HOUR_OF_DAY));
		jsonObject.put(
			prefix + "Minute", jCalendar.get(java.util.Calendar.MINUTE));
		jsonObject.put(
			prefix + "Month", jCalendar.get(java.util.Calendar.MONTH));
		jsonObject.put(prefix + "Year", jCalendar.get(java.util.Calendar.YEAR));
	}

	private static JSONObject _getPermissionsJSONObject(
		PermissionChecker permissionChecker, Calendar calendar) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			ActionKeys.DELETE,
			CalendarPermission.contains(
				permissionChecker, calendar, ActionKeys.DELETE));

		jsonObject.put(
			ActionKeys.MANAGE_BOOKINGS,
			CalendarPermission.contains(
				permissionChecker, calendar, ActionKeys.MANAGE_BOOKINGS));

		jsonObject.put(
			ActionKeys.PERMISSIONS,
			CalendarPermission.contains(
				permissionChecker, calendar, ActionKeys.PERMISSIONS));

		jsonObject.put(
			ActionKeys.UPDATE,
			CalendarPermission.contains(
				permissionChecker, calendar, ActionKeys.UPDATE));

		jsonObject.put(
			ActionKeys.VIEW,
			CalendarPermission.contains(
				permissionChecker, calendar, ActionKeys.VIEW));

		jsonObject.put(
			ActionKeys.VIEW_BOOKING_DETAILS,
			CalendarPermission.contains(
				permissionChecker, calendar, ActionKeys.VIEW_BOOKING_DETAILS));

		return jsonObject;
	}

	private static TimeZone _utcTimeZone = TimeZone.getTimeZone(StringPool.UTC);

}