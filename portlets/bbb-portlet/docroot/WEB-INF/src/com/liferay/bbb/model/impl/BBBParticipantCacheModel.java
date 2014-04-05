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

import com.liferay.bbb.model.BBBParticipant;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BBBParticipant in entity cache.
 *
 * @author Shinn Lok
 * @see BBBParticipant
 * @generated
 */
public class BBBParticipantCacheModel implements CacheModel<BBBParticipant>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{bbbParticipantId=");
		sb.append(bbbParticipantId);
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
		sb.append(", bbbMeetingId=");
		sb.append(bbbMeetingId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", type=");
		sb.append(type);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BBBParticipant toEntityModel() {
		BBBParticipantImpl bbbParticipantImpl = new BBBParticipantImpl();

		bbbParticipantImpl.setBbbParticipantId(bbbParticipantId);
		bbbParticipantImpl.setGroupId(groupId);
		bbbParticipantImpl.setCompanyId(companyId);
		bbbParticipantImpl.setUserId(userId);

		if (userName == null) {
			bbbParticipantImpl.setUserName(StringPool.BLANK);
		}
		else {
			bbbParticipantImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			bbbParticipantImpl.setCreateDate(null);
		}
		else {
			bbbParticipantImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			bbbParticipantImpl.setModifiedDate(null);
		}
		else {
			bbbParticipantImpl.setModifiedDate(new Date(modifiedDate));
		}

		bbbParticipantImpl.setBbbMeetingId(bbbMeetingId);

		if (name == null) {
			bbbParticipantImpl.setName(StringPool.BLANK);
		}
		else {
			bbbParticipantImpl.setName(name);
		}

		if (emailAddress == null) {
			bbbParticipantImpl.setEmailAddress(StringPool.BLANK);
		}
		else {
			bbbParticipantImpl.setEmailAddress(emailAddress);
		}

		bbbParticipantImpl.setType(type);
		bbbParticipantImpl.setStatus(status);

		bbbParticipantImpl.resetOriginalValues();

		return bbbParticipantImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		bbbParticipantId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		bbbMeetingId = objectInput.readLong();
		name = objectInput.readUTF();
		emailAddress = objectInput.readUTF();
		type = objectInput.readInt();
		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(bbbParticipantId);
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
		objectOutput.writeLong(bbbMeetingId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (emailAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(emailAddress);
		}

		objectOutput.writeInt(type);
		objectOutput.writeInt(status);
	}

	public long bbbParticipantId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long bbbMeetingId;
	public String name;
	public String emailAddress;
	public int type;
	public int status;
}