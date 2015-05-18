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

import com.liferay.portal.kernel.util.Http;

import java.util.List;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

/**
 * @author László Csontos
 * @author André de Oliveira
 */
public class CertAuthPoolingHttpClientFactory implements HttpClientFactory {

	@Override
	public HttpClient createInstance() throws Exception {
		SSLSocketFactory sslSocketFactory = _sslSocketFactoryBuilder.build();

		_basePoolingHttpClientFactory = new BasePoolingHttpClientFactory(
			createPoolingClientConnectionManager(sslSocketFactory));

		_basePoolingHttpClientFactory.setDefaultMaxConnectionsPerRoute(
			_defaultMaxConnectionsPerRoute);
		_basePoolingHttpClientFactory.setHttpRequestInterceptors(
			_httpRequestInterceptors);
		_basePoolingHttpClientFactory.setMaxTotalConnections(
			_maxTotalConnections);

		return _basePoolingHttpClientFactory.createInstance();
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
		_basePoolingHttpClientFactory.shutdown();
	}

	protected PoolingClientConnectionManager
		createPoolingClientConnectionManager(
			SSLSocketFactory sslSocketFactory) {

		SchemeRegistry schemeRegistry = createSchemeRegistry(sslSocketFactory);

		return new PoolingClientConnectionManager(schemeRegistry);
	}

	protected SchemeRegistry createSchemeRegistry(
		SSLSocketFactory sslSocketFactory) {

		SchemeRegistry schemeRegistry = new SchemeRegistry();

		Scheme scheme = new Scheme(
			Http.HTTPS, Http.HTTPS_PORT, sslSocketFactory);

		schemeRegistry.register(scheme);

		return schemeRegistry;
	}

	private BasePoolingHttpClientFactory _basePoolingHttpClientFactory;
	private Integer _defaultMaxConnectionsPerRoute;
	private List<HttpRequestInterceptor> _httpRequestInterceptors;
	private Integer _maxTotalConnections;
	private SSLSocketFactoryBuilder _sslSocketFactoryBuilder;

}