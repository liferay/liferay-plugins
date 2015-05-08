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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for TasksEntry. This utility wraps
 * {@link com.liferay.tasks.service.impl.TasksEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ryan Park
 * @see TasksEntryLocalService
 * @see com.liferay.tasks.service.base.TasksEntryLocalServiceBaseImpl
 * @see com.liferay.tasks.service.impl.TasksEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class TasksEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.tasks.service.impl.TasksEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the tasks entry to the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntry the tasks entry
	* @return the tasks entry that was added
	*/
	public static com.liferay.tasks.model.TasksEntry addTasksEntry(
		com.liferay.tasks.model.TasksEntry tasksEntry) {
		return getService().addTasksEntry(tasksEntry);
	}

	public static com.liferay.tasks.model.TasksEntry addTasksEntry(
		long userId, java.lang.String title, int priority, long assigneeUserId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute, boolean addDueDate,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTasksEntry(userId, title, priority, assigneeUserId,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			addDueDate, serviceContext);
	}

	/**
	* Creates a new tasks entry with the primary key. Does not add the tasks entry to the database.
	*
	* @param tasksEntryId the primary key for the new tasks entry
	* @return the new tasks entry
	*/
	public static com.liferay.tasks.model.TasksEntry createTasksEntry(
		long tasksEntryId) {
		return getService().createTasksEntry(tasksEntryId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the tasks entry from the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntry the tasks entry
	* @return the tasks entry that was removed
	* @throws PortalException
	*/
	public static com.liferay.tasks.model.TasksEntry deleteTasksEntry(
		com.liferay.tasks.model.TasksEntry tasksEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTasksEntry(tasksEntry);
	}

	/**
	* Deletes the tasks entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry that was removed
	* @throws PortalException if a tasks entry with the primary key could not be found
	*/
	public static com.liferay.tasks.model.TasksEntry deleteTasksEntry(
		long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTasksEntry(tasksEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.tasks.model.TasksEntry fetchTasksEntry(
		long tasksEntryId) {
		return getService().fetchTasksEntry(tasksEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<com.liferay.tasks.model.TasksEntry> getAssigneeTasksEntries(
		long assigneeUserId, int start, int end) {
		return getService().getAssigneeTasksEntries(assigneeUserId, start, end);
	}

	public static int getAssigneeTasksEntriesCount(long assigneeUserId) {
		return getService().getAssigneeTasksEntriesCount(assigneeUserId);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static java.util.List<com.liferay.tasks.model.TasksEntry> getGroupAssigneeTasksEntries(
		long groupId, long assigneeUserId, int start, int end) {
		return getService()
				   .getGroupAssigneeTasksEntries(groupId, assigneeUserId,
			start, end);
	}

	public static int getGroupAssigneeTasksEntriesCount(long groupId,
		long assigneeUserId) {
		return getService()
				   .getGroupAssigneeTasksEntriesCount(groupId, assigneeUserId);
	}

	public static java.util.List<com.liferay.tasks.model.TasksEntry> getGroupResolverTasksEntries(
		long groupId, long resolverUserId, int start, int end) {
		return getService()
				   .getGroupResolverTasksEntries(groupId, resolverUserId,
			start, end);
	}

	public static int getGroupResolverTasksEntriesCount(long groupId,
		long resolverUserId) {
		return getService()
				   .getGroupResolverTasksEntriesCount(groupId, resolverUserId);
	}

	public static java.util.List<com.liferay.tasks.model.TasksEntry> getGroupUserTasksEntries(
		long groupId, long userId, int start, int end) {
		return getService().getGroupUserTasksEntries(groupId, userId, start, end);
	}

	public static int getGroupUserTasksEntriesCount(long groupId, long userId) {
		return getService().getGroupUserTasksEntriesCount(groupId, userId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List<com.liferay.tasks.model.TasksEntry> getResolverTasksEntries(
		long resolverUserId, int start, int end) {
		return getService().getResolverTasksEntries(resolverUserId, start, end);
	}

	public static int getResolverTasksEntriesCount(long resolverUserId) {
		return getService().getResolverTasksEntriesCount(resolverUserId);
	}

	public static java.util.List<com.liferay.tasks.model.TasksEntry> getTasksEntries(
		long groupId, int start, int end) {
		return getService().getTasksEntries(groupId, start, end);
	}

	public static java.util.List<com.liferay.tasks.model.TasksEntry> getTasksEntries(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds, int start,
		int end) {
		return getService()
				   .getTasksEntries(groupId, userId, priority, assigneeUserId,
			status, assetTagIds, notAssetTagIds, start, end);
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
	public static java.util.List<com.liferay.tasks.model.TasksEntry> getTasksEntries(
		int start, int end) {
		return getService().getTasksEntries(start, end);
	}

	/**
	* Returns the number of tasks entries.
	*
	* @return the number of tasks entries
	*/
	public static int getTasksEntriesCount() {
		return getService().getTasksEntriesCount();
	}

	public static int getTasksEntriesCount(long groupId) {
		return getService().getTasksEntriesCount(groupId);
	}

	public static int getTasksEntriesCount(long groupId, long userId,
		int priority, long assigneeUserId, int status, long[] tagsEntryIds,
		long[] notTagsEntryIds) {
		return getService()
				   .getTasksEntriesCount(groupId, userId, priority,
			assigneeUserId, status, tagsEntryIds, notTagsEntryIds);
	}

	/**
	* Returns the tasks entry with the primary key.
	*
	* @param tasksEntryId the primary key of the tasks entry
	* @return the tasks entry
	* @throws PortalException if a tasks entry with the primary key could not be found
	*/
	public static com.liferay.tasks.model.TasksEntry getTasksEntry(
		long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTasksEntry(tasksEntryId);
	}

	public static java.util.List<com.liferay.tasks.model.TasksEntry> getUserTasksEntries(
		long userId, int start, int end) {
		return getService().getUserTasksEntries(userId, start, end);
	}

	public static int getUserTasksEntriesCount(long userId) {
		return getService().getUserTasksEntriesCount(userId);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static void updateAsset(long userId,
		com.liferay.tasks.model.TasksEntry tasksEntry, long[] assetCategoryIds,
		java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateAsset(userId, tasksEntry, assetCategoryIds, assetTagNames);
	}

	/**
	* Updates the tasks entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tasksEntry the tasks entry
	* @return the tasks entry that was updated
	*/
	public static com.liferay.tasks.model.TasksEntry updateTasksEntry(
		com.liferay.tasks.model.TasksEntry tasksEntry) {
		return getService().updateTasksEntry(tasksEntry);
	}

	public static com.liferay.tasks.model.TasksEntry updateTasksEntry(
		long tasksEntryId, java.lang.String title, int priority,
		long assigneeUserId, long resolverUserId, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean addDueDate, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTasksEntry(tasksEntryId, title, priority,
			assigneeUserId, resolverUserId, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute, addDueDate, status,
			serviceContext);
	}

	public static com.liferay.tasks.model.TasksEntry updateTasksEntryStatus(
		long tasksEntryId, long resolverUserId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTasksEntryStatus(tasksEntryId, resolverUserId,
			status, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static TasksEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TasksEntryLocalService.class.getName());

			if (invokableLocalService instanceof TasksEntryLocalService) {
				_service = (TasksEntryLocalService)invokableLocalService;
			}
			else {
				_service = new TasksEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TasksEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(TasksEntryLocalService service) {
	}

	private static TasksEntryLocalService _service;
}