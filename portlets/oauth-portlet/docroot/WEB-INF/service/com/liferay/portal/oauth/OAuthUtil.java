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

package com.liferay.portal.oauth;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.service.ServiceContext;

import java.io.IOException;
import java.io.OutputStream;

import javax.portlet.PortletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ivica Cardic
 * @author Raymond Augé
 */
public class OAuthUtil {

	public static String addParameters(String url, String... parameters)
		throws IOException {

		return getOAuth().addParameters(url, parameters);
	}

	public static void authorize(
			OAuthAccessor accessor, long userId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		getOAuth().authorize(accessor, userId, serviceContext);
	}

	public static void formEncode(
			String oauthToken, String tokenSecret, OutputStream out)
		throws IOException {

		getOAuth().formEncode(oauthToken, tokenSecret, out);
	}

	public static void generateAccessToken(
			OAuthAccessor accessor, long userId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		getOAuth().generateAccessToken(accessor, userId, serviceContext);
	}

	public static void generateRequestToken(OAuthAccessor accessor)
		throws OAuthException {

		getOAuth().generateRequestToken(accessor);
	}

	public static OAuthAccessor getAccessor(OAuthMessage requestMessage)
		throws IOException, PortalException {

		return getOAuth().getAccessor(requestMessage);
	}

	public static OAuthConsumer getConsumer(OAuthMessage requestMessage)
		throws IOException, PortalException, SystemException {

		return getOAuth().getConsumer(requestMessage);
	}

	public static OAuthMessage getMessage(HttpServletRequest request) {
		return getOAuth().getMessage(request);
	}

	public static OAuthMessage getMessage(
		HttpServletRequest request, String url) {

		return getOAuth().getMessage(request, url);
	}

	public static OAuthMessage getMessage(PortletRequest portletRequest) {
		return getOAuth().getMessage(portletRequest);
	}

	public static OAuthMessage getMessage(
		PortletRequest portletRequest, String url) {

		return getOAuth().getMessage(portletRequest, url);
	}

	public static OAuth getOAuth() {
		return _oAuth;
	}

	public static void handleException(
			HttpServletRequest request, HttpServletResponse response,
			Exception exception, boolean sendBody)
		throws IOException, ServletException {

		getOAuth().handleException(request, response, exception, sendBody);
	}

	public static String randomizeToken(String token) {
		return getOAuth().randomizeToken(token);
	}

	public static void validateMessage(
			OAuthMessage message, OAuthAccessor accessor)
		throws OAuthException {

		getOAuth().validateMessage(message, accessor);
	}

	public void setOAuth(OAuth oAuth) {
		_oAuth = oAuth;
	}

	private static OAuth _oAuth;

}