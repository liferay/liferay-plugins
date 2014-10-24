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
package com.liferay.microblogs.hook.upgrade.v1_0_1;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.MicroblogsEntryConstants;
import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;
import com.liferay.microblogs.util.MicroblogsUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Evan Thibodeau
 */
public class UpgradeUserNotificationEvent extends UpgradeProcess {

	public static int getNotificationType(
			MicroblogsEntry microblogsEntry, long userId)
		throws PortalException {

		if (MicroblogsUtil.isTaggedUser(
				microblogsEntry.getMicroblogsEntryId(), false, userId)) {

			return MicroblogsEntryConstants.NOTIFICATION_TYPE_TAG;
		}
		else if (microblogsEntry.getType() ==
					MicroblogsEntryConstants.TYPE_REPLY) {

			long parentMicroblogsEntryId =
				MicroblogsUtil.getParentMicroblogsEntryId(microblogsEntry);

			if (MicroblogsUtil.getParentMicroblogsUserId(microblogsEntry) ==
					userId) {

				return MicroblogsEntryConstants.NOTIFICATION_TYPE_REPLY;
			}
			else if (MicroblogsUtil.hasReplied(
						parentMicroblogsEntryId, userId)) {

				return MicroblogsEntryConstants.
					NOTIFICATION_TYPE_REPLY_TO_REPLIED;
			}
			else if (MicroblogsUtil.isTaggedUser(
						parentMicroblogsEntryId, true, userId)) {

				return MicroblogsEntryConstants.
					NOTIFICATION_TYPE_REPLY_TO_TAGGED;
			}
		}

		return 0;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeNotifications();
	}

	protected void updateNotification(
			long userNotificationEventId, JSONObject jsonObject)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"update UserNotificationEvent set payload = ? " +
					"where userNotificationEventId = ?");

			ps.setString(1, jsonObject.toString());
			ps.setLong(2, userNotificationEventId);

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
				"select userNotificationEventId, userId, payload from " +
					"UserNotificationEvent where type_ = ?");

			ps.setString(1, "1_WAR_microblogsportlet");

			rs = ps.executeQuery();

			while (rs.next()) {
				long userNotificationEventId = rs.getLong(
					"userNotificationEventId");
				String payload = rs.getString("payload");

				JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
					payload);

				long microblogsEntryId = payloadJSONObject.getLong("classPK");

				MicroblogsEntry microblogsEntry =
					MicroblogsEntryLocalServiceUtil.fetchMicroblogsEntry(
						microblogsEntryId);

				if (microblogsEntry == null) {
					return;
				}

				int notificationType = payloadJSONObject.getInt(
					"notificationType");

				if (notificationType != 0) {
					return;
				}

				long userId = rs.getLong("userId");

				notificationType = getNotificationType(microblogsEntry, userId);

				payloadJSONObject.put("notificationType", notificationType);

				updateNotification(userNotificationEventId, payloadJSONObject);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}