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

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

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
		StringBundler sb = new StringBundler((_syncDLObjects.size() * 78) + 5);

		sb.append("{\"lastAccessTime\":");
		sb.append(_lastAccessTime);
		sb.append(",\"resultsTotal\":");
		sb.append(_resultsTotal);
		sb.append(",\"syncDLObjects\":[");

		for (int i = 0; i < _syncDLObjects.size(); i++) {
			SyncDLObject syncDLObject = _syncDLObjects.get(i);

			sb.append("{\"changeLog\":");
			append(sb, syncDLObject.getChangeLog(), true);
			sb.append(",\"checksum\":");
			append(sb, syncDLObject.getChecksum(), false);
			sb.append(",\"companyId\":");
			sb.append(syncDLObject.getCompanyId());
			sb.append(",\"createTime\":");
			sb.append(syncDLObject.getCreateTime());
			sb.append(",\"description\":");
			append(sb, syncDLObject.getDescription(), true);
			sb.append(",\"event\":");
			append(sb, syncDLObject.getEvent(), false);
			sb.append(",\"extension\":");
			append(sb, syncDLObject.getExtension(), true);
			sb.append(",\"extraSettings\":");
			append(sb, syncDLObject.getExtraSettings(), true);
			sb.append(",\"lockExpirationDate\":");

			Date lockExpirationDate = syncDLObject.getLockExpirationDate();

			if (lockExpirationDate != null) {
				sb.append(lockExpirationDate.getTime());
			}
			else {
				sb.append(StringPool.NULL);
			}

			sb.append(",\"lockUserId\":");
			sb.append(syncDLObject.getLockUserId());
			sb.append(",\"lockUserName\":");
			append(sb, syncDLObject.getLockUserName(), true);
			sb.append(",\"mimeType\":");
			append(sb, syncDLObject.getMimeType(), true);
			sb.append(",\"modifiedTime\":");
			sb.append(syncDLObject.getModifiedTime());
			sb.append(",\"name\":");
			append(sb, syncDLObject.getName(), true);
			sb.append(",\"parentFolderId\":");
			sb.append(syncDLObject.getParentFolderId());
			sb.append(",\"repositoryId\":");
			sb.append(syncDLObject.getRepositoryId());
			sb.append(",\"size\":");
			sb.append(syncDLObject.getSize());
			sb.append(",\"syncDLObjectId\":");
			sb.append(syncDLObject.getSyncDLObjectId());
			sb.append(",\"type\":");
			append(sb, syncDLObject.getType(), false);
			sb.append(",\"typePK\":");
			sb.append(syncDLObject.getTypePK());
			sb.append(",\"typeUuid\":");
			append(sb, syncDLObject.getTypeUuid(), false);
			sb.append(",\"userId\":");
			sb.append(syncDLObject.getUserId());
			sb.append(",\"userName\":");
			append(sb, syncDLObject.getUserName(), true);
			sb.append(",\"version\":");
			append(sb, syncDLObject.getVersion(), false);
			sb.append(",\"versionId\":");
			sb.append(syncDLObject.getVersionId());
			sb.append(StringPool.CLOSE_CURLY_BRACE);

			if (i != (_syncDLObjects.size() - 1)) {
				sb.append(StringPool.COMMA);
			}
		}

		sb.append("]}");

		return sb.toString();
	}

	protected void append(StringBundler sb, String s, boolean escape) {
		sb.append(StringPool.QUOTE);

		if (escape) {
			s = StringEscapeUtils.escapeJava(s);
		}

		sb.append(s);

		sb.append(StringPool.QUOTE);
	}

	private long _lastAccessTime;
	private int _resultsTotal;
	private List<SyncDLObject> _syncDLObjects;

}