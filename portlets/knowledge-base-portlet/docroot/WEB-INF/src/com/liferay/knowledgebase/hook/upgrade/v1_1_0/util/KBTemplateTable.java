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

package com.liferay.knowledgebase.hook.upgrade.v1_1_0.util;

import java.sql.Types;

/**
 * @author Peter Shin
 */
public class KBTemplateTable {

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"kbTemplateId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"title", Types.VARCHAR}, {"content", Types.CLOB},
		{"engineType", Types.INTEGER},
		{"cacheable", Types.BOOLEAN}
	};

	public static final String TABLE_NAME = "KBTemplate";

	public static final String TABLE_SQL_CREATE = "create table KBTemplate (uuid_ VARCHAR(75) null,kbTemplateId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,title STRING null,content TEXT null,engineType INTEGER,cacheable BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table KBTemplate";

}