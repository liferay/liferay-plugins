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

import com.liferay.socialcoding.model.JIRAChangeGroup;

/**
 * The persistence interface for the j i r a change group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.socialcoding.service.persistence.impl.JIRAChangeGroupPersistenceImpl
 * @see JIRAChangeGroupUtil
 * @generated
 */
@ProviderType
public interface JIRAChangeGroupPersistence extends BasePersistence<JIRAChangeGroup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAChangeGroupUtil} to access the j i r a change group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the j i r a change groups where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @return the matching j i r a change groups
	*/
	public java.util.List<JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId);

	/**
	* Returns a range of all the j i r a change groups where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jiraUserId the jira user ID
	* @param start the lower bound of the range of j i r a change groups
	* @param end the upper bound of the range of j i r a change groups (not inclusive)
	* @return the range of matching j i r a change groups
	*/
	public java.util.List<JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end);

	/**
	* Returns an ordered range of all the j i r a change groups where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jiraUserId the jira user ID
	* @param start the lower bound of the range of j i r a change groups
	* @param end the upper bound of the range of j i r a change groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a change groups
	*/
	public java.util.List<JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeGroup> orderByComparator);

	/**
	* Returns the first j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	*/
	public JIRAChangeGroup findByJiraUserId_First(java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Returns the first j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change group, or <code>null</code> if a matching j i r a change group could not be found
	*/
	public JIRAChangeGroup fetchByJiraUserId_First(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeGroup> orderByComparator);

	/**
	* Returns the last j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	*/
	public JIRAChangeGroup findByJiraUserId_Last(java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Returns the last j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change group, or <code>null</code> if a matching j i r a change group could not be found
	*/
	public JIRAChangeGroup fetchByJiraUserId_Last(java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeGroup> orderByComparator);

	/**
	* Returns the j i r a change groups before and after the current j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraChangeGroupId the primary key of the current j i r a change group
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	*/
	public JIRAChangeGroup[] findByJiraUserId_PrevAndNext(
		long jiraChangeGroupId, java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Removes all the j i r a change groups where jiraUserId = &#63; from the database.
	*
	* @param jiraUserId the jira user ID
	*/
	public void removeByJiraUserId(java.lang.String jiraUserId);

	/**
	* Returns the number of j i r a change groups where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @return the number of matching j i r a change groups
	*/
	public int countByJiraUserId(java.lang.String jiraUserId);

	/**
	* Returns all the j i r a change groups where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @return the matching j i r a change groups
	*/
	public java.util.List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId);

	/**
	* Returns a range of all the j i r a change groups where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jiraIssueId the jira issue ID
	* @param start the lower bound of the range of j i r a change groups
	* @param end the upper bound of the range of j i r a change groups (not inclusive)
	* @return the range of matching j i r a change groups
	*/
	public java.util.List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId,
		int start, int end);

	/**
	* Returns an ordered range of all the j i r a change groups where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jiraIssueId the jira issue ID
	* @param start the lower bound of the range of j i r a change groups
	* @param end the upper bound of the range of j i r a change groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a change groups
	*/
	public java.util.List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeGroup> orderByComparator);

	/**
	* Returns the first j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	*/
	public JIRAChangeGroup findByJiraIssueId_First(long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Returns the first j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change group, or <code>null</code> if a matching j i r a change group could not be found
	*/
	public JIRAChangeGroup fetchByJiraIssueId_First(long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeGroup> orderByComparator);

	/**
	* Returns the last j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	*/
	public JIRAChangeGroup findByJiraIssueId_Last(long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Returns the last j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change group, or <code>null</code> if a matching j i r a change group could not be found
	*/
	public JIRAChangeGroup fetchByJiraIssueId_Last(long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeGroup> orderByComparator);

	/**
	* Returns the j i r a change groups before and after the current j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraChangeGroupId the primary key of the current j i r a change group
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	*/
	public JIRAChangeGroup[] findByJiraIssueId_PrevAndNext(
		long jiraChangeGroupId, long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Removes all the j i r a change groups where jiraIssueId = &#63; from the database.
	*
	* @param jiraIssueId the jira issue ID
	*/
	public void removeByJiraIssueId(long jiraIssueId);

	/**
	* Returns the number of j i r a change groups where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @return the number of matching j i r a change groups
	*/
	public int countByJiraIssueId(long jiraIssueId);

	/**
	* Caches the j i r a change group in the entity cache if it is enabled.
	*
	* @param jiraChangeGroup the j i r a change group
	*/
	public void cacheResult(JIRAChangeGroup jiraChangeGroup);

	/**
	* Caches the j i r a change groups in the entity cache if it is enabled.
	*
	* @param jiraChangeGroups the j i r a change groups
	*/
	public void cacheResult(java.util.List<JIRAChangeGroup> jiraChangeGroups);

	/**
	* Creates a new j i r a change group with the primary key. Does not add the j i r a change group to the database.
	*
	* @param jiraChangeGroupId the primary key for the new j i r a change group
	* @return the new j i r a change group
	*/
	public JIRAChangeGroup create(long jiraChangeGroupId);

	/**
	* Removes the j i r a change group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group
	* @return the j i r a change group that was removed
	* @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	*/
	public JIRAChangeGroup remove(long jiraChangeGroupId)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	public JIRAChangeGroup updateImpl(JIRAChangeGroup jiraChangeGroup);

	/**
	* Returns the j i r a change group with the primary key or throws a {@link NoSuchJIRAChangeGroupException} if it could not be found.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group
	* @return the j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	*/
	public JIRAChangeGroup findByPrimaryKey(long jiraChangeGroupId)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	/**
	* Returns the j i r a change group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group
	* @return the j i r a change group, or <code>null</code> if a j i r a change group with the primary key could not be found
	*/
	public JIRAChangeGroup fetchByPrimaryKey(long jiraChangeGroupId);

	@Override
	public java.util.Map<java.io.Serializable, JIRAChangeGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the j i r a change groups.
	*
	* @return the j i r a change groups
	*/
	public java.util.List<JIRAChangeGroup> findAll();

	/**
	* Returns a range of all the j i r a change groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of j i r a change groups
	* @param end the upper bound of the range of j i r a change groups (not inclusive)
	* @return the range of j i r a change groups
	*/
	public java.util.List<JIRAChangeGroup> findAll(int start, int end);

	/**
	* Returns an ordered range of all the j i r a change groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of j i r a change groups
	* @param end the upper bound of the range of j i r a change groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of j i r a change groups
	*/
	public java.util.List<JIRAChangeGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeGroup> orderByComparator);

	/**
	* Removes all the j i r a change groups from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of j i r a change groups.
	*
	* @return the number of j i r a change groups
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}