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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
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
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static TasksEntry update(TasksEntry tasksEntry)
		throws SystemException {
		return getPersistence().update(tasksEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static TasksEntry update(TasksEntry tasksEntry,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(tasksEntry, serviceContext);
	}

	/**
	* Returns all the tasks entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the tasks entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Returns the first tasks entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
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
	* Returns the first tasks entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
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
	* Returns the last tasks entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
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
	* Returns all the tasks entries that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the tasks entries that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
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
	* Removes all the tasks entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of tasks entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the tasks entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the tasks entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the tasks entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Returns the first tasks entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
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
	* Returns the first tasks entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last tasks entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
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
	* Returns the last tasks entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where userId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param userId the user ID
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
	* Removes all the tasks entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of tasks entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the tasks entries where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssigneeUserId(assigneeUserId);
	}

	/**
	* Returns a range of all the tasks entries where assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssigneeUserId(assigneeUserId, start, end);
	}

	/**
	* Returns an ordered range of all the tasks entries where assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Returns the first tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
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
	* Returns the first tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByAssigneeUserId_First(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssigneeUserId_First(assigneeUserId,
			orderByComparator);
	}

	/**
	* Returns the last tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
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
	* Returns the last tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByAssigneeUserId_Last(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssigneeUserId_Last(assigneeUserId, orderByComparator);
	}

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param assigneeUserId the assignee user ID
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
	* Removes all the tasks entries where assigneeUserId = &#63; from the database.
	*
	* @param assigneeUserId the assignee user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAssigneeUserId(long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAssigneeUserId(assigneeUserId);
	}

	/**
	* Returns the number of tasks entries where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAssigneeUserId(long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssigneeUserId(assigneeUserId);
	}

	/**
	* Returns all the tasks entries where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByResolverUserId(
		long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByResolverUserId(resolverUserId);
	}

	/**
	* Returns a range of all the tasks entries where resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByResolverUserId(resolverUserId, start, end);
	}

	/**
	* Returns an ordered range of all the tasks entries where resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Returns the first tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
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
	* Returns the first tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByResolverUserId_First(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByResolverUserId_First(resolverUserId,
			orderByComparator);
	}

	/**
	* Returns the last tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
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
	* Returns the last tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByResolverUserId_Last(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByResolverUserId_Last(resolverUserId, orderByComparator);
	}

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param resolverUserId the resolver user ID
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
	* Removes all the tasks entries where resolverUserId = &#63; from the database.
	*
	* @param resolverUserId the resolver user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByResolverUserId(long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByResolverUserId(resolverUserId);
	}

	/**
	* Returns the number of tasks entries where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByResolverUserId(long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByResolverUserId(resolverUserId);
	}

	/**
	* Returns all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_U(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_U(groupId, userId);
	}

	/**
	* Returns a range of all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_U(groupId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
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
	* Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByG_U_First(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_U_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
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
	* Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByG_U_Last(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_U_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param userId the user ID
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
	* Returns all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_U(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_U(groupId, userId);
	}

	/**
	* Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_U(groupId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param userId the user ID
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
	* Removes all the tasks entries where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_U(groupId, userId);
	}

	/**
	* Returns the number of tasks entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_U(groupId, userId);
	}

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_U(groupId, userId);
	}

	/**
	* Returns all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_A(groupId, assigneeUserId);
	}

	/**
	* Returns a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_A(groupId, assigneeUserId, start, end);
	}

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
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
	* Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByG_A_First(
		long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_A_First(groupId, assigneeUserId, orderByComparator);
	}

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
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
	* Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByG_A_Last(
		long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_A_Last(groupId, assigneeUserId, orderByComparator);
	}

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
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
	* Returns all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @return the matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_A(
		long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_A(groupId, assigneeUserId);
	}

	/**
	* Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
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
	* Removes all the tasks entries where groupId = &#63; and assigneeUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_A(long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_A(groupId, assigneeUserId);
	}

	/**
	* Returns the number of tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_A(long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_A(groupId, assigneeUserId);
	}

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @return the number of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_A(long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_A(groupId, assigneeUserId);
	}

	/**
	* Returns all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_R(groupId, resolverUserId);
	}

	/**
	* Returns a range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_R(groupId, resolverUserId, start, end);
	}

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Returns the first tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
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
	* Returns the first tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByG_R_First(
		long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_R_First(groupId, resolverUserId, orderByComparator);
	}

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
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
	* Returns the last tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByG_R_Last(
		long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_R_Last(groupId, resolverUserId, orderByComparator);
	}

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
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
	* Returns all the tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @return the matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_R(
		long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_R(groupId, resolverUserId);
	}

	/**
	* Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
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
	* Removes all the tasks entries where groupId = &#63; and resolverUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_R(long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_R(groupId, resolverUserId);
	}

	/**
	* Returns the number of tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_R(long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_R(groupId, resolverUserId);
	}

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @return the number of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_R(long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_R(groupId, resolverUserId);
	}

	/**
	* Caches the tasks entry in the entity cache if it is enabled.
	*
	* @param tasksEntry the tasks entry
	*/
	public static void cacheResult(
		com.liferay.tasks.model.TasksEntry tasksEntry) {
		getPersistence().cacheResult(tasksEntry);
	}

	/**
	* Caches the tasks entries in the entity cache if it is enabled.
	*
	* @param tasksEntries the tasks entries
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
	* @param tasksEntryId the primary key of the tasks entry
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
		com.liferay.tasks.model.TasksEntry tasksEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(tasksEntry);
	}

	/**
	* Returns the tasks entry with the primary key or throws a {@link com.liferay.tasks.NoSuchTasksEntryException} if it could not be found.
	*
	* @param tasksEntryId the primary key of the tasks entry
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
	* Returns the tasks entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry, or <code>null</code> if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tasks.model.TasksEntry fetchByPrimaryKey(
		long tasksEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(tasksEntryId);
	}

	/**
	* Returns all the tasks entries.
	*
	* @return the tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the tasks entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tasks.model.TasksEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the tasks entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
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
	* Removes all the tasks entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of tasks entries.
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

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(TasksEntryPersistence persistence) {
	}

	private static TasksEntryPersistence _persistence;
}