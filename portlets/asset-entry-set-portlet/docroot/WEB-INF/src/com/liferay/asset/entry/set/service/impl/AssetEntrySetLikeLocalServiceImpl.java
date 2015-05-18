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
import com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Sherry Yang
 */
public class AssetEntrySetLikeLocalServiceImpl
	extends AssetEntrySetLikeLocalServiceBaseImpl {

	@Override
	public AssetEntrySetLike fetchAssetEntrySetLike(
			long assetEntrySetId, long classNameId, long classPK)
		throws SystemException {

		AssetEntrySetLikePK assetEntrySetLikePK = new AssetEntrySetLikePK(
			assetEntrySetId, classNameId, classPK);

		return assetEntrySetLikePersistence.fetchByPrimaryKey(
			assetEntrySetLikePK);
	}

	@Override
	public List<AssetEntrySetLike> getAssetEntrySetLikes(
			long assetEntrySetId, long classNameId, long classPK, int start,
			int end)
		throws SystemException {

		return assetEntrySetLikeFinder.findByAESI_NotC_C(
			assetEntrySetId, classNameId, classPK, start, end);
	}

}