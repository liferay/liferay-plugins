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

package com.liferay.asset.entry.set.hook.upgrade.v1_0_6;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Matthew Kong
 */
public class UpgradeAssetEntrySet extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement("select * from AssetEntrySet");

			rs = ps.executeQuery();

			ps = con.prepareStatement(
				"update AssetEntrySet set payload = ? where assetEntrySetId " +
					"= ?");

			while (rs.next()) {
				String payload = rs.getString("payload");

				JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
					payload);

				String linkData = payloadJSONObject.getString("linkData");

				if (Validator.isNull(linkData)) {
					continue;
				}

				JSONObject linkDataJSONObject =
					JSONFactoryUtil.createJSONObject(linkData);

				payloadJSONObject.put("linkData", linkDataJSONObject);

				ps.setString(1, payloadJSONObject.toString());

				long assetEntrySetId = rs.getLong("assetEntrySetId");

				ps.setLong(2, assetEntrySetId);

				ps.executeUpdate();
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}