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

package com.liferay.calendar.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.calendar.model.CalendarResource;

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
 * The cache model class for representing CalendarResource in entity cache.
 *
 * @author Eduardo Lundgren
 * @see CalendarResource
 * @generated
 */
@ProviderType
public class CalendarResourceCacheModel implements CacheModel<CalendarResource>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CalendarResourceCacheModel)) {
			return false;
		}

		CalendarResourceCacheModel calendarResourceCacheModel = (CalendarResourceCacheModel)obj;

		if (calendarResourceId == calendarResourceCacheModel.calendarResourceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, calendarResourceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", calendarResourceId=");
		sb.append(calendarResourceId);
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
		sb.append(", resourceBlockId=");
		sb.append(resourceBlockId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", classUuid=");
		sb.append(classUuid);
		sb.append(", code=");
		sb.append(code);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CalendarResource toEntityModel() {
		CalendarResourceImpl calendarResourceImpl = new CalendarResourceImpl();

		if (uuid == null) {
			calendarResourceImpl.setUuid(StringPool.BLANK);
		}
		else {
			calendarResourceImpl.setUuid(uuid);
		}

		calendarResourceImpl.setCalendarResourceId(calendarResourceId);
		calendarResourceImpl.setGroupId(groupId);
		calendarResourceImpl.setCompanyId(companyId);
		calendarResourceImpl.setUserId(userId);

		if (userName == null) {
			calendarResourceImpl.setUserName(StringPool.BLANK);
		}
		else {
			calendarResourceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			calendarResourceImpl.setCreateDate(null);
		}
		else {
			calendarResourceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			calendarResourceImpl.setModifiedDate(null);
		}
		else {
			calendarResourceImpl.setModifiedDate(new Date(modifiedDate));
		}

		calendarResourceImpl.setResourceBlockId(resourceBlockId);
		calendarResourceImpl.setClassNameId(classNameId);
		calendarResourceImpl.setClassPK(classPK);

		if (classUuid == null) {
			calendarResourceImpl.setClassUuid(StringPool.BLANK);
		}
		else {
			calendarResourceImpl.setClassUuid(classUuid);
		}

		if (code == null) {
			calendarResourceImpl.setCode(StringPool.BLANK);
		}
		else {
			calendarResourceImpl.setCode(code);
		}

		if (name == null) {
			calendarResourceImpl.setName(StringPool.BLANK);
		}
		else {
			calendarResourceImpl.setName(name);
		}

		if (description == null) {
			calendarResourceImpl.setDescription(StringPool.BLANK);
		}
		else {
			calendarResourceImpl.setDescription(description);
		}

		calendarResourceImpl.setActive(active);

		calendarResourceImpl.resetOriginalValues();

		return calendarResourceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		calendarResourceId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		resourceBlockId = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		classUuid = objectInput.readUTF();
		code = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		active = objectInput.readBoolean();
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

		objectOutput.writeLong(calendarResourceId);
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
		objectOutput.writeLong(resourceBlockId);
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);

		if (classUuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(classUuid);
		}

		if (code == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(code);
		}

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

		objectOutput.writeBoolean(active);
	}

	public String uuid;
	public long calendarResourceId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long resourceBlockId;
	public long classNameId;
	public long classPK;
	public String classUuid;
	public String code;
	public String name;
	public String description;
	public boolean active;
}