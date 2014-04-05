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

package com.liferay.portal.workflow.kaleo.model.upgrade;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.util.BaseUpgradeTableListener;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.ServiceComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class BaseKaleoUpgradeTableListener extends BaseUpgradeTableListener {

	protected Map<Long, Long> getKeyValueMap(
			String tableName, String keyColumnName, String valueColumnName)
		throws SystemException {

		Map<Long, Long> keyValueMap = new HashMap<Long, Long>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select " + keyColumnName + ", " + valueColumnName + " from " +
					tableName);

			rs = ps.executeQuery();

			while (rs.next()) {
				long key = rs.getLong(keyColumnName);
				long value = rs.getLong(valueColumnName);

				if (_log.isDebugEnabled()) {
					_log.debug(
						"{" + keyColumnName + "=" + key + ", " +
							valueColumnName + "=" + value + "}");
				}

				keyValueMap.put(key, value);
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return keyValueMap;
	}

	protected boolean isFixAutoUpgrade(
		ServiceComponent previousServiceComponent) {

		if (previousServiceComponent.getBuildNumber() >= 4) {
			return false;
		}

		return true;
	}

	protected void updateKeyValueMap(
			Map<Long, Long> keyValueMap, String kaleoClassName,
			String tableName, String keyColumnName)
		throws Exception {

		for (Map.Entry<Long, Long> entry : keyValueMap.entrySet()) {
			StringBundler sb = new StringBundler(10);

			sb.append("update ");
			sb.append(tableName);
			sb.append(" set kaleoClassName = '");
			sb.append(kaleoClassName);
			sb.append("', kaleoClassPK = ");
			sb.append(entry.getValue());
			sb.append(" where ");
			sb.append(keyColumnName);
			sb.append(" = ");
			sb.append(entry.getKey());

			runSQL(sb.toString());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		BaseKaleoUpgradeTableListener.class);

}