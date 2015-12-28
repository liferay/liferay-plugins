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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link JIRAIssueLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssueLocalService
 * @generated
 */
@ProviderType
public class JIRAIssueLocalServiceWrapper implements JIRAIssueLocalService,
	ServiceWrapper<JIRAIssueLocalService> {
	public JIRAIssueLocalServiceWrapper(
		JIRAIssueLocalService jiraIssueLocalService) {
		_jiraIssueLocalService = jiraIssueLocalService;
	}

	/**
	* Adds the j i r a issue to the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssue the j i r a issue
	* @return the j i r a issue that was added
	*/
	@Override
	public com.liferay.socialcoding.model.JIRAIssue addJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue) {
		return _jiraIssueLocalService.addJIRAIssue(jiraIssue);
	}

	/**
	* Creates a new j i r a issue with the primary key. Does not add the j i r a issue to the database.
	*
	* @param jiraIssueId the primary key for the new j i r a issue
	* @return the new j i r a issue
	*/
	@Override
	public com.liferay.socialcoding.model.JIRAIssue createJIRAIssue(
		long jiraIssueId) {
		return _jiraIssueLocalService.createJIRAIssue(jiraIssueId);
	}

	/**
	* Deletes the j i r a issue from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssue the j i r a issue
	* @return the j i r a issue that was removed
	*/
	@Override
	public com.liferay.socialcoding.model.JIRAIssue deleteJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue) {
		return _jiraIssueLocalService.deleteJIRAIssue(jiraIssue);
	}

	/**
	* Deletes the j i r a issue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue that was removed
	* @throws PortalException if a j i r a issue with the primary key could not be found
	*/
	@Override
	public com.liferay.socialcoding.model.JIRAIssue deleteJIRAIssue(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.deleteJIRAIssue(jiraIssueId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _jiraIssueLocalService.dynamicQuery();
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
		return _jiraIssueLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _jiraIssueLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _jiraIssueLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _jiraIssueLocalService.dynamicQueryCount(dynamicQuery);
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
		return _jiraIssueLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.socialcoding.model.JIRAIssue fetchJIRAIssue(
		long jiraIssueId) {
		return _jiraIssueLocalService.fetchJIRAIssue(jiraIssueId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _jiraIssueLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getAssigneeJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end) {
		return _jiraIssueLocalService.getAssigneeJIRAIssues(modifiedDate,
			projectId, assigneeJiraUserId, start, end);
	}

	@Override
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId, int start, int end) {
		return _jiraIssueLocalService.getAssigneeJIRAIssues(projectId,
			assigneeJiraUserId, start, end);
	}

	@Override
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end) {
		return _jiraIssueLocalService.getAssigneeJIRAIssues(projectId,
			assigneeJiraUserId, status, start, end);
	}

	@Override
	public int getAssigneeJIRAIssuesCount(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId) {
		return _jiraIssueLocalService.getAssigneeJIRAIssuesCount(modifiedDate,
			projectId, assigneeJiraUserId);
	}

	@Override
	public int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId) {
		return _jiraIssueLocalService.getAssigneeJIRAIssuesCount(projectId,
			assigneeJiraUserId);
	}

	@Override
	public int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status) {
		return _jiraIssueLocalService.getAssigneeJIRAIssuesCount(projectId,
			assigneeJiraUserId, status);
	}

	@Override
	public com.liferay.socialcoding.model.JIRAIssue getFirstAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.getFirstAssigneeJIRAIssue(projectId,
			assigneeJiraUserId);
	}

	@Override
	public com.liferay.socialcoding.model.JIRAIssue getFirstReporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.getFirstReporterJIRAIssue(projectId,
			reporterJiraUserId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _jiraIssueLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the j i r a issue with the primary key.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue
	* @throws PortalException if a j i r a issue with the primary key could not be found
	*/
	@Override
	public com.liferay.socialcoding.model.JIRAIssue getJIRAIssue(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.getJIRAIssue(jiraIssueId);
	}

	@Override
	public com.liferay.socialcoding.model.JIRAIssue getJIRAIssue(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.getJIRAIssue(key);
	}

	/**
	* Returns a range of all the j i r a issues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of j i r a issues
	*/
	@Override
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getJIRAIssues(
		int start, int end) {
		return _jiraIssueLocalService.getJIRAIssues(start, end);
	}

	/**
	* Returns the number of j i r a issues.
	*
	* @return the number of j i r a issues
	*/
	@Override
	public int getJIRAIssuesCount() {
		return _jiraIssueLocalService.getJIRAIssuesCount();
	}

	@Override
	public com.liferay.socialcoding.model.JIRAIssue getLastAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.getLastAssigneeJIRAIssue(projectId,
			assigneeJiraUserId);
	}

	@Override
	public com.liferay.socialcoding.model.JIRAIssue getLastreporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.getLastreporterJIRAIssue(projectId,
			reporterJiraUserId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _jiraIssueLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getReporterJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end) {
		return _jiraIssueLocalService.getReporterJIRAIssues(modifiedDate,
			projectId, reporterJiraUserId, start, end);
	}

	@Override
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId, int start, int end) {
		return _jiraIssueLocalService.getReporterJIRAIssues(projectId,
			reporterJiraUserId, start, end);
	}

	@Override
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end) {
		return _jiraIssueLocalService.getReporterJIRAIssues(projectId,
			reporterJiraUserId, status, start, end);
	}

	@Override
	public int getReporterJIRAIssuesCount(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId) {
		return _jiraIssueLocalService.getReporterJIRAIssuesCount(modifiedDate,
			projectId, reporterJiraUserId);
	}

	@Override
	public int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId) {
		return _jiraIssueLocalService.getReporterJIRAIssuesCount(projectId,
			reporterJiraUserId);
	}

	@Override
	public int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status) {
		return _jiraIssueLocalService.getReporterJIRAIssuesCount(projectId,
			reporterJiraUserId, status);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _jiraIssueLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Updates the j i r a issue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraIssue the j i r a issue
	* @return the j i r a issue that was updated
	*/
	@Override
	public com.liferay.socialcoding.model.JIRAIssue updateJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue) {
		return _jiraIssueLocalService.updateJIRAIssue(jiraIssue);
	}

	@Override
	public void updateJIRAIssues(long projectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_jiraIssueLocalService.updateJIRAIssues(projectId);
	}

	@Override
	public JIRAIssueLocalService getWrappedService() {
		return _jiraIssueLocalService;
	}

	@Override
	public void setWrappedService(JIRAIssueLocalService jiraIssueLocalService) {
		_jiraIssueLocalService = jiraIssueLocalService;
	}

	private JIRAIssueLocalService _jiraIssueLocalService;
}