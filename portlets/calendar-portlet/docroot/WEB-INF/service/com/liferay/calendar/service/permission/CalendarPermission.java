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

package com.liferay.calendar.service.permission;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarPermission {

	public static void check(
			PermissionChecker permissionChecker, Calendar calendar,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, calendar, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long calendarId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, calendarId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, Calendar calendar,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				calendar.getCompanyId(), Calendar.class.getName(),
				calendar.getCalendarId(), calendar.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			calendar.getGroupId(), Calendar.class.getName(),
			calendar.getCalendarId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long calendarId,
			String actionId)
		throws PortalException, SystemException {

		Calendar calendar = CalendarLocalServiceUtil.getCalendar(calendarId);

		return contains(permissionChecker, calendar, actionId);
	}

}