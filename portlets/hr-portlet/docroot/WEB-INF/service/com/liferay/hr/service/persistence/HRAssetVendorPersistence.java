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

import com.liferay.hr.model.HRAssetVendor;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r asset vendor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetVendorPersistenceImpl
 * @see HRAssetVendorUtil
 * @generated
 */
public interface HRAssetVendorPersistence extends BasePersistence<HRAssetVendor> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRAssetVendorUtil} to access the h r asset vendor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r asset vendor in the entity cache if it is enabled.
	*
	* @param hrAssetVendor the h r asset vendor
	*/
	public void cacheResult(com.liferay.hr.model.HRAssetVendor hrAssetVendor);

	/**
	* Caches the h r asset vendors in the entity cache if it is enabled.
	*
	* @param hrAssetVendors the h r asset vendors
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRAssetVendor> hrAssetVendors);

	/**
	* Creates a new h r asset vendor with the primary key. Does not add the h r asset vendor to the database.
	*
	* @param hrAssetVendorId the primary key for the new h r asset vendor
	* @return the new h r asset vendor
	*/
	public com.liferay.hr.model.HRAssetVendor create(long hrAssetVendorId);

	/**
	* Removes the h r asset vendor with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrAssetVendorId the primary key of the h r asset vendor
	* @return the h r asset vendor that was removed
	* @throws com.liferay.hr.NoSuchAssetVendorException if a h r asset vendor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAssetVendor remove(long hrAssetVendorId)
		throws com.liferay.hr.NoSuchAssetVendorException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRAssetVendor updateImpl(
		com.liferay.hr.model.HRAssetVendor hrAssetVendor, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r asset vendor with the primary key or throws a {@link com.liferay.hr.NoSuchAssetVendorException} if it could not be found.
	*
	* @param hrAssetVendorId the primary key of the h r asset vendor
	* @return the h r asset vendor
	* @throws com.liferay.hr.NoSuchAssetVendorException if a h r asset vendor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAssetVendor findByPrimaryKey(
		long hrAssetVendorId)
		throws com.liferay.hr.NoSuchAssetVendorException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r asset vendor with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrAssetVendorId the primary key of the h r asset vendor
	* @return the h r asset vendor, or <code>null</code> if a h r asset vendor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAssetVendor fetchByPrimaryKey(
		long hrAssetVendorId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r asset vendors.
	*
	* @return the h r asset vendors
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAssetVendor> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r asset vendors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r asset vendors
	* @param end the upper bound of the range of h r asset vendors (not inclusive)
	* @return the range of h r asset vendors
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAssetVendor> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r asset vendors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r asset vendors
	* @param end the upper bound of the range of h r asset vendors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r asset vendors
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAssetVendor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r asset vendors from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r asset vendors.
	*
	* @return the number of h r asset vendors
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRAssetVendor remove(HRAssetVendor hrAssetVendor)
		throws SystemException;
}