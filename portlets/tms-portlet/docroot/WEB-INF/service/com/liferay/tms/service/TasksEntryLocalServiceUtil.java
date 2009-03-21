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

package com.liferay.tms.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="TasksEntryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TasksEntryLocalServiceUtil {
	public static com.liferay.tms.model.TasksEntry addTasksEntry(
		com.liferay.tms.model.TasksEntry tasksEntry)
		throws com.liferay.portal.SystemException {
		return getService().addTasksEntry(tasksEntry);
	}

	public static com.liferay.tms.model.TasksEntry createTasksEntry(
		long tasksEntryId) {
		return getService().createTasksEntry(tasksEntryId);
	}

	public static void deleteTasksEntry(long tasksEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteTasksEntry(tasksEntryId);
	}

	public static void deleteTasksEntry(
		com.liferay.tms.model.TasksEntry tasksEntry)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteTasksEntry(tasksEntry);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.tms.model.TasksEntry getTasksEntry(
		long tasksEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getTasksEntry(tasksEntryId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> getTasksEntries(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getTasksEntries(start, end);
	}

	public static int getTasksEntriesCount()
		throws com.liferay.portal.SystemException {
		return getService().getTasksEntriesCount();
	}

	public static com.liferay.tms.model.TasksEntry updateTasksEntry(
		com.liferay.tms.model.TasksEntry tasksEntry)
		throws com.liferay.portal.SystemException {
		return getService().updateTasksEntry(tasksEntry);
	}

	public static com.liferay.tms.model.TasksEntry updateTasksEntry(
		com.liferay.tms.model.TasksEntry tasksEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateTasksEntry(tasksEntry, merge);
	}

	public static com.liferay.tms.model.TasksEntry addTasksEntry(long userId,
		java.lang.String title, int priority, long assigneeUserId,
		java.util.Date dueDate,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .addTasksEntry(userId, title, priority, assigneeUserId,
			dueDate, serviceContext);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> getAssigneeTasksEntries(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getAssigneeTasksEntries(userId, start, end);
	}

	public static int getAssigneeTasksEntriesCount(long userId)
		throws com.liferay.portal.SystemException {
		return getService().getAssigneeTasksEntriesCount(userId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> getGroupAssigneeTasksEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getGroupAssigneeTasksEntries(groupId, userId, start, end);
	}

	public static int getGroupAssigneeTasksEntriesCount(long groupId,
		long userId) throws com.liferay.portal.SystemException {
		return getService().getGroupAssigneeTasksEntriesCount(groupId, userId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> getGroupResolverTasksEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getGroupResolverTasksEntries(groupId, userId, start, end);
	}

	public static int getGroupResolverTasksEntriesCount(long groupId,
		long userId) throws com.liferay.portal.SystemException {
		return getService().getGroupResolverTasksEntriesCount(groupId, userId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> getGroupUserTasksEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getGroupUserTasksEntries(groupId, userId, start, end);
	}

	public static int getGroupUserTasksEntriesCount(long groupId, long userId)
		throws com.liferay.portal.SystemException {
		return getService().getGroupUserTasksEntriesCount(groupId, userId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> getResolverTasksEntries(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getResolverTasksEntries(userId, start, end);
	}

	public static int getResolverTasksEntriesCount(long userId)
		throws com.liferay.portal.SystemException {
		return getService().getResolverTasksEntriesCount(userId);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> getTasksEntries(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getTasksEntries(groupId, start, end);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> getTasksEntries(
		long groupId, int priority, long assigneeUserId, long reporterUserId,
		int status, long[] tagsEntryIds, long[] notTagsEntryIds, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService()
				   .getTasksEntries(groupId, priority, assigneeUserId,
			reporterUserId, status, tagsEntryIds, notTagsEntryIds, start, end);
	}

	public static int getTasksEntriesCount(long groupId)
		throws com.liferay.portal.SystemException {
		return getService().getTasksEntriesCount(groupId);
	}

	public static int getTasksEntriesCount(long groupId, int priority,
		long assigneeUserId, long reporterUserId, int status,
		long[] tagsEntryIds, long[] notTagsEntryIds)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getTasksEntriesCount(groupId, priority, assigneeUserId,
			reporterUserId, status, tagsEntryIds, notTagsEntryIds);
	}

	public static java.util.List<com.liferay.tms.model.TasksEntry> getUserTasksEntries(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getUserTasksEntries(userId, start, end);
	}

	public static int getUserTasksEntriesCount(long userId)
		throws com.liferay.portal.SystemException {
		return getService().getUserTasksEntriesCount(userId);
	}

	public static void updateTagsAsset(long userId,
		com.liferay.tms.model.TasksEntry tasksEntry,
		java.lang.String[] tagsCategories, java.lang.String[] tagsEntries)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService()
			.updateTagsAsset(userId, tasksEntry, tagsCategories, tagsEntries);
	}

	public static com.liferay.tms.model.TasksEntry updateTasksEntry(
		long tasksEntryId, java.lang.String title, int priority,
		long assigneeUserId, long resolverUserId, java.util.Date dueDate,
		int status, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .updateTasksEntry(tasksEntryId, title, priority,
			assigneeUserId, resolverUserId, dueDate, status, serviceContext);
	}

	public static TasksEntryLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("tms-portlet",
					TasksEntryLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("tms-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new TasksEntryLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(TasksEntryLocalService service) {
		_service = service;
	}

	private static TasksEntryLocalService _service;
}