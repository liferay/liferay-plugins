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

package com.liferay.notifications.model.impl;

import com.liferay.notifications.model.UserNotificationEvent;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UserNotificationEvent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserNotificationEvent
 * @generated
 */
public class UserNotificationEventCacheModel implements CacheModel<UserNotificationEvent>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{notificationEventId=");
		sb.append(notificationEventId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userNotificationEventId=");
		sb.append(userNotificationEventId);
		sb.append(", timestamp=");
		sb.append(timestamp);
		sb.append(", delivered=");
		sb.append(delivered);
		sb.append(", actionRequired=");
		sb.append(actionRequired);
		sb.append(", archived=");
		sb.append(archived);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserNotificationEvent toEntityModel() {
		UserNotificationEventImpl userNotificationEventImpl = new UserNotificationEventImpl();

		userNotificationEventImpl.setNotificationEventId(notificationEventId);
		userNotificationEventImpl.setCompanyId(companyId);
		userNotificationEventImpl.setUserId(userId);
		userNotificationEventImpl.setUserNotificationEventId(userNotificationEventId);
		userNotificationEventImpl.setTimestamp(timestamp);
		userNotificationEventImpl.setDelivered(delivered);
		userNotificationEventImpl.setActionRequired(actionRequired);
		userNotificationEventImpl.setArchived(archived);

		userNotificationEventImpl.resetOriginalValues();

		return userNotificationEventImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		notificationEventId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userNotificationEventId = objectInput.readLong();
		timestamp = objectInput.readLong();
		delivered = objectInput.readBoolean();
		actionRequired = objectInput.readBoolean();
		archived = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(notificationEventId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(userNotificationEventId);
		objectOutput.writeLong(timestamp);
		objectOutput.writeBoolean(delivered);
		objectOutput.writeBoolean(actionRequired);
		objectOutput.writeBoolean(archived);
	}

	public long notificationEventId;
	public long companyId;
	public long userId;
	public long userNotificationEventId;
	public long timestamp;
	public boolean delivered;
	public boolean actionRequired;
	public boolean archived;
}