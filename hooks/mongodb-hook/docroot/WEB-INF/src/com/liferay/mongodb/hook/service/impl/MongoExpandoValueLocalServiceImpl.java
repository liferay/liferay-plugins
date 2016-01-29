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

package com.liferay.mongodb.hook.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.expando.kernel.exception.NoSuchColumnException;
import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceWrapper;
import com.liferay.expando.kernel.service.persistence.ExpandoColumnUtil;
import com.liferay.expando.kernel.service.persistence.ExpandoValueUtil;
import com.liferay.mongodb.lang.MongoOperator;
import com.liferay.mongodb.util.MongoDBUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class MongoExpandoValueLocalServiceImpl
	extends ExpandoValueLocalServiceWrapper {

	public MongoExpandoValueLocalServiceImpl(
		ExpandoValueLocalService expandoValueLocalService) {

		super(expandoValueLocalService);
	}

	@Override
	public ExpandoValue addValue(
			long classNameId, long tableId, long columnId, long classPK,
			String data)
		throws PortalException {

		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
			tableId);

		ExpandoColumn expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(
			columnId);

		ExpandoValue expandoValue = ExpandoValueUtil.create(0);

		expandoValue.setCompanyId(expandoTable.getCompanyId());
		expandoValue.setTableId(tableId);
		expandoValue.setColumnId(columnId);
		expandoValue.setRowId(classPK);
		expandoValue.setClassNameId(classNameId);
		expandoValue.setClassPK(classPK);
		expandoValue.setData(data);

		DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

		DBObject queryDBObject = new BasicDBObject();

		queryDBObject.put("companyId", expandoTable.getCompanyId());
		queryDBObject.put("tableId", tableId);
		queryDBObject.put("rowId", classPK);
		queryDBObject.put("classNameId", classNameId);
		queryDBObject.put("classPK", classPK);

		BasicDBObject expandoValueDBObject =
			(BasicDBObject)dbCollection.findOne(queryDBObject);

		if (expandoValueDBObject != null) {
			expandoValue.setValueId(expandoValueDBObject.getLong("valueId"));

			DBObject operatorDBObject = new BasicDBObject();

			DBObject updateExpandoValueDBObject = new BasicDBObject(
				expandoColumn.getName(),
				getData(expandoColumn, expandoValue));

			operatorDBObject.put(MongoOperator.SET, updateExpandoValueDBObject);

			dbCollection.update(expandoValueDBObject, operatorDBObject);
		}
		else {
			long valueId = CounterLocalServiceUtil.increment();

			expandoValue.setValueId(valueId);

			queryDBObject.put("valueId", valueId);
			queryDBObject.put(
				expandoColumn.getName(), getData(expandoColumn, expandoValue));

			dbCollection.insert(queryDBObject);
		}

		return expandoValue;
	}

	@Override
	public void addValues(
			long classNameId, long tableId, List<ExpandoColumn> expandoColumns,
			long classPK, Map<String, String> data)
		throws PortalException {

		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
			tableId);

		ExpandoValue expandoValue = ExpandoValueUtil.create(0);

		expandoValue.setCompanyId(expandoTable.getCompanyId());
		expandoValue.setTableId(tableId);
		expandoValue.setRowId(classPK);
		expandoValue.setClassNameId(classNameId);
		expandoValue.setClassPK(classPK);

		DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

		DBObject queryDBObject = new BasicDBObject();

		queryDBObject.put("companyId", expandoTable.getCompanyId());
		queryDBObject.put("tableId", tableId);
		queryDBObject.put("rowId", classPK);
		queryDBObject.put("classNameId", classNameId);
		queryDBObject.put("classPK", classPK);

		BasicDBObject expandoValueDBObject =
			(BasicDBObject)dbCollection.findOne(queryDBObject);

		if (expandoValueDBObject != null) {
			expandoValue.setValueId(expandoValueDBObject.getLong("valueId"));

			DBObject operatorDBObject = new BasicDBObject();

			DBObject updateExpandoValueDBObject = new BasicDBObject();

			updateExpandoValueDBObject(
				updateExpandoValueDBObject, expandoColumns, data, expandoValue);

			operatorDBObject.put(MongoOperator.SET, updateExpandoValueDBObject);

			dbCollection.update(expandoValueDBObject, operatorDBObject);
		}
		else {
			long valueId = CounterLocalServiceUtil.increment();

			expandoValue.setValueId(valueId);

			queryDBObject.put("valueId", valueId);

			updateExpandoValueDBObject(
				queryDBObject, expandoColumns, data, expandoValue);

			dbCollection.insert(queryDBObject);
		}
	}

	@Override
	public void deleteColumnValues(long columnId) {
		try {
			ExpandoColumn expandoColumn = ExpandoColumnUtil.fetchByPrimaryKey(
				columnId);

			if (expandoColumn == null) {
				return;
			}

			ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
				expandoColumn.getTableId());

			DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

			DBObject operatorDBObject = new BasicDBObject(
				MongoOperator.SET,
				new BasicDBObject(expandoColumn.getName(), null));

			dbCollection.update(
				new BasicDBObject(), operatorDBObject, false, true);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public void deleteRowValues(long rowId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteTableValues(long tableId) {
		try {
			ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
				tableId);

			DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

			DBObject valueDBObject = new BasicDBObject();

			List<ExpandoColumn> expandoColumns =
				ExpandoColumnLocalServiceUtil.getColumns(tableId);

			for (ExpandoColumn expandoColumn : expandoColumns) {
				valueDBObject.put(expandoColumn.getName(), null);
			}

			DBObject operatorDBObject = new BasicDBObject(
				MongoOperator.SET, valueDBObject);

			dbCollection.update(
				new BasicDBObject(), operatorDBObject, false, true);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public void deleteValue(long valueId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteValue(long columnId, long rowId) throws PortalException {
		ExpandoColumn expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(
			columnId);

		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
			expandoColumn.getTableId());

		deleteValue(
			expandoTable.getCompanyId(), expandoTable.getClassNameId(),
			expandoTable.getName(), expandoColumn.getName(), rowId);
	}

	@Override
	public void deleteValue(
			long companyId, long classNameId, String tableName,
			String columnName, long classPK)
		throws PortalException {

		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
			companyId, classNameId, tableName);

		DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

		DBObject queryDBObject = new BasicDBObject();

		queryDBObject.put("companyId", expandoTable.getCompanyId());
		queryDBObject.put("tableId", expandoTable.getTableId());
		queryDBObject.put("rowId", classPK);
		queryDBObject.put("classNameId", expandoTable.getClassNameId());
		queryDBObject.put("classPK", classPK);

		DBObject valueDBObject = new BasicDBObject();

		valueDBObject.put(columnName, null);

		DBObject operatorDBObject = new BasicDBObject(
			MongoOperator.SET, valueDBObject);

		dbCollection.update(queryDBObject, operatorDBObject);
	}

	@Override
	public void deleteValues(long classNameId, long classPK) {
		long companyId = CompanyThreadLocal.getCompanyId();

		List<ExpandoTable> expandoTables =
			ExpandoTableLocalServiceUtil.getTables(companyId, classNameId);

		for (ExpandoTable expandoTable : expandoTables) {
			DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

			DBObject queryDBObject = new BasicDBObject();

			queryDBObject.put("companyId", expandoTable.getCompanyId());
			queryDBObject.put("tableId", expandoTable.getTableId());
			queryDBObject.put("rowId", classPK);
			queryDBObject.put("classNameId", expandoTable.getClassNameId());
			queryDBObject.put("classPK", classPK);

			dbCollection.remove(queryDBObject);
		}
	}

	@Override
	public List<ExpandoValue> getColumnValues(
		long columnId, int start, int end) {

		try {
			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.getColumn(columnId);

			ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
				expandoColumn.getTableId());

			return getColumnValues(
				expandoTable.getCompanyId(), expandoTable.getClassNameId(),
				expandoTable.getName(), expandoColumn.getName(), null, start,
				end);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public List<ExpandoValue> getColumnValues(
		long companyId, long classNameId, String tableName, String columnName,
		String data, int start, int end) {

		try {
			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.getColumn(
					companyId, classNameId, tableName, columnName);

			DBCollection dbCollection = MongoDBUtil.getCollection(
				companyId, classNameId, tableName);

			DBCursor dbCursor = null;

			if (Validator.isNotNull(data)) {
				DBObject queryDBObject = new BasicDBObject();

				ExpandoValue expandoValue = ExpandoValueUtil.create(0);

				expandoValue.setColumnId(expandoColumn.getColumnId());
				expandoValue.setData(data);

				queryDBObject.put(
					columnName, getData(expandoColumn, expandoValue));

				dbCursor = dbCollection.find(queryDBObject);
			}
			else {
				dbCursor = dbCollection.find();
			}

			if ((start != QueryUtil.ALL_POS) && (end != QueryUtil.ALL_POS)) {
				dbCursor = dbCursor.skip(start).limit(end - start);
			}

			List<ExpandoValue> expandoValues = new ArrayList<>();

			for (DBObject dbObject : dbCursor.toArray()) {
				BasicDBObject expandoValueDBObject = (BasicDBObject)dbObject;

				ExpandoValue expandoValue = toExpandoValue(
					expandoValueDBObject, expandoColumn);

				expandoValues.add(expandoValue);
			}

			return expandoValues;
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public int getColumnValuesCount(long columnId) {
		try {
			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.getColumn(columnId);

			ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
				expandoColumn.getTableId());

			return getColumnValuesCount(
				expandoTable.getCompanyId(), expandoTable.getClassNameId(),
				expandoTable.getName(), expandoColumn.getName(), null);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public int getColumnValuesCount(
		long companyId, long classNameId, String tableName, String columnName,
		String data) {

		try {
			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.getColumn(
					companyId, classNameId, tableName, columnName);

			DBCollection dbCollection = MongoDBUtil.getCollection(
				companyId, classNameId, tableName);

			DBCursor dbCursor = null;

			if (Validator.isNotNull(data)) {
				DBObject queryDBObject = new BasicDBObject();

				ExpandoValue expandoValue = ExpandoValueUtil.create(0);

				expandoValue.setColumnId(expandoColumn.getColumnId());
				expandoValue.setData(data);

				queryDBObject.put(
					columnName, getData(expandoColumn, expandoValue));

				dbCursor = dbCollection.find(queryDBObject);
			}
			else {
				dbCursor = dbCollection.find();
			}

			return dbCursor.count();
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public List<ExpandoValue> getRowValues(long rowId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<ExpandoValue> getRowValues(long rowId, int start, int end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<ExpandoValue> getRowValues(
		long companyId, long classNameId, String tableName, long classPK,
		int start, int end) {

		try {
			ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, classNameId, tableName);

			DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

			BasicDBObject queryDBObject = new BasicDBObject();

			queryDBObject.put("companyId", expandoTable.getCompanyId());
			queryDBObject.put("tableId", expandoTable.getTableId());
			queryDBObject.put("rowId", classPK);
			queryDBObject.put("classNameId", expandoTable.getClassNameId());
			queryDBObject.put("classPK", classPK);

			BasicDBObject expandoValueDBObject =
				(BasicDBObject)dbCollection.findOne(queryDBObject);

			if (expandoValueDBObject == null) {
				expandoValueDBObject = queryDBObject;
			}

			List<ExpandoColumn> expandoColumns =
				ExpandoColumnLocalServiceUtil.getColumns(
					expandoTable.getTableId());

			if ((start != QueryUtil.ALL_POS) && (end != QueryUtil.ALL_POS)) {
				expandoColumns = expandoColumns.subList(start, end);
			}

			List<ExpandoValue> expandoValues = new ArrayList<>();

			for (ExpandoColumn expandoColumn : expandoColumns) {
				ExpandoValue expandoValue = toExpandoValue(
					expandoValueDBObject, expandoColumn);

				expandoValues.add(expandoValue);
			}

			return expandoValues;
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public int getRowValuesCount(long rowId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getRowValuesCount(
		long companyId, long classNameId, String tableName, long classPK) {

		try {
			ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, classNameId, tableName);

			return ExpandoColumnLocalServiceUtil.getColumnsCount(
				expandoTable.getTableId());
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public ExpandoValue getValue(long valueId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ExpandoValue getValue(long columnId, long rowId)
		throws PortalException {

		ExpandoColumn expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(
			columnId);

		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
			expandoColumn.getTableId());

		return getValue(
			expandoTable.getCompanyId(), expandoTable.getClassNameId(),
			expandoTable.getName(), expandoColumn.getName(), rowId);
	}

	@Override
	public ExpandoValue getValue(long tableId, long columnId, long classPK) {
		try {
			ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
				tableId);

			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.getColumn(columnId);

			return getValue(
				expandoTable.getCompanyId(), expandoTable.getClassName(),
				expandoTable.getName(), expandoColumn.getName(), classPK);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public ExpandoValue getValue(
		long companyId, long classNameId, String tableName, String columnName,
		long classPK) {

		try {
			ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, classNameId, tableName);

			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.getColumn(
					expandoTable.getTableId(), columnName);

			DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

			DBObject queryDBObject = new BasicDBObject();

			queryDBObject.put("companyId", expandoTable.getCompanyId());
			queryDBObject.put("tableId", expandoTable.getTableId());
			queryDBObject.put("rowId", classPK);
			queryDBObject.put("classNameId", expandoTable.getClassNameId());
			queryDBObject.put("classPK", classPK);

			BasicDBObject expandoValueDBObject =
				(BasicDBObject)dbCollection.findOne(queryDBObject);

			if (expandoValueDBObject == null) {
				return null;
			}

			return toExpandoValue(expandoValueDBObject, expandoColumn);
		}
		catch (NoSuchColumnException nsce) {
			return null;
		}
		catch (NoSuchTableException nste) {
			return null;
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	protected Object getData(
			ExpandoColumn expandoColumn, ExpandoValue expandoValue)
		throws PortalException {

		int type = expandoColumn.getType();

		if (type == ExpandoColumnConstants.BOOLEAN) {
			return expandoValue.getBoolean();
		}
		else if (type == ExpandoColumnConstants.BOOLEAN_ARRAY) {
			return expandoValue.getBooleanArray();
		}
		else if (type == ExpandoColumnConstants.DATE) {
			return expandoValue.getDate();
		}
		else if (type == ExpandoColumnConstants.DATE_ARRAY) {
			return expandoValue.getDateArray();
		}
		else if (type == ExpandoColumnConstants.DOUBLE) {
			return expandoValue.getDouble();
		}
		else if (type == ExpandoColumnConstants.DOUBLE_ARRAY) {
			return expandoValue.getDoubleArray();
		}
		else if (type == ExpandoColumnConstants.FLOAT) {
			return expandoValue.getFloat();
		}
		else if (type == ExpandoColumnConstants.FLOAT_ARRAY) {
			return expandoValue.getFloatArray();
		}
		else if (type == ExpandoColumnConstants.INTEGER) {
			return expandoValue.getInteger();
		}
		else if (type == ExpandoColumnConstants.INTEGER_ARRAY) {
			return expandoValue.getIntegerArray();
		}
		else if (type == ExpandoColumnConstants.LONG) {
			return expandoValue.getLong();
		}
		else if (type == ExpandoColumnConstants.LONG_ARRAY) {
			return expandoValue.getLongArray();
		}
		else if (type == ExpandoColumnConstants.SHORT) {
			return expandoValue.getShort();
		}
		else if (type == ExpandoColumnConstants.SHORT_ARRAY) {
			return expandoValue.getShortArray();
		}
		else if (type == ExpandoColumnConstants.STRING_ARRAY) {
			return expandoValue.getStringArray();
		}
		else {
			return expandoValue.getString();
		}
	}

	protected ExpandoValue toExpandoValue(
			BasicDBObject expandoValueDBObject, ExpandoColumn expandoColumn)
		throws PortalException {

		ExpandoValue expandoValue = ExpandoValueUtil.create(0);

		expandoValue.setCompanyId(expandoValueDBObject.getLong("companyId"));
		expandoValue.setTableId(expandoValueDBObject.getLong("tableId"));
		expandoValue.setColumnId(expandoColumn.getColumnId());
		expandoValue.setRowId(expandoValueDBObject.getLong("rowId"));
		expandoValue.setClassNameId(
			expandoValueDBObject.getLong("classNameId"));
		expandoValue.setClassPK(expandoValueDBObject.getLong("classPK"));

		Object value = expandoValueDBObject.get(expandoColumn.getName());

		if (value == null) {
			return expandoValue;
		}

		int type = expandoColumn.getType();

		if (type == ExpandoColumnConstants.BOOLEAN) {
			expandoValue.setBoolean((Boolean)value);
		}
		else if (type == ExpandoColumnConstants.BOOLEAN_ARRAY) {
			List<Boolean> list = (List<Boolean>)value;

			expandoValue.setBooleanArray(
				ArrayUtil.toArray(list.toArray(new Boolean[list.size()])));
		}
		else if (type == ExpandoColumnConstants.DATE) {
			expandoValue.setDate(
				(Date)expandoValueDBObject.get(expandoColumn.getName()));
		}
		else if (type == ExpandoColumnConstants.DATE_ARRAY) {
			List<Date> list = (List<Date>)value;

			expandoValue.setDateArray(list.toArray(new Date[list.size()]));
		}
		else if (type == ExpandoColumnConstants.DOUBLE) {
			expandoValue.setDouble(
				(Double)expandoValueDBObject.get(expandoColumn.getName()));
		}
		else if (type == ExpandoColumnConstants.DOUBLE_ARRAY) {
			List<Double> list = (List<Double>)value;

			expandoValue.setDoubleArray(
				ArrayUtil.toArray(list.toArray(new Double[list.size()])));
		}
		else if (type == ExpandoColumnConstants.FLOAT) {
			expandoValue.setFloat(
				(Float)expandoValueDBObject.get(expandoColumn.getName()));
		}
		else if (type == ExpandoColumnConstants.FLOAT_ARRAY) {
			List<Float> list = (List<Float>)value;

			expandoValue.setFloatArray(
				ArrayUtil.toArray(list.toArray(new Float[list.size()])));
		}
		else if (type == ExpandoColumnConstants.INTEGER) {
			expandoValue.setInteger((Integer)value);
		}
		else if (type == ExpandoColumnConstants.INTEGER_ARRAY) {
			List<Integer> list = (List<Integer>)value;

			expandoValue.setIntegerArray(
				ArrayUtil.toArray(list.toArray(new Integer[list.size()])));
		}
		else if (type == ExpandoColumnConstants.LONG) {
			expandoValue.setLong(
				(Long)expandoValueDBObject.get(expandoColumn.getName()));
		}
		else if (type == ExpandoColumnConstants.LONG_ARRAY) {
			List<Long> list = (List<Long>)value;

			expandoValue.setLongArray(
				ArrayUtil.toArray(list.toArray(new Long[list.size()])));
		}
		else if (type == ExpandoColumnConstants.SHORT) {
			expandoValue.setShort((Short)expandoValueDBObject.get(
				expandoColumn.getName()));
		}
		else if (type == ExpandoColumnConstants.SHORT_ARRAY) {
			List<Short> list = (List<Short>)value;

			expandoValue.setShortArray(
				ArrayUtil.toArray(list.toArray(new Short[list.size()])));
		}
		else if (type == ExpandoColumnConstants.STRING_ARRAY) {
			List<String> list = (List<String>)value;

			expandoValue.setStringArray(list.toArray(new String[list.size()]));
		}
		else {
			expandoValue.setString((String)value);
		}

		return expandoValue;
	}

	protected void updateExpandoValueDBObject(
			DBObject expandoValueDBObject, List<ExpandoColumn> expandoColumns,
			Map<String, String> data, ExpandoValue expandoValue)
		throws PortalException {

		for (ExpandoColumn expandoColumn : expandoColumns) {
			String dataString = data.get(expandoColumn.getName());

			if (Validator.isNull(dataString)) {
				continue;
			}

			expandoValue.setColumnId(expandoColumn.getColumnId());

			expandoValue.setData(dataString);

			expandoValueDBObject.put(
				expandoColumn.getName(), getData(expandoColumn, expandoValue));
		}
	}

}