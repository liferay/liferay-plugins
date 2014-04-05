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
public class KaleoInstanceTokenSoap implements Serializable {
	public static KaleoInstanceTokenSoap toSoapModel(KaleoInstanceToken model) {
		KaleoInstanceTokenSoap soapModel = new KaleoInstanceTokenSoap();

		soapModel.setKaleoInstanceTokenId(model.getKaleoInstanceTokenId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKaleoDefinitionId(model.getKaleoDefinitionId());
		soapModel.setKaleoInstanceId(model.getKaleoInstanceId());
		soapModel.setParentKaleoInstanceTokenId(model.getParentKaleoInstanceTokenId());
		soapModel.setCurrentKaleoNodeId(model.getCurrentKaleoNodeId());
		soapModel.setCurrentKaleoNodeName(model.getCurrentKaleoNodeName());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setCompleted(model.getCompleted());
		soapModel.setCompletionDate(model.getCompletionDate());

		return soapModel;
	}

	public static KaleoInstanceTokenSoap[] toSoapModels(
		KaleoInstanceToken[] models) {
		KaleoInstanceTokenSoap[] soapModels = new KaleoInstanceTokenSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoInstanceTokenSoap[][] toSoapModels(
		KaleoInstanceToken[][] models) {
		KaleoInstanceTokenSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KaleoInstanceTokenSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoInstanceTokenSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoInstanceTokenSoap[] toSoapModels(
		List<KaleoInstanceToken> models) {
		List<KaleoInstanceTokenSoap> soapModels = new ArrayList<KaleoInstanceTokenSoap>(models.size());

		for (KaleoInstanceToken model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoInstanceTokenSoap[soapModels.size()]);
	}

	public KaleoInstanceTokenSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoInstanceTokenId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoInstanceTokenId(pk);
	}

	public long getKaleoInstanceTokenId() {
		return _kaleoInstanceTokenId;
	}

	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoInstanceTokenId = kaleoInstanceTokenId;
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

	public long getParentKaleoInstanceTokenId() {
		return _parentKaleoInstanceTokenId;
	}

	public void setParentKaleoInstanceTokenId(long parentKaleoInstanceTokenId) {
		_parentKaleoInstanceTokenId = parentKaleoInstanceTokenId;
	}

	public long getCurrentKaleoNodeId() {
		return _currentKaleoNodeId;
	}

	public void setCurrentKaleoNodeId(long currentKaleoNodeId) {
		_currentKaleoNodeId = currentKaleoNodeId;
	}

	public String getCurrentKaleoNodeName() {
		return _currentKaleoNodeName;
	}

	public void setCurrentKaleoNodeName(String currentKaleoNodeName) {
		_currentKaleoNodeName = currentKaleoNodeName;
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

	private long _kaleoInstanceTokenId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoInstanceId;
	private long _parentKaleoInstanceTokenId;
	private long _currentKaleoNodeId;
	private String _currentKaleoNodeName;
	private String _className;
	private long _classPK;
	private boolean _completed;
	private Date _completionDate;
}