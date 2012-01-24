/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.calendar.model.CalendarResource;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing CalendarResource in entity cache.
 *
 * @author Eduardo Lundgren
 * @see CalendarResource
 * @generated
 */
public class CalendarResourceCacheModel implements CacheModel<CalendarResource>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

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
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", classUuid=");
		sb.append(classUuid);
		sb.append(", defaultCalendarId=");
		sb.append(defaultCalendarId);
		sb.append(", code=");
		sb.append(code);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", type=");
		sb.append(type);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

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

		if (className == null) {
			calendarResourceImpl.setClassName(StringPool.BLANK);
		}
		else {
			calendarResourceImpl.setClassName(className);
		}

		calendarResourceImpl.setClassPK(classPK);

		if (classUuid == null) {
			calendarResourceImpl.setClassUuid(StringPool.BLANK);
		}
		else {
			calendarResourceImpl.setClassUuid(classUuid);
		}

		calendarResourceImpl.setDefaultCalendarId(defaultCalendarId);

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

		if (type == null) {
			calendarResourceImpl.setType(StringPool.BLANK);
		}
		else {
			calendarResourceImpl.setType(type);
		}

		calendarResourceImpl.setActive(active);

		calendarResourceImpl.resetOriginalValues();

		return calendarResourceImpl;
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
	public String className;
	public long classPK;
	public String classUuid;
	public long defaultCalendarId;
	public String code;
	public String name;
	public String description;
	public String type;
	public boolean active;
}