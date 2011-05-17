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
public class HRAssetCheckoutClp extends BaseModelImpl<HRAssetCheckout>
	implements HRAssetCheckout {
	public HRAssetCheckoutClp() {
	}

	public Class<?> getModelClass() {
		return HRAssetCheckout.class;
	}

	public String getModelClassName() {
		return HRAssetCheckout.class.getName();
	}

	public long getPrimaryKey() {
		return _hrAssetCheckoutId;
	}

	public void setPrimaryKey(long primaryKey) {
		setHrAssetCheckoutId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrAssetCheckoutId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getHrAssetCheckoutId() {
		return _hrAssetCheckoutId;
	}

	public void setHrAssetCheckoutId(long hrAssetCheckoutId) {
		_hrAssetCheckoutId = hrAssetCheckoutId;
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

	public long getHrAssetId() {
		return _hrAssetId;
	}

	public void setHrAssetId(long hrAssetId) {
		_hrAssetId = hrAssetId;
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

	public Date getCheckoutDate() {
		return _checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		_checkoutDate = checkoutDate;
	}

	public Date getExpectedCheckInDate() {
		return _expectedCheckInDate;
	}

	public void setExpectedCheckInDate(Date expectedCheckInDate) {
		_expectedCheckInDate = expectedCheckInDate;
	}

	public Date getActualCheckInDate() {
		return _actualCheckInDate;
	}

	public void setActualCheckInDate(Date actualCheckInDate) {
		_actualCheckInDate = actualCheckInDate;
	}

	public HRAssetCheckout toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (HRAssetCheckout)Proxy.newProxyInstance(HRAssetCheckout.class.getClassLoader(),
				new Class[] { HRAssetCheckout.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		HRAssetCheckoutClp clone = new HRAssetCheckoutClp();

		clone.setHrAssetCheckoutId(getHrAssetCheckoutId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setHrAssetId(getHrAssetId());
		clone.setHrUserId(getHrUserId());
		clone.setCheckoutDate(getCheckoutDate());
		clone.setExpectedCheckInDate(getExpectedCheckInDate());
		clone.setActualCheckInDate(getActualCheckInDate());

		return clone;
	}

	public int compareTo(HRAssetCheckout hrAssetCheckout) {
		long primaryKey = hrAssetCheckout.getPrimaryKey();

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

		HRAssetCheckoutClp hrAssetCheckout = null;

		try {
			hrAssetCheckout = (HRAssetCheckoutClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = hrAssetCheckout.getPrimaryKey();

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

		sb.append("{hrAssetCheckoutId=");
		sb.append(getHrAssetCheckoutId());
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
		sb.append(", hrAssetId=");
		sb.append(getHrAssetId());
		sb.append(", hrUserId=");
		sb.append(getHrUserId());
		sb.append(", checkoutDate=");
		sb.append(getCheckoutDate());
		sb.append(", expectedCheckInDate=");
		sb.append(getExpectedCheckInDate());
		sb.append(", actualCheckInDate=");
		sb.append(getActualCheckInDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRAssetCheckout");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrAssetCheckoutId</column-name><column-value><![CDATA[");
		sb.append(getHrAssetCheckoutId());
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
			"<column><column-name>hrAssetId</column-name><column-value><![CDATA[");
		sb.append(getHrAssetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrUserId</column-name><column-value><![CDATA[");
		sb.append(getHrUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>checkoutDate</column-name><column-value><![CDATA[");
		sb.append(getCheckoutDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>expectedCheckInDate</column-name><column-value><![CDATA[");
		sb.append(getExpectedCheckInDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualCheckInDate</column-name><column-value><![CDATA[");
		sb.append(getActualCheckInDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _hrAssetCheckoutId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrAssetId;
	private long _hrUserId;
	private String _hrUserUuid;
	private Date _checkoutDate;
	private Date _expectedCheckInDate;
	private Date _actualCheckInDate;
}