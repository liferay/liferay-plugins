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

package com.liferay.tasks.social;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.TasksEntryLocalServiceUtil;
import com.liferay.tasks.service.permission.TasksEntryPermission;

/**
 * @author Ryan Park
 */
public class TasksActivityInterpreter extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		TasksEntry tasksEntry = TasksEntryLocalServiceUtil.getTasksEntry(
			activity.getClassPK());

		return getJSONValue(
			activity.getExtraData(), "title", tasksEntry.getTitle());
	}

	@Override
	protected String getLink(
		SocialActivity activity, ServiceContext serviceContext) {

		return StringPool.BLANK;
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		long userId = activity.getUserId();
		long receiverUserId = activity.getReceiverUserId();

		int activityType = activity.getType();

		if (activityType == TasksActivityKeys.RESOLVE_ENTRY) {
			TasksEntry tasksEntry = TasksEntryLocalServiceUtil.getTasksEntry(
				activity.getClassPK());

			userId = tasksEntry.getResolverUserId();
			receiverUserId = tasksEntry.getUserId();
		}

		String creatorUserName = getUserName(userId, serviceContext);
		String receiverUserName = getUserName(receiverUserId, serviceContext);

		return new Object[] {creatorUserName, receiverUserName};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		long userId = activity.getUserId();
		long receiverUserId = activity.getReceiverUserId();

		int activityType = activity.getType();

		if (activityType == TasksActivityKeys.ADD_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				return "activity-tasks-add-entry-for";
			}
			else {
				return "activity-tasks-add-entry";
			}
		}
		else if (activityType == TasksActivityKeys.REOPEN_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				return "activity-tasks-reopen-entry-for";
			}
			else {
				return "activity-tasks-reopen-entry";
			}
		}
		else if (activityType == TasksActivityKeys.RESOLVE_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				return "activity-tasks-resolve-entry-for";
			}
			else {
				return "activity-tasks-resolve-entry";
			}
		}
		else if (activityType == TasksActivityKeys.UPDATE_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				return "activity-tasks-update-entry-for";
			}
			else {
				return "activity-tasks-update-entry";
			}
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		TasksEntry tasksEntry = TasksEntryLocalServiceUtil.fetchTasksEntry(
			activity.getClassPK());

		if (tasksEntry == null) {
			return false;
		}

		return TasksEntryPermission.contains(
			permissionChecker, tasksEntry, ActionKeys.VIEW);
	}

	private static final String[] _CLASS_NAMES = {TasksEntry.class.getName()};

}