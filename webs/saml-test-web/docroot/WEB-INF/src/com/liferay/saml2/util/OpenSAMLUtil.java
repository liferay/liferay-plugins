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

import java.io.StringWriter;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.opensaml.Configuration;
import org.opensaml.common.IdentifierGenerator;
import org.opensaml.common.SAMLObject;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.common.impl.SecureRandomIdentifierGenerator;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Audience;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.AuthnContext;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.LogoutRequest;
import org.opensaml.saml2.core.LogoutResponse;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.SubjectConfirmation;
import org.opensaml.saml2.core.SubjectConfirmationData;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.saml2.metadata.impl.EndpointImpl;
import org.opensaml.security.SAMLSignatureProfileValidator;
import org.opensaml.xml.XMLObjectBuilder;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.SecurityHelper;
import org.opensaml.xml.security.credential.BasicCredential;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureException;
import org.opensaml.xml.signature.SignatureValidator;
import org.opensaml.xml.signature.Signer;
import org.opensaml.xml.util.XMLHelper;
import org.w3c.dom.Element;

public class OpenSAMLUtil {

	static {
		XMLObjectBuilderFactory xmlObjectBuilderFactory = Configuration.getBuilderFactory();

		try {
			identifierGenerator = new SecureRandomIdentifierGenerator();
		}
		catch (NoSuchAlgorithmException nsae) {
			throw new RuntimeException(nsae);
		}

		audienceBuilder =
			(SAMLObjectBuilder<Audience>)
				xmlObjectBuilderFactory.getBuilder(Audience.DEFAULT_ELEMENT_NAME);
		audienceRestrictionBuilder =
			(SAMLObjectBuilder<AudienceRestriction>)
				xmlObjectBuilderFactory.getBuilder(AudienceRestriction.DEFAULT_ELEMENT_NAME);
		assertionBuilder =
			(SAMLObjectBuilder<Assertion>)
				xmlObjectBuilderFactory.getBuilder(Assertion.DEFAULT_ELEMENT_NAME);
		authnContextBuilder = 
			(SAMLObjectBuilder<AuthnContext>) 
				xmlObjectBuilderFactory.getBuilder(AuthnContext.DEFAULT_ELEMENT_NAME);
		authnContextClassRefBuilder = 
			(SAMLObjectBuilder<AuthnContextClassRef>) 
				xmlObjectBuilderFactory.getBuilder(AuthnContextClassRef.DEFAULT_ELEMENT_NAME);
		authnRequestBuilder = 
			(SAMLObjectBuilder<AuthnRequest>) 
				xmlObjectBuilderFactory.getBuilder(AuthnRequest.DEFAULT_ELEMENT_NAME);
		authnStatementBuilder = 
			(SAMLObjectBuilder<AuthnStatement>) 
				xmlObjectBuilderFactory.getBuilder(AuthnStatement.DEFAULT_ELEMENT_NAME);
		conditionsBuilder = 
			(SAMLObjectBuilder<Conditions>) 
				xmlObjectBuilderFactory.getBuilder(Conditions.DEFAULT_ELEMENT_NAME);
		issuerBuilder = 
			(SAMLObjectBuilder<Issuer>) 
				xmlObjectBuilderFactory.getBuilder(Issuer.DEFAULT_ELEMENT_NAME);
		logoutRequestBuilder = 
			(SAMLObjectBuilder<LogoutRequest>) 
			xmlObjectBuilderFactory.getBuilder(LogoutRequest.DEFAULT_ELEMENT_NAME);
		logoutResponseBuilder = 
			(SAMLObjectBuilder<LogoutResponse>) 
			xmlObjectBuilderFactory.getBuilder(LogoutResponse.DEFAULT_ELEMENT_NAME);
		nameIDBuilder = 
			(SAMLObjectBuilder<NameID>) 
				xmlObjectBuilderFactory.getBuilder(NameID.DEFAULT_ELEMENT_NAME);
		marshallerFactory = Configuration.getMarshallerFactory();
		responseBuilder = 
			(SAMLObjectBuilder<Response>) 
				xmlObjectBuilderFactory.getBuilder(Response.DEFAULT_ELEMENT_NAME);
		signatureBuilder = 
			xmlObjectBuilderFactory.getBuilder(Signature.DEFAULT_ELEMENT_NAME);
		statusBuilder = 
			(SAMLObjectBuilder<Status>) 
				xmlObjectBuilderFactory.getBuilder(Status.DEFAULT_ELEMENT_NAME);
		statusCodeBuilder = 
			(SAMLObjectBuilder<StatusCode>) 
				xmlObjectBuilderFactory.getBuilder(StatusCode.DEFAULT_ELEMENT_NAME);
		subjectBuilder = 
			(SAMLObjectBuilder<Subject>) 
				xmlObjectBuilderFactory.getBuilder(Subject.DEFAULT_ELEMENT_NAME);
		subjectConfirmationBuilder = 
			(SAMLObjectBuilder<SubjectConfirmation>) 
				xmlObjectBuilderFactory.getBuilder(SubjectConfirmation.DEFAULT_ELEMENT_NAME);
		subjectConfirmationDataBuilder = 
			(SAMLObjectBuilder<SubjectConfirmationData>) 
				xmlObjectBuilderFactory.getBuilder(SubjectConfirmationData.DEFAULT_ELEMENT_NAME);
	}

