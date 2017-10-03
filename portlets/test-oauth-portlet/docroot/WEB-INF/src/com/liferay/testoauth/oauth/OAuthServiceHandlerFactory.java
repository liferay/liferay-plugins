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
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Igor Beslic
 */
public class OAuthServiceHandlerFactory {

	public static OAuthServiceHandler getInstance(
			String key, String secret, String accessURL, String authorizeURL,
			String requestURL)
		throws PortalException {

		return getInstance(
			key, secret, accessURL, authorizeURL, requestURL, null);
	}

	public static OAuthServiceHandler getInstance(
			String key, String secret, String accessURL, String authorizeURL,
			String requestURL, String callbackURL)
		throws PortalException {

		_validate(key, secret, accessURL, authorizeURL, requestURL);

		String cacheKey = key + accessURL;

		if (_oAuthServiceHandlers.containsKey(cacheKey)) {
			return _oAuthServiceHandlers.get(cacheKey);
		}

		OAuthServiceHandlerImpl oAuthServiceHandlerBase =
			new OAuthServiceHandlerImpl();

		oAuthServiceHandlerBase.setConsumerKey(key);
		oAuthServiceHandlerBase.setConsumerSecret(secret);
		oAuthServiceHandlerBase.setAccessURL(accessURL);

		if (Validator.isNotNull(callbackURL)) {
			authorizeURL = HttpUtil.addParameter(
				authorizeURL, "oauth_callback", callbackURL);
		}

		oAuthServiceHandlerBase.setAuthorizeURL(authorizeURL);

		oAuthServiceHandlerBase.setRequestURL(requestURL);

		_oAuthServiceHandlers.put(cacheKey, oAuthServiceHandlerBase);

		return oAuthServiceHandlerBase;
	}

	public static void resetServiceHandlers() {
		_oAuthServiceHandlers.clear();
	}

	private static void _validate(
			String key, String secret, String accessURL, String authorizeURL,
			String requestURL)
		throws PortalException {

		if (Validator.isNull(key)) {
			throw new PortalException("Key is null");
		}

		if (Validator.isNull(secret)) {
			throw new PortalException("Secret is null");
		}

		if (Validator.isNull(accessURL)) {
			throw new PortalException("Access URL is null");
		}

		if (Validator.isNull(authorizeURL)) {
			throw new PortalException("Authorize URL is null");
		}

		if (Validator.isNull(requestURL)) {
			throw new PortalException("Request URL is null");
		}
	}

	private static Map<String, OAuthServiceHandler> _oAuthServiceHandlers =
		new HashMap<>();

}