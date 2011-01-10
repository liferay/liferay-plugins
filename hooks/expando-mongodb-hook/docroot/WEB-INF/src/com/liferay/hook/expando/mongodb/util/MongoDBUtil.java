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

package com.liferay.hook.expando.mongodb.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.expando.model.ExpandoTable;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

/**
 * @author Raymond Augé
 */
public class MongoDBUtil {

	private MongoDBUtil(Mongo mongo, String dbName) {
		_dbName = dbName;
		_mongo = mongo;
	}

	public static String collectionName(String className, String name) {
		return className.concat(StringPool.POUND).concat(name);
	}

	public static DBCollection getCollection(ExpandoTable table) {
		DB db = getDB(table.getCompanyId());

		String tableName = MongoDBUtil.collectionName(
			table.getClassName(), table.getName());

		return db.getCollection(tableName);
	}

	public static DBCollection getCollection(
		long companyId, String className, String name) {

		DB db = getDB(companyId);

		String tableName = MongoDBUtil.collectionName(className, name);

		return db.getCollection(tableName);
	}

	public static DB getDB(long companyId) {
		return getMongo().getDB(createKey(getDBName(), companyId));
	}

	public static Mongo getMongo() {
		return _instance._mongo;
	}

	public static void init(Mongo mongo, String dbName) {
		_instance = new MongoDBUtil(mongo, dbName);
	}

	private static String createKey(String dbName, long companyId) {
		return dbName.concat(StringPool.UNDERLINE).concat(
			String.valueOf(companyId));
	}

	private static String getDBName() {
		return _instance._dbName;
	}

	private static MongoDBUtil _instance;

	private String _dbName;
	private Mongo _mongo;

}