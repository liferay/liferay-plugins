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

/**
 * @author László Csontos
 * @author Bruno Farache
 * @author André de Oliveira
 */
public class DelegatingHttpClientFactory implements HttpClientFactory {

	@Override
	public HttpClient createInstance() {
		return _httpClientFactory.createInstance();
	}

	public Integer getDefaultMaxConnectionsPerRoute() {
		return _defaultMaxConnectionsPerRoute;
	}

	public List<HttpRequestInterceptor> getHttpRequestInterceptors() {
		return _httpRequestInterceptors;
	}

	public Integer getMaxTotalConnections() {
		return _maxTotalConnections;
	}

	@Override
	public void setDefaultMaxConnectionsPerRoute(
		Integer defaultMaxConnectionsPerRoute) {

		_defaultMaxConnectionsPerRoute = defaultMaxConnectionsPerRoute;
	}

	@Override
	public void setHttpRequestInterceptors(
		List<HttpRequestInterceptor> httpRequestInterceptors) {

		_httpRequestInterceptors = httpRequestInterceptors;
	}

	@Override
	public void setMaxTotalConnections(Integer maxTotalConnections) {
		_maxTotalConnections = maxTotalConnections;
	}

	@Override
	public void shutdown() {
		_httpClientFactory.shutdown();
	}

	protected void setHttpClientFactory(HttpClientFactory httpClientFactory) {
		_httpClientFactory = httpClientFactory;
	}

	private Integer _defaultMaxConnectionsPerRoute;
	private HttpClientFactory _httpClientFactory;
	private List<HttpRequestInterceptor> _httpRequestInterceptors;
	private Integer _maxTotalConnections;

}