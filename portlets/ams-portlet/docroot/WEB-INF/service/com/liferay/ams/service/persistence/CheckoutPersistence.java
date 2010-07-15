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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * @author    Brian Wing Shun Chan
 * @see       CheckoutPersistenceImpl
 * @see       CheckoutUtil
 * @generated
 */
public interface CheckoutPersistence extends BasePersistence<Checkout> {
	public void cacheResult(com.liferay.ams.model.Checkout checkout);

	public void cacheResult(
		java.util.List<com.liferay.ams.model.Checkout> checkouts);

	public com.liferay.ams.model.Checkout create(long checkoutId);

	public com.liferay.ams.model.Checkout remove(long checkoutId)
		throws com.liferay.ams.NoSuchCheckoutException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.Checkout updateImpl(
		com.liferay.ams.model.Checkout checkout, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.Checkout findByPrimaryKey(long checkoutId)
		throws com.liferay.ams.NoSuchCheckoutException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.Checkout fetchByPrimaryKey(long checkoutId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.Checkout> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.Checkout> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.Checkout> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}