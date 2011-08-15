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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.DefaultWorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskAssignee;
import com.liferay.portal.workflow.jbpm.util.AssigneeUtil;
import com.liferay.portal.workflow.jbpm.util.WorkflowContextUtil;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskImpl extends DefaultWorkflowTask {

	public WorkflowTaskImpl(TaskInstance taskInstance)
		throws PortalException, SystemException {

		ProcessInstance processInstance = taskInstance.getProcessInstance();
		ProcessDefinition processDefinition =
			processInstance.getProcessDefinition();
		Token token = taskInstance.getToken();

		setAsynchronous(!taskInstance.isBlocking());
		setCompletionDate(taskInstance.getEnd());
		setCreateDate(taskInstance.getCreate());
		setDescription(taskInstance.getDescription());
		setDueDate(taskInstance.getDueDate());
		setName(taskInstance.getName());
		setOptionalAttributes(
			WorkflowContextUtil.convertToMap(taskInstance.getVariables()));
		setWorkflowDefinitionId(processDefinition.getId());
		setWorkflowDefinitionName(processDefinition.getName());
		setWorkflowDefinitionVersion(processDefinition.getVersion());
		setWorkflowInstanceId(token.getId());

		ContextInstance contextInstance = processInstance.getContextInstance();

		long companyId = GetterUtil.getLong(
			(String)contextInstance.getVariable("companyId"));
		long groupId = GetterUtil.getLong(
			(String)contextInstance.getVariable("groupId"));

		List<Assignee> assignees = AssigneeUtil.getAssignees(
			companyId, groupId, taskInstance.getActorId(),
			taskInstance.getPooledActors());

		setWorkflowTaskAssignees(toWorkflowTaskAssignees(assignees));
		setWorkflowTaskId(taskInstance.getId());
	}

	protected List<WorkflowTaskAssignee> toWorkflowTaskAssignees(
		List<Assignee> assignees) {

		List<WorkflowTaskAssignee> workflowTaskAssignees =
			new ArrayList<WorkflowTaskAssignee>(assignees.size());

		for (Assignee assignee : assignees) {
			WorkflowTaskAssignee workflowTaskAssignee =
				new WorkflowTaskAssignee(
					assignee.getAssigneeClassName(),
					assignee.getAssigneeClassPK());

			workflowTaskAssignees.add(workflowTaskAssignee);
		}

		return workflowTaskAssignees;
	}

}