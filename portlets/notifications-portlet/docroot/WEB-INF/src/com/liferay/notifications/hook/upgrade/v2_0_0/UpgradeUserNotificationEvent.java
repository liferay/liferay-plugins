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

package com.liferay.notifications.hook.upgrade.v2_0_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jonathan Lee
 */
public class UpgradeUserNotificationEvent extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeNotifications();
	}

	protected void upgradeNotifications() throws Exception {
		if (!hasTable("Notifications_UserNotificationEvent")) {
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select userNotificationEventId, actionRequired from " +
					"Notifications_UserNotificationEvent");

			rs = ps.executeQuery();

			while (rs.next()) {
				long userNotificationEventId = rs.getLong(
					"userNotificationEventId");
				int actionRequired = rs.getInt("actionRequired");

				StringBundler sb = new StringBundler(4);

				sb.append("update UserNotificationEvent set actionRequired = ");
				sb.append(actionRequired);
				sb.append(" where userNotificationEventId = ");
				sb.append(userNotificationEventId);

				runSQL(sb.toString());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		runSQL("drop table Notifications_UserNotificationEvent");
	}

}