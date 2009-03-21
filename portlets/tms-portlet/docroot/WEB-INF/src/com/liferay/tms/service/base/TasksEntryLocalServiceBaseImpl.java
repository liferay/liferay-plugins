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

package com.liferay.tms.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.tms.model.TasksEntry;
import com.liferay.tms.service.TasksEntryLocalService;
import com.liferay.tms.service.persistence.TasksEntryFinder;
import com.liferay.tms.service.persistence.TasksEntryPersistence;

import java.util.List;

/**
 * <a href="TasksEntryLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class TasksEntryLocalServiceBaseImpl
	implements TasksEntryLocalService {
	public TasksEntry addTasksEntry(TasksEntry tasksEntry)
		throws SystemException {
		tasksEntry.setNew(true);

		return tasksEntryPersistence.update(tasksEntry, false);
	}

	public TasksEntry createTasksEntry(long tasksEntryId) {
		return tasksEntryPersistence.create(tasksEntryId);
	}

	public void deleteTasksEntry(long tasksEntryId)
		throws PortalException, SystemException {
		tasksEntryPersistence.remove(tasksEntryId);
	}

	public void deleteTasksEntry(TasksEntry tasksEntry)
		throws PortalException, SystemException {
		tasksEntryPersistence.remove(tasksEntry);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return tasksEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return tasksEntryPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	public TasksEntry getTasksEntry(long tasksEntryId)
		throws PortalException, SystemException {
		return tasksEntryPersistence.findByPrimaryKey(tasksEntryId);
	}

	public List<TasksEntry> getTasksEntries(int start, int end)
		throws SystemException {
		return tasksEntryPersistence.findAll(start, end);
	}

	public int getTasksEntriesCount() throws SystemException {
		return tasksEntryPersistence.countAll();
	}

	public TasksEntry updateTasksEntry(TasksEntry tasksEntry)
		throws SystemException {
		tasksEntry.setNew(false);

		return tasksEntryPersistence.update(tasksEntry, true);
	}

	public TasksEntry updateTasksEntry(TasksEntry tasksEntry, boolean merge)
		throws SystemException {
		tasksEntry.setNew(false);

		return tasksEntryPersistence.update(tasksEntry, merge);
	}

	public TasksEntryLocalService getTasksEntryLocalService() {
		return tasksEntryLocalService;
	}

	public void setTasksEntryLocalService(
		TasksEntryLocalService tasksEntryLocalService) {
		this.tasksEntryLocalService = tasksEntryLocalService;
	}

	public TasksEntryPersistence getTasksEntryPersistence() {
		return tasksEntryPersistence;
	}

	public void setTasksEntryPersistence(
		TasksEntryPersistence tasksEntryPersistence) {
		this.tasksEntryPersistence = tasksEntryPersistence;
	}

	public TasksEntryFinder getTasksEntryFinder() {
		return tasksEntryFinder;
	}

	public void setTasksEntryFinder(TasksEntryFinder tasksEntryFinder) {
		this.tasksEntryFinder = tasksEntryFinder;
	}

	@BeanReference(name = "com.liferay.tms.service.TasksEntryLocalService.impl")
	protected TasksEntryLocalService tasksEntryLocalService;
	@BeanReference(name = "com.liferay.tms.service.persistence.TasksEntryPersistence.impl")
	protected TasksEntryPersistence tasksEntryPersistence;
	@BeanReference(name = "com.liferay.tms.service.persistence.TasksEntryFinder.impl")
	protected TasksEntryFinder tasksEntryFinder;
}