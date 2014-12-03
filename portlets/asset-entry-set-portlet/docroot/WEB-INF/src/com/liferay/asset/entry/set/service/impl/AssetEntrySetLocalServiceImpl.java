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
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.ratings.model.RatingsStats;

import java.util.Date;
import java.util.List;

/**
 * @author Calvin Keum
 */
public class AssetEntrySetLocalServiceImpl
	extends AssetEntrySetLocalServiceBaseImpl {

	public AssetEntrySet addAssetEntrySet(
			long userId, long creatorClassNameId, long creatorClassPK,
			JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		long assetEntrySetId = counterLocalService.increment();

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.create(
			assetEntrySetId);

		assetEntrySet.setCompanyId(user.getCompanyId());
		assetEntrySet.setUserId(user.getUserId());
		assetEntrySet.setUserName(user.getFullName());
		assetEntrySet.setCreateTime(now.getTime());
		assetEntrySet.setModifiedTime(now.getTime());

		long parentAssetEntrySetId = payloadJSONObject.getLong(
			AssetEntrySetConstants.KEY_PARENT_ASSET_ENTRY_SET_ID,
			AssetEntrySetConstants.VALUE_PARENT_ASSET_ENTRY_SET_ID_DEFAULT);

		assetEntrySet.setParentAssetEntrySetId(parentAssetEntrySetId);

		assetEntrySet.setCreatorClassNameId(creatorClassNameId);
		assetEntrySet.setCreatorClassPK(creatorClassPK);
		assetEntrySet.setPayload(payloadJSONObject.toString());

		assetEntrySetPersistence.update(assetEntrySet);

		updateChildrenAssetEntrySetCount(parentAssetEntrySetId);

		updateAsset(
			assetEntrySet,
			GetterUtil.getLongValues(
				StringUtil.split(
					payloadJSONObject.getString(
						AssetEntrySetConstants.KEY_ASSET_CATEGORY_IDS))),
			StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.KEY_ASSET_TAG_NAMES)));

		return assetEntrySet;
	}

	@Override
	public AssetEntrySet deleteAssetEntrySet(AssetEntrySet assetEntrySet)
		throws PortalException, SystemException {

		assetEntrySetPersistence.remove(assetEntrySet);

		AssetEntryLocalServiceUtil.deleteEntry(
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
	public AssetEntrySet getAssetEntrySet(long assetEntrySetId)
		throws PortalException, SystemException {

		return assetEntrySetPersistence.findByPrimaryKey(assetEntrySetId);
	}

	public AssetEntrySet getAssetEntrySet(
			long parentAssetEntrySetId, long creatorClassNameId,
			long creatorClassPK, int start, int end)
		throws PortalException, SystemException {

		return assetEntrySetPersistence.findByPAESI_CCNI_CCPK(
			parentAssetEntrySetId, creatorClassNameId, creatorClassPK);
	}

	@Override
	public List<AssetEntrySet> getAssetEntrySets(
			long parentAssetEntrySetId, long lastAccessTime, int start, int end)
		throws SystemException {

		return assetEntrySetPersistence.findByCT_PASEI(
			lastAccessTime, parentAssetEntrySetId, start, end);
	}

	public List<AssetEntrySet> getAssetEntrySets(
			long parentAssetEntrySetId, long creatorClassNameId, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		return assetEntrySetPersistence.findByPAESI_CCNI(
			parentAssetEntrySetId, creatorClassNameId, start, end, obc);
	}

	public List<AssetEntrySet> getAssetEntrySets(
			long creatorClassNameId, long creatorClassPK, String assetTagName,
			boolean andOperator, int start, int end)
		throws SystemException {

		return assetEntrySetFinder.findByCCNI_CCPK_ATN(
			creatorClassNameId, creatorClassPK, assetTagName, andOperator,
			start, end);
	}

	public List<AssetEntrySet> getAssetEntrySets(
			long creatorClassNameId, String assetTagName, int start, int end)
		throws SystemException {

		return assetEntrySetFinder.findByCCNI_ATN(
			creatorClassNameId, assetTagName, start, end);
	}

	public int getAssetEntrySetsCount(
			long parentAssetEntrySetId, long creatorClassNameId)
		throws SystemException {

		return assetEntrySetPersistence.countByPAESI_CCNI(
			parentAssetEntrySetId, creatorClassNameId);
	}

	public int getAssetEntrySetsCount(
			long creatorClassNameId, long creatorClassPK,
			long parentAssetEntrySetId)
		throws SystemException {

		return assetEntrySetPersistence.countByPAESI_CCNI_CCPK(
			parentAssetEntrySetId, creatorClassNameId, creatorClassPK);
	}

	public int getAssetEntrySetsCount(
			long creatorClassNameId, long creatorClassPK, String assetTagName,
			boolean andOperator)
		throws SystemException {

		return assetEntrySetFinder.countByCCNI_CCPK_ATN(
			creatorClassNameId, creatorClassPK, assetTagName, andOperator);
	}

	public int getAssetEntrySetsCount(
			long creatorClassNameId, String assetTagName)
		throws SystemException {

		return assetEntrySetFinder.countByCCNI_ATN(
			creatorClassNameId, assetTagName);
	}

	public List<AssetEntrySet> getParentAssetEntrySetAssetEntrySets(
			long parentAssetEntrySetId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return assetEntrySetPersistence.findByParentAssetEntrySetId(
			parentAssetEntrySetId, start, end, orderByComparator);
	}

	public int getParentAssetEntrySetAssetEntrySetsCount(
			long parentAssetEntrySetId)
		throws SystemException {

		return assetEntrySetPersistence.countByParentAssetEntrySetId(
			parentAssetEntrySetId);
	}

	@Override
	public AssetEntrySet likeAssetEntrySet(long userId, long assetEntrySetId)
		throws PortalException, SystemException {

		return updateRatingsEntry(userId, assetEntrySetId, 1);
	}

	@Override
	public AssetEntrySet unlikeAssetEntrySet(long userId, long assetEntrySetId)
		throws PortalException, SystemException {

		return updateRatingsEntry(userId, assetEntrySetId, 0);
	}

	public void updateAsset(
			AssetEntrySet assetEntrySet, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getCompanyGroup(
			assetEntrySet.getCompanyId());

		AssetEntryLocalServiceUtil.updateEntry(
			assetEntrySet.getUserId(), group.getGroupId(),
			AssetEntrySet.class.getName(), assetEntrySet.getAssetEntrySetId(),
			assetCategoryIds, assetTagNames);
	}

	public AssetEntrySet updateAssetEntrySet(
			long assetEntrySetId, JSONObject payloadJSONObject)
		throws PortalException, SystemException {

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			assetEntrySetId);

		Date now = new Date();

		assetEntrySet.setModifiedTime(now.getTime());
		assetEntrySet.setPayload(payloadJSONObject.toString());

		assetEntrySetPersistence.update(assetEntrySet);

		updateAsset(
			assetEntrySet,
			GetterUtil.getLongValues(
				StringUtil.split(
					payloadJSONObject.getString(
						AssetEntrySetConstants.KEY_ASSET_CATEGORY_IDS))),
			StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.KEY_ASSET_TAG_NAMES)));

		return assetEntrySet;
	}

	@Override
	public AssetEntrySet updateChildrenAssetEntrySetCount(
			long parentAssetEntrySetId)
		throws PortalException, SystemException {

		if (parentAssetEntrySetId == 0) {
			return null;
		}

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			parentAssetEntrySetId);

		int childrenAssetEntrySetCount =
			assetEntrySetPersistence.countByParentAssetEntrySetId(
				parentAssetEntrySetId);

		assetEntrySet.setChildrenAssetEntrySetCount(childrenAssetEntrySetCount);

		assetEntrySetPersistence.update(assetEntrySet);

		return assetEntrySet;
	}

	protected AssetEntrySet updateRatingsEntry(
			long userId, long assetEntrySetId, long score)
		throws PortalException, SystemException {

		String className = AssetEntrySet.class.getName();

		ratingsEntryLocalService.updateEntry(
			userId, className, assetEntrySetId, score, new ServiceContext());

		AssetEntrySet assetEntrySet = assetEntrySetPersistence.findByPrimaryKey(
			assetEntrySetId);

		RatingsStats ratingsStats = ratingsStatsLocalService.getStats(
			className, assetEntrySetId);

		assetEntrySet.setRatingsTotalScore((long)ratingsStats.getTotalScore());

		assetEntrySetPersistence.update(assetEntrySet);

		return assetEntrySet;
	}

}