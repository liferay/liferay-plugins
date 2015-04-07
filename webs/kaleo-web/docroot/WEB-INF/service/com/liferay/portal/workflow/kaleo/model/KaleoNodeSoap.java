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
public class KaleoNodeSoap implements Serializable {
	public static KaleoNodeSoap toSoapModel(KaleoNode model) {
		KaleoNodeSoap soapModel = new KaleoNodeSoap();

		soapModel.setKaleoNodeId(model.getKaleoNodeId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKaleoDefinitionId(model.getKaleoDefinitionId());
		soapModel.setName(model.getName());
		soapModel.setMetadata(model.getMetadata());
		soapModel.setDescription(model.getDescription());
		soapModel.setType(model.getType());
		soapModel.setInitial(model.getInitial());
		soapModel.setTerminal(model.getTerminal());

		return soapModel;
	}

	public static KaleoNodeSoap[] toSoapModels(KaleoNode[] models) {
		KaleoNodeSoap[] soapModels = new KaleoNodeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoNodeSoap[][] toSoapModels(KaleoNode[][] models) {
		KaleoNodeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KaleoNodeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoNodeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoNodeSoap[] toSoapModels(List<KaleoNode> models) {
		List<KaleoNodeSoap> soapModels = new ArrayList<KaleoNodeSoap>(models.size());

		for (KaleoNode model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoNodeSoap[soapModels.size()]);
	}

	public KaleoNodeSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoNodeId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoNodeId(pk);
	}

	public long getKaleoNodeId() {
		return _kaleoNodeId;
	}

	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoNodeId = kaleoNodeId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getMetadata() {
		return _metadata;
	}

	public void setMetadata(String metadata) {
		_metadata = metadata;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public boolean getInitial() {
		return _initial;
	}

	public boolean isInitial() {
		return _initial;
	}

	public void setInitial(boolean initial) {
		_initial = initial;
	}

	public boolean getTerminal() {
		return _terminal;
	}

	public boolean isTerminal() {
		return _terminal;
	}

	public void setTerminal(boolean terminal) {
		_terminal = terminal;
	}

	private long _kaleoNodeId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private String _name;
	private String _metadata;
	private String _description;
	private String _type;
	private boolean _initial;
	private boolean _terminal;
}