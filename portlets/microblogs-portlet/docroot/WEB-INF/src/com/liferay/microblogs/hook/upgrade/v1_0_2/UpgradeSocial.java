/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.microblogs.hook.upgrade.v1_0_2;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Matthew Kong
 */
public class UpgradeSocial extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeMicroblogActivities();
	}

	protected void updateSocialActivity(long activityId, JSONObject jsonObject)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"update SocialActivity set extraData = ? where activityId = ?");

			ps.setString(1, jsonObject.toString());
			ps.setLong(2, activityId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void upgradeMicroblogActivities() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select activityId, extraData from SocialActivity where " +
					"classNameId = ?");

			ps.setLong(1, PortalUtil.getClassNameId(MicroblogsEntry.class));

			rs = ps.executeQuery();

			while (rs.next()) {
				long activityId = rs.getLong("activityId");
				String extraData = rs.getString("extraData");

				JSONObject extraDataJSONObject =
					JSONFactoryUtil.createJSONObject(extraData);

				long parentMicroblogsEntryId = extraDataJSONObject.getLong(
					"receiverMicroblogsEntryId");

				extraDataJSONObject.put(
					"parentMicroblogsEntryId", parentMicroblogsEntryId);

				extraDataJSONObject.remove("receiverMicroblogsEntryId");

				updateSocialActivity(activityId, extraDataJSONObject);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}