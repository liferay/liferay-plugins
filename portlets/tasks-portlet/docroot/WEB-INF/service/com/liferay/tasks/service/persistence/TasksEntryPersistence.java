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

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.tasks.model.TasksEntry;

/**
 * The persistence interface for the tasks entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see TasksEntryPersistenceImpl
 * @see TasksEntryUtil
 * @generated
 */
public interface TasksEntryPersistence extends BasePersistence<TasksEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TasksEntryUtil} to access the tasks entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the tasks entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry[] findByGroupId_PrevAndNext(
		long tasksEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry[] filterFindByGroupId_PrevAndNext(
		long tasksEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tasks entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the tasks entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first tasks entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last tasks entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry[] findByUserId_PrevAndNext(
		long tasksEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tasks entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the tasks entries where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry findByAssigneeUserId_First(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByAssigneeUserId_First(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry findByAssigneeUserId_Last(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByAssigneeUserId_Last(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry[] findByAssigneeUserId_PrevAndNext(
		long tasksEntryId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where assigneeUserId = &#63; from the database.
	*
	* @param assigneeUserId the assignee user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAssigneeUserId(long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tasks entries where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByAssigneeUserId(long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the tasks entries where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByResolverUserId(
		long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry findByResolverUserId_First(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByResolverUserId_First(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry findByResolverUserId_Last(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByResolverUserId_Last(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry[] findByResolverUserId_PrevAndNext(
		long tasksEntryId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where resolverUserId = &#63; from the database.
	*
	* @param resolverUserId the resolver user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByResolverUserId(long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tasks entries where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByResolverUserId(long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByG_U(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry findByG_U_First(long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByG_U_First(long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry findByG_U_Last(long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByG_U_Last(long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry[] findByG_U_PrevAndNext(
		long tasksEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_U(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry[] filterFindByG_U_PrevAndNext(
		long tasksEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tasks entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry findByG_A_First(long groupId,
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByG_A_First(long groupId,
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry findByG_A_Last(long groupId,
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByG_A_Last(long groupId,
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry[] findByG_A_PrevAndNext(
		long tasksEntryId, long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @return the matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_A(
		long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_A(
		long groupId, long assigneeUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_A(
		long groupId, long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry[] filterFindByG_A_PrevAndNext(
		long tasksEntryId, long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where groupId = &#63; and assigneeUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_A(long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_A(long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @return the number of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByG_A(long groupId, long assigneeUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @return the matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry findByG_R_First(long groupId,
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByG_R_First(long groupId,
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry findByG_R_Last(long groupId,
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByG_R_Last(long groupId,
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry[] findByG_R_PrevAndNext(
		long tasksEntryId, long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @return the matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_R(
		long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_R(
		long groupId, long resolverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> filterFindByG_R(
		long groupId, long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tasks.model.TasksEntry[] filterFindByG_R_PrevAndNext(
		long tasksEntryId, long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where groupId = &#63; and resolverUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_R(long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @return the number of matching tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_R(long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @return the number of matching tasks entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByG_R(long groupId, long resolverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the tasks entry in the entity cache if it is enabled.
	*
	* @param tasksEntry the tasks entry
	*/
	public void cacheResult(com.liferay.tasks.model.TasksEntry tasksEntry);

	/**
	* Caches the tasks entries in the entity cache if it is enabled.
	*
	* @param tasksEntries the tasks entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.tasks.model.TasksEntry> tasksEntries);

	/**
	* Creates a new tasks entry with the primary key. Does not add the tasks entry to the database.
	*
	* @param tasksEntryId the primary key for the new tasks entry
	* @return the new tasks entry
	*/
	public com.liferay.tasks.model.TasksEntry create(long tasksEntryId);

	/**
	* Removes the tasks entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry that was removed
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry remove(long tasksEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	public com.liferay.tasks.model.TasksEntry updateImpl(
		com.liferay.tasks.model.TasksEntry tasksEntry)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the tasks entry with the primary key or throws a {@link com.liferay.tasks.NoSuchTasksEntryException} if it could not be found.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry
	* @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry findByPrimaryKey(
		long tasksEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tasks.NoSuchTasksEntryException;

	/**
	* Returns the tasks entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry, or <code>null</code> if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry fetchByPrimaryKey(
		long tasksEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the tasks entries.
	*
	* @return the tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tasks.model.TasksEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tasks.model.TasksEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the tasks entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of tasks entries.
	*
	* @return the number of tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}