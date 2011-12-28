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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialcoding.model.JIRAIssue;

import java.util.List;

/**
 * The persistence utility for the j i r a issue service. This utility wraps {@link JIRAIssuePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssuePersistence
 * @see JIRAIssuePersistenceImpl
 * @generated
 */
public class JIRAIssueUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(JIRAIssue jiraIssue) {
		getPersistence().clearCache(jiraIssue);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<JIRAIssue> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JIRAIssue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JIRAIssue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static JIRAIssue update(JIRAIssue jiraIssue, boolean merge)
		throws SystemException {
		return getPersistence().update(jiraIssue, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static JIRAIssue update(JIRAIssue jiraIssue, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(jiraIssue, merge, serviceContext);
	}

	/**
	* Caches the j i r a issue in the entity cache if it is enabled.
	*
	* @param jiraIssue the j i r a issue
	*/
	public static void cacheResult(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue) {
		getPersistence().cacheResult(jiraIssue);
	}

	/**
	* Caches the j i r a issues in the entity cache if it is enabled.
	*
	* @param jiraIssues the j i r a issues
	*/
	public static void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAIssue> jiraIssues) {
		getPersistence().cacheResult(jiraIssues);
	}

	/**
	* Creates a new j i r a issue with the primary key. Does not add the j i r a issue to the database.
	*
	* @param jiraIssueId the primary key for the new j i r a issue
	* @return the new j i r a issue
	*/
	public static com.liferay.socialcoding.model.JIRAIssue create(
		long jiraIssueId) {
		return getPersistence().create(jiraIssueId);
	}

	/**
	* Removes the j i r a issue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue that was removed
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue remove(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence().remove(jiraIssueId);
	}

	public static com.liferay.socialcoding.model.JIRAIssue updateImpl(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(jiraIssue, merge);
	}

	/**
	* Returns the j i r a issue with the primary key or throws a {@link com.liferay.socialcoding.NoSuchJIRAIssueException} if it could not be found.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByPrimaryKey(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence().findByPrimaryKey(jiraIssueId);
	}

	/**
	* Returns the j i r a issue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue, or <code>null</code> if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue fetchByPrimaryKey(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(jiraIssueId);
	}

	/**
	* Returns all the j i r a issues where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByProjectId(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByProjectId(projectId);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByProjectId(
		long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByProjectId(projectId, start, end);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByProjectId(
		long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByProjectId(projectId, start, end, orderByComparator);
	}

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByProjectId_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByProjectId_First(projectId, orderByComparator);
	}

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByProjectId_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByProjectId_Last(projectId, orderByComparator);
	}

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue[] findByProjectId_PrevAndNext(
		long jiraIssueId, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByProjectId_PrevAndNext(jiraIssueId, projectId,
			orderByComparator);
	}

	/**
	* Returns the j i r a issue where key = &#63; or throws a {@link com.liferay.socialcoding.NoSuchJIRAIssueException} if it could not be found.
	*
	* @param key the key
	* @return the matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence().findByKey(key);
	}

	/**
	* Returns the j i r a issue where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue fetchByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key);
	}

	/**
	* Returns the j i r a issue where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key, retrieveFromCache);
	}

	/**
	* Returns all the j i r a issues where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByReporterJiraUserId(reporterJiraUserId);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByReporterJiraUserId(reporterJiraUserId, start, end);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByReporterJiraUserId(reporterJiraUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByReporterJiraUserId_First(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByReporterJiraUserId_First(reporterJiraUserId,
			orderByComparator);
	}

	/**
	* Returns the last j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByReporterJiraUserId_Last(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByReporterJiraUserId_Last(reporterJiraUserId,
			orderByComparator);
	}

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue[] findByReporterJiraUserId_PrevAndNext(
		long jiraIssueId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByReporterJiraUserId_PrevAndNext(jiraIssueId,
			reporterJiraUserId, orderByComparator);
	}

	/**
	* Returns all the j i r a issues where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssigneeJiraUserId(assigneeJiraUserId);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssigneeJiraUserId(assigneeJiraUserId, start, end);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssigneeJiraUserId(assigneeJiraUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByAssigneeJiraUserId_First(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByAssigneeJiraUserId_First(assigneeJiraUserId,
			orderByComparator);
	}

	/**
	* Returns the last j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByAssigneeJiraUserId_Last(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByAssigneeJiraUserId_Last(assigneeJiraUserId,
			orderByComparator);
	}

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue[] findByAssigneeJiraUserId_PrevAndNext(
		long jiraIssueId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByAssigneeJiraUserId_PrevAndNext(jiraIssueId,
			assigneeJiraUserId, orderByComparator);
	}

	/**
	* Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMD_P(modifiedDate, projectId);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMD_P(modifiedDate, projectId, start, end);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P(modifiedDate, projectId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByMD_P_First(
		java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_First(modifiedDate, projectId, orderByComparator);
	}

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByMD_P_Last(
		java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_Last(modifiedDate, projectId, orderByComparator);
	}

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue[] findByMD_P_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_PrevAndNext(jiraIssueId, modifiedDate,
			projectId, orderByComparator);
	}

	/**
	* Returns all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_RJUI(projectId, reporterJiraUserId);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_RJUI(projectId, reporterJiraUserId, start, end);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_RJUI(projectId, reporterJiraUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_First(
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_First(projectId, reporterJiraUserId,
			orderByComparator);
	}

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_Last(
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_Last(projectId, reporterJiraUserId,
			orderByComparator);
	}

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue[] findByP_RJUI_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_PrevAndNext(jiraIssueId, projectId,
			reporterJiraUserId, orderByComparator);
	}

	/**
	* Returns all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_AJUI(projectId, assigneeJiraUserId);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_AJUI(projectId, assigneeJiraUserId, start, end);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_AJUI(projectId, assigneeJiraUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_First(
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_First(projectId, assigneeJiraUserId,
			orderByComparator);
	}

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_Last(
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_Last(projectId, assigneeJiraUserId,
			orderByComparator);
	}

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the primary key of the current j i r a issue
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue[] findByP_AJUI_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_PrevAndNext(jiraIssueId, projectId,
			assigneeJiraUserId, orderByComparator);
	}

	/**
	* Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P_RJUI(modifiedDate, projectId,
			reporterJiraUserId, start, end);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P_RJUI(modifiedDate, projectId,
			reporterJiraUserId, start, end, orderByComparator);
	}

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByMD_P_RJUI_First(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_RJUI_First(modifiedDate, projectId,
			reporterJiraUserId, orderByComparator);
	}

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByMD_P_RJUI_Last(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_RJUI_Last(modifiedDate, projectId,
			reporterJiraUserId, orderByComparator);
	}

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
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
	public static com.liferay.socialcoding.model.JIRAIssue[] findByMD_P_RJUI_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_RJUI_PrevAndNext(jiraIssueId, modifiedDate,
			projectId, reporterJiraUserId, orderByComparator);
	}

	/**
	* Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P_AJUI(modifiedDate, projectId,
			assigneeJiraUserId, start, end);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P_AJUI(modifiedDate, projectId,
			assigneeJiraUserId, start, end, orderByComparator);
	}

	/**
	* Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByMD_P_AJUI_First(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_AJUI_First(modifiedDate, projectId,
			assigneeJiraUserId, orderByComparator);
	}

	/**
	* Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByMD_P_AJUI_Last(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_AJUI_Last(modifiedDate, projectId,
			assigneeJiraUserId, orderByComparator);
	}

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
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
	public static com.liferay.socialcoding.model.JIRAIssue[] findByMD_P_AJUI_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_AJUI_PrevAndNext(jiraIssueId, modifiedDate,
			projectId, assigneeJiraUserId, orderByComparator);
	}

	/**
	* Returns all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_RJUI_S(projectId, reporterJiraUserId, status);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_RJUI_S(projectId, reporterJiraUserId, status,
			start, end);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_RJUI_S(projectId, reporterJiraUserId, status,
			start, end, orderByComparator);
	}

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_S_First(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_S_First(projectId, reporterJiraUserId, status,
			orderByComparator);
	}

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_S_Last(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_S_Last(projectId, reporterJiraUserId, status,
			orderByComparator);
	}

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
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
	public static com.liferay.socialcoding.model.JIRAIssue[] findByP_RJUI_S_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_S_PrevAndNext(jiraIssueId, projectId,
			reporterJiraUserId, status, orderByComparator);
	}

	/**
	* Returns all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @return the matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_AJUI_S(projectId, assigneeJiraUserId, status);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_AJUI_S(projectId, assigneeJiraUserId, status,
			start, end);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_AJUI_S(projectId, assigneeJiraUserId, status,
			start, end, orderByComparator);
	}

	/**
	* Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_S_First(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_S_First(projectId, assigneeJiraUserId, status,
			orderByComparator);
	}

	/**
	* Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a issue
	* @throws com.liferay.socialcoding.NoSuchJIRAIssueException if a matching j i r a issue could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_S_Last(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_S_Last(projectId, assigneeJiraUserId, status,
			orderByComparator);
	}

	/**
	* Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
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
	public static com.liferay.socialcoding.model.JIRAIssue[] findByP_AJUI_S_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_S_PrevAndNext(jiraIssueId, projectId,
			assigneeJiraUserId, status, orderByComparator);
	}

	/**
	* Returns all the j i r a issues.
	*
	* @return the j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the j i r a issues where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByProjectId(projectId);
	}

	/**
	* Removes the j i r a issue where key = &#63; from the database.
	*
	* @param key the key
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		getPersistence().removeByKey(key);
	}

	/**
	* Removes all the j i r a issues where reporterJiraUserId = &#63; from the database.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByReporterJiraUserId(
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByReporterJiraUserId(reporterJiraUserId);
	}

	/**
	* Removes all the j i r a issues where assigneeJiraUserId = &#63; from the database.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAssigneeJiraUserId(assigneeJiraUserId);
	}

	/**
	* Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByMD_P(java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByMD_P(modifiedDate, projectId);
	}

	/**
	* Removes all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; from the database.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_RJUI(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_RJUI(projectId, reporterJiraUserId);
	}

	/**
	* Removes all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; from the database.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_AJUI(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_AJUI(projectId, assigneeJiraUserId);
	}

	/**
	* Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByMD_P_RJUI(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId);
	}

	/**
	* Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByMD_P_AJUI(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId);
	}

	/**
	* Removes all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63; from the database.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_RJUI_S(projectId, reporterJiraUserId, status);
	}

	/**
	* Removes all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63; from the database.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_AJUI_S(projectId, assigneeJiraUserId, status);
	}

	/**
	* Removes all the j i r a issues from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of j i r a issues where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByProjectId(projectId);
	}

	/**
	* Returns the number of j i r a issues where key = &#63;.
	*
	* @param key the key
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKey(key);
	}

	/**
	* Returns the number of j i r a issues where reporterJiraUserId = &#63;.
	*
	* @param reporterJiraUserId the reporter jira user ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByReporterJiraUserId(
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByReporterJiraUserId(reporterJiraUserId);
	}

	/**
	* Returns the number of j i r a issues where assigneeJiraUserId = &#63;.
	*
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssigneeJiraUserId(assigneeJiraUserId);
	}

	/**
	* Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByMD_P(java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByMD_P(modifiedDate, projectId);
	}

	/**
	* Returns the number of j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_RJUI(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_RJUI(projectId, reporterJiraUserId);
	}

	/**
	* Returns the number of j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_AJUI(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_AJUI(projectId, assigneeJiraUserId);
	}

	/**
	* Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByMD_P_RJUI(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId);
	}

	/**
	* Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByMD_P_AJUI(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId);
	}

	/**
	* Returns the number of j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param reporterJiraUserId the reporter jira user ID
	* @param status the status
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByP_RJUI_S(projectId, reporterJiraUserId, status);
	}

	/**
	* Returns the number of j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	*
	* @param projectId the project ID
	* @param assigneeJiraUserId the assignee jira user ID
	* @param status the status
	* @return the number of matching j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByP_AJUI_S(projectId, assigneeJiraUserId, status);
	}

	/**
	* Returns the number of j i r a issues.
	*
	* @return the number of j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static JIRAIssuePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (JIRAIssuePersistence)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.getServletContextName(),
					JIRAIssuePersistence.class.getName());

			ReferenceRegistry.registerReference(JIRAIssueUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(JIRAIssuePersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(JIRAIssueUtil.class, "_persistence");
	}

	private static JIRAIssuePersistence _persistence;
}