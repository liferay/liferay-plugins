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

package com.liferay.socialcoding.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link JIRAProjectLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAProjectLocalService
 * @generated
 */
public class JIRAProjectLocalServiceWrapper implements JIRAProjectLocalService,
	ServiceWrapper<JIRAProjectLocalService> {
	public JIRAProjectLocalServiceWrapper(
		JIRAProjectLocalService jiraProjectLocalService) {
		_jiraProjectLocalService = jiraProjectLocalService;
	}

	/**
	* Adds the j i r a project to the database. Also notifies the appropriate model listeners.
	*
	* @param jiraProject the j i r a project
	* @return the j i r a project that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAProject addJIRAProject(
		com.liferay.socialcoding.model.JIRAProject jiraProject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraProjectLocalService.addJIRAProject(jiraProject);
	}

	/**
	* Creates a new j i r a project with the primary key. Does not add the j i r a project to the database.
	*
	* @param jiraProjectId the primary key for the new j i r a project
	* @return the new j i r a project
	*/
	public com.liferay.socialcoding.model.JIRAProject createJIRAProject(
		long jiraProjectId) {
		return _jiraProjectLocalService.createJIRAProject(jiraProjectId);
	}

	/**
	* Deletes the j i r a project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraProjectId the primary key of the j i r a project
	* @return the j i r a project that was removed
	* @throws PortalException if a j i r a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAProject deleteJIRAProject(
		long jiraProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _jiraProjectLocalService.deleteJIRAProject(jiraProjectId);
	}

	/**
	* Deletes the j i r a project from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraProject the j i r a project
	* @return the j i r a project that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAProject deleteJIRAProject(
		com.liferay.socialcoding.model.JIRAProject jiraProject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraProjectLocalService.deleteJIRAProject(jiraProject);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _jiraProjectLocalService.dynamicQuery();
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
		return _jiraProjectLocalService.dynamicQuery(dynamicQuery);
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
		return _jiraProjectLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _jiraProjectLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _jiraProjectLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.socialcoding.model.JIRAProject fetchJIRAProject(
		long jiraProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraProjectLocalService.fetchJIRAProject(jiraProjectId);
	}

	/**
	* Returns the j i r a project with the primary key.
	*
	* @param jiraProjectId the primary key of the j i r a project
	* @return the j i r a project
	* @throws PortalException if a j i r a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAProject getJIRAProject(
		long jiraProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _jiraProjectLocalService.getJIRAProject(jiraProjectId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _jiraProjectLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the j i r a projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of j i r a projects
	* @param end the upper bound of the range of j i r a projects (not inclusive)
	* @return the range of j i r a projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAProject> getJIRAProjects(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraProjectLocalService.getJIRAProjects(start, end);
	}

	/**
	* Returns the number of j i r a projects.
	*
	* @return the number of j i r a projects
	* @throws SystemException if a system exception occurred
	*/
	public int getJIRAProjectsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraProjectLocalService.getJIRAProjectsCount();
	}

	/**
	* Updates the j i r a project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraProject the j i r a project
	* @return the j i r a project that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAProject updateJIRAProject(
		com.liferay.socialcoding.model.JIRAProject jiraProject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraProjectLocalService.updateJIRAProject(jiraProject);
	}

	/**
	* Updates the j i r a project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraProject the j i r a project
	* @param merge whether to merge the j i r a project with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the j i r a project that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAProject updateJIRAProject(
		com.liferay.socialcoding.model.JIRAProject jiraProject, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraProjectLocalService.updateJIRAProject(jiraProject, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _jiraProjectLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_jiraProjectLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _jiraProjectLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public JIRAProjectLocalService getWrappedJIRAProjectLocalService() {
		return _jiraProjectLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedJIRAProjectLocalService(
		JIRAProjectLocalService jiraProjectLocalService) {
		_jiraProjectLocalService = jiraProjectLocalService;
	}

	public JIRAProjectLocalService getWrappedService() {
		return _jiraProjectLocalService;
	}

	public void setWrappedService(
		JIRAProjectLocalService jiraProjectLocalService) {
		_jiraProjectLocalService = jiraProjectLocalService;
	}

	private JIRAProjectLocalService _jiraProjectLocalService;
}