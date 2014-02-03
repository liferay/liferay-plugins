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

import com.liferay.sync.engine.documentlibrary.handler.BaseHandler;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;

import java.net.URL;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
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
public class HttpUtil {

	public static String executeGet(long syncAccountId, String urlPath)
		throws Exception {

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			syncAccountId);

		URL url = new URL(syncAccount.getUrl());

		HttpHost httpHost = new HttpHost(
			url.getHost(), url.getPort(), url.getProtocol());

		HttpGet httpGet = new HttpGet(urlPath);

		HttpClient httpClient = _getHttpClient(syncAccount, url);

		return httpClient.execute(
			httpHost, httpGet, new BaseHandler(),
			_getBasicHttpContext(httpHost));
	}

	public static String executePost(
			long syncAccountId, String urlPath, Map<String, Object> parameters)
		throws Exception {

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			syncAccountId);

		URL url = new URL(syncAccount.getUrl());

		HttpClient httpClient = _getHttpClient(syncAccount, url);

		HttpHost httpHost = new HttpHost(
			url.getHost(), url.getPort(), url.getProtocol());

		HttpPost httpPost = new HttpPost(syncAccount.getUrl() + urlPath);

		Path filePath = (Path)parameters.remove("filePath");

		MultipartEntityBuilder multipartEntityBuilder =
			_getMultipartEntityBuilder(parameters);

		if (filePath != null) {
			multipartEntityBuilder.addPart(
				"file",
				_getByteArrayBody(
					Files.readAllBytes(filePath),
					(String)parameters.get("mimeType"),
					(String)parameters.get("title")));
		}

		httpPost.setEntity(multipartEntityBuilder.build());

		return httpClient.execute(
			httpHost, httpPost, new BaseHandler(),
			_getBasicHttpContext(httpHost));
	}

	private static BasicAuthCache _getBasicAuthCache(HttpHost httpHost) {
		BasicAuthCache basicAuthCache = new BasicAuthCache();

		BasicScheme basicScheme = new BasicScheme();

		basicAuthCache.put(httpHost, basicScheme);

		return basicAuthCache;
	}

	private static BasicHttpContext _getBasicHttpContext(HttpHost httpHost) {
		BasicHttpContext basicHttpContext = new BasicHttpContext();

		basicHttpContext.setAttribute(
			HttpClientContext.AUTH_CACHE, _getBasicAuthCache(httpHost));

		return basicHttpContext;
	}

	private static ContentBody _getByteArrayBody(
			byte[] bytes, String mimeType, String fileName)
		throws Exception {

		return new ByteArrayBody(bytes, ContentType.create(mimeType), fileName);
	}

	private static HttpClient _getHttpClient(SyncAccount syncAccount, URL url)
		throws Exception {

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		CredentialsProvider credentialsProvider =
			new BasicCredentialsProvider();

		credentialsProvider.setCredentials(
			new AuthScope(url.getHost(), url.getPort()),
			new UsernamePasswordCredentials(
				syncAccount.getLogin(),
				Encryptor.decrypt(syncAccount.getPassword())));

		httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);

		return httpClientBuilder.build();
	}

	private static MultipartEntityBuilder _getMultipartEntityBuilder(
		Map<String, Object> parameters) {

		MultipartEntityBuilder multipartEntityBuilder =
			MultipartEntityBuilder.create();

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			if (_ignoredParameterKeys.contains(entry.getKey())) {
				continue;
			}

			multipartEntityBuilder.addPart(
				entry.getKey(), _getStringBody(entry.getValue()));
		}

		return multipartEntityBuilder;
	}

	private static StringBody _getStringBody(Object value) {
		return new StringBody(
			String.valueOf(value),
			ContentType.create(MediaType.TEXT_PLAIN, Charset.defaultCharset()));
	}

	private static Set<String> _ignoredParameterKeys = new HashSet<String>(
		Arrays.asList("filePath", "syncFile"));

}