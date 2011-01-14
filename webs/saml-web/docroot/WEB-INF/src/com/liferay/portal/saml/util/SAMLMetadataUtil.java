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

package com.liferay.portal.saml.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.saml.opensaml.OpenSAMLUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;

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
public class SAMLMetadataUtil {

	public static String getIdPEntityId(long companyId) throws SystemException {
		return _getProperty(companyId, PropsKeys.SAML_IDP_ENTITY_ID);
	}

	public static String getAudience(long companyId, String entityId)
		throws SystemException {

		String key =
			PropsKeys.SAML_SP_METADATA_AUDIENCE + "[" + entityId + "]";

		return _getProperty(companyId, key);
	}

	public static String getAcsURL(long companyId, String entityId)
		throws SystemException {

		return _getProperty(
				companyId,
				PropsKeys.SAML_SP_METADATA_ACS_URL + "[" + entityId + "]");
	}

	public static int getAssertionLifetime(long companyId, String entityId)
		throws SystemException {

		return GetterUtil.getInteger(
			_getProperty(
				companyId,
				PropsKeys.SAML_SP_METADATA_ASSERTION_LIFETIME + "[" + entityId + "]"));
	}

	public static int getClockSkew(long companyId, String entityId)
		throws SystemException {

		return GetterUtil.getInteger(
			_getProperty(companyId,
				PropsKeys.SAML_SP_METADATA_CLOCK_SKEW + "[" + entityId + "]"));
	}

	public static Credential getSigningCredential(
			long companyId, String entityId)
		throws SystemException {

		String keystoreKeyAlias = _getProperty(
				companyId,
				PropsKeys.SAML_SP_METADATA_CREDENTIAL_ALIAS + "[" + entityId + "]");
		String keystoreKeyPassword = _getProperty(
				companyId,
				PropsKeys.SAML_SP_METADATA_CREDENTIAL_PASSWORD + "[" + entityId + "]");

		if (Validator.isNull(keystoreKeyAlias)) {
			keystoreKeyAlias = _getProperty(
				companyId, PropsKeys.SAML_IDP_CREDENTIAL_ALIAS);
			keystoreKeyPassword = _getProperty(
				companyId, PropsKeys.SAML_IDP_CREDENTIAL_PASSWORD);
		}

		if (Validator.isNull(keystoreKeyAlias)) {
			return null;
		}

		PublicKey publicKey = null;
		PrivateKey privateKey = null;

		try {
			KeyStore keystore = getKeyStore(companyId);

			privateKey = (PrivateKey)keystore.getKey(
					keystoreKeyAlias, keystoreKeyPassword.toCharArray());

			Certificate cert = keystore.getCertificate(keystoreKeyAlias);

			publicKey = cert.getPublicKey();

		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		return OpenSAMLUtil.getCredential(publicKey, privateKey);
	}

	public static Credential getValidationCredential(
			long companyId, String entityId) throws SystemException {

		String keystoreKeyAlias = _getProperty(
			companyId,
			PropsKeys.SAML_SP_METADATA_CREDENTIAL_CERTIFICATE_ALIAS + "[" + entityId + "]");

		if (Validator.isNull(keystoreKeyAlias)) {
			return null;
		}

		PublicKey publicKey = null;
		PrivateKey privateKey = null;

		try {
			KeyStore keystore = getKeyStore(companyId);

			Certificate cert = keystore.getCertificate(keystoreKeyAlias);

			publicKey = cert.getPublicKey();

		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		return OpenSAMLUtil.getCredential(publicKey, privateKey);
	}

	public static String getKeepaliveURL(long companyId, String entityId)
		throws SystemException {

		String keepaliveURL =
			_getProperty(companyId, PropsKeys.SAML_SP_METADATA_KEEPALIVE_URL +
				"[" + entityId + "]");

		return keepaliveURL;
	}

	public static KeyStore getKeyStore(long companyId) throws SystemException {
		String keystoreType = _getProperty(
				companyId, PropsKeys.SAML_CREDENTIAL_KEYSTORE_TYPE);

		String keystoreFile = _getProperty(
				companyId, PropsKeys.SAML_CREDENTIAL_KEYSTORE_FILE);

		String keystorePassword = _getProperty(
				companyId, PropsKeys.SAML_CREDENTIAL_KEYSTORE_PASSWORD);

		try {
			KeyStore keystore = KeyStore.getInstance(keystoreType);

			InputStream keystoreStream = null;

			if (keystoreFile.startsWith("classpath:")) {
				String path = keystoreFile.substring(10);

				keystoreStream =
					SAMLMetadataUtil.class.getResourceAsStream(path);
			}
			else {
				keystoreStream = new FileInputStream(keystoreFile);
			}

			keystore.load(
					keystoreStream,
					keystorePassword.toCharArray());

			return keystore;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static String getSLOURL(long companyId, String entityId)
		throws SystemException {

		String key =
			PropsKeys.SAML_SP_METADATA_SLO_URL + "[" + entityId + "]";

		return _getProperty(companyId, key);
	}

	public static boolean isAttributesIncludeEnabled(
			long companyId, String entityId) throws SystemException{

		String key =
			PropsKeys.SAML_SP_METADATA_ATTRIBUTES_INCLUDE +
			"[" + entityId + "]";

		return GetterUtil.getBoolean(
				_getProperty(companyId, key));
	}

	private static String _getProperty(long companyId, String key)
		throws SystemException {

		try {
			return PrefsPropsUtil.getString(companyId, key);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}
}
