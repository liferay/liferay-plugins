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

import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
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
import org.osgi.service.component.annotations.Reference;

/**
 * @author László Csontos
 * @author Bruno Farache
 * @author André de Oliveira
 */
@Component(
	immediate = true,
	property = {
		"default.max.connections.per.route=20", "max.total.connections=20",
		"password=solr", "type=BASIC", "username=solr"
	},
	service = HttpClientFactory.class
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
	protected void activate(Map<String, Object> properties) {
		int defaultMaxConnectionsPerRoute = MapUtil.getInteger(
			properties, "default.max.connections.per.route", 20);

		setDefaultMaxConnectionsPerRoute(defaultMaxConnectionsPerRoute);

		int maxTotalConnections = MapUtil.getInteger(
			properties, "max.total.connections", 20);

		setMaxTotalConnections(maxTotalConnections);

		String password = MapUtil.getString(
			properties, "password", StringPool.BLANK);

		setPassword(password);

		String username = MapUtil.getString(
			properties, "username", StringPool.BLANK);

		setUsername(username);
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
	private String _username;

}