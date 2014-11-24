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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.pool.PoolStats;

/**
 * @author László Csontos
 * @author Bruno Farache
 * @author André de Oliveira
 */
public class BasicAuthPoolingHttpClientFactory implements HttpClientFactory {

	public BasicAuthPoolingHttpClientFactory(
		PoolingClientConnectionManager poolingClientConnectionManager) {

		_poolingClientConnectionManager = poolingClientConnectionManager;
	}

	@Override
	public HttpClient createInstance() {
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient(
			_poolingClientConnectionManager);

		if ((_username != null) && (_password != null)) {
			if (_authScope == null) {
				_authScope = AuthScope.ANY;
			}

			CredentialsProvider credentialsProvider =
				defaultHttpClient.getCredentialsProvider();

			credentialsProvider.setCredentials(
				_authScope,
				new UsernamePasswordCredentials(_username, _password));
		}

		for (HttpRequestInterceptor httpRequestInterceptor :
				_httpRequestInterceptors) {

			defaultHttpClient.addRequestInterceptor(httpRequestInterceptor, 0);
		}

		return defaultHttpClient;
	}

	public void setAuthScope(AuthScope authScope) {
		_authScope = authScope;
	}

	public void setDefaultMaxConnectionsPerRoute(
		Integer defaultMaxConnectionsPerRoute) {

		if (defaultMaxConnectionsPerRoute != null) {
			_poolingClientConnectionManager.setDefaultMaxPerRoute(
				defaultMaxConnectionsPerRoute.intValue());
		}
	}

	public void setHttpRequestInterceptors(
		List<HttpRequestInterceptor> httpRequestInterceptors) {

		_httpRequestInterceptors = httpRequestInterceptors;
	}

	public void setMaxTotalConnections(Integer maxTotalConnections) {
		if (maxTotalConnections != null) {
			_poolingClientConnectionManager.setMaxTotal(
				maxTotalConnections.intValue());
		}
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setUsername(String username) {
		_username = username;
	}

	@Override
	public void shutdown() {
		int retry = 0;

		while (retry < 10) {
			PoolStats poolStats =
				_poolingClientConnectionManager.getTotalStats();

			int availableConnections = poolStats.getAvailable();

			if (availableConnections <= 0) {
				break;
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					toString() + " waiting on " + availableConnections +
						" connections");
			}

			_poolingClientConnectionManager.closeIdleConnections(
				200, TimeUnit.MILLISECONDS);

			try {
				Thread.sleep(500);
			}
			catch (InterruptedException ie) {
			}

			retry++;
		}

		_poolingClientConnectionManager.shutdown();

		if (_log.isDebugEnabled()) {
			_log.debug(toString() + " is shutdown");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		BasicAuthPoolingHttpClientFactory.class);

	private AuthScope _authScope;
	private List<HttpRequestInterceptor> _httpRequestInterceptors;
	private String _password;
	private PoolingClientConnectionManager _poolingClientConnectionManager;
	private String _username;

}