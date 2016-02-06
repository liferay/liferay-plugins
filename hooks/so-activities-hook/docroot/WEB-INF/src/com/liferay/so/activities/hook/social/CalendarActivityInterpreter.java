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

package com.liferay.so.activities.hook.social;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.so.activities.util.SocialActivityKeyConstants;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.model.SocialActivitySet;
import com.liferay.social.kernel.service.SocialActivityLocalServiceUtil;
import com.liferay.social.kernel.service.SocialActivitySetLocalServiceUtil;

import java.text.DateFormat;
import java.text.Format;

import java.util.TimeZone;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Evan Thibodeau
 * @author Matthew Kong
 */
public class CalendarActivityInterpreter extends SOSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected long getActivitySetId(long activityId) {
		try {
			SocialActivitySet activitySet = null;

			SocialActivity activity =
				SocialActivityLocalServiceUtil.getActivity(activityId);

			if (activity.getType() ==
					SocialActivityKeyConstants.
						CALENDAR_UPDATE_CALENDAR_BOOKING) {

				activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getUserId(), activity.getClassNameId(),
						activity.getClassPK(), activity.getType());
			}

			if ((activitySet != null) && !isExpired(activitySet, false)) {
				return activitySet.getActivitySetId();
			}
		}
		catch (Exception e) {
		}

		return 0;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		return getBody(
			activity.getClassName(), activity.getClassPK(), serviceContext);
	}

	@Override
	protected String getBody(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		if (activitySet.getType() ==
				SocialActivityKeyConstants.CALENDAR_UPDATE_CALENDAR_BOOKING) {

			return getBody(
				activitySet.getClassName(), activitySet.getClassPK(),
				serviceContext);
		}

		return super.getBody(activitySet, serviceContext);
	}

	protected String getBody(
			String className, long classPK, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(15);

		sb.append("<div class=\"activity-body\"><div class=\"title\">");
		sb.append(getPageTitle(className, classPK, serviceContext));
		sb.append("</div><div class=\"date\"><strong>");
		sb.append(serviceContext.translate("date"));
		sb.append(": </strong>");

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.fetchCalendarBooking(classPK);

		Format dateFormatDate = null;

		if (calendarBooking.isAllDay()) {
			dateFormatDate = FastDateFormatFactoryUtil.getDateTime(
				DateFormat.FULL, DateFormat.SHORT, serviceContext.getLocale(),
				TimeZone.getTimeZone(StringPool.UTC));
		}
		else {
			dateFormatDate = FastDateFormatFactoryUtil.getDateTime(
				DateFormat.FULL, DateFormat.SHORT, serviceContext.getLocale(),
				serviceContext.getTimeZone());
		}

		sb.append(dateFormatDate.format(calendarBooking.getStartTime()));

		sb.append("</div><div class=\"location\"><strong>");
		sb.append(serviceContext.translate("location"));
		sb.append(": </strong>");
		sb.append(calendarBooking.getLocation());
		sb.append("</div><div class=\"description\"><strong>");
		sb.append(serviceContext.translate("description"));
		sb.append(": </strong>");

		AssetRenderer assetRenderer = getAssetRenderer(className, classPK);

		sb.append(
			StringUtil.shorten(
				HtmlUtil.escape(assetRenderer.getSummary(), 200)));

		sb.append("</div></div>");

		return sb.toString();
	}

	@Override
	protected String getLinkURL(
			String className, long classPK, ServiceContext serviceContext)
		throws Exception {

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.fetchCalendarBooking(classPK);

		long plid = PortalUtil.getPlidFromPortletId(
			calendarBooking.getGroupId(), CalendarPortletKeys.CALENDAR);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			serviceContext.getRequest(), CalendarPortletKeys.CALENDAR, plid,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/view_calendar_booking.jsp");
		portletURL.setParameter(
			"calendarBookingId",
			String.valueOf(calendarBooking.getCalendarBookingId()));

		return portletURL.toString();
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		if (activity.getType() ==
				SocialActivityKeyConstants.CALENDAR_ADD_CALENDAR_BOOKING) {

			return "added-a-new-calendar-event";
		}
		else if (activity.getType() ==
					SocialActivityKeyConstants.
						CALENDAR_UPDATE_CALENDAR_BOOKING) {

			return "updated-a-calendar-event";
		}

		return StringPool.BLANK;
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivitySet activitySet) {

		if (activitySet.getType() ==
				SocialActivityKeyConstants.CALENDAR_ADD_CALENDAR_BOOKING) {

			return "added-x-new-calendar-events";
		}
		else if (activitySet.getType() ==
					SocialActivityKeyConstants.
						CALENDAR_UPDATE_CALENDAR_BOOKING) {

			return "made-x-updates-to-a-calendar-event";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.fetchCalendarBooking(
				activity.getClassPK());

		if (calendarBooking == null) {
			return false;
		}

		if (actionId.equals(ActionKeys.VIEW) &&
			!CalendarPermission.contains(
				permissionChecker, calendarBooking.getCalendarId(),
				"VIEW_BOOKING_DETAILS")) {

			return false;
		}

		return CalendarPermission.contains(
			permissionChecker, calendarBooking.getCalendarId(), actionId);
	}

	private static final String[] _CLASS_NAMES =
		{CalendarBooking.class.getName()};

}