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

package com.liferay.opensocial.shindig.oauth;

import com.google.inject.Singleton;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.model.OAuthConsumer;
import com.liferay.opensocial.model.OAuthConsumerConstants;
import com.liferay.opensocial.model.OAuthToken;
import com.liferay.opensocial.model.impl.GadgetConstants;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.opensocial.service.OAuthConsumerLocalServiceUtil;
import com.liferay.opensocial.service.OAuthTokenLocalServiceUtil;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.opensocial.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import net.oauth.OAuth;
import net.oauth.OAuthServiceProvider;
import net.oauth.signature.RSA_SHA1;

import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.gadgets.GadgetException;
import org.apache.shindig.gadgets.oauth.OAuthStore;

/**
 * @author Dennis Ju
 */
@Singleton
public class LiferayOAuthStore implements OAuthStore {

	public LiferayOAuthStore(OAuthConsumer oAuthConsumer) {
		_oAuthConsumer = oAuthConsumer;
	}

	public ConsumerInfo getConsumerKeyAndSecret(
			SecurityToken securityToken, String serviceName,
			OAuthServiceProvider oAuthServiceProvider)
		throws GadgetException {

		OAuthConsumer oAuthConsumer = getOAuthConsumer(
			securityToken, serviceName);

		if (oAuthConsumer == null) {
			throw new GadgetException(
				GadgetException.Code.INTERNAL_SERVER_ERROR,
				"No key for gadget " + securityToken.getAppUrl() +
					" and service " + serviceName);
		}

		net.oauth.OAuthConsumer netOAuthConsumer = null;

		String keyType = oAuthConsumer.getKeyType();

		if (keyType.equals(OAuthConsumerConstants.KEY_TYPE_RSA_PRIVATE)) {
			netOAuthConsumer = new net.oauth.OAuthConsumer(
				null, oAuthConsumer.getConsumerKey(), null,
				oAuthServiceProvider);

			netOAuthConsumer.setProperty(
				OAuth.OAUTH_SIGNATURE_METHOD, OAuth.RSA_SHA1);
			netOAuthConsumer.setProperty(
				RSA_SHA1.PRIVATE_KEY, oAuthConsumer.getConsumerSecret());
		}
		else {
			netOAuthConsumer = new net.oauth.OAuthConsumer(
				null, oAuthConsumer.getConsumerKey(),
				oAuthConsumer.getConsumerSecret(), oAuthServiceProvider);

			netOAuthConsumer.setProperty(
				OAuth.OAUTH_SIGNATURE_METHOD, OAuth.HMAC_SHA1);
		}

		String keyName = oAuthConsumer.getKeyName();

		String callbackURL = ShindigUtil.transformURL(_callbackURL);

		return new ConsumerInfo(netOAuthConsumer, keyName, callbackURL);
	}

	public TokenInfo getTokenInfo(
			SecurityToken securityToken, ConsumerInfo consumerInfo,
			String serviceName, String tokenName)
		throws GadgetException {

		OAuthToken oAuthToken = getOAuthToken(
			securityToken, serviceName, tokenName);

		if (oAuthToken == null) {
			return null;
		}

		TokenInfo tokenInfo = new TokenInfo(
			oAuthToken.getAccessToken(), oAuthToken.getTokenSecret(),
			oAuthToken.getSessionHandle(), oAuthToken.getExpiration());

		return tokenInfo;
	}

	public void removeToken(
			SecurityToken securityToken, ConsumerInfo consumerInfo,
			String serviceName, String tokenName)
		throws GadgetException {

		OAuthToken oAuthToken = getOAuthToken(
			securityToken, serviceName, tokenName);

		if (oAuthToken == null) {
			return;
		}

		try {
			OAuthTokenLocalServiceUtil.deleteOAuthToken(oAuthToken);
		}
		catch (Exception e) {
			throw new GadgetException(
				GadgetException.Code.INTERNAL_SERVER_ERROR, e);
		}
	}

	public void setTokenInfo(
			SecurityToken securityToken, ConsumerInfo consumerInfo,
			String serviceName, String tokenName, TokenInfo tokenInfo)
		throws GadgetException {

		long userId = GetterUtil.getLong(securityToken.getViewerId());

		User user = null;

		try {
			user = UserLocalServiceUtil.getUser(userId);
		}
		catch (Exception e) {
			throw new GadgetException(
				GadgetException.Code.INTERNAL_SERVER_ERROR, e);
		}

		Gadget gadget = null;

		try {
			gadget = GadgetLocalServiceUtil.fetchGadget(
				user.getCompanyId(), securityToken.getAppUrl());
		}
		catch (SystemException se) {
			throw new GadgetException(
				GadgetException.Code.INTERNAL_SERVER_ERROR, se);
		}

		String gadgetKey = StringPool.BLANK;

		if (gadget == null) {
			gadgetKey = GadgetConstants.toAdhocGadgetKey(
				securityToken.getModuleId());
		}
		else {
			gadgetKey = GadgetConstants.toPublishedGadgetKey(
				gadget.getGadgetId());
		}

		try {
			OAuthTokenLocalServiceUtil.addOAuthToken(
				userId, gadgetKey, serviceName, securityToken.getModuleId(),
				tokenInfo.getAccessToken(), tokenName,
				tokenInfo.getTokenSecret(), tokenInfo.getSessionHandle(),
				tokenInfo.getTokenExpireMillis());
		}
		catch (Exception e) {
			throw new GadgetException(
				GadgetException.Code.INTERNAL_SERVER_ERROR, e);
		}
	}

	protected OAuthConsumer getOAuthConsumer(
			SecurityToken securityToken, String serviceName)
		throws GadgetException {

		OAuthConsumer oAuthConsumer = null;

		try {
			oAuthConsumer = OAuthConsumerLocalServiceUtil.fetchOAuthConsumer(
				securityToken.getAppId(), serviceName);
		}
		catch (SystemException se) {
			throw new GadgetException(
				GadgetException.Code.INTERNAL_SERVER_ERROR, se);
		}

		if (oAuthConsumer == null) {
			return _oAuthConsumer;
		}

		if (oAuthConsumer.getKeyType().equals(
				OAuthConsumerConstants.KEY_TYPE_RSA_PRIVATE)) {

			if (_oAuthConsumer == null) {
				throw new GadgetException(
					GadgetException.Code.INTERNAL_SERVER_ERROR,
					"No OAuth key specified");
			}

			oAuthConsumer.setConsumerSecret(_oAuthConsumer.getConsumerSecret());
		}

		return oAuthConsumer;
	}

	protected OAuthToken getOAuthToken(
			SecurityToken securityToken, String serviceName, String tokenName)
		throws GadgetException {

		long userId = GetterUtil.getLong(securityToken.getViewerId());

		OAuthToken oAuthToken = null;

		try {
			oAuthToken = OAuthTokenLocalServiceUtil.fetchOAuthToken(
				userId, securityToken.getAppId(), serviceName,
				securityToken.getModuleId(), tokenName);
		}
		catch (SystemException se) {
			throw new GadgetException(
				GadgetException.Code.INTERNAL_SERVER_ERROR, se);
		}

		return oAuthToken;
	}

	private String _callbackURL = PortletPropsValues.SHINDIG_OAUTH_CALLBACK_URL;
	private OAuthConsumer _oAuthConsumer;

}