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

package com.liferay.jsonwebserviceclient;

import java.io.IOException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.login.CredentialException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class JSONWebServiceClientImpl implements JSONWebServiceClient {

	public void afterPropertiesSet() {
		PoolingClientConnectionManager poolingClientConnectionManager =
			new PoolingClientConnectionManager();

		poolingClientConnectionManager.setMaxTotal(20);

		_defaultHttpClient = new DefaultHttpClient(
			poolingClientConnectionManager);

		HttpParams httpParams = _defaultHttpClient.getParams();

		httpParams.setIntParameter(
			CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
	}

	public void destroy() {
		ClientConnectionManager clientConnectionManager =
			_defaultHttpClient.getConnectionManager();

		clientConnectionManager.shutdown();
	}

	@Override
	public String doGet(String url, Map<String, String> parameters)
		throws CredentialException, IOException {

		List<NameValuePair> nameValuePairs = toNameValuePairs(parameters);

		if (!nameValuePairs.isEmpty()) {
			String queryString = URLEncodedUtils.format(
				nameValuePairs, Charsets.UTF_8);

			url += "?" + queryString;
		}

		if (_logger.isDebugEnabled()) {
			_logger.debug(
				"Sending GET request to " + _login + "@" + _hostName + url);
		}

		HttpGet httpGet = new HttpGet(url);

		return execute(httpGet);
	}

	@Override
	public String doPost(String url, Map<String, String> parameters)
		throws CredentialException, IOException {

		if (_logger.isDebugEnabled()) {
			_logger.debug(
				"Sending POST request to " + _login + "@" + _hostName + url);
		}

		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> nameValuePairs = toNameValuePairs(parameters);

		HttpEntity httpEntity = new UrlEncodedFormEntity(
			nameValuePairs, Charsets.UTF_8);

		httpPost.setEntity(httpEntity);

		return execute(httpPost);
	}

	public String getHostName() {
		return _hostName;
	}

	public int getPort() {
		return _port;
	}

	public String getProtocol() {
		return _protocol;
	}

	@Override
	public void resetHttpClient() {
		destroy();

		afterPropertiesSet();
	}

	public void setHostName(String hostName) {
		_hostName = hostName;
	}

	public void setHostPort(int port) {
		_port = port;
	}

	@Override
	public void setLogin(String login) {
		_login = login;
	}

	@Override
	public void setPassword(String password) {
		_password = password;
	}

	public void setProtocol(String protocol) {
		_protocol = protocol;
	}

	protected String execute(HttpRequestBase httpRequestBase)
		throws CredentialException, IOException {

		HttpHost httpHost = new HttpHost(_hostName, _port, _protocol);

		try {
			HttpContext httpContext = null;

			if ((_login != null) && (_password != null)) {
				CredentialsProvider credentialsProvider =
					_defaultHttpClient.getCredentialsProvider();

				AuthScope authScope = new AuthScope(
					httpHost.getHostName(), httpHost.getPort());

				UsernamePasswordCredentials usernamePasswordCredentials =
					new UsernamePasswordCredentials(_login, _password);

				credentialsProvider.setCredentials(
					authScope, usernamePasswordCredentials);

				httpContext = new BasicHttpContext();

				AuthCache authCache = new BasicAuthCache();

				BasicScheme basicScheme = new BasicScheme();

				authCache.put(httpHost, basicScheme);

				httpContext.setAttribute(ClientContext.AUTH_CACHE, authCache);
			}

			HttpResponse httpResponse = null;

			if (httpContext == null) {
				httpResponse = _defaultHttpClient.execute(
					httpHost, httpRequestBase);
			}
			else {
				httpResponse = _defaultHttpClient.execute(
					httpHost, httpRequestBase, httpContext);
			}

			StatusLine statusLine = httpResponse.getStatusLine();

			if (statusLine.getStatusCode() ==
					HttpServletResponse.SC_NOT_FOUND) {

				if (_logger.isWarnEnabled()) {
					_logger.warn("Status code " + statusLine.getStatusCode());
				}

				return null;
			}
			else if (statusLine.getStatusCode() ==
						HttpServletResponse.SC_UNAUTHORIZED) {

				throw new CredentialException(
					"Not authorized to access JSON web service");
			}

			return EntityUtils.toString(
				httpResponse.getEntity(), Charsets.UTF_8);
		}
		finally {
			httpRequestBase.releaseConnection();
		}
	}

	protected List<NameValuePair> toNameValuePairs(
		Map<String, String> parameters) {

		List<NameValuePair> nameValuePairs = new LinkedList<NameValuePair>();

		Set<Map.Entry<String, String>> set = parameters.entrySet();

		Iterator<Map.Entry<String, String>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();

			String key = entry.getKey();

			String value = entry.getValue();

			if (value == null) {
				key = "-" + key;

				value = "";
			}

			NameValuePair nameValuePair = new BasicNameValuePair(key, value);

			nameValuePairs.add(nameValuePair);
		}

		return nameValuePairs;
	}

	private static Logger _logger = LoggerFactory.getLogger(
		JSONWebServiceClientImpl.class);

	private DefaultHttpClient _defaultHttpClient;
	private String _hostName;
	private String _login;
	private String _password;
	private int _port = 80;
	private String _protocol = "http";

}