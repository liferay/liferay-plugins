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

import com.liferay.hr.model.HRAsset;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRAsset in entity cache.
 *
 * @author Wesley Gong
 * @see HRAsset
 * @generated
 */
public class HRAssetCacheModel implements CacheModel<HRAsset> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{hrAssetId=");
		sb.append(hrAssetId);
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
		sb.append(", hrAssetDefinitionId=");
		sb.append(hrAssetDefinitionId);
		sb.append(", hrAssetTypeId=");
		sb.append(hrAssetTypeId);
		sb.append(", serialNumber=");
		sb.append(serialNumber);
		sb.append(", inactiveDate=");
		sb.append(inactiveDate);
		sb.append("}");

		return sb.toString();
	}

	public HRAsset toEntityModel() {
		HRAssetImpl hrAssetImpl = new HRAssetImpl();

		hrAssetImpl.setHrAssetId(hrAssetId);
		hrAssetImpl.setGroupId(groupId);
		hrAssetImpl.setCompanyId(companyId);
		hrAssetImpl.setUserId(userId);

		if (userName == null) {
			hrAssetImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrAssetImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrAssetImpl.setCreateDate(null);
		}
		else {
			hrAssetImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrAssetImpl.setModifiedDate(null);
		}
		else {
			hrAssetImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrAssetImpl.setHrAssetDefinitionId(hrAssetDefinitionId);
		hrAssetImpl.setHrAssetTypeId(hrAssetTypeId);

		if (serialNumber == null) {
			hrAssetImpl.setSerialNumber(StringPool.BLANK);
		}
		else {
			hrAssetImpl.setSerialNumber(serialNumber);
		}

		if (inactiveDate == Long.MIN_VALUE) {
			hrAssetImpl.setInactiveDate(null);
		}
		else {
			hrAssetImpl.setInactiveDate(new Date(inactiveDate));
		}

		hrAssetImpl.resetOriginalValues();

		return hrAssetImpl;
	}

	public long hrAssetId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrAssetDefinitionId;
	public long hrAssetTypeId;
	public String serialNumber;
	public long inactiveDate;
}