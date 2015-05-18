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

import com.liferay.portal.kernel.exception.SystemException;

import java.util.Map;

import org.apache.http.client.HttpClient;

/**
 * @author László Csontos
 * @author André de Oliveira
 */
public class HttpClientFactoryImpl implements HttpClientFactory {

	@Override
	public HttpClient createInstance() throws Exception {
		HttpClientFactory httpClientFactory = getHttpClientFactory();

		return httpClientFactory.createInstance();
	}

	public void setAuth(String auth) {
		_auth = auth;
	}

	public void setHttpClientFactories(
		Map<String, HttpClientFactory> httpClientFactories) {

		_httpClientFactories = httpClientFactories;
	}

	@Override
	public void shutdown() {
		HttpClientFactory httpClientFactory = getHttpClientFactory();

		httpClientFactory.shutdown();
	}

	private HttpClientFactory getHttpClientFactory() {
		HttpClientFactory httpClientFactory = _httpClientFactories.get(_auth);

		if (httpClientFactory == null) {
			throw new SystemException(
				"Invalid auth: " + _auth + ". Valid property values: " +
					_httpClientFactories.keySet());
		}

		return httpClientFactory;
	}

	private String _auth;
	private Map<String, HttpClientFactory> _httpClientFactories;

}