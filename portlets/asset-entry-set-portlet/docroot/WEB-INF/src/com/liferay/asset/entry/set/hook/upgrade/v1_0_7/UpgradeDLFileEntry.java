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

package com.liferay.asset.entry.set.hook.upgrade.v1_0_7;

import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Matthew Kong
 */
public class UpgradeDLFileEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select * from DLFileEntry where classNameId = " +
					AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID +
						" order by classPK");

			rs = ps.executeQuery();

			long classPK = 0;
			List<Long> fileEntryIds = new ArrayList<Long>();

			while (rs.next()) {
				long curClassPK = rs.getLong("classPK");

				if (curClassPK != classPK) {
					classPK = curClassPK;

					fileEntryIds = getFileEntryIds(classPK);
				}

				long fileEntryId = rs.getLong("fileEntryId");

				if (!fileEntryIds.contains(fileEntryId)) {
					DLFileEntryLocalServiceUtil.deleteFileEntry(fileEntryId);
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected List<Long> getFileEntryIds(long assetEntrySetId)
		throws Exception {

		List<Long> fileEntryIds = new ArrayList<Long>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select * from AssetEntrySet where assetEntrySetId = " +
					assetEntrySetId);

			rs = ps.executeQuery();

			if (rs.next()) {
				String payload = rs.getString("payload");

				JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
					payload);

				JSONArray imageDataJSONArray = payloadJSONObject.getJSONArray(
					"imageData");

				if (imageDataJSONArray != null) {
					for (int i = 0; i < imageDataJSONArray.length(); i++) {
						JSONObject imageDataJSONObject =
							imageDataJSONArray.getJSONObject(i);

						JSONObject fileEntryIdsJSONObject =
							imageDataJSONObject.getJSONObject("fileEntryIds");

						Iterator<String> iterator =
							fileEntryIdsJSONObject.keys();

						while (iterator.hasNext()) {
							String key = iterator.next();

							fileEntryIds.add(
								fileEntryIdsJSONObject.getLong(key));
						}
					}
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return fileEntryIds;
	}

}