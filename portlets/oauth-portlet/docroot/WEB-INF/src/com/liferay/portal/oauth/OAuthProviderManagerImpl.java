/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.oauth;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.oauth.model.OAuthApplication;
import com.liferay.portal.oauth.service.OAuthApplicationLocalServiceUtil;
import com.liferay.portal.oauth.service.OAuthApplications_UsersLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.oauth.OAuthConstants;

import java.io.IOException;
import java.io.OutputStream;

import java.util.Collection;
import java.util.HashSet;

import javax.portlet.PortletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.oauth.OAuth;
import net.oauth.server.OAuthServlet;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Ivica Cardic
 */
public class OAuthProviderManagerImpl implements OAuthProviderManager {

	public OAuthProviderManagerImpl(OAuthValidator oAuthValidator) {
		this._oAuthValidator = oAuthValidator;
	}

	public String addParameters(
			String url, String... parameters) throws OAuthException {
		try {
			return OAuth.addParameters(url, parameters);
		}
		catch (IOException e) {
			throw new OAuthException(e);
		}
	}

	public void formEncode(
			String oauthToken, String tokenSecret, OutputStream out)
		throws OAuthException {

		try {
			OAuth.formEncode(
					OAuth.newList(
							OAuthConstants.OAUTH_TOKEN, oauthToken,
							OAuthConstants.OAUTH_TOKEN_SECRET,
							tokenSecret), out);
		}
		catch (IOException e) {
			throw new OAuthException(e);
		}
	}

	/**
	 * Generate a fresh access token and secret for a consumer.
	 *
	 * @throws net.oauth.OAuthException
	 */
	public void generateAccessToken(OAuthAccessor accessor, long userId)
		throws SystemException {

		// generate oauth_token and oauth_secret
		String consumerKey = accessor.getConsumer().getOAuthApplication()
			.getConsumerKey();

		// generate token and secret based on consumerKey

		// for now use md5 of name + current time as token
		String tokenData = consumerKey + System.nanoTime();
		String token = DigestUtils.md5Hex(tokenData);

		// for now use md5 of name + current time + token as secret
		String secretData = consumerKey + System.nanoTime() + token;
		String secret = DigestUtils.md5Hex(secretData);

		accessor.setAccessToken(token);
		accessor.setTokenSecret(secret);
		accessor.setRequestToken(null);

		OAuthApplication oAuthApplication =
				accessor.getConsumer().getOAuthApplication();

		OAuthApplications_UsersLocalServiceUtil
			.updateOAuthApplications_Users(
				oAuthApplication.getApplicationId(), userId,
				accessor.getAccessToken(), accessor.getTokenSecret());

		// first remove the accessor from cache
		ALL_TOKENS.remove(accessor);

		accessor.setRequestToken(null);
		accessor.setTokenSecret(secret);
		accessor.setAccessToken(token);

		// update token in local cache
		ALL_TOKENS.add(accessor);
	}

	/**
	 * Generate a fresh request token and secret for a consumer.
	 */
	public void generateRequestToken(OAuthAccessor accessor) {

		// generate oauth_token and oauth_secret
		String consumerKey = accessor.getConsumer().getOAuthApplication()
			.getConsumerKey();

		// generate token and secret based on consumerKey

		// for now use md5 of name + current time as token
		String tokenData = consumerKey + System.nanoTime();
		String token = DigestUtils.md5Hex(tokenData);

		// for now use md5 of name + current time + token as secret
		String secretData = consumerKey + System.nanoTime() + token;
		String secret = DigestUtils.md5Hex(secretData);

		accessor.setRequestToken(token);
		accessor.setTokenSecret(secret);
		accessor.setAccessToken(null);

		// add to the local cache
		ALL_TOKENS.add(accessor);

	}

