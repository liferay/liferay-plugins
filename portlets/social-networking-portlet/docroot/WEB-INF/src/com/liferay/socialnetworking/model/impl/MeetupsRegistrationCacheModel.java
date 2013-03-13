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

package com.liferay.socialnetworking.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.socialnetworking.model.MeetupsRegistration;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MeetupsRegistration in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistration
 * @generated
 */
public class MeetupsRegistrationCacheModel implements CacheModel<MeetupsRegistration>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{meetupsRegistrationId=");
		sb.append(meetupsRegistrationId);
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
		sb.append(", meetupsEntryId=");
		sb.append(meetupsEntryId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", comments=");
		sb.append(comments);
		sb.append("}");

		return sb.toString();
	}

	public MeetupsRegistration toEntityModel() {
		MeetupsRegistrationImpl meetupsRegistrationImpl = new MeetupsRegistrationImpl();

		meetupsRegistrationImpl.setMeetupsRegistrationId(meetupsRegistrationId);
		meetupsRegistrationImpl.setCompanyId(companyId);
		meetupsRegistrationImpl.setUserId(userId);

		if (userName == null) {
			meetupsRegistrationImpl.setUserName(StringPool.BLANK);
		}
		else {
			meetupsRegistrationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			meetupsRegistrationImpl.setCreateDate(null);
		}
		else {
			meetupsRegistrationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			meetupsRegistrationImpl.setModifiedDate(null);
		}
		else {
			meetupsRegistrationImpl.setModifiedDate(new Date(modifiedDate));
		}

		meetupsRegistrationImpl.setMeetupsEntryId(meetupsEntryId);
		meetupsRegistrationImpl.setStatus(status);

		if (comments == null) {
			meetupsRegistrationImpl.setComments(StringPool.BLANK);
		}
		else {
			meetupsRegistrationImpl.setComments(comments);
		}

		meetupsRegistrationImpl.resetOriginalValues();

		return meetupsRegistrationImpl;
	}

	public void readExternal(ObjectInput objectInput) throws IOException {
		meetupsRegistrationId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		meetupsEntryId = objectInput.readLong();
		status = objectInput.readInt();
		comments = objectInput.readUTF();
	}

	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(meetupsRegistrationId);
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
		objectOutput.writeLong(meetupsEntryId);
		objectOutput.writeInt(status);

		if (comments == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(comments);
		}
	}

	public long meetupsRegistrationId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long meetupsEntryId;
	public int status;
	public String comments;
}