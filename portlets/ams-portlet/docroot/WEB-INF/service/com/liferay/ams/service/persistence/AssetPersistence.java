/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.ams.service.persistence;

import com.liferay.ams.model.Asset;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * @author    Brian Wing Shun Chan
 * @see       AssetPersistenceImpl
 * @see       AssetUtil
 * @generated
 */
public interface AssetPersistence extends BasePersistence<Asset> {
	public void cacheResult(com.liferay.ams.model.Asset asset);

	public void cacheResult(java.util.List<com.liferay.ams.model.Asset> assets);

	public com.liferay.ams.model.Asset create(long assetId);

	public com.liferay.ams.model.Asset remove(long assetId)
		throws com.liferay.ams.NoSuchAssetException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.Asset updateImpl(
		com.liferay.ams.model.Asset asset, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.Asset findByPrimaryKey(long assetId)
		throws com.liferay.ams.NoSuchAssetException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.Asset fetchByPrimaryKey(long assetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.Asset> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.Asset> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.Asset> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}