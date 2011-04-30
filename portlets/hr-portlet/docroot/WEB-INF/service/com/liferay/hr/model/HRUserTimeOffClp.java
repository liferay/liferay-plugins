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
public class HRUserTimeOffClp extends BaseModelImpl<HRUserTimeOff>
	implements HRUserTimeOff {
	public HRUserTimeOffClp() {
	}

	public Class<?> getModelClass() {
		return HRUserTimeOff.class;
	}

	public String getModelClassName() {
		return HRUserTimeOff.class.getName();
	}

	public long getPrimaryKey() {
		return _hrUserTimeOffId;
	}

	public void setPrimaryKey(long pk) {
		setHrUserTimeOffId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrUserTimeOffId);
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

	public HRUserTimeOff toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRUserTimeOff)Proxy.newProxyInstance(HRUserTimeOff.class.getClassLoader(),
				new Class[] { HRUserTimeOff.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		HRUserTimeOffClp clone = new HRUserTimeOffClp();

		clone.setHrUserTimeOffId(getHrUserTimeOffId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setHrTimeOffTypeId(getHrTimeOffTypeId());
		clone.setHrUserId(getHrUserId());
		clone.setYear(getYear());
		clone.setHoursAllowed(getHoursAllowed());
		clone.setHoursAccrued(getHoursAccrued());
		clone.setHoursCarriedOver(getHoursCarriedOver());
		clone.setHoursUsed(getHoursUsed());

		return clone;
	}

	public int compareTo(HRUserTimeOff hrUserTimeOff) {
		long pk = hrUserTimeOff.getPrimaryKey();

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

		HRUserTimeOffClp hrUserTimeOff = null;

		try {
			hrUserTimeOff = (HRUserTimeOffClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = hrUserTimeOff.getPrimaryKey();

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
		StringBundler sb = new StringBundler(29);

		sb.append("{hrUserTimeOffId=");
		sb.append(getHrUserTimeOffId());
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
		sb.append(", year=");
		sb.append(getYear());
		sb.append(", hoursAllowed=");
		sb.append(getHoursAllowed());
		sb.append(", hoursAccrued=");
		sb.append(getHoursAccrued());
		sb.append(", hoursCarriedOver=");
		sb.append(getHoursCarriedOver());
		sb.append(", hoursUsed=");
		sb.append(getHoursUsed());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRUserTimeOff");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrUserTimeOffId</column-name><column-value><![CDATA[");
		sb.append(getHrUserTimeOffId());
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
			"<column><column-name>year</column-name><column-value><![CDATA[");
		sb.append(getYear());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hoursAllowed</column-name><column-value><![CDATA[");
		sb.append(getHoursAllowed());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hoursAccrued</column-name><column-value><![CDATA[");
		sb.append(getHoursAccrued());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hoursCarriedOver</column-name><column-value><![CDATA[");
		sb.append(getHoursCarriedOver());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hoursUsed</column-name><column-value><![CDATA[");
		sb.append(getHoursUsed());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrUserTimeOffId;
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
	private int _year;
	private double _hoursAllowed;
	private double _hoursAccrued;
	private double _hoursCarriedOver;
	private double _hoursUsed;
}