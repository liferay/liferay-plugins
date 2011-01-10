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

import com.liferay.hook.expando.mongodb.service.model.MongoExpandoValue;
import com.liferay.hook.expando.mongodb.util.MongoDB;
import com.liferay.hook.expando.mongodb.util.MongoDBUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.expando.ColumnNameException;
import com.liferay.portlet.expando.ColumnTypeException;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.NoSuchColumnException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalService;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceWrapper;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.util.ExpandoBridgeIndexer;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import java.util.Date;
import java.util.List;

/**
 * @author Raymond Augé
 */
public class MongoDBColumnLocalService
	extends ExpandoColumnLocalServiceWrapper {

	public MongoDBColumnLocalService(
		ExpandoColumnLocalService expandoColumnLocalService) {

		super(expandoColumnLocalService);
	}

	public void deleteColumn(ExpandoColumn column)
		throws SystemException {

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(
				column.getTableId());
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		super.deleteColumn(column);

		DBCollection collection = MongoDBUtil.getCollection(table);

		collection.dropIndex(column.getName());

		BasicDBObject query = new BasicDBObject();
		BasicDBObject action = new BasicDBObject(
			MongoDB.OPS.UNSET, new BasicDBObject(column.getName(), 1));

		collection.update(query, action, false, true);
	}

	public void deleteColumn(
			long companyId, long classNameId, String tableName, String name)
		throws PortalException, SystemException {

		ExpandoColumn column = super.getColumn(
			companyId, classNameId, tableName, name);

		deleteColumn(column);
	}

	public void deleteColumn(
			long companyId, String className, String tableName, String name)
		throws PortalException, SystemException {

		ExpandoColumn column = super.getColumn(
			companyId, className, tableName, name);

		deleteColumn(column);
	}

	public void deleteColumn(long tableId, String name)
		throws PortalException, SystemException {

		ExpandoColumn column = super.getColumn(tableId, name);

		deleteColumn(column);
	}

	public void deleteColumn(long columnId)
		throws PortalException, SystemException {

		ExpandoColumn column = super.getColumn(columnId);

		deleteColumn(column);
	}

	public void deleteColumns(
			long companyId, long classNameId, String tableName)
		throws PortalException, SystemException {

		List<ExpandoColumn> columns = super.getColumns(
			companyId, classNameId, tableName);

		for (ExpandoColumn column : columns) {
			deleteColumn(column);
		}
	}

	public void deleteColumns(
			long companyId, String className, String tableName)
		throws PortalException, SystemException {

		List<ExpandoColumn> columns = super.getColumns(
			companyId, className, tableName);

		for (ExpandoColumn column : columns) {
			deleteColumn(column);
		}
	}

	public void deleteColumns(long tableId)
		throws SystemException {

		List<ExpandoColumn> columns = super.getColumns(tableId);

		for (ExpandoColumn column : columns) {
			deleteColumn(column);
		}
	}

	public ExpandoColumn updateColumn(
			long columnId, String name, int type, Object defaultData)
		throws PortalException, SystemException {

		ExpandoColumn column = super.updateColumn(
			columnId, name, type, defaultData);

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			column.getTableId());

		String oldName = column.getName();

		DBCollection collection = MongoDBUtil.getCollection(table);

		BasicDBObject query = new BasicDBObject();
		BasicDBObject action = new BasicDBObject(
			MongoDB.OPS.RENAME, new BasicDBObject(oldName, name));

		collection.update(query, action);

		return column;
	}

	public ExpandoColumn updateColumn(long columnId, String name, int type)
		throws PortalException, SystemException {

		return updateColumn(columnId, name, type, null);
	}

	public ExpandoColumn updateTypeSettings(long columnId, String typeSettings)
		throws PortalException, SystemException {

		ExpandoColumn column = super.updateTypeSettings(columnId, typeSettings);

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			column.getTableId());

		UnicodeProperties properties = column.getTypeSettingsProperties();

		boolean indexable = GetterUtil.getBoolean(
			properties.getProperty(ExpandoBridgeIndexer.INDEXABLE));

		if (indexable) {
			DBCollection collection = MongoDBUtil.getCollection(table);

			collection.createIndex(new BasicDBObject(column.getName(), 1));
		}

		return column;
	}


	protected ExpandoValue validate(
			long columnId, long tableId, String name, int type,
			Object defaultData)
		throws PortalException, SystemException {

		if (Validator.isNull(name)) {
			throw new ColumnNameException();
		}

		try {
			ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
				tableId, name);

			if ((column != null) && (column.getColumnId() != columnId)) {
				throw new DuplicateColumnNameException();
			}
		}
		catch (NoSuchColumnException nsce) {
		}

		if ((type != ExpandoColumnConstants.BOOLEAN) &&
			(type != ExpandoColumnConstants.BOOLEAN_ARRAY) &&
			(type != ExpandoColumnConstants.DATE) &&
			(type != ExpandoColumnConstants.DATE_ARRAY) &&
			(type != ExpandoColumnConstants.DOUBLE) &&
			(type != ExpandoColumnConstants.DOUBLE_ARRAY) &&
			(type != ExpandoColumnConstants.FLOAT) &&
			(type != ExpandoColumnConstants.FLOAT_ARRAY) &&
			(type != ExpandoColumnConstants.INTEGER) &&
			(type != ExpandoColumnConstants.INTEGER_ARRAY) &&
			(type != ExpandoColumnConstants.LONG) &&
			(type != ExpandoColumnConstants.LONG_ARRAY) &&
			(type != ExpandoColumnConstants.SHORT) &&
			(type != ExpandoColumnConstants.SHORT_ARRAY) &&
			(type != ExpandoColumnConstants.STRING) &&
			(type != ExpandoColumnConstants.STRING_ARRAY)) {

			throw new ColumnTypeException();
		}

		ExpandoValue value = new MongoExpandoValue();

		if (defaultData != null) {
			value.setColumnId(columnId);

			if (type == ExpandoColumnConstants.BOOLEAN) {
				value.setBoolean((Boolean)defaultData);
			}
			else if (type == ExpandoColumnConstants.BOOLEAN_ARRAY) {
				value.setBooleanArray((boolean[])defaultData);
			}
			else if (type == ExpandoColumnConstants.DATE) {
				value.setDate((Date)defaultData);
			}
			else if (type == ExpandoColumnConstants.DATE_ARRAY) {
				value.setDateArray((Date[])defaultData);
			}
			else if (type == ExpandoColumnConstants.DOUBLE) {
				value.setDouble((Double)defaultData);
			}
			else if (type == ExpandoColumnConstants.DOUBLE_ARRAY) {
				value.setDoubleArray((double[])defaultData);
			}
			else if (type == ExpandoColumnConstants.FLOAT) {
				value.setFloat((Float)defaultData);
			}
			else if (type == ExpandoColumnConstants.FLOAT_ARRAY) {
				value.setFloatArray((float[])defaultData);
			}
			else if (type == ExpandoColumnConstants.INTEGER) {
				value.setInteger((Integer)defaultData);
			}
			else if (type == ExpandoColumnConstants.INTEGER_ARRAY) {
				value.setIntegerArray((int[])defaultData);
			}
			else if (type == ExpandoColumnConstants.LONG) {
				value.setLong((Long)defaultData);
			}
			else if (type == ExpandoColumnConstants.LONG_ARRAY) {
				value.setLongArray((long[])defaultData);
			}
			else if (type == ExpandoColumnConstants.SHORT) {
				value.setShort((Short)defaultData);
			}
			else if (type == ExpandoColumnConstants.SHORT_ARRAY) {
				value.setShortArray((short[])defaultData);
			}
			else if (type == ExpandoColumnConstants.STRING) {
				value.setString((String)defaultData);
			}
			else if (type == ExpandoColumnConstants.STRING_ARRAY) {
				value.setStringArray((String[])defaultData);
			}
		}

		return value;
	}

}