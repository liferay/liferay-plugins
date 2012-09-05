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
import com.liferay.portal.oauth.model.ApplicationUser;

import java.io.Serializable;

/**
 * The cache model class for representing ApplicationUser in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationUser
 * @generated
 */
public class ApplicationUserCacheModel implements CacheModel<ApplicationUser>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{oaauId=");
		sb.append(oaauId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", applicationId=");
		sb.append(applicationId);
		sb.append(", accessToken=");
		sb.append(accessToken);
		sb.append(", accessSecret=");
		sb.append(accessSecret);
		sb.append(", authorized=");
		sb.append(authorized);
		sb.append("}");

		return sb.toString();
	}

	public ApplicationUser toEntityModel() {
		ApplicationUserImpl applicationUserImpl = new ApplicationUserImpl();

		applicationUserImpl.setOaauId(oaauId);
		applicationUserImpl.setUserId(userId);
		applicationUserImpl.setApplicationId(applicationId);

		if (accessToken == null) {
			applicationUserImpl.setAccessToken(StringPool.BLANK);
		}
		else {
			applicationUserImpl.setAccessToken(accessToken);
		}

		if (accessSecret == null) {
			applicationUserImpl.setAccessSecret(StringPool.BLANK);
		}
		else {
			applicationUserImpl.setAccessSecret(accessSecret);
		}

		applicationUserImpl.setAuthorized(authorized);

		applicationUserImpl.resetOriginalValues();

		return applicationUserImpl;
	}

	public long oaauId;
	public long userId;
	public long applicationId;
	public String accessToken;
	public String accessSecret;
	public boolean authorized;
}