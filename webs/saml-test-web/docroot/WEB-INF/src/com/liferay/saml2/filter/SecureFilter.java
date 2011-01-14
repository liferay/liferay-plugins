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

package com.liferay.saml2.filter;

import com.liferay.saml2.session.SPSession;
import com.liferay.saml2.session.SessionManager;
import com.liferay.saml2.session.SessionManagerFactory;
import com.liferay.saml2.util.CredentialUtil;
import com.liferay.saml2.util.HttpRequestUtil;
import com.liferay.saml2.util.PropsUtil;
import com.liferay.saml2.util.OpenSAMLUtil;
import com.liferay.saml2.util.PropsValues;
import com.liferay.saml2.util.WebKeys;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.opensaml.common.SAMLObject;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.encoding.HTTPRedirectDeflateEncoder;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.security.credential.Credential;

public class SecureFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain)
		throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;

		HttpSession session = request.getSession(true);

		SPSession spSession = sessionManager.findBySessionId(session.getId());

		if (spSession != null && spSession.isValid()) {
			request.setAttribute(WebKeys.SP_SESSION, spSession);

			chain.doFilter(servletRequest, servletResponse);

			return;
		}
		else if (spSession != null && !spSession.isValid()) {
			sessionManager.invalidate(spSession);
		}

		String authnRequestId = OpenSAMLUtil.generateIdentifier();
		DateTime issueInstant = new DateTime();
		Issuer issuer = OpenSAMLUtil.buildIssuer(
			PropsValues.SAML_SP_ENTITY_ID);

		AuthnRequest authnRequest = OpenSAMLUtil.buildAuthnRequest();

		authnRequest.setDestination(PropsValues.SAML_IDP_SSO_URL);
		authnRequest.setID(authnRequestId);
		authnRequest.setIssueInstant(issueInstant);
		authnRequest.setIssuer(issuer);
		authnRequest.setAssertionConsumerServiceURL(
			HttpRequestUtil.getACSURL(request));
		authnRequest.setIsPassive(
			PropsValues.SAML_SP_AUTHN_REQUEST_PASSIVE);
		authnRequest.setForceAuthn(Boolean.FALSE);

		Endpoint endpoint = OpenSAMLUtil.buildEndpoint(
			SAMLConstants.SAML2_REDIRECT_BINDING_URI,
			PropsValues.SAML_IDP_SSO_URL);

		SAMLMessageContext<SAMLObject, AuthnRequest, NameID> context =
			new BasicSAMLMessageContext<SAMLObject, AuthnRequest, NameID>();

		context.setPeerEntityEndpoint(endpoint);
		context.setOutboundSAMLMessage(authnRequest);
		context.setOutboundMessageTransport(
			new HttpServletResponseAdapter(response, request.isSecure()));
		context.setRelayState(HttpRequestUtil.getCurrentURL(request));

		session.setAttribute(WebKeys.SAML_AUTHN_REQUEST_ID, authnRequestId);

		HTTPRedirectDeflateEncoder encoder = new HTTPRedirectDeflateEncoder();

		try {
			encoder.encode(context);
		}
		catch (MessageEncodingException me) {
			throw new ServletException(me);
		}
	}

	public void init(FilterConfig config) throws ServletException {
	}

	private static SessionManager sessionManager = SessionManagerFactory.getSessionManager();
}
