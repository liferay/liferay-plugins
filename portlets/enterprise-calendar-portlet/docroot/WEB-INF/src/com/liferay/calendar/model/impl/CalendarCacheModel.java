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

import com.liferay.calendar.model.Calendar;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Calendar in entity cache.
 *
 * @author Eduardo Lundgren
 * @see Calendar
 * @generated
 */
public class CalendarCacheModel implements CacheModel<Calendar>, Serializable {
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

		calendarImpl.setColor(color);
		calendarImpl.setDefaultCalendar(defaultCalendar);

		calendarImpl.resetOriginalValues();

		return calendarImpl;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

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
		sb.append(", color=");
		sb.append(color);
		sb.append(", defaultCalendar=");
		sb.append(defaultCalendar);
		sb.append("}");

		return sb.toString();
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
	public int color;
	public boolean defaultCalendar;
}