package com.liferay.calendar;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.model.BaseCustomAttributesDisplay;


public class CalendarCustomAttributesDisplay
	extends BaseCustomAttributesDisplay {

	public static final String CLASS_NAME = CalendarBooking.class.getName();

	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/calendar.png";
	}

}