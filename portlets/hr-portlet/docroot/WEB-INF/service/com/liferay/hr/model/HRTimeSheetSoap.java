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
public class HRTimeSheetSoap implements Serializable {
	public static HRTimeSheetSoap toSoapModel(HRTimeSheet model) {
		HRTimeSheetSoap soapModel = new HRTimeSheetSoap();

		soapModel.setHrTimeSheetId(model.getHrTimeSheetId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHrUserId(model.getHrUserId());
		soapModel.setStartDayOfYear(model.getStartDayOfYear());
		soapModel.setEndDayOfYear(model.getEndDayOfYear());
		soapModel.setYear(model.getYear());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());

		return soapModel;
	}

	public static HRTimeSheetSoap[] toSoapModels(HRTimeSheet[] models) {
		HRTimeSheetSoap[] soapModels = new HRTimeSheetSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRTimeSheetSoap[][] toSoapModels(HRTimeSheet[][] models) {
		HRTimeSheetSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRTimeSheetSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRTimeSheetSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRTimeSheetSoap[] toSoapModels(List<HRTimeSheet> models) {
		List<HRTimeSheetSoap> soapModels = new ArrayList<HRTimeSheetSoap>(models.size());

		for (HRTimeSheet model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRTimeSheetSoap[soapModels.size()]);
	}

	public HRTimeSheetSoap() {
	}

	public long getPrimaryKey() {
		return _hrTimeSheetId;
	}

	public void setPrimaryKey(long pk) {
		setHrTimeSheetId(pk);
	}

	public long getHrTimeSheetId() {
		return _hrTimeSheetId;
	}

	public void setHrTimeSheetId(long hrTimeSheetId) {
		_hrTimeSheetId = hrTimeSheetId;
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

	public long getHrUserId() {
		return _hrUserId;
	}

	public void setHrUserId(long hrUserId) {
		_hrUserId = hrUserId;
	}

	public int getStartDayOfYear() {
		return _startDayOfYear;
	}

	public void setStartDayOfYear(int startDayOfYear) {
		_startDayOfYear = startDayOfYear;
	}

	public int getEndDayOfYear() {
		return _endDayOfYear;
	}

	public void setEndDayOfYear(int endDayOfYear) {
		_endDayOfYear = endDayOfYear;
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int year) {
		_year = year;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	private long _hrTimeSheetId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrUserId;
	private int _startDayOfYear;
	private int _endDayOfYear;
	private int _year;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
}