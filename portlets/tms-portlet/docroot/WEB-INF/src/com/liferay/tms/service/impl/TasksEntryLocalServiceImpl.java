/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.tms.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.tags.service.TagsAssetLocalServiceUtil;
import com.liferay.tms.model.TasksEntry;
import com.liferay.tms.service.base.TasksEntryLocalServiceBaseImpl;
import com.liferay.tms.tasks.social.TasksActivityKeys;
import com.liferay.tms.tasks.util.TasksConstants;

import java.util.Date;
import java.util.List;

/**
 * <a href="TasksEntryLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ryan Park
 *
 */
public class TasksEntryLocalServiceImpl extends TasksEntryLocalServiceBaseImpl {

	public TasksEntry addTasksEntry(
			long userId, String title, int priority, long assigneeUserId,
			Date dueDate, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Entry

		User user = UserLocalServiceUtil.getUserById(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

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
		tasksEntry.setStatus(TasksConstants.STATUS_OPEN);

		tasksEntryPersistence.update(tasksEntry);

		// Social

		SocialActivityLocalServiceUtil.addActivity(
			userId, groupId, TasksEntry.class.getName(), tasksEntryId,
			TasksActivityKeys.ADD_TASK, StringPool.BLANK, 0);

		// Tags

		updateTagsAsset(
			userId, tasksEntry, serviceContext.getTagsCategories(),
			serviceContext.getTagsEntries());

		return tasksEntry;
	}

	public void deleteTasksEntry(long tasksEntryId)
		throws PortalException, SystemException {

		TasksEntry tasksEntry = tasksEntryPersistence.findByPrimaryKey(
			tasksEntryId);

		deleteTasksEntry(tasksEntry);
	}

	public void deleteTasksEntry(TasksEntry tasksEntry)
		throws PortalException, SystemException {

		// Tags

		TagsAssetLocalServiceUtil.deleteAsset(
			TasksEntry.class.getName(), tasksEntry.getTasksEntryId());

		// Social

		SocialActivityLocalServiceUtil.deleteActivities(
			TasksEntry.class.getName(), tasksEntry.getTasksEntryId());

		// Message boards

		MBMessageLocalServiceUtil.deleteDiscussionMessages(
			TasksEntry.class.getName(), tasksEntry.getTasksEntryId());

		// Entry

		tasksEntryPersistence.remove(tasksEntry);
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
			long reporterUserId, int status, long[] tagsEntryIds,
			long[] notTagsEntryIds, int start, int end)
		throws SystemException {

		return tasksEntryFinder.findByG_P_A_R_S_T_N(
			groupId, priority, assigneeUserId, reporterUserId, status,
			tagsEntryIds, notTagsEntryIds, start, end);
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

	public void updateTagsAsset(
			long userId, TasksEntry tasksEntry, String[] tagsCategories,
			String[] tagsEntries)
		throws PortalException, SystemException {

		TagsAssetLocalServiceUtil.updateAsset(
			userId, tasksEntry.getGroupId(), TasksEntry.class.getName(),
			tasksEntry.getTasksEntryId(), tagsCategories, tagsEntries, true,
			null, null, null, null, null, tasksEntry.getTitle(),
			StringPool.BLANK, null, null, 0, 0, null, false);
	}

	public TasksEntry updateTasksEntry(
			long tasksEntryId, String title, int priority, long assigneeUserId,
			long resolverUserId, Date dueDate, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Entry

		Date now = new Date();

		TasksEntry tasksEntry = tasksEntryPersistence.findByPrimaryKey(
			tasksEntryId);

		tasksEntry.setModifiedDate(now);
		tasksEntry.setTitle(title);
		tasksEntry.setPriority(priority);
		tasksEntry.setAssigneeUserId(assigneeUserId);
		tasksEntry.setDueDate(dueDate);

		if (status == TasksConstants.STATUS_RESOLVED) {
			tasksEntry.setResolverUserId(resolverUserId);
			tasksEntry.setFinishDate(now);
		}
		else {
			tasksEntry.setResolverUserId(0);
			tasksEntry.setFinishDate(null);
		}

		tasksEntry.setStatus(status);

		tasksEntryPersistence.update(tasksEntry);

		// Social

		int activity = TasksActivityKeys.UPDATE_TASK;

		if (status == TasksConstants.STATUS_RESOLVED) {
			activity = TasksActivityKeys.RESOLVE_TASK;
		}
		else if (status == TasksConstants.STATUS_REOPENED) {
			activity = TasksActivityKeys.REOPEN_TASK;
		}

		SocialActivityLocalServiceUtil.addActivity(
			tasksEntry.getUserId(), tasksEntry.getGroupId(),
			TasksEntry.class.getName(), tasksEntryId, activity,
			StringPool.BLANK, 0);

		// Tags

		updateTagsAsset(
			tasksEntry.getUserId(), tasksEntry,
			serviceContext.getTagsCategories(),
			serviceContext.getTagsEntries());

		return tasksEntry;
	}

}