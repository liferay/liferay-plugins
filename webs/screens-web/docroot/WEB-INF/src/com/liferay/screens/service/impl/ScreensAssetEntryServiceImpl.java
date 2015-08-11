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

package com.liferay.screens.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.model.PortletItem;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;
import com.liferay.portlet.assetpublisher.util.AssetPublisherUtil;
import com.liferay.screens.service.base.ScreensAssetEntryServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletPreferences;

/**
 * @author José Manuel Navarro
 */
public class ScreensAssetEntryServiceImpl
	extends ScreensAssetEntryServiceBaseImpl {

	@Override
	public JSONArray getAssetEntries(
		AssetEntryQuery assetEntryQuery, Locale locale)
		throws PortalException, SystemException {

		List<AssetEntry> assetEntries = assetEntryLocalService.getEntries(
			assetEntryQuery);

		JSONArray assetEntriesJSONArray = convertToJSONArray(locale, assetEntries);

		return assetEntriesJSONArray;
	}

	public JSONArray getFilteredAssetEntries(long companyId, long groupId, String portletItemName, Locale locale)
		throws PortalException, SystemException {

		List<AssetEntry> assetEntries = new ArrayList<AssetEntry>();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PortletItem.class)
			.add(PropertyFactoryUtil.forName("name").eq(portletItemName));
		dynamicQuery.setLimit(0, 1);

		List<PortletItem> items = portletItemLocalService.dynamicQuery(dynamicQuery);

		if (!items.isEmpty())

		{
			PortletItem portletItem = items.get(0);

			PortletPreferences portletPreferences = portletPreferencesLocalService.getPreferences(
				portletItem.getCompanyId(), portletItem.getPortletItemId(), PortletKeys.PREFS_OWNER_TYPE_ARCHIVED, 0, portletItem.getPortletId());

			String selectionStyle = GetterUtil.getString(portletPreferences.getValue("selectionStyle", null), "dynamic");

			if (selectionStyle.equals("dynamic")) {
				assetEntries = AssetPublisherUtil.getAssetEntries(portletPreferences, null, groupId, Integer.MAX_VALUE, false);
			}
			else {
				MockPermissionChecker permissionChecker = new MockPermissionChecker(companyId);
				try {
					assetEntries = AssetPublisherUtil.getAssetEntries(null, portletPreferences, permissionChecker, new long[]{groupId},
						portletPreferences.getValues("assetEntryXml", new String[0]), false, false);
				}
				catch (Exception e) {
					throw new PortalException(e);
				}
			}
		}

		return convertToJSONArray(locale, assetEntries);
	}

	private JSONArray convertToJSONArray(Locale locale, List<AssetEntry> assetEntries) throws JSONException {
		JSONArray assetEntriesJSONArray = JSONFactoryUtil.createJSONArray();

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