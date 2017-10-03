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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * @author Igor Beslic
 */
public class OAuthServiceHandlerImpl implements OAuthServiceHandler {

	@Override
	public Token extractAccessToken(Token requestToken, String oAuthVerifier) {
		Verifier verifier = new Verifier(oAuthVerifier);

		OAuthService oAuthService = getOAuthService();

		return oAuthService.getAccessToken(requestToken, verifier);
	}

	@Override
	public String getAuthorizeURL(Token requestToken) {
		return getAuthorizeURL(requestToken, null);
	}

	@Override
	public String getAuthorizeURL(Token requestToken, String callbackURL) {
		if (requestToken == null) {
			_log.error("Request token parameter is null");

			return null;
		}

		String authorizeURL = _authorizeURL;

		if (Validator.isNotNull(callbackURL)) {
			authorizeURL = HttpUtil.addParameter(
				authorizeURL, OAuthConstants.CALLBACK, callbackURL);
		}

		return HttpUtil.addParameter(
			authorizeURL, OAuthConstants.TOKEN, requestToken.getToken());
	}

	@Override
	public OAuthService getOAuthService() {
		if (_oAuthService == null) {
			Api api = new DefaultApi10a() {

				@Override
				public String getAccessTokenEndpoint() {
					return _accessURL;
				}

				@Override
				public String getAuthorizationUrl(Token token) {
					return HttpUtil.addParameter(
						_authorizeURL, OAuthConstants.TOKEN, token.getToken());
				}

				@Override
				public String getRequestTokenEndpoint() {
					return _requestURL;
				}

			};

			OAuthConfig oAuthConfig = new OAuthConfig(
				_consumerKey, _consumerSecret, "http://localhost:8080",
				SignatureType.Header, null, null);

			_oAuthService = api.createService(oAuthConfig);
		}

		return _oAuthService;
	}

	public Token getRequestToken() {
		try {
			OAuthService oAuthService = getOAuthService();

			return oAuthService.getRequestToken();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	protected void setAccessURL(String accessURL) {
		_accessURL = accessURL;
	}

	protected void setAuthorizeURL(String authorizeURL) {
		_authorizeURL = authorizeURL;
	}

	protected void setConsumerKey(String consumerKey) {
		_consumerKey = consumerKey;
	}

	protected void setConsumerSecret(String consumerSecret) {
		_consumerSecret = consumerSecret;
	}

	protected void setRequestURL(String requestURL) {
		_requestURL = requestURL;
	}

	private static Log _log = LogFactoryUtil.getLog(
		OAuthServiceHandlerImpl.class);

	private String _accessURL;
	private String _authorizeURL;
	private String _consumerKey;
	private String _consumerSecret;
	private OAuthService _oAuthService;
	private String _requestURL;

}