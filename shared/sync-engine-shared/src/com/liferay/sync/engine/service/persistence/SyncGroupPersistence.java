/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.sync.engine.service.persistence;

import com.liferay.sync.engine.model.SyncGroup;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class SyncGroupPersistence extends BasePersistenceImpl<SyncGroup, Long> {

	public SyncGroupPersistence() throws SQLException {
		super(SyncGroup.class);
	}

	public SyncGroup getSyncGroup(long syncAccountId, long groupId)
		throws SQLException {

		Map<String, Object> fieldValues = new HashMap<String, Object>();

		fieldValues.put("groupId", groupId);
		fieldValues.put("syncAccountId", syncAccountId);

		List<SyncGroup> groups = queryForFieldValues(fieldValues);

		if ((groups == null) || (groups.size() == 0)) {
			return null;
		}

		return groups.get(0);
	}

}