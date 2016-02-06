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

package com.liferay.pushnotifications.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.pushnotifications.model.PushNotificationsDevice;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PushNotificationsDevice in entity cache.
 *
 * @author Bruno Farache
 * @see PushNotificationsDevice
 * @generated
 */
@ProviderType
public class PushNotificationsDeviceCacheModel implements CacheModel<PushNotificationsDevice>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PushNotificationsDeviceCacheModel)) {
			return false;
		}

		PushNotificationsDeviceCacheModel pushNotificationsDeviceCacheModel = (PushNotificationsDeviceCacheModel)obj;

		if (pushNotificationsDeviceId == pushNotificationsDeviceCacheModel.pushNotificationsDeviceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, pushNotificationsDeviceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{pushNotificationsDeviceId=");
		sb.append(pushNotificationsDeviceId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", platform=");
		sb.append(platform);
		sb.append(", token=");
		sb.append(token);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PushNotificationsDevice toEntityModel() {
		PushNotificationsDeviceImpl pushNotificationsDeviceImpl = new PushNotificationsDeviceImpl();

		pushNotificationsDeviceImpl.setPushNotificationsDeviceId(pushNotificationsDeviceId);
		pushNotificationsDeviceImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			pushNotificationsDeviceImpl.setCreateDate(null);
		}
		else {
			pushNotificationsDeviceImpl.setCreateDate(new Date(createDate));
		}

		if (platform == null) {
			pushNotificationsDeviceImpl.setPlatform(StringPool.BLANK);
		}
		else {
			pushNotificationsDeviceImpl.setPlatform(platform);
		}

		if (token == null) {
			pushNotificationsDeviceImpl.setToken(StringPool.BLANK);
		}
		else {
			pushNotificationsDeviceImpl.setToken(token);
		}

		pushNotificationsDeviceImpl.resetOriginalValues();

		return pushNotificationsDeviceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		pushNotificationsDeviceId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		platform = objectInput.readUTF();
		token = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(pushNotificationsDeviceId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);

		if (platform == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(platform);
		}

		if (token == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(token);
		}
	}

	public long pushNotificationsDeviceId;
	public long userId;
	public long createDate;
	public String platform;
	public String token;
}