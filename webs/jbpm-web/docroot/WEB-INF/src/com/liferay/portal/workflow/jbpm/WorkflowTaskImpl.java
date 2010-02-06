/**
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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.DefaultWorkflowTask;

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

		setAssigneeUserId(GetterUtil.getLong(taskInstance.getActorId()));
		setAsynchronous(!taskInstance.isBlocking());
		setCompletionDate(taskInstance.getEnd());
		setCreateDate(taskInstance.getCreate());
		setDescription(taskInstance.getDescription());
		setDueDate(taskInstance.getDueDate());
		setName(taskInstance.getName());
		setOptionalAttributes(taskInstance.getVariables());
		setWorkflowDefinitionId(processDefinition.getId());
		setWorkflowDefinitionName(processDefinition.getName());
		setWorkflowDefinitionVersion(processDefinition.getVersion());
		setWorkflowInstanceId(token.getId());
		setWorkflowTaskId(taskInstance.getId());
	}

}