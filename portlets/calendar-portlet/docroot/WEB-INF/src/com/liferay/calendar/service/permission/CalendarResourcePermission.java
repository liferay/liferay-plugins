/*
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Michael C. Han
 */
public class CalendarResourcePermission {

	public static void check(
			PermissionChecker permissionChecker, CalendarResource resource,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, resource, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long resourceId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, resourceId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, CalendarResource resource,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				resource.getCompanyId(), CalendarResource.class.getName(),
				resource.getCalendarResourceId(), resource.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			resource.getGroupId(), CalendarResource.class.getName(),
			resource.getCalendarResourceId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long resourceId,
			String actionId)
		throws PortalException, SystemException {

		CalendarResource resource =
			CalendarResourceLocalServiceUtil.getCalendarResource(resourceId);

		return contains(permissionChecker, resource, actionId);
	}
}
