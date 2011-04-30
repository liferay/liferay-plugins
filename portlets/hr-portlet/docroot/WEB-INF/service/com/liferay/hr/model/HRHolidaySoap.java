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
public class HRHolidaySoap implements Serializable {
	public static HRHolidaySoap toSoapModel(HRHoliday model) {
		HRHolidaySoap soapModel = new HRHolidaySoap();

		soapModel.setHrHolidayId(model.getHrHolidayId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setDayOfYear(model.getDayOfYear());
		soapModel.setYear(model.getYear());
		soapModel.setPaid(model.getPaid());

		return soapModel;
	}

	public static HRHolidaySoap[] toSoapModels(HRHoliday[] models) {
		HRHolidaySoap[] soapModels = new HRHolidaySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRHolidaySoap[][] toSoapModels(HRHoliday[][] models) {
		HRHolidaySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRHolidaySoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRHolidaySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRHolidaySoap[] toSoapModels(List<HRHoliday> models) {
		List<HRHolidaySoap> soapModels = new ArrayList<HRHolidaySoap>(models.size());

		for (HRHoliday model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRHolidaySoap[soapModels.size()]);
	}

	public HRHolidaySoap() {
	}

	public long getPrimaryKey() {
		return _hrHolidayId;
	}

	public void setPrimaryKey(long pk) {
		setHrHolidayId(pk);
	}

	public long getHrHolidayId() {
		return _hrHolidayId;
	}

	public void setHrHolidayId(long hrHolidayId) {
		_hrHolidayId = hrHolidayId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
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

	public boolean getPaid() {
		return _paid;
	}

	public boolean isPaid() {
		return _paid;
	}

	public void setPaid(boolean paid) {
		_paid = paid;
	}

	private long _hrHolidayId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private int _dayOfYear;
	private int _year;
	private boolean _paid;
}