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

package com.liferay.so.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.so.model.FavoriteSite;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing FavoriteSite in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see FavoriteSite
 * @generated
 */
@ProviderType
public class FavoriteSiteCacheModel implements CacheModel<FavoriteSite>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FavoriteSiteCacheModel)) {
			return false;
		}

		FavoriteSiteCacheModel favoriteSiteCacheModel = (FavoriteSiteCacheModel)obj;

		if (favoriteSiteId == favoriteSiteCacheModel.favoriteSiteId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, favoriteSiteId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{favoriteSiteId=");
		sb.append(favoriteSiteId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FavoriteSite toEntityModel() {
		FavoriteSiteImpl favoriteSiteImpl = new FavoriteSiteImpl();

		favoriteSiteImpl.setFavoriteSiteId(favoriteSiteId);
		favoriteSiteImpl.setGroupId(groupId);
		favoriteSiteImpl.setCompanyId(companyId);
		favoriteSiteImpl.setUserId(userId);

		favoriteSiteImpl.resetOriginalValues();

		return favoriteSiteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		favoriteSiteId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(favoriteSiteId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
	}

	public long favoriteSiteId;
	public long groupId;
	public long companyId;
	public long userId;
}