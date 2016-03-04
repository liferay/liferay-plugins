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

package com.liferay.asset.entry.set.hook.upgrade.v1_0_5;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Image;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
				"select * from DLFileEntry where (extraSettings is null) or " +
					"(extraSettings = '')");

			rs = ps.executeQuery();

			ps = con.prepareStatement(
				"update DLFileEntry set extraSettings = ? where " +
					"fileEntryId = ?");

			while (rs.next()) {
				long fileEntryId = rs.getLong("fileEntryId");

				JSONObject fileEntryImageJSONObject =
					getFileEntryImageJSONObject(fileEntryId);

				if (fileEntryImageJSONObject == null) {
					continue;
				}

				UnicodeProperties extraSettingsProperties =
					new UnicodeProperties();

				extraSettingsProperties.put(
					"fileEntryImageJSONObject",
					fileEntryImageJSONObject.toString());

				ps.setString(1, extraSettingsProperties.toString());

				ps.setLong(2, fileEntryId);

				ps.executeUpdate();
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected JSONObject getFileEntryImageJSONObject(long fileEntryId)
		throws Exception {

		JSONObject fileEntryImageJSONObject =
			JSONFactoryUtil.createJSONObject();

		try {
			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
				fileEntryId);

			String imageType = getImageType(fileEntry.getTitle());

			Image image = ImageToolUtil.getImage(
				DLFileEntryLocalServiceUtil.getFileAsStream(
					fileEntryId, fileEntry.getVersion(), false));

			fileEntryImageJSONObject.put(
				"height_" + imageType, image.getHeight());
			fileEntryImageJSONObject.put(
				"imageURL_" + imageType,
				DLUtil.getPreviewURL(
					fileEntry, fileEntry.getFileVersion(), null,
					StringPool.BLANK, false, true));
			fileEntryImageJSONObject.put("mimeType", fileEntry.getMimeType());
			fileEntryImageJSONObject.put("name", fileEntry.getTitle());
			fileEntryImageJSONObject.put(
				"width_" + imageType, image.getWidth());

			return fileEntryImageJSONObject;
		}
		catch (Exception e) {
		}

		return null;
	}

	protected String getImageType(String title) {
		for (String imageType : _IMAGE_TYPES) {
			if (title.contains(imageType)) {
				return imageType;
			}
		}

		return "raw";
	}

	private final static String[] _IMAGE_TYPES =
		{"email", "full", "mobile", "raw", "thumbnail", "web"};

}