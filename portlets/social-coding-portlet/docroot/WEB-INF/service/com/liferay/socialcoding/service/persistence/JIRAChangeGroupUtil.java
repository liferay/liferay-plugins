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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialcoding.model.JIRAChangeGroup;

import java.util.List;

/**
 * The persistence utility for the j i r a change group service. This utility wraps {@link com.liferay.socialcoding.service.persistence.impl.JIRAChangeGroupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeGroupPersistence
 * @see com.liferay.socialcoding.service.persistence.impl.JIRAChangeGroupPersistenceImpl
 * @generated
 */
@ProviderType
public class JIRAChangeGroupUtil {
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
	public static void clearCache(JIRAChangeGroup jiraChangeGroup) {
		getPersistence().clearCache(jiraChangeGroup);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<JIRAChangeGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JIRAChangeGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JIRAChangeGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<JIRAChangeGroup> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static JIRAChangeGroup update(JIRAChangeGroup jiraChangeGroup) {
		return getPersistence().update(jiraChangeGroup);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static JIRAChangeGroup update(JIRAChangeGroup jiraChangeGroup,
		ServiceContext serviceContext) {
		return getPersistence().update(jiraChangeGroup, serviceContext);
	}

	/**
	* Returns all the j i r a change groups where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @return the matching j i r a change groups
	*/
	public static List<JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId) {
		return getPersistence().findByJiraUserId(jiraUserId);
	}

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
	public static List<JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end) {
		return getPersistence().findByJiraUserId(jiraUserId, start, end);
	}

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
	public static List<JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end,
		OrderByComparator<JIRAChangeGroup> orderByComparator) {
		return getPersistence()
				   .findByJiraUserId(jiraUserId, start, end, orderByComparator);
	}

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching j i r a change groups
	*/
	public static List<JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end,
		OrderByComparator<JIRAChangeGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByJiraUserId(jiraUserId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	*/
	public static JIRAChangeGroup findByJiraUserId_First(
		java.lang.String jiraUserId,
		OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraUserId_First(jiraUserId, orderByComparator);
	}

	/**
	* Returns the first j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change group, or <code>null</code> if a matching j i r a change group could not be found
	*/
	public static JIRAChangeGroup fetchByJiraUserId_First(
		java.lang.String jiraUserId,
		OrderByComparator<JIRAChangeGroup> orderByComparator) {
		return getPersistence()
				   .fetchByJiraUserId_First(jiraUserId, orderByComparator);
	}

	/**
	* Returns the last j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	*/
	public static JIRAChangeGroup findByJiraUserId_Last(
		java.lang.String jiraUserId,
		OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraUserId_Last(jiraUserId, orderByComparator);
	}

	/**
	* Returns the last j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change group, or <code>null</code> if a matching j i r a change group could not be found
	*/
	public static JIRAChangeGroup fetchByJiraUserId_Last(
		java.lang.String jiraUserId,
		OrderByComparator<JIRAChangeGroup> orderByComparator) {
		return getPersistence()
				   .fetchByJiraUserId_Last(jiraUserId, orderByComparator);
	}

	/**
	* Returns the j i r a change groups before and after the current j i r a change group in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraChangeGroupId the primary key of the current j i r a change group
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	*/
	public static JIRAChangeGroup[] findByJiraUserId_PrevAndNext(
		long jiraChangeGroupId, java.lang.String jiraUserId,
		OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraUserId_PrevAndNext(jiraChangeGroupId, jiraUserId,
			orderByComparator);
	}

	/**
	* Removes all the j i r a change groups where jiraUserId = &#63; from the database.
	*
	* @param jiraUserId the jira user ID
	*/
	public static void removeByJiraUserId(java.lang.String jiraUserId) {
		getPersistence().removeByJiraUserId(jiraUserId);
	}

	/**
	* Returns the number of j i r a change groups where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @return the number of matching j i r a change groups
	*/
	public static int countByJiraUserId(java.lang.String jiraUserId) {
		return getPersistence().countByJiraUserId(jiraUserId);
	}

	/**
	* Returns all the j i r a change groups where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @return the matching j i r a change groups
	*/
	public static List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId) {
		return getPersistence().findByJiraIssueId(jiraIssueId);
	}

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
	public static List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId,
		int start, int end) {
		return getPersistence().findByJiraIssueId(jiraIssueId, start, end);
	}

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
	public static List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId,
		int start, int end, OrderByComparator<JIRAChangeGroup> orderByComparator) {
		return getPersistence()
				   .findByJiraIssueId(jiraIssueId, start, end, orderByComparator);
	}

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching j i r a change groups
	*/
	public static List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId,
		int start, int end,
		OrderByComparator<JIRAChangeGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByJiraIssueId(jiraIssueId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	*/
	public static JIRAChangeGroup findByJiraIssueId_First(long jiraIssueId,
		OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraIssueId_First(jiraIssueId, orderByComparator);
	}

	/**
	* Returns the first j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change group, or <code>null</code> if a matching j i r a change group could not be found
	*/
	public static JIRAChangeGroup fetchByJiraIssueId_First(long jiraIssueId,
		OrderByComparator<JIRAChangeGroup> orderByComparator) {
		return getPersistence()
				   .fetchByJiraIssueId_First(jiraIssueId, orderByComparator);
	}

	/**
	* Returns the last j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	*/
	public static JIRAChangeGroup findByJiraIssueId_Last(long jiraIssueId,
		OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraIssueId_Last(jiraIssueId, orderByComparator);
	}

	/**
	* Returns the last j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change group, or <code>null</code> if a matching j i r a change group could not be found
	*/
	public static JIRAChangeGroup fetchByJiraIssueId_Last(long jiraIssueId,
		OrderByComparator<JIRAChangeGroup> orderByComparator) {
		return getPersistence()
				   .fetchByJiraIssueId_Last(jiraIssueId, orderByComparator);
	}

	/**
	* Returns the j i r a change groups before and after the current j i r a change group in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraChangeGroupId the primary key of the current j i r a change group
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	*/
	public static JIRAChangeGroup[] findByJiraIssueId_PrevAndNext(
		long jiraChangeGroupId, long jiraIssueId,
		OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraIssueId_PrevAndNext(jiraChangeGroupId,
			jiraIssueId, orderByComparator);
	}

	/**
	* Removes all the j i r a change groups where jiraIssueId = &#63; from the database.
	*
	* @param jiraIssueId the jira issue ID
	*/
	public static void removeByJiraIssueId(long jiraIssueId) {
		getPersistence().removeByJiraIssueId(jiraIssueId);
	}

	/**
	* Returns the number of j i r a change groups where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @return the number of matching j i r a change groups
	*/
	public static int countByJiraIssueId(long jiraIssueId) {
		return getPersistence().countByJiraIssueId(jiraIssueId);
	}

	/**
	* Caches the j i r a change group in the entity cache if it is enabled.
	*
	* @param jiraChangeGroup the j i r a change group
	*/
	public static void cacheResult(JIRAChangeGroup jiraChangeGroup) {
		getPersistence().cacheResult(jiraChangeGroup);
	}

	/**
	* Caches the j i r a change groups in the entity cache if it is enabled.
	*
	* @param jiraChangeGroups the j i r a change groups
	*/
	public static void cacheResult(List<JIRAChangeGroup> jiraChangeGroups) {
		getPersistence().cacheResult(jiraChangeGroups);
	}

	/**
	* Creates a new j i r a change group with the primary key. Does not add the j i r a change group to the database.
	*
	* @param jiraChangeGroupId the primary key for the new j i r a change group
	* @return the new j i r a change group
	*/
	public static JIRAChangeGroup create(long jiraChangeGroupId) {
		return getPersistence().create(jiraChangeGroupId);
	}

	/**
	* Removes the j i r a change group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group
	* @return the j i r a change group that was removed
	* @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	*/
	public static JIRAChangeGroup remove(long jiraChangeGroupId)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence().remove(jiraChangeGroupId);
	}

	public static JIRAChangeGroup updateImpl(JIRAChangeGroup jiraChangeGroup) {
		return getPersistence().updateImpl(jiraChangeGroup);
	}

	/**
	* Returns the j i r a change group with the primary key or throws a {@link NoSuchJIRAChangeGroupException} if it could not be found.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group
	* @return the j i r a change group
	* @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	*/
	public static JIRAChangeGroup findByPrimaryKey(long jiraChangeGroupId)
		throws com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence().findByPrimaryKey(jiraChangeGroupId);
	}

	/**
	* Returns the j i r a change group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraChangeGroupId the primary key of the j i r a change group
	* @return the j i r a change group, or <code>null</code> if a j i r a change group with the primary key could not be found
	*/
	public static JIRAChangeGroup fetchByPrimaryKey(long jiraChangeGroupId) {
		return getPersistence().fetchByPrimaryKey(jiraChangeGroupId);
	}

	public static java.util.Map<java.io.Serializable, JIRAChangeGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the j i r a change groups.
	*
	* @return the j i r a change groups
	*/
	public static List<JIRAChangeGroup> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<JIRAChangeGroup> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<JIRAChangeGroup> findAll(int start, int end,
		OrderByComparator<JIRAChangeGroup> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of j i r a change groups
	*/
	public static List<JIRAChangeGroup> findAll(int start, int end,
		OrderByComparator<JIRAChangeGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the j i r a change groups from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of j i r a change groups.
	*
	* @return the number of j i r a change groups
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static JIRAChangeGroupPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (JIRAChangeGroupPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.getServletContextName(),
					JIRAChangeGroupPersistence.class.getName());

			ReferenceRegistry.registerReference(JIRAChangeGroupUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static JIRAChangeGroupPersistence _persistence;
}