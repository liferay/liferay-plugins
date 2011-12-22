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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the checkout service. This utility wraps {@link CheckoutPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CheckoutPersistence
 * @see CheckoutPersistenceImpl
 * @generated
 */
public class CheckoutUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Checkout checkout) {
		getPersistence().clearCache(checkout);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Checkout> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Checkout> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Checkout> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Checkout update(Checkout checkout, boolean merge)
		throws SystemException {
		return getPersistence().update(checkout, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Checkout update(Checkout checkout, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(checkout, merge, serviceContext);
	}

	/**
	* Caches the checkout in the entity cache if it is enabled.
	*
	* @param checkout the checkout
	*/
	public static void cacheResult(com.liferay.ams.model.Checkout checkout) {
		getPersistence().cacheResult(checkout);
	}

	/**
	* Caches the checkouts in the entity cache if it is enabled.
	*
	* @param checkouts the checkouts
	*/
	public static void cacheResult(
		java.util.List<com.liferay.ams.model.Checkout> checkouts) {
		getPersistence().cacheResult(checkouts);
	}

	/**
	* Creates a new checkout with the primary key. Does not add the checkout to the database.
	*
	* @param checkoutId the primary key for the new checkout
	* @return the new checkout
	*/
	public static com.liferay.ams.model.Checkout create(long checkoutId) {
		return getPersistence().create(checkoutId);
	}

	/**
	* Removes the checkout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout that was removed
	* @throws com.liferay.ams.NoSuchCheckoutException if a checkout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Checkout remove(long checkoutId)
		throws com.liferay.ams.NoSuchCheckoutException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(checkoutId);
	}

	public static com.liferay.ams.model.Checkout updateImpl(
		com.liferay.ams.model.Checkout checkout, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(checkout, merge);
	}

	/**
	* Returns the checkout with the primary key or throws a {@link com.liferay.ams.NoSuchCheckoutException} if it could not be found.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout
	* @throws com.liferay.ams.NoSuchCheckoutException if a checkout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Checkout findByPrimaryKey(
		long checkoutId)
		throws com.liferay.ams.NoSuchCheckoutException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(checkoutId);
	}

	/**
	* Returns the checkout with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout, or <code>null</code> if a checkout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Checkout fetchByPrimaryKey(
		long checkoutId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(checkoutId);
	}

	/**
	* Returns all the checkouts.
	*
	* @return the checkouts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.ams.model.Checkout> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.ams.model.Checkout> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.ams.model.Checkout> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the checkouts from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of checkouts.
	*
	* @return the number of checkouts
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CheckoutPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CheckoutPersistence)PortletBeanLocatorUtil.locate(com.liferay.ams.service.ClpSerializer.getServletContextName(),
					CheckoutPersistence.class.getName());

			ReferenceRegistry.registerReference(CheckoutUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(CheckoutPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(CheckoutUtil.class, "_persistence");
	}

	private static CheckoutPersistence _persistence;
}