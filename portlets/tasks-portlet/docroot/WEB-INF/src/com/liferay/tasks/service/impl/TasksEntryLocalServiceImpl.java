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

package com.liferay.tasks.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.tasks.TasksEntryDueDateException;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.model.TasksEntryConstants;
import com.liferay.tasks.service.base.TasksEntryLocalServiceBaseImpl;
import com.liferay.tasks.social.TasksActivityKeys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ryan Park
 */
public class TasksEntryLocalServiceImpl extends TasksEntryLocalServiceBaseImpl {

	public TasksEntry addTasksEntry(
			long userId, String title, int priority, long assigneeUserId,
			int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
			int dueDateMinute, boolean neverDue, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Tasks entry

		User user = UserLocalServiceUtil.getUserById(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		Date dueDate = null;

		if (!neverDue) {
			dueDate = PortalUtil.getDate(
				dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
				dueDateMinute, user.getTimeZone(),
				new TasksEntryDueDateException());
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

		tasksEntryPersistence.update(tasksEntry, false);

		// Resources

		resourceLocalService.addModelResources(tasksEntry, serviceContext);

		// Asset

		updateAsset(
			userId, tasksEntry, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		// Social

		SocialActivityLocalServiceUtil.addActivity(
			userId, groupId, TasksEntry.class.getName(), tasksEntryId,
			TasksActivityKeys.ADD_ENTRY, StringPool.BLANK, assigneeUserId);

		// Notification

		sendNotificationEvent(
			tasksEntry, TasksEntryConstants.STATUS_ALL, 0, serviceContext);

		return tasksEntry;
	}

	@Override
	public void deleteTasksEntry(long tasksEntryId)
		throws PortalException, SystemException {

		TasksEntry tasksEntry = tasksEntryPersistence.findByPrimaryKey(
			tasksEntryId);

		deleteTasksEntry(tasksEntry);
	}

	@Override
	public void deleteTasksEntry(TasksEntry tasksEntry)
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
			boolean neverDue, int status, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Tasks entry

		Date now = new Date();

		TasksEntry tasksEntry = tasksEntryPersistence.findByPrimaryKey(
			tasksEntryId);

		User user = UserLocalServiceUtil.getUserById(tasksEntry.getUserId());

		Date dueDate = null;

		if (!neverDue) {
			dueDate = PortalUtil.getDate(
				dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
				dueDateMinute, user.getTimeZone(),
				new TasksEntryDueDateException());
		}

		long previousAssigneeUserId = tasksEntry.getAssigneeUserId();

		tasksEntry.setModifiedDate(now);
		tasksEntry.setTitle(title);
		tasksEntry.setPriority(priority);
		tasksEntry.setAssigneeUserId(assigneeUserId);
		tasksEntry.setDueDate(dueDate);

		int previousStatus = tasksEntry.getStatus();

		if (status == TasksEntryConstants.STATUS_RESOLVED) {
			tasksEntry.setResolverUserId(resolverUserId);
			tasksEntry.setFinishDate(now);
		}
		else {
			tasksEntry.setResolverUserId(0);
			tasksEntry.setFinishDate(null);
		}

		tasksEntry.setStatus(status);

		tasksEntryPersistence.update(tasksEntry, false);

		// Asset

		updateAsset(
			tasksEntry.getUserId(), tasksEntry,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		// Social

		int activity = TasksActivityKeys.UPDATE_ENTRY;

		if (status == TasksEntryConstants.STATUS_RESOLVED) {
			activity = TasksActivityKeys.RESOLVE_ENTRY;
		}
		else if (status == TasksEntryConstants.STATUS_REOPENED) {
			activity = TasksActivityKeys.REOPEN_ENTRY;
		}

		SocialActivityLocalServiceUtil.addActivity(
			serviceContext.getUserId(), tasksEntry.getGroupId(),
			TasksEntry.class.getName(), tasksEntryId, activity,
			StringPool.BLANK, assigneeUserId);

		// Notification

		sendNotificationEvent(
			tasksEntry, previousStatus, previousAssigneeUserId, serviceContext);

		return tasksEntry;
	}

	public TasksEntry updateTasksEntryStatus(
			long tasksEntryId, long resolverUserId, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

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

		int previousStatus = tasksEntry.getStatus();

		tasksEntry.setStatus(status);

		tasksEntryPersistence.update(tasksEntry, false);

		// Notification

		sendNotificationEvent(
			tasksEntry, previousStatus, tasksEntry.getAssigneeUserId(),
			serviceContext);

		return tasksEntry;
	}

	protected void sendNotificationEvent(
			TasksEntry tasksEntry, int previousStatus,
			long previousAssigneeUserId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		int status = tasksEntry.getStatus();

		if ((status != TasksEntryConstants.STATUS_OPEN) &&
			(status != TasksEntryConstants.STATUS_RESOLVED) &&
			(status != TasksEntryConstants.STATUS_REOPENED)) {

			return;
		}

		long senderUserId = serviceContext.getUserId();
		long assigneeUserId = tasksEntry.getAssigneeUserId();
		long creatorUserId = tasksEntry.getUserId();

		List<Long> receiverUserIds = new ArrayList<Long>(3);

		if (creatorUserId != senderUserId ) {
			receiverUserIds.add(creatorUserId);
		}

		if (assigneeUserId != senderUserId &&
			!receiverUserIds.contains(assigneeUserId)) {

				receiverUserIds.add(assigneeUserId);
		}

		if (previousAssigneeUserId != 0 &&
			previousAssigneeUserId != senderUserId &&
			!receiverUserIds.contains(assigneeUserId)) {

			receiverUserIds.add(previousAssigneeUserId);
		}

		JSONObject notificationEventJSON = JSONFactoryUtil.createJSONObject();

		notificationEventJSON.put("portletId", "1_WAR_tasksportlet");
		notificationEventJSON.put("senderUserId", senderUserId);
		notificationEventJSON.put("tasksEntryId", tasksEntry.getTasksEntryId());
		notificationEventJSON.put("body", tasksEntry.getTitle());

		for (long receiverUserId : receiverUserIds) {
			User receiverUser = UserLocalServiceUtil.getUserById(
				receiverUserId);

			String title = StringPool.BLANK;

			if (assigneeUserId != previousAssigneeUserId) {
				if (receiverUserId == previousAssigneeUserId) {
					title = "reassigned-your-task";
				}
				else {
					title = "assigned-you-a-task";
				}
			}
			else if (status != previousStatus) {
				String statusLabel = TasksEntryConstants.getStatusLabel(
					tasksEntry.getStatus());

				title = statusLabel + "-the-task";
			}
			else {
				title = "modified-the-task";
			}

			notificationEventJSON.put("title", title);

			NotificationEvent notificationEvent =
				NotificationEventFactoryUtil.createNotificationEvent(
					System.currentTimeMillis(), "6_WAR_soportlet",
					notificationEventJSON);

			notificationEvent.setDeliveryRequired(0);

			ChannelHubManagerUtil.sendNotificationEvent(
				receiverUser.getCompanyId(), receiverUserId, notificationEvent);
		}
	}

}