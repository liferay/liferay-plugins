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

package com.liferay.portal.search.solr.server;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.search.solr.http.BasicAuthPoolingHttpClientFactory;
import com.liferay.portal.search.solr.http.HttpClientFactory;

import java.util.List;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

/**
 * @author Bruno Farache
 * @author László Csontos
 * @author André de Oliveira
 */
@Deprecated
public class BasicAuthSolrServer extends BaseHttpSolrServer {

	public void afterPropertiesSet() {
		if (_log.isWarnEnabled()) {
			_log.warn(
				ClassUtil.getClassName(this) + " is deprecated in favor of " +
				com.liferay.portal.search.solr.server.HttpSolrServer.class.
					getName());
		}

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

		initHttpSolrServer(basicAuthPoolingHttpClientFactory.createInstance());
	}

	public void setAuthScope(AuthScope authScope) {
		_authScope = authScope;
	}

	public void setDefaultMaxConnectionsPerRoute(
		int defaultMaxConnectionsPerRoute) {

		_defaultMaxConnectionsPerRoute = defaultMaxConnectionsPerRoute;

		HttpSolrServer httpSolrServer = getHttpSolrServer();

		if (httpSolrServer != null) {
			httpSolrServer.setDefaultMaxConnectionsPerHost(
				defaultMaxConnectionsPerRoute);
		}
	}

	public void setHttpRequestInterceptors(
		List<HttpRequestInterceptor> httpRequestInterceptors) {

		_httpRequestInterceptors = httpRequestInterceptors;
	}

	public void setMaxTotalConnections(int maxTotalConnections) {
		_maxTotalConnections = maxTotalConnections;

		HttpSolrServer httpSolrServer = getHttpSolrServer();

		if (httpSolrServer != null) {
			httpSolrServer.setMaxTotalConnections(maxTotalConnections);
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
		super.shutdown();

		_httpClientFactory.shutdown();
	}

	private static Log _log = LogFactoryUtil.getLog(BasicAuthSolrServer.class);

	private AuthScope _authScope;
	private Integer _defaultMaxConnectionsPerRoute;
	private HttpClientFactory _httpClientFactory;
	private List<HttpRequestInterceptor> _httpRequestInterceptors;
	private Integer _maxTotalConnections;
	private String _password;
	private String _username;

}