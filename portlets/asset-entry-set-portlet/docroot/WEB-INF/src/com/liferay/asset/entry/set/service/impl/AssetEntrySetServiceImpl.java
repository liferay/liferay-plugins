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

package com.liferay.asset.entry.set.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.asset.entry.set.service.base.AssetEntrySetServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

/**
 * @author Matthew Kong
 */
@ProviderType
public class AssetEntrySetServiceImpl extends AssetEntrySetServiceBaseImpl {

	@Override
	public AssetEntrySet addAssetEntrySet(JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		return assetEntrySetLocalService.addAssetEntrySet(
			getUserId(), payloadJSONObject);
	}

	@Override
	public AssetEntrySet addAssetEntrySet(
			long parentAssetEntrySetId, JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		return assetEntrySetLocalService.addAssetEntrySet(
			getUserId(), parentAssetEntrySetId, payloadJSONObject);
	}

	@Override
	public List<AssetEntrySet> getAssetEntrySets(
			long assetEntrySetId, long lastAccessTime, int start, int end)
		throws SystemException {

		return assetEntrySetLocalService.getAssetEntrySets(
			assetEntrySetId, lastAccessTime, start, end);
	}

	@Override
	public AssetEntrySet likeAssetEntrySet(long assetEntrySetId)
		throws PortalException, SystemException {

		return assetEntrySetLocalService.likeAssetEntrySet(
			getUserId(), assetEntrySetId);
	}

	@Override
	public AssetEntrySet unlikeAssetEntrySet(long assetEntrySetId)
		throws PortalException, SystemException {

		return assetEntrySetLocalService.unlikeAssetEntrySet(
			getUserId(), assetEntrySetId);
	}

}