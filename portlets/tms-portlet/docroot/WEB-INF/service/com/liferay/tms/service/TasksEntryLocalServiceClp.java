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

package com.liferay.tms.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * @author Ryan Park
 */
public class TasksEntryLocalServiceClp implements TasksEntryLocalService {
	public TasksEntryLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addTasksEntryMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addTasksEntry", com.liferay.tms.model.TasksEntry.class);

		_createTasksEntryMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"createTasksEntry", long.class);

		_deleteTasksEntryMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteTasksEntry", long.class);

		_deleteTasksEntryMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteTasksEntry", com.liferay.tms.model.TasksEntry.class);

		_dynamicQueryMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

		_dynamicQueryMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
				int.class, int.class);

		_dynamicQueryMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_dynamicQueryCountMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQueryCount",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

		_getTasksEntryMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"getTasksEntry", long.class);

		_getTasksEntriesMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
				"getTasksEntries", int.class, int.class);

		_getTasksEntriesCountMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
				"getTasksEntriesCount");

		_updateTasksEntryMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateTasksEntry", com.liferay.tms.model.TasksEntry.class);

		_updateTasksEntryMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateTasksEntry", com.liferay.tms.model.TasksEntry.class,
				boolean.class);

		_getBeanIdentifierMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
				"getBeanIdentifier");

		_setBeanIdentifierMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
				"setBeanIdentifier", java.lang.String.class);

		_addTasksEntryMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
				"addTasksEntry", long.class, java.lang.String.class, int.class,
				long.class, int.class, int.class, int.class, int.class,
				int.class, boolean.class,
				com.liferay.portal.service.ServiceContext.class);

		_getAssigneeTasksEntriesMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
				"getAssigneeTasksEntries", long.class, int.class, int.class);

		_getAssigneeTasksEntriesCountMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
				"getAssigneeTasksEntriesCount", long.class);

		_getGroupAssigneeTasksEntriesMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupAssigneeTasksEntries", long.class, long.class,
				int.class, int.class);

		_getGroupAssigneeTasksEntriesCountMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupAssigneeTasksEntriesCount", long.class, long.class);

		_getGroupResolverTasksEntriesMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupResolverTasksEntries", long.class, long.class,
				int.class, int.class);

		_getGroupResolverTasksEntriesCountMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupResolverTasksEntriesCount", long.class, long.class);

		_getGroupUserTasksEntriesMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupUserTasksEntries", long.class, long.class, int.class,
				int.class);

		_getGroupUserTasksEntriesCountMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupUserTasksEntriesCount", long.class, long.class);

		_getResolverTasksEntriesMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
				"getResolverTasksEntries", long.class, int.class, int.class);

		_getResolverTasksEntriesCountMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
				"getResolverTasksEntriesCount", long.class);

		_getTasksEntriesMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
				"getTasksEntries", long.class, int.class, int.class);

		_getTasksEntriesMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
				"getTasksEntries", long.class, int.class, long.class,
				long.class, int.class, long[].class, long[].class, int.class,
				int.class);

		_getTasksEntriesCountMethodKey28 = new MethodKey(_classLoaderProxy.getClassName(),
				"getTasksEntriesCount", long.class);

		_getTasksEntriesCountMethodKey29 = new MethodKey(_classLoaderProxy.getClassName(),
				"getTasksEntriesCount", long.class, int.class, long.class,
				long.class, int.class, long[].class, long[].class);

		_getUserTasksEntriesMethodKey30 = new MethodKey(_classLoaderProxy.getClassName(),
				"getUserTasksEntries", long.class, int.class, int.class);

		_getUserTasksEntriesCountMethodKey31 = new MethodKey(_classLoaderProxy.getClassName(),
				"getUserTasksEntriesCount", long.class);

		_updateAssetMethodKey32 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateAsset", long.class,
				com.liferay.tms.model.TasksEntry.class, long[].class,
				java.lang.String[].class);

		_updateTasksEntryMethodKey33 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateTasksEntry", long.class, java.lang.String.class,
				int.class, long.class, long.class, int.class, int.class,
				int.class, int.class, int.class, boolean.class, int.class,
				com.liferay.portal.service.ServiceContext.class);

		_updateTasksEntryStatusMethodKey34 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateTasksEntryStatus", long.class, long.class, int.class,
				com.liferay.portal.service.ServiceContext.class);
	}

	public com.liferay.tms.model.TasksEntry addTasksEntry(
		com.liferay.tms.model.TasksEntry tasksEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addTasksEntryMethodKey0,
				ClpSerializer.translateInput(tasksEntry));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.tms.model.TasksEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.tms.model.TasksEntry createTasksEntry(long tasksEntryId) {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_createTasksEntryMethodKey1,
				tasksEntryId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.tms.model.TasksEntry)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteTasksEntry(long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteTasksEntryMethodKey2,
				tasksEntryId);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteTasksEntry(com.liferay.tms.model.TasksEntry tasksEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteTasksEntryMethodKey3,
				ClpSerializer.translateInput(tasksEntry));

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey4,
				ClpSerializer.translateInput(dynamicQuery));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey5,
				ClpSerializer.translateInput(dynamicQuery), start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey6,
				ClpSerializer.translateInput(dynamicQuery), start, end,
				ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryCountMethodKey7,
				ClpSerializer.translateInput(dynamicQuery));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	public com.liferay.tms.model.TasksEntry getTasksEntry(long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getTasksEntryMethodKey8,
				tasksEntryId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.tms.model.TasksEntry)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.tms.model.TasksEntry> getTasksEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getTasksEntriesMethodKey9,
				start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.tms.model.TasksEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getTasksEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getTasksEntriesCountMethodKey10);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.tms.model.TasksEntry updateTasksEntry(
		com.liferay.tms.model.TasksEntry tasksEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateTasksEntryMethodKey11,
				ClpSerializer.translateInput(tasksEntry));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.tms.model.TasksEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.tms.model.TasksEntry updateTasksEntry(
		com.liferay.tms.model.TasksEntry tasksEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateTasksEntryMethodKey12,
				ClpSerializer.translateInput(tasksEntry), merge);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.tms.model.TasksEntry)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getBeanIdentifier() {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getBeanIdentifierMethodKey13);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		MethodHandler methodHandler = new MethodHandler(_setBeanIdentifierMethodKey14,
				ClpSerializer.translateInput(beanIdentifier));

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public com.liferay.tms.model.TasksEntry addTasksEntry(long userId,
		java.lang.String title, int priority, long assigneeUserId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute, boolean neverDue,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addTasksEntryMethodKey15,
				userId, ClpSerializer.translateInput(title), priority,
				assigneeUserId, dueDateMonth, dueDateDay, dueDateYear,
				dueDateHour, dueDateMinute, neverDue,
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.tms.model.TasksEntry)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.tms.model.TasksEntry> getAssigneeTasksEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getAssigneeTasksEntriesMethodKey16,
				userId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.tms.model.TasksEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getAssigneeTasksEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getAssigneeTasksEntriesCountMethodKey17,
				userId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.tms.model.TasksEntry> getGroupAssigneeTasksEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupAssigneeTasksEntriesMethodKey18,
				groupId, userId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.tms.model.TasksEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getGroupAssigneeTasksEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupAssigneeTasksEntriesCountMethodKey19,
				groupId, userId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.tms.model.TasksEntry> getGroupResolverTasksEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupResolverTasksEntriesMethodKey20,
				groupId, userId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.tms.model.TasksEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getGroupResolverTasksEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupResolverTasksEntriesCountMethodKey21,
				groupId, userId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.tms.model.TasksEntry> getGroupUserTasksEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupUserTasksEntriesMethodKey22,
				groupId, userId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.tms.model.TasksEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getGroupUserTasksEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupUserTasksEntriesCountMethodKey23,
				groupId, userId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.tms.model.TasksEntry> getResolverTasksEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getResolverTasksEntriesMethodKey24,
				userId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.tms.model.TasksEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getResolverTasksEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getResolverTasksEntriesCountMethodKey25,
				userId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.tms.model.TasksEntry> getTasksEntries(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getTasksEntriesMethodKey26,
				groupId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.tms.model.TasksEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.tms.model.TasksEntry> getTasksEntries(
		long groupId, int priority, long assigneeUserId, long reporterUserId,
		int status, long[] tagsEntryIds, long[] notTagsEntryIds, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getTasksEntriesMethodKey27,
				groupId, priority, assigneeUserId, reporterUserId, status,
				ClpSerializer.translateInput(tagsEntryIds),
				ClpSerializer.translateInput(notTagsEntryIds), start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.tms.model.TasksEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getTasksEntriesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getTasksEntriesCountMethodKey28,
				groupId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int getTasksEntriesCount(long groupId, int priority,
		long assigneeUserId, long reporterUserId, int status,
		long[] tagsEntryIds, long[] notTagsEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getTasksEntriesCountMethodKey29,
				groupId, priority, assigneeUserId, reporterUserId, status,
				ClpSerializer.translateInput(tagsEntryIds),
				ClpSerializer.translateInput(notTagsEntryIds));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.tms.model.TasksEntry> getUserTasksEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getUserTasksEntriesMethodKey30,
				userId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.tms.model.TasksEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getUserTasksEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getUserTasksEntriesCountMethodKey31,
				userId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public void updateAsset(long userId,
		com.liferay.tms.model.TasksEntry tasksEntry, long[] assetCategoryIds,
		java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_updateAssetMethodKey32,
				userId, ClpSerializer.translateInput(tasksEntry),
				ClpSerializer.translateInput(assetCategoryIds),
				ClpSerializer.translateInput(assetTagNames));

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public com.liferay.tms.model.TasksEntry updateTasksEntry(
		long tasksEntryId, java.lang.String title, int priority,
		long assigneeUserId, long resolverUserId, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean neverDue, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateTasksEntryMethodKey33,
				tasksEntryId, ClpSerializer.translateInput(title), priority,
				assigneeUserId, resolverUserId, dueDateMonth, dueDateDay,
				dueDateYear, dueDateHour, dueDateMinute, neverDue, status,
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.tms.model.TasksEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.tms.model.TasksEntry updateTasksEntryStatus(
		long tasksEntryId, long resolverUserId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateTasksEntryStatusMethodKey34,
				tasksEntryId, resolverUserId, status,
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.tms.model.TasksEntry)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
	private MethodKey _addTasksEntryMethodKey0;
	private MethodKey _createTasksEntryMethodKey1;
	private MethodKey _deleteTasksEntryMethodKey2;
	private MethodKey _deleteTasksEntryMethodKey3;
	private MethodKey _dynamicQueryMethodKey4;
	private MethodKey _dynamicQueryMethodKey5;
	private MethodKey _dynamicQueryMethodKey6;
	private MethodKey _dynamicQueryCountMethodKey7;
	private MethodKey _getTasksEntryMethodKey8;
	private MethodKey _getTasksEntriesMethodKey9;
	private MethodKey _getTasksEntriesCountMethodKey10;
	private MethodKey _updateTasksEntryMethodKey11;
	private MethodKey _updateTasksEntryMethodKey12;
	private MethodKey _getBeanIdentifierMethodKey13;
	private MethodKey _setBeanIdentifierMethodKey14;
	private MethodKey _addTasksEntryMethodKey15;
	private MethodKey _getAssigneeTasksEntriesMethodKey16;
	private MethodKey _getAssigneeTasksEntriesCountMethodKey17;
	private MethodKey _getGroupAssigneeTasksEntriesMethodKey18;
	private MethodKey _getGroupAssigneeTasksEntriesCountMethodKey19;
	private MethodKey _getGroupResolverTasksEntriesMethodKey20;
	private MethodKey _getGroupResolverTasksEntriesCountMethodKey21;
	private MethodKey _getGroupUserTasksEntriesMethodKey22;
	private MethodKey _getGroupUserTasksEntriesCountMethodKey23;
	private MethodKey _getResolverTasksEntriesMethodKey24;
	private MethodKey _getResolverTasksEntriesCountMethodKey25;
	private MethodKey _getTasksEntriesMethodKey26;
	private MethodKey _getTasksEntriesMethodKey27;
	private MethodKey _getTasksEntriesCountMethodKey28;
	private MethodKey _getTasksEntriesCountMethodKey29;
	private MethodKey _getUserTasksEntriesMethodKey30;
	private MethodKey _getUserTasksEntriesCountMethodKey31;
	private MethodKey _updateAssetMethodKey32;
	private MethodKey _updateTasksEntryMethodKey33;
	private MethodKey _updateTasksEntryStatusMethodKey34;
}