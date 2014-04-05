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

package com.liferay.compat.portal.service;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class ServiceContext {

	public ServiceContext(
		com.liferay.portal.service.ServiceContext serviceContext) {

		_serviceContext = serviceContext;
	}

	public ServiceContext(ThemeDisplay themeDisplay) {
		_themeDisplay = themeDisplay;
	}

	public LiferayPortletRequest getLiferayPortletRequest() {
		if (_serviceContext == null) {
			return null;
		}

		HttpServletRequest request = _serviceContext.getRequest();

		if (request == null) {
			return null;
		}

		return (LiferayPortletRequest)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);
	}

	public LiferayPortletResponse getLiferayPortletResponse() {
		if (_serviceContext == null) {
			return null;
		}

		HttpServletRequest request = _serviceContext.getRequest();

		if (request == null) {
			return null;
		}

		return (LiferayPortletResponse)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE);
	}

	public Locale getLocale() {
		if (_themeDisplay != null) {
			return _themeDisplay.getLocale();
		}

		return _serviceContext.getLocale();
	}

	public String getPathFriendlyURLPublic() {
		if (_themeDisplay != null) {
			return _themeDisplay.getPathFriendlyURLPublic();
		}

		ThemeDisplay themeDisplay = getThemeDisplay();

		if (themeDisplay == null) {
			return null;
		}

		return themeDisplay.getPathFriendlyURLPublic();
	}

	public String getPathMain() {
		if (_themeDisplay != null) {
			return _themeDisplay.getPathMain();
		}

		return _serviceContext.getPathMain();
	}

	public long getPlid() {
		if (_themeDisplay != null) {
			return _themeDisplay.getPlid();
		}

		return _serviceContext.getPlid();
	}

	public String getPortalURL() {
		if (_themeDisplay != null) {
			return _themeDisplay.getPortalURL();
		}

		return _serviceContext.getPortalURL();
	}

	public long getScopeGroupId() {
		if (_themeDisplay != null) {
			return _themeDisplay.getScopeGroupId();
		}

		return _serviceContext.getScopeGroupId();
	}

	public com.liferay.portal.service.ServiceContext getServiceContext() {
		return _serviceContext;
	}

	public ThemeDisplay getThemeDisplay() {
		if (_themeDisplay != null) {
			return _themeDisplay;
		}

		HttpServletRequest request = _serviceContext.getRequest();

		if (request == null) {
			return null;
		}

		return (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public TimeZone getTimeZone() {
		if (_themeDisplay != null) {
			return _themeDisplay.getTimeZone();
		}

		ThemeDisplay themeDisplay = getThemeDisplay();

		if (themeDisplay == null) {
			return null;
		}

		return themeDisplay.getTimeZone();
	}

	public long getUserId() {
		if (_themeDisplay != null) {
			return _themeDisplay.getUserId();
		}

		return _serviceContext.getUserId();
	}

	public String translate(String pattern, Object... arguments) {
		if (_themeDisplay != null) {
			return _themeDisplay.translate(pattern, arguments);
		}

		return _serviceContext.translate(pattern, arguments);
	}

	private com.liferay.portal.service.ServiceContext _serviceContext;
	private ThemeDisplay _themeDisplay;

}