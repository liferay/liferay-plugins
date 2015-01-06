/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class KaleoLogSoap implements Serializable {
	public static KaleoLogSoap toSoapModel(KaleoLog model) {
		KaleoLogSoap soapModel = new KaleoLogSoap();

		soapModel.setKaleoLogId(model.getKaleoLogId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKaleoClassName(model.getKaleoClassName());
		soapModel.setKaleoClassPK(model.getKaleoClassPK());
		soapModel.setKaleoDefinitionId(model.getKaleoDefinitionId());
		soapModel.setKaleoInstanceId(model.getKaleoInstanceId());
		soapModel.setKaleoInstanceTokenId(model.getKaleoInstanceTokenId());
		soapModel.setKaleoTaskInstanceTokenId(model.getKaleoTaskInstanceTokenId());
		soapModel.setKaleoNodeName(model.getKaleoNodeName());
		soapModel.setTerminalKaleoNode(model.getTerminalKaleoNode());
		soapModel.setKaleoActionId(model.getKaleoActionId());
		soapModel.setKaleoActionName(model.getKaleoActionName());
		soapModel.setKaleoActionDescription(model.getKaleoActionDescription());
		soapModel.setPreviousKaleoNodeId(model.getPreviousKaleoNodeId());
		soapModel.setPreviousKaleoNodeName(model.getPreviousKaleoNodeName());
		soapModel.setPreviousAssigneeClassName(model.getPreviousAssigneeClassName());
		soapModel.setPreviousAssigneeClassPK(model.getPreviousAssigneeClassPK());
		soapModel.setCurrentAssigneeClassName(model.getCurrentAssigneeClassName());
		soapModel.setCurrentAssigneeClassPK(model.getCurrentAssigneeClassPK());
		soapModel.setType(model.getType());
		soapModel.setComment(model.getComment());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setDuration(model.getDuration());
		soapModel.setWorkflowContext(model.getWorkflowContext());

		return soapModel;
	}

	public static KaleoLogSoap[] toSoapModels(KaleoLog[] models) {
		KaleoLogSoap[] soapModels = new KaleoLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoLogSoap[][] toSoapModels(KaleoLog[][] models) {
		KaleoLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KaleoLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoLogSoap[] toSoapModels(List<KaleoLog> models) {
		List<KaleoLogSoap> soapModels = new ArrayList<KaleoLogSoap>(models.size());

		for (KaleoLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoLogSoap[soapModels.size()]);
	}

	public KaleoLogSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoLogId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoLogId(pk);
	}

	public long getKaleoLogId() {
		return _kaleoLogId;
	}

	public void setKaleoLogId(long kaleoLogId) {
		_kaleoLogId = kaleoLogId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public String getKaleoClassName() {
		return _kaleoClassName;
	}

	public void setKaleoClassName(String kaleoClassName) {
		_kaleoClassName = kaleoClassName;
	}

	public long getKaleoClassPK() {
		return _kaleoClassPK;
	}

	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoClassPK = kaleoClassPK;
	}

	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinitionId = kaleoDefinitionId;
	}

	public long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstanceId = kaleoInstanceId;
	}

	public long getKaleoInstanceTokenId() {
		return _kaleoInstanceTokenId;
	}

	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoInstanceTokenId = kaleoInstanceTokenId;
	}

	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskInstanceTokenId;
	}

	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTaskInstanceTokenId = kaleoTaskInstanceTokenId;
	}

	public String getKaleoNodeName() {
		return _kaleoNodeName;
	}

	public void setKaleoNodeName(String kaleoNodeName) {
		_kaleoNodeName = kaleoNodeName;
	}

	public boolean getTerminalKaleoNode() {
		return _terminalKaleoNode;
	}

	public boolean isTerminalKaleoNode() {
		return _terminalKaleoNode;
	}

	public void setTerminalKaleoNode(boolean terminalKaleoNode) {
		_terminalKaleoNode = terminalKaleoNode;
	}

	public long getKaleoActionId() {
		return _kaleoActionId;
	}

	public void setKaleoActionId(long kaleoActionId) {
		_kaleoActionId = kaleoActionId;
	}

	public String getKaleoActionName() {
		return _kaleoActionName;
	}

	public void setKaleoActionName(String kaleoActionName) {
		_kaleoActionName = kaleoActionName;
	}

	public String getKaleoActionDescription() {
		return _kaleoActionDescription;
	}

	public void setKaleoActionDescription(String kaleoActionDescription) {
		_kaleoActionDescription = kaleoActionDescription;
	}

	public long getPreviousKaleoNodeId() {
		return _previousKaleoNodeId;
	}

	public void setPreviousKaleoNodeId(long previousKaleoNodeId) {
		_previousKaleoNodeId = previousKaleoNodeId;
	}

	public String getPreviousKaleoNodeName() {
		return _previousKaleoNodeName;
	}

	public void setPreviousKaleoNodeName(String previousKaleoNodeName) {
		_previousKaleoNodeName = previousKaleoNodeName;
	}

	public String getPreviousAssigneeClassName() {
		return _previousAssigneeClassName;
	}

	public void setPreviousAssigneeClassName(String previousAssigneeClassName) {
		_previousAssigneeClassName = previousAssigneeClassName;
	}

	public long getPreviousAssigneeClassPK() {
		return _previousAssigneeClassPK;
	}

	public void setPreviousAssigneeClassPK(long previousAssigneeClassPK) {
		_previousAssigneeClassPK = previousAssigneeClassPK;
	}

	public String getCurrentAssigneeClassName() {
		return _currentAssigneeClassName;
	}

	public void setCurrentAssigneeClassName(String currentAssigneeClassName) {
		_currentAssigneeClassName = currentAssigneeClassName;
	}

	public long getCurrentAssigneeClassPK() {
		return _currentAssigneeClassPK;
	}

	public void setCurrentAssigneeClassPK(long currentAssigneeClassPK) {
		_currentAssigneeClassPK = currentAssigneeClassPK;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public long getDuration() {
		return _duration;
	}

	public void setDuration(long duration) {
		_duration = duration;
	}

	public String getWorkflowContext() {
		return _workflowContext;
	}

	public void setWorkflowContext(String workflowContext) {
		_workflowContext = workflowContext;
	}

	private long _kaleoLogId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _kaleoClassName;
	private long _kaleoClassPK;
	private long _kaleoDefinitionId;
	private long _kaleoInstanceId;
	private long _kaleoInstanceTokenId;
	private long _kaleoTaskInstanceTokenId;
	private String _kaleoNodeName;
	private boolean _terminalKaleoNode;
	private long _kaleoActionId;
	private String _kaleoActionName;
	private String _kaleoActionDescription;
	private long _previousKaleoNodeId;
	private String _previousKaleoNodeName;
	private String _previousAssigneeClassName;
	private long _previousAssigneeClassPK;
	private String _currentAssigneeClassName;
	private long _currentAssigneeClassPK;
	private String _type;
	private String _comment;
	private Date _startDate;
	private Date _endDate;
	private long _duration;
	private String _workflowContext;
}