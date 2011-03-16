/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.tasks.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.tasks.model.TasksEntry;

import java.util.List;

/**
 * The persistence utility for the tasks entry service. This utility wraps {@link TasksEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see TasksEntryPersistence
 * @see TasksEntryPersistenceImpl
 * @generated
 */
public class TasksEntryUtil {
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
	public static void clearCache(TasksEntry tasksEntry) {
		getPersistence().clearCache(tasksEntry);
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
	public static List<TasksEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TasksEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TasksEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static TasksEntry remove(TasksEntry tasksEntry)
		throws SystemException {
		return getPersistence().remove(tasksEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TasksEntry update(TasksEntry tasksEntry, boolean merge)
		throws SystemException {
		return getPersistence().update(tasksEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TasksEntry update(TasksEntry tasksEntry, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(tasksEntry, merge, serviceContext);
	}

	/**
	* Caches the tasks entry in the entity cache if it is enabled.
	*
	* @param tasksEntry the tasks entry to cache
	*/
	public static void cacheResult(
		com.liferay.tasks.model.TasksEntry tasksEntry) {
		getPersistence().cacheResult(tasksEntry);
	}

	/**
	* Caches the tasks entries in the entity cache if it is enabled.
	*
	* @param tasksEntries the tasks entries to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.tasks.model.TasksEntry> tasksEntries) {
		getPersistence().cacheResult(tasksEntries);
	}

	/**
	* Creates a new tasks entry with the primary key. Does not add the tasks entry to the database.
	*
	* @param tasksEntryId the primary key for the new tasks entry
	* @return the new tasks entry
	*/
	public static com.liferay.tasks.model.TasksEntry create(long tasksEntryId) {
		return getPersistence().create(tasksEntryId);
	}

	/**
	* Removes the tasks entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntryId the primary key of the tasks entry to remove
	* @return the tasks entry that was removed
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry remove(long tasksEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence().remove(tasksEntryId);
	}

	public static com.liferay.tasks.model.TasksEntry updateImpl(
		com.liferay.tasks.model.TasksEntry tasksEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(tasksEntry, merge);
	}

	/**
	* Finds the tasks entry with the primary key or throws a {@link com.liferay.tasks.NoSuchTasksEntryException} if it could not be found.
	*
	* @param tasksEntryId the primary key of the tasks entry to find
	* @return the tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByPrimaryKey(
		long tasksEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence().findByPrimaryKey(tasksEntryId);
	}

	/**
	* Finds the tasks entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param tasksEntryId the primary key of the tasks entry to find
	* @return the tasks entry, or <code>null</code> if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByPrimaryKey(
		long tasksEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(tasksEntryId);
	}

	/**
	* Finds all the tasks entries where groupId = &#63;.
	*
	* @param groupId the group ID to search with
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Finds a range of all the tasks entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Finds an ordered range of all the tasks entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Finds the first tasks entry in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Finds the last tasks entry in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Finds the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry[] findByGroupId_PrevAndNext(
		long tasksEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(tasksEntryId, groupId,
			orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the tasks entries where groupId = &#63;.
	*
	* @param groupId the group ID to search with
	* @return the matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Filters by the user's permissions and finds a range of all the tasks entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the tasks entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Filters the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry[] filterFindByGroupId_PrevAndNext(
		long tasksEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(tasksEntryId, groupId,
			orderByComparator);
	}

	/**
	* Finds all the tasks entries where userId = &#63;.
	*
	* @param userId the user ID to search with
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Finds a range of all the tasks entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Finds an ordered range of all the tasks entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Finds the first tasks entry in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Finds the last tasks entry in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Finds the tasks entries before and after the current tasks entry in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param userId the user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry[] findByUserId_PrevAndNext(
		long tasksEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByUserId_PrevAndNext(tasksEntryId, userId,
			orderByComparator);
	}

	/**
	* Finds all the tasks entries where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID to search with
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssigneeUserId(assigneeUserId);
	}

	/**
	* Finds a range of all the tasks entries where assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assigneeUserId the assignee user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssigneeUserId(assigneeUserId, start, end);
	}

	/**
	* Finds an ordered range of all the tasks entries where assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assigneeUserId the assignee user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssigneeUserId(assigneeUserId, start, end,
			orderByComparator);
	}

	/**
	* Finds the first tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assigneeUserId the assignee user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByAssigneeUserId_First(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByAssigneeUserId_First(assigneeUserId, orderByComparator);
	}

	/**
	* Finds the last tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param assigneeUserId the assignee user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByAssigneeUserId_Last(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByAssigneeUserId_Last(assigneeUserId, orderByComparator);
	}

	/**
	* Finds the tasks entries before and after the current tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param assigneeUserId the assignee user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry[] findByAssigneeUserId_PrevAndNext(
		long tasksEntryId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByAssigneeUserId_PrevAndNext(tasksEntryId,
			assigneeUserId, orderByComparator);
	}

	/**
	* Finds all the tasks entries where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID to search with
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByResolverUserId(
		long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByResolverUserId(resolverUserId);
	}

	/**
	* Finds a range of all the tasks entries where resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resolverUserId the resolver user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByResolverUserId(resolverUserId, start, end);
	}

	/**
	* Finds an ordered range of all the tasks entries where resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resolverUserId the resolver user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResolverUserId(resolverUserId, start, end,
			orderByComparator);
	}

	/**
	* Finds the first tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resolverUserId the resolver user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByResolverUserId_First(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByResolverUserId_First(resolverUserId, orderByComparator);
	}

	/**
	* Finds the last tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resolverUserId the resolver user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByResolverUserId_Last(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByResolverUserId_Last(resolverUserId, orderByComparator);
	}

	/**
	* Finds the tasks entries before and after the current tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param resolverUserId the resolver user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry[] findByResolverUserId_PrevAndNext(
		long tasksEntryId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByResolverUserId_PrevAndNext(tasksEntryId,
			resolverUserId, orderByComparator);
	}

	/**
	* Finds all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param userId the user ID to search with
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_U(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_U(groupId, userId);
	}

	/**
	* Finds a range of all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param userId the user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_U(groupId, userId, start, end);
	}

	/**
	* Finds an ordered range of all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param userId the user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_U(groupId, userId, start, end, orderByComparator);
	}

	/**
	* Finds the first tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param userId the user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByG_U_First(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByG_U_First(groupId, userId, orderByComparator);
	}

	/**
	* Finds the last tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param userId the user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByG_U_Last(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByG_U_Last(groupId, userId, orderByComparator);
	}

	/**
	* Finds the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID to search with
	* @param userId the user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry[] findByG_U_PrevAndNext(
		long tasksEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByG_U_PrevAndNext(tasksEntryId, groupId, userId,
			orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param userId the user ID to search with
	* @return the matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_U(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_U(groupId, userId);
	}

	/**
	* Filters by the user's permissions and finds a range of all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param userId the user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_U(groupId, userId, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param userId the user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_U(groupId, userId, start, end,
			orderByComparator);
	}

	/**
	* Filters the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID to search with
	* @param userId the user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry[] filterFindByG_U_PrevAndNext(
		long tasksEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .filterFindByG_U_PrevAndNext(tasksEntryId, groupId, userId,
			orderByComparator);
	}

	/**
	* Finds all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param assigneeUserId the assignee user ID to search with
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_A(groupId, assigneeUserId);
	}

	/**
	* Finds a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param assigneeUserId the assignee user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_A(groupId, assigneeUserId, start, end);
	}

	/**
	* Finds an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param assigneeUserId the assignee user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_A(groupId, assigneeUserId, start, end,
			orderByComparator);
	}

	/**
	* Finds the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param assigneeUserId the assignee user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByG_A_First(
		long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByG_A_First(groupId, assigneeUserId, orderByComparator);
	}

	/**
	* Finds the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param assigneeUserId the assignee user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByG_A_Last(
		long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByG_A_Last(groupId, assigneeUserId, orderByComparator);
	}

	/**
	* Finds the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID to search with
	* @param assigneeUserId the assignee user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry[] findByG_A_PrevAndNext(
		long tasksEntryId, long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByG_A_PrevAndNext(tasksEntryId, groupId,
			assigneeUserId, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param assigneeUserId the assignee user ID to search with
	* @return the matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_A(
		long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_A(groupId, assigneeUserId);
	}

	/**
	* Filters by the user's permissions and finds a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param assigneeUserId the assignee user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_A(
		long groupId, long assigneeUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_A(groupId, assigneeUserId, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param assigneeUserId the assignee user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_A(
		long groupId, long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_A(groupId, assigneeUserId, start, end,
			orderByComparator);
	}

	/**
	* Filters the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID to search with
	* @param assigneeUserId the assignee user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry[] filterFindByG_A_PrevAndNext(
		long tasksEntryId, long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .filterFindByG_A_PrevAndNext(tasksEntryId, groupId,
			assigneeUserId, orderByComparator);
	}

	/**
	* Finds all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resolverUserId the resolver user ID to search with
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_R(groupId, resolverUserId);
	}

	/**
	* Finds a range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resolverUserId the resolver user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_R(groupId, resolverUserId, start, end);
	}

	/**
	* Finds an ordered range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resolverUserId the resolver user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R(groupId, resolverUserId, start, end,
			orderByComparator);
	}

	/**
	* Finds the first tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resolverUserId the resolver user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByG_R_First(
		long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByG_R_First(groupId, resolverUserId, orderByComparator);
	}

	/**
	* Finds the last tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resolverUserId the resolver user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry findByG_R_Last(
		long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByG_R_Last(groupId, resolverUserId, orderByComparator);
	}

	/**
	* Finds the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID to search with
	* @param resolverUserId the resolver user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry[] findByG_R_PrevAndNext(
		long tasksEntryId, long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .findByG_R_PrevAndNext(tasksEntryId, groupId,
			resolverUserId, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resolverUserId the resolver user ID to search with
	* @return the matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_R(
		long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_R(groupId, resolverUserId);
	}

	/**
	* Filters by the user's permissions and finds a range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resolverUserId the resolver user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_R(
		long groupId, long resolverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R(groupId, resolverUserId, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resolverUserId the resolver user ID to search with
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_R(
		long groupId, long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R(groupId, resolverUserId, start, end,
			orderByComparator);
	}

	/**
	* Filters the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID to search with
	* @param resolverUserId the resolver user ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry[] filterFindByG_R_PrevAndNext(
		long tasksEntryId, long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException {
		return getPersistence()
				   .filterFindByG_R_PrevAndNext(tasksEntryId, groupId,
			resolverUserId, orderByComparator);
	}

	/**
	* Finds all the tasks entries.
	*
	* @return the tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the tasks entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @return the range of tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the tasks entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of tasks entries to return
	* @param end the upper bound of the range of tasks entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the tasks entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Removes all the tasks entries where userId = &#63; from the database.
	*
	* @param userId the user ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the tasks entries where assigneeUserId = &#63; from the database.
	*
	* @param assigneeUserId the assignee user ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAssigneeUserId(long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAssigneeUserId(assigneeUserId);
	}

	/**
	* Removes all the tasks entries where resolverUserId = &#63; from the database.
	*
	* @param resolverUserId the resolver user ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByResolverUserId(long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByResolverUserId(resolverUserId);
	}

	/**
	* Removes all the tasks entries where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param userId the user ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_U(groupId, userId);
	}

	/**
	* Removes all the tasks entries where groupId = &#63; and assigneeUserId = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param assigneeUserId the assignee user ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_A(long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_A(groupId, assigneeUserId);
	}

	/**
	* Removes all the tasks entries where groupId = &#63; and resolverUserId = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param resolverUserId the resolver user ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_R(long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_R(groupId, resolverUserId);
	}

	/**
	* Removes all the tasks entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the tasks entries where groupId = &#63;.
	*
	* @param groupId the group ID to search with
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Filters by the user's permissions and counts all the tasks entries where groupId = &#63;.
	*
	* @param groupId the group ID to search with
	* @return the number of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Counts all the tasks entries where userId = &#63;.
	*
	* @param userId the user ID to search with
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Counts all the tasks entries where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID to search with
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAssigneeUserId(long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssigneeUserId(assigneeUserId);
	}

	/**
	* Counts all the tasks entries where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID to search with
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByResolverUserId(long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByResolverUserId(resolverUserId);
	}

	/**
	* Counts all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param userId the user ID to search with
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_U(groupId, userId);
	}

	/**
	* Filters by the user's permissions and counts all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param userId the user ID to search with
	* @return the number of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_U(groupId, userId);
	}

	/**
	* Counts all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param assigneeUserId the assignee user ID to search with
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_A(long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_A(groupId, assigneeUserId);
	}

	/**
	* Filters by the user's permissions and counts all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param assigneeUserId the assignee user ID to search with
	* @return the number of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_A(long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_A(groupId, assigneeUserId);
	}

	/**
	* Counts all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resolverUserId the resolver user ID to search with
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_R(long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_R(groupId, resolverUserId);
	}

	/**
	* Filters by the user's permissions and counts all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resolverUserId the resolver user ID to search with
	* @return the number of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_R(long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_R(groupId, resolverUserId);
	}

	/**
	* Counts all the tasks entries.
	*
	* @return the number of tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TasksEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TasksEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.tasks.service.ClpSerializer.getServletContextName(),
					TasksEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(TasksEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(TasksEntryPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(TasksEntryUtil.class, "_persistence");
	}

	private static TasksEntryPersistence _persistence;
}