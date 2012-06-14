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

package com.liferay.portal.oauth.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.oauth.model.OAuthApplications_Users;
import com.liferay.portal.oauth.model.impl.OAuthApplications_UsersImpl;
import com.liferay.portal.oauth.service.base.OAuthApplications_UsersLocalServiceBaseImpl;

/**
 * The implementation of the o auth applications_ users local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portal.oauth.service.OAuthApplications_UsersLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.oauth.service.base.OAuthApplications_UsersLocalServiceBaseImpl
 * @see com.liferay.portal.oauth.service.OAuthApplications_UsersLocalServiceUtil
 */
public class OAuthApplications_UsersLocalServiceImpl
	extends OAuthApplications_UsersLocalServiceBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.portal.oauth.service.OAuthApplications_UsersLocalServiceUtil} to access the o auth applications_ users local service.
	 */

	public OAuthApplications_Users updateOAuthApplications_Users(
			long oAuthApplicationId, long userId, boolean authorized)
		throws SystemException{

		OAuthApplications_Users oAuthApplications_users =
			oAuthApplications_UsersPersistence.fetchByA_U(
				oAuthApplicationId, userId);

		if (oAuthApplications_users == null) {
			oAuthApplications_users = new OAuthApplications_UsersImpl();
		}

		oAuthApplications_users.setAuthorized(true);

		oAuthApplications_users = updateOAuthApplications_Users(
			oAuthApplications_users);

		return oAuthApplications_users;
	}

	public OAuthApplications_Users updateOAuthApplications_Users(
			long oAuthApplicationId, long userId, String accessToken,
			String accessSecret)
		throws SystemException{

		OAuthApplications_Users oAuthApplications_users =
			oAuthApplications_UsersPersistence.fetchByA_U(
				oAuthApplicationId, userId);

		if (oAuthApplications_users == null) {
			oAuthApplications_users = new OAuthApplications_UsersImpl();
			oAuthApplications_users.setApplicationId(oAuthApplicationId);
			oAuthApplications_users.setUserId(userId);
			oAuthApplications_users.setAccessToken(accessToken);
			oAuthApplications_users.setAccessSecret(accessSecret);
		}

		oAuthApplications_users.setAccessToken(accessToken);
		oAuthApplications_users.setAccessSecret(accessSecret);

		oAuthApplications_users = updateOAuthApplications_Users(
			oAuthApplications_users);

		return oAuthApplications_users;
	}

}