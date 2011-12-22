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

package com.liferay.socialcoding.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link JIRAActionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAActionLocalService
 * @generated
 */
public class JIRAActionLocalServiceWrapper implements JIRAActionLocalService,
	ServiceWrapper<JIRAActionLocalService> {
	public JIRAActionLocalServiceWrapper(
		JIRAActionLocalService jiraActionLocalService) {
		_jiraActionLocalService = jiraActionLocalService;
	}

	/**
	* Adds the j i r a action to the database. Also notifies the appropriate model listeners.
	*
	* @param jiraAction the j i r a action
	* @return the j i r a action that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction addJIRAAction(
		com.liferay.socialcoding.model.JIRAAction jiraAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.addJIRAAction(jiraAction);
	}

	/**
	* Creates a new j i r a action with the primary key. Does not add the j i r a action to the database.
	*
	* @param jiraActionId the primary key for the new j i r a action
	* @return the new j i r a action
	*/
	public com.liferay.socialcoding.model.JIRAAction createJIRAAction(
		long jiraActionId) {
		return _jiraActionLocalService.createJIRAAction(jiraActionId);
	}

	/**
	* Deletes the j i r a action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraActionId the primary key of the j i r a action
	* @throws PortalException if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteJIRAAction(long jiraActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_jiraActionLocalService.deleteJIRAAction(jiraActionId);
	}

	/**
	* Deletes the j i r a action from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraAction the j i r a action
	* @throws SystemException if a system exception occurred
	*/
	public void deleteJIRAAction(
		com.liferay.socialcoding.model.JIRAAction jiraAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		_jiraActionLocalService.deleteJIRAAction(jiraAction);
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
		return _jiraActionLocalService.dynamicQuery(dynamicQuery);
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
		return _jiraActionLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _jiraActionLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _jiraActionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.socialcoding.model.JIRAAction fetchJIRAAction(
		long jiraActionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.fetchJIRAAction(jiraActionId);
	}

	/**
	* Returns the j i r a action with the primary key.
	*
	* @param jiraActionId the primary key of the j i r a action
	* @return the j i r a action
	* @throws PortalException if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction getJIRAAction(
		long jiraActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.getJIRAAction(jiraActionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the j i r a actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @return the range of j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAAction> getJIRAActions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.getJIRAActions(start, end);
	}

	/**
	* Returns the number of j i r a actions.
	*
	* @return the number of j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public int getJIRAActionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.getJIRAActionsCount();
	}

	/**
	* Updates the j i r a action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraAction the j i r a action
	* @return the j i r a action that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction updateJIRAAction(
		com.liferay.socialcoding.model.JIRAAction jiraAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.updateJIRAAction(jiraAction);
	}

	/**
	* Updates the j i r a action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraAction the j i r a action
	* @param merge whether to merge the j i r a action with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the j i r a action that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction updateJIRAAction(
		com.liferay.socialcoding.model.JIRAAction jiraAction, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.updateJIRAAction(jiraAction, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _jiraActionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_jiraActionLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public JIRAActionLocalService getWrappedJIRAActionLocalService() {
		return _jiraActionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedJIRAActionLocalService(
		JIRAActionLocalService jiraActionLocalService) {
		_jiraActionLocalService = jiraActionLocalService;
	}

	public JIRAActionLocalService getWrappedService() {
		return _jiraActionLocalService;
	}

	public void setWrappedService(JIRAActionLocalService jiraActionLocalService) {
		_jiraActionLocalService = jiraActionLocalService;
	}

	private JIRAActionLocalService _jiraActionLocalService;
}