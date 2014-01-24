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

import com.liferay.sync.engine.model.SyncSite;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class SyncSitePersistence extends BasePersistenceImpl<SyncSite, Long> {

	public SyncSitePersistence() throws SQLException {
		super(SyncSite.class);
	}

	public SyncSite fetchSyncSite(long groupId, long syncAccountId)
		throws SQLException {

		Map<String, Object> fieldValues = new HashMap<String, Object>();

		fieldValues.put("groupId", groupId);
		fieldValues.put("syncAccountId", syncAccountId);

		List<SyncSite> syncSites = queryForFieldValues(fieldValues);

		if ((syncSites == null) || syncSites.isEmpty()) {
			return null;
		}

		return syncSites.get(0);
	}

	public List<SyncSite> findSyncSites(long syncAccountId)
		throws SQLException {

		return queryForEq("syncAccountId", syncAccountId);
	}

}