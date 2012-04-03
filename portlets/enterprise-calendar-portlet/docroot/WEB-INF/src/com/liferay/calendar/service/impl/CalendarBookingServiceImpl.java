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
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.base.CalendarBookingServiceBaseImpl;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarBookingServiceImpl extends CalendarBookingServiceBaseImpl {

	public CalendarBooking addCalendarBooking(
			long calendarId, long parentCalendarBookingId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> locationMap, String type, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute, boolean allDay,
			String recurrence, Integer priority, boolean outOfOffice,
			int firstReminder, int secondReminder, boolean required,
			String requestMessage, String responseMessage,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarPermission.check(
			getPermissionChecker(), calendarId, ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.addCalendarBooking(
			getUserId(), calendarId, parentCalendarBookingId, titleMap,
			descriptionMap, locationMap, type, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, allDay,
			recurrence, priority, outOfOffice, firstReminder, secondReminder,
			required, requestMessage, responseMessage, serviceContext);
	}

	public CalendarBooking deleteCalendarBooking(long calendarBookingId)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			calendarBookingLocalService.getCalendarBooking(calendarBookingId);

		CalendarPermission.check(
			getPermissionChecker(), calendarBooking.getCalendarId(),
			ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.deleteCalendarBooking(
			calendarBookingId);
	}

	public CalendarBooking getCalendarBooking(long calendarBookingId)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			calendarBookingLocalService.getCalendarBooking(calendarBookingId);

		return filterCalendarBooking(calendarBooking);
	}

	public List<CalendarBooking> getCalendarBookings(
			long calendarId, Date startDate, Date endDate)
		throws PortalException, SystemException {

		List<CalendarBooking> calendarBookings =
			CalendarBookingLocalServiceUtil.getCalendarBookings(
				calendarId, startDate, endDate);

		for (CalendarBooking calendarBooking : calendarBookings) {
			filterCalendarBooking(calendarBooking);
		}

		return calendarBookings;
	}

	public List<CalendarBooking> search(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, String type,
			Date startDate, Date endDate, Integer priority, int status,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarBookingFinder.filterFindByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, title, description, location, type,
			startDate, endDate, priority, status, andOperator, start, end,
			orderByComparator);
	}

	public List<CalendarBooking> searchByKeywords(
			long companyId, long[] groupIds, long[] calendarIds,
			String keywords, long[] calendarResourceIds,
			long parentCalendarBookingId, Date startDate, Date endDate,
			Integer priority, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarBookingFinder.filterFindByKeywords(
			companyId, groupIds, calendarIds, keywords, calendarResourceIds,
			parentCalendarBookingId, startDate, endDate, priority, status,
			start, end, orderByComparator);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, Date startDate, Date endDate, Integer priority,
			int status)
		throws SystemException {

		return calendarBookingFinder.filterCountByKeywords(
			companyId, groupIds, calendarIds, keywords, calendarResourceIds,
			parentCalendarBookingId, startDate, endDate, priority, status);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, String type,
			Date startDate, Date endDate, Integer priority, int status,
			boolean andOperator)
		throws SystemException {

		return calendarBookingFinder.filterCountByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, title, description, location, type,
			startDate, endDate, priority, status, andOperator);
	}

	public CalendarBooking updateCalendarBooking(
			long calendarBookingId, long calendarId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> locationMap, String type, int status,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			boolean allDay, String recurrence, Integer priority,
			boolean outOfOffice, int firstReminder, int secondReminder,
			boolean required, String requestMessage, String responseMessage,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarPermission.check(
			getPermissionChecker(), calendarId, ActionKeys.MANAGE_BOOKINGS);

		return calendarBookingLocalService.updateCalendarBooking(
			getUserId(), calendarBookingId, calendarId, titleMap,
			descriptionMap, locationMap, type, status, startDateMonth,
			startDateDay, startDateYear, startDateHour, startDateMinute,
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			allDay, recurrence, priority, outOfOffice, firstReminder,
			secondReminder, required, requestMessage, responseMessage,
			serviceContext);
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

}