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

package com.liferay.calendar.asset;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.calendar.util.PortletKeys;
import com.liferay.calendar.util.WebKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

/**
 * @author Fabio Pezzutto
 * @author Eduardo Lundgren
 * @author Pier Paolo Ramon
 */
public class CalendarBookingAssetRenderer
	extends BaseAssetRenderer implements TrashRenderer {

	public CalendarBookingAssetRenderer(CalendarBooking calendarBooking) {
		_calendarBooking = calendarBooking;
	}

	@Override
	public String getClassName() {
		return CalendarBooking.class.getName();
	}

	@Override
	public long getClassPK() {
		return _calendarBooking.getCalendarBookingId();
	}

	@Override
	public long getGroupId() {
		return _calendarBooking.getGroupId();
	}

	@Override
	public String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/date.png";
	}

	@Override
	public String getPortletId() {
		AssetRendererFactory assetRendererFactory = getAssetRendererFactory();

		return assetRendererFactory.getPortletId();
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Locale locale = getLocale(portletRequest);

		String summary = _calendarBooking.getDescription(locale);

		return StringUtil.shorten(HtmlUtil.stripHtml(summary), 200);
	}

	@Override
	public String getTitle(Locale locale) {
		return _calendarBooking.getTitle(locale);
	}

	@Override
	public String getType() {
		return CalendarBookingAssetRendererFactory.TYPE;
	}

	@Override
	public PortletURL getURLEdit(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			getControlPanelPlid(liferayPortletRequest), PortletKeys.CALENDAR,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/edit_calendar_booking.jsp");
		portletURL.setParameter(
			"calendarBookingId",
			String.valueOf(_calendarBooking.getCalendarBookingId()));

		return portletURL;
	}

	@Override
	public String getURLViewInContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		try {
			PortletURL portletURL = liferayPortletResponse.createRenderURL(
				PortletKeys.CALENDAR);

			portletURL.setParameter("mvcPath", "/view_calendar_booking.jsp");
			portletURL.setParameter(
				"calendarBookingId",
				String.valueOf(_calendarBooking.getCalendarBookingId()));
			portletURL.setWindowState(WindowState.MAXIMIZED);

			return portletURL.toString();
		}
		catch (Exception e) {
		}

		return null;
	}

	@Override
	public long getUserId() {
		return _calendarBooking.getUserId();
	}

	@Override
	public String getUserName() {
		return _calendarBooking.getUserName();
	}

	@Override
	public String getUuid() {
		return _calendarBooking.getUuid();
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {
		Calendar calendar = null;

		try {
			calendar = _calendarBooking.getCalendar();
		}
		catch (Exception e) {
			_log.error(e);
		}

		return CalendarPermission.contains(
			permissionChecker, calendar, ActionKeys.MANAGE_BOOKINGS);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		Calendar calendar = null;

		try {
			calendar = _calendarBooking.getCalendar();
		}
		catch (Exception e) {
			_log.error(e);
		}

		return CalendarPermission.contains(
			permissionChecker, calendar, ActionKeys.VIEW);
	}

	@Override
	public boolean isPrintable() {
		return true;
	}

	@Override
	public String render(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String template)
		throws Exception {

		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			portletRequest.setAttribute(
				WebKeys.CALENDAR_BOOKING, _calendarBooking);

			return "/asset/" + template + ".jsp";
		}
		else {
			return null;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CalendarBookingAssetRenderer.class);

	private CalendarBooking _calendarBooking;

}