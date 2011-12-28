/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.workflow.kaleo.definition.NodeType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public class UpgradeTaskInstanceToken extends UpgradeProcess {

	protected void deleteKaleoInstanceTokens() throws Exception {
		if (_kaleoInstanceTokenIds.isEmpty()) {
			return;
		}

		StringBundler sb = new StringBundler();

		sb.append("delete from KaleoInstanceToken where ");

		Iterator<Long> itr = _kaleoInstanceTokenIds.iterator();

		while (itr.hasNext()) {
			sb.append("(kaleoInstanceTokenId = ");
			sb.append(itr.next());
			sb.append(StringPool.CLOSE_PARENTHESIS);

			if (itr.hasNext()) {
				sb.append(" OR ");
			}
		}

		String sql = sb.toString();

		runSQL(sql);
	}

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select kaleoTaskInstanceTokenId, kaleoInstanceTokenId from " +
					"KaleoTaskInstanceToken");

			rs = ps.executeQuery();

			while (rs.next()) {
				long kaleoTaskInstanceTokenId = rs.getLong(
					"kaleoTaskInstanceTokenId");
				long oldKaleoInstanceTokenId = rs.getLong(
					"kaleoInstanceTokenId");

				long newKaleoInstanceTokenId = getKaleoInstanceTokenId(
					oldKaleoInstanceTokenId);

				if (oldKaleoInstanceTokenId == newKaleoInstanceTokenId) {
					continue;
				}

				StringBundler sb = new StringBundler();

				sb.append("update KaleoTaskInstanceToken set ");
				sb.append("kaleoInstanceTokenId = ");
				sb.append(newKaleoInstanceTokenId);
				sb.append(" where kaleoTaskInstanceTokenId = ");
				sb.append(kaleoTaskInstanceTokenId);

				String sql = sb.toString();

				runSQL(sql);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		deleteKaleoInstanceTokens();
	}

	protected long getKaleoInstanceTokenId(long kaleoInstanceTokenId)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler();

			sb.append("select KaleoNode.type_, ");
			sb.append("KaleoInstanceToken.kaleoInstanceTokenId ");
			sb.append("from KaleoNode inner join KaleoInstanceToken on ");
			sb.append("(Kaleonode.kaleoNodeId = ");
			sb.append("KaleoInstanceToken.currentKaleoNodeId) ");
			sb.append("where KaleoInstanceToken.kaleoInstanceTokenId = ");
			sb.append("(select parentKaleoInstanceTokenId from ");
			sb.append("KaleoInstanceToken where KaleoInstanceTokenId = ?)");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			ps.setLong(1, kaleoInstanceTokenId);

			rs = ps.executeQuery();

			if (rs.next()) {
				String type = rs.getString("type_");

				if (!type.equals(NodeType.TASK.toString())) {
					return kaleoInstanceTokenId;
				}

				long parentKaleoInstanceTokenId = rs.getLong(
					"kaleoInstanceTokenId");

				_kaleoInstanceTokenIds.add(kaleoInstanceTokenId);

				return getKaleoInstanceTokenId(parentKaleoInstanceTokenId);
			}
			else {
				return kaleoInstanceTokenId;
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private Set<Long> _kaleoInstanceTokenIds = new HashSet<Long>();

}