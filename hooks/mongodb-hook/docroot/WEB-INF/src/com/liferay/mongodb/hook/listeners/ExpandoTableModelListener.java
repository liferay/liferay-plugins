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

package com.liferay.mongodb.hook.listeners;

import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.mongodb.util.MongoDBUtil;
import com.liferay.portal.kernel.model.BaseModelListener;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class ExpandoTableModelListener extends BaseModelListener<ExpandoTable> {

	@Override
	public void onAfterCreate(ExpandoTable expandoTable) {
		DB db = MongoDBUtil.getDB(expandoTable.getCompanyId());

		String collectionName = MongoDBUtil.getCollectionName(
			expandoTable.getClassName(), expandoTable.getName());

		if (!db.collectionExists(collectionName)) {
			DBCollection dbCollection = db.createCollection(
				collectionName, null);

			dbCollection.createIndex(new BasicDBObject("valueId", 1));
			dbCollection.createIndex(new BasicDBObject("companyId", 1));
			dbCollection.createIndex(new BasicDBObject("tableId", 1));
			dbCollection.createIndex(new BasicDBObject("rowId", 1));
			dbCollection.createIndex(new BasicDBObject("classNameId", 1));
			dbCollection.createIndex(new BasicDBObject("classPK", 1));
		}
	}

	@Override
	public void onAfterRemove(ExpandoTable expandoTable) {
		DB db = MongoDBUtil.getDB(expandoTable.getCompanyId());

		String tableName = MongoDBUtil.getCollectionName(
			expandoTable.getClassName(), expandoTable.getName());

		if (db.collectionExists(tableName)) {
			DBCollection dbCollection = db.getCollection(tableName);

			dbCollection.drop();
		}
	}

}