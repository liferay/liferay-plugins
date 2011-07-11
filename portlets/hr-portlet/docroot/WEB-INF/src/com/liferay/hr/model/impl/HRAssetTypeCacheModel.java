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

package com.liferay.hr.model.impl;

import com.liferay.hr.model.HRAssetType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRAssetType in entity cache.
 *
 * @author Wesley Gong
 * @see HRAssetType
 * @generated
 */
public class HRAssetTypeCacheModel implements CacheModel<HRAssetType> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{hrAssetTypeId=");
		sb.append(hrAssetTypeId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	public HRAssetType toEntityModel() {
		HRAssetTypeImpl hrAssetTypeImpl = new HRAssetTypeImpl();

		hrAssetTypeImpl.setHrAssetTypeId(hrAssetTypeId);
		hrAssetTypeImpl.setGroupId(groupId);
		hrAssetTypeImpl.setCompanyId(companyId);
		hrAssetTypeImpl.setUserId(userId);

		if (userName == null) {
			hrAssetTypeImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrAssetTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrAssetTypeImpl.setCreateDate(null);
		}
		else {
			hrAssetTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrAssetTypeImpl.setModifiedDate(null);
		}
		else {
			hrAssetTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			hrAssetTypeImpl.setName(StringPool.BLANK);
		}
		else {
			hrAssetTypeImpl.setName(name);
		}

		if (description == null) {
			hrAssetTypeImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrAssetTypeImpl.setDescription(description);
		}

		hrAssetTypeImpl.resetOriginalValues();

		return hrAssetTypeImpl;
	}

	public long hrAssetTypeId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
}