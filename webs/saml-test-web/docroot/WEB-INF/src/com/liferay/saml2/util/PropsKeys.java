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
public interface PropsKeys {

	public static final String GOOGLE_APPS_ISSUER = "google.apps.issuer";

	public static final String GOOGLE_APPS_ACS_URL = "google.apps.acs.url";

	public static final String GOOGLE_APPS_RELAY_STATE_URL = "google.apps.relay.state.url";

	public static final String SAML_CREDENTIAL_KEYSTORE_FILE = "saml.credential.keystore.file";

	public static final String SAML_CREDENTIAL_KEYSTORE_PASSWORD = "saml.credential.keystore.password";

	public static final String SAML_CREDENTIAL_KEYSTORE_TYPE = "saml.credential.keystore.type";

	public static final String SAML_IDP_CREDENTIAL_ALIAS = "saml.idp.credential.alias";

	public static final String SAML_IDP_CREDENTIAL_PASSWORD = "saml.idp.credential.password";

	public static final String SAML_IDP_ENTITY_ID = "saml.idp.entity.id";

	public static final String SAML_IDP_KEEPALIVE_URL = "saml.idp.keepalive.url";

	public static final String SAML_IDP_SSO_URL = "saml.idp.sso.url";

	public static final String SAML_SP_AUTHN_REQUEST_PASSIVE = "saml.sp.authn.request.passive";

	public static final String SAML_SP_CREDENTIAL_ALIAS = "saml.sp.credential.alias";

	public static final String SAML_SP_CREDENTIAL_PASSWORD = "saml.sp.credential.password";

	public static final String SAML_SP_ENTITY_ID = "saml.sp.entity.id";

}
