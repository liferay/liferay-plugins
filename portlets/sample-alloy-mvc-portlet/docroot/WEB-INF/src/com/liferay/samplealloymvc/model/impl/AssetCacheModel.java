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

package com.liferay.samplealloymvc.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.samplealloymvc.model.Asset;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Asset in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Asset
 * @generated
 */
@ProviderType
public class AssetCacheModel implements CacheModel<Asset>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetCacheModel)) {
			return false;
		}

		AssetCacheModel assetCacheModel = (AssetCacheModel)obj;

		if (assetId == assetCacheModel.assetId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, assetId);
	}

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

	@Override
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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		assetId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		definitionId = objectInput.readLong();
		serialNumber = objectInput.readUTF();
		inactiveDate = objectInput.readLong();

		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(assetId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(definitionId);

		if (serialNumber == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(serialNumber);
		}

		objectOutput.writeLong(inactiveDate);

		objectOutput.writeBoolean(active);
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