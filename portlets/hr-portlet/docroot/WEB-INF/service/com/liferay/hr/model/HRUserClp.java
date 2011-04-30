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
 * @author Brian Wing Shun Chan
 */
public class HRUserClp extends BaseModelImpl<HRUser> implements HRUser {
	public HRUserClp() {
	}

	public Class<?> getModelClass() {
		return HRUser.class;
	}

	public String getModelClassName() {
		return HRUser.class.getName();
	}

	public long getPrimaryKey() {
		return _hrUserId;
	}

	public void setPrimaryKey(long pk) {
		setHrUserId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrUserId);
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

	public long getHrEmploymentTypeId() {
		return _hrEmploymentTypeId;
	}

	public void setHrEmploymentTypeId(long hrEmploymentTypeId) {
		_hrEmploymentTypeId = hrEmploymentTypeId;
	}

	public long getHrJobTitleId() {
		return _hrJobTitleId;
	}

	public void setHrJobTitleId(long hrJobTitleId) {
		_hrJobTitleId = hrJobTitleId;
	}

	public long getHrOfficeId() {
		return _hrOfficeId;
	}

	public void setHrOfficeId(long hrOfficeId) {
		_hrOfficeId = hrOfficeId;
	}

	public long getHrTerminationTypeId() {
		return _hrTerminationTypeId;
	}

	public void setHrTerminationTypeId(long hrTerminationTypeId) {
		_hrTerminationTypeId = hrTerminationTypeId;
	}

	public long getHrWageTypeId() {
		return _hrWageTypeId;
	}

	public void setHrWageTypeId(long hrWageTypeId) {
		_hrWageTypeId = hrWageTypeId;
	}

	public Date getHireDate() {
		return _hireDate;
	}

	public void setHireDate(Date hireDate) {
		_hireDate = hireDate;
	}

	public Date getTerminationDate() {
		return _terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		_terminationDate = terminationDate;
	}

	public double getWageAmount() {
		return _wageAmount;
	}

	public void setWageAmount(double wageAmount) {
		_wageAmount = wageAmount;
	}

	public String getWageCurrencyCode() {
		return _wageCurrencyCode;
	}

	public void setWageCurrencyCode(String wageCurrencyCode) {
		_wageCurrencyCode = wageCurrencyCode;
	}

	public boolean getBenefitsExempt() {
		return _benefitsExempt;
	}

	public boolean isBenefitsExempt() {
		return _benefitsExempt;
	}

	public void setBenefitsExempt(boolean benefitsExempt) {
		_benefitsExempt = benefitsExempt;
	}

	public boolean getOvertimeExempt() {
		return _overtimeExempt;
	}

	public boolean isOvertimeExempt() {
		return _overtimeExempt;
	}

	public void setOvertimeExempt(boolean overtimeExempt) {
		_overtimeExempt = overtimeExempt;
	}

	public HRUser toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRUser)Proxy.newProxyInstance(HRUser.class.getClassLoader(),
				new Class[] { HRUser.class }, new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		HRUserClp clone = new HRUserClp();

		clone.setHrUserId(getHrUserId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setHrEmploymentTypeId(getHrEmploymentTypeId());
		clone.setHrJobTitleId(getHrJobTitleId());
		clone.setHrOfficeId(getHrOfficeId());
		clone.setHrTerminationTypeId(getHrTerminationTypeId());
		clone.setHrWageTypeId(getHrWageTypeId());
		clone.setHireDate(getHireDate());
		clone.setTerminationDate(getTerminationDate());
		clone.setWageAmount(getWageAmount());
		clone.setWageCurrencyCode(getWageCurrencyCode());
		clone.setBenefitsExempt(getBenefitsExempt());
		clone.setOvertimeExempt(getOvertimeExempt());

		return clone;
	}

	public int compareTo(HRUser hrUser) {
		long pk = hrUser.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		HRUserClp hrUser = null;

		try {
			hrUser = (HRUserClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = hrUser.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{hrUserId=");
		sb.append(getHrUserId());
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
		sb.append(", hrEmploymentTypeId=");
		sb.append(getHrEmploymentTypeId());
		sb.append(", hrJobTitleId=");
		sb.append(getHrJobTitleId());
		sb.append(", hrOfficeId=");
		sb.append(getHrOfficeId());
		sb.append(", hrTerminationTypeId=");
		sb.append(getHrTerminationTypeId());
		sb.append(", hrWageTypeId=");
		sb.append(getHrWageTypeId());
		sb.append(", hireDate=");
		sb.append(getHireDate());
		sb.append(", terminationDate=");
		sb.append(getTerminationDate());
		sb.append(", wageAmount=");
		sb.append(getWageAmount());
		sb.append(", wageCurrencyCode=");
		sb.append(getWageCurrencyCode());
		sb.append(", benefitsExempt=");
		sb.append(getBenefitsExempt());
		sb.append(", overtimeExempt=");
		sb.append(getOvertimeExempt());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRUser");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrUserId</column-name><column-value><![CDATA[");
		sb.append(getHrUserId());
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
			"<column><column-name>hrEmploymentTypeId</column-name><column-value><![CDATA[");
		sb.append(getHrEmploymentTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrJobTitleId</column-name><column-value><![CDATA[");
		sb.append(getHrJobTitleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrOfficeId</column-name><column-value><![CDATA[");
		sb.append(getHrOfficeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrTerminationTypeId</column-name><column-value><![CDATA[");
		sb.append(getHrTerminationTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrWageTypeId</column-name><column-value><![CDATA[");
		sb.append(getHrWageTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hireDate</column-name><column-value><![CDATA[");
		sb.append(getHireDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>terminationDate</column-name><column-value><![CDATA[");
		sb.append(getTerminationDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wageAmount</column-name><column-value><![CDATA[");
		sb.append(getWageAmount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wageCurrencyCode</column-name><column-value><![CDATA[");
		sb.append(getWageCurrencyCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>benefitsExempt</column-name><column-value><![CDATA[");
		sb.append(getBenefitsExempt());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>overtimeExempt</column-name><column-value><![CDATA[");
		sb.append(getOvertimeExempt());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrUserId;
	private String _hrUserUuid;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrEmploymentTypeId;
	private long _hrJobTitleId;
	private long _hrOfficeId;
	private long _hrTerminationTypeId;
	private long _hrWageTypeId;
	private Date _hireDate;
	private Date _terminationDate;
	private double _wageAmount;
	private String _wageCurrencyCode;
	private boolean _benefitsExempt;
	private boolean _overtimeExempt;
}