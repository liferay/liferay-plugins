package com.liferay.calendar.service.permission;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Fabio Pezzutto
 */
public class CalendarBookingPermission {

	public static void check(
			PermissionChecker permissionChecker,
			CalendarBooking calendarBooking, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, calendarBooking, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long calendarBookingId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, calendarBookingId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, CalendarBooking calendarBooking,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				calendarBooking.getCompanyId(),
				CalendarBooking.class.getName(),
				calendarBooking.getCalendarBookingId(),
				calendarBooking.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			calendarBooking.getGroupId(), CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long calendarBookingId,
			String actionId)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.getCalendarBooking(
				calendarBookingId);

		return contains(permissionChecker, calendarBooking, actionId);
	}

}