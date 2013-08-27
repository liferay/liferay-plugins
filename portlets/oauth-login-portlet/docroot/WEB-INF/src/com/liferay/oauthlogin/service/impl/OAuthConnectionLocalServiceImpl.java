/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.oauthlogin.service.impl;

import com.liferay.oauthlogin.AccessTokenURLException;
import com.liferay.oauthlogin.AuthorizeURLException;
import com.liferay.oauthlogin.OAuthConnectionNameException;
import com.liferay.oauthlogin.RedirectURLException;
import com.liferay.oauthlogin.RequestTokenURLException;
import com.liferay.oauthlogin.SocialAccountIdURLException;
import com.liferay.oauthlogin.model.OAuthConnection;
import com.liferay.oauthlogin.service.base.OAuthConnectionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;

import java.io.File;
import java.io.IOException;

import java.util.Date;
import java.util.List;

/**
 * @author Andy Yang
 * @author Terry Jia
 */
public class OAuthConnectionLocalServiceImpl
	extends OAuthConnectionLocalServiceBaseImpl {

	public OAuthConnection addOAuthConnection(
			boolean enabled, String name, String description, int oAuthVersion,
			String key, String secret, String scope, String authorizeURL,
			String accessTokenURL, int accessTokenVerb,
			int accessTokenExtractorType, String requestTokenURL,
			int requestTokenVerb, int signatureServiceType, String redirectURL,
			String socialAccountIdURL, int socialAccountIdURLVerb,
			String socialAccountIdField, int socialAccountIdType,
			String socialAccountIdScript, File icon,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(
			name, authorizeURL, accessTokenURL, redirectURL, requestTokenURL,
			socialAccountIdURL);

		long OAuthConnectionId = counterLocalService.increment();

		byte[] iconBytes = null;

		try {
			iconBytes = FileUtil.getBytes(icon);
		}
		catch (IOException ioe) {
		}

		OAuthConnection oAuthConnection = oAuthConnectionPersistence.create(
			OAuthConnectionId);

		oAuthConnection.setUserId(serviceContext.getUserId());
		oAuthConnection.setCompanyId(serviceContext.getCompanyId());
		oAuthConnection.setCreateDate(serviceContext.getCreateDate(now));
		oAuthConnection.setModifiedDate(serviceContext.getModifiedDate(now));
		oAuthConnection.setEnabled(enabled);
		oAuthConnection.setName(name);
		oAuthConnection.setDescription(description);
		oAuthConnection.setOAuthVersion(oAuthVersion);
		oAuthConnection.setKey(key);
		oAuthConnection.setSecret(secret);
		oAuthConnection.setScope(scope);
		oAuthConnection.setAuthorizeURL(authorizeURL);
		oAuthConnection.setAccessTokenURL(accessTokenURL);
		oAuthConnection.setAccessTokenVerb(accessTokenVerb);
		oAuthConnection.setAccessTokenExtractorType(accessTokenExtractorType);
		oAuthConnection.setRequestTokenURL(requestTokenURL);
		oAuthConnection.setRequestTokenVerb(requestTokenVerb);
		oAuthConnection.setSignatureServiceType(signatureServiceType);
		oAuthConnection.setRedirectURL(redirectURL);
		oAuthConnection.setSocialAccountIdURL(socialAccountIdURL);
		oAuthConnection.setSocialAccountIdURLVerb(socialAccountIdURLVerb);
		oAuthConnection.setSocialAccountIdField(socialAccountIdField);
		oAuthConnection.setSocialAccountIdType(socialAccountIdType);
		oAuthConnection.setSocialAccountIdScript(socialAccountIdScript);

		if (iconBytes != null) {
			oAuthConnection.setIconId(counterLocalService.increment());

			imageLocalService.updateImage(
				oAuthConnection.getIconId(), iconBytes);
		}

		return oAuthConnectionPersistence.update(oAuthConnection);
	}

	public OAuthConnection deleteOAuthConnection(long oAuthConnectionId)
		throws PortalException, SystemException {

		OAuthConnection oAuthConnection =
			oAuthConnectionPersistence.findByPrimaryKey(oAuthConnectionId);

		long iconId = oAuthConnection.getIconId();

		imageLocalService.deleteImage(iconId);

		return super.deleteOAuthConnection(oAuthConnectionId);
	}

	public List<OAuthConnection> getOAuthConnectionsEnabled(boolean enabled)
		throws SystemException {

		return oAuthConnectionPersistence.findByEnabled(enabled);
	}

	public OAuthConnection updateOAuthConnection(
			long oAuthConnectionId, boolean enabled, String name,
			String description, int oAuthVersion, String key, String secret,
			String scope, String authorizeURL, String accessTokenURL,
			int accessTokenVerb, int accessTokenExtractorType,
			String requestTokenURL, int requestTokenVerb,
			int signatureServiceType, String redirectURL,
			String socialAccountIdURL, int socialAccountIdURLVerb,
			String socialAccountIdField, int socialAccountIdType,
			String socialAccountIdScript, File icon,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(
			name, authorizeURL, accessTokenURL, redirectURL, requestTokenURL,
			socialAccountIdURL);

		OAuthConnection oAuthConnection =
			oAuthConnectionPersistence.findByPrimaryKey(oAuthConnectionId);

		byte[] iconBytes = null;

		try {
			iconBytes = FileUtil.getBytes(icon);
		}
		catch (IOException ioe) {
		}

		oAuthConnection.setUserId(serviceContext.getUserId());
		oAuthConnection.setModifiedDate(serviceContext.getModifiedDate(now));
		oAuthConnection.setEnabled(enabled);
		oAuthConnection.setName(name);
		oAuthConnection.setDescription(description);
		oAuthConnection.setOAuthVersion(oAuthVersion);
		oAuthConnection.setKey(key);
		oAuthConnection.setSecret(secret);
		oAuthConnection.setScope(scope);
		oAuthConnection.setAuthorizeURL(authorizeURL);
		oAuthConnection.setAccessTokenURL(accessTokenURL);
		oAuthConnection.setAccessTokenVerb(accessTokenVerb);
		oAuthConnection.setAccessTokenExtractorType(accessTokenExtractorType);
		oAuthConnection.setRequestTokenURL(requestTokenURL);
		oAuthConnection.setRequestTokenVerb(requestTokenVerb);
		oAuthConnection.setSignatureServiceType(signatureServiceType);
		oAuthConnection.setRedirectURL(redirectURL);
		oAuthConnection.setSocialAccountIdURL(socialAccountIdURL);
		oAuthConnection.setSocialAccountIdURLVerb(socialAccountIdURLVerb);
		oAuthConnection.setSocialAccountIdField(socialAccountIdField);
		oAuthConnection.setSocialAccountIdType(socialAccountIdType);
		oAuthConnection.setSocialAccountIdScript(socialAccountIdScript);

		if (iconBytes != null) {
			if (oAuthConnection.getIconId() == 0) {
				oAuthConnection.setIconId(counterLocalService.increment());
			}

			imageLocalService.updateImage(
				oAuthConnection.getIconId(), iconBytes);
		}

		oAuthConnectionPersistence.update(oAuthConnection);

		return oAuthConnection;
	}

	protected void validate(
			String name, String authorizeURL, String accessTokenURL,
			String redirectURL, String requestTokenURL,
			String socialAccountIdURL)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new OAuthConnectionNameException();
		}

		if (Validator.isNotNull(authorizeURL) &&
			!Validator.isUrl(authorizeURL)) {

			throw new AuthorizeURLException();
		}

		if (Validator.isNotNull(accessTokenURL) &&
			!Validator.isUrl(accessTokenURL)) {

			throw new AccessTokenURLException();
		}

		if (Validator.isNotNull(redirectURL) && !Validator.isUrl(redirectURL)) {
			throw new RedirectURLException();
		}

		if (Validator.isNotNull(requestTokenURL) &&
			!Validator.isUrl(requestTokenURL)) {

			throw new RequestTokenURLException();
		}

		if (Validator.isNotNull(socialAccountIdURL) &&
			!Validator.isUrl(socialAccountIdURL)) {

			throw new SocialAccountIdURLException();
		}
	}

}