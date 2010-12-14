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
 * The implementation of the o auth token local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.opensocial.service.OAuthTokenLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.opensocial.service.base.OAuthTokenLocalServiceBaseImpl
 * @see com.liferay.opensocial.service.OAuthTokenLocalServiceUtil
 */
public class OAuthTokenLocalServiceImpl
	extends OAuthTokenLocalServiceBaseImpl {

	public OAuthToken addOAuthToken(
			long userId, long gadgetId, long moduleId, String serviceName,
			String tokenName, String accessToken, String tokenSecret,
			String sessionHandle, long tokenExpireMillis)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);

		Date now = new Date();

		long oAuthTokenId = counterLocalService.increment();

		OAuthToken oAuthToken = oAuthTokenPersistence.create(oAuthTokenId);

		oAuthToken.setCompanyId(user.getCompanyId());
		oAuthToken.setCreateDate(now);
		oAuthToken.setModifiedDate(now);
		oAuthToken.setUserId(userId);
		oAuthToken.setGadgetId(gadgetId);
		oAuthToken.setModuleId(moduleId);
		oAuthToken.setServiceName(serviceName);
		oAuthToken.setTokenName(tokenName);
		oAuthToken.setAccessToken(accessToken);
		oAuthToken.setTokenSecret(tokenSecret);
		oAuthToken.setSessionHandle(sessionHandle);
		oAuthToken.setTokenExpireMillis(tokenExpireMillis);

		oAuthTokenPersistence.update(oAuthToken, false);

		return oAuthToken;
	}

	public void deleteOAuthToken(
			long userId, long gadgetId, long moduleId, String serviceName,
			String tokenName)
		throws PortalException, SystemException {

		OAuthToken oAuthToken = oAuthTokenPersistence.findByU_G_M_S_T(
				userId, gadgetId, moduleId, serviceName, tokenName);

		oAuthTokenPersistence.remove(oAuthToken);
	}

	public void deleteOAuthTokens(
			long gadgetId, String serviceName)
		throws SystemException {

		oAuthTokenPersistence.removeByG_S(gadgetId, serviceName);
	}

	public OAuthToken getOAuthToken(
			long userId, long gadgetId, long moduleId, String serviceName,
			String tokenName)
		throws PortalException, SystemException {

		return oAuthTokenPersistence.findByU_G_M_S_T(
			userId, gadgetId, moduleId, serviceName, tokenName);
	}

	public List<OAuthToken> getOAuthTokens(long gadgetId, String serviceName)
		throws SystemException {

		return oAuthTokenPersistence.findByG_S(gadgetId, serviceName);
	}

}