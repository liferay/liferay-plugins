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

package com.liferay.tasks.social;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.TasksEntryLocalServiceUtil;

/**
 * @author Ryan Park
 */
public class TasksActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		TasksEntry tasksEntry = TasksEntryLocalServiceUtil.getTasksEntry(
			activity.getClassPK());

		long userId = activity.getUserId();
		long receiverUserId = activity.getReceiverUserId();

		int activityType = activity.getType();

		// Link

		String link = StringPool.BLANK;

		// Title

		String titlePattern = null;

		if (activityType == TasksActivityKeys.ADD_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				titlePattern = "activity-tasks-add-entry-for";
			}
			else {
				titlePattern = "activity-tasks-add-entry";
			}
		}
		else if (activityType == TasksActivityKeys.REOPEN_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				titlePattern = "activity-tasks-reopened-entry-for";
			}
			else {
				titlePattern = "activity-tasks-reopened-entry";
			}
		}
		else if (activityType == TasksActivityKeys.RESOLVE_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				titlePattern = "activity-tasks-resolved-entry-for";
			}
			else {
				titlePattern = "activity-tasks-resolved-entry";
			}

			userId = tasksEntry.getResolverUserId();
			receiverUserId = tasksEntry.getUserId();
		}
		else if (activityType == TasksActivityKeys.UPDATE_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				titlePattern = "activity-tasks-update-entry-for";
			}
			else {
				titlePattern = "activity-tasks-update-entry";
			}
		}

		String creatorUserName = getUserName(userId, themeDisplay);
		String receiverUserName = getUserName(receiverUserId, themeDisplay);

		Object[] titleArguments =
			new Object[] {creatorUserName, receiverUserName};

		String title = themeDisplay.translate(titlePattern, titleArguments);

		// Body

		String body = getValue(
			activity.getExtraData(), "title", tasksEntry.getTitle());

		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		TasksEntry.class.getName()
	};

}