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
import com.liferay.portal.workflow.edoras.model.impl.WorkflowInstanceImpl;

import java.util.List;

import org.edorasframework.process.api.ProcessSystemUtil;
import org.edorasframework.process.api.entity.ProcessInstance;
import org.edorasframework.process.api.model.ProcessModel;
import org.edorasframework.process.api.service.ProcessService;
import org.edorasframework.process.api.session.ProcessSession;
import org.edorasframework.process.core.entity.DefaultProcessInstance;

/**
 * <a href="WorkflowInstanceBridge.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowInstanceBridge
	extends DefaultProcessInstance
	implements WorkflowEntity, WorkflowEntityBridge<WorkflowInstance> {

	public WorkflowInstanceBridge() {
	}

	public WorkflowInstanceBridge(WorkflowInstance workflowInstance) {
		this(workflowInstance, null, true);
	}

	public WorkflowInstanceBridge(
		WorkflowInstance workflowInstance,
		WorkflowInstanceBridge parentWorkflowInstanceBridge,
		boolean loadChildren) {

		initializeFromReading(
			workflowInstance, parentWorkflowInstanceBridge, loadChildren);
	}

	public long getWorkflowDefinitionId() {
		if (_worflowDefinitionId != 0) {
			return _worflowDefinitionId;
		}

		ProcessSession processSession = ProcessSystemUtil.getCurrentSession();

		ProcessService processService = processSession.getService();

		ProcessModel processModel = processService.getProcessModel(
			getProcessModelId(), getProcessModelVersion());

		_worflowDefinitionId = processModel.getRepositoryPK();

		return _worflowDefinitionId;
	}

	public WorkflowInstance initializeForInsert() {
		prePersist();
		unwrap();
		transferPropertiesForSaving();
		postPersist();

		return _workflowInstance;
	}

	public WorkflowInstance initializeForUpdate() {
		preUpdate();
		transferPropertiesForSaving();
		postPersist();

		return _workflowInstance;
	}

	public void initializeFromReading(WorkflowInstance workflowInstance) {
		initializeFromReading(workflowInstance, null, true);
	}

	public void initializeFromReading(
		WorkflowInstance workflowInstance,
		WorkflowInstanceBridge parentWorkflowInstanceBridge,
		boolean loadChildren) {

		_workflowInstance = workflowInstance;

		ProcessSession processSession = ProcessSystemUtil.getCurrentSession();

		if (processSession == null) {
			throw new IllegalArgumentException(
				"No process session while reading workflow entities");
		}

		Class<?> setupId = WorkflowEntityBridgeUtil.getSetupClassForName(
			workflowInstance.getSetupId());

		ProcessService processService = processSession.getService();

		ProcessModel processModel = processService.getProcessModel(
			workflowInstance.getWorkflowDefinitionId());

		Long relationClassPK = workflowInstance.getRelationClassPK();

		if (relationClassPK == 0) {
			relationClassPK = null;
		}

		setId(workflowInstance.getPrimaryKey());
		setTenantId(workflowInstance.getCompanyId());
		//setUserId(workflowInstance.getUserId());
		//setUserName(workflowInstance.getUserName());
		setCreatedAt(workflowInstance.getCreateDate());
		setModifiedAt(workflowInstance.getModifiedDate());
		setSetupId(setupId);
		setBusinessId(workflowInstance.getFriendlyId());
		setProcessModelId(processModel.getProcessModelId());
		setProcessModelVersion(processModel.getProcessModelVersion());
		setParent(parentWorkflowInstanceBridge);
		setRelationType(workflowInstance.getRelationClassName());
		setRelationId(relationClassPK);
		setXmlAttributeMap(workflowInstance.getAttributes());
		setNestedProcessModelIds(
			workflowInstance.getNestedWorkflowDefinitionIds());
		setNestedProcessVersions(
			workflowInstance.getNestedWorkflowDefinitionVersions());
		setNestedRelatedElements(workflowInstance.getNestedRelatedElements());
		setCurrentElement(workflowInstance.getCurrentElementName());
		setRelatedElement(workflowInstance.getRelatedElementName());
		setFinished(workflowInstance.getFinished());
		setFinishedAt(workflowInstance.getFinishedDated());
		setActive(workflowInstance.getActive());

		postLoad();

		if (loadChildren) {
			List<ProcessInstance> processInstances = (List<ProcessInstance>)
				WorkflowEntityBridgeUtil.wrapWorkflowInstances(
					workflowInstance.getChildren(), this, loadChildren);

			setChildren(processInstances);
		}
	}

	public boolean setNew(boolean isNew) {
		WorkflowInstance workflowInstance = unwrap();

		return workflowInstance.setNew(isNew);
	}

	public WorkflowInstance unwrap() {
		if (_workflowInstance == null) {
			_workflowInstance = new WorkflowInstanceImpl();
		}

		return _workflowInstance;
	}

	public void transferPropertiesForSaving() {
		long parentWorkflowInstanceId = 0;

		ProcessInstance parentProcessInstance = getParent();

		if (parentProcessInstance != null) {
			parentWorkflowInstanceId = parentProcessInstance.getPrimaryKey();
		}

		Long relationId = getRelationId();

		if (relationId == null) {
			relationId = Long.valueOf(0);
		}

		_workflowInstance.setPrimaryKey(getPrimaryKey());
		_workflowInstance.setCompanyId(getTenantId());
		//_workflowInstance.setUserId(getUserId());
		//_workflowInstance.setUserName(getUserName());
		_workflowInstance.setCreateDate(getCreatedAt());
		_workflowInstance.setModifiedDate(getModifiedAt());
		_workflowInstance.setSetupId(getSetupId().getName());
		_workflowInstance.setFriendlyId(getBusinessId());
		_workflowInstance.setWorkflowDefinitionId(getWorkflowDefinitionId());
		_workflowInstance.setWorkflowDefinitionName(getProcessModelId());
		_workflowInstance.setWorkflowDefinitionVersion(
			getProcessModelVersion());
		_workflowInstance.setParentWorkflowInstanceId(parentWorkflowInstanceId);
		_workflowInstance.setRelationClassName(getRelationType());
		_workflowInstance.setRelationClassPK(relationId);
		_workflowInstance.setAttributes(getXmlAttributeMap());
		_workflowInstance.setNestedWorkflowDefinitionIds(
			getNestedProcessModelIds());
		_workflowInstance.setNestedWorkflowDefinitionVersions(
			getNestedProcessVersions());
		_workflowInstance.setNestedRelatedElements(getNestedRelatedElements());
		_workflowInstance.setCurrentElementName(getCurrentElement());
		_workflowInstance.setRelatedElementName(getRelatedElement());
		_workflowInstance.setFinished(isFinished());
		_workflowInstance.setFinishedDated(getFinishedAt());
		_workflowInstance.setActive(isActive());
	}

	private transient long _worflowDefinitionId;
	private transient WorkflowInstance _workflowInstance;

}