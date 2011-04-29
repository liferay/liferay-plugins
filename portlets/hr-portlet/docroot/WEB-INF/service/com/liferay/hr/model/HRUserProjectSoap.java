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
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class HRUserProjectSoap implements Serializable {
	public static HRUserProjectSoap toSoapModel(HRUserProject model) {
		HRUserProjectSoap soapModel = new HRUserProjectSoap();

		soapModel.setHrUserProjectId(model.getHrUserProjectId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHrProjectBillingRateId(model.getHrProjectBillingRateId());
		soapModel.setHrProjectId(model.getHrProjectId());
		soapModel.setHrProjectRoleId(model.getHrProjectRoleId());
		soapModel.setHrUserId(model.getHrUserId());
		soapModel.setActualRate(model.getActualRate());

		return soapModel;
	}

	public static HRUserProjectSoap[] toSoapModels(HRUserProject[] models) {
		HRUserProjectSoap[] soapModels = new HRUserProjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRUserProjectSoap[][] toSoapModels(HRUserProject[][] models) {
		HRUserProjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRUserProjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRUserProjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRUserProjectSoap[] toSoapModels(List<HRUserProject> models) {
		List<HRUserProjectSoap> soapModels = new ArrayList<HRUserProjectSoap>(models.size());

		for (HRUserProject model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRUserProjectSoap[soapModels.size()]);
	}

	public HRUserProjectSoap() {
	}

	public long getPrimaryKey() {
		return _hrUserProjectId;
	}

	public void setPrimaryKey(long pk) {
		setHrUserProjectId(pk);
	}

	public long getHrUserProjectId() {
		return _hrUserProjectId;
	}

	public void setHrUserProjectId(long hrUserProjectId) {
		_hrUserProjectId = hrUserProjectId;
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

	public long getHrProjectBillingRateId() {
		return _hrProjectBillingRateId;
	}

	public void setHrProjectBillingRateId(long hrProjectBillingRateId) {
		_hrProjectBillingRateId = hrProjectBillingRateId;
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

	public long getHrUserId() {
		return _hrUserId;
	}

	public void setHrUserId(long hrUserId) {
		_hrUserId = hrUserId;
	}

	public double getActualRate() {
		return _actualRate;
	}

	public void setActualRate(double actualRate) {
		_actualRate = actualRate;
	}

	private long _hrUserProjectId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrProjectBillingRateId;
	private long _hrProjectId;
	private long _hrProjectRoleId;
	private long _hrUserId;
	private double _actualRate;
}