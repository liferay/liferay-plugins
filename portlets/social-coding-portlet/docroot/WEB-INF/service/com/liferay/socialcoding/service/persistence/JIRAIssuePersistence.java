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

package com.liferay.socialcoding.service.persistence;

import aQute.bnd.annotation.ProviderType;

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
 * @see com.liferay.socialcoding.service.persistence.impl.JIRAIssuePersistenceImpl
 * @see JIRAIssueUtil
 * @generated
 */
@ProviderType
public interface JIRAIssuePersistence extends BasePersistence<JIRAIssue> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAIssueUtil} to access the j i r a issue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the j i r a issues where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByProjectId(long projectId);

	/**
	* Returns a range of all the j i r a issues where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByProjectId(long projectId, int start,
		int end);

	/**
	* Returns an ordered range of all the j i r a issues where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByProjectId(long projectId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByProjectId_First(long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByProjectId_First(long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByProjectId_Last(long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByProjectId_Last(long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	*/
	public JIRAIssue[] findByProjectId_PrevAndNext(long jiraIssueId,
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Removes all the j i r a issues where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	*/
	public void removeByProjectId(long projectId);

	/**
	* Returns the number of j i r a issues where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching j i r a issues
	*/
	public int countByProjectId(long projectId);

	/**
	* Returns all the j i r a issues where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @return the matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId);

	/**
	* Returns a range of all the j i r a issues where reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId, int start, int end);

	/**
	* Returns an ordered range of all the j i r a issues where reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the first j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByReporterJiraUserId_First(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByReporterJiraUserId_First(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the last j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByReporterJiraUserId_Last(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByReporterJiraUserId_Last(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	*/
	public JIRAIssue[] findByReporterJiraUserId_PrevAndNext(long jiraIssueId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Removes all the j i r a issues where reporterJiraUserId = &#63; from the database.
	*
	* @param reporterJiraUserId the reporter jira user ID
	*/
	public void removeByReporterJiraUserId(java.lang.String reporterJiraUserId);

	/**
	* Returns the number of j i r a issues where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @return the number of matching j i r a issues
	*/
	public int countByReporterJiraUserId(java.lang.String reporterJiraUserId);

	/**
	* Returns all the j i r a issues where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId);

	/**
	* Returns a range of all the j i r a issues where assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId, int start, int end);

	/**
	* Returns an ordered range of all the j i r a issues where assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the first j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByAssigneeJiraUserId_First(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByAssigneeJiraUserId_First(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the last j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByAssigneeJiraUserId_Last(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByAssigneeJiraUserId_Last(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	*/
	public JIRAIssue[] findByAssigneeJiraUserId_PrevAndNext(long jiraIssueId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Removes all the j i r a issues where assigneeJiraUserId = &#63; from the database.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	*/
	public void removeByAssigneeJiraUserId(java.lang.String assigneeJiraUserId);

	/**
	* Returns the number of j i r a issues where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the number of matching j i r a issues
	*/
	public int countByAssigneeJiraUserId(java.lang.String assigneeJiraUserId);

	/**
	* Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @return the matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByMD_P(java.util.Date modifiedDate,
		long projectId);

	/**
	* Returns a range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByMD_P(java.util.Date modifiedDate,
		long projectId, int start, int end);

	/**
	* Returns an ordered range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByMD_P(java.util.Date modifiedDate,
		long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByMD_P_First(java.util.Date modifiedDate,
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByMD_P_First(java.util.Date modifiedDate,
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByMD_P_Last(java.util.Date modifiedDate,
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByMD_P_Last(java.util.Date modifiedDate,
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	*/
	public JIRAIssue[] findByMD_P_PrevAndNext(long jiraIssueId,
		java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	*/
	public void removeByMD_P(java.util.Date modifiedDate, long projectId);

	/**
	* Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @return the number of matching j i r a issues
	*/
	public int countByMD_P(java.util.Date modifiedDate, long projectId);

	/**
	* Returns all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @return the matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByP_RJUI(long projectId,
		java.lang.String reporterJiraUserId);

	/**
	* Returns a range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByP_RJUI(long projectId,
		java.lang.String reporterJiraUserId, int start, int end);

	/**
	* Returns an ordered range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByP_RJUI(long projectId,
		java.lang.String reporterJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByP_RJUI_First(long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByP_RJUI_First(long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByP_RJUI_Last(long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByP_RJUI_Last(long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	*/
	public JIRAIssue[] findByP_RJUI_PrevAndNext(long jiraIssueId,
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Removes all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; from the database.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	*/
	public void removeByP_RJUI(long projectId,
		java.lang.String reporterJiraUserId);

	/**
	* Returns the number of j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @return the number of matching j i r a issues
	*/
	public int countByP_RJUI(long projectId, java.lang.String reporterJiraUserId);

	/**
	* Returns all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByP_AJUI(long projectId,
		java.lang.String assigneeJiraUserId);

	/**
	* Returns a range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByP_AJUI(long projectId,
		java.lang.String assigneeJiraUserId, int start, int end);

	/**
	* Returns an ordered range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByP_AJUI(long projectId,
		java.lang.String assigneeJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByP_AJUI_First(long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByP_AJUI_First(long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByP_AJUI_Last(long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByP_AJUI_Last(long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	*/
	public JIRAIssue[] findByP_AJUI_PrevAndNext(long jiraIssueId,
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Removes all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; from the database.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	*/
	public void removeByP_AJUI(long projectId,
		java.lang.String assigneeJiraUserId);

	/**
	* Returns the number of j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the number of matching j i r a issues
	*/
	public int countByP_AJUI(long projectId, java.lang.String assigneeJiraUserId);

	/**
	* Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @return the matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId);

	/**
	* Returns a range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end);

	/**
	* Returns an ordered range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByMD_P_RJUI_First(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByMD_P_RJUI_First(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByMD_P_RJUI_Last(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByMD_P_RJUI_Last(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	*/
	public JIRAIssue[] findByMD_P_RJUI_PrevAndNext(long jiraIssueId,
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	*/
	public void removeByMD_P_RJUI(java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId);

	/**
	* Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @return the number of matching j i r a issues
	*/
	public int countByMD_P_RJUI(java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId);

	/**
	* Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId);

	/**
	* Returns a range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end);

	/**
	* Returns an ordered range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByMD_P_AJUI_First(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByMD_P_AJUI_First(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByMD_P_AJUI_Last(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByMD_P_AJUI_Last(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	*/
	public JIRAIssue[] findByMD_P_AJUI_PrevAndNext(long jiraIssueId,
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	*/
	public void removeByMD_P_AJUI(java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId);

	/**
	* Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the number of matching j i r a issues
	*/
	public int countByMD_P_AJUI(java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId);

	/**
	* Returns all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @return the matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status);

	/**
	* Returns a range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status,
		int start, int end);

	/**
	* Returns an ordered range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByP_RJUI_S_First(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByP_RJUI_S_First(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByP_RJUI_S_Last(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByP_RJUI_S_Last(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	*/
	public JIRAIssue[] findByP_RJUI_S_PrevAndNext(long jiraIssueId,
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Removes all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63; from the database.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	*/
	public void removeByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status);

	/**
	* Returns the number of j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @return the number of matching j i r a issues
	*/
	public int countByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status);

	/**
	* Returns all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @return the matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status);

	/**
	* Returns a range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status,
		int start, int end);

	/**
	* Returns an ordered range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a issues
	*/
	public java.util.List<JIRAIssue> findByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByP_AJUI_S_First(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByP_AJUI_S_First(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	*/
	public JIRAIssue findByP_AJUI_S_Last(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	*/
	public JIRAIssue fetchByP_AJUI_S_Last(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	*/
	public JIRAIssue[] findByP_AJUI_S_PrevAndNext(long jiraIssueId,
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Removes all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63; from the database.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	*/
	public void removeByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status);

	/**
	* Returns the number of j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @return the number of matching j i r a issues
	*/
	public int countByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status);

	/**
	* Caches the j i r a issue in the entity cache if it is enabled.
	*
	* @param jiraIssue the j i r a issue
	*/
	public void cacheResult(JIRAIssue jiraIssue);

	/**
	* Caches the j i r a issues in the entity cache if it is enabled.
	*
	* @param jiraIssues the j i r a issues
	*/
	public void cacheResult(java.util.List<JIRAIssue> jiraIssues);

	/**
	* Creates a new j i r a issue with the primary key. Does not add the j i r a issue to the database.
	*
	* @param jiraIssueId the primary key for the new j i r a issue
	* @return the new j i r a issue
	*/
	public JIRAIssue create(long jiraIssueId);

	/**
	* Removes the j i r a issue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue that was removed
	* @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	*/
	public JIRAIssue remove(long jiraIssueId)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	public JIRAIssue updateImpl(JIRAIssue jiraIssue);

	/**
	* Returns the j i r a issue with the primary key or throws a {@link NoSuchJIRAIssueException} if it could not be found.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue
	* @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	*/
	public JIRAIssue findByPrimaryKey(long jiraIssueId)
		throws com.liferay.socialcoding.NoSuchJIRAIssueException;

	/**
	* Returns the j i r a issue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue, or <code>null</code> if a j i r a issue with the primary key could not be found
	*/
	public JIRAIssue fetchByPrimaryKey(long jiraIssueId);

	@Override
	public java.util.Map<java.io.Serializable, JIRAIssue> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the j i r a issues.
	*
	* @return the j i r a issues
	*/
	public java.util.List<JIRAIssue> findAll();

	/**
	* Returns a range of all the j i r a issues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of j i r a issues
	*/
	public java.util.List<JIRAIssue> findAll(int start, int end);

	/**
	* Returns an ordered range of all the j i r a issues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of j i r a issues
	*/
	public java.util.List<JIRAIssue> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAIssue> orderByComparator);

	/**
	* Removes all the j i r a issues from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of j i r a issues.
	*
	* @return the number of j i r a issues
	*/
	public int countAll();
}