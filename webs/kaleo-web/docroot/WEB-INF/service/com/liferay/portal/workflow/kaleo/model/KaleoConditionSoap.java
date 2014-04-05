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
public class KaleoConditionSoap implements Serializable {
	public static KaleoConditionSoap toSoapModel(KaleoCondition model) {
		KaleoConditionSoap soapModel = new KaleoConditionSoap();

		soapModel.setKaleoConditionId(model.getKaleoConditionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKaleoDefinitionId(model.getKaleoDefinitionId());
		soapModel.setKaleoNodeId(model.getKaleoNodeId());
		soapModel.setScript(model.getScript());
		soapModel.setScriptLanguage(model.getScriptLanguage());
		soapModel.setScriptRequiredContexts(model.getScriptRequiredContexts());

		return soapModel;
	}

	public static KaleoConditionSoap[] toSoapModels(KaleoCondition[] models) {
		KaleoConditionSoap[] soapModels = new KaleoConditionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoConditionSoap[][] toSoapModels(KaleoCondition[][] models) {
		KaleoConditionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KaleoConditionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoConditionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoConditionSoap[] toSoapModels(List<KaleoCondition> models) {
		List<KaleoConditionSoap> soapModels = new ArrayList<KaleoConditionSoap>(models.size());

		for (KaleoCondition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoConditionSoap[soapModels.size()]);
	}

	public KaleoConditionSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoConditionId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoConditionId(pk);
	}

	public long getKaleoConditionId() {
		return _kaleoConditionId;
	}

	public void setKaleoConditionId(long kaleoConditionId) {
		_kaleoConditionId = kaleoConditionId;
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

	public long getKaleoNodeId() {
		return _kaleoNodeId;
	}

	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoNodeId = kaleoNodeId;
	}

	public String getScript() {
		return _script;
	}

	public void setScript(String script) {
		_script = script;
	}

	public String getScriptLanguage() {
		return _scriptLanguage;
	}

	public void setScriptLanguage(String scriptLanguage) {
		_scriptLanguage = scriptLanguage;
	}

	public String getScriptRequiredContexts() {
		return _scriptRequiredContexts;
	}

	public void setScriptRequiredContexts(String scriptRequiredContexts) {
		_scriptRequiredContexts = scriptRequiredContexts;
	}

	private long _kaleoConditionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoNodeId;
	private String _script;
	private String _scriptLanguage;
	private String _scriptRequiredContexts;
}