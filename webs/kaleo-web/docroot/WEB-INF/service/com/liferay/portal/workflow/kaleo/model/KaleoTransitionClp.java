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
import com.liferay.portal.workflow.kaleo.service.KaleoTransitionLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class KaleoTransitionClp extends BaseModelImpl<KaleoTransition>
	implements KaleoTransition {
	public KaleoTransitionClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoTransition.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTransition.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoTransitionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoTransitionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoTransitionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoTransitionId", getKaleoTransitionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoNodeId", getKaleoNodeId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("sourceKaleoNodeId", getSourceKaleoNodeId());
		attributes.put("sourceKaleoNodeName", getSourceKaleoNodeName());
		attributes.put("targetKaleoNodeId", getTargetKaleoNodeId());
		attributes.put("targetKaleoNodeName", getTargetKaleoNodeName());
		attributes.put("defaultTransition", getDefaultTransition());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoTransitionId = (Long)attributes.get("kaleoTransitionId");

		if (kaleoTransitionId != null) {
			setKaleoTransitionId(kaleoTransitionId);
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

		Long kaleoNodeId = (Long)attributes.get("kaleoNodeId");

		if (kaleoNodeId != null) {
			setKaleoNodeId(kaleoNodeId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long sourceKaleoNodeId = (Long)attributes.get("sourceKaleoNodeId");

		if (sourceKaleoNodeId != null) {
			setSourceKaleoNodeId(sourceKaleoNodeId);
		}

		String sourceKaleoNodeName = (String)attributes.get(
				"sourceKaleoNodeName");

		if (sourceKaleoNodeName != null) {
			setSourceKaleoNodeName(sourceKaleoNodeName);
		}

		Long targetKaleoNodeId = (Long)attributes.get("targetKaleoNodeId");

		if (targetKaleoNodeId != null) {
			setTargetKaleoNodeId(targetKaleoNodeId);
		}

		String targetKaleoNodeName = (String)attributes.get(
				"targetKaleoNodeName");

		if (targetKaleoNodeName != null) {
			setTargetKaleoNodeName(targetKaleoNodeName);
		}

		Boolean defaultTransition = (Boolean)attributes.get("defaultTransition");

		if (defaultTransition != null) {
			setDefaultTransition(defaultTransition);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getKaleoTransitionId() {
		return _kaleoTransitionId;
	}

	@Override
	public void setKaleoTransitionId(long kaleoTransitionId) {
		_kaleoTransitionId = kaleoTransitionId;

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoTransitionId",
						long.class);

				method.invoke(_kaleoTransitionRemoteModel, kaleoTransitionId);
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

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_kaleoTransitionRemoteModel, groupId);
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

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_kaleoTransitionRemoteModel, companyId);
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

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_kaleoTransitionRemoteModel, userId);
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

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_kaleoTransitionRemoteModel, userName);
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

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_kaleoTransitionRemoteModel, createDate);
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

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_kaleoTransitionRemoteModel, modifiedDate);
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

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoDefinitionId",
						long.class);

				method.invoke(_kaleoTransitionRemoteModel, kaleoDefinitionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoNodeId() {
		return _kaleoNodeId;
	}

	@Override
	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoNodeId = kaleoNodeId;

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoNodeId", long.class);

				method.invoke(_kaleoTransitionRemoteModel, kaleoNodeId);
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

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_kaleoTransitionRemoteModel, name);
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

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_kaleoTransitionRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSourceKaleoNodeId() {
		return _sourceKaleoNodeId;
	}

	@Override
	public void setSourceKaleoNodeId(long sourceKaleoNodeId) {
		_sourceKaleoNodeId = sourceKaleoNodeId;

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setSourceKaleoNodeId",
						long.class);

				method.invoke(_kaleoTransitionRemoteModel, sourceKaleoNodeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSourceKaleoNodeName() {
		return _sourceKaleoNodeName;
	}

	@Override
	public void setSourceKaleoNodeName(String sourceKaleoNodeName) {
		_sourceKaleoNodeName = sourceKaleoNodeName;

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setSourceKaleoNodeName",
						String.class);

				method.invoke(_kaleoTransitionRemoteModel, sourceKaleoNodeName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTargetKaleoNodeId() {
		return _targetKaleoNodeId;
	}

	@Override
	public void setTargetKaleoNodeId(long targetKaleoNodeId) {
		_targetKaleoNodeId = targetKaleoNodeId;

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setTargetKaleoNodeId",
						long.class);

				method.invoke(_kaleoTransitionRemoteModel, targetKaleoNodeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTargetKaleoNodeName() {
		return _targetKaleoNodeName;
	}

	@Override
	public void setTargetKaleoNodeName(String targetKaleoNodeName) {
		_targetKaleoNodeName = targetKaleoNodeName;

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setTargetKaleoNodeName",
						String.class);

				method.invoke(_kaleoTransitionRemoteModel, targetKaleoNodeName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getDefaultTransition() {
		return _defaultTransition;
	}

	@Override
	public boolean isDefaultTransition() {
		return _defaultTransition;
	}

	@Override
	public void setDefaultTransition(boolean defaultTransition) {
		_defaultTransition = defaultTransition;

		if (_kaleoTransitionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTransitionRemoteModel.getClass();

				Method method = clazz.getMethod("setDefaultTransition",
						boolean.class);

				method.invoke(_kaleoTransitionRemoteModel, defaultTransition);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNode getTargetKaleoNode() {
		try {
			String methodName = "getTargetKaleoNode";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.workflow.kaleo.model.KaleoNode returnObj = (com.liferay.portal.workflow.kaleo.model.KaleoNode)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNode getSourceKaleoNode() {
		try {
			String methodName = "getSourceKaleoNode";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.workflow.kaleo.model.KaleoNode returnObj = (com.liferay.portal.workflow.kaleo.model.KaleoNode)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getKaleoTransitionRemoteModel() {
		return _kaleoTransitionRemoteModel;
	}

	public void setKaleoTransitionRemoteModel(
		BaseModel<?> kaleoTransitionRemoteModel) {
		_kaleoTransitionRemoteModel = kaleoTransitionRemoteModel;
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

		Class<?> remoteModelClass = _kaleoTransitionRemoteModel.getClass();

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

		Object returnValue = method.invoke(_kaleoTransitionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			KaleoTransitionLocalServiceUtil.addKaleoTransition(this);
		}
		else {
			KaleoTransitionLocalServiceUtil.updateKaleoTransition(this);
		}
	}

	@Override
	public KaleoTransition toEscapedModel() {
		return (KaleoTransition)ProxyUtil.newProxyInstance(KaleoTransition.class.getClassLoader(),
			new Class[] { KaleoTransition.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoTransitionClp clone = new KaleoTransitionClp();

		clone.setKaleoTransitionId(getKaleoTransitionId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoNodeId(getKaleoNodeId());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setSourceKaleoNodeId(getSourceKaleoNodeId());
		clone.setSourceKaleoNodeName(getSourceKaleoNodeName());
		clone.setTargetKaleoNodeId(getTargetKaleoNodeId());
		clone.setTargetKaleoNodeName(getTargetKaleoNodeName());
		clone.setDefaultTransition(getDefaultTransition());

		return clone;
	}

	@Override
	public int compareTo(KaleoTransition kaleoTransition) {
		int value = 0;

		if (getKaleoTransitionId() < kaleoTransition.getKaleoTransitionId()) {
			value = -1;
		}
		else if (getKaleoTransitionId() > kaleoTransition.getKaleoTransitionId()) {
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

		if (!(obj instanceof KaleoTransitionClp)) {
			return false;
		}

		KaleoTransitionClp kaleoTransition = (KaleoTransitionClp)obj;

		long primaryKey = kaleoTransition.getPrimaryKey();

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
		StringBundler sb = new StringBundler(33);

		sb.append("{kaleoTransitionId=");
		sb.append(getKaleoTransitionId());
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
		sb.append(", kaleoNodeId=");
		sb.append(getKaleoNodeId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", sourceKaleoNodeId=");
		sb.append(getSourceKaleoNodeId());
		sb.append(", sourceKaleoNodeName=");
		sb.append(getSourceKaleoNodeName());
		sb.append(", targetKaleoNodeId=");
		sb.append(getTargetKaleoNodeId());
		sb.append(", targetKaleoNodeName=");
		sb.append(getTargetKaleoNodeName());
		sb.append(", defaultTransition=");
		sb.append(getDefaultTransition());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoTransition");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoTransitionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTransitionId());
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
			"<column><column-name>kaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNodeId());
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
			"<column><column-name>sourceKaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getSourceKaleoNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sourceKaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getSourceKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>targetKaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getTargetKaleoNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>targetKaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getTargetKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>defaultTransition</column-name><column-value><![CDATA[");
		sb.append(getDefaultTransition());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoTransitionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoNodeId;
	private String _name;
	private String _description;
	private long _sourceKaleoNodeId;
	private String _sourceKaleoNodeName;
	private long _targetKaleoNodeId;
	private String _targetKaleoNodeName;
	private boolean _defaultTransition;
	private BaseModel<?> _kaleoTransitionRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}