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

package com.liferay.ams.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CheckoutLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CheckoutLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.ams.model.Checkout addCheckout(
		com.liferay.ams.model.Checkout checkout) {
		return _checkoutLocalService.addCheckout(checkout);
	}

	/**
	* Creates a new checkout with the primary key. Does not add the checkout to the database.
	*
	* @param checkoutId the primary key for the new checkout
	* @return the new checkout
	*/
	@Override
	public com.liferay.ams.model.Checkout createCheckout(long checkoutId) {
		return _checkoutLocalService.createCheckout(checkoutId);
	}

	/**
	* Deletes the checkout from the database. Also notifies the appropriate model listeners.
	*
	* @param checkout the checkout
	* @return the checkout that was removed
	*/
	@Override
	public com.liferay.ams.model.Checkout deleteCheckout(
		com.liferay.ams.model.Checkout checkout) {
		return _checkoutLocalService.deleteCheckout(checkout);
	}

	/**
	* Deletes the checkout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout that was removed
	* @throws PortalException if a checkout with the primary key could not be found
	*/
	@Override
	public com.liferay.ams.model.Checkout deleteCheckout(long checkoutId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _checkoutLocalService.deleteCheckout(checkoutId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _checkoutLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _checkoutLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _checkoutLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.ams.model.impl.CheckoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _checkoutLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.ams.model.impl.CheckoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _checkoutLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _checkoutLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _checkoutLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.ams.model.Checkout fetchCheckout(long checkoutId) {
		return _checkoutLocalService.fetchCheckout(checkoutId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _checkoutLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the checkout with the primary key.
	*
	* @param checkoutId the primary key of the checkout
	* @return the checkout
	* @throws PortalException if a checkout with the primary key could not be found
	*/
	@Override
	public com.liferay.ams.model.Checkout getCheckout(long checkoutId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _checkoutLocalService.getCheckout(checkoutId);
	}

	/**
	* Returns a range of all the checkouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.ams.model.impl.CheckoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of checkouts
	* @param end the upper bound of the range of checkouts (not inclusive)
	* @return the range of checkouts
	*/
	@Override
	public java.util.List<com.liferay.ams.model.Checkout> getCheckouts(
		int start, int end) {
		return _checkoutLocalService.getCheckouts(start, end);
	}

	/**
	* Returns the number of checkouts.
	*
	* @return the number of checkouts
	*/
	@Override
	public int getCheckoutsCount() {
		return _checkoutLocalService.getCheckoutsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _checkoutLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _checkoutLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _checkoutLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _checkoutLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Updates the checkout in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param checkout the checkout
	* @return the checkout that was updated
	*/
	@Override
	public com.liferay.ams.model.Checkout updateCheckout(
		com.liferay.ams.model.Checkout checkout) {
		return _checkoutLocalService.updateCheckout(checkout);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public CheckoutLocalService getWrappedCheckoutLocalService() {
		return _checkoutLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedCheckoutLocalService(
		CheckoutLocalService checkoutLocalService) {
		_checkoutLocalService = checkoutLocalService;
	}

	@Override
	public CheckoutLocalService getWrappedService() {
		return _checkoutLocalService;
	}

	@Override
	public void setWrappedService(CheckoutLocalService checkoutLocalService) {
		_checkoutLocalService = checkoutLocalService;
	}

	private CheckoutLocalService _checkoutLocalService;
}