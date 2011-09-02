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

package com.liferay.tasks.service;

/**
 * <p>
 * This class is a wrapper for {@link TasksEntryLocalService}.
 * </p>
 *
 * @author    Ryan Park
 * @see       TasksEntryLocalService
 * @generated
 */
public class TasksEntryLocalServiceWrapper implements TasksEntryLocalService {
	public TasksEntryLocalServiceWrapper(
		TasksEntryLocalService tasksEntryLocalService) {
		_tasksEntryLocalService = tasksEntryLocalService;
	}

	/**
	* Adds the tasks entry to the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntry the tasks entry
	* @return the tasks entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry addTasksEntry(
		com.liferay.tasks.model.TasksEntry tasksEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.addTasksEntry(tasksEntry);
	}

	/**
	* Creates a new tasks entry with the primary key. Does not add the tasks entry to the database.
	*
	* @param tasksEntryId the primary key for the new tasks entry
	* @return the new tasks entry
	*/
	public com.liferay.tasks.model.TasksEntry createTasksEntry(
		long tasksEntryId) {
		return _tasksEntryLocalService.createTasksEntry(tasksEntryId);
	}

	/**
	* Deletes the tasks entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @throws PortalException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteTasksEntry(long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_tasksEntryLocalService.deleteTasksEntry(tasksEntryId);
	}

	/**
	* Deletes the tasks entry from the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntry the tasks entry
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public void deleteTasksEntry(com.liferay.tasks.model.TasksEntry tasksEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_tasksEntryLocalService.deleteTasksEntry(tasksEntry);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the tasks entry with the primary key.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry
	* @throws PortalException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry getTasksEntry(long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getTasksEntry(tasksEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the tasks entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tasks.model.TasksEntry> getTasksEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getTasksEntries(start, end);
	}

	/**
	* Returns the number of tasks entries.
	*
	* @return the number of tasks entries
	* @throws SystemException if a system exception occurred
	*/
	public int getTasksEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getTasksEntriesCount();
	}

	/**
	* Updates the tasks entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tasksEntry the tasks entry
	* @return the tasks entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry updateTasksEntry(
		com.liferay.tasks.model.TasksEntry tasksEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.updateTasksEntry(tasksEntry);
	}

	/**
	* Updates the tasks entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tasksEntry the tasks entry
	* @param merge whether to merge the tasks entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the tasks entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tasks.model.TasksEntry updateTasksEntry(
		com.liferay.tasks.model.TasksEntry tasksEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.updateTasksEntry(tasksEntry, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _tasksEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_tasksEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.tasks.model.TasksEntry addTasksEntry(long userId,
		java.lang.String title, int priority, long assigneeUserId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute, boolean neverDue,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.addTasksEntry(userId, title, priority,
			assigneeUserId, dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
			dueDateMinute, neverDue, serviceContext);
	}

	public java.util.List<com.liferay.tasks.model.TasksEntry> getAssigneeTasksEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getAssigneeTasksEntries(userId, start,
			end);
	}

	public int getAssigneeTasksEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getAssigneeTasksEntriesCount(userId);
	}

	public java.util.List<com.liferay.tasks.model.TasksEntry> getGroupAssigneeTasksEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getGroupAssigneeTasksEntries(groupId,
			userId, start, end);
	}

	public int getGroupAssigneeTasksEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getGroupAssigneeTasksEntriesCount(groupId,
			userId);
	}

	public java.util.List<com.liferay.tasks.model.TasksEntry> getGroupResolverTasksEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getGroupResolverTasksEntries(groupId,
			userId, start, end);
	}

	public int getGroupResolverTasksEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getGroupResolverTasksEntriesCount(groupId,
			userId);
	}

	public java.util.List<com.liferay.tasks.model.TasksEntry> getGroupUserTasksEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getGroupUserTasksEntries(groupId,
			userId, start, end);
	}

	public int getGroupUserTasksEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getGroupUserTasksEntriesCount(groupId,
			userId);
	}

	public java.util.List<com.liferay.tasks.model.TasksEntry> getResolverTasksEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getResolverTasksEntries(userId, start,
			end);
	}

	public int getResolverTasksEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getResolverTasksEntriesCount(userId);
	}

	public java.util.List<com.liferay.tasks.model.TasksEntry> getTasksEntries(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getTasksEntries(groupId, start, end);
	}

	public java.util.List<com.liferay.tasks.model.TasksEntry> getTasksEntries(
		long groupId, int priority, long assigneeUserId, long reporterUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getTasksEntries(groupId, priority,
			assigneeUserId, reporterUserId, status, assetTagIds,
			notAssetTagIds, start, end);
	}

	public int getTasksEntriesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getTasksEntriesCount(groupId);
	}

	public int getTasksEntriesCount(long groupId, int priority,
		long assigneeUserId, long reporterUserId, int status,
		long[] tagsEntryIds, long[] notTagsEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getTasksEntriesCount(groupId, priority,
			assigneeUserId, reporterUserId, status, tagsEntryIds,
			notTagsEntryIds);
	}

	public java.util.List<com.liferay.tasks.model.TasksEntry> getUserTasksEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getUserTasksEntries(userId, start, end);
	}

	public int getUserTasksEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getUserTasksEntriesCount(userId);
	}

	public void updateAsset(long userId,
		com.liferay.tasks.model.TasksEntry tasksEntry, long[] assetCategoryIds,
		java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_tasksEntryLocalService.updateAsset(userId, tasksEntry,
			assetCategoryIds, assetTagNames);
	}

	public com.liferay.tasks.model.TasksEntry updateTasksEntry(
		long tasksEntryId, java.lang.String title, int priority,
		long assigneeUserId, long resolverUserId, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean neverDue, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.updateTasksEntry(tasksEntryId, title,
			priority, assigneeUserId, resolverUserId, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute, neverDue, status,
			serviceContext);
	}

	public com.liferay.tasks.model.TasksEntry updateTasksEntryStatus(
		long tasksEntryId, long resolverUserId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.updateTasksEntryStatus(tasksEntryId,
			resolverUserId, status, serviceContext);
	}

	public TasksEntryLocalService getWrappedTasksEntryLocalService() {
		return _tasksEntryLocalService;
	}

	public void setWrappedTasksEntryLocalService(
		TasksEntryLocalService tasksEntryLocalService) {
		_tasksEntryLocalService = tasksEntryLocalService;
	}

	private TasksEntryLocalService _tasksEntryLocalService;
}