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
import com.liferay.saml2.util.OpenSAMLUtil;
import com.liferay.saml2.util.PropsValues;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.opensaml.common.SAMLObject;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.binding.decoding.SAMLMessageDecoder;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.decoding.HTTPSOAP11Decoder;
import org.opensaml.saml2.binding.encoding.HTTPSOAP11Encoder;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.LogoutRequest;
import org.opensaml.saml2.core.LogoutResponse;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.ws.message.decoder.MessageDecodingException;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.http.HTTPInTransport;
import org.opensaml.ws.transport.http.HTTPOutTransport;
import org.opensaml.ws.transport.http.HttpServletRequestAdapter;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.validation.ValidationException;

public class SingleLogoutServlet extends HttpServlet {

	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HTTPInTransport in = new HttpServletRequestAdapter(request);
		HTTPOutTransport out = new HttpServletResponseAdapter(
				response, request.isSecure());

		System.out.println("URI: " + request.getRequestURI());

		Endpoint endpoint = OpenSAMLUtil.buildEndpoint(
				SAMLConstants.SAML2_SOAP11_BINDING_URI,
				HttpRequestUtil.getCurrentURL(request));

		SAMLMessageContext<SAMLObject, SAMLObject, NameID> context =
			new BasicSAMLMessageContext<SAMLObject, SAMLObject, NameID>();

		context.setInboundMessageTransport(in);
		context.setOutboundMessageTransport(out);
		context.setPeerEntityEndpoint(endpoint);

		try {
			Credential credential = CredentialUtil.getCredential(
				PropsValues.SAML_SP_CREDENTIAL_ALIAS,
				PropsValues.SAML_SP_CREDENTIAL_PASSWORD);

			context.setOutboundSAMLMessageSigningCredential(credential);
		}
		catch (Exception e1) {
			e1.printStackTrace();
			throw new ServletException(e1);
		}

		SAMLMessageDecoder decoder = getSAMLMessageDecoder(request);

		try {
			decoder.decode(context);
		}
		catch (MessageDecodingException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}

		SAMLObject samlMessage = context.getInboundSAMLMessage();

		try {
			System.out.println("Inbound SAML message:\n" + OpenSAMLUtil.marshallSAMLObject(samlMessage));
		}
		catch (MarshallingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (samlMessage instanceof LogoutRequest) {
				handleLogoutRequest(context);
			}
			else if (samlMessage instanceof LogoutResponse) {
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

		try {
			System.out.println("Outbound SAML message:\n" + OpenSAMLUtil.marshallSAMLObject(context.getOutboundSAMLMessage()));
		}
		catch (MarshallingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HTTPSOAP11Encoder encoder = new HTTPSOAP11Encoder();

		try {
			encoder.encode(context);
		} catch (MessageEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private SAMLMessageDecoder getSAMLMessageDecoder(HttpServletRequest request) {
		String requestURI = request.getRequestURI();

		if (requestURI.endsWith("/saml/SOAP/SLO")) {
			return new HTTPSOAP11Decoder();
		}

		return null;
	}

	private void handleLogoutRequest(
			SAMLMessageContext<SAMLObject, SAMLObject, NameID> context)
		throws Exception {

		LogoutRequest logoutRequest = (LogoutRequest)context.getInboundSAMLMessage();

		System.out.println("Logout request for: " + logoutRequest.getNameID().getValue());

		// Verify signature

		if (!logoutRequest.isSigned()) {
			throw new Exception(
				"SOAP binding for LogoutRequest requires SAML message to be signed");
		}

		Credential credential = CredentialUtil.getCredential(
			PropsValues.SAML_IDP_CREDENTIAL_ALIAS,
			PropsValues.SAML_IDP_CREDENTIAL_PASSWORD);

		try {
			OpenSAMLUtil.validateSignature(logoutRequest, credential);
		}
		catch (ValidationException e) {
			throw new Exception("Signature validaton failed: " + e.getMessage());
		}

		String statusCodeURI = null;

		SPSession spSession = sessionManager.findByNameID(logoutRequest.getNameID().getValue());

		if (spSession != null) {
			System.out.println("Logging out session " + spSession.getSessionId());

			sessionManager.invalidate(spSession);

			statusCodeURI = StatusCode.SUCCESS_URI;
		}
		else {
			statusCodeURI = StatusCode.UNKNOWN_PRINCIPAL_URI;
		}

		LogoutResponse logoutResponse = OpenSAMLUtil.buildLogoutResponse();

		String id = OpenSAMLUtil.generateIdentifier();
		DateTime issueInstant = new DateTime();
		Issuer issuer = OpenSAMLUtil.buildIssuer(
			PropsValues.SAML_SP_ENTITY_ID);
		StatusCode statusCode = OpenSAMLUtil.buildStatusCode(statusCodeURI);
		Status status = OpenSAMLUtil.buildStatus(statusCode);

		logoutResponse.setID(id);
		logoutResponse.setInResponseTo(logoutRequest.getID());
		logoutResponse.setIssueInstant(issueInstant);
		logoutResponse.setIssuer(issuer);
		logoutResponse.setStatus(status);

		OpenSAMLUtil.signObject(
			logoutResponse, context.getOuboundSAMLMessageSigningCredential());

		context.setOutboundSAMLMessage(logoutResponse);
	}

	private static SessionManager sessionManager = SessionManagerFactory.getSessionManager();
}
