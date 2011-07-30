/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class KaleoTaskFormSoap implements Serializable {
	public static KaleoTaskFormSoap toSoapModel(KaleoTaskForm model) {
		KaleoTaskFormSoap soapModel = new KaleoTaskFormSoap();

		soapModel.setKaleoTaskFormId(model.getKaleoTaskFormId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKaleoDefinitionId(model.getKaleoDefinitionId());
		soapModel.setKaleoTaskId(model.getKaleoTaskId());
		soapModel.setDescription(model.getDescription());
		soapModel.setFormTemplateId(model.getFormTemplateId());

		return soapModel;
	}

	public static KaleoTaskFormSoap[] toSoapModels(KaleoTaskForm[] models) {
		KaleoTaskFormSoap[] soapModels = new KaleoTaskFormSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoTaskFormSoap[][] toSoapModels(KaleoTaskForm[][] models) {
		KaleoTaskFormSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KaleoTaskFormSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoTaskFormSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoTaskFormSoap[] toSoapModels(List<KaleoTaskForm> models) {
		List<KaleoTaskFormSoap> soapModels = new ArrayList<KaleoTaskFormSoap>(models.size());

		for (KaleoTaskForm model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoTaskFormSoap[soapModels.size()]);
	}

	public KaleoTaskFormSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoTaskFormId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoTaskFormId(pk);
	}

	public long getKaleoTaskFormId() {
		return _kaleoTaskFormId;
	}

	public void setKaleoTaskFormId(long kaleoTaskFormId) {
		_kaleoTaskFormId = kaleoTaskFormId;
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

	public long getKaleoTaskId() {
		return _kaleoTaskId;
	}

	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTaskId = kaleoTaskId;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getFormTemplateId() {
		return _formTemplateId;
	}

	public void setFormTemplateId(long formTemplateId) {
		_formTemplateId = formTemplateId;
	}

	private long _kaleoTaskFormId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoTaskId;
	private String _description;
	private long _formTemplateId;
}