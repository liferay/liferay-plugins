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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TasksEntryLocalService}.
 *
 * @author Ryan Park
 * @see TasksEntryLocalService
 * @generated
 */
public class TasksEntryLocalServiceWrapper implements TasksEntryLocalService,
	ServiceWrapper<TasksEntryLocalService> {
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
	@Override
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
	@Override
	public com.liferay.tasks.model.TasksEntry createTasksEntry(
		long tasksEntryId) {
		return _tasksEntryLocalService.createTasksEntry(tasksEntryId);
	}

	/**
	* Deletes the tasks entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry that was removed
	* @throws PortalException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.tasks.model.TasksEntry deleteTasksEntry(
		long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.deleteTasksEntry(tasksEntryId);
	}

	/**
	* Deletes the tasks entry from the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntry the tasks entry
	* @return the tasks entry that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.tasks.model.TasksEntry deleteTasksEntry(
		com.liferay.tasks.model.TasksEntry tasksEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.deleteTasksEntry(tasksEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _tasksEntryLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
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
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.tasks.model.TasksEntry fetchTasksEntry(long tasksEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.fetchTasksEntry(tasksEntryId);
	}

	/**
	* Returns the tasks entry with the primary key.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry
	* @throws PortalException if a tasks entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.tasks.model.TasksEntry getTasksEntry(long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getTasksEntry(tasksEntryId);
	}

	@Override
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tasks.model.impl.TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tasks entries
	* @param end the upper bound of the range of tasks entries (not inclusive)
	* @return the range of tasks entries
	* @throws SystemException if a system exception occurred
	*/
	@Override
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
	@Override
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
	@Override
	public com.liferay.tasks.model.TasksEntry updateTasksEntry(
		com.liferay.tasks.model.TasksEntry tasksEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.updateTasksEntry(tasksEntry);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _tasksEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_tasksEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _tasksEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.tasks.model.TasksEntry addTasksEntry(long userId,
		java.lang.String title, int priority, long assigneeUserId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute, boolean addDueDate,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.addTasksEntry(userId, title, priority,
			assigneeUserId, dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
			dueDateMinute, addDueDate, serviceContext);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getAssigneeTasksEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getAssigneeTasksEntries(userId, start,
			end);
	}

	@Override
	public int getAssigneeTasksEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getAssigneeTasksEntriesCount(userId);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getGroupAssigneeTasksEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getGroupAssigneeTasksEntries(groupId,
			userId, start, end);
	}

	@Override
	public int getGroupAssigneeTasksEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getGroupAssigneeTasksEntriesCount(groupId,
			userId);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getGroupResolverTasksEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getGroupResolverTasksEntries(groupId,
			userId, start, end);
	}

	@Override
	public int getGroupResolverTasksEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getGroupResolverTasksEntriesCount(groupId,
			userId);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getGroupUserTasksEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getGroupUserTasksEntries(groupId,
			userId, start, end);
	}

	@Override
	public int getGroupUserTasksEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getGroupUserTasksEntriesCount(groupId,
			userId);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getResolverTasksEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getResolverTasksEntries(userId, start,
			end);
	}

	@Override
	public int getResolverTasksEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getResolverTasksEntriesCount(userId);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getTasksEntries(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getTasksEntries(groupId, start, end);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getTasksEntries(
		long groupId, int priority, long assigneeUserId, long reporterUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getTasksEntries(groupId, priority,
			assigneeUserId, reporterUserId, status, assetTagIds,
			notAssetTagIds, start, end);
	}

	@Override
	public int getTasksEntriesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getTasksEntriesCount(groupId);
	}

	@Override
	public int getTasksEntriesCount(long groupId, int priority,
		long assigneeUserId, long reporterUserId, int status,
		long[] tagsEntryIds, long[] notTagsEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getTasksEntriesCount(groupId, priority,
			assigneeUserId, reporterUserId, status, tagsEntryIds,
			notTagsEntryIds);
	}

	@Override
	public java.util.List<com.liferay.tasks.model.TasksEntry> getUserTasksEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getUserTasksEntries(userId, start, end);
	}

	@Override
	public int getUserTasksEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.getUserTasksEntriesCount(userId);
	}

	@Override
	public void updateAsset(long userId,
		com.liferay.tasks.model.TasksEntry tasksEntry, long[] assetCategoryIds,
		java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_tasksEntryLocalService.updateAsset(userId, tasksEntry,
			assetCategoryIds, assetTagNames);
	}

	@Override
	public com.liferay.tasks.model.TasksEntry updateTasksEntry(
		long tasksEntryId, java.lang.String title, int priority,
		long assigneeUserId, long resolverUserId, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean addDueDate, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.updateTasksEntry(tasksEntryId, title,
			priority, assigneeUserId, resolverUserId, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute, addDueDate, status,
			serviceContext);
	}

	@Override
	public com.liferay.tasks.model.TasksEntry updateTasksEntryStatus(
		long tasksEntryId, long resolverUserId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tasksEntryLocalService.updateTasksEntryStatus(tasksEntryId,
			resolverUserId, status, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public TasksEntryLocalService getWrappedTasksEntryLocalService() {
		return _tasksEntryLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedTasksEntryLocalService(
		TasksEntryLocalService tasksEntryLocalService) {
		_tasksEntryLocalService = tasksEntryLocalService;
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