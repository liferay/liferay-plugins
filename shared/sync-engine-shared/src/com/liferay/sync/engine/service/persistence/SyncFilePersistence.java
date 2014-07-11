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

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import com.liferay.sync.engine.model.SyncFile;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class SyncFilePersistence extends BasePersistenceImpl<SyncFile, Long> {

	public SyncFilePersistence() throws SQLException {
		super(SyncFile.class);
	}

	public SyncFile fetchByFK_S(String fileKey, long syncAccountId)
		throws SQLException {

		Map<String, Object> fieldValues = new HashMap<String, Object>();

		fieldValues.put("fileKey", fileKey);
		fieldValues.put("syncAccountId", syncAccountId);

		List<SyncFile> syncFiles = queryForFieldValues(fieldValues);

		if ((syncFiles == null) || syncFiles.isEmpty()) {
			return null;
		}

		return syncFiles.get(0);
	}

	public SyncFile fetchByFPN_S(String filePathName, long syncAccountId)
		throws SQLException {

		Map<String, Object> fieldValues = new HashMap<String, Object>();

		fieldValues.put("filePathName", filePathName);
		fieldValues.put("syncAccountId", syncAccountId);

		List<SyncFile> syncFiles = queryForFieldValues(fieldValues);

		if ((syncFiles == null) || syncFiles.isEmpty()) {
			return null;
		}

		return syncFiles.get(0);
	}

	public SyncFile fetchByR_S_T(
			long repositoryId, long syncAccountId, long typePK)
		throws SQLException {

		Map<String, Object> fieldValues = new HashMap<String, Object>();

		fieldValues.put("repositoryId", repositoryId);
		fieldValues.put("syncAccountId", syncAccountId);
		fieldValues.put("typePK", typePK);

		List<SyncFile> syncFiles = queryForFieldValues(fieldValues);

		if ((syncFiles == null) || syncFiles.isEmpty()) {
			return null;
		}

		return syncFiles.get(0);
	}

	public List<SyncFile> findByC_S(String checksum, long syncAccountId)
		throws SQLException {

		Map<String, Object> fieldValues = new HashMap<String, Object>();

		fieldValues.put("checksum", checksum);
		fieldValues.put("syncAccountId", syncAccountId);

		return queryForFieldValues(fieldValues);
	}

	public List<SyncFile> findByFilePathName(String filePathName)
		throws SQLException {

		Map<String, Object> fieldValues = new HashMap<String, Object>();

		fieldValues.put("filePathName", filePathName);

		return queryForFieldValues(fieldValues);
	}

	public List<SyncFile> findByF_L_S(
			String filePathName, long localSyncTime, long syncAccountId)
		throws SQLException {

		QueryBuilder<SyncFile, Long> queryBuilder = queryBuilder();

		Where<SyncFile, Long> where = queryBuilder.where();

		where.like("filePathName", filePathName + "%");

		where.and();

		where.lt("localSyncTime", localSyncTime);

		where.and();

		where.eq("syncAccountId", syncAccountId);

		where.and();

		where.ne("type", SyncFile.TYPE_SYSTEM);

		return query(queryBuilder.prepare());
	}

	public List<SyncFile> findByState(int state) throws SQLException {
		return queryForEq("state", state);
	}

	public List<SyncFile> findBySyncAccountId(long syncAccountId)
		throws SQLException {

		return queryForEq("syncAccountId", syncAccountId);
	}

}