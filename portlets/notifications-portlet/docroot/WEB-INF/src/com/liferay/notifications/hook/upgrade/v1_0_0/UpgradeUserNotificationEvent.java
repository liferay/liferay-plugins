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

package com.liferay.notifications.hook.upgrade.v1_0_0;

import com.liferay.announcements.web.constants.AnnouncementsPortletKeys;
import com.liferay.notifications.util.PortletKeys;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.announcements.constants.AnnouncementsConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Matthew Kong
 */
public class UpgradeUserNotificationEvent extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeNotifications();
	}

	protected void updateNotification(
			long userNotificationEventId, String type, JSONObject jsonObject)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"update UserNotificationEvent set type_ = ?, payload = ? " +
					"where userNotificationEventId = ?");

			ps.setString(1, type);
			ps.setString(2, jsonObject.toString());
			ps.setLong(3, userNotificationEventId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void upgradeNotifications() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select userNotificationEventId, payload from " +
					"UserNotificationEvent where type_ = ?");

			ps.setString(1, "6_WAR_soportlet");

			rs = ps.executeQuery();

			while (rs.next()) {
				long userNotificationEventId = rs.getLong(
					"userNotificationEventId");
				String payload = rs.getString("payload");

				JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
					payload);

				String type = payloadJSONObject.getString("portletId");

				if (Validator.isNull(type)) {
					return;
				}

				if (type.equals(_PORTLET_ID_ANNOUNCEMENTS) ||
					type.equals(AnnouncementsPortletKeys.ANNOUNCEMENTS)) {

					type = PortletKeys.SO_ANNOUNCEMENTS;
				}

				payloadJSONObject.remove("portletId");

				long entryId = payloadJSONObject.getLong("entryId");

				if (entryId > 0) {
					payloadJSONObject.put("classPK", entryId);

					payloadJSONObject.remove("entryId");
				}
				else if (type.equals(PortletKeys.CONTACTS_CENTER)) {
					long socialRequestId = payloadJSONObject.getLong(
						"requestId");

					if (socialRequestId > 0) {
						payloadJSONObject.put("classPK", socialRequestId);

						payloadJSONObject.remove("socialRequestId");
					}
				}
				else if (type.equals(PortletKeys.SO_INVITE_MEMBERS)) {
					long memberRequestId = payloadJSONObject.getLong(
						"memberRequestId");

					if (memberRequestId > 0) {
						payloadJSONObject.put("classPK", memberRequestId);

						payloadJSONObject.remove("memberRequestId");
					}
				}

				updateNotification(
					userNotificationEventId, type, payloadJSONObject);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static final String _PORTLET_ID_ANNOUNCEMENTS = "84";

}