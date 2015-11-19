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

package com.liferay.testtransaction.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BarLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BarLocalService
 * @generated
 */
@ProviderType
public class BarLocalServiceWrapper implements BarLocalService,
	ServiceWrapper<BarLocalService> {
	public BarLocalServiceWrapper(BarLocalService barLocalService) {
		_barLocalService = barLocalService;
	}

	/**
	* Adds the bar to the database. Also notifies the appropriate model listeners.
	*
	* @param bar the bar
	* @return the bar that was added
	*/
	@Override
	public com.liferay.testtransaction.model.Bar addBar(
		com.liferay.testtransaction.model.Bar bar) {
		return _barLocalService.addBar(bar);
	}

	@Override
	public void addBarAndClassName_PortalRollback(java.lang.String text) {
		_barLocalService.addBarAndClassName_PortalRollback(text);
	}

	@Override
	public void addBarAndClassName_PortletRollback(java.lang.String text) {
		_barLocalService.addBarAndClassName_PortletRollback(text);
	}

	@Override
	public void addBar_Rollback(java.lang.String text) {
		_barLocalService.addBar_Rollback(text);
	}

	@Override
	public com.liferay.testtransaction.model.Bar addBar_Success(
		java.lang.String text) {
		return _barLocalService.addBar_Success(text);
	}

	/**
	* Creates a new bar with the primary key. Does not add the bar to the database.
	*
	* @param barId the primary key for the new bar
	* @return the new bar
	*/
	@Override
	public com.liferay.testtransaction.model.Bar createBar(long barId) {
		return _barLocalService.createBar(barId);
	}

	/**
	* Deletes the bar from the database. Also notifies the appropriate model listeners.
	*
	* @param bar the bar
	* @return the bar that was removed
	*/
	@Override
	public com.liferay.testtransaction.model.Bar deleteBar(
		com.liferay.testtransaction.model.Bar bar) {
		return _barLocalService.deleteBar(bar);
	}

	/**
	* Deletes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param barId the primary key of the bar
	* @return the bar that was removed
	* @throws PortalException if a bar with the primary key could not be found
	*/
	@Override
	public com.liferay.testtransaction.model.Bar deleteBar(long barId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _barLocalService.deleteBar(barId);
	}

	@Override
	public void deleteBarAndClassName(com.liferay.testtransaction.model.Bar bar)
		throws com.liferay.portal.kernel.exception.PortalException {
		_barLocalService.deleteBarAndClassName(bar);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _barLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _barLocalService.dynamicQuery();
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
		return _barLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testtransaction.model.impl.BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _barLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testtransaction.model.impl.BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _barLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _barLocalService.dynamicQueryCount(dynamicQuery);
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
		return _barLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.testtransaction.model.Bar fetchBar(long barId) {
		return _barLocalService.fetchBar(barId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _barLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the bar with the primary key.
	*
	* @param barId the primary key of the bar
	* @return the bar
	* @throws PortalException if a bar with the primary key could not be found
	*/
	@Override
	public com.liferay.testtransaction.model.Bar getBar(long barId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _barLocalService.getBar(barId);
	}

	@Override
	public com.liferay.testtransaction.model.Bar getBar(java.lang.String text)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _barLocalService.getBar(text);
	}

	/**
	* Returns a range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testtransaction.model.impl.BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of bars
	*/
	@Override
	public java.util.List<com.liferay.testtransaction.model.Bar> getBars(
		int start, int end) {
		return _barLocalService.getBars(start, end);
	}

	/**
	* Returns the number of bars.
	*
	* @return the number of bars
	*/
	@Override
	public int getBarsCount() {
		return _barLocalService.getBarsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _barLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _barLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _barLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasBar(java.lang.String text) {
		return _barLocalService.hasBar(text);
	}

	@Override
	public boolean hasClassName() {
		return _barLocalService.hasClassName();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _barLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public void testAddClassNameAndBar_Success(java.lang.String text) {
		_barLocalService.testAddClassNameAndBar_Success(text);
	}

	/**
	* Updates the bar in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param bar the bar
	* @return the bar that was updated
	*/
	@Override
	public com.liferay.testtransaction.model.Bar updateBar(
		com.liferay.testtransaction.model.Bar bar) {
		return _barLocalService.updateBar(bar);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public BarLocalService getWrappedBarLocalService() {
		return _barLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedBarLocalService(BarLocalService barLocalService) {
		_barLocalService = barLocalService;
	}

	@Override
	public BarLocalService getWrappedService() {
		return _barLocalService;
	}

	@Override
	public void setWrappedService(BarLocalService barLocalService) {
		_barLocalService = barLocalService;
	}

	private BarLocalService _barLocalService;
}