package com.liferay.calendar.asset;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.calendar.util.PortletKeys;
import com.liferay.calendar.util.WebKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;


public class CalendarBookingAssetRenderer extends BaseAssetRenderer {

	public CalendarBookingAssetRenderer(CalendarBooking calendarBooking) {
		_calendarBooking = calendarBooking;
	}

	public String getAssetRendererFactoryClassName() {
		return CalendarBookingAssetRendererFactory.CLASS_NAME;
	}

	public long getClassPK() {
		return _calendarBooking.getCalendarBookingId();
	}

	public long getGroupId() {
		return _calendarBooking.getGroupId();
	}

	public String getSummary(Locale locale) {
		return _calendarBooking.getDescription(locale);
	}

	public String getTitle(Locale locale) {
		return _calendarBooking.getTitle(locale);
	}

	@Override
	public PortletURL getURLEdit(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		PortletURL editPortletURL = liferayPortletResponse.createRenderURL(
			PortletKeys.CALENDAR);

		editPortletURL.setParameter("mvcPath", "/edit_calendar_booking.jsp");
		editPortletURL.setParameter(
			"calendarBookingId", String.valueOf(
				_calendarBooking.getCalendarBookingId()));

		return editPortletURL;
	}

	@Override
	public String getURLViewInContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		PortletURL viewPortletURL;

		long plid = LayoutConstants.DEFAULT_PLID;

		try {
			plid = PortalUtil.getPlidFromPortletId(
				_calendarBooking.getGroupId(), PortletKeys.CALENDAR);
		}
		catch(Exception e) {
		}

		if (plid != LayoutConstants.DEFAULT_PLID) {
			viewPortletURL = liferayPortletResponse.createLiferayPortletURL(
				plid, PortletKeys.CALENDAR, PortletRequest.RENDER_PHASE);
		}
		else {
			viewPortletURL = liferayPortletResponse.createRenderURL(
				PortletKeys.CALENDAR);
		}

		String redirect = PortalUtil.getCurrentCompleteURL(
			liferayPortletRequest.getHttpServletRequest());

		viewPortletURL.setParameter("mvcPath", "/view_calendar_booking.jsp");
		viewPortletURL.setParameter(
			"calendarBookingId",
			String.valueOf(_calendarBooking.getCalendarBookingId()));
		viewPortletURL.setParameter("redirect", redirect);

		return viewPortletURL.toString();
	}

	public long getUserId() {
		return _calendarBooking.getUserId();
	}

	public String getUserName() {
		return _calendarBooking.getUserName();
	}

	public String getUuid() {
		return _calendarBooking.getUuid();
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {

		Calendar calendar = null;

		try {
			calendar = CalendarLocalServiceUtil.getCalendar(
				_calendarBooking.getCalendarId());
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
			calendar = CalendarLocalServiceUtil.getCalendar(
				_calendarBooking.getCalendarId());
		}
		catch (Exception e) {
			_log.error(e);
		}

		return CalendarPermission.contains(
			permissionChecker, calendar, ActionKeys.VIEW_BOOKING_DETAILS);
	}

	@Override
	public boolean isPrintable() {
		return true;
	}

	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse,
			String template)
		throws Exception {

		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			renderRequest.setAttribute(
				WebKeys.CALENDAR_BOOKING, _calendarBooking);

			return "/asset/" + template + ".jsp";
		}
		else {
			return null;
		}
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/calendar.png";
	}

	private CalendarBooking _calendarBooking;

	private static Log _log = LogFactoryUtil.getLog(
		CalendarBookingAssetRenderer.class);

}