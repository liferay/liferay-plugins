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

package com.liferay.so.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProjectsEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProjectsEntryLocalService
 * @generated
 */
@ProviderType
public class ProjectsEntryLocalServiceWrapper
	implements ProjectsEntryLocalService,
		ServiceWrapper<ProjectsEntryLocalService> {
	public ProjectsEntryLocalServiceWrapper(
		ProjectsEntryLocalService projectsEntryLocalService) {
		_projectsEntryLocalService = projectsEntryLocalService;
	}

	/**
	* Adds the projects entry to the database. Also notifies the appropriate model listeners.
	*
	* @param projectsEntry the projects entry
	* @return the projects entry that was added
	*/
	@Override
	public com.liferay.so.model.ProjectsEntry addProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry) {
		return _projectsEntryLocalService.addProjectsEntry(projectsEntry);
	}

	@Override
	public com.liferay.so.model.ProjectsEntry addProjectsEntry(long userId,
		java.lang.String title, java.lang.String description,
		int startDateMonth, int startDateDay, int startDateYear,
		int endDateMonth, int endDateDay, int endDateYear, boolean current,
		java.lang.String data)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectsEntryLocalService.addProjectsEntry(userId, title,
			description, startDateMonth, startDateDay, startDateYear,
			endDateMonth, endDateDay, endDateYear, current, data);
	}

	/**
	* Creates a new projects entry with the primary key. Does not add the projects entry to the database.
	*
	* @param projectsEntryId the primary key for the new projects entry
	* @return the new projects entry
	*/
	@Override
	public com.liferay.so.model.ProjectsEntry createProjectsEntry(
		long projectsEntryId) {
		return _projectsEntryLocalService.createProjectsEntry(projectsEntryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectsEntryLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the projects entry from the database. Also notifies the appropriate model listeners.
	*
	* @param projectsEntry the projects entry
	* @return the projects entry that was removed
	*/
	@Override
	public com.liferay.so.model.ProjectsEntry deleteProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry) {
		return _projectsEntryLocalService.deleteProjectsEntry(projectsEntry);
	}

	/**
	* Deletes the projects entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectsEntryId the primary key of the projects entry
	* @return the projects entry that was removed
	* @throws PortalException if a projects entry with the primary key could not be found
	*/
	@Override
	public com.liferay.so.model.ProjectsEntry deleteProjectsEntry(
		long projectsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectsEntryLocalService.deleteProjectsEntry(projectsEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _projectsEntryLocalService.dynamicQuery();
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
		return _projectsEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectsEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _projectsEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _projectsEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _projectsEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.so.model.ProjectsEntry fetchProjectsEntry(
		long projectsEntryId) {
		return _projectsEntryLocalService.fetchProjectsEntry(projectsEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _projectsEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _projectsEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _projectsEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectsEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the projects entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @return the range of projects entries
	*/
	@Override
	public java.util.List<com.liferay.so.model.ProjectsEntry> getProjectsEntries(
		int start, int end) {
		return _projectsEntryLocalService.getProjectsEntries(start, end);
	}

	/**
	* Returns the number of projects entries.
	*
	* @return the number of projects entries
	*/
	@Override
	public int getProjectsEntriesCount() {
		return _projectsEntryLocalService.getProjectsEntriesCount();
	}

	/**
	* Returns the projects entry with the primary key.
	*
	* @param projectsEntryId the primary key of the projects entry
	* @return the projects entry
	* @throws PortalException if a projects entry with the primary key could not be found
	*/
	@Override
	public com.liferay.so.model.ProjectsEntry getProjectsEntry(
		long projectsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectsEntryLocalService.getProjectsEntry(projectsEntryId);
	}

	@Override
	public java.util.List<com.liferay.so.model.ProjectsEntry> getUserProjectsEntries(
		long userId) {
		return _projectsEntryLocalService.getUserProjectsEntries(userId);
	}

	@Override
	public int getUserProjectsEntriesCount(long userId) {
		return _projectsEntryLocalService.getUserProjectsEntriesCount(userId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _projectsEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Updates the projects entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectsEntry the projects entry
	* @return the projects entry that was updated
	*/
	@Override
	public com.liferay.so.model.ProjectsEntry updateProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry) {
		return _projectsEntryLocalService.updateProjectsEntry(projectsEntry);
	}

	@Override
	public com.liferay.so.model.ProjectsEntry updateProjectsEntry(
		long projectsEntryId, java.lang.String title,
		java.lang.String description, int startDateMonth, int startDateDay,
		int startDateYear, int endDateMonth, int endDateDay, int endDateYear,
		boolean current, java.lang.String data)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _projectsEntryLocalService.updateProjectsEntry(projectsEntryId,
			title, description, startDateMonth, startDateDay, startDateYear,
			endDateMonth, endDateDay, endDateYear, current, data);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public ProjectsEntryLocalService getWrappedProjectsEntryLocalService() {
		return _projectsEntryLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedProjectsEntryLocalService(
		ProjectsEntryLocalService projectsEntryLocalService) {
		_projectsEntryLocalService = projectsEntryLocalService;
	}

	@Override
	public ProjectsEntryLocalService getWrappedService() {
		return _projectsEntryLocalService;
	}

	@Override
	public void setWrappedService(
		ProjectsEntryLocalService projectsEntryLocalService) {
		_projectsEntryLocalService = projectsEntryLocalService;
	}

	private ProjectsEntryLocalService _projectsEntryLocalService;
}