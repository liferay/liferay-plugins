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

import com.liferay.socialcoding.model.JIRAChangeGroup;

/**
 * The persistence interface for the j i r a change group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeGroupPersistenceImpl
 * @see JIRAChangeGroupUtil
 * @generated
 */
public interface JIRAChangeGroupPersistence extends BasePersistence<JIRAChangeGroup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAChangeGroupUtil} to access the j i r a change group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the j i r a change group in the entity cache if it is enabled.
	*
	* @param jiraChangeGroup the j i r a change group
	*/
	public void cacheResult(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup);

	/**
	* Caches the j i r a change groups in the entity cache if it is enabled.
	*
	* @param jiraChangeGroups the j i r a change groups
	*/
	public void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> jiraChangeGroups);

	/**
	* Creates a new j i r a change group with the primary key. Does not add the j i r a change group to the database.
	*
	* @param jiraChangeGroupId the primary key for the new j i r a change group
	* @return the new j i r a change group
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup create(
		long jiraChangeGroupId);

	/**
	* Removes the j i r a change group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group
	* @return the j i r a change group that was removed
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup remove(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	public com.liferay.socialcoding.model.JIRAChangeGroup updateImpl(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a change group with the primary key or throws a {@link com.liferay.socialcoding.NoSuchJIRAChangeGroupException} if it could not be found.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group
	* @return the j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup findByPrimaryKey(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Returns the j i r a change group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group
	* @return the j i r a change group, or <code>null</code> if a j i r a change group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup fetchByPrimaryKey(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the j i r a change groups where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @return the matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a change groups where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraUserId the jira user ID
	* @param start the lower bound of the range of j i r a change groups
	* @param end the upper bound of the range of j i r a change groups (not inclusive)
	* @return the range of matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a change groups where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraUserId the jira user ID
	* @param start the lower bound of the range of j i r a change groups
	* @param end the upper bound of the range of j i r a change groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup findByJiraUserId_First(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Returns the last j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup findByJiraUserId_Last(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Returns the j i r a change groups before and after the current j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraChangeGroupId the primary key of the current j i r a change group
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup[] findByJiraUserId_PrevAndNext(
		long jiraChangeGroupId, java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Returns all the j i r a change groups where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @return the matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a change groups where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the jira issue ID
	* @param start the lower bound of the range of j i r a change groups
	* @param end the upper bound of the range of j i r a change groups (not inclusive)
	* @return the range of matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a change groups where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the jira issue ID
	* @param start the lower bound of the range of j i r a change groups
	* @param end the upper bound of the range of j i r a change groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup findByJiraIssueId_First(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Returns the last j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup findByJiraIssueId_Last(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Returns the j i r a change groups before and after the current j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraChangeGroupId the primary key of the current j i r a change group
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeGroup[] findByJiraIssueId_PrevAndNext(
		long jiraChangeGroupId, long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Returns all the j i r a change groups.
	*
	* @return the j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a change groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of j i r a change groups
	* @param end the upper bound of the range of j i r a change groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a change groups where jiraUserId = &#63; from the database.
	*
	* @param jiraUserId the jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a change groups where jiraIssueId = &#63; from the database.
	*
	* @param jiraIssueId the jira issue ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a change groups from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a change groups where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @return the number of matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public int countByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a change groups where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @return the number of matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public int countByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a change groups.
	*
	* @return the number of j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}