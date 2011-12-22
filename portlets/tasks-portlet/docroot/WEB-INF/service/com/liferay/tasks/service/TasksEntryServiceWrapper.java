/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.tasks.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link TasksEntryService}.
 * </p>
 *
 * @author    Ryan Park
 * @see       TasksEntryService
 * @generated
 */
public class TasksEntryServiceWrapper implements TasksEntryService,
	ServiceWrapper<TasksEntryService> {
	public TasksEntryServiceWrapper(TasksEntryService tasksEntryService) {
		_tasksEntryService = tasksEntryService;
	}

	public com.liferay.tasks.model.TasksEntry addTasksEntry(
		java.lang.String title, int priority, long assigneeUserId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute, boolean neverDue,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryService.addTasksEntry(title, priority,
			assigneeUserId, dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
			dueDateMinute, neverDue, serviceContext);
	}

	public com.liferay.tasks.model.TasksEntry getTasksEntry(long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryService.getTasksEntry(tasksEntryId);
	}

	public com.liferay.tasks.model.TasksEntry updateTasksEntry(
		long tasksEntryId, java.lang.String title, int priority,
		long assigneeUserId, long resolverUserId, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean neverDue, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryService.updateTasksEntry(tasksEntryId, title,
			priority, assigneeUserId, resolverUserId, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute, neverDue, status,
			serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TasksEntryService getWrappedTasksEntryService() {
		return _tasksEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTasksEntryService(TasksEntryService tasksEntryService) {
		_tasksEntryService = tasksEntryService;
	}

	public TasksEntryService getWrappedService() {
		return _tasksEntryService;
	}

	public void setWrappedService(TasksEntryService tasksEntryService) {
		_tasksEntryService = tasksEntryService;
	}

	private TasksEntryService _tasksEntryService;
}