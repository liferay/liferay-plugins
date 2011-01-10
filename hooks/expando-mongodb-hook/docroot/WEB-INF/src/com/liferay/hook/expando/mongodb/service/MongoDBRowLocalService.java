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

import com.liferay.hook.expando.mongodb.service.model.MongoExpandoRow;
import com.liferay.hook.expando.mongodb.util.MongoDBUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoRowLocalService;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceWrapper;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raymond Augé
 */
public class MongoDBRowLocalService
	extends ExpandoRowLocalServiceWrapper {

	public MongoDBRowLocalService(
		ExpandoRowLocalService expandoRowLocalService) {

		super(expandoRowLocalService);
	}

	public void deleteRow(long tableId, long classPK)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(tableId);

		deleteRow(
			table.getCompanyId(), table.getClassName(), table.getName(),
			classPK);
	}

	public void deleteRow(
			long companyId, long classNameId, String name, long classPK)
		throws PortalException, SystemException {

		String className = PortalUtil.getClassName(classNameId);

		deleteRow(companyId, className, name, classPK);
	}

	public void deleteRow(
			long companyId, String className, String name, long classPK)
		throws PortalException, SystemException {

		DBCollection collection = MongoDBUtil.getCollection(
			companyId, className, name);

		collection.remove(new BasicDBObject("classPK", classPK));
	}

	public List<ExpandoRow> getDefaultTableRows(
			long companyId, long classNameId, int start, int end)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return getDefaultTableRows(companyId, className, start, end);
	}

	public List<ExpandoRow> getDefaultTableRows(
			long companyId, String className, int start, int end)
		throws SystemException {

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getDefaultTable(
				companyId, className);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		return getRows(table, start, end);
	}


	public int getDefaultTableRowsCount(long companyId, long classNameId)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return getRowsCount(
			companyId, className, ExpandoTableConstants.DEFAULT_TABLE_NAME);
	}

	public int getDefaultTableRowsCount(long companyId, String className)
		throws SystemException {

		return getRowsCount(
			companyId, className, ExpandoTableConstants.DEFAULT_TABLE_NAME);
	}

	public ExpandoRow getRow(long rowId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	public ExpandoRow getRow(ExpandoTable table, long classPK)
		throws SystemException {

		DBCollection collection = MongoDBUtil.getCollection(table);

		DBObject dbObject = collection.findOne(
			new BasicDBObject("classPK", classPK));

		ExpandoRow row = new MongoExpandoRow();

		row.setRowId((Long)dbObject.get("rowId"));
		row.setCompanyId(table.getCompanyId());
		row.setTableId(table.getTableId());
		row.setClassPK((Long)dbObject.get("classPK"));

		return row;
	}

	public ExpandoRow getRow(long tableId, long classPK)
		throws PortalException, SystemException {

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(tableId);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		return getRow(table, classPK);
	}

	public ExpandoRow getRow(
			long companyId, long classNameId, String name, long classPK)
		throws SystemException {

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(
				companyId, classNameId, name);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		return getRow(table, classPK);
	}

	public ExpandoRow getRow(
			long companyId, String className, String name, long classPK)
		throws SystemException {

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(
				companyId, className, name);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		return getRow(table, classPK);
	}

	public List<ExpandoRow> getRows(ExpandoTable table, int start, int end)
		throws SystemException {

		DBCollection collection = MongoDBUtil.getCollection(table);

		DBCursor cursor = collection.find(new BasicDBObject());

		if ((start != QueryUtil.ALL_POS) && (end != QueryUtil.ALL_POS)) {
			cursor.limit(start);
			cursor.limit(end - start);
		}

		List<ExpandoRow> results = new ArrayList<ExpandoRow>();

		for (DBObject dbObject : cursor.toArray()) {
			MongoExpandoRow row = new MongoExpandoRow();

			row.setRowId((Long)dbObject.get("rowId"));
			row.setCompanyId(table.getCompanyId());
			row.setTableId(table.getTableId());
			row.setClassPK((Long)dbObject.get("classPK"));

			results.add(row);
		}

		return results;
	}

	public List<ExpandoRow> getRows(long tableId, int start, int end)
		throws SystemException {

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(tableId);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		return getRows(table, start, end);
	}

	public List<ExpandoRow> getRows(
			long companyId, long classNameId, String name, int start, int end)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return getRows(companyId, className, name, start, end);
	}

	public List<ExpandoRow> getRows(
			long companyId, String className, String name, int start,
			int end)
		throws SystemException {

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(
				companyId, className, name);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		return getRows(table, start, end);
	}

	public int getRowsCount(long companyId, long classNameId, String tableName)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return getRowsCount(companyId, className, tableName);
	}

	public int getRowsCount(long companyId, String className, String name)
		throws SystemException {

		DBCollection collection = MongoDBUtil.getCollection(
			companyId, className, name);

		return (int)collection.count();
	}

	public int getRowsCount(long tableId)
		throws SystemException {

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(tableId);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		return getRowsCount(
			table.getCompanyId(), table.getClassName(), table.getName());
	}

}