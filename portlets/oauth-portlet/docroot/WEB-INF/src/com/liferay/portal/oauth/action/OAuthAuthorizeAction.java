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

package com.liferay.portal.oauth.action;

import com.liferay.portal.kernel.portlet.WindowStateFactory;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.oauth.util.OAuthConstants;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.PortletURLFactoryUtil;

import java.io.IOException;

import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Authorization request handler action.
 *
 * @author Ivica Cardic
 */
public class OAuthAuthorizeAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		if (!isSignedIn()) {
			return redirectToLogin(request, response);
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

		String redirect;

		PortletURL portletURL = PortletURLFactoryUtil.create(
				request, PORTLET_ID, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);

		portletURL.setWindowState(getWindowState(request));
		portletURL.setPortletMode(PortletMode.VIEW);
		portletURL.setParameter(
			OAuthConstants.OAUTH_TOKEN,
			request.getParameter(OAuthConstants.OAUTH_TOKEN));
		portletURL.setParameter("saveLastPath", "0");

		redirect = portletURL.toString();

		response.sendRedirect(redirect);

		return null;
	}

	protected WindowState getWindowState(HttpServletRequest request) {
		WindowState windowState = WindowState.MAXIMIZED;

		String windowStateString = ParamUtil.getString(request, "windowState");

		if (Validator.isNotNull(windowStateString)) {
			windowState = WindowStateFactory.getWindowState(windowStateString);
		}

		return windowState;
	}

	protected boolean isSignedIn() {
		PermissionChecker permissionChecker = PermissionThreadLocal
			.getPermissionChecker();

		if ((permissionChecker == null) || !permissionChecker.isSignedIn()) {
			return false;
		}

		return true;
	}

	protected String redirectToLogin(
		HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String uri = request.getRequestURI();
		String queryString = request.getQueryString();

		StringBundler sb = new StringBundler();
		sb.append(themeDisplay.getPathMain());
		sb.append("/portal/login?redirect=");
		sb.append(HttpUtil.encodeURL(uri));
		if (Validator.isNotNull(queryString)) {
			sb.append(HttpUtil.encodeURL("?"+queryString));
		}

		response.sendRedirect(sb.toString());

		return null;
	}

	private static final String PORTLET_ID = "3_WAR_oauthportlet";

}