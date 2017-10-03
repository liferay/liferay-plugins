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

package com.liferay.testoauth.oauth;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletPreferences;

/**
 * @author Igor Beslic
 */
public class OAuthUtil {

	public static final int OAUTH_ACCESSOR_MISSING = 1;

	public static final int OAUTH_CONSUMER_MISSING = 0;

	public static final int OAUTH_READY = 2;

	public static String buildURL(
		String hostName, int port, String protocol, String uri) {

		StringBundler sb = new StringBundler(7);

		sb.append(protocol);
		sb.append(StringPool.COLON);
		sb.append(StringPool.DOUBLE_SLASH);
		sb.append(hostName);

		if (port != 80) {
			sb.append(StringPool.COLON);
			sb.append(port);
		}

		sb.append(uri);

		return sb.toString();
	}

	public static OAuthServiceHandler getOAuthServiceHandler(
		PortletPreferences portletPreferences) {

		return getOAuthServiceHandler(portletPreferences, null);
	}

	public static OAuthServiceHandler getOAuthServiceHandler(
		PortletPreferences portletPreferences, String callbackURL) {

		String accessURI = portletPreferences.getValue("accessURI", null);
		String authorizeURI = portletPreferences.getValue("authorizeURI", null);
		String key = portletPreferences.getValue("key", null);
		String hostName = portletPreferences.getValue("hostName", null);
		String hostPort = portletPreferences.getValue("hostPort", null);
		String requestURI = portletPreferences.getValue("requestURI", null);
		String secret = portletPreferences.getValue("secret", null);
		String windowState = portletPreferences.getValue("windowState", null);

		String accessURL = buildURL(
			hostName, GetterUtil.getInteger(hostPort), "http", accessURI);

		String authorizeURL = buildURL(
			hostName, GetterUtil.getInteger(hostPort), "http", authorizeURI);

		if (Validator.isNotNull(windowState)) {
			authorizeURL = HttpUtil.addParameter(
				authorizeURL, "windowState", windowState);
		}

		String requestURL = buildURL(
			hostName, GetterUtil.getInteger(hostPort), "http", requestURI);

		try {
			return OAuthServiceHandlerFactory.getInstance(
				key, secret, accessURL, authorizeURL, requestURL, callbackURL);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);

			return null;
		}
	}

	public static int getOAuthStatus(PortletPreferences portletPreferences) {
		if (!isAccessorReady(portletPreferences)) {
			return OAUTH_ACCESSOR_MISSING;
		}

		if (!isConsumerReady(portletPreferences)) {
			return OAUTH_CONSUMER_MISSING;
		}

		return OAUTH_READY;
	}

	public static boolean isAccessorReady(
		PortletPreferences portletPreferences) {

		String accessSecret = portletPreferences.getValue("accessSecret", null);
		String accessToken = portletPreferences.getValue("accessToken", null);

		if (Validator.isNotNull(accessSecret) &&
			Validator.isNotNull(accessToken)) {

			return true;
		}

		return false;
	}

	public static boolean isConsumerReady(
		PortletPreferences portletPreferences) {

		String key = portletPreferences.getValue("key", null);
		String secret = portletPreferences.getValue("secret", null);

		if (Validator.isNotNull(key) && Validator.isNotNull(secret)) {
			return true;
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(OAuthUtil.class);

}