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

package com.liferay.portal.search.solr.internal.server;

import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.search.solr.http.HttpClientFactory;

import java.util.Map;

import org.apache.solr.client.solrj.SolrServer;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author László Csontos
 * @author André de Oliveira
 */
@Component(
	immediate = true, property = {"url=http://localhost:8080/solr"},
	service = SolrServer.class
)
public class HttpSolrServer extends BaseHttpSolrServer {

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		String url = MapUtil.getString(
			properties, "url", "http://localhost:8080/solr");

		setUrl(url);

		initHttpSolrServer(_httpClientFactory.createInstance());
	}

	@Deactivate
	protected void deactivate() {
		shutdown();
	}

	@Reference(unbind = "-")
	protected void setHttpClientFactory(HttpClientFactory httpClientFactory) {
		_httpClientFactory = httpClientFactory;
	}

	private HttpClientFactory _httpClientFactory;

}