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

package com.liferay.pushnotifications.model;

import aQute.bnd.annotation.ProviderType;

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

import com.liferay.pushnotifications.service.ClpSerializer;
import com.liferay.pushnotifications.service.PushNotificationsDeviceLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bruno Farache
 */
@ProviderType
public class PushNotificationsDeviceClp extends BaseModelImpl<PushNotificationsDevice>
	implements PushNotificationsDevice {
	public PushNotificationsDeviceClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return PushNotificationsDevice.class;
	}

	@Override
	public String getModelClassName() {
		return PushNotificationsDevice.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _pushNotificationsDeviceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPushNotificationsDeviceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _pushNotificationsDeviceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("pushNotificationsDeviceId",
			getPushNotificationsDeviceId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("platform", getPlatform());
		attributes.put("token", getToken());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long pushNotificationsDeviceId = (Long)attributes.get(
				"pushNotificationsDeviceId");

		if (pushNotificationsDeviceId != null) {
			setPushNotificationsDeviceId(pushNotificationsDeviceId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String platform = (String)attributes.get("platform");

		if (platform != null) {
			setPlatform(platform);
		}

		String token = (String)attributes.get("token");

		if (token != null) {
			setToken(token);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getPushNotificationsDeviceId() {
		return _pushNotificationsDeviceId;
	}

	@Override
	public void setPushNotificationsDeviceId(long pushNotificationsDeviceId) {
		_pushNotificationsDeviceId = pushNotificationsDeviceId;

		if (_pushNotificationsDeviceRemoteModel != null) {
			try {
				Class<?> clazz = _pushNotificationsDeviceRemoteModel.getClass();

				Method method = clazz.getMethod("setPushNotificationsDeviceId",
						long.class);

				method.invoke(_pushNotificationsDeviceRemoteModel,
					pushNotificationsDeviceId);
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

		if (_pushNotificationsDeviceRemoteModel != null) {
			try {
				Class<?> clazz = _pushNotificationsDeviceRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_pushNotificationsDeviceRemoteModel, userId);
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
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_pushNotificationsDeviceRemoteModel != null) {
			try {
				Class<?> clazz = _pushNotificationsDeviceRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_pushNotificationsDeviceRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPlatform() {
		return _platform;
	}

	@Override
	public void setPlatform(String platform) {
		_platform = platform;

		if (_pushNotificationsDeviceRemoteModel != null) {
			try {
				Class<?> clazz = _pushNotificationsDeviceRemoteModel.getClass();

				Method method = clazz.getMethod("setPlatform", String.class);

				method.invoke(_pushNotificationsDeviceRemoteModel, platform);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getToken() {
		return _token;
	}

	@Override
	public void setToken(String token) {
		_token = token;

		if (_pushNotificationsDeviceRemoteModel != null) {
			try {
				Class<?> clazz = _pushNotificationsDeviceRemoteModel.getClass();

				Method method = clazz.getMethod("setToken", String.class);

				method.invoke(_pushNotificationsDeviceRemoteModel, token);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getPushNotificationsDeviceRemoteModel() {
		return _pushNotificationsDeviceRemoteModel;
	}

	public void setPushNotificationsDeviceRemoteModel(
		BaseModel<?> pushNotificationsDeviceRemoteModel) {
		_pushNotificationsDeviceRemoteModel = pushNotificationsDeviceRemoteModel;
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

		Class<?> remoteModelClass = _pushNotificationsDeviceRemoteModel.getClass();

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

		Object returnValue = method.invoke(_pushNotificationsDeviceRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			PushNotificationsDeviceLocalServiceUtil.addPushNotificationsDevice(this);
		}
		else {
			PushNotificationsDeviceLocalServiceUtil.updatePushNotificationsDevice(this);
		}
	}

	@Override
	public PushNotificationsDevice toEscapedModel() {
		return (PushNotificationsDevice)ProxyUtil.newProxyInstance(PushNotificationsDevice.class.getClassLoader(),
			new Class[] { PushNotificationsDevice.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		PushNotificationsDeviceClp clone = new PushNotificationsDeviceClp();

		clone.setPushNotificationsDeviceId(getPushNotificationsDeviceId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setPlatform(getPlatform());
		clone.setToken(getToken());

		return clone;
	}

	@Override
	public int compareTo(PushNotificationsDevice pushNotificationsDevice) {
		long primaryKey = pushNotificationsDevice.getPrimaryKey();

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

		if (!(obj instanceof PushNotificationsDeviceClp)) {
			return false;
		}

		PushNotificationsDeviceClp pushNotificationsDevice = (PushNotificationsDeviceClp)obj;

		long primaryKey = pushNotificationsDevice.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{pushNotificationsDeviceId=");
		sb.append(getPushNotificationsDeviceId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", platform=");
		sb.append(getPlatform());
		sb.append(", token=");
		sb.append(getToken());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.pushnotifications.model.PushNotificationsDevice");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>pushNotificationsDeviceId</column-name><column-value><![CDATA[");
		sb.append(getPushNotificationsDeviceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>platform</column-name><column-value><![CDATA[");
		sb.append(getPlatform());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>token</column-name><column-value><![CDATA[");
		sb.append(getToken());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _pushNotificationsDeviceId;
	private long _userId;
	private Date _createDate;
	private String _platform;
	private String _token;
	private BaseModel<?> _pushNotificationsDeviceRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.pushnotifications.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}