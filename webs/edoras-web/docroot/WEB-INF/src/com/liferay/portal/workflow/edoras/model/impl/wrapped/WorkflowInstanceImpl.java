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
import org.edorasframework.process.api.session.ProcessSession;
import org.edorasframework.process.core.entity.DefaultProcessInstance;


/**
 * <a href="WorkflowInstanceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowInstanceImpl extends DefaultProcessInstance
	implements WorkflowEntity {

	public WorkflowInstanceImpl() {

	}

	public WorkflowInstanceImpl(WorkflowInstance wrappedInstance) {
		_wrappedInstance = wrappedInstance;
	}

	public WorkflowInstanceImpl(
		WorkflowInstanceImpl parentInstance, WorkflowInstance wrappedInstance,
		boolean loadChildren) {

		_wrappedInstance = wrappedInstance;
		initializeFromReading(wrappedInstance, parentInstance, loadChildren);
	}

	public long getWorkflowDefinitionId() {
		if (_worflowDefinitionId == 0) {
			ProcessSession session = ProcessSystemUtil.getCurrentSession();
			ProcessModel model =
				session.getService().getProcessModel(
					getProcessModelId(), getProcessModelVersion());
			_worflowDefinitionId = model.getRepositoryPK();
		}

		return _worflowDefinitionId;
	}

	public WorkflowInstance initializeForInsert() {
		prePersist();
		unwrap();
		transferProperties();
		postPersist();
		return _wrappedInstance;
	}

	public WorkflowInstance initializeForUpdate() {
		preUpdate();
		transferProperties();
		postPersist();
		return _wrappedInstance;
	}

	public void initializeFromReading(
		WorkflowInstance instance, WorkflowInstanceImpl parentInstance,
		boolean loadChildren) {
		ProcessSession session = ProcessSystemUtil.getCurrentSession();

		setId(instance.getPrimaryKey());
		setTenantId(instance.getCompanyId());
		setSetupId(WorkflowEntityTransferUtil.getSetupClassForName(instance.getSetupId()));
		setBusinessId(instance.getFriendlyId());
		setRelationType(instance.getRelationClassName());
		long relationPK = instance.getRelationClassPK();
		setRelationId(relationPK == 0 ? null : relationPK);
		ProcessModel model =
			session.getService().getProcessModel(
				instance.getWorkflowDefinitionId());
		setProcessModelId(model.getProcessModelId());
		setProcessModelVersion(model.getProcessModelVersion());
		setNestedProcessModelIds(instance.getNestedWorkflowDefinitionIds());
		setNestedProcessVersions(instance.getNestedWorkflowDefinitionVersions());
		setNestedRelatedElements(instance.getNestedRelatedElements());
		setCreatedAt(instance.getCreateDate());
		setModifiedAt(instance.getModifiedDate());
		setFinishedAt(instance.getFinishedDated());
		setParent(parentInstance);
		setCurrentElement(instance.getCurrentElementName());
		setRelatedElement(instance.getRelatedElementName());
		setFinished(instance.getFinished());
		setActive(instance.getActive());
		setXmlAttributeMap(instance.getAttributes());

		postLoad();

		if (loadChildren) {
			setChildren((List<ProcessInstance>) WorkflowEntityTransferUtil.transferLoadedObjects(
				instance.getChildren(), this, loadChildren));
		}
	}

	public boolean setNew(boolean isNew) {
		return unwrap().setNew(isNew);
	}

	protected void transferProperties() {
		_wrappedInstance.setPrimaryKey(getPrimaryKey());
		_wrappedInstance.setCompanyId(getTenantId());
		_wrappedInstance.setSetupId(getSetupId().getName());
		_wrappedInstance.setFriendlyId(getBusinessId());
		_wrappedInstance.setRelationClassName(getRelationType());
		Long relationId = getRelationId();
		if (relationId == null) {
			_wrappedInstance.setRelationClassPK(0);
		}
		else {
			_wrappedInstance.setRelationClassPK(getRelationId());
		}
		_wrappedInstance.setWorkflowDefinitionId(getWorkflowDefinitionId());
		_wrappedInstance.setNestedWorkflowDefinitionIds(getNestedProcessModelIds());
		_wrappedInstance.setNestedWorkflowDefinitionVersions(getNestedProcessVersions());
		_wrappedInstance.setNestedRelatedElements(getNestedRelatedElements());
		_wrappedInstance.setCreateDate(getCreatedAt());
		_wrappedInstance.setModifiedDate(getModifiedAt());
		_wrappedInstance.setFinishedDated(getFinishedAt());

		ProcessInstance parent = getParent();
		if (parent == null) {
			_wrappedInstance.setParentWorkflowInstanceId(0);
		}
		else {
			_wrappedInstance.setParentWorkflowInstanceId(parent.getPrimaryKey());
		}

		_wrappedInstance.setCurrentElementName(getCurrentElement());
		_wrappedInstance.setRelatedElementName(getRelatedElement());
		_wrappedInstance.setFinished(isFinished());
		_wrappedInstance.setActive(isActive());
		_wrappedInstance.setAttributes(getXmlAttributeMap());
	}

	public WorkflowInstance unwrap() {
		if (_wrappedInstance == null) {
			_wrappedInstance =
				new com.liferay.portal.workflow.edoras.model.impl.WorkflowInstanceImpl();
		}
		return _wrappedInstance;
	}

	private transient long _worflowDefinitionId;
	private transient WorkflowInstance _wrappedInstance;
}