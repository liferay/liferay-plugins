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

	public SyncFile fetchSyncFile(
			long parentFolderId, long repositoryId, long syncAccountId)
		throws SQLException {

		Map<String, Object> fieldValues = new HashMap<String, Object>();

		fieldValues.put("parentFolderId", parentFolderId);
		fieldValues.put("repositoryId", repositoryId);
		fieldValues.put("syncAccountId", syncAccountId);

		List<SyncFile> syncFiles = queryForFieldValues(fieldValues);

		if ((syncFiles == null) || syncFiles.isEmpty()) {
			return null;
		}

		return syncFiles.get(0);
	}

	public SyncFile fetchSyncFile(String filePath, long syncAccountId)
		throws SQLException {

		Map<String, Object> fieldValues = new HashMap<String, Object>();

		fieldValues.put("filePath", filePath);
		fieldValues.put("syncAccountId", syncAccountId);

		List<SyncFile> syncFiles = queryForFieldValues(fieldValues);

		if ((syncFiles == null) || syncFiles.isEmpty()) {
			return null;
		}

		return syncFiles.get(0);
	}

	public List<SyncFile> findSyncFiles(long syncAccountId)
		throws SQLException {

		return queryForEq("syncAccountId", syncAccountId);
	}

}