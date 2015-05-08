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

package com.liferay.ams.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.ams.model.Checkout;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the checkout service. This utility wraps {@link com.liferay.ams.service.persistence.impl.CheckoutPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CheckoutPersistence
 * @see com.liferay.ams.service.persistence.impl.CheckoutPersistenceImpl
 * @generated
 */
@ProviderType
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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Checkout> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Checkout> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Checkout> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Checkout> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Checkout update(Checkout checkout) {
		return getPersistence().update(checkout);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Checkout update(Checkout checkout,
		ServiceContext serviceContext) {
		return getPersistence().update(checkout, serviceContext);
	}

	/**
	* Caches the checkout in the entity cache if it is enabled.
	*
	* @param checkout the checkout
	*/
	public static void cacheResult(Checkout checkout) {
		getPersistence().cacheResult(checkout);
	}

	/**
	* Caches the checkouts in the entity cache if it is enabled.
	*
	* @param checkouts the checkouts
	*/
	public static void cacheResult(List<Checkout> checkouts) {
		getPersistence().cacheResult(checkouts);
	}

	/**
	* Creates a new checkout with the primary key. Does not add the checkout to the database.
	*
	* @param checkoutId the primary key for the new checkout
	* @return the new checkout
	*/
	public static Checkout create(long checkoutId) {
		return getPersistence().create(checkoutId);
	}

	/**
	* Removes the checkout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout that was removed
	* @throws NoSuchCheckoutException if a checkout with the primary key could not be found
	*/
	public static Checkout remove(long checkoutId)
		throws com.liferay.ams.NoSuchCheckoutException {
		return getPersistence().remove(checkoutId);
	}

	public static Checkout updateImpl(Checkout checkout) {
		return getPersistence().updateImpl(checkout);
	}

	/**
	* Returns the checkout with the primary key or throws a {@link NoSuchCheckoutException} if it could not be found.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout
	* @throws NoSuchCheckoutException if a checkout with the primary key could not be found
	*/
	public static Checkout findByPrimaryKey(long checkoutId)
		throws com.liferay.ams.NoSuchCheckoutException {
		return getPersistence().findByPrimaryKey(checkoutId);
	}

	/**
	* Returns the checkout with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout, or <code>null</code> if a checkout with the primary key could not be found
	*/
	public static Checkout fetchByPrimaryKey(long checkoutId) {
		return getPersistence().fetchByPrimaryKey(checkoutId);
	}

	public static java.util.Map<java.io.Serializable, Checkout> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the checkouts.
	*
	* @return the checkouts
	*/
	public static List<Checkout> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Checkout> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Checkout> findAll(int start, int end,
		OrderByComparator<Checkout> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the checkouts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of checkouts.
	*
	* @return the number of checkouts
	*/
	public static int countAll() {
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

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(CheckoutPersistence persistence) {
	}

	private static CheckoutPersistence _persistence;
}