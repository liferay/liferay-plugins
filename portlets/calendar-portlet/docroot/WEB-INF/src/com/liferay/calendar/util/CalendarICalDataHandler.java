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
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarBookingServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.ModelHintsUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.net.URI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.Date;
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
		CalendarBuilder builder = new CalendarBuilder();

		UnsyncStringReader unsyncStringReader = new UnsyncStringReader(data);

		net.fortuna.ical4j.model.Calendar calendar = builder.build(
			unsyncStringReader);

		List<VEvent> vEvents = calendar.getComponents(Component.VEVENT);

		for (VEvent vEvent : vEvents) {
			importICalEvent(calendarId, vEvent);
		}
	}

	protected void importICalEvent(long calendarId, VEvent event)
		throws Exception {

		Calendar calendar = CalendarLocalServiceUtil.getCalendar(calendarId);

		long companyId = calendar.getCompanyId();
		long groupId = calendar.getGroupId();

		User owner = UserLocalServiceUtil.getUser(calendar.getUserId());

		Locale locale = owner.getLocale();

		// Title

		Map<Locale, String> titleMap = new HashMap<Locale, String>();

		Summary summary = event.getSummary();

		if (summary != null) {
			String title = ModelHintsUtil.trimString(
				CalendarBooking.class.getName(), "title", summary.getValue());

			titleMap.put(locale, title);
		}

		// Description

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();

		Description description = event.getDescription();

		if (description != null) {
			descriptionMap.put(locale, description.getValue());
		}

		// Location

		String location = StringPool.BLANK;

		if (event.getLocation() != null) {
			location = event.getLocation().getValue();
		}

		// Start date

		Date startDate = event.getStartDate().getDate();

		// End date

		Date endDate = event.getEndDate().getDate();

		// All day

		boolean allDay = false;

		if (isICalDateOnly(event.getStartDate())) {
			allDay = true;
		}

		// Recurrence

		RRule rrule = (RRule)event.getProperty(Property.RRULE);

		String recurrence = StringPool.BLANK;

		if (rrule != null) {
			recurrence = StringUtil.trim(rrule.toString());
		}

		// Reminders

		ComponentList alarms = event.getAlarms();

		int remindersSize = alarms.size();

		long[] reminders = new long[remindersSize];
		String[] reminderTypes = new String[remindersSize];

		int i = 0;

		for (Iterator<VAlarm> it = alarms.iterator(); it.hasNext(); i++) {
			VAlarm vAlarm = it.next();

			Action action = vAlarm.getAction();

			String value = StringUtil.lowerCase(action.getValue());

			if (!isActionSupported(value)) {
				continue;
			}

			reminderTypes[i] = value;

			Trigger trigger = vAlarm.getTrigger();

			Dur dur = trigger.getDuration();

			long time = (dur.getWeeks() * Time.WEEK) +
				(dur.getDays() * Time.DAY) + (dur.getHours() * Time.HOUR) +
				(dur.getMinutes() * Time.MINUTE) +
				(dur.getSeconds() * Time.SECOND);

			reminders[i] = time;
		}

		// Attendees

		PropertyList attendees = event.getProperties(Property.ATTENDEE);

		List<Long> childCalendarIds = new ArrayList<Long>();

		for (Iterator<Attendee> it = attendees.iterator(); it.hasNext();) {
			Attendee attendee = it.next();

			URI calAddress = attendee.getCalAddress();

			User user = UserLocalServiceUtil.fetchUserByEmailAddress(
				companyId, calAddress.getSchemeSpecificPart());

			if ((user == null) || (calendar.getUserId() == user.getUserId())) {
				continue;
			}

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setScopeGroupId(groupId);

			CalendarResource calendarResource =
				CalendarResourceUtil.getUserCalendarResource(
					user.getUserId(), serviceContext);

			if (calendarResource == null) {
				continue;
			}

			childCalendarIds.add(calendarResource.getDefaultCalendarId());
		}

		// Merge calendar booking

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(groupId);

		String uuid = null;

		CalendarBooking calendarBooking = null;

		Uid uid = event.getUid();

		if (uid != null) {
			uuid = uid.getValue();

			calendarBooking =
				CalendarBookingLocalServiceUtil.fetchCalendarBooking(
					uuid, groupId);

			if (calendarBooking == null) {
				uuid = PortalUUIDUtil.generate(uuid.getBytes());

				calendarBooking =
					CalendarBookingLocalServiceUtil.fetchCalendarBooking(
						uuid, calendar.getGroupId());
			}
		}

		long[] childCalendarIdsArray = ArrayUtil.toArray(
			childCalendarIds.toArray(new Long[childCalendarIds.size()]));

		long firstReminder = 0;
		String firstReminderType = null;

		long secondReminder = 0;
		String secondReminderType = null;

		if (remindersSize > 0) {
			firstReminder = reminders[0];
			firstReminderType = reminderTypes[0];

			if (remindersSize > 1) {
				secondReminder = reminders[1];
				secondReminderType = reminderTypes[1];
			}
		}

		if (calendarBooking == null) {
			serviceContext.setUuid(uuid);

			CalendarBookingServiceUtil.addCalendarBooking(
				calendarId, childCalendarIdsArray,
				CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
				titleMap, descriptionMap, location, startDate.getTime(),
				endDate.getTime(), allDay, recurrence, firstReminder,
				firstReminderType, secondReminder, secondReminderType,
				serviceContext);
		}
		else {
			CalendarBookingServiceUtil.updateCalendarBooking(
				calendarBooking.getCalendarBookingId(), calendarId,
				childCalendarIdsArray, titleMap, descriptionMap, location,
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

		if ((valueParameter != null) &&
			valueParameter.getValue().equals("DATE")) {

			return true;
		}

		return false;
	}

	protected VAlarm toICalAlarm(
		NotificationType notificationType, long reminder, String emailAddress) {

		Dur dur = toICalDur(reminder);

		VAlarm alarm = new VAlarm(dur);

		PropertyList alarmProps = alarm.getProperties();

		Action action = Action.DISPLAY;

		if (notificationType == NotificationType.EMAIL) {
			URI uri = URI.create("mailto:".concat(emailAddress));

			Attendee attendee = new Attendee(uri);

			action = Action.EMAIL;

			alarmProps.add(attendee);
			alarmProps.add(new Summary("Alarm notification"));
		}

		alarmProps.add(action);
		alarmProps.add(new Description("This is an event reminder"));

		return alarm;
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
		List<CalendarBooking> calendarBookings) throws Exception {

		net.fortuna.ical4j.model.Calendar iCal =
			new net.fortuna.ical4j.model.Calendar();

		ProdId prodId = new ProdId(
			"-//Liferay Inc//Liferay Portal " + ReleaseInfo.getVersion() +
			"//EN");

		PropertyList propertiesList = iCal.getProperties();

		propertiesList.add(prodId);
		propertiesList.add(Version.VERSION_2_0);
		propertiesList.add(CalScale.GREGORIAN);
		propertiesList.add(Method.PUBLISH);

		List<VEvent> components = iCal.getComponents();

		for (CalendarBooking calendarBooking : calendarBookings) {
			components.add(toICalEvent(calendarBooking));
		}

		return iCal;
	}

	protected DateTime toICalDateTime(long time) {
		DateTime dateTime = new DateTime();

		dateTime.setUtc(true);
		dateTime.setTime(time);

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

		User owner = UserLocalServiceUtil.getUser(calendarBooking.getUserId());

		Locale locale = owner.getLocale();

		VEvent vEvent = new VEvent();

		PropertyList eventProps = vEvent.getProperties();

		// UID

		Uid uid = new Uid(calendarBooking.getUuid());

		eventProps.add(uid);

		// Dates

		if (calendarBooking.isAllDay()) {
			DtStart dtStart = new DtStart(
				new Date(calendarBooking.getStartDate()));

			eventProps.add(dtStart);
		}
		else {
			DtStart dtStart = new DtStart(
				toICalDateTime(calendarBooking.getStartDate()));

			eventProps.add(dtStart);

			DtEnd dtEnd = new DtEnd(
				toICalDateTime(calendarBooking.getEndDate()));

			eventProps.add(dtEnd);
		}

		// Title

		Summary summary = new Summary(calendarBooking.getTitle(locale));

		eventProps.add(summary);

		// Description

		Description description = new Description(
			calendarBooking.getDescription(locale));

		eventProps.add(description);

		// Location

		Location location = new Location(calendarBooking.getLocation());

		eventProps.add(location);

		// Recurrence

		if (calendarBooking.isRecurring()) {
			String value = StringUtil.replace(
				calendarBooking.getRecurrence(), _rRulePart, StringPool.BLANK);

			RRule rRule = new RRule(value);

			eventProps.add(rRule);
		}

		// Reminders

		ComponentList alarms = vEvent.getAlarms();

		long firstReminder = calendarBooking.getFirstReminder();

		if (firstReminder > 0) {
			VAlarm alarm = toICalAlarm(
				calendarBooking.getFirstReminderNotificationType(),
				firstReminder, owner.getEmailAddress());

			alarms.add(alarm);
		}

		long secondReminder = calendarBooking.getSecondReminder();

		if (secondReminder > 0) {
			VAlarm alarm = toICalAlarm(
				calendarBooking.getSecondReminderNotificationType(),
				secondReminder, owner.getEmailAddress());

			alarms.add(alarm);
		}

		// Attendees

		List<CalendarBooking> childCalendarBookings =
			calendarBooking.getChildCalendarBookings();

		long userClassNameId = PortalUtil.getClassNameId(User.class);

		for (CalendarBooking childCalendarBooking : childCalendarBookings) {
			CalendarResource calResource =
				childCalendarBooking.getCalendarResource();

			long classNameId = calResource.getClassNameId();
			long classPK = calResource.getClassPK();

			if ((classNameId != userClassNameId) ||
				(calendarBooking.getCalendarBookingId() ==
					childCalendarBooking.getCalendarBookingId())) {

				continue;
			}

			User user = UserLocalServiceUtil.getUser(classPK);

			Attendee attendee = toICalAttendee(
				user.getFullName(), user.getEmailAddress(),
				childCalendarBooking.getStatus());

			eventProps.add(attendee);
		}

		return vEvent;
	}

	protected String toString(net.fortuna.ical4j.model.Calendar iCalCalendar)
		throws Exception {

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		CalendarOutputter calOutput = new CalendarOutputter();

		if (iCalCalendar.getComponents().isEmpty()) {
			calOutput.setValidating(false);
		}

		calOutput.output(iCalCalendar, unsyncStringWriter);

		unsyncStringWriter.flush();

		return unsyncStringWriter.toString();
	}

	private static final String _rRulePart = "RRULE:";

}