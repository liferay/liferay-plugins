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

package com.liferay.asset.entry.set.hook.upgrade.v1_0_1;

import com.liferay.asset.entry.set.util.PortletPropsValues;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

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
				long assetEntrySetId = rs.getLong("assetEntrySetId");
				String payload = rs.getString("payload");

				JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
					payload);

				if (!Validator.equals(
						payloadJSONObject.getString("type"), "image")) {

					continue;
				}

				JSONArray newImageDataJSONArray =
					JSONFactoryUtil.createJSONArray();

				JSONArray imageDataJSONArray = payloadJSONObject.getJSONArray(
					"imageData");

				for (int i = 0; i < imageDataJSONArray.length(); i++) {
					JSONObject newImageJSONObject =
						JSONFactoryUtil.createJSONObject();

					JSONObject fileEntryIdsJSONObject =
						JSONFactoryUtil.createJSONObject();

					JSONObject imageJSONObject =
						imageDataJSONArray.getJSONObject(i);

					for (String imageType :
							PortletPropsValues.ASSET_ENTRY_SET_IMAGE_TYPES) {

						String key = "imageURL_" + imageType;

						String url = imageJSONObject.getString(key);

						int beginIndex = url.lastIndexOf(StringPool.SLASH) + 1;

						int endIndex = url.indexOf(StringPool.QUESTION);

						if (endIndex < 0) {
							endIndex = url.length();
						}

						String uuid = url.substring(beginIndex, endIndex);

						DLFileEntry dlFileEntry =
							DLFileEntryLocalServiceUtil.
								fetchDLFileEntryByUuidAndCompanyId(
									uuid, PortalUtil.getDefaultCompanyId());

						if (dlFileEntry == null) {
							continue;
						}

						fileEntryIdsJSONObject.put(
							imageType, dlFileEntry.getFileEntryId());

						newImageJSONObject.put(key, url);
					}

					newImageJSONObject.put(
						"fileEntryIds", fileEntryIdsJSONObject);

					newImageDataJSONArray.put(newImageJSONObject);
				}

				payloadJSONObject.put("imageData", newImageDataJSONArray);

				ps.setString(1, payloadJSONObject.toString());

				ps.setLong(2, assetEntrySetId);

				ps.executeUpdate();
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}