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

package com.liferay.chat.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.chat.service.ClpSerializer;
import com.liferay.chat.service.StatusLocalServiceUtil;

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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class StatusClp extends BaseModelImpl<Status> implements Status {
	public StatusClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Status.class;
	}

	@Override
	public String getModelClassName() {
		return Status.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _statusId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStatusId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _statusId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("statusId", getStatusId());
		attributes.put("userId", getUserId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("online", getOnline());
		attributes.put("awake", getAwake());
		attributes.put("activePanelIds", getActivePanelIds());
		attributes.put("message", getMessage());
		attributes.put("playSound", getPlaySound());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long statusId = (Long)attributes.get("statusId");

		if (statusId != null) {
			setStatusId(statusId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long modifiedDate = (Long)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean online = (Boolean)attributes.get("online");

		if (online != null) {
			setOnline(online);
		}

		Boolean awake = (Boolean)attributes.get("awake");

		if (awake != null) {
			setAwake(awake);
		}

		String activePanelIds = (String)attributes.get("activePanelIds");

		if (activePanelIds != null) {
			setActivePanelIds(activePanelIds);
		}

		String message = (String)attributes.get("message");

		if (message != null) {
			setMessage(message);
		}

		Boolean playSound = (Boolean)attributes.get("playSound");

		if (playSound != null) {
			setPlaySound(playSound);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getStatusId() {
		return _statusId;
	}

	@Override
	public void setStatusId(long statusId) {
		_statusId = statusId;

		if (_statusRemoteModel != null) {
			try {
				Class<?> clazz = _statusRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusId", long.class);

				method.invoke(_statusRemoteModel, statusId);
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

		if (_statusRemoteModel != null) {
			try {
				Class<?> clazz = _statusRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_statusRemoteModel, userId);
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
	public long getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(long modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_statusRemoteModel != null) {
			try {
				Class<?> clazz = _statusRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", long.class);

				method.invoke(_statusRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getOnline() {
		return _online;
	}

	@Override
	public boolean isOnline() {
		return _online;
	}

	@Override
	public void setOnline(boolean online) {
		_online = online;

		if (_statusRemoteModel != null) {
			try {
				Class<?> clazz = _statusRemoteModel.getClass();

				Method method = clazz.getMethod("setOnline", boolean.class);

				method.invoke(_statusRemoteModel, online);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAwake() {
		return _awake;
	}

	@Override
	public boolean isAwake() {
		return _awake;
	}

	@Override
	public void setAwake(boolean awake) {
		_awake = awake;

		if (_statusRemoteModel != null) {
			try {
				Class<?> clazz = _statusRemoteModel.getClass();

				Method method = clazz.getMethod("setAwake", boolean.class);

				method.invoke(_statusRemoteModel, awake);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getActivePanelIds() {
		return _activePanelIds;
	}

	@Override
	public void setActivePanelIds(String activePanelIds) {
		_activePanelIds = activePanelIds;

		if (_statusRemoteModel != null) {
			try {
				Class<?> clazz = _statusRemoteModel.getClass();

				Method method = clazz.getMethod("setActivePanelIds",
						String.class);

				method.invoke(_statusRemoteModel, activePanelIds);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getMessage() {
		return _message;
	}

	@Override
	public void setMessage(String message) {
		_message = message;

		if (_statusRemoteModel != null) {
			try {
				Class<?> clazz = _statusRemoteModel.getClass();

				Method method = clazz.getMethod("setMessage", String.class);

				method.invoke(_statusRemoteModel, message);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getPlaySound() {
		return _playSound;
	}

	@Override
	public boolean isPlaySound() {
		return _playSound;
	}

	@Override
	public void setPlaySound(boolean playSound) {
		_playSound = playSound;

		if (_statusRemoteModel != null) {
			try {
				Class<?> clazz = _statusRemoteModel.getClass();

				Method method = clazz.getMethod("setPlaySound", boolean.class);

				method.invoke(_statusRemoteModel, playSound);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getStatusRemoteModel() {
		return _statusRemoteModel;
	}

	public void setStatusRemoteModel(BaseModel<?> statusRemoteModel) {
		_statusRemoteModel = statusRemoteModel;
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

		Class<?> remoteModelClass = _statusRemoteModel.getClass();

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

		Object returnValue = method.invoke(_statusRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			StatusLocalServiceUtil.addStatus(this);
		}
		else {
			StatusLocalServiceUtil.updateStatus(this);
		}
	}

	@Override
	public Status toEscapedModel() {
		return (Status)ProxyUtil.newProxyInstance(Status.class.getClassLoader(),
			new Class[] { Status.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		StatusClp clone = new StatusClp();

		clone.setStatusId(getStatusId());
		clone.setUserId(getUserId());
		clone.setModifiedDate(getModifiedDate());
		clone.setOnline(getOnline());
		clone.setAwake(getAwake());
		clone.setActivePanelIds(getActivePanelIds());
		clone.setMessage(getMessage());
		clone.setPlaySound(getPlaySound());

		return clone;
	}

	@Override
	public int compareTo(Status status) {
		long primaryKey = status.getPrimaryKey();

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

		if (!(obj instanceof StatusClp)) {
			return false;
		}

		StatusClp status = (StatusClp)obj;

		long primaryKey = status.getPrimaryKey();

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
		StringBundler sb = new StringBundler(17);

		sb.append("{statusId=");
		sb.append(getStatusId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", online=");
		sb.append(getOnline());
		sb.append(", awake=");
		sb.append(getAwake());
		sb.append(", activePanelIds=");
		sb.append(getActivePanelIds());
		sb.append(", message=");
		sb.append(getMessage());
		sb.append(", playSound=");
		sb.append(getPlaySound());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.chat.model.Status");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>statusId</column-name><column-value><![CDATA[");
		sb.append(getStatusId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>online</column-name><column-value><![CDATA[");
		sb.append(getOnline());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>awake</column-name><column-value><![CDATA[");
		sb.append(getAwake());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>activePanelIds</column-name><column-value><![CDATA[");
		sb.append(getActivePanelIds());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>message</column-name><column-value><![CDATA[");
		sb.append(getMessage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>playSound</column-name><column-value><![CDATA[");
		sb.append(getPlaySound());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _statusId;
	private long _userId;
	private long _modifiedDate;
	private boolean _online;
	private boolean _awake;
	private String _activePanelIds;
	private String _message;
	private boolean _playSound;
	private BaseModel<?> _statusRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.chat.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}