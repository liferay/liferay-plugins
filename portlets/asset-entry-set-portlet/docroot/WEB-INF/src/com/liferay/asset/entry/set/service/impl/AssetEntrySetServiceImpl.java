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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.io.File;

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
	public JSONObject addFileAttachment(File file)
		throws PortalException, SystemException {

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
	public AssetEntrySet getAssetEntrySet(
			long assetEntrySetId, int childAssetEntrySetsLimit,
			int likedParticipantsLimit)
		throws PortalException, SystemException {

		AssetEntrySetPermissionUtil.check(
			getPermissionChecker(), assetEntrySetId, ActionKeys.VIEW);

		return assetEntrySetLocalService.getAssetEntrySet(
			getUserId(), assetEntrySetId, childAssetEntrySetsLimit,
			likedParticipantsLimit);
	}

	@Override
	public List<AssetEntrySet> getNewAssetEntrySets(
			long createTime, long parentAssetEntrySetId, String sharedTo,
			String[] assetTagNames, int childAssetEntrySetsLimit,
			int likedParticipantsLimit, int start, int end)
		throws PortalException, SystemException {

		JSONArray sharedToJSONArray = JSONFactoryUtil.createJSONArray(sharedTo);

		return assetEntrySetLocalService.getNewAssetEntrySets(
			getUserId(), createTime, parentAssetEntrySetId, sharedToJSONArray,
			assetTagNames, childAssetEntrySetsLimit, likedParticipantsLimit,
			start, end);
	}

	@Override
	public List<AssetEntrySet> getOldAssetEntrySets(
			long createTime, long parentAssetEntrySetId, String sharedTo,
			String[] assetTagNames, int childAssetEntrySetsLimit,
			int likedParticipantsLimit, int start, int end)
		throws PortalException, SystemException {

		JSONArray sharedToJSONArray = JSONFactoryUtil.createJSONArray(sharedTo);

		return assetEntrySetLocalService.getOldAssetEntrySets(
			getUserId(), createTime, parentAssetEntrySetId, sharedToJSONArray,
			assetTagNames, childAssetEntrySetsLimit, likedParticipantsLimit,
			start, end);
	}

	@Override
	public AssetEntrySet likeAssetEntrySet(
			long assetEntrySetId, int childAssetEntrySetsLimit,
			int likedParticipantsLimit)
		throws PortalException, SystemException {

		return assetEntrySetLocalService.likeAssetEntrySet(
			getUserId(), assetEntrySetId, childAssetEntrySetsLimit,
			likedParticipantsLimit);
	}

	@Override
	public AssetEntrySet unlikeAssetEntrySet(
			long assetEntrySetId, int childAssetEntrySetsLimit,
			int likedParticipantsLimit)
		throws PortalException, SystemException {

		return assetEntrySetLocalService.unlikeAssetEntrySet(
			getUserId(), assetEntrySetId, childAssetEntrySetsLimit,
			likedParticipantsLimit);
	}

	@Override
	public AssetEntrySet updateAssetEntrySet(
			long assetEntrySetId, String payload, boolean privateAssetEntrySet)
		throws PortalException, SystemException {

		AssetEntrySetPermissionUtil.check(
			getPermissionChecker(), assetEntrySetId, ActionKeys.UPDATE);

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			payload);

		return assetEntrySetLocalService.updateAssetEntrySet(
			assetEntrySetId, payloadJSONObject, privateAssetEntrySet);
	}

}