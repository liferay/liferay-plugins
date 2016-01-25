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

package com.liferay.samplealloymvc.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.samplealloymvc.exception.NoSuchCheckoutException;
import com.liferay.samplealloymvc.model.Checkout;

/**
 * The persistence interface for the checkout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.samplealloymvc.service.persistence.impl.CheckoutPersistenceImpl
 * @see CheckoutUtil
 * @generated
 */
@ProviderType
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
	public void cacheResult(Checkout checkout);

	/**
	* Caches the checkouts in the entity cache if it is enabled.
	*
	* @param checkouts the checkouts
	*/
	public void cacheResult(java.util.List<Checkout> checkouts);

	/**
	* Creates a new checkout with the primary key. Does not add the checkout to the database.
	*
	* @param checkoutId the primary key for the new checkout
	* @return the new checkout
	*/
	public Checkout create(long checkoutId);

	/**
	* Removes the checkout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout that was removed
	* @throws NoSuchCheckoutException if a checkout with the primary key could not be found
	*/
	public Checkout remove(long checkoutId) throws NoSuchCheckoutException;

	public Checkout updateImpl(Checkout checkout);

	/**
	* Returns the checkout with the primary key or throws a {@link NoSuchCheckoutException} if it could not be found.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout
	* @throws NoSuchCheckoutException if a checkout with the primary key could not be found
	*/
	public Checkout findByPrimaryKey(long checkoutId)
		throws NoSuchCheckoutException;

	/**
	* Returns the checkout with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout, or <code>null</code> if a checkout with the primary key could not be found
	*/
	public Checkout fetchByPrimaryKey(long checkoutId);

	@Override
	public java.util.Map<java.io.Serializable, Checkout> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the checkouts.
	*
	* @return the checkouts
	*/
	public java.util.List<Checkout> findAll();

	/**
	* Returns a range of all the checkouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CheckoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of checkouts
	* @param end the upper bound of the range of checkouts (not inclusive)
	* @return the range of checkouts
	*/
	public java.util.List<Checkout> findAll(int start, int end);

	/**
	* Returns an ordered range of all the checkouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CheckoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of checkouts
	* @param end the upper bound of the range of checkouts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of checkouts
	*/
	public java.util.List<Checkout> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Checkout> orderByComparator);

	/**
	* Returns an ordered range of all the checkouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CheckoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of checkouts
	* @param end the upper bound of the range of checkouts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of checkouts
	*/
	public java.util.List<Checkout> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Checkout> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the checkouts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of checkouts.
	*
	* @return the number of checkouts
	*/
	public int countAll();
}