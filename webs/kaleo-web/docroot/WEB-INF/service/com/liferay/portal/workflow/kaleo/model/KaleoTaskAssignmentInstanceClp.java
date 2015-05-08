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
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentInstanceLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class KaleoTaskAssignmentInstanceClp extends BaseModelImpl<KaleoTaskAssignmentInstance>
	implements KaleoTaskAssignmentInstance {
	public KaleoTaskAssignmentInstanceClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoTaskAssignmentInstance.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTaskAssignmentInstance.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoTaskAssignmentInstanceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoTaskAssignmentInstanceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoTaskAssignmentInstanceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoTaskAssignmentInstanceId",
			getKaleoTaskAssignmentInstanceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoInstanceId", getKaleoInstanceId());
		attributes.put("kaleoInstanceTokenId", getKaleoInstanceTokenId());
		attributes.put("kaleoTaskInstanceTokenId", getKaleoTaskInstanceTokenId());
		attributes.put("kaleoTaskId", getKaleoTaskId());
		attributes.put("kaleoTaskName", getKaleoTaskName());
		attributes.put("assigneeClassName", getAssigneeClassName());
		attributes.put("assigneeClassPK", getAssigneeClassPK());
		attributes.put("completed", getCompleted());
		attributes.put("completionDate", getCompletionDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoTaskAssignmentInstanceId = (Long)attributes.get(
				"kaleoTaskAssignmentInstanceId");

		if (kaleoTaskAssignmentInstanceId != null) {
			setKaleoTaskAssignmentInstanceId(kaleoTaskAssignmentInstanceId);
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

		Long kaleoInstanceId = (Long)attributes.get("kaleoInstanceId");

		if (kaleoInstanceId != null) {
			setKaleoInstanceId(kaleoInstanceId);
		}

		Long kaleoInstanceTokenId = (Long)attributes.get("kaleoInstanceTokenId");

		if (kaleoInstanceTokenId != null) {
			setKaleoInstanceTokenId(kaleoInstanceTokenId);
		}

		Long kaleoTaskInstanceTokenId = (Long)attributes.get(
				"kaleoTaskInstanceTokenId");

		if (kaleoTaskInstanceTokenId != null) {
			setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
		}

		Long kaleoTaskId = (Long)attributes.get("kaleoTaskId");

		if (kaleoTaskId != null) {
			setKaleoTaskId(kaleoTaskId);
		}

		String kaleoTaskName = (String)attributes.get("kaleoTaskName");

		if (kaleoTaskName != null) {
			setKaleoTaskName(kaleoTaskName);
		}

		String assigneeClassName = (String)attributes.get("assigneeClassName");

		if (assigneeClassName != null) {
			setAssigneeClassName(assigneeClassName);
		}

		Long assigneeClassPK = (Long)attributes.get("assigneeClassPK");

		if (assigneeClassPK != null) {
			setAssigneeClassPK(assigneeClassPK);
		}

		Boolean completed = (Boolean)attributes.get("completed");

		if (completed != null) {
			setCompleted(completed);
		}

		Date completionDate = (Date)attributes.get("completionDate");

		if (completionDate != null) {
			setCompletionDate(completionDate);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getKaleoTaskAssignmentInstanceId() {
		return _kaleoTaskAssignmentInstanceId;
	}

	@Override
	public void setKaleoTaskAssignmentInstanceId(
		long kaleoTaskAssignmentInstanceId) {
		_kaleoTaskAssignmentInstanceId = kaleoTaskAssignmentInstanceId;

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoTaskAssignmentInstanceId",
						long.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel,
					kaleoTaskAssignmentInstanceId);
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

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel, groupId);
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

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel, companyId);
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

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel, userId);
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

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel, userName);
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

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel,
					createDate);
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

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel,
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

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoDefinitionId",
						long.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel,
					kaleoDefinitionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	@Override
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstanceId = kaleoInstanceId;

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoInstanceId", long.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel,
					kaleoInstanceId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoInstanceTokenId() {
		return _kaleoInstanceTokenId;
	}

	@Override
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoInstanceTokenId = kaleoInstanceTokenId;

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoInstanceTokenId",
						long.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel,
					kaleoInstanceTokenId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskInstanceTokenId;
	}

	@Override
	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTaskInstanceTokenId = kaleoTaskInstanceTokenId;

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoTaskInstanceTokenId",
						long.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel,
					kaleoTaskInstanceTokenId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoTaskId() {
		return _kaleoTaskId;
	}

	@Override
	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTaskId = kaleoTaskId;

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoTaskId", long.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel,
					kaleoTaskId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKaleoTaskName() {
		return _kaleoTaskName;
	}

	@Override
	public void setKaleoTaskName(String kaleoTaskName) {
		_kaleoTaskName = kaleoTaskName;

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoTaskName", String.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel,
					kaleoTaskName);
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

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setAssigneeClassName",
						String.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel,
					assigneeClassName);
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

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setAssigneeClassPK", long.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel,
					assigneeClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getCompleted() {
		return _completed;
	}

	@Override
	public boolean isCompleted() {
		return _completed;
	}

	@Override
	public void setCompleted(boolean completed) {
		_completed = completed;

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setCompleted", boolean.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel, completed);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCompletionDate() {
		return _completionDate;
	}

	@Override
	public void setCompletionDate(Date completionDate) {
		_completionDate = completionDate;

		if (_kaleoTaskAssignmentInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setCompletionDate", Date.class);

				method.invoke(_kaleoTaskAssignmentInstanceRemoteModel,
					completionDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getKaleoTaskAssignmentInstanceRemoteModel() {
		return _kaleoTaskAssignmentInstanceRemoteModel;
	}

	public void setKaleoTaskAssignmentInstanceRemoteModel(
		BaseModel<?> kaleoTaskAssignmentInstanceRemoteModel) {
		_kaleoTaskAssignmentInstanceRemoteModel = kaleoTaskAssignmentInstanceRemoteModel;
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

		Class<?> remoteModelClass = _kaleoTaskAssignmentInstanceRemoteModel.getClass();

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

		Object returnValue = method.invoke(_kaleoTaskAssignmentInstanceRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			KaleoTaskAssignmentInstanceLocalServiceUtil.addKaleoTaskAssignmentInstance(this);
		}
		else {
			KaleoTaskAssignmentInstanceLocalServiceUtil.updateKaleoTaskAssignmentInstance(this);
		}
	}

	@Override
	public KaleoTaskAssignmentInstance toEscapedModel() {
		return (KaleoTaskAssignmentInstance)ProxyUtil.newProxyInstance(KaleoTaskAssignmentInstance.class.getClassLoader(),
			new Class[] { KaleoTaskAssignmentInstance.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoTaskAssignmentInstanceClp clone = new KaleoTaskAssignmentInstanceClp();

		clone.setKaleoTaskAssignmentInstanceId(getKaleoTaskAssignmentInstanceId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoInstanceId(getKaleoInstanceId());
		clone.setKaleoInstanceTokenId(getKaleoInstanceTokenId());
		clone.setKaleoTaskInstanceTokenId(getKaleoTaskInstanceTokenId());
		clone.setKaleoTaskId(getKaleoTaskId());
		clone.setKaleoTaskName(getKaleoTaskName());
		clone.setAssigneeClassName(getAssigneeClassName());
		clone.setAssigneeClassPK(getAssigneeClassPK());
		clone.setCompleted(getCompleted());
		clone.setCompletionDate(getCompletionDate());

		return clone;
	}

	@Override
	public int compareTo(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		int value = 0;

		if (getKaleoTaskAssignmentInstanceId() < kaleoTaskAssignmentInstance.getKaleoTaskAssignmentInstanceId()) {
			value = -1;
		}
		else if (getKaleoTaskAssignmentInstanceId() > kaleoTaskAssignmentInstance.getKaleoTaskAssignmentInstanceId()) {
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

		if (!(obj instanceof KaleoTaskAssignmentInstanceClp)) {
			return false;
		}

		KaleoTaskAssignmentInstanceClp kaleoTaskAssignmentInstance = (KaleoTaskAssignmentInstanceClp)obj;

		long primaryKey = kaleoTaskAssignmentInstance.getPrimaryKey();

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

		sb.append("{kaleoTaskAssignmentInstanceId=");
		sb.append(getKaleoTaskAssignmentInstanceId());
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
		sb.append(", kaleoInstanceId=");
		sb.append(getKaleoInstanceId());
		sb.append(", kaleoInstanceTokenId=");
		sb.append(getKaleoInstanceTokenId());
		sb.append(", kaleoTaskInstanceTokenId=");
		sb.append(getKaleoTaskInstanceTokenId());
		sb.append(", kaleoTaskId=");
		sb.append(getKaleoTaskId());
		sb.append(", kaleoTaskName=");
		sb.append(getKaleoTaskName());
		sb.append(", assigneeClassName=");
		sb.append(getAssigneeClassName());
		sb.append(", assigneeClassPK=");
		sb.append(getAssigneeClassPK());
		sb.append(", completed=");
		sb.append(getCompleted());
		sb.append(", completionDate=");
		sb.append(getCompletionDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoTaskAssignmentInstanceId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskAssignmentInstanceId());
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
			"<column><column-name>kaleoInstanceId</column-name><column-value><![CDATA[");
		sb.append(getKaleoInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoInstanceTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoTaskInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskInstanceTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoTaskId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoTaskName</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskName());
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
			"<column><column-name>completed</column-name><column-value><![CDATA[");
		sb.append(getCompleted());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completionDate</column-name><column-value><![CDATA[");
		sb.append(getCompletionDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoTaskAssignmentInstanceId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoInstanceId;
	private long _kaleoInstanceTokenId;
	private long _kaleoTaskInstanceTokenId;
	private long _kaleoTaskId;
	private String _kaleoTaskName;
	private String _assigneeClassName;
	private long _assigneeClassPK;
	private boolean _completed;
	private Date _completionDate;
	private BaseModel<?> _kaleoTaskAssignmentInstanceRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}