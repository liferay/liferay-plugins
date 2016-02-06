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

package com.liferay.so.activities.hook.social;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.so.activities.util.SocialActivityKeyConstants;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.model.SocialActivitySet;
import com.liferay.social.kernel.service.SocialActivityLocalServiceUtil;
import com.liferay.social.kernel.service.SocialActivitySetLocalServiceUtil;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.TasksEntryLocalServiceUtil;
import com.liferay.tasks.service.permission.TasksEntryPermission;

import java.text.Format;

import java.util.List;

/**
 * @author Matthew Kong
 */
public class TasksActivityInterpreter extends SOSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected long getActivitySetId(long activityId) {
		try {
			SocialActivitySet activitySet = null;

			SocialActivity activity =
				SocialActivityLocalServiceUtil.getActivity(activityId);

			if (activity.getType() ==
					SocialActivityKeyConstants.TASKS_UPDATE_ENTRY) {

				activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getUserId(), activity.getClassNameId(),
						activity.getClassPK(), activity.getType());
			}

			if ((activitySet != null) && !isExpired(activitySet, false)) {
				return activitySet.getActivitySetId();
			}
		}
		catch (Exception e) {
		}

		return 0;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		return getBody(
			activity.getClassName(), activity.getClassPK(), serviceContext);
	}

	@Override
	protected String getBody(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		if (activitySet.getType() ==
				SocialActivityKeyConstants.TASKS_UPDATE_ENTRY) {

			return getBody(
				activitySet.getClassName(), activitySet.getClassPK(),
				serviceContext);
		}

		StringBundler sb = new StringBundler();

		sb.append("<div class=\"grouped-activity-body-container\">");
		sb.append("<div class=\"grouped-activity-body\">");

		List<SocialActivity> activities = getViewableActivities(
			activitySet, serviceContext);

		for (SocialActivity activity : activities) {
			sb.append("<div class=\"activity-subentry tasks\">");
			sb.append(
				getBody(
					activity.getClassName(), activity.getClassPK(),
					serviceContext));
			sb.append("</div>");
		}

		sb.append("</div></div>");

		return sb.toString();
	}

	protected String getBody(
			String className, long classPK, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(14);

		sb.append("<div class=\"activity-body-container ");

		TasksEntry tasksEntry = TasksEntryLocalServiceUtil.getTasksEntry(
			classPK);

		sb.append(tasksEntry.getPriorityLabel());

		sb.append("\"><div class=\"activity-body\"><div class=\"title\">");
		sb.append(getPageTitle(className, classPK, serviceContext));
		sb.append("</div><div class=\"tasks-entry-content\">");
		sb.append("<span class=\"tasks-entry-status\"><strong>");
		sb.append(serviceContext.translate("assigned-to"));
		sb.append(": </strong>");

		if (tasksEntry.getAssigneeUserId() > 0) {
			String assigneeDisplayURL = null;

			User assigneeUser = UserLocalServiceUtil.fetchUser(
				tasksEntry.getAssigneeUserId());

			if (assigneeUser != null) {
				assigneeDisplayURL = assigneeUser.getDisplayURL(
					serviceContext.getThemeDisplay());
			}

			String assigneeUserLink = wrapLink(
				assigneeDisplayURL, tasksEntry.getAssigneeFullName());

			sb.append(assigneeUserLink);
		}
		else {
			sb.append(serviceContext.translate("unassigned"));
		}

		sb.append("</span><span class=\"tasks-entry-due-date\"><strong>");
		sb.append(serviceContext.translate("due-date"));
		sb.append(": </strong>");

		if (tasksEntry.getDueDate() != null) {
			Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
				serviceContext.getLocale(), serviceContext.getTimeZone());

			sb.append(dateFormatDateTime.format(tasksEntry.getDueDate()));
		}
		else {
			sb.append(serviceContext.translate("none"));
		}

		sb.append("</span></div></div></div>");

		return sb.toString();
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		if ((activity.getReceiverUserId() <= 0) ||
			(activity.getUserId() == activity.getReceiverUserId())) {

			return null;
		}

		String receiverUserName = getUserName(
			activity.getReceiverUserId(), serviceContext);

		return new Object[] {receiverUserName};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		String titlePattern = null;

		if (activity.getType() == SocialActivityKeyConstants.TASKS_ADD_ENTRY) {
			titlePattern = "created-a-new-task";
		}
		else if (activity.getType() ==
					SocialActivityKeyConstants.TASKS_REOPEN_ENTRY) {

			titlePattern = "reopened-a-task";
		}
		else if (activity.getType() ==
					SocialActivityKeyConstants.TASKS_RESOLVE_ENTRY) {

			titlePattern = "resolved-a-task";
		}
		else if (activity.getType() ==
					SocialActivityKeyConstants.TASKS_UPDATE_ENTRY) {

			titlePattern = "updated-a-task";
		}
		else {
			return StringPool.BLANK;
		}

		if ((activity.getReceiverUserId() > 0) &&
			(activity.getUserId() != activity.getReceiverUserId())) {

			titlePattern = titlePattern.concat("-for");
		}

		return titlePattern;
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivitySet activitySet) {

		if (activitySet.getType() ==
				SocialActivityKeyConstants.TASKS_ADD_ENTRY) {

			return "created-x-new-tasks";
		}
		else if (activitySet.getType() ==
					SocialActivityKeyConstants.TASKS_REOPEN_ENTRY) {

			return "reopened-x-tasks";
		}
		else if (activitySet.getType() ==
					SocialActivityKeyConstants.TASKS_RESOLVE_ENTRY) {

			return "resolved-x-tasks";
		}
		else if (activitySet.getType() ==
					SocialActivityKeyConstants.TASKS_UPDATE_ENTRY) {

			return "made-x-updates-to-a-task";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		Group group = GroupLocalServiceUtil.fetchGroup(activity.getGroupId());

		if ((group != null) && group.isUser()) {
			return false;
		}

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