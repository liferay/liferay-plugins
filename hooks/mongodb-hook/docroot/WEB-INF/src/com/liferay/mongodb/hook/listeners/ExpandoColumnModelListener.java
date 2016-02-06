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

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.mongodb.lang.MongoOperator;
import com.liferay.mongodb.util.MongoDBUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class ExpandoColumnModelListener
	extends BaseModelListener<ExpandoColumn> {

	@Override
	public void onAfterRemove(ExpandoColumn expandoColumn)
		throws ModelListenerException {

		try {
			doOnAfterRemove(expandoColumn);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void doOnAfterRemove(ExpandoColumn expandoColumn)
		throws Exception {

		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.getTable(
			expandoColumn.getTableId());

		DBCollection dbCollection = MongoDBUtil.getCollection(expandoTable);

		for (DBObject indexDBObject : dbCollection.getIndexInfo()) {
			DBObject keyDBObject = (DBObject)indexDBObject.get("key");

			if (keyDBObject.containsField(expandoColumn.getName())) {
				dbCollection.dropIndex(keyDBObject);
			}
		}

		DBObject operatorDBObject = new BasicDBObject(
			MongoOperator.UNSET, new BasicDBObject(expandoColumn.getName(), 1));

		dbCollection.update(new BasicDBObject(), operatorDBObject, false, true);
	}

}