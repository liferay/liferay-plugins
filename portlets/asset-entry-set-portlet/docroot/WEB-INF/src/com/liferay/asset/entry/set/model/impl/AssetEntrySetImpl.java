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

package com.liferay.asset.entry.set.model.impl;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetEntrySetImpl extends AssetEntrySetBaseImpl {

	public AssetEntrySetImpl() {
	}

	@JSON
	@Override
	public JSONObject getCreator() {
		return _creatorJSONObject;
	}

	@Override
	public void setCreator(JSONObject creatorJSONObject) {
		_creatorJSONObject = creatorJSONObject;
	}

	private JSONObject _creatorJSONObject;

}