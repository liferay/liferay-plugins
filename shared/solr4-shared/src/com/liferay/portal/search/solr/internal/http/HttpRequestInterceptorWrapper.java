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

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

/**
 * @author Michael C. Han
 */
public class HttpRequestInterceptorWrapper
	implements Comparable<HttpRequestInterceptorWrapper>,
		HttpRequestInterceptor {

	public HttpRequestInterceptorWrapper(
		HttpRequestInterceptor httpRequestInterceptor, int sortOrder) {

		_httpRequestInterceptor = httpRequestInterceptor;
		_sortOrder = sortOrder;
	}

	@Override
	public int compareTo(
		HttpRequestInterceptorWrapper httpRequestInterceptorWrapper) {

		Integer sortOrder = _sortOrder;

		return sortOrder.compareTo(
			httpRequestInterceptorWrapper.getSortOrder());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		return _httpRequestInterceptor.equals(obj);
	}

	public int getSortOrder() {
		return _sortOrder;
	}

	@Override
	public int hashCode() {
		return _httpRequestInterceptor.hashCode();
	}

	@Override
	public void process(HttpRequest httpRequest, HttpContext httpContext)
		throws HttpException, IOException {

		_httpRequestInterceptor.process(httpRequest, httpContext);
	}

	private HttpRequestInterceptor _httpRequestInterceptor;
	private int _sortOrder = 0;

}