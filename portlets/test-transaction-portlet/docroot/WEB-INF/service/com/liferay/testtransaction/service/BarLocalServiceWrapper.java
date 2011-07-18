/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

/**
 * <p>
 * This class is a wrapper for {@link BarLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       BarLocalService
 * @generated
 */
public class BarLocalServiceWrapper implements BarLocalService {
	public BarLocalServiceWrapper(BarLocalService barLocalService) {
		_barLocalService = barLocalService;
	}

	/**
	* Adds the bar to the database. Also notifies the appropriate model listeners.
	*
	* @param bar the bar
	* @return the bar that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testtransaction.model.Bar addBar(
		com.liferay.testtransaction.model.Bar bar)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _barLocalService.addBar(bar);
	}

	/**
	* Creates a new bar with the primary key. Does not add the bar to the database.
	*
	* @param barId the primary key for the new bar
	* @return the new bar
	*/
	public com.liferay.testtransaction.model.Bar createBar(long barId) {
		return _barLocalService.createBar(barId);
	}

	/**
	* Deletes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param barId the primary key of the bar
	* @throws PortalException if a bar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteBar(long barId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_barLocalService.deleteBar(barId);
	}

	/**
	* Deletes the bar from the database. Also notifies the appropriate model listeners.
	*
	* @param bar the bar
	* @throws SystemException if a system exception occurred
	*/
	public void deleteBar(com.liferay.testtransaction.model.Bar bar)
		throws com.liferay.portal.kernel.exception.SystemException {
		_barLocalService.deleteBar(bar);
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
		return _barLocalService.dynamicQuery(dynamicQuery);
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
		return _barLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _barLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _barLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the bar with the primary key.
	*
	* @param barId the primary key of the bar
	* @return the bar
	* @throws PortalException if a bar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testtransaction.model.Bar getBar(long barId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _barLocalService.getBar(barId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _barLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of bars
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testtransaction.model.Bar> getBars(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _barLocalService.getBars(start, end);
	}

	/**
	* Returns the number of bars.
	*
	* @return the number of bars
	* @throws SystemException if a system exception occurred
	*/
	public int getBarsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _barLocalService.getBarsCount();
	}

	/**
	* Updates the bar in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param bar the bar
	* @return the bar that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testtransaction.model.Bar updateBar(
		com.liferay.testtransaction.model.Bar bar)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _barLocalService.updateBar(bar);
	}

	/**
	* Updates the bar in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param bar the bar
	* @param merge whether to merge the bar with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the bar that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testtransaction.model.Bar updateBar(
		com.liferay.testtransaction.model.Bar bar, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _barLocalService.updateBar(bar, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _barLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_barLocalService.setBeanIdentifier(beanIdentifier);
	}

	public void addBar_Rollback(java.lang.String text)
		throws com.liferay.portal.kernel.exception.SystemException {
		_barLocalService.addBar_Rollback(text);
	}

	public com.liferay.testtransaction.model.Bar addBar_Success(
		java.lang.String text)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _barLocalService.addBar_Success(text);
	}

	public void addBarAndClassName_PortalRollback(java.lang.String text)
		throws com.liferay.portal.kernel.exception.SystemException {
		_barLocalService.addBarAndClassName_PortalRollback(text);
	}

	public void addBarAndClassName_PortletRollback(java.lang.String text)
		throws com.liferay.portal.kernel.exception.SystemException {
		_barLocalService.addBarAndClassName_PortletRollback(text);
	}

	public void deleteBarAndClassName(com.liferay.testtransaction.model.Bar bar)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_barLocalService.deleteBarAndClassName(bar);
	}

	public com.liferay.testtransaction.model.Bar getBar(java.lang.String text)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _barLocalService.getBar(text);
	}

	public boolean hasBar(java.lang.String text)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _barLocalService.hasBar(text);
	}

	public boolean hasClassName()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _barLocalService.hasClassName();
	}

	public void testAddClassNameAndBar_Success(java.lang.String text)
		throws com.liferay.portal.kernel.exception.SystemException {
		_barLocalService.testAddClassNameAndBar_Success(text);
	}

	public BarLocalService getWrappedBarLocalService() {
		return _barLocalService;
	}

	public void setWrappedBarLocalService(BarLocalService barLocalService) {
		_barLocalService = barLocalService;
	}

	private BarLocalService _barLocalService;
}