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

import com.liferay.calendar.model.CalendarBooking;

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
 * The cache model class for representing CalendarBooking in entity cache.
 *
 * @author Eduardo Lundgren
 * @see CalendarBooking
 * @generated
 */
@ProviderType
public class CalendarBookingCacheModel implements CacheModel<CalendarBooking>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CalendarBookingCacheModel)) {
			return false;
		}

		CalendarBookingCacheModel calendarBookingCacheModel = (CalendarBookingCacheModel)obj;

		if (calendarBookingId == calendarBookingCacheModel.calendarBookingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, calendarBookingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(57);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", calendarBookingId=");
		sb.append(calendarBookingId);
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
		sb.append(", calendarId=");
		sb.append(calendarId);
		sb.append(", calendarResourceId=");
		sb.append(calendarResourceId);
		sb.append(", parentCalendarBookingId=");
		sb.append(parentCalendarBookingId);
		sb.append(", vEventUid=");
		sb.append(vEventUid);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", location=");
		sb.append(location);
		sb.append(", startTime=");
		sb.append(startTime);
		sb.append(", endTime=");
		sb.append(endTime);
		sb.append(", allDay=");
		sb.append(allDay);
		sb.append(", recurrence=");
		sb.append(recurrence);
		sb.append(", firstReminder=");
		sb.append(firstReminder);
		sb.append(", firstReminderType=");
		sb.append(firstReminderType);
		sb.append(", secondReminder=");
		sb.append(secondReminder);
		sb.append(", secondReminderType=");
		sb.append(secondReminderType);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CalendarBooking toEntityModel() {
		CalendarBookingImpl calendarBookingImpl = new CalendarBookingImpl();

		if (uuid == null) {
			calendarBookingImpl.setUuid(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setUuid(uuid);
		}

		calendarBookingImpl.setCalendarBookingId(calendarBookingId);
		calendarBookingImpl.setGroupId(groupId);
		calendarBookingImpl.setCompanyId(companyId);
		calendarBookingImpl.setUserId(userId);

		if (userName == null) {
			calendarBookingImpl.setUserName(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			calendarBookingImpl.setCreateDate(null);
		}
		else {
			calendarBookingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			calendarBookingImpl.setModifiedDate(null);
		}
		else {
			calendarBookingImpl.setModifiedDate(new Date(modifiedDate));
		}

		calendarBookingImpl.setResourceBlockId(resourceBlockId);
		calendarBookingImpl.setCalendarId(calendarId);
		calendarBookingImpl.setCalendarResourceId(calendarResourceId);
		calendarBookingImpl.setParentCalendarBookingId(parentCalendarBookingId);

		if (vEventUid == null) {
			calendarBookingImpl.setVEventUid(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setVEventUid(vEventUid);
		}

		if (title == null) {
			calendarBookingImpl.setTitle(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setTitle(title);
		}

		if (description == null) {
			calendarBookingImpl.setDescription(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setDescription(description);
		}

		if (location == null) {
			calendarBookingImpl.setLocation(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setLocation(location);
		}

		calendarBookingImpl.setStartTime(startTime);
		calendarBookingImpl.setEndTime(endTime);
		calendarBookingImpl.setAllDay(allDay);

		if (recurrence == null) {
			calendarBookingImpl.setRecurrence(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setRecurrence(recurrence);
		}

		calendarBookingImpl.setFirstReminder(firstReminder);

		if (firstReminderType == null) {
			calendarBookingImpl.setFirstReminderType(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setFirstReminderType(firstReminderType);
		}

		calendarBookingImpl.setSecondReminder(secondReminder);

		if (secondReminderType == null) {
			calendarBookingImpl.setSecondReminderType(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setSecondReminderType(secondReminderType);
		}

		calendarBookingImpl.setStatus(status);
		calendarBookingImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			calendarBookingImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			calendarBookingImpl.setStatusDate(null);
		}
		else {
			calendarBookingImpl.setStatusDate(new Date(statusDate));
		}

		calendarBookingImpl.resetOriginalValues();

		return calendarBookingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		calendarBookingId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		resourceBlockId = objectInput.readLong();
		calendarId = objectInput.readLong();
		calendarResourceId = objectInput.readLong();
		parentCalendarBookingId = objectInput.readLong();
		vEventUid = objectInput.readUTF();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		location = objectInput.readUTF();
		startTime = objectInput.readLong();
		endTime = objectInput.readLong();
		allDay = objectInput.readBoolean();
		recurrence = objectInput.readUTF();
		firstReminder = objectInput.readLong();
		firstReminderType = objectInput.readUTF();
		secondReminder = objectInput.readLong();
		secondReminderType = objectInput.readUTF();
		status = objectInput.readInt();
		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
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

		objectOutput.writeLong(calendarBookingId);
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
		objectOutput.writeLong(calendarId);
		objectOutput.writeLong(calendarResourceId);
		objectOutput.writeLong(parentCalendarBookingId);

		if (vEventUid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(vEventUid);
		}

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (location == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(location);
		}

		objectOutput.writeLong(startTime);
		objectOutput.writeLong(endTime);
		objectOutput.writeBoolean(allDay);

		if (recurrence == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(recurrence);
		}

		objectOutput.writeLong(firstReminder);

		if (firstReminderType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(firstReminderType);
		}

		objectOutput.writeLong(secondReminder);

		if (secondReminderType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(secondReminderType);
		}

		objectOutput.writeInt(status);
		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public String uuid;
	public long calendarBookingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long resourceBlockId;
	public long calendarId;
	public long calendarResourceId;
	public long parentCalendarBookingId;
	public String vEventUid;
	public String title;
	public String description;
	public String location;
	public long startTime;
	public long endTime;
	public boolean allDay;
	public String recurrence;
	public long firstReminder;
	public String firstReminderType;
	public long secondReminder;
	public String secondReminderType;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}