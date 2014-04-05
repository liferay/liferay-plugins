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

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.tasks.TasksEntryDueDateException;
import com.liferay.tasks.TasksEntryTitleException;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.model.TasksEntryConstants;
import com.liferay.tasks.service.base.TasksEntryLocalServiceBaseImpl;
import com.liferay.tasks.social.TasksActivityKeys;
import com.liferay.tasks.util.PortletKeys;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author Ryan Park
 * @author Jonathan Lee
 */
public class TasksEntryLocalServiceImpl extends TasksEntryLocalServiceBaseImpl {

	public TasksEntry addTasksEntry(
			long userId, String title, int priority, long assigneeUserId,
			int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
			int dueDateMinute, boolean addDueDate,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Tasks entry

		User user = UserLocalServiceUtil.getUserById(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(title);

		Date dueDate = null;

		if (addDueDate) {
			dueDate = PortalUtil.getDate(
				dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
				dueDateMinute, user.getTimeZone(),
				TasksEntryDueDateException.class);
		}

		long tasksEntryId = CounterLocalServiceUtil.increment();

		TasksEntry tasksEntry = tasksEntryPersistence.create(tasksEntryId);

		tasksEntry.setGroupId(groupId);
		tasksEntry.setCompanyId(user.getCompanyId());
		tasksEntry.setUserId(user.getUserId());
		tasksEntry.setUserName(user.getFullName());
		tasksEntry.setCreateDate(now);
		tasksEntry.setModifiedDate(now);
		tasksEntry.setTitle(title);
		tasksEntry.setPriority(priority);
		tasksEntry.setAssigneeUserId(assigneeUserId);
		tasksEntry.setDueDate(dueDate);
		tasksEntry.setStatus(TasksEntryConstants.STATUS_OPEN);

		tasksEntryPersistence.update(tasksEntry);

		// Resources

		resourceLocalService.addModelResources(tasksEntry, serviceContext);

		// Asset

		updateAsset(
			userId, tasksEntry, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		// Social

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", tasksEntry.getTitle());

		SocialActivityLocalServiceUtil.addActivity(
			userId, groupId, TasksEntry.class.getName(), tasksEntryId,
			TasksActivityKeys.ADD_ENTRY, extraDataJSONObject.toString(),
			assigneeUserId);

		// Notifications

		sendNotificationEvent(
			tasksEntry, TasksEntryConstants.STATUS_ALL, assigneeUserId,
			serviceContext);

		return tasksEntry;
	}

	@Override
	public TasksEntry deleteTasksEntry(long tasksEntryId)
		throws PortalException, SystemException {

		TasksEntry tasksEntry = tasksEntryPersistence.findByPrimaryKey(
			tasksEntryId);

		return deleteTasksEntry(tasksEntry);
	}

	@Override
	public TasksEntry deleteTasksEntry(TasksEntry tasksEntry)
		throws PortalException, SystemException {

		// Tasks entry

		tasksEntryPersistence.remove(tasksEntry);

		// Asset

		AssetEntryLocalServiceUtil.deleteEntry(
			TasksEntry.class.getName(), tasksEntry.getTasksEntryId());

		// Message boards

		MBMessageLocalServiceUtil.deleteDiscussionMessages(
			TasksEntry.class.getName(), tasksEntry.getTasksEntryId());

		// Social

		SocialActivityLocalServiceUtil.deleteActivities(
			TasksEntry.class.getName(), tasksEntry.getTasksEntryId());

		return tasksEntry;
	}

	public List<TasksEntry> getAssigneeTasksEntries(
			long userId, int start, int end)
		throws SystemException {

		return tasksEntryPersistence.findByAssigneeUserId(userId, start, end);
	}

	public int getAssigneeTasksEntriesCount(long userId)
		throws SystemException {

		return tasksEntryPersistence.countByAssigneeUserId(userId);
	}

	public List<TasksEntry> getGroupAssigneeTasksEntries(
			long groupId, long userId, int start, int end)
		throws SystemException {

		return tasksEntryPersistence.findByG_A(groupId, userId, start, end);
	}

	public int getGroupAssigneeTasksEntriesCount(long groupId, long userId)
		throws SystemException {

		return tasksEntryPersistence.countByG_A(groupId, userId);
	}

	public List<TasksEntry> getGroupResolverTasksEntries(
			long groupId, long userId, int start, int end)
		throws SystemException {

		return tasksEntryPersistence.findByG_R(groupId, userId, start, end);
	}

	public int getGroupResolverTasksEntriesCount(long groupId, long userId)
		throws SystemException {

		return tasksEntryPersistence.countByG_R(groupId, userId);
	}

	public List<TasksEntry> getGroupUserTasksEntries(
			long groupId, long userId, int start, int end)
		throws SystemException {

		return tasksEntryPersistence.findByG_U(groupId, userId, start, end);
	}

	public int getGroupUserTasksEntriesCount(long groupId, long userId)
		throws SystemException {

		return tasksEntryPersistence.countByG_U(groupId, userId);
	}

	public List<TasksEntry> getResolverTasksEntries(
			long userId, int start, int end)
		throws SystemException {

		return tasksEntryPersistence.findByResolverUserId(userId, start, end);
	}

	public int getResolverTasksEntriesCount(long userId)
		throws SystemException {

		return tasksEntryPersistence.countByResolverUserId(userId);
	}

	public List<TasksEntry> getTasksEntries(long groupId, int start, int end)
		throws SystemException {

		return tasksEntryPersistence.findByGroupId(groupId, start, end);
	}

	public List<TasksEntry> getTasksEntries(
			long groupId, int priority, long assigneeUserId,
			long reporterUserId, int status, long[] assetTagIds,
			long[] notAssetTagIds, int start, int end)
		throws SystemException {

		return tasksEntryFinder.findByG_P_A_R_S_T_N(
			groupId, priority, assigneeUserId, reporterUserId, status,
			assetTagIds, notAssetTagIds, start, end);
	}

	public int getTasksEntriesCount(long groupId) throws SystemException {
		return tasksEntryPersistence.countByGroupId(groupId);
	}

	public int getTasksEntriesCount(
			long groupId, int priority, long assigneeUserId,
			long reporterUserId, int status, long[] tagsEntryIds,
			long[] notTagsEntryIds)
		throws SystemException {

		return tasksEntryFinder.countByG_P_A_R_S_T_N(
			groupId, priority, assigneeUserId, reporterUserId, status,
			tagsEntryIds, notTagsEntryIds);
	}

	@Override
	public TasksEntry getTasksEntry(long tasksEntryId)
		throws PortalException, SystemException {

		return tasksEntryPersistence.findByPrimaryKey(tasksEntryId);
	}

	public List<TasksEntry> getUserTasksEntries(long userId, int start, int end)
		throws SystemException {

		return tasksEntryPersistence.findByUserId(userId, start, end);
	}

	public int getUserTasksEntriesCount(long userId) throws SystemException {
		return tasksEntryPersistence.countByUserId(userId);
	}

	public void updateAsset(
			long userId, TasksEntry tasksEntry, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException, SystemException {

		AssetEntryLocalServiceUtil.updateEntry(
			userId, tasksEntry.getGroupId(), TasksEntry.class.getName(),
			tasksEntry.getTasksEntryId(), assetCategoryIds, assetTagNames);
	}

	public TasksEntry updateTasksEntry(
			long tasksEntryId, String title, int priority, long assigneeUserId,
			long resolverUserId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean addDueDate, int status, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Tasks entry

		Date now = new Date();

		TasksEntry tasksEntry = tasksEntryPersistence.findByPrimaryKey(
			tasksEntryId);

		User user = UserLocalServiceUtil.getUserById(tasksEntry.getUserId());

		validate(title);

		Date dueDate = null;

		if (addDueDate) {
			dueDate = PortalUtil.getDate(
				dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
				dueDateMinute, user.getTimeZone(),
				TasksEntryDueDateException.class);
		}

		long oldAssigneeUserId = tasksEntry.getAssigneeUserId();
		int oldStatus = tasksEntry.getStatus();

		tasksEntry.setModifiedDate(now);
		tasksEntry.setTitle(title);
		tasksEntry.setPriority(priority);
		tasksEntry.setAssigneeUserId(assigneeUserId);
		tasksEntry.setDueDate(dueDate);

		if (status == TasksEntryConstants.STATUS_RESOLVED) {
			tasksEntry.setResolverUserId(resolverUserId);
			tasksEntry.setFinishDate(now);
		}
		else {
			tasksEntry.setResolverUserId(0);
			tasksEntry.setFinishDate(null);
		}

		tasksEntry.setStatus(status);

		tasksEntryPersistence.update(tasksEntry);

		// Asset

		updateAsset(
			tasksEntry.getUserId(), tasksEntry,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		// Social

		addSocialActivity(status, tasksEntry, serviceContext);

		// Notifications

		sendNotificationEvent(
			tasksEntry, oldStatus, oldAssigneeUserId, serviceContext);

		return tasksEntry;
	}

	public TasksEntry updateTasksEntryStatus(
			long tasksEntryId, long resolverUserId, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Tasks entry

		Date now = new Date();

		TasksEntry tasksEntry = tasksEntryPersistence.findByPrimaryKey(
			tasksEntryId);

		tasksEntry.setModifiedDate(now);

		if (status == TasksEntryConstants.STATUS_RESOLVED) {
			tasksEntry.setResolverUserId(resolverUserId);
			tasksEntry.setFinishDate(now);
		}
		else {
			tasksEntry.setResolverUserId(0);
			tasksEntry.setFinishDate(null);
		}

		int oldStatus = tasksEntry.getStatus();

		tasksEntry.setStatus(status);

		tasksEntryPersistence.update(tasksEntry);

		// Social

		addSocialActivity(status, tasksEntry, serviceContext);

		// Notifications

		sendNotificationEvent(
			tasksEntry, oldStatus, tasksEntry.getAssigneeUserId(),
			serviceContext);

		return tasksEntry;
	}

	protected void addSocialActivity(
			int status, TasksEntry tasksEntry, ServiceContext serviceContext)
		throws PortalException, SystemException {

		int activity = TasksActivityKeys.UPDATE_ENTRY;

		if (status == TasksEntryConstants.STATUS_REOPENED) {
			activity = TasksActivityKeys.REOPEN_ENTRY;
		}
		else if (status == TasksEntryConstants.STATUS_RESOLVED) {
			activity = TasksActivityKeys.RESOLVE_ENTRY;
		}

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", tasksEntry.getTitle());

		SocialActivityLocalServiceUtil.addActivity(
			serviceContext.getUserId(), tasksEntry.getGroupId(),
			TasksEntry.class.getName(), tasksEntry.getTasksEntryId(), activity,
			extraDataJSONObject.toString(), tasksEntry.getAssigneeUserId());
	}

	protected void sendNotificationEvent(
			TasksEntry tasksEntry, int oldStatus, long oldAssigneeUserId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		HashSet<Long> receiverUserIds = new HashSet<Long>(3);

		receiverUserIds.add(oldAssigneeUserId);
		receiverUserIds.add(tasksEntry.getUserId());
		receiverUserIds.add(tasksEntry.getAssigneeUserId());

		receiverUserIds.remove(serviceContext.getUserId());

		JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		notificationEventJSONObject.put(
			"classPK", tasksEntry.getTasksEntryId());
		notificationEventJSONObject.put("userId", serviceContext.getUserId());

		for (long receiverUserId : receiverUserIds) {
			if ((receiverUserId == 0) ||
				!UserNotificationManagerUtil.isDeliver(
					receiverUserId, PortletKeys.TASKS, 0,
					TasksEntryConstants.STATUS_ALL,
					UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

				continue;
			}

			String title = StringPool.BLANK;

			if (oldStatus == TasksEntryConstants.STATUS_ALL) {
				title = "x-assigned-you-a-task";
			}
			else if (tasksEntry.getAssigneeUserId() != oldAssigneeUserId) {
				if (receiverUserId == oldAssigneeUserId) {
					title = "x-reassigned-your-task";
				}
				else {
					title = "x-assigned-you-a-task";
				}
			}
			else if (tasksEntry.getStatus() != oldStatus) {
				if ((tasksEntry.getStatus() !=
						TasksEntryConstants.STATUS_OPEN) &&
					(tasksEntry.getStatus() !=
						TasksEntryConstants.STATUS_REOPENED) &&
					(tasksEntry.getStatus() !=
						TasksEntryConstants.STATUS_RESOLVED)) {

					return;
				}

				String statusLabel = TasksEntryConstants.getStatusLabel(
					tasksEntry.getStatus());

				title = "x-" + statusLabel + "-the-task";
			}
			else {
				title = "x-modified-the-task";
			}

			notificationEventJSONObject.put("title", title);

			NotificationEvent notificationEvent =
				NotificationEventFactoryUtil.createNotificationEvent(
					System.currentTimeMillis(), PortletKeys.TASKS,
					notificationEventJSONObject);

			notificationEvent.setDeliveryRequired(0);

			UserNotificationEventLocalServiceUtil.addUserNotificationEvent(
				receiverUserId, notificationEvent);
		}
	}

	protected void validate(String title) throws PortalException {
		if (Validator.isNull(title)) {
			throw new TasksEntryTitleException();
		}
	}

}