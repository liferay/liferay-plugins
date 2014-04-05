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

package com.liferay.portal.workflow.kaleo.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.DefaultWorkflowDefinition;
import com.liferay.portal.kernel.workflow.DefaultWorkflowInstance;
import com.liferay.portal.kernel.workflow.DefaultWorkflowLog;
import com.liferay.portal.kernel.workflow.DefaultWorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskAssignee;
import com.liferay.portal.model.Role;
import com.liferay.portal.workflow.kaleo.export.DefinitionExporterUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalServiceUtil;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class WorkflowModelUtil {

	public static WorkflowDefinition toWorkflowDefinition(
		KaleoDefinition kaleoDefinition) {

		DefaultWorkflowDefinition defaultWorkflowDefinition =
			new DefaultWorkflowDefinition();

		defaultWorkflowDefinition.setActive(kaleoDefinition.getActive());

		String content = kaleoDefinition.getContent();

		if (Validator.isNull(content)) {
			try {
				content = DefinitionExporterUtil.export(
					kaleoDefinition.getKaleoDefinitionId());

				kaleoDefinition.setContent(content);

				KaleoDefinitionLocalServiceUtil.updateKaleoDefinition(
					kaleoDefinition);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to export definition to string", e);
				}
			}
		}

		defaultWorkflowDefinition.setContent(content);

		defaultWorkflowDefinition.setName(kaleoDefinition.getName());
		defaultWorkflowDefinition.setTitle(kaleoDefinition.getTitle());
		defaultWorkflowDefinition.setVersion(kaleoDefinition.getVersion());

		return defaultWorkflowDefinition;
	}

	public static WorkflowInstance toWorkflowInstance(
			KaleoInstance kaleoInstance, KaleoInstanceToken kaleoInstanceToken)
		throws PortalException, SystemException {

		return toWorkflowInstance(kaleoInstance, kaleoInstanceToken, null);
	}

	public static WorkflowInstance toWorkflowInstance(
			KaleoInstance kaleoInstance, KaleoInstanceToken kaleoInstanceToken,
			Map<String, Serializable> workflowContext)
		throws PortalException, SystemException {

		DefaultWorkflowInstance defaultWorkflowInstance =
			new DefaultWorkflowInstance();

		defaultWorkflowInstance.setEndDate(kaleoInstance.getCompletionDate());
		defaultWorkflowInstance.setStartDate(kaleoInstance.getCreateDate());
		defaultWorkflowInstance.setState(
			kaleoInstanceToken.getCurrentKaleoNode().getName());

		if (workflowContext != null) {
			defaultWorkflowInstance.setWorkflowContext(workflowContext);
		}
		else {
			defaultWorkflowInstance.setWorkflowContext(
				WorkflowContextUtil.convert(
					kaleoInstance.getWorkflowContext()));
		}

		defaultWorkflowInstance.setWorkflowDefinitionName(
			kaleoInstance.getKaleoDefinitionName());
		defaultWorkflowInstance.setWorkflowDefinitionVersion(
			kaleoInstance.getKaleoDefinitionVersion());
		defaultWorkflowInstance.setWorkflowInstanceId(
			kaleoInstance.getKaleoInstanceId());

		return defaultWorkflowInstance;
	}

	public static WorkflowLog toWorkflowLog(KaleoLog kaleoLog) {
		DefaultWorkflowLog defaultWorkflowLog = new DefaultWorkflowLog();

		defaultWorkflowLog.setAuditUserId(kaleoLog.getUserId());
		defaultWorkflowLog.setComment(kaleoLog.getComment());
		defaultWorkflowLog.setCreateDate(kaleoLog.getCreateDate());
		defaultWorkflowLog.setPreviousState(
			kaleoLog.getPreviousKaleoNodeName());

		long previousAssigneeClassPK = kaleoLog.getPreviousAssigneeClassPK();

		if (previousAssigneeClassPK > 0) {
			String previousAssigneeClassName =
				kaleoLog.getPreviousAssigneeClassName();

			if (previousAssigneeClassName.equals(Role.class.getName())) {
				defaultWorkflowLog.setPreviousRoleId(previousAssigneeClassPK);
			}
			else {
				defaultWorkflowLog.setPreviousUserId(previousAssigneeClassPK);
			}
		}

		long currentAssigneeClassPK = kaleoLog.getCurrentAssigneeClassPK();

		if (currentAssigneeClassPK > 0) {
			String currentAssigneeClassName =
				kaleoLog.getCurrentAssigneeClassName();

			if (currentAssigneeClassName.equals(Role.class.getName())) {
				defaultWorkflowLog.setRoleId(currentAssigneeClassPK);
			}
			else {
				defaultWorkflowLog.setUserId(currentAssigneeClassPK);
			}
		}

		defaultWorkflowLog.setState(kaleoLog.getKaleoNodeName());
		defaultWorkflowLog.setType(KaleoLogUtil.convert(kaleoLog.getType()));
		defaultWorkflowLog.setWorkflowLogId(kaleoLog.getKaleoLogId());
		defaultWorkflowLog.setWorkflowTaskId(
			kaleoLog.getKaleoTaskInstanceTokenId());

		return defaultWorkflowLog;
	}

	public static WorkflowTask toWorkflowTask(
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			Map<String, Serializable> workflowContext)
		throws PortalException, SystemException {

		DefaultWorkflowTask defaultWorkflowTask = new DefaultWorkflowTask();

		defaultWorkflowTask.setCreateDate(
			kaleoTaskInstanceToken.getCreateDate());
		defaultWorkflowTask.setCompletionDate(
			kaleoTaskInstanceToken.getCompletionDate());
		defaultWorkflowTask.setDescription(
			kaleoTaskInstanceToken.getKaleoTask().getDescription());
		defaultWorkflowTask.setDueDate(kaleoTaskInstanceToken.getDueDate());
		defaultWorkflowTask.setName(
			kaleoTaskInstanceToken.getKaleoTask().getName());

		if (workflowContext != null) {
			defaultWorkflowTask.setOptionalAttributes(workflowContext);
		}
		else {
			defaultWorkflowTask.setOptionalAttributes(
				WorkflowContextUtil.convert(
					kaleoTaskInstanceToken.getWorkflowContext()));
		}

		KaleoInstanceToken kaleoInstanceToken =
			kaleoTaskInstanceToken.getKaleoInstanceToken();
		KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

		defaultWorkflowTask.setWorkflowDefinitionId(
			kaleoInstance.getKaleoDefinitionId());
		defaultWorkflowTask.setWorkflowDefinitionName(
			kaleoInstance.getKaleoDefinitionName());
		defaultWorkflowTask.setWorkflowDefinitionVersion(
			kaleoInstance.getKaleoDefinitionVersion());
		defaultWorkflowTask.setWorkflowInstanceId(
			kaleoInstance.getKaleoInstanceId());

		List<WorkflowTaskAssignee> workflowTaskAssignees =
			KaleoTaskAssignmentInstanceUtil.getWorkflowTaskAssignees(
				kaleoTaskInstanceToken);

		defaultWorkflowTask.setWorkflowTaskAssignees(workflowTaskAssignees);

		defaultWorkflowTask.setWorkflowTaskId(
			kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());

		return defaultWorkflowTask;
	}

	private static Log _log = LogFactoryUtil.getLog(WorkflowModelUtil.class);

}