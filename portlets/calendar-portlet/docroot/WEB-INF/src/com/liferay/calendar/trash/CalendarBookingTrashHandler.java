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

		CalendarBooking calendarBooking = getCalendarBooking(classPK);

		return calendarBooking.isInTrash();
	}

	@Override
	public void restoreTrashEntry(long userId, long classPK)
		throws PortalException, SystemException {

		CalendarBookingLocalServiceUtil.restoreCalendarBookingFromTrash(
			userId, classPK);
	}

	@Override
	public boolean isRestorable(long classPK)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking = getCalendarBooking(classPK);

		if (calendarBooking.isMasterBooking()) {
			return true;
		}
		else {
			return false;
		}
	}

	protected CalendarBooking getCalendarBooking(long classPK)
		throws PortalException, SystemException {

		return CalendarBookingLocalServiceUtil.getCalendarBooking(classPK);
	}

	@Override
	protected boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking = getCalendarBooking(classPK);

		return CalendarPermission.contains(
			permissionChecker, calendarBooking.getCalendar(),
			ActionKeys.MANAGE_BOOKINGS);
	}

}
