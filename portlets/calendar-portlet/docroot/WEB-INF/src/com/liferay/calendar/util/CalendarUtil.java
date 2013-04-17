/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UniqueList;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Eduardo Lundgren
 * @author Peter Shin
 * @author Fabio Pezzutto
 */
public class CalendarUtil {

	public static JSONObject getCalendarRenderingRules(
			ThemeDisplay themeDisplay, long[] calendarIds, int[] statuses,
			long startTime, long endTime, String ruleName)
		throws SystemException {

		List<CalendarBooking> calendarBookings =
			CalendarBookingLocalServiceUtil.search(
				themeDisplay.getCompanyId(),
				new long[] {
					0, themeDisplay.getCompanyGroupId(),
					themeDisplay.getScopeGroupId()
				},
				calendarIds, new long[0], -1, null, startTime, endTime, true,
				statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				(OrderByComparator)null);

		Map<Integer, Map<Integer, List<Integer>>> rulesMap =
			new HashMap<Integer, Map<Integer, List<Integer>>>();

		for (CalendarBooking calendarBooking : calendarBookings) {
			java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
				calendarBooking.getStartTime());
			java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(
				calendarBooking.getEndTime());

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
		List<String> keywordsList = new UniqueList<String>();

		StringBundler sb = new StringBundler();

		for (char c : keywords.toCharArray()) {
			if (Character.isWhitespace(c)) {
				if (sb.length() > 0) {
					keywordsList.add(sb.toString());

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
			keywordsList.add(sb.toString());
		}

		return StringUtil.split(StringUtil.merge(keywordsList));
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
		jsonObject.put("global", calendarResource.isGlobal());
		jsonObject.put("groupId", calendar.getGroupId());
		jsonObject.put("name", calendar.getName(themeDisplay.getLocale()));
		jsonObject.put(
			"permissions",
			_getPermissionsJSONObject(
				themeDisplay.getPermissionChecker(), calendar));
		jsonObject.put("resourceGroupId", calendar.getResourceGroupId());
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

}