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

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.oauth.model.Application;
import com.liferay.portal.oauth.model.ApplicationUser;
import com.liferay.portal.oauth.service.ApplicationLocalServiceUtil;
import com.liferay.portal.oauth.service.ApplicationUserLocalServiceUtil;
import com.liferay.portal.oauth.util.OAuthConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.PwdGenerator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.portlet.PortletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.oauth.OAuth;
import net.oauth.server.OAuthServlet;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Implements OAuth 1.0 Revision A. For detailed information about revision
 * please check <a href="http://oauth.net/core/1.0a/">OAuth 1.0a Spec</a> and
 * <a href="http://tools.ietf.org/html/rfc5849">OAuth 1.0 RFC 5849</a>.
 *
 * @author Ivica Cardic
 * @author Raymond Augé
 */
public class OAuth_1_0_AImpl implements com.liferay.portal.oauth.OAuth {

	public OAuth_1_0_AImpl(OAuthValidator oAuthValidator) {

		this._oAuthValidator = oAuthValidator;
	}

	public String addParameters(String url, String... parameters)
		throws IOException {

		return OAuth.addParameters(url, parameters);
	}

	public void authorize(
		OAuthAccessor accessor, long userId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Boolean authorized = (Boolean)accessor.getProperty(
			OAuthConstants.AUTHORIZED);

		if ((authorized != null) && authorized.equals(Boolean.TRUE) &&
			(accessor.getRequestToken() != null)) {
			throw new OAuthException(OAuthConstants.TOKEN_EXPIRED);
		}

		// first remove the accessor from cache

		_portalCache.remove(accessor.getRequestToken());

		accessor.setProperty("user", userId);
		accessor.setProperty(OAuthConstants.AUTHORIZED, Boolean.TRUE);

		// update token in local cache
		_portalCache.put(accessor.getRequestToken(), (Serializable) accessor);
	}

	public void formEncode(
		String oauthToken, String tokenSecret, OutputStream out)
		throws IOException {

		OAuth.formEncode(
			OAuth.newList(
				OAuthConstants.OAUTH_TOKEN, oauthToken,
				OAuthConstants.OAUTH_TOKEN_SECRET, tokenSecret), out);
	}

	public void generateAccessToken(
		OAuthAccessor accessor, long userId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Boolean authorized = (Boolean)accessor.getProperty(
			OAuthConstants.AUTHORIZED);

		if ((authorized != null) && authorized.equals(Boolean.TRUE) &&
			(accessor.getAccessToken() != null)) {
			throw new OAuthException(OAuthConstants.TOKEN_EXPIRED);
		}

		OAuthConsumer consumer = accessor.getConsumer();
		Application application = consumer.getApplication();

		String consumerKey = application.getConsumerKey();

		// generate token and secret based on consumerKey

		String token = randomizeToken(consumerKey);

		String secret = randomizeToken(consumerKey.concat(token));

		accessor.setAccessToken(token);
		accessor.setTokenSecret(secret);
		accessor.setRequestToken(null);

		ApplicationUser applicationUser = null;

		try {
		applicationUser =
			ApplicationUserLocalServiceUtil.getApplicationUserByApplicationId(
			userId, application.getApplicationId());
		}catch (NoSuchApplicationUserException e){}

		if (applicationUser == null) {
			ApplicationUserLocalServiceUtil.addApplicationUser(
				userId, application.getApplicationId(),
				accessor.getAccessToken(), accessor.getTokenSecret(),
				serviceContext);
		}else {
			applicationUser.setAccessToken(accessor.getAccessToken());
			applicationUser.setAccessSecret(accessor.getTokenSecret());

			ApplicationUserLocalServiceUtil.updateApplicationUser(
				applicationUser);
		}

		// first remove the accessor from cache

		_portalCache.remove(token);

		accessor.setRequestToken(null);
		accessor.setTokenSecret(secret);
		accessor.setAccessToken(token);

		// update token in local cache
		_portalCache.put(token, (Serializable) accessor);
	}

	/**
	 * Generate a fresh request token and secret for a consumer.
	 */
	public void generateRequestToken(OAuthAccessor accessor) {

		OAuthConsumer consumer = accessor.getConsumer();
		Application application = consumer.getApplication();

		String consumerKey = application.getConsumerKey();

		// Generate token and secret based on consumerKey

		String token = randomizeToken(consumerKey);

		String secret = randomizeToken(consumerKey.concat(token));

		accessor.setRequestToken(token);
		accessor.setTokenSecret(secret);
		accessor.setAccessToken(null);

		// add to the local cache

		_portalCache.put(token, (Serializable) accessor);
	}

	/**
	 * Get the access token and token secret for the given oauth_token.
	 */
	public OAuthAccessor getAccessor(OAuthMessage requestMessage)
		throws OAuthException {

		// try to load from local cache if not throw exception

		String consumerToken;

		try {
			consumerToken = requestMessage.getToken();
		}
		catch (IOException ioe) {
			throw new OAuthException(ioe);
		}

		OAuthAccessor accessor = (OAuthAccessor)_portalCache.get(consumerToken);

		if (accessor == null) {
			throw new OAuthException(OAuthConstants.TOKEN_EXPIRED);
		}

		return accessor;
	}

	public OAuthConsumer getConsumer(OAuthMessage requestMessage)
		throws IOException, PortalException, SystemException {

		String consumerKey = requestMessage.getConsumerKey();

		Application application = ApplicationLocalServiceUtil.getApplication(
			consumerKey);

		if (application == null) {
			throw new OAuthException(OAuthConstants.TOKEN_REJECTED);
		}

		return new OAuthConsumerImpl(application);
	}

	public OAuthMessage getMessage(HttpServletRequest request) {

		return getMessage(request, null);
	}

	public OAuthMessage getMessage(HttpServletRequest request, String url) {

		return new OAuthMessageImpl(OAuthServlet.getMessage(request, url));
	}

	public OAuthMessage getMessage(PortletRequest portletRequest) {

		return getMessage(portletRequest, null);
	}

	public OAuthMessage getMessage(PortletRequest portletRequest, String url) {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		return getMessage(request, url);
	}

	public void handleException(
		HttpServletRequest request, HttpServletResponse response,
		Exception exception, boolean sendBody)
		throws IOException, ServletException {

		String realm = Http.HTTP_WITH_SLASH;

		if (request.isSecure()) {
			realm = Http.HTTPS_WITH_SLASH;
		}

		realm += request.getLocalName();

		if (exception.getCause() != null) {
			exception = (Exception) exception.getCause();
		}

		OAuthServlet.handleException(response, exception, realm, sendBody);
	}

	public String randomizeToken(String token) {

		token =
			token.concat(
				PwdGenerator.getPassword(
					PwdGenerator.KEY1
					.concat(PwdGenerator.KEY2)
					.concat(PwdGenerator.KEY3), 12));

		return DigestUtils.md5Hex(token);
	}

	public void validateMessage(OAuthMessage message, OAuthAccessor accessor)
		throws OAuthException {

		_oAuthValidator.validateMessage(message, accessor);
	}

	private static final String _CACHE_NAME = OAuth_1_0_AImpl.class.getName();

	private static PortalCache<String, Serializable> _portalCache =
		MultiVMPoolUtil.getCache(_CACHE_NAME);

	private OAuthValidator _oAuthValidator;

}