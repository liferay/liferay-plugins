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

package com.liferay.portal.saml.opensaml;

import com.liferay.portal.saml.SAMLIdentifierGenerator;

import java.io.StringWriter;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.IdentifierGenerator;
import org.opensaml.common.SAMLObject;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.binding.decoding.HTTPRedirectDeflateDecoder;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.AttributeValue;
import org.opensaml.saml2.core.Audience;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.AuthnContext;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.LogoutRequest;
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
import org.opensaml.ws.message.decoder.MessageDecodingException;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.XMLObjectBuilder;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.schema.XSString;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.SecurityHelper;
import org.opensaml.xml.security.credential.BasicCredential;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureException;
import org.opensaml.xml.signature.SignatureValidator;
import org.opensaml.xml.signature.Signer;
import org.opensaml.xml.util.Base64;
import org.opensaml.xml.util.XMLHelper;
import org.opensaml.xml.validation.ValidationException;
import org.w3c.dom.Element;

/**
 * @author Mika Koivisto
 */
public class OpenSAMLUtil {

	public static Attribute buildAttribute() {
		return _instance._buildAttribute();
	}

	public static AttributeStatement buildAttributeStatement() {
		return _instance._buildAttributeStatement();
	}

	public static XMLObject buildAttributeValue(String value) {
		return _instance._buildAttributeValue(value);
	}

	public static Audience buildAudience() {
		return _instance._buildAudience();
	}

	public static AudienceRestriction buildAudienceRestriction() {
		return _instance._buildAudienceRestriction();
	}

	public static Assertion buildAssertion() {
		return _instance._buildAssertion();
	}

	public static AuthnContext buildAuthnContext() {
		return _instance._buildAuthnContext();
	}

	public static AuthnContextClassRef buildAuthnContextClassRef() {
		return _instance._buildAuthnContextClassRef();
	}

	public static AuthnRequest buildAuthnRequest() {
		return _instance._buildAuthnRequest();
	}

	public static AuthnStatement buildAuthnStatement() {
		return _instance._buildAuthnStatement();
	}

	public static Conditions buildConditions() {
		return _instance._buildConditions();
	}

	public static Endpoint buildEndpoint(String binding, String location) {
		return _instance._buildEndpoint(binding, location);
	}

	public static Issuer buildIssuer(String value) {
		return _instance._buildIssuer(value);
	}

	public static LogoutRequest buildLogoutRequest() {
		return _instance._buildLogoutRequest();
	}

	public static NameID buildNameID(String value) {
		return _instance._buildNameID(value);
	}

	public static NameID buildNameID(String value, String format) {
		return _instance._buildNameID(value, format);
	}

	public static Response buildResponse() {
		return _instance._buildResponse();
	}

	public static Signature buildSignature(Credential credential) {
		return _instance._buildSignature(credential);
	}

	public static Status buildStatus(StatusCode statusCode) {
		return _instance._buildStatus(statusCode);
	}

	public static StatusCode buildStatusCode(String value) {
		return _instance._buildStatusCode(value);
	}

	public static Subject buildSubject(NameID nameID) {
		return _instance._buildSubject(nameID);
	}

	public static SubjectConfirmation buildSubjectConfirmation() {
		return _instance._buildSubjectConfirmation();
	}

	public static SubjectConfirmationData buildSubjectConfirmationData() {
		return _instance._buildSubjectConfirmationData();
	}

	public static void decodeHTTPRedirectRequest(
			SAMLMessageContext<
				? extends SAMLObject, ? extends SAMLObject,
				? extends SAMLObject> context)
		throws MessageDecodingException, SecurityException {

		_instance._decodeHTTPRedirectRequest(context);
	}

	public static String encodeSAMLObject(SAMLObject object)
		throws MarshallingException {

		return _instance._encodeSAMLObject(object);
	}

	public static String generateIdentifier() {
		return _instance._generateIdentifier();
	}

	public static Credential getCredential(PublicKey pub, PrivateKey priv) {
		BasicCredential credential = new BasicCredential();

		credential.setPrivateKey(priv);
		credential.setPublicKey(pub);
		return credential;
	}

	public static String marshallSAMLObject(SAMLObject samlObject)
		throws MarshallingException {

		return _instance._marshallSAMLObject(samlObject);
	}

	public static void signObject(
			SignableSAMLObject signableObject, Credential credential)
		throws MarshallingException, SignatureException, SecurityException {

		_instance._signObject(signableObject, credential);
	}

	public static void validateSignature(
			SignableSAMLObject signableSAMLObject, Credential credential)
		throws ValidationException {

		_instance._validateSignature(signableSAMLObject, credential);
	}

