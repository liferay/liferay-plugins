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

package com.liferay.sync.hook.upgrade.v1_0_2;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Shinn Lok
 */
public class UpgradeIndex extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		DB db = DBFactoryUtil.getDB();

		String type = db.getType();

		try {
			if (type.equals(DB.TYPE_MYSQL)) {
				runSQL(
					"create index IX_32B691FF on SyncDLObject (treePath(255))");
				runSQL(
					"create index IX_EE41CBEB on SyncDLObject " +
						"(treePath(255), event_)");
			}
			else {
				runSQL("create index IX_32B691FF on SyncDLObject (treePath))");
				runSQL(
					"create index IX_EE41CBEB on SyncDLObject (treePath, " +
						"event_)");
			}
		}
		catch (Exception e) {
		}
	}

}