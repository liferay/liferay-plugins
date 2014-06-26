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

package com.liferay.socialnetworking.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.UserLocalServiceUtil;

import com.liferay.socialnetworking.service.ClpSerializer;
import com.liferay.socialnetworking.service.MeetupsRegistrationLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetupsRegistrationClp extends BaseModelImpl<MeetupsRegistration>
	implements MeetupsRegistration {
	public MeetupsRegistrationClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return MeetupsRegistration.class;
	}

	@Override
	public String getModelClassName() {
		return MeetupsRegistration.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _meetupsRegistrationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setMeetupsRegistrationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _meetupsRegistrationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("meetupsRegistrationId", getMeetupsRegistrationId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("meetupsEntryId", getMeetupsEntryId());
		attributes.put("status", getStatus());
		attributes.put("comments", getComments());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long meetupsRegistrationId = (Long)attributes.get(
				"meetupsRegistrationId");

		if (meetupsRegistrationId != null) {
			setMeetupsRegistrationId(meetupsRegistrationId);
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

		Long meetupsEntryId = (Long)attributes.get("meetupsEntryId");

		if (meetupsEntryId != null) {
			setMeetupsEntryId(meetupsEntryId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getMeetupsRegistrationId() {
		return _meetupsRegistrationId;
	}

	@Override
	public void setMeetupsRegistrationId(long meetupsRegistrationId) {
		_meetupsRegistrationId = meetupsRegistrationId;

		if (_meetupsRegistrationRemoteModel != null) {
			try {
				Class<?> clazz = _meetupsRegistrationRemoteModel.getClass();

				Method method = clazz.getMethod("setMeetupsRegistrationId",
						long.class);

				method.invoke(_meetupsRegistrationRemoteModel,
					meetupsRegistrationId);
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

		if (_meetupsRegistrationRemoteModel != null) {
			try {
				Class<?> clazz = _meetupsRegistrationRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_meetupsRegistrationRemoteModel, companyId);
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

		if (_meetupsRegistrationRemoteModel != null) {
			try {
				Class<?> clazz = _meetupsRegistrationRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_meetupsRegistrationRemoteModel, userId);
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

		if (_meetupsRegistrationRemoteModel != null) {
			try {
				Class<?> clazz = _meetupsRegistrationRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_meetupsRegistrationRemoteModel, userName);
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

		if (_meetupsRegistrationRemoteModel != null) {
			try {
				Class<?> clazz = _meetupsRegistrationRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_meetupsRegistrationRemoteModel, createDate);
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

		if (_meetupsRegistrationRemoteModel != null) {
			try {
				Class<?> clazz = _meetupsRegistrationRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_meetupsRegistrationRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getMeetupsEntryId() {
		return _meetupsEntryId;
	}

	@Override
	public void setMeetupsEntryId(long meetupsEntryId) {
		_meetupsEntryId = meetupsEntryId;

		if (_meetupsRegistrationRemoteModel != null) {
			try {
				Class<?> clazz = _meetupsRegistrationRemoteModel.getClass();

				Method method = clazz.getMethod("setMeetupsEntryId", long.class);

				method.invoke(_meetupsRegistrationRemoteModel, meetupsEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_meetupsRegistrationRemoteModel != null) {
			try {
				Class<?> clazz = _meetupsRegistrationRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_meetupsRegistrationRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getComments() {
		return _comments;
	}

	@Override
	public void setComments(String comments) {
		_comments = comments;

		if (_meetupsRegistrationRemoteModel != null) {
			try {
				Class<?> clazz = _meetupsRegistrationRemoteModel.getClass();

				Method method = clazz.getMethod("setComments", String.class);

				method.invoke(_meetupsRegistrationRemoteModel, comments);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getMeetupsRegistrationRemoteModel() {
		return _meetupsRegistrationRemoteModel;
	}

	public void setMeetupsRegistrationRemoteModel(
		BaseModel<?> meetupsRegistrationRemoteModel) {
		_meetupsRegistrationRemoteModel = meetupsRegistrationRemoteModel;
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

		Class<?> remoteModelClass = _meetupsRegistrationRemoteModel.getClass();

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

		Object returnValue = method.invoke(_meetupsRegistrationRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			MeetupsRegistrationLocalServiceUtil.addMeetupsRegistration(this);
		}
		else {
			MeetupsRegistrationLocalServiceUtil.updateMeetupsRegistration(this);
		}
	}

	@Override
	public MeetupsRegistration toEscapedModel() {
		return (MeetupsRegistration)ProxyUtil.newProxyInstance(MeetupsRegistration.class.getClassLoader(),
			new Class[] { MeetupsRegistration.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		MeetupsRegistrationClp clone = new MeetupsRegistrationClp();

		clone.setMeetupsRegistrationId(getMeetupsRegistrationId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setMeetupsEntryId(getMeetupsEntryId());
		clone.setStatus(getStatus());
		clone.setComments(getComments());

		return clone;
	}

	@Override
	public int compareTo(MeetupsRegistration meetupsRegistration) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				meetupsRegistration.getModifiedDate());

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

		if (!(obj instanceof MeetupsRegistrationClp)) {
			return false;
		}

		MeetupsRegistrationClp meetupsRegistration = (MeetupsRegistrationClp)obj;

		long primaryKey = meetupsRegistration.getPrimaryKey();

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
		StringBundler sb = new StringBundler(19);

		sb.append("{meetupsRegistrationId=");
		sb.append(getMeetupsRegistrationId());
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
		sb.append(", meetupsEntryId=");
		sb.append(getMeetupsEntryId());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", comments=");
		sb.append(getComments());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.socialnetworking.model.MeetupsRegistration");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>meetupsRegistrationId</column-name><column-value><![CDATA[");
		sb.append(getMeetupsRegistrationId());
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
			"<column><column-name>meetupsEntryId</column-name><column-value><![CDATA[");
		sb.append(getMeetupsEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comments</column-name><column-value><![CDATA[");
		sb.append(getComments());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _meetupsRegistrationId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _meetupsEntryId;
	private int _status;
	private String _comments;
	private BaseModel<?> _meetupsRegistrationRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.socialnetworking.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}