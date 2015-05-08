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

package com.liferay.marketplace.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.marketplace.model.App;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing App in entity cache.
 *
 * @author Ryan Park
 * @see App
 * @generated
 */
@ProviderType
public class AppCacheModel implements CacheModel<App>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AppCacheModel)) {
			return false;
		}

		AppCacheModel appCacheModel = (AppCacheModel)obj;

		if (appId == appCacheModel.appId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, appId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", appId=");
		sb.append(appId);
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
		sb.append(", remoteAppId=");
		sb.append(remoteAppId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", category=");
		sb.append(category);
		sb.append(", iconURL=");
		sb.append(iconURL);
		sb.append(", version=");
		sb.append(version);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public App toEntityModel() {
		AppImpl appImpl = new AppImpl();

		if (uuid == null) {
			appImpl.setUuid(StringPool.BLANK);
		}
		else {
			appImpl.setUuid(uuid);
		}

		appImpl.setAppId(appId);
		appImpl.setCompanyId(companyId);
		appImpl.setUserId(userId);

		if (userName == null) {
			appImpl.setUserName(StringPool.BLANK);
		}
		else {
			appImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			appImpl.setCreateDate(null);
		}
		else {
			appImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			appImpl.setModifiedDate(null);
		}
		else {
			appImpl.setModifiedDate(new Date(modifiedDate));
		}

		appImpl.setRemoteAppId(remoteAppId);

		if (title == null) {
			appImpl.setTitle(StringPool.BLANK);
		}
		else {
			appImpl.setTitle(title);
		}

		if (description == null) {
			appImpl.setDescription(StringPool.BLANK);
		}
		else {
			appImpl.setDescription(description);
		}

		if (category == null) {
			appImpl.setCategory(StringPool.BLANK);
		}
		else {
			appImpl.setCategory(category);
		}

		if (iconURL == null) {
			appImpl.setIconURL(StringPool.BLANK);
		}
		else {
			appImpl.setIconURL(iconURL);
		}

		if (version == null) {
			appImpl.setVersion(StringPool.BLANK);
		}
		else {
			appImpl.setVersion(version);
		}

		appImpl.resetOriginalValues();

		return appImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		appId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		remoteAppId = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		category = objectInput.readUTF();
		iconURL = objectInput.readUTF();
		version = objectInput.readUTF();
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

		objectOutput.writeLong(appId);
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
		objectOutput.writeLong(remoteAppId);

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

		if (category == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(category);
		}

		if (iconURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(iconURL);
		}

		if (version == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(version);
		}
	}

	public String uuid;
	public long appId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long remoteAppId;
	public String title;
	public String description;
	public String category;
	public String iconURL;
	public String version;
}