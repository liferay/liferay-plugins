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

package com.liferay.socialnetworking.hook.upgrade.v1_0_0.util;

import java.sql.Types;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetupsEntryTable {

	public static final String TABLE_NAME = "SN_MeetupsEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"meetupsEntryId", new Integer(Types.BIGINT)},
		{"companyId", new Integer(Types.BIGINT)},
		{"userId", new Integer(Types.BIGINT)},
		{"userName", new Integer(Types.VARCHAR)},
		{"createDate", new Integer(Types.TIMESTAMP)},
		{"modifiedDate", new Integer(Types.TIMESTAMP)},
		{"title", new Integer(Types.VARCHAR)},
		{"description", new Integer(Types.VARCHAR)},
		{"startDate", new Integer(Types.TIMESTAMP)},
		{"endDate", new Integer(Types.TIMESTAMP)},
		{"totalAttendees", new Integer(Types.INTEGER)},
		{"maxAttendees", new Integer(Types.INTEGER)},
		{"price", new Integer(Types.DOUBLE)},
		{"thumbnailId", new Integer(Types.BIGINT)}
	};

	public static final String TABLE_SQL_CREATE = "create table SN_MeetupsEntry (meetupsEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,title VARCHAR(75) null,description STRING null,startDate DATE null,endDate DATE null,totalAttendees INTEGER,maxAttendees INTEGER,price DOUBLE,thumbnailId LONG)";

	public static final String TABLE_SQL_DROP = "drop table SN_MeetupsEntry";

}