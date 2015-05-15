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

package com.liferay.mobilewidgets.service.impl;

import com.liferay.mobilewidgets.service.base.MobileWidgetsAssetEntryServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;

import java.util.List;
import java.util.Locale;

/**
 * @author José Manuel Navarro
 */
public class MobileWidgetsAssetEntryServiceImpl
	extends MobileWidgetsAssetEntryServiceBaseImpl {

	@Override
	public JSONArray getAssetEntries(
			AssetEntryQuery assetEntryQuery, Locale locale)
		throws PortalException, SystemException {

		JSONArray assetEntriesJSONArray = JSONFactoryUtil.createJSONArray();

		List<AssetEntry> assetEntries = assetEntryLocalService.getEntries(
			assetEntryQuery);

		for (AssetEntry assetEntry : assetEntries) {
			JSONObject assetEntryJSONObject = JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(assetEntry));

			assetEntryJSONObject.put(
				"description", assetEntry.getDescription(locale));
			assetEntryJSONObject.put("summary", assetEntry.getSummary(locale));
			assetEntryJSONObject.put("title", assetEntry.getTitle(locale));

			assetEntriesJSONArray.put(assetEntryJSONObject);
		}

		return assetEntriesJSONArray;
	}

}