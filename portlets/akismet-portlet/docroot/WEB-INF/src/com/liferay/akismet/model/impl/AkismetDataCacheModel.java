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

package com.liferay.akismet.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.akismet.model.AkismetData;

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
 * The cache model class for representing AkismetData in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AkismetData
 * @generated
 */
@ProviderType
public class AkismetDataCacheModel implements CacheModel<AkismetData>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AkismetDataCacheModel)) {
			return false;
		}

		AkismetDataCacheModel akismetDataCacheModel = (AkismetDataCacheModel)obj;

		if (akismetDataId == akismetDataCacheModel.akismetDataId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, akismetDataId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{akismetDataId=");
		sb.append(akismetDataId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", permalink=");
		sb.append(permalink);
		sb.append(", referrer=");
		sb.append(referrer);
		sb.append(", userAgent=");
		sb.append(userAgent);
		sb.append(", userIP=");
		sb.append(userIP);
		sb.append(", userURL=");
		sb.append(userURL);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AkismetData toEntityModel() {
		AkismetDataImpl akismetDataImpl = new AkismetDataImpl();

		akismetDataImpl.setAkismetDataId(akismetDataId);

		if (modifiedDate == Long.MIN_VALUE) {
			akismetDataImpl.setModifiedDate(null);
		}
		else {
			akismetDataImpl.setModifiedDate(new Date(modifiedDate));
		}

		akismetDataImpl.setClassNameId(classNameId);
		akismetDataImpl.setClassPK(classPK);

		if (type == null) {
			akismetDataImpl.setType(StringPool.BLANK);
		}
		else {
			akismetDataImpl.setType(type);
		}

		if (permalink == null) {
			akismetDataImpl.setPermalink(StringPool.BLANK);
		}
		else {
			akismetDataImpl.setPermalink(permalink);
		}

		if (referrer == null) {
			akismetDataImpl.setReferrer(StringPool.BLANK);
		}
		else {
			akismetDataImpl.setReferrer(referrer);
		}

		if (userAgent == null) {
			akismetDataImpl.setUserAgent(StringPool.BLANK);
		}
		else {
			akismetDataImpl.setUserAgent(userAgent);
		}

		if (userIP == null) {
			akismetDataImpl.setUserIP(StringPool.BLANK);
		}
		else {
			akismetDataImpl.setUserIP(userIP);
		}

		if (userURL == null) {
			akismetDataImpl.setUserURL(StringPool.BLANK);
		}
		else {
			akismetDataImpl.setUserURL(userURL);
		}

		akismetDataImpl.resetOriginalValues();

		return akismetDataImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		akismetDataId = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		type = objectInput.readUTF();
		permalink = objectInput.readUTF();
		referrer = objectInput.readUTF();
		userAgent = objectInput.readUTF();
		userIP = objectInput.readUTF();
		userURL = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(akismetDataId);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (permalink == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(permalink);
		}

		if (referrer == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(referrer);
		}

		if (userAgent == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userAgent);
		}

		if (userIP == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userIP);
		}

		if (userURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userURL);
		}
	}

	public long akismetDataId;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String type;
	public String permalink;
	public String referrer;
	public String userAgent;
	public String userIP;
	public String userURL;
}