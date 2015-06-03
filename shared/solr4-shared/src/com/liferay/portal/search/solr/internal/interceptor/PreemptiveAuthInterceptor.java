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

package com.liferay.portal.search.solr.internal.interceptor;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eric Min
 */
@Component(immediate = true, service = HttpRequestInterceptor.class)
public class PreemptiveAuthInterceptor implements HttpRequestInterceptor {

	@Override
	public void process(HttpRequest request, HttpContext httpContext) {
		AuthState authState = (AuthState)httpContext.getAttribute(
			ClientContext.TARGET_AUTH_STATE);

		if (authState.getAuthScheme() != null) {
			return;
		}

		CredentialsProvider credentialsProvider =
			(CredentialsProvider)httpContext.getAttribute(
				ClientContext.CREDS_PROVIDER);

		HttpHost targetHttpHost = (HttpHost)httpContext.getAttribute(
			ExecutionContext.HTTP_TARGET_HOST);

		AuthScope authScope = new AuthScope(
			targetHttpHost.getHostName(), targetHttpHost.getPort());

		Credentials credentials = credentialsProvider.getCredentials(authScope);

		if (credentials != null) {
			authState.update(new BasicScheme(), credentials);
		}
	}

}