/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialcoding.model.JIRAChangeGroup;

import java.util.List;

/**
 * The persistence utility for the j i r a change group service.
 *
 * <p>
 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regnerate this class.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeGroupPersistence
 * @see JIRAChangeGroupPersistenceImpl
 * @generated
 */
public class JIRAChangeGroupUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(JIRAChangeGroup jiraChangeGroup) {
		getPersistence().clearCache(jiraChangeGroup);
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
	public static List<JIRAChangeGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JIRAChangeGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JIRAChangeGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static JIRAChangeGroup remove(JIRAChangeGroup jiraChangeGroup)
		throws SystemException {
		return getPersistence().remove(jiraChangeGroup);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static JIRAChangeGroup update(JIRAChangeGroup jiraChangeGroup,
		boolean merge) throws SystemException {
		return getPersistence().update(jiraChangeGroup, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static JIRAChangeGroup update(JIRAChangeGroup jiraChangeGroup,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(jiraChangeGroup, merge, serviceContext);
	}

	/**
	* Caches the j i r a change group in the entity cache if it is enabled.
	*
	* @param jiraChangeGroup the j i r a change group to cache
	*/
	public static void cacheResult(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup) {
		getPersistence().cacheResult(jiraChangeGroup);
	}

	/**
	* Caches the j i r a change groups in the entity cache if it is enabled.
	*
	* @param jiraChangeGroups the j i r a change groups to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> jiraChangeGroups) {
		getPersistence().cacheResult(jiraChangeGroups);
	}

	/**
	* Creates a new j i r a change group with the primary key.
	*
	* @param jiraChangeGroupId the primary key for the new j i r a change group
	* @return the new j i r a change group
	*/
	public static com.liferay.socialcoding.model.JIRAChangeGroup create(
		long jiraChangeGroupId) {
		return getPersistence().create(jiraChangeGroupId);
	}

	/**
	* Removes the j i r a change group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group to remove
	* @return the j i r a change group that was removed
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeGroup remove(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence().remove(jiraChangeGroupId);
	}

	public static com.liferay.socialcoding.model.JIRAChangeGroup updateImpl(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(jiraChangeGroup, merge);
	}

	/**
	* Finds the j i r a change group with the primary key or throws a {@link com.liferay.socialcoding.NoSuchJIRAChangeGroupException} if it could not be found.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group to find
	* @return the j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeGroup findByPrimaryKey(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence().findByPrimaryKey(jiraChangeGroupId);
	}

	/**
	* Finds the j i r a change group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group to find
	* @return the j i r a change group, or <code>null</code> if a j i r a change group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeGroup fetchByPrimaryKey(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(jiraChangeGroupId);
	}

	/**
	* Finds all the j i r a change groups where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user id to search with
	* @return the matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraUserId(jiraUserId);
	}

	/**
	* Finds a range of all the j i r a change groups where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraUserId the jira user id to search with
	* @param start the lower bound of the range of j i r a change groups to return
	* @param end the upper bound of the range of j i r a change groups to return (not inclusive)
	* @return the range of matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraUserId(jiraUserId, start, end);
	}

	/**
	* Finds an ordered range of all the j i r a change groups where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraUserId the jira user id to search with
	* @param start the lower bound of the range of j i r a change groups to return
	* @param end the upper bound of the range of j i r a change groups to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJiraUserId(jiraUserId, start, end, orderByComparator);
	}

	/**
	* Finds the first j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraUserId the jira user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeGroup findByJiraUserId_First(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraUserId_First(jiraUserId, orderByComparator);
	}

	/**
	* Finds the last j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraUserId the jira user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeGroup findByJiraUserId_Last(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraUserId_Last(jiraUserId, orderByComparator);
	}

	/**
	* Finds the j i r a change groups before and after the current j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraChangeGroupId the primary key of the current j i r a change group
	* @param jiraUserId the jira user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeGroup[] findByJiraUserId_PrevAndNext(
		long jiraChangeGroupId, java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraUserId_PrevAndNext(jiraChangeGroupId, jiraUserId,
			orderByComparator);
	}

	/**
	* Finds all the j i r a change groups where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue id to search with
	* @return the matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraIssueId(jiraIssueId);
	}

	/**
	* Finds a range of all the j i r a change groups where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the jira issue id to search with
	* @param start the lower bound of the range of j i r a change groups to return
	* @param end the upper bound of the range of j i r a change groups to return (not inclusive)
	* @return the range of matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraIssueId(jiraIssueId, start, end);
	}

	/**
	* Finds an ordered range of all the j i r a change groups where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the jira issue id to search with
	* @param start the lower bound of the range of j i r a change groups to return
	* @param end the upper bound of the range of j i r a change groups to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJiraIssueId(jiraIssueId, start, end, orderByComparator);
	}

	/**
	* Finds the first j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the jira issue id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeGroup findByJiraIssueId_First(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraIssueId_First(jiraIssueId, orderByComparator);
	}

	/**
	* Finds the last j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraIssueId the jira issue id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeGroup findByJiraIssueId_Last(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraIssueId_Last(jiraIssueId, orderByComparator);
	}

	/**
	* Finds the j i r a change groups before and after the current j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraChangeGroupId the primary key of the current j i r a change group
	* @param jiraIssueId the jira issue id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next j i r a change group
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeGroup[] findByJiraIssueId_PrevAndNext(
		long jiraChangeGroupId, long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraIssueId_PrevAndNext(jiraChangeGroupId,
			jiraIssueId, orderByComparator);
	}

	/**
	* Finds all the j i r a change groups.
	*
	* @return the j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the j i r a change groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of j i r a change groups to return
	* @param end the upper bound of the range of j i r a change groups to return (not inclusive)
	* @return the range of j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the j i r a change groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of j i r a change groups to return
	* @param end the upper bound of the range of j i r a change groups to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the j i r a change groups where jiraUserId = &#63; from the database.
	*
	* @param jiraUserId the jira user id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByJiraUserId(jiraUserId);
	}

	/**
	* Removes all the j i r a change groups where jiraIssueId = &#63; from the database.
	*
	* @param jiraIssueId the jira issue id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByJiraIssueId(jiraIssueId);
	}

	/**
	* Removes all the j i r a change groups from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the j i r a change groups where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user id to search with
	* @return the number of matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public static int countByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByJiraUserId(jiraUserId);
	}

	/**
	* Counts all the j i r a change groups where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue id to search with
	* @return the number of matching j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public static int countByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByJiraIssueId(jiraIssueId);
	}

	/**
	* Counts all the j i r a change groups.
	*
	* @return the number of j i r a change groups
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static JIRAChangeGroupPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (JIRAChangeGroupPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					JIRAChangeGroupPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(JIRAChangeGroupPersistence persistence) {
		_persistence = persistence;
	}

	private static JIRAChangeGroupPersistence _persistence;
}