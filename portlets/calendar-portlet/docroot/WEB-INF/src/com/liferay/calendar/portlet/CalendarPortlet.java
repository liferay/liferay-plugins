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

package com.liferay.calendar.portlet;

import com.liferay.calendar.CalendarResourceCodeException;
import com.liferay.calendar.DuplicateCalendarResourceException;
import com.liferay.calendar.NoSuchResourceException;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.notification.NotificationTemplateContextFactory;
import com.liferay.calendar.recurrence.Frequency;
import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.recurrence.RecurrenceSerializer;
import com.liferay.calendar.recurrence.Weekday;
import com.liferay.calendar.service.CalendarBookingServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceServiceUtil;
import com.liferay.calendar.service.CalendarServiceUtil;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.calendar.util.CalendarDataFormat;
import com.liferay.calendar.util.CalendarDataHandler;
import com.liferay.calendar.util.CalendarDataHandlerFactory;
import com.liferay.calendar.util.CalendarResourceUtil;
import com.liferay.calendar.util.CalendarUtil;
import com.liferay.calendar.util.JCalendarUtil;
import com.liferay.calendar.util.WebKeys;
import com.liferay.calendar.util.comparator.CalendarResourceNameComparator;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.comparator.UserFirstNameComparator;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Andrea Di Giorgi
 * @author Marcellus Tavares
 */
public class CalendarPortlet extends MVCPortlet {

	public void deleteCalendar(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long calendarId = ParamUtil.getLong(actionRequest, "calendarId");

		CalendarServiceUtil.deleteCalendar(calendarId);
	}

	public void deleteCalendarResource(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long calendarResourceId = ParamUtil.getLong(
			actionRequest, "calendarResourceId");

		CalendarResourceServiceUtil.deleteCalendarResource(calendarResourceId);
	}

