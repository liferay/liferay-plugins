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

import com.liferay.portal.search.solr.http.HttpClientFactory;

import java.util.Map;

/**
 * @author László Csontos
 * @author André de Oliveira
 * @author Tibor Lipusz
 */
public class HttpSolrServer extends BaseHttpSolrServer {

	public void afterPropertiesSet() throws Exception {
		HttpClientFactory httpClientFactory = _httpClientFactories.get(_auth);

		if (httpClientFactory == null) {
			throw new IllegalStateException(
				"No HTTP client factory for " + _auth);
		}

		initHttpSolrServer(httpClientFactory.createInstance());
	}

	public void setAuth(String auth) {
		_auth = auth;
	}

	public void setHttpClientFactories(
		Map<String, HttpClientFactory> httpClientFactories) {

		_httpClientFactories = httpClientFactories;
	}

	private String _auth;
	private Map<String, HttpClientFactory> _httpClientFactories;

}