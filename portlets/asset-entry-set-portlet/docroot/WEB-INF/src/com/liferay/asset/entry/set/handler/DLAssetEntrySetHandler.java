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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthew Kong
 */
public class DLAssetEntrySetHandler extends BaseAssetEntrySetHandler {

	public DLAssetEntrySetHandler(String portletId) {
		super(portletId);
	}

	@Override
	public String interpret(JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		List<Long> assetEntryIds = new ArrayList<Long>();

		long userId = payloadJSONObject.getLong("userId");
		long repositoryId = payloadJSONObject.getLong("repositoryId");
		long folderId = payloadJSONObject.getLong("folderId");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAssetTagNames(
			StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES)));

		JSONArray filesJSONArray = payloadJSONObject.getJSONArray("files");

		for (int i = 0; i < filesJSONArray.length(); i++) {
			JSONObject fileJSONObject = filesJSONArray.getJSONObject(i);

			FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
				userId, repositoryId, folderId,
				fileJSONObject.getString("fileName"), null,
				fileJSONObject.getString("title"),
				fileJSONObject.getString("description"),
				fileJSONObject.getString("changeLog"),
				Base64.decode(fileJSONObject.getString("file")),
				serviceContext);

			AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
				DLFileEntry.class.getName(), fileEntry.getFileEntryId());

			assetEntryIds.add(assetEntry.getEntryId());
		}

		jsonObject.put("assetEntryIds", StringUtil.merge(assetEntryIds));

		jsonObject.put("message", payloadJSONObject.getString("message"));
		jsonObject.put("type", payloadJSONObject.getString("type"));
		jsonObject.put("url", payloadJSONObject.getString("url"));

		jsonObject.put(
			AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO,
			payloadJSONObject.getJSONArray(
				AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO));

		return JSONFactoryUtil.looseSerialize(jsonObject);
	}

}