	public static Audience buildAudience() {
		return audienceBuilder.buildObject();
	}

	public static AudienceRestriction buildAudienceRestriction() {
		return audienceRestrictionBuilder.buildObject();
	}

	public static Assertion buildAssertion() {
		Assertion assertion = assertionBuilder.buildObject();

		assertion.setID(generateIdentifier());
		assertion.setVersion(SAMLVersion.VERSION_20);

		return assertion;
	}

	public static AuthnContext buildAuthnContext() {
		return authnContextBuilder.buildObject();
	}

	public static AuthnContextClassRef buildAuthnContextClassRef() {
		return authnContextClassRefBuilder.buildObject();
	}

	public static AuthnRequest buildAuthnRequest() {
		AuthnRequest authnRequest = authnRequestBuilder.buildObject();

		authnRequest.setID(generateIdentifier());
		authnRequest.setVersion(SAMLVersion.VERSION_20);

		return authnRequest;
	}

	public static AuthnStatement buildAuthnStatement() {
		return authnStatementBuilder.buildObject();
	}

	public static Conditions buildConditions() {
		return conditionsBuilder.buildObject();
	}

	public static Endpoint buildEndpoint(String binding, String location) {
		Endpoint endpoint = new MyEndpoint();

		endpoint.setBinding(binding);
		endpoint.setLocation(location);

		return endpoint;
	}

	public static Issuer buildIssuer(String value) {
		Issuer issuer = issuerBuilder.buildObject();

		issuer.setValue(value);

		return issuer;
	}

	public static LogoutRequest buildLogoutRequest() {
		return logoutRequestBuilder.buildObject();
	}

	public static LogoutResponse buildLogoutResponse() {
		return logoutResponseBuilder.buildObject();
	}

	public static NameID buildNameID(String value) {
		NameID nameID = nameIDBuilder.buildObject();

		nameID.setValue(value);

		return nameID;
	}

	public static Response buildResponse() {
		Response response = responseBuilder.buildObject();

		response.setID(generateIdentifier());
		response.setVersion(SAMLVersion.VERSION_20);

		return response;
	}

	public static Signature buildSignature(Credential credential) {
		Signature signature = 
			signatureBuilder.buildObject(Signature.DEFAULT_ELEMENT_NAME);

		signature.setSigningCredential(credential);

		return signature;
	}

	public static Status buildStatus(StatusCode statusCode) {
		Status status = statusBuilder.buildObject();

		status.setStatusCode(statusCode);

		return status;
	}

	public static StatusCode buildStatusCode(String value) {
		StatusCode statusCode = statusCodeBuilder.buildObject();

		statusCode.setValue(value);

		return statusCode;
	}

	public static Subject buildSubject(NameID nameID) {
		Subject subject = subjectBuilder.buildObject();

		subject.setNameID(nameID);

		return subject;
	}