	private OpenSAMLUtil() {
		try {
			DefaultBootstrap.bootstrap();
		}
		catch (ConfigurationException ce) {
			throw new RuntimeException(ce);
		}

		xmlObjectBuilderFactory = Configuration.getBuilderFactory();

		identifierGenerator = new SAMLIdentifierGenerator();

		attributeBuilder =
			(SAMLObjectBuilder<Attribute>)
			xmlObjectBuilderFactory.getBuilder(Attribute.DEFAULT_ELEMENT_NAME);
		attributeStatementBuilder =
			(SAMLObjectBuilder<AttributeStatement>)
			xmlObjectBuilderFactory.getBuilder(AttributeStatement.DEFAULT_ELEMENT_NAME);
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
		httpRedirectDeflateDecoder = new HTTPRedirectDeflateDecoder();
		issuerBuilder = 
			(SAMLObjectBuilder<Issuer>) 
				xmlObjectBuilderFactory.getBuilder(Issuer.DEFAULT_ELEMENT_NAME);
		logoutRequestBuilder = 
			(SAMLObjectBuilder<LogoutRequest>) 
			xmlObjectBuilderFactory.getBuilder(LogoutRequest.DEFAULT_ELEMENT_NAME);
		nameIDBuilder = 
			(SAMLObjectBuilder<NameID>) 
				xmlObjectBuilderFactory.getBuilder(NameID.DEFAULT_ELEMENT_NAME);
		marshallerFactory = Configuration.getMarshallerFactory();
		responseBuilder = 
			(SAMLObjectBuilder<Response>) 
				xmlObjectBuilderFactory.getBuilder(Response.DEFAULT_ELEMENT_NAME);
		signatureBuilder = 
			xmlObjectBuilderFactory.getBuilder(Signature.DEFAULT_ELEMENT_NAME);
		signatureProfileValidator = new SAMLSignatureProfileValidator();
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
		xsStringBuilder = 
			(XMLObjectBuilder<XSString>)
				xmlObjectBuilderFactory.getBuilder(XSString.TYPE_NAME);
	}

	private Attribute _buildAttribute() {
		return attributeBuilder.buildObject();
	}

	private AttributeStatement _buildAttributeStatement() {
		return attributeStatementBuilder.buildObject();
	}

	private XMLObject _buildAttributeValue(String value) {
		XSString xsValues = xsStringBuilder.buildObject(
				AttributeValue.DEFAULT_ELEMENT_NAME, XSString.TYPE_NAME);
		xsValues.setValue(value);
		return xsValues;
	}

	private Audience _buildAudience() {
		return audienceBuilder.buildObject();
	}

	private AudienceRestriction _buildAudienceRestriction() {
		return audienceRestrictionBuilder.buildObject();
	}

	private Assertion _buildAssertion() {
		Assertion assertion = assertionBuilder.buildObject();

		assertion.setID(generateIdentifier());
		assertion.setVersion(SAMLVersion.VERSION_20);

		return assertion;
	}

	private AuthnContext _buildAuthnContext() {
		return authnContextBuilder.buildObject();
	}

	private AuthnContextClassRef _buildAuthnContextClassRef() {
		return authnContextClassRefBuilder.buildObject();
	}

	private AuthnRequest _buildAuthnRequest() {
		AuthnRequest authnRequest = authnRequestBuilder.buildObject();

		authnRequest.setID(generateIdentifier());
		authnRequest.setVersion(SAMLVersion.VERSION_20);

		return authnRequest;
	}

	private AuthnStatement _buildAuthnStatement() {
		return authnStatementBuilder.buildObject();
	}

	private Conditions _buildConditions() {
		return conditionsBuilder.buildObject();
	}

	private Endpoint _buildEndpoint(String binding, String location) {
		Endpoint endpoint = new MyEndpoint();

		endpoint.setBinding(binding);
		endpoint.setLocation(location);

		return endpoint;
	}

	private Issuer _buildIssuer(String value) {
		Issuer issuer = issuerBuilder.buildObject();

		issuer.setValue(value);

		return issuer;
	}

	private LogoutRequest _buildLogoutRequest() {
		return logoutRequestBuilder.buildObject();
	}

	private NameID _buildNameID(String value) {
		NameID nameID = nameIDBuilder.buildObject();

		nameID.setValue(value);

		return nameID;
	}

	private NameID _buildNameID(String value, String format) {
		NameID nameID = nameIDBuilder.buildObject();

		nameID.setValue(value);
		nameID.setFormat(format);

		return nameID;
	}

	private Response _buildResponse() {
		Response response = responseBuilder.buildObject();

		response.setID(generateIdentifier());
		response.setVersion(SAMLVersion.VERSION_20);

		return response;
	}

