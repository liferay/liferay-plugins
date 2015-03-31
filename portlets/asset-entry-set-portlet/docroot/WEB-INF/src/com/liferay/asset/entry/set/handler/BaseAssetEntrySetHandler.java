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
import com.liferay.asset.entry.set.service.AssetEntrySetLocalServiceUtil;
import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;

/**
 * @author Calvin Keum
 */
public class BaseAssetEntrySetHandler implements AssetEntrySetHandler {

	public BaseAssetEntrySetHandler(String portletId) {
		_portletId = portletId;
	}

	@Override
	public String getPortletId() {
		return _portletId;
	}

	@Override
	public JSONObject interpret(
			JSONObject payloadJSONObject, long assetEntrySetId)
		throws PortalException, SystemException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		AssetEntrySet assetEntrySet =
			AssetEntrySetLocalServiceUtil.fetchAssetEntrySet(assetEntrySetId);

		if ((assetEntrySet != null) &&
			isContentModified(
				JSONFactoryUtil.createJSONObject(assetEntrySet.getPayload()),
				payloadJSONObject)) {

			jsonObject.put("contentModifiedTime", System.currentTimeMillis());
		}

		jsonObject.put("linkData", payloadJSONObject.getString("linkData"));
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

		return jsonObject;
	}

	protected boolean isContentModified(
		JSONObject oldPayloadJSONObject, JSONObject newPayloadJSONObject) {

		Iterator<String> keys = oldPayloadJSONObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			if (key.equals(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES) ||
				key.equals(AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO)) {

				continue;
			}

			String oldValue = oldPayloadJSONObject.getString(key);
			String newValue = newPayloadJSONObject.getString(key);

			if (!Validator.equals(oldValue, newValue)) {
				return true;
			}
		}

		return false;
	}

	protected void setPortletId(String portletId) {
		_portletId = portletId;
	}

	private String _portletId;

}