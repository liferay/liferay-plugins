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

package com.liferay.calendar.trash;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.trash.BaseTrashHandler;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Pier Paolo Ramon
 */
public class CalendarBookingTrashHandler extends BaseTrashHandler {

	@Override
	public void deleteTrashEntry(long classPK)
		throws PortalException, SystemException {

		CalendarBookingLocalServiceUtil.deleteCalendarBooking(classPK);
	}

	@Override
	public String getClassName() {
		return CalendarBooking.class.getName();
	}

	@Override
	public boolean isInTrash(long classPK)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.getCalendarBooking(classPK);

		return calendarBooking.isInTrash();
	}

	@Override
	public boolean isRestorable(long classPK)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.getCalendarBooking(classPK);

		if (calendarBooking.isMasterBooking()) {
			return true;
		}

		return false;
	}

	@Override
	public void restoreTrashEntry(long userId, long classPK)
		throws PortalException, SystemException {

		CalendarBookingLocalServiceUtil.restoreCalendarBookingFromTrash(
			userId, classPK);
	}

	@Override
	protected boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.getCalendarBooking(classPK);

		return CalendarPermission.contains(
			permissionChecker, calendarBooking.getCalendar(),
			ActionKeys.MANAGE_BOOKINGS);
	}

}