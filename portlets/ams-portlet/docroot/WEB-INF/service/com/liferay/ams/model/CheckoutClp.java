/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.ams.model;

import com.liferay.ams.service.CheckoutLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class CheckoutClp extends BaseModelImpl<Checkout> implements Checkout {
	public CheckoutClp() {
	}

	public Class<?> getModelClass() {
		return Checkout.class;
	}

	public String getModelClassName() {
		return Checkout.class.getName();
	}

	public long getPrimaryKey() {
		return _checkoutId;
	}

	public void setPrimaryKey(long primaryKey) {
		setCheckoutId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_checkoutId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("checkoutId", getCheckoutId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("assetId", getAssetId());
		attributes.put("checkOutDate", getCheckOutDate());
		attributes.put("expectedCheckInDate", getExpectedCheckInDate());
		attributes.put("actualCheckInDate", getActualCheckInDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long checkoutId = (Long)attributes.get("checkoutId");

		if (checkoutId != null) {
			setCheckoutId(checkoutId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long assetId = (Long)attributes.get("assetId");

		if (assetId != null) {
			setAssetId(assetId);
		}

		Date checkOutDate = (Date)attributes.get("checkOutDate");

		if (checkOutDate != null) {
			setCheckOutDate(checkOutDate);
		}

		Date expectedCheckInDate = (Date)attributes.get("expectedCheckInDate");

		if (expectedCheckInDate != null) {
			setExpectedCheckInDate(expectedCheckInDate);
		}

		Date actualCheckInDate = (Date)attributes.get("actualCheckInDate");

		if (actualCheckInDate != null) {
			setActualCheckInDate(actualCheckInDate);
		}
	}

	public long getCheckoutId() {
		return _checkoutId;
	}

	public void setCheckoutId(long checkoutId) {
		_checkoutId = checkoutId;
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

	public long getAssetId() {
		return _assetId;
	}

	public void setAssetId(long assetId) {
		_assetId = assetId;
	}

	public Date getCheckOutDate() {
		return _checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		_checkOutDate = checkOutDate;
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

	public BaseModel<?> getCheckoutRemoteModel() {
		return _checkoutRemoteModel;
	}

	public void setCheckoutRemoteModel(BaseModel<?> checkoutRemoteModel) {
		_checkoutRemoteModel = checkoutRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			CheckoutLocalServiceUtil.addCheckout(this);
		}
		else {
			CheckoutLocalServiceUtil.updateCheckout(this);
		}
	}

	@Override
	public Checkout toEscapedModel() {
		return (Checkout)ProxyUtil.newProxyInstance(Checkout.class.getClassLoader(),
			new Class[] { Checkout.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CheckoutClp clone = new CheckoutClp();

		clone.setCheckoutId(getCheckoutId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAssetId(getAssetId());
		clone.setCheckOutDate(getCheckOutDate());
		clone.setExpectedCheckInDate(getExpectedCheckInDate());
		clone.setActualCheckInDate(getActualCheckInDate());

		return clone;
	}

	public int compareTo(Checkout checkout) {
		long primaryKey = checkout.getPrimaryKey();

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

		CheckoutClp checkout = null;

		try {
			checkout = (CheckoutClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = checkout.getPrimaryKey();

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
		StringBundler sb = new StringBundler(21);

		sb.append("{checkoutId=");
		sb.append(getCheckoutId());
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
		sb.append(", assetId=");
		sb.append(getAssetId());
		sb.append(", checkOutDate=");
		sb.append(getCheckOutDate());
		sb.append(", expectedCheckInDate=");
		sb.append(getExpectedCheckInDate());
		sb.append(", actualCheckInDate=");
		sb.append(getActualCheckInDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.ams.model.Checkout");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>checkoutId</column-name><column-value><![CDATA[");
		sb.append(getCheckoutId());
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
			"<column><column-name>assetId</column-name><column-value><![CDATA[");
		sb.append(getAssetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>checkOutDate</column-name><column-value><![CDATA[");
		sb.append(getCheckOutDate());
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

	private long _checkoutId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _assetId;
	private Date _checkOutDate;
	private Date _expectedCheckInDate;
	private Date _actualCheckInDate;
	private BaseModel<?> _checkoutRemoteModel;
}