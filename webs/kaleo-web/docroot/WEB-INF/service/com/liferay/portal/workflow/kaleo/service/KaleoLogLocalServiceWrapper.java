/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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


/**
 * <a href="KaleoLogLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link KaleoLogLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoLogLocalService
 * @generated
 */
public class KaleoLogLocalServiceWrapper implements KaleoLogLocalService {
	public KaleoLogLocalServiceWrapper(
		KaleoLogLocalService kaleoLogLocalService) {
		_kaleoLogLocalService = kaleoLogLocalService;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.addKaleoLog(kaleoLog);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog createKaleoLog(
		long kaleoLogId) {
		return _kaleoLogLocalService.createKaleoLog(kaleoLogId);
	}

	public void deleteKaleoLog(long kaleoLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoLogLocalService.deleteKaleoLog(kaleoLogId);
	}

	public void deleteKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoLogLocalService.deleteKaleoLog(kaleoLog);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog getKaleoLog(
		long kaleoLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.getKaleoLog(kaleoLogId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoLogs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.getKaleoLogs(start, end);
	}

	public int getKaleoLogsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.getKaleoLogsCount();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog updateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.updateKaleoLog(kaleoLog);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog updateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.updateKaleoLog(kaleoLog, merge);
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
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken previousKaleoTaskInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken newKaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.addTaskAssignmentKaleoLog(previousKaleoTaskInstanceToken,
			newKaleoTaskInstanceToken, comment, workflowContext, serviceContext);
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

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoTaskKaleoLogs(
		long kaleoTaskId, java.util.List<java.lang.Integer> logTypes,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.getKaleoTaskKaleoLogs(kaleoTaskId,
			logTypes, start, end, orderByComparator);
	}

	public int getKaleoTaskKaleoLogsCount(long kaleoTaskId,
		java.util.List<java.lang.Integer> logTypes)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLogLocalService.getKaleoTaskKaleoLogsCount(kaleoTaskId,
			logTypes);
	}

	public KaleoLogLocalService getWrappedKaleoLogLocalService() {
		return _kaleoLogLocalService;
	}

	private KaleoLogLocalService _kaleoLogLocalService;
}