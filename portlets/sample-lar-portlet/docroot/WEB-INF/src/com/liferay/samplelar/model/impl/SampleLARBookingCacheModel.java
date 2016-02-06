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

package com.liferay.samplelar.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.samplelar.model.SampleLARBooking;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SampleLARBooking in entity cache.
 *
 * @author Mate Thurzo
 * @see SampleLARBooking
 * @generated
 */
@ProviderType
public class SampleLARBookingCacheModel implements CacheModel<SampleLARBooking>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SampleLARBookingCacheModel)) {
			return false;
		}

		SampleLARBookingCacheModel sampleLARBookingCacheModel = (SampleLARBookingCacheModel)obj;

		if (sampleLARBookingId == sampleLARBookingCacheModel.sampleLARBookingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, sampleLARBookingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", sampleLARBookingId=");
		sb.append(sampleLARBookingId);
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
		sb.append(", bookingNumber=");
		sb.append(bookingNumber);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SampleLARBooking toEntityModel() {
		SampleLARBookingImpl sampleLARBookingImpl = new SampleLARBookingImpl();

		if (uuid == null) {
			sampleLARBookingImpl.setUuid(StringPool.BLANK);
		}
		else {
			sampleLARBookingImpl.setUuid(uuid);
		}

		sampleLARBookingImpl.setSampleLARBookingId(sampleLARBookingId);
		sampleLARBookingImpl.setGroupId(groupId);
		sampleLARBookingImpl.setCompanyId(companyId);
		sampleLARBookingImpl.setUserId(userId);

		if (userName == null) {
			sampleLARBookingImpl.setUserName(StringPool.BLANK);
		}
		else {
			sampleLARBookingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			sampleLARBookingImpl.setCreateDate(null);
		}
		else {
			sampleLARBookingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			sampleLARBookingImpl.setModifiedDate(null);
		}
		else {
			sampleLARBookingImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (bookingNumber == null) {
			sampleLARBookingImpl.setBookingNumber(StringPool.BLANK);
		}
		else {
			sampleLARBookingImpl.setBookingNumber(bookingNumber);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			sampleLARBookingImpl.setLastPublishDate(null);
		}
		else {
			sampleLARBookingImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		sampleLARBookingImpl.resetOriginalValues();

		return sampleLARBookingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		sampleLARBookingId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		bookingNumber = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();
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

		objectOutput.writeLong(sampleLARBookingId);

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

		if (bookingNumber == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(bookingNumber);
		}

		objectOutput.writeLong(lastPublishDate);
	}

	public String uuid;
	public long sampleLARBookingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String bookingNumber;
	public long lastPublishDate;
}