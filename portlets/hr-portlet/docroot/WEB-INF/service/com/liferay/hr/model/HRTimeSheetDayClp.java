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
public class HRTimeSheetDayClp extends BaseModelImpl<HRTimeSheetDay>
	implements HRTimeSheetDay {
	public HRTimeSheetDayClp() {
	}

	public Class<?> getModelClass() {
		return HRTimeSheetDay.class;
	}

	public String getModelClassName() {
		return HRTimeSheetDay.class.getName();
	}

	public long getPrimaryKey() {
		return _hrTimeSheetDayId;
	}

	public void setPrimaryKey(long primaryKey) {
		setHrTimeSheetDayId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrTimeSheetDayId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	public String getHrUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getHrUserId(), "uuid", _hrUserUuid);
	}

	public void setHrUserUuid(String hrUserUuid) {
		_hrUserUuid = hrUserUuid;
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

	public HRTimeSheetDay toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRTimeSheetDay)Proxy.newProxyInstance(HRTimeSheetDay.class.getClassLoader(),
				new Class[] { HRTimeSheetDay.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		HRTimeSheetDayClp clone = new HRTimeSheetDayClp();

		clone.setHrTimeSheetDayId(getHrTimeSheetDayId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setHrTimeSheetId(getHrTimeSheetId());
		clone.setHrUserId(getHrUserId());
		clone.setDayOfYear(getDayOfYear());
		clone.setYear(getYear());
		clone.setHours(getHours());

		return clone;
	}

	public int compareTo(HRTimeSheetDay hrTimeSheetDay) {
		long primaryKey = hrTimeSheetDay.getPrimaryKey();

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

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		HRTimeSheetDayClp hrTimeSheetDay = null;

		try {
			hrTimeSheetDay = (HRTimeSheetDayClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = hrTimeSheetDay.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
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
		StringBundler sb = new StringBundler(25);

		sb.append("{hrTimeSheetDayId=");
		sb.append(getHrTimeSheetDayId());
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
		sb.append(", hrTimeSheetId=");
		sb.append(getHrTimeSheetId());
		sb.append(", hrUserId=");
		sb.append(getHrUserId());
		sb.append(", dayOfYear=");
		sb.append(getDayOfYear());
		sb.append(", year=");
		sb.append(getYear());
		sb.append(", hours=");
		sb.append(getHours());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRTimeSheetDay");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrTimeSheetDayId</column-name><column-value><![CDATA[");
		sb.append(getHrTimeSheetDayId());
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
			"<column><column-name>hrTimeSheetId</column-name><column-value><![CDATA[");
		sb.append(getHrTimeSheetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrUserId</column-name><column-value><![CDATA[");
		sb.append(getHrUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dayOfYear</column-name><column-value><![CDATA[");
		sb.append(getDayOfYear());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>year</column-name><column-value><![CDATA[");
		sb.append(getYear());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hours</column-name><column-value><![CDATA[");
		sb.append(getHours());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrTimeSheetDayId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrTimeSheetId;
	private long _hrUserId;
	private String _hrUserUuid;
	private int _dayOfYear;
	private int _year;
	private double _hours;
}