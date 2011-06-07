/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.hr.service.persistence;

import com.liferay.hr.model.HRAsset;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r asset service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetPersistenceImpl
 * @see HRAssetUtil
 * @generated
 */
public interface HRAssetPersistence extends BasePersistence<HRAsset> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRAssetUtil} to access the h r asset persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r asset in the entity cache if it is enabled.
	*
	* @param hrAsset the h r asset
	*/
	public void cacheResult(com.liferay.hr.model.HRAsset hrAsset);

	/**
	* Caches the h r assets in the entity cache if it is enabled.
	*
	* @param hrAssets the h r assets
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRAsset> hrAssets);

	/**
	* Creates a new h r asset with the primary key. Does not add the h r asset to the database.
	*
	* @param hrAssetId the primary key for the new h r asset
	* @return the new h r asset
	*/
	public com.liferay.hr.model.HRAsset create(long hrAssetId);

	/**
	* Removes the h r asset with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrAssetId the primary key of the h r asset
	* @return the h r asset that was removed
	* @throws com.liferay.hr.NoSuchAssetException if a h r asset with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAsset remove(long hrAssetId)
		throws com.liferay.hr.NoSuchAssetException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRAsset updateImpl(
		com.liferay.hr.model.HRAsset hrAsset, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r asset with the primary key or throws a {@link com.liferay.hr.NoSuchAssetException} if it could not be found.
	*
	* @param hrAssetId the primary key of the h r asset
	* @return the h r asset
	* @throws com.liferay.hr.NoSuchAssetException if a h r asset with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAsset findByPrimaryKey(long hrAssetId)
		throws com.liferay.hr.NoSuchAssetException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r asset with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrAssetId the primary key of the h r asset
	* @return the h r asset, or <code>null</code> if a h r asset with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAsset fetchByPrimaryKey(long hrAssetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r assets.
	*
	* @return the h r assets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAsset> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r assets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r assets
	* @param end the upper bound of the range of h r assets (not inclusive)
	* @return the range of h r assets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAsset> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r assets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r assets
	* @param end the upper bound of the range of h r assets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r assets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAsset> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r assets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r assets.
	*
	* @return the number of h r assets
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRAsset remove(HRAsset hrAsset) throws SystemException;
}