	@Override
	public void init() throws PortletException {
		super.init();

		NotificationTemplateContextFactory.setPortletConfig(getPortletConfig());
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			getCalendar(renderRequest);
			getCalendarBooking(renderRequest);
			getCalendarResource(renderRequest);
		}
		catch (Exception e) {
			if (e instanceof NoSuchResourceException) {
				SessionErrors.add(renderRequest, e.getClass());
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("calendarResources")) {
				serveCalendarResources(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("exportCalendar")) {
				serveExportCalendar(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("importCalendar")) {
				serveImportCalendar(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void updateCalendar(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long calendarId = ParamUtil.getLong(actionRequest, "calendarId");

		long calendarResourceId = ParamUtil.getLong(
			actionRequest, "calendarResourceId");
		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		int color = ParamUtil.getInteger(actionRequest, "color");
		boolean defaultCalendar = ParamUtil.getBoolean(
			actionRequest, "defaultCalendar", false);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Calendar.class.getName(), actionRequest);

		if (calendarId <= 0) {
			CalendarResource calendarResource =
				CalendarResourceServiceUtil.getCalendarResource(
					calendarResourceId);

			CalendarServiceUtil.addCalendar(
				calendarResource.getGroupId(), calendarResourceId, nameMap,
				descriptionMap, color, defaultCalendar, serviceContext);
		}
		else {
			CalendarServiceUtil.updateCalendar(
				calendarId, nameMap, descriptionMap, color, defaultCalendar,
				serviceContext);
		}
	}

	public void updateCalendarBooking(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long calendarBookingId = ParamUtil.getLong(
			actionRequest, "calendarBookingId");

		long calendarId = ParamUtil.getLong(actionRequest, "calendarId");
		long[] childCalendarIds = ParamUtil.getLongValues(
			actionRequest, "childCalendarIds");
		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "title");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		String location = ParamUtil.getString(actionRequest, "location");
		java.util.Calendar startDateJCalendar = getJCalendar(
			actionRequest, "startDate");
		java.util.Calendar endDateJCalendar = getJCalendar(
			actionRequest, "endDate");
		boolean allDay = ParamUtil.getBoolean(actionRequest, "allDay");
		String recurrence = getRecurrence(actionRequest);
		int status = ParamUtil.getInteger(actionRequest, "status");

		long[] reminders = getReminders(actionRequest);
		String[] remindersType = getRemindersType(actionRequest);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CalendarBooking.class.getName(), actionRequest);

		if (calendarBookingId <= 0) {
			CalendarBookingServiceUtil.addCalendarBooking(
				calendarId, childCalendarIds,
				CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
				titleMap, descriptionMap, location,
				startDateJCalendar.getTimeInMillis(),
				endDateJCalendar.getTimeInMillis(), allDay, recurrence,
				reminders[0], remindersType[0], reminders[1], remindersType[1],
				serviceContext);
		}
		else {
			CalendarBookingServiceUtil.updateCalendarBooking(
				calendarBookingId, calendarId, childCalendarIds, titleMap,
				descriptionMap, location, startDateJCalendar.getTimeInMillis(),
				endDateJCalendar.getTimeInMillis(), allDay, recurrence,
				reminders[0], remindersType[0], reminders[1], remindersType[1],
				status, serviceContext);
		}
	}

	public void updateCalendarResource(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long calendarResourceId = ParamUtil.getLong(
			actionRequest, "calendarResourceId");

		long defaultCalendarId = ParamUtil.getLong(
			actionRequest, "defaultCalendarId");
		String code = ParamUtil.getString(actionRequest, "code");
		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		String type = ParamUtil.getString(actionRequest, "type");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CalendarResource.class.getName(), actionRequest);

		if (calendarResourceId <= 0) {
			CalendarResourceServiceUtil.addCalendarResource(
				serviceContext.getScopeGroupId(), null, 0,
				PortalUUIDUtil.generate(), defaultCalendarId, code, nameMap,
				descriptionMap, type, active, serviceContext);
		}
		else {
			CalendarResourceServiceUtil.updateCalendarResource(
				calendarResourceId, defaultCalendarId, nameMap, descriptionMap,
				type, active, serviceContext);
		}
	}

	protected void addCalendarJSONObject(
			PortletRequest portletRequest, JSONArray jsonArray,
			long classNameId, long classPK)
		throws PortalException, SystemException {

		CalendarResource calendarResource =
			CalendarResourceUtil.getCalendarResource(
				portletRequest, classNameId, classPK);

		if (calendarResource == null) {
			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		List<Calendar> calendars =
			CalendarLocalServiceUtil.getCalendarResourceCalendars(
				calendarResource.getGroupId(),
				calendarResource.getCalendarResourceId());

		for (Calendar calendar : calendars) {
			if (!CalendarPermission.contains(
					permissionChecker, calendar, ActionKeys.VIEW)) {

				continue;
			}

			JSONObject jsonObject = CalendarUtil.toCalendarJSONObject(
				themeDisplay, calendar);

			jsonArray.put(jsonObject);
		}
	}

	protected void getCalendar(PortletRequest portletRequest) throws Exception {
		long calendarId = ParamUtil.getLong(portletRequest, "calendarId");

		if (calendarId <= 0) {
			return;
		}

		Calendar calendar = CalendarServiceUtil.getCalendar(calendarId);

		portletRequest.setAttribute(WebKeys.CALENDAR, calendar);
	}

	protected void getCalendarBooking(PortletRequest portletRequest)
		throws Exception {

		long calendarBookingId = ParamUtil.getLong(
			portletRequest, "calendarBookingId");

		if (calendarBookingId <= 0) {
			return;
		}

		CalendarBooking calendarBooking =
			CalendarBookingServiceUtil.getCalendarBooking(calendarBookingId);

		portletRequest.setAttribute(WebKeys.CALENDAR_BOOKING, calendarBooking);
	}

	protected void getCalendarResource(PortletRequest portletRequest)
		throws Exception {

		long calendarResourceId = ParamUtil.getLong(
			portletRequest, "calendarResourceId");

		long classNameId = ParamUtil.getLong(portletRequest, "classNameId");
		long classPK = ParamUtil.getLong(portletRequest, "classPK");

		CalendarResource calendarResource = null;

		if (calendarResourceId > 0) {
			calendarResource =
				CalendarResourceServiceUtil.getCalendarResource(
					calendarResourceId);
		}
		else if ((classNameId > 0) && (classPK > 0)) {
			calendarResource = CalendarResourceUtil.getCalendarResource(
				portletRequest, classNameId, classPK);
		}

		portletRequest.setAttribute(
			WebKeys.CALENDAR_RESOURCE, calendarResource);
	}

	protected java.util.Calendar getJCalendar(
		PortletRequest portletRequest, String name) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		int month = ParamUtil.getInteger(portletRequest, name + "Month");
		int day = ParamUtil.getInteger(portletRequest, name + "Day");
		int year = ParamUtil.getInteger(portletRequest, name + "Year");
		int hour = ParamUtil.getInteger(portletRequest, name + "Hour");
		int minute = ParamUtil.getInteger(portletRequest, name + "Minute");

		int amPm = ParamUtil.getInteger(portletRequest, name + "AmPm");

		if (amPm == java.util.Calendar.PM) {
			hour += 12;
		}

		return JCalendarUtil.getJCalendar(
			year, month, day, hour, minute, 0, 0, themeDisplay.getTimeZone());
	}

	protected String getRecurrence(ActionRequest actionRequest) {
		boolean repeat = ParamUtil.getBoolean(actionRequest, "repeat");

		if (!repeat) {
			return null;
		}

		Recurrence recurrence = new Recurrence();

		int count = 0;

		String ends = ParamUtil.getString(actionRequest, "ends");

		if (ends.equals("after")) {
			count = ParamUtil.getInteger(actionRequest, "count");
		}

		recurrence.setCount(count);

		Frequency frequency = Frequency.parse(
			ParamUtil.getString(actionRequest, "frequency"));

		recurrence.setFrequency(frequency);

		int interval = ParamUtil.getInteger(actionRequest, "interval");

		recurrence.setInterval(interval);

		java.util.Calendar untilJCalendar = null;

		if (ends.equals("on")) {
			int untilDateDay = ParamUtil.getInteger(
				actionRequest, "untilDateDay");
			int untilDateMonth = ParamUtil.getInteger(
				actionRequest, "untilDateMonth");
			int untilDateYear = ParamUtil.getInteger(
				actionRequest, "untilDateYear");

			untilJCalendar = CalendarFactoryUtil.getCalendar();

			untilJCalendar.set(java.util.Calendar.DATE, untilDateDay);
			untilJCalendar.set(java.util.Calendar.MONTH, untilDateMonth);
			untilJCalendar.set(java.util.Calendar.YEAR, untilDateYear);
		}

		recurrence.setUntil(untilJCalendar);

		List<Weekday> weekdays = new ArrayList<Weekday>();

		if (frequency == Frequency.WEEKLY) {
			for (Weekday weekday : Weekday.values()) {
				boolean checked = ParamUtil.getBoolean(
					actionRequest, weekday.getValue());

				if (checked) {
					weekdays.add(weekday);
				}
			}
		}

		recurrence.setWeekdays(weekdays);

		return RecurrenceSerializer.serialize(recurrence);
	}

	protected long[] getReminders(PortletRequest portletRequest) {
		long firstReminder = ParamUtil.getInteger(
			portletRequest, "reminderValue0");
		long firstReminderDuration = ParamUtil.getInteger(
			portletRequest, "reminderDuration0");
		long secondReminder = ParamUtil.getInteger(
			portletRequest, "reminderValue1");
		long secondReminderDuration = ParamUtil.getInteger(
			portletRequest, "reminderDuration1");

		return new long[] {
			firstReminder * firstReminderDuration * Time.SECOND,
			secondReminder * secondReminderDuration * Time.SECOND
		};
	}

	protected String[] getRemindersType(PortletRequest portletRequest) {
		String firstReminderType = ParamUtil.getString(
			portletRequest, "reminderType0");
		String secondReminderType = ParamUtil.getString(
			portletRequest, "reminderType1");

		return new String[] {
			firstReminderType, secondReminderType
		};
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof CalendarResourceCodeException ||
			cause instanceof DuplicateCalendarResourceException ||
			cause instanceof PrincipalException) {

			return true;
		}

		return false;
	}

