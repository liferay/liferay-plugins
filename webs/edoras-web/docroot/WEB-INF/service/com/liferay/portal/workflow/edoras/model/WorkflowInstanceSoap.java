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

package com.liferay.portal.workflow.edoras.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="WorkflowInstanceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceSoap implements Serializable {
	public static WorkflowInstanceSoap toSoapModel(WorkflowInstance model) {
		WorkflowInstanceSoap soapModel = new WorkflowInstanceSoap();

		soapModel.setWorkflowInstanceId(model.getWorkflowInstanceId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSetupId(model.getSetupId());
		soapModel.setFriendlyId(model.getFriendlyId());
		soapModel.setWorkflowDefinitionId(model.getWorkflowDefinitionId());
		soapModel.setParentWorkflowInstanceId(model.getParentWorkflowInstanceId());
		soapModel.setWorkflowDefinitionName(model.getWorkflowDefinitionName());
		soapModel.setWorkflowDefinitionVersion(model.getWorkflowDefinitionVersion());
		soapModel.setRelationClassName(model.getRelationClassName());
		soapModel.setRelationClassPK(model.getRelationClassPK());
		soapModel.setAttributes(model.getAttributes());
		soapModel.setNestedWorkflowDefinitionIds(model.getNestedWorkflowDefinitionIds());
		soapModel.setNestedWorkflowDefinitionVersions(model.getNestedWorkflowDefinitionVersions());
		soapModel.setNestedRelatedElements(model.getNestedRelatedElements());
		soapModel.setCurrentElementName(model.getCurrentElementName());
		soapModel.setRelatedElementName(model.getRelatedElementName());
		soapModel.setFinished(model.getFinished());
		soapModel.setFinishedDated(model.getFinishedDated());
		soapModel.setActive(model.getActive());

		return soapModel;
	}

	public static WorkflowInstanceSoap[] toSoapModels(WorkflowInstance[] models) {
		WorkflowInstanceSoap[] soapModels = new WorkflowInstanceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WorkflowInstanceSoap[][] toSoapModels(
		WorkflowInstance[][] models) {
		WorkflowInstanceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WorkflowInstanceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WorkflowInstanceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WorkflowInstanceSoap[] toSoapModels(
		List<WorkflowInstance> models) {
		List<WorkflowInstanceSoap> soapModels = new ArrayList<WorkflowInstanceSoap>(models.size());

		for (WorkflowInstance model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WorkflowInstanceSoap[soapModels.size()]);
	}

	public WorkflowInstanceSoap() {
	}

	public long getPrimaryKey() {
		return _workflowInstanceId;
	}

	public void setPrimaryKey(long pk) {
		setWorkflowInstanceId(pk);
	}

	public long getWorkflowInstanceId() {
		return _workflowInstanceId;
	}

	public void setWorkflowInstanceId(long workflowInstanceId) {
		_workflowInstanceId = workflowInstanceId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getSetupId() {
		return _setupId;
	}

	public void setSetupId(String setupId) {
		_setupId = setupId;
	}

	public String getFriendlyId() {
		return _friendlyId;
	}

	public void setFriendlyId(String friendlyId) {
		_friendlyId = friendlyId;
	}

	public long getWorkflowDefinitionId() {
		return _workflowDefinitionId;
	}

	public void setWorkflowDefinitionId(long workflowDefinitionId) {
		_workflowDefinitionId = workflowDefinitionId;
	}

	public long getParentWorkflowInstanceId() {
		return _parentWorkflowInstanceId;
	}

	public void setParentWorkflowInstanceId(long parentWorkflowInstanceId) {
		_parentWorkflowInstanceId = parentWorkflowInstanceId;
	}

	public String getWorkflowDefinitionName() {
		return _workflowDefinitionName;
	}

	public void setWorkflowDefinitionName(String workflowDefinitionName) {
		_workflowDefinitionName = workflowDefinitionName;
	}

	public int getWorkflowDefinitionVersion() {
		return _workflowDefinitionVersion;
	}

	public void setWorkflowDefinitionVersion(int workflowDefinitionVersion) {
		_workflowDefinitionVersion = workflowDefinitionVersion;
	}

	public String getRelationClassName() {
		return _relationClassName;
	}

	public void setRelationClassName(String relationClassName) {
		_relationClassName = relationClassName;
	}

	public long getRelationClassPK() {
		return _relationClassPK;
	}

	public void setRelationClassPK(long relationClassPK) {
		_relationClassPK = relationClassPK;
	}

	public String getAttributes() {
		return _attributes;
	}

	public void setAttributes(String attributes) {
		_attributes = attributes;
	}

	public String getNestedWorkflowDefinitionIds() {
		return _nestedWorkflowDefinitionIds;
	}

	public void setNestedWorkflowDefinitionIds(
		String nestedWorkflowDefinitionIds) {
		_nestedWorkflowDefinitionIds = nestedWorkflowDefinitionIds;
	}

	public String getNestedWorkflowDefinitionVersions() {
		return _nestedWorkflowDefinitionVersions;
	}

	public void setNestedWorkflowDefinitionVersions(
		String nestedWorkflowDefinitionVersions) {
		_nestedWorkflowDefinitionVersions = nestedWorkflowDefinitionVersions;
	}

	public String getNestedRelatedElements() {
		return _nestedRelatedElements;
	}

	public void setNestedRelatedElements(String nestedRelatedElements) {
		_nestedRelatedElements = nestedRelatedElements;
	}

	public String getCurrentElementName() {
		return _currentElementName;
	}

	public void setCurrentElementName(String currentElementName) {
		_currentElementName = currentElementName;
	}

	public String getRelatedElementName() {
		return _relatedElementName;
	}

	public void setRelatedElementName(String relatedElementName) {
		_relatedElementName = relatedElementName;
	}

	public boolean getFinished() {
		return _finished;
	}

	public boolean isFinished() {
		return _finished;
	}

	public void setFinished(boolean finished) {
		_finished = finished;
	}

	public Date getFinishedDated() {
		return _finishedDated;
	}

	public void setFinishedDated(Date finishedDated) {
		_finishedDated = finishedDated;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private long _workflowInstanceId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _setupId;
	private String _friendlyId;
	private long _workflowDefinitionId;
	private long _parentWorkflowInstanceId;
	private String _workflowDefinitionName;
	private int _workflowDefinitionVersion;
	private String _relationClassName;
	private long _relationClassPK;
	private String _attributes;
	private String _nestedWorkflowDefinitionIds;
	private String _nestedWorkflowDefinitionVersions;
	private String _nestedRelatedElements;
	private String _currentElementName;
	private String _relatedElementName;
	private boolean _finished;
	private Date _finishedDated;
	private boolean _active;
}