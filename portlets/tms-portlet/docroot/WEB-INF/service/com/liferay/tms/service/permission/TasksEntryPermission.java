/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.tms.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.tms.model.TasksEntry;
import com.liferay.tms.service.TasksEntryLocalServiceUtil;

/**
 * @author Ryan Park
 */
public class TasksEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, long tasksEntryId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, tasksEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, TasksEntry tasksEntry,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, tasksEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long tasksEntryId,
			String actionId)
		throws PortalException, SystemException {

		TasksEntry tasksEntry = TasksEntryLocalServiceUtil.getTasksEntry(
			tasksEntryId);

		return contains(permissionChecker, tasksEntry, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, TasksEntry tasksEntry,
		String actionId) {

		if (permissionChecker.getUserId() == tasksEntry.getAssigneeUserId()) {
			return true;
		}

		if (permissionChecker.hasOwnerPermission(
				tasksEntry.getCompanyId(), TasksEntry.class.getName(),
				tasksEntry.getTasksEntryId(), tasksEntry.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			tasksEntry.getGroupId(), TasksEntry.class.getName(),
			tasksEntry.getTasksEntryId(), actionId);
	}

}