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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Wesley Gong
 */
public class HRTimeOffPolicyClp extends BaseModelImpl<HRTimeOffPolicy>
	implements HRTimeOffPolicy {
	public HRTimeOffPolicyClp() {
	}

	public Class<?> getModelClass() {
		return HRTimeOffPolicy.class;
	}

	public String getModelClassName() {
		return HRTimeOffPolicy.class.getName();
	}

	public long getPrimaryKey() {
		return _hrTimeOffPolicyId;
	}

	public void setPrimaryKey(long primaryKey) {
		setHrTimeOffPolicyId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrTimeOffPolicyId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
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

	public String getHrUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getHrUserId(), "uuid", _hrUserUuid);
	}

	public void setHrUserUuid(String hrUserUuid) {
		_hrUserUuid = hrUserUuid;
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

	@Override
	public HRTimeOffPolicy toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRTimeOffPolicy)Proxy.newProxyInstance(HRTimeOffPolicy.class.getClassLoader(),
				new Class[] { HRTimeOffPolicy.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	@Override
	public Object clone() {
		HRTimeOffPolicyClp clone = new HRTimeOffPolicyClp();

		clone.setHrTimeOffPolicyId(getHrTimeOffPolicyId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setHrTimeOffTypeId(getHrTimeOffTypeId());
		clone.setHrUserId(getHrUserId());
		clone.setAccrueHRTimeOffFrequencyTypeId(getAccrueHRTimeOffFrequencyTypeId());
		clone.setResetHRTimeOffFrequencyTypeId(getResetHRTimeOffFrequencyTypeId());
		clone.setEffectiveDate(getEffectiveDate());
		clone.setInactive(getInactive());
		clone.setHoursAllowed(getHoursAllowed());
		clone.setHoursBaseAmount(getHoursBaseAmount());
		clone.setHoursToAccrue(getHoursToAccrue());
		clone.setCarryOverHoursAllowed(getCarryOverHoursAllowed());

		return clone;
	}

	public int compareTo(HRTimeOffPolicy hrTimeOffPolicy) {
		long primaryKey = hrTimeOffPolicy.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		HRTimeOffPolicyClp hrTimeOffPolicy = null;

		try {
			hrTimeOffPolicy = (HRTimeOffPolicyClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = hrTimeOffPolicy.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{hrTimeOffPolicyId=");
		sb.append(getHrTimeOffPolicyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", hrTimeOffTypeId=");
		sb.append(getHrTimeOffTypeId());
		sb.append(", hrUserId=");
		sb.append(getHrUserId());
		sb.append(", accrueHRTimeOffFrequencyTypeId=");
		sb.append(getAccrueHRTimeOffFrequencyTypeId());
		sb.append(", resetHRTimeOffFrequencyTypeId=");
		sb.append(getResetHRTimeOffFrequencyTypeId());
		sb.append(", effectiveDate=");
		sb.append(getEffectiveDate());
		sb.append(", inactive=");
		sb.append(getInactive());
		sb.append(", hoursAllowed=");
		sb.append(getHoursAllowed());
		sb.append(", hoursBaseAmount=");
		sb.append(getHoursBaseAmount());
		sb.append(", hoursToAccrue=");
		sb.append(getHoursToAccrue());
		sb.append(", carryOverHoursAllowed=");
		sb.append(getCarryOverHoursAllowed());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRTimeOffPolicy");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrTimeOffPolicyId</column-name><column-value><![CDATA[");
		sb.append(getHrTimeOffPolicyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrTimeOffTypeId</column-name><column-value><![CDATA[");
		sb.append(getHrTimeOffTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrUserId</column-name><column-value><![CDATA[");
		sb.append(getHrUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accrueHRTimeOffFrequencyTypeId</column-name><column-value><![CDATA[");
		sb.append(getAccrueHRTimeOffFrequencyTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resetHRTimeOffFrequencyTypeId</column-name><column-value><![CDATA[");
		sb.append(getResetHRTimeOffFrequencyTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>effectiveDate</column-name><column-value><![CDATA[");
		sb.append(getEffectiveDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inactive</column-name><column-value><![CDATA[");
		sb.append(getInactive());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hoursAllowed</column-name><column-value><![CDATA[");
		sb.append(getHoursAllowed());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hoursBaseAmount</column-name><column-value><![CDATA[");
		sb.append(getHoursBaseAmount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hoursToAccrue</column-name><column-value><![CDATA[");
		sb.append(getHoursToAccrue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>carryOverHoursAllowed</column-name><column-value><![CDATA[");
		sb.append(getCarryOverHoursAllowed());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrTimeOffPolicyId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrTimeOffTypeId;
	private long _hrUserId;
	private String _hrUserUuid;
	private long _accrueHRTimeOffFrequencyTypeId;
	private long _resetHRTimeOffFrequencyTypeId;
	private Date _effectiveDate;
	private boolean _inactive;
	private double _hoursAllowed;
	private double _hoursBaseAmount;
	private double _hoursToAccrue;
	private double _carryOverHoursAllowed;
}