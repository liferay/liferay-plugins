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

import com.liferay.portal.NoSuchCompanyException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.Authenticator;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.vldap.server.handler.util.LdapHandlerContext;
import com.liferay.vldap.server.handler.util.SaslCallbackHandler;
import com.liferay.vldap.util.PortletPropsValues;

import java.net.InetSocketAddress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.sasl.Sasl;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;

import org.apache.directory.shared.ldap.model.entry.Value;
import org.apache.directory.shared.ldap.model.message.BindRequest;
import org.apache.directory.shared.ldap.model.message.BindResponse;
import org.apache.directory.shared.ldap.model.message.Request;
import org.apache.directory.shared.ldap.model.message.Response;
import org.apache.directory.shared.ldap.model.message.ResultCodeEnum;
import org.apache.directory.shared.ldap.model.name.Dn;
import org.apache.directory.shared.ldap.model.name.Rdn;
import org.apache.directory.shared.util.StringConstants;
import org.apache.mina.core.session.IoSession;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class BindLdapHandler extends BaseLdapHandler {

	public static final String DIGEST_MD5 = "DIGEST-MD5";

	public List<Response> messageReceived(
			Request request, IoSession ioSession,
			LdapHandlerContext ldapHandlerContext)
		throws Exception {

		BindRequest bindRequest = (BindRequest)request;

		String saslMechanism = GetterUtil.getString(
			bindRequest.getSaslMechanism());

		Response response = null;

		if (saslMechanism.equals(DIGEST_MD5)) {
			response = getSaslResponse(
				bindRequest, ioSession, ldapHandlerContext);
		}
		else if (bindRequest.isSimple()) {
			response = getSimpleResponse(
				bindRequest, ioSession, ldapHandlerContext);
		}
		else {
			response = getUnsupportedResponse(bindRequest);
		}

		return toList(response);
	}

	protected String getSaslHostName(IoSession ioSession) {
		String saslHostName = PortletPropsValues.BIND_SASL_HOSTNAME;

		if (Validator.isNull(saslHostName)) {
			InetSocketAddress inetSocketAddress =
				(InetSocketAddress)ioSession.getLocalAddress();

			saslHostName = inetSocketAddress.getHostName();
		}

		if (_log.isDebugEnabled()) {
			_log.debug("SASL host name " + saslHostName);
		}

		return saslHostName;
	}

	protected Response getSaslResponse(
			BindRequest bindRequest, IoSession ioSession,
			LdapHandlerContext ldapHandlerContext)
		throws Exception {

		if (bindRequest.getCredentials() == null) {
			bindRequest.setCredentials(StringConstants.EMPTY_BYTES);
		}

		SaslServer saslServer = ldapHandlerContext.getSaslServer();

		try {
			if (saslServer == null) {
				synchronized (ldapHandlerContext) {
					saslServer = ldapHandlerContext.getSaslServer();

					if (saslServer == null) {
						SaslCallbackHandler saslCallbackHandler =
							new SaslCallbackHandler();

						ldapHandlerContext.setSaslCallbackHandler(
							saslCallbackHandler);

						saslServer = Sasl.createSaslServer(
							DIGEST_MD5, _LDAP, getSaslHostName(ioSession),
							null, saslCallbackHandler);

						ldapHandlerContext.setSaslServer(saslServer);
					}
				}
			}

			BindResponse bindResponse = bindRequest.getResultResponse();

			byte[] challenge = saslServer.evaluateResponse(
				bindRequest.getCredentials());

			bindResponse.setServerSaslCreds(challenge);
		}
		catch (SaslException sasle) {
			_log.error(sasle, sasle);

			ldapHandlerContext.setSaslCallbackHandler(null);
			ldapHandlerContext.setSaslServer(null);

			return getResponse(bindRequest, ResultCodeEnum.INVALID_CREDENTIALS);
		}

		if (saslServer.isComplete()) {
			SaslCallbackHandler saslCallbackHandler =
				ldapHandlerContext.getSaslCallbackHandler();

			ldapHandlerContext.setSaslCallbackHandler(null);
			ldapHandlerContext.setSaslServer(null);

			Dn name = saslCallbackHandler.getName();

			setCompany(ldapHandlerContext, name);
			setUser(ldapHandlerContext, name);

			return getResponse(bindRequest, ResultCodeEnum.SUCCESS);
		}
		else {
			return getResponse(
				bindRequest, ResultCodeEnum.SASL_BIND_IN_PROGRESS);
		}
	}

	protected Response getSimpleResponse(
			BindRequest bindRequest, IoSession ioSession,
			LdapHandlerContext ldapHandlerContext)
		throws Exception {

		Dn name = bindRequest.getName();

		if (Validator.isNull(name.getName())) {
			return getResponse(bindRequest, ResultCodeEnum.SUCCESS);
		}

		Company company = setCompany(ldapHandlerContext, name);

		String screenName = getValue(name, "cn");

		if (Validator.isNull(screenName)) {
			return getResponse(bindRequest, ResultCodeEnum.INVALID_CREDENTIALS);
		}

		String password = new String(bindRequest.getCredentials());
		Map<String, String[]> headerMap = new HashMap<String, String[]>();
		Map<String, String[]> parameterMap = new HashMap<String, String[]>();
		Map<String, Object> resultsMap = new HashMap<String, Object>();

		int authResult = UserLocalServiceUtil.authenticateByScreenName(
			company.getCompanyId(), screenName, password, headerMap,
			parameterMap, resultsMap);

		if (authResult != Authenticator.SUCCESS) {
			return getResponse(bindRequest, ResultCodeEnum.INVALID_CREDENTIALS);
		}

		setUser(ldapHandlerContext, name);

		return getResponse(bindRequest, ResultCodeEnum.SUCCESS);
	}

	protected Response getUnsupportedResponse(
		BindRequest bindRequest) {

		return getResponse(
			bindRequest, ResultCodeEnum.AUTH_METHOD_NOT_SUPPORTED);
	}

	protected String getValue(Dn dn, String normType) {
		for (Rdn rdn : dn) {
			String rdnNormType = rdn.getNormType();
			Value<?> rdnNormValue = rdn.getNormValue();

			if (rdnNormType.equalsIgnoreCase(normType)) {
				return GetterUtil.getString(rdnNormValue.getString());
			}
		}

		return StringPool.BLANK;
	}

	protected Company setCompany(LdapHandlerContext ldapHandlerContext, Dn name)
		throws Exception {

		String webId = getValue(name, "webId");

		Company company = null;

		try {
			company = CompanyLocalServiceUtil.getCompanyByWebId(webId);
		}
		catch (NoSuchCompanyException nsce) {
			long companyId = PortalUtil.getDefaultCompanyId();

			company = CompanyLocalServiceUtil.getCompany(companyId);
		}

		ldapHandlerContext.setCompany(company);

		return company;
	}

	protected User setUser(LdapHandlerContext ldapHandlerContext, Dn name)
		throws Exception {

		String screenName = getValue(name, "cn");

		Company company = ldapHandlerContext.getCompany();

		User user = UserLocalServiceUtil.getUserByScreenName(
			company.getCompanyId(), screenName);

		if (!user.isDefaultUser()) {
			ldapHandlerContext.setUser(user);
		}

		return user;
	}

	private static final String _LDAP = "ldap";

	private static Log _log = LogFactoryUtil.getLog(BindLdapHandler.class);

}