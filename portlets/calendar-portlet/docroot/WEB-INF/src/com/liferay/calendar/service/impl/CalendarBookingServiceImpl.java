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

package com.liferay.calendar.service.impl;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.base.CalendarBookingServiceBaseImpl;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.calendar.workflow.CalendarBookingApprovalWorkflow;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarBookingServiceImpl extends CalendarBookingServiceBaseImpl {

	public CalendarBooking addCalendarBooking(
			long calendarId, long[] childCalendarIds,
			long parentCalendarBookingId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String location, long startDate,
			long endDate, boolean allDay, String recurrence, long firstReminder,
			String firstReminderType, long secondReminder,
			String secondReminderType, ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarPermission.check(
			getPermissionChecker(), calendarId, ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.addCalendarBooking(
			getUserId(), calendarId, childCalendarIds, parentCalendarBookingId,
			titleMap, descriptionMap, location, startDate, endDate, allDay,
			recurrence, firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	public CalendarBooking deleteCalendarBooking(long calendarBookingId)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		CalendarPermission.check(
			getPermissionChecker(), calendarBooking.getCalendarId(),
			ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.deleteCalendarBooking(
			calendarBookingId);
	}

	public CalendarBooking fetchCalendarBooking(long calendarBookingId)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.fetchByPrimaryKey(calendarBookingId);

		if (calendarBooking == null) {
			return null;
		}

		return filterCalendarBooking(calendarBooking);
	}

	public CalendarBooking getCalendarBooking(long calendarBookingId)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		return filterCalendarBooking(calendarBooking);
	}

	public CalendarBooking getCalendarBooking(
			long calendarId, long parentCalendarBookingId)
		throws PortalException, SystemException {

		CalendarPermission.check(
			getPermissionChecker(), calendarId, ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.getCalendarBooking(
			calendarId, parentCalendarBookingId);
	}

	public List<CalendarBooking> getCalendarBookings(
			long calendarId, long startDate, long endDate)
		throws PortalException, SystemException {

		List<CalendarBooking> calendarBookings =
			calendarBookingLocalService.getCalendarBookings(
				calendarId, startDate, endDate);

		for (CalendarBooking calendarBooking : calendarBookings) {
			filterCalendarBooking(calendarBooking);
		}

		return calendarBookings;
	}

	public List<CalendarBooking> getChildCalendarBookings(
			long parentCalendarBookingId)
		throws PortalException, SystemException {

		List<CalendarBooking> calendarBookings =
			calendarBookingLocalService.getChildCalendarBookings(
				parentCalendarBookingId);

		for (CalendarBooking calendarBooking : calendarBookings) {
			filterCalendarBooking(calendarBooking);
		}

		return calendarBookings;
	}

	public List<CalendarBooking> getChildCalendarBookings(
			long parentCalendarBookingId, int status)
		throws PortalException, SystemException {

		List<CalendarBooking> calendarBookings =
			calendarBookingLocalService.getChildCalendarBookings(
				parentCalendarBookingId, status);

		for (CalendarBooking calendarBooking : calendarBookings) {
			filterCalendarBooking(calendarBooking);
		}

		return calendarBookings;
	}

	public void invokeTransition(
			long calendarBookingId, String transitionName,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		CalendarPermission.check(
			getPermissionChecker(), calendarBooking.getCalendarId(),
			ActionKeys.MANAGE_BOOKINGS);

		calendarBookingApprovalWorkflow.invokeTransition(
			getUserId(), calendarBookingId, transitionName, serviceContext);
	}

	public List<CalendarBooking> search(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, long startDate, long endDate, boolean recurring,
			int[] statuses, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<CalendarBooking> calendarBookings =
			calendarBookingLocalService.search(
				companyId, groupIds, calendarIds, calendarResourceIds,
				parentCalendarBookingId, keywords, startDate, endDate,
				recurring, statuses, start, end, orderByComparator);

		return filterCalendarBookings(calendarBookings, ActionKeys.VIEW);
	}

	public List<CalendarBooking> search(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, long startDate,
			long endDate, boolean recurring, int[] statuses,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<CalendarBooking> calendarBookings =
			calendarBookingLocalService.search(
				companyId, groupIds, calendarIds, calendarResourceIds,
				parentCalendarBookingId, title, description, location,
				startDate, endDate, recurring, statuses, andOperator, start,
				end, orderByComparator);

		return filterCalendarBookings(calendarBookings, ActionKeys.VIEW);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, long startDate, long endDate, boolean recurring,
			int[] statuses)
		throws PortalException, SystemException {

		List<CalendarBooking> calendarBookings = search(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, keywords, startDate, endDate, recurring,
			statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		return calendarBookings.size();
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, long startDate,
			long endDate, boolean recurring, int[] statuses,
			boolean andOperator)
		throws PortalException, SystemException {

		List<CalendarBooking> calendarBookings = search(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, title, description, location, startDate,
			endDate, recurring, statuses, andOperator, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		return calendarBookings.size();
	}

	public CalendarBooking updateCalendarBooking(
			long calendarBookingId, long calendarId, long[] childCalendarIds,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, long startDate, long endDate, boolean allDay,
			String recurrence, long firstReminder, String firstReminderType,
			long secondReminder, String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarPermission.check(
			getPermissionChecker(), calendarId, ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.updateCalendarBooking(
			getUserId(), calendarBookingId, calendarId, childCalendarIds,
			titleMap, descriptionMap, location, startDate, endDate, allDay,
			recurrence, firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
	}

	public CalendarBooking updateCalendarBooking(
			long calendarBookingId, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String location, long startDate, long endDate, boolean allDay,
			String recurrence, long firstReminder, String firstReminderType,
			long secondReminder, String secondReminderType, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarPermission.check(
			getPermissionChecker(), calendarId, ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.updateCalendarBooking(
			getUserId(), calendarBookingId, calendarId, titleMap,
			descriptionMap, location, startDate, endDate, allDay, recurrence,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
	}

	protected CalendarBooking filterCalendarBooking(
			CalendarBooking calendarBooking)
		throws PortalException, SystemException {

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
		throws PortalException, SystemException {

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