	public static SubjectConfirmation buildSubjectConfirmation() {
		SubjectConfirmation subjectConfirmation = subjectConfirmationBuilder.buildObject();

		return subjectConfirmation;
	}

	public static SubjectConfirmationData buildSubjectConfirmationData() {
		SubjectConfirmationData subjectConfirmationData = subjectConfirmationDataBuilder.buildObject();

		return subjectConfirmationData;
	}

	public static String generateIdentifier() {
		return identifierGenerator.generateIdentifier(20);
	}

	public static Credential getCredential(PublicKey pub, PrivateKey priv) {
		BasicCredential credential = new BasicCredential();

		credential.setPrivateKey(priv);
		credential.setPublicKey(pub);
		return credential;
	}

	public static String marshallSAMLObject(SAMLObject samlObject)
		throws MarshallingException {

		Marshaller marshaller =
			marshallerFactory.getMarshaller(samlObject);

		StringWriter stringWriter = new StringWriter();

		Element dom = marshaller.marshall(samlObject);

		XMLHelper.writeNode(dom, stringWriter);

		return stringWriter.toString();
	}

	public static void signObject(
			SignableSAMLObject signableObject, Credential credential)
		throws MarshallingException, SignatureException, SecurityException {

		Signature signature = buildSignature(credential);

		SecurityHelper.prepareSignatureParams(
			signature, credential, null, null);

		signableObject.setSignature(signature);

		Marshaller marshaller = marshallerFactory.getMarshaller(signableObject);

		marshaller.marshall(signableObject);

		Signer.signObject(signature);
	}

	public static void validateSignature(
			SignableSAMLObject signableSAMLObject, Credential credential)
		throws Exception {

		SAMLSignatureProfileValidator sigProfileValidator = new SAMLSignatureProfileValidator();

		sigProfileValidator.validate(signableSAMLObject.getSignature());

		SignatureValidator sigValidator = new SignatureValidator(credential);

		sigValidator.validate(signableSAMLObject.getSignature());
	}

	public static class MyEndpoint extends EndpointImpl {

		protected MyEndpoint(String namespaceURI, String elementLocalName,
				String namespacePrefix) {
			super(namespaceURI, elementLocalName, namespacePrefix);
		}

		public MyEndpoint() {
			super(
				Endpoint.DEFAULT_ELEMENT_NAME.getNamespaceURI(),
				Endpoint.DEFAULT_ELEMENT_NAME.getLocalPart(),
				Endpoint.DEFAULT_ELEMENT_NAME.getPrefix());
		}
	}

	private static SAMLObjectBuilder<Audience> audienceBuilder;
	private static SAMLObjectBuilder<AudienceRestriction> audienceRestrictionBuilder;
	private static SAMLObjectBuilder<Assertion> assertionBuilder;
	private static SAMLObjectBuilder<AuthnContext> authnContextBuilder;
	private static SAMLObjectBuilder<AuthnContextClassRef> authnContextClassRefBuilder;
	private static SAMLObjectBuilder<AuthnRequest> authnRequestBuilder;
	private static SAMLObjectBuilder<AuthnStatement> authnStatementBuilder;
	private static SAMLObjectBuilder<Conditions> conditionsBuilder;
	private static IdentifierGenerator identifierGenerator;
	private static SAMLObjectBuilder<Issuer> issuerBuilder;
	private static SAMLObjectBuilder<LogoutRequest> logoutRequestBuilder;
	private static SAMLObjectBuilder<LogoutResponse> logoutResponseBuilder;
	private static MarshallerFactory marshallerFactory;
	private static SAMLObjectBuilder<NameID> nameIDBuilder;
	private static SAMLObjectBuilder<Response> responseBuilder;
	private static XMLObjectBuilder<Signature> signatureBuilder;
	private static SAMLObjectBuilder<Status> statusBuilder;
	private static SAMLObjectBuilder<StatusCode> statusCodeBuilder;
	private static SAMLObjectBuilder<Subject> subjectBuilder;
	private static SAMLObjectBuilder<SubjectConfirmation> subjectConfirmationBuilder;
	private static SAMLObjectBuilder<SubjectConfirmationData> subjectConfirmationDataBuilder;
}
