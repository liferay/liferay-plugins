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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.tasks.exception.NoSuchTasksEntryException;
import com.liferay.tasks.model.TasksEntry;

/**
 * The persistence interface for the tasks entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see com.liferay.tasks.service.persistence.impl.TasksEntryPersistenceImpl
 * @see TasksEntryUtil
 * @generated
 */
@ProviderType
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
	*/
	public java.util.List<TasksEntry> findByGroupId(long groupId);

	/**
	* Returns a range of all the tasks entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] findByGroupId_PrevAndNext(long tasksEntryId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByGroupId(long groupId);

	/**
	* Returns a range of all the tasks entries that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] filterFindByGroupId_PrevAndNext(long tasksEntryId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of tasks entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching tasks entries
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching tasks entries that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns all the tasks entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByUserId(long userId);

	/**
	* Returns a range of all the tasks entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByUserId(long userId, int start,
		int end);

	/**
	* Returns an ordered range of all the tasks entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first tasks entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the last tasks entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where userId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] findByUserId_PrevAndNext(long tasksEntryId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of tasks entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching tasks entries
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the tasks entries where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByAssigneeUserId(long assigneeUserId);

	/**
	* Returns a range of all the tasks entries where assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries where assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByAssigneeUserId_First(long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByAssigneeUserId_First(long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the last tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByAssigneeUserId_Last(long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByAssigneeUserId_Last(long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where assigneeUserId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] findByAssigneeUserId_PrevAndNext(long tasksEntryId,
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where assigneeUserId = &#63; from the database.
	*
	* @param assigneeUserId the assignee user ID
	*/
	public void removeByAssigneeUserId(long assigneeUserId);

	/**
	* Returns the number of tasks entries where assigneeUserId = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @return the number of matching tasks entries
	*/
	public int countByAssigneeUserId(long assigneeUserId);

	/**
	* Returns all the tasks entries where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByResolverUserId(long resolverUserId);

	/**
	* Returns a range of all the tasks entries where resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries where resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByResolverUserId_First(long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByResolverUserId_First(long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the last tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByResolverUserId_Last(long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByResolverUserId_Last(long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where resolverUserId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] findByResolverUserId_PrevAndNext(long tasksEntryId,
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where resolverUserId = &#63; from the database.
	*
	* @param resolverUserId the resolver user ID
	*/
	public void removeByResolverUserId(long resolverUserId);

	/**
	* Returns the number of tasks entries where resolverUserId = &#63;.
	*
	* @param resolverUserId the resolver user ID
	* @return the number of matching tasks entries
	*/
	public int countByResolverUserId(long resolverUserId);

	/**
	* Returns all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_U(long groupId, long userId);

	/**
	* Returns a range of all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_U(long groupId, long userId,
		int start, int end);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_U(long groupId, long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_U(long groupId, long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByG_U_First(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByG_U_First(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByG_U_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByG_U_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] findByG_U_PrevAndNext(long tasksEntryId, long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_U(long groupId, long userId);

	/**
	* Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_U(long groupId,
		long userId, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_U(long groupId,
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] filterFindByG_U_PrevAndNext(long tasksEntryId,
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	*/
	public void removeByG_U(long groupId, long userId);

	/**
	* Returns the number of tasks entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching tasks entries
	*/
	public int countByG_U(long groupId, long userId);

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching tasks entries that the user has permission to view
	*/
	public int filterCountByG_U(long groupId, long userId);

	/**
	* Returns all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_A(long groupId,
		long assigneeUserId);

	/**
	* Returns a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_A(long groupId,
		long assigneeUserId, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_A(long groupId,
		long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_A(long groupId,
		long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByG_A_First(long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByG_A_First(long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByG_A_Last(long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByG_A_Last(long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] findByG_A_PrevAndNext(long tasksEntryId, long groupId,
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @return the matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_A(long groupId,
		long assigneeUserId);

	/**
	* Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_A(long groupId,
		long assigneeUserId, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63; and assigneeUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_A(long groupId,
		long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] filterFindByG_A_PrevAndNext(long tasksEntryId,
		long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where groupId = &#63; and assigneeUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	*/
	public void removeByG_A(long groupId, long assigneeUserId);

	/**
	* Returns the number of tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @return the number of matching tasks entries
	*/
	public int countByG_A(long groupId, long assigneeUserId);

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @return the number of matching tasks entries that the user has permission to view
	*/
	public int filterCountByG_A(long groupId, long assigneeUserId);

	/**
	* Returns all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_R(long groupId,
		long resolverUserId);

	/**
	* Returns a range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_R(long groupId,
		long resolverUserId, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_R(long groupId,
		long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_R(long groupId,
		long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByG_R_First(long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByG_R_First(long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByG_R_Last(long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByG_R_Last(long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] findByG_R_PrevAndNext(long tasksEntryId, long groupId,
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @return the matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_R(long groupId,
		long resolverUserId);

	/**
	* Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_R(long groupId,
		long resolverUserId, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63; and resolverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_R(long groupId,
		long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] filterFindByG_R_PrevAndNext(long tasksEntryId,
		long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Removes all the tasks entries where groupId = &#63; and resolverUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	*/
	public void removeByG_R(long groupId, long resolverUserId);

	/**
	* Returns the number of tasks entries where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @return the number of matching tasks entries
	*/
	public int countByG_R(long groupId, long resolverUserId);

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param resolverUserId the resolver user ID
	* @return the number of matching tasks entries that the user has permission to view
	*/
	public int filterCountByG_R(long groupId, long resolverUserId);

	/**
	* Returns all the tasks entries where userId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param status the status
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByU_S(long userId, int status);

	/**
	* Returns a range of all the tasks entries where userId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByU_S(long userId, int status,
		int start, int end);

	/**
	* Returns an ordered range of all the tasks entries where userId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByU_S(long userId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where userId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByU_S(long userId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first tasks entry in the ordered set where userId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByU_S_First(long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where userId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByU_S_First(long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the last tasks entry in the ordered set where userId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByU_S_Last(long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where userId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByU_S_Last(long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where userId = &#63; and status = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param userId the user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] findByU_S_PrevAndNext(long tasksEntryId, long userId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries where userId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param statuses the statuses
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByU_S(long userId, int[] statuses);

	/**
	* Returns a range of all the tasks entries where userId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param statuses the statuses
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByU_S(long userId, int[] statuses,
		int start, int end);

	/**
	* Returns an ordered range of all the tasks entries where userId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param statuses the statuses
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByU_S(long userId, int[] statuses,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where userId = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByU_S(long userId, int[] statuses,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the tasks entries where userId = &#63; and status = &#63; from the database.
	*
	* @param userId the user ID
	* @param status the status
	*/
	public void removeByU_S(long userId, int status);

	/**
	* Returns the number of tasks entries where userId = &#63; and status = &#63;.
	*
	* @param userId the user ID
	* @param status the status
	* @return the number of matching tasks entries
	*/
	public int countByU_S(long userId, int status);

	/**
	* Returns the number of tasks entries where userId = &#63; and status = any &#63;.
	*
	* @param userId the user ID
	* @param statuses the statuses
	* @return the number of matching tasks entries
	*/
	public int countByU_S(long userId, int[] statuses);

	/**
	* Returns all the tasks entries where assigneeUserId = &#63; and status = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByA_S(long assigneeUserId, int status);

	/**
	* Returns a range of all the tasks entries where assigneeUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByA_S(long assigneeUserId,
		int status, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries where assigneeUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByA_S(long assigneeUserId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where assigneeUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByA_S(long assigneeUserId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByA_S_First(long assigneeUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByA_S_First(long assigneeUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the last tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByA_S_Last(long assigneeUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByA_S_Last(long assigneeUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] findByA_S_PrevAndNext(long tasksEntryId,
		long assigneeUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries where assigneeUserId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeUserId the assignee user ID
	* @param statuses the statuses
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByA_S(long assigneeUserId,
		int[] statuses);

	/**
	* Returns a range of all the tasks entries where assigneeUserId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeUserId the assignee user ID
	* @param statuses the statuses
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByA_S(long assigneeUserId,
		int[] statuses, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries where assigneeUserId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeUserId the assignee user ID
	* @param statuses the statuses
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByA_S(long assigneeUserId,
		int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where assigneeUserId = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByA_S(long assigneeUserId,
		int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the tasks entries where assigneeUserId = &#63; and status = &#63; from the database.
	*
	* @param assigneeUserId the assignee user ID
	* @param status the status
	*/
	public void removeByA_S(long assigneeUserId, int status);

	/**
	* Returns the number of tasks entries where assigneeUserId = &#63; and status = &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @return the number of matching tasks entries
	*/
	public int countByA_S(long assigneeUserId, int status);

	/**
	* Returns the number of tasks entries where assigneeUserId = &#63; and status = any &#63;.
	*
	* @param assigneeUserId the assignee user ID
	* @param statuses the statuses
	* @return the number of matching tasks entries
	*/
	public int countByA_S(long assigneeUserId, int[] statuses);

	/**
	* Returns all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_U_S(long groupId, long userId,
		int status);

	/**
	* Returns a range of all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_U_S(long groupId, long userId,
		int status, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_U_S(long groupId, long userId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_U_S(long groupId, long userId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByG_U_S_First(long groupId, long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByG_U_S_First(long groupId, long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByG_U_S_Last(long groupId, long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByG_U_S_Last(long groupId, long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] findByG_U_S_PrevAndNext(long tasksEntryId,
		long groupId, long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @return the matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_U_S(long groupId,
		long userId, int status);

	/**
	* Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_U_S(long groupId,
		long userId, int status, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_U_S(long groupId,
		long userId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] filterFindByG_U_S_PrevAndNext(long tasksEntryId,
		long groupId, long userId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statuses the statuses
	* @return the matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_U_S(long groupId,
		long userId, int[] statuses);

	/**
	* Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statuses the statuses
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_U_S(long groupId,
		long userId, int[] statuses, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statuses the statuses
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_U_S(long groupId,
		long userId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns all the tasks entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statuses the statuses
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_U_S(long groupId, long userId,
		int[] statuses);

	/**
	* Returns a range of all the tasks entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statuses the statuses
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_U_S(long groupId, long userId,
		int[] statuses, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statuses the statuses
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_U_S(long groupId, long userId,
		int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_U_S(long groupId, long userId,
		int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	*/
	public void removeByG_U_S(long groupId, long userId, int status);

	/**
	* Returns the number of tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @return the number of matching tasks entries
	*/
	public int countByG_U_S(long groupId, long userId, int status);

	/**
	* Returns the number of tasks entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statuses the statuses
	* @return the number of matching tasks entries
	*/
	public int countByG_U_S(long groupId, long userId, int[] statuses);

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param status the status
	* @return the number of matching tasks entries that the user has permission to view
	*/
	public int filterCountByG_U_S(long groupId, long userId, int status);

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param statuses the statuses
	* @return the number of matching tasks entries that the user has permission to view
	*/
	public int filterCountByG_U_S(long groupId, long userId, int[] statuses);

	/**
	* Returns all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_A_S(long groupId,
		long assigneeUserId, int status);

	/**
	* Returns a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_A_S(long groupId,
		long assigneeUserId, int status, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_A_S(long groupId,
		long assigneeUserId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_A_S(long groupId,
		long assigneeUserId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByG_A_S_First(long groupId, long assigneeUserId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByG_A_S_First(long groupId, long assigneeUserId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry
	* @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	*/
	public TasksEntry findByG_A_S_Last(long groupId, long assigneeUserId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	*/
	public TasksEntry fetchByG_A_S_Last(long groupId, long assigneeUserId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] findByG_A_S_PrevAndNext(long tasksEntryId,
		long groupId, long assigneeUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @return the matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_A_S(long groupId,
		long assigneeUserId, int status);

	/**
	* Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_A_S(long groupId,
		long assigneeUserId, int status, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_A_S(long groupId,
		long assigneeUserId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* @param tasksEntryId the primary key of the current tasks entry
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry[] filterFindByG_A_S_PrevAndNext(long tasksEntryId,
		long groupId, long assigneeUserId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException;

	/**
	* Returns all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param statuses the statuses
	* @return the matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_A_S(long groupId,
		long assigneeUserId, int[] statuses);

	/**
	* Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param statuses the statuses
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_A_S(long groupId,
		long assigneeUserId, int[] statuses, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param statuses the statuses
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries that the user has permission to view
	*/
	public java.util.List<TasksEntry> filterFindByG_A_S(long groupId,
		long assigneeUserId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param statuses the statuses
	* @return the matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_A_S(long groupId,
		long assigneeUserId, int[] statuses);

	/**
	* Returns a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param statuses the statuses
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_A_S(long groupId,
		long assigneeUserId, int[] statuses, int start, int end);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param statuses the statuses
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_A_S(long groupId,
		long assigneeUserId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tasks entries
	*/
	public java.util.List<TasksEntry> findByG_A_S(long groupId,
		long assigneeUserId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	*/
	public void removeByG_A_S(long groupId, long assigneeUserId, int status);

	/**
	* Returns the number of tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @return the number of matching tasks entries
	*/
	public int countByG_A_S(long groupId, long assigneeUserId, int status);

	/**
	* Returns the number of tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param statuses the statuses
	* @return the number of matching tasks entries
	*/
	public int countByG_A_S(long groupId, long assigneeUserId, int[] statuses);

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param status the status
	* @return the number of matching tasks entries that the user has permission to view
	*/
	public int filterCountByG_A_S(long groupId, long assigneeUserId, int status);

	/**
	* Returns the number of tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param assigneeUserId the assignee user ID
	* @param statuses the statuses
	* @return the number of matching tasks entries that the user has permission to view
	*/
	public int filterCountByG_A_S(long groupId, long assigneeUserId,
		int[] statuses);

	/**
	* Caches the tasks entry in the entity cache if it is enabled.
	*
	* @param tasksEntry the tasks entry
	*/
	public void cacheResult(TasksEntry tasksEntry);

	/**
	* Caches the tasks entries in the entity cache if it is enabled.
	*
	* @param tasksEntries the tasks entries
	*/
	public void cacheResult(java.util.List<TasksEntry> tasksEntries);

	/**
	* Creates a new tasks entry with the primary key. Does not add the tasks entry to the database.
	*
	* @param tasksEntryId the primary key for the new tasks entry
	* @return the new tasks entry
	*/
	public TasksEntry create(long tasksEntryId);

	/**
	* Removes the tasks entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry that was removed
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry remove(long tasksEntryId)
		throws NoSuchTasksEntryException;

	public TasksEntry updateImpl(TasksEntry tasksEntry);

	/**
	* Returns the tasks entry with the primary key or throws a {@link NoSuchTasksEntryException} if it could not be found.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry
	* @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	*/
	public TasksEntry findByPrimaryKey(long tasksEntryId)
		throws NoSuchTasksEntryException;

	/**
	* Returns the tasks entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry, or <code>null</code> if a tasks entry with the primary key could not be found
	*/
	public TasksEntry fetchByPrimaryKey(long tasksEntryId);

	@Override
	public java.util.Map<java.io.Serializable, TasksEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the tasks entries.
	*
	* @return the tasks entries
	*/
	public java.util.List<TasksEntry> findAll();

	/**
	* Returns a range of all the tasks entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of tasks entries
	*/
	public java.util.List<TasksEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the tasks entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of tasks entries
	*/
	public java.util.List<TasksEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator);

	/**
	* Returns an ordered range of all the tasks entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of tasks entries
	*/
	public java.util.List<TasksEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TasksEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the tasks entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of tasks entries.
	*
	* @return the number of tasks entries
	*/
	public int countAll();
}