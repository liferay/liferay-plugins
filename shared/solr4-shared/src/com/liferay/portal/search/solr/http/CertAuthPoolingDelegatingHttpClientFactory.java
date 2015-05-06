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

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLSocketFactory;

/**
 * @author László Csontos
 * @author André de Oliveira
 */
public class CertAuthPoolingDelegatingHttpClientFactory
	extends DelegatingHttpClientFactory {

	@Override
	public HttpClient createInstance() throws Exception {
		SSLSocketFactory sslSocketFactory = _sslSocketFactoryBuilder.build();

		CertAuthPoolingHttpClientFactory httpClientFactory =
			new CertAuthPoolingHttpClientFactory(sslSocketFactory);

		httpClientFactory.setDefaultMaxConnectionsPerRoute(
			getDefaultMaxConnectionsPerRoute());
		httpClientFactory.setHttpRequestInterceptors(
			getHttpRequestInterceptors());
		httpClientFactory.setMaxTotalConnections(getMaxTotalConnections());

		setHttpClientFactory(httpClientFactory);

		return super.createInstance();
	}

	public void setSslSocketFactoryBuilder(
		SSLSocketFactoryBuilder sslSocketFactoryBuilder) {

		_sslSocketFactoryBuilder = sslSocketFactoryBuilder;
	}

	private SSLSocketFactoryBuilder _sslSocketFactoryBuilder;

}