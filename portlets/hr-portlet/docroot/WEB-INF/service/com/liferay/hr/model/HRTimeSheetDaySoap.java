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
public class HRTimeSheetDaySoap implements Serializable {
	public static HRTimeSheetDaySoap toSoapModel(HRTimeSheetDay model) {
		HRTimeSheetDaySoap soapModel = new HRTimeSheetDaySoap();

		soapModel.setHrTimeSheetDayId(model.getHrTimeSheetDayId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHrTimeSheetId(model.getHrTimeSheetId());
		soapModel.setHrUserId(model.getHrUserId());
		soapModel.setDayOfYear(model.getDayOfYear());
		soapModel.setYear(model.getYear());
		soapModel.setHours(model.getHours());

		return soapModel;
	}

	public static HRTimeSheetDaySoap[] toSoapModels(HRTimeSheetDay[] models) {
		HRTimeSheetDaySoap[] soapModels = new HRTimeSheetDaySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRTimeSheetDaySoap[][] toSoapModels(HRTimeSheetDay[][] models) {
		HRTimeSheetDaySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRTimeSheetDaySoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRTimeSheetDaySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRTimeSheetDaySoap[] toSoapModels(List<HRTimeSheetDay> models) {
		List<HRTimeSheetDaySoap> soapModels = new ArrayList<HRTimeSheetDaySoap>(models.size());

		for (HRTimeSheetDay model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRTimeSheetDaySoap[soapModels.size()]);
	}

	public HRTimeSheetDaySoap() {
	}

	public long getPrimaryKey() {
		return _hrTimeSheetDayId;
	}

	public void setPrimaryKey(long pk) {
		setHrTimeSheetDayId(pk);
	}

	public long getHrTimeSheetDayId() {
		return _hrTimeSheetDayId;
	}

	public void setHrTimeSheetDayId(long hrTimeSheetDayId) {
		_hrTimeSheetDayId = hrTimeSheetDayId;
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

	public long getHrTimeSheetId() {
		return _hrTimeSheetId;
	}

	public void setHrTimeSheetId(long hrTimeSheetId) {
		_hrTimeSheetId = hrTimeSheetId;
	}

	public long getHrUserId() {
		return _hrUserId;
	}

	public void setHrUserId(long hrUserId) {
		_hrUserId = hrUserId;
	}

	public int getDayOfYear() {
		return _dayOfYear;
	}

	public void setDayOfYear(int dayOfYear) {
		_dayOfYear = dayOfYear;
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int year) {
		_year = year;
	}

	public double getHours() {
		return _hours;
	}

	public void setHours(double hours) {
		_hours = hours;
	}

	private long _hrTimeSheetDayId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrTimeSheetId;
	private long _hrUserId;
	private int _dayOfYear;
	private int _year;
	private double _hours;
}