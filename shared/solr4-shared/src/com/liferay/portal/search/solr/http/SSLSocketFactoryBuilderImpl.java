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

import java.security.KeyStore;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/**
 * @author László Csontos
 * @author André de Oliveira
 */
public class SSLSocketFactoryBuilderImpl implements SSLSocketFactoryBuilder {

	@Override
	public SSLSocketFactory build() throws Exception {
		KeyStore keyStore = _keyStoreLoader.load(
			_keyStoreType, _keyStorePath, _keyStorePassword);

		if (keyStore == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Custom keyStore has not been initialized, falling back " +
						"to system's defaults.");
			}

			return SSLSocketFactory.getSystemSocketFactory();
		}

		KeyStore trustStore = null;

		TrustStrategy trustStrategy = null;

		if (_verifyServerCertificate) {
			trustStore = _keyStoreLoader.load(
				_trustStoreType, _trustStorePath, _trustStorePassword);

			if (trustStore == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Custom trustStore has not been initialized, falling " +
							"back to system's defaults.");
				}

				return SSLSocketFactory.getSystemSocketFactory();
			}
		}
		else {
			trustStrategy = new TrustSelfSignedStrategy();
		}

		X509HostnameVerifier hostnameVerifier =
			SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;

		if (!_verifyServerHostname) {
			hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
		}

		try {
			return new SSLSocketFactory(
				_DEFAULT_ALGORITHM, keyStore, String.valueOf(_keyStorePassword),
				trustStore, null, trustStrategy, hostnameVerifier);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Custom SSLSocketFactory initialization has failed; " +
						"falling back to system's default.", e);
			}

			return SSLSocketFactory.getSystemSocketFactory();
		}
	}

	public void setKeyStoreLoader(KeyStoreLoader keyStoreLoader) {
		_keyStoreLoader = keyStoreLoader;
	}

	@Override
	public void setKeyStorePassword(char[] keyStorePassword) {
		_keyStorePassword = keyStorePassword;
	}

	@Override
	public void setKeyStorePath(String keyStorePath) {
		_keyStorePath = keyStorePath;
	}

	@Override
	public void setKeyStoreType(String keyStoreType) {
		_keyStoreType = keyStoreType;
	}

	@Override
	public void setTrustStorePassword(char[] trustStorePassword) {
		_trustStorePassword = trustStorePassword;
	}

	@Override
	public void setTrustStorePath(String trustStorePath) {
		_trustStorePath = trustStorePath;
	}

	@Override
	public void setTrustStoreType(String trustStoreType) {
		_trustStoreType = trustStoreType;
	}

	@Override
	public void setVerifyServerCertificate(boolean verifyServerCertificate) {
		_verifyServerCertificate = verifyServerCertificate;
	}

	@Override
	public void setVerifyServerHostname(boolean verifyServerHostname) {
		_verifyServerHostname = verifyServerHostname;
	}

	private static final String _DEFAULT_ALGORITHM = SSLSocketFactory.TLS;

	private static final String _DEFAULT_KEYSTORE_TYPE =
		KeyStore.getDefaultType();

	private static Log _log = LogFactoryUtil.getLog(
		SSLSocketFactoryBuilderImpl.class);

	private KeyStoreLoader _keyStoreLoader;
	private char[] _keyStorePassword;
	private String _keyStorePath;
	private String _keyStoreType = _DEFAULT_KEYSTORE_TYPE;
	private char[] _trustStorePassword;
	private String _trustStorePath;
	private String _trustStoreType = _DEFAULT_KEYSTORE_TYPE;
	private boolean _verifyServerCertificate = true;
	private boolean _verifyServerHostname = true;

}