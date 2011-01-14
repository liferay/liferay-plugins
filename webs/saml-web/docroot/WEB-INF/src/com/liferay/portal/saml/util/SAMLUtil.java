
package com.liferay.portal.saml.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.saml.AttributeResolver;
import com.liferay.portal.saml.AttributeResolverFactory;
import com.liferay.portal.saml.NameIDResolver;
import com.liferay.portal.saml.NameIDResolverFactory;
import com.liferay.portal.saml.SSORequestContext;
import com.liferay.portal.saml.opensaml.HttpClientInTransport;
import com.liferay.portal.saml.opensaml.HttpClientOutTransport;
import com.liferay.portal.saml.opensaml.OpenSAMLUtil;
import com.liferay.portal.saml.session.SessionManagerUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.CookieUtil;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.joda.time.DateTime;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.decoding.HTTPSOAP11Decoder;
import org.opensaml.saml2.binding.encoding.HTTPSOAP11Encoder;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
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
import org.opensaml.ws.message.decoder.MessageDecodingException;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.http.HTTPInTransport;
import org.opensaml.ws.transport.http.HttpServletRequestAdapter;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.signature.SignatureException;
import org.opensaml.xml.validation.ValidationException;

public class SAMLUtil {

	public static String performLogout(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		long companyId = PortalUtil.getCompanyId(request);
		User user = PortalUtil.getUser(request);
		String sessionId = CookieUtil.get(request, WebKeys.SSOSESSIONID);
		String entityId = SAMLMetadataUtil.getIdPEntityId(companyId);
		String statusCodeURI = StatusCode.SUCCESS_URI;

		if (Validator.isNotNull(sessionId)) {
			List<String> sps = SessionManagerUtil.getActiveSPs(sessionId);

			for (String spEntityId : sps) {
				String id = OpenSAMLUtil.generateIdentifier();
				DateTime issueInstant = new DateTime();

				String sloURL =
					SAMLMetadataUtil.getSLOURL(companyId, spEntityId);

				if (Validator.isNull(sloURL)) {
					statusCodeURI = StatusCode.PARTIAL_LOGOUT_URI;
					continue;
				}

				Credential signingCredential =
					SAMLMetadataUtil.getSigningCredential(
						companyId, spEntityId);
				Issuer issuer = OpenSAMLUtil.buildIssuer(entityId);

				NameIDResolver nameIDResolver =
					NameIDResolverFactory.getNameIDResolver(
						companyId, spEntityId);

				NameID nameID = nameIDResolver.resolve(user);

				Endpoint endpoint =
					OpenSAMLUtil.buildEndpoint(
						SAMLConstants.SAML2_SOAP11_BINDING_URI, sloURL);

				LogoutRequest logoutRequest = OpenSAMLUtil.buildLogoutRequest();

				logoutRequest.setDestination(sloURL);
				logoutRequest.setID(id);
				logoutRequest.setIssueInstant(issueInstant);
				logoutRequest.setIssuer(issuer);
				logoutRequest.setNameID(nameID);

				try {
					OpenSAMLUtil.signObject(logoutRequest, signingCredential);
				}
				catch (MarshallingException me) {
					throw new PortalException(me);
				}
				catch (SignatureException se) {
					throw new PortalException(se);
				}
				catch (SecurityException se) {
					throw new PortalException(se);
				}

				if (_log.isDebugEnabled()) {
					try {
						_log.debug(
							"Sending LogoutRequest to " + sloURL + ":\n" +
							OpenSAMLUtil.marshallSAMLObject(logoutRequest));
					}
					catch (MarshallingException me) {
					}
				}

				PostMethod postMethod = new PostMethod(sloURL);

				SAMLMessageContext<LogoutResponse, LogoutRequest, NameID> context =
					new BasicSAMLMessageContext<LogoutResponse, LogoutRequest, NameID>();

				context.setOutboundSAMLMessage(logoutRequest);
				context.setPeerEntityEndpoint(endpoint);
				context.setOutboundMessageTransport(
					new HttpClientOutTransport(postMethod));
				context.setInboundMessageTransport(
					new HttpClientInTransport(postMethod, sloURL));

				HTTPSOAP11Encoder encoder = new HTTPSOAP11Encoder();
				HTTPSOAP11Decoder decoder = new HTTPSOAP11Decoder();

				try {
					encoder.encode(context);

					HttpClient httpClient = new HttpClient();
					httpClient.executeMethod(postMethod);

					decoder.decode(context);
				}
				catch (MessageEncodingException e) {
					throw new PortalException(e);
				}
				catch (MessageDecodingException e) {
					throw new PortalException(e);
				}
				catch (SecurityException e) {
					throw new PortalException(e);
				}
				catch (HttpException e) {
					throw new PortalException(e);
				}
				catch (IOException e) {
					throw new PortalException(e);
				}

				LogoutResponse logoutResponse = context.getInboundSAMLMessage();

				Credential validationCredential =
					SAMLMetadataUtil.getValidationCredential(
						companyId, spEntityId);

				try {
					OpenSAMLUtil.validateSignature(
						logoutResponse, validationCredential);
				}
				catch (ValidationException ve) {
					_log.warn("Signature verification failed.", ve);
				}

				Status responseStatus = logoutResponse.getStatus();
				StatusCode responseStatusCode = responseStatus.getStatusCode();
				String responseStatusURI = responseStatusCode.getValue();

				if (!StatusCode.SUCCESS_URI.equals(responseStatusURI)) {

					statusCodeURI = StatusCode.PARTIAL_LOGOUT_URI;
				}

			}

			if (Validator.isNotNull(sessionId)) {
				SessionManagerUtil.destroySession(sessionId);

				Cookie ssoSessionCookie =
					new Cookie(WebKeys.SSOSESSIONID, sessionId);

				ssoSessionCookie.setMaxAge(0);
				ssoSessionCookie.setPath(StringPool.SLASH);
				ssoSessionCookie.setSecure(request.isSecure());

				response.addCookie(ssoSessionCookie);
			}
		}

		return statusCodeURI;
	}

