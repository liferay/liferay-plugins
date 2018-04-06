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

import com.liferay.meeting.webex.model.WebExSite;

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
 * The cache model class for representing WebExSite in entity cache.
 *
 * @author Anant Singh
 * @see WebExSite
 * @generated
 */
@ProviderType
public class WebExSiteCacheModel implements CacheModel<WebExSite>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WebExSiteCacheModel)) {
			return false;
		}

		WebExSiteCacheModel webExSiteCacheModel = (WebExSiteCacheModel)obj;

		if (webExSiteId == webExSiteCacheModel.webExSiteId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, webExSiteId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", webExSiteId=");
		sb.append(webExSiteId);
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
		sb.append(", apiURL=");
		sb.append(apiURL);
		sb.append(", login=");
		sb.append(login);
		sb.append(", password=");
		sb.append(password);
		sb.append(", partnerKey=");
		sb.append(partnerKey);
		sb.append(", siteKey=");
		sb.append(siteKey);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WebExSite toEntityModel() {
		WebExSiteImpl webExSiteImpl = new WebExSiteImpl();

		if (uuid == null) {
			webExSiteImpl.setUuid(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setUuid(uuid);
		}

		webExSiteImpl.setWebExSiteId(webExSiteId);
		webExSiteImpl.setGroupId(groupId);
		webExSiteImpl.setCompanyId(companyId);
		webExSiteImpl.setUserId(userId);

		if (userName == null) {
			webExSiteImpl.setUserName(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			webExSiteImpl.setCreateDate(null);
		}
		else {
			webExSiteImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			webExSiteImpl.setModifiedDate(null);
		}
		else {
			webExSiteImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			webExSiteImpl.setName(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setName(name);
		}

		if (apiURL == null) {
			webExSiteImpl.setApiURL(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setApiURL(apiURL);
		}

		if (login == null) {
			webExSiteImpl.setLogin(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setLogin(login);
		}

		if (password == null) {
			webExSiteImpl.setPassword(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setPassword(password);
		}

		if (partnerKey == null) {
			webExSiteImpl.setPartnerKey(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setPartnerKey(partnerKey);
		}

		webExSiteImpl.setSiteKey(siteKey);

		webExSiteImpl.resetOriginalValues();

		return webExSiteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		webExSiteId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		apiURL = objectInput.readUTF();
		login = objectInput.readUTF();
		password = objectInput.readUTF();
		partnerKey = objectInput.readUTF();

		siteKey = objectInput.readLong();
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

		objectOutput.writeLong(webExSiteId);

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

		if (apiURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(apiURL);
		}

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

		if (partnerKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(partnerKey);
		}

		objectOutput.writeLong(siteKey);
	}

	public String uuid;
	public long webExSiteId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String apiURL;
	public String login;
	public String password;
	public String partnerKey;
	public long siteKey;
}