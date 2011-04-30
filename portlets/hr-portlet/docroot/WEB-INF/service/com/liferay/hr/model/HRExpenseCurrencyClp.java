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
public class HRExpenseCurrencyClp extends BaseModelImpl<HRExpenseCurrency>
	implements HRExpenseCurrency {
	public HRExpenseCurrencyClp() {
	}

	public Class<?> getModelClass() {
		return HRExpenseCurrency.class;
	}

	public String getModelClassName() {
		return HRExpenseCurrency.class.getName();
	}

	public long getPrimaryKey() {
		return _hrExpenseCurrencyId;
	}

	public void setPrimaryKey(long pk) {
		setHrExpenseCurrencyId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrExpenseCurrencyId);
	}

	public long getHrExpenseCurrencyId() {
		return _hrExpenseCurrencyId;
	}

	public void setHrExpenseCurrencyId(long hrExpenseCurrencyId) {
		_hrExpenseCurrencyId = hrExpenseCurrencyId;
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

	public String getFromCurrencyCode() {
		return _fromCurrencyCode;
	}

	public void setFromCurrencyCode(String fromCurrencyCode) {
		_fromCurrencyCode = fromCurrencyCode;
	}

	public String getToCurrencyCode() {
		return _toCurrencyCode;
	}

	public void setToCurrencyCode(String toCurrencyCode) {
		_toCurrencyCode = toCurrencyCode;
	}

	public Date getConversionDate() {
		return _conversionDate;
	}

	public void setConversionDate(Date conversionDate) {
		_conversionDate = conversionDate;
	}

	public double getConversionValue() {
		return _conversionValue;
	}

	public void setConversionValue(double conversionValue) {
		_conversionValue = conversionValue;
	}

	public HRExpenseCurrency toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRExpenseCurrency)Proxy.newProxyInstance(HRExpenseCurrency.class.getClassLoader(),
				new Class[] { HRExpenseCurrency.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		HRExpenseCurrencyClp clone = new HRExpenseCurrencyClp();

		clone.setHrExpenseCurrencyId(getHrExpenseCurrencyId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setFromCurrencyCode(getFromCurrencyCode());
		clone.setToCurrencyCode(getToCurrencyCode());
		clone.setConversionDate(getConversionDate());
		clone.setConversionValue(getConversionValue());

		return clone;
	}

	public int compareTo(HRExpenseCurrency hrExpenseCurrency) {
		long pk = hrExpenseCurrency.getPrimaryKey();

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

		HRExpenseCurrencyClp hrExpenseCurrency = null;

		try {
			hrExpenseCurrency = (HRExpenseCurrencyClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = hrExpenseCurrency.getPrimaryKey();

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
		StringBundler sb = new StringBundler(23);

		sb.append("{hrExpenseCurrencyId=");
		sb.append(getHrExpenseCurrencyId());
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
		sb.append(", fromCurrencyCode=");
		sb.append(getFromCurrencyCode());
		sb.append(", toCurrencyCode=");
		sb.append(getToCurrencyCode());
		sb.append(", conversionDate=");
		sb.append(getConversionDate());
		sb.append(", conversionValue=");
		sb.append(getConversionValue());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRExpenseCurrency");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrExpenseCurrencyId</column-name><column-value><![CDATA[");
		sb.append(getHrExpenseCurrencyId());
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
			"<column><column-name>fromCurrencyCode</column-name><column-value><![CDATA[");
		sb.append(getFromCurrencyCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>toCurrencyCode</column-name><column-value><![CDATA[");
		sb.append(getToCurrencyCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>conversionDate</column-name><column-value><![CDATA[");
		sb.append(getConversionDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>conversionValue</column-name><column-value><![CDATA[");
		sb.append(getConversionValue());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrExpenseCurrencyId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _fromCurrencyCode;
	private String _toCurrencyCode;
	private Date _conversionDate;
	private double _conversionValue;
}