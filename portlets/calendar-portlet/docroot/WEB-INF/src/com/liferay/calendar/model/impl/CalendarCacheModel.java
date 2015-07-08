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

import com.liferay.calendar.model.Calendar;

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
 * The cache model class for representing Calendar in entity cache.
 *
 * @author Eduardo Lundgren
 * @see Calendar
 * @generated
 */
@ProviderType
public class CalendarCacheModel implements CacheModel<Calendar>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CalendarCacheModel)) {
			return false;
		}

		CalendarCacheModel calendarCacheModel = (CalendarCacheModel)obj;

		if (calendarId == calendarCacheModel.calendarId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, calendarId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", calendarId=");
		sb.append(calendarId);
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
		sb.append(", calendarResourceId=");
		sb.append(calendarResourceId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", timeZoneId=");
		sb.append(timeZoneId);
		sb.append(", color=");
		sb.append(color);
		sb.append(", defaultCalendar=");
		sb.append(defaultCalendar);
		sb.append(", enableComments=");
		sb.append(enableComments);
		sb.append(", enableRatings=");
		sb.append(enableRatings);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Calendar toEntityModel() {
		CalendarImpl calendarImpl = new CalendarImpl();

		if (uuid == null) {
			calendarImpl.setUuid(StringPool.BLANK);
		}
		else {
			calendarImpl.setUuid(uuid);
		}

		calendarImpl.setCalendarId(calendarId);
		calendarImpl.setGroupId(groupId);
		calendarImpl.setCompanyId(companyId);
		calendarImpl.setUserId(userId);

		if (userName == null) {
			calendarImpl.setUserName(StringPool.BLANK);
		}
		else {
			calendarImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			calendarImpl.setCreateDate(null);
		}
		else {
			calendarImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			calendarImpl.setModifiedDate(null);
		}
		else {
			calendarImpl.setModifiedDate(new Date(modifiedDate));
		}

		calendarImpl.setResourceBlockId(resourceBlockId);
		calendarImpl.setCalendarResourceId(calendarResourceId);

		if (name == null) {
			calendarImpl.setName(StringPool.BLANK);
		}
		else {
			calendarImpl.setName(name);
		}

		if (description == null) {
			calendarImpl.setDescription(StringPool.BLANK);
		}
		else {
			calendarImpl.setDescription(description);
		}

		if (timeZoneId == null) {
			calendarImpl.setTimeZoneId(StringPool.BLANK);
		}
		else {
			calendarImpl.setTimeZoneId(timeZoneId);
		}

		calendarImpl.setColor(color);
		calendarImpl.setDefaultCalendar(defaultCalendar);
		calendarImpl.setEnableComments(enableComments);
		calendarImpl.setEnableRatings(enableRatings);

		calendarImpl.resetOriginalValues();

		return calendarImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		calendarId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		resourceBlockId = objectInput.readLong();
		calendarResourceId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		timeZoneId = objectInput.readUTF();
		color = objectInput.readInt();
		defaultCalendar = objectInput.readBoolean();
		enableComments = objectInput.readBoolean();
		enableRatings = objectInput.readBoolean();
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

		objectOutput.writeLong(calendarId);
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
		objectOutput.writeLong(calendarResourceId);

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

		if (timeZoneId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(timeZoneId);
		}

		objectOutput.writeInt(color);
		objectOutput.writeBoolean(defaultCalendar);
		objectOutput.writeBoolean(enableComments);
		objectOutput.writeBoolean(enableRatings);
	}

	public String uuid;
	public long calendarId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long resourceBlockId;
	public long calendarResourceId;
	public String name;
	public String description;
	public String timeZoneId;
	public int color;
	public boolean defaultCalendar;
	public boolean enableComments;
	public boolean enableRatings;
}