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

package com.liferay.mongodb.hook.service.impl;

import com.liferay.mongodb.util.MongoDBUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.expando.DuplicateTableNameException;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoTableLocalService;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceWrapper;

import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class MongoExpandoTableLocalServiceImpl
	extends ExpandoTableLocalServiceWrapper {

	public MongoExpandoTableLocalServiceImpl(
		ExpandoTableLocalService expandoTableLocalService) {

		super(expandoTableLocalService);
	}

	@Override
	public ExpandoTable updateTable(long tableId, String name)
		throws PortalException, SystemException {

		ExpandoTable expandoTable = super.getTable(tableId);

		DB db = MongoDBUtil.getDB(expandoTable.getCompanyId());

		String collectionName = MongoDBUtil.getCollectionName(
			expandoTable.getClassName(), expandoTable.getName());
		String newCollectionName = MongoDBUtil.getCollectionName(
			expandoTable.getClassName(), name);

		if (db.collectionExists(newCollectionName)) {
			throw new DuplicateTableNameException(name);
		}

		expandoTable = super.updateTable(tableId, name);

		if (db.collectionExists(collectionName)) {
			DBCollection dbCollection = db.getCollection(collectionName);

			dbCollection.rename(newCollectionName);
		}
		else {
			db.createCollection(newCollectionName, null);
		}

		return expandoTable;
	}

}