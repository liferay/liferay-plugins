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
	public int countBySharedToClassPKsMap(
		java.util.Map<java.lang.Long, long[]> sharedToClassPKsMap)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByCCNI_ATN(long creatorClassNameId,
		java.lang.String assetTagName,
		java.util.Map<java.lang.Long, long[]> sharedToClassPKsMap)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByCCNI_CCPK_ATN(long creatorClassNameId,
		long creatorClassPK, java.lang.String assetTagName,
		java.util.Map<java.lang.Long, long[]> sharedToClassPKsMap,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findBySharedToClassPKsMap(
		java.util.Map<java.lang.Long, long[]> sharedToClassPKsMap, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByCCNI_ATN(
		long creatorClassNameId, java.lang.String assetTagName,
		java.util.Map<java.lang.Long, long[]> sharedToClassPKsMap, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByCCNI_CCPK_ATN(
		long creatorClassNameId, long creatorClassPK,
		java.lang.String assetTagName,
		java.util.Map<java.lang.Long, long[]> sharedToClassPKsMap,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}