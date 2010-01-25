/*
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.workflow.jbpm.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowInstanceImpl;
import com.liferay.portal.kernel.workflow.WorkflowTaskImpl;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="WorkflowObjectUtil.java.html}"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class WorkflowObjectUtil {

	public static WorkflowInstance createWorkflowInstance(Token token) {

		ProcessInstance processInstance = token.getProcessInstance();

		Map<String, Object> context =
			processInstance.getContextInstance().getVariables(token);

		if (context == null) {
			context = Collections.EMPTY_MAP;
		}

		context = Collections.unmodifiableMap(context);

		Date endDate = token.getEnd();
		Date startDate = token.getStart();
		String state = token.getNode().getName();

		ProcessDefinition processDefinition =
			processInstance.getProcessDefinition();
		String workflowDefinitionName = processDefinition.getName();
		int workflowDefinitionVersion = processDefinition.getVersion();

		long workflowInstanceId = token.getId();

		return new WorkflowInstanceImpl(
			workflowInstanceId, context, endDate, startDate,
			state, workflowDefinitionName, workflowDefinitionVersion);
	}

	public static WorkflowTask createWorkflowTask(TaskInstance taskInstance) {
		ProcessInstance processInstance = taskInstance.getProcessInstance();

		Set<PooledActor> pooledActors = taskInstance.getPooledActors();

		long assigneeRoleId = 0;
		if ((pooledActors != null) && !pooledActors.isEmpty()) {
			PooledActor pooledActor = pooledActors.iterator().next();

			assigneeRoleId = GetterUtil.getLong(pooledActor.getActorId());
		}

		long assigneeUserId = GetterUtil.getLong(taskInstance.getActorId());
		boolean asynchronous = !taskInstance.isBlocking();
		Map<String, Object> optionalAttributes = taskInstance.getVariables();
		Date completionDate = taskInstance.getEnd();
		Date createDate = taskInstance.getCreate();
		String description = taskInstance.getDescription();
		Date dueDate = taskInstance.getDueDate();
		String name = taskInstance.getName();
		long workflowTaskId = taskInstance.getId();


		ProcessDefinition processDefinition =
			processInstance.getProcessDefinition();

		long workflowDefinitionId = processDefinition.getId();
		String workflowDefinitionName = processDefinition.getName();
		int workflowDefinitionVersion = processDefinition.getVersion();

		Token token = taskInstance.getToken();
		long workflowInstanceId = token.getId();

		return new WorkflowTaskImpl(
			workflowTaskId, workflowInstanceId, assigneeRoleId, assigneeUserId,
			asynchronous, completionDate, createDate, description,
			dueDate, name, optionalAttributes, workflowDefinitionId,
			workflowDefinitionName, workflowDefinitionVersion);
	}
}
