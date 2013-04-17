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
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarBookingServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.ModelHintsUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.net.URI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateList;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.CuType;
import net.fortuna.ical4j.model.parameter.PartStat;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.parameter.Rsvp;
import net.fortuna.ical4j.model.property.Action;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.DateProperty;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.ExDate;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.RRule;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Trigger;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;

/**
 * @author Marcellus Tavares
 */
public class CalendarICalDataHandler implements CalendarDataHandler {

	public String exportCalendar(long calendarId) throws Exception {
		List<CalendarBooking> calendarBookings =
			CalendarBookingLocalServiceUtil.getCalendarBookings(calendarId);

		net.fortuna.ical4j.model.Calendar iCalCalendar = toICalCalendar(
			calendarBookings);

		return toString(iCalCalendar);
	}

	public String exportCalendarBooking(long calendarBookingId)
		throws Exception {

		List<CalendarBooking> calendarBookings =
			new ArrayList<CalendarBooking>();

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.getCalendarBooking(
				calendarBookingId);

		calendarBookings.add(calendarBooking);

		net.fortuna.ical4j.model.Calendar iCalCalendar = toICalCalendar(
			calendarBookings);

		return toString(iCalCalendar);
	}

	public void importCalendar(long calendarId, String data) throws Exception {
		CalendarBuilder calendarBuilder = new CalendarBuilder();

		UnsyncStringReader unsyncStringReader = new UnsyncStringReader(data);

		net.fortuna.ical4j.model.Calendar iCalCalendar = calendarBuilder.build(
			unsyncStringReader);

		List<VEvent> vEvents = iCalCalendar.getComponents(Component.VEVENT);

		for (VEvent vEvent : vEvents) {
			importICalEvent(calendarId, vEvent);
		}
	}

