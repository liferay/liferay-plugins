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

package com.liferay.sync.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.sync.model.SyncDevice;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SyncDevice in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDevice
 * @generated
 */
@ProviderType
public class SyncDeviceCacheModel implements CacheModel<SyncDevice>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncDeviceCacheModel)) {
			return false;
		}

		SyncDeviceCacheModel syncDeviceCacheModel = (SyncDeviceCacheModel)obj;

		if (syncDeviceId == syncDeviceCacheModel.syncDeviceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, syncDeviceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", syncDeviceId=");
		sb.append(syncDeviceId);
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
		sb.append(", type=");
		sb.append(type);
		sb.append(", buildNumber=");
		sb.append(buildNumber);
		sb.append(", featureSet=");
		sb.append(featureSet);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SyncDevice toEntityModel() {
		SyncDeviceImpl syncDeviceImpl = new SyncDeviceImpl();

		if (uuid == null) {
			syncDeviceImpl.setUuid(StringPool.BLANK);
		}
		else {
			syncDeviceImpl.setUuid(uuid);
		}

		syncDeviceImpl.setSyncDeviceId(syncDeviceId);
		syncDeviceImpl.setCompanyId(companyId);
		syncDeviceImpl.setUserId(userId);

		if (userName == null) {
			syncDeviceImpl.setUserName(StringPool.BLANK);
		}
		else {
			syncDeviceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			syncDeviceImpl.setCreateDate(null);
		}
		else {
			syncDeviceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			syncDeviceImpl.setModifiedDate(null);
		}
		else {
			syncDeviceImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (type == null) {
			syncDeviceImpl.setType(StringPool.BLANK);
		}
		else {
			syncDeviceImpl.setType(type);
		}

		syncDeviceImpl.setBuildNumber(buildNumber);
		syncDeviceImpl.setFeatureSet(featureSet);
		syncDeviceImpl.setStatus(status);

		syncDeviceImpl.resetOriginalValues();

		return syncDeviceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		syncDeviceId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		type = objectInput.readUTF();

		buildNumber = objectInput.readLong();

		featureSet = objectInput.readInt();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(syncDeviceId);

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

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeLong(buildNumber);

		objectOutput.writeInt(featureSet);

		objectOutput.writeInt(status);
	}

	public String uuid;
	public long syncDeviceId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String type;
	public long buildNumber;
	public int featureSet;
	public int status;
}