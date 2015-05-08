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
import com.liferay.portal.workflow.kaleo.service.KaleoConditionLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class KaleoConditionClp extends BaseModelImpl<KaleoCondition>
	implements KaleoCondition {
	public KaleoConditionClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoCondition.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoCondition.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoConditionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoConditionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoConditionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoConditionId", getKaleoConditionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoNodeId", getKaleoNodeId());
		attributes.put("script", getScript());
		attributes.put("scriptLanguage", getScriptLanguage());
		attributes.put("scriptRequiredContexts", getScriptRequiredContexts());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoConditionId = (Long)attributes.get("kaleoConditionId");

		if (kaleoConditionId != null) {
			setKaleoConditionId(kaleoConditionId);
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

		String script = (String)attributes.get("script");

		if (script != null) {
			setScript(script);
		}

		String scriptLanguage = (String)attributes.get("scriptLanguage");

		if (scriptLanguage != null) {
			setScriptLanguage(scriptLanguage);
		}

		String scriptRequiredContexts = (String)attributes.get(
				"scriptRequiredContexts");

		if (scriptRequiredContexts != null) {
			setScriptRequiredContexts(scriptRequiredContexts);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getKaleoConditionId() {
		return _kaleoConditionId;
	}

	@Override
	public void setKaleoConditionId(long kaleoConditionId) {
		_kaleoConditionId = kaleoConditionId;

		if (_kaleoConditionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoConditionId",
						long.class);

				method.invoke(_kaleoConditionRemoteModel, kaleoConditionId);
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

		if (_kaleoConditionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_kaleoConditionRemoteModel, groupId);
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

		if (_kaleoConditionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_kaleoConditionRemoteModel, companyId);
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

		if (_kaleoConditionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_kaleoConditionRemoteModel, userId);
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

		if (_kaleoConditionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_kaleoConditionRemoteModel, userName);
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

		if (_kaleoConditionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_kaleoConditionRemoteModel, createDate);
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

		if (_kaleoConditionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_kaleoConditionRemoteModel, modifiedDate);
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

		if (_kaleoConditionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoDefinitionId",
						long.class);

				method.invoke(_kaleoConditionRemoteModel, kaleoDefinitionId);
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

		if (_kaleoConditionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoNodeId", long.class);

				method.invoke(_kaleoConditionRemoteModel, kaleoNodeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getScript() {
		return _script;
	}

	@Override
	public void setScript(String script) {
		_script = script;

		if (_kaleoConditionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setScript", String.class);

				method.invoke(_kaleoConditionRemoteModel, script);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getScriptLanguage() {
		return _scriptLanguage;
	}

	@Override
	public void setScriptLanguage(String scriptLanguage) {
		_scriptLanguage = scriptLanguage;

		if (_kaleoConditionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setScriptLanguage",
						String.class);

				method.invoke(_kaleoConditionRemoteModel, scriptLanguage);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getScriptRequiredContexts() {
		return _scriptRequiredContexts;
	}

	@Override
	public void setScriptRequiredContexts(String scriptRequiredContexts) {
		_scriptRequiredContexts = scriptRequiredContexts;

		if (_kaleoConditionRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoConditionRemoteModel.getClass();

				Method method = clazz.getMethod("setScriptRequiredContexts",
						String.class);

				method.invoke(_kaleoConditionRemoteModel, scriptRequiredContexts);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getKaleoConditionRemoteModel() {
		return _kaleoConditionRemoteModel;
	}

	public void setKaleoConditionRemoteModel(
		BaseModel<?> kaleoConditionRemoteModel) {
		_kaleoConditionRemoteModel = kaleoConditionRemoteModel;
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

		Class<?> remoteModelClass = _kaleoConditionRemoteModel.getClass();

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

		Object returnValue = method.invoke(_kaleoConditionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			KaleoConditionLocalServiceUtil.addKaleoCondition(this);
		}
		else {
			KaleoConditionLocalServiceUtil.updateKaleoCondition(this);
		}
	}

	@Override
	public KaleoCondition toEscapedModel() {
		return (KaleoCondition)ProxyUtil.newProxyInstance(KaleoCondition.class.getClassLoader(),
			new Class[] { KaleoCondition.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoConditionClp clone = new KaleoConditionClp();

		clone.setKaleoConditionId(getKaleoConditionId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoNodeId(getKaleoNodeId());
		clone.setScript(getScript());
		clone.setScriptLanguage(getScriptLanguage());
		clone.setScriptRequiredContexts(getScriptRequiredContexts());

		return clone;
	}

	@Override
	public int compareTo(KaleoCondition kaleoCondition) {
		int value = 0;

		if (getKaleoConditionId() < kaleoCondition.getKaleoConditionId()) {
			value = -1;
		}
		else if (getKaleoConditionId() > kaleoCondition.getKaleoConditionId()) {
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

		if (!(obj instanceof KaleoConditionClp)) {
			return false;
		}

		KaleoConditionClp kaleoCondition = (KaleoConditionClp)obj;

		long primaryKey = kaleoCondition.getPrimaryKey();

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

		sb.append("{kaleoConditionId=");
		sb.append(getKaleoConditionId());
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
		sb.append(", script=");
		sb.append(getScript());
		sb.append(", scriptLanguage=");
		sb.append(getScriptLanguage());
		sb.append(", scriptRequiredContexts=");
		sb.append(getScriptRequiredContexts());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoCondition");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoConditionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoConditionId());
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
			"<column><column-name>script</column-name><column-value><![CDATA[");
		sb.append(getScript());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scriptLanguage</column-name><column-value><![CDATA[");
		sb.append(getScriptLanguage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scriptRequiredContexts</column-name><column-value><![CDATA[");
		sb.append(getScriptRequiredContexts());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoConditionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoNodeId;
	private String _script;
	private String _scriptLanguage;
	private String _scriptRequiredContexts;
	private BaseModel<?> _kaleoConditionRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}