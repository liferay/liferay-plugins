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

import com.liferay.bbb.model.BBBMeeting;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BBBMeeting in entity cache.
 *
 * @author Shinn Lok
 * @see BBBMeeting
 * @generated
 */
public class BBBMeetingCacheModel implements CacheModel<BBBMeeting>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{bbbMeetingId=");
		sb.append(bbbMeetingId);
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
		sb.append(", bbbServerId=");
		sb.append(bbbServerId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", attendeePassword=");
		sb.append(attendeePassword);
		sb.append(", moderatorPassword=");
		sb.append(moderatorPassword);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BBBMeeting toEntityModel() {
		BBBMeetingImpl bbbMeetingImpl = new BBBMeetingImpl();

		bbbMeetingImpl.setBbbMeetingId(bbbMeetingId);
		bbbMeetingImpl.setGroupId(groupId);
		bbbMeetingImpl.setCompanyId(companyId);
		bbbMeetingImpl.setUserId(userId);

		if (userName == null) {
			bbbMeetingImpl.setUserName(StringPool.BLANK);
		}
		else {
			bbbMeetingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			bbbMeetingImpl.setCreateDate(null);
		}
		else {
			bbbMeetingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			bbbMeetingImpl.setModifiedDate(null);
		}
		else {
			bbbMeetingImpl.setModifiedDate(new Date(modifiedDate));
		}

		bbbMeetingImpl.setBbbServerId(bbbServerId);

		if (name == null) {
			bbbMeetingImpl.setName(StringPool.BLANK);
		}
		else {
			bbbMeetingImpl.setName(name);
		}

		if (description == null) {
			bbbMeetingImpl.setDescription(StringPool.BLANK);
		}
		else {
			bbbMeetingImpl.setDescription(description);
		}

		if (attendeePassword == null) {
			bbbMeetingImpl.setAttendeePassword(StringPool.BLANK);
		}
		else {
			bbbMeetingImpl.setAttendeePassword(attendeePassword);
		}

		if (moderatorPassword == null) {
			bbbMeetingImpl.setModeratorPassword(StringPool.BLANK);
		}
		else {
			bbbMeetingImpl.setModeratorPassword(moderatorPassword);
		}

		bbbMeetingImpl.setStatus(status);

		bbbMeetingImpl.resetOriginalValues();

		return bbbMeetingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		bbbMeetingId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		bbbServerId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		attendeePassword = objectInput.readUTF();
		moderatorPassword = objectInput.readUTF();
		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(bbbMeetingId);
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
		objectOutput.writeLong(bbbServerId);

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

		if (attendeePassword == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(attendeePassword);
		}

		if (moderatorPassword == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(moderatorPassword);
		}

		objectOutput.writeInt(status);
	}

	public long bbbMeetingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long bbbServerId;
	public String name;
	public String description;
	public String attendeePassword;
	public String moderatorPassword;
	public int status;
}