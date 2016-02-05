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

package com.liferay.so.model;

import aQute.bnd.annotation.ProviderType;

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

import com.liferay.so.service.ClpSerializer;
import com.liferay.so.service.MemberRequestLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class MemberRequestClp extends BaseModelImpl<MemberRequest>
	implements MemberRequest {
	public MemberRequestClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return MemberRequest.class;
	}

	@Override
	public String getModelClassName() {
		return MemberRequest.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _memberRequestId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setMemberRequestId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _memberRequestId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("memberRequestId", getMemberRequestId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("key", getKey());
		attributes.put("receiverUserId", getReceiverUserId());
		attributes.put("invitedRoleId", getInvitedRoleId());
		attributes.put("invitedTeamId", getInvitedTeamId());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long memberRequestId = (Long)attributes.get("memberRequestId");

		if (memberRequestId != null) {
			setMemberRequestId(memberRequestId);
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

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		Long receiverUserId = (Long)attributes.get("receiverUserId");

		if (receiverUserId != null) {
			setReceiverUserId(receiverUserId);
		}

		Long invitedRoleId = (Long)attributes.get("invitedRoleId");

		if (invitedRoleId != null) {
			setInvitedRoleId(invitedRoleId);
		}

		Long invitedTeamId = (Long)attributes.get("invitedTeamId");

		if (invitedTeamId != null) {
			setInvitedTeamId(invitedTeamId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getMemberRequestId() {
		return _memberRequestId;
	}

	@Override
	public void setMemberRequestId(long memberRequestId) {
		_memberRequestId = memberRequestId;

		if (_memberRequestRemoteModel != null) {
			try {
				Class<?> clazz = _memberRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setMemberRequestId", long.class);

				method.invoke(_memberRequestRemoteModel, memberRequestId);
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

		if (_memberRequestRemoteModel != null) {
			try {
				Class<?> clazz = _memberRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_memberRequestRemoteModel, groupId);
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

		if (_memberRequestRemoteModel != null) {
			try {
				Class<?> clazz = _memberRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_memberRequestRemoteModel, companyId);
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

		if (_memberRequestRemoteModel != null) {
			try {
				Class<?> clazz = _memberRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_memberRequestRemoteModel, userId);
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

		if (_memberRequestRemoteModel != null) {
			try {
				Class<?> clazz = _memberRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_memberRequestRemoteModel, userName);
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

		if (_memberRequestRemoteModel != null) {
			try {
				Class<?> clazz = _memberRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_memberRequestRemoteModel, createDate);
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

		if (_memberRequestRemoteModel != null) {
			try {
				Class<?> clazz = _memberRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_memberRequestRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public void setKey(String key) {
		_key = key;

		if (_memberRequestRemoteModel != null) {
			try {
				Class<?> clazz = _memberRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setKey", String.class);

				method.invoke(_memberRequestRemoteModel, key);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getReceiverUserId() {
		return _receiverUserId;
	}

	@Override
	public void setReceiverUserId(long receiverUserId) {
		_receiverUserId = receiverUserId;

		if (_memberRequestRemoteModel != null) {
			try {
				Class<?> clazz = _memberRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setReceiverUserId", long.class);

				method.invoke(_memberRequestRemoteModel, receiverUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getReceiverUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getReceiverUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setReceiverUserUuid(String receiverUserUuid) {
	}

	@Override
	public long getInvitedRoleId() {
		return _invitedRoleId;
	}

	@Override
	public void setInvitedRoleId(long invitedRoleId) {
		_invitedRoleId = invitedRoleId;

		if (_memberRequestRemoteModel != null) {
			try {
				Class<?> clazz = _memberRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setInvitedRoleId", long.class);

				method.invoke(_memberRequestRemoteModel, invitedRoleId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getInvitedTeamId() {
		return _invitedTeamId;
	}

	@Override
	public void setInvitedTeamId(long invitedTeamId) {
		_invitedTeamId = invitedTeamId;

		if (_memberRequestRemoteModel != null) {
			try {
				Class<?> clazz = _memberRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setInvitedTeamId", long.class);

				method.invoke(_memberRequestRemoteModel, invitedTeamId);
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

		if (_memberRequestRemoteModel != null) {
			try {
				Class<?> clazz = _memberRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_memberRequestRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getMemberRequestRemoteModel() {
		return _memberRequestRemoteModel;
	}

	public void setMemberRequestRemoteModel(
		BaseModel<?> memberRequestRemoteModel) {
		_memberRequestRemoteModel = memberRequestRemoteModel;
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

		Class<?> remoteModelClass = _memberRequestRemoteModel.getClass();

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

		Object returnValue = method.invoke(_memberRequestRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			MemberRequestLocalServiceUtil.addMemberRequest(this);
		}
		else {
			MemberRequestLocalServiceUtil.updateMemberRequest(this);
		}
	}

	@Override
	public MemberRequest toEscapedModel() {
		return (MemberRequest)ProxyUtil.newProxyInstance(MemberRequest.class.getClassLoader(),
			new Class[] { MemberRequest.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		MemberRequestClp clone = new MemberRequestClp();

		clone.setMemberRequestId(getMemberRequestId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKey(getKey());
		clone.setReceiverUserId(getReceiverUserId());
		clone.setInvitedRoleId(getInvitedRoleId());
		clone.setInvitedTeamId(getInvitedTeamId());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(MemberRequest memberRequest) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				memberRequest.getCreateDate());

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

		if (!(obj instanceof MemberRequestClp)) {
			return false;
		}

		MemberRequestClp memberRequest = (MemberRequestClp)obj;

		long primaryKey = memberRequest.getPrimaryKey();

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
		StringBundler sb = new StringBundler(25);

		sb.append("{memberRequestId=");
		sb.append(getMemberRequestId());
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
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", receiverUserId=");
		sb.append(getReceiverUserId());
		sb.append(", invitedRoleId=");
		sb.append(getInvitedRoleId());
		sb.append(", invitedTeamId=");
		sb.append(getInvitedTeamId());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.so.model.MemberRequest");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>memberRequestId</column-name><column-value><![CDATA[");
		sb.append(getMemberRequestId());
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
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>receiverUserId</column-name><column-value><![CDATA[");
		sb.append(getReceiverUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>invitedRoleId</column-name><column-value><![CDATA[");
		sb.append(getInvitedRoleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>invitedTeamId</column-name><column-value><![CDATA[");
		sb.append(getInvitedTeamId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _memberRequestId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _key;
	private long _receiverUserId;
	private long _invitedRoleId;
	private long _invitedTeamId;
	private int _status;
	private BaseModel<?> _memberRequestRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.so.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}