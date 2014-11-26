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

import org.apache.http.auth.AuthScope;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

/**
 * @author László Csontos
 * @author Bruno Farache
 * @author André de Oliveira
 */
public class BasicAuthPoolingHttpClientFactoryBean
	extends BaseHttpClientFactoryBean {

	public void afterPropertiesSet() {
		PoolingClientConnectionManager poolingClientConnectionManager =
			new PoolingClientConnectionManager();

		BasicAuthPoolingHttpClientFactory httpClientFactory =
			new BasicAuthPoolingHttpClientFactory(
				poolingClientConnectionManager);

		httpClientFactory.setAuthScope(_authScope);
		httpClientFactory.setDefaultMaxConnectionsPerRoute(
			getDefaultMaxConnectionsPerRoute());
		httpClientFactory.setHttpRequestInterceptors(
			getHttpRequestInterceptors());
		httpClientFactory.setMaxTotalConnections(getMaxTotalConnections());
		httpClientFactory.setPassword(_password);
		httpClientFactory.setUsername(_username);

		setHttpClientFactory(httpClientFactory);
	}

	public void setAuthScope(AuthScope authScope) {
		_authScope = authScope;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setUsername(String username) {
		_username = username;
	}

	private AuthScope _authScope;
	private String _password;
	private String _username;

}