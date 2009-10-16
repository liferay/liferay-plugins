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
 * <a href="WorkflowTaskSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskSoap implements Serializable {
	public static WorkflowTaskSoap toSoapModel(WorkflowTask model) {
		WorkflowTaskSoap soapModel = new WorkflowTaskSoap();

		soapModel.setWorkflowTaskId(model.getWorkflowTaskId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setFriendlyId(model.getFriendlyId());
		soapModel.setWorkflowDefinitionId(model.getWorkflowDefinitionId());
		soapModel.setWorkflowInstanceId(model.getWorkflowInstanceId());
		soapModel.setMetaName(model.getMetaName());
		soapModel.setRelation(model.getRelation());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setCompletionDate(model.getCompletionDate());
		soapModel.setCompleted(model.getCompleted());
		soapModel.setState(model.getState());
		soapModel.setPriority(model.getPriority());
		soapModel.setAssigneeUserId(model.getAssigneeUserId());
		soapModel.setAssigneeUserName(model.getAssigneeUserName());
		soapModel.setAssignedGroupName(model.getAssignedGroupName());
		soapModel.setRoleId(model.getRoleId());

		return soapModel;
	}

	public static WorkflowTaskSoap[] toSoapModels(WorkflowTask[] models) {
		WorkflowTaskSoap[] soapModels = new WorkflowTaskSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WorkflowTaskSoap[][] toSoapModels(WorkflowTask[][] models) {
		WorkflowTaskSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WorkflowTaskSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WorkflowTaskSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WorkflowTaskSoap[] toSoapModels(List<WorkflowTask> models) {
		List<WorkflowTaskSoap> soapModels = new ArrayList<WorkflowTaskSoap>(models.size());

		for (WorkflowTask model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WorkflowTaskSoap[soapModels.size()]);
	}

	public WorkflowTaskSoap() {
	}

	public long getPrimaryKey() {
		return _workflowTaskId;
	}

	public void setPrimaryKey(long pk) {
		setWorkflowTaskId(pk);
	}

	public long getWorkflowTaskId() {
		return _workflowTaskId;
	}

	public void setWorkflowTaskId(long workflowTaskId) {
		_workflowTaskId = workflowTaskId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
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

	public long getWorkflowInstanceId() {
		return _workflowInstanceId;
	}

	public void setWorkflowInstanceId(long workflowInstanceId) {
		_workflowInstanceId = workflowInstanceId;
	}

	public String getMetaName() {
		return _metaName;
	}

	public void setMetaName(String metaName) {
		_metaName = metaName;
	}

	public String getRelation() {
		return _relation;
	}

	public void setRelation(String relation) {
		_relation = relation;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public Date getCompletionDate() {
		return _completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		_completionDate = completionDate;
	}

	public boolean getCompleted() {
		return _completed;
	}

	public boolean isCompleted() {
		return _completed;
	}

	public void setCompleted(boolean completed) {
		_completed = completed;
	}

	public int getState() {
		return _state;
	}

	public void setState(int state) {
		_state = state;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	public long getAssigneeUserId() {
		return _assigneeUserId;
	}

	public void setAssigneeUserId(long assigneeUserId) {
		_assigneeUserId = assigneeUserId;
	}

	public String getAssigneeUserName() {
		return _assigneeUserName;
	}

	public void setAssigneeUserName(String assigneeUserName) {
		_assigneeUserName = assigneeUserName;
	}

	public String getAssignedGroupName() {
		return _assignedGroupName;
	}

	public void setAssignedGroupName(String assignedGroupName) {
		_assignedGroupName = assignedGroupName;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	private long _workflowTaskId;
	private long _companyId;
	private Date _createDate;
	private String _friendlyId;
	private long _workflowDefinitionId;
	private long _workflowInstanceId;
	private String _metaName;
	private String _relation;
	private Date _dueDate;
	private Date _completionDate;
	private boolean _completed;
	private int _state;
	private int _priority;
	private long _assigneeUserId;
	private String _assigneeUserName;
	private String _assignedGroupName;
	private long _roleId;
}