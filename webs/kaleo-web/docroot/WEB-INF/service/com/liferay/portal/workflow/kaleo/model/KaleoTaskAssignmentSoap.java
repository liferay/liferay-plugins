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
public class KaleoTaskAssignmentSoap implements Serializable {
	public static KaleoTaskAssignmentSoap toSoapModel(KaleoTaskAssignment model) {
		KaleoTaskAssignmentSoap soapModel = new KaleoTaskAssignmentSoap();

		soapModel.setKaleoTaskAssignmentId(model.getKaleoTaskAssignmentId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKaleoClassName(model.getKaleoClassName());
		soapModel.setKaleoClassPK(model.getKaleoClassPK());
		soapModel.setKaleoDefinitionId(model.getKaleoDefinitionId());
		soapModel.setKaleoNodeId(model.getKaleoNodeId());
		soapModel.setAssigneeClassName(model.getAssigneeClassName());
		soapModel.setAssigneeClassPK(model.getAssigneeClassPK());
		soapModel.setAssigneeActionId(model.getAssigneeActionId());
		soapModel.setAssigneeScript(model.getAssigneeScript());
		soapModel.setAssigneeScriptLanguage(model.getAssigneeScriptLanguage());
		soapModel.setAssigneeScriptRequiredContexts(model.getAssigneeScriptRequiredContexts());

		return soapModel;
	}

	public static KaleoTaskAssignmentSoap[] toSoapModels(
		KaleoTaskAssignment[] models) {
		KaleoTaskAssignmentSoap[] soapModels = new KaleoTaskAssignmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoTaskAssignmentSoap[][] toSoapModels(
		KaleoTaskAssignment[][] models) {
		KaleoTaskAssignmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KaleoTaskAssignmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoTaskAssignmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoTaskAssignmentSoap[] toSoapModels(
		List<KaleoTaskAssignment> models) {
		List<KaleoTaskAssignmentSoap> soapModels = new ArrayList<KaleoTaskAssignmentSoap>(models.size());

		for (KaleoTaskAssignment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoTaskAssignmentSoap[soapModels.size()]);
	}

	public KaleoTaskAssignmentSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoTaskAssignmentId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoTaskAssignmentId(pk);
	}

	public long getKaleoTaskAssignmentId() {
		return _kaleoTaskAssignmentId;
	}

	public void setKaleoTaskAssignmentId(long kaleoTaskAssignmentId) {
		_kaleoTaskAssignmentId = kaleoTaskAssignmentId;
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

	public long getKaleoNodeId() {
		return _kaleoNodeId;
	}

	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoNodeId = kaleoNodeId;
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

	public String getAssigneeActionId() {
		return _assigneeActionId;
	}

	public void setAssigneeActionId(String assigneeActionId) {
		_assigneeActionId = assigneeActionId;
	}

	public String getAssigneeScript() {
		return _assigneeScript;
	}

	public void setAssigneeScript(String assigneeScript) {
		_assigneeScript = assigneeScript;
	}

	public String getAssigneeScriptLanguage() {
		return _assigneeScriptLanguage;
	}

	public void setAssigneeScriptLanguage(String assigneeScriptLanguage) {
		_assigneeScriptLanguage = assigneeScriptLanguage;
	}

	public String getAssigneeScriptRequiredContexts() {
		return _assigneeScriptRequiredContexts;
	}

	public void setAssigneeScriptRequiredContexts(
		String assigneeScriptRequiredContexts) {
		_assigneeScriptRequiredContexts = assigneeScriptRequiredContexts;
	}

	private long _kaleoTaskAssignmentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _kaleoClassName;
	private long _kaleoClassPK;
	private long _kaleoDefinitionId;
	private long _kaleoNodeId;
	private String _assigneeClassName;
	private long _assigneeClassPK;
	private String _assigneeActionId;
	private String _assigneeScript;
	private String _assigneeScriptLanguage;
	private String _assigneeScriptRequiredContexts;
}