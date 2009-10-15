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

package com.liferay.portal.workflow.edoras.model.impl.wrapped;

import com.liferay.portal.workflow.edoras.model.WorkflowInstance;
import com.liferay.portal.workflow.edoras.model.impl.WorkflowEntity;

import java.util.List;

import org.edorasframework.process.api.ProcessSystemUtil;
import org.edorasframework.process.api.entity.ProcessInstance;
import org.edorasframework.process.api.model.ProcessModel;
import org.edorasframework.process.api.service.ProcessService;
import org.edorasframework.process.api.session.ProcessSession;
import org.edorasframework.process.core.entity.DefaultProcessInstance;

/**
 * <a href="WorkflowInstanceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowInstanceImpl
	extends DefaultProcessInstance implements WorkflowEntity {

	public WorkflowInstanceImpl() {
	}

	public WorkflowInstanceImpl(WorkflowInstance workflowInstance) {
		_workflowInstance = workflowInstance;
	}

	public WorkflowInstanceImpl(
		WorkflowInstanceImpl parentWorkflowInstanceImpl,
		WorkflowInstance workflowInstance, boolean loadChildren) {

		_workflowInstance = workflowInstance;

		initializeFromReading(
			workflowInstance, parentWorkflowInstanceImpl, loadChildren);
	}

	public long getWorkflowDefinitionId() {
		if (_worflowDefinitionId == 0) {
			ProcessSession processSession =
				ProcessSystemUtil.getCurrentSession();

			ProcessService processService = processSession.getService();

			ProcessModel processModel = processService.getProcessModel(
				getProcessModelId(), getProcessModelVersion());

			_worflowDefinitionId = processModel.getRepositoryPK();
		}

		return _worflowDefinitionId;
	}

	public WorkflowInstance initializeForInsert() {
		prePersist();
		unwrap();
		transferProperties();
		postPersist();

		return _workflowInstance;
	}

	public WorkflowInstance initializeForUpdate() {
		preUpdate();
		transferProperties();
		postPersist();

		return _workflowInstance;
	}

	public void initializeFromReading(
		WorkflowInstance workflowInstance,
		WorkflowInstanceImpl parentWorkflowInstanceImpl, boolean loadChildren) {

		ProcessSession processSession = ProcessSystemUtil.getCurrentSession();

		setId(workflowInstance.getPrimaryKey());
		setTenantId(workflowInstance.getCompanyId());

		Class<?> setupId = WorkflowEntityTransferUtil.getSetupClassForName(
			workflowInstance.getSetupId());

		setSetupId(setupId);

		setBusinessId(workflowInstance.getFriendlyId());
		setRelationType(workflowInstance.getRelationClassName());

		long relationPK = workflowInstance.getRelationClassPK();

		if (relationPK == 0) {
			setRelationId(null);
		}
		else {
			setRelationId(relationPK);
		}

		ProcessModel processModel = processSession.getService().getProcessModel(
			workflowInstance.getWorkflowDefinitionId());

		setProcessModelId(processModel.getProcessModelId());
		setProcessModelVersion(processModel.getProcessModelVersion());
		setNestedProcessModelIds(
			workflowInstance.getNestedWorkflowDefinitionIds());
		setNestedProcessVersions(
			workflowInstance.getNestedWorkflowDefinitionVersions());
		setNestedRelatedElements(workflowInstance.getNestedRelatedElements());
		setCreatedAt(workflowInstance.getCreateDate());
		setModifiedAt(workflowInstance.getModifiedDate());
		setFinishedAt(workflowInstance.getFinishedDated());
		setParent(parentWorkflowInstanceImpl);
		setCurrentElement(workflowInstance.getCurrentElementName());
		setRelatedElement(workflowInstance.getRelatedElementName());
		setFinished(workflowInstance.getFinished());
		setActive(workflowInstance.getActive());
		setXmlAttributeMap(workflowInstance.getAttributes());

		postLoad();

		if (loadChildren) {
			List<ProcessInstance> processInstances = (List<ProcessInstance>)
				WorkflowEntityTransferUtil.transferLoadedObjects(
					workflowInstance.getChildren(), this, loadChildren);

			setChildren(processInstances);
		}
	}

	public boolean setNew(boolean isNew) {
		return unwrap().setNew(isNew);
	}

	public WorkflowInstance unwrap() {
		if (_workflowInstance == null) {
			_workflowInstance =
				new com.liferay.portal.workflow.edoras.model.impl.
					WorkflowInstanceImpl();
		}

		return _workflowInstance;
	}

	protected void transferProperties() {
		_workflowInstance.setPrimaryKey(getPrimaryKey());
		_workflowInstance.setCompanyId(getTenantId());
		_workflowInstance.setSetupId(getSetupId().getName());
		_workflowInstance.setFriendlyId(getBusinessId());
		_workflowInstance.setRelationClassName(getRelationType());

		Long relationId = getRelationId();

		if (relationId == null) {
			_workflowInstance.setRelationClassPK(0);
		}
		else {
			_workflowInstance.setRelationClassPK(getRelationId());
		}

		_workflowInstance.setWorkflowDefinitionId(getWorkflowDefinitionId());
		_workflowInstance.setNestedWorkflowDefinitionIds(
			getNestedProcessModelIds());
		_workflowInstance.setNestedWorkflowDefinitionVersions(
			getNestedProcessVersions());
		_workflowInstance.setNestedRelatedElements(getNestedRelatedElements());
		_workflowInstance.setCreateDate(getCreatedAt());
		_workflowInstance.setModifiedDate(getModifiedAt());
		_workflowInstance.setFinishedDated(getFinishedAt());

		ProcessInstance parentProcessInstance = getParent();

		if (parentProcessInstance == null) {
			_workflowInstance.setParentWorkflowInstanceId(0);
		}
		else {
			_workflowInstance.setParentWorkflowInstanceId(
				parentProcessInstance.getPrimaryKey());
		}

		_workflowInstance.setCurrentElementName(getCurrentElement());
		_workflowInstance.setRelatedElementName(getRelatedElement());
		_workflowInstance.setFinished(isFinished());
		_workflowInstance.setActive(isActive());
		_workflowInstance.setAttributes(getXmlAttributeMap());
	}

	private transient long _worflowDefinitionId;
	private transient WorkflowInstance _workflowInstance;

}