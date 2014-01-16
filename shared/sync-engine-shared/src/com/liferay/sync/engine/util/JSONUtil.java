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

package com.liferay.sync.engine.util;

import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.remote.portlet.documentlibrary.handler.BaseHandler;
import com.liferay.sync.engine.service.SyncAccountService;

import java.net.URL;

import java.nio.charset.Charset;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;

/**
 * @author Shinn Lok
 */
public class JSONUtil {

	public static String executeRequest(
			long syncAccountId, String method, Map<String, Object> parameters)
		throws Exception {

		SyncAccount syncAccount = SyncAccountService.getSyncAccount(
			syncAccountId);

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		URL url = new URL(syncAccount.getUrl());

		CredentialsProvider credentialsProvider =
			new BasicCredentialsProvider();

		credentialsProvider.setCredentials(
			new AuthScope(url.getHost(), url.getPort()),
			new UsernamePasswordCredentials(
				syncAccount.getLogin(),
				Encryptor.decrypt(syncAccount.getPassword())));

		httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);

		HttpClient httpClient = httpClientBuilder.build();

		HttpHost httpHost = new HttpHost(
			url.getHost(), url.getPort(), url.getProtocol());

		BasicAuthCache basicAuthCache = new BasicAuthCache();

		BasicScheme basicScheme = new BasicScheme();

		basicAuthCache.put(httpHost, basicScheme);

		BasicHttpContext basicHttpContext = new BasicHttpContext();

		basicHttpContext.setAttribute(
			HttpClientContext.AUTH_CACHE, basicAuthCache);

		HttpPost httpPost = new HttpPost(syncAccount.getUrl() + method);

		MultipartEntityBuilder multipartEntityBuilder =
			getMultipartEntityBuilder(parameters);

		httpPost.setEntity(multipartEntityBuilder.build());

		BaseHandler baseHandler = new BaseHandler();

		return httpClient.execute(
			httpHost, httpPost, baseHandler, basicHttpContext);
	}

	protected static MultipartEntityBuilder getMultipartEntityBuilder(
		Map<String, Object> parameters) {

		MultipartEntityBuilder multipartEntityBuilder =
			MultipartEntityBuilder.create();

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			multipartEntityBuilder.addPart(
				entry.getKey(), getStringBody(entry.getValue()));
		}

		return multipartEntityBuilder;
	}

	protected static ContentBody getStringBody(Object value) {
		return new StringBody(
			String.valueOf(value),
			ContentType.create(MediaType.TEXT_PLAIN, Charset.defaultCharset()));
	}

}