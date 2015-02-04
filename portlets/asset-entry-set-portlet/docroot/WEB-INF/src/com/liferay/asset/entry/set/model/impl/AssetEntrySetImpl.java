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

import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.asset.entry.set.service.AssetEntrySetLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetEntrySetImpl extends AssetEntrySetBaseImpl {

	public AssetEntrySetImpl() {
	}

	@Override
	public List<AssetEntrySet> getChildAssetEntrySets() {
		return _childAssetEntrySets;
	}

	@Override
	public void setChildAssetEntrySets(int childAssetEntrySetsLimit)
		throws PortalException, SystemException {

		if (childAssetEntrySetsLimit <= 0) {
			return;
		}

		_childAssetEntrySets =
			AssetEntrySetLocalServiceUtil.getChildAssetEntrySets(
				getAssetEntrySetId(), 0, childAssetEntrySetsLimit, null);
	}

	private List<AssetEntrySet> _childAssetEntrySets;

}