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
import com.liferay.pushnotifications.service.PushNotificationsEntryLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bruno Farache
 */
@ProviderType
public class PushNotificationsEntryClp extends BaseModelImpl<PushNotificationsEntry>
	implements PushNotificationsEntry {
	public PushNotificationsEntryClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return PushNotificationsEntry.class;
	}

	@Override
	public String getModelClassName() {
		return PushNotificationsEntry.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _pushNotificationsEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPushNotificationsEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _pushNotificationsEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("pushNotificationsEntryId", getPushNotificationsEntryId());
		attributes.put("userId", getUserId());
		attributes.put("createTime", getCreateTime());
		attributes.put("parentPushNotificationsEntryId",
			getParentPushNotificationsEntryId());
		attributes.put("childrenPushNotificationsEntriesCount",
			getChildrenPushNotificationsEntriesCount());
		attributes.put("payload", getPayload());
		attributes.put("ratingsTotalScore", getRatingsTotalScore());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long pushNotificationsEntryId = (Long)attributes.get(
				"pushNotificationsEntryId");

		if (pushNotificationsEntryId != null) {
			setPushNotificationsEntryId(pushNotificationsEntryId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long createTime = (Long)attributes.get("createTime");

		if (createTime != null) {
			setCreateTime(createTime);
		}

		Long parentPushNotificationsEntryId = (Long)attributes.get(
				"parentPushNotificationsEntryId");

		if (parentPushNotificationsEntryId != null) {
			setParentPushNotificationsEntryId(parentPushNotificationsEntryId);
		}

		Integer childrenPushNotificationsEntriesCount = (Integer)attributes.get(
				"childrenPushNotificationsEntriesCount");

		if (childrenPushNotificationsEntriesCount != null) {
			setChildrenPushNotificationsEntriesCount(childrenPushNotificationsEntriesCount);
		}

		String payload = (String)attributes.get("payload");

		if (payload != null) {
			setPayload(payload);
		}

		Long ratingsTotalScore = (Long)attributes.get("ratingsTotalScore");

		if (ratingsTotalScore != null) {
			setRatingsTotalScore(ratingsTotalScore);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getPushNotificationsEntryId() {
		return _pushNotificationsEntryId;
	}

	@Override
	public void setPushNotificationsEntryId(long pushNotificationsEntryId) {
		_pushNotificationsEntryId = pushNotificationsEntryId;

		if (_pushNotificationsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _pushNotificationsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPushNotificationsEntryId",
						long.class);

				method.invoke(_pushNotificationsEntryRemoteModel,
					pushNotificationsEntryId);
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

		if (_pushNotificationsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _pushNotificationsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_pushNotificationsEntryRemoteModel, userId);
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
	public long getCreateTime() {
		return _createTime;
	}

	@Override
	public void setCreateTime(long createTime) {
		_createTime = createTime;

		if (_pushNotificationsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _pushNotificationsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateTime", long.class);

				method.invoke(_pushNotificationsEntryRemoteModel, createTime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getParentPushNotificationsEntryId() {
		return _parentPushNotificationsEntryId;
	}

	@Override
	public void setParentPushNotificationsEntryId(
		long parentPushNotificationsEntryId) {
		_parentPushNotificationsEntryId = parentPushNotificationsEntryId;

		if (_pushNotificationsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _pushNotificationsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setParentPushNotificationsEntryId",
						long.class);

				method.invoke(_pushNotificationsEntryRemoteModel,
					parentPushNotificationsEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getChildrenPushNotificationsEntriesCount() {
		return _childrenPushNotificationsEntriesCount;
	}

	@Override
	public void setChildrenPushNotificationsEntriesCount(
		int childrenPushNotificationsEntriesCount) {
		_childrenPushNotificationsEntriesCount = childrenPushNotificationsEntriesCount;

		if (_pushNotificationsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _pushNotificationsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setChildrenPushNotificationsEntriesCount",
						int.class);

				method.invoke(_pushNotificationsEntryRemoteModel,
					childrenPushNotificationsEntriesCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPayload() {
		return _payload;
	}

	@Override
	public void setPayload(String payload) {
		_payload = payload;

		if (_pushNotificationsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _pushNotificationsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPayload", String.class);

				method.invoke(_pushNotificationsEntryRemoteModel, payload);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getRatingsTotalScore() {
		return _ratingsTotalScore;
	}

	@Override
	public void setRatingsTotalScore(long ratingsTotalScore) {
		_ratingsTotalScore = ratingsTotalScore;

		if (_pushNotificationsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _pushNotificationsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setRatingsTotalScore",
						long.class);

				method.invoke(_pushNotificationsEntryRemoteModel,
					ratingsTotalScore);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setUser(
		com.liferay.portal.kernel.json.JSONObject userJSONObject) {
		try {
			String methodName = "setUser";

			Class<?>[] parameterTypes = new Class<?>[] {
					com.liferay.portal.kernel.json.JSONObject.class
				};

			Object[] parameterValues = new Object[] { userJSONObject };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getUser() {
		try {
			String methodName = "getUser";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.kernel.json.JSONObject returnObj = (com.liferay.portal.kernel.json.JSONObject)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getPushNotificationsEntryRemoteModel() {
		return _pushNotificationsEntryRemoteModel;
	}

	public void setPushNotificationsEntryRemoteModel(
		BaseModel<?> pushNotificationsEntryRemoteModel) {
		_pushNotificationsEntryRemoteModel = pushNotificationsEntryRemoteModel;
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

		Class<?> remoteModelClass = _pushNotificationsEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_pushNotificationsEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			PushNotificationsEntryLocalServiceUtil.addPushNotificationsEntry(this);
		}
		else {
			PushNotificationsEntryLocalServiceUtil.updatePushNotificationsEntry(this);
		}
	}

	@Override
	public PushNotificationsEntry toEscapedModel() {
		return (PushNotificationsEntry)ProxyUtil.newProxyInstance(PushNotificationsEntry.class.getClassLoader(),
			new Class[] { PushNotificationsEntry.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		PushNotificationsEntryClp clone = new PushNotificationsEntryClp();

		clone.setPushNotificationsEntryId(getPushNotificationsEntryId());
		clone.setUserId(getUserId());
		clone.setCreateTime(getCreateTime());
		clone.setParentPushNotificationsEntryId(getParentPushNotificationsEntryId());
		clone.setChildrenPushNotificationsEntriesCount(getChildrenPushNotificationsEntriesCount());
		clone.setPayload(getPayload());
		clone.setRatingsTotalScore(getRatingsTotalScore());

		return clone;
	}

	@Override
	public int compareTo(PushNotificationsEntry pushNotificationsEntry) {
		int value = 0;

		if (getCreateTime() < pushNotificationsEntry.getCreateTime()) {
			value = -1;
		}
		else if (getCreateTime() > pushNotificationsEntry.getCreateTime()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof PushNotificationsEntryClp)) {
			return false;
		}

		PushNotificationsEntryClp pushNotificationsEntry = (PushNotificationsEntryClp)obj;

		long primaryKey = pushNotificationsEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(15);

		sb.append("{pushNotificationsEntryId=");
		sb.append(getPushNotificationsEntryId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createTime=");
		sb.append(getCreateTime());
		sb.append(", parentPushNotificationsEntryId=");
		sb.append(getParentPushNotificationsEntryId());
		sb.append(", childrenPushNotificationsEntriesCount=");
		sb.append(getChildrenPushNotificationsEntriesCount());
		sb.append(", payload=");
		sb.append(getPayload());
		sb.append(", ratingsTotalScore=");
		sb.append(getRatingsTotalScore());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.pushnotifications.model.PushNotificationsEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>pushNotificationsEntryId</column-name><column-value><![CDATA[");
		sb.append(getPushNotificationsEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createTime</column-name><column-value><![CDATA[");
		sb.append(getCreateTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentPushNotificationsEntryId</column-name><column-value><![CDATA[");
		sb.append(getParentPushNotificationsEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>childrenPushNotificationsEntriesCount</column-name><column-value><![CDATA[");
		sb.append(getChildrenPushNotificationsEntriesCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>payload</column-name><column-value><![CDATA[");
		sb.append(getPayload());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ratingsTotalScore</column-name><column-value><![CDATA[");
		sb.append(getRatingsTotalScore());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _pushNotificationsEntryId;
	private long _userId;
	private long _createTime;
	private long _parentPushNotificationsEntryId;
	private int _childrenPushNotificationsEntriesCount;
	private String _payload;
	private long _ratingsTotalScore;
	private BaseModel<?> _pushNotificationsEntryRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.pushnotifications.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}