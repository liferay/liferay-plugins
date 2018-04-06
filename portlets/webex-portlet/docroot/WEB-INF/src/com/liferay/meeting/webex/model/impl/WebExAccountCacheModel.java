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

package com.liferay.meeting.webex.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.meeting.webex.model.WebExAccount;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WebExAccount in entity cache.
 *
 * @author Anant Singh
 * @see WebExAccount
 * @generated
 */
@ProviderType
public class WebExAccountCacheModel implements CacheModel<WebExAccount>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WebExAccountCacheModel)) {
			return false;
		}

		WebExAccountCacheModel webExAccountCacheModel = (WebExAccountCacheModel)obj;

		if (webExAccountId == webExAccountCacheModel.webExAccountId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, webExAccountId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", webExAccountId=");
		sb.append(webExAccountId);
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
		sb.append(", webExSiteId=");
		sb.append(webExSiteId);
		sb.append(", login=");
		sb.append(login);
		sb.append(", password=");
		sb.append(password);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WebExAccount toEntityModel() {
		WebExAccountImpl webExAccountImpl = new WebExAccountImpl();

		if (uuid == null) {
			webExAccountImpl.setUuid(StringPool.BLANK);
		}
		else {
			webExAccountImpl.setUuid(uuid);
		}

		webExAccountImpl.setWebExAccountId(webExAccountId);
		webExAccountImpl.setGroupId(groupId);
		webExAccountImpl.setCompanyId(companyId);
		webExAccountImpl.setUserId(userId);

		if (userName == null) {
			webExAccountImpl.setUserName(StringPool.BLANK);
		}
		else {
			webExAccountImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			webExAccountImpl.setCreateDate(null);
		}
		else {
			webExAccountImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			webExAccountImpl.setModifiedDate(null);
		}
		else {
			webExAccountImpl.setModifiedDate(new Date(modifiedDate));
		}

		webExAccountImpl.setWebExSiteId(webExSiteId);

		if (login == null) {
			webExAccountImpl.setLogin(StringPool.BLANK);
		}
		else {
			webExAccountImpl.setLogin(login);
		}

		if (password == null) {
			webExAccountImpl.setPassword(StringPool.BLANK);
		}
		else {
			webExAccountImpl.setPassword(password);
		}

		webExAccountImpl.resetOriginalValues();

		return webExAccountImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		webExAccountId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		webExSiteId = objectInput.readLong();
		login = objectInput.readUTF();
		password = objectInput.readUTF();
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

		objectOutput.writeLong(webExAccountId);

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

		objectOutput.writeLong(webExSiteId);

		if (login == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(login);
		}

		if (password == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(password);
		}
	}

	public String uuid;
	public long webExAccountId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long webExSiteId;
	public String login;
	public String password;
}