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

/**
 * @author Ivica Cardic
 */
public class OAuthAccessorImpl implements OAuthAccessor {

	public OAuthAccessorImpl(OAuthConsumer oAuthConsumer) {
		this(new net.oauth.OAuthAccessor(
			(net.oauth.OAuthConsumer)oAuthConsumer.getWrappedOAuthConsumer()));

		this._oAuthConsumer = oAuthConsumer;
	}

	public OAuthAccessorImpl(net.oauth.OAuthAccessor oAuthAccessor) {
		this._oAuthAccessor = oAuthAccessor;
	}

	public String getAccessToken() {
		return _oAuthAccessor.accessToken;
	}

	public OAuthConsumer getConsumer() {
		_oAuthConsumer.setWrappedOAuthConsumer(_oAuthAccessor.consumer);

		return _oAuthConsumer;
	}

	public Object getProperty(String name) {
		return _oAuthAccessor.getProperty(name);
	}

	public String getRequestToken() {
		return _oAuthAccessor.requestToken;
	}

	public String getTokenSecret() {
		return _oAuthAccessor.tokenSecret;
	}

	public Object getWrappedOAuthAccessor() {
		return _oAuthAccessor;
	}

	public void setAccessToken(String accesToken) {
		this._oAuthAccessor.accessToken = accesToken;
	}

	public void setProperty(String name, Object value) {
		this._oAuthAccessor.setProperty(name, value);
	}

	public void setRequestToken(String requestToken) {
		this._oAuthAccessor.requestToken = requestToken;
	}

	public void setTokenSecret(String tokenSecret) {
		this._oAuthAccessor.tokenSecret = tokenSecret;
	}

	private net.oauth.OAuthAccessor _oAuthAccessor;
	private OAuthConsumer _oAuthConsumer;

}