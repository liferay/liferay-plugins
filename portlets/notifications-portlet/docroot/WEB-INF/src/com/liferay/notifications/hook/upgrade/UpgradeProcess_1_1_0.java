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

package com.liferay.notifications.hook.upgrade;

import com.liferay.notifications.hook.upgrade.v1_1_0.UpgradeUserNotificationEvent;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Calvin Keum
 */
public class UpgradeProcess_1_1_0 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return 110;
	}

	@Override
	protected void doUpgrade() throws Exception {
		DB db = DBFactoryUtil.getDB();
		String type = db.getType();

		if (!type.equals(DB.TYPE_ORACLE)) {
			upgrade(UpgradeUserNotificationEvent.class);
		}
	}

}