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
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class KaleoInstanceTokenClp extends BaseModelImpl<KaleoInstanceToken>
	implements KaleoInstanceToken {
	public KaleoInstanceTokenClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoInstanceToken.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoInstanceToken.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoInstanceTokenId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoInstanceTokenId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoInstanceTokenId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoInstanceTokenId", getKaleoInstanceTokenId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoInstanceId", getKaleoInstanceId());
		attributes.put("parentKaleoInstanceTokenId",
			getParentKaleoInstanceTokenId());
		attributes.put("currentKaleoNodeId", getCurrentKaleoNodeId());
		attributes.put("currentKaleoNodeName", getCurrentKaleoNodeName());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("completed", getCompleted());
		attributes.put("completionDate", getCompletionDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoInstanceTokenId = (Long)attributes.get("kaleoInstanceTokenId");

		if (kaleoInstanceTokenId != null) {
			setKaleoInstanceTokenId(kaleoInstanceTokenId);
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

		Long parentKaleoInstanceTokenId = (Long)attributes.get(
				"parentKaleoInstanceTokenId");

		if (parentKaleoInstanceTokenId != null) {
			setParentKaleoInstanceTokenId(parentKaleoInstanceTokenId);
		}

		Long currentKaleoNodeId = (Long)attributes.get("currentKaleoNodeId");

		if (currentKaleoNodeId != null) {
			setCurrentKaleoNodeId(currentKaleoNodeId);
		}

		String currentKaleoNodeName = (String)attributes.get(
				"currentKaleoNodeName");

		if (currentKaleoNodeName != null) {
			setCurrentKaleoNodeName(currentKaleoNodeName);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
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
	public long getKaleoInstanceTokenId() {
		return _kaleoInstanceTokenId;
	}

	@Override
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoInstanceTokenId = kaleoInstanceTokenId;

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoInstanceTokenId",
						long.class);

				method.invoke(_kaleoInstanceTokenRemoteModel,
					kaleoInstanceTokenId);
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

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_kaleoInstanceTokenRemoteModel, groupId);
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

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_kaleoInstanceTokenRemoteModel, companyId);
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

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_kaleoInstanceTokenRemoteModel, userId);
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

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_kaleoInstanceTokenRemoteModel, userName);
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

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_kaleoInstanceTokenRemoteModel, createDate);
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

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_kaleoInstanceTokenRemoteModel, modifiedDate);
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

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoDefinitionId",
						long.class);

				method.invoke(_kaleoInstanceTokenRemoteModel, kaleoDefinitionId);
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

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoInstanceId", long.class);

				method.invoke(_kaleoInstanceTokenRemoteModel, kaleoInstanceId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getParentKaleoInstanceTokenId() {
		return _parentKaleoInstanceTokenId;
	}

	@Override
	public void setParentKaleoInstanceTokenId(long parentKaleoInstanceTokenId) {
		_parentKaleoInstanceTokenId = parentKaleoInstanceTokenId;

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setParentKaleoInstanceTokenId",
						long.class);

				method.invoke(_kaleoInstanceTokenRemoteModel,
					parentKaleoInstanceTokenId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCurrentKaleoNodeId() {
		return _currentKaleoNodeId;
	}

	@Override
	public void setCurrentKaleoNodeId(long currentKaleoNodeId) {
		_currentKaleoNodeId = currentKaleoNodeId;

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrentKaleoNodeId",
						long.class);

				method.invoke(_kaleoInstanceTokenRemoteModel, currentKaleoNodeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCurrentKaleoNodeName() {
		return _currentKaleoNodeName;
	}

	@Override
	public void setCurrentKaleoNodeName(String currentKaleoNodeName) {
		_currentKaleoNodeName = currentKaleoNodeName;

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrentKaleoNodeName",
						String.class);

				method.invoke(_kaleoInstanceTokenRemoteModel,
					currentKaleoNodeName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getClassName() {
		return _className;
	}

	@Override
	public void setClassName(String className) {
		_className = className;

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setClassName", String.class);

				method.invoke(_kaleoInstanceTokenRemoteModel, className);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_classPK = classPK;

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_kaleoInstanceTokenRemoteModel, classPK);
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

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCompleted", boolean.class);

				method.invoke(_kaleoInstanceTokenRemoteModel, completed);
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

		if (_kaleoInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCompletionDate", Date.class);

				method.invoke(_kaleoInstanceTokenRemoteModel, completionDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean hasIncompleteChildrenKaleoInstanceToken() {
		try {
			String methodName = "hasIncompleteChildrenKaleoInstanceToken";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getIncompleteChildrenKaleoInstanceTokens() {
		try {
			String methodName = "getIncompleteChildrenKaleoInstanceTokens";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> returnObj =
				(java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNode getCurrentKaleoNode() {
		try {
			String methodName = "getCurrentKaleoNode";

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
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getParentKaleoInstanceToken() {
		try {
			String methodName = "getParentKaleoInstanceToken";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken returnObj =
				(com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public void setCurrentKaleoNode(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode) {
		try {
			String methodName = "setCurrentKaleoNode";

			Class<?>[] parameterTypes = new Class<?>[] {
					com.liferay.portal.workflow.kaleo.model.KaleoNode.class
				};

			Object[] parameterValues = new Object[] { kaleoNode };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstance getKaleoInstance() {
		try {
			String methodName = "getKaleoInstance";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.workflow.kaleo.model.KaleoInstance returnObj = (com.liferay.portal.workflow.kaleo.model.KaleoInstance)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getChildrenKaleoInstanceTokens() {
		try {
			String methodName = "getChildrenKaleoInstanceTokens";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> returnObj =
				(java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getKaleoInstanceTokenRemoteModel() {
		return _kaleoInstanceTokenRemoteModel;
	}

	public void setKaleoInstanceTokenRemoteModel(
		BaseModel<?> kaleoInstanceTokenRemoteModel) {
		_kaleoInstanceTokenRemoteModel = kaleoInstanceTokenRemoteModel;
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

		Class<?> remoteModelClass = _kaleoInstanceTokenRemoteModel.getClass();

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

		Object returnValue = method.invoke(_kaleoInstanceTokenRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			KaleoInstanceTokenLocalServiceUtil.addKaleoInstanceToken(this);
		}
		else {
			KaleoInstanceTokenLocalServiceUtil.updateKaleoInstanceToken(this);
		}
	}

	@Override
	public KaleoInstanceToken toEscapedModel() {
		return (KaleoInstanceToken)ProxyUtil.newProxyInstance(KaleoInstanceToken.class.getClassLoader(),
			new Class[] { KaleoInstanceToken.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoInstanceTokenClp clone = new KaleoInstanceTokenClp();

		clone.setKaleoInstanceTokenId(getKaleoInstanceTokenId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoInstanceId(getKaleoInstanceId());
		clone.setParentKaleoInstanceTokenId(getParentKaleoInstanceTokenId());
		clone.setCurrentKaleoNodeId(getCurrentKaleoNodeId());
		clone.setCurrentKaleoNodeName(getCurrentKaleoNodeName());
		clone.setClassName(getClassName());
		clone.setClassPK(getClassPK());
		clone.setCompleted(getCompleted());
		clone.setCompletionDate(getCompletionDate());

		return clone;
	}

	@Override
	public int compareTo(KaleoInstanceToken kaleoInstanceToken) {
		int value = 0;

		if (getKaleoInstanceTokenId() < kaleoInstanceToken.getKaleoInstanceTokenId()) {
			value = -1;
		}
		else if (getKaleoInstanceTokenId() > kaleoInstanceToken.getKaleoInstanceTokenId()) {
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

		if (!(obj instanceof KaleoInstanceTokenClp)) {
			return false;
		}

		KaleoInstanceTokenClp kaleoInstanceToken = (KaleoInstanceTokenClp)obj;

		long primaryKey = kaleoInstanceToken.getPrimaryKey();

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

		sb.append("{kaleoInstanceTokenId=");
		sb.append(getKaleoInstanceTokenId());
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
		sb.append(", parentKaleoInstanceTokenId=");
		sb.append(getParentKaleoInstanceTokenId());
		sb.append(", currentKaleoNodeId=");
		sb.append(getCurrentKaleoNodeId());
		sb.append(", currentKaleoNodeName=");
		sb.append(getCurrentKaleoNodeName());
		sb.append(", className=");
		sb.append(getClassName());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", completed=");
		sb.append(getCompleted());
		sb.append(", completionDate=");
		sb.append(getCompletionDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoInstanceTokenId());
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
			"<column><column-name>parentKaleoInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getParentKaleoInstanceTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentKaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getCurrentKaleoNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentKaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getCurrentKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>className</column-name><column-value><![CDATA[");
		sb.append(getClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
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

	private long _kaleoInstanceTokenId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoInstanceId;
	private long _parentKaleoInstanceTokenId;
	private long _currentKaleoNodeId;
	private String _currentKaleoNodeName;
	private String _className;
	private long _classPK;
	private boolean _completed;
	private Date _completionDate;
	private BaseModel<?> _kaleoInstanceTokenRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}