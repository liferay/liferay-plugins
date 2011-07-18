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

import com.liferay.hr.service.HRExpenseCurrencyConversionLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Wesley Gong
 */
public class HRExpenseCurrencyConversionClp extends BaseModelImpl<HRExpenseCurrencyConversion>
	implements HRExpenseCurrencyConversion {
	public HRExpenseCurrencyConversionClp() {
	}

	public Class<?> getModelClass() {
		return HRExpenseCurrencyConversion.class;
	}

	public String getModelClassName() {
		return HRExpenseCurrencyConversion.class.getName();
	}

	public long getPrimaryKey() {
		return _hrExpenseCurrencyConversionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setHrExpenseCurrencyConversionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrExpenseCurrencyConversionId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getHrExpenseCurrencyConversionId() {
		return _hrExpenseCurrencyConversionId;
	}

	public void setHrExpenseCurrencyConversionId(
		long hrExpenseCurrencyConversionId) {
		_hrExpenseCurrencyConversionId = hrExpenseCurrencyConversionId;
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

	public long getFromHRExpenseCurrencyId() {
		return _fromHRExpenseCurrencyId;
	}

	public void setFromHRExpenseCurrencyId(long fromHRExpenseCurrencyId) {
		_fromHRExpenseCurrencyId = fromHRExpenseCurrencyId;
	}

	public long getToHRExpenseCurrencyId() {
		return _toHRExpenseCurrencyId;
	}

	public void setToHRExpenseCurrencyId(long toHRExpenseCurrencyId) {
		_toHRExpenseCurrencyId = toHRExpenseCurrencyId;
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

	public void persist() throws SystemException {
		HRExpenseCurrencyConversionLocalServiceUtil.updateHRExpenseCurrencyConversion(this);
	}

	@Override
	public HRExpenseCurrencyConversion toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRExpenseCurrencyConversion)Proxy.newProxyInstance(HRExpenseCurrencyConversion.class.getClassLoader(),
				new Class[] { HRExpenseCurrencyConversion.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	@Override
	public Object clone() {
		HRExpenseCurrencyConversionClp clone = new HRExpenseCurrencyConversionClp();

		clone.setHrExpenseCurrencyConversionId(getHrExpenseCurrencyConversionId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setFromHRExpenseCurrencyId(getFromHRExpenseCurrencyId());
		clone.setToHRExpenseCurrencyId(getToHRExpenseCurrencyId());
		clone.setConversionDate(getConversionDate());
		clone.setConversionValue(getConversionValue());

		return clone;
	}

	public int compareTo(
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion) {
		int value = 0;

		value = DateUtil.compareTo(getConversionDate(),
				hrExpenseCurrencyConversion.getConversionDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		HRExpenseCurrencyConversionClp hrExpenseCurrencyConversion = null;

		try {
			hrExpenseCurrencyConversion = (HRExpenseCurrencyConversionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = hrExpenseCurrencyConversion.getPrimaryKey();

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
		StringBundler sb = new StringBundler(23);

		sb.append("{hrExpenseCurrencyConversionId=");
		sb.append(getHrExpenseCurrencyConversionId());
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
		sb.append(", fromHRExpenseCurrencyId=");
		sb.append(getFromHRExpenseCurrencyId());
		sb.append(", toHRExpenseCurrencyId=");
		sb.append(getToHRExpenseCurrencyId());
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
		sb.append("com.liferay.hr.model.HRExpenseCurrencyConversion");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrExpenseCurrencyConversionId</column-name><column-value><![CDATA[");
		sb.append(getHrExpenseCurrencyConversionId());
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
			"<column><column-name>fromHRExpenseCurrencyId</column-name><column-value><![CDATA[");
		sb.append(getFromHRExpenseCurrencyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>toHRExpenseCurrencyId</column-name><column-value><![CDATA[");
		sb.append(getToHRExpenseCurrencyId());
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

	private long _hrExpenseCurrencyConversionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _fromHRExpenseCurrencyId;
	private long _toHRExpenseCurrencyId;
	private Date _conversionDate;
	private double _conversionValue;
}