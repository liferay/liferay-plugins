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
public class HRUserTaskClp extends BaseModelImpl<HRUserTask>
	implements HRUserTask {
	public HRUserTaskClp() {
	}

	public Class<?> getModelClass() {
		return HRUserTask.class;
	}

	public String getModelClassName() {
		return HRUserTask.class.getName();
	}

	public long getPrimaryKey() {
		return _hrUserTaskId;
	}

	public void setPrimaryKey(long primaryKey) {
		setHrUserTaskId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrUserTaskId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getHrUserTaskId() {
		return _hrUserTaskId;
	}

	public void setHrUserTaskId(long hrUserTaskId) {
		_hrUserTaskId = hrUserTaskId;
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

	public long getHrBillabilityId() {
		return _hrBillabilityId;
	}

	public void setHrBillabilityId(long hrBillabilityId) {
		_hrBillabilityId = hrBillabilityId;
	}

	public long getHrTaskId() {
		return _hrTaskId;
	}

	public void setHrTaskId(long hrTaskId) {
		_hrTaskId = hrTaskId;
	}

	public long getHrTimesheetId() {
		return _hrTimesheetId;
	}

	public void setHrTimesheetId(long hrTimesheetId) {
		_hrTimesheetId = hrTimesheetId;
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

	@Override
	public HRUserTask toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRUserTask)Proxy.newProxyInstance(HRUserTask.class.getClassLoader(),
				new Class[] { HRUserTask.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	@Override
	public Object clone() {
		HRUserTaskClp clone = new HRUserTaskClp();

		clone.setHrUserTaskId(getHrUserTaskId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setHrBillabilityId(getHrBillabilityId());
		clone.setHrTaskId(getHrTaskId());
		clone.setHrTimesheetId(getHrTimesheetId());
		clone.setHrUserId(getHrUserId());

		return clone;
	}

	public int compareTo(HRUserTask hrUserTask) {
		long primaryKey = hrUserTask.getPrimaryKey();

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

		HRUserTaskClp hrUserTask = null;

		try {
			hrUserTask = (HRUserTaskClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = hrUserTask.getPrimaryKey();

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

		sb.append("{hrUserTaskId=");
		sb.append(getHrUserTaskId());
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
		sb.append(", hrBillabilityId=");
		sb.append(getHrBillabilityId());
		sb.append(", hrTaskId=");
		sb.append(getHrTaskId());
		sb.append(", hrTimesheetId=");
		sb.append(getHrTimesheetId());
		sb.append(", hrUserId=");
		sb.append(getHrUserId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRUserTask");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrUserTaskId</column-name><column-value><![CDATA[");
		sb.append(getHrUserTaskId());
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
			"<column><column-name>hrBillabilityId</column-name><column-value><![CDATA[");
		sb.append(getHrBillabilityId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrTaskId</column-name><column-value><![CDATA[");
		sb.append(getHrTaskId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrTimesheetId</column-name><column-value><![CDATA[");
		sb.append(getHrTimesheetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrUserId</column-name><column-value><![CDATA[");
		sb.append(getHrUserId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrUserTaskId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrBillabilityId;
	private long _hrTaskId;
	private long _hrTimesheetId;
	private long _hrUserId;
	private String _hrUserUuid;
}