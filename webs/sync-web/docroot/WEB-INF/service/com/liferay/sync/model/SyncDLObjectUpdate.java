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

package com.liferay.sync.model;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * @author Michael Young
 * @author Shinn Lok
 */
@JSON
public class SyncDLObjectUpdate {

	public SyncDLObjectUpdate(
		List<SyncDLObject> syncDLObjects, int resultsTotal,
		long lastAccessTime) {

		_syncDLObjects = syncDLObjects;
		_resultsTotal = resultsTotal;
		_lastAccessTime = lastAccessTime;
	}

	public long getLastAccessTime() {
		return _lastAccessTime;
	}

	public int getResultsTotal() {
		return _resultsTotal;
	}

	@JSON
	public List<SyncDLObject> getSyncDLObjects() {
		return _syncDLObjects;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler((_syncDLObjects.size() * 52) + 5);

		sb.append("{\"lastAccessTime\": ");
		sb.append(_lastAccessTime);
		sb.append(", \"resultsTotal\": ");
		sb.append(_resultsTotal);
		sb.append(", \"syncDLObjects\": [");

		for (int i = 0; i < _syncDLObjects.size(); i++) {
			SyncDLObject syncDLObject = _syncDLObjects.get(i);

			sb.append("{\"syncDLObjectId\": ");
			sb.append(syncDLObject.getSyncDLObjectId());
			sb.append(", \"companyId\": ");
			sb.append(syncDLObject.getCompanyId());
			sb.append(", \"userId\": ");
			sb.append(syncDLObject.getUserId());
			sb.append(", \"userName\": \"");
			sb.append(syncDLObject.getUserName());
			sb.append("\", \"createTime\": ");
			sb.append(syncDLObject.getCreateTime());
			sb.append(", \"modifiedTime\": ");
			sb.append(syncDLObject.getModifiedTime());
			sb.append(", \"repositoryId\": ");
			sb.append(syncDLObject.getRepositoryId());
			sb.append(", \"parentFolderId\": ");
			sb.append(syncDLObject.getParentFolderId());
			sb.append(", \"name\": \"");
			sb.append(syncDLObject.getName());
			sb.append("\", \"extension\": \"");
			sb.append(syncDLObject.getExtension());
			sb.append("\", \"mimeType\": \"");
			sb.append(syncDLObject.getMimeType());
			sb.append("\", \"description\": \"");
			sb.append(syncDLObject.getDescription());
			sb.append("\", \"changeLog\": \"");
			sb.append(syncDLObject.getChangeLog());
			sb.append("\", \"extraSettings\": \"");
			sb.append(syncDLObject.getExtraSettings());
			sb.append("\", \"version\": \"");
			sb.append(syncDLObject.getVersion());
			sb.append("\", \"versionId\": ");
			sb.append(syncDLObject.getVersionId());
			sb.append(", \"size\": ");
			sb.append(syncDLObject.getSize());
			sb.append(", \"checksum\": \"");
			sb.append(syncDLObject.getChecksum());
			sb.append("\", \"event\": \"");
			sb.append(syncDLObject.getEvent());
			sb.append("\", \"lockExpirationDate\": ");
			sb.append(syncDLObject.getLockExpirationDate());
			sb.append(", \"lockUserId\": ");
			sb.append(syncDLObject.getLockUserId());
			sb.append(", \"lockUserName\": \"");
			sb.append(syncDLObject.getLockUserName());
			sb.append("\", \"type\": \"");
			sb.append(syncDLObject.getType());
			sb.append("\", \"typePK\": ");
			sb.append(syncDLObject.getTypePK());
			sb.append(", \"typeUuid\": \"");
			sb.append(syncDLObject.getTypeUuid());
			sb.append("\"}");

			if (i != (_syncDLObjects.size() - 1)) {
				sb.append(StringPool.COMMA);
			}
		}

		sb.append("]}");

		return sb.toString();
	}

	private long _lastAccessTime;
	private int _resultsTotal;
	private List<SyncDLObject> _syncDLObjects;

}