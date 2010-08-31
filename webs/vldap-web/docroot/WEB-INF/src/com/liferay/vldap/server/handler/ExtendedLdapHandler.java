/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.vldap.server.handler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.vldap.server.handler.util.LdapHandlerContext;
import com.liferay.vldap.server.handler.util.LdapSslContextFactory;
import com.liferay.vldap.util.VLDAPConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.directory.shared.ldap.message.internal.InternalExtendedRequest;
import org.apache.directory.shared.ldap.message.internal.InternalExtendedResponse;
import org.apache.directory.shared.ldap.message.internal.InternalRequest;
import org.apache.directory.shared.ldap.message.internal.InternalResponse;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.ssl.SslFilter;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class ExtendedLdapHandler extends BaseLdapHandler {

	public List<InternalResponse> messageReceived(
		InternalRequest internalRequest, IoSession ioSession,
		LdapHandlerContext ldapHandlerContext) {

		try {
			InternalExtendedRequest internalExtendedRequest =
				(InternalExtendedRequest)internalRequest;

			String oid = internalExtendedRequest.getOid();

			if (oid.equals(_START_TLS)) {
				handleStartTLS(internalExtendedRequest, ioSession);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	protected List<InternalResponse> handleStartTLS(
			InternalExtendedRequest internalExtendedRequest,
			IoSession ioSession)
		throws Exception {

		SSLContext sslContext = LdapSslContextFactory.getSSLContext(true);

		SslFilter sslFilter = new SslFilter(sslContext);

		IoFilterChain ioFilterChain = ioSession.getFilterChain();

		ioFilterChain.addFirst("sslFilter", sslFilter);

		InternalExtendedResponse internalExtendedResponse =
			(InternalExtendedResponse)getInternalResponse(
				internalExtendedRequest);

		internalExtendedResponse.setResponseName(_START_TLS);

		Map<Object, Object> sessionAttributes = new HashMap<Object, Object>();

		sessionAttributes.put(SslFilter.DISABLE_ENCRYPTION_ONCE, true);

		internalExtendedResponse.put(
			VLDAPConstants.SESSION_ATTRIBUTES, sessionAttributes);

		return toList(internalExtendedResponse);
	}

	private static final String _START_TLS = "1.3.6.1.4.1.1466.20037";

	private static Log _log = LogFactoryUtil.getLog(ExtendedLdapHandler.class);

}