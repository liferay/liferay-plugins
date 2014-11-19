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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.pushnotifications.model.PushNotificationsEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PushNotificationsEntry in entity cache.
 *
 * @author Bruno Farache
 * @see PushNotificationsEntry
 * @generated
 */
@ProviderType
public class PushNotificationsEntryCacheModel implements CacheModel<PushNotificationsEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{pushNotificationsEntryId=");
		sb.append(pushNotificationsEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createTime=");
		sb.append(createTime);
		sb.append(", parentPushNotificationsEntryId=");
		sb.append(parentPushNotificationsEntryId);
		sb.append(", childrenPushNotificationsEntriesCount=");
		sb.append(childrenPushNotificationsEntriesCount);
		sb.append(", payload=");
		sb.append(payload);
		sb.append(", ratingsTotalScore=");
		sb.append(ratingsTotalScore);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PushNotificationsEntry toEntityModel() {
		PushNotificationsEntryImpl pushNotificationsEntryImpl = new PushNotificationsEntryImpl();

		pushNotificationsEntryImpl.setPushNotificationsEntryId(pushNotificationsEntryId);
		pushNotificationsEntryImpl.setUserId(userId);
		pushNotificationsEntryImpl.setCreateTime(createTime);
		pushNotificationsEntryImpl.setParentPushNotificationsEntryId(parentPushNotificationsEntryId);
		pushNotificationsEntryImpl.setChildrenPushNotificationsEntriesCount(childrenPushNotificationsEntriesCount);

		if (payload == null) {
			pushNotificationsEntryImpl.setPayload(StringPool.BLANK);
		}
		else {
			pushNotificationsEntryImpl.setPayload(payload);
		}

		pushNotificationsEntryImpl.setRatingsTotalScore(ratingsTotalScore);

		pushNotificationsEntryImpl.resetOriginalValues();

		return pushNotificationsEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		pushNotificationsEntryId = objectInput.readLong();
		userId = objectInput.readLong();
		createTime = objectInput.readLong();
		parentPushNotificationsEntryId = objectInput.readLong();
		childrenPushNotificationsEntriesCount = objectInput.readInt();
		payload = objectInput.readUTF();
		ratingsTotalScore = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(pushNotificationsEntryId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createTime);
		objectOutput.writeLong(parentPushNotificationsEntryId);
		objectOutput.writeInt(childrenPushNotificationsEntriesCount);

		if (payload == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(payload);
		}

		objectOutput.writeLong(ratingsTotalScore);
	}

	public long pushNotificationsEntryId;
	public long userId;
	public long createTime;
	public long parentPushNotificationsEntryId;
	public int childrenPushNotificationsEntriesCount;
	public String payload;
	public long ratingsTotalScore;
}