	protected void serveCalendarResources(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String keywords = ParamUtil.getString(resourceRequest, "keywords");

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		long classNameId = PortalUtil.getClassNameId(CalendarResource.class);

		List<CalendarResource> calendarResources =
			CalendarResourceServiceUtil.search(
				themeDisplay.getCompanyId(), new long[0],
				new long[] {classNameId}, keywords, true, true, 0,
				SearchContainer.DEFAULT_DELTA,
				new CalendarResourceNameComparator());

		for (CalendarResource calendarResource : calendarResources) {
			addCalendarJSONObject(
				resourceRequest, jsonArray, calendarResource.getClassNameId(),
				calendarResource.getClassPK());
		}

		long groupClassNameId = PortalUtil.getClassNameId(Group.class);

		String[] params = {"usersGroups:" + themeDisplay.getUserId() + ":long"};

		String name = StringUtil.merge(
			CustomSQLUtil.keywords(keywords), StringPool.BLANK);

		List<Group> groups = GroupServiceUtil.search(
			themeDisplay.getCompanyId(), name, null, params, 0,
			SearchContainer.DEFAULT_DELTA);

		for (Group group : groups) {
			addCalendarJSONObject(
				resourceRequest, jsonArray, groupClassNameId,
				group.getGroupId());
		}

		long userClassNameId = PortalUtil.getClassNameId(User.class);

		List<User> users = UserLocalServiceUtil.search(
			themeDisplay.getCompanyId(), keywords, 0, null, 0,
			SearchContainer.DEFAULT_DELTA, new UserFirstNameComparator());

		for (User user : users) {
			addCalendarJSONObject(
				resourceRequest, jsonArray, userClassNameId, user.getUserId());
		}

		writeJSON(resourceRequest, resourceResponse, jsonArray);
	}

	protected void serveExportCalendar(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long calendarId = ParamUtil.getLong(resourceRequest, "calendarId");

		Calendar calendar = CalendarLocalServiceUtil.getCalendar(calendarId);

		String fileName =
			calendar.getName(themeDisplay.getLocale()) + CharPool.PERIOD +
				String.valueOf(CalendarDataFormat.ICAL);

		CalendarDataHandler calendarDataHandler =
			CalendarDataHandlerFactory.getCalendarDataHandler(
				CalendarDataFormat.ICAL);

		String data = calendarDataHandler.exportCalendar(calendarId);

		String contentType = MimeTypesUtil.getContentType(fileName);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, fileName, data.getBytes(),
			contentType);
	}

	protected void serveImportCalendar(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(resourceRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long calendarId = ParamUtil.getLong(resourceRequest, "calendarId");

		File file = uploadPortletRequest.getFile("file");

		String data = FileUtil.read(file);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(data)) {
			CalendarDataHandler calendarDataHandler =
				CalendarDataHandlerFactory.getCalendarDataHandler(
					CalendarDataFormat.ICAL);

			calendarDataHandler.importCalendar(calendarId, data);
		}
		else {
			jsonObject.put(
				"error", themeDisplay.translate("failed-to-import-empty-file"));
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

}