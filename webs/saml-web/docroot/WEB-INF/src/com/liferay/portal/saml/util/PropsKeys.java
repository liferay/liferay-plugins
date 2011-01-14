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

/**
 * @author Brian Wing Shun Chan
 */
public interface PropsKeys {


	public static final String SAML_CREDENTIAL_KEYSTORE_FILE = "saml.credential.keystore.file";

	public static final String SAML_CREDENTIAL_KEYSTORE_PASSWORD = "saml.credential.keystore.password";

	public static final String SAML_CREDENTIAL_KEYSTORE_TYPE = "saml.credential.keystore.type";

	public static final String SAML_IDP_CREDENTIAL_ALIAS = "saml.idp.credential.alias";

	public static final String SAML_IDP_CREDENTIAL_PASSWORD = "saml.idp.credential.password";

	public static final String SAML_IDP_ENABLED = "saml.idp.enabled";

	public static final String SAML_IDP_ENTITY_ID = "saml.idp.entity.id";

	public static final String SAML_IDP_SSO_ENABLED = "saml.idp.sso.enabled";

	public static final String SAML_IDP_SSO_SESSION_MAXIMUM_AGE = "saml.idp.sso.session.maximum.age";

	public static final String SAML_IDP_SSO_SESSION_TIMEOUT = "saml.idp.sso.session.timeout";

	public static final String SAML_SP_METADATA_AUDIENCE = "saml.sp.metadata.audience";

	public static final String SAML_SP_METADATA_ACS_URL = "saml.sp.metadata.acs.url";

	public static final String SAML_SP_METADATA_ASSERTION_LIFETIME = "saml.sp.metadata.assertion.lifetime";

	public static final String SAML_SP_METADATA_ASSERTION_SIGN = "saml.sp.metadata.assertion.sign";

	public static final String SAML_SP_METADATA_ATTRIBUTES_INCLUDE = "saml.sp.metadata.attributes.include";

	public static final String SAML_SP_METADATA_ATTRIBUTES_RESOLVER = "saml.sp.metadata.attributes.resolver";

	public static final String SAML_SP_METADATA_ATTRIBUTES_RESOLVER_ATTRIBUTE_NAMES = "saml.sp.metadata.attributes.resolver.attribute.names";

	public static final String SAML_SP_METADATA_CREDENTIAL_ALIAS = "saml.sp.metadata.credential.alias";

	public static final String SAML_SP_METADATA_CREDENTIAL_CERTIFICATE_ALIAS = "saml.sp.metadata.credential.certificate.alias";

	public static final String SAML_SP_METADATA_CREDENTIAL_PASSWORD = "saml.sp.metadata.credential.password";

	public static final String SAML_SP_METADATA_CLOCK_SKEW = "saml.sp.metadata.clock.skew";

	public static final String SAML_SP_METADATA_KEEPALIVE_URL = "saml.sp.metadata.keepalive.url";

	public static final String SAML_SP_METADATA_NAMEID_RESOLVER = "saml.sp.metadata.nameid.resolver";

	public static final String SAML_SP_METADATA_NAMEID_RESOLVER_NAMEID_FORMAT = "saml.sp.metadata.nameid.resolver.nameid.format";

	public static final String SAML_SP_METADATA_SLO_URL = "saml.sp.metadata.slo.url";

}