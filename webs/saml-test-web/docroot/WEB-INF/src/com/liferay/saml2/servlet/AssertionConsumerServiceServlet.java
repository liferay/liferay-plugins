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

package com.liferay.saml2.servlet;

import com.liferay.saml2.session.SPSession;
import com.liferay.saml2.session.SessionManager;
import com.liferay.saml2.session.SessionManagerFactory;
import com.liferay.saml2.util.CredentialUtil;
import com.liferay.saml2.util.HttpRequestUtil;
import com.liferay.saml2.util.PropsUtil;
import com.liferay.saml2.util.OpenSAMLUtil;
import com.liferay.saml2.util.PropsValues;
import com.liferay.saml2.util.WebKeys;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SAMLObject;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.decoding.HTTPPostDecoder;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.security.SAMLSignatureProfileValidator;
import org.opensaml.ws.transport.http.HTTPInTransport;
import org.opensaml.ws.transport.http.HttpServletRequestAdapter;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.security.credential.BasicCredential;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.signature.SignatureValidator;
import org.opensaml.xml.validation.ValidationException;
import org.opensaml.xml.validation.ValidatorSuite;

public class AssertionConsumerServiceServlet extends HttpServlet {

	public void init(ServletConfig config) {
	}

	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		sendError(
			request, response,
			"Cannot parse the assertion. HTTP GET is not supported.");
	}

	public void doPost(
			HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		try {
			handleResponse(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();

			sendError(request, response, e.getMessage());
		}
	}

	protected void handleResponse(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		HTTPInTransport in = new HttpServletRequestAdapter(request);

		SAMLMessageContext<Response, SAMLObject, NameID> context =
			new BasicSAMLMessageContext<Response, SAMLObject, NameID>();

		Endpoint endpoint = OpenSAMLUtil.buildEndpoint(
			SAMLConstants.SAML2_POST_BINDING_URI,
			HttpRequestUtil.getACSURL(request));

		context.setPeerEntityEndpoint(endpoint);
		context.setInboundMessageTransport(in);

		HTTPPostDecoder decoder = new HTTPPostDecoder();

		decoder.decode(context);

		Response samlResponse = (Response)context.getInboundMessage();

		HttpSession session = request.getSession(true);

		// Verify signature

		if (!samlResponse.isSigned()) {
			throw new Exception(
				"HTTP-POST binding requires SAML message to be signed");
		}

		Credential credential = CredentialUtil.getCredential(
			PropsValues.SAML_IDP_CREDENTIAL_ALIAS,
			PropsValues.SAML_IDP_CREDENTIAL_PASSWORD);

		try {
			OpenSAMLUtil.validateSignature(samlResponse, credential);
		}
		catch (ValidationException e) {
			throw new Exception("Signature validaton failed: " + e.getMessage());
		}

		Status status = samlResponse.getStatus();

		if (!StatusCode.SUCCESS_URI.equals(status.getStatusCode().getValue())) {
			throw new Exception("Assertion failed: " +
				status.getStatusCode().getValue());
		}

		List<Assertion> assertions = samlResponse.getAssertions();

		if (assertions.isEmpty()) {
			throw new Exception("No assertions found in response from IdP");
		}
		else if (assertions.size() > 1) {
			throw new Exception(
				"More than 1 assertion found in response from IdP");
		}

		Assertion assertion = assertions.get(0);

		if (assertion.isSigned()) {
			OpenSAMLUtil.validateSignature(assertion, credential);
		}

		// Check in response to

		if (samlResponse.getInResponseTo() != null) {
			verifyInResponseTo(request, samlResponse.getInResponseTo());
		}

		String nameID = assertion.getSubject().getNameID().getValue();
		String nameIDFormat = assertion.getSubject().getNameID().getFormat();
		DateTime notOnOrAfter = assertion.getConditions().getNotOnOrAfter();
		DateTime notBefore = assertion.getConditions().getNotBefore();

		if (notBefore.isAfterNow() || notOnOrAfter.isBeforeNow()) {
			throw new Exception("Assertion not valid");
		}

		// Check for replay

		Map<String, DateTime> replayCache = getReplayCache(request);

		DateTime expiration = replayCache.get(assertion.getID());

		if (expiration != null && expiration.isAfter(notOnOrAfter.getMillis())) {
			throw new Exception("Replay attempt. Assertion ID: " + assertion.getID() + " already consumed.");
		}
		else {
			replayCache.put(assertion.getID(), assertion.getConditions().getNotOnOrAfter());
		}

		Map<String, List<XMLObject>> attributes = new HashMap<String, List<XMLObject>>();
		
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();

		for (AttributeStatement statement : attributeStatements) {
			for (Attribute attribute : statement.getAttributes()) {
				attributes.put(
					attribute.getName(), attribute.getAttributeValues());
			}
		}

		SPSession spSession = new SPSession();

		spSession.setSessionId(session.getId());
		spSession.setNameID(nameID);
		spSession.setNameIDFormat(nameIDFormat);
		spSession.setNotOnOrAfter(notOnOrAfter);
		spSession.addAttributes(attributes);

		sessionManager.registerSPSession(spSession);

		response.sendRedirect(context.getRelayState());
	}

	private void verifyInResponseTo(
			HttpServletRequest request, String inResponseTo) throws Exception {

		HttpSession session = request.getSession(true);

		String authnRequestId = (String)session.getAttribute(WebKeys.SAML_AUTHN_REQUEST_ID);

		if (authnRequestId == null || !inResponseTo.equals(authnRequestId)) {
			throw new Exception("Unsolicited response");
		}

		session.removeAttribute(WebKeys.SAML_AUTHN_REQUEST_ID);
	}

	private Map<String, DateTime> getReplayCache(HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		Map<String, DateTime> replayCache = (Map<String, DateTime>)session.getAttribute("REPLAY_CACHE");

		if (replayCache == null) {
			replayCache = new HashMap<String, DateTime>();
			session.setAttribute("REPLAY_CACHE", replayCache);
		}

		return replayCache;
	}

	protected void sendError(
			HttpServletRequest request, HttpServletResponse response,
			String msg)
		throws ServletException, IOException {

		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");

		request.setAttribute("error", msg);
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

	private static SessionManager sessionManager = SessionManagerFactory.getSessionManager();
}
