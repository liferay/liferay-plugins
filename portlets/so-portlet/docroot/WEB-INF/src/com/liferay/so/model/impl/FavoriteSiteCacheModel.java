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

package com.liferay.so.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import com.liferay.so.model.FavoriteSite;

import java.io.Serializable;

/**
 * The cache model class for representing FavoriteSite in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see FavoriteSite
 * @generated
 */
public class FavoriteSiteCacheModel implements CacheModel<FavoriteSite>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{favoriteSiteId=");
		sb.append(favoriteSiteId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append("}");

		return sb.toString();
	}

	public FavoriteSite toEntityModel() {
		FavoriteSiteImpl favoriteSiteImpl = new FavoriteSiteImpl();

		favoriteSiteImpl.setFavoriteSiteId(favoriteSiteId);
		favoriteSiteImpl.setCompanyId(companyId);
		favoriteSiteImpl.setUserId(userId);
		favoriteSiteImpl.setGroupId(groupId);

		favoriteSiteImpl.resetOriginalValues();

		return favoriteSiteImpl;
	}

	public long favoriteSiteId;
	public long companyId;
	public long userId;
	public long groupId;
}