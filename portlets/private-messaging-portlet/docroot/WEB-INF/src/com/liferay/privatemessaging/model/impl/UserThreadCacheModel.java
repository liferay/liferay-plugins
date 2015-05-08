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

package com.liferay.privatemessaging.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.privatemessaging.model.UserThread;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserThread in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserThread
 * @generated
 */
@ProviderType
public class UserThreadCacheModel implements CacheModel<UserThread>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserThreadCacheModel)) {
			return false;
		}

		UserThreadCacheModel userThreadCacheModel = (UserThreadCacheModel)obj;

		if (userThreadId == userThreadCacheModel.userThreadId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userThreadId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{userThreadId=");
		sb.append(userThreadId);
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
		sb.append(", mbThreadId=");
		sb.append(mbThreadId);
		sb.append(", topMBMessageId=");
		sb.append(topMBMessageId);
		sb.append(", read=");
		sb.append(read);
		sb.append(", deleted=");
		sb.append(deleted);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserThread toEntityModel() {
		UserThreadImpl userThreadImpl = new UserThreadImpl();

		userThreadImpl.setUserThreadId(userThreadId);
		userThreadImpl.setCompanyId(companyId);
		userThreadImpl.setUserId(userId);

		if (userName == null) {
			userThreadImpl.setUserName(StringPool.BLANK);
		}
		else {
			userThreadImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			userThreadImpl.setCreateDate(null);
		}
		else {
			userThreadImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			userThreadImpl.setModifiedDate(null);
		}
		else {
			userThreadImpl.setModifiedDate(new Date(modifiedDate));
		}

		userThreadImpl.setMbThreadId(mbThreadId);
		userThreadImpl.setTopMBMessageId(topMBMessageId);
		userThreadImpl.setRead(read);
		userThreadImpl.setDeleted(deleted);

		userThreadImpl.resetOriginalValues();

		return userThreadImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userThreadId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		mbThreadId = objectInput.readLong();
		topMBMessageId = objectInput.readLong();
		read = objectInput.readBoolean();
		deleted = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(userThreadId);
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
		objectOutput.writeLong(mbThreadId);
		objectOutput.writeLong(topMBMessageId);
		objectOutput.writeBoolean(read);
		objectOutput.writeBoolean(deleted);
	}

	public long userThreadId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long mbThreadId;
	public long topMBMessageId;
	public boolean read;
	public boolean deleted;
}