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

/**
 * @author Mika Koivisto
 */
public class PropsValues {

	public static final String GOOGLE_APPS_ISSUER = PropsUtil.getProperty(PropsKeys.GOOGLE_APPS_ISSUER);

	public static final String GOOGLE_APPS_ACS_URL = PropsUtil.getProperty(PropsKeys.GOOGLE_APPS_ACS_URL);

	public static final String GOOGLE_APPS_RELAY_STATE_URL = PropsUtil.getProperty(PropsKeys.GOOGLE_APPS_RELAY_STATE_URL);

	public static final String SAML_CREDENTIAL_KEYSTORE_FILE = PropsUtil.getProperty(PropsKeys.SAML_CREDENTIAL_KEYSTORE_FILE);

	public static final String SAML_CREDENTIAL_KEYSTORE_PASSWORD = PropsUtil.getProperty(PropsKeys.SAML_CREDENTIAL_KEYSTORE_PASSWORD);

	public static final String SAML_CREDENTIAL_KEYSTORE_TYPE = PropsUtil.getProperty(PropsKeys.SAML_CREDENTIAL_KEYSTORE_TYPE);

	public static final String SAML_IDP_CREDENTIAL_ALIAS = PropsUtil.getProperty(PropsKeys.SAML_IDP_CREDENTIAL_ALIAS);

	public static final String SAML_IDP_CREDENTIAL_PASSWORD = PropsUtil.getProperty(PropsKeys.SAML_IDP_CREDENTIAL_PASSWORD);

	public static final String SAML_IDP_ENTITY_ID = PropsUtil.getProperty(PropsKeys.SAML_IDP_ENTITY_ID);

	public static final String SAML_IDP_KEEPALIVE_URL = PropsUtil.getProperty(PropsKeys.SAML_IDP_KEEPALIVE_URL);

	public static final String SAML_IDP_SSO_URL = PropsUtil.getProperty(PropsKeys.SAML_IDP_SSO_URL);

	public static final Boolean SAML_SP_AUTHN_REQUEST_PASSIVE = Boolean.valueOf(PropsUtil.getProperty(PropsKeys.SAML_SP_AUTHN_REQUEST_PASSIVE));

	public static final String SAML_SP_CREDENTIAL_ALIAS = PropsUtil.getProperty(PropsKeys.SAML_SP_CREDENTIAL_ALIAS);

	public static final String SAML_SP_CREDENTIAL_PASSWORD = PropsUtil.getProperty(PropsKeys.SAML_SP_CREDENTIAL_PASSWORD);

	public static final String SAML_SP_ENTITY_ID = PropsUtil.getProperty(PropsKeys.SAML_SP_ENTITY_ID);

}
