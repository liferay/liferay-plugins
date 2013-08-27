/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.oauthlogin.model.impl;

import com.liferay.oauthlogin.model.OAuthConnection;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OAuthConnection in entity cache.
 *
 * @author Andy Yang
 * @see OAuthConnection
 * @generated
 */
public class OAuthConnectionCacheModel implements CacheModel<OAuthConnection>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(53);

		sb.append("{oAuthConnectionId=");
		sb.append(oAuthConnectionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", enabled=");
		sb.append(enabled);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", iconId=");
		sb.append(iconId);
		sb.append(", oAuthVersion=");
		sb.append(oAuthVersion);
		sb.append(", key=");
		sb.append(key);
		sb.append(", secret=");
		sb.append(secret);
		sb.append(", scope=");
		sb.append(scope);
		sb.append(", authorizeURL=");
		sb.append(authorizeURL);
		sb.append(", accessTokenURL=");
		sb.append(accessTokenURL);
		sb.append(", accessTokenVerb=");
		sb.append(accessTokenVerb);
		sb.append(", accessTokenExtractorType=");
		sb.append(accessTokenExtractorType);
		sb.append(", requestTokenURL=");
		sb.append(requestTokenURL);
		sb.append(", requestTokenVerb=");
		sb.append(requestTokenVerb);
		sb.append(", signatureServiceType=");
		sb.append(signatureServiceType);
		sb.append(", redirectURL=");
		sb.append(redirectURL);
		sb.append(", socialAccountIdURL=");
		sb.append(socialAccountIdURL);
		sb.append(", socialAccountIdURLVerb=");
		sb.append(socialAccountIdURLVerb);
		sb.append(", socialAccountIdField=");
		sb.append(socialAccountIdField);
		sb.append(", socialAccountIdType=");
		sb.append(socialAccountIdType);
		sb.append(", socialAccountIdScript=");
		sb.append(socialAccountIdScript);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OAuthConnection toEntityModel() {
		OAuthConnectionImpl oAuthConnectionImpl = new OAuthConnectionImpl();

		oAuthConnectionImpl.setOAuthConnectionId(oAuthConnectionId);
		oAuthConnectionImpl.setCompanyId(companyId);
		oAuthConnectionImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			oAuthConnectionImpl.setCreateDate(null);
		}
		else {
			oAuthConnectionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			oAuthConnectionImpl.setModifiedDate(null);
		}
		else {
			oAuthConnectionImpl.setModifiedDate(new Date(modifiedDate));
		}

		oAuthConnectionImpl.setEnabled(enabled);

		if (name == null) {
			oAuthConnectionImpl.setName(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setName(name);
		}

		if (description == null) {
			oAuthConnectionImpl.setDescription(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setDescription(description);
		}

		oAuthConnectionImpl.setIconId(iconId);
		oAuthConnectionImpl.setOAuthVersion(oAuthVersion);

		if (key == null) {
			oAuthConnectionImpl.setKey(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setKey(key);
		}

		if (secret == null) {
			oAuthConnectionImpl.setSecret(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setSecret(secret);
		}

		if (scope == null) {
			oAuthConnectionImpl.setScope(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setScope(scope);
		}

		if (authorizeURL == null) {
			oAuthConnectionImpl.setAuthorizeURL(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setAuthorizeURL(authorizeURL);
		}

		if (accessTokenURL == null) {
			oAuthConnectionImpl.setAccessTokenURL(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setAccessTokenURL(accessTokenURL);
		}

		oAuthConnectionImpl.setAccessTokenVerb(accessTokenVerb);
		oAuthConnectionImpl.setAccessTokenExtractorType(accessTokenExtractorType);

		if (requestTokenURL == null) {
			oAuthConnectionImpl.setRequestTokenURL(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setRequestTokenURL(requestTokenURL);
		}

		oAuthConnectionImpl.setRequestTokenVerb(requestTokenVerb);
		oAuthConnectionImpl.setSignatureServiceType(signatureServiceType);

		if (redirectURL == null) {
			oAuthConnectionImpl.setRedirectURL(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setRedirectURL(redirectURL);
		}

		if (socialAccountIdURL == null) {
			oAuthConnectionImpl.setSocialAccountIdURL(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setSocialAccountIdURL(socialAccountIdURL);
		}

		oAuthConnectionImpl.setSocialAccountIdURLVerb(socialAccountIdURLVerb);

		if (socialAccountIdField == null) {
			oAuthConnectionImpl.setSocialAccountIdField(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setSocialAccountIdField(socialAccountIdField);
		}

		oAuthConnectionImpl.setSocialAccountIdType(socialAccountIdType);

		if (socialAccountIdScript == null) {
			oAuthConnectionImpl.setSocialAccountIdScript(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setSocialAccountIdScript(socialAccountIdScript);
		}

		oAuthConnectionImpl.resetOriginalValues();

		return oAuthConnectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		oAuthConnectionId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		enabled = objectInput.readBoolean();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		iconId = objectInput.readLong();
		oAuthVersion = objectInput.readInt();
		key = objectInput.readUTF();
		secret = objectInput.readUTF();
		scope = objectInput.readUTF();
		authorizeURL = objectInput.readUTF();
		accessTokenURL = objectInput.readUTF();
		accessTokenVerb = objectInput.readInt();
		accessTokenExtractorType = objectInput.readInt();
		requestTokenURL = objectInput.readUTF();
		requestTokenVerb = objectInput.readInt();
		signatureServiceType = objectInput.readInt();
		redirectURL = objectInput.readUTF();
		socialAccountIdURL = objectInput.readUTF();
		socialAccountIdURLVerb = objectInput.readInt();
		socialAccountIdField = objectInput.readUTF();
		socialAccountIdType = objectInput.readInt();
		socialAccountIdScript = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(oAuthConnectionId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeBoolean(enabled);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(iconId);
		objectOutput.writeInt(oAuthVersion);

		if (key == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(key);
		}

		if (secret == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(secret);
		}

		if (scope == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(scope);
		}

		if (authorizeURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(authorizeURL);
		}

		if (accessTokenURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accessTokenURL);
		}

		objectOutput.writeInt(accessTokenVerb);
		objectOutput.writeInt(accessTokenExtractorType);

		if (requestTokenURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(requestTokenURL);
		}

		objectOutput.writeInt(requestTokenVerb);
		objectOutput.writeInt(signatureServiceType);

		if (redirectURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(redirectURL);
		}

		if (socialAccountIdURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(socialAccountIdURL);
		}

		objectOutput.writeInt(socialAccountIdURLVerb);

		if (socialAccountIdField == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(socialAccountIdField);
		}

		objectOutput.writeInt(socialAccountIdType);

		if (socialAccountIdScript == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(socialAccountIdScript);
		}
	}

	public long oAuthConnectionId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public boolean enabled;
	public String name;
	public String description;
	public long iconId;
	public int oAuthVersion;
	public String key;
	public String secret;
	public String scope;
	public String authorizeURL;
	public String accessTokenURL;
	public int accessTokenVerb;
	public int accessTokenExtractorType;
	public String requestTokenURL;
	public int requestTokenVerb;
	public int signatureServiceType;
	public String redirectURL;
	public String socialAccountIdURL;
	public int socialAccountIdURLVerb;
	public String socialAccountIdField;
	public int socialAccountIdType;
	public String socialAccountIdScript;
}