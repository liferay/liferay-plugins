package com.liferay.saml2.servlet;

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
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.encoding.HTTPRedirectDeflateEncoder;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;

public class GenerateGoogleAppsAuthnRequestServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String authnRequestId = OpenSAMLUtil.generateIdentifier();
		DateTime issueInstant = new DateTime();
		Issuer issuer = OpenSAMLUtil.buildIssuer(
			PropsValues.GOOGLE_APPS_ISSUER);

		AuthnRequest authnRequest = OpenSAMLUtil.buildAuthnRequest();

		authnRequest.setID(authnRequestId);
		authnRequest.setIssueInstant(issueInstant);
		authnRequest.setIssuer(issuer);
		authnRequest.setAssertionConsumerServiceURL(
			PropsValues.GOOGLE_APPS_ACS_URL);
		authnRequest.setIsPassive(
			Boolean.valueOf(
				PropsValues.SAML_SP_AUTHN_REQUEST_PASSIVE));
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
		context.setRelayState(
			PropsValues.GOOGLE_APPS_RELAY_STATE_URL);

		HTTPRedirectDeflateEncoder encoder = new HTTPRedirectDeflateEncoder();

		try {
			encoder.encode(context);
		}
		catch (MessageEncodingException e) {
			throw new ServletException(e);
		}
	}
}
