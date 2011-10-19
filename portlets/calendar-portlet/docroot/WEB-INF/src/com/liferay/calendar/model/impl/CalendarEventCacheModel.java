/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.calendar.model.CalendarEvent;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing CalendarEvent in entity cache.
 *
 * @author Eduardo Lundgren
 * @see CalendarEvent
 * @generated
 */
public class CalendarEventCacheModel implements CacheModel<CalendarEvent>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", calendarEventId=");
		sb.append(calendarEventId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", location=");
		sb.append(location);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", durationHour=");
		sb.append(durationHour);
		sb.append(", durationMinute=");
		sb.append(durationMinute);
		sb.append(", allDay=");
		sb.append(allDay);
		sb.append(", recurrence=");
		sb.append(recurrence);
		sb.append(", type=");
		sb.append(type);
		sb.append(", remindBy=");
		sb.append(remindBy);
		sb.append(", firstReminder=");
		sb.append(firstReminder);
		sb.append(", secondReminder=");
		sb.append(secondReminder);
		sb.append("}");

		return sb.toString();
	}

	public CalendarEvent toEntityModel() {
		CalendarEventImpl calendarEventImpl = new CalendarEventImpl();

		if (uuid == null) {
			calendarEventImpl.setUuid(StringPool.BLANK);
		}
		else {
			calendarEventImpl.setUuid(uuid);
		}

		calendarEventImpl.setCalendarEventId(calendarEventId);
		calendarEventImpl.setGroupId(groupId);
		calendarEventImpl.setCompanyId(companyId);
		calendarEventImpl.setUserId(userId);

		if (userName == null) {
			calendarEventImpl.setUserName(StringPool.BLANK);
		}
		else {
			calendarEventImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			calendarEventImpl.setCreateDate(null);
		}
		else {
			calendarEventImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			calendarEventImpl.setModifiedDate(null);
		}
		else {
			calendarEventImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			calendarEventImpl.setTitle(StringPool.BLANK);
		}
		else {
			calendarEventImpl.setTitle(title);
		}

		if (description == null) {
			calendarEventImpl.setDescription(StringPool.BLANK);
		}
		else {
			calendarEventImpl.setDescription(description);
		}

		if (location == null) {
			calendarEventImpl.setLocation(StringPool.BLANK);
		}
		else {
			calendarEventImpl.setLocation(location);
		}

		if (startDate == Long.MIN_VALUE) {
			calendarEventImpl.setStartDate(null);
		}
		else {
			calendarEventImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			calendarEventImpl.setEndDate(null);
		}
		else {
			calendarEventImpl.setEndDate(new Date(endDate));
		}

		calendarEventImpl.setDurationHour(durationHour);
		calendarEventImpl.setDurationMinute(durationMinute);
		calendarEventImpl.setAllDay(allDay);

		if (recurrence == null) {
			calendarEventImpl.setRecurrence(StringPool.BLANK);
		}
		else {
			calendarEventImpl.setRecurrence(recurrence);
		}

		if (type == null) {
			calendarEventImpl.setType(StringPool.BLANK);
		}
		else {
			calendarEventImpl.setType(type);
		}

		calendarEventImpl.setRemindBy(remindBy);
		calendarEventImpl.setFirstReminder(firstReminder);
		calendarEventImpl.setSecondReminder(secondReminder);

		calendarEventImpl.resetOriginalValues();

		return calendarEventImpl;
	}

	public String uuid;
	public long calendarEventId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String description;
	public String location;
	public long startDate;
	public long endDate;
	public int durationHour;
	public int durationMinute;
	public boolean allDay;
	public String recurrence;
	public String type;
	public int remindBy;
	public int firstReminder;
	public int secondReminder;
}