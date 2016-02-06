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

import com.liferay.sync.model.SyncDLFileVersionDiff;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SyncDLFileVersionDiff in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLFileVersionDiff
 * @generated
 */
@ProviderType
public class SyncDLFileVersionDiffCacheModel implements CacheModel<SyncDLFileVersionDiff>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncDLFileVersionDiffCacheModel)) {
			return false;
		}

		SyncDLFileVersionDiffCacheModel syncDLFileVersionDiffCacheModel = (SyncDLFileVersionDiffCacheModel)obj;

		if (syncDLFileVersionDiffId == syncDLFileVersionDiffCacheModel.syncDLFileVersionDiffId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, syncDLFileVersionDiffId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{syncDLFileVersionDiffId=");
		sb.append(syncDLFileVersionDiffId);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", sourceFileVersionId=");
		sb.append(sourceFileVersionId);
		sb.append(", targetFileVersionId=");
		sb.append(targetFileVersionId);
		sb.append(", dataFileEntryId=");
		sb.append(dataFileEntryId);
		sb.append(", size=");
		sb.append(size);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SyncDLFileVersionDiff toEntityModel() {
		SyncDLFileVersionDiffImpl syncDLFileVersionDiffImpl = new SyncDLFileVersionDiffImpl();

		syncDLFileVersionDiffImpl.setSyncDLFileVersionDiffId(syncDLFileVersionDiffId);
		syncDLFileVersionDiffImpl.setFileEntryId(fileEntryId);
		syncDLFileVersionDiffImpl.setSourceFileVersionId(sourceFileVersionId);
		syncDLFileVersionDiffImpl.setTargetFileVersionId(targetFileVersionId);
		syncDLFileVersionDiffImpl.setDataFileEntryId(dataFileEntryId);
		syncDLFileVersionDiffImpl.setSize(size);

		if (expirationDate == Long.MIN_VALUE) {
			syncDLFileVersionDiffImpl.setExpirationDate(null);
		}
		else {
			syncDLFileVersionDiffImpl.setExpirationDate(new Date(expirationDate));
		}

		syncDLFileVersionDiffImpl.resetOriginalValues();

		return syncDLFileVersionDiffImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		syncDLFileVersionDiffId = objectInput.readLong();

		fileEntryId = objectInput.readLong();

		sourceFileVersionId = objectInput.readLong();

		targetFileVersionId = objectInput.readLong();

		dataFileEntryId = objectInput.readLong();

		size = objectInput.readLong();
		expirationDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(syncDLFileVersionDiffId);

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeLong(sourceFileVersionId);

		objectOutput.writeLong(targetFileVersionId);

		objectOutput.writeLong(dataFileEntryId);

		objectOutput.writeLong(size);
		objectOutput.writeLong(expirationDate);
	}

	public long syncDLFileVersionDiffId;
	public long fileEntryId;
	public long sourceFileVersionId;
	public long targetFileVersionId;
	public long dataFileEntryId;
	public long size;
	public long expirationDate;
}