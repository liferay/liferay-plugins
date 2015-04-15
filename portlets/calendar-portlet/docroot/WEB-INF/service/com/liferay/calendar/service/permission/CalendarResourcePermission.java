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

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.util.PortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Eduardo Lundgren
 * @author Michael C. Han
 */
public class CalendarResourcePermission {

	public static void check(
			PermissionChecker permissionChecker,
			CalendarResource calendarResource, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, calendarResource, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long calendarResourceId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, calendarResourceId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, CalendarResource calendarResource,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, calendarResource.getGroupId(),
			CalendarResource.class.getName(),
			calendarResource.getCalendarResourceId(), PortletKeys.CALENDAR,
			actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (permissionChecker.hasOwnerPermission(
				calendarResource.getCompanyId(),
				CalendarResource.class.getName(),
				calendarResource.getCalendarResourceId(),
				calendarResource.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			calendarResource.getGroupId(), CalendarResource.class.getName(),
			calendarResource.getCalendarResourceId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long calendarResourceId,
			String actionId)
		throws PortalException {

		CalendarResource calendarResource =
			CalendarResourceLocalServiceUtil.getCalendarResource(
				calendarResourceId);

		return contains(permissionChecker, calendarResource, actionId);
	}

}