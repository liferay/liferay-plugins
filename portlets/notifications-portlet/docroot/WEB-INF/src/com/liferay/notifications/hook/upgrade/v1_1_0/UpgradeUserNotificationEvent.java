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

package com.liferay.notifications.hook.upgrade.v1_1_0;

import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.notifications.service.UserNotificationEventLocalServiceUtil;
import com.liferay.notifications.util.NotificationsConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Calvin Keum
 */
public class UpgradeUserNotificationEvent extends UpgradeProcess {

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

			ps = con.prepareStatement(
				"select userNotificationEventId, userId, type_, timestamp, " +
				"delivered, payload, archived from UserNotificationEvent");

			rs = ps.executeQuery();

			while (rs.next()) {
				long userNotificationEventId = rs.getLong(
					"userNotificationEventId");
				long userId = rs.getLong("userId");
				String type = rs.getString("type_");
				long timestamp = rs.getLong("timestamp");
				boolean delivered = rs.getBoolean("delivered");
				String payload = rs.getString("payload");
				boolean archived = rs.getBoolean("archived");

				List<String> actionRequiredTypes = ListUtil.fromArray(
					NotificationsConstants.ACTIONABLE_TYPES);

				boolean actionRequired = false;

				if (actionRequiredTypes.contains(type)) {
					actionRequired = true;
				}

				UserNotificationEventLocalServiceUtil.addUserNotificationEvent(
					userNotificationEventId, userId, timestamp, actionRequired,
					delivered, archived);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}