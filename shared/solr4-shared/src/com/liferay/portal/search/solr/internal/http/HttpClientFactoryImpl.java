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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.solr.http.HttpClientFactory;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author László Csontos
 * @author André de Oliveira
 */
@Component(
	immediate = true, property = {"auth=BASIC"},
	service = HttpClientFactory.class
)
public class HttpClientFactoryImpl implements HttpClientFactory {

	@Override
	public HttpClient createInstance() throws Exception {
		HttpClientFactory httpClientFactory = getHttpClientFactory();

		return httpClientFactory.createInstance();
	}

	@Deactivate
	@Override
	public void shutdown() {
		HttpClientFactory httpClientFactory = getHttpClientFactory();

		httpClientFactory.shutdown();
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_auth = MapUtil.getString(properties, "auth", "BASIC");
	}

	@Reference(target = "(auth=BASIC)", unbind = "-")
	protected void setBasicHttpClientFactory(
		HttpClientFactory httpClientFactory) {

		_httpClientFactories.put("BASIC", httpClientFactory);
	}

	@Reference(target = "(auth=CERT)", unbind = "-")
	protected void setCertHttpClientFactory(
		HttpClientFactory httpClientFactory) {

		_httpClientFactories.put("CERT", httpClientFactory);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(&(!(auth=BASIC))(!(auth=CERT)))"
	)
	protected void setHttpClientFactory(
		HttpClientFactory httpClientFactory,
		Map<String, Object> properties) {

		String auth = MapUtil.getString(properties, "auth");

		if (Validator.isNull(auth)) {
			throw new IllegalArgumentException("Invalid auth: " + auth);
		}

		_httpClientFactories.put(auth, httpClientFactory);
	}

	protected void unsetHttpClientFactory(
		HttpClientFactory httpClientFactory,
		Map<String, Object> properties) {

		String auth = MapUtil.getString(properties, "auth");

		if (Validator.isNull(auth)) {
			return;
		}

		_httpClientFactories.remove(auth);
	}

	private HttpClientFactory getHttpClientFactory() {
		HttpClientFactory httpClientFactory = _httpClientFactories.get(_auth);

		if (httpClientFactory == null) {
			throw new SystemException(
				"No HTTP client factory found for " + _auth);
		}

		return httpClientFactory;
	}

	private String _auth;
	private Map<String, HttpClientFactory> _httpClientFactories =
		new HashMap<>();

}