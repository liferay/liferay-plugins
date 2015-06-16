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

package com.liferay.portal.search.solr.internal.http;

import aQute.bnd.annotation.metatype.Configurable;

import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.search.solr.configuration.SolrConfiguration;
import com.liferay.portal.search.solr.http.HttpClientFactory;
import com.liferay.portal.search.solr.http.SSLSocketFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author László Csontos
 * @author André de Oliveira
 */
@Component(
	configurationPid = "com.liferay.portal.search.solr.configuration.SolrConfiguration",
	immediate = true, service = HttpClientFactory.class
)
public class CertAuthPoolingHttpClientFactory
	extends BasePoolingHttpClientFactory {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_solrConfiguration = Configurable.createConfigurable(
			SolrConfiguration.class, properties);

		int defaultMaxConnectionsPerRoute =
			_solrConfiguration.defaultMaxConnectionsPerRoute();

		setDefaultMaxConnectionsPerRoute(defaultMaxConnectionsPerRoute);

		int maxTotalConnections = _solrConfiguration.maxTotalConnections();

		setMaxTotalConnections(maxTotalConnections);
	}

	@Override
	protected void configure(DefaultHttpClient defaultHttpClient) {
	}

	@Override
	protected PoolingClientConnectionManager
		createPoolingClientConnectionManager() throws Exception {

		SSLSocketFactory sslSocketFactory = _sslSocketFactoryBuilder.build();

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

	@Deactivate
	protected void deactivate() {
		shutdown();
	}

	@Reference(unbind = "-")
	protected void setHttpRequestInterceptor(
		HttpRequestInterceptor httpRequestInterceptor) {

		List<HttpRequestInterceptor> httpRequestInterceptors =
			new ArrayList<>();

		httpRequestInterceptors.add(httpRequestInterceptor);

		setHttpRequestInterceptors(httpRequestInterceptors);
	}

	@Reference(unbind = "-")
	protected void setSslSocketFactoryBuilder(
		SSLSocketFactoryBuilder sslSocketFactoryBuilder) {

		_sslSocketFactoryBuilder = sslSocketFactoryBuilder;
	}

	private volatile SolrConfiguration _solrConfiguration;
	private SSLSocketFactoryBuilder _sslSocketFactoryBuilder;

}