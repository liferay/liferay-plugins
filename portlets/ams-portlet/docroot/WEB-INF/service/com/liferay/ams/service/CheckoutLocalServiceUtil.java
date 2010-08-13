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
 * The utility for the checkout local service. This utility wraps {@link com.liferay.ams.service.impl.CheckoutLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link com.liferay.ams.service.impl.CheckoutLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CheckoutLocalService
 * @see com.liferay.ams.service.base.CheckoutLocalServiceBaseImpl
 * @see com.liferay.ams.service.impl.CheckoutLocalServiceImpl
 * @generated
 */
public class CheckoutLocalServiceUtil {
	/**
	* Adds the checkout to the database. Also notifies the appropriate model listeners.
	*
	* @param checkout the checkout to add
	* @return the checkout that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Checkout addCheckout(
		com.liferay.ams.model.Checkout checkout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addCheckout(checkout);
	}

	/**
	* Creates a new checkout with the primary key. Does not add the checkout to the database.
	*
	* @param checkoutId the primary key for the new checkout
	* @return the new checkout
	*/
	public static com.liferay.ams.model.Checkout createCheckout(long checkoutId) {
		return getService().createCheckout(checkoutId);
	}

	/**
	* Deletes the checkout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param checkoutId the primary key of the checkout to delete
	* @throws PortalException if a checkout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteCheckout(long checkoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCheckout(checkoutId);
	}

	/**
	* Deletes the checkout from the database. Also notifies the appropriate model listeners.
	*
	* @param checkout the checkout to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteCheckout(com.liferay.ams.model.Checkout checkout)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCheckout(checkout);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the checkout with the primary key.
	*
	* @param checkoutId the primary key of the checkout to get
	* @return the checkout
	* @throws PortalException if a checkout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Checkout getCheckout(long checkoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCheckout(checkoutId);
	}

	/**
	* Gets a range of all the checkouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of checkouts to return
	* @param end the upper bound of the range of checkouts to return (not inclusive)
	* @return the range of checkouts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.ams.model.Checkout> getCheckouts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCheckouts(start, end);
	}

	/**
	* Gets the number of checkouts.
	*
	* @return the number of checkouts
	* @throws SystemException if a system exception occurred
	*/
	public static int getCheckoutsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCheckoutsCount();
	}

	/**
	* Updates the checkout in the database. Also notifies the appropriate model listeners.
	*
	* @param checkout the checkout to update
	* @return the checkout that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Checkout updateCheckout(
		com.liferay.ams.model.Checkout checkout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCheckout(checkout);
	}

	/**
	* Updates the checkout in the database. Also notifies the appropriate model listeners.
	*
	* @param checkout the checkout to update
	* @param merge whether to merge the checkout with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the checkout that was updated
	* @throws SystemException if a system exception occurred
	*/
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
					portletClassLoader);

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