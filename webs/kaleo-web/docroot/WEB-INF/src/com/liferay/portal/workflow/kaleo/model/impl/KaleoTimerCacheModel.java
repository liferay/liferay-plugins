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

package com.liferay.portal.workflow.kaleo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoTimer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimer
 * @generated
 */
@ProviderType
public class KaleoTimerCacheModel implements CacheModel<KaleoTimer>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTimerCacheModel)) {
			return false;
		}

		KaleoTimerCacheModel kaleoTimerCacheModel = (KaleoTimerCacheModel)obj;

		if (kaleoTimerId == kaleoTimerCacheModel.kaleoTimerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kaleoTimerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{kaleoTimerId=");
		sb.append(kaleoTimerId);
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
		sb.append(", kaleoClassName=");
		sb.append(kaleoClassName);
		sb.append(", kaleoClassPK=");
		sb.append(kaleoClassPK);
		sb.append(", kaleoDefinitionId=");
		sb.append(kaleoDefinitionId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", blocking=");
		sb.append(blocking);
		sb.append(", description=");
		sb.append(description);
		sb.append(", duration=");
		sb.append(duration);
		sb.append(", scale=");
		sb.append(scale);
		sb.append(", recurrenceDuration=");
		sb.append(recurrenceDuration);
		sb.append(", recurrenceScale=");
		sb.append(recurrenceScale);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KaleoTimer toEntityModel() {
		KaleoTimerImpl kaleoTimerImpl = new KaleoTimerImpl();

		kaleoTimerImpl.setKaleoTimerId(kaleoTimerId);
		kaleoTimerImpl.setGroupId(groupId);
		kaleoTimerImpl.setCompanyId(companyId);
		kaleoTimerImpl.setUserId(userId);

		if (userName == null) {
			kaleoTimerImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoTimerImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoTimerImpl.setCreateDate(null);
		}
		else {
			kaleoTimerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoTimerImpl.setModifiedDate(null);
		}
		else {
			kaleoTimerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (kaleoClassName == null) {
			kaleoTimerImpl.setKaleoClassName(StringPool.BLANK);
		}
		else {
			kaleoTimerImpl.setKaleoClassName(kaleoClassName);
		}

		kaleoTimerImpl.setKaleoClassPK(kaleoClassPK);
		kaleoTimerImpl.setKaleoDefinitionId(kaleoDefinitionId);

		if (name == null) {
			kaleoTimerImpl.setName(StringPool.BLANK);
		}
		else {
			kaleoTimerImpl.setName(name);
		}

		kaleoTimerImpl.setBlocking(blocking);

		if (description == null) {
			kaleoTimerImpl.setDescription(StringPool.BLANK);
		}
		else {
			kaleoTimerImpl.setDescription(description);
		}

		kaleoTimerImpl.setDuration(duration);

		if (scale == null) {
			kaleoTimerImpl.setScale(StringPool.BLANK);
		}
		else {
			kaleoTimerImpl.setScale(scale);
		}

		kaleoTimerImpl.setRecurrenceDuration(recurrenceDuration);

		if (recurrenceScale == null) {
			kaleoTimerImpl.setRecurrenceScale(StringPool.BLANK);
		}
		else {
			kaleoTimerImpl.setRecurrenceScale(recurrenceScale);
		}

		kaleoTimerImpl.resetOriginalValues();

		return kaleoTimerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kaleoTimerId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		kaleoClassName = objectInput.readUTF();
		kaleoClassPK = objectInput.readLong();
		kaleoDefinitionId = objectInput.readLong();
		name = objectInput.readUTF();
		blocking = objectInput.readBoolean();
		description = objectInput.readUTF();
		duration = objectInput.readDouble();
		scale = objectInput.readUTF();
		recurrenceDuration = objectInput.readDouble();
		recurrenceScale = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(kaleoTimerId);
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

		if (kaleoClassName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(kaleoClassName);
		}

		objectOutput.writeLong(kaleoClassPK);
		objectOutput.writeLong(kaleoDefinitionId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeBoolean(blocking);

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeDouble(duration);

		if (scale == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(scale);
		}

		objectOutput.writeDouble(recurrenceDuration);

		if (recurrenceScale == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(recurrenceScale);
		}
	}

	public long kaleoTimerId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String kaleoClassName;
	public long kaleoClassPK;
	public long kaleoDefinitionId;
	public String name;
	public boolean blocking;
	public String description;
	public double duration;
	public String scale;
	public double recurrenceDuration;
	public String recurrenceScale;
}