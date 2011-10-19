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

import com.liferay.calendar.model.CalendarBooking;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing CalendarBooking in entity cache.
 *
 * @author Eduardo Lundgren
 * @see CalendarBooking
 * @generated
 */
public class CalendarBookingCacheModel implements CacheModel<CalendarBooking>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(55);

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
		sb.append(", calendarEventId=");
		sb.append(calendarEventId);
		sb.append(", calendarResourceId=");
		sb.append(calendarResourceId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", title=");
		sb.append(title);
		sb.append(", name=");
		sb.append(name);
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
		sb.append(", recurrence=");
		sb.append(recurrence);
		sb.append(", type=");
		sb.append(type);
		sb.append(", required=");
		sb.append(required);
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

		calendarBookingImpl.setCalendarEventId(calendarEventId);
		calendarBookingImpl.setCalendarResourceId(calendarResourceId);
		calendarBookingImpl.setClassNameId(classNameId);
		calendarBookingImpl.setClassPK(classPK);

		if (title == null) {
			calendarBookingImpl.setTitle(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setTitle(title);
		}

		if (name == null) {
			calendarBookingImpl.setName(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setName(name);
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

		if (startDate == Long.MIN_VALUE) {
			calendarBookingImpl.setStartDate(null);
		}
		else {
			calendarBookingImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			calendarBookingImpl.setEndDate(null);
		}
		else {
			calendarBookingImpl.setEndDate(new Date(endDate));
		}

		calendarBookingImpl.setDurationHour(durationHour);
		calendarBookingImpl.setDurationMinute(durationMinute);

		if (recurrence == null) {
			calendarBookingImpl.setRecurrence(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setRecurrence(recurrence);
		}

		if (type == null) {
			calendarBookingImpl.setType(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setType(type);
		}

		calendarBookingImpl.setRequired(required);
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

	public String uuid;
	public long calendarBookingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long calendarEventId;
	public long calendarResourceId;
	public long classNameId;
	public long classPK;
	public String title;
	public String name;
	public String description;
	public String location;
	public long startDate;
	public long endDate;
	public int durationHour;
	public int durationMinute;
	public String recurrence;
	public String type;
	public boolean required;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}