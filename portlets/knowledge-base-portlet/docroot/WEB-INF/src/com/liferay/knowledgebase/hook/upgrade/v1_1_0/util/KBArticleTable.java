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
public class KBArticleTable {

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"kbArticleId", Types.BIGINT},
		{"resourcePrimKey", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"rootResourcePrimKey", Types.BIGINT},
		{"parentResourcePrimKey", Types.BIGINT}, {"version", Types.INTEGER},
		{"title", Types.VARCHAR}, {"content", Types.CLOB},
		{"description", Types.VARCHAR}, {"kbTemplateId", Types.BIGINT},
		{"priority", Types.DOUBLE}, {"sections", Types.VARCHAR},
		{"viewCount", Types.INTEGER}, {"latest", Types.BOOLEAN},
		{"main", Types.BOOLEAN}, {"status", Types.INTEGER},
		{"statusByUserId", Types.BIGINT}, {"statusByUserName", Types.VARCHAR},
		{"statusDate", Types.TIMESTAMP}
	};

	public static final String TABLE_NAME = "KBArticle";

	public static final String TABLE_SQL_CREATE = "create table KBArticle (uuid_ VARCHAR(75) null,kbArticleId LONG not null primary key,resourcePrimKey LONG,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,rootResourcePrimKey LONG,parentResourcePrimKey LONG,version INTEGER,title STRING null,content TEXT null,description STRING null,kbTemplateId LONG,priority DOUBLE,sections STRING null,viewCount INTEGER,latest BOOLEAN,main BOOLEAN,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table KBArticle";

}