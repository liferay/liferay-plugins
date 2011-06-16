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
public class HRUserProjectClp extends BaseModelImpl<HRUserProject>
	implements HRUserProject {
	public HRUserProjectClp() {
	}

	public Class<?> getModelClass() {
		return HRUserProject.class;
	}

	public String getModelClassName() {
		return HRUserProject.class.getName();
	}

	public long getPrimaryKey() {
		return _hrUserProjectId;
	}

	public void setPrimaryKey(long primaryKey) {
		setHrUserProjectId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrUserProjectId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getHrUserProjectId() {
		return _hrUserProjectId;
	}

	public void setHrUserProjectId(long hrUserProjectId) {
		_hrUserProjectId = hrUserProjectId;
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

	public long getHrProjectBillingRateId() {
		return _hrProjectBillingRateId;
	}

	public void setHrProjectBillingRateId(long hrProjectBillingRateId) {
		_hrProjectBillingRateId = hrProjectBillingRateId;
	}

	public long getHrProjectId() {
		return _hrProjectId;
	}

	public void setHrProjectId(long hrProjectId) {
		_hrProjectId = hrProjectId;
	}

	public long getHrProjectRoleId() {
		return _hrProjectRoleId;
	}

	public void setHrProjectRoleId(long hrProjectRoleId) {
		_hrProjectRoleId = hrProjectRoleId;
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

	public double getActualRate() {
		return _actualRate;
	}

	public void setActualRate(double actualRate) {
		_actualRate = actualRate;
	}

	@Override
	public HRUserProject toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRUserProject)Proxy.newProxyInstance(HRUserProject.class.getClassLoader(),
				new Class[] { HRUserProject.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	@Override
	public Object clone() {
		HRUserProjectClp clone = new HRUserProjectClp();

		clone.setHrUserProjectId(getHrUserProjectId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setHrProjectBillingRateId(getHrProjectBillingRateId());
		clone.setHrProjectId(getHrProjectId());
		clone.setHrProjectRoleId(getHrProjectRoleId());
		clone.setHrUserId(getHrUserId());
		clone.setActualRate(getActualRate());

		return clone;
	}

	public int compareTo(HRUserProject hrUserProject) {
		long primaryKey = hrUserProject.getPrimaryKey();

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

		HRUserProjectClp hrUserProject = null;

		try {
			hrUserProject = (HRUserProjectClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = hrUserProject.getPrimaryKey();

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
		StringBundler sb = new StringBundler(25);

		sb.append("{hrUserProjectId=");
		sb.append(getHrUserProjectId());
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
		sb.append(", hrProjectBillingRateId=");
		sb.append(getHrProjectBillingRateId());
		sb.append(", hrProjectId=");
		sb.append(getHrProjectId());
		sb.append(", hrProjectRoleId=");
		sb.append(getHrProjectRoleId());
		sb.append(", hrUserId=");
		sb.append(getHrUserId());
		sb.append(", actualRate=");
		sb.append(getActualRate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRUserProject");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrUserProjectId</column-name><column-value><![CDATA[");
		sb.append(getHrUserProjectId());
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
			"<column><column-name>hrProjectBillingRateId</column-name><column-value><![CDATA[");
		sb.append(getHrProjectBillingRateId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrProjectId</column-name><column-value><![CDATA[");
		sb.append(getHrProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrProjectRoleId</column-name><column-value><![CDATA[");
		sb.append(getHrProjectRoleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrUserId</column-name><column-value><![CDATA[");
		sb.append(getHrUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualRate</column-name><column-value><![CDATA[");
		sb.append(getActualRate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrUserProjectId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrProjectBillingRateId;
	private long _hrProjectId;
	private long _hrProjectRoleId;
	private long _hrUserId;
	private String _hrUserUuid;
	private double _actualRate;
}