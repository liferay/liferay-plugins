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
import com.liferay.asset.entry.set.participant.AssetEntrySetParticipantInfoUtil;
import com.liferay.asset.entry.set.service.AssetEntrySetLikeLocalServiceUtil;
import com.liferay.asset.entry.set.service.base.AssetEntrySetLikeLocalServiceBaseImpl;
import com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK;
import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ObjectValuePair;

import java.util.ArrayList;
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

		List<AssetEntrySetLike> unmodifiableAssetEntrySetLikes =
			assetEntrySetLikePersistence.findByAssetEntrySetId(
				assetEntrySetId, start, end + 1);

		if (ListUtil.isEmpty(unmodifiableAssetEntrySetLikes)) {
			return likedParticipantFullNamesJSONArray;
		}

		List<AssetEntrySetLike> assetEntrySetLikes =
			new ArrayList<AssetEntrySetLike>(unmodifiableAssetEntrySetLikes);

		ObjectValuePair<Long, Long> classNameIdAndClassPKOVP =
			AssetEntrySetParticipantInfoUtil.getClassNameIdAndClassPKOVP(
				userId);

		AssetEntrySetLikePK assetEntrySetLikePK = new AssetEntrySetLikePK(
			assetEntrySetId, classNameIdAndClassPKOVP.getKey(),
			classNameIdAndClassPKOVP.getValue());

		AssetEntrySetLike participantAssetEntrySetLike =
			AssetEntrySetLikeLocalServiceUtil.fetchAssetEntrySetLike(
				assetEntrySetLikePK);

		if (assetEntrySetLikes.contains(participantAssetEntrySetLike)) {
			assetEntrySetLikes.remove(participantAssetEntrySetLike);
		}
		else {
			assetEntrySetLikes.remove(assetEntrySetLikes.size() -1);
		}

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