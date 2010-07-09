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

package com.liferay.ams.service;


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link CheckoutLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CheckoutLocalService
 * @generated
 */
public class CheckoutLocalServiceWrapper implements CheckoutLocalService {
	public CheckoutLocalServiceWrapper(
		CheckoutLocalService checkoutLocalService) {
		_checkoutLocalService = checkoutLocalService;
	}

	public com.liferay.ams.model.Checkout addCheckout(
		com.liferay.ams.model.Checkout checkout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.addCheckout(checkout);
	}

	public com.liferay.ams.model.Checkout createCheckout(long checkoutId) {
		return _checkoutLocalService.createCheckout(checkoutId);
	}

	public void deleteCheckout(long checkoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_checkoutLocalService.deleteCheckout(checkoutId);
	}

	public void deleteCheckout(com.liferay.ams.model.Checkout checkout)
		throws com.liferay.portal.kernel.exception.SystemException {
		_checkoutLocalService.deleteCheckout(checkout);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.ams.model.Checkout getCheckout(long checkoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.getCheckout(checkoutId);
	}

	public java.util.List<com.liferay.ams.model.Checkout> getCheckouts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.getCheckouts(start, end);
	}

	public int getCheckoutsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.getCheckoutsCount();
	}

	public com.liferay.ams.model.Checkout updateCheckout(
		com.liferay.ams.model.Checkout checkout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.updateCheckout(checkout);
	}

	public com.liferay.ams.model.Checkout updateCheckout(
		com.liferay.ams.model.Checkout checkout, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.updateCheckout(checkout, merge);
	}

	public CheckoutLocalService getWrappedCheckoutLocalService() {
		return _checkoutLocalService;
	}

	private CheckoutLocalService _checkoutLocalService;
}