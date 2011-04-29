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
public class HRTimeSheetHoursPerDaySoap implements Serializable {
	public static HRTimeSheetHoursPerDaySoap toSoapModel(
		HRTimeSheetHoursPerDay model) {
		HRTimeSheetHoursPerDaySoap soapModel = new HRTimeSheetHoursPerDaySoap();

		soapModel.setHrTimeSheetHoursPerDay(model.getHrTimeSheetHoursPerDay());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHrOfficeId(model.getHrOfficeId());
		soapModel.setHoursPerDay(model.getHoursPerDay());

		return soapModel;
	}

	public static HRTimeSheetHoursPerDaySoap[] toSoapModels(
		HRTimeSheetHoursPerDay[] models) {
		HRTimeSheetHoursPerDaySoap[] soapModels = new HRTimeSheetHoursPerDaySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRTimeSheetHoursPerDaySoap[][] toSoapModels(
		HRTimeSheetHoursPerDay[][] models) {
		HRTimeSheetHoursPerDaySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRTimeSheetHoursPerDaySoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRTimeSheetHoursPerDaySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRTimeSheetHoursPerDaySoap[] toSoapModels(
		List<HRTimeSheetHoursPerDay> models) {
		List<HRTimeSheetHoursPerDaySoap> soapModels = new ArrayList<HRTimeSheetHoursPerDaySoap>(models.size());

		for (HRTimeSheetHoursPerDay model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRTimeSheetHoursPerDaySoap[soapModels.size()]);
	}

	public HRTimeSheetHoursPerDaySoap() {
	}

	public long getPrimaryKey() {
		return _hrTimeSheetHoursPerDay;
	}

	public void setPrimaryKey(long pk) {
		setHrTimeSheetHoursPerDay(pk);
	}

	public long getHrTimeSheetHoursPerDay() {
		return _hrTimeSheetHoursPerDay;
	}

	public void setHrTimeSheetHoursPerDay(long hrTimeSheetHoursPerDay) {
		_hrTimeSheetHoursPerDay = hrTimeSheetHoursPerDay;
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

	public long getHrOfficeId() {
		return _hrOfficeId;
	}

	public void setHrOfficeId(long hrOfficeId) {
		_hrOfficeId = hrOfficeId;
	}

	public double getHoursPerDay() {
		return _hoursPerDay;
	}

	public void setHoursPerDay(double hoursPerDay) {
		_hoursPerDay = hoursPerDay;
	}

	private long _hrTimeSheetHoursPerDay;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrOfficeId;
	private double _hoursPerDay;
}