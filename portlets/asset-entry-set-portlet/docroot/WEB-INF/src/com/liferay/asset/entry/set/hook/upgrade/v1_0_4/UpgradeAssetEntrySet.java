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

package com.liferay.asset.entry.set.hook.upgrade.v1_0_4;

import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sherry Yang
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

				String assetEntryIds = payloadJSONObject.getString(
					"assetEntryIds");

				JSONArray imageDataJSONArray = payloadJSONObject.getJSONArray(
					"imageData");

				for (int i = 0; i < imageDataJSONArray.length(); i++) {
					JSONObject imageJSONObject =
						imageDataJSONArray.getJSONObject(i);

					String mimeType = imageJSONObject.getString("mimeType");

					if (!Validator.equals(mimeType, ContentTypes.IMAGE_GIF)) {
						continue;
					}

					String imageURLRaw = imageJSONObject.getString(
						"imageURL_raw");

					imageJSONObject.put("imageURL_full", imageURLRaw);

					int heightRaw = imageJSONObject.getInt("height_raw");

					imageJSONObject.put("height_full", heightRaw);

					int widthRaw = imageJSONObject.getInt("width_raw");

					imageJSONObject.put("width_full", widthRaw);

					JSONObject fileEntryIdsJSONObject =
						imageJSONObject.getJSONObject("fileEntryIds");

					long fullFileEntryId = fileEntryIdsJSONObject.getLong(
						"full");

					long rawFileEntryId = fileEntryIdsJSONObject.getLong("raw");

					fileEntryIdsJSONObject.put("full", rawFileEntryId);

					AssetEntry fullAssetEntry =
						AssetEntryLocalServiceUtil.fetchEntry(
							AssetEntrySet.class.getName(), fullFileEntryId);

					if (fullAssetEntry != null) {
						List<String> assetEntrySetIdList =
							new ArrayList<String>(
								Arrays.asList(StringUtil.split(assetEntryIds)));

						assetEntrySetIdList.remove(
							String.valueOf(fullAssetEntry.getEntryId()));

						assetEntryIds = StringUtil.merge(assetEntrySetIdList);
					}

					PortletFileRepositoryUtil.deletePortletFileEntry(
						fullFileEntryId);
				}

				payloadJSONObject.put("assetEntryIds", assetEntryIds);

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