	private Signature _buildSignature(Credential credential) {
		Signature signature = 
			signatureBuilder.buildObject(Signature.DEFAULT_ELEMENT_NAME);

		signature.setSigningCredential(credential);

		return signature;
	}

	private Status _buildStatus(StatusCode statusCode) {
		Status status = statusBuilder.buildObject();

		status.setStatusCode(statusCode);

		return status;
	}

	private StatusCode _buildStatusCode(String value) {
		StatusCode statusCode = statusCodeBuilder.buildObject();

		statusCode.setValue(value);

		return statusCode;
	}

	private Subject _buildSubject(NameID nameID) {
		Subject subject = subjectBuilder.buildObject();

		subject.setNameID(nameID);

		return subject;
	}

	private SubjectConfirmation _buildSubjectConfirmation() {
		SubjectConfirmation subjectConfirmation = subjectConfirmationBuilder.buildObject();

		return subjectConfirmation;
	}

	private SubjectConfirmationData _buildSubjectConfirmationData() {
		SubjectConfirmationData subjectConfirmationData = subjectConfirmationDataBuilder.buildObject();

		return subjectConfirmationData;
	}

	private void _decodeHTTPRedirectRequest(
			SAMLMessageContext<
				? extends SAMLObject, ? extends SAMLObject,
				? extends SAMLObject> context)
		throws MessageDecodingException, SecurityException {

		httpRedirectDeflateDecoder.decode(context);
	}

	private String _encodeSAMLObject(SAMLObject object)
		throws MarshallingException {

		String xml = marshallSAMLObject(object);

		String encodedMessage = Base64.encodeBytes(
				xml.getBytes(), Base64.DONT_BREAK_LINES); 

		return encodedMessage;
	}

	private String _generateIdentifier() {
		return identifierGenerator.generateIdentifier(20);
	}

	private String _marshallSAMLObject(SAMLObject samlObject)
		throws MarshallingException {

		Marshaller marshaller =
			marshallerFactory.getMarshaller(samlObject);

		StringWriter stringWriter = new StringWriter();

		Element dom = marshaller.marshall(samlObject);

		XMLHelper.writeNode(dom, stringWriter);

		return stringWriter.toString();
	}

	private void _signObject(
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

	private void _validateSignature(
			SignableSAMLObject signableSAMLObject, Credential credential)
		throws ValidationException {

		signatureProfileValidator.validate(signableSAMLObject.getSignature());

		SignatureValidator signatureValidator = new SignatureValidator(credential);

		signatureValidator.validate(signableSAMLObject.getSignature());
	}

	private class MyEndpoint extends EndpointImpl {

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

	private static OpenSAMLUtil _instance = new OpenSAMLUtil();

	private SAMLObjectBuilder<Attribute> attributeBuilder;
	private SAMLObjectBuilder<AttributeStatement> attributeStatementBuilder;
	private SAMLObjectBuilder<Audience> audienceBuilder;
	private SAMLObjectBuilder<AudienceRestriction> audienceRestrictionBuilder;
	private SAMLObjectBuilder<Assertion> assertionBuilder;
	private SAMLObjectBuilder<AuthnContext> authnContextBuilder;
	private SAMLObjectBuilder<AuthnContextClassRef> authnContextClassRefBuilder;
	private SAMLObjectBuilder<AuthnRequest> authnRequestBuilder;
	private SAMLObjectBuilder<AuthnStatement> authnStatementBuilder;
	private SAMLObjectBuilder<Conditions> conditionsBuilder;
	private HTTPRedirectDeflateDecoder httpRedirectDeflateDecoder;
	private IdentifierGenerator identifierGenerator;
	private SAMLObjectBuilder<Issuer> issuerBuilder;
	private SAMLObjectBuilder<LogoutRequest> logoutRequestBuilder;
	private SAMLObjectBuilder<NameID> nameIDBuilder;
	private MarshallerFactory marshallerFactory;
	private SAMLObjectBuilder<Response> responseBuilder;
	private XMLObjectBuilder<Signature> signatureBuilder;
	private SAMLSignatureProfileValidator signatureProfileValidator;
	private SAMLObjectBuilder<Status> statusBuilder;
	private SAMLObjectBuilder<StatusCode> statusCodeBuilder;
	private SAMLObjectBuilder<Subject> subjectBuilder;
	private SAMLObjectBuilder<SubjectConfirmation> subjectConfirmationBuilder;
	private SAMLObjectBuilder<SubjectConfirmationData> subjectConfirmationDataBuilder;
	private XMLObjectBuilderFactory xmlObjectBuilderFactory;
	private XMLObjectBuilder<XSString> xsStringBuilder;
}
