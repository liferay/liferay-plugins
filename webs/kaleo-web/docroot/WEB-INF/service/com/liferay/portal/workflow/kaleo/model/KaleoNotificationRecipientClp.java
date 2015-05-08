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

package com.liferay.portal.workflow.kaleo.model;

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
import com.liferay.portal.workflow.kaleo.service.ClpSerializer;
import com.liferay.portal.workflow.kaleo.service.KaleoNotificationRecipientLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class KaleoNotificationRecipientClp extends BaseModelImpl<KaleoNotificationRecipient>
	implements KaleoNotificationRecipient {
	public KaleoNotificationRecipientClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoNotificationRecipient.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoNotificationRecipient.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoNotificationRecipientId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoNotificationRecipientId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoNotificationRecipientId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoNotificationRecipientId",
			getKaleoNotificationRecipientId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoNotificationId", getKaleoNotificationId());
		attributes.put("recipientClassName", getRecipientClassName());
		attributes.put("recipientClassPK", getRecipientClassPK());
		attributes.put("recipientRoleType", getRecipientRoleType());
		attributes.put("address", getAddress());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoNotificationRecipientId = (Long)attributes.get(
				"kaleoNotificationRecipientId");

		if (kaleoNotificationRecipientId != null) {
			setKaleoNotificationRecipientId(kaleoNotificationRecipientId);
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

		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
		}

		Long kaleoNotificationId = (Long)attributes.get("kaleoNotificationId");

		if (kaleoNotificationId != null) {
			setKaleoNotificationId(kaleoNotificationId);
		}

		String recipientClassName = (String)attributes.get("recipientClassName");

		if (recipientClassName != null) {
			setRecipientClassName(recipientClassName);
		}

		Long recipientClassPK = (Long)attributes.get("recipientClassPK");

		if (recipientClassPK != null) {
			setRecipientClassPK(recipientClassPK);
		}

		Integer recipientRoleType = (Integer)attributes.get("recipientRoleType");

		if (recipientRoleType != null) {
			setRecipientRoleType(recipientRoleType);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getKaleoNotificationRecipientId() {
		return _kaleoNotificationRecipientId;
	}

	@Override
	public void setKaleoNotificationRecipientId(
		long kaleoNotificationRecipientId) {
		_kaleoNotificationRecipientId = kaleoNotificationRecipientId;

		if (_kaleoNotificationRecipientRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRecipientRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoNotificationRecipientId",
						long.class);

				method.invoke(_kaleoNotificationRecipientRemoteModel,
					kaleoNotificationRecipientId);
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

		if (_kaleoNotificationRecipientRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRecipientRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_kaleoNotificationRecipientRemoteModel, groupId);
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

		if (_kaleoNotificationRecipientRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRecipientRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_kaleoNotificationRecipientRemoteModel, companyId);
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

		if (_kaleoNotificationRecipientRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRecipientRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_kaleoNotificationRecipientRemoteModel, userId);
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

		if (_kaleoNotificationRecipientRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRecipientRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_kaleoNotificationRecipientRemoteModel, userName);
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

		if (_kaleoNotificationRecipientRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRecipientRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_kaleoNotificationRecipientRemoteModel, createDate);
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

		if (_kaleoNotificationRecipientRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRecipientRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_kaleoNotificationRecipientRemoteModel,
					modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinitionId = kaleoDefinitionId;

		if (_kaleoNotificationRecipientRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRecipientRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoDefinitionId",
						long.class);

				method.invoke(_kaleoNotificationRecipientRemoteModel,
					kaleoDefinitionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoNotificationId() {
		return _kaleoNotificationId;
	}

	@Override
	public void setKaleoNotificationId(long kaleoNotificationId) {
		_kaleoNotificationId = kaleoNotificationId;

		if (_kaleoNotificationRecipientRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRecipientRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoNotificationId",
						long.class);

				method.invoke(_kaleoNotificationRecipientRemoteModel,
					kaleoNotificationId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRecipientClassName() {
		return _recipientClassName;
	}

	@Override
	public void setRecipientClassName(String recipientClassName) {
		_recipientClassName = recipientClassName;

		if (_kaleoNotificationRecipientRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRecipientRemoteModel.getClass();

				Method method = clazz.getMethod("setRecipientClassName",
						String.class);

				method.invoke(_kaleoNotificationRecipientRemoteModel,
					recipientClassName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getRecipientClassPK() {
		return _recipientClassPK;
	}

	@Override
	public void setRecipientClassPK(long recipientClassPK) {
		_recipientClassPK = recipientClassPK;

		if (_kaleoNotificationRecipientRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRecipientRemoteModel.getClass();

				Method method = clazz.getMethod("setRecipientClassPK",
						long.class);

				method.invoke(_kaleoNotificationRecipientRemoteModel,
					recipientClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getRecipientRoleType() {
		return _recipientRoleType;
	}

	@Override
	public void setRecipientRoleType(int recipientRoleType) {
		_recipientRoleType = recipientRoleType;

		if (_kaleoNotificationRecipientRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRecipientRemoteModel.getClass();

				Method method = clazz.getMethod("setRecipientRoleType",
						int.class);

				method.invoke(_kaleoNotificationRecipientRemoteModel,
					recipientRoleType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAddress() {
		return _address;
	}

	@Override
	public void setAddress(String address) {
		_address = address;

		if (_kaleoNotificationRecipientRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoNotificationRecipientRemoteModel.getClass();

				Method method = clazz.getMethod("setAddress", String.class);

				method.invoke(_kaleoNotificationRecipientRemoteModel, address);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getKaleoNotificationRecipientRemoteModel() {
		return _kaleoNotificationRecipientRemoteModel;
	}

	public void setKaleoNotificationRecipientRemoteModel(
		BaseModel<?> kaleoNotificationRecipientRemoteModel) {
		_kaleoNotificationRecipientRemoteModel = kaleoNotificationRecipientRemoteModel;
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

		Class<?> remoteModelClass = _kaleoNotificationRecipientRemoteModel.getClass();

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

		Object returnValue = method.invoke(_kaleoNotificationRecipientRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			KaleoNotificationRecipientLocalServiceUtil.addKaleoNotificationRecipient(this);
		}
		else {
			KaleoNotificationRecipientLocalServiceUtil.updateKaleoNotificationRecipient(this);
		}
	}

	@Override
	public KaleoNotificationRecipient toEscapedModel() {
		return (KaleoNotificationRecipient)ProxyUtil.newProxyInstance(KaleoNotificationRecipient.class.getClassLoader(),
			new Class[] { KaleoNotificationRecipient.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoNotificationRecipientClp clone = new KaleoNotificationRecipientClp();

		clone.setKaleoNotificationRecipientId(getKaleoNotificationRecipientId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoNotificationId(getKaleoNotificationId());
		clone.setRecipientClassName(getRecipientClassName());
		clone.setRecipientClassPK(getRecipientClassPK());
		clone.setRecipientRoleType(getRecipientRoleType());
		clone.setAddress(getAddress());

		return clone;
	}

	@Override
	public int compareTo(KaleoNotificationRecipient kaleoNotificationRecipient) {
		int value = 0;

		if (getKaleoNotificationRecipientId() < kaleoNotificationRecipient.getKaleoNotificationRecipientId()) {
			value = -1;
		}
		else if (getKaleoNotificationRecipientId() > kaleoNotificationRecipient.getKaleoNotificationRecipientId()) {
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

		if (!(obj instanceof KaleoNotificationRecipientClp)) {
			return false;
		}

		KaleoNotificationRecipientClp kaleoNotificationRecipient = (KaleoNotificationRecipientClp)obj;

		long primaryKey = kaleoNotificationRecipient.getPrimaryKey();

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
		StringBundler sb = new StringBundler(27);

		sb.append("{kaleoNotificationRecipientId=");
		sb.append(getKaleoNotificationRecipientId());
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
		sb.append(", kaleoDefinitionId=");
		sb.append(getKaleoDefinitionId());
		sb.append(", kaleoNotificationId=");
		sb.append(getKaleoNotificationId());
		sb.append(", recipientClassName=");
		sb.append(getRecipientClassName());
		sb.append(", recipientClassPK=");
		sb.append(getRecipientClassPK());
		sb.append(", recipientRoleType=");
		sb.append(getRecipientRoleType());
		sb.append(", address=");
		sb.append(getAddress());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoNotificationRecipientId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNotificationRecipientId());
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
			"<column><column-name>kaleoDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoNotificationId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNotificationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recipientClassName</column-name><column-value><![CDATA[");
		sb.append(getRecipientClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recipientClassPK</column-name><column-value><![CDATA[");
		sb.append(getRecipientClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recipientRoleType</column-name><column-value><![CDATA[");
		sb.append(getRecipientRoleType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>address</column-name><column-value><![CDATA[");
		sb.append(getAddress());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoNotificationRecipientId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoNotificationId;
	private String _recipientClassName;
	private long _recipientClassPK;
	private int _recipientRoleType;
	private String _address;
	private BaseModel<?> _kaleoNotificationRecipientRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}