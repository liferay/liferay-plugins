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

package com.liferay.notifications.hook.upgrade.v1_3_0;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Christopher Kian
 */
public class UpgradeUserNotificationEvent extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeNotificationEvents();
	}

	protected void upgradeNotificationEvents() throws Exception {
		DB db = DBFactoryUtil.getDB();

		String type = db.getType();

		if (type.equals(DB.TYPE_ORACLE)) {
			runSQL("delete from Ntfctns_UserNotificationEvent");

			upgrade(
				com.liferay.notifications.hook.upgrade.v1_1_0.
					UpgradeUserNotificationEvent.class);
		}
	}

}