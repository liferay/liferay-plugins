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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

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
	public String interpret(JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("message", payloadJSONObject.getString("message"));
		jsonObject.put("type", payloadJSONObject.getString("type"));
		jsonObject.put("url", payloadJSONObject.getString("url"));

		return JSONFactoryUtil.looseSerialize(jsonObject);
	}

	protected void setPortletId(String portletId) {
		_portletId = portletId;
	}

	private String _portletId;

}