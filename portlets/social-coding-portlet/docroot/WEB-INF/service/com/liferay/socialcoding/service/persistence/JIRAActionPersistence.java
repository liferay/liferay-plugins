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

import com.liferay.socialcoding.model.JIRAAction;

/**
 * The persistence interface for the j i r a action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAActionPersistenceImpl
 * @see JIRAActionUtil
 * @generated
 */
public interface JIRAActionPersistence extends BasePersistence<JIRAAction> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAActionUtil} to access the j i r a action persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the j i r a action in the entity cache if it is enabled.
	*
	* @param jiraAction the j i r a action
	*/
	public void cacheResult(
		com.liferay.socialcoding.model.JIRAAction jiraAction);

	/**
	* Caches the j i r a actions in the entity cache if it is enabled.
	*
	* @param jiraActions the j i r a actions
	*/
	public void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAAction> jiraActions);

	/**
	* Creates a new j i r a action with the primary key. Does not add the j i r a action to the database.
	*
	* @param jiraActionId the primary key for the new j i r a action
	* @return the new j i r a action
	*/
	public com.liferay.socialcoding.model.JIRAAction create(long jiraActionId);

	/**
	* Removes the j i r a action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraActionId the primary key of the j i r a action
	* @return the j i r a action that was removed
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction remove(long jiraActionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException;

	public com.liferay.socialcoding.model.JIRAAction updateImpl(
		com.liferay.socialcoding.model.JIRAAction jiraAction, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a action with the primary key or throws a {@link com.liferay.socialcoding.NoSuchJIRAActionException} if it could not be found.
	*
	* @param jiraActionId the primary key of the j i r a action
	* @return the j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction findByPrimaryKey(
		long jiraActionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException;

	/**
	* Returns the j i r a action with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraActionId the primary key of the j i r a action
	* @return the j i r a action, or <code>null</code> if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction fetchByPrimaryKey(
		long jiraActionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the j i r a actions where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @return the matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAAction> findByJiraUserId(
		java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a actions where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraUserId the jira user ID
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @return the range of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAAction> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a actions where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraUserId the jira user ID
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAAction> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a action in the ordered set where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction findByJiraUserId_First(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException;

	/**
	* Returns the last j i r a action in the ordered set where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction findByJiraUserId_Last(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException;

	/**
	* Returns the j i r a actions before and after the current j i r a action in the ordered set where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraActionId the primary key of the current j i r a action
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction[] findByJiraUserId_PrevAndNext(
		long jiraActionId, java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException;

	/**
	* Returns all the j i r a actions where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @return the matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAAction> findByJiraIssueId(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a actions where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the jira issue ID
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @return the range of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAAction> findByJiraIssueId(
		long jiraIssueId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a actions where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the jira issue ID
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAAction> findByJiraIssueId(
		long jiraIssueId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a action in the ordered set where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction findByJiraIssueId_First(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException;

	/**
	* Returns the last j i r a action in the ordered set where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction findByJiraIssueId_Last(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException;

	/**
	* Returns the j i r a actions before and after the current j i r a action in the ordered set where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraActionId the primary key of the current j i r a action
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction[] findByJiraIssueId_PrevAndNext(
		long jiraActionId, long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException;

	/**
	* Returns all the j i r a actions where type = &#63;.
	*
	* @param type the type
	* @return the matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAAction> findByType(
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a actions where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @return the range of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAAction> findByType(
		java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a actions where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAAction> findByType(
		java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a action in the ordered set where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction findByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException;

	/**
	* Returns the last j i r a action in the ordered set where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction findByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException;

	/**
	* Returns the j i r a actions before and after the current j i r a action in the ordered set where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraActionId the primary key of the current j i r a action
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAAction[] findByType_PrevAndNext(
		long jiraActionId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException;

	/**
	* Returns all the j i r a actions.
	*
	* @return the j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAAction> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.socialcoding.model.JIRAAction> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAAction> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a actions where jiraUserId = &#63; from the database.
	*
	* @param jiraUserId the jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a actions where jiraIssueId = &#63; from the database.
	*
	* @param jiraIssueId the jira issue ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a actions where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a actions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a actions where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @return the number of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public int countByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a actions where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @return the number of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public int countByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a actions where type = &#63;.
	*
	* @param type the type
	* @return the number of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public int countByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a actions.
	*
	* @return the number of j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}