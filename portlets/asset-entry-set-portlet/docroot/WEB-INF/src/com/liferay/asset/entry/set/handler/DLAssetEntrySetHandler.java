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

package com.liferay.asset.entry.set.handler;

import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;

import java.io.File;

/**
 * @author Matthew Kong
 */
public class DLAssetEntrySetHandler extends BaseAssetEntrySetHandler {

	public DLAssetEntrySetHandler(String portletId) {
		super(portletId);
	}

	@Override
	public String interpret(JSONObject payloadJSONObject, File file)
		throws PortalException, SystemException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAssetTagNames(
			StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES)));

		FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
			payloadJSONObject.getLong("userId"),
			payloadJSONObject.getLong("repositoryId"),
			payloadJSONObject.getLong("folderId"),
			payloadJSONObject.getString("fileName"), null,
			payloadJSONObject.getString("title"),
			payloadJSONObject.getString("description"),
			payloadJSONObject.getString("changeLog"), file, serviceContext);

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
			DLFileEntry.class.getName(), fileEntry.getFileEntryId());

		jsonObject.put("assetEntryIds", assetEntry.getEntryId());

		jsonObject.put(
			"imageURL",
			DLUtil.getPreviewURL(
				fileEntry, fileEntry.getFileVersion(), null, StringPool.BLANK,
				false, true));
		jsonObject.put("message", payloadJSONObject.getString("message"));
		jsonObject.put("type", payloadJSONObject.getString("type"));

		jsonObject.put(
			AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES,
			payloadJSONObject.getString(
				AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES));
		jsonObject.put(
			AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO,
			payloadJSONObject.getJSONArray(
				AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO));

		return JSONFactoryUtil.looseSerialize(jsonObject);
	}

}