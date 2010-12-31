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

import org.jbpm.graph.def.ProcessDefinition;

/**
 * @author Marcellus Tavares
 */
public class WorkflowDefinitionExtensionImpl {

	public WorkflowDefinitionExtensionImpl() {
	}

	public WorkflowDefinitionExtensionImpl(
		ProcessDefinition processDefinition, String title, boolean active) {

		_processDefinition = processDefinition;
		_title = title;
		_active = active;
	}

	public ProcessDefinition getProcessDefinition() {
		return _processDefinition;
	}

	public String getTitle() {
		return _title;
	}

	public long getWorkflowDefinitionExtensionId() {
		return _workflowDefinitionExtensionId;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setProcessDefinition(ProcessDefinition processDefinition) {
		_processDefinition = processDefinition;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setWorkflowDefinitionExtensionId(
		long workflowDefinitionExtensionId) {

		_workflowDefinitionExtensionId = workflowDefinitionExtensionId;
	}

	private boolean _active;
	private String _title;
	private ProcessDefinition _processDefinition;
	private long _workflowDefinitionExtensionId;

}