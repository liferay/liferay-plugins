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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.DefaultWorkflowTask;
import com.liferay.portal.workflow.jbpm.util.WorkflowContextUtil;

import java.util.Set;

import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="WorkflowTaskImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskImpl extends DefaultWorkflowTask {

	public WorkflowTaskImpl(TaskInstance taskInstance) {
		ProcessInstance processInstance = taskInstance.getProcessInstance();
		ProcessDefinition processDefinition =
			processInstance.getProcessDefinition();
		Token token = taskInstance.getToken();

		Set<PooledActor> pooledActors = taskInstance.getPooledActors();

		if ((pooledActors != null) && !pooledActors.isEmpty()) {
			PooledActor pooledActor = pooledActors.iterator().next();

			setAssigneeRoleId(GetterUtil.getLong(pooledActor.getActorId()));
		}

		if (Validator.isEmailAddress(taskInstance.getActorId())) {
			setAssigneeEmailAddress(taskInstance.getActorId());
		}
		else {
			setAssigneeUserId(GetterUtil.getLong(taskInstance.getActorId()));
		}

		setAsynchronous(!taskInstance.isBlocking());
		setCompletionDate(taskInstance.getEnd());
		setCreateDate(taskInstance.getCreate());
		setDescription(taskInstance.getDescription());
		setDueDate(taskInstance.getDueDate());
		setName(taskInstance.getName());
		setOptionalAttributes(
			WorkflowContextUtil.convert(taskInstance.getVariables()));
		setWorkflowDefinitionId(processDefinition.getId());
		setWorkflowDefinitionName(processDefinition.getName());
		setWorkflowDefinitionVersion(processDefinition.getVersion());
		setWorkflowInstanceId(token.getId());
		setWorkflowTaskId(taskInstance.getId());
	}

}