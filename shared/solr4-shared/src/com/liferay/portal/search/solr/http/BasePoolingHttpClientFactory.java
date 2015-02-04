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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.pool.PoolStats;

/**
 * @author László Csontos
 * @author Bruno Farache
 * @author André de Oliveira
 */
public class BasePoolingHttpClientFactory implements HttpClientFactory {

	public BasePoolingHttpClientFactory(
		PoolingClientConnectionManager poolingClientConnectionManager) {

		_poolingClientConnectionManager = poolingClientConnectionManager;
	}

	@Override
	public DefaultHttpClient createInstance() {
		if (_log.isDebugEnabled()) {
			_log.debug("Create instance");
		}

		DefaultHttpClient defaultHttpClient = new DefaultHttpClient(
			_poolingClientConnectionManager);

		for (HttpRequestInterceptor httpRequestInterceptor :
				_httpRequestInterceptors) {

			defaultHttpClient.addRequestInterceptor(httpRequestInterceptor, 0);
		}

		return defaultHttpClient;
	}

	@Override
	public void setDefaultMaxConnectionsPerRoute(
		Integer defaultMaxConnectionsPerRoute) {

		if (defaultMaxConnectionsPerRoute != null) {
			_poolingClientConnectionManager.setDefaultMaxPerRoute(
				defaultMaxConnectionsPerRoute.intValue());
		}
	}

	@Override
	public void setHttpRequestInterceptors(
		List<HttpRequestInterceptor> httpRequestInterceptors) {

		_httpRequestInterceptors = httpRequestInterceptors;
	}

	@Override
	public void setMaxTotalConnections(Integer maxTotalConnections) {
		if (maxTotalConnections != null) {
			_poolingClientConnectionManager.setMaxTotal(
				maxTotalConnections.intValue());
		}
	}

	@Override
	public void shutdown() {
		if (_log.isDebugEnabled()) {
			_log.debug("Shut down");
		}

		int retry = 0;

		while (retry < 10) {
			PoolStats poolStats =
				_poolingClientConnectionManager.getTotalStats();

			int availableConnections = poolStats.getAvailable();

			if (availableConnections <= 0) {
				break;
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					toString() + " is waiting on " + availableConnections +
						" connections");
			}

			_poolingClientConnectionManager.closeIdleConnections(
				200, TimeUnit.MILLISECONDS);

			try {
				Thread.sleep(500);
			}
			catch (InterruptedException ie) {
			}

			retry++;
		}

		_poolingClientConnectionManager.shutdown();

		if (_log.isDebugEnabled()) {
			_log.debug(toString() + " was shut down");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		BasePoolingHttpClientFactory.class);

	private List<HttpRequestInterceptor> _httpRequestInterceptors;
	private PoolingClientConnectionManager _poolingClientConnectionManager;

}