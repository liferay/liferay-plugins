/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.socialcoding.model.JIRAAction;

import java.util.List;

/**
 * The persistence utility for the j i r a action service. This utility wraps {@link JIRAActionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAActionPersistence
 * @see JIRAActionPersistenceImpl
 * @generated
 */
public class JIRAActionUtil {
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
	public static void clearCache(JIRAAction jiraAction) {
		getPersistence().clearCache(jiraAction);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<JIRAAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JIRAAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JIRAAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static JIRAAction update(JIRAAction jiraAction)
		throws SystemException {
		return getPersistence().update(jiraAction);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static JIRAAction update(JIRAAction jiraAction,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(jiraAction, serviceContext);
	}

	/**
	* Returns all the j i r a actions where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @return the matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAAction> findByJiraUserId(
		java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraUserId(jiraUserId);
	}

	/**
	* Returns a range of all the j i r a actions where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jiraUserId the jira user ID
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @return the range of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAAction> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraUserId(jiraUserId, start, end);
	}

	/**
	* Returns an ordered range of all the j i r a actions where jiraUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jiraUserId the jira user ID
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAAction> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJiraUserId(jiraUserId, start, end, orderByComparator);
	}

	/**
	* Returns the first j i r a action in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction findByJiraUserId_First(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException {
		return getPersistence()
				   .findByJiraUserId_First(jiraUserId, orderByComparator);
	}

	/**
	* Returns the first j i r a action in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a action, or <code>null</code> if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction fetchByJiraUserId_First(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJiraUserId_First(jiraUserId, orderByComparator);
	}

	/**
	* Returns the last j i r a action in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction findByJiraUserId_Last(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException {
		return getPersistence()
				   .findByJiraUserId_Last(jiraUserId, orderByComparator);
	}

	/**
	* Returns the last j i r a action in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a action, or <code>null</code> if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction fetchByJiraUserId_Last(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJiraUserId_Last(jiraUserId, orderByComparator);
	}

	/**
	* Returns the j i r a actions before and after the current j i r a action in the ordered set where jiraUserId = &#63;.
	*
	* @param jiraActionId the primary key of the current j i r a action
	* @param jiraUserId the jira user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction[] findByJiraUserId_PrevAndNext(
		long jiraActionId, java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException {
		return getPersistence()
				   .findByJiraUserId_PrevAndNext(jiraActionId, jiraUserId,
			orderByComparator);
	}

	/**
	* Removes all the j i r a actions where jiraUserId = &#63; from the database.
	*
	* @param jiraUserId the jira user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByJiraUserId(jiraUserId);
	}

	/**
	* Returns the number of j i r a actions where jiraUserId = &#63;.
	*
	* @param jiraUserId the jira user ID
	* @return the number of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByJiraUserId(jiraUserId);
	}

	/**
	* Returns all the j i r a actions where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @return the matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAAction> findByJiraIssueId(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraIssueId(jiraIssueId);
	}

	/**
	* Returns a range of all the j i r a actions where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jiraIssueId the jira issue ID
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @return the range of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAAction> findByJiraIssueId(
		long jiraIssueId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraIssueId(jiraIssueId, start, end);
	}

	/**
	* Returns an ordered range of all the j i r a actions where jiraIssueId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jiraIssueId the jira issue ID
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAAction> findByJiraIssueId(
		long jiraIssueId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJiraIssueId(jiraIssueId, start, end, orderByComparator);
	}

	/**
	* Returns the first j i r a action in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction findByJiraIssueId_First(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException {
		return getPersistence()
				   .findByJiraIssueId_First(jiraIssueId, orderByComparator);
	}

	/**
	* Returns the first j i r a action in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a action, or <code>null</code> if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction fetchByJiraIssueId_First(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJiraIssueId_First(jiraIssueId, orderByComparator);
	}

	/**
	* Returns the last j i r a action in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction findByJiraIssueId_Last(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException {
		return getPersistence()
				   .findByJiraIssueId_Last(jiraIssueId, orderByComparator);
	}

	/**
	* Returns the last j i r a action in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a action, or <code>null</code> if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction fetchByJiraIssueId_Last(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJiraIssueId_Last(jiraIssueId, orderByComparator);
	}

	/**
	* Returns the j i r a actions before and after the current j i r a action in the ordered set where jiraIssueId = &#63;.
	*
	* @param jiraActionId the primary key of the current j i r a action
	* @param jiraIssueId the jira issue ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction[] findByJiraIssueId_PrevAndNext(
		long jiraActionId, long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException {
		return getPersistence()
				   .findByJiraIssueId_PrevAndNext(jiraActionId, jiraIssueId,
			orderByComparator);
	}

	/**
	* Removes all the j i r a actions where jiraIssueId = &#63; from the database.
	*
	* @param jiraIssueId the jira issue ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByJiraIssueId(jiraIssueId);
	}

	/**
	* Returns the number of j i r a actions where jiraIssueId = &#63;.
	*
	* @param jiraIssueId the jira issue ID
	* @return the number of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByJiraIssueId(jiraIssueId);
	}

	/**
	* Returns all the j i r a actions where type = &#63;.
	*
	* @param type the type
	* @return the matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAAction> findByType(
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type);
	}

	/**
	* Returns a range of all the j i r a actions where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @return the range of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAAction> findByType(
		java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end);
	}

	/**
	* Returns an ordered range of all the j i r a actions where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAAction> findByType(
		java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end, orderByComparator);
	}

	/**
	* Returns the first j i r a action in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction findByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException {
		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	* Returns the first j i r a action in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a action, or <code>null</code> if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction fetchByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	* Returns the last j i r a action in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction findByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException {
		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	* Returns the last j i r a action in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a action, or <code>null</code> if a matching j i r a action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction fetchByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_Last(type, orderByComparator);
	}

	/**
	* Returns the j i r a actions before and after the current j i r a action in the ordered set where type = &#63;.
	*
	* @param jiraActionId the primary key of the current j i r a action
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction[] findByType_PrevAndNext(
		long jiraActionId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException {
		return getPersistence()
				   .findByType_PrevAndNext(jiraActionId, type, orderByComparator);
	}

	/**
	* Removes all the j i r a actions where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByType(type);
	}

	/**
	* Returns the number of j i r a actions where type = &#63;.
	*
	* @param type the type
	* @return the number of matching j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByType(java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByType(type);
	}

	/**
	* Caches the j i r a action in the entity cache if it is enabled.
	*
	* @param jiraAction the j i r a action
	*/
	public static void cacheResult(
		com.liferay.socialcoding.model.JIRAAction jiraAction) {
		getPersistence().cacheResult(jiraAction);
	}

	/**
	* Caches the j i r a actions in the entity cache if it is enabled.
	*
	* @param jiraActions the j i r a actions
	*/
	public static void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAAction> jiraActions) {
		getPersistence().cacheResult(jiraActions);
	}

	/**
	* Creates a new j i r a action with the primary key. Does not add the j i r a action to the database.
	*
	* @param jiraActionId the primary key for the new j i r a action
	* @return the new j i r a action
	*/
	public static com.liferay.socialcoding.model.JIRAAction create(
		long jiraActionId) {
		return getPersistence().create(jiraActionId);
	}

	/**
	* Removes the j i r a action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraActionId the primary key of the j i r a action
	* @return the j i r a action that was removed
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction remove(
		long jiraActionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException {
		return getPersistence().remove(jiraActionId);
	}

	public static com.liferay.socialcoding.model.JIRAAction updateImpl(
		com.liferay.socialcoding.model.JIRAAction jiraAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(jiraAction);
	}

	/**
	* Returns the j i r a action with the primary key or throws a {@link com.liferay.socialcoding.NoSuchJIRAActionException} if it could not be found.
	*
	* @param jiraActionId the primary key of the j i r a action
	* @return the j i r a action
	* @throws com.liferay.socialcoding.NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction findByPrimaryKey(
		long jiraActionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAActionException {
		return getPersistence().findByPrimaryKey(jiraActionId);
	}

	/**
	* Returns the j i r a action with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraActionId the primary key of the j i r a action
	* @return the j i r a action, or <code>null</code> if a j i r a action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAAction fetchByPrimaryKey(
		long jiraActionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(jiraActionId);
	}

	/**
	* Returns all the j i r a actions.
	*
	* @return the j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAAction> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the j i r a actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @return the range of j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAAction> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the j i r a actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of j i r a actions
	* @param end the upper bound of the range of j i r a actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAAction> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the j i r a actions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of j i r a actions.
	*
	* @return the number of j i r a actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static JIRAActionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (JIRAActionPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.getServletContextName(),
					JIRAActionPersistence.class.getName());

			ReferenceRegistry.registerReference(JIRAActionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(JIRAActionPersistence persistence) {
	}

	private static JIRAActionPersistence _persistence;
}