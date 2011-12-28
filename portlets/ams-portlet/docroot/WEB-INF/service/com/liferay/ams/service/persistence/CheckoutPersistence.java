/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.ams.model.Checkout;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the checkout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CheckoutPersistenceImpl
 * @see CheckoutUtil
 * @generated
 */
public interface CheckoutPersistence extends BasePersistence<Checkout> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CheckoutUtil} to access the checkout persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the checkout in the entity cache if it is enabled.
	*
	* @param checkout the checkout
	*/
	public void cacheResult(com.liferay.ams.model.Checkout checkout);

	/**
	* Caches the checkouts in the entity cache if it is enabled.
	*
	* @param checkouts the checkouts
	*/
	public void cacheResult(
		java.util.List<com.liferay.ams.model.Checkout> checkouts);

	/**
	* Creates a new checkout with the primary key. Does not add the checkout to the database.
	*
	* @param checkoutId the primary key for the new checkout
	* @return the new checkout
	*/
	public com.liferay.ams.model.Checkout create(long checkoutId);

	/**
	* Removes the checkout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout that was removed
	* @throws com.liferay.ams.NoSuchCheckoutException if a checkout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.ams.model.Checkout remove(long checkoutId)
		throws com.liferay.ams.NoSuchCheckoutException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.Checkout updateImpl(
		com.liferay.ams.model.Checkout checkout, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the checkout with the primary key or throws a {@link com.liferay.ams.NoSuchCheckoutException} if it could not be found.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout
	* @throws com.liferay.ams.NoSuchCheckoutException if a checkout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.ams.model.Checkout findByPrimaryKey(long checkoutId)
		throws com.liferay.ams.NoSuchCheckoutException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the checkout with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout, or <code>null</code> if a checkout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.ams.model.Checkout fetchByPrimaryKey(long checkoutId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the checkouts.
	*
	* @return the checkouts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.ams.model.Checkout> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the checkouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of checkouts
	* @param end the upper bound of the range of checkouts (not inclusive)
	* @return the range of checkouts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.ams.model.Checkout> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the checkouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of checkouts
	* @param end the upper bound of the range of checkouts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of checkouts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.ams.model.Checkout> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the checkouts from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of checkouts.
	*
	* @return the number of checkouts
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}