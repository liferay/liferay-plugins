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

package com.liferay.socialcoding.hook.upgrade.v1_0_0.util;

import java.sql.Types;

/**
 * @author Brian Wing Shun Chan
 */
public class SVNRevisionTable {

	public static final String TABLE_NAME = "SC_SVNRevision";

	public static final Object[][] TABLE_COLUMNS = {
		{"svnRevisionId", new Integer(Types.BIGINT)},
		{"svnUserId", new Integer(Types.VARCHAR)},
		{"createDate", new Integer(Types.TIMESTAMP)},
		{"svnRepositoryId", new Integer(Types.BIGINT)},
		{"revisionNumber", new Integer(Types.BIGINT)},
		{"comments", new Integer(Types.VARCHAR)}
	};

	public static final String TABLE_SQL_CREATE = "create table SC_SVNRevision (svnRevisionId LONG not null primary key,svnUserId VARCHAR(75) null,createDate DATE null,svnRepositoryId LONG,revisionNumber LONG,comments STRING null)";

	public static final String TABLE_SQL_DROP = "drop table SC_SVNRevision";

}