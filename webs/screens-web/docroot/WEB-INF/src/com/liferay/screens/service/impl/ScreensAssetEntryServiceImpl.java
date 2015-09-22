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
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.PortletItem;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
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

		return toJSONArray(assetEntries, locale);
	}

	public JSONArray getFilteredAssetEntries(
			long companyId, long groupId, String portletItemName, Locale locale)
		throws PortalException, SystemException {

		List<AssetEntry> assetEntries = new ArrayList<AssetEntry>();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			PortletItem.class);

		Property property = PropertyFactoryUtil.forName("name");

		dynamicQuery.add(property.eq(portletItemName));

		dynamicQuery.setLimit(0, 1);

		List<PortletItem> portletItems = portletItemLocalService.dynamicQuery(
			dynamicQuery);

		if (portletItems.isEmpty()) {
			return toJSONArray(assetEntries, locale);
		}

		PortletItem portletItem = portletItems.get(0);

		PortletPreferences portletPreferences =
			portletPreferencesLocalService.getPreferences(
				portletItem.getCompanyId(), portletItem.getPortletItemId(),
				PortletKeys.PREFS_OWNER_TYPE_ARCHIVED, 0,
				portletItem.getPortletId());

		String selectionStyle = GetterUtil.getString(
			portletPreferences.getValue("selectionStyle", null), "dynamic");

		if (selectionStyle.equals("dynamic")) {
			assetEntries = AssetPublisherUtil.getAssetEntries(
				portletPreferences, null, groupId, Integer.MAX_VALUE, false);
		}
		else {
			try {
				PermissionChecker permissionChecker =
					PermissionCheckerFactoryUtil.create(getUser());

				assetEntries = AssetPublisherUtil.getAssetEntries(
					null, portletPreferences, permissionChecker,
					new long[] {groupId},
					portletPreferences.getValues(
						"assetEntryXml", new String[0]),
					false, false);
			}
			catch (Exception e) {
				throw new PortalException(e);
			}
		}

		return toJSONArray(assetEntries, locale);
	}

	protected JSONArray toJSONArray(
			List<AssetEntry> assetEntries, Locale locale)
		throws JSONException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (AssetEntry assetEntry : assetEntries) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(assetEntry));

			jsonObject.put("description", assetEntry.getDescription(locale));
			jsonObject.put("summary", assetEntry.getSummary(locale));
			jsonObject.put("title", assetEntry.getTitle(locale));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

}