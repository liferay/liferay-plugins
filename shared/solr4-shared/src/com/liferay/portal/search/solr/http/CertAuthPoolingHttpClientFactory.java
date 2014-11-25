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
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

/**
 * @author László Csontos
 * @author André de Oliveira
 */
public class CertAuthPoolingHttpClientFactory implements HttpClientFactory {

	public CertAuthPoolingHttpClientFactory(SSLSocketFactory sslSocketFactory) {
		SchemeRegistry schemeRegistry = createSchemeRegistry(sslSocketFactory);

		PoolingClientConnectionManager poolingClientConnectionManager =
			new PoolingClientConnectionManager(schemeRegistry);

		_basePoolingHttpClientFactory = new BasePoolingHttpClientFactory(
			poolingClientConnectionManager);
	}

	@Override
	public HttpClient createInstance() {
		return _basePoolingHttpClientFactory.createInstance();
	}

	public void setDefaultMaxConnectionsPerRoute(
		Integer defaultMaxConnectionsPerRoute) {

		_basePoolingHttpClientFactory.setDefaultMaxConnectionsPerRoute(
			defaultMaxConnectionsPerRoute);
	}

	public void setHttpRequestInterceptors(
		List<HttpRequestInterceptor> httpRequestInterceptors) {

		_basePoolingHttpClientFactory.setHttpRequestInterceptors(
			httpRequestInterceptors);
	}

	public void setMaxTotalConnections(Integer maxTotalConnections) {
		_basePoolingHttpClientFactory.setMaxTotalConnections(
			maxTotalConnections);
	}

	@Override
	public void shutdown() {
		_basePoolingHttpClientFactory.shutdown();
	}

	protected SchemeRegistry createSchemeRegistry(
		SSLSocketFactory sslSocketFactory) {

		Scheme scheme = new Scheme(
			_DEFAULT_SCHEME_NAME, _DEFAULT_SCHEME_PORT, sslSocketFactory);

		SchemeRegistry schemeRegistry = new SchemeRegistry();

		schemeRegistry.register(scheme);

		return schemeRegistry;
	}

	private static final String _DEFAULT_SCHEME_NAME = "https";

	private static final int _DEFAULT_SCHEME_PORT = 443;

	private BasePoolingHttpClientFactory _basePoolingHttpClientFactory;

}