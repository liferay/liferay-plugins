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

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link KaleoLogLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoLogLocalService
 * @generated
 */
public class KaleoLogLocalServiceWrapper implements KaleoLogLocalService,
	ServiceWrapper<KaleoLogLocalService> {
	public KaleoLogLocalServiceWrapper(
		KaleoLogLocalService kaleoLogLocalService) {
		_kaleoLogLocalService = kaleoLogLocalService;
	}

	/**
	* Adds the kaleo log to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @return the kaleo log that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoLog addKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.addKaleoLog(kaleoLog);
	}

	/**
	* Creates a new kaleo log with the primary key. Does not add the kaleo log to the database.
	*
	* @param kaleoLogId the primary key for the new kaleo log
	* @return the new kaleo log
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoLog createKaleoLog(
		long kaleoLogId) {
		return _kaleoLogLocalService.createKaleoLog(kaleoLogId);
	}

	/**
	* Deletes the kaleo log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoLogId the primary key of the kaleo log
	* @throws PortalException if a kaleo log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoLog(long kaleoLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoLogLocalService.deleteKaleoLog(kaleoLogId);
	}

	/**
	* Deletes the kaleo log from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoLogLocalService.deleteKaleoLog(kaleoLog);
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
		return _kaleoLogLocalService.dynamicQuery(dynamicQuery);
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
		return _kaleoLogLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _kaleoLogLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _kaleoLogLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the kaleo log with the primary key.
	*
	* @param kaleoLogId the primary key of the kaleo log
	* @return the kaleo log
	* @throws PortalException if a kaleo log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoLog getKaleoLog(
		long kaleoLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.getKaleoLog(kaleoLogId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo logs
	* @param end the upper bound of the range of kaleo logs (not inclusive)
	* @return the range of kaleo logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoLogs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.getKaleoLogs(start, end);
	}

	/**
	* Returns the number of kaleo logs.
	*
	* @return the number of kaleo logs
	* @throws SystemException if a system exception occurred
	*/
	public int getKaleoLogsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.getKaleoLogsCount();
	}

	/**
	* Updates the kaleo log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @return the kaleo log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoLog updateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.updateKaleoLog(kaleoLog);
	}

	/**
	* Updates the kaleo log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @param merge whether to merge the kaleo log with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the kaleo log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoLog updateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.updateKaleoLog(kaleoLog, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _kaleoLogLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoLogLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addActionExecutionKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction,
		long startTime, long endTime, java.lang.String comment,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.addActionExecutionKaleoLog(kaleoInstanceToken,
			kaleoAction, startTime, endTime, comment, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addNodeEntryKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoNode sourceKaleoNode,
		com.liferay.portal.workflow.kaleo.model.KaleoNode targetKaleoNode,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.addNodeEntryKaleoLog(kaleoInstanceToken,
			sourceKaleoNode, targetKaleoNode, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addNodeExitKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoNode departingKaleoNode,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.addNodeExitKaleoLog(kaleoInstanceToken,
			departingKaleoNode, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskAssignmentKaleoLog(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> previousKaleoTaskAssignmentInstances,
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.addTaskAssignmentKaleoLog(previousKaleoTaskAssignmentInstances,
			kaleoTaskInstanceToken, comment, workflowContext, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskCompletionKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.addTaskCompletionKaleoLog(kaleoTaskInstanceToken,
			comment, workflowContext, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskUpdateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.addTaskUpdateKaleoLog(kaleoTaskInstanceToken,
			comment, workflowContext, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addWorkflowInstanceEndKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.addWorkflowInstanceEndKaleoLog(kaleoInstanceToken,
			serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addWorkflowInstanceStartKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.addWorkflowInstanceStartKaleoLog(kaleoInstanceToken,
			serviceContext);
	}

	public void deleteCompanyKaleoLogs(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoLogLocalService.deleteCompanyKaleoLogs(companyId);
	}

	public void deleteKaleoDefinitionKaleoLogs(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoLogLocalService.deleteKaleoDefinitionKaleoLogs(kaleoDefinitionId);
	}

	public void deleteKaleoInstanceKaleoLogs(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoLogLocalService.deleteKaleoInstanceKaleoLogs(kaleoInstanceId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoInstanceKaleoLogs(
		long kaleoInstanceId, java.util.List<java.lang.Integer> logTypes,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.getKaleoInstanceKaleoLogs(kaleoInstanceId,
			logTypes, start, end, orderByComparator);
	}

	public int getKaleoInstanceKaleoLogsCount(long kaleoInstanceId,
		java.util.List<java.lang.Integer> logTypes)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.getKaleoInstanceKaleoLogsCount(kaleoInstanceId,
			logTypes);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoTaskInstanceTokenKaleoLogs(
		long kaleoTaskInstanceTokenId,
		java.util.List<java.lang.Integer> logTypes, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.getKaleoTaskInstanceTokenKaleoLogs(kaleoTaskInstanceTokenId,
			logTypes, start, end, orderByComparator);
	}

	public int getKaleoTaskInstanceTokenKaleoLogsCount(
		long kaleoTaskInstanceTokenId,
		java.util.List<java.lang.Integer> logTypes)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.getKaleoTaskInstanceTokenKaleoLogsCount(kaleoTaskInstanceTokenId,
			logTypes);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public KaleoLogLocalService getWrappedKaleoLogLocalService() {
		return _kaleoLogLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedKaleoLogLocalService(
		KaleoLogLocalService kaleoLogLocalService) {
		_kaleoLogLocalService = kaleoLogLocalService;
	}

	public KaleoLogLocalService getWrappedService() {
		return _kaleoLogLocalService;
	}

	public void setWrappedService(KaleoLogLocalService kaleoLogLocalService) {
		_kaleoLogLocalService = kaleoLogLocalService;
	}

	private KaleoLogLocalService _kaleoLogLocalService;
}