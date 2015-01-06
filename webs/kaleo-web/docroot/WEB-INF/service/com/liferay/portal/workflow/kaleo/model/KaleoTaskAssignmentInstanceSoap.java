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
public class KaleoTaskAssignmentInstanceSoap implements Serializable {
	public static KaleoTaskAssignmentInstanceSoap toSoapModel(
		KaleoTaskAssignmentInstance model) {
		KaleoTaskAssignmentInstanceSoap soapModel = new KaleoTaskAssignmentInstanceSoap();

		soapModel.setKaleoTaskAssignmentInstanceId(model.getKaleoTaskAssignmentInstanceId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKaleoDefinitionId(model.getKaleoDefinitionId());
		soapModel.setKaleoInstanceId(model.getKaleoInstanceId());
		soapModel.setKaleoInstanceTokenId(model.getKaleoInstanceTokenId());
		soapModel.setKaleoTaskInstanceTokenId(model.getKaleoTaskInstanceTokenId());
		soapModel.setKaleoTaskId(model.getKaleoTaskId());
		soapModel.setKaleoTaskName(model.getKaleoTaskName());
		soapModel.setAssigneeClassName(model.getAssigneeClassName());
		soapModel.setAssigneeClassPK(model.getAssigneeClassPK());
		soapModel.setCompleted(model.getCompleted());
		soapModel.setCompletionDate(model.getCompletionDate());

		return soapModel;
	}

	public static KaleoTaskAssignmentInstanceSoap[] toSoapModels(
		KaleoTaskAssignmentInstance[] models) {
		KaleoTaskAssignmentInstanceSoap[] soapModels = new KaleoTaskAssignmentInstanceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoTaskAssignmentInstanceSoap[][] toSoapModels(
		KaleoTaskAssignmentInstance[][] models) {
		KaleoTaskAssignmentInstanceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KaleoTaskAssignmentInstanceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoTaskAssignmentInstanceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoTaskAssignmentInstanceSoap[] toSoapModels(
		List<KaleoTaskAssignmentInstance> models) {
		List<KaleoTaskAssignmentInstanceSoap> soapModels = new ArrayList<KaleoTaskAssignmentInstanceSoap>(models.size());

		for (KaleoTaskAssignmentInstance model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoTaskAssignmentInstanceSoap[soapModels.size()]);
	}

	public KaleoTaskAssignmentInstanceSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoTaskAssignmentInstanceId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoTaskAssignmentInstanceId(pk);
	}

	public long getKaleoTaskAssignmentInstanceId() {
		return _kaleoTaskAssignmentInstanceId;
	}

	public void setKaleoTaskAssignmentInstanceId(
		long kaleoTaskAssignmentInstanceId) {
		_kaleoTaskAssignmentInstanceId = kaleoTaskAssignmentInstanceId;
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

	public long getKaleoTaskId() {
		return _kaleoTaskId;
	}

	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTaskId = kaleoTaskId;
	}

	public String getKaleoTaskName() {
		return _kaleoTaskName;
	}

	public void setKaleoTaskName(String kaleoTaskName) {
		_kaleoTaskName = kaleoTaskName;
	}

	public String getAssigneeClassName() {
		return _assigneeClassName;
	}

	public void setAssigneeClassName(String assigneeClassName) {
		_assigneeClassName = assigneeClassName;
	}

	public long getAssigneeClassPK() {
		return _assigneeClassPK;
	}

	public void setAssigneeClassPK(long assigneeClassPK) {
		_assigneeClassPK = assigneeClassPK;
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

	private long _kaleoTaskAssignmentInstanceId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoInstanceId;
	private long _kaleoInstanceTokenId;
	private long _kaleoTaskInstanceTokenId;
	private long _kaleoTaskId;
	private String _kaleoTaskName;
	private String _assigneeClassName;
	private long _assigneeClassPK;
	private boolean _completed;
	private Date _completionDate;
}