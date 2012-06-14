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

package com.liferay.portal.oauth.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.oauth.model.OAuthApplications_Users;

import java.io.Serializable;

/**
 * The cache model class for representing OAuthApplications_Users in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OAuthApplications_Users
 * @generated
 */
public class OAuthApplications_UsersCacheModel implements CacheModel<OAuthApplications_Users>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{oaauid=");
		sb.append(oaauid);
		sb.append(", accessToken=");
		sb.append(accessToken);
		sb.append(", accessSecret=");
		sb.append(accessSecret);
		sb.append(", applicationId=");
		sb.append(applicationId);
		sb.append(", authorized=");
		sb.append(authorized);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	public OAuthApplications_Users toEntityModel() {
		OAuthApplications_UsersImpl oAuthApplications_UsersImpl = new OAuthApplications_UsersImpl();

		oAuthApplications_UsersImpl.setOaauid(oaauid);

		if (accessToken == null) {
			oAuthApplications_UsersImpl.setAccessToken(StringPool.BLANK);
		}
		else {
			oAuthApplications_UsersImpl.setAccessToken(accessToken);
		}

		if (accessSecret == null) {
			oAuthApplications_UsersImpl.setAccessSecret(StringPool.BLANK);
		}
		else {
			oAuthApplications_UsersImpl.setAccessSecret(accessSecret);
		}

		oAuthApplications_UsersImpl.setApplicationId(applicationId);
		oAuthApplications_UsersImpl.setAuthorized(authorized);
		oAuthApplications_UsersImpl.setUserId(userId);

		oAuthApplications_UsersImpl.resetOriginalValues();

		return oAuthApplications_UsersImpl;
	}

	public long oaauid;
	public String accessToken;
	public String accessSecret;
	public long applicationId;
	public boolean authorized;
	public long userId;
}