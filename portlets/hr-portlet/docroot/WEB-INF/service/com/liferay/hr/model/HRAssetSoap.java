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

package com.liferay.hr.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Wesley Gong
 * @generated
 */
public class HRAssetSoap implements Serializable {
	public static HRAssetSoap toSoapModel(HRAsset model) {
		HRAssetSoap soapModel = new HRAssetSoap();

		soapModel.setHrAssetId(model.getHrAssetId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHrAssetDefinitionId(model.getHrAssetDefinitionId());
		soapModel.setHrAssetTypeId(model.getHrAssetTypeId());
		soapModel.setSerialNumber(model.getSerialNumber());
		soapModel.setInactiveDate(model.getInactiveDate());

		return soapModel;
	}

	public static HRAssetSoap[] toSoapModels(HRAsset[] models) {
		HRAssetSoap[] soapModels = new HRAssetSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRAssetSoap[][] toSoapModels(HRAsset[][] models) {
		HRAssetSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRAssetSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRAssetSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRAssetSoap[] toSoapModels(List<HRAsset> models) {
		List<HRAssetSoap> soapModels = new ArrayList<HRAssetSoap>(models.size());

		for (HRAsset model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRAssetSoap[soapModels.size()]);
	}

	public HRAssetSoap() {
	}

	public long getPrimaryKey() {
		return _hrAssetId;
	}

	public void setPrimaryKey(long pk) {
		setHrAssetId(pk);
	}

	public long getHrAssetId() {
		return _hrAssetId;
	}

	public void setHrAssetId(long hrAssetId) {
		_hrAssetId = hrAssetId;
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

	public long getHrAssetDefinitionId() {
		return _hrAssetDefinitionId;
	}

	public void setHrAssetDefinitionId(long hrAssetDefinitionId) {
		_hrAssetDefinitionId = hrAssetDefinitionId;
	}

	public long getHrAssetTypeId() {
		return _hrAssetTypeId;
	}

	public void setHrAssetTypeId(long hrAssetTypeId) {
		_hrAssetTypeId = hrAssetTypeId;
	}

	public String getSerialNumber() {
		return _serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		_serialNumber = serialNumber;
	}

	public Date getInactiveDate() {
		return _inactiveDate;
	}

	public void setInactiveDate(Date inactiveDate) {
		_inactiveDate = inactiveDate;
	}

	private long _hrAssetId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrAssetDefinitionId;
	private long _hrAssetTypeId;
	private String _serialNumber;
	private Date _inactiveDate;
}