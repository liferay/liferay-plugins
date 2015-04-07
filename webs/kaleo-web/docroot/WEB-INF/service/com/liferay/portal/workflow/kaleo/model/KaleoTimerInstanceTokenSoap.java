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
public class KaleoTimerInstanceTokenSoap implements Serializable {
	public static KaleoTimerInstanceTokenSoap toSoapModel(
		KaleoTimerInstanceToken model) {
		KaleoTimerInstanceTokenSoap soapModel = new KaleoTimerInstanceTokenSoap();

		soapModel.setKaleoTimerInstanceTokenId(model.getKaleoTimerInstanceTokenId());
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
		soapModel.setKaleoTimerId(model.getKaleoTimerId());
		soapModel.setKaleoTimerName(model.getKaleoTimerName());
		soapModel.setBlocking(model.getBlocking());
		soapModel.setCompletionUserId(model.getCompletionUserId());
		soapModel.setCompleted(model.getCompleted());
		soapModel.setCompletionDate(model.getCompletionDate());
		soapModel.setWorkflowContext(model.getWorkflowContext());

		return soapModel;
	}

	public static KaleoTimerInstanceTokenSoap[] toSoapModels(
		KaleoTimerInstanceToken[] models) {
		KaleoTimerInstanceTokenSoap[] soapModels = new KaleoTimerInstanceTokenSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoTimerInstanceTokenSoap[][] toSoapModels(
		KaleoTimerInstanceToken[][] models) {
		KaleoTimerInstanceTokenSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KaleoTimerInstanceTokenSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoTimerInstanceTokenSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoTimerInstanceTokenSoap[] toSoapModels(
		List<KaleoTimerInstanceToken> models) {
		List<KaleoTimerInstanceTokenSoap> soapModels = new ArrayList<KaleoTimerInstanceTokenSoap>(models.size());

		for (KaleoTimerInstanceToken model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoTimerInstanceTokenSoap[soapModels.size()]);
	}

	public KaleoTimerInstanceTokenSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoTimerInstanceTokenId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoTimerInstanceTokenId(pk);
	}

	public long getKaleoTimerInstanceTokenId() {
		return _kaleoTimerInstanceTokenId;
	}

	public void setKaleoTimerInstanceTokenId(long kaleoTimerInstanceTokenId) {
		_kaleoTimerInstanceTokenId = kaleoTimerInstanceTokenId;
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

	public long getKaleoTimerId() {
		return _kaleoTimerId;
	}

	public void setKaleoTimerId(long kaleoTimerId) {
		_kaleoTimerId = kaleoTimerId;
	}

	public String getKaleoTimerName() {
		return _kaleoTimerName;
	}

	public void setKaleoTimerName(String kaleoTimerName) {
		_kaleoTimerName = kaleoTimerName;
	}

	public boolean getBlocking() {
		return _blocking;
	}

	public boolean isBlocking() {
		return _blocking;
	}

	public void setBlocking(boolean blocking) {
		_blocking = blocking;
	}

	public long getCompletionUserId() {
		return _completionUserId;
	}

	public void setCompletionUserId(long completionUserId) {
		_completionUserId = completionUserId;
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

	public Date getCompletionDate() {
		return _completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		_completionDate = completionDate;
	}

	public String getWorkflowContext() {
		return _workflowContext;
	}

	public void setWorkflowContext(String workflowContext) {
		_workflowContext = workflowContext;
	}

	private long _kaleoTimerInstanceTokenId;
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
	private long _kaleoTimerId;
	private String _kaleoTimerName;
	private boolean _blocking;
	private long _completionUserId;
	private boolean _completed;
	private Date _completionDate;
	private String _workflowContext;
}