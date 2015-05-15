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

package com.liferay.asset.entry.set.util;

import com.liferay.asset.entry.set.handler.AssetEntrySetHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Calvin Keum
 */
public class AssetEntrySetManagerUtil {

	public static void addAssetEntrySetHandler(
		AssetEntrySetHandler assetEntrySetHandler) {

		String portletId = assetEntrySetHandler.getPortletId();

		AssetEntrySetHandler curAssetEntrySetHandler =
			_assetEntrySetHandlers.get(portletId);

		if (curAssetEntrySetHandler != null) {
			return;
		}

		_assetEntrySetHandlers.put(portletId, assetEntrySetHandler);
	}

	public static void addAssetEntrySetPortletId(
		String type, String portletId) {

		String curPortletId = _assetEntrySetPortletIds.get(type);

		if (Validator.isNotNull(curPortletId)) {
			return;
		}

		_assetEntrySetPortletIds.put(type, portletId);
	}

	public static void deleteAssetEntrySetHandler(
		AssetEntrySetHandler assetEntrySetHandler) {

		String portletId = assetEntrySetHandler.getPortletId();

		assetEntrySetHandler = _assetEntrySetHandlers.get(portletId);

		if (assetEntrySetHandler == null) {
			return;
		}

		_assetEntrySetHandlers.remove(assetEntrySetHandler);
	}

	public static void deleteAssetEntrySetPortletId(String type) {
		String portletId = _assetEntrySetPortletIds.get(type);

		if (Validator.isNull(portletId)) {
			return;
		}

		_assetEntrySetPortletIds.remove(type);
	}

	public static Map<String, AssetEntrySetHandler> getAssetEntrySetHandlers() {
		return _assetEntrySetHandlers;
	}

	public static AssetEntrySetHandler getAssetEntrySetHandlers(
		long portletId) {

		return _assetEntrySetHandlers.get(portletId);
	}

	public static JSONObject interpret(
			long userId, long assetEntrySetId, JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		String type = payloadJSONObject.getString("type");

		String portletId = _assetEntrySetPortletIds.get(type);

		AssetEntrySetHandler assetEntrySetHandler = _assetEntrySetHandlers.get(
			portletId);

		if (assetEntrySetHandler == null) {
			return null;
		}

		return assetEntrySetHandler.interpret(
			userId, assetEntrySetId, payloadJSONObject);
	}

	private static Map<String, AssetEntrySetHandler> _assetEntrySetHandlers =
		new HashMap<String, AssetEntrySetHandler>();
	private static Map<String, String> _assetEntrySetPortletIds =
		new HashMap<String, String>();

}