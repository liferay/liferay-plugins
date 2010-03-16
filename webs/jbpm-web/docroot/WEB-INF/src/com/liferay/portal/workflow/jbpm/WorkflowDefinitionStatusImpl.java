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

import org.jbpm.graph.def.ProcessDefinition;

/**
 * <a href="WorkflowDefinitionStatusImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Marcellus Tavares
 */
public class WorkflowDefinitionStatusImpl {

	public WorkflowDefinitionStatusImpl() {
	}

	public WorkflowDefinitionStatusImpl(
		boolean active, ProcessDefinition processDefinition){

		_active = active;
		_processDefinition = processDefinition;
	}

	public ProcessDefinition getProcessDefinition(){
		return _processDefinition;
	}

	public long getWorkflowDefinitionStatusId(){
		return _workflowDefinitionStatusId;
	}

	public boolean isActive(){
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setProcessDefinition(ProcessDefinition processDefinition) {
		_processDefinition = processDefinition;
	}

	public void setWorkflowDefinitionStatusId(long workflowDefinitionStatusId){
		_workflowDefinitionStatusId = workflowDefinitionStatusId;
	}

	private boolean _active;
	private ProcessDefinition _processDefinition;
	private long _workflowDefinitionStatusId;

}