	public static void processAuthnRequest(
		HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		SSORequestContext ssoRequestContext = _decodeAuthnRequest(request);

		SAMLMessageContext<AuthnRequest, Response, NameID> context =
			new BasicSAMLMessageContext<AuthnRequest, Response, NameID>();

		context.setInboundMessageIssuer(ssoRequestContext.getRelyingPartyId());
		context.setInboundSAMLMessage(ssoRequestContext.getAuthnRequest());
		context.setRelayState(ssoRequestContext.getRelayState());

		AuthnRequest authnRequest = context.getInboundSAMLMessage();

		if (_log.isDebugEnabled()) {
			String xml;

			try {
				xml = OpenSAMLUtil.marshallSAMLObject(authnRequest);
			}
			catch (MarshallingException me) {
				throw new PortalException(me);
			}

			_log.debug("Received AuthnRequest:\n" + xml);
		}

		long companyId = PortalUtil.getCompanyId(request);

		String spEntityId = context.getInboundMessageIssuer();

		String idpEntityId = SAMLMetadataUtil.getIdPEntityId(companyId);

		context.setOutboundMessageIssuer(idpEntityId);

		String acsURL = SAMLMetadataUtil.getAcsURL(companyId, spEntityId);

		Endpoint endpoint =
			OpenSAMLUtil.buildEndpoint(
				SAMLConstants.SAML2_POST_BINDING_URI, acsURL);

		context.setPeerEntityEndpoint(endpoint);

		Credential signingCredential =
			SAMLMetadataUtil.getSigningCredential(companyId, spEntityId);

		context.setOutboundSAMLMessageSigningCredential(signingCredential);

		User user = PortalUtil.getUser(request);

		String ssoSessionId =
			CookieUtil.get(request, WebKeys.SSOSESSIONID);

		boolean sessionExpired = false;

		if (Validator.isNotNull(ssoSessionId)) {
			sessionExpired = SessionManagerUtil.isExpired(ssoSessionId);

			if (sessionExpired) {
				Cookie ssoSessionCookie =
					new Cookie(WebKeys.SSOSESSIONID, ssoSessionId);

				ssoSessionCookie.setMaxAge(0);
				ssoSessionCookie.setPath(StringPool.SLASH);
				ssoSessionCookie.setSecure(request.isSecure());

				response.addCookie(ssoSessionCookie);
			}
		}

		if (sessionExpired || (authnRequest.isForceAuthn() && (user != null) &&
			(ssoRequestContext.getStage() == SSORequestContext.STAGE_INITIAL))) {

			HttpSession session = request.getSession();

			session.invalidate();

			session = request.getSession(true);

			_sendToLogin(request, response, context);

			return;
		}

		if (authnRequest.isPassive() && (user == null)) {
			_handleFailure(
				request, response, context, StatusCode.NO_PASSIVE_URI);

			return;
		}

		// Authenticate user

		if (user == null) {
			_sendToLogin(request, response, context);

			return;
		}

		Response samlResponse = _buildSuccessResponse(user, context);

		context.setOutboundSAMLMessage(samlResponse);

		// Create SSO Session

		if (Validator.isNull(ssoSessionId)) {
			ssoSessionId = OpenSAMLUtil.generateIdentifier();
		}

		ServiceContext serviceContext =
			ServiceContextFactory.getInstance(request);

		SessionManagerUtil.registerSession(
			ssoSessionId, spEntityId, serviceContext);

		Cookie ssoSessionCookie =
			new Cookie(WebKeys.SSOSESSIONID, ssoSessionId);

		ssoSessionCookie.setMaxAge(-1);
		ssoSessionCookie.setPath(StringPool.SLASH);
		ssoSessionCookie.setSecure(request.isSecure());

		response.addCookie(ssoSessionCookie);

		if (_log.isDebugEnabled()) {
			String xml;

			try {
				xml = OpenSAMLUtil.marshallSAMLObject(samlResponse);
			}
			catch (MarshallingException me) {
				throw new PortalException(me);
			}

			_log.debug("Sending SAML Response:\n" + xml);
		}

		_sendResponse(request, response, context);
	}

