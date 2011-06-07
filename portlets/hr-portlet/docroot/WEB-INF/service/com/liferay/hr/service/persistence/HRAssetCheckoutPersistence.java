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

import com.liferay.hr.model.HRAssetCheckout;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r asset checkout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetCheckoutPersistenceImpl
 * @see HRAssetCheckoutUtil
 * @generated
 */
public interface HRAssetCheckoutPersistence extends BasePersistence<HRAssetCheckout> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRAssetCheckoutUtil} to access the h r asset checkout persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r asset checkout in the entity cache if it is enabled.
	*
	* @param hrAssetCheckout the h r asset checkout
	*/
	public void cacheResult(
		com.liferay.hr.model.HRAssetCheckout hrAssetCheckout);

	/**
	* Caches the h r asset checkouts in the entity cache if it is enabled.
	*
	* @param hrAssetCheckouts the h r asset checkouts
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRAssetCheckout> hrAssetCheckouts);

	/**
	* Creates a new h r asset checkout with the primary key. Does not add the h r asset checkout to the database.
	*
	* @param hrAssetCheckoutId the primary key for the new h r asset checkout
	* @return the new h r asset checkout
	*/
	public com.liferay.hr.model.HRAssetCheckout create(long hrAssetCheckoutId);

	/**
	* Removes the h r asset checkout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrAssetCheckoutId the primary key of the h r asset checkout
	* @return the h r asset checkout that was removed
	* @throws com.liferay.hr.NoSuchAssetCheckoutException if a h r asset checkout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAssetCheckout remove(long hrAssetCheckoutId)
		throws com.liferay.hr.NoSuchAssetCheckoutException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRAssetCheckout updateImpl(
		com.liferay.hr.model.HRAssetCheckout hrAssetCheckout, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r asset checkout with the primary key or throws a {@link com.liferay.hr.NoSuchAssetCheckoutException} if it could not be found.
	*
	* @param hrAssetCheckoutId the primary key of the h r asset checkout
	* @return the h r asset checkout
	* @throws com.liferay.hr.NoSuchAssetCheckoutException if a h r asset checkout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAssetCheckout findByPrimaryKey(
		long hrAssetCheckoutId)
		throws com.liferay.hr.NoSuchAssetCheckoutException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r asset checkout with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrAssetCheckoutId the primary key of the h r asset checkout
	* @return the h r asset checkout, or <code>null</code> if a h r asset checkout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAssetCheckout fetchByPrimaryKey(
		long hrAssetCheckoutId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r asset checkouts.
	*
	* @return the h r asset checkouts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAssetCheckout> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r asset checkouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r asset checkouts
	* @param end the upper bound of the range of h r asset checkouts (not inclusive)
	* @return the range of h r asset checkouts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAssetCheckout> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r asset checkouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r asset checkouts
	* @param end the upper bound of the range of h r asset checkouts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r asset checkouts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAssetCheckout> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r asset checkouts from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r asset checkouts.
	*
	* @return the number of h r asset checkouts
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRAssetCheckout remove(HRAssetCheckout hrAssetCheckout)
		throws SystemException;
}