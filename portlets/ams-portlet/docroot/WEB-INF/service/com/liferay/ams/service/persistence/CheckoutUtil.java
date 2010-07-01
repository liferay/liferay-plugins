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

import com.liferay.ams.model.Checkout;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * <a href="CheckoutUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CheckoutPersistence
 * @see       CheckoutPersistenceImpl
 * @generated
 */
public class CheckoutUtil {
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
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static Checkout remove(Checkout checkout) throws SystemException {
		return getPersistence().remove(checkout);
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

	public static void cacheResult(com.liferay.ams.model.Checkout checkout) {
		getPersistence().cacheResult(checkout);
	}

	public static void cacheResult(
		java.util.List<com.liferay.ams.model.Checkout> checkouts) {
		getPersistence().cacheResult(checkouts);
	}

	public static com.liferay.ams.model.Checkout create(long checkoutId) {
		return getPersistence().create(checkoutId);
	}

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

	public static com.liferay.ams.model.Checkout findByPrimaryKey(
		long checkoutId)
		throws com.liferay.ams.NoSuchCheckoutException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(checkoutId);
	}

	public static com.liferay.ams.model.Checkout fetchByPrimaryKey(
		long checkoutId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(checkoutId);
	}

	public static java.util.List<com.liferay.ams.model.Checkout> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.ams.model.Checkout> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.ams.model.Checkout> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CheckoutPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CheckoutPersistence)PortletBeanLocatorUtil.locate(com.liferay.ams.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					CheckoutPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(CheckoutPersistence persistence) {
		_persistence = persistence;
	}

	private static CheckoutPersistence _persistence;
}