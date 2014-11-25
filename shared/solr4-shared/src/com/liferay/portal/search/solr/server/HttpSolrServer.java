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

import com.liferay.portal.search.solr.http.CertAuthPoolingHttpClientFactory;
import com.liferay.portal.search.solr.http.SSLSocketFactoryBuilder;

import java.util.List;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.solr.client.solrj.SolrServer;

/**
 * @author László Csontos
 * @author André de Oliveira
 */
public abstract class HttpSolrServer extends SolrServer {

	public void afterPropertiesSet() throws Exception {
		SSLSocketFactoryBuilder sslSocketFactoryBuilder =
			new SSLSocketFactoryBuilder();

		SSLSocketFactory sslSocketFactory = sslSocketFactoryBuilder.build();

		CertAuthPoolingHttpClientFactory httpClientFactory =
			new CertAuthPoolingHttpClientFactory(sslSocketFactory);

		httpClientFactory.setDefaultMaxConnectionsPerRoute(
			_defaultMaxConnectionsPerRoute);
		httpClientFactory.setHttpRequestInterceptors(_httpRequestInterceptors);
		httpClientFactory.setMaxTotalConnections(_maxTotalConnections);
	}

	private Integer _defaultMaxConnectionsPerRoute;
	private List<HttpRequestInterceptor> _httpRequestInterceptors;
	private Integer _maxTotalConnections;

}