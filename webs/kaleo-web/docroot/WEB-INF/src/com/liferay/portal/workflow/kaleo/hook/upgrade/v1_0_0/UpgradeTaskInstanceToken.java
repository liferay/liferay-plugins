/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
		if (kaleoInstanceTokenIds.size() == 0) {
			return;
		}

		StringBundler sb = new StringBundler();

		sb.append("delete from kaleoInstanceToken where ");

		Iterator<Long> it = kaleoInstanceTokenIds.iterator();

		while (it.hasNext()) {
			sb.append("(kaleoInstanceTokenId = ");
			sb.append(it.next());
			sb.append(StringPool.CLOSE_PARENTHESIS);

			if (it.hasNext()) {
				sb.append(" OR ");
			}
		}

		String sql = sb.toString();

		runSQL(sql);
	}

	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select kaleoTaskInstanceTokenId, kaleoInstanceTokenId " +
				"from kaleoTaskInstanceToken");

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

				sb.append("update KaleoTaskInstanceToken ");
				sb.append("set kaleoInstanceTokenId = ");
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
		throws Exception{

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		long parentKaleoInstanceTokenId = 0;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler();

			sb.append("select kaleoNode.type_, ");
			sb.append("kaleoInstanceToken.kaleoInstanceTokenId ");
			sb.append("from kaleoNode inner join kaleoInstanceToken on ");
			sb.append("(kaleonode.kaleonodeId = ");
			sb.append("kaleoInstanceToken.currentKaleoNodeId) ");
			sb.append("where kaleoInstanceToken.kaleoInstanceTokenId = ");
			sb.append("(select parentKaleoInstanceTokenId ");
			sb.append("from kaleoInstanceToken ");
			sb.append("where kaleoInstanceTokenId = ? )");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			ps.setLong(1, kaleoInstanceTokenId);

			rs = ps.executeQuery();

			if (rs.next()) {
				String nodeType = rs.getString("type_");
				parentKaleoInstanceTokenId = rs.getLong(
					"kaleoInstanceTokenId");

				if (!nodeType.equals(NodeType.TASK.toString())) {
					return kaleoInstanceTokenId;
				}

				kaleoInstanceTokenIds.add(kaleoInstanceTokenId);
			}
			else {
				return kaleoInstanceTokenId;
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return getKaleoInstanceTokenId(parentKaleoInstanceTokenId);
	}

	protected Set<Long> kaleoInstanceTokenIds = new HashSet<Long>();

}