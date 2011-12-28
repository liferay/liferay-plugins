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
import com.liferay.calendar.service.permission.CalendarResourcePermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * @author Eduardo Lundgren
 */
public class CalendarBookingServiceImpl extends CalendarBookingServiceBaseImpl {

	public CalendarBooking addCalendarBooking(
			long calendarEventId, long calendarResourceId, boolean required,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId, ActionKeys.ADD_BOOKING);

		return calendarBookingLocalService.addCalendarBooking(
			getUserId(), calendarEventId, calendarResourceId, required,
			serviceContext);
	}

	public void deleteCalendarBooking(
			long calendarResourceId, long calendarBookingId)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId,
			ActionKeys.UPDATE_BOOKINGS);

		calendarBookingLocalService.deleteCalendarBooking(calendarBookingId);
	}

	public CalendarBooking getCalendarBooking(
			long calendarResourceId, long calendarBookingId)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId,
			ActionKeys.VIEW_BOOKINGS);

		return calendarBookingLocalService.getCalendarBooking(
			calendarBookingId);
	}

	public List<CalendarBooking> getCalendarBookings(
			String className, long classPK, long calendarResourceId, int start,
			int end, OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId,
			ActionKeys.VIEW_BOOKINGS);

		return calendarBookingLocalService.getCalendarBookings(
			className, classPK, start, end, orderByComparator);
	}

	public int getCalendarBookingsCount(
			String className, long classPK, long calendarResourceId)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId,
			ActionKeys.VIEW_BOOKINGS);

		return calendarBookingLocalService.getCalendarBookingsCount(
			className, classPK);
	}

	public List<CalendarBooking> getCalendarEventCalendarBookings(
			long calendarEventId, long calendarResourceId, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId,
			ActionKeys.VIEW_BOOKINGS);

		return calendarBookingLocalService.getCalendarEventCalendarBookings(
			calendarEventId, start, end, orderByComparator);
	}

	public int getCalendarEventCalendarBookingsCount(
			long calendarEventId, long calendarResourceId)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId,
			ActionKeys.VIEW_BOOKINGS);

		return calendarBookingLocalService.
			getCalendarEventCalendarBookingsCount(calendarEventId);
	}

	public List<CalendarBooking> getCalendarResourceCalendarBookings(
			long calendarResourceId, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId,
			ActionKeys.VIEW_BOOKINGS);

		return calendarBookingLocalService.getCalendarResourceCalendarBookings(
			calendarResourceId, start, end, orderByComparator);
	}

	public int getCalendarResourceCalendarBookingsCount(long calendarResourceId)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId,
			ActionKeys.VIEW_BOOKINGS);

		return calendarBookingLocalService.
			getCalendarResourceCalendarBookingsCount(calendarResourceId);
	}

	public List<CalendarBooking> search(
			long calendarResourceId, String title, String description,
			String type, boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId,
			ActionKeys.VIEW_BOOKINGS);

		return calendarBookingLocalService.search(
			calendarResourceId, title, description, type, andOperator, start,
			end, orderByComparator);
	}

	public int searchCount(
			long calendarResourceId, String title, String description,
			String type, boolean andOperator)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId,
			ActionKeys.VIEW_BOOKINGS);

		return calendarBookingLocalService.searchCount(
			calendarResourceId, title, description, type, andOperator);
	}

	public CalendarBooking updateCalendarBooking(
			long calendarResourceId, long calendarBookingId, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarResourcePermission.check(
			getPermissionChecker(), calendarResourceId,
			ActionKeys.UPDATE_BOOKINGS);

		return calendarBookingLocalService.updateCalendarBooking(
			getUserId(), calendarBookingId, status, serviceContext);
	}

}