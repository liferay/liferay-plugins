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
public class HRTimeSheetHoursPerDayClp extends BaseModelImpl<HRTimeSheetHoursPerDay>
	implements HRTimeSheetHoursPerDay {
	public HRTimeSheetHoursPerDayClp() {
	}

	public Class<?> getModelClass() {
		return HRTimeSheetHoursPerDay.class;
	}

	public String getModelClassName() {
		return HRTimeSheetHoursPerDay.class.getName();
	}

	public long getPrimaryKey() {
		return _hrTimeSheetHoursPerDayId;
	}

	public void setPrimaryKey(long pk) {
		setHrTimeSheetHoursPerDayId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrTimeSheetHoursPerDayId);
	}

	public long getHrTimeSheetHoursPerDayId() {
		return _hrTimeSheetHoursPerDayId;
	}

	public void setHrTimeSheetHoursPerDayId(long hrTimeSheetHoursPerDayId) {
		_hrTimeSheetHoursPerDayId = hrTimeSheetHoursPerDayId;
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

	public long getHrOfficeId() {
		return _hrOfficeId;
	}

	public void setHrOfficeId(long hrOfficeId) {
		_hrOfficeId = hrOfficeId;
	}

	public double getHoursPerDay() {
		return _hoursPerDay;
	}

	public void setHoursPerDay(double hoursPerDay) {
		_hoursPerDay = hoursPerDay;
	}

	public HRTimeSheetHoursPerDay toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRTimeSheetHoursPerDay)Proxy.newProxyInstance(HRTimeSheetHoursPerDay.class.getClassLoader(),
				new Class[] { HRTimeSheetHoursPerDay.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		HRTimeSheetHoursPerDayClp clone = new HRTimeSheetHoursPerDayClp();

		clone.setHrTimeSheetHoursPerDayId(getHrTimeSheetHoursPerDayId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setHrOfficeId(getHrOfficeId());
		clone.setHoursPerDay(getHoursPerDay());

		return clone;
	}

	public int compareTo(HRTimeSheetHoursPerDay hrTimeSheetHoursPerDay) {
		long pk = hrTimeSheetHoursPerDay.getPrimaryKey();

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

		HRTimeSheetHoursPerDayClp hrTimeSheetHoursPerDay = null;

		try {
			hrTimeSheetHoursPerDay = (HRTimeSheetHoursPerDayClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = hrTimeSheetHoursPerDay.getPrimaryKey();

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
		StringBundler sb = new StringBundler(19);

		sb.append("{hrTimeSheetHoursPerDayId=");
		sb.append(getHrTimeSheetHoursPerDayId());
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
		sb.append(", hrOfficeId=");
		sb.append(getHrOfficeId());
		sb.append(", hoursPerDay=");
		sb.append(getHoursPerDay());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRTimeSheetHoursPerDay");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrTimeSheetHoursPerDayId</column-name><column-value><![CDATA[");
		sb.append(getHrTimeSheetHoursPerDayId());
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
			"<column><column-name>hrOfficeId</column-name><column-value><![CDATA[");
		sb.append(getHrOfficeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hoursPerDay</column-name><column-value><![CDATA[");
		sb.append(getHoursPerDay());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrTimeSheetHoursPerDayId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrOfficeId;
	private double _hoursPerDay;
}