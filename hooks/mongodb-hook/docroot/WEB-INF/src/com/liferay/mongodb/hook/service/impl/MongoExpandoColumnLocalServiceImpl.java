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

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceWrapper;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.mongodb.lang.MongoOperator;
import com.liferay.mongodb.util.MongoDBUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class MongoExpandoColumnLocalServiceImpl
	extends ExpandoColumnLocalServiceWrapper {

	public MongoExpandoColumnLocalServiceImpl(
		ExpandoColumnLocalService expandoColumnLocalService) {

		super(expandoColumnLocalService);
	}

	@Override
	public ExpandoColumn updateColumn(
			long columnId, String name, int type, Object defaultData)
		throws PortalException {

		ExpandoColumn expandoColumn = super.updateColumn(
			columnId, name, type, defaultData);

		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
			expandoColumn.getTableId());

		DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

		DBObject operatorDBObject = new BasicDBObject(
			MongoOperator.RENAME,
			new BasicDBObject(expandoColumn.getName(), name));

		dbCollection.update(new BasicDBObject(), operatorDBObject);

		return expandoColumn;
	}

	@Override
	public ExpandoColumn updateTypeSettings(long columnId, String typeSettings)
		throws PortalException {

		ExpandoColumn expandoColumn = super.updateTypeSettings(
			columnId, typeSettings);

		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
			expandoColumn.getTableId());

		UnicodeProperties typeSettingsProperties =
			expandoColumn.getTypeSettingsProperties();

		int indexType = GetterUtil.getInteger(
			typeSettingsProperties.getProperty(
				ExpandoColumnConstants.INDEX_TYPE));

		if (indexType != ExpandoColumnConstants.INDEX_TYPE_NONE) {
			DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

			dbCollection.createIndex(
				new BasicDBObject(expandoColumn.getName(), 1));
		}

		return expandoColumn;
	}

}