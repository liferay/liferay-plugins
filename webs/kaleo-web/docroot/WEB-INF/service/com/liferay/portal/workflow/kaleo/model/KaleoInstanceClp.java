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
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class KaleoInstanceClp extends BaseModelImpl<KaleoInstance>
	implements KaleoInstance {
	public KaleoInstanceClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoInstance.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoInstance.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoInstanceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoInstanceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoInstanceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoInstanceId", getKaleoInstanceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoDefinitionName", getKaleoDefinitionName());
		attributes.put("kaleoDefinitionVersion", getKaleoDefinitionVersion());
		attributes.put("rootKaleoInstanceTokenId", getRootKaleoInstanceTokenId());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("completed", getCompleted());
		attributes.put("completionDate", getCompletionDate());
		attributes.put("workflowContext", getWorkflowContext());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoInstanceId = (Long)attributes.get("kaleoInstanceId");

		if (kaleoInstanceId != null) {
			setKaleoInstanceId(kaleoInstanceId);
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

		String kaleoDefinitionName = (String)attributes.get(
				"kaleoDefinitionName");

		if (kaleoDefinitionName != null) {
			setKaleoDefinitionName(kaleoDefinitionName);
		}

		Integer kaleoDefinitionVersion = (Integer)attributes.get(
				"kaleoDefinitionVersion");

		if (kaleoDefinitionVersion != null) {
			setKaleoDefinitionVersion(kaleoDefinitionVersion);
		}

		Long rootKaleoInstanceTokenId = (Long)attributes.get(
				"rootKaleoInstanceTokenId");

		if (rootKaleoInstanceTokenId != null) {
			setRootKaleoInstanceTokenId(rootKaleoInstanceTokenId);
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

		String workflowContext = (String)attributes.get("workflowContext");

		if (workflowContext != null) {
			setWorkflowContext(workflowContext);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	@Override
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstanceId = kaleoInstanceId;

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoInstanceId", long.class);

				method.invoke(_kaleoInstanceRemoteModel, kaleoInstanceId);
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

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_kaleoInstanceRemoteModel, groupId);
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

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_kaleoInstanceRemoteModel, companyId);
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

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_kaleoInstanceRemoteModel, userId);
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

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_kaleoInstanceRemoteModel, userName);
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

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_kaleoInstanceRemoteModel, createDate);
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

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_kaleoInstanceRemoteModel, modifiedDate);
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

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoDefinitionId",
						long.class);

				method.invoke(_kaleoInstanceRemoteModel, kaleoDefinitionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKaleoDefinitionName() {
		return _kaleoDefinitionName;
	}

	@Override
	public void setKaleoDefinitionName(String kaleoDefinitionName) {
		_kaleoDefinitionName = kaleoDefinitionName;

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoDefinitionName",
						String.class);

				method.invoke(_kaleoInstanceRemoteModel, kaleoDefinitionName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getKaleoDefinitionVersion() {
		return _kaleoDefinitionVersion;
	}

	@Override
	public void setKaleoDefinitionVersion(int kaleoDefinitionVersion) {
		_kaleoDefinitionVersion = kaleoDefinitionVersion;

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoDefinitionVersion",
						int.class);

				method.invoke(_kaleoInstanceRemoteModel, kaleoDefinitionVersion);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getRootKaleoInstanceTokenId() {
		return _rootKaleoInstanceTokenId;
	}

	@Override
	public void setRootKaleoInstanceTokenId(long rootKaleoInstanceTokenId) {
		_rootKaleoInstanceTokenId = rootKaleoInstanceTokenId;

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setRootKaleoInstanceTokenId",
						long.class);

				method.invoke(_kaleoInstanceRemoteModel,
					rootKaleoInstanceTokenId);
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

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setClassName", String.class);

				method.invoke(_kaleoInstanceRemoteModel, className);
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

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_kaleoInstanceRemoteModel, classPK);
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

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setCompleted", boolean.class);

				method.invoke(_kaleoInstanceRemoteModel, completed);
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

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setCompletionDate", Date.class);

				method.invoke(_kaleoInstanceRemoteModel, completionDate);
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

		if (_kaleoInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setWorkflowContext",
						String.class);

				method.invoke(_kaleoInstanceRemoteModel, workflowContext);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition getKaleoDefinition() {
		try {
			String methodName = "getKaleoDefinition";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.workflow.kaleo.model.KaleoDefinition returnObj = (com.liferay.portal.workflow.kaleo.model.KaleoDefinition)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getRootKaleoInstanceToken(
		com.liferay.portal.service.ServiceContext serviceContext) {
		try {
			String methodName = "getRootKaleoInstanceToken";

			Class<?>[] parameterTypes = new Class<?>[] {
					com.liferay.portal.service.ServiceContext.class
				};

			Object[] parameterValues = new Object[] { serviceContext };

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
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getRootKaleoInstanceToken(
		Map<java.lang.String, Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext) {
		try {
			String methodName = "getRootKaleoInstanceToken";

			Class<?>[] parameterTypes = new Class<?>[] {
					Map.class, com.liferay.portal.service.ServiceContext.class
				};

			Object[] parameterValues = new Object[] {
					workflowContext, serviceContext
				};

			com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken returnObj =
				(com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getKaleoInstanceRemoteModel() {
		return _kaleoInstanceRemoteModel;
	}

	public void setKaleoInstanceRemoteModel(
		BaseModel<?> kaleoInstanceRemoteModel) {
		_kaleoInstanceRemoteModel = kaleoInstanceRemoteModel;
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

		Class<?> remoteModelClass = _kaleoInstanceRemoteModel.getClass();

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

		Object returnValue = method.invoke(_kaleoInstanceRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			KaleoInstanceLocalServiceUtil.addKaleoInstance(this);
		}
		else {
			KaleoInstanceLocalServiceUtil.updateKaleoInstance(this);
		}
	}

	@Override
	public KaleoInstance toEscapedModel() {
		return (KaleoInstance)ProxyUtil.newProxyInstance(KaleoInstance.class.getClassLoader(),
			new Class[] { KaleoInstance.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoInstanceClp clone = new KaleoInstanceClp();

		clone.setKaleoInstanceId(getKaleoInstanceId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoDefinitionName(getKaleoDefinitionName());
		clone.setKaleoDefinitionVersion(getKaleoDefinitionVersion());
		clone.setRootKaleoInstanceTokenId(getRootKaleoInstanceTokenId());
		clone.setClassName(getClassName());
		clone.setClassPK(getClassPK());
		clone.setCompleted(getCompleted());
		clone.setCompletionDate(getCompletionDate());
		clone.setWorkflowContext(getWorkflowContext());

		return clone;
	}

	@Override
	public int compareTo(KaleoInstance kaleoInstance) {
		int value = 0;

		if (getKaleoInstanceId() < kaleoInstance.getKaleoInstanceId()) {
			value = -1;
		}
		else if (getKaleoInstanceId() > kaleoInstance.getKaleoInstanceId()) {
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

		if (!(obj instanceof KaleoInstanceClp)) {
			return false;
		}

		KaleoInstanceClp kaleoInstance = (KaleoInstanceClp)obj;

		long primaryKey = kaleoInstance.getPrimaryKey();

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

		sb.append("{kaleoInstanceId=");
		sb.append(getKaleoInstanceId());
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
		sb.append(", kaleoDefinitionName=");
		sb.append(getKaleoDefinitionName());
		sb.append(", kaleoDefinitionVersion=");
		sb.append(getKaleoDefinitionVersion());
		sb.append(", rootKaleoInstanceTokenId=");
		sb.append(getRootKaleoInstanceTokenId());
		sb.append(", className=");
		sb.append(getClassName());
		sb.append(", classPK=");
		sb.append(getClassPK());
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
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoInstance");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoInstanceId</column-name><column-value><![CDATA[");
		sb.append(getKaleoInstanceId());
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
			"<column><column-name>kaleoDefinitionName</column-name><column-value><![CDATA[");
		sb.append(getKaleoDefinitionName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoDefinitionVersion</column-name><column-value><![CDATA[");
		sb.append(getKaleoDefinitionVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rootKaleoInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getRootKaleoInstanceTokenId());
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
		sb.append(
			"<column><column-name>workflowContext</column-name><column-value><![CDATA[");
		sb.append(getWorkflowContext());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoInstanceId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private String _kaleoDefinitionName;
	private int _kaleoDefinitionVersion;
	private long _rootKaleoInstanceTokenId;
	private String _className;
	private long _classPK;
	private boolean _completed;
	private Date _completionDate;
	private String _workflowContext;
	private BaseModel<?> _kaleoInstanceRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}