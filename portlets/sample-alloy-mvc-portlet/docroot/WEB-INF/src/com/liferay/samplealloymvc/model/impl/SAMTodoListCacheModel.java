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

import com.liferay.samplealloymvc.model.SAMTodoList;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SAMTodoList in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SAMTodoList
 * @generated
 */
@ProviderType
public class SAMTodoListCacheModel implements CacheModel<SAMTodoList>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SAMTodoListCacheModel)) {
			return false;
		}

		SAMTodoListCacheModel samTodoListCacheModel = (SAMTodoListCacheModel)obj;

		if (samTodoListId == samTodoListCacheModel.samTodoListId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, samTodoListId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{samTodoListId=");
		sb.append(samTodoListId);
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
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SAMTodoList toEntityModel() {
		SAMTodoListImpl samTodoListImpl = new SAMTodoListImpl();

		samTodoListImpl.setSamTodoListId(samTodoListId);
		samTodoListImpl.setCompanyId(companyId);
		samTodoListImpl.setUserId(userId);

		if (userName == null) {
			samTodoListImpl.setUserName(StringPool.BLANK);
		}
		else {
			samTodoListImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			samTodoListImpl.setCreateDate(null);
		}
		else {
			samTodoListImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			samTodoListImpl.setModifiedDate(null);
		}
		else {
			samTodoListImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			samTodoListImpl.setName(StringPool.BLANK);
		}
		else {
			samTodoListImpl.setName(name);
		}

		samTodoListImpl.resetOriginalValues();

		return samTodoListImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		samTodoListId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(samTodoListId);

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

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long samTodoListId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
}