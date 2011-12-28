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

package com.liferay.tasks.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.tasks.model.TasksEntry;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TasksEntry in entity cache.
 *
 * @author Ryan Park
 * @see TasksEntry
 * @generated
 */
public class TasksEntryCacheModel implements CacheModel<TasksEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{tasksEntryId=");
		sb.append(tasksEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", title=");
		sb.append(title);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", assigneeUserId=");
		sb.append(assigneeUserId);
		sb.append(", resolverUserId=");
		sb.append(resolverUserId);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append(", finishDate=");
		sb.append(finishDate);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	public TasksEntry toEntityModel() {
		TasksEntryImpl tasksEntryImpl = new TasksEntryImpl();

		tasksEntryImpl.setTasksEntryId(tasksEntryId);
		tasksEntryImpl.setGroupId(groupId);
		tasksEntryImpl.setCompanyId(companyId);
		tasksEntryImpl.setUserId(userId);

		if (userName == null) {
			tasksEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			tasksEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			tasksEntryImpl.setCreateDate(null);
		}
		else {
			tasksEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			tasksEntryImpl.setModifiedDate(null);
		}
		else {
			tasksEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			tasksEntryImpl.setTitle(StringPool.BLANK);
		}
		else {
			tasksEntryImpl.setTitle(title);
		}

		tasksEntryImpl.setPriority(priority);
		tasksEntryImpl.setAssigneeUserId(assigneeUserId);
		tasksEntryImpl.setResolverUserId(resolverUserId);

		if (dueDate == Long.MIN_VALUE) {
			tasksEntryImpl.setDueDate(null);
		}
		else {
			tasksEntryImpl.setDueDate(new Date(dueDate));
		}

		if (finishDate == Long.MIN_VALUE) {
			tasksEntryImpl.setFinishDate(null);
		}
		else {
			tasksEntryImpl.setFinishDate(new Date(finishDate));
		}

		tasksEntryImpl.setStatus(status);

		tasksEntryImpl.resetOriginalValues();

		return tasksEntryImpl;
	}

	public long tasksEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public int priority;
	public long assigneeUserId;
	public long resolverUserId;
	public long dueDate;
	public long finishDate;
	public int status;
}