	/**
	 * Get the access token and token secret for the given oauth_token.
	 */
	public OAuthAccessor getAccessor(OAuthMessage requestMessage)
		throws OAuthException, OAuthProblemException {

		// try to load from local cache if not throw exception
		String consumerToken = null;
		try {
			consumerToken = requestMessage.getToken();
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}

		OAuthAccessor accessor = null;
		for (OAuthAccessor a : ALL_TOKENS) {
			if (a.getRequestToken() != null) {
				if (a.getRequestToken().equals(consumerToken)) {
					accessor = a;
					break;
				}
			} else if (a.getAccessToken() != null) {
				if (a.getAccessToken().equals(consumerToken)) {
					accessor = a;
					break;
				}
			}
		}

		if (accessor == null) {
			throw new OAuthProblemException(
				OAuthProblemException.TOKEN_EXPIRED);
		}

		return accessor;
	}

	public OAuthConsumer getConsumer(OAuthMessage requestMessage)
		throws OAuthException, OAuthProblemException {

		OAuthConsumer consumer;

		String consumerKey;

		try {
			consumerKey = requestMessage.getConsumerKey();
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}

		OAuthApplication oAuthApplication;

		try {
			oAuthApplication = OAuthApplicationLocalServiceUtil.getApplication(
				consumerKey);
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}

		if (oAuthApplication == null) {
			throw new OAuthProblemException(
				OAuthProblemException.TOKEN_REJECTED);
		}
		else {
			consumer = new OAuthConsumerImpl(oAuthApplication);
		}

		return consumer;
	}

	public OAuthMessage getMessage(HttpServletRequest request, String url) {
		return new OAuthMessageImpl(OAuthServlet.getMessage(request, url));
	}

	public OAuthMessage getMessage(PortletRequest request, String url) {
		HttpServletRequest httpServletRequest =
			PortalUtil.getHttpServletRequest(request);

		return new OAuthMessageImpl(
			OAuthServlet.getMessage(httpServletRequest, url));
	}

	public void handleException(
			HttpServletRequest request, HttpServletResponse response,
			Exception e, boolean sendBody)
		throws IOException, ServletException {

		String realm = (request.isSecure()) ? "https://":"http://";
		realm += request.getLocalName();

		if (e.getCause() != null) {
			e = (Exception)e.getCause();
		}

		OAuthServlet.handleException(response, e, realm, sendBody);
	}

	/**
	 * Mark user as authorized
	 */
	public void markAsAuthorized(OAuthAccessor accessor, long userId)
		throws SystemException {

		OAuthApplication oAuthApplication =
			accessor.getConsumer().getOAuthApplication();

		OAuthApplications_UsersLocalServiceUtil
			.updateOAuthApplications_Users(
				oAuthApplication.getApplicationId(), userId, true);

		// first remove the accessor from cache
		ALL_TOKENS.remove(accessor);

		accessor.setProperty("user", userId);
		accessor.setProperty(OAuthConstants.AUTHORIZED, Boolean.TRUE);

		// update token in local cache
		ALL_TOKENS.add(accessor);
	}

	public void markAsAuthorized(
			OAuthAccessor accessor, long userId, ServiceContext serviceContext)
		throws PortalException, SystemException {
		OAuthApplication oAuthApplication =
				accessor.getConsumer().getOAuthApplication();

		OAuthApplications_UsersLocalServiceUtil
			.updateOAuthApplications_Users(
					true, oAuthApplication.getApplicationId(), userId, null,
					null, serviceContext);

		// first remove the accessor from cache
		ALL_TOKENS.remove(accessor);

		accessor.setProperty("user", userId);
		accessor.setProperty(OAuthConstants.AUTHORIZED, Boolean.TRUE);

		// update token in local cache
		ALL_TOKENS.add(accessor);
	}

	public void validateMessage(OAuthMessage message, OAuthAccessor accessor)
			throws OAuthException {

		_oAuthValidator.validateMessage(message, accessor);
	}

	private static final Collection<OAuthAccessor> ALL_TOKENS =
		new HashSet<OAuthAccessor>();

	private OAuthValidator _oAuthValidator;

}