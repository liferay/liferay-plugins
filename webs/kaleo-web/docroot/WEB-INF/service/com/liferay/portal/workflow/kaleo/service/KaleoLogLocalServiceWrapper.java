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

package com.liferay.portal.workflow.kaleo.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KaleoLogLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoLogLocalService
 * @generated
 */
@ProviderType
public class KaleoLogLocalServiceWrapper implements KaleoLogLocalService,
	ServiceWrapper<KaleoLogLocalService> {
	public KaleoLogLocalServiceWrapper(
		KaleoLogLocalService kaleoLogLocalService) {
		_kaleoLogLocalService = kaleoLogLocalService;
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog addActionExecutionKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction,
		long startTime, long endTime, java.lang.String comment,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoLogLocalService.addActionExecutionKaleoLog(kaleoInstanceToken,
			kaleoAction, startTime, endTime, comment, serviceContext);
	}

	/**
	* Adds the kaleo log to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @return the kaleo log that was added
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog addKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog) {
		return _kaleoLogLocalService.addKaleoLog(kaleoLog);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog addNodeEntryKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoNode sourceKaleoNode,
		com.liferay.portal.workflow.kaleo.model.KaleoNode targetKaleoNode,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoLogLocalService.addNodeEntryKaleoLog(kaleoInstanceToken,
			sourceKaleoNode, targetKaleoNode, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog addNodeExitKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoNode departingKaleoNode,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoLogLocalService.addNodeExitKaleoLog(kaleoInstanceToken,
			departingKaleoNode, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskAssignmentKaleoLog(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> previousKaleoTaskAssignmentInstances,
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoLogLocalService.addTaskAssignmentKaleoLog(previousKaleoTaskAssignmentInstances,
			kaleoTaskInstanceToken, comment, workflowContext, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskCompletionKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoLogLocalService.addTaskCompletionKaleoLog(kaleoTaskInstanceToken,
			comment, workflowContext, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskUpdateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoLogLocalService.addTaskUpdateKaleoLog(kaleoTaskInstanceToken,
			comment, workflowContext, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog addWorkflowInstanceEndKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoLogLocalService.addWorkflowInstanceEndKaleoLog(kaleoInstanceToken,
			serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog addWorkflowInstanceStartKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoLogLocalService.addWorkflowInstanceStartKaleoLog(kaleoInstanceToken,
			serviceContext);
	}

	/**
	* Creates a new kaleo log with the primary key. Does not add the kaleo log to the database.
	*
	* @param kaleoLogId the primary key for the new kaleo log
	* @return the new kaleo log
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog createKaleoLog(
		long kaleoLogId) {
		return _kaleoLogLocalService.createKaleoLog(kaleoLogId);
	}

	@Override
	public void deleteCompanyKaleoLogs(long companyId) {
		_kaleoLogLocalService.deleteCompanyKaleoLogs(companyId);
	}

	@Override
	public void deleteKaleoDefinitionKaleoLogs(long kaleoDefinitionId) {
		_kaleoLogLocalService.deleteKaleoDefinitionKaleoLogs(kaleoDefinitionId);
	}

	@Override
	public void deleteKaleoInstanceKaleoLogs(long kaleoInstanceId) {
		_kaleoLogLocalService.deleteKaleoInstanceKaleoLogs(kaleoInstanceId);
	}

	/**
	* Deletes the kaleo log from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @return the kaleo log that was removed
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog deleteKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog) {
		return _kaleoLogLocalService.deleteKaleoLog(kaleoLog);
	}

	/**
	* Deletes the kaleo log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoLogId the primary key of the kaleo log
	* @return the kaleo log that was removed
	* @throws PortalException if a kaleo log with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog deleteKaleoLog(
		long kaleoLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoLogLocalService.deleteKaleoLog(kaleoLogId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoLogLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kaleoLogLocalService.dynamicQuery();
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
		return _kaleoLogLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _kaleoLogLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _kaleoLogLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _kaleoLogLocalService.dynamicQueryCount(dynamicQuery);
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
		return _kaleoLogLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog fetchKaleoLog(
		long kaleoLogId) {
		return _kaleoLogLocalService.fetchKaleoLog(kaleoLogId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _kaleoLogLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _kaleoLogLocalService.getBeanIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoInstanceKaleoLogs(
		long kaleoInstanceId, java.util.List<java.lang.Integer> logTypes,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoLog> orderByComparator) {
		return _kaleoLogLocalService.getKaleoInstanceKaleoLogs(kaleoInstanceId,
			logTypes, start, end, orderByComparator);
	}

	@Override
	public int getKaleoInstanceKaleoLogsCount(long kaleoInstanceId,
		java.util.List<java.lang.Integer> logTypes) {
		return _kaleoLogLocalService.getKaleoInstanceKaleoLogsCount(kaleoInstanceId,
			logTypes);
	}

	/**
	* Returns the kaleo log with the primary key.
	*
	* @param kaleoLogId the primary key of the kaleo log
	* @return the kaleo log
	* @throws PortalException if a kaleo log with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog getKaleoLog(
		long kaleoLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoLogLocalService.getKaleoLog(kaleoLogId);
	}

	/**
	* Returns a range of all the kaleo logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo logs
	* @param end the upper bound of the range of kaleo logs (not inclusive)
	* @return the range of kaleo logs
	*/
	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoLogs(
		int start, int end) {
		return _kaleoLogLocalService.getKaleoLogs(start, end);
	}

	/**
	* Returns the number of kaleo logs.
	*
	* @return the number of kaleo logs
	*/
	@Override
	public int getKaleoLogsCount() {
		return _kaleoLogLocalService.getKaleoLogsCount();
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoTaskInstanceTokenKaleoLogs(
		long kaleoTaskInstanceTokenId,
		java.util.List<java.lang.Integer> logTypes, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoLog> orderByComparator) {
		return _kaleoLogLocalService.getKaleoTaskInstanceTokenKaleoLogs(kaleoTaskInstanceTokenId,
			logTypes, start, end, orderByComparator);
	}

	@Override
	public int getKaleoTaskInstanceTokenKaleoLogsCount(
		long kaleoTaskInstanceTokenId,
		java.util.List<java.lang.Integer> logTypes) {
		return _kaleoLogLocalService.getKaleoTaskInstanceTokenKaleoLogsCount(kaleoTaskInstanceTokenId,
			logTypes);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoLogLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kaleoLogLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoLogLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the kaleo log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @return the kaleo log that was updated
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog updateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog) {
		return _kaleoLogLocalService.updateKaleoLog(kaleoLog);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public KaleoLogLocalService getWrappedKaleoLogLocalService() {
		return _kaleoLogLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedKaleoLogLocalService(
		KaleoLogLocalService kaleoLogLocalService) {
		_kaleoLogLocalService = kaleoLogLocalService;
	}

	@Override
	public KaleoLogLocalService getWrappedService() {
		return _kaleoLogLocalService;
	}

	@Override
	public void setWrappedService(KaleoLogLocalService kaleoLogLocalService) {
		_kaleoLogLocalService = kaleoLogLocalService;
	}

	private KaleoLogLocalService _kaleoLogLocalService;
}