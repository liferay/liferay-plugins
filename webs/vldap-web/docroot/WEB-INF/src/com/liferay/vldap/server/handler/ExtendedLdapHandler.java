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

package com.liferay.vldap.server.handler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.vldap.server.handler.util.LdapHandlerContext;
import com.liferay.vldap.server.handler.util.LdapSslContextFactory;
import com.liferay.vldap.util.OIDConstants;
import com.liferay.vldap.util.VLDAPConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.directory.shared.ldap.model.message.ExtendedRequest;
import org.apache.directory.shared.ldap.model.message.ExtendedResponse;
import org.apache.directory.shared.ldap.model.message.Request;
import org.apache.directory.shared.ldap.model.message.Response;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.ssl.SslFilter;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class ExtendedLdapHandler extends BaseLdapHandler {

	public List<Response> messageReceived(
		Request request, IoSession ioSession,
		LdapHandlerContext ldapHandlerContext) {

		try {
			ExtendedRequest<ExtendedResponse> extendedRequest =
				(ExtendedRequest<ExtendedResponse>)request;

			String oid = extendedRequest.getRequestName();

			if (oid.equals(OIDConstants.START_TLS)) {
				return handleStartTLS(extendedRequest, ioSession);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	protected List<Response> handleStartTLS(
			ExtendedRequest<ExtendedResponse> extendedRequest,
			IoSession ioSession)
		throws Exception {

		SSLContext sslContext = LdapSslContextFactory.getSSLContext(true);

		SslFilter sslFilter = new SslFilter(sslContext);

		IoFilterChain ioFilterChain = ioSession.getFilterChain();

		ioFilterChain.addFirst("sslFilter", sslFilter);

		ExtendedResponse extendedResponse = getResponse(extendedRequest);

		extendedResponse.setResponseName(OIDConstants.START_TLS);

		Map<Object, Object> sessionAttributes = new HashMap<Object, Object>();

		sessionAttributes.put(SslFilter.DISABLE_ENCRYPTION_ONCE, true);

		extendedResponse.put(
			VLDAPConstants.SESSION_ATTRIBUTES, sessionAttributes);

		return toList(extendedResponse);
	}

	private static Log _log = LogFactoryUtil.getLog(ExtendedLdapHandler.class);

}