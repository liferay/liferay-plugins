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

package com.liferay.calendar.lar;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.service.persistence.CalendarBookingUtil;
import com.liferay.calendar.service.persistence.CalendarResourceUtil;
import com.liferay.calendar.service.persistence.CalendarUtil;
import com.liferay.calendar.util.PortletKeys;
import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Marcellus Tavares
 */
public class CalendarPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "calendar";

	public CalendarPortletDataHandler() {
		setAlwaysExportable(true);
		setDataLocalized(true);
		setDataPortletPreferences(
			"calendarNotificationBodyEmailInvite",
			"calendarNotificationBodyEmailReminder",
			"calendarNotificationSubjectEmailInvite",
			"calendarNotificationSubjectEmailReminder", "defaultDuration",
			"defaultView", "emailFromAddress", "emailFromName", "isoTimeFormat",
			"timeZoneId", "usePortalTimeZone", "weekStartsOn");
		setExportControls(
			new PortletDataHandlerBoolean(NAMESPACE, "bookings", true, false));
		setImportControls(new PortletDataHandlerControl[0]);
		setPublishToLiveByDefault(true);
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletDataContext.addPrimaryKey(
				CalendarPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		CalendarResourceLocalServiceUtil.deleteCalendarResources(
			portletDataContext.getScopeGroupId());

		return portletPreferences;
	}

	@Override
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.addPermissions(
			"com.liferay.portlet.calendar",
			portletDataContext.getScopeGroupId());

		Element rootElement = addExportDataRootElement(portletDataContext);

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		Element calendarsElement = rootElement.addElement("calendars");
		Element calendarBookingsElement = rootElement.addElement(
			"calendar-bookings");
		Element calendarResourcesElement = rootElement.addElement(
			"calendar-resources");

		List<CalendarResource> calendarResources =
			CalendarResourceLocalServiceUtil.getCalendarResources(
				portletDataContext.getScopeGroupId());

		for (CalendarResource calendarResource : calendarResources) {
			if (portletDataContext.isWithinDateRange(
					calendarResource.getModifiedDate())) {

					exportCalendarResource(
						portletDataContext, calendarsElement,
						calendarBookingsElement, calendarResourcesElement,
						calendarResource);
			}
		}

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		portletDataContext.importPermissions(
			"com.liferay.portlet.calendar",
			portletDataContext.getSourceGroupId(),
			portletDataContext.getScopeGroupId());

		Element rootElement = portletDataContext.getImportDataRootElement();

		Element calendarResourcesElement = rootElement.element(
			"calendar-resources");

		List<Element> calendarResourceElements =
			calendarResourcesElement.elements("calendar-resource");

		for (Element calendarResourceElement : calendarResourceElements) {
			importCalendarResource(portletDataContext, calendarResourceElement);
		}

		Element calendarsElement = rootElement.element("calendars");

		List<Element> calendarElements = calendarsElement.elements("calendar");

		for (Element calendarElement : calendarElements) {
			importCalendar(portletDataContext, calendarElement);
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "bookings")) {
			Element calendarBookingsElement = rootElement.element(
				"calendar-bookings");

			List<Element> calendarBookingElements =
				calendarBookingsElement.elements("calendar-booking");

			for (Element calendarBookingElement : calendarBookingElements) {
				importCalendarBooking(
					portletDataContext, calendarBookingElement);
			}
		}

		return portletPreferences;
	}

	protected void exportCalendar(
			PortletDataContext portletDataContext, Element calendarsElement,
			Element calendarBookingsElement, Calendar calendar)
		throws Exception {

		String path = getCalendarPath(portletDataContext, calendar);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "bookings")) {
			List<CalendarBooking> calendarBookings =
				CalendarBookingLocalServiceUtil.getCalendarBookings(
					calendar.getCalendarId());

			for (CalendarBooking calendarBooking : calendarBookings) {
				exportCalendarBooking(
					portletDataContext, calendarBookingsElement,
					calendarBooking);
			}
		}

		Element calendarElement = calendarsElement.addElement("calendar");

		portletDataContext.addClassedModel(
			calendarElement, path, calendar, NAMESPACE);
	}

	protected void exportCalendarBooking(
			PortletDataContext portletDataContext,
			Element calendarBookingsElement, CalendarBooking calendarBooking)
		throws Exception {

		String path = getCalendarBookingPath(
			portletDataContext, calendarBooking);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		Element bookingElement = calendarBookingsElement.addElement(
			"calendar-booking");

		portletDataContext.addClassedModel(
			bookingElement, path, calendarBooking, NAMESPACE);
	}

	protected void exportCalendarResource(
			PortletDataContext portletDataContext, Element calendarsElement,
			Element calendarBookingsElement, Element calendarResourcesElement,
			CalendarResource calendarResource)
		throws Exception {

		String path = getCalendarResourcePath(
			portletDataContext, calendarResource);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		List<Calendar> calendars = calendarResource.getCalendars();

		for (Calendar calendar : calendars) {
			exportCalendar(
				portletDataContext, calendarsElement, calendarBookingsElement,
				calendar);
		}

		Element resourceElement = calendarResourcesElement.addElement(
			"calendar-resource");

		portletDataContext.addClassedModel(
			resourceElement, path, calendarResource, NAMESPACE);
	}

	protected String getCalendarBookingPath(
		PortletDataContext portletDataContext,
		CalendarBooking calendarBooking) {

		StringBundler sb = new StringBundler(4);

		sb.append(portletDataContext.getPortletPath(PortletKeys.CALENDAR));
		sb.append("/calendar-bookings/");
		sb.append(calendarBooking.getCalendarBookingId());
		sb.append(".xml");

		return sb.toString();
	}

	protected String getCalendarPath(
		PortletDataContext portletDataContext, Calendar calendar) {

		StringBundler sb = new StringBundler(4);

		sb.append(portletDataContext.getPortletPath(PortletKeys.CALENDAR));
		sb.append("/calendars/");
		sb.append(calendar.getCalendarId());
		sb.append(".xml");

		return sb.toString();
	}

	protected String getCalendarResourcePath(
		PortletDataContext portletDataContext,
		CalendarResource calendarResource) {

		StringBundler sb = new StringBundler(5);

		sb.append(portletDataContext.getPortletPath(PortletKeys.CALENDAR));
		sb.append("/calendar-resources/");
		sb.append(calendarResource.getCalendarResourceId());
		sb.append(".xml");

		return sb.toString();
	}

	protected String getImportCalendarBookingPath(
		PortletDataContext portletDataContext, long calendarBookingId) {

		StringBundler sb = new StringBundler(4);

		sb.append(
			portletDataContext.getSourcePortletPath(PortletKeys.CALENDAR));
		sb.append("/calendar-bookings/");
		sb.append(calendarBookingId);
		sb.append(".xml");

		return sb.toString();
	}

	protected String getImportCalendarPath(
		PortletDataContext portletDataContext, long calendarId) {

		StringBundler sb = new StringBundler(4);

		sb.append(
			portletDataContext.getSourcePortletPath(PortletKeys.CALENDAR));
		sb.append("/calendars/");
		sb.append(calendarId);
		sb.append(".xml");

		return sb.toString();
	}

	protected void importCalendar(
			PortletDataContext portletDataContext, Element calendarElement)
		throws Exception {

		String path = calendarElement.attributeValue("path");

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		Calendar calendar = (Calendar)portletDataContext.getZipEntryAsObject(
			path);

		long userId = portletDataContext.getUserId(calendar.getUserUuid());

		Map<Long, Long> calendarResourceIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				CalendarResource.class);

		long calendarResourceId = MapUtil.getLong(
			calendarResourceIds, calendar.getCalendarResourceId(),
			calendar.getCalendarResourceId());

		CalendarResource calendarResource =
			CalendarResourceLocalServiceUtil.getCalendarResource(
				calendarResourceId);

		long groupId = calendarResource.getGroupId();

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			path, calendar, NAMESPACE);

		Calendar importedCalendar = null;

		if (calendar.isDefaultCalendar()) {
			importedCalendar = CalendarLocalServiceUtil.updateCalendar(
				calendarResource.getDefaultCalendarId(), calendar.getNameMap(),
				calendar.getDescriptionMap(), calendar.getColor(),
				serviceContext);
		}
		else {
			if (portletDataContext.isDataStrategyMirror()) {
				Calendar existingCalendar = CalendarUtil.fetchByUUID_G(
					calendar.getUuid(), groupId);

				if (existingCalendar == null) {
					serviceContext.setUuid(calendar.getUuid());

					importedCalendar = CalendarLocalServiceUtil.addCalendar(
						userId, groupId, calendarResourceId,
						calendar.getNameMap(), calendar.getDescriptionMap(),
						calendar.getColor(), calendar.getDefaultCalendar(),
						serviceContext);
				}
				else {
					importedCalendar = CalendarLocalServiceUtil.updateCalendar(
						existingCalendar.getCalendarId(), calendar.getNameMap(),
						calendar.getDescriptionMap(), calendar.getColor(),
						serviceContext);
				}
			}
			else {
				importedCalendar = CalendarLocalServiceUtil.addCalendar(
					userId, groupId, calendarResourceId, calendar.getNameMap(),
					calendar.getDescriptionMap(), calendar.getColor(),
					calendar.getDefaultCalendar(), serviceContext);
			}
		}

		portletDataContext.importClassedModel(
			calendar, importedCalendar, NAMESPACE);
	}

	protected void importCalendarBooking(
			PortletDataContext portletDataContext,
			Element calendarBookingElement)
		throws Exception {

		String path = calendarBookingElement.attributeValue("path");

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		CalendarBooking calendarBooking =
			(CalendarBooking)portletDataContext.getZipEntryAsObject(path);

		importCalendarBooking(
			portletDataContext, path, calendarBookingElement, calendarBooking);
	}

	protected void importCalendarBooking(
			PortletDataContext portletDataContext, String path,
			Element calendarBookingElement, CalendarBooking calendarBooking)
		throws Exception {

		long userId = portletDataContext.getUserId(
			calendarBooking.getUserUuid());

		Map<Long, Long> calendarIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Calendar.class);

		long calendarId = MapUtil.getLong(
			calendarIds, calendarBooking.getCalendarId(),
			calendarBooking.getCalendarId());

		Calendar calendar = CalendarLocalServiceUtil.getCalendar(calendarId);

		long groupId = calendar.getGroupId();

		Map<Long, Long> calendarBookingIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				CalendarBooking.class);

		long parentCalendarBookingId = 0;

		if (calendarBooking.getParentCalendarBookingId() !=
				calendarBooking.getCalendarBookingId()) {

			String importCalendarBookingPath = getImportCalendarBookingPath(
				portletDataContext,
				calendarBooking.getParentCalendarBookingId());

			if (portletDataContext.isPathNotProcessed(path)) {
				CalendarBooking parentCalendarBooking =
					(CalendarBooking)portletDataContext.getZipEntryAsObject(
						path);

				importCalendarBooking(
					portletDataContext, importCalendarBookingPath,
					calendarBookingElement, parentCalendarBooking);

				parentCalendarBookingId = MapUtil.getLong(
					calendarBookingIds,
					calendarBooking.getParentCalendarBookingId(),
					calendarBooking.getParentCalendarBookingId());
			}
		}

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			calendarBookingElement, calendarBooking, NAMESPACE);

		CalendarBooking importedCalendarBooking = null;

		if (portletDataContext.isDataStrategyMirror()) {
			CalendarBooking existingCalendarBooking =
				CalendarBookingUtil.fetchByUUID_G(
					calendarBooking.getUuid(), groupId);

			if (existingCalendarBooking == null) {
				serviceContext.setUuid(calendarBooking.getUuid());

				importedCalendarBooking =
					CalendarBookingLocalServiceUtil.addCalendarBooking(
						userId, calendarId, new long[0],
						parentCalendarBookingId, calendarBooking.getTitleMap(),
						calendarBooking.getDescriptionMap(),
						calendarBooking.getLocation(),
						calendarBooking.getStartTime(),
						calendarBooking.getEndTime(),
						calendarBooking.getAllDay(),
						calendarBooking.getRecurrence(),
						calendarBooking.getFirstReminder(),
						calendarBooking.getFirstReminderType(),
						calendarBooking.getSecondReminder(),
						calendarBooking.getSecondReminderType(),
						serviceContext);
			}
			else {
				importedCalendarBooking =
					CalendarBookingLocalServiceUtil.updateCalendarBooking(
						userId, existingCalendarBooking.getCalendarBookingId(),
						calendarId, new long[0], calendarBooking.getTitleMap(),
						calendarBooking.getDescriptionMap(),
						calendarBooking.getLocation(),
						calendarBooking.getStartTime(),
						calendarBooking.getEndTime(),
						calendarBooking.getAllDay(),
						calendarBooking.getRecurrence(),
						calendarBooking.getFirstReminder(),
						calendarBooking.getFirstReminderType(),
						calendarBooking.getSecondReminder(),
						calendarBooking.getSecondReminderType(),
						calendarBooking.getStatus(), serviceContext);
			}
		}
		else {
			importedCalendarBooking =
				CalendarBookingLocalServiceUtil.addCalendarBooking(
					userId, calendarId, new long[0], parentCalendarBookingId,
					calendarBooking.getTitleMap(),
					calendarBooking.getDescriptionMap(),
					calendarBooking.getLocation(),
					calendarBooking.getStartTime(),
					calendarBooking.getEndTime(), calendarBooking.getAllDay(),
					calendarBooking.getRecurrence(),
					calendarBooking.getFirstReminder(),
					calendarBooking.getFirstReminderType(),
					calendarBooking.getSecondReminder(),
					calendarBooking.getSecondReminderType(), serviceContext);
		}

		portletDataContext.importClassedModel(
			calendarBooking, importedCalendarBooking, NAMESPACE);

	}

	protected void importCalendarResource(
			PortletDataContext portletDataContext,
			Element calendarResourceElement)
		throws Exception {

		String path = calendarResourceElement.attributeValue("path");

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		CalendarResource calendarResource =
			(CalendarResource)portletDataContext.getZipEntryAsObject(path);

		long userId = portletDataContext.getUserId(
			calendarResource.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			calendarResourceElement, calendarResource, NAMESPACE);

		CalendarResource importedCalendarResource = null;

		if (portletDataContext.isDataStrategyMirror()) {
			CalendarResource existingCalendarResource =
				CalendarResourceUtil.fetchByUUID_G(
					calendarResource.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingCalendarResource == null) {
				serviceContext.setUuid(calendarResource.getUuid());

				importedCalendarResource =
					CalendarResourceLocalServiceUtil.addCalendarResource(
						userId, portletDataContext.getScopeGroupId(),
						calendarResource.getClassName(), 0,
						calendarResource.getClassUuid(),
						calendarResource.getCode(),
						calendarResource.getNameMap(),
						calendarResource.getDescriptionMap(),
						calendarResource.isActive(), serviceContext);
			}
			else {
				importedCalendarResource =
					CalendarResourceLocalServiceUtil.updateCalendarResource(
						existingCalendarResource.getCalendarResourceId(),
						calendarResource.getNameMap(),
						calendarResource.getDescriptionMap(),
						calendarResource.isActive(), serviceContext);
			}
		}
		else {
			importedCalendarResource =
				CalendarResourceLocalServiceUtil.addCalendarResource(
					userId, portletDataContext.getScopeGroupId(),
					calendarResource.getClassName(), 0,
					calendarResource.getClassUuid(), calendarResource.getCode(),
					calendarResource.getNameMap(),
					calendarResource.getDescriptionMap(),
					calendarResource.isActive(), serviceContext);
		}

		portletDataContext.importClassedModel(
			calendarResource, importedCalendarResource, NAMESPACE);
	}

}