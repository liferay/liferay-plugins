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

package com.liferay.calendar.hook.upgrade.v1_0_0.util;

import java.sql.Types;

/**
 * @author Jenny Chen
 */
public class CalendarBookingTable {

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"calendarBookingId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"resourceBlockId", Types.BIGINT},
		{"calendarId", Types.BIGINT},
		{"calendarResourceId", Types.BIGINT},
		{"parentCalendarBookingId", Types.BIGINT},
		{"title", Types.VARCHAR},
		{"description", Types.CLOB},
		{"location", Types.VARCHAR},
		{"startTime", Types.BIGINT},
		{"endTime", Types.BIGINT},
		{"allDay", Types.BOOLEAN},
		{"recurrence", Types.VARCHAR},
		{"firstReminder", Types.BIGINT},
		{"firstReminderType", Types.VARCHAR},
		{"secondReminder", Types.BIGINT},
		{"secondReminderType", Types.VARCHAR},
		{"status", Types.INTEGER},
		{"statusByUserId", Types.BIGINT},
		{"statusByUserName", Types.VARCHAR},
		{"statusDate", Types.TIMESTAMP}
	};

	public static final String TABLE_NAME = "CalendarBooking";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create unique index IX_113A264E on CalendarBooking (calendarId, parentCalendarBookingId)",
		"create index IX_470170B4 on CalendarBooking (calendarId, status)",
		"create index IX_B198FFC on CalendarBooking (calendarResourceId)",
		"create index IX_F7B8A941 on CalendarBooking (parentCalendarBookingId, status)",
		"create index IX_22DFDB49 on CalendarBooking (resourceBlockId)",
		"create index IX_A21D9FD5 on CalendarBooking (uuid_, companyId)",
		"create unique index IX_F4C61797 on CalendarBooking (uuid_, groupId)"
	};

	public static final String TABLE_SQL_CREATE = "create table CalendarBooking (uuid_ VARCHAR(75) null,calendarBookingId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,resourceBlockId LONG,calendarId LONG,calendarResourceId LONG,parentCalendarBookingId LONG,title STRING null,description TEXT null,location STRING null,startTime LONG,endTime LONG,allDay BOOLEAN,recurrence STRING null,firstReminder LONG,firstReminderType VARCHAR(75) null,secondReminder LONG,secondReminderType VARCHAR(75) null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table CalendarBooking";

}