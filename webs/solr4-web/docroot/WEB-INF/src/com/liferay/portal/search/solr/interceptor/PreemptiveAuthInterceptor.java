/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.search.solr.interceptor;

import java.io.IOException;

import org.apache.http.HttpException;
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

/**
 * @author Eric Min
 */
public class PreemptiveAuthInterceptor implements HttpRequestInterceptor {

	public void process(HttpRequest request, HttpContext context)
		throws HttpException, IOException {

		AuthState authState = (AuthState)context.getAttribute(
			ClientContext.TARGET_AUTH_STATE);

		if (authState.getAuthScheme() != null) {
			return;
		}

		CredentialsProvider credentialsProvider =
			(CredentialsProvider)context.getAttribute(
				ClientContext.CREDS_PROVIDER);

		HttpHost targetHost = (HttpHost)context.getAttribute(
			ExecutionContext.HTTP_TARGET_HOST);

		AuthScope authScope = new AuthScope(
			targetHost.getHostName(), targetHost.getPort());

		Credentials credentials = credentialsProvider.getCredentials(authScope);

		if (credentials != null) {
			authState.setAuthScheme(new BasicScheme());
			authState.setCredentials(credentials);
		}
	}

}