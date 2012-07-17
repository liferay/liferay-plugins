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
 */
public class OAuthProviderManagerUtil {

	public static String addParameters(String url, String... parameters)
		throws OAuthException {

		return getOAuthProviderManager().addParameters(url, parameters);
	}

	public static void formEncode(
			String oauthToken, String tokenSecret, OutputStream out)
		throws OAuthException {

		getOAuthProviderManager().formEncode(oauthToken, tokenSecret, out);
	}

	public static void generateAccessToken(OAuthAccessor accessor, long userId)
		throws OAuthException, SystemException {

		getOAuthProviderManager().generateAccessToken(accessor, userId);
	}

	public static void generateRequestToken(OAuthAccessor accessor)
		throws OAuthException {

		getOAuthProviderManager().generateRequestToken(accessor);
	}

	public static OAuthAccessor getAccessor(OAuthMessage requestMessage)
		throws OAuthException, OAuthProblemException {

		return getOAuthProviderManager().getAccessor(requestMessage);
	}

	public static OAuthConsumer getConsumer(OAuthMessage requestMessage)
		throws OAuthException, OAuthProblemException {

		return getOAuthProviderManager().getConsumer(requestMessage);
	}

	public static OAuthMessage getMessage(
			HttpServletRequest request, String url) {

		return getOAuthProviderManager().getMessage(request, url);
	}

	public static OAuthMessage getMessage(PortletRequest request, String url) {
		return getOAuthProviderManager().getMessage(request, url);
	}

	public static OAuthProviderManager getOAuthProviderManager() {
		return _oAuthProviderManager;
	}

	public static void handleException(
		HttpServletRequest request, HttpServletResponse response, Exception e,
		boolean sendBody)
		throws IOException, ServletException {

		getOAuthProviderManager().handleException(
			request, response, e, sendBody);
	}

	public static void markAsAuthorized(OAuthAccessor accessor, long userId)
		throws OAuthException, SystemException {

		getOAuthProviderManager().markAsAuthorized(accessor, userId);
	}

	public static void markAsAuthorized(
			OAuthAccessor accessor, long userId, ServiceContext serviceContext)
		throws PortalException, SystemException {
		getOAuthProviderManager().markAsAuthorized(
				accessor, userId, serviceContext);
	}

	public static void validateMessage(
			OAuthMessage message, OAuthAccessor accessor)
		throws OAuthException {

		getOAuthProviderManager().validateMessage(message, accessor);
	}

	public void setOAuthProviderManager(
		OAuthProviderManager oAuthProviderManager) {

		this._oAuthProviderManager = oAuthProviderManager;
	}

	private static OAuthProviderManager _oAuthProviderManager;

}