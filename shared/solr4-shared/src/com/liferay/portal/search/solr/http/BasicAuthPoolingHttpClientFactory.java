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

package com.liferay.portal.search.solr.http;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

/**
 * @author László Csontos
 * @author Bruno Farache
 * @author André de Oliveira
 */
public class BasicAuthPoolingHttpClientFactory
	extends BasePoolingHttpClientFactory {

	public void setAuthScope(AuthScope authScope) {
		_authScope = authScope;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setUsername(String username) {
		_username = username;
	}

	@Override
	protected void configure(DefaultHttpClient defaultHttpClient) {
		if (Validator.isBlank(_username)) {
			return;
		}

		if (_authScope == null) {
			_authScope = AuthScope.ANY;
		}

		if (Validator.isNull(_password)) {
			_password = StringPool.BLANK;
		}

		CredentialsProvider credentialsProvider =
			defaultHttpClient.getCredentialsProvider();

		credentialsProvider.setCredentials(
			_authScope,
			new UsernamePasswordCredentials(_username, _password));
	}

	@Override
	protected PoolingClientConnectionManager
		createPoolingClientConnectionManager() {

		return new PoolingClientConnectionManager();
	}

	private AuthScope _authScope;
	private String _password;
	private String _username;

}