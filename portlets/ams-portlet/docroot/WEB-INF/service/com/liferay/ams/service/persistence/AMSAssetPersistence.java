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

import com.liferay.ams.model.AMSAsset;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="AMSAssetPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSAssetPersistenceImpl
 * @see       AMSAssetUtil
 * @generated
 */
public interface AMSAssetPersistence extends BasePersistence<AMSAsset> {
	public void cacheResult(com.liferay.ams.model.AMSAsset amsAsset);

	public void cacheResult(
		java.util.List<com.liferay.ams.model.AMSAsset> amsAssets);

	public com.liferay.ams.model.AMSAsset create(long amsAssetId);

	public com.liferay.ams.model.AMSAsset remove(long amsAssetId)
		throws com.liferay.ams.NoSuchAssetException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.AMSAsset updateImpl(
		com.liferay.ams.model.AMSAsset amsAsset, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.AMSAsset findByPrimaryKey(long amsAssetId)
		throws com.liferay.ams.NoSuchAssetException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.AMSAsset fetchByPrimaryKey(long amsAssetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.AMSAsset> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.AMSAsset> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.AMSAsset> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}