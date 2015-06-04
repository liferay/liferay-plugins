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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.search.solr.http.KeyStoreLoader;
import com.liferay.portal.search.solr.http.SSLSocketFactoryBuilder;

import java.security.KeyStore;

import java.util.Map;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author László Csontos
 * @author André de Oliveira
 */
@Component(
	immediate = true,
	property = {
		"key.store.password=secret", "key.store.path=classpath:/keystore.jks",
		"key.store.type=JKS", "trust.store.password=secret",
		"trust.store.path=classpath:/truststore.jks", "trust.store.type=JKS",
		"verify.server.certificate=true", "verify.server.name=true"
	},
	service = SSLSocketFactoryBuilder.class
)
public class SSLSocketFactoryBuilderImpl implements SSLSocketFactoryBuilder {

	@Override
	public SSLSocketFactory build() throws Exception {
		KeyStore keyStore = _keyStoreLoader.load(
			_keyStoreType, _keyStorePath, _keyStorePassword);

		if (keyStore == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Use system defaults because there is no custom key store");
			}

			return SSLSocketFactory.getSystemSocketFactory();
		}

		KeyStore trustKeyStore = null;

		TrustStrategy trustStrategy = null;

		if (_verifyServerCertificate) {
			trustKeyStore = _keyStoreLoader.load(
				_trustStoreType, _trustStorePath, _trustStorePassword);

			if (trustKeyStore == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Use system defaults because there is no custom " +
							"trust store");
				}

				return SSLSocketFactory.getSystemSocketFactory();
			}
		}
		else {
			trustStrategy = new TrustSelfSignedStrategy();
		}

		X509HostnameVerifier x509HostnameVerifier =
			SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;

		if (!_verifyServerHostname) {
			x509HostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
		}

		try {
			return new SSLSocketFactory(
				SSLSocketFactory.TLS, keyStore,
				String.valueOf(_keyStorePassword), trustKeyStore, null,
				trustStrategy, x509HostnameVerifier);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Use system defaults because the custom SSL socket " +
						"factory was not able to initialize",
					e);
			}

			return SSLSocketFactory.getSystemSocketFactory();
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_keyStorePassword = MapUtil.getString(
			properties, "key.store.password", StringPool.BLANK).toCharArray();
		_keyStorePath = MapUtil.getString(
			properties, "key.store.path", "classpath:/keystore.jks");
		_keyStoreType = MapUtil.getString(
			properties, "key.store.type", KeyStore.getDefaultType());
		_trustStorePassword = MapUtil.getString(
			properties, "trust.store.password", StringPool.BLANK).toCharArray();
		_trustStorePath = MapUtil.getString(
			properties, "trust.store.path", "classpath:/truststore.jks");
		_trustStoreType = MapUtil.getString(
			properties, "trust.store.type", KeyStore.getDefaultType());
		_verifyServerCertificate = MapUtil.getBoolean(
			properties, "verify.server.certificate", true);
		_verifyServerHostname = MapUtil.getBoolean(
			properties, "verify.server.hostname", true);
	}

	@Reference(unbind = "-")
	protected void setKeyStoreLoader(KeyStoreLoader keyStoreLoader) {
		_keyStoreLoader = keyStoreLoader;
	}

	private static Log _log = LogFactoryUtil.getLog(
		SSLSocketFactoryBuilderImpl.class);

	private KeyStoreLoader _keyStoreLoader;
	private char[] _keyStorePassword;
	private String _keyStorePath;
	private String _keyStoreType = KeyStore.getDefaultType();
	private char[] _trustStorePassword;
	private String _trustStorePath;
	private String _trustStoreType = KeyStore.getDefaultType();
	private boolean _verifyServerCertificate = true;
	private boolean _verifyServerHostname = true;

}