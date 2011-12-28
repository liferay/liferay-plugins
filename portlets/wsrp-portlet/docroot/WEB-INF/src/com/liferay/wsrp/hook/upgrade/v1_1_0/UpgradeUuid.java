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

package com.liferay.wsrp.hook.upgrade.v1_1_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.wsrp.consumer.portlet.ConsumerPortlet;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Michael Young
 */
public class UpgradeUuid extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		List<WSRPConsumerPortlet> wsrpConsumerPortlets =
			WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlets(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (WSRPConsumerPortlet wsrpConsumerPortlet : wsrpConsumerPortlets) {
			StringBuilder sb = new StringBuilder(4);

			sb.append(ConsumerPortlet.PORTLET_NAME_PREFIX);
			sb.append(wsrpConsumerPortlet.getCompanyId());
			sb.append(StringPool.UNDERLINE);
			sb.append(wsrpConsumerPortlet.getWsrpConsumerPortletId());

			String oldPortletId = PortalUtil.getJsSafePortletId(sb.toString());

			String newPortletId = ConsumerPortlet.PORTLET_NAME_PREFIX.concat(
				wsrpConsumerPortlet.getUuid());

			newPortletId = PortalUtil.getJsSafePortletId(
				PortalUUIDUtil.toJsSafeUuid(newPortletId));

			updateLayout(oldPortletId, newPortletId);
			updateResourceAction(oldPortletId, newPortletId);
			updateResourcePermission(oldPortletId, newPortletId);
		}
	}

	protected void updateLayout(long plid, String typeSettings)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"update Layout set typeSettings = ? where plid = " + plid);

			ps.setString(1, typeSettings);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void updateLayout(String oldPortletId, String newPortletId)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select plid, typeSettings from Layout where typeSettings " +
					"like ?");

			ps.setString(
				1,
				StringPool.PERCENT + oldPortletId + StringPool.PERCENT);

			rs = ps.executeQuery();

			while (rs.next()) {
				long plid = rs.getLong("plid");
				String typeSettings = rs.getString("typeSettings");

				typeSettings = StringUtil.replace(
					typeSettings, oldPortletId, newPortletId);

				updateLayout(plid, typeSettings);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateResourceAction(
			String oldPortletId, String newPortletId)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"update ResourceAction set name = ? where name = ? ");

			ps.setString(1, newPortletId);
			ps.setString(2, oldPortletId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void updateResourcePermission(
			long resourcePermissionId, String name, String primKey)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"update ResourcePermission set name = ?, primKey = ? where " +
					"resourcePermissionId = ?");

			ps.setString(1, name);
			ps.setString(2, primKey);
			ps.setLong(3, resourcePermissionId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void updateResourcePermission(
			String oldPortletId, String newPortletId)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select resourcePermissionId, primKey from " +
					"ResourcePermission where name = ?");

			ps.setString(1, oldPortletId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long resourcePermissionId = rs.getLong("resourcePermissionId");
				String primKey = rs.getString("primKey");

				primKey = StringUtil.replace(
					primKey, oldPortletId, newPortletId);

				updateResourcePermission(
					resourcePermissionId, newPortletId, primKey);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}