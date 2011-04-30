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
public class HRProjectBillingRateSoap implements Serializable {
	public static HRProjectBillingRateSoap toSoapModel(
		HRProjectBillingRate model) {
		HRProjectBillingRateSoap soapModel = new HRProjectBillingRateSoap();

		soapModel.setHrProjectBillingRateId(model.getHrProjectBillingRateId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHrProjectId(model.getHrProjectId());
		soapModel.setHrProjectRoleId(model.getHrProjectRoleId());
		soapModel.setRate(model.getRate());

		return soapModel;
	}

	public static HRProjectBillingRateSoap[] toSoapModels(
		HRProjectBillingRate[] models) {
		HRProjectBillingRateSoap[] soapModels = new HRProjectBillingRateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRProjectBillingRateSoap[][] toSoapModels(
		HRProjectBillingRate[][] models) {
		HRProjectBillingRateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRProjectBillingRateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRProjectBillingRateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRProjectBillingRateSoap[] toSoapModels(
		List<HRProjectBillingRate> models) {
		List<HRProjectBillingRateSoap> soapModels = new ArrayList<HRProjectBillingRateSoap>(models.size());

		for (HRProjectBillingRate model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRProjectBillingRateSoap[soapModels.size()]);
	}

	public HRProjectBillingRateSoap() {
	}

	public long getPrimaryKey() {
		return _hrProjectBillingRateId;
	}

	public void setPrimaryKey(long pk) {
		setHrProjectBillingRateId(pk);
	}

	public long getHrProjectBillingRateId() {
		return _hrProjectBillingRateId;
	}

	public void setHrProjectBillingRateId(long hrProjectBillingRateId) {
		_hrProjectBillingRateId = hrProjectBillingRateId;
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

	public long getHrProjectId() {
		return _hrProjectId;
	}

	public void setHrProjectId(long hrProjectId) {
		_hrProjectId = hrProjectId;
	}

	public long getHrProjectRoleId() {
		return _hrProjectRoleId;
	}

	public void setHrProjectRoleId(long hrProjectRoleId) {
		_hrProjectRoleId = hrProjectRoleId;
	}

	public double getRate() {
		return _rate;
	}

	public void setRate(double rate) {
		_rate = rate;
	}

	private long _hrProjectBillingRateId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrProjectId;
	private long _hrProjectRoleId;
	private double _rate;
}