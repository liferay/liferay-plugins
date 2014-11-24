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

/**
 * @author André de Oliveira
 * @author László Csontos
 */
public interface HttpClientFactory {

	public HttpClient createInstance();

	public void setDefaultMaxConnectionsPerRoute(
		Integer defaultMaxConnectionsPerRoute);

	public void setHttpRequestInterceptors(
		List<HttpRequestInterceptor> httpRequestInterceptors);

	public void setMaxTotalConnections(Integer maxTotalConnections);

	public void shutdown();

}