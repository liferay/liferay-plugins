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

import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import java.util.LinkedHashSet;

/**
 * @author Matthew Kong
 */
public class DLAssetEntrySetHandler extends BaseAssetEntrySetHandler {

	public DLAssetEntrySetHandler(String portletId) {
		super(portletId);
	}

	@Override
	public JSONObject interpret(JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAssetTagNames(
			StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES)));

		JSONObject jsonObject = super.interpret(payloadJSONObject);

		LinkedHashSet<Long> assetEntryIds = new LinkedHashSet<Long>();

		assetEntryIds.addAll(
			ListUtil.toList(
				StringUtil.split(
					payloadJSONObject.getString("assetEntryIds"), 0L)));

		JSONArray jsonArray = payloadJSONObject.getJSONArray("imageData");

		jsonObject.put("imageData", jsonArray);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject imageJSONObject = jsonArray.getJSONObject(i);

			for (long fileEntryId :
					StringUtil.split(
						imageJSONObject.getString("fileEntryIds"), 0L)) {

				DLFileEntry fileEntry =
					DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);

				fileEntry.setClassNameId(
					PortalUtil.getClassNameId(AssetEntrySet.class));

				fileEntry.setClassPK(
					imageJSONObject.getLong("assetEntrySetId"));

				fileEntry = DLFileEntryLocalServiceUtil.updateDLFileEntry(
					fileEntry);

				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
					DLFileEntry.class.getName(),
					GetterUtil.getLong(fileEntryId));

				assetEntryIds.add(assetEntry.getEntryId());

				DLAppLocalServiceUtil.updateFileEntry(
					fileEntry.getUserId(), fileEntryId, fileEntry.getTitle(),
					fileEntry.getMimeType(), fileEntry.getTitle(),
					fileEntry.getDescription(), StringPool.BLANK, false,
					fileEntry.getContentStream(), fileEntry.getSize(),
					serviceContext);
			}

			jsonObject.put(
				"payloadJSONObject",
				ArrayUtil.toString(assetEntryIds.toArray(), StringPool.BLANK));
		}

		return jsonObject;
	}

}