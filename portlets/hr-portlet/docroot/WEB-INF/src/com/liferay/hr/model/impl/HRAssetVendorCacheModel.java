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

import com.liferay.hr.model.HRAssetVendor;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRAssetVendor in entity cache.
 *
 * @author Wesley Gong
 * @see HRAssetVendor
 * @generated
 */
public class HRAssetVendorCacheModel implements CacheModel<HRAssetVendor> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{hrAssetVendorId=");
		sb.append(hrAssetVendorId);
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

	public HRAssetVendor toEntityModel() {
		HRAssetVendorImpl hrAssetVendorImpl = new HRAssetVendorImpl();

		hrAssetVendorImpl.setHrAssetVendorId(hrAssetVendorId);
		hrAssetVendorImpl.setGroupId(groupId);
		hrAssetVendorImpl.setCompanyId(companyId);
		hrAssetVendorImpl.setUserId(userId);

		if (userName == null) {
			hrAssetVendorImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrAssetVendorImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrAssetVendorImpl.setCreateDate(null);
		}
		else {
			hrAssetVendorImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrAssetVendorImpl.setModifiedDate(null);
		}
		else {
			hrAssetVendorImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			hrAssetVendorImpl.setName(StringPool.BLANK);
		}
		else {
			hrAssetVendorImpl.setName(name);
		}

		if (description == null) {
			hrAssetVendorImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrAssetVendorImpl.setDescription(description);
		}

		hrAssetVendorImpl.resetOriginalValues();

		return hrAssetVendorImpl;
	}

	public long hrAssetVendorId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
}