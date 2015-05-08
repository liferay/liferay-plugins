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

package com.liferay.ams.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.ams.service.AssetLocalServiceUtil;
import com.liferay.ams.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class AssetClp extends BaseModelImpl<Asset> implements Asset {
	public AssetClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Asset.class;
	}

	@Override
	public String getModelClassName() {
		return Asset.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _assetId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAssetId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetId", getAssetId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("definitionId", getDefinitionId());
		attributes.put("serialNumber", getSerialNumber());
		attributes.put("inactiveDate", getInactiveDate());
		attributes.put("active", getActive());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetId = (Long)attributes.get("assetId");

		if (assetId != null) {
			setAssetId(assetId);
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

		Long definitionId = (Long)attributes.get("definitionId");

		if (definitionId != null) {
			setDefinitionId(definitionId);
		}

		String serialNumber = (String)attributes.get("serialNumber");

		if (serialNumber != null) {
			setSerialNumber(serialNumber);
		}

		Date inactiveDate = (Date)attributes.get("inactiveDate");

		if (inactiveDate != null) {
			setInactiveDate(inactiveDate);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getAssetId() {
		return _assetId;
	}

	@Override
	public void setAssetId(long assetId) {
		_assetId = assetId;

		if (_assetRemoteModel != null) {
			try {
				Class<?> clazz = _assetRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetId", long.class);

				method.invoke(_assetRemoteModel, assetId);
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

		if (_assetRemoteModel != null) {
			try {
				Class<?> clazz = _assetRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_assetRemoteModel, companyId);
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

		if (_assetRemoteModel != null) {
			try {
				Class<?> clazz = _assetRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_assetRemoteModel, userId);
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

		if (_assetRemoteModel != null) {
			try {
				Class<?> clazz = _assetRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_assetRemoteModel, userName);
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

		if (_assetRemoteModel != null) {
			try {
				Class<?> clazz = _assetRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_assetRemoteModel, createDate);
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

		if (_assetRemoteModel != null) {
			try {
				Class<?> clazz = _assetRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_assetRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getDefinitionId() {
		return _definitionId;
	}

	@Override
	public void setDefinitionId(long definitionId) {
		_definitionId = definitionId;

		if (_assetRemoteModel != null) {
			try {
				Class<?> clazz = _assetRemoteModel.getClass();

				Method method = clazz.getMethod("setDefinitionId", long.class);

				method.invoke(_assetRemoteModel, definitionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSerialNumber() {
		return _serialNumber;
	}

	@Override
	public void setSerialNumber(String serialNumber) {
		_serialNumber = serialNumber;

		if (_assetRemoteModel != null) {
			try {
				Class<?> clazz = _assetRemoteModel.getClass();

				Method method = clazz.getMethod("setSerialNumber", String.class);

				method.invoke(_assetRemoteModel, serialNumber);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getInactiveDate() {
		return _inactiveDate;
	}

	@Override
	public void setInactiveDate(Date inactiveDate) {
		_inactiveDate = inactiveDate;

		if (_assetRemoteModel != null) {
			try {
				Class<?> clazz = _assetRemoteModel.getClass();

				Method method = clazz.getMethod("setInactiveDate", Date.class);

				method.invoke(_assetRemoteModel, inactiveDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getActive() {
		return _active;
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_active = active;

		if (_assetRemoteModel != null) {
			try {
				Class<?> clazz = _assetRemoteModel.getClass();

				Method method = clazz.getMethod("setActive", boolean.class);

				method.invoke(_assetRemoteModel, active);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAssetRemoteModel() {
		return _assetRemoteModel;
	}

	public void setAssetRemoteModel(BaseModel<?> assetRemoteModel) {
		_assetRemoteModel = assetRemoteModel;
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

		Class<?> remoteModelClass = _assetRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			AssetLocalServiceUtil.addAsset(this);
		}
		else {
			AssetLocalServiceUtil.updateAsset(this);
		}
	}

	@Override
	public Asset toEscapedModel() {
		return (Asset)ProxyUtil.newProxyInstance(Asset.class.getClassLoader(),
			new Class[] { Asset.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AssetClp clone = new AssetClp();

		clone.setAssetId(getAssetId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setDefinitionId(getDefinitionId());
		clone.setSerialNumber(getSerialNumber());
		clone.setInactiveDate(getInactiveDate());
		clone.setActive(getActive());

		return clone;
	}

	@Override
	public int compareTo(Asset asset) {
		long primaryKey = asset.getPrimaryKey();

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

		if (!(obj instanceof AssetClp)) {
			return false;
		}

		AssetClp asset = (AssetClp)obj;

		long primaryKey = asset.getPrimaryKey();

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

		sb.append("{assetId=");
		sb.append(getAssetId());
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
		sb.append(", definitionId=");
		sb.append(getDefinitionId());
		sb.append(", serialNumber=");
		sb.append(getSerialNumber());
		sb.append(", inactiveDate=");
		sb.append(getInactiveDate());
		sb.append(", active=");
		sb.append(getActive());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.ams.model.Asset");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>assetId</column-name><column-value><![CDATA[");
		sb.append(getAssetId());
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
			"<column><column-name>definitionId</column-name><column-value><![CDATA[");
		sb.append(getDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serialNumber</column-name><column-value><![CDATA[");
		sb.append(getSerialNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inactiveDate</column-name><column-value><![CDATA[");
		sb.append(getInactiveDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _assetId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _definitionId;
	private String _serialNumber;
	private Date _inactiveDate;
	private boolean _active;
	private BaseModel<?> _assetRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}