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
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Wesley Gong
 */
public class HRTimeOffClp extends BaseModelImpl<HRTimeOff> implements HRTimeOff {
	public HRTimeOffClp() {
	}

	public Class<?> getModelClass() {
		return HRTimeOff.class;
	}

	public String getModelClassName() {
		return HRTimeOff.class.getName();
	}

	public long getPrimaryKey() {
		return _hrTimeOffId;
	}

	public void setPrimaryKey(long pk) {
		setHrTimeOffId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrTimeOffId);
	}

	public long getHrTimeOffId() {
		return _hrTimeOffId;
	}

	public void setHrTimeOffId(long hrTimeOffId) {
		_hrTimeOffId = hrTimeOffId;
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

	public int getStartDayOfYear() {
		return _startDayOfYear;
	}

	public void setStartDayOfYear(int startDayOfYear) {
		_startDayOfYear = startDayOfYear;
	}

	public int getEndDayOfYear() {
		return _endDayOfYear;
	}

	public void setEndDayOfYear(int endDayOfYear) {
		_endDayOfYear = endDayOfYear;
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int year) {
		_year = year;
	}

	public double getTotalHours() {
		return _totalHours;
	}

	public void setTotalHours(double totalHours) {
		_totalHours = totalHours;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getStatusByUserId(), "uuid",
			_statusByUserUuid);
	}

	public void setStatusByUserUuid(String statusByUserUuid) {
		_statusByUserUuid = statusByUserUuid;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	/**
	 * @deprecated {@link #isApproved}
	 */
	public boolean getApproved() {
		return isApproved();
	}

	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	public HRTimeOff toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRTimeOff)Proxy.newProxyInstance(HRTimeOff.class.getClassLoader(),
				new Class[] { HRTimeOff.class }, new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		HRTimeOffClp clone = new HRTimeOffClp();

		clone.setHrTimeOffId(getHrTimeOffId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setHrTimeOffTypeId(getHrTimeOffTypeId());
		clone.setHrTimeSheetId(getHrTimeSheetId());
		clone.setHrUserId(getHrUserId());
		clone.setStartDayOfYear(getStartDayOfYear());
		clone.setEndDayOfYear(getEndDayOfYear());
		clone.setYear(getYear());
		clone.setTotalHours(getTotalHours());
		clone.setStatus(getStatus());
		clone.setStatusByUserId(getStatusByUserId());
		clone.setStatusByUserName(getStatusByUserName());
		clone.setStatusDate(getStatusDate());

		return clone;
	}

	public int compareTo(HRTimeOff hrTimeOff) {
		long pk = hrTimeOff.getPrimaryKey();

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

		HRTimeOffClp hrTimeOff = null;

		try {
			hrTimeOff = (HRTimeOffClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = hrTimeOff.getPrimaryKey();

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

		sb.append("{hrTimeOffId=");
		sb.append(getHrTimeOffId());
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
		sb.append(", hrTimeSheetId=");
		sb.append(getHrTimeSheetId());
		sb.append(", hrUserId=");
		sb.append(getHrUserId());
		sb.append(", startDayOfYear=");
		sb.append(getStartDayOfYear());
		sb.append(", endDayOfYear=");
		sb.append(getEndDayOfYear());
		sb.append(", year=");
		sb.append(getYear());
		sb.append(", totalHours=");
		sb.append(getTotalHours());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRTimeOff");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrTimeOffId</column-name><column-value><![CDATA[");
		sb.append(getHrTimeOffId());
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
			"<column><column-name>hrTimeSheetId</column-name><column-value><![CDATA[");
		sb.append(getHrTimeSheetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrUserId</column-name><column-value><![CDATA[");
		sb.append(getHrUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDayOfYear</column-name><column-value><![CDATA[");
		sb.append(getStartDayOfYear());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDayOfYear</column-name><column-value><![CDATA[");
		sb.append(getEndDayOfYear());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>year</column-name><column-value><![CDATA[");
		sb.append(getYear());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>totalHours</column-name><column-value><![CDATA[");
		sb.append(getTotalHours());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrTimeOffId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrTimeOffTypeId;
	private long _hrTimeSheetId;
	private long _hrUserId;
	private String _hrUserUuid;
	private int _startDayOfYear;
	private int _endDayOfYear;
	private int _year;
	private double _totalHours;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserUuid;
	private String _statusByUserName;
	private Date _statusDate;
}