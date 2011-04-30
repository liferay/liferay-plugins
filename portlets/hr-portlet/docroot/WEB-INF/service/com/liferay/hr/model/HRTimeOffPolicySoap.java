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
public class HRTimeOffPolicySoap implements Serializable {
	public static HRTimeOffPolicySoap toSoapModel(HRTimeOffPolicy model) {
		HRTimeOffPolicySoap soapModel = new HRTimeOffPolicySoap();

		soapModel.setHrTimeOffPolicyId(model.getHrTimeOffPolicyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHrTimeOffTypeId(model.getHrTimeOffTypeId());
		soapModel.setHrUserId(model.getHrUserId());
		soapModel.setAccrueHRTimeOffFrequencyTypeId(model.getAccrueHRTimeOffFrequencyTypeId());
		soapModel.setResetHRTimeOffFrequencyTypeId(model.getResetHRTimeOffFrequencyTypeId());
		soapModel.setEffectiveDate(model.getEffectiveDate());
		soapModel.setInactive(model.getInactive());
		soapModel.setHoursAllowed(model.getHoursAllowed());
		soapModel.setHoursBaseAmount(model.getHoursBaseAmount());
		soapModel.setHoursToAccrue(model.getHoursToAccrue());
		soapModel.setCarryOverHoursAllowed(model.getCarryOverHoursAllowed());

		return soapModel;
	}

	public static HRTimeOffPolicySoap[] toSoapModels(HRTimeOffPolicy[] models) {
		HRTimeOffPolicySoap[] soapModels = new HRTimeOffPolicySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRTimeOffPolicySoap[][] toSoapModels(
		HRTimeOffPolicy[][] models) {
		HRTimeOffPolicySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRTimeOffPolicySoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRTimeOffPolicySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRTimeOffPolicySoap[] toSoapModels(
		List<HRTimeOffPolicy> models) {
		List<HRTimeOffPolicySoap> soapModels = new ArrayList<HRTimeOffPolicySoap>(models.size());

		for (HRTimeOffPolicy model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRTimeOffPolicySoap[soapModels.size()]);
	}

	public HRTimeOffPolicySoap() {
	}

	public long getPrimaryKey() {
		return _hrTimeOffPolicyId;
	}

	public void setPrimaryKey(long pk) {
		setHrTimeOffPolicyId(pk);
	}

	public long getHrTimeOffPolicyId() {
		return _hrTimeOffPolicyId;
	}

	public void setHrTimeOffPolicyId(long hrTimeOffPolicyId) {
		_hrTimeOffPolicyId = hrTimeOffPolicyId;
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

	public long getAccrueHRTimeOffFrequencyTypeId() {
		return _accrueHRTimeOffFrequencyTypeId;
	}

	public void setAccrueHRTimeOffFrequencyTypeId(
		long accrueHRTimeOffFrequencyTypeId) {
		_accrueHRTimeOffFrequencyTypeId = accrueHRTimeOffFrequencyTypeId;
	}

	public long getResetHRTimeOffFrequencyTypeId() {
		return _resetHRTimeOffFrequencyTypeId;
	}

	public void setResetHRTimeOffFrequencyTypeId(
		long resetHRTimeOffFrequencyTypeId) {
		_resetHRTimeOffFrequencyTypeId = resetHRTimeOffFrequencyTypeId;
	}

	public Date getEffectiveDate() {
		return _effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		_effectiveDate = effectiveDate;
	}

	public boolean getInactive() {
		return _inactive;
	}

	public boolean isInactive() {
		return _inactive;
	}

	public void setInactive(boolean inactive) {
		_inactive = inactive;
	}

	public double getHoursAllowed() {
		return _hoursAllowed;
	}

	public void setHoursAllowed(double hoursAllowed) {
		_hoursAllowed = hoursAllowed;
	}

	public double getHoursBaseAmount() {
		return _hoursBaseAmount;
	}

	public void setHoursBaseAmount(double hoursBaseAmount) {
		_hoursBaseAmount = hoursBaseAmount;
	}

	public double getHoursToAccrue() {
		return _hoursToAccrue;
	}

	public void setHoursToAccrue(double hoursToAccrue) {
		_hoursToAccrue = hoursToAccrue;
	}

	public double getCarryOverHoursAllowed() {
		return _carryOverHoursAllowed;
	}

	public void setCarryOverHoursAllowed(double carryOverHoursAllowed) {
		_carryOverHoursAllowed = carryOverHoursAllowed;
	}

	private long _hrTimeOffPolicyId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrTimeOffTypeId;
	private long _hrUserId;
	private long _accrueHRTimeOffFrequencyTypeId;
	private long _resetHRTimeOffFrequencyTypeId;
	private Date _effectiveDate;
	private boolean _inactive;
	private double _hoursAllowed;
	private double _hoursBaseAmount;
	private double _hoursToAccrue;
	private double _carryOverHoursAllowed;
}