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

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.TasksEntryLocalServiceUtil;

/**
 * @author Ryan Park
 * @generated
 */
public abstract class TasksEntryActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public TasksEntryActionableDynamicQuery() throws SystemException {
		setBaseLocalService(TasksEntryLocalServiceUtil.getService());
		setClass(TasksEntry.class);

		setClassLoader(com.liferay.tasks.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("tasksEntryId");
	}
}