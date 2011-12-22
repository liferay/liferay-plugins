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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoRowLocalService;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceWrapper;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.persistence.ExpandoRowUtil;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class MongoExpandoRowLocalServiceImpl
	extends ExpandoRowLocalServiceWrapper {

	public MongoExpandoRowLocalServiceImpl(
		ExpandoRowLocalService expandoRowLocalService) {

		super(expandoRowLocalService);
	}

	@Override
	public ExpandoRow addRow(long tableId, long classPK) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteRow(long rowId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteRow(long tableId, long classPK)
		throws PortalException, SystemException {

		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
			tableId);

		DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

		dbCollection.remove(new BasicDBObject("classPK", classPK));
	}

	@Override
	public ExpandoRow getRow(long rowId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ExpandoRow getRow(long tableId, long classPK)
		throws PortalException, SystemException {

		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
			tableId);

		return getRow(expandoTable, classPK);
	}

	@Override
	public ExpandoRow getRow(
			long companyId, long classNameId, String tableName, long classPK)
		throws SystemException {

		try {
			ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, classNameId, tableName);

			return getRow(expandoTable, classPK);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}
	}

	@Override
	public List<ExpandoRow> getRows(long tableId, int start, int end)
		throws SystemException {

		try {
			ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
				tableId);

			return getRows(expandoTable, start, end);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public List<ExpandoRow> getRows(
			long companyId, long classNameId, String tableName, int start,
			int end)
		throws SystemException {

		try {
			ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, classNameId, tableName);

			return getRows(expandoTable, start, end);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public int getRowsCount(long tableId)
		throws SystemException {

		try {
			ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
				tableId);

			return getRowsCount(
				expandoTable.getCompanyId(), expandoTable.getClassNameId(),
				expandoTable.getName());
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public int getRowsCount(
		long companyId, long classNameId, String tableName) {

		DBCollection dbCollection = MongoDBUtil.getCollection(
			companyId, classNameId, tableName);

		return (int)dbCollection.count();
	}

	protected ExpandoRow getRow(ExpandoTable expandoTable, long classPK) {
		DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

		DBObject expandoRowDBObject = dbCollection.findOne(
			new BasicDBObject("classPK", classPK));

		return toExpandoRow(expandoRowDBObject, expandoTable);
	}

	protected List<ExpandoRow> getRows(
		ExpandoTable expandoTable, int start, int end) {

		DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

		DBCursor dbCursor = dbCollection.find(new BasicDBObject());

		if ((start != QueryUtil.ALL_POS) && (end != QueryUtil.ALL_POS)) {
			dbCursor.limit(start);
			dbCursor.limit(end - start);
		}

		List<ExpandoRow> expandoRows = new ArrayList<ExpandoRow>();

		for (DBObject expandoRowDBObject : dbCursor.toArray()) {
			ExpandoRow expandoRow = toExpandoRow(
				expandoRowDBObject, expandoTable);

			expandoRows.add(expandoRow);
		}

		return expandoRows;
	}

	protected ExpandoRow toExpandoRow(
		DBObject expandoRowDBObject, ExpandoTable expandoTable) {

		ExpandoRow expandoRow = ExpandoRowUtil.create(
			(Long)expandoRowDBObject.get("rowId"));

		expandoRow.setCompanyId(expandoTable.getCompanyId());
		expandoRow.setTableId(expandoTable.getTableId());
		expandoRow.setClassPK((Long)expandoRowDBObject.get("classPK"));

		return expandoRow;
	}

}