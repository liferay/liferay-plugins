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

import com.liferay.portal.kernel.workflow.DefaultWorkflowInstance;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;

/**
 * <a href="WorkflowInstanceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceImpl extends DefaultWorkflowInstance {

	public WorkflowInstanceImpl(Token token) {
		ProcessInstance processInstance = token.getProcessInstance();
		ProcessDefinition processDefinition =
			processInstance.getProcessDefinition();

		setEndDate(token.getEnd());
		setStartDate(token.getStart());
		setState(token.getNode().getName());

		ContextInstance contextInstance = processInstance.getContextInstance();

		Map<String, Object> variables = contextInstance.getVariables(token);

		if (variables == null) {
			variables = new HashMap<String, Object>();
		}

		Map<String, Serializable> workflowContext =
			new HashMap<String, Serializable>();

		Iterator<Map.Entry<String, Object>> itr =
			variables.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Object> entry = itr.next();

			workflowContext.put(entry.getKey(), (Serializable)entry.getValue());
		}

		setWorkflowContext(Collections.unmodifiableMap(workflowContext));

		setWorkflowDefinitionName(processDefinition.getName());
		setWorkflowDefinitionVersion(processDefinition.getVersion());
		setWorkflowInstanceId(token.getId());
	}

}