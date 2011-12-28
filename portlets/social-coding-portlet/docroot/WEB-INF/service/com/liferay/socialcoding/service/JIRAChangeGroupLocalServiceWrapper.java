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
 * This class is a wrapper for {@link JIRAChangeGroupLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAChangeGroupLocalService
 * @generated
 */
public class JIRAChangeGroupLocalServiceWrapper
	implements JIRAChangeGroupLocalService,
		ServiceWrapper<JIRAChangeGroupLocalService> {
	public JIRAChangeGroupLocalServiceWrapper(
		JIRAChangeGroupLocalService jiraChangeGroupLocalService) {
		_jiraChangeGroupLocalService = jiraChangeGroupLocalService;
	}

	/**
	* Adds the j i r a change group to the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeGroup the j i r a change group
	* @return the j i r a change group that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup addJIRAChangeGroup(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.addJIRAChangeGroup(jiraChangeGroup);
	}

	/**
	* Creates a new j i r a change group with the primary key. Does not add the j i r a change group to the database.
	*
	* @param jiraChangeGroupId the primary key for the new j i r a change group
	* @return the new j i r a change group
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup createJIRAChangeGroup(
		long jiraChangeGroupId) {
		return _jiraChangeGroupLocalService.createJIRAChangeGroup(jiraChangeGroupId);
	}

	/**
	* Deletes the j i r a change group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group
	* @throws PortalException if a j i r a change group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteJIRAChangeGroup(long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_jiraChangeGroupLocalService.deleteJIRAChangeGroup(jiraChangeGroupId);
	}

	/**
	* Deletes the j i r a change group from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeGroup the j i r a change group
	* @throws SystemException if a system exception occurred
	*/
	public void deleteJIRAChangeGroup(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		_jiraChangeGroupLocalService.deleteJIRAChangeGroup(jiraChangeGroup);
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
		return _jiraChangeGroupLocalService.dynamicQuery(dynamicQuery);
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
		return _jiraChangeGroupLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
		return _jiraChangeGroupLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _jiraChangeGroupLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.socialcoding.model.JIRAChangeGroup fetchJIRAChangeGroup(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.fetchJIRAChangeGroup(jiraChangeGroupId);
	}

	/**
	* Returns the j i r a change group with the primary key.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group
	* @return the j i r a change group
	* @throws PortalException if a j i r a change group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup getJIRAChangeGroup(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.getJIRAChangeGroup(jiraChangeGroupId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the j i r a change groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of j i r a change groups
	* @param end the upper bound of the range of j i r a change groups (not inclusive)
	* @return the range of j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> getJIRAChangeGroups(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.getJIRAChangeGroups(start, end);
	}

	/**
	* Returns the number of j i r a change groups.
	*
	* @return the number of j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public int getJIRAChangeGroupsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.getJIRAChangeGroupsCount();
	}

	/**
	* Updates the j i r a change group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeGroup the j i r a change group
	* @return the j i r a change group that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup updateJIRAChangeGroup(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.updateJIRAChangeGroup(jiraChangeGroup);
	}

	/**
	* Updates the j i r a change group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeGroup the j i r a change group
	* @param merge whether to merge the j i r a change group with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the j i r a change group that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup updateJIRAChangeGroup(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.updateJIRAChangeGroup(jiraChangeGroup,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _jiraChangeGroupLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_jiraChangeGroupLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public JIRAChangeGroupLocalService getWrappedJIRAChangeGroupLocalService() {
		return _jiraChangeGroupLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedJIRAChangeGroupLocalService(
		JIRAChangeGroupLocalService jiraChangeGroupLocalService) {
		_jiraChangeGroupLocalService = jiraChangeGroupLocalService;
	}

	public JIRAChangeGroupLocalService getWrappedService() {
		return _jiraChangeGroupLocalService;
	}

	public void setWrappedService(
		JIRAChangeGroupLocalService jiraChangeGroupLocalService) {
		_jiraChangeGroupLocalService = jiraChangeGroupLocalService;
	}

	private JIRAChangeGroupLocalService _jiraChangeGroupLocalService;
}