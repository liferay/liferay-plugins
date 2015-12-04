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

package com.liferay.asset.entry.set.hook.upgrade.v1_0_3;

import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.asset.entry.set.util.AssetEntrySetImageUtil;
import com.liferay.asset.entry.set.util.PortletKeys;
import com.liferay.asset.entry.set.util.PortletPropsKeys;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.util.portlet.PortletProps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Matthew Kong
 */
public class UpgradeAssetEntrySet extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeAssetEntrySet("full");
	}

	protected void upgradeAssetEntrySet(String imageType) throws Exception {
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

				String[] assetTagNames = StringUtil.split(
					payloadJSONObject.getString(
						AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES));

				String assetEntryIds = payloadJSONObject.getString(
					"assetEntryIds");

				JSONArray newImageDataJSONArray =
					JSONFactoryUtil.createJSONArray();

				JSONArray imageDataJSONArray = payloadJSONObject.getJSONArray(
					"imageData");

				for (int i = 0; i < imageDataJSONArray.length(); i++) {
					JSONObject imageJSONObject =
						imageDataJSONArray.getJSONObject(i);

					JSONObject fileEntryIdsJSONObject =
						imageJSONObject.getJSONObject("fileEntryIds");

					long rawFileEntryId = fileEntryIdsJSONObject.getLong("raw");

					FileEntry rawFileEntry =
						PortletFileRepositoryUtil.getPortletFileEntry(
							rawFileEntryId);

					ImageBag imageBag = ImageToolUtil.read(
						DLFileEntryLocalServiceUtil.getFileAsStream(
							rawFileEntry.getFileEntryId(),
							rawFileEntry.getVersion(), false));

					String imageMaxSize = PortletProps.get(
						PortletPropsKeys.ASSET_ENTRY_SET_IMAGE_MAX_SIZE,
						new Filter(imageType));

					FileEntry fileEntry =
						AssetEntrySetImageUtil.addScaledImageFileEntry(
							rawFileEntry.getUserId(),
							AssetEntrySetConstants.
								ASSET_ENTRY_SET_CLASS_NAME_ID,
							0L, PortletKeys.ASSET_ENTRY_SET, imageBag,
							imageType, rawFileEntry.getTitle(), imageMaxSize);

					DLFileEntry dlFileEntry =
						DLFileEntryLocalServiceUtil.getFileEntry(
							fileEntry.getFileEntryId());

					dlFileEntry.setClassPK(assetEntrySetId);

					dlFileEntry = DLFileEntryLocalServiceUtil.updateDLFileEntry(
						dlFileEntry);

					Group group = GroupLocalServiceUtil.getCompanyGroup(
						dlFileEntry.getCompanyId());

					AssetEntry assetEntry =
						AssetEntryLocalServiceUtil.updateEntry(
							dlFileEntry.getUserId(), group.getGroupId(),
							AssetEntrySet.class.getName(),
							dlFileEntry.getFileEntryId(), null, assetTagNames);

					assetEntryIds =
						assetEntryIds + StringPool.COMMA +
							assetEntry.getEntryId();

					newImageDataJSONArray.put(
						AssetEntrySetImageUtil.getImageJSONObject(
							imageJSONObject, fileEntry, imageType));
				}

				payloadJSONObject.put("assetEntryIds", assetEntryIds);
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