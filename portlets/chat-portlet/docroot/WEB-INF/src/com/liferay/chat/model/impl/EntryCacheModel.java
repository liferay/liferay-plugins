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

package com.liferay.chat.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.chat.model.Entry;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Entry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Entry
 * @generated
 */
@ProviderType
public class EntryCacheModel implements CacheModel<Entry>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntryCacheModel)) {
			return false;
		}

		EntryCacheModel entryCacheModel = (EntryCacheModel)obj;

		if (entryId == entryCacheModel.entryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, entryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{entryId=");
		sb.append(entryId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", fromUserId=");
		sb.append(fromUserId);
		sb.append(", toUserId=");
		sb.append(toUserId);
		sb.append(", content=");
		sb.append(content);
		sb.append(", flag=");
		sb.append(flag);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Entry toEntityModel() {
		EntryImpl entryImpl = new EntryImpl();

		entryImpl.setEntryId(entryId);
		entryImpl.setCreateDate(createDate);
		entryImpl.setFromUserId(fromUserId);
		entryImpl.setToUserId(toUserId);

		if (content == null) {
			entryImpl.setContent(StringPool.BLANK);
		}
		else {
			entryImpl.setContent(content);
		}

		entryImpl.setFlag(flag);

		entryImpl.resetOriginalValues();

		return entryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		entryId = objectInput.readLong();
		createDate = objectInput.readLong();
		fromUserId = objectInput.readLong();
		toUserId = objectInput.readLong();
		content = objectInput.readUTF();
		flag = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(entryId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(fromUserId);
		objectOutput.writeLong(toUserId);

		if (content == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeInt(flag);
	}

	public long entryId;
	public long createDate;
	public long fromUserId;
	public long toUserId;
	public String content;
	public int flag;
}