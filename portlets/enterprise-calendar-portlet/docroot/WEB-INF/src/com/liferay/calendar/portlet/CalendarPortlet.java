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

import com.liferay.calendar.DuplicateCalendarResourceException;
import com.liferay.calendar.NoSuchResourceException;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarBookingServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceServiceUtil;
import com.liferay.calendar.service.CalendarServiceUtil;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.ActionKeys;
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
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
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

import java.io.IOException;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Andrea Di Giorgi
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
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException, IOException {

		try {
			Calendar calendar = null;

			long calendarId = ParamUtil.getLong(renderRequest, "calendarId");

			if (calendarId > 0) {
				calendar = CalendarServiceUtil.getCalendar(calendarId);
			}

			renderRequest.setAttribute(WebKeys.CALENDAR, calendar);

			CalendarBooking calendarBooking = null;

			long calendarBookingId = ParamUtil.getLong(
				renderRequest, "calendarBookingId");

			if (calendarBookingId > 0) {
				calendarBooking = CalendarBookingServiceUtil.getCalendarBooking(
					calendarBookingId);
			}

			renderRequest.setAttribute(
				WebKeys.CALENDAR_BOOKING, calendarBooking);

			CalendarResource calendarResource = null;

			long calendarResourceId = ParamUtil.getLong(
				renderRequest, "calendarResourceId");
			long classNameId = ParamUtil.getLong(renderRequest, "classNameId");
			long classPK = ParamUtil.getLong(renderRequest, "classPK");

			if (calendarResourceId > 0) {
				calendarResource =
					CalendarResourceServiceUtil.getCalendarResource(
						calendarResourceId);
			}
			else if ((classNameId > 0) && (classPK > 0)) {
				calendarResource = CalendarResourceUtil.getCalendarResource(
					PortalUtil.getHttpServletRequest(renderRequest),
					classNameId, classPK);
			}

			renderRequest.setAttribute(
				WebKeys.CALENDAR_RESOURCE, calendarResource);
		}
		catch (Exception e) {
			if (e instanceof NoSuchResourceException) {
				SessionErrors.add(renderRequest, e.getClass().getName());
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	public void serveCalendarResources(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Locale locale = themeDisplay.getLocale();

		long companyId = PortalUtil.getCompanyId(resourceRequest);
		String keywords = ParamUtil.getString(resourceRequest, "keywords");

		JSONArray resourcesJSONArray = JSONFactoryUtil.createJSONArray();

		long classNameId = PortalUtil.getClassNameId(CalendarResource.class);

		List<CalendarResource> calendarResources =
			CalendarResourceServiceUtil.search(
				companyId, new long[] {}, new long[] {classNameId}, keywords,
				true, true, 0, SearchContainer.DEFAULT_DELTA,
				new CalendarResourceNameComparator());

		for (CalendarResource calendarResource : calendarResources) {
			updateCalendarJSONArray(
				request, resourcesJSONArray, calendarResource.getClassNameId(),
				calendarResource.getClassPK(), calendarResource.getName(locale),
				locale);
		}

		long userClassNameId = PortalUtil.getClassNameId(User.class);

		List<User> users = UserLocalServiceUtil.search(
			companyId, keywords, 0, null, 0, SearchContainer.DEFAULT_DELTA,
			new UserFirstNameComparator());

		for (User user : users) {
			updateCalendarJSONArray(
				request, resourcesJSONArray, userClassNameId, user.getUserId(),
				user.getFullName(), locale);
		}

		long groupClassNameId = PortalUtil.getClassNameId(Group.class);

		String[] params = new String[] {
			"usersGroups:" + themeDisplay.getUserId() + ":long"
		};

		String name = StringUtil.merge(
			CustomSQLUtil.keywords(keywords), StringPool.BLANK);

		List<Group> groups = GroupServiceUtil.search(
			companyId, name, null, params, 0, SearchContainer.DEFAULT_DELTA);

		for (Group group : groups) {
			updateCalendarJSONArray(
				request, resourcesJSONArray, groupClassNameId,
				group.getGroupId(), group.getName(), locale);
		}

		writeJSON(resourceRequest, resourceResponse, resourcesJSONArray);
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

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CalendarBooking.class.getName(), actionRequest);

		User user = PortalUtil.getUser(actionRequest);

		TimeZone utcTimeZone = TimeZoneUtil.getTimeZone(StringPool.UTC);

		long calendarId = ParamUtil.getLong(actionRequest, "calendarId");
		long calendarBookingId = ParamUtil.getLong(
			actionRequest, "calendarBookingId");
		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "title");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		String location = ParamUtil.getString(actionRequest, "location");
		boolean allDay = ParamUtil.getBoolean(actionRequest, "allDay");
		String recurrence = ParamUtil.getString(actionRequest, "recurrence");
		int status = ParamUtil.getInteger(actionRequest, "status");

		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");
		int startDateHour = ParamUtil.getInteger(
			actionRequest, "startDateHour");
		int startDateMinute = ParamUtil.getInteger(
			actionRequest, "startDateMinute");
		int startDateAmPm = ParamUtil.getInteger(
			actionRequest, "startDateAmPm");

		if (startDateAmPm == java.util.Calendar.PM) {
			startDateHour += 12;
		}

		java.util.Calendar startDate = JCalendarUtil.getJCalendar(
			utcTimeZone, startDateYear, startDateMonth, startDateDay,
			startDateHour, startDateMinute, 0, 0);

		int endDateMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(actionRequest, "endDateYear");
		int endDateHour = ParamUtil.getInteger(actionRequest, "endDateHour");
		int endDateMinute = ParamUtil.getInteger(
			actionRequest, "endDateMinute");
		int endDateAmPm = ParamUtil.getInteger(actionRequest, "endDateAmPm");

		if (endDateAmPm == java.util.Calendar.PM) {
			endDateHour += 12;
		}

		java.util.Calendar endDate = JCalendarUtil.getJCalendar(
			utcTimeZone, endDateYear, endDateMonth, endDateDay, endDateHour,
			endDateMinute, 0, 0);

		CalendarBooking calendarBooking = null;

		if (calendarBookingId > 0) {
			calendarBooking =
				CalendarBookingServiceUtil.updateCalendarBooking(
					calendarBookingId, calendarId, titleMap, descriptionMap,
					location, status, startDate.getTime(), endDate.getTime(),
					allDay, recurrence, 0, 0, serviceContext);
		}
		else {
			calendarBooking =
				CalendarBookingServiceUtil.addCalendarBooking(
					calendarId, titleMap, descriptionMap, location,
					startDate.getTime(), endDate.getTime(), allDay, recurrence,
					0, 0, serviceContext);
		}

		if (calendarBooking.isMasterBooking()) {
			updateCalendarBookingInvitedCalendars(
				actionRequest, actionResponse, calendarBooking, serviceContext);
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
				calendarResourceId, defaultCalendarId, code, nameMap,
				descriptionMap, type, active, serviceContext);
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateCalendarResourceException ||
			cause instanceof PrincipalException) {

			return true;
		}

		return false;
	}

	protected void updateCalendarBookingInvitedCalendars(
			ActionRequest actionRequest, ActionResponse actionResponse,
			CalendarBooking parentCalendarBooking,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		long[] invitedCalendarIds = ParamUtil.getLongValues(
			actionRequest, "invitedCalendarIds");

		List<CalendarBooking> calendarBookings =
			CalendarBookingServiceUtil.getChildCalendarBookings(
				parentCalendarBooking.getCalendarBookingId());

		for (CalendarBooking calendarBooking : calendarBookings) {
			if (!ArrayUtil.contains(
					invitedCalendarIds, calendarBooking.getCalendarId())) {

				CalendarBookingServiceUtil.deleteCalendarBooking(
					calendarBooking.getCalendarBookingId());
			}
		}

		for (long calendarId : invitedCalendarIds) {
			int total =
				CalendarBookingLocalServiceUtil.getCalendarBookingsCount(
					calendarId, parentCalendarBooking.getCalendarBookingId());

			if (total == 0) {
				CalendarBookingLocalServiceUtil.addCalendarBooking(
					parentCalendarBooking.getUserId(), calendarId,
					parentCalendarBooking.getCalendarBookingId(),
					parentCalendarBooking.getTitleMap(),
					parentCalendarBooking.getDescriptionMap(),
					parentCalendarBooking.getLocation(),
					parentCalendarBooking.getUTCStartDate(),
					parentCalendarBooking.getUTCEndDate(),
					parentCalendarBooking.getAllDay(),
					parentCalendarBooking.getRecurrence(),
					parentCalendarBooking.getFirstReminder(),
					parentCalendarBooking.getSecondReminder(), serviceContext);
			}
		}
	}

	protected void updateCalendarJSONArray(
			HttpServletRequest request, JSONArray jsonArray, long classNameId,
			long classPK, String name, Locale locale)
		throws PortalException, SystemException {

		CalendarResource calendarResource =
			CalendarResourceUtil.getCalendarResource(
				request, classNameId, classPK);

		if (calendarResource != null) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			PermissionChecker permissionChecker =
				themeDisplay.getPermissionChecker();

			List<Calendar> calendars =
				CalendarLocalServiceUtil.getCalendarResourceCalendars(
					calendarResource.getGroupId(),
					calendarResource.getCalendarResourceId());

			for (Calendar calendar : calendars) {
				if (CalendarPermission.contains(
						permissionChecker, calendar, ActionKeys.VIEW)) {

					JSONObject jsonObject = CalendarUtil.toCalendarJSONObject(
						themeDisplay, calendar);

					jsonArray.put(jsonObject);
				}
			}
		}
	}

}