	protected void importICalEvent(long calendarId, VEvent vEvent)
		throws Exception {

		Calendar calendar = CalendarLocalServiceUtil.getCalendar(calendarId);

		// Title

		User user = UserLocalServiceUtil.getUser(calendar.getUserId());

		Map<Locale, String> titleMap = new HashMap<Locale, String>();

		Summary summary = vEvent.getSummary();

		if (summary != null) {
			String title = ModelHintsUtil.trimString(
				CalendarBooking.class.getName(), "title", summary.getValue());

			titleMap.put(user.getLocale(), title);
		}

		// Description

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();

		Description description = vEvent.getDescription();

		if (description != null) {
			descriptionMap.put(user.getLocale(), description.getValue());
		}

		// Location

		String locationString = StringPool.BLANK;

		Location location = vEvent.getLocation();

		if (location != null) {
			locationString = location.getValue();
		}

		// Dates

		DtStart dtStart = vEvent.getStartDate();

		Date startDate = dtStart.getDate();

		DtEnd dtEnd = vEvent.getEndDate();

		Date endDate = dtEnd.getDate();

		// All day

		boolean allDay = false;

		if (isICalDateOnly(dtStart)) {
			allDay = true;

			long time = endDate.getTime();

			endDate.setTime(time - 1);
		}

		// Recurrence

		RRule rrule = (RRule)vEvent.getProperty(Property.RRULE);

		String recurrence = StringPool.BLANK;

		if (rrule != null) {
			recurrence = StringUtil.trim(rrule.toString());

			PropertyList propertyList = vEvent.getProperties(Property.EXDATE);

			if (!propertyList.isEmpty()) {
				StringBundler sb = new StringBundler();

				Iterator<ExDate> iterator = propertyList.iterator();

				while (iterator.hasNext()) {
					ExDate exDate = iterator.next();

					DateList dateList = exDate.getDates();

					ListIterator<Date> listIterator = dateList.listIterator();

					while (listIterator.hasNext()) {
						Date date = listIterator.next();

						java.util.Calendar jCalendar =
							JCalendarUtil.getJCalendar(date.getTime());

						int year = jCalendar.get(java.util.Calendar.YEAR);
						int month = jCalendar.get(java.util.Calendar.MONTH) + 1;
						int day = jCalendar.get(java.util.Calendar.DATE);
						int hour = jCalendar.get(
							java.util.Calendar.HOUR_OF_DAY);
						int minute = jCalendar.get(java.util.Calendar.MINUTE);
						int second = jCalendar.get(java.util.Calendar.SECOND);

						sb.append(
							String.format(
								_EXDATE_FORMAT, year, month, day, hour, minute,
								second));

						if (listIterator.hasNext()) {
							sb.append(StringPool.COMMA);
						}
					}

					if (iterator.hasNext()) {
						sb.append(StringPool.COMMA);
					}
				}

				recurrence = recurrence.concat(
					StringPool.NEW_LINE).concat(_EXDATE).concat(sb.toString());
			}
		}

		// Reminders

		ComponentList componentList = vEvent.getAlarms();

		long[] reminders = new long[componentList.size()];
		String[] reminderTypes = new String[componentList.size()];

		int i = 0;

		for (Iterator<VAlarm> iterator = componentList.iterator();
				iterator.hasNext();) {

			VAlarm vAlarm = iterator.next();

			Action action = vAlarm.getAction();

			String value = StringUtil.lowerCase(action.getValue());

			if (!isActionSupported(value)) {
				continue;
			}

			reminderTypes[i] = value;

			Trigger trigger = vAlarm.getTrigger();

			long time = 0;

			DateTime dateTime = trigger.getDateTime();

			Dur dur = trigger.getDuration();

			if ((dateTime == null) && (dur == null)) {
				continue;
			}

			if (dateTime != null) {
				time = startDate.getTime() - dateTime.getTime();

				if (time < 0) {
					continue;
				}
			}
			else {
				if (!dur.isNegative()) {
					continue;
				}

				time += dur.getWeeks() * Time.WEEK;
				time += dur.getDays() * Time.DAY;
				time += dur.getHours() * Time.HOUR;
				time += dur.getMinutes() * Time.MINUTE;
				time += dur.getSeconds() * Time.SECOND;
			}

			reminders[i] = time;

			i++;
		}

		long firstReminder = 0;
		String firstReminderType = null;
		long secondReminder = 0;
		String secondReminderType = null;

		if (i > 0) {
			firstReminder = reminders[0];
			firstReminderType = reminderTypes[0];
		}

		if (i > 1) {
			secondReminder = reminders[1];
			secondReminderType = reminderTypes[1];
		}

		// Attendees

		PropertyList propertyList = vEvent.getProperties(Property.ATTENDEE);

		List<Long> childCalendarIds = new ArrayList<Long>();

		for (Iterator<Attendee> iterator = propertyList.iterator();
				iterator.hasNext();) {

			Attendee attendee = iterator.next();

			URI uri = attendee.getCalAddress();

			if (uri == null) {
				continue;
			}

			User attendeeUser = UserLocalServiceUtil.fetchUserByEmailAddress(
				calendar.getCompanyId(), uri.getSchemeSpecificPart());

			if ((attendeeUser == null) ||
				(calendar.getUserId() == attendeeUser.getUserId())) {

				continue;
			}

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(calendar.getCompanyId());
			serviceContext.setScopeGroupId(calendar.getGroupId());

			CalendarResource calendarResource =
				CalendarResourceUtil.getUserCalendarResource(
					attendeeUser.getUserId(), serviceContext);

			if (calendarResource == null) {
				continue;
			}

			childCalendarIds.add(calendarResource.getDefaultCalendarId());
		}

		long[] childCalendarIdsArray = ArrayUtil.toArray(
			childCalendarIds.toArray(new Long[childCalendarIds.size()]));

		// Merge calendar booking

		CalendarBooking calendarBooking = null;

		String uuid = null;

		Uid uid = vEvent.getUid();

		if (uid != null) {
			uuid = uid.getValue();

			calendarBooking =
				CalendarBookingLocalServiceUtil.fetchCalendarBooking(
					uuid, calendar.getGroupId());

			if (calendarBooking == null) {
				uuid = PortalUUIDUtil.generate(uuid.getBytes());

				calendarBooking =
					CalendarBookingLocalServiceUtil.fetchCalendarBooking(
						uuid, calendar.getGroupId());
			}
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(calendar.getGroupId());

		if (calendarBooking == null) {
			serviceContext.setUuid(uuid);

			CalendarBookingServiceUtil.addCalendarBooking(
				calendarId, childCalendarIdsArray,
				CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
				titleMap, descriptionMap, locationString, startDate.getTime(),
				endDate.getTime(), allDay, recurrence, firstReminder,
				firstReminderType, secondReminder, secondReminderType,
				serviceContext);
		}
		else {
			CalendarBookingServiceUtil.updateCalendarBooking(
				calendarBooking.getCalendarBookingId(), calendarId,
				childCalendarIdsArray, titleMap, descriptionMap, locationString,
				startDate.getTime(), endDate.getTime(), allDay, recurrence,
				firstReminder, firstReminderType, secondReminder,
				secondReminderType, calendarBooking.getStatus(),
				serviceContext);
		}
	}

	protected boolean isActionSupported(String value) {
		try {
			NotificationType.parse(value);
		}
		catch (IllegalArgumentException iae) {
			return false;
		}

		return true;
	}

	protected boolean isICalDateOnly(DateProperty dateProperty) {
		Parameter valueParameter = dateProperty.getParameter(Parameter.VALUE);

		if (valueParameter == null) {
			return false;
		}

		String value = valueParameter.getValue();

		if (value.equals("DATE")) {
			return true;
		}

		return false;
	}

	protected VAlarm toICalAlarm(
		NotificationType notificationType, long reminder, String emailAddress) {

		Dur dur = toICalDur(reminder);

		VAlarm vAlarm = new VAlarm(dur);

		PropertyList propertyList = vAlarm.getProperties();

		Action action = Action.DISPLAY;

		if (notificationType == NotificationType.EMAIL) {
			URI uri = URI.create("mailto:".concat(emailAddress));

			Attendee attendee = new Attendee(uri);

			action = Action.EMAIL;

			propertyList.add(attendee);
			propertyList.add(new Summary("Alarm Notification"));
		}

		propertyList.add(action);
		propertyList.add(new Description("This is an event reminder."));

		return vAlarm;
	}

	protected Attendee toICalAttendee(
		String fullName, String emailAddress, int status) {

		Attendee attendee = new Attendee();

		URI uri = URI.create("mailto:".concat(emailAddress));

		attendee.setCalAddress(uri);

		Cn cn = new Cn(fullName);

		ParameterList parameters = attendee.getParameters();

		parameters.add(cn);
		parameters.add(CuType.INDIVIDUAL);
		parameters.add(Role.REQ_PARTICIPANT);
		parameters.add(Rsvp.TRUE);

		if (status == WorkflowConstants.STATUS_APPROVED) {
			parameters.add(PartStat.ACCEPTED);
		}
		else {
			parameters.add(PartStat.NEEDS_ACTION);
		}

		return attendee;
	}

	protected net.fortuna.ical4j.model.Calendar toICalCalendar(
			List<CalendarBooking> calendarBookings)
		throws Exception {

		net.fortuna.ical4j.model.Calendar iCalCalendar =
			new net.fortuna.ical4j.model.Calendar();

		PropertyList propertiesList = iCalCalendar.getProperties();

		ProdId prodId = new ProdId(
			"-//Liferay Inc//Liferay Portal " + ReleaseInfo.getVersion() +
			"//EN");

		propertiesList.add(prodId);

		propertiesList.add(Version.VERSION_2_0);
		propertiesList.add(CalScale.GREGORIAN);
		propertiesList.add(Method.PUBLISH);

		List<VEvent> vEvents = iCalCalendar.getComponents();

		for (CalendarBooking calendarBooking : calendarBookings) {
			vEvents.add(toICalEvent(calendarBooking));
		}

		return iCalCalendar;
	}

	protected DateTime toICalDateTime(long time) {
		DateTime dateTime = new DateTime();

		dateTime.setTime(time);
		dateTime.setUtc(true);

		return dateTime;
	}

	protected Dur toICalDur(long reminder) {
		int weeks = (int)(reminder / Time.WEEK);

		if (weeks > 0) {
			return new Dur(weeks);
		}

		int days = (int)(reminder / Time.DAY);

		if (days > 0) {
			return new Dur(days, 0, 0, 0);
		}

		int hours = (int)(reminder / Time.HOUR);

		if (hours > 0) {
			return new Dur(0, hours, 0, 0);
		}

		int minutes = (int)(reminder / Time.MINUTE);

		if (minutes > 0) {
			return new Dur(0, 0, minutes, 0);
		}

		int seconds = (int)(reminder / Time.SECOND);

		if (seconds > 0) {
			return new Dur(0, 0, 0, seconds);
		}

		return null;
	}

	protected VEvent toICalEvent(CalendarBooking calendarBooking)
		throws Exception {

		VEvent vEvent = new VEvent();

		PropertyList propertyList = vEvent.getProperties();

		// UID

		Uid uid = new Uid(calendarBooking.getUuid());

		propertyList.add(uid);

		// Dates

		if (calendarBooking.isAllDay()) {
			DtStart dtStart = new DtStart(
				new Date(calendarBooking.getStartTime()));

			propertyList.add(dtStart);
		}
		else {
			DtStart dtStart = new DtStart(
				toICalDateTime(calendarBooking.getStartTime()));

			propertyList.add(dtStart);

			DtEnd dtEnd = new DtEnd(
				toICalDateTime(calendarBooking.getEndTime()));

			propertyList.add(dtEnd);
		}

		// Title

		User user = UserLocalServiceUtil.getUser(calendarBooking.getUserId());

		Summary summary = new Summary(
			calendarBooking.getTitle(user.getLocale()));

		propertyList.add(summary);

		// Description

		Description description = new Description(
			calendarBooking.getDescription(user.getLocale()));

		propertyList.add(description);

		// Location

		Location location = new Location(calendarBooking.getLocation());

		propertyList.add(location);

		// Recurrence

		if (calendarBooking.isRecurring()) {
			String recurrence = calendarBooking.getRecurrence();

			int index = recurrence.indexOf(StringPool.NEW_LINE);

			if (index > 0) {
				recurrence = recurrence.substring(0, index);
			}

			String value = StringUtil.replace(
				recurrence, _RRULE, StringPool.BLANK);

			RRule rRule = new RRule(value);

			propertyList.add(rRule);

			ExDate exDate = toICalExDate(calendarBooking.getRecurrenceObj());

			if (exDate != null) {
				propertyList.add(exDate);
			}
		}

		// Reminders

		ComponentList componentList = vEvent.getAlarms();

		long firstReminder = calendarBooking.getFirstReminder();

		if (firstReminder > 0) {
			VAlarm vAlarm = toICalAlarm(
				calendarBooking.getFirstReminderNotificationType(),
				firstReminder, user.getEmailAddress());

			componentList.add(vAlarm);
		}

		long secondReminder = calendarBooking.getSecondReminder();

		if (secondReminder > 0) {
			VAlarm alarm = toICalAlarm(
				calendarBooking.getSecondReminderNotificationType(),
				secondReminder, user.getEmailAddress());

			componentList.add(alarm);
		}

		// Attendees

		List<CalendarBooking> childCalendarBookings =
			calendarBooking.getChildCalendarBookings();

		for (CalendarBooking childCalendarBooking : childCalendarBookings) {
			CalendarResource calResource =
				childCalendarBooking.getCalendarResource();

			if (!calResource.isUser() ||
				(calendarBooking.getCalendarBookingId() ==
					childCalendarBooking.getCalendarBookingId())) {

				continue;
			}

			User calResourceUser = UserLocalServiceUtil.getUser(
				calResource.getClassPK());

			Attendee attendee = toICalAttendee(
				calResourceUser.getFullName(),
				calResourceUser.getEmailAddress(),
				childCalendarBooking.getStatus());

			propertyList.add(attendee);
		}

		return vEvent;
	}

	protected ExDate toICalExDate(Recurrence recurrence) {
		List<java.util.Calendar> exceptionJCalendars =
			recurrence.getExceptionJCalendars();

		if (exceptionJCalendars.isEmpty()) {
			return null;
		}

		DateList dateList = new DateList();

		dateList.setUtc(true);

		for (java.util.Calendar exceptionJCalendar : exceptionJCalendars) {
			DateTime dateTime = toICalDateTime(
				exceptionJCalendar.getTimeInMillis());

			dateList.add(dateTime);
		}

		ExDate exDate = new ExDate(dateList);

		return exDate;
	}

	protected String toString(net.fortuna.ical4j.model.Calendar iCalCalendar)
		throws Exception {

		CalendarOutputter calendarOutputter = new CalendarOutputter();

		ComponentList componentList = iCalCalendar.getComponents();

		if (componentList.isEmpty()) {
			calendarOutputter.setValidating(false);
		}

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		calendarOutputter.output(iCalCalendar, unsyncStringWriter);

		unsyncStringWriter.flush();

		return unsyncStringWriter.toString();
	}

	private static final String _EXDATE =
		"EXDATE;TZID=\"UTC\";VALUE=DATE-TIME:";

	private static final String _EXDATE_FORMAT = "%04d%02d%02dT%02d%02d%02dZ";

	private static final String _RRULE = "RRULE:";

}