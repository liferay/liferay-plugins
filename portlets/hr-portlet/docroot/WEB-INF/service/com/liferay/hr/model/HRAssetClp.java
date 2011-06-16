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
public class HRAssetClp extends BaseModelImpl<HRAsset> implements HRAsset {
	public HRAssetClp() {
	}

	public Class<?> getModelClass() {
		return HRAsset.class;
	}

	public String getModelClassName() {
		return HRAsset.class.getName();
	}

	public long getPrimaryKey() {
		return _hrAssetId;
	}

	public void setPrimaryKey(long primaryKey) {
		setHrAssetId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrAssetId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getHrAssetId() {
		return _hrAssetId;
	}

	public void setHrAssetId(long hrAssetId) {
		_hrAssetId = hrAssetId;
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

	public long getHrAssetDefinitionId() {
		return _hrAssetDefinitionId;
	}

	public void setHrAssetDefinitionId(long hrAssetDefinitionId) {
		_hrAssetDefinitionId = hrAssetDefinitionId;
	}

	public long getHrAssetTypeId() {
		return _hrAssetTypeId;
	}

	public void setHrAssetTypeId(long hrAssetTypeId) {
		_hrAssetTypeId = hrAssetTypeId;
	}

	public String getSerialNumber() {
		return _serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		_serialNumber = serialNumber;
	}

	public Date getInactiveDate() {
		return _inactiveDate;
	}

	public void setInactiveDate(Date inactiveDate) {
		_inactiveDate = inactiveDate;
	}

	@Override
	public HRAsset toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRAsset)Proxy.newProxyInstance(HRAsset.class.getClassLoader(),
				new Class[] { HRAsset.class }, new AutoEscapeBeanHandler(this));
		}
	}

	@Override
	public Object clone() {
		HRAssetClp clone = new HRAssetClp();

		clone.setHrAssetId(getHrAssetId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setHrAssetDefinitionId(getHrAssetDefinitionId());
		clone.setHrAssetTypeId(getHrAssetTypeId());
		clone.setSerialNumber(getSerialNumber());
		clone.setInactiveDate(getInactiveDate());

		return clone;
	}

	public int compareTo(HRAsset hrAsset) {
		long primaryKey = hrAsset.getPrimaryKey();

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

		HRAssetClp hrAsset = null;

		try {
			hrAsset = (HRAssetClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = hrAsset.getPrimaryKey();

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

		sb.append("{hrAssetId=");
		sb.append(getHrAssetId());
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
		sb.append(", hrAssetDefinitionId=");
		sb.append(getHrAssetDefinitionId());
		sb.append(", hrAssetTypeId=");
		sb.append(getHrAssetTypeId());
		sb.append(", serialNumber=");
		sb.append(getSerialNumber());
		sb.append(", inactiveDate=");
		sb.append(getInactiveDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRAsset");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrAssetId</column-name><column-value><![CDATA[");
		sb.append(getHrAssetId());
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
			"<column><column-name>hrAssetDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getHrAssetDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrAssetTypeId</column-name><column-value><![CDATA[");
		sb.append(getHrAssetTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serialNumber</column-name><column-value><![CDATA[");
		sb.append(getSerialNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inactiveDate</column-name><column-value><![CDATA[");
		sb.append(getInactiveDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrAssetId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrAssetDefinitionId;
	private long _hrAssetTypeId;
	private String _serialNumber;
	private Date _inactiveDate;
}