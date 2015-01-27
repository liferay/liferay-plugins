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
import com.liferay.asset.entry.set.service.permission.AssetEntrySetPermissionUtil;
import com.liferay.asset.entry.set.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;

import java.io.File;
import java.io.IOException;

import java.util.List;

/**
 * @author Matthew Kong
 */
@ProviderType
public class AssetEntrySetServiceImpl extends AssetEntrySetServiceBaseImpl {

	@Override
	public AssetEntrySet addAssetEntrySet(
			long parentAssetEntrySetId, long creatorClassNameId,
			long creatorClassPK, String payload, boolean privateAssetEntrySet)
		throws PortalException, SystemException {

		AssetEntrySetPermissionUtil.check(
			getPermissionChecker(), creatorClassNameId, creatorClassPK,
			ActionKeys.ADD_ASSET_ENTRY_SET);

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			payload);

		return assetEntrySetLocalService.addAssetEntrySet(
			getUserId(), parentAssetEntrySetId, creatorClassNameId,
			creatorClassPK, payloadJSONObject, privateAssetEntrySet);
	}

	@Override
	public AssetEntrySet addAssetEntrySet(
			long parentAssetEntrySetId, String payload,
			boolean privateAssetEntrySet)
		throws PortalException, SystemException {

		AssetEntrySetPermissionUtil.check(
			getPermissionChecker(), _CLASS_NAME_ID_USER, getUserId(),
			ActionKeys.ADD_ASSET_ENTRY_SET);

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			payload);

		return assetEntrySetLocalService.addAssetEntrySet(
			getUserId(), parentAssetEntrySetId, payloadJSONObject,
			privateAssetEntrySet);
	}

	@Override
	public AssetEntrySet addAssetEntrySet(
			String payload, boolean privateAssetEntrySet)
		throws PortalException, SystemException {

		AssetEntrySetPermissionUtil.check(
			getPermissionChecker(), _CLASS_NAME_ID_USER, getUserId(),
			ActionKeys.ADD_ASSET_ENTRY_SET);

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			payload);

		return assetEntrySetLocalService.addAssetEntrySet(
			getUserId(), payloadJSONObject, privateAssetEntrySet);
	}

	@Override
	public JSONObject addFileAttachment(File file)
		throws IOException, PortalException, SystemException {

		return assetEntrySetLocalService.addFileAttachment(getUserId(), file);
	}

	@Override
	public AssetEntrySet deleteAssetEntrySet(long assetEntrySetId)
		throws PortalException, SystemException {

		AssetEntrySetPermissionUtil.check(
			getPermissionChecker(), assetEntrySetId, ActionKeys.DELETE);

		return assetEntrySetLocalService.deleteAssetEntrySet(assetEntrySetId);
	}

	@Override
	public List<AssetEntrySet> getNewAssetEntrySets(
			long createTime, long parentAssetEntrySetId,
			int childAssetEntrySetsLimit, int start, int end)
		throws PortalException, SystemException {

		return assetEntrySetLocalService.getNewAssetEntrySets(
			getUserId(), createTime, parentAssetEntrySetId,
			childAssetEntrySetsLimit, start, end);
	}

	@Override
	public List<AssetEntrySet> getOldAssetEntrySets(
			long createTime, long parentAssetEntrySetId,
			int childAssetEntrySetsLimit, int start, int end)
		throws PortalException, SystemException {

		return assetEntrySetLocalService.getOldAssetEntrySets(
			getUserId(), createTime, parentAssetEntrySetId,
			childAssetEntrySetsLimit, start, end);
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

	private static final long _CLASS_NAME_ID_USER =
		ClassNameLocalServiceUtil.getClassNameId(User.class);

}