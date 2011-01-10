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

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.hook.expando.mongodb.service.model.MongoExpandoValue;
import com.liferay.hook.expando.mongodb.util.MongoDB;
import com.liferay.hook.expando.mongodb.util.MongoDBUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.NoSuchColumnException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalService;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceWrapper;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public class MongoDBValueLocalService
	extends ExpandoValueLocalServiceWrapper {

	public MongoDBValueLocalService(
		ExpandoValueLocalService expandoValueLocalService) {

		super(expandoValueLocalService);
	}

	public ExpandoValue addValue(
			long classNameId, long tableId, long columnId, long classPK,
			String data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(tableId);
		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			columnId);

		DBCollection collection = MongoDBUtil.getCollection(table);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setTableId(tableId);
		value.setColumnId(columnId);
		value.setRowId(classPK);
		value.setClassNameId(classNameId);
		value.setClassPK(classPK);
		value.setData(data);

		BasicDBObject query = new BasicDBObject();

		query.put("companyId", table.getCompanyId());
		query.put("tableId", tableId);
		query.put("rowId", classPK);
		query.put("classNameId", classNameId);
		query.put("classPK", classPK);

		BasicDBObject result = (BasicDBObject)collection.findOne(query);

		if (result == null) {
			long valueId = CounterLocalServiceUtil.increment();

			value.setValueId(valueId);
			value.setNew(true);

			query.put("valueId", valueId);

			query.put(column.getName(), getData(column, value));

			collection.insert(query);
		}
		else {
			value.setValueId(result.getLong("valueId"));

			Object dataObject = getData(column, value);

			BasicDBObject action = new BasicDBObject();

			action.put(
				MongoDB.OPS.SET, new BasicDBObject(
					column.getName(), dataObject));

			collection.update(result , action);
		}

		return value;
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, boolean data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setBoolean(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, boolean[] data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setBooleanArray(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, Date data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setDate(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, Date[] data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setDateArray(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, double data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setDouble(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, double[] data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setDoubleArray(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, float data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setFloat(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, float[] data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setFloatArray(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, int data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setInteger(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, int[] data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setIntegerArray(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, long data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setLong(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, long[] data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setLongArray(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, Object data)
		throws PortalException, SystemException {

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			companyId, className, tableName, columnName);

		int type = column.getType();

		if (type == ExpandoColumnConstants.BOOLEAN) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				((Boolean)data).booleanValue());
		}
		else if (type == ExpandoColumnConstants.BOOLEAN_ARRAY) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				(boolean[])data);
		}
		else if (type == ExpandoColumnConstants.DATE) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				(Date)data);
		}
		else if (type == ExpandoColumnConstants.DATE_ARRAY) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				(Date[])data);
		}
		else if (type == ExpandoColumnConstants.DOUBLE) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				((Double)data).doubleValue());
		}
		else if (type == ExpandoColumnConstants.DOUBLE_ARRAY) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				(double[])data);
		}
		else if (type == ExpandoColumnConstants.FLOAT) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				((Float)data).floatValue());
		}
		else if (type == ExpandoColumnConstants.FLOAT_ARRAY) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				(float[])data);
		}
		else if (type == ExpandoColumnConstants.INTEGER) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				((Integer)data).intValue());
		}
		else if (type == ExpandoColumnConstants.INTEGER_ARRAY) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				(int[])data);
		}
		else if (type == ExpandoColumnConstants.LONG) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				((Long)data).longValue());
		}
		else if (type == ExpandoColumnConstants.LONG_ARRAY) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				(long[])data);
		}
		else if (type == ExpandoColumnConstants.SHORT) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				((Short)data).shortValue());
		}
		else if (type == ExpandoColumnConstants.SHORT_ARRAY) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				(short[])data);
		}
		else if (type == ExpandoColumnConstants.STRING_ARRAY) {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				(String[])data);
		}
		else {
			return addValue(
				companyId, className, tableName, columnName, classPK,
				(String)data);
		}
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, short data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setShort(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, short[] data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setShortArray(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, String data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setString(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public ExpandoValue addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, String[] data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
			companyId, className, tableName);

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			table.getTableId(), columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setColumnId(column.getColumnId());
		value.setStringArray(data);

		return addValue(
			table.getClassNameId(), table.getTableId(), column.getColumnId(),
			classPK, value.getData());
	}

	public void addValues(
			long classNameId, long tableId, List<ExpandoColumn> columns,
			long classPK, Map<String, String> data)
		throws PortalException, SystemException {

		ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(tableId);

		DBCollection collection = MongoDBUtil.getCollection(table);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(table.getCompanyId());
		value.setTableId(tableId);
		value.setRowId(classPK);
		value.setClassNameId(classNameId);
		value.setClassPK(classPK);

		BasicDBObject dbObject = new BasicDBObject();

		dbObject.put("companyId", table.getCompanyId());
		dbObject.put("tableId", tableId);
		dbObject.put("rowId", classPK);
		dbObject.put("classNameId", classNameId);
		dbObject.put("classPK", classPK);

		BasicDBObject result = (BasicDBObject)collection.findOne(dbObject);

		if (result == null) {
			long valueId = CounterLocalServiceUtil.increment();

			value.setValueId(valueId);
			value.setNew(true);

			dbObject.put("valueId", valueId);

			putColumnData(dbObject, columns, data, value);

			collection.insert(dbObject);
		}
		else {
			value.setValueId(result.getLong("valueId"));
			value.setNew(false);

			BasicDBObject values = new BasicDBObject();

			putColumnData(values, columns, data, value);

			BasicDBObject action = new BasicDBObject();

			action.put(MongoDB.OPS.SET, values);

			collection.update(result , action);
		}
	}

	public void deleteColumnValues(long columnId)
		throws SystemException {

		ExpandoColumn column = null;
		ExpandoTable table = null;

		try {
			column = ExpandoColumnLocalServiceUtil.getColumn(columnId);
			table = ExpandoTableLocalServiceUtil.getTable(
				column.getTableId());
		}
		catch (NoSuchColumnException nsce) {
			return;
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		DBCollection collection = MongoDBUtil.getCollection(table);

		BasicDBObject query = new BasicDBObject();
		BasicDBObject action = new BasicDBObject(
			MongoDB.OPS.SET, new BasicDBObject(column.getName(), null));

		collection.update(query, action, false, true);
	}

	public void deleteRowValues(long rowId)
		throws SystemException {

		throw new UnsupportedOperationException();
	}

	public void deleteTableValues(long tableId)
		throws SystemException {

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(tableId);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		DBCollection collection = MongoDBUtil.getCollection(table);

		BasicDBObject objectData = new BasicDBObject();

		List<ExpandoColumn> columns = ExpandoColumnLocalServiceUtil.getColumns(
			tableId);

		for (ExpandoColumn column : columns) {
			objectData.put(column.getName(), null);
		}

		BasicDBObject query = new BasicDBObject();
		BasicDBObject action = new BasicDBObject(MongoDB.OPS.SET, objectData);

		collection.update(query, action, false, true);
	}

	public void deleteValue(long columnId, long rowId)
		throws PortalException, SystemException {

		ExpandoColumn column = null;
		ExpandoTable table = null;

		try {
			column = ExpandoColumnLocalServiceUtil.getColumn(columnId);
			table = ExpandoTableLocalServiceUtil.getTable(column.getTableId());
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		deleteValue(
			table.getCompanyId(), table.getClassName(), table.getName(),
			column.getName(), rowId);
	}

	public void deleteValue(
			long companyId, long classNameId, String name, String columnName,
			long classPK)
		throws PortalException, SystemException {

		String className = PortalUtil.getClassName(classNameId);

		deleteValue(companyId, className, name, columnName, classPK);
	}

	public void deleteValue(
			long companyId, String className, String name, String columnName,
			long classPK)
		throws PortalException, SystemException {

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(
				companyId, className, name);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		DBCollection collection = MongoDBUtil.getCollection(table);

		BasicDBObject objectData = new BasicDBObject();

		objectData.put(columnName, null);

		BasicDBObject query = new BasicDBObject();

		query.put("companyId", table.getCompanyId());
		query.put("tableId", table.getTableId());
		query.put("rowId", classPK);
		query.put("classNameId", table.getClassNameId());
		query.put("classPK", classPK);

		BasicDBObject action = new BasicDBObject(MongoDB.OPS.SET, objectData);

		collection.update(query, action);
	}

	public void deleteValue(long valueId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	public void deleteValues(long classNameId, long classPK)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		deleteValues(className, classPK);
	}

	public void deleteValues(String className, long classPK)
		throws SystemException {

		long companyId = CompanyThreadLocal.getCompanyId();

		List<ExpandoTable> tables = ExpandoTableLocalServiceUtil.getTables(
			companyId, className);

		for (ExpandoTable table : tables) {
			DBCollection collection = MongoDBUtil.getCollection(table);

			BasicDBObject query = new BasicDBObject();

			query.put("companyId", table.getCompanyId());
			query.put("tableId", table.getTableId());
			query.put("rowId", classPK);
			query.put("classNameId", table.getClassNameId());
			query.put("classPK", classPK);

			collection.remove(query);
		}
	}

	public List<ExpandoValue> getColumnValues(long columnId, int start, int end)
		throws SystemException {

		ExpandoColumn column = null;
		ExpandoTable table = null;

		try {
			column = ExpandoColumnLocalServiceUtil.getColumn(columnId);
			table = ExpandoTableLocalServiceUtil.getTable(column.getTableId());
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		return getColumnValues(
			table.getCompanyId(), table.getClassName(), table.getName(),
			column.getName(), null, start, end);
	}

	public List<ExpandoValue> getColumnValues(
			long companyId, long classNameId, String name, String columnName,
			int start, int end)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return getColumnValues(
			companyId, className, name, columnName, null, start, end);
	}

	public List<ExpandoValue> getColumnValues(
			long companyId, long classNameId, String name, String columnName,
			String data, int start, int end)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return getColumnValues(
			companyId, className, name, columnName, data, start, end);
	}

	public List<ExpandoValue> getColumnValues(
			long companyId, String className, String name, String columnName,
			int start, int end)
		throws SystemException {

		return getColumnValues(
			companyId, className, name, columnName, null, start, end);
	}

	public List<ExpandoValue> getColumnValues(
			long companyId, String className, String name, String columnName,
			String data, int start, int end)
		throws SystemException {

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			companyId, className, name, columnName);

		DBCollection collection = MongoDBUtil.getCollection(
			companyId, className, name);

		List<ExpandoValue> values = new ArrayList<ExpandoValue>();

		DBCursor cursor = null;

		if (Validator.isNotNull(data)) {
			BasicDBObject query = new BasicDBObject();

			ExpandoValue value = new MongoExpandoValue();

			value.setColumnId(column.getColumnId());
			value.setData(data);

			try {
				query.put(columnName, getData(column, value));
			}
			catch (PortalException e) {
				throw new SystemException(e);
			}

			cursor = collection.find(query);
		}
		else {
			cursor = collection.find();
		}

		if ((start != QueryUtil.ALL_POS) && end != QueryUtil.ALL_POS) {
			cursor = cursor.skip(start).limit(end - start);
		}

		for (DBObject dbObject : cursor.toArray()) {
			BasicDBObject basicDBObject = (BasicDBObject)dbObject;

			ExpandoValue value = new MongoExpandoValue();

			value.setCompanyId(basicDBObject.getLong("companyId"));
			value.setTableId(basicDBObject.getLong("tableId"));
			value.setColumnId(column.getColumnId());
			value.setRowId(basicDBObject.getLong("rowId"));
			value.setClassNameId(basicDBObject.getLong("classNameId"));
			value.setClassPK(basicDBObject.getLong("classPK"));

			try {
				putData(column, value, dbObject);
			}
			catch (PortalException e) {
				throw new SystemException(e);
			}

			values.add(value);
		}

		return values;
	}

	public int getColumnValuesCount(
			long companyId, long classNameId, String name, String columnName,
			String data)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return getColumnValuesCount(
			companyId, className, name, columnName, data);
	}

	public int getColumnValuesCount(
			long companyId, long classNameId, String name, String columnName)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return getColumnValuesCount(
			companyId, className, name, columnName, null);
	}

	public int getColumnValuesCount(
			long companyId, String className, String name, String columnName,
			String data)
		throws SystemException {

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			companyId, className, name, columnName);

		DBCollection collection = MongoDBUtil.getCollection(
			companyId, className, name);

		DBCursor cursor = null;

		if (Validator.isNotNull(data)) {
			BasicDBObject query = new BasicDBObject();

			ExpandoValue value = new MongoExpandoValue();

			value.setColumnId(column.getColumnId());
			value.setData(data);

			try {
				query.put(columnName, getData(column, value));
			}
			catch (PortalException e) {
				throw new SystemException(e);
			}

			cursor = collection.find(query);
		}
		else {
			cursor = collection.find();
		}

		return cursor.count();
	}

	public int getColumnValuesCount(
			long companyId, String className, String name, String columnName)
		throws SystemException {

		return getColumnValuesCount(
			companyId, className, name, columnName, null);
	}

	public int getColumnValuesCount(long columnId)
		throws SystemException {

		ExpandoColumn column = null;
		ExpandoTable table = null;

		try {
			column = ExpandoColumnLocalServiceUtil.getColumn(columnId);
			table = ExpandoTableLocalServiceUtil.getTable(column.getTableId());
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		return getColumnValuesCount(
			table.getCompanyId(), table.getClassName(), table.getName(),
			column.getName(), null);
	}

	public boolean getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, boolean defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getBoolean();
		}
	}

	public boolean[] getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, boolean[] defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getBooleanArray();
		}
	}

	public Date getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, Date defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getDate();
		}
	}

	public Date[] getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, Date[] defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getDateArray();
		}
	}

	public double getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, double defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getDouble();
		}
	}

	public double[] getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, double[] defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getDoubleArray();
		}
	}

	public float getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, float defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getFloat();
		}
	}

	public float[] getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, float[] defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getFloatArray();
		}
	}

	public int getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, int defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getInteger();
		}
	}

	public int[] getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, int[] defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getIntegerArray();
		}
	}

	public long getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, long defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getLong();
		}
	}

	public long[] getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, long[] defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getLongArray();
		}
	}

	public short getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, short defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getShort();
		}
	}

	public short[] getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, short[] defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getShortArray();
		}
	}

	public String getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, String defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getString();
		}
	}

	public String[] getData(
			long companyId, String className, String tableName,
			String columnName, long classPK, String[] defaultData)
		throws PortalException, SystemException {

		ExpandoValue value = getValue(
			companyId, className, tableName, columnName, classPK);

		if (value == null) {
			return defaultData;
		}
		else {
			return value.getStringArray();
		}
	}

	public Serializable getData(
			long companyId, String className, String tableName,
			String columnName, long classPK)
		throws PortalException, SystemException {

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			companyId, className, tableName, columnName);

		ExpandoValue value = new MongoExpandoValue();

		value.setColumnId(column.getColumnId());
		value.setData(column.getDefaultData());

		int type = column.getType();

		if (type == ExpandoColumnConstants.BOOLEAN) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getBoolean());
		}
		else if (type == ExpandoColumnConstants.BOOLEAN_ARRAY) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getBooleanArray());
		}
		else if (type == ExpandoColumnConstants.DATE) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getDate());
		}
		else if (type == ExpandoColumnConstants.DATE_ARRAY) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getDateArray());
		}
		else if (type == ExpandoColumnConstants.DOUBLE) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getDouble());
		}
		else if (type == ExpandoColumnConstants.DOUBLE_ARRAY) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getDoubleArray());
		}
		else if (type == ExpandoColumnConstants.FLOAT) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getFloat());
		}
		else if (type == ExpandoColumnConstants.FLOAT_ARRAY) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getFloatArray());
		}
		else if (type == ExpandoColumnConstants.INTEGER) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getInteger());
		}
		else if (type == ExpandoColumnConstants.INTEGER_ARRAY) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getIntegerArray());
		}
		else if (type == ExpandoColumnConstants.LONG) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getLong());
		}
		else if (type == ExpandoColumnConstants.LONG_ARRAY) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getLongArray());
		}
		else if (type == ExpandoColumnConstants.SHORT) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getShort());
		}
		else if (type == ExpandoColumnConstants.SHORT_ARRAY) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getShortArray());
		}
		else if (type == ExpandoColumnConstants.STRING_ARRAY) {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getStringArray());
		}
		else {
			return getData(
				companyId, className, tableName, columnName, classPK,
				value.getString());
		}
	}

	public List<ExpandoValue> getDefaultTableColumnValues(
			long companyId, long classNameId, String columnName, int start,
			int end)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return getColumnValues(
			companyId, className, ExpandoTableConstants.DEFAULT_TABLE_NAME,
			columnName, start, end);
	}

	public List<ExpandoValue> getDefaultTableColumnValues(
			long companyId, String className, String columnName, int start,
			int end)
		throws SystemException {

		return getColumnValues(
			companyId, className, ExpandoTableConstants.DEFAULT_TABLE_NAME,
			columnName, start, end);
	}

	public int getDefaultTableColumnValuesCount(
			long companyId, long classNameId, String columnName)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return getColumnValuesCount(
			companyId, className, ExpandoTableConstants.DEFAULT_TABLE_NAME,
			columnName);
	}

	public int getDefaultTableColumnValuesCount(
			long companyId, String className, String columnName)
		throws SystemException {

		return getColumnValuesCount(
			companyId, className, ExpandoTableConstants.DEFAULT_TABLE_NAME,
			columnName);
	}

	public List<ExpandoValue> getRowValues(long rowId, int start, int end)
		throws SystemException {

		throw new UnsupportedOperationException();
	}

	public List<ExpandoValue> getRowValues(
			long companyId, long classNameId, String tableName, long classPK,
			int start, int end)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return getRowValues(
			companyId, className, tableName, classPK, start, end);
	}

	public List<ExpandoValue> getRowValues(
			long companyId, String className, String name, long classPK,
			int start, int end)
		throws SystemException {

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(
				companyId, className, name);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		DBCollection collection = MongoDBUtil.getCollection(table);

		BasicDBObject query = new BasicDBObject();

		query.put("companyId", table.getCompanyId());
		query.put("tableId", table.getTableId());
		query.put("rowId", classPK);
		query.put("classNameId", table.getClassNameId());
		query.put("classPK", classPK);

		BasicDBObject dbObject = (BasicDBObject)collection.findOne(query);

		if (dbObject == null) {
			dbObject = query;
		}

		List<ExpandoValue> values = new ArrayList<ExpandoValue>();

		List<ExpandoColumn> columns = ExpandoColumnLocalServiceUtil.getColumns(
			table.getTableId());

		if ((start != QueryUtil.ALL_POS) && (end != QueryUtil.ALL_POS)) {
			columns = columns.subList(start, end);
		}

		for (ExpandoColumn column : columns) {
			ExpandoValue value = new MongoExpandoValue();

			value.setCompanyId(dbObject.getLong("companyId"));
			value.setTableId(dbObject.getLong("tableId"));
			value.setRowId(dbObject.getLong("rowId"));
			value.setClassNameId(dbObject.getLong("classNameId"));
			value.setClassPK(dbObject.getLong("classPK"));

			value.setColumnId(column.getColumnId());

			try {
				putData(column, value, dbObject);
			}
			catch (PortalException e) {
				throw new SystemException(e);
			}

			values.add(value);
		}

		return values;
	}

	public List<ExpandoValue> getRowValues(long rowId)
		throws SystemException {

		throw new UnsupportedOperationException();
	}

	public int getRowValuesCount(
			long companyId, long classNameId, String tableName, long classPK)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return getRowValuesCount(
			companyId, className, tableName, classPK);
	}

	public int getRowValuesCount(
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

		return ExpandoColumnLocalServiceUtil.getColumnsCount(
			table.getTableId());
	}

	public int getRowValuesCount(long rowId)
		throws SystemException {

		throw new UnsupportedOperationException();
	}

	public ExpandoValue getValue(long tableId, long columnId, long classPK)
		throws SystemException {

		ExpandoColumn column = null;
		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(tableId);
			column = ExpandoColumnLocalServiceUtil.getColumn(columnId);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		return getValue(
			table.getCompanyId(), table.getClassName(), table.getName(),
			column.getName(), classPK);
	}

	public ExpandoValue getValue(
			long companyId, long classNameId, String tableName,
			String columnName, long classPK)
		throws SystemException {

		String className = PortalUtil.getClassName(classNameId);

		return getValue(
			companyId, className, tableName, columnName, classPK);
	}

	public ExpandoValue getValue(long columnId, long rowId)
		throws PortalException, SystemException {

		ExpandoColumn column = null;
		ExpandoTable table = null;

		try {
			column = ExpandoColumnLocalServiceUtil.getColumn(columnId);
			table = ExpandoTableLocalServiceUtil.getTable(column.getTableId());
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		return getValue(
			table.getCompanyId(), table.getClassName(), table.getName(),
			column.getName(), rowId);
	}

	public ExpandoValue getValue(
			long companyId, String className, String name, String columnName,
			long classPK)
		throws SystemException {

		ExpandoColumn column = null;
		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(
				companyId, className, name);
			column = ExpandoColumnLocalServiceUtil.getColumn(
				table.getTableId(), columnName);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		DBCollection collection = MongoDBUtil.getCollection(table);

		BasicDBObject query = new BasicDBObject();

		query.put("companyId", table.getCompanyId());
		query.put("tableId", table.getTableId());
		query.put("rowId", classPK);
		query.put("classNameId", table.getClassNameId());
		query.put("classPK", classPK);

		BasicDBObject dbObject = (BasicDBObject)collection.findOne(query);

		ExpandoValue value = new MongoExpandoValue();

		value.setCompanyId(dbObject.getLong("companyId"));
		value.setTableId(dbObject.getLong("tableId"));
		value.setColumnId(column.getColumnId());
		value.setRowId(dbObject.getLong("rowId"));
		value.setClassNameId(dbObject.getLong("classNameId"));
		value.setClassPK(dbObject.getLong("classPK"));

		try {
			putData(column, value, dbObject);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}

		return value;
	}

	public ExpandoValue getValue(long valueId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	protected void putData(
			ExpandoColumn column, ExpandoValue value, DBObject dbObject)
		throws PortalException, SystemException {

		int type = column.getType();

		if (type == ExpandoColumnConstants.BOOLEAN) {
			value.setBoolean((Boolean)dbObject.get(column.getName()));
		}
		else if (type == ExpandoColumnConstants.BOOLEAN_ARRAY) {
			BasicDBList dbList = (BasicDBList)dbObject.get(column.getName());

			value.setBooleanArray(
				ArrayUtil.toArray(dbList.toArray(new Boolean[dbList.size()])));
		}
		else if (type == ExpandoColumnConstants.DATE) {
			value.setDate((Date)dbObject.get(column.getName()));
		}
		else if (type == ExpandoColumnConstants.DATE_ARRAY) {
			BasicDBList dbList = (BasicDBList)dbObject.get(column.getName());

			value.setDateArray(dbList.toArray(new Date[dbList.size()]));
		}
		else if (type == ExpandoColumnConstants.DOUBLE) {
			value.setDouble((Double)dbObject.get(column.getName()));
		}
		else if (type == ExpandoColumnConstants.DOUBLE_ARRAY) {
			BasicDBList dbList = (BasicDBList)dbObject.get(column.getName());

			value.setDoubleArray(
				ArrayUtil.toArray(dbList.toArray(new Double[dbList.size()])));
		}
		else if (type == ExpandoColumnConstants.FLOAT) {
			value.setFloat((Float)dbObject.get(column.getName()));
		}
		else if (type == ExpandoColumnConstants.FLOAT_ARRAY) {
			BasicDBList dbList = (BasicDBList)dbObject.get(column.getName());

			value.setFloatArray(
				ArrayUtil.toArray(dbList.toArray(new Float[dbList.size()])));
		}
		else if (type == ExpandoColumnConstants.INTEGER) {
			value.setInteger((Integer)dbObject.get(column.getName()));
		}
		else if (type == ExpandoColumnConstants.INTEGER_ARRAY) {
			BasicDBList dbList = (BasicDBList)dbObject.get(column.getName());

			value.setIntegerArray(
				ArrayUtil.toArray(dbList.toArray(new Integer[dbList.size()])));
		}
		else if (type == ExpandoColumnConstants.LONG) {
			value.setLong((Long)dbObject.get(column.getName()));
		}
		else if (type == ExpandoColumnConstants.LONG_ARRAY) {
			BasicDBList dbList = (BasicDBList)dbObject.get(column.getName());

			value.setLongArray(
				ArrayUtil.toArray(dbList.toArray(new Long[dbList.size()])));
		}
		else if (type == ExpandoColumnConstants.SHORT) {
			value.setShort((Short)dbObject.get(column.getName()));
		}
		else if (type == ExpandoColumnConstants.SHORT_ARRAY) {
			BasicDBList dbList = (BasicDBList)dbObject.get(column.getName());

			value.setShortArray(
				ArrayUtil.toArray(dbList.toArray(new Short[dbList.size()])));
		}
		else if (type == ExpandoColumnConstants.STRING_ARRAY) {
			BasicDBList dbList = (BasicDBList)dbObject.get(column.getName());

			value.setStringArray(dbList.toArray(new String[dbList.size()]));
		}
		else {
			value.setString((String)dbObject.get(column.getName()));
		}
	}

	protected Object getData(ExpandoColumn column, ExpandoValue value)
		throws PortalException, SystemException {

		int type = column.getType();

		if (type == ExpandoColumnConstants.BOOLEAN) {
			return value.getBoolean();
		}
		else if (type == ExpandoColumnConstants.BOOLEAN_ARRAY) {
			return value.getBooleanArray();
		}
		else if (type == ExpandoColumnConstants.DATE) {
			return value.getDate();
		}
		else if (type == ExpandoColumnConstants.DATE_ARRAY) {
			return value.getDateArray();
		}
		else if (type == ExpandoColumnConstants.DOUBLE) {
			return value.getDouble();
		}
		else if (type == ExpandoColumnConstants.DOUBLE_ARRAY) {
			return value.getDoubleArray();
		}
		else if (type == ExpandoColumnConstants.FLOAT) {
			return value.getFloat();
		}
		else if (type == ExpandoColumnConstants.FLOAT_ARRAY) {
			return value.getFloatArray();
		}
		else if (type == ExpandoColumnConstants.INTEGER) {
			return value.getInteger();
		}
		else if (type == ExpandoColumnConstants.INTEGER_ARRAY) {
			return value.getIntegerArray();
		}
		else if (type == ExpandoColumnConstants.LONG) {
			return value.getLong();
		}
		else if (type == ExpandoColumnConstants.LONG_ARRAY) {
			return value.getLongArray();
		}
		else if (type == ExpandoColumnConstants.SHORT) {
			return value.getShort();
		}
		else if (type == ExpandoColumnConstants.SHORT_ARRAY) {
			return value.getShortArray();
		}
		else if (type == ExpandoColumnConstants.STRING_ARRAY) {
			return value.getStringArray();
		}
		else {
			return value.getString();
		}
	}

	protected void putColumnData(
			DBObject dbObject, List<ExpandoColumn> columns,
			Map<String, String> data, ExpandoValue value)
		throws PortalException, SystemException {

		for (ExpandoColumn column : columns) {
			String dataString = data.get(column.getName());

			if (dataString != null) {
				value.setColumnId(column.getColumnId());

				value.setData(dataString);

				dbObject.put(column.getName(), getData(column, value));
			}
		}
	}

}