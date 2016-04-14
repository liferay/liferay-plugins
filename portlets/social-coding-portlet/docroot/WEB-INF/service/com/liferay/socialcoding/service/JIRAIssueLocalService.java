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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.socialcoding.model.JIRAIssue;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for JIRAIssue. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssueLocalServiceUtil
 * @see com.liferay.socialcoding.service.base.JIRAIssueLocalServiceBaseImpl
 * @see com.liferay.socialcoding.service.impl.JIRAIssueLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface JIRAIssueLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAIssueLocalServiceUtil} to access the j i r a issue local service. Add custom service methods to {@link com.liferay.socialcoding.service.impl.JIRAIssueLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Adds the j i r a issue to the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssue the j i r a issue
	* @return the j i r a issue that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public JIRAIssue addJIRAIssue(JIRAIssue jiraIssue);

	/**
	* Creates a new j i r a issue with the primary key. Does not add the j i r a issue to the database.
	*
	* @param jiraIssueId the primary key for the new j i r a issue
	* @return the new j i r a issue
	*/
	public JIRAIssue createJIRAIssue(long jiraIssueId);

	/**
	* Deletes the j i r a issue from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssue the j i r a issue
	* @return the j i r a issue that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public JIRAIssue deleteJIRAIssue(JIRAIssue jiraIssue);

	/**
	* Deletes the j i r a issue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue that was removed
	* @throws PortalException if a j i r a issue with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public JIRAIssue deleteJIRAIssue(long jiraIssueId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JIRAIssue fetchJIRAIssue(long jiraIssueId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JIRAIssue getFirstAssigneeJIRAIssue(long projectId,
		java.lang.String assigneeJiraUserId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JIRAIssue getFirstReporterJIRAIssue(long projectId,
		java.lang.String reporterJiraUserId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JIRAIssue getJIRAIssue(java.lang.String key)
		throws PortalException;

	/**
	* Returns the j i r a issue with the primary key.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue
	* @throws PortalException if a j i r a issue with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JIRAIssue getJIRAIssue(long jiraIssueId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JIRAIssue getLastAssigneeJIRAIssue(long projectId,
		java.lang.String assigneeJiraUserId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JIRAIssue getLastreporterJIRAIssue(long projectId,
		java.lang.String reporterJiraUserId) throws PortalException;

	/**
	* Updates the j i r a issue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraIssue the j i r a issue
	* @return the j i r a issue that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public JIRAIssue updateJIRAIssue(JIRAIssue jiraIssue);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssigneeJIRAIssuesCount(Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status);

	/**
	* Returns the number of j i r a issues.
	*
	* @return the number of j i r a issues
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getJIRAIssuesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getReporterJIRAIssuesCount(Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JIRAIssue> getAssigneeJIRAIssues(Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JIRAIssue> getAssigneeJIRAIssues(long projectId,
		java.lang.String assigneeJiraUserId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JIRAIssue> getAssigneeJIRAIssues(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status,
		int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JIRAIssue> getJIRAIssues(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JIRAIssue> getReporterJIRAIssues(Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JIRAIssue> getReporterJIRAIssues(long projectId,
		java.lang.String reporterJiraUserId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JIRAIssue> getReporterJIRAIssues(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status,
		int start, int end);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void updateJIRAIssues(long projectId) throws PortalException;
}