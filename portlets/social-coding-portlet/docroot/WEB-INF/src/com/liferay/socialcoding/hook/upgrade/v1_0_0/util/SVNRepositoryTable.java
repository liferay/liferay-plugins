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

package com.liferay.socialcoding.hook.upgrade.v1_0_0.util;

import java.sql.Types;

/**
 * @author Brian Wing Shun Chan
 */
public class SVNRepositoryTable {

	public static final Object[][] TABLE_COLUMNS = {
		{"svnRepositoryId", Integer.valueOf(Types.BIGINT)},
		{"url", Integer.valueOf(Types.VARCHAR)},
		{"revisionNumber", Integer.valueOf(Types.BIGINT)}
	};

	public static final String TABLE_NAME = "SC_SVNRepository";

	public static final String TABLE_SQL_CREATE = "create table SC_SVNRepository (svnRepositoryId LONG not null primary key,url VARCHAR(200) null,revisionNumber LONG)";

	public static final String TABLE_SQL_DROP = "drop table SC_SVNRepository";

}