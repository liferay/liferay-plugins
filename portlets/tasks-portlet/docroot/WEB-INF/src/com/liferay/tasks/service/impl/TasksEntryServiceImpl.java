/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.tasks.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.base.TasksEntryServiceBaseImpl;
import com.liferay.tasks.service.permission.TasksEntryPermission;
import com.liferay.tasks.service.permission.TasksPermission;

/**
 * @author Ryan Park
 */
public class TasksEntryServiceImpl extends TasksEntryServiceBaseImpl {

	public TasksEntry addTasksEntry(
			String title, int priority, long assigneeUserId, int dueDateMonth,
			int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, ServiceContext serviceContext)
		throws PortalException, SystemException {

		TasksPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_ENTRY);

		return tasksEntryLocalService.addTasksEntry(
			getUserId(), title, priority, assigneeUserId, dueDateMonth,
			dueDateDay, dueDateYear, dueDateHour, dueDateMinute, neverDue,
			serviceContext);
	}

	public TasksEntry deleteTasksEntry(long tasksEntryId)
		throws PortalException, SystemException {

		TasksEntryPermission.check(
			getPermissionChecker(), tasksEntryId, ActionKeys.UPDATE);

		return tasksEntryLocalService.deleteTasksEntry(tasksEntryId);
	}

	public TasksEntry getTasksEntry(long tasksEntryId)
		throws PortalException, SystemException {

		TasksEntryPermission.check(
			getPermissionChecker(), tasksEntryId, ActionKeys.VIEW);

		return tasksEntryLocalService.getTasksEntry(tasksEntryId);
	}

	public TasksEntry updateTasksEntry(
			long tasksEntryId, String title, int priority, long assigneeUserId,
			long resolverUserId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, int status, ServiceContext serviceContext)
		throws PortalException, SystemException {

		TasksEntryPermission.check(
			getPermissionChecker(), tasksEntryId, ActionKeys.UPDATE);

		return tasksEntryLocalService.updateTasksEntry(
			tasksEntryId, title, priority, assigneeUserId, resolverUserId,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			neverDue, status, serviceContext);
	}

	public TasksEntry updateTasksEntryStatus(
			long tasksEntryId, long resolverUserId, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		TasksEntryPermission.check(
			getPermissionChecker(), tasksEntryId, ActionKeys.UPDATE);

		return tasksEntryLocalService.updateTasksEntryStatus(
			tasksEntryId, resolverUserId, status, serviceContext);
	}

}