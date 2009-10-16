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
 * <a href="WorkflowLogSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowLogSoap implements Serializable {
	public static WorkflowLogSoap toSoapModel(WorkflowLog model) {
		WorkflowLogSoap soapModel = new WorkflowLogSoap();

		soapModel.setWorkflowLogId(model.getWorkflowLogId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setWorkflowDefinitionId(model.getWorkflowDefinitionId());
		soapModel.setWorkflowInstanceId(model.getWorkflowInstanceId());
		soapModel.setWorkflowTaskId(model.getWorkflowTaskId());
		soapModel.setLogEntityType(model.getLogEntityType());
		soapModel.setDescription(model.getDescription());
		soapModel.setActivityName(model.getActivityName());
		soapModel.setOldState(model.getOldState());
		soapModel.setNewState(model.getNewState());
		soapModel.setType(model.getType());
		soapModel.setComment(model.getComment());

		return soapModel;
	}

	public static WorkflowLogSoap[] toSoapModels(WorkflowLog[] models) {
		WorkflowLogSoap[] soapModels = new WorkflowLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WorkflowLogSoap[][] toSoapModels(WorkflowLog[][] models) {
		WorkflowLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WorkflowLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WorkflowLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WorkflowLogSoap[] toSoapModels(List<WorkflowLog> models) {
		List<WorkflowLogSoap> soapModels = new ArrayList<WorkflowLogSoap>(models.size());

		for (WorkflowLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WorkflowLogSoap[soapModels.size()]);
	}

	public WorkflowLogSoap() {
	}

	public long getPrimaryKey() {
		return _workflowLogId;
	}

	public void setPrimaryKey(long pk) {
		setWorkflowLogId(pk);
	}

	public long getWorkflowLogId() {
		return _workflowLogId;
	}

	public void setWorkflowLogId(long workflowLogId) {
		_workflowLogId = workflowLogId;
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

	public long getWorkflowTaskId() {
		return _workflowTaskId;
	}

	public void setWorkflowTaskId(long workflowTaskId) {
		_workflowTaskId = workflowTaskId;
	}

	public int getLogEntityType() {
		return _logEntityType;
	}

	public void setLogEntityType(int logEntityType) {
		_logEntityType = logEntityType;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getActivityName() {
		return _activityName;
	}

	public void setActivityName(String activityName) {
		_activityName = activityName;
	}

	public String getOldState() {
		return _oldState;
	}

	public void setOldState(String oldState) {
		_oldState = oldState;
	}

	public String getNewState() {
		return _newState;
	}

	public void setNewState(String newState) {
		_newState = newState;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	private long _workflowLogId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _workflowDefinitionId;
	private long _workflowInstanceId;
	private long _workflowTaskId;
	private int _logEntityType;
	private String _description;
	private String _activityName;
	private String _oldState;
	private String _newState;
	private int _type;
	private String _comment;
}