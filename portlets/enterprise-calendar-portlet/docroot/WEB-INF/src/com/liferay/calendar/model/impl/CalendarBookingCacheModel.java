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

		calendarBookingImpl.setCalendarId(calendarId);
		calendarBookingImpl.setCalendarResourceId(calendarResourceId);
		calendarBookingImpl.setParentCalendarBookingId(parentCalendarBookingId);

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

		if (type == null) {
			calendarBookingImpl.setType(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setType(type);
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

		calendarBookingImpl.setAllDay(allDay);

		if (recurrence == null) {
			calendarBookingImpl.setRecurrence(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setRecurrence(recurrence);
		}

		calendarBookingImpl.setPriority(priority);
		calendarBookingImpl.setOutOfOffice(outOfOffice);
		calendarBookingImpl.setRemindBy(remindBy);
		calendarBookingImpl.setFirstReminder(firstReminder);
		calendarBookingImpl.setSecondReminder(secondReminder);
		calendarBookingImpl.setRequired(required);

		if (requestMessage == null) {
			calendarBookingImpl.setRequestMessage(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setRequestMessage(requestMessage);
		}

		if (responseMessage == null) {
			calendarBookingImpl.setResponseMessage(StringPool.BLANK);
		}
		else {
			calendarBookingImpl.setResponseMessage(responseMessage);
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
	public String toString() {
		StringBundler sb = new StringBundler(63);

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
		sb.append(", calendarId=");
		sb.append(calendarId);
		sb.append(", calendarResourceId=");
		sb.append(calendarResourceId);
		sb.append(", parentCalendarBookingId=");
		sb.append(parentCalendarBookingId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", location=");
		sb.append(location);
		sb.append(", type=");
		sb.append(type);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", allDay=");
		sb.append(allDay);
		sb.append(", recurrence=");
		sb.append(recurrence);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", outOfOffice=");
		sb.append(outOfOffice);
		sb.append(", remindBy=");
		sb.append(remindBy);
		sb.append(", firstReminder=");
		sb.append(firstReminder);
		sb.append(", secondReminder=");
		sb.append(secondReminder);
		sb.append(", required=");
		sb.append(required);
		sb.append(", requestMessage=");
		sb.append(requestMessage);
		sb.append(", responseMessage=");
		sb.append(responseMessage);
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

	public String uuid;
	public long calendarBookingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long calendarId;
	public long calendarResourceId;
	public long parentCalendarBookingId;
	public String title;
	public String description;
	public String location;
	public String type;
	public long startDate;
	public long endDate;
	public boolean allDay;
	public String recurrence;
	public int priority;
	public boolean outOfOffice;
	public int remindBy;
	public int firstReminder;
	public int secondReminder;
	public boolean required;
	public String requestMessage;
	public String responseMessage;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}