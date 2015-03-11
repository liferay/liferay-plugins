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

import com.liferay.asset.entry.set.model.AssetEntrySetLike;
import com.liferay.asset.entry.set.service.base.AssetEntrySetLikeLocalServiceBaseImpl;
import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.asset.entry.set.util.AssetEntrySetParticipantInfoUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ObjectValuePair;

import java.util.List;

/**
 * @author Sherry Yang
 */
public class AssetEntrySetLikeLocalServiceImpl
	extends AssetEntrySetLikeLocalServiceBaseImpl {

	@Override
	public JSONArray getLikedParticipantFullNames(
			long userId, long assetEntrySetId, int start, int end)
		throws PortalException, SystemException {

		JSONArray likedParticipantFullNamesJSONArray =
			JSONFactoryUtil.createJSONArray();

		ObjectValuePair<Long, Long> classNameIdAndClassPKOVP =
			AssetEntrySetParticipantInfoUtil.getClassNameIdAndClassPKOVP(
				userId);

		List<AssetEntrySetLike> assetEntrySetLikes =
			assetEntrySetLikeFinder.findByAESI_NotC_C(
				assetEntrySetId, classNameIdAndClassPKOVP.getKey(),
				classNameIdAndClassPKOVP.getValue(), start, end);

		for (AssetEntrySetLike assetEntrySetLike : assetEntrySetLikes) {
			JSONObject participantJSONObject =
				JSONFactoryUtil.createJSONObject();

			AssetEntrySetParticipantInfoUtil.getParticipantJSONObject(
				participantJSONObject, assetEntrySetLike.getClassNameId(),
				assetEntrySetLike.getClassPK(), false);

			String participantFullName = participantJSONObject.getString(
				AssetEntrySetConstants.ASSET_ENTRY_KEY_PARTICIPANT_FULL_NAME);

			likedParticipantFullNamesJSONArray.put(participantFullName);
		}

		return likedParticipantFullNamesJSONArray;
	}

}