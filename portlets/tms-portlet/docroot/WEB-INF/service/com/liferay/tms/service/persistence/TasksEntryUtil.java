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

/**
 * <a href="TasksEntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TasksEntryUtil {
	public static com.liferay.tms.model.TasksEntry create(long tasksEntryId) {
		return getPersistence().create(tasksEntryId);
	}

	public static com.liferay.tms.model.TasksEntry remove(long tasksEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().remove(tasksEntryId);
	}

	public static com.liferay.tms.model.TasksEntry remove(
		com.liferay.tms.model.TasksEntry tasksEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(tasksEntry);
	}

	public static com.liferay.tms.model.TasksEntry update(
		com.liferay.tms.model.TasksEntry tasksEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(tasksEntry);
	}

	public static com.liferay.tms.model.TasksEntry update(
		com.liferay.tms.model.TasksEntry tasksEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(tasksEntry, merge);
	}

	public static com.liferay.tms.model.TasksEntry updateImpl(
		com.liferay.tms.model.TasksEntry tasksEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(tasksEntry, merge);
	}

	public static com.liferay.tms.model.TasksEntry findByPrimaryKey(
		long tasksEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByPrimaryKey(tasksEntryId);
	}

	public static com.liferay.tms.model.TasksEntry fetchByPrimaryKey(
		long tasksEntryId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(tasksEntryId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByGroupId(
		long groupId) throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId, start, end, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByGroupId_First(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByGroupId_First(groupId, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByGroupId_Last(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByGroupId_Last(groupId, obc);
	}

	public static com.liferay.tms.model.TasksEntry[] findByGroupId_PrevAndNext(
		long tasksEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(tasksEntryId, groupId, obc);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByUserId(
		long userId) throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId, start, end, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByUserId_First(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByUserId_First(userId, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByUserId_Last(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByUserId_Last(userId, obc);
	}

	public static com.liferay.tms.model.TasksEntry[] findByUserId_PrevAndNext(
		long tasksEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence()
				   .findByUserId_PrevAndNext(tasksEntryId, userId, obc);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId) throws com.liferay.portal.SystemException {
		return getPersistence().findByAssigneeUserId(assigneeUserId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByAssigneeUserId(assigneeUserId, start, end);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByAssigneeUserId(assigneeUserId, start, end, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByAssigneeUserId_First(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByAssigneeUserId_First(assigneeUserId, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByAssigneeUserId_Last(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByAssigneeUserId_Last(assigneeUserId, obc);
	}

	public static com.liferay.tms.model.TasksEntry[] findByAssigneeUserId_PrevAndNext(
		long tasksEntryId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence()
				   .findByAssigneeUserId_PrevAndNext(tasksEntryId,
			assigneeUserId, obc);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByResolverUserId(
		long resolverUserId) throws com.liferay.portal.SystemException {
		return getPersistence().findByResolverUserId(resolverUserId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByResolverUserId(resolverUserId, start, end);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByResolverUserId(
		long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByResolverUserId(resolverUserId, start, end, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByResolverUserId_First(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByResolverUserId_First(resolverUserId, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByResolverUserId_Last(
		long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByResolverUserId_Last(resolverUserId, obc);
	}

	public static com.liferay.tms.model.TasksEntry[] findByResolverUserId_PrevAndNext(
		long tasksEntryId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence()
				   .findByResolverUserId_PrevAndNext(tasksEntryId,
			resolverUserId, obc);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByG_U(
		long groupId, long userId) throws com.liferay.portal.SystemException {
		return getPersistence().findByG_U(groupId, userId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_U(groupId, userId, start, end);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_U(groupId, userId, start, end, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByG_U_First(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByG_U_First(groupId, userId, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByG_U_Last(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByG_U_Last(groupId, userId, obc);
	}

	public static com.liferay.tms.model.TasksEntry[] findByG_U_PrevAndNext(
		long tasksEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence()
				   .findByG_U_PrevAndNext(tasksEntryId, groupId, userId, obc);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_A(groupId, assigneeUserId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_A(groupId, assigneeUserId, start, end);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByG_A(
		long groupId, long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByG_A(groupId, assigneeUserId, start, end, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByG_A_First(
		long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByG_A_First(groupId, assigneeUserId, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByG_A_Last(
		long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByG_A_Last(groupId, assigneeUserId, obc);
	}

	public static com.liferay.tms.model.TasksEntry[] findByG_A_PrevAndNext(
		long tasksEntryId, long groupId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence()
				   .findByG_A_PrevAndNext(tasksEntryId, groupId,
			assigneeUserId, obc);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_R(groupId, resolverUserId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_R(groupId, resolverUserId, start, end);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findByG_R(
		long groupId, long resolverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByG_R(groupId, resolverUserId, start, end, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByG_R_First(
		long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByG_R_First(groupId, resolverUserId, obc);
	}

	public static com.liferay.tms.model.TasksEntry findByG_R_Last(
		long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence().findByG_R_Last(groupId, resolverUserId, obc);
	}

	public static com.liferay.tms.model.TasksEntry[] findByG_R_PrevAndNext(
		long tasksEntryId, long groupId, long resolverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTasksEntryException {
		return getPersistence()
				   .findByG_R_PrevAndNext(tasksEntryId, groupId,
			resolverUserId, obc);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	public static void removeByUserId(long userId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByUserId(userId);
	}

	public static void removeByAssigneeUserId(long assigneeUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByAssigneeUserId(assigneeUserId);
	}

	public static void removeByResolverUserId(long resolverUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByResolverUserId(resolverUserId);
	}

	public static void removeByG_U(long groupId, long userId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByG_U(groupId, userId);
	}

	public static void removeByG_A(long groupId, long assigneeUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByG_A(groupId, assigneeUserId);
	}

	public static void removeByG_R(long groupId, long resolverUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByG_R(groupId, resolverUserId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByGroupId(long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	public static int countByUserId(long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUserId(userId);
	}

	public static int countByAssigneeUserId(long assigneeUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByAssigneeUserId(assigneeUserId);
	}

	public static int countByResolverUserId(long resolverUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByResolverUserId(resolverUserId);
	}

	public static int countByG_U(long groupId, long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByG_U(groupId, userId);
	}

	public static int countByG_A(long groupId, long assigneeUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByG_A(groupId, assigneeUserId);
	}

	public static int countByG_R(long groupId, long resolverUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByG_R(groupId, resolverUserId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static TasksEntryPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(TasksEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static TasksEntryPersistence _persistence;
}