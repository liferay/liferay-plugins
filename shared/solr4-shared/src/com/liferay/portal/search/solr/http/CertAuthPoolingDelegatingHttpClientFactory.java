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
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLSocketFactory;

/**
 * @author László Csontos
 * @author André de Oliveira
 */
public class CertAuthPoolingDelegatingHttpClientFactory
	implements HttpClientFactory {

	@Override
	public HttpClient createInstance() throws Exception {
		SSLSocketFactory sslSocketFactory = _sslSocketFactoryBuilder.build();

		CertAuthPoolingHttpClientFactory httpClientFactory =
			new CertAuthPoolingHttpClientFactory(sslSocketFactory);

		httpClientFactory.setDefaultMaxConnectionsPerRoute(
			_defaultMaxConnectionsPerRoute);
		httpClientFactory.setHttpRequestInterceptors(_httpRequestInterceptors);
		httpClientFactory.setMaxTotalConnections(_maxTotalConnections);

		_httpClientFactory = httpClientFactory;

		return _httpClientFactory.createInstance();
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

	public void setSslSocketFactoryBuilder(
		SSLSocketFactoryBuilder sslSocketFactoryBuilder) {

		_sslSocketFactoryBuilder = sslSocketFactoryBuilder;
	}

	@Override
	public void shutdown() {
		_httpClientFactory.shutdown();
	}

	private Integer _defaultMaxConnectionsPerRoute;
	private HttpClientFactory _httpClientFactory;
	private List<HttpRequestInterceptor> _httpRequestInterceptors;
	private Integer _maxTotalConnections;
	private SSLSocketFactoryBuilder _sslSocketFactoryBuilder;

}