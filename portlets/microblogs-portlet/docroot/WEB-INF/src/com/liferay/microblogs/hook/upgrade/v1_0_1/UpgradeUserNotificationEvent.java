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
package com.liferay.microblogs.hook.upgrade.v1_0_0;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.MicroblogsEntryConstants;
import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;
import com.liferay.microblogs.util.MicroblogsUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Evan Thibodeau
 */
public class UpgradeUserNotificationEvent extends UpgradeProcess {

	protected void addUserNotificationEvent(
			long companyId, long userId, long userNotificationEventId,
			long timestamp, boolean actionRequired, boolean delivered,
			boolean archived)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(5);

			sb.append("insert into Notifications_UserNotificationEvent (");
			sb.append("notificationEventId, companyId, userId,");
			sb.append("userNotificationEventId, timestamp , delivered,");
			sb.append("actionRequired, archived) values (?, ?, ?, ?, ?, ?");
			sb.append(", ?, ?)");

			ps = con.prepareStatement(sb.toString());

			ps.setLong(1, increment());
			ps.setLong(2, companyId);
			ps.setLong(3, userId);
			ps.setLong(4, userNotificationEventId);
			ps.setLong(5, timestamp);
			ps.setBoolean(6, actionRequired);
			ps.setBoolean(7, delivered);
			ps.setBoolean(8, archived);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeNotificationEvents();
	}

	protected void upgradeNotificationEvents() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement("select * from UserNotificationEvent");

			rs = ps.executeQuery();

			while (rs.next()) {
				long userNotificationEventId = rs.getLong(
					"userNotificationEventId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String type = rs.getString("type_");
				long timestamp = rs.getLong("timestamp");
				boolean delivered = rs.getBoolean("delivered");
				boolean archived = rs.getBoolean("archived");

				boolean actionRequired = false;

				if (_actionRequiredTypes.contains(type)) {
					actionRequired = true;
				}

				addUserNotificationEvent(
					companyId, userId, userNotificationEventId, timestamp,
					actionRequired, delivered, archived);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private List<String> _actionRequiredTypes = ListUtil.fromArray(
		NotificationsConstants.ACTIONABLE_TYPES);

}