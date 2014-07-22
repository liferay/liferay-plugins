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

package com.liferay.sync.engine.service.persistence;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

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

	public SyncSite fetchByF_S(String filePathName, long syncAccountId)
		throws SQLException {

		Map<String, Object> fieldValues = new HashMap<String, Object>();

		fieldValues.put("filePathName", filePathName);
		fieldValues.put("syncAccountId", syncAccountId);

		List<SyncSite> syncSites = queryForFieldValuesArgs(fieldValues);

		if ((syncSites == null) || syncSites.isEmpty()) {
			return null;
		}

		return syncSites.get(0);
	}

	public SyncSite fetchByG_S(long groupId, long syncAccountId)
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

	public List<SyncSite> findBySyncAccountId(long syncAccountId)
		throws SQLException {

		return queryForEq("syncAccountId", syncAccountId);
	}

	public List<Long> findByA_S(boolean active, long syncAccountId)
		throws SQLException {

		QueryBuilder<SyncSite, Long> queryBuilder = queryBuilder();

		queryBuilder = queryBuilder.selectColumns("syncSiteId");

		Where<SyncSite, Long> where = queryBuilder.where();

		where.eq("active", active);

		where.and();

		where.eq("syncAccountId", syncAccountId);

		GenericRawResults<Long> genericRawResults = queryRaw(
			queryBuilder.prepareStatementString(),
			new RawRowMapper<Long>() {

				@Override
				public Long mapRow(
					String[] columnNames, String[] resultColumns) {

					return Long.valueOf(resultColumns[0]);
				}

			});

		return genericRawResults.getResults();
	}

}