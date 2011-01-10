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

package com.liferay.hook.expando.mongodb.service;

import com.liferay.hook.expando.mongodb.util.MongoDBUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.DuplicateTableNameException;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoTableLocalService;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceWrapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import java.util.List;

/**
 * @author Raymond Augé
 */
public class MongoDBTableLocalService
	extends ExpandoTableLocalServiceWrapper {

	public MongoDBTableLocalService(
		ExpandoTableLocalService expandoTableLocalService) {

		super(expandoTableLocalService);
	}

	public ExpandoTable addDefaultTable(long companyId, long classNameId)
		throws PortalException, SystemException {

		return addTable(
			companyId, classNameId, ExpandoTableConstants.DEFAULT_TABLE_NAME);
	}

	public ExpandoTable addDefaultTable(long companyId, String className)
		throws PortalException, SystemException {

		return addTable(
			companyId, className, ExpandoTableConstants.DEFAULT_TABLE_NAME);
	}

	public ExpandoTable addTable(long companyId, long classNameId, String name)
		throws PortalException, SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return addTable(companyId, className, name);
	}

	public ExpandoTable addTable(long companyId, String className, String name)
		throws PortalException, SystemException {

		ExpandoTable table =  super.addTable(companyId, className, name);

		DB db = MongoDBUtil.getDB(companyId);

		String tableName = MongoDBUtil.collectionName(className, name);

		System.out.println("MongoDB collection: " + tableName);

		if (!db.collectionExists(tableName)) {
			DBCollection collection = db.createCollection(tableName, null);

			collection.createIndex(new BasicDBObject("classPK", 1));
			collection.createIndex(new BasicDBObject("rowId", 1));
			collection.createIndex(new BasicDBObject("valueId", 1));
		}

		return table;
	}

	public void deleteTable(ExpandoTable table)
		throws SystemException {

		super.deleteTable(table);

		DB db = MongoDBUtil.getDB(table.getCompanyId());

		String tableName = MongoDBUtil.collectionName(
			table.getClassName(), table.getName());

		if (db.collectionExists(tableName)) {
			DBCollection collection = db.getCollection(tableName);

			collection.drop();
		}
	}

	public void deleteTable(long companyId, long classNameId, String name)
		throws PortalException, SystemException {

		ExpandoTable table = super.getTable(companyId, classNameId, name);

		deleteTable(table);
	}

	public void deleteTable(long companyId, String className, String name)
		throws PortalException, SystemException {

		ExpandoTable table = super.getTable(companyId, className, name);

		deleteTable(table);
	}

	public void deleteTable(long tableId)
		throws PortalException, SystemException {

		ExpandoTable table = super.getTable(tableId);

		deleteTable(table);
	}

	public void deleteTables(long companyId, long classNameId)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		deleteTables(companyId, className);
	}

	public void deleteTables(long companyId, String className)
		throws SystemException {

		List<ExpandoTable> tables = super.getTables(companyId, className);

		for (ExpandoTable table : tables) {
			deleteTable(table);
		}
	}

	public ExpandoTable getDefaultTable(long companyId, long classNameId)
		throws PortalException, SystemException {

		return super.getDefaultTable(companyId, classNameId);
	}

	public ExpandoTable getDefaultTable(long companyId, String className)
		throws PortalException, SystemException {

		return super.getDefaultTable(companyId, className);
	}

	public ExpandoTable getTable(long companyId, long classNameId, String name)
		throws PortalException, SystemException {

		return super.getTable(companyId, classNameId, name);
	}

	public ExpandoTable getTable(long companyId, String className, String name)
		throws PortalException, SystemException {

		return super.getTable(companyId, className, name);
	}

	public ExpandoTable getTable(long tableId)
		throws PortalException, SystemException {

		return super.getTable(tableId);
	}

	public List<ExpandoTable> getTables(long companyId, long classNameId)
		throws SystemException {

		return super.getTables(companyId, classNameId);
	}

	public List<ExpandoTable> getTables(long companyId, String className)
		throws SystemException {

		return super.getTables(companyId, className);
	}

	public ExpandoTable updateTable(long tableId, String name)
		throws PortalException, SystemException {

		ExpandoTable table = super.getTable(tableId);

		DB db = MongoDBUtil.getDB(table.getCompanyId());

		String tableName = MongoDBUtil.collectionName(
			table.getClassName(), table.getName());
		String newTableName = MongoDBUtil.collectionName(
			table.getClassName(), name);

		if (db.collectionExists(newTableName)) {
			throw new DuplicateTableNameException(name);
		}

		table = super.updateTable(tableId, name);

		if (db.collectionExists(tableName)) {
			DBCollection collection = db.getCollection(tableName);

			collection.rename(newTableName);
		}
		else {
			db.createCollection(newTableName, null);
		}

		return table;
	}

}