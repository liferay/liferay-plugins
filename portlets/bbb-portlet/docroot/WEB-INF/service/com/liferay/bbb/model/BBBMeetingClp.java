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

package com.liferay.bbb.model;

import com.liferay.bbb.service.BBBMeetingLocalServiceUtil;
import com.liferay.bbb.service.ClpSerializer;

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

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class BBBMeetingClp extends BaseModelImpl<BBBMeeting>
	implements BBBMeeting {
	public BBBMeetingClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return BBBMeeting.class;
	}

	@Override
	public String getModelClassName() {
		return BBBMeeting.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _bbbMeetingId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBbbMeetingId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _bbbMeetingId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("bbbMeetingId", getBbbMeetingId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("bbbServerId", getBbbServerId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("attendeePassword", getAttendeePassword());
		attributes.put("moderatorPassword", getModeratorPassword());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long bbbMeetingId = (Long)attributes.get("bbbMeetingId");

		if (bbbMeetingId != null) {
			setBbbMeetingId(bbbMeetingId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		Long bbbServerId = (Long)attributes.get("bbbServerId");

		if (bbbServerId != null) {
			setBbbServerId(bbbServerId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String attendeePassword = (String)attributes.get("attendeePassword");

		if (attendeePassword != null) {
			setAttendeePassword(attendeePassword);
		}

		String moderatorPassword = (String)attributes.get("moderatorPassword");

		if (moderatorPassword != null) {
			setModeratorPassword(moderatorPassword);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getBbbMeetingId() {
		return _bbbMeetingId;
	}

	@Override
	public void setBbbMeetingId(long bbbMeetingId) {
		_bbbMeetingId = bbbMeetingId;

		if (_bbbMeetingRemoteModel != null) {
			try {
				Class<?> clazz = _bbbMeetingRemoteModel.getClass();

				Method method = clazz.getMethod("setBbbMeetingId", long.class);

				method.invoke(_bbbMeetingRemoteModel, bbbMeetingId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_bbbMeetingRemoteModel != null) {
			try {
				Class<?> clazz = _bbbMeetingRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_bbbMeetingRemoteModel, groupId);
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

		if (_bbbMeetingRemoteModel != null) {
			try {
				Class<?> clazz = _bbbMeetingRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_bbbMeetingRemoteModel, companyId);
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

		if (_bbbMeetingRemoteModel != null) {
			try {
				Class<?> clazz = _bbbMeetingRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_bbbMeetingRemoteModel, userId);
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

		if (_bbbMeetingRemoteModel != null) {
			try {
				Class<?> clazz = _bbbMeetingRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_bbbMeetingRemoteModel, userName);
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

		if (_bbbMeetingRemoteModel != null) {
			try {
				Class<?> clazz = _bbbMeetingRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_bbbMeetingRemoteModel, createDate);
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

		if (_bbbMeetingRemoteModel != null) {
			try {
				Class<?> clazz = _bbbMeetingRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_bbbMeetingRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getBbbServerId() {
		return _bbbServerId;
	}

	@Override
	public void setBbbServerId(long bbbServerId) {
		_bbbServerId = bbbServerId;

		if (_bbbMeetingRemoteModel != null) {
			try {
				Class<?> clazz = _bbbMeetingRemoteModel.getClass();

				Method method = clazz.getMethod("setBbbServerId", long.class);

				method.invoke(_bbbMeetingRemoteModel, bbbServerId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_bbbMeetingRemoteModel != null) {
			try {
				Class<?> clazz = _bbbMeetingRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_bbbMeetingRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public void setDescription(String description) {
		_description = description;

		if (_bbbMeetingRemoteModel != null) {
			try {
				Class<?> clazz = _bbbMeetingRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_bbbMeetingRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAttendeePassword() {
		return _attendeePassword;
	}

	@Override
	public void setAttendeePassword(String attendeePassword) {
		_attendeePassword = attendeePassword;

		if (_bbbMeetingRemoteModel != null) {
			try {
				Class<?> clazz = _bbbMeetingRemoteModel.getClass();

				Method method = clazz.getMethod("setAttendeePassword",
						String.class);

				method.invoke(_bbbMeetingRemoteModel, attendeePassword);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getModeratorPassword() {
		return _moderatorPassword;
	}

	@Override
	public void setModeratorPassword(String moderatorPassword) {
		_moderatorPassword = moderatorPassword;

		if (_bbbMeetingRemoteModel != null) {
			try {
				Class<?> clazz = _bbbMeetingRemoteModel.getClass();

				Method method = clazz.getMethod("setModeratorPassword",
						String.class);

				method.invoke(_bbbMeetingRemoteModel, moderatorPassword);
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

		if (_bbbMeetingRemoteModel != null) {
			try {
				Class<?> clazz = _bbbMeetingRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_bbbMeetingRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getBBBMeetingRemoteModel() {
		return _bbbMeetingRemoteModel;
	}

	public void setBBBMeetingRemoteModel(BaseModel<?> bbbMeetingRemoteModel) {
		_bbbMeetingRemoteModel = bbbMeetingRemoteModel;
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

		Class<?> remoteModelClass = _bbbMeetingRemoteModel.getClass();

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

		Object returnValue = method.invoke(_bbbMeetingRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			BBBMeetingLocalServiceUtil.addBBBMeeting(this);
		}
		else {
			BBBMeetingLocalServiceUtil.updateBBBMeeting(this);
		}
	}

	@Override
	public BBBMeeting toEscapedModel() {
		return (BBBMeeting)ProxyUtil.newProxyInstance(BBBMeeting.class.getClassLoader(),
			new Class[] { BBBMeeting.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		BBBMeetingClp clone = new BBBMeetingClp();

		clone.setBbbMeetingId(getBbbMeetingId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setBbbServerId(getBbbServerId());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setAttendeePassword(getAttendeePassword());
		clone.setModeratorPassword(getModeratorPassword());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(BBBMeeting bbbMeeting) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), bbbMeeting.getCreateDate());

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

		if (!(obj instanceof BBBMeetingClp)) {
			return false;
		}

		BBBMeetingClp bbbMeeting = (BBBMeetingClp)obj;

		long primaryKey = bbbMeeting.getPrimaryKey();

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
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{bbbMeetingId=");
		sb.append(getBbbMeetingId());
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
		sb.append(", bbbServerId=");
		sb.append(getBbbServerId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", attendeePassword=");
		sb.append(getAttendeePassword());
		sb.append(", moderatorPassword=");
		sb.append(getModeratorPassword());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.bbb.model.BBBMeeting");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>bbbMeetingId</column-name><column-value><![CDATA[");
		sb.append(getBbbMeetingId());
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
			"<column><column-name>bbbServerId</column-name><column-value><![CDATA[");
		sb.append(getBbbServerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>attendeePassword</column-name><column-value><![CDATA[");
		sb.append(getAttendeePassword());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>moderatorPassword</column-name><column-value><![CDATA[");
		sb.append(getModeratorPassword());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _bbbMeetingId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _bbbServerId;
	private String _name;
	private String _description;
	private String _attendeePassword;
	private String _moderatorPassword;
	private int _status;
	private BaseModel<?> _bbbMeetingRemoteModel;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}