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

package com.liferay.asset.sharing.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.sharing.model.AssetSharingEntry;
import com.liferay.asset.sharing.service.base.AssetSharingEntryLocalServiceBaseImpl;
import com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK;

import java.util.List;
import java.util.Map;

/**
 * @author Sherry Yang
 */
@ProviderType
public class AssetSharingEntryLocalServiceImpl
	extends AssetSharingEntryLocalServiceBaseImpl {

	@Override
	public void addAssetSharingEntries(
		long classNameId, long classPK, Map<Long, long[]> sharedToClassPKsMap) {

		for (Long sharedToClassNameId : sharedToClassPKsMap.keySet()) {
			long[] sharedToClassPKs = sharedToClassPKsMap.get(
				sharedToClassNameId);

			if (sharedToClassPKs.length == 0) {
				continue;
			}

			for (long sharedToClassPK : sharedToClassPKs) {
				addAssetSharingEntry(
					classNameId, classPK, sharedToClassNameId, sharedToClassPK);
			}
		}
	}

	@Override
	public void addAssetSharingEntry(
		long classNameId, long classPK, long sharedToClassNameId,
		long sharedToClassPK) {

		AssetSharingEntryPK assetSharingEntryPK = new AssetSharingEntryPK(
			classNameId, classPK, sharedToClassNameId, sharedToClassPK);

		AssetSharingEntry assetSharingEntry =
			assetSharingEntryPersistence.fetchByPrimaryKey(assetSharingEntryPK);

		if (assetSharingEntry == null) {
			assetSharingEntry = assetSharingEntryPersistence.create(
				assetSharingEntryPK);

			assetSharingEntryPersistence.update(assetSharingEntry);
		}
	}

	@Override
	public void deleteAssetSharingEntries(long classNameId, long classPK) {
		assetSharingEntryPersistence.removeByC_C(classNameId, classPK);
	}

	@Override
	public List<AssetSharingEntry> getAssetSharingEntries(
		long classNameId, long classPK) {

		return assetSharingEntryPersistence.findByC_C(classNameId, classPK);
	}

	@Override
	public List<AssetSharingEntry> getAssetSharingEntries(
		long classNameId, long classPK, long sharedToClassNameId) {

		return assetSharingEntryPersistence.findByC_C_S(
			classNameId, classPK, sharedToClassNameId);
	}

	@Override
	public List<AssetSharingEntry> getSharedToAssetSharingEntries(
		long sharedToClassNameId, long sharedToClassPK, int start, int end) {

		return assetSharingEntryPersistence.findByS_S(
			sharedToClassNameId, sharedToClassPK, start, end);
	}

	@Override
	public List<AssetSharingEntry> getSharedToAssetSharingEntries(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		int start, int end) {

		return assetSharingEntryPersistence.findByC_S_S(
			classNameId, sharedToClassNameId, sharedToClassPK, start, end);
	}

	@Override
	public int getSharedToAssetSharingEntriesCount(
		long sharedToClassNameId, long sharedToClassPK) {

		return assetSharingEntryPersistence.countByS_S(
			sharedToClassNameId, sharedToClassPK);
	}

	@Override
	public int getSharedToAssetSharingEntriesCount(
		long classNameId, long sharedToClassNameId, long sharedToClassPK) {

		return assetSharingEntryPersistence.countByC_S_S(
			classNameId, sharedToClassNameId, sharedToClassPK);
	}

}