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

package com.liferay.sync.model.upgrade;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.util.BaseUpgradeTableListener;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ServiceComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dennis Ju
 */
public class SyncDLObjectUpgradeTableListener extends BaseUpgradeTableListener {

	@Override
	public void onAfterUpdateTable(
			ServiceComponent previousServiceComponent,
			UpgradeTable upgradeTable)
		throws Exception {

		if (_syncDLObjectIds == null) {
			return;
		}

		Map<Long, Long> syncDLObjectIds = _syncDLObjectIds;

		_syncDLObjectIds = null;

		updateSyncDLObjectIds(syncDLObjectIds);

		runSQL("alter table SyncDLObject add primary key (syncDLObjectId)");
	}

	@Override
	public void onBeforeUpdateTable(
			ServiceComponent previousServiceComponent,
			UpgradeTable upgradeTable)
		throws Exception {

		if (previousServiceComponent.getBuildNumber() >= 2) {
			return;
		}

		String createSQL = upgradeTable.getCreateSQL();

		createSQL = StringUtil.replace(
			createSQL, " primary key", StringPool.BLANK);

		upgradeTable.setCreateSQL(createSQL);

		_syncDLObjectIds = getSyncDLObjectIds();
	}

	protected Map<Long, Long> getSyncDLObjectIds() {
		Map<Long, Long> syncDLObjectIds = new HashMap<Long, Long>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String keyColumnName = "typePK";
			String valueColumnName = "objectId";

			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select " + keyColumnName + ", " + valueColumnName + " from " +
					"SyncDLObject");

			rs = ps.executeQuery();

			while (rs.next()) {
				long key = rs.getLong(keyColumnName);
				long value = rs.getLong(valueColumnName);

				if (_log.isDebugEnabled()) {
					_log.debug(
						"{" + keyColumnName + "=" + key + ", " +
							valueColumnName + "=" + value + "}");
				}

				syncDLObjectIds.put(key, value);
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return syncDLObjectIds;
	}

	protected void updateSyncDLObjectIds(Map<Long, Long> keyValueMap)
		throws Exception {

		for (Map.Entry<Long, Long> entry : keyValueMap.entrySet()) {
			StringBundler sb = new StringBundler(4);

			sb.append("update SyncDLObject set syncDLObjectId = ");
			sb.append(entry.getValue());
			sb.append(" where typePK = ");
			sb.append(entry.getKey());

			runSQL(sb.toString());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SyncDLObjectUpgradeTableListener.class);

	private Map<Long, Long> _syncDLObjectIds;

}