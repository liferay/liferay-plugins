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

package com.liferay.asset.entry.set.service.persistence;

/**
 * @author Brian Wing Shun Chan
 */
public interface AssetEntrySetFinder {
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetReference> findAssetEntrySetReferenceByPAESI_CNI(
		long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByCT_PAESI_CNI(
		long classNameId, long classPK, long createTime, boolean gtCreateTime,
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.json.JSONArray sharedToJSONArray,
		java.lang.String[] assetTagNames, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}