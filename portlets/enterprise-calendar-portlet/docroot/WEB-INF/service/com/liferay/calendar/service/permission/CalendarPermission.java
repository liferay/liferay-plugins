package com.liferay.calendar.service.permission;

import com.liferay.calendar.model.Calendar;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
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
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, Calendar calendar,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				calendar.getCompanyId(),
				Calendar.class.getName(),
				calendar.getCalendarResourceId(),
				calendar.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			calendar.getGroupId(), Calendar.class.getName(),
			calendar.getCalendarId(), actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {

		return permissionChecker.hasPermission(
			groupId, Calendar.class.getName(), groupId, actionId);
	}

}