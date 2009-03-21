/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.tms.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="TasksEntryPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface TasksEntryPersistence extends BasePersistence {
	public com.liferay.tms.model.TasksEntry create(long tasksEntryId);

	public com.liferay.tms.model.TasksEntry remove(long tasksEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry remove(
		com.liferay.tms.model.TasksEntry tasksEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.tms.model.TasksEntry update(
		com.liferay.tms.model.TasksEntry tasksEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.tms.model.TasksEntry update(
		com.liferay.tms.model.TasksEntry tasksEntry, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.tms.model.TasksEntry updateImpl(
		com.liferay.tms.model.TasksEntry tasksEntry, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.tms.model.TasksEntry findByPrimaryKey(long tasksEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry fetchByPrimaryKey(long tasksEntryId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByGroupId(
		long groupId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.tms.model.TasksEntry findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry[] findByGroupId_PrevAndNext(
		long tasksEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByUserId(
		long userId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.tms.model.TasksEntry findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry[] findByUserId_PrevAndNext(
		long tasksEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.tms.model.TasksEntry findByAssigneeUserId_First(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry findByAssigneeUserId_Last(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry[] findByAssigneeUserId_PrevAndNext(
		long tasksEntryId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByResolverUserId(
		long resolverUserId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.tms.model.TasksEntry findByResolverUserId_First(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry findByResolverUserId_Last(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry[] findByResolverUserId_PrevAndNext(
		long tasksEntryId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByG_U(
		long groupId, long userId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.tms.model.TasksEntry findByG_U_First(long groupId,
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry findByG_U_Last(long groupId,
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry[] findByG_U_PrevAndNext(
		long tasksEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.tms.model.TasksEntry findByG_A_First(long groupId,
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry findByG_A_Last(long groupId,
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry[] findByG_A_PrevAndNext(
		long tasksEntryId, long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.tms.model.TasksEntry findByG_R_First(long groupId,
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry findByG_R_Last(long groupId,
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public com.liferay.tms.model.TasksEntry[] findByG_R_PrevAndNext(
		long tasksEntryId, long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findAll(int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.tms.model.TasksEntry> findAll(int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByGroupId(long groupId)
		throws com.liferay.portal.SystemException;

	public void removeByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public void removeByAssigneeUserId(long assigneeUserId)
		throws com.liferay.portal.SystemException;

	public void removeByResolverUserId(long resolverUserId)
		throws com.liferay.portal.SystemException;

	public void removeByG_U(long groupId, long userId)
		throws com.liferay.portal.SystemException;

	public void removeByG_A(long groupId, long assigneeUserId)
		throws com.liferay.portal.SystemException;

	public void removeByG_R(long groupId, long resolverUserId)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByGroupId(long groupId)
		throws com.liferay.portal.SystemException;

	public int countByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public int countByAssigneeUserId(long assigneeUserId)
		throws com.liferay.portal.SystemException;

	public int countByResolverUserId(long resolverUserId)
		throws com.liferay.portal.SystemException;

	public int countByG_U(long groupId, long userId)
		throws com.liferay.portal.SystemException;

	public int countByG_A(long groupId, long assigneeUserId)
		throws com.liferay.portal.SystemException;

	public int countByG_R(long groupId, long resolverUserId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}