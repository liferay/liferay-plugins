/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.testmisc.localization;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Eduardo Garcia
 */
public class TestLocalizationUtil {

	public static String getLocalizedPortletDescription(
		HttpServletRequest request, ServletContext servletContext) {

		Locale locale = new Locale("en", "US");

		Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);

		return PortalUtil.getPortletDescription(
			portlet, servletContext, locale);
	}

	public static String getLocalizedPortletTitle(
		HttpServletRequest request, ServletContext servletContext) {

		Locale locale = new Locale("en", "US");
		
		Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);

		return PortalUtil.getPortletTitle(portlet, servletContext, locale);
	}
	
	public static String getPortletDisplayTitle(HttpServletRequest request) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return portletDisplay.getTitle();
	}
	
}
