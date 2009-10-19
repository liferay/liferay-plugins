/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.edoras.dao.model;

import com.liferay.portal.workflow.edoras.model.WorkflowInstance;
import com.liferay.portal.workflow.edoras.model.WorkflowJob;
import com.liferay.portal.workflow.edoras.model.impl.WorkflowJobImpl;

import org.edorasframework.process.core.job.DefaultProcessJob;

/**
 * <a href="WorkflowJobBridge.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowJobBridge extends DefaultProcessJob
	implements WorkflowEntity, WorkflowEntityBridge<WorkflowJob> {

	public WorkflowJobBridge() {

	}

	public WorkflowJobBridge(WorkflowJob workflowJob) {
		initializeFromReading(workflowJob);
	}

	public long getPrimaryKey() {
		return getId();
	}

	public WorkflowJob initializeForInsert() {
		unwrap();
		transferPropertiesForSaving();

		return _workflowJob;
	}

	public WorkflowJob initializeForUpdate() {
		transferPropertiesForSaving();

		return _workflowJob;
	}

	public void initializeFromReading(WorkflowJob workflowJob) {
		_workflowJob = workflowJob;

		Class<?> setupId = WorkflowEntityBridgeUtil.getSetupClassForName(
			workflowJob.getSetupId());

		WorkflowInstance workflowInstance = workflowJob.getWorkflowInstance();

		WorkflowInstanceBridge workflowInstanceBridge =
			new WorkflowInstanceBridge(workflowInstance);

		setId(workflowJob.getPrimaryKey());
		setTenantId(workflowJob.getCompanyId());
		setCreatedAt(workflowJob.getCreateDate());
		setSetupId(setupId);
		//setWorkflowDefinitionId(workflowJob.getWorkflowDefinitionId());
		setProcessInstance(workflowInstanceBridge);
		setElementName(workflowJob.getElementName());
		setCause(workflowJob.getCause());
		setDueDate(workflowJob.getDueDate());
		setNotBeforeDate(workflowJob.getNotBeforeDate());
		//setExceptionCount(workflowJob.getExceptionCount());

		postLoad();
	}

	public boolean setNew(boolean isNew) {
		WorkflowJob workflowJob = unwrap();

		return workflowJob.setNew(isNew);
	}

	public void setPrimaryKey(long primaryKey) {
		setId(primaryKey);
	}

	public void transferPropertiesForSaving() {
		unwrap();

		_workflowJob.setPrimaryKey(getId());
		_workflowJob.setCompanyId(getTenantId());
		_workflowJob.setCreateDate(getCreatedAt());
		_workflowJob.setSetupId(getSetupId().getName());
		//_workflowJob.setWorkflowDefinitionId(getWorkflowDefinitionId());
		_workflowJob.setWorkflowInstanceId(
			getProcessInstance().getPrimaryKey());
		_workflowJob.setElementName(getElementName());
		_workflowJob.setCause(getCause());
		_workflowJob.setDueDate(getDueDate());
		_workflowJob.setNotBeforeDate(getNotBeforeDate());
		//_workflowJob.setExceptionCount(getExceptionCount());
	}

	public WorkflowJob unwrap() {
		if (_workflowJob == null) {
			_workflowJob = new WorkflowJobImpl();
		}

		return _workflowJob;
	}

	private transient WorkflowJob _workflowJob;

}