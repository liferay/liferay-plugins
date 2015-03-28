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
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Matthew Kong
 */
public class DLAssetEntrySetHandler extends BaseAssetEntrySetHandler {

	public DLAssetEntrySetHandler(String portletId) {
		super(portletId);
	}

	@Override
	public JSONObject interpret(
			JSONObject payloadJSONObject, long assetEntrySetId)
		throws PortalException, SystemException {

		JSONObject jsonObject = super.interpret(
			payloadJSONObject, assetEntrySetId);

		Set<Long> assetEntryIds = new HashSet<Long>();

		String[] assetTagNames = StringUtil.split(
			payloadJSONObject.getString(
				AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES));

		JSONArray jsonArray = payloadJSONObject.getJSONArray("imageData");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject imageJSONObject = jsonArray.getJSONObject(i);

			for (long fileEntryId :
					StringUtil.split(
						imageJSONObject.getString("fileEntryIds"), 0L)) {

				DLFileEntry dlFileEntry =
					DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);

				dlFileEntry.setClassPK(assetEntrySetId);

				dlFileEntry = DLFileEntryLocalServiceUtil.updateDLFileEntry(
					dlFileEntry);

				AssetEntry assetEntry = updateAssetEntry(
					dlFileEntry, assetTagNames);

				assetEntryIds.add(assetEntry.getEntryId());
			}
		}

		jsonObject.put("assetEntryIds", StringUtil.merge(assetEntryIds));

		jsonObject.put("imageData", jsonArray);

		return jsonObject;
	}

	protected AssetEntry updateAssetEntry(
			DLFileEntry dlFileEntry, String[] assetTagNames)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getCompanyGroup(
			dlFileEntry.getCompanyId());

		return AssetEntryLocalServiceUtil.updateEntry(
			dlFileEntry.getUserId(), group.getGroupId(),
			AssetEntrySet.class.getName(), dlFileEntry.getFileEntryId(), null,
			assetTagNames);
	}

}