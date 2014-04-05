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

package com.liferay.portal.workflow.kaleo.hook.upgrade.v1_2_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.workflow.kaleo.hook.upgrade.v1_2_0.util.KaleoLogTable;

import java.sql.SQLException;

/**
 * @author Kenneth Chang
 */
public class UpgradeKaleoLog extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type KaleoLog comment TEXT null");
		}
		catch (SQLException sqle) {
			upgradeTable(
				KaleoLogTable.TABLE_NAME, KaleoLogTable.TABLE_COLUMNS,
				KaleoLogTable.TABLE_SQL_CREATE,
				KaleoLogTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}