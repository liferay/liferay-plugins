/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.saml2.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

import org.opensaml.xml.security.credential.Credential;

/**
 * @author Mika Koivisto
 */
public class CredentialUtil {

	public static Credential getCredential(String alias, String password)
		throws Exception {

		KeyStore keystore = getKeyStore();

		PrivateKey privateKey = (PrivateKey)keystore.getKey(
			alias, password.toCharArray());

		Certificate cert = keystore.getCertificate(alias);

		PublicKey publicKey = cert.getPublicKey();

		return OpenSAMLUtil.getCredential(publicKey, privateKey);
	}

	public static KeyStore getKeyStore() throws Exception {
		if (_keystore != null) {
			return _keystore;
		}

		String keystoreType = 
			PropsValues.SAML_CREDENTIAL_KEYSTORE_TYPE;

		String keystoreFile = 
			PropsValues.SAML_CREDENTIAL_KEYSTORE_FILE;

		String keystorePassword = 
			PropsValues.SAML_CREDENTIAL_KEYSTORE_PASSWORD;

		KeyStore keystore = KeyStore.getInstance(keystoreType);

		InputStream keystoreStream = null;

		if (keystoreFile.startsWith("classpath:")) {
			String path = keystoreFile.substring(10);

			keystoreStream =
				CredentialUtil.class.getResourceAsStream(path);
		}
		else {
			keystoreStream = new FileInputStream(keystoreFile);
		}

		keystore.load(
			keystoreStream,
			keystorePassword.toCharArray());

		_keystore = keystore;

		return _keystore;

	}

	private static KeyStore _keystore;
}
