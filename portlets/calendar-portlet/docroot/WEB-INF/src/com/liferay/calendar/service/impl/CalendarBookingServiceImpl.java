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

package com.liferay.calendar.service.impl;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.base.CalendarBookingServiceBaseImpl;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.calendar.util.CalendarUtil;
import com.liferay.calendar.util.JCalendarUtil;
import com.liferay.calendar.util.RSSUtil;
import com.liferay.calendar.workflow.CalendarBookingApprovalWorkflow;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.feed.synd.SyndLink;
import com.sun.syndication.feed.synd.SyndLinkImpl;
import com.sun.syndication.io.FeedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Bruno Basto
 * @author Pier Paolo Ramon
 */
public class CalendarBookingServiceImpl extends CalendarBookingServiceBaseImpl {

	@Override
	public CalendarBooking addCalendarBooking(
			long calendarId, long[] childCalendarIds,
			long parentCalendarBookingId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location,
			int startTimeYear, int startTimeMonth, int startTimeDay,
			int startTimeHour, int startTimeMinute, int endTimeYear,
			int endTimeMonth, int endTimeDay, int endTimeHour,
			int endTimeMinute, String timeZoneId, boolean allDay,
			String recurrence, long firstReminder, String firstReminderType,
			long secondReminder, String secondReminderType,
			ServiceContext serviceContext)
		throws PortalException {

		TimeZone timeZone = TimeZoneUtil.getTimeZone(timeZoneId);

		if (allDay) {
			timeZone = TimeZone.getTimeZone(StringPool.UTC);
		}

		java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			startTimeYear, startTimeMonth, startTimeDay, startTimeHour,
			startTimeMinute, 0, 0, timeZone);
		java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(
			endTimeYear, endTimeMonth, endTimeDay, endTimeHour, endTimeMinute,
			0, 0, timeZone);

		return calendarBookingService.addCalendarBooking(
			calendarId, childCalendarIds, parentCalendarBookingId, titleMap,
			descriptionMap, location, startTimeJCalendar.getTimeInMillis(),
			endTimeJCalendar.getTimeInMillis(), allDay, recurrence,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	@Override
	public CalendarBooking addCalendarBooking(
			long calendarId, long[] childCalendarIds,
			long parentCalendarBookingId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, long startTime,
			long endTime, boolean allDay, String recurrence, long firstReminder,
			String firstReminderType, long secondReminder,
			String secondReminderType, ServiceContext serviceContext)
		throws PortalException {

		CalendarPermission.check(
			getPermissionChecker(), calendarId, ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.addCalendarBooking(
			getUserId(), calendarId, childCalendarIds, parentCalendarBookingId,
			titleMap, descriptionMap, location, startTime, endTime, allDay,
			recurrence, firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	@Override
	public CalendarBooking deleteCalendarBooking(long calendarBookingId)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		CalendarPermission.check(
			getPermissionChecker(), calendarBooking.getCalendarId(),
			ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.deleteCalendarBooking(
			calendarBookingId);
	}

	@Override
	public void deleteCalendarBookingInstance(
			long calendarBookingId, long startTime, boolean allFollowing)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		CalendarPermission.check(
			getPermissionChecker(), calendarBooking.getCalendarId(),
			ActionKeys.MANAGE_BOOKINGS);

		calendarBookingLocalService.deleteCalendarBookingInstance(
			calendarBookingId, startTime, allFollowing);
	}

	@Override
	public String exportCalendarBooking(long calendarBookingId, String type)
		throws Exception {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		CalendarPermission.check(
			getPermissionChecker(), calendarBooking.getCalendar(),
			ActionKeys.VIEW_BOOKING_DETAILS);

		return calendarBookingLocalService.exportCalendarBooking(
			calendarBookingId, type);
	}

	@Override
	public CalendarBooking fetchCalendarBooking(long calendarBookingId)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.fetchByPrimaryKey(calendarBookingId);

		if (calendarBooking == null) {
			return null;
		}

		return filterCalendarBooking(calendarBooking);
	}

	@Override
	public CalendarBooking getCalendarBooking(long calendarBookingId)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		return filterCalendarBooking(calendarBooking);
	}

	@Override
	public CalendarBooking getCalendarBooking(
			long calendarId, long parentCalendarBookingId)
		throws PortalException {

		CalendarPermission.check(
			getPermissionChecker(), calendarId, ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.getCalendarBooking(
			calendarId, parentCalendarBookingId);
	}

	@Override
	public CalendarBooking getCalendarBookingInstance(
			long calendarBookingId, int instanceIndex)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingLocalService.getCalendarBookingInstance(
				calendarBookingId, instanceIndex);

		return filterCalendarBooking(calendarBooking);
	}

	@Override
	public List<CalendarBooking> getCalendarBookings(
			long calendarId, long startTime, long endTime)
		throws PortalException {

		return getCalendarBookings(
			calendarId, startTime, endTime, QueryUtil.ALL_POS);
	}

	@Override
	public List<CalendarBooking> getCalendarBookings(
			long calendarId, long startTime, long endTime, int max)
		throws PortalException {

		List<CalendarBooking> calendarBookings =
			calendarBookingLocalService.getCalendarBookings(
				calendarId, startTime, endTime, max);

		for (CalendarBooking calendarBooking : calendarBookings) {
			filterCalendarBooking(calendarBooking);
		}

		return calendarBookings;
	}

	@Override
	public String getCalendarBookingsRSS(
			long calendarId, long startTime, long endTime, int max, String type,
			double version, String displayStyle, ThemeDisplay themeDisplay)
		throws PortalException {

		Calendar calendar = calendarService.getCalendar(calendarId);

		List<CalendarBooking> calendarBookings = getCalendarBookings(
			calendarId, startTime, endTime, max);

		return exportToRSS(
			calendar.getName(themeDisplay.getLocale()),
			calendar.getDescription(themeDisplay.getLocale()), type, version,
			displayStyle, PortalUtil.getLayoutFullURL(themeDisplay),
			calendarBookings, themeDisplay);
	}

	@Override
	public List<CalendarBooking> getChildCalendarBookings(
			long parentCalendarBookingId)
		throws PortalException {

		List<CalendarBooking> calendarBookings =
			calendarBookingLocalService.getChildCalendarBookings(
				parentCalendarBookingId);

		for (CalendarBooking calendarBooking : calendarBookings) {
			filterCalendarBooking(calendarBooking);
		}

		return calendarBookings;
	}

	@Override
	public List<CalendarBooking> getChildCalendarBookings(
			long parentCalendarBookingId, int status)
		throws PortalException {

		List<CalendarBooking> calendarBookings =
			calendarBookingLocalService.getChildCalendarBookings(
				parentCalendarBookingId, status);

		for (CalendarBooking calendarBooking : calendarBookings) {
			filterCalendarBooking(calendarBooking);
		}

		return calendarBookings;
	}

	@Override
	public CalendarBooking getNewStartTimeAndDurationCalendarBooking(
			long calendarBookingId, long offset, long duration)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		CalendarPermission.check(
			getPermissionChecker(), calendarBooking.getCalendarId(),
			ActionKeys.MANAGE_BOOKINGS);

		calendarBooking = CalendarUtil.getNewStartTimeCalendarBooking(
			calendarBooking, offset);

		calendarBooking = CalendarUtil.getNewDurationCalendarBooking(
			calendarBooking, duration);

		return calendarBooking;
	}

