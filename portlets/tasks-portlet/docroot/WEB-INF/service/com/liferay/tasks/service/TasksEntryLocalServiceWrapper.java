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

package com.liferay.tasks.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TasksEntryLocalService}.
 *
 * @author Ryan Park
 * @see TasksEntryLocalService
 * @generated
 */
@ProviderType
public class TasksEntryLocalServiceWrapper implements TasksEntryLocalService,
	ServiceWrapper<TasksEntryLocalService> {
	public TasksEntryLocalServiceWrapper(
		TasksEntryLocalService tasksEntryLocalService) {
		_tasksEntryLocalService = tasksEntryLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _tasksEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _tasksEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _tasksEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _tasksEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _tasksEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the tasks entry to the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntry the tasks entry
	* @return the tasks entry that was added
	*/
	@Override
	public com.liferay.tasks.model.TasksEntry addTasksEntry(
		com.liferay.tasks.model.TasksEntry tasksEntry) {
		return _tasksEntryLocalService.addTasksEntry(tasksEntry);
	}

	@Override
	public com.liferay.tasks.model.TasksEntry addTasksEntry(long userId,
		java.lang.String title, int priority, long assigneeUserId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute, boolean addDueDate,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _tasksEntryLocalService.addTasksEntry(userId, title, priority,
			assigneeUserId, dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
			dueDateMinute, addDueDate, serviceContext);
	}

	/**
	* Creates a new tasks entry with the primary key. Does not add the tasks entry to the database.
	*
	* @param tasksEntryId the primary key for the new tasks entry
	* @return the new tasks entry
	*/
	@Override
	public com.liferay.tasks.model.TasksEntry createTasksEntry(
		long tasksEntryId) {
		return _tasksEntryLocalService.createTasksEntry(tasksEntryId);
	}

	/**
	* Deletes the tasks entry from the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntry the tasks entry
	* @return the tasks entry that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.tasks.model.TasksEntry deleteTasksEntry(
		com.liferay.tasks.model.TasksEntry tasksEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _tasksEntryLocalService.deleteTasksEntry(tasksEntry);
	}

	/**
	* Deletes the tasks entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry that was removed
	* @throws PortalException if a tasks entry with the primary key could not be found
	*/
	@Override
	public com.liferay.tasks.model.TasksEntry deleteTasksEntry(
		long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _tasksEntryLocalService.deleteTasksEntry(tasksEntryId);
	}

	@Override
	public com.liferay.tasks.model.TasksEntry fetchTasksEntry(long tasksEntryId) {
		return _tasksEntryLocalService.fetchTasksEntry(tasksEntryId);
	}

	/**
	* Returns the tasks entry with the primary key.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry
	* @throws PortalException if a tasks entry with the primary key could not be found
	*/
	@Override
	public com.liferay.tasks.model.TasksEntry getTasksEntry(long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _tasksEntryLocalService.getTasksEntry(tasksEntryId);
	}

	/**
	* Updates the tasks entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tasksEntry the tasks entry
	* @return the tasks entry that was updated
	*/
	@Override
	public com.liferay.tasks.model.TasksEntry updateTasksEntry(
		com.liferay.tasks.model.TasksEntry tasksEntry) {
		return _tasksEntryLocalService.updateTasksEntry(tasksEntry);
	}

	@Override
	public com.liferay.tasks.model.TasksEntry updateTasksEntry(
		long tasksEntryId, java.lang.String title, int priority,
		long assigneeUserId, long resolverUserId, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean addDueDate, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _tasksEntryLocalService.updateTasksEntry(tasksEntryId, title,
			priority, assigneeUserId, resolverUserId, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute, addDueDate, status,
			serviceContext);
	}

	@Override
	public com.liferay.tasks.model.TasksEntry updateTasksEntryStatus(
		long tasksEntryId, long resolverUserId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _tasksEntryLocalService.updateTasksEntryStatus(tasksEntryId,
			resolverUserId, status, serviceContext);
	}

	@Override
	public int getAssigneeTasksEntriesCount(long assigneeUserId) {
		return _tasksEntryLocalService.getAssigneeTasksEntriesCount(assigneeUserId);
	}

	@Override
	public int getGroupAssigneeTasksEntriesCount(long groupId,
		long assigneeUserId) {
		return _tasksEntryLocalService.getGroupAssigneeTasksEntriesCount(groupId,
			assigneeUserId);
	}

	@Override
	public int getGroupResolverTasksEntriesCount(long groupId,
		long resolverUserId) {
		return _tasksEntryLocalService.getGroupResolverTasksEntriesCount(groupId,
			resolverUserId);
	}

	@Override
	public int getGroupUserTasksEntriesCount(long groupId, long userId) {
		return _tasksEntryLocalService.getGroupUserTasksEntriesCount(groupId,
			userId);
	}

	@Override
	public int getResolverTasksEntriesCount(long resolverUserId) {
		return _tasksEntryLocalService.getResolverTasksEntriesCount(resolverUserId);
	}

	/**
	* Returns the number of tasks entries.
	*
	* @return the number of tasks entries
	*/
	@Override
	public int getTasksEntriesCount() {
		return _tasksEntryLocalService.getTasksEntriesCount();
	}

	@Override
	public int getTasksEntriesCount(long groupId) {
		return _tasksEntryLocalService.getTasksEntriesCount(groupId);
	}

	@Override
	public int getTasksEntriesCount(long groupId, long userId, int priority,
		long assigneeUserId, int status, long[] tagsEntryIds,
		long[] notTagsEntryIds) {
		return _tasksEntryLocalService.getTasksEntriesCount(groupId, userId,
			priority, assigneeUserId, status, tagsEntryIds, notTagsEntryIds);
	}

	@Override
	public int getUserTasksEntriesCount(long userId) {
		return _tasksEntryLocalService.getUserTasksEntriesCount(userId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _tasksEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _tasksEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _tasksEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _tasksEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _tasksEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getAssigneeTasksEntries(
		long assigneeUserId, int start, int end) {
		return _tasksEntryLocalService.getAssigneeTasksEntries(assigneeUserId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getGroupAssigneeTasksEntries(
		long groupId, long assigneeUserId, int start, int end) {
		return _tasksEntryLocalService.getGroupAssigneeTasksEntries(groupId,
			assigneeUserId, start, end);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getGroupResolverTasksEntries(
		long groupId, long resolverUserId, int start, int end) {
		return _tasksEntryLocalService.getGroupResolverTasksEntries(groupId,
			resolverUserId, start, end);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getGroupUserTasksEntries(
		long groupId, long userId, int start, int end) {
		return _tasksEntryLocalService.getGroupUserTasksEntries(groupId,
			userId, start, end);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getResolverTasksEntries(
		long resolverUserId, int start, int end) {
		return _tasksEntryLocalService.getResolverTasksEntries(resolverUserId,
			start, end);
	}

	/**
	* Returns a range of all the tasks entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of tasks entries
	*/
	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getTasksEntries(
		int start, int end) {
		return _tasksEntryLocalService.getTasksEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getTasksEntries(
		long groupId, int start, int end) {
		return _tasksEntryLocalService.getTasksEntries(groupId, start, end);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getTasksEntries(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds, int start,
		int end) {
		return _tasksEntryLocalService.getTasksEntries(groupId, userId,
			priority, assigneeUserId, status, assetTagIds, notAssetTagIds,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getUserTasksEntries(
		long userId, int start, int end) {
		return _tasksEntryLocalService.getUserTasksEntries(userId, start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _tasksEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _tasksEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void updateAsset(long userId,
		com.liferay.tasks.model.TasksEntry tasksEntry, long[] assetCategoryIds,
		java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException {
		_tasksEntryLocalService.updateAsset(userId, tasksEntry,
			assetCategoryIds, assetTagNames);
	}

	@Override
	public TasksEntryLocalService getWrappedService() {
		return _tasksEntryLocalService;
	}

	@Override
	public void setWrappedService(TasksEntryLocalService tasksEntryLocalService) {
		_tasksEntryLocalService = tasksEntryLocalService;
	}

	private TasksEntryLocalService _tasksEntryLocalService;
}