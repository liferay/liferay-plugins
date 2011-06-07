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

import com.liferay.hr.model.HRAssetType;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r asset type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetTypePersistenceImpl
 * @see HRAssetTypeUtil
 * @generated
 */
public interface HRAssetTypePersistence extends BasePersistence<HRAssetType> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRAssetTypeUtil} to access the h r asset type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r asset type in the entity cache if it is enabled.
	*
	* @param hrAssetType the h r asset type
	*/
	public void cacheResult(com.liferay.hr.model.HRAssetType hrAssetType);

	/**
	* Caches the h r asset types in the entity cache if it is enabled.
	*
	* @param hrAssetTypes the h r asset types
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRAssetType> hrAssetTypes);

	/**
	* Creates a new h r asset type with the primary key. Does not add the h r asset type to the database.
	*
	* @param hrAssetTypeId the primary key for the new h r asset type
	* @return the new h r asset type
	*/
	public com.liferay.hr.model.HRAssetType create(long hrAssetTypeId);

	/**
	* Removes the h r asset type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrAssetTypeId the primary key of the h r asset type
	* @return the h r asset type that was removed
	* @throws com.liferay.hr.NoSuchAssetTypeException if a h r asset type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAssetType remove(long hrAssetTypeId)
		throws com.liferay.hr.NoSuchAssetTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRAssetType updateImpl(
		com.liferay.hr.model.HRAssetType hrAssetType, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r asset type with the primary key or throws a {@link com.liferay.hr.NoSuchAssetTypeException} if it could not be found.
	*
	* @param hrAssetTypeId the primary key of the h r asset type
	* @return the h r asset type
	* @throws com.liferay.hr.NoSuchAssetTypeException if a h r asset type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAssetType findByPrimaryKey(long hrAssetTypeId)
		throws com.liferay.hr.NoSuchAssetTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r asset type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrAssetTypeId the primary key of the h r asset type
	* @return the h r asset type, or <code>null</code> if a h r asset type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAssetType fetchByPrimaryKey(
		long hrAssetTypeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r asset types.
	*
	* @return the h r asset types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAssetType> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r asset types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r asset types
	* @param end the upper bound of the range of h r asset types (not inclusive)
	* @return the range of h r asset types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAssetType> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r asset types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r asset types
	* @param end the upper bound of the range of h r asset types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r asset types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAssetType> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r asset types from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r asset types.
	*
	* @return the number of h r asset types
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRAssetType remove(HRAssetType hrAssetType)
		throws SystemException;
}