	@Override
	public boolean hasChildCalendarBookings(long parentCalendarBookingId) {
		int total = calendarBookingPersistence.countByParentCalendarBookingId(
			parentCalendarBookingId);

		if (total > 1) {
			return true;
		}

		return false;
	}

	@Override
	public void invokeTransition(
			long calendarBookingId, int status, ServiceContext serviceContext)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		CalendarPermission.check(
			getPermissionChecker(), calendarBooking.getCalendarId(),
			ActionKeys.MANAGE_BOOKINGS);

		calendarBookingLocalService.updateStatus(
			getUserId(), calendarBooking, status, serviceContext);
	}

	@Override
	public CalendarBooking moveCalendarBookingToTrash(long calendarBookingId)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		CalendarPermission.check(
			getPermissionChecker(), calendarBooking.getCalendarId(),
			ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.moveCalendarBookingToTrash(
			getUserId(), calendarBooking.getCalendarBookingId());
	}

	@Override
	public CalendarBooking restoreCalendarBookingFromTrash(
			long calendarBookingId)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		CalendarPermission.check(
			getPermissionChecker(), calendarBooking.getCalendarId(),
			ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.restoreCalendarBookingFromTrash(
			getUserId(), calendarBooking.getCalendarBookingId());
	}

	@AccessControlled(guestAccessEnabled = true)
	@Override
	public List<CalendarBooking> search(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, long startTime, long endTime, boolean recurring,
			int[] statuses, int start, int end,
			OrderByComparator<CalendarBooking> orderByComparator)
		throws PortalException {

		List<CalendarBooking> calendarBookings =
			calendarBookingLocalService.search(
				companyId, groupIds, calendarIds, calendarResourceIds,
				parentCalendarBookingId, keywords, startTime, endTime,
				recurring, statuses, start, end, orderByComparator);

		return filterCalendarBookings(calendarBookings, ActionKeys.VIEW);
	}

	@Override
	public List<CalendarBooking> search(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, long startTime,
			long endTime, boolean recurring, int[] statuses,
			boolean andOperator, int start, int end,
			OrderByComparator<CalendarBooking> orderByComparator)
		throws PortalException {

		List<CalendarBooking> calendarBookings =
			calendarBookingLocalService.search(
				companyId, groupIds, calendarIds, calendarResourceIds,
				parentCalendarBookingId, title, description, location,
				startTime, endTime, recurring, statuses, andOperator, start,
				end, orderByComparator);

		return filterCalendarBookings(calendarBookings, ActionKeys.VIEW);
	}

	@AccessControlled(guestAccessEnabled = true)
	@Override
	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, long startTime, long endTime, boolean recurring,
			int[] statuses)
		throws PortalException {

		List<CalendarBooking> calendarBookings = search(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, keywords, startTime, endTime, recurring,
			statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		return calendarBookings.size();
	}

	@Override
	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, long startTime,
			long endTime, boolean recurring, int[] statuses,
			boolean andOperator)
		throws PortalException {

		List<CalendarBooking> calendarBookings = search(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, title, description, location, startTime,
			endTime, recurring, statuses, andOperator, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		return calendarBookings.size();
	}

	@Override
	public CalendarBooking updateCalendarBooking(
			long calendarBookingId, long calendarId, long[] childCalendarIds,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, long startTime, long endTime, boolean allDay,
			String recurrence, long firstReminder, String firstReminderType,
			long secondReminder, String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException {

		CalendarPermission.check(
			getPermissionChecker(), calendarId, ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.updateCalendarBooking(
			getUserId(), calendarBookingId, calendarId, childCalendarIds,
			titleMap, descriptionMap, location, startTime, endTime, allDay,
			recurrence, firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
	}

	@Override
	public CalendarBooking updateCalendarBooking(
			long calendarBookingId, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, long startTime, long endTime, boolean allDay,
			String recurrence, long firstReminder, String firstReminderType,
			long secondReminder, String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException {

		CalendarPermission.check(
			getPermissionChecker(), calendarId, ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.updateCalendarBooking(
			getUserId(), calendarBookingId, calendarId, titleMap,
			descriptionMap, location, startTime, endTime, allDay, recurrence,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
	}

	@Override
	public CalendarBooking updateCalendarBookingInstance(
			long calendarBookingId, int instanceIndex, long calendarId,
			long[] childCalendarIds, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, long startTime,
			long endTime, boolean allDay, String recurrence,
			boolean allFollowing, long firstReminder, String firstReminderType,
			long secondReminder, String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException {

		CalendarPermission.check(
			getPermissionChecker(), calendarId, ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.updateCalendarBookingInstance(
			getUserId(), calendarBookingId, instanceIndex, calendarId,
			childCalendarIds, titleMap, descriptionMap, location, startTime,
			endTime, allDay, recurrence, allFollowing, firstReminder,
			firstReminderType, secondReminder, secondReminderType, status,
			serviceContext);
	}

	@Override
	public CalendarBooking updateCalendarBookingInstance(
			long calendarBookingId, int instanceIndex, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, int startTimeYear, int startTimeMonth,
			int startTimeDay, int startTimeHour, int startTimeMinute,
			int endTimeYear, int endTimeMonth, int endTimeDay, int endTimeHour,
			int endTimeMinute, String timeZoneId, boolean allDay,
			String recurrence, boolean allFollowing, long firstReminder,
			String firstReminderType, long secondReminder,
			String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException {

		TimeZone timeZone = TimeZoneUtil.getTimeZone(timeZoneId);

		if (allDay) {
			timeZone = TimeZone.getTimeZone(StringPool.UTC);
		}

		java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			startTimeYear, startTimeMonth, startTimeDay, startTimeHour,
			startTimeMinute, 0, 0, timeZone);

		java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(
			endTimeYear, endTimeMonth, endTimeDay, endTimeHour, endTimeMinute,
			0, 0, timeZone);

		return calendarBookingService.updateCalendarBookingInstance(
			calendarBookingId, instanceIndex, calendarId, titleMap,
			descriptionMap, location, startTimeJCalendar.getTimeInMillis(),
			endTimeJCalendar.getTimeInMillis(), allDay, recurrence,
			allFollowing, firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
	}

	@Override
	public CalendarBooking updateCalendarBookingInstance(
			long calendarBookingId, int instanceIndex, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, long startTime, long endTime, boolean allDay,
			String recurrence, boolean allFollowing, long firstReminder,
			String firstReminderType, long secondReminder,
			String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException {

		CalendarPermission.check(
			getPermissionChecker(), calendarId, ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.updateCalendarBookingInstance(
			getUserId(), calendarBookingId, instanceIndex, calendarId, titleMap,
			descriptionMap, location, startTime, endTime, allDay, recurrence,
			allFollowing, firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
	}

	@Override
	public CalendarBooking updateOffsetAndDuration(
			long calendarBookingId, long calendarId, long[] childCalendarIds,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, long offset, long duration, boolean allDay,
			String recurrence, long firstReminder, String firstReminderType,
			long secondReminder, String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.fetchByPrimaryKey(calendarBookingId);

		java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			calendarBooking.getStartTime() + offset);
		java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(
			startTimeJCalendar.getTimeInMillis() + duration);

		return calendarBookingService.updateCalendarBooking(
			calendarBookingId, calendarId, childCalendarIds, titleMap,
			descriptionMap, location, startTimeJCalendar.getTimeInMillis(),
			endTimeJCalendar.getTimeInMillis(), allDay, recurrence,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
	}

	@Override
	public CalendarBooking updateOffsetAndDuration(
			long calendarBookingId, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, long offset, long duration, boolean allDay,
			String recurrence, long firstReminder, String firstReminderType,
			long secondReminder, String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException {

		long[] childCalendarIds =
			calendarBookingLocalService.getChildCalendarIds(
				calendarBookingId, calendarId);

		return updateOffsetAndDuration(
			calendarBookingId, calendarId, childCalendarIds, titleMap,
			descriptionMap, location, offset, duration, allDay, recurrence,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
	}

	protected String exportToRSS(
		String name, String description, String type, double version,
		String displayStyle, String feedURL,
		List<CalendarBooking> calendarBookings, ThemeDisplay themeDisplay) {

		SyndFeed syndFeed = new SyndFeedImpl();

		syndFeed.setDescription(description);

		List<SyndEntry> syndEntries = new ArrayList<>();

		syndFeed.setEntries(syndEntries);

		Locale locale = themeDisplay.getLocale();

		for (CalendarBooking calendarBooking : calendarBookings) {
			SyndEntry syndEntry = new SyndEntryImpl();

			String author = PortalUtil.getUserName(calendarBooking);

			syndEntry.setAuthor(author);

			SyndContent syndContent = new SyndContentImpl();

			syndContent.setType(RSSUtil.ENTRY_TYPE_DEFAULT);

			String value = RSSUtil.getContent(
				calendarBooking, displayStyle, themeDisplay);

			syndContent.setValue(value);

			syndEntry.setDescription(syndContent);

			String link = StringPool.BLANK;

			syndEntry.setLink(link);

			syndEntry.setPublishedDate(calendarBooking.getCreateDate());
			syndEntry.setTitle(calendarBooking.getTitle(locale));
			syndEntry.setUpdatedDate(calendarBooking.getModifiedDate());
			syndEntry.setUri(link);

			syndEntries.add(syndEntry);
		}

		syndFeed.setFeedType(RSSUtil.getFeedType(type, version));

		List<SyndLink> syndLinks = new ArrayList<>();

		syndFeed.setLinks(syndLinks);

		SyndLink syndLink = new SyndLinkImpl();

		syndLinks.add(syndLink);

		syndLink.setHref(feedURL);
		syndLink.setRel("self");

		syndFeed.setPublishedDate(new Date());
		syndFeed.setTitle(name);
		syndFeed.setUri(feedURL);

		try {
			return RSSUtil.export(syndFeed);
		}
		catch (FeedException fe) {
			throw new SystemException(fe);
		}
	}

	protected CalendarBooking filterCalendarBooking(
			CalendarBooking calendarBooking)
		throws PortalException {

		if (!CalendarPermission.contains(
				getPermissionChecker(), calendarBooking.getCalendarId(),
				ActionKeys.VIEW_BOOKING_DETAILS)) {

			calendarBooking.setTitle(StringPool.BLANK);
			calendarBooking.setDescription(StringPool.BLANK);
			calendarBooking.setLocation(StringPool.BLANK);
		}

		return calendarBooking;
	}

	protected List<CalendarBooking> filterCalendarBookings(
			List<CalendarBooking> calendarBookings, String actionId)
		throws PortalException {

		calendarBookings = ListUtil.copy(calendarBookings);

		Iterator<CalendarBooking> itr = calendarBookings.iterator();

		while (itr.hasNext()) {
			CalendarBooking calendarBooking = itr.next();

			if (!CalendarPermission.contains(
					getPermissionChecker(), calendarBooking.getCalendarId(),
					ActionKeys.VIEW_BOOKING_DETAILS)) {

				if (!CalendarPermission.contains(
						getPermissionChecker(), calendarBooking.getCalendarId(),
						actionId)) {

					itr.remove();
				}
				else {
					filterCalendarBooking(calendarBooking);
				}
			}
		}

		return calendarBookings;
	}

	@BeanReference(type = CalendarBookingApprovalWorkflow.class)
	protected CalendarBookingApprovalWorkflow calendarBookingApprovalWorkflow;

}