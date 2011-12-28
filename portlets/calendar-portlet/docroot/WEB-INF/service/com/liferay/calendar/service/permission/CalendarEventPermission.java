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

package com.liferay.calendar.service.permission;

import com.liferay.calendar.model.CalendarEvent;
import com.liferay.calendar.service.CalendarEventLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Eduardo Lundgren
 * @author Michael C. Han
 */
public class CalendarEventPermission {

	public static void check(
			PermissionChecker permissionChecker, CalendarEvent calendarEvent,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, calendarEvent, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long calendarEventId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, calendarEventId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, CalendarEvent calendarEvent,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				calendarEvent.getCompanyId(), CalendarEvent.class.getName(),
				calendarEvent.getCalendarEventId(), calendarEvent.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			calendarEvent.getGroupId(), CalendarEvent.class.getName(),
			calendarEvent.getCalendarEventId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long calendarEventId,
			String actionId)
		throws PortalException, SystemException {

		CalendarEvent calendarEvent =
			CalendarEventLocalServiceUtil.getCalendarEvent(calendarEventId);

		return contains(permissionChecker, calendarEvent, actionId);
	}

}