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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <p>
 * This class provides static methods for the
 * {@link CheckoutLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CheckoutLocalService
 * @generated
 */
public class CheckoutLocalServiceUtil {
	public static com.liferay.ams.model.Checkout addCheckout(
		com.liferay.ams.model.Checkout checkout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addCheckout(checkout);
	}

	public static com.liferay.ams.model.Checkout createCheckout(long checkoutId) {
		return getService().createCheckout(checkoutId);
	}

	public static void deleteCheckout(long checkoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCheckout(checkoutId);
	}

	public static void deleteCheckout(com.liferay.ams.model.Checkout checkout)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCheckout(checkout);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.ams.model.Checkout getCheckout(long checkoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCheckout(checkoutId);
	}

	public static java.util.List<com.liferay.ams.model.Checkout> getCheckouts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCheckouts(start, end);
	}

	public static int getCheckoutsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCheckoutsCount();
	}

	public static com.liferay.ams.model.Checkout updateCheckout(
		com.liferay.ams.model.Checkout checkout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCheckout(checkout);
	}

	public static com.liferay.ams.model.Checkout updateCheckout(
		com.liferay.ams.model.Checkout checkout, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCheckout(checkout, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static CheckoutLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					CheckoutLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					CheckoutLocalService.class.getName(), portletClassLoader);

			_service = new CheckoutLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(CheckoutLocalService service) {
		_service = service;
	}

	private static CheckoutLocalService _service;
}