	private static Response _buildFailureResponse(
		SAMLMessageContext<AuthnRequest, Response, NameID> context,
		String statusURI)
		throws SystemException {

		String entityId = context.getOutboundMessageIssuer();
		String requestID = context.getInboundSAMLMessageId();
		String acsURL = context.getPeerEntityEndpoint().getLocation();

		StatusCode statusCode = OpenSAMLUtil.buildStatusCode(statusURI);

		DateTime issueInstant = new DateTime();

		Issuer issuer = OpenSAMLUtil.buildIssuer(entityId);

		Status status = OpenSAMLUtil.buildStatus(statusCode);

		Response response = OpenSAMLUtil.buildResponse();

		response.setIssueInstant(issueInstant);
		response.setStatus(status);
		response.setInResponseTo(requestID);
		response.setIssuer(issuer);
		response.setDestination(acsURL);

		return response;
	}

	private static Response _buildSuccessResponse(
		User user, SAMLMessageContext<AuthnRequest, Response, NameID> context)
		throws SystemException, PortalException {

		long companyId = user.getCompanyId();
		String authnRequestID = context.getInboundSAMLMessageId();
		String acsURL = context.getPeerEntityEndpoint().getLocation();
		DateTime authnIssueInstant =
			context.getInboundSAMLMessageIssueInstant();
		String spEntityId = context.getInboundMessageIssuer();
		String issuerName = context.getOutboundMessageIssuer();
		int clockSkew = SAMLMetadataUtil.getClockSkew(companyId, spEntityId);
		int assertionLifetime =
			SAMLMetadataUtil.getAssertionLifetime(companyId, spEntityId);

		DateTime issueInstant = new DateTime();
		DateTime notBefore = new DateTime().minusMillis(clockSkew);
		DateTime notOnOrAfter =
			new DateTime().plusMillis(clockSkew).plusSeconds(assertionLifetime);

		Issuer issuer = OpenSAMLUtil.buildIssuer(issuerName);

		NameIDResolver nameIDResolver =
			NameIDResolverFactory.getNameIDResolver(companyId, spEntityId);

		NameID nameID = nameIDResolver.resolve(user);

		SubjectConfirmationData subjectConfirmationData =
			OpenSAMLUtil.buildSubjectConfirmationData();
		subjectConfirmationData.setRecipient(acsURL);
		subjectConfirmationData.setNotBefore(notBefore);
		subjectConfirmationData.setNotOnOrAfter(notOnOrAfter);

		SubjectConfirmation subjectConfirmation =
			OpenSAMLUtil.buildSubjectConfirmation();
		subjectConfirmation.setSubjectConfirmationData(subjectConfirmationData);
		subjectConfirmation.setMethod(SubjectConfirmation.METHOD_BEARER);

		Subject subject = OpenSAMLUtil.buildSubject(nameID);
		subject.getSubjectConfirmations().add(subjectConfirmation);

		Audience audience = OpenSAMLUtil.buildAudience();
		audience.setAudienceURI(acsURL);

		AudienceRestriction audienceRestriction =
			OpenSAMLUtil.buildAudienceRestriction();
		audienceRestriction.getAudiences().add(audience);

		Conditions conditions = OpenSAMLUtil.buildConditions();
		conditions.getAudienceRestrictions().add(audienceRestriction);
		conditions.setNotBefore(notBefore);
		conditions.setNotOnOrAfter(notOnOrAfter);

		AuthnContextClassRef authnContextClassRef =
			OpenSAMLUtil.buildAuthnContextClassRef();
		authnContextClassRef.setAuthnContextClassRef(
			AuthnContext.PASSWORD_AUTHN_CTX);

		AuthnContext authnContext = OpenSAMLUtil.buildAuthnContext();
		authnContext.setAuthnContextClassRef(authnContextClassRef);

		AuthnStatement authnStatement = OpenSAMLUtil.buildAuthnStatement();
		authnStatement.setAuthnInstant(authnIssueInstant);
		authnStatement.setAuthnContext(authnContext);

		Assertion assertion = OpenSAMLUtil.buildAssertion();

		assertion.setIssueInstant(issueInstant);
		assertion.setIssuer(OpenSAMLUtil.buildIssuer(issuerName));
		assertion.setSubject(subject);
		assertion.setConditions(conditions);
		assertion.getAuthnStatements().add(authnStatement);

		if (SAMLMetadataUtil.isAttributesIncludeEnabled(
			companyId, spEntityId)) {

			AttributeStatement attributeStatement =
				OpenSAMLUtil.buildAttributeStatement();

			AttributeResolver attributeResolver =
				AttributeResolverFactory.getAttributeResolver(
					companyId, spEntityId);

			List<Attribute> attributes = attributeResolver.resolve(user);

			attributeStatement.getAttributes().addAll(attributes);

			assertion.getAttributeStatements().add(attributeStatement);
		}

		try {
			OpenSAMLUtil.signObject(
				assertion, context.getOuboundSAMLMessageSigningCredential());
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		StatusCode statusCode =
			OpenSAMLUtil.buildStatusCode(StatusCode.SUCCESS_URI);

		Status status = OpenSAMLUtil.buildStatus(statusCode);

		Response response = OpenSAMLUtil.buildResponse();

		response.setIssueInstant(issueInstant);
		response.setStatus(status);
		response.setInResponseTo(authnRequestID);
		response.getAssertions().add(assertion);
		response.setIssuer(issuer);
		response.setDestination(acsURL);

		return response;
	}

	private static SSORequestContext _decodeAuthnRequest(
		HttpServletRequest request)
		throws PortalException {

		SAMLMessageContext<AuthnRequest, Response, NameID> context =
			new BasicSAMLMessageContext<AuthnRequest, Response, NameID>();

		HttpSession session = request.getSession();

		SSORequestContext ssoRequestContext =
			(SSORequestContext)session.getAttribute(
				WebKeys.SSO_REQUEST_CONTEXT);

		if (ssoRequestContext != null) {
			session.removeAttribute(WebKeys.SSO_REQUEST_CONTEXT);

			context.setInboundMessageIssuer(ssoRequestContext.getRelyingPartyId());
			context.setInboundSAMLMessage(ssoRequestContext.getAuthnRequest());
			context.setRelayState(ssoRequestContext.getRelayState());

			ssoRequestContext.setStage(SSORequestContext.STAGE_AUTHENTICATED);

			return ssoRequestContext;
		}

		HTTPInTransport in = new HttpServletRequestAdapter(request);

		context.setInboundMessageTransport(in);

		try {
			OpenSAMLUtil.decodeHTTPRedirectRequest(context);
		}
		catch (MessageDecodingException mde) {
			throw new PortalException(mde);
		}
		catch (SecurityException se) {
			throw new PortalException(se);
		}

		ssoRequestContext = new SSORequestContext(
			context.getInboundMessageIssuer(), context.getRelayState(),
			context.getInboundSAMLMessage());

		return ssoRequestContext;
	}

	private static void _handleFailure(
			HttpServletRequest request, HttpServletResponse response,
			SAMLMessageContext<AuthnRequest, Response, NameID> context,
			String statusCodeURI)
		throws PortalException, SystemException {

		Response samlResponse =
			_buildFailureResponse(context, statusCodeURI);

		context.setOutboundSAMLMessage(samlResponse);

		_sendResponse(request, response, context);
	}

	private static void _sendToLogin(
			HttpServletRequest request, HttpServletResponse response,
			SAMLMessageContext<AuthnRequest, Response, NameID> context)
		throws SystemException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		HttpSession session = request.getSession();

		SSORequestContext ssoRequestContext = new SSORequestContext(
			context.getInboundMessageIssuer(), context.getRelayState(),
			context.getInboundSAMLMessage());

		session.setAttribute(WebKeys.SSO_REQUEST_CONTEXT, ssoRequestContext);

		String signInUrl = themeDisplay.getPathMain() + 
			"/portal/login?redirect=" + PortalUtil.getCurrentURL(request);

		try {
			response.sendRedirect(signInUrl);
		}
		catch (IOException e) {
			throw new SystemException(e);
		}
	}

