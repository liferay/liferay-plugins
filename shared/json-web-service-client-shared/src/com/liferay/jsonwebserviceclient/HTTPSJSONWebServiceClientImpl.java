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

import java.security.KeyStore;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class HTTPSJSONWebServiceClientImpl extends JSONWebServiceClientImpl {

	@Override
	public void afterPropertiesSet() {
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager =
			new PoolingHttpClientConnectionManager(
				60000, TimeUnit.MILLISECONDS);

		poolingHttpClientConnectionManager.setMaxTotal(20);

		HttpClientBuilder httpClientBuilder = HttpClients.custom();

		httpClientBuilder.setConnectionManager(
			poolingHttpClientConnectionManager);

		if ((_login != null) && (_password != null)) {
			CredentialsProvider credentialsProvider =
				new BasicCredentialsProvider();

			UsernamePasswordCredentials usernamePasswordCredentials =
				new UsernamePasswordCredentials(_login, _password);

			AuthScope authScope = new AuthScope(_hostName, _port);

			credentialsProvider.setCredentials(
				authScope, usernamePasswordCredentials);

			httpClientBuilder.setDefaultCredentialsProvider(
				credentialsProvider);
		}
		else {
			if (_logger.isWarnEnabled()) {
				_logger.warn("Username and password are required.");
			}
		}

		try {
			httpClientBuilder.setSSLSocketFactory(
				getSSLConnectionSocketFactory());

			_closeableHttpClient = httpClientBuilder.build();

			if (_logger.isDebugEnabled()) {
				_logger.debug(
					"HTTPS Client configured for " + _protocol +
						"://" + _hostName);
			}
		}
		catch (Exception e) {
			_logger.error("SSL connection setup failed", e);
		}
	}

	@Override
	public void setKeyStore(KeyStore keyStore) {
		_keyStore = keyStore;
	}

	protected SSLConnectionSocketFactory getSSLConnectionSocketFactory()
		throws Exception {

		if (_sslConnectionSocketFactory != null) {
			return _sslConnectionSocketFactory;
		}

		SSLContextBuilder sslContextBuilder = SSLContexts.custom();

		sslContextBuilder.loadTrustMaterial(
			_keyStore, new TrustSelfSignedStrategy());

		SSLContext sslContext = sslContextBuilder.build();

		_sslConnectionSocketFactory = new SSLConnectionSocketFactory(
			sslContext, new String[] { "TLSv1" }, null,
			SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

		return _sslConnectionSocketFactory;
	}

	private static Logger _logger = LoggerFactory.getLogger(
		HTTPSJSONWebServiceClientImpl.class);

	private KeyStore _keyStore;
	private SSLConnectionSocketFactory _sslConnectionSocketFactory;

}