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
public class HRBranchSoap implements Serializable {
	public static HRBranchSoap toSoapModel(HRBranch model) {
		HRBranchSoap soapModel = new HRBranchSoap();

		soapModel.setHrBranchId(model.getHrBranchId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOrganizationId(model.getOrganizationId());

		return soapModel;
	}

	public static HRBranchSoap[] toSoapModels(HRBranch[] models) {
		HRBranchSoap[] soapModels = new HRBranchSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRBranchSoap[][] toSoapModels(HRBranch[][] models) {
		HRBranchSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRBranchSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRBranchSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRBranchSoap[] toSoapModels(List<HRBranch> models) {
		List<HRBranchSoap> soapModels = new ArrayList<HRBranchSoap>(models.size());

		for (HRBranch model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRBranchSoap[soapModels.size()]);
	}

	public HRBranchSoap() {
	}

	public long getPrimaryKey() {
		return _hrBranchId;
	}

	public void setPrimaryKey(long pk) {
		setHrBranchId(pk);
	}

	public long getHrBranchId() {
		return _hrBranchId;
	}

	public void setHrBranchId(long hrBranchId) {
		_hrBranchId = hrBranchId;
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

	public long getOrganizationId() {
		return _organizationId;
	}

	public void setOrganizationId(long organizationId) {
		_organizationId = organizationId;
	}

	private long _hrBranchId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _organizationId;
}