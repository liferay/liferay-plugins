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

package com.liferay.bbb.model.impl;

import com.liferay.bbb.model.BBBServer;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BBBServer in entity cache.
 *
 * @author Shinn Lok
 * @see BBBServer
 * @generated
 */
public class BBBServerCacheModel implements CacheModel<BBBServer>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{bbbServerId=");
		sb.append(bbbServerId);
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
		sb.append(", url=");
		sb.append(url);
		sb.append(", secret=");
		sb.append(secret);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BBBServer toEntityModel() {
		BBBServerImpl bbbServerImpl = new BBBServerImpl();

		bbbServerImpl.setBbbServerId(bbbServerId);
		bbbServerImpl.setCompanyId(companyId);
		bbbServerImpl.setUserId(userId);

		if (userName == null) {
			bbbServerImpl.setUserName(StringPool.BLANK);
		}
		else {
			bbbServerImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			bbbServerImpl.setCreateDate(null);
		}
		else {
			bbbServerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			bbbServerImpl.setModifiedDate(null);
		}
		else {
			bbbServerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			bbbServerImpl.setName(StringPool.BLANK);
		}
		else {
			bbbServerImpl.setName(name);
		}

		if (url == null) {
			bbbServerImpl.setUrl(StringPool.BLANK);
		}
		else {
			bbbServerImpl.setUrl(url);
		}

		if (secret == null) {
			bbbServerImpl.setSecret(StringPool.BLANK);
		}
		else {
			bbbServerImpl.setSecret(secret);
		}

		bbbServerImpl.setActive(active);

		bbbServerImpl.resetOriginalValues();

		return bbbServerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		bbbServerId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		url = objectInput.readUTF();
		secret = objectInput.readUTF();
		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(bbbServerId);
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

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		if (secret == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(secret);
		}

		objectOutput.writeBoolean(active);
	}

	public long bbbServerId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String url;
	public String secret;
	public boolean active;
}