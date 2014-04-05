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
public class KaleoInstanceSoap implements Serializable {
	public static KaleoInstanceSoap toSoapModel(KaleoInstance model) {
		KaleoInstanceSoap soapModel = new KaleoInstanceSoap();

		soapModel.setKaleoInstanceId(model.getKaleoInstanceId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKaleoDefinitionId(model.getKaleoDefinitionId());
		soapModel.setKaleoDefinitionName(model.getKaleoDefinitionName());
		soapModel.setKaleoDefinitionVersion(model.getKaleoDefinitionVersion());
		soapModel.setRootKaleoInstanceTokenId(model.getRootKaleoInstanceTokenId());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setCompleted(model.getCompleted());
		soapModel.setCompletionDate(model.getCompletionDate());
		soapModel.setWorkflowContext(model.getWorkflowContext());

		return soapModel;
	}

	public static KaleoInstanceSoap[] toSoapModels(KaleoInstance[] models) {
		KaleoInstanceSoap[] soapModels = new KaleoInstanceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoInstanceSoap[][] toSoapModels(KaleoInstance[][] models) {
		KaleoInstanceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KaleoInstanceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoInstanceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoInstanceSoap[] toSoapModels(List<KaleoInstance> models) {
		List<KaleoInstanceSoap> soapModels = new ArrayList<KaleoInstanceSoap>(models.size());

		for (KaleoInstance model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoInstanceSoap[soapModels.size()]);
	}

	public KaleoInstanceSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoInstanceId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoInstanceId(pk);
	}

	public long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstanceId = kaleoInstanceId;
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

	public String getKaleoDefinitionName() {
		return _kaleoDefinitionName;
	}

	public void setKaleoDefinitionName(String kaleoDefinitionName) {
		_kaleoDefinitionName = kaleoDefinitionName;
	}

	public int getKaleoDefinitionVersion() {
		return _kaleoDefinitionVersion;
	}

	public void setKaleoDefinitionVersion(int kaleoDefinitionVersion) {
		_kaleoDefinitionVersion = kaleoDefinitionVersion;
	}

	public long getRootKaleoInstanceTokenId() {
		return _rootKaleoInstanceTokenId;
	}

	public void setRootKaleoInstanceTokenId(long rootKaleoInstanceTokenId) {
		_rootKaleoInstanceTokenId = rootKaleoInstanceTokenId;
	}

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
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

	private long _kaleoInstanceId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private String _kaleoDefinitionName;
	private int _kaleoDefinitionVersion;
	private long _rootKaleoInstanceTokenId;
	private String _className;
	private long _classPK;
	private boolean _completed;
	private Date _completionDate;
	private String _workflowContext;
}