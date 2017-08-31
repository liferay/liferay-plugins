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

package com.liferay.asset.entry.set.hook.upgrade.v1_0_8;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

/**
 * @author Timothy Bell
 */
public class UpgradeAssetEntrySet extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasIndex("AssetEntrySet", "IX_116E481")) {
			runSQL("alter table AssetEntrySet drop index IX_116E481");
		}

		if (hasIndex("AssetEntrySet", "IX_26CA2F3B")) {
			runSQL("alter table AssetEntrySet drop index IX_26CA2F3B");
		}
	}

	protected boolean hasIndex(String tableName, String indexName)
		throws Exception {

		Connection con = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			DatabaseMetaData metadata = con.getMetaData();

			rs = metadata.getIndexInfo(null, null, tableName, false, false);

			while (rs.next()) {
				String curIndexName = rs.getString("index_name");

				if (Validator.equals(indexName, curIndexName)) {
					return true;
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, null, rs);
		}

		return false;
	}

}