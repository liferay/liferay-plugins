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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.solr.configuration.SolrConfiguration;
import com.liferay.portal.search.solr.http.HttpClientFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author László Csontos
 * @author Bruno Farache
 * @author André de Oliveira
 */
@Component(
	configurationPid = "com.liferay.portal.search.solr.configuration.SolrConfiguration",
	immediate = true, service = HttpClientFactory.class
)
public class BasicAuthPoolingHttpClientFactory
	extends BasePoolingHttpClientFactory {

	public void setAuthScope(AuthScope authScope) {
		_authScope = authScope;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setUsername(String username) {
		_username = username;
	}

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

		String basicAuthPassword = _solrConfiguration.basicAuthPassword();

		setPassword(basicAuthPassword);

		String basicAuthUserName = _solrConfiguration.basicAuthUserName();

		setUsername(basicAuthUserName);
	}

	@Override
	protected void configure(DefaultHttpClient defaultHttpClient) {
		if (Validator.isBlank(_username)) {
			return;
		}

		if (_authScope == null) {
			_authScope = AuthScope.ANY;
		}

		if (Validator.isNull(_password)) {
			_password = StringPool.BLANK;
		}

		CredentialsProvider credentialsProvider =
			defaultHttpClient.getCredentialsProvider();

		credentialsProvider.setCredentials(
			_authScope,
			new UsernamePasswordCredentials(_username, _password));
	}

	@Override
	protected PoolingClientConnectionManager
		createPoolingClientConnectionManager() {

		return new PoolingClientConnectionManager();
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

	private AuthScope _authScope;
	private String _password;
	private volatile SolrConfiguration _solrConfiguration;
	private String _username;

}