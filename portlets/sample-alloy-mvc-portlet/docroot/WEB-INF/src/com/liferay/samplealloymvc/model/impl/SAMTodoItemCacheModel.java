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

import com.liferay.samplealloymvc.model.SAMTodoItem;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SAMTodoItem in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SAMTodoItem
 * @generated
 */
@ProviderType
public class SAMTodoItemCacheModel implements CacheModel<SAMTodoItem>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SAMTodoItemCacheModel)) {
			return false;
		}

		SAMTodoItemCacheModel samTodoItemCacheModel = (SAMTodoItemCacheModel)obj;

		if (samTodoItemId == samTodoItemCacheModel.samTodoItemId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, samTodoItemId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{samTodoItemId=");
		sb.append(samTodoItemId);
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
		sb.append(", samTodoListId=");
		sb.append(samTodoListId);
		sb.append(", description=");
		sb.append(description);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SAMTodoItem toEntityModel() {
		SAMTodoItemImpl samTodoItemImpl = new SAMTodoItemImpl();

		samTodoItemImpl.setSamTodoItemId(samTodoItemId);
		samTodoItemImpl.setCompanyId(companyId);
		samTodoItemImpl.setUserId(userId);

		if (userName == null) {
			samTodoItemImpl.setUserName(StringPool.BLANK);
		}
		else {
			samTodoItemImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			samTodoItemImpl.setCreateDate(null);
		}
		else {
			samTodoItemImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			samTodoItemImpl.setModifiedDate(null);
		}
		else {
			samTodoItemImpl.setModifiedDate(new Date(modifiedDate));
		}

		samTodoItemImpl.setSamTodoListId(samTodoListId);

		if (description == null) {
			samTodoItemImpl.setDescription(StringPool.BLANK);
		}
		else {
			samTodoItemImpl.setDescription(description);
		}

		samTodoItemImpl.setPriority(priority);
		samTodoItemImpl.setStatus(status);

		samTodoItemImpl.resetOriginalValues();

		return samTodoItemImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		samTodoItemId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		samTodoListId = objectInput.readLong();
		description = objectInput.readUTF();

		priority = objectInput.readInt();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(samTodoItemId);

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

		objectOutput.writeLong(samTodoListId);

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(priority);

		objectOutput.writeInt(status);
	}

	public long samTodoItemId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long samTodoListId;
	public String description;
	public int priority;
	public int status;
}