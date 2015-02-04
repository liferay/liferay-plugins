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

import org.apache.http.conn.ssl.SSLSocketFactory;

/**
 * @author László Csontos
 * @author André de Oliveira
 */
public class CertAuthPoolingDelegatingHttpClientFactory
	extends DelegatingHttpClientFactory {

	public void afterPropertiesSet() throws Exception {
		SSLSocketFactory sslSocketFactory = _sslSocketFactoryBuilder.build();

		HttpClientFactory httpClientFactory =
			new CertAuthPoolingHttpClientFactory(sslSocketFactory);

		httpClientFactory.setDefaultMaxConnectionsPerRoute(
			getDefaultMaxConnectionsPerRoute());
		httpClientFactory.setHttpRequestInterceptors(
			getHttpRequestInterceptors());
		httpClientFactory.setMaxTotalConnections(getMaxTotalConnections());

		setHttpClientFactory(httpClientFactory);
	}

	public void setKeyStorePassword(char[] keyStorePassword) {
		_sslSocketFactoryBuilder.setKeyStorePassword(keyStorePassword);
	}

	public void setKeyStorePath(String keyStoreLocation) {
		_sslSocketFactoryBuilder.setKeyStorePath(keyStoreLocation);
	}

	public void setKeyStoreType(String keyStoreType) {
		_sslSocketFactoryBuilder.setKeyStoreType(keyStoreType);
	}

	public void setSslSocketFactoryBuilder(
		SSLSocketFactoryBuilder sslSocketFactoryBuilder) {

		_sslSocketFactoryBuilder = sslSocketFactoryBuilder;
	}

	public void setTrustStorePassword(char[] trustStorePassword) {
		_sslSocketFactoryBuilder.setTrustStorePassword(trustStorePassword);
	}

	public void setTrustStorePath(String trustStorePath) {
		_sslSocketFactoryBuilder.setTrustStorePath(trustStorePath);
	}

	public void setTrustStoreType(String trustStoreType) {
		_sslSocketFactoryBuilder.setTrustStoreType(trustStoreType);
	}

	public void setVerifyServerCertificate(boolean verifyServerCertificate) {
		_sslSocketFactoryBuilder.setVerifyServerCertificate(
			verifyServerCertificate);
	}

	public void setVerifyServerHostname(boolean verifyServerHostname) {
		_sslSocketFactoryBuilder.setVerifyServerHostname(verifyServerHostname);
	}

	private SSLSocketFactoryBuilder _sslSocketFactoryBuilder;

}