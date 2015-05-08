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

package com.liferay.opensocial.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.opensocial.model.OAuthToken;

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
 * The cache model class for representing OAuthToken in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OAuthToken
 * @generated
 */
@ProviderType
public class OAuthTokenCacheModel implements CacheModel<OAuthToken>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OAuthTokenCacheModel)) {
			return false;
		}

		OAuthTokenCacheModel oAuthTokenCacheModel = (OAuthTokenCacheModel)obj;

		if (oAuthTokenId == oAuthTokenCacheModel.oAuthTokenId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, oAuthTokenId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{oAuthTokenId=");
		sb.append(oAuthTokenId);
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
		sb.append(", gadgetKey=");
		sb.append(gadgetKey);
		sb.append(", serviceName=");
		sb.append(serviceName);
		sb.append(", moduleId=");
		sb.append(moduleId);
		sb.append(", accessToken=");
		sb.append(accessToken);
		sb.append(", tokenName=");
		sb.append(tokenName);
		sb.append(", tokenSecret=");
		sb.append(tokenSecret);
		sb.append(", sessionHandle=");
		sb.append(sessionHandle);
		sb.append(", expiration=");
		sb.append(expiration);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OAuthToken toEntityModel() {
		OAuthTokenImpl oAuthTokenImpl = new OAuthTokenImpl();

		oAuthTokenImpl.setOAuthTokenId(oAuthTokenId);
		oAuthTokenImpl.setCompanyId(companyId);
		oAuthTokenImpl.setUserId(userId);

		if (userName == null) {
			oAuthTokenImpl.setUserName(StringPool.BLANK);
		}
		else {
			oAuthTokenImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			oAuthTokenImpl.setCreateDate(null);
		}
		else {
			oAuthTokenImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			oAuthTokenImpl.setModifiedDate(null);
		}
		else {
			oAuthTokenImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (gadgetKey == null) {
			oAuthTokenImpl.setGadgetKey(StringPool.BLANK);
		}
		else {
			oAuthTokenImpl.setGadgetKey(gadgetKey);
		}

		if (serviceName == null) {
			oAuthTokenImpl.setServiceName(StringPool.BLANK);
		}
		else {
			oAuthTokenImpl.setServiceName(serviceName);
		}

		oAuthTokenImpl.setModuleId(moduleId);

		if (accessToken == null) {
			oAuthTokenImpl.setAccessToken(StringPool.BLANK);
		}
		else {
			oAuthTokenImpl.setAccessToken(accessToken);
		}

		if (tokenName == null) {
			oAuthTokenImpl.setTokenName(StringPool.BLANK);
		}
		else {
			oAuthTokenImpl.setTokenName(tokenName);
		}

		if (tokenSecret == null) {
			oAuthTokenImpl.setTokenSecret(StringPool.BLANK);
		}
		else {
			oAuthTokenImpl.setTokenSecret(tokenSecret);
		}

		if (sessionHandle == null) {
			oAuthTokenImpl.setSessionHandle(StringPool.BLANK);
		}
		else {
			oAuthTokenImpl.setSessionHandle(sessionHandle);
		}

		oAuthTokenImpl.setExpiration(expiration);

		oAuthTokenImpl.resetOriginalValues();

		return oAuthTokenImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		oAuthTokenId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		gadgetKey = objectInput.readUTF();
		serviceName = objectInput.readUTF();
		moduleId = objectInput.readLong();
		accessToken = objectInput.readUTF();
		tokenName = objectInput.readUTF();
		tokenSecret = objectInput.readUTF();
		sessionHandle = objectInput.readUTF();
		expiration = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(oAuthTokenId);
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

		if (gadgetKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(gadgetKey);
		}

		if (serviceName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(serviceName);
		}

		objectOutput.writeLong(moduleId);

		if (accessToken == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accessToken);
		}

		if (tokenName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(tokenName);
		}

		if (tokenSecret == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(tokenSecret);
		}

		if (sessionHandle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionHandle);
		}

		objectOutput.writeLong(expiration);
	}

	public long oAuthTokenId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String gadgetKey;
	public String serviceName;
	public long moduleId;
	public String accessToken;
	public String tokenName;
	public String tokenSecret;
	public String sessionHandle;
	public long expiration;
}