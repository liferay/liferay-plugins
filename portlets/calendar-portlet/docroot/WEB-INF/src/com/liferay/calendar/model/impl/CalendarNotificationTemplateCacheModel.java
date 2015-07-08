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

import com.liferay.calendar.model.CalendarNotificationTemplate;

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
 * The cache model class for representing CalendarNotificationTemplate in entity cache.
 *
 * @author Eduardo Lundgren
 * @see CalendarNotificationTemplate
 * @generated
 */
@ProviderType
public class CalendarNotificationTemplateCacheModel implements CacheModel<CalendarNotificationTemplate>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CalendarNotificationTemplateCacheModel)) {
			return false;
		}

		CalendarNotificationTemplateCacheModel calendarNotificationTemplateCacheModel =
			(CalendarNotificationTemplateCacheModel)obj;

		if (calendarNotificationTemplateId == calendarNotificationTemplateCacheModel.calendarNotificationTemplateId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, calendarNotificationTemplateId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", calendarNotificationTemplateId=");
		sb.append(calendarNotificationTemplateId);
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
		sb.append(", calendarId=");
		sb.append(calendarId);
		sb.append(", notificationType=");
		sb.append(notificationType);
		sb.append(", notificationTypeSettings=");
		sb.append(notificationTypeSettings);
		sb.append(", notificationTemplateType=");
		sb.append(notificationTemplateType);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", body=");
		sb.append(body);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CalendarNotificationTemplate toEntityModel() {
		CalendarNotificationTemplateImpl calendarNotificationTemplateImpl = new CalendarNotificationTemplateImpl();

		if (uuid == null) {
			calendarNotificationTemplateImpl.setUuid(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setUuid(uuid);
		}

		calendarNotificationTemplateImpl.setCalendarNotificationTemplateId(calendarNotificationTemplateId);
		calendarNotificationTemplateImpl.setGroupId(groupId);
		calendarNotificationTemplateImpl.setCompanyId(companyId);
		calendarNotificationTemplateImpl.setUserId(userId);

		if (userName == null) {
			calendarNotificationTemplateImpl.setUserName(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			calendarNotificationTemplateImpl.setCreateDate(null);
		}
		else {
			calendarNotificationTemplateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			calendarNotificationTemplateImpl.setModifiedDate(null);
		}
		else {
			calendarNotificationTemplateImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		calendarNotificationTemplateImpl.setCalendarId(calendarId);

		if (notificationType == null) {
			calendarNotificationTemplateImpl.setNotificationType(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setNotificationType(notificationType);
		}

		if (notificationTypeSettings == null) {
			calendarNotificationTemplateImpl.setNotificationTypeSettings(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setNotificationTypeSettings(notificationTypeSettings);
		}

		if (notificationTemplateType == null) {
			calendarNotificationTemplateImpl.setNotificationTemplateType(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setNotificationTemplateType(notificationTemplateType);
		}

		if (subject == null) {
			calendarNotificationTemplateImpl.setSubject(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setSubject(subject);
		}

		if (body == null) {
			calendarNotificationTemplateImpl.setBody(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setBody(body);
		}

		calendarNotificationTemplateImpl.resetOriginalValues();

		return calendarNotificationTemplateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		calendarNotificationTemplateId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		calendarId = objectInput.readLong();
		notificationType = objectInput.readUTF();
		notificationTypeSettings = objectInput.readUTF();
		notificationTemplateType = objectInput.readUTF();
		subject = objectInput.readUTF();
		body = objectInput.readUTF();
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

		objectOutput.writeLong(calendarNotificationTemplateId);
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
		objectOutput.writeLong(calendarId);

		if (notificationType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notificationType);
		}

		if (notificationTypeSettings == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notificationTypeSettings);
		}

		if (notificationTemplateType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notificationTemplateType);
		}

		if (subject == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subject);
		}

		if (body == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(body);
		}
	}

	public String uuid;
	public long calendarNotificationTemplateId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long calendarId;
	public String notificationType;
	public String notificationTypeSettings;
	public String notificationTemplateType;
	public String subject;
	public String body;
}