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
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class KaleoTaskAssignmentClp extends BaseModelImpl<KaleoTaskAssignment>
	implements KaleoTaskAssignment {
	public KaleoTaskAssignmentClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoTaskAssignment.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTaskAssignment.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoTaskAssignmentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoTaskAssignmentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoTaskAssignmentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoTaskAssignmentId", getKaleoTaskAssignmentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoClassName", getKaleoClassName());
		attributes.put("kaleoClassPK", getKaleoClassPK());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoNodeId", getKaleoNodeId());
		attributes.put("assigneeClassName", getAssigneeClassName());
		attributes.put("assigneeClassPK", getAssigneeClassPK());
		attributes.put("assigneeActionId", getAssigneeActionId());
		attributes.put("assigneeScript", getAssigneeScript());
		attributes.put("assigneeScriptLanguage", getAssigneeScriptLanguage());
		attributes.put("assigneeScriptRequiredContexts",
			getAssigneeScriptRequiredContexts());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoTaskAssignmentId = (Long)attributes.get(
				"kaleoTaskAssignmentId");

		if (kaleoTaskAssignmentId != null) {
			setKaleoTaskAssignmentId(kaleoTaskAssignmentId);
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

		String kaleoClassName = (String)attributes.get("kaleoClassName");

		if (kaleoClassName != null) {
			setKaleoClassName(kaleoClassName);
		}

		Long kaleoClassPK = (Long)attributes.get("kaleoClassPK");

		if (kaleoClassPK != null) {
			setKaleoClassPK(kaleoClassPK);
		}

		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
		}

		Long kaleoNodeId = (Long)attributes.get("kaleoNodeId");

		if (kaleoNodeId != null) {
			setKaleoNodeId(kaleoNodeId);
		}

		String assigneeClassName = (String)attributes.get("assigneeClassName");

		if (assigneeClassName != null) {
			setAssigneeClassName(assigneeClassName);
		}

		Long assigneeClassPK = (Long)attributes.get("assigneeClassPK");

		if (assigneeClassPK != null) {
			setAssigneeClassPK(assigneeClassPK);
		}

		String assigneeActionId = (String)attributes.get("assigneeActionId");

		if (assigneeActionId != null) {
			setAssigneeActionId(assigneeActionId);
		}

		String assigneeScript = (String)attributes.get("assigneeScript");

		if (assigneeScript != null) {
			setAssigneeScript(assigneeScript);
		}

		String assigneeScriptLanguage = (String)attributes.get(
				"assigneeScriptLanguage");

		if (assigneeScriptLanguage != null) {
			setAssigneeScriptLanguage(assigneeScriptLanguage);
		}

		String assigneeScriptRequiredContexts = (String)attributes.get(
				"assigneeScriptRequiredContexts");

		if (assigneeScriptRequiredContexts != null) {
			setAssigneeScriptRequiredContexts(assigneeScriptRequiredContexts);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getKaleoTaskAssignmentId() {
		return _kaleoTaskAssignmentId;
	}

	@Override
	public void setKaleoTaskAssignmentId(long kaleoTaskAssignmentId) {
		_kaleoTaskAssignmentId = kaleoTaskAssignmentId;

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoTaskAssignmentId",
						long.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel,
					kaleoTaskAssignmentId);
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

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, groupId);
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

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, companyId);
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

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, userId);
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

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, userName);
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

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, createDate);
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

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKaleoClassName() {
		return _kaleoClassName;
	}

	@Override
	public void setKaleoClassName(String kaleoClassName) {
		_kaleoClassName = kaleoClassName;

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoClassName",
						String.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, kaleoClassName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoClassPK() {
		return _kaleoClassPK;
	}

	@Override
	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoClassPK = kaleoClassPK;

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoClassPK", long.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, kaleoClassPK);
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

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoDefinitionId",
						long.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, kaleoDefinitionId);
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

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoNodeId", long.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, kaleoNodeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAssigneeClassName() {
		return _assigneeClassName;
	}

	@Override
	public void setAssigneeClassName(String assigneeClassName) {
		_assigneeClassName = assigneeClassName;

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAssigneeClassName",
						String.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, assigneeClassName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAssigneeClassPK() {
		return _assigneeClassPK;
	}

	@Override
	public void setAssigneeClassPK(long assigneeClassPK) {
		_assigneeClassPK = assigneeClassPK;

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAssigneeClassPK", long.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, assigneeClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAssigneeActionId() {
		return _assigneeActionId;
	}

	@Override
	public void setAssigneeActionId(String assigneeActionId) {
		_assigneeActionId = assigneeActionId;

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAssigneeActionId",
						String.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, assigneeActionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAssigneeScript() {
		return _assigneeScript;
	}

	@Override
	public void setAssigneeScript(String assigneeScript) {
		_assigneeScript = assigneeScript;

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAssigneeScript",
						String.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel, assigneeScript);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAssigneeScriptLanguage() {
		return _assigneeScriptLanguage;
	}

	@Override
	public void setAssigneeScriptLanguage(String assigneeScriptLanguage) {
		_assigneeScriptLanguage = assigneeScriptLanguage;

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAssigneeScriptLanguage",
						String.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel,
					assigneeScriptLanguage);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAssigneeScriptRequiredContexts() {
		return _assigneeScriptRequiredContexts;
	}

	@Override
	public void setAssigneeScriptRequiredContexts(
		String assigneeScriptRequiredContexts) {
		_assigneeScriptRequiredContexts = assigneeScriptRequiredContexts;

		if (_kaleoTaskAssignmentRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAssigneeScriptRequiredContexts",
						String.class);

				method.invoke(_kaleoTaskAssignmentRemoteModel,
					assigneeScriptRequiredContexts);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getKaleoTaskAssignmentRemoteModel() {
		return _kaleoTaskAssignmentRemoteModel;
	}

	public void setKaleoTaskAssignmentRemoteModel(
		BaseModel<?> kaleoTaskAssignmentRemoteModel) {
		_kaleoTaskAssignmentRemoteModel = kaleoTaskAssignmentRemoteModel;
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

		Class<?> remoteModelClass = _kaleoTaskAssignmentRemoteModel.getClass();

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

		Object returnValue = method.invoke(_kaleoTaskAssignmentRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			KaleoTaskAssignmentLocalServiceUtil.addKaleoTaskAssignment(this);
		}
		else {
			KaleoTaskAssignmentLocalServiceUtil.updateKaleoTaskAssignment(this);
		}
	}

	@Override
	public KaleoTaskAssignment toEscapedModel() {
		return (KaleoTaskAssignment)ProxyUtil.newProxyInstance(KaleoTaskAssignment.class.getClassLoader(),
			new Class[] { KaleoTaskAssignment.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoTaskAssignmentClp clone = new KaleoTaskAssignmentClp();

		clone.setKaleoTaskAssignmentId(getKaleoTaskAssignmentId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoClassName(getKaleoClassName());
		clone.setKaleoClassPK(getKaleoClassPK());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoNodeId(getKaleoNodeId());
		clone.setAssigneeClassName(getAssigneeClassName());
		clone.setAssigneeClassPK(getAssigneeClassPK());
		clone.setAssigneeActionId(getAssigneeActionId());
		clone.setAssigneeScript(getAssigneeScript());
		clone.setAssigneeScriptLanguage(getAssigneeScriptLanguage());
		clone.setAssigneeScriptRequiredContexts(getAssigneeScriptRequiredContexts());

		return clone;
	}

	@Override
	public int compareTo(KaleoTaskAssignment kaleoTaskAssignment) {
		int value = 0;

		if (getKaleoTaskAssignmentId() < kaleoTaskAssignment.getKaleoTaskAssignmentId()) {
			value = -1;
		}
		else if (getKaleoTaskAssignmentId() > kaleoTaskAssignment.getKaleoTaskAssignmentId()) {
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

		if (!(obj instanceof KaleoTaskAssignmentClp)) {
			return false;
		}

		KaleoTaskAssignmentClp kaleoTaskAssignment = (KaleoTaskAssignmentClp)obj;

		long primaryKey = kaleoTaskAssignment.getPrimaryKey();

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
		StringBundler sb = new StringBundler(35);

		sb.append("{kaleoTaskAssignmentId=");
		sb.append(getKaleoTaskAssignmentId());
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
		sb.append(", kaleoClassName=");
		sb.append(getKaleoClassName());
		sb.append(", kaleoClassPK=");
		sb.append(getKaleoClassPK());
		sb.append(", kaleoDefinitionId=");
		sb.append(getKaleoDefinitionId());
		sb.append(", kaleoNodeId=");
		sb.append(getKaleoNodeId());
		sb.append(", assigneeClassName=");
		sb.append(getAssigneeClassName());
		sb.append(", assigneeClassPK=");
		sb.append(getAssigneeClassPK());
		sb.append(", assigneeActionId=");
		sb.append(getAssigneeActionId());
		sb.append(", assigneeScript=");
		sb.append(getAssigneeScript());
		sb.append(", assigneeScriptLanguage=");
		sb.append(getAssigneeScriptLanguage());
		sb.append(", assigneeScriptRequiredContexts=");
		sb.append(getAssigneeScriptRequiredContexts());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoTaskAssignmentId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskAssignmentId());
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
			"<column><column-name>kaleoClassName</column-name><column-value><![CDATA[");
		sb.append(getKaleoClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoClassPK</column-name><column-value><![CDATA[");
		sb.append(getKaleoClassPK());
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
			"<column><column-name>assigneeClassName</column-name><column-value><![CDATA[");
		sb.append(getAssigneeClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeClassPK</column-name><column-value><![CDATA[");
		sb.append(getAssigneeClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeActionId</column-name><column-value><![CDATA[");
		sb.append(getAssigneeActionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeScript</column-name><column-value><![CDATA[");
		sb.append(getAssigneeScript());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeScriptLanguage</column-name><column-value><![CDATA[");
		sb.append(getAssigneeScriptLanguage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeScriptRequiredContexts</column-name><column-value><![CDATA[");
		sb.append(getAssigneeScriptRequiredContexts());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoTaskAssignmentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _kaleoClassName;
	private long _kaleoClassPK;
	private long _kaleoDefinitionId;
	private long _kaleoNodeId;
	private String _assigneeClassName;
	private long _assigneeClassPK;
	private String _assigneeActionId;
	private String _assigneeScript;
	private String _assigneeScriptLanguage;
	private String _assigneeScriptRequiredContexts;
	private BaseModel<?> _kaleoTaskAssignmentRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}