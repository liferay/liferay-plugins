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

import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.asset.entry.set.service.base.AssetEntrySetLocalServiceBaseImpl;
import com.liferay.asset.entry.set.util.AssetEntrySetConstants;
import com.liferay.asset.entry.set.util.AssetEntrySetManagerUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.ratings.model.RatingsStats;

import java.util.Date;
import java.util.List;

/**
 * @author Calvin Keum
 */
public class AssetEntrySetLocalServiceImpl
	extends AssetEntrySetLocalServiceBaseImpl {

	@Override
	public AssetEntrySet addAssetEntrySet(
			long userId, JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		return addAssetEntrySet(userId, 0, payloadJSONObject);
	}

	@Override
	public AssetEntrySet addAssetEntrySet(
			long userId, long parentAssetEntrySetId,
			JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		return addAssetEntrySet(
			userId, parentAssetEntrySetId,
			classNameLocalService.getClassNameId(User.class), userId,
			payloadJSONObject);
	}

	@Override
	public AssetEntrySet addAssetEntrySet(
			long userId, long parentAssetEntrySetId, long creatorClassNameId,
			long creatorClassPK, JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		long assetEntrySetId = counterLocalService.increment();

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.create(
			assetEntrySetId);

		User user = userPersistence.findByPrimaryKey(userId);

		assetEntrySet.setCompanyId(user.getCompanyId());
		assetEntrySet.setUserId(user.getUserId());

		Date now = new Date();

		assetEntrySet.setCreateTime(now.getTime());
		assetEntrySet.setModifiedTime(now.getTime());

		assetEntrySet.setParentAssetEntrySetId(parentAssetEntrySetId);
		assetEntrySet.setCreatorClassNameId(creatorClassNameId);
		assetEntrySet.setCreatorClassPK(creatorClassPK);
		assetEntrySet.setPayload(
			AssetEntrySetManagerUtil.interpret(payloadJSONObject));

		assetEntrySetPersistence.update(assetEntrySet);

		setCreatorJSONObject(assetEntrySet);

		updateChildAssetEntrySetsCount(parentAssetEntrySetId);

		updateAssetEntry(
			assetEntrySet,
			StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES)));

		return assetEntrySet;
	}

	@Override
	public AssetEntrySet deleteAssetEntrySet(AssetEntrySet assetEntrySet)
		throws PortalException, SystemException {

		assetEntrySetPersistence.remove(assetEntrySet);

		assetEntryLocalService.deleteEntry(
			AssetEntrySet.class.getName(), assetEntrySet.getAssetEntrySetId());

		return assetEntrySet;
	}

	@Override
	public AssetEntrySet deleteAssetEntrySet(long assetEntrySetId)
		throws PortalException, SystemException {

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			assetEntrySetId);

		return deleteAssetEntrySet(assetEntrySet);
	}

	@Override
	public List<AssetEntrySet> getAssetEntrySets(
			long parentAssetEntrySetId, long lastAccessTime, int start, int end)
		throws PortalException, SystemException {

		List<AssetEntrySet> assetEntrySets =
			assetEntrySetPersistence.findByCT_PASEI(
				lastAccessTime, parentAssetEntrySetId, start, end);

		setCreatorJSONObjects(assetEntrySets);

		return assetEntrySets;
	}

	@Override
	public List<AssetEntrySet> getAssetEntrySets(
			long creatorClassNameId, long creatorClassPK, String assetTagName,
			boolean andOperator, int start, int end)
		throws PortalException, SystemException {

		List<AssetEntrySet> assetEntrySets =
			assetEntrySetFinder.findByCCNI_CCPK_ATN(
				creatorClassNameId, creatorClassPK, assetTagName, andOperator,
				start, end);

		setCreatorJSONObjects(assetEntrySets);

		return assetEntrySets;
	}

	@Override
	public List<AssetEntrySet> getAssetEntrySets(
			long creatorClassNameId, String assetTagName, int start, int end)
		throws PortalException, SystemException {

		List<AssetEntrySet> assetEntrySets = assetEntrySetFinder.findByCCNI_ATN(
			creatorClassNameId, assetTagName, start, end);

		setCreatorJSONObjects(assetEntrySets);

		return assetEntrySets;
	}

	@Override
	public int getAssetEntrySetsCount(
			long creatorClassNameId, long creatorClassPK, String assetTagName,
			boolean andOperator)
		throws SystemException {

		return assetEntrySetFinder.countByCCNI_CCPK_ATN(
			creatorClassNameId, creatorClassPK, assetTagName, andOperator);
	}

	@Override
	public int getAssetEntrySetsCount(
			long creatorClassNameId, String assetTagName)
		throws SystemException {

		return assetEntrySetFinder.countByCCNI_ATN(
			creatorClassNameId, assetTagName);
	}

	@Override
	public List<AssetEntrySet> getChildAssetEntrySets(
			long parentAssetEntrySetId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return assetEntrySetPersistence.findByParentAssetEntrySetId(
			parentAssetEntrySetId, start, end, orderByComparator);
	}

	@Override
	public AssetEntrySet likeAssetEntrySet(long userId, long assetEntrySetId)
		throws PortalException, SystemException {

		return updateRatingsStatsTotalScore(userId, assetEntrySetId, 1);
	}

	@Override
	public AssetEntrySet unlikeAssetEntrySet(long userId, long assetEntrySetId)
		throws PortalException, SystemException {

		return updateRatingsStatsTotalScore(userId, assetEntrySetId, 0);
	}

	@Override
	public void updateAssetEntry(long assetEntrySetId, String[] assetTagNames)
		throws PortalException, SystemException {

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			assetEntrySetId);

		updateAssetEntry(assetEntrySet, assetTagNames);
	}

	@Override
	public AssetEntrySet updateAssetEntrySet(
			long assetEntrySetId, JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			assetEntrySetId);

		Date now = new Date();

		assetEntrySet.setModifiedTime(now.getTime());

		assetEntrySet.setPayload(
			AssetEntrySetManagerUtil.interpret(payloadJSONObject));

		assetEntrySetPersistence.update(assetEntrySet);

		setCreatorJSONObject(assetEntrySet);

		updateAssetEntry(
			assetEntrySet,
			StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES)));

		return assetEntrySet;
	}

	@Override
	public AssetEntrySet updateChildAssetEntrySetsCount(
			long parentAssetEntrySetId)
		throws PortalException, SystemException {

		if (parentAssetEntrySetId == 0) {
			return null;
		}

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			parentAssetEntrySetId);

		int childAssetEntrySetsCount =
			assetEntrySetPersistence.countByParentAssetEntrySetId(
				parentAssetEntrySetId);

		assetEntrySet.setChildAssetEntrySetsCount(childAssetEntrySetsCount);

		assetEntrySetPersistence.update(assetEntrySet);

		return assetEntrySet;
	}

	protected void setCreatorJSONObject(AssetEntrySet assetEntrySet)
		throws PortalException, SystemException {

		JSONObject creatorJSONObject = JSONFactoryUtil.createJSONObject();

		String creatorFullName = StringPool.BLANK;
		String creatorPortraitURL = StringPool.BLANK;
		String creatorURL = StringPool.BLANK;

		if (assetEntrySet.getCreatorClassNameId() ==
				classNameLocalService.getClassNameId(User.class)) {

			User user = userPersistence.findByPrimaryKey(
				assetEntrySet.getUserId());

			creatorFullName = user.getFullName();

			creatorPortraitURL = UserConstants.getPortraitURL(
				PortalUtil.getPathImage(), user.isMale(), user.getPortraitId());

			Group group = user.getGroup();

			creatorURL = PropsUtil.get(
				PropsKeys.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING);

			creatorURL += group.getFriendlyURL();
		}
		else {
			AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
				PortalUtil.getClassName(assetEntrySet.getCreatorClassNameId()),
				assetEntrySet.getCreatorClassPK());

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				assetEntry.getDescription());

			creatorFullName = jsonObject.getString(
				AssetEntrySetConstants.ASSET_ENTRY_KEY_CREATOR_FULL_NAME);
			creatorPortraitURL = jsonObject.getString(
				AssetEntrySetConstants.ASSET_ENTRY_KEY_CREATOR_PORTRAIT_URL);
			creatorURL = jsonObject.getString(
				AssetEntrySetConstants.ASSET_ENTRY_KEY_CREATOR_URL);
		}

		creatorJSONObject.put(
			AssetEntrySetConstants.ASSET_ENTRY_KEY_CREATOR_FULL_NAME,
			creatorFullName);
		creatorJSONObject.put(
			AssetEntrySetConstants.ASSET_ENTRY_KEY_CREATOR_PORTRAIT_URL,
			creatorPortraitURL);
		creatorJSONObject.put(
			AssetEntrySetConstants.ASSET_ENTRY_KEY_CREATOR_URL, creatorURL);

		assetEntrySet.setCreator(creatorJSONObject);
	}

	protected void setCreatorJSONObjects(List<AssetEntrySet> assetEntrySets)
		throws PortalException, SystemException {

		for (AssetEntrySet assetEntrySet : assetEntrySets) {
			setCreatorJSONObject(assetEntrySet);
		}
	}

	protected void updateAssetEntry(
			AssetEntrySet assetEntrySet, String[] assetTagNames)
		throws PortalException, SystemException {

		Group group = groupLocalService.getCompanyGroup(
			assetEntrySet.getCompanyId());

		assetEntryLocalService.updateEntry(
			assetEntrySet.getUserId(), group.getGroupId(),
			AssetEntrySet.class.getName(), assetEntrySet.getAssetEntrySetId(),
			null, assetTagNames);
	}

	protected AssetEntrySet updateRatingsStatsTotalScore(
			long userId, long assetEntrySetId, long score)
		throws PortalException, SystemException {

		String className = AssetEntrySet.class.getName();

		ratingsEntryLocalService.updateEntry(
			userId, className, assetEntrySetId, score, new ServiceContext());

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			assetEntrySetId);

		RatingsStats ratingsStats = ratingsStatsLocalService.getStats(
			className, assetEntrySetId);

		assetEntrySet.setRatingsStatsTotalScore(
			(int)ratingsStats.getTotalScore());

		assetEntrySetPersistence.update(assetEntrySet);

		return assetEntrySet;
	}

}