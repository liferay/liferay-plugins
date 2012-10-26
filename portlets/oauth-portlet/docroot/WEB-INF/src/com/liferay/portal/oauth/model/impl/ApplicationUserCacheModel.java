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

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ApplicationUser in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationUser
 * @generated
 */
public class ApplicationUserCacheModel implements CacheModel<ApplicationUser>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

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

		applicationUserImpl.resetOriginalValues();

		return applicationUserImpl;
	}

	public void readExternal(ObjectInput objectInput) throws IOException {
		oaauId = objectInput.readLong();
		userId = objectInput.readLong();
		applicationId = objectInput.readLong();
		accessToken = objectInput.readUTF();
		accessSecret = objectInput.readUTF();
	}

	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(oaauId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(applicationId);

		if (accessToken == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accessToken);
		}

		if (accessSecret == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accessSecret);
		}
	}

	public long oaauId;
	public long userId;
	public long applicationId;
	public String accessToken;
	public String accessSecret;
}