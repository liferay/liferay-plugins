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
import com.liferay.portal.workflow.kaleo.service.KaleoTimerInstanceTokenLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class KaleoTimerInstanceTokenClp extends BaseModelImpl<KaleoTimerInstanceToken>
	implements KaleoTimerInstanceToken {
	public KaleoTimerInstanceTokenClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoTimerInstanceToken.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTimerInstanceToken.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoTimerInstanceTokenId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoTimerInstanceTokenId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoTimerInstanceTokenId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoTimerInstanceTokenId",
			getKaleoTimerInstanceTokenId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoClassName", getKaleoClassName());
		attributes.put("kaleoClassPK", getKaleoClassPK());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoInstanceId", getKaleoInstanceId());
		attributes.put("kaleoInstanceTokenId", getKaleoInstanceTokenId());
		attributes.put("kaleoTaskInstanceTokenId", getKaleoTaskInstanceTokenId());
		attributes.put("kaleoTimerId", getKaleoTimerId());
		attributes.put("kaleoTimerName", getKaleoTimerName());
		attributes.put("blocking", getBlocking());
		attributes.put("completionUserId", getCompletionUserId());
		attributes.put("completed", getCompleted());
		attributes.put("completionDate", getCompletionDate());
		attributes.put("workflowContext", getWorkflowContext());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoTimerInstanceTokenId = (Long)attributes.get(
				"kaleoTimerInstanceTokenId");

		if (kaleoTimerInstanceTokenId != null) {
			setKaleoTimerInstanceTokenId(kaleoTimerInstanceTokenId);
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

		Long kaleoTimerId = (Long)attributes.get("kaleoTimerId");

		if (kaleoTimerId != null) {
			setKaleoTimerId(kaleoTimerId);
		}

		String kaleoTimerName = (String)attributes.get("kaleoTimerName");

		if (kaleoTimerName != null) {
			setKaleoTimerName(kaleoTimerName);
		}

		Boolean blocking = (Boolean)attributes.get("blocking");

		if (blocking != null) {
			setBlocking(blocking);
		}

		Long completionUserId = (Long)attributes.get("completionUserId");

		if (completionUserId != null) {
			setCompletionUserId(completionUserId);
		}

		Boolean completed = (Boolean)attributes.get("completed");

		if (completed != null) {
			setCompleted(completed);
		}

		Date completionDate = (Date)attributes.get("completionDate");

		if (completionDate != null) {
			setCompletionDate(completionDate);
		}

		String workflowContext = (String)attributes.get("workflowContext");

		if (workflowContext != null) {
			setWorkflowContext(workflowContext);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getKaleoTimerInstanceTokenId() {
		return _kaleoTimerInstanceTokenId;
	}

	@Override
	public void setKaleoTimerInstanceTokenId(long kaleoTimerInstanceTokenId) {
		_kaleoTimerInstanceTokenId = kaleoTimerInstanceTokenId;

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoTimerInstanceTokenId",
						long.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel,
					kaleoTimerInstanceTokenId);
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel, groupId);
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel, companyId);
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel, userId);
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel, userName);
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel, createDate);
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel, modifiedDate);
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoClassName",
						String.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel,
					kaleoClassName);
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoClassPK", long.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel, kaleoClassPK);
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoDefinitionId",
						long.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel,
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoInstanceId", long.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel,
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoInstanceTokenId",
						long.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel,
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoTaskInstanceTokenId",
						long.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel,
					kaleoTaskInstanceTokenId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoTimerId() {
		return _kaleoTimerId;
	}

	@Override
	public void setKaleoTimerId(long kaleoTimerId) {
		_kaleoTimerId = kaleoTimerId;

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoTimerId", long.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel, kaleoTimerId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKaleoTimerName() {
		return _kaleoTimerName;
	}

	@Override
	public void setKaleoTimerName(String kaleoTimerName) {
		_kaleoTimerName = kaleoTimerName;

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoTimerName",
						String.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel,
					kaleoTimerName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getBlocking() {
		return _blocking;
	}

	@Override
	public boolean isBlocking() {
		return _blocking;
	}

	@Override
	public void setBlocking(boolean blocking) {
		_blocking = blocking;

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setBlocking", boolean.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel, blocking);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompletionUserId() {
		return _completionUserId;
	}

	@Override
	public void setCompletionUserId(long completionUserId) {
		_completionUserId = completionUserId;

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCompletionUserId",
						long.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel,
					completionUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCompletionUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getCompletionUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setCompletionUserUuid(String completionUserUuid) {
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCompleted", boolean.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel, completed);
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

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setCompletionDate", Date.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel,
					completionDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getWorkflowContext() {
		return _workflowContext;
	}

	@Override
	public void setWorkflowContext(String workflowContext) {
		_workflowContext = workflowContext;

		if (_kaleoTimerInstanceTokenRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoTimerInstanceTokenRemoteModel.getClass();

				Method method = clazz.getMethod("setWorkflowContext",
						String.class);

				method.invoke(_kaleoTimerInstanceTokenRemoteModel,
					workflowContext);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getKaleoInstanceToken() {
		try {
			String methodName = "getKaleoInstanceToken";

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
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken getKaleoTaskInstanceToken() {
		try {
			String methodName = "getKaleoTaskInstanceToken";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken returnObj =
				(com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer getKaleoTimer() {
		try {
			String methodName = "getKaleoTimer";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.workflow.kaleo.model.KaleoTimer returnObj = (com.liferay.portal.workflow.kaleo.model.KaleoTimer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getKaleoTimerInstanceTokenRemoteModel() {
		return _kaleoTimerInstanceTokenRemoteModel;
	}

	public void setKaleoTimerInstanceTokenRemoteModel(
		BaseModel<?> kaleoTimerInstanceTokenRemoteModel) {
		_kaleoTimerInstanceTokenRemoteModel = kaleoTimerInstanceTokenRemoteModel;
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

		Class<?> remoteModelClass = _kaleoTimerInstanceTokenRemoteModel.getClass();

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

		Object returnValue = method.invoke(_kaleoTimerInstanceTokenRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			KaleoTimerInstanceTokenLocalServiceUtil.addKaleoTimerInstanceToken(this);
		}
		else {
			KaleoTimerInstanceTokenLocalServiceUtil.updateKaleoTimerInstanceToken(this);
		}
	}

	@Override
	public KaleoTimerInstanceToken toEscapedModel() {
		return (KaleoTimerInstanceToken)ProxyUtil.newProxyInstance(KaleoTimerInstanceToken.class.getClassLoader(),
			new Class[] { KaleoTimerInstanceToken.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoTimerInstanceTokenClp clone = new KaleoTimerInstanceTokenClp();

		clone.setKaleoTimerInstanceTokenId(getKaleoTimerInstanceTokenId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoClassName(getKaleoClassName());
		clone.setKaleoClassPK(getKaleoClassPK());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoInstanceId(getKaleoInstanceId());
		clone.setKaleoInstanceTokenId(getKaleoInstanceTokenId());
		clone.setKaleoTaskInstanceTokenId(getKaleoTaskInstanceTokenId());
		clone.setKaleoTimerId(getKaleoTimerId());
		clone.setKaleoTimerName(getKaleoTimerName());
		clone.setBlocking(getBlocking());
		clone.setCompletionUserId(getCompletionUserId());
		clone.setCompleted(getCompleted());
		clone.setCompletionDate(getCompletionDate());
		clone.setWorkflowContext(getWorkflowContext());

		return clone;
	}

	@Override
	public int compareTo(KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		int value = 0;

		if (getKaleoTimerInstanceTokenId() < kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId()) {
			value = -1;
		}
		else if (getKaleoTimerInstanceTokenId() > kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId()) {
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

		if (!(obj instanceof KaleoTimerInstanceTokenClp)) {
			return false;
		}

		KaleoTimerInstanceTokenClp kaleoTimerInstanceToken = (KaleoTimerInstanceTokenClp)obj;

		long primaryKey = kaleoTimerInstanceToken.getPrimaryKey();

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
		StringBundler sb = new StringBundler(41);

		sb.append("{kaleoTimerInstanceTokenId=");
		sb.append(getKaleoTimerInstanceTokenId());
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
		sb.append(", kaleoInstanceId=");
		sb.append(getKaleoInstanceId());
		sb.append(", kaleoInstanceTokenId=");
		sb.append(getKaleoInstanceTokenId());
		sb.append(", kaleoTaskInstanceTokenId=");
		sb.append(getKaleoTaskInstanceTokenId());
		sb.append(", kaleoTimerId=");
		sb.append(getKaleoTimerId());
		sb.append(", kaleoTimerName=");
		sb.append(getKaleoTimerName());
		sb.append(", blocking=");
		sb.append(getBlocking());
		sb.append(", completionUserId=");
		sb.append(getCompletionUserId());
		sb.append(", completed=");
		sb.append(getCompleted());
		sb.append(", completionDate=");
		sb.append(getCompletionDate());
		sb.append(", workflowContext=");
		sb.append(getWorkflowContext());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(64);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoTimerInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTimerInstanceTokenId());
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
			"<column><column-name>kaleoTimerId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTimerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoTimerName</column-name><column-value><![CDATA[");
		sb.append(getKaleoTimerName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>blocking</column-name><column-value><![CDATA[");
		sb.append(getBlocking());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completionUserId</column-name><column-value><![CDATA[");
		sb.append(getCompletionUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completed</column-name><column-value><![CDATA[");
		sb.append(getCompleted());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completionDate</column-name><column-value><![CDATA[");
		sb.append(getCompletionDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowContext</column-name><column-value><![CDATA[");
		sb.append(getWorkflowContext());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoTimerInstanceTokenId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _kaleoClassName;
	private long _kaleoClassPK;
	private long _kaleoDefinitionId;
	private long _kaleoInstanceId;
	private long _kaleoInstanceTokenId;
	private long _kaleoTaskInstanceTokenId;
	private long _kaleoTimerId;
	private String _kaleoTimerName;
	private boolean _blocking;
	private long _completionUserId;
	private boolean _completed;
	private Date _completionDate;
	private String _workflowContext;
	private BaseModel<?> _kaleoTimerInstanceTokenRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}