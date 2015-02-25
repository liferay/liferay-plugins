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
	public int countBySharedTo(
		com.liferay.portal.kernel.json.JSONArray sharedToJSONArray)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByCCNI_ATN(long creatorClassNameId,
		java.lang.String assetTagName,
		com.liferay.portal.kernel.json.JSONArray sharedToJSONArray)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByCCNI_CCPK_ATN(long creatorClassNameId,
		long creatorClassPK, java.lang.String assetTagName,
		com.liferay.portal.kernel.json.JSONArray sharedToJSONArray,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findBySharedTo(
		com.liferay.portal.kernel.json.JSONArray sharedToJSONArray, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByCT_PASEI(
		long createTime, boolean gtCreateTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.json.JSONArray sharedToJSONArray, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByCCNI_ATN(
		long creatorClassNameId, java.lang.String assetTagName,
		com.liferay.portal.kernel.json.JSONArray sharedToJSONArray, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByCCNI_CCPK_ATN(
		long creatorClassNameId, long creatorClassPK,
		java.lang.String assetTagName,
		com.liferay.portal.kernel.json.JSONArray sharedToJSONArray,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}