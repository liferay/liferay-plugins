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
public class HRUserTimeOffSoap implements Serializable {
	public static HRUserTimeOffSoap toSoapModel(HRUserTimeOff model) {
		HRUserTimeOffSoap soapModel = new HRUserTimeOffSoap();

		soapModel.setHrUserTimeOffId(model.getHrUserTimeOffId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHrTimeOffTypeId(model.getHrTimeOffTypeId());
		soapModel.setHrUserId(model.getHrUserId());
		soapModel.setYear(model.getYear());
		soapModel.setHoursAllowed(model.getHoursAllowed());
		soapModel.setHoursAccrued(model.getHoursAccrued());
		soapModel.setHoursCarriedOver(model.getHoursCarriedOver());
		soapModel.setHoursUsed(model.getHoursUsed());

		return soapModel;
	}

	public static HRUserTimeOffSoap[] toSoapModels(HRUserTimeOff[] models) {
		HRUserTimeOffSoap[] soapModels = new HRUserTimeOffSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRUserTimeOffSoap[][] toSoapModels(HRUserTimeOff[][] models) {
		HRUserTimeOffSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRUserTimeOffSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRUserTimeOffSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRUserTimeOffSoap[] toSoapModels(List<HRUserTimeOff> models) {
		List<HRUserTimeOffSoap> soapModels = new ArrayList<HRUserTimeOffSoap>(models.size());

		for (HRUserTimeOff model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRUserTimeOffSoap[soapModels.size()]);
	}

	public HRUserTimeOffSoap() {
	}

	public long getPrimaryKey() {
		return _hrUserTimeOffId;
	}

	public void setPrimaryKey(long pk) {
		setHrUserTimeOffId(pk);
	}

	public long getHrUserTimeOffId() {
		return _hrUserTimeOffId;
	}

	public void setHrUserTimeOffId(long hrUserTimeOffId) {
		_hrUserTimeOffId = hrUserTimeOffId;
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

	public long getHrTimeOffTypeId() {
		return _hrTimeOffTypeId;
	}

	public void setHrTimeOffTypeId(long hrTimeOffTypeId) {
		_hrTimeOffTypeId = hrTimeOffTypeId;
	}

	public long getHrUserId() {
		return _hrUserId;
	}

	public void setHrUserId(long hrUserId) {
		_hrUserId = hrUserId;
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int year) {
		_year = year;
	}

	public double getHoursAllowed() {
		return _hoursAllowed;
	}

	public void setHoursAllowed(double hoursAllowed) {
		_hoursAllowed = hoursAllowed;
	}

	public double getHoursAccrued() {
		return _hoursAccrued;
	}

	public void setHoursAccrued(double hoursAccrued) {
		_hoursAccrued = hoursAccrued;
	}

	public double getHoursCarriedOver() {
		return _hoursCarriedOver;
	}

	public void setHoursCarriedOver(double hoursCarriedOver) {
		_hoursCarriedOver = hoursCarriedOver;
	}

	public double getHoursUsed() {
		return _hoursUsed;
	}

	public void setHoursUsed(double hoursUsed) {
		_hoursUsed = hoursUsed;
	}

	private long _hrUserTimeOffId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrTimeOffTypeId;
	private long _hrUserId;
	private int _year;
	private double _hoursAllowed;
	private double _hoursAccrued;
	private double _hoursCarriedOver;
	private double _hoursUsed;
}