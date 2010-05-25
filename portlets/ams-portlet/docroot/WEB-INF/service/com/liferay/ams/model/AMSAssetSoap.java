/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.ams.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="AMSAssetSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * {@link com.liferay.ams.service.http.AMSAssetServiceSoap}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.ams.service.http.AMSAssetServiceSoap
 * @generated
 */
public class AMSAssetSoap implements Serializable {
	public static AMSAssetSoap toSoapModel(AMSAsset model) {
		AMSAssetSoap soapModel = new AMSAssetSoap();

		soapModel.setAmsAssetId(model.getAmsAssetId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAssetDefinitionId(model.getAssetDefinitionId());
		soapModel.setSerialNumber(model.getSerialNumber());
		soapModel.setInactiveDate(model.getInactiveDate());
		soapModel.setActive(model.getActive());

		return soapModel;
	}

	public static AMSAssetSoap[] toSoapModels(AMSAsset[] models) {
		AMSAssetSoap[] soapModels = new AMSAssetSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AMSAssetSoap[][] toSoapModels(AMSAsset[][] models) {
		AMSAssetSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AMSAssetSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AMSAssetSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AMSAssetSoap[] toSoapModels(List<AMSAsset> models) {
		List<AMSAssetSoap> soapModels = new ArrayList<AMSAssetSoap>(models.size());

		for (AMSAsset model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AMSAssetSoap[soapModels.size()]);
	}

	public AMSAssetSoap() {
	}

	public long getPrimaryKey() {
		return _amsAssetId;
	}

	public void setPrimaryKey(long pk) {
		setAmsAssetId(pk);
	}

	public long getAmsAssetId() {
		return _amsAssetId;
	}

	public void setAmsAssetId(long amsAssetId) {
		_amsAssetId = amsAssetId;
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

	public long getAssetDefinitionId() {
		return _assetDefinitionId;
	}

	public void setAssetDefinitionId(long assetDefinitionId) {
		_assetDefinitionId = assetDefinitionId;
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

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private long _amsAssetId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _assetDefinitionId;
	private String _serialNumber;
	private Date _inactiveDate;
	private boolean _active;
}