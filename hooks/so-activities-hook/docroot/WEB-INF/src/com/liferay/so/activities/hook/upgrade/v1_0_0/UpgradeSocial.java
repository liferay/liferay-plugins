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

package com.liferay.so.activities.hook.upgrade.v1_0_0;

import com.liferay.compat.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Matthew Kong
 */
public class UpgradeSocial extends UpgradeProcess {

	protected void addActivitySet(
			long activitySetId, long groupId, long companyId, long userId,
			long createDate, long classNameId, long classPK, int type_)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(4);

			sb.append("insert into SocialActivitySet (activitySetId, ");
			sb.append("groupId, companyId, userId, createDate, modifiedDate, ");
			sb.append("classNameId, classPK, type_, activityCount) values ");
			sb.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps = con.prepareStatement(sb.toString());

			ps.setLong(1, activitySetId);
			ps.setLong(2, groupId);
			ps.setLong(3, companyId);
			ps.setLong(4, userId);
			ps.setLong(5, createDate);
			ps.setLong(6, createDate);
			ps.setLong(7, classNameId);
			ps.setLong(8, classPK);
			ps.setInt(9, type_);
			ps.setInt(10, 1);

			ps.executeUpdate();
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to add activity set " + activitySetId, e);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void addSOSocialActivity(long activityId, long activitySetId)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"insert into SO_SocialActivity (activityId, activitySetId) " +
					"values (?, ?)");

			ps.setLong(1, activityId);
			ps.setLong(2, activitySetId);

			ps.executeUpdate();
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to add SO_SocialActivity " + activityId, e);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		migrateActivities();
	}

	protected boolean hasActivitySets() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement("select count(*) from SocialActivitySet");

			rs = ps.executeQuery();

			while (rs.next()) {
				int count = rs.getInt(1);

				if (count > 0) {
					return true;
				}
			}

			return false;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void migrateActivities() throws Exception {
		if (hasActivitySets()) {
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select activityId, groupId, companyId, userId, " +
					"createDate, mirrorActivityId, classNameId, classPK, " +
						"type_ from SocialActivity");

			rs = ps.executeQuery();

			while (rs.next()) {
				long activityId = rs.getLong("activityId");
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				long createDate = rs.getLong("createDate");
				long mirrorActivityId = rs.getLong("mirrorActivityId");
				long classNameId = rs.getLong("classNameId");
				long classPK = rs.getLong("classPK");
				int type_ = rs.getInt("type_");

				if (mirrorActivityId > 0) {
					continue;
				}

				long activitySetId = increment();

				addActivitySet(
					activitySetId, groupId, companyId, userId, createDate,
					classNameId, classPK, type_);
				addSOSocialActivity(activityId, activitySetId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UpgradeSocial.class);

}