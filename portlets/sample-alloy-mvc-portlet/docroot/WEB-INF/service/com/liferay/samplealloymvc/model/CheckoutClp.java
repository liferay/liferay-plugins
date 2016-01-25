/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.samplealloymvc.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.samplealloymvc.service.CheckoutLocalServiceUtil;
import com.liferay.samplealloymvc.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class CheckoutClp extends BaseModelImpl<Checkout> implements Checkout {
	public CheckoutClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Checkout.class;
	}

	@Override
	public String getModelClassName() {
		return Checkout.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _checkoutId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCheckoutId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _checkoutId;
	}

	@Override
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getCheckoutId() {
		return _checkoutId;
	}

	@Override
	public void setCheckoutId(long checkoutId) {
		_checkoutId = checkoutId;

		if (_checkoutRemoteModel != null) {
			try {
				Class<?> clazz = _checkoutRemoteModel.getClass();

				Method method = clazz.getMethod("setCheckoutId", long.class);

				method.invoke(_checkoutRemoteModel, checkoutId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_checkoutRemoteModel != null) {
			try {
				Class<?> clazz = _checkoutRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_checkoutRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_checkoutRemoteModel != null) {
			try {
				Class<?> clazz = _checkoutRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_checkoutRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_checkoutRemoteModel != null) {
			try {
				Class<?> clazz = _checkoutRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_checkoutRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_checkoutRemoteModel != null) {
			try {
				Class<?> clazz = _checkoutRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_checkoutRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_checkoutRemoteModel != null) {
			try {
				Class<?> clazz = _checkoutRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_checkoutRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAssetId() {
		return _assetId;
	}

	@Override
	public void setAssetId(long assetId) {
		_assetId = assetId;

		if (_checkoutRemoteModel != null) {
			try {
				Class<?> clazz = _checkoutRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetId", long.class);

				method.invoke(_checkoutRemoteModel, assetId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCheckOutDate() {
		return _checkOutDate;
	}

	@Override
	public void setCheckOutDate(Date checkOutDate) {
		_checkOutDate = checkOutDate;

		if (_checkoutRemoteModel != null) {
			try {
				Class<?> clazz = _checkoutRemoteModel.getClass();

				Method method = clazz.getMethod("setCheckOutDate", Date.class);

				method.invoke(_checkoutRemoteModel, checkOutDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getExpectedCheckInDate() {
		return _expectedCheckInDate;
	}

	@Override
	public void setExpectedCheckInDate(Date expectedCheckInDate) {
		_expectedCheckInDate = expectedCheckInDate;

		if (_checkoutRemoteModel != null) {
			try {
				Class<?> clazz = _checkoutRemoteModel.getClass();

				Method method = clazz.getMethod("setExpectedCheckInDate",
						Date.class);

				method.invoke(_checkoutRemoteModel, expectedCheckInDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getActualCheckInDate() {
		return _actualCheckInDate;
	}

	@Override
	public void setActualCheckInDate(Date actualCheckInDate) {
		_actualCheckInDate = actualCheckInDate;

		if (_checkoutRemoteModel != null) {
			try {
				Class<?> clazz = _checkoutRemoteModel.getClass();

				Method method = clazz.getMethod("setActualCheckInDate",
						Date.class);

				method.invoke(_checkoutRemoteModel, actualCheckInDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getCheckoutRemoteModel() {
		return _checkoutRemoteModel;
	}

	public void setCheckoutRemoteModel(BaseModel<?> checkoutRemoteModel) {
		_checkoutRemoteModel = checkoutRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _checkoutRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_checkoutRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
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

	@Override
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
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CheckoutClp)) {
			return false;
		}

		CheckoutClp checkout = (CheckoutClp)obj;

		long primaryKey = checkout.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
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

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.samplealloymvc.model.Checkout");
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
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _assetId;
	private Date _checkOutDate;
	private Date _expectedCheckInDate;
	private Date _actualCheckInDate;
	private BaseModel<?> _checkoutRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.samplealloymvc.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}