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

package com.liferay.portal.workflow.kaleo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoDefinition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoDefinition
 * @generated
 */
@ProviderType
public class KaleoDefinitionCacheModel implements CacheModel<KaleoDefinition>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoDefinitionCacheModel)) {
			return false;
		}

		KaleoDefinitionCacheModel kaleoDefinitionCacheModel = (KaleoDefinitionCacheModel)obj;

		if (kaleoDefinitionId == kaleoDefinitionCacheModel.kaleoDefinitionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kaleoDefinitionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{kaleoDefinitionId=");
		sb.append(kaleoDefinitionId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", content=");
		sb.append(content);
		sb.append(", version=");
		sb.append(version);
		sb.append(", active=");
		sb.append(active);
		sb.append(", startKaleoNodeId=");
		sb.append(startKaleoNodeId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KaleoDefinition toEntityModel() {
		KaleoDefinitionImpl kaleoDefinitionImpl = new KaleoDefinitionImpl();

		kaleoDefinitionImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoDefinitionImpl.setGroupId(groupId);
		kaleoDefinitionImpl.setCompanyId(companyId);
		kaleoDefinitionImpl.setUserId(userId);

		if (userName == null) {
			kaleoDefinitionImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoDefinitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoDefinitionImpl.setCreateDate(null);
		}
		else {
			kaleoDefinitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoDefinitionImpl.setModifiedDate(null);
		}
		else {
			kaleoDefinitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			kaleoDefinitionImpl.setName(StringPool.BLANK);
		}
		else {
			kaleoDefinitionImpl.setName(name);
		}

		if (title == null) {
			kaleoDefinitionImpl.setTitle(StringPool.BLANK);
		}
		else {
			kaleoDefinitionImpl.setTitle(title);
		}

		if (description == null) {
			kaleoDefinitionImpl.setDescription(StringPool.BLANK);
		}
		else {
			kaleoDefinitionImpl.setDescription(description);
		}

		if (content == null) {
			kaleoDefinitionImpl.setContent(StringPool.BLANK);
		}
		else {
			kaleoDefinitionImpl.setContent(content);
		}

		kaleoDefinitionImpl.setVersion(version);
		kaleoDefinitionImpl.setActive(active);
		kaleoDefinitionImpl.setStartKaleoNodeId(startKaleoNodeId);

		kaleoDefinitionImpl.resetOriginalValues();

		return kaleoDefinitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kaleoDefinitionId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		content = objectInput.readUTF();
		version = objectInput.readInt();
		active = objectInput.readBoolean();
		startKaleoNodeId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(kaleoDefinitionId);
		objectOutput.writeLong(groupId);
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

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (content == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeInt(version);
		objectOutput.writeBoolean(active);
		objectOutput.writeLong(startKaleoNodeId);
	}

	public long kaleoDefinitionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String title;
	public String description;
	public String content;
	public int version;
	public boolean active;
	public long startKaleoNodeId;
}