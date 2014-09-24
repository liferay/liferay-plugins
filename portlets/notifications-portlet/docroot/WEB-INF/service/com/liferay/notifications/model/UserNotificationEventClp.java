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

package com.liferay.notifications.model;

import com.liferay.notifications.service.ClpSerializer;
import com.liferay.notifications.service.UserNotificationEventLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class UserNotificationEventClp extends BaseModelImpl<UserNotificationEvent>
	implements UserNotificationEvent {
	public UserNotificationEventClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return UserNotificationEvent.class;
	}

	@Override
	public String getModelClassName() {
		return UserNotificationEvent.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _notificationEventId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setNotificationEventId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _notificationEventId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("notificationEventId", getNotificationEventId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userNotificationEventId", getUserNotificationEventId());
		attributes.put("timestamp", getTimestamp());
		attributes.put("delivered", getDelivered());
		attributes.put("actionRequired", getActionRequired());
		attributes.put("archived", getArchived());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long notificationEventId = (Long)attributes.get("notificationEventId");

		if (notificationEventId != null) {
			setNotificationEventId(notificationEventId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long userNotificationEventId = (Long)attributes.get(
				"userNotificationEventId");

		if (userNotificationEventId != null) {
			setUserNotificationEventId(userNotificationEventId);
		}

		Long timestamp = (Long)attributes.get("timestamp");

		if (timestamp != null) {
			setTimestamp(timestamp);
		}

		Boolean delivered = (Boolean)attributes.get("delivered");

		if (delivered != null) {
			setDelivered(delivered);
		}

		Boolean actionRequired = (Boolean)attributes.get("actionRequired");

		if (actionRequired != null) {
			setActionRequired(actionRequired);
		}

		Boolean archived = (Boolean)attributes.get("archived");

		if (archived != null) {
			setArchived(archived);
		}
	}

	@Override
	public long getNotificationEventId() {
		return _notificationEventId;
	}

	@Override
	public void setNotificationEventId(long notificationEventId) {
		_notificationEventId = notificationEventId;

		if (_userNotificationEventRemoteModel != null) {
			try {
				Class<?> clazz = _userNotificationEventRemoteModel.getClass();

				Method method = clazz.getMethod("setNotificationEventId",
						long.class);

				method.invoke(_userNotificationEventRemoteModel,
					notificationEventId);
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

		if (_userNotificationEventRemoteModel != null) {
			try {
				Class<?> clazz = _userNotificationEventRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_userNotificationEventRemoteModel, companyId);
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

		if (_userNotificationEventRemoteModel != null) {
			try {
				Class<?> clazz = _userNotificationEventRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_userNotificationEventRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public long getUserNotificationEventId() {
		return _userNotificationEventId;
	}

	@Override
	public void setUserNotificationEventId(long userNotificationEventId) {
		_userNotificationEventId = userNotificationEventId;

		if (_userNotificationEventRemoteModel != null) {
			try {
				Class<?> clazz = _userNotificationEventRemoteModel.getClass();

				Method method = clazz.getMethod("setUserNotificationEventId",
						long.class);

				method.invoke(_userNotificationEventRemoteModel,
					userNotificationEventId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTimestamp() {
		return _timestamp;
	}

	@Override
	public void setTimestamp(long timestamp) {
		_timestamp = timestamp;

		if (_userNotificationEventRemoteModel != null) {
			try {
				Class<?> clazz = _userNotificationEventRemoteModel.getClass();

				Method method = clazz.getMethod("setTimestamp", long.class);

				method.invoke(_userNotificationEventRemoteModel, timestamp);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getDelivered() {
		return _delivered;
	}

	@Override
	public boolean isDelivered() {
		return _delivered;
	}

	@Override
	public void setDelivered(boolean delivered) {
		_delivered = delivered;

		if (_userNotificationEventRemoteModel != null) {
			try {
				Class<?> clazz = _userNotificationEventRemoteModel.getClass();

				Method method = clazz.getMethod("setDelivered", boolean.class);

				method.invoke(_userNotificationEventRemoteModel, delivered);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getActionRequired() {
		return _actionRequired;
	}

	@Override
	public boolean isActionRequired() {
		return _actionRequired;
	}

	@Override
	public void setActionRequired(boolean actionRequired) {
		_actionRequired = actionRequired;

		if (_userNotificationEventRemoteModel != null) {
			try {
				Class<?> clazz = _userNotificationEventRemoteModel.getClass();

				Method method = clazz.getMethod("setActionRequired",
						boolean.class);

				method.invoke(_userNotificationEventRemoteModel, actionRequired);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getArchived() {
		return _archived;
	}

	@Override
	public boolean isArchived() {
		return _archived;
	}

	@Override
	public void setArchived(boolean archived) {
		_archived = archived;

		if (_userNotificationEventRemoteModel != null) {
			try {
				Class<?> clazz = _userNotificationEventRemoteModel.getClass();

				Method method = clazz.getMethod("setArchived", boolean.class);

				method.invoke(_userNotificationEventRemoteModel, archived);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getDeliverBy() {
		try {
			String methodName = "getDeliverBy";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Long returnObj = (Long)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public com.liferay.portal.model.UserNotificationEvent getUserNotificationEvent() {
		try {
			String methodName = "getUserNotificationEvent";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.model.UserNotificationEvent returnObj = (com.liferay.portal.model.UserNotificationEvent)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getPayload() {
		try {
			String methodName = "getPayload";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getType() {
		try {
			String methodName = "getType";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getUserNotificationEventRemoteModel() {
		return _userNotificationEventRemoteModel;
	}

	public void setUserNotificationEventRemoteModel(
		BaseModel<?> userNotificationEventRemoteModel) {
		_userNotificationEventRemoteModel = userNotificationEventRemoteModel;
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

		Class<?> remoteModelClass = _userNotificationEventRemoteModel.getClass();

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

		Object returnValue = method.invoke(_userNotificationEventRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			UserNotificationEventLocalServiceUtil.addUserNotificationEvent(this);
		}
		else {
			UserNotificationEventLocalServiceUtil.updateUserNotificationEvent(this);
		}
	}

	@Override
	public UserNotificationEvent toEscapedModel() {
		return (UserNotificationEvent)ProxyUtil.newProxyInstance(UserNotificationEvent.class.getClassLoader(),
			new Class[] { UserNotificationEvent.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		UserNotificationEventClp clone = new UserNotificationEventClp();

		clone.setNotificationEventId(getNotificationEventId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserNotificationEventId(getUserNotificationEventId());
		clone.setTimestamp(getTimestamp());
		clone.setDelivered(getDelivered());
		clone.setActionRequired(getActionRequired());
		clone.setArchived(getArchived());

		return clone;
	}

	@Override
	public int compareTo(UserNotificationEvent userNotificationEvent) {
		int value = 0;

		if (getTimestamp() < userNotificationEvent.getTimestamp()) {
			value = -1;
		}
		else if (getTimestamp() > userNotificationEvent.getTimestamp()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserNotificationEventClp)) {
			return false;
		}

		UserNotificationEventClp userNotificationEvent = (UserNotificationEventClp)obj;

		long primaryKey = userNotificationEvent.getPrimaryKey();

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
		StringBundler sb = new StringBundler(17);

		sb.append("{notificationEventId=");
		sb.append(getNotificationEventId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userNotificationEventId=");
		sb.append(getUserNotificationEventId());
		sb.append(", timestamp=");
		sb.append(getTimestamp());
		sb.append(", delivered=");
		sb.append(getDelivered());
		sb.append(", actionRequired=");
		sb.append(getActionRequired());
		sb.append(", archived=");
		sb.append(getArchived());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.notifications.model.UserNotificationEvent");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>notificationEventId</column-name><column-value><![CDATA[");
		sb.append(getNotificationEventId());
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
			"<column><column-name>userNotificationEventId</column-name><column-value><![CDATA[");
		sb.append(getUserNotificationEventId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>timestamp</column-name><column-value><![CDATA[");
		sb.append(getTimestamp());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>delivered</column-name><column-value><![CDATA[");
		sb.append(getDelivered());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actionRequired</column-name><column-value><![CDATA[");
		sb.append(getActionRequired());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>archived</column-name><column-value><![CDATA[");
		sb.append(getArchived());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _notificationEventId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private long _userNotificationEventId;
	private long _timestamp;
	private boolean _delivered;
	private boolean _actionRequired;
	private boolean _archived;
	private BaseModel<?> _userNotificationEventRemoteModel;
}