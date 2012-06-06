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

package com.liferay.calendar.util;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.comparator.CalendarNameComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.ContentUtil;
import com.liferay.util.UniqueList;
import com.liferay.util.portlet.PortletProps;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Eduardo Lundgren
 * @author Peter Shin
 * @author Fabio Pezzutto
 */
public class CalendarUtil {

	public static List<User> getBookingNotificationRecipients(
			CalendarBooking calendarBooking)
		throws SystemException, PortalException {

		Calendar calendar = CalendarLocalServiceUtil.getCalendar(
			calendarBooking.getCalendarId());

		List<Role> roles = CalendarLocalServiceUtil.getCalendarPermissionRoles(
			calendar.getCompanyId(), calendar.getResourceBlockId(),
			ActionKeys.MANAGE_BOOKINGS);

		List<User> manageBookingUsers = new ArrayList<User>();

		for (Role role : roles) {

			if (role.getType() == RoleConstants.TYPE_REGULAR) {
				manageBookingUsers.addAll(
					UserLocalServiceUtil.getRoleUsers(role.getRoleId()));
			}
			else {

				List<UserGroupRole> userGroupRoles =
					UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(
						calendar.getGroupId(), role.getRoleId());

				for (UserGroupRole userGroupRole : userGroupRoles) {
					manageBookingUsers.add(userGroupRole.getUser());
				}
			}
		}

		return manageBookingUsers;
	}

	public static String getEmailBookingNotificationBody(
		PortletPreferences preferences) {

		String emailBookingNotificationBody = preferences.getValue(
			"emailBookingNotificationBody", StringPool.BLANK);

		if (Validator.isNotNull(emailBookingNotificationBody)) {
			return emailBookingNotificationBody;
		}
		else {
			return ContentUtil.get(PortletProps.get(
				PortletPropsKeys.CALENDAR_EMAIL_BOOKING_NOTIFICATION_BODY));
		}
	}

	public static boolean getEmailBookingNotificationEnabled(
		PortletPreferences preferences) {

		String emailBookingNotificationEnabled = preferences.getValue(
			"emailBookingNotificationEnabled", StringPool.BLANK);

		if (Validator.isNotNull(emailBookingNotificationEnabled)) {
			return GetterUtil.getBoolean(emailBookingNotificationEnabled);
		}
		else {
			return
				PortletPropsValues.CALENDAR_EMAIL_BOOKING_NOTIFICATION_ENABLED;
		}
	}

	public static String getEmailBookingNotificationSubject(
		PortletPreferences preferences) {

		String emailBookingNotificationSubject = preferences.getValue(
			"emailBookingNotificationSubject", StringPool.BLANK);

		if (Validator.isNotNull(emailBookingNotificationSubject)) {
			return emailBookingNotificationSubject;
		}
		else {
			return ContentUtil.get(PortletProps.get(
				PortletPropsKeys.CALENDAR_EMAIL_BOOKING_NOTIFICATION_SUBJECT));
		}
	}

	public static String getEmailBookingReminderBody(
		PortletPreferences preferences) {

		String emailBookingReminderBody = preferences.getValue(
			"emailBookingReminderBody", StringPool.BLANK);

		if (Validator.isNotNull(emailBookingReminderBody)) {
			return emailBookingReminderBody;
		}
		else {
			return ContentUtil.get(PortletProps.get(
				PortletPropsKeys.CALENDAR_EMAIL_BOOKING_REMINDER_BODY));
		}
	}

	public static boolean getEmailBookingReminderEnabled(
		PortletPreferences preferences) {

		String emailBookingReminderEnabled = preferences.getValue(
			"emailBookingReminderEnabled", StringPool.BLANK);

		if (Validator.isNotNull(emailBookingReminderEnabled)) {
			return GetterUtil.getBoolean(emailBookingReminderEnabled);
		}
		else {
			return PortletPropsValues.CALENDAR_EMAIL_BOOKING_REMINDER_ENABLED;
		}
	}

	public static String getEmailBookingReminderSubject(
		PortletPreferences preferences) {

		String emailBookingReminderSubject = preferences.getValue(
			"emailBookingReminderSubject", StringPool.BLANK);

		if (Validator.isNotNull(emailBookingReminderSubject)) {
			return emailBookingReminderSubject;
		}
		else {
			return ContentUtil.get(PortletProps.get(
				PortletPropsKeys.CALENDAR_EMAIL_BOOKING_REMINDER_SUBJECT));
		}
	}

	public static String getEmailFromAddress(
			PortletPreferences preferences, long companyId)
		throws SystemException {

		return PortalUtil.getEmailFromAddress(
			preferences, companyId,
			PortletPropsValues.CALENDAR_EMAIL_FROM_ADDRESS);
	}

	public static String getEmailFromName(
			PortletPreferences preferences, long companyId)
		throws SystemException {

		return PortalUtil.getEmailFromName(
			preferences, companyId,
			PortletPropsValues.CALENDAR_EMAIL_FROM_NAME);
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
		jsonObject.put("name", calendar.getName(themeDisplay.getLocale()));
		jsonObject.put(
			"permissions",
			_getPermissionsJSONObject(
				themeDisplay.getPermissionChecker(), calendar));
		jsonObject.put("userId", calendar.getUserId());

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
			ActionKeys.MANAGE_BOOKINGS,
			CalendarPermission.contains(
				permissionChecker, calendar, ActionKeys.MANAGE_BOOKINGS));

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