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

import com.liferay.hr.model.HRAssetProduct;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRAssetProduct in entity cache.
 *
 * @author Wesley Gong
 * @see HRAssetProduct
 * @generated
 */
public class HRAssetProductCacheModel implements CacheModel<HRAssetProduct> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{hrAssetProductId=");
		sb.append(hrAssetProductId);
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
		sb.append(", hrAssetVendorId=");
		sb.append(hrAssetVendorId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	public HRAssetProduct toEntityModel() {
		HRAssetProductImpl hrAssetProductImpl = new HRAssetProductImpl();

		hrAssetProductImpl.setHrAssetProductId(hrAssetProductId);
		hrAssetProductImpl.setGroupId(groupId);
		hrAssetProductImpl.setCompanyId(companyId);
		hrAssetProductImpl.setUserId(userId);

		if (userName == null) {
			hrAssetProductImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrAssetProductImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrAssetProductImpl.setCreateDate(null);
		}
		else {
			hrAssetProductImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrAssetProductImpl.setModifiedDate(null);
		}
		else {
			hrAssetProductImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrAssetProductImpl.setHrAssetVendorId(hrAssetVendorId);

		if (name == null) {
			hrAssetProductImpl.setName(StringPool.BLANK);
		}
		else {
			hrAssetProductImpl.setName(name);
		}

		if (description == null) {
			hrAssetProductImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrAssetProductImpl.setDescription(description);
		}

		hrAssetProductImpl.resetOriginalValues();

		return hrAssetProductImpl;
	}

	public long hrAssetProductId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrAssetVendorId;
	public String name;
	public String description;
}