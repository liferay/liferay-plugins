package com.liferay.calendar.asset;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.util.ActionKeys;
import com.liferay.calendar.util.PortletKeys;
import com.liferay.calendar.util.WebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;


public class CalendarBookingAssetRendererFactory
	extends BaseAssetRendererFactory {

	public static final String CLASS_NAME = CalendarBooking.class.getName();

	public static final String TYPE = "calendar";

	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.getCalendarBooking(classPK);

		return new CalendarBookingAssetRenderer(calendarBooking);
	}

	public String getClassName() {
		return CLASS_NAME;
	}

	public String getType() {
		return TYPE;
	}

	@Override
	public PortletURL getURLAdd(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletURL addAssetURL = null;

		long plid = LayoutConstants.DEFAULT_PLID;

		try {
			plid = PortalUtil.getPlidFromPortletId(
				themeDisplay.getScopeGroupId(), PortletKeys.CALENDAR);
		}
		catch(Exception e) {
		}

		if (plid != LayoutConstants.DEFAULT_PLID) {
			addAssetURL = liferayPortletResponse.createLiferayPortletURL(
				plid, PortletKeys.CALENDAR, PortletRequest.RENDER_PHASE);
		}
		else {
			addAssetURL = liferayPortletResponse.createRenderURL(
				PortletKeys.CALENDAR);
		}

		addAssetURL.setParameter("mvcPath", "/edit_calendar_booking.jsp");

		return addAssetURL;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.getCalendarBooking(classPK);

		if (actionId.equals(ActionKeys.VIEW)) {
			actionId = ActionKeys.VIEW_BOOKING_DETAILS;
		}
		else if ( (actionId.equals(ActionKeys.UPDATE)) ||
				(actionId.equals(ActionKeys.DELETE)) ) {

			actionId = ActionKeys.MANAGE_BOOKINGS;
		}

		return CalendarPermission.contains(
			permissionChecker, calendarBooking.getCalendarId(), actionId);
	}

	@Override
	public boolean isLinkable() {
		return _LINKABLE;
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/calendar.png";
	}

	private static final boolean _LINKABLE = true;

}