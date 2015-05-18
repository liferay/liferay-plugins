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

import java.util.List;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

/**
 * @author László Csontos
 * @author Bruno Farache
 * @author André de Oliveira
 */
public class BasicAuthPoolingDelegatingHttpClientFactory
	implements HttpClientFactory {

	@Override
	public HttpClient createInstance() throws Exception {
		PoolingClientConnectionManager poolingClientConnectionManager =
			new PoolingClientConnectionManager();

		BasicAuthPoolingHttpClientFactory basicAuthPoolingHttpClientFactory =
			new BasicAuthPoolingHttpClientFactory(
				poolingClientConnectionManager);

		basicAuthPoolingHttpClientFactory.setAuthScope(_authScope);
		basicAuthPoolingHttpClientFactory.setDefaultMaxConnectionsPerRoute(
			_defaultMaxConnectionsPerRoute);
		basicAuthPoolingHttpClientFactory.setHttpRequestInterceptors(
			_httpRequestInterceptors);
		basicAuthPoolingHttpClientFactory.setMaxTotalConnections(
			_maxTotalConnections);
		basicAuthPoolingHttpClientFactory.setPassword(_password);
		basicAuthPoolingHttpClientFactory.setUsername(_username);

		_httpClientFactory = basicAuthPoolingHttpClientFactory;

		return _httpClientFactory.createInstance();
	}

	public void setAuthScope(AuthScope authScope) {
		_authScope = authScope;
	}

	public void setDefaultMaxConnectionsPerRoute(
		Integer defaultMaxConnectionsPerRoute) {

		_defaultMaxConnectionsPerRoute = defaultMaxConnectionsPerRoute;
	}

	public void setHttpRequestInterceptors(
		List<HttpRequestInterceptor> httpRequestInterceptors) {

		_httpRequestInterceptors = httpRequestInterceptors;
	}

	public void setMaxTotalConnections(Integer maxTotalConnections) {
		_maxTotalConnections = maxTotalConnections;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setUsername(String username) {
		_username = username;
	}

	@Override
	public void shutdown() {
		_httpClientFactory.shutdown();
	}

	private AuthScope _authScope;
	private Integer _defaultMaxConnectionsPerRoute;
	private HttpClientFactory _httpClientFactory;
	private List<HttpRequestInterceptor> _httpRequestInterceptors;
	private Integer _maxTotalConnections;
	private String _password;
	private String _username;

}