	private static void _sendResponse(
		HttpServletRequest request, HttpServletResponse response,
		SAMLMessageContext<AuthnRequest, Response, NameID> context)
		throws PortalException, SystemException {

		Response samlResponse = context.getOutboundSAMLMessage();

		// Sign response

		try {
			if (context.getOuboundSAMLMessageSigningCredential() != null) {
				OpenSAMLUtil.signObject(
					context.getOutboundSAMLMessage(),
					context.getOuboundSAMLMessageSigningCredential());
			}

			String encodedSAMLResponse =
				OpenSAMLUtil.encodeSAMLObject(samlResponse);

			response.setHeader(
				HttpHeaders.CACHE_CONTROL,
				HttpHeaders.CACHE_CONTROL_NO_CACHE_NO_STORE_VALUE);
			response.setHeader(
				HttpHeaders.PRAGMA, HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);

			request.setAttribute("SAMLResponse", encodedSAMLResponse);

			if (context.getRelayState() != null) {
				request.setAttribute("RelayState", context.getRelayState());
			}

			request.setAttribute(
				"action", context.getPeerEntityEndpoint().getLocation());

		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		try {
			request.getRequestDispatcher(
				"/html/portal/saml/http_post_binding.jsp").include(
				request, response);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SAMLUtil.class);
}
