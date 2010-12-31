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

package com.liferay.opensocial.service.impl;

import com.liferay.opensocial.model.OAuthToken;
import com.liferay.opensocial.service.base.OAuthTokenLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Dennis Ju
 */
public class OAuthTokenLocalServiceImpl
	extends OAuthTokenLocalServiceBaseImpl {

	public OAuthToken addOAuthToken(
			long userId, long gadgetId, String serviceName, long moduleId,
			String accessToken, String tokenName, String tokenSecret,
			String sessionHandle, int expiration)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);
		Date now = new Date();

		long oAuthTokenId = counterLocalService.increment();

		OAuthToken oAuthToken = oAuthTokenPersistence.create(oAuthTokenId);

		oAuthToken.setCompanyId(user.getCompanyId());
		oAuthToken.setUserId(user.getUserId());
		oAuthToken.setUserName(user.getFullName());
		oAuthToken.setCreateDate(now);
		oAuthToken.setModifiedDate(now);
		oAuthToken.setGadgetId(gadgetId);
		oAuthToken.setServiceName(serviceName);
		oAuthToken.setModuleId(moduleId);
		oAuthToken.setAccessToken(accessToken);
		oAuthToken.setTokenName(tokenName);
		oAuthToken.setTokenSecret(tokenSecret);
		oAuthToken.setSessionHandle(sessionHandle);
		oAuthToken.setExpiration(expiration);

		oAuthTokenPersistence.update(oAuthToken, false);

		return oAuthToken;
	}

	public void deleteOAuthToken(
			long userId, long gadgetId, String serviceName, long moduleId,
			String tokenName)
		throws PortalException, SystemException {

		oAuthTokenPersistence.removeByU_G_S_M_T(
			userId, gadgetId, serviceName, moduleId, tokenName);
	}

	public void deleteOAuthTokens(long gadgetId, String serviceName)
		throws SystemException {

		oAuthTokenPersistence.removeByG_S(gadgetId, serviceName);
	}

	public OAuthToken getOAuthToken(
			long userId, long gadgetId, String serviceName, long moduleId,
			String tokenName)
		throws PortalException, SystemException {

		return oAuthTokenPersistence.findByU_G_S_M_T(
			userId, gadgetId, serviceName, moduleId, tokenName);
	}

	public List<OAuthToken> getOAuthTokens(long gadgetId, String serviceName)
		throws SystemException {

		return oAuthTokenPersistence.findByG_S(gadgetId, serviceName);
	}

}