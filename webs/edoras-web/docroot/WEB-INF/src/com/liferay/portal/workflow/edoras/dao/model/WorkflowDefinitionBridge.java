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

import com.liferay.portal.workflow.edoras.WorkflowManagerUtil;
import com.liferay.portal.workflow.edoras.model.WorkflowDefinition;
import com.liferay.portal.workflow.edoras.model.impl.WorkflowDefinitionImpl;

import org.edorasframework.process.api.service.MutableProcessModelDefinition;
import org.edorasframework.process.core.service.DefaultProcessModelDefinition;

/**
 * <a href="WorkflowDefinitionBridge.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowDefinitionBridge
	extends DefaultProcessModelDefinition
	implements MutableProcessModelDefinition, WorkflowEntity,
			   WorkflowEntityBridge<WorkflowDefinition> {

	public WorkflowDefinitionBridge() {
	}

	public WorkflowDefinitionBridge(WorkflowDefinition workflowDefinition) {
		initializeFromReading(workflowDefinition);
	}

	public WorkflowDefinition initializeForInsert() {
		transferPropertiesForSaving();

		return _workflowDefinition;
	}

	public WorkflowDefinition initializeForUpdate() {
		transferPropertiesForSaving();

		return _workflowDefinition;
	}

	public void initializeFromReading(WorkflowDefinition workflowDefinition) {
		_workflowDefinition = workflowDefinition;

		Long tenantId =
			WorkflowManagerUtil.getTenantId(workflowDefinition.getCompanyId());

		setPrimaryKey(workflowDefinition.getPrimaryKey());
		setTenantId(tenantId);
		//setUserId(workflowDefinition.getUserId());
		//setUserName(workflowDefinition.getUserName());
		//setCreateDate(workflowDefinition.getCreateDate());
		//setModifiedDate(workflowDefinition.getModifiedDate());
		setProcessModelId(workflowDefinition.getName());
		setProcessModelVersion(workflowDefinition.getVersion());
		setModelDesignerVersion(workflowDefinition.getDesignerVersion());
		setProcessModelAsXML(workflowDefinition.getModelXml());
		setGraphicalProcessModelAsXML(workflowDefinition.getGraphicalXml());
		//setPersistent(workflowDefinition.isPersistent());
	}

	public boolean setNew(boolean isNew) {
		WorkflowDefinition workflowDefinition = unwrap();

		return workflowDefinition.setNew(isNew);
	}

	public void transferPropertiesForSaving() {
		unwrap();

		long companyId = WorkflowManagerUtil.getCompanyId(getTenantId());

		_workflowDefinition.setPrimaryKey(getPrimaryKey());
		_workflowDefinition.setCompanyId(companyId);
		//_workflowDefinition.setUserId(getUserId());
		//_workflowDefinition.setUserName(getUserName());
		//_workflowDefinition.setCreateDate(getCreateDate());
		//_workflowDefinition.setModifiedDate(getModifiedDate());
		_workflowDefinition.setName(getProcessModelId());
		_workflowDefinition.setVersion(getProcessModelVersion());
		_workflowDefinition.setDesignerVersion(getModelDesignerVersion());
		_workflowDefinition.setModelXml(getProcessModelAsXML());
		_workflowDefinition.setGraphicalXml(getGraphicalProcessModelAsXML());
		//_workflowDefinition.setPersistent(isPersistent());
	}

	public WorkflowDefinition unwrap() {
		if (_workflowDefinition == null) {
			_workflowDefinition = new WorkflowDefinitionImpl();
		}

		return _workflowDefinition;
	}

	private transient WorkflowDefinition _workflowDefinition;

}