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

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.NoSuchLogException;
import com.liferay.portal.workflow.kaleo.definition.LogType;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.base.KaleoLogLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.util.KaleoLogUtil;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoLogLocalServiceImpl extends KaleoLogLocalServiceBaseImpl {

	public KaleoLog addActionExecutionKaleoLog(
			KaleoInstanceToken kaleoInstanceToken, KaleoAction kaleoAction,
			long startTime, long endTime, String comment,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoLog kaleoLog = createKaleoLog(
			kaleoInstanceToken, LogType.ACTION_EXECUTION, serviceContext);

		kaleoLog.setKaleoClassName(kaleoAction.getKaleoClassName());
		kaleoLog.setKaleoClassPK(kaleoAction.getKaleoClassPK());
		kaleoLog.setKaleoDefinitionId(kaleoAction.getKaleoDefinitionId());
		kaleoLog.setKaleoNodeName(kaleoAction.getKaleoNodeName());
		kaleoLog.setComment(comment);
		kaleoLog.setStartDate(new Date(startTime));
		kaleoLog.setEndDate(new Date(endTime));
		kaleoLog.setDuration(endTime - startTime);

		kaleoLogPersistence.update(kaleoLog, false);

		return kaleoLog;
	}

	public KaleoLog addNodeEntryKaleoLog(
			KaleoInstanceToken kaleoInstanceToken, KaleoNode sourceKaleoNode,
			KaleoNode targetKaleoNode, ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoLog kaleoLog = createKaleoLog(
			kaleoInstanceToken, LogType.NODE_ENTRY, serviceContext);

		kaleoLog.setKaleoClassName(KaleoNode.class.getName());
		kaleoLog.setKaleoClassPK(targetKaleoNode.getKaleoNodeId());
		kaleoLog.setKaleoDefinitionId(targetKaleoNode.getKaleoDefinitionId());
		kaleoLog.setKaleoNodeName(targetKaleoNode.getName());
		kaleoLog.setTerminalKaleoNode(targetKaleoNode.isTerminal());

		if (sourceKaleoNode != null) {
			kaleoLog.setPreviousKaleoNodeId(sourceKaleoNode.getKaleoNodeId());
			kaleoLog.setPreviousKaleoNodeName(sourceKaleoNode.getName());
		}

		kaleoLog.setStartDate(kaleoLog.getCreateDate());

		kaleoLogPersistence.update(kaleoLog, false);

		return kaleoLog;
	}

	public KaleoLog addNodeExitKaleoLog(
			KaleoInstanceToken kaleoInstanceToken, KaleoNode departingKaleoNode,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoLog kaleoLog = createKaleoLog(
			kaleoInstanceToken, LogType.NODE_EXIT, serviceContext);

		kaleoLog.setKaleoClassName(KaleoNode.class.getName());
		kaleoLog.setKaleoClassPK(departingKaleoNode.getKaleoNodeId());
		kaleoLog.setKaleoDefinitionId(
			departingKaleoNode.getKaleoDefinitionId());
		kaleoLog.setKaleoNodeName(departingKaleoNode.getName());
		kaleoLog.setEndDate(kaleoLog.getCreateDate());

		try {
			KaleoLog previousKaleoLog = getPreviousLog(
				kaleoLog.getKaleoInstanceTokenId(), kaleoLog.getKaleoClassPK(),
				LogType.WORKFLOW_INSTANCE_START);

			Date startDate = previousKaleoLog.getStartDate();
			Date endDate = kaleoLog.getEndDate();

			kaleoLog.setDuration(endDate.getTime() - startDate.getTime());
		}
		catch (NoSuchLogException nsle) {
		}

		kaleoLogPersistence.update(kaleoLog, false);

		return kaleoLog;
	}

	public KaleoLog addTaskAssignmentKaleoLog(
			List<KaleoTaskAssignmentInstance>
			previousKaleoTaskAssignmentInstances,
			KaleoTaskInstanceToken kaleoTaskInstanceToken, String comment,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			kaleoTaskInstanceToken.getKaleoInstanceToken();

		KaleoLog kaleoLog = createKaleoLog(
			kaleoInstanceToken, LogType.TASK_ASSIGNMENT, serviceContext);

		kaleoLog.setKaleoTaskInstanceTokenId(
			kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());

		KaleoNode currentKaleoNode = kaleoInstanceToken.getCurrentKaleoNode();

		kaleoLog.setKaleoClassName(KaleoNode.class.getName());
		kaleoLog.setKaleoClassPK(currentKaleoNode.getKaleoNodeId());
		kaleoLog.setKaleoDefinitionId(currentKaleoNode.getKaleoDefinitionId());
		kaleoLog.setKaleoNodeName(currentKaleoNode.getName());

		if (previousKaleoTaskAssignmentInstances != null) {
			if (previousKaleoTaskAssignmentInstances.size() == 1) {
				KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance =
					previousKaleoTaskAssignmentInstances.get(0);

				kaleoLog.setPreviousAssigneeClassName(
					kaleoTaskAssignmentInstance.getAssigneeClassName());
				kaleoLog.setPreviousAssigneeClassPK(
					kaleoTaskAssignmentInstance.getAssigneeClassPK());
			}
		}

		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			kaleoTaskInstanceToken.getKaleoTaskAssignmentInstances();

		if (!kaleoTaskAssignmentInstances.isEmpty()) {
			KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance =
				kaleoTaskAssignmentInstances.get(0);

			kaleoLog.setCurrentAssigneeClassName(
				kaleoTaskAssignmentInstance.getAssigneeClassName());
			kaleoLog.setCurrentAssigneeClassPK(
				kaleoTaskAssignmentInstance.getAssigneeClassPK());
		}

		kaleoLog.setComment(comment);
		kaleoLog.setWorkflowContext(
			WorkflowContextUtil.convert(workflowContext));

		kaleoLogPersistence.update(kaleoLog, false);

		return kaleoLog;
	}

	public KaleoLog addTaskCompletionKaleoLog(
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			String comment, Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			kaleoTaskInstanceToken.getKaleoInstanceToken();

		KaleoLog kaleoLog = createKaleoLog(
			kaleoInstanceToken, LogType.TASK_COMPLETION, serviceContext);

		kaleoLog.setKaleoTaskInstanceTokenId(
			kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());

		KaleoNode currentKaleoNode = kaleoInstanceToken.getCurrentKaleoNode();

		kaleoLog.setKaleoClassName(KaleoNode.class.getName());
		kaleoLog.setKaleoClassPK(currentKaleoNode.getKaleoNodeId());
		kaleoLog.setKaleoDefinitionId(currentKaleoNode.getKaleoDefinitionId());
		kaleoLog.setKaleoNodeName(currentKaleoNode.getName());

		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			kaleoTaskInstanceToken.getKaleoTaskAssignmentInstances();

		if (!kaleoTaskAssignmentInstances.isEmpty()) {
			KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance =
				kaleoTaskAssignmentInstances.get(0);

			kaleoLog.setCurrentAssigneeClassName(
				kaleoTaskAssignmentInstance.getAssigneeClassName());
			kaleoLog.setCurrentAssigneeClassPK(
				kaleoTaskAssignmentInstance.getAssigneeClassPK());
		}

		kaleoLog.setComment(comment);
		kaleoLog.setWorkflowContext(
			WorkflowContextUtil.convert(workflowContext));

		kaleoLogPersistence.update(kaleoLog, false);

		return kaleoLog;
	}

	public KaleoLog addTaskUpdateKaleoLog(
			KaleoTaskInstanceToken kaleoTaskInstanceToken, String comment,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			kaleoTaskInstanceToken.getKaleoInstanceToken();

		KaleoLog kaleoLog = createKaleoLog(
			kaleoInstanceToken, LogType.TASK_UPDATE, serviceContext);

		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			kaleoTaskInstanceToken.getKaleoTaskAssignmentInstances();

		if (!kaleoTaskAssignmentInstances.isEmpty()) {
			KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance =
				kaleoTaskAssignmentInstances.get(0);

			kaleoLog.setCurrentAssigneeClassPK(
				kaleoTaskAssignmentInstance.getAssigneeClassPK());
			kaleoLog.setCurrentAssigneeClassName(
				kaleoTaskAssignmentInstance.getAssigneeClassName());
		}

		kaleoLog.setComment(comment);
		kaleoLog.setWorkflowContext(
			WorkflowContextUtil.convert(workflowContext));

		kaleoLogPersistence.update(kaleoLog, false);

		return kaleoLog;
	}

	public KaleoLog addWorkflowInstanceEndKaleoLog(
			KaleoInstanceToken kaleoInstanceToken,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoLog kaleoLog = createKaleoLog(
			kaleoInstanceToken, LogType.WORKFLOW_INSTANCE_END, serviceContext);

		kaleoLog.setEndDate(kaleoLog.getCreateDate());

		try {
			KaleoLog previousKaleoLog = getPreviousLog(
				kaleoLog.getKaleoInstanceTokenId(), 0,
				LogType.WORKFLOW_INSTANCE_START);

			Date startDate = previousKaleoLog.getStartDate();
			Date endDate = kaleoLog.getEndDate();

			kaleoLog.setDuration(endDate.getTime() - startDate.getTime());
		}
		catch (NoSuchLogException nsle) {
		}

		kaleoLogPersistence.update(kaleoLog, false);

		return kaleoLog;
	}

	public KaleoLog addWorkflowInstanceStartKaleoLog(
			KaleoInstanceToken kaleoInstanceToken,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoLog kaleoLog = createKaleoLog(
			kaleoInstanceToken, LogType.WORKFLOW_INSTANCE_START,
			serviceContext);

		kaleoLog.setStartDate(kaleoLog.getCreateDate());

		KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

		kaleoLog.setWorkflowContext(kaleoInstance.getWorkflowContext());

		kaleoLogPersistence.update(kaleoLog, false);

		return kaleoLog;
	}

	public void deleteCompanyKaleoLogs(long companyId) throws SystemException {
		kaleoLogPersistence.removeByCompanyId(companyId);
	}

	public void deleteKaleoDefinitionKaleoLogs(long kaleoDefinitionId)
		throws SystemException {

		kaleoLogPersistence.removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	public void deleteKaleoInstanceKaleoLogs(long kaleoInstanceId)
		throws SystemException {

		kaleoLogPersistence.removeByKaleoInstanceId(kaleoInstanceId);
	}

	public List<KaleoLog> getKaleoInstanceKaleoLogs(
			long kaleoInstanceId, List<Integer> logTypes, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		if ((logTypes == null) || logTypes.isEmpty()) {
			return kaleoLogPersistence.findByKaleoInstanceId(
				kaleoInstanceId, start, end, orderByComparator);
		}
		else {
			DynamicQuery dynamicQuery = buildKaleoInstanceDynamicQuery(
				kaleoInstanceId, logTypes);

			return dynamicQuery(dynamicQuery, start, end, orderByComparator);
		}
	}

	public int getKaleoInstanceKaleoLogsCount(
			long kaleoInstanceId, List<Integer> logTypes)
		throws SystemException {

		if ((logTypes == null) || logTypes.isEmpty()) {
			return kaleoLogPersistence.countByKaleoInstanceId(kaleoInstanceId);
		}
		else {
			DynamicQuery dynamicQuery = buildKaleoInstanceDynamicQuery(
				kaleoInstanceId, logTypes);

			return (int)dynamicQueryCount(dynamicQuery);
		}
	}

	public List<KaleoLog> getKaleoTaskInstanceTokenKaleoLogs(
			long kaleoTaskInstanceTokenId, List<Integer> logTypes, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		if ((logTypes == null) || logTypes.isEmpty()) {
			return kaleoLogPersistence.findByKaleoTaskInstanceTokenId(
				kaleoTaskInstanceTokenId, start, end, orderByComparator);
		}
		else {
			DynamicQuery dynamicQuery = buildKaleoTaskInstanceTokenDynamicQuery(
				kaleoTaskInstanceTokenId, logTypes);

			return dynamicQuery(dynamicQuery, start, end, orderByComparator);
		}
	}

	public int getKaleoTaskInstanceTokenKaleoLogsCount(
			long kaleoTaskInstanceTokenId, List<Integer> logTypes)
		throws SystemException {

		if ((logTypes == null) || logTypes.isEmpty()) {
			return kaleoLogPersistence.countByKaleoTaskInstanceTokenId(
				kaleoTaskInstanceTokenId);
		}
		else {
			DynamicQuery dynamicQuery = buildKaleoTaskInstanceTokenDynamicQuery(
				kaleoTaskInstanceTokenId, logTypes);

			return (int)dynamicQueryCount(dynamicQuery);
		}
	}

	protected void addLogTypesJunction(
		DynamicQuery dynamicQuery, List<Integer> logTypes) {

		Junction junction = RestrictionsFactoryUtil.disjunction();

		for (Integer logType : logTypes) {
			String logTypeString = KaleoLogUtil.convert(logType);

			if (Validator.isNotNull(logTypeString)) {
				junction.add(
					PropertyFactoryUtil.forName("type").eq(logTypeString));
			}
		}

		dynamicQuery.add(junction);
	}

	protected DynamicQuery buildKaleoInstanceDynamicQuery(
		long kaleoInstanceId, List<Integer> logTypes) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoLog.class, getClass().getClassLoader());

		dynamicQuery.add(
			PropertyFactoryUtil.forName("kaleoInstanceId").eq(kaleoInstanceId));

		addLogTypesJunction(dynamicQuery, logTypes);

		return dynamicQuery;
	}

	protected DynamicQuery buildKaleoTaskInstanceTokenDynamicQuery(
		long kaleoTaskId, List<Integer> logTypes) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoLog.class, getClass().getClassLoader());

		dynamicQuery.add(
			PropertyFactoryUtil.forName("kaleoTaskInstanceTokenId").eq(
				kaleoTaskId));

		addLogTypesJunction(dynamicQuery, logTypes);

		return dynamicQuery;
	}

	protected KaleoLog createKaleoLog(
			KaleoInstanceToken kaleoInstanceToken, LogType logType,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getGuestOrUserId());
		Date now = new Date();

		long kaleoLogId = counterLocalService.increment();

		KaleoLog kaleoLog = kaleoLogPersistence.create(kaleoLogId);

		kaleoLog.setCompanyId(user.getCompanyId());
		kaleoLog.setUserId(user.getUserId());
		kaleoLog.setUserName(user.getFullName());
		kaleoLog.setCreateDate(now);
		kaleoLog.setModifiedDate(now);
		kaleoLog.setKaleoDefinitionId(
			kaleoInstanceToken.getKaleoDefinitionId());
		kaleoLog.setKaleoInstanceId(kaleoInstanceToken.getKaleoInstanceId());
		kaleoLog.setKaleoInstanceTokenId(
			kaleoInstanceToken.getKaleoInstanceTokenId());
		kaleoLog.setType(logType.name());

		return kaleoLog;
	}

	protected KaleoLog getPreviousLog(
			long kaleoInstanceTokenId, long kaleoNodeId, LogType logType)
		throws PortalException, SystemException {

		List<KaleoLog> kaleoLogEntries = null;

		if (kaleoNodeId > 0) {
			kaleoLogEntries = kaleoLogPersistence.findByKCN_KCPK_KITI_T(
				KaleoNode.class.getName(), kaleoNodeId, kaleoInstanceTokenId,
				logType.name());
		}
		else {
			kaleoLogEntries = kaleoLogPersistence.findByKITI_T(
				kaleoInstanceTokenId, logType.name());
		}

		if (!kaleoLogEntries.isEmpty()) {
			return kaleoLogEntries.get(0);
		}

		throw new NoSuchLogException();
	}

}