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

package com.liferay.ams.model.impl;

import com.liferay.ams.model.Asset;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Asset in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Asset
 * @generated
 */
public class AssetCacheModel implements CacheModel<Asset>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{assetId=");
		sb.append(assetId);
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
		sb.append(", definitionId=");
		sb.append(definitionId);
		sb.append(", serialNumber=");
		sb.append(serialNumber);
		sb.append(", inactiveDate=");
		sb.append(inactiveDate);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	public Asset toEntityModel() {
		AssetImpl assetImpl = new AssetImpl();

		assetImpl.setAssetId(assetId);
		assetImpl.setCompanyId(companyId);
		assetImpl.setUserId(userId);

		if (userName == null) {
			assetImpl.setUserName(StringPool.BLANK);
		}
		else {
			assetImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetImpl.setCreateDate(null);
		}
		else {
			assetImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			assetImpl.setModifiedDate(null);
		}
		else {
			assetImpl.setModifiedDate(new Date(modifiedDate));
		}

		assetImpl.setDefinitionId(definitionId);

		if (serialNumber == null) {
			assetImpl.setSerialNumber(StringPool.BLANK);
		}
		else {
			assetImpl.setSerialNumber(serialNumber);
		}

		if (inactiveDate == Long.MIN_VALUE) {
			assetImpl.setInactiveDate(null);
		}
		else {
			assetImpl.setInactiveDate(new Date(inactiveDate));
		}

		assetImpl.setActive(active);

		assetImpl.resetOriginalValues();

		return assetImpl;
	}

	public long assetId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long definitionId;
	public String serialNumber;
	public long inactiveDate;
	public boolean active;
}