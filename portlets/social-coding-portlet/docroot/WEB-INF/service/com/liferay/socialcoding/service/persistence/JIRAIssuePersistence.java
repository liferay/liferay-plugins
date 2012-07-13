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

package com.liferay.socialcoding.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.socialcoding.model.JIRAIssue;

/**
 * The persistence interface for the j i r a issue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssuePersistenceImpl
 * @see JIRAIssueUtil
 * @generated
 */
public interface JIRAIssuePersistence extends BasePersistence<JIRAIssue> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAIssueUtil} to access the j i r a issue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the j i r a issue in the entity cache if it is enabled.
	*
	* @param jiraIssue the j i r a issue
	*/
	public void cacheResult(com.liferay.socialcoding.model.JIRAIssue jiraIssue);

	/**
	* Caches the j i r a issues in the entity cache if it is enabled.
	*
	* @param jiraIssues the j i r a issues
	*/
	public void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAIssue> jiraIssues);

	/**
	* Creates a new j i r a issue with the primary key. Does not add the j i r a issue to the database.
	*
	* @param jiraIssueId the primary key for the new j i r a issue
	* @return the new j i r a issue
	*/
	public com.liferay.socialcoding.model.JIRAIssue create(long jiraIssueId);

	/**
	* Removes the j i r a issue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue that was removed
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue remove(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue updateImpl(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a issue with the primary key or throws a {@link com.liferay.socialcoding.NoSuchJIRAIssueException} if it could not be found.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByPrimaryKey(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the j i r a issue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue, or <code>null</code> if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByPrimaryKey(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the j i r a issues where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByProjectId(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a issues where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByProjectId(
		long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a issues where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByProjectId(
		long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByProjectId_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByProjectId_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByProjectId_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByProjectId_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue[] findByProjectId_PrevAndNext(
		long jiraIssueId, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the j i r a issue where key = &#63; or throws a {@link com.liferay.socialcoding.NoSuchJIRAIssueException} if it could not be found.
	*
	* @param key the key
	* @return the matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the j i r a issue where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a issue where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the j i r a issues where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a issues where reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a issues where reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByReporterJiraUserId_First(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByReporterJiraUserId_First(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByReporterJiraUserId_Last(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByReporterJiraUserId_Last(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue[] findByReporterJiraUserId_PrevAndNext(
		long jiraIssueId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns all the j i r a issues where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a issues where assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a issues where assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByAssigneeJiraUserId_First(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByAssigneeJiraUserId_First(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByAssigneeJiraUserId_Last(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByAssigneeJiraUserId_Last(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue[] findByAssigneeJiraUserId_PrevAndNext(
		long jiraIssueId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByMD_P_First(
		java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByMD_P_First(
		java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByMD_P_Last(
		java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByMD_P_Last(
		java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue[] findByMD_P_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_First(
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByP_RJUI_First(
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_Last(
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByP_RJUI_Last(
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue[] findByP_RJUI_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_First(
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByP_AJUI_First(
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_Last(
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByP_AJUI_Last(
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue[] findByP_AJUI_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByMD_P_RJUI_First(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByMD_P_RJUI_First(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByMD_P_RJUI_Last(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByMD_P_RJUI_Last(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue[] findByMD_P_RJUI_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByMD_P_AJUI_First(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByMD_P_AJUI_First(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByMD_P_AJUI_Last(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByMD_P_AJUI_Last(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue[] findByMD_P_AJUI_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_S_First(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByP_RJUI_S_First(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_S_Last(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByP_RJUI_S_Last(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue[] findByP_RJUI_S_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_S_First(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByP_AJUI_S_First(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_S_Last(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue fetchByP_AJUI_S_Last(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue[] findByP_AJUI_S_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns all the j i r a issues.
	*
	* @return the j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a issues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a issues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a issues where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the j i r a issue where key = &#63; from the database.
	*
	* @param key the key
	* @return the j i r a issue that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAIssue removeByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Removes all the j i r a issues where reporterJiraUserId = &#63; from the database.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByReporterJiraUserId(java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a issues where assigneeJiraUserId = &#63; from the database.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAssigneeJiraUserId(java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByMD_P(java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; from the database.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByP_RJUI(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; from the database.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByP_AJUI(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByMD_P_RJUI(java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByMD_P_AJUI(java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63; from the database.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63; from the database.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a issues from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a issues where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public int countByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a issues where key = &#63;.
	*
	* @param key the key
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a issues where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public int countByReporterJiraUserId(java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a issues where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public int countByAssigneeJiraUserId(java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public int countByMD_P(java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public int countByP_RJUI(long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public int countByP_AJUI(long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public int countByMD_P_RJUI(java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public int countByMD_P_AJUI(java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public int countByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public int countByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a issues.
	*
	* @return the number of j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}