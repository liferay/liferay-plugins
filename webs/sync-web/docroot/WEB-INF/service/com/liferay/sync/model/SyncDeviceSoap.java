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

package com.liferay.sync.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.sync.service.http.SyncDeviceServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.sync.service.http.SyncDeviceServiceSoap
 * @generated
 */
@ProviderType
public class SyncDeviceSoap implements Serializable {
	public static SyncDeviceSoap toSoapModel(SyncDevice model) {
		SyncDeviceSoap soapModel = new SyncDeviceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSyncDeviceId(model.getSyncDeviceId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setType(model.getType());
		soapModel.setBuildNumber(model.getBuildNumber());
		soapModel.setFeatureSet(model.getFeatureSet());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static SyncDeviceSoap[] toSoapModels(SyncDevice[] models) {
		SyncDeviceSoap[] soapModels = new SyncDeviceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SyncDeviceSoap[][] toSoapModels(SyncDevice[][] models) {
		SyncDeviceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SyncDeviceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SyncDeviceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SyncDeviceSoap[] toSoapModels(List<SyncDevice> models) {
		List<SyncDeviceSoap> soapModels = new ArrayList<SyncDeviceSoap>(models.size());

		for (SyncDevice model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SyncDeviceSoap[soapModels.size()]);
	}

	public SyncDeviceSoap() {
	}

	public long getPrimaryKey() {
		return _syncDeviceId;
	}

	public void setPrimaryKey(long pk) {
		setSyncDeviceId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSyncDeviceId() {
		return _syncDeviceId;
	}

	public void setSyncDeviceId(long syncDeviceId) {
		_syncDeviceId = syncDeviceId;
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

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public long getBuildNumber() {
		return _buildNumber;
	}

	public void setBuildNumber(long buildNumber) {
		_buildNumber = buildNumber;
	}

	public int getFeatureSet() {
		return _featureSet;
	}

	public void setFeatureSet(int featureSet) {
		_featureSet = featureSet;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private String _uuid;
	private long _syncDeviceId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _type;
	private long _buildNumber;
	private int _featureSet;
	private int _status;
}