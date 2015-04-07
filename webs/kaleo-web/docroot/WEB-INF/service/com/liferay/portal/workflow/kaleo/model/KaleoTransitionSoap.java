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
public class KaleoTransitionSoap implements Serializable {
	public static KaleoTransitionSoap toSoapModel(KaleoTransition model) {
		KaleoTransitionSoap soapModel = new KaleoTransitionSoap();

		soapModel.setKaleoTransitionId(model.getKaleoTransitionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKaleoDefinitionId(model.getKaleoDefinitionId());
		soapModel.setKaleoNodeId(model.getKaleoNodeId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setSourceKaleoNodeId(model.getSourceKaleoNodeId());
		soapModel.setSourceKaleoNodeName(model.getSourceKaleoNodeName());
		soapModel.setTargetKaleoNodeId(model.getTargetKaleoNodeId());
		soapModel.setTargetKaleoNodeName(model.getTargetKaleoNodeName());
		soapModel.setDefaultTransition(model.getDefaultTransition());

		return soapModel;
	}

	public static KaleoTransitionSoap[] toSoapModels(KaleoTransition[] models) {
		KaleoTransitionSoap[] soapModels = new KaleoTransitionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoTransitionSoap[][] toSoapModels(
		KaleoTransition[][] models) {
		KaleoTransitionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KaleoTransitionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoTransitionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoTransitionSoap[] toSoapModels(
		List<KaleoTransition> models) {
		List<KaleoTransitionSoap> soapModels = new ArrayList<KaleoTransitionSoap>(models.size());

		for (KaleoTransition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoTransitionSoap[soapModels.size()]);
	}

	public KaleoTransitionSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoTransitionId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoTransitionId(pk);
	}

	public long getKaleoTransitionId() {
		return _kaleoTransitionId;
	}

	public void setKaleoTransitionId(long kaleoTransitionId) {
		_kaleoTransitionId = kaleoTransitionId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getSourceKaleoNodeId() {
		return _sourceKaleoNodeId;
	}

	public void setSourceKaleoNodeId(long sourceKaleoNodeId) {
		_sourceKaleoNodeId = sourceKaleoNodeId;
	}

	public String getSourceKaleoNodeName() {
		return _sourceKaleoNodeName;
	}

	public void setSourceKaleoNodeName(String sourceKaleoNodeName) {
		_sourceKaleoNodeName = sourceKaleoNodeName;
	}

	public long getTargetKaleoNodeId() {
		return _targetKaleoNodeId;
	}

	public void setTargetKaleoNodeId(long targetKaleoNodeId) {
		_targetKaleoNodeId = targetKaleoNodeId;
	}

	public String getTargetKaleoNodeName() {
		return _targetKaleoNodeName;
	}

	public void setTargetKaleoNodeName(String targetKaleoNodeName) {
		_targetKaleoNodeName = targetKaleoNodeName;
	}

	public boolean getDefaultTransition() {
		return _defaultTransition;
	}

	public boolean isDefaultTransition() {
		return _defaultTransition;
	}

	public void setDefaultTransition(boolean defaultTransition) {
		_defaultTransition = defaultTransition;
	}

	private long _kaleoTransitionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoNodeId;
	private String _name;
	private String _description;
	private long _sourceKaleoNodeId;
	private String _sourceKaleoNodeName;
	private long _targetKaleoNodeId;
	private String _targetKaleoNodeName;
	private boolean _defaultTransition;
}