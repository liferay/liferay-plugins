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
public class HRUserTaskSoap implements Serializable {
	public static HRUserTaskSoap toSoapModel(HRUserTask model) {
		HRUserTaskSoap soapModel = new HRUserTaskSoap();

		soapModel.setHrUserTaskId(model.getHrUserTaskId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHrBillabilityId(model.getHrBillabilityId());
		soapModel.setHrTaskId(model.getHrTaskId());
		soapModel.setHrTimesheetId(model.getHrTimesheetId());
		soapModel.setHrUserId(model.getHrUserId());

		return soapModel;
	}

	public static HRUserTaskSoap[] toSoapModels(HRUserTask[] models) {
		HRUserTaskSoap[] soapModels = new HRUserTaskSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRUserTaskSoap[][] toSoapModels(HRUserTask[][] models) {
		HRUserTaskSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRUserTaskSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRUserTaskSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRUserTaskSoap[] toSoapModels(List<HRUserTask> models) {
		List<HRUserTaskSoap> soapModels = new ArrayList<HRUserTaskSoap>(models.size());

		for (HRUserTask model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRUserTaskSoap[soapModels.size()]);
	}

	public HRUserTaskSoap() {
	}

	public long getPrimaryKey() {
		return _hrUserTaskId;
	}

	public void setPrimaryKey(long pk) {
		setHrUserTaskId(pk);
	}

	public long getHrUserTaskId() {
		return _hrUserTaskId;
	}

	public void setHrUserTaskId(long hrUserTaskId) {
		_hrUserTaskId = hrUserTaskId;
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

	public long getHrBillabilityId() {
		return _hrBillabilityId;
	}

	public void setHrBillabilityId(long hrBillabilityId) {
		_hrBillabilityId = hrBillabilityId;
	}

	public long getHrTaskId() {
		return _hrTaskId;
	}

	public void setHrTaskId(long hrTaskId) {
		_hrTaskId = hrTaskId;
	}

	public long getHrTimesheetId() {
		return _hrTimesheetId;
	}

	public void setHrTimesheetId(long hrTimesheetId) {
		_hrTimesheetId = hrTimesheetId;
	}

	public long getHrUserId() {
		return _hrUserId;
	}

	public void setHrUserId(long hrUserId) {
		_hrUserId = hrUserId;
	}

	private long _hrUserTaskId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrBillabilityId;
	private long _hrTaskId;
	private long _hrTimesheetId;
	private long _hrUserId;
}