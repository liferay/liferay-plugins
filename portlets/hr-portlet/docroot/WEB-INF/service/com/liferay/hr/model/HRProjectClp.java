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
public class HRProjectClp extends BaseModelImpl<HRProject> implements HRProject {
	public HRProjectClp() {
	}

	public Class<?> getModelClass() {
		return HRProject.class;
	}

	public String getModelClassName() {
		return HRProject.class.getName();
	}

	public long getPrimaryKey() {
		return _hrProjectId;
	}

	public void setPrimaryKey(long primaryKey) {
		setHrProjectId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrProjectId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getHrProjectId() {
		return _hrProjectId;
	}

	public void setHrProjectId(long hrProjectId) {
		_hrProjectId = hrProjectId;
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

	public long getHrClientId() {
		return _hrClientId;
	}

	public void setHrClientId(long hrClientId) {
		_hrClientId = hrClientId;
	}

	public long getHrProjectStatusId() {
		return _hrProjectStatusId;
	}

	public void setHrProjectStatusId(long hrProjectStatusId) {
		_hrProjectStatusId = hrProjectStatusId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getEstimatedStartDate() {
		return _estimatedStartDate;
	}

	public void setEstimatedStartDate(Date estimatedStartDate) {
		_estimatedStartDate = estimatedStartDate;
	}

	public Date getEstimatedEndDate() {
		return _estimatedEndDate;
	}

	public void setEstimatedEndDate(Date estimatedEndDate) {
		_estimatedEndDate = estimatedEndDate;
	}

	public double getEstimatedHours() {
		return _estimatedHours;
	}

	public void setEstimatedHours(double estimatedHours) {
		_estimatedHours = estimatedHours;
	}

	public double getEstimatedHoursCost() {
		return _estimatedHoursCost;
	}

	public void setEstimatedHoursCost(double estimatedHoursCost) {
		_estimatedHoursCost = estimatedHoursCost;
	}

	public String getEstimatedHoursCostCurrencyCode() {
		return _estimatedHoursCostCurrencyCode;
	}

	public void setEstimatedHoursCostCurrencyCode(
		String estimatedHoursCostCurrencyCode) {
		_estimatedHoursCostCurrencyCode = estimatedHoursCostCurrencyCode;
	}

	public double getEstimatedExpenses() {
		return _estimatedExpenses;
	}

	public void setEstimatedExpenses(double estimatedExpenses) {
		_estimatedExpenses = estimatedExpenses;
	}

	public String getEstimatedExpensesCurrencyCode() {
		return _estimatedExpensesCurrencyCode;
	}

	public void setEstimatedExpensesCurrencyCode(
		String estimatedExpensesCurrencyCode) {
		_estimatedExpensesCurrencyCode = estimatedExpensesCurrencyCode;
	}

	public Date getActualStartDate() {
		return _actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		_actualStartDate = actualStartDate;
	}

	public Date getActualEndDate() {
		return _actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		_actualEndDate = actualEndDate;
	}

	public double getActualHours() {
		return _actualHours;
	}

	public void setActualHours(double actualHours) {
		_actualHours = actualHours;
	}

	public double getActualHoursCost() {
		return _actualHoursCost;
	}

	public void setActualHoursCost(double actualHoursCost) {
		_actualHoursCost = actualHoursCost;
	}

	public String getActualHoursCostCurrencyCode() {
		return _actualHoursCostCurrencyCode;
	}

	public void setActualHoursCostCurrencyCode(
		String actualHoursCostCurrencyCode) {
		_actualHoursCostCurrencyCode = actualHoursCostCurrencyCode;
	}

	public double getActualExpenses() {
		return _actualExpenses;
	}

	public void setActualExpenses(double actualExpenses) {
		_actualExpenses = actualExpenses;
	}

	public double getActualExpensesCurrencyCode() {
		return _actualExpensesCurrencyCode;
	}

	public void setActualExpensesCurrencyCode(double actualExpensesCurrencyCode) {
		_actualExpensesCurrencyCode = actualExpensesCurrencyCode;
	}

	public HRProject toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRProject)Proxy.newProxyInstance(HRProject.class.getClassLoader(),
				new Class[] { HRProject.class }, new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		HRProjectClp clone = new HRProjectClp();

		clone.setHrProjectId(getHrProjectId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setHrClientId(getHrClientId());
		clone.setHrProjectStatusId(getHrProjectStatusId());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setEstimatedStartDate(getEstimatedStartDate());
		clone.setEstimatedEndDate(getEstimatedEndDate());
		clone.setEstimatedHours(getEstimatedHours());
		clone.setEstimatedHoursCost(getEstimatedHoursCost());
		clone.setEstimatedHoursCostCurrencyCode(getEstimatedHoursCostCurrencyCode());
		clone.setEstimatedExpenses(getEstimatedExpenses());
		clone.setEstimatedExpensesCurrencyCode(getEstimatedExpensesCurrencyCode());
		clone.setActualStartDate(getActualStartDate());
		clone.setActualEndDate(getActualEndDate());
		clone.setActualHours(getActualHours());
		clone.setActualHoursCost(getActualHoursCost());
		clone.setActualHoursCostCurrencyCode(getActualHoursCostCurrencyCode());
		clone.setActualExpenses(getActualExpenses());
		clone.setActualExpensesCurrencyCode(getActualExpensesCurrencyCode());

		return clone;
	}

	public int compareTo(HRProject hrProject) {
		long primaryKey = hrProject.getPrimaryKey();

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

		HRProjectClp hrProject = null;

		try {
			hrProject = (HRProjectClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = hrProject.getPrimaryKey();

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
		StringBundler sb = new StringBundler(51);

		sb.append("{hrProjectId=");
		sb.append(getHrProjectId());
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
		sb.append(", hrClientId=");
		sb.append(getHrClientId());
		sb.append(", hrProjectStatusId=");
		sb.append(getHrProjectStatusId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", estimatedStartDate=");
		sb.append(getEstimatedStartDate());
		sb.append(", estimatedEndDate=");
		sb.append(getEstimatedEndDate());
		sb.append(", estimatedHours=");
		sb.append(getEstimatedHours());
		sb.append(", estimatedHoursCost=");
		sb.append(getEstimatedHoursCost());
		sb.append(", estimatedHoursCostCurrencyCode=");
		sb.append(getEstimatedHoursCostCurrencyCode());
		sb.append(", estimatedExpenses=");
		sb.append(getEstimatedExpenses());
		sb.append(", estimatedExpensesCurrencyCode=");
		sb.append(getEstimatedExpensesCurrencyCode());
		sb.append(", actualStartDate=");
		sb.append(getActualStartDate());
		sb.append(", actualEndDate=");
		sb.append(getActualEndDate());
		sb.append(", actualHours=");
		sb.append(getActualHours());
		sb.append(", actualHoursCost=");
		sb.append(getActualHoursCost());
		sb.append(", actualHoursCostCurrencyCode=");
		sb.append(getActualHoursCostCurrencyCode());
		sb.append(", actualExpenses=");
		sb.append(getActualExpenses());
		sb.append(", actualExpensesCurrencyCode=");
		sb.append(getActualExpensesCurrencyCode());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(79);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRProject");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrProjectId</column-name><column-value><![CDATA[");
		sb.append(getHrProjectId());
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
			"<column><column-name>hrClientId</column-name><column-value><![CDATA[");
		sb.append(getHrClientId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrProjectStatusId</column-name><column-value><![CDATA[");
		sb.append(getHrProjectStatusId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>estimatedStartDate</column-name><column-value><![CDATA[");
		sb.append(getEstimatedStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>estimatedEndDate</column-name><column-value><![CDATA[");
		sb.append(getEstimatedEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>estimatedHours</column-name><column-value><![CDATA[");
		sb.append(getEstimatedHours());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>estimatedHoursCost</column-name><column-value><![CDATA[");
		sb.append(getEstimatedHoursCost());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>estimatedHoursCostCurrencyCode</column-name><column-value><![CDATA[");
		sb.append(getEstimatedHoursCostCurrencyCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>estimatedExpenses</column-name><column-value><![CDATA[");
		sb.append(getEstimatedExpenses());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>estimatedExpensesCurrencyCode</column-name><column-value><![CDATA[");
		sb.append(getEstimatedExpensesCurrencyCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualStartDate</column-name><column-value><![CDATA[");
		sb.append(getActualStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualEndDate</column-name><column-value><![CDATA[");
		sb.append(getActualEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualHours</column-name><column-value><![CDATA[");
		sb.append(getActualHours());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualHoursCost</column-name><column-value><![CDATA[");
		sb.append(getActualHoursCost());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualHoursCostCurrencyCode</column-name><column-value><![CDATA[");
		sb.append(getActualHoursCostCurrencyCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualExpenses</column-name><column-value><![CDATA[");
		sb.append(getActualExpenses());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualExpensesCurrencyCode</column-name><column-value><![CDATA[");
		sb.append(getActualExpensesCurrencyCode());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrProjectId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrClientId;
	private long _hrProjectStatusId;
	private String _name;
	private String _description;
	private Date _estimatedStartDate;
	private Date _estimatedEndDate;
	private double _estimatedHours;
	private double _estimatedHoursCost;
	private String _estimatedHoursCostCurrencyCode;
	private double _estimatedExpenses;
	private String _estimatedExpensesCurrencyCode;
	private Date _actualStartDate;
	private Date _actualEndDate;
	private double _actualHours;
	private double _actualHoursCost;
	private String _actualHoursCostCurrencyCode;
	private double _actualExpenses;
	private double _actualExpensesCurrencyCode;
}