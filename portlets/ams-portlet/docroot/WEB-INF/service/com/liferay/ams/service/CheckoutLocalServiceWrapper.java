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

package com.liferay.ams.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CheckoutLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CheckoutLocalService
 * @generated
 */
public class CheckoutLocalServiceWrapper implements CheckoutLocalService,
	ServiceWrapper<CheckoutLocalService> {
	public CheckoutLocalServiceWrapper(
		CheckoutLocalService checkoutLocalService) {
		_checkoutLocalService = checkoutLocalService;
	}

	/**
	* Adds the checkout to the database. Also notifies the appropriate model listeners.
	*
	* @param checkout the checkout
	* @return the checkout that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.ams.model.Checkout addCheckout(
		com.liferay.ams.model.Checkout checkout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.addCheckout(checkout);
	}

	/**
	* Creates a new checkout with the primary key. Does not add the checkout to the database.
	*
	* @param checkoutId the primary key for the new checkout
	* @return the new checkout
	*/
	public com.liferay.ams.model.Checkout createCheckout(long checkoutId) {
		return _checkoutLocalService.createCheckout(checkoutId);
	}

	/**
	* Deletes the checkout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param checkoutId the primary key of the checkout
	* @throws PortalException if a checkout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCheckout(long checkoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_checkoutLocalService.deleteCheckout(checkoutId);
	}

	/**
	* Deletes the checkout from the database. Also notifies the appropriate model listeners.
	*
	* @param checkout the checkout
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCheckout(com.liferay.ams.model.Checkout checkout)
		throws com.liferay.portal.kernel.exception.SystemException {
		_checkoutLocalService.deleteCheckout(checkout);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.ams.model.Checkout fetchCheckout(long checkoutId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.fetchCheckout(checkoutId);
	}

	/**
	* Returns the checkout with the primary key.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout
	* @throws PortalException if a checkout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.ams.model.Checkout getCheckout(long checkoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.getCheckout(checkoutId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.ams.model.Checkout> getCheckouts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.getCheckouts(start, end);
	}

	/**
	* Returns the number of checkouts.
	*
	* @return the number of checkouts
	* @throws SystemException if a system exception occurred
	*/
	public int getCheckoutsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.getCheckoutsCount();
	}

	/**
	* Updates the checkout in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param checkout the checkout
	* @return the checkout that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.ams.model.Checkout updateCheckout(
		com.liferay.ams.model.Checkout checkout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.updateCheckout(checkout);
	}

	/**
	* Updates the checkout in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param checkout the checkout
	* @param merge whether to merge the checkout with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the checkout that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.ams.model.Checkout updateCheckout(
		com.liferay.ams.model.Checkout checkout, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkoutLocalService.updateCheckout(checkout, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _checkoutLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_checkoutLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CheckoutLocalService getWrappedCheckoutLocalService() {
		return _checkoutLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCheckoutLocalService(
		CheckoutLocalService checkoutLocalService) {
		_checkoutLocalService = checkoutLocalService;
	}

	public CheckoutLocalService getWrappedService() {
		return _checkoutLocalService;
	}

	public void setWrappedService(CheckoutLocalService checkoutLocalService) {
		_checkoutLocalService = checkoutLocalService;
	}

	private CheckoutLocalService _checkoutLocalService;
}