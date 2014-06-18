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

package com.liferay.sync.engine.session;

import com.btr.proxy.search.ProxySearch;

import com.liferay.sync.engine.documentlibrary.handler.Handler;

import java.net.ProxySelector;
import java.net.URL;

import java.nio.charset.Charset;
import java.nio.file.Path;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;

/**
 * @author Shinn Lok
 * @author Dennis Ju
 */
public class Session {

	public Session(
		URL url, String login, String password, boolean trustSelfSigned,
		int maxConnections) {

		_executorService = Executors.newFixedThreadPool(maxConnections);

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		CredentialsProvider credentialsProvider =
			new BasicCredentialsProvider();

		credentialsProvider.setCredentials(
			new AuthScope(url.getHost(), url.getPort()),
			new UsernamePasswordCredentials(login, password));

		httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);

		httpClientBuilder.setMaxConnPerRoute(maxConnections);
		httpClientBuilder.setMaxConnTotal(maxConnections);
		httpClientBuilder.setRoutePlanner(_getHttpRoutePlanner());

		if (trustSelfSigned) {
			try {
				SSLContextBuilder sslContextBuilder = new SSLContextBuilder();

				sslContextBuilder.loadTrustMaterial(
					null, new TrustSelfSignedStrategy());

				SSLConnectionSocketFactory sslConnectionSocketFactory =
					new SSLConnectionSocketFactory(
						sslContextBuilder.build(),
						SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

				httpClientBuilder.setSSLSocketFactory(
					sslConnectionSocketFactory);
			}
			catch (Exception e) {
				_logger.error(e.getMessage(), e);
			}
		}

		_httpClient = httpClientBuilder.build();

		_httpHost = new HttpHost(
			url.getHost(), url.getPort(), url.getProtocol());
	}

	public HttpResponse execute(HttpRequest httpRequest) throws Exception {
		return execute(httpRequest, _getBasicHttpContext());
	}

	public <T> T execute(HttpRequest httpRequest, Handler<? extends T> handler)
		throws Exception {

		return execute(httpRequest, handler, _getBasicHttpContext());
	}

	public <T> T execute(
			HttpRequest httpRequest, Handler<? extends T> handler,
			HttpContext httpContext)
		throws Exception {

		return _httpClient.execute(
			_httpHost, httpRequest, handler, httpContext);
	}

	public HttpResponse execute(
			HttpRequest httpRequest, HttpContext httpContext)
		throws Exception {

		return _httpClient.execute(_httpHost, httpRequest, httpContext);
	}

	public void executeAsynchronousGet(
			final String urlPath, final Handler<Void> handler)
		throws Exception {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					executeGet(urlPath, handler);
				}
				catch (Exception e) {
					handler.handleException(e);
				}
			}

		};

		_executorService.execute(runnable);
	}

	public void executeAsynchronousPost(
			final String urlPath, final Map<String, Object> parameters,
			final Handler<Void> handler)
		throws Exception {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					executePost(urlPath, parameters, handler);
				}
				catch (Exception e) {
					handler.handleException(e);
				}
			}

		};

		_executorService.execute(runnable);
	}

	public HttpResponse executeGet(String urlPath) throws Exception {
		HttpGet httpGet = new HttpGet(urlPath);

		return _httpClient.execute(_httpHost, httpGet, _getBasicHttpContext());
	}

	public <T> T executeGet(String urlPath, Handler<? extends T> handler)
		throws Exception {

		HttpGet httpGet = new HttpGet(urlPath);

		return _httpClient.execute(
			_httpHost, httpGet, handler, _getBasicHttpContext());
	}

	public HttpResponse executePost(
			String urlPath, Map<String, Object> parameters)
		throws Exception {

		HttpPost httpPost = new HttpPost(urlPath);

		_buildHttpPostBody(httpPost, parameters);

		return _httpClient.execute(_httpHost, httpPost, _getBasicHttpContext());
	}

	public <T> T executePost(
			String urlPath, Map<String, Object> parameters,
			Handler<? extends T> handler)
		throws Exception {

		HttpPost httpPost = new HttpPost(urlPath);

		_buildHttpPostBody(httpPost, parameters);

		return _httpClient.execute(
			_httpHost, httpPost, handler, _getBasicHttpContext());
	}

	private void _buildHttpPostBody(
			HttpPost httpPost, Map<String, Object> parameters)
		throws Exception {

		Path deltaFilePath = (Path)parameters.get("deltaFilePath");
		Path filePath = (Path)parameters.get("filePath");

		MultipartEntityBuilder multipartEntityBuilder =
			_getMultipartEntityBuilder(parameters);

		if (deltaFilePath != null) {
			multipartEntityBuilder.addPart(
				"deltaFile",
				_getFileBody(
					deltaFilePath, (String)parameters.get("mimeType"),
					(String)parameters.get("title")));
		}
		else if (filePath != null) {
			multipartEntityBuilder.addPart(
				"file",
				_getFileBody(
					filePath, (String)parameters.get("mimeType"),
					(String)parameters.get("title")));
		}

		httpPost.setEntity(multipartEntityBuilder.build());
	}

	private BasicAuthCache _getBasicAuthCache() {
		BasicAuthCache basicAuthCache = new BasicAuthCache();

		BasicScheme basicScheme = new BasicScheme();

		basicAuthCache.put(_httpHost, basicScheme);

		return basicAuthCache;
	}

	private BasicHttpContext _getBasicHttpContext() {
		BasicHttpContext basicHttpContext = new BasicHttpContext();

		basicHttpContext.setAttribute(
			HttpClientContext.AUTH_CACHE, _getBasicAuthCache());

		return basicHttpContext;
	}

	private ContentBody _getFileBody(
			Path filePath, String mimeType, String fileName)
		throws Exception {

		return new FileBody(
			filePath.toFile(), ContentType.create(mimeType), fileName);
	}

	private HttpRoutePlanner _getHttpRoutePlanner() {
		if (_httpRoutePlanner != null) {
			return _httpRoutePlanner;
		}

		ProxySearch proxySearch = ProxySearch.getDefaultProxySearch();

		ProxySelector proxySelector = proxySearch.getProxySelector();

		if (proxySelector == null) {
			proxySelector = ProxySelector.getDefault();
		}

		_httpRoutePlanner = new SystemDefaultRoutePlanner(proxySelector);

		return _httpRoutePlanner;
	}

	private MultipartEntityBuilder _getMultipartEntityBuilder(
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

	private StringBody _getStringBody(Object value) {
		return new StringBody(
			String.valueOf(value),
			ContentType.create(
				MediaType.TEXT_PLAIN_VALUE, Charset.defaultCharset()));
	}

	private static Logger _logger = LoggerFactory.getLogger(Session.class);

	private static HttpRoutePlanner _httpRoutePlanner;

	private ExecutorService _executorService;
	private HttpClient _httpClient;
	private HttpHost _httpHost;
	private Set<String> _ignoredParameterKeys = new HashSet<String>(
		Arrays.asList("filePath", "syncFile", "syncSite"));

}