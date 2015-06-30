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

package com.liferay.asset.entry.set.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Matthew Kong
 */
public class UpgradeAssetEntrySet extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeAssetEntrySet();
	}

	protected void upgradeAssetEntrySet() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement("select * from AssetEntrySet");

			rs = ps.executeQuery();

			ps = con.prepareStatement(
				"update AssetEntrySet set payload = ? where assetEntrySetId =" +
					" ?");

			while (rs.next()) {
				long assetEntrySetId = rs.getLong("assetEntrySetId");
				String payload = rs.getString("payload");

				payload = StringUtil.replace(
					payload, StringPool.NBSP, StringPool.SPACE);
				payload = HtmlUtil.unescape(payload);

				ps.setString(1, payload);

				ps.setLong(2, assetEntrySetId);

				ps.executeUpdate();
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}