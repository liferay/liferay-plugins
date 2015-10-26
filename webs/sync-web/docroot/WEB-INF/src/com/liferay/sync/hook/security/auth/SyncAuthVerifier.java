/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.sync.hook.security.auth;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AccessControlContext;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.AuthVerifier;
import com.liferay.portal.security.auth.AuthVerifierResult;
import com.liferay.portal.security.auth.Authenticator;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.sync.service.ClpSerializer;
import com.liferay.util.PwdGenerator;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.oauth.jsontoken.Checker;
import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.JsonTokenParser;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;
import net.oauth.jsontoken.crypto.HmacSHA256Verifier;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.jsontoken.crypto.Signer;
import net.oauth.jsontoken.crypto.Verifier;
import net.oauth.jsontoken.discovery.VerifierProvider;
import net.oauth.jsontoken.discovery.VerifierProviders;

import org.joda.time.Instant;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class SyncAuthVerifier implements AuthVerifier {

	@Override
	public String getAuthType() {
		return SyncAuthVerifier.class.getSimpleName();
	}

	public String getUserId(String tokenString) {
		try {
			JsonTokenParser jsonTokenParser = getJsonTokenParser();

			JsonToken jsonToken = jsonTokenParser.verifyAndDeserialize(
				tokenString);

			JsonPrimitive userIdJsonPrimitive = jsonToken.getParamAsPrimitive(
				"userId");

			if (userIdJsonPrimitive == null) {
				return null;
			}

			long userId = userIdJsonPrimitive.getAsLong();

			User user = UserLocalServiceUtil.fetchUser(userId);

			Date passwordModifiedDate = user.getPasswordModifiedDate();

			if (passwordModifiedDate != null) {
				Instant instant = jsonToken.getIssuedAt();

				if (instant.isBefore(passwordModifiedDate.getTime())) {
					return null;
				}
			}

			return String.valueOf(userId);
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public AuthVerifierResult verify(
			AccessControlContext accessControlContext, Properties properties)
		throws AuthException {

		AuthVerifierResult authVerifierResult = new AuthVerifierResult();

		HttpServletRequest request = accessControlContext.getRequest();

		String uri = (String)request.getAttribute(WebKeys.INVOKER_FILTER_URI);

		if (uri.startsWith("/download/")) {
			String contextPath = request.getContextPath();

			if (!contextPath.equals(
					StringPool.FORWARD_SLASH +
						ClpSerializer.getServletContextName())) {

				return authVerifierResult;
			}
		}

		try {
			String[] credentials = getCredentials(
				request, accessControlContext.getResponse());

			if (credentials != null) {
				authVerifierResult.setPassword(credentials[1]);
				authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
				authVerifierResult.setUserId(
					GetterUtil.getLong(credentials[0]));
			}
			else {

				// SYNC-1463

				Map<String, Object> settings =
					accessControlContext.getSettings();

				settings.remove("basic_auth");
			}

			return authVerifierResult;
		}
		catch (Exception e) {
			throw new AuthException(e);
		}
	}

	protected String createToken(long userId) {
		Signer signer = null;

		try {
			signer = getSigner();
		}
		catch (Exception e) {
			return null;
		}

		JsonToken jsonToken = new JsonToken(signer);

		Instant instant = new Instant();

		jsonToken.setExpiration(instant.plus(_expiration));
		jsonToken.setIssuedAt(instant);

		JsonObject payloadJsonObject = jsonToken.getPayloadAsJsonObject();

		payloadJsonObject.addProperty("userId", userId);

		try {
			return jsonToken.serializeAndSign();
		}
		catch (Exception e) {
			return null;
		}
	}

	protected String[] getCredentials(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String token = request.getHeader(_TOKEN_HEADER);

		if (Validator.isNotNull(token)) {
			String userId = getUserId(token);

			if (userId != null) {
				return new String[] {userId, null};
			}
		}

		String authorization = request.getHeader("Authorization");

		if (authorization == null) {
			return null;
		}

		long userId = 0;

		Map<String, Object> resultsMap = new HashMap<String, Object>();

		StringTokenizer st = new StringTokenizer(authorization);

		st.nextToken();

		String credentials = new String(Base64.decode(st.nextToken()));

		int pos = credentials.indexOf(CharPool.COLON);

		String login = credentials.substring(0, pos);
		String password = credentials.substring(pos + 1);

		Company company = PortalUtil.getCompany(request);

		String authType = company.getAuthType();

		if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
			int authResult = UserLocalServiceUtil.authenticateByEmailAddress(
				company.getCompanyId(), login, password, null, null,
				resultsMap);

			if (authResult != Authenticator.SUCCESS) {
				throw new AuthException();
			}

			userId = MapUtil.getLong(resultsMap, "userId");
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
			int authResult = UserLocalServiceUtil.authenticateByScreenName(
				company.getCompanyId(), login, password, null, null,
				resultsMap);

			if (authResult != Authenticator.SUCCESS) {
				throw new AuthException();
			}

			userId = MapUtil.getLong(resultsMap, "userId");
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
			int authResult = UserLocalServiceUtil.authenticateByUserId(
				company.getCompanyId(), GetterUtil.getLong(login), password,
				null, null, resultsMap);

			if (authResult != Authenticator.SUCCESS) {
				throw new AuthException();
			}

			userId = GetterUtil.getLong(login);
		}

		token = createToken(userId);

		if (token != null) {
			response.addHeader(_TOKEN_HEADER, token);
		}

		return new String[] {String.valueOf(userId), password};
	}

	protected JsonTokenParser getJsonTokenParser() throws Exception {
		if (_jsonTokenParser != null) {
			return _jsonTokenParser;
		}

		final Verifier verifier = new HmacSHA256Verifier(_secret.getBytes());

		VerifierProvider verifierProvider = new VerifierProvider() {

			@Override
			public List<Verifier> findVerifier(String signerId, String keyId) {
				return Lists.newArrayList(verifier);
			}

		};

		VerifierProviders verifyProviders = new VerifierProviders();

		verifyProviders.setVerifierProvider(
			SignatureAlgorithm.HS256, verifierProvider);

		Checker checker = new Checker() {

			@Override
			public void check(JsonObject jsonObject) {
			}

		};

		_jsonTokenParser = new JsonTokenParser(verifyProviders, checker);

		return _jsonTokenParser;
	}

	protected Signer getSigner() {
		if (_signer != null) {
			return _signer;
		}

		try {
			_signer = new HmacSHA256Signer(null, null, _secret.getBytes());

			return _signer;
		}
		catch (Exception e) {
			return null;
		}
	}

	private static final String _TOKEN_HEADER = "Sync-JWT";

	private static long _expiration = 3600000;
	private static JsonTokenParser _jsonTokenParser;
	private static String _secret = PwdGenerator.getPassword();
	private static Signer _signer;

}