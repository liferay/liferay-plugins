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

package com.liferay.calendar.social;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.PortletKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityConstants;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Marcellus Tavares
 */
public class CalendarActivityInterpreter extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getEntryTitle(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.getCalendarBooking(
				activity.getClassPK());

		return calendarBooking.getTitle(serviceContext.getLocale());
	}

	@Override
	protected String getPath(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		long plid = PortalUtil.getPlidFromPortletId(
			serviceContext.getScopeGroupId(), PortletKeys.CALENDAR);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			serviceContext.getRequest(), PortletKeys.CALENDAR, plid,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/view_calendar_booking.jsp");
		portletURL.setParameter("backURL", serviceContext.getCurrentURL());
		portletURL.setParameter(
			"calendarBookingId", String.valueOf(activity.getClassPK()));

		return portletURL.toString();
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		int activityType = activity.getType();

		if (activityType == CalendarActivityKeys.ADD_CALENDAR_BOOKING) {
			if (Validator.isNull(groupName)) {
				return "activity-calendar-booking-add-booking";
			}
			else {
				return "activity-calendar-booking-add-booking-in";
			}
		}
		else if (activityType == SocialActivityConstants.TYPE_MOVE_TO_TRASH) {
			if (Validator.isNull(groupName)) {
				return "activity-calendar-booking-move-to-trash";
			}
			else {
				return "activity-calendar-booking-move-to-trash-in";
			}
		}
		else if (activityType ==
					SocialActivityConstants.TYPE_RESTORE_FROM_TRASH) {

			if (Validator.isNull(groupName)) {
				return "activity-calendar-booking-restore-from-trash";
			}
			else {
				return "activity-calendar-booking-restore-from-trash-in";
			}
		}
		else if (activityType == CalendarActivityKeys.UPDATE_CALENDAR_BOOKING) {
			if (Validator.isNull(groupName)) {
				return "activity-calendar-booking-update-booking";
			}
			else {
				return "activity-calendar-booking-update-booking-in";
			}
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.getCalendarBooking(
				activity.getClassPK());

		return CalendarPermission.contains(
			permissionChecker, calendarBooking.getCalendarId(), actionId);
	}

	private static final String[] _CLASS_NAMES =
		{CalendarBooking.class.getName()};

}