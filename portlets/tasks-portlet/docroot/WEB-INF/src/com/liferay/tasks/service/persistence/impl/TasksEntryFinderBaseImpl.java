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

package com.liferay.tasks.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.persistence.TasksEntryPersistence;

/**
 * @author Ryan Park
 * @generated
 */
public class TasksEntryFinderBaseImpl extends BasePersistenceImpl<TasksEntry> {
	/**
	 * Returns the tasks entry persistence.
	 *
	 * @return the tasks entry persistence
	 */
	public TasksEntryPersistence getTasksEntryPersistence() {
		return tasksEntryPersistence;
	}

	/**
	 * Sets the tasks entry persistence.
	 *
	 * @param tasksEntryPersistence the tasks entry persistence
	 */
	public void setTasksEntryPersistence(
		TasksEntryPersistence tasksEntryPersistence) {
		this.tasksEntryPersistence = tasksEntryPersistence;
	}

	@BeanReference(type = TasksEntryPersistence.class)
	protected TasksEntryPersistence tasksEntryPersistence;
}