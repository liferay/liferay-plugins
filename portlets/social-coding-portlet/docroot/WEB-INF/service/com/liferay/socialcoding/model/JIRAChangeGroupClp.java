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

package com.liferay.socialcoding.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.socialcoding.service.ClpSerializer;
import com.liferay.socialcoding.service.JIRAChangeGroupLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class JIRAChangeGroupClp extends BaseModelImpl<JIRAChangeGroup>
	implements JIRAChangeGroup {
	public JIRAChangeGroupClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return JIRAChangeGroup.class;
	}

	@Override
	public String getModelClassName() {
		return JIRAChangeGroup.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _jiraChangeGroupId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setJiraChangeGroupId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jiraChangeGroupId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraChangeGroupId", getJiraChangeGroupId());
		attributes.put("jiraUserId", getJiraUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("jiraIssueId", getJiraIssueId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraChangeGroupId = (Long)attributes.get("jiraChangeGroupId");

		if (jiraChangeGroupId != null) {
			setJiraChangeGroupId(jiraChangeGroupId);
		}

		String jiraUserId = (String)attributes.get("jiraUserId");

		if (jiraUserId != null) {
			setJiraUserId(jiraUserId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long jiraIssueId = (Long)attributes.get("jiraIssueId");

		if (jiraIssueId != null) {
			setJiraIssueId(jiraIssueId);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getJiraChangeGroupId() {
		return _jiraChangeGroupId;
	}

	@Override
	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeGroupId = jiraChangeGroupId;

		if (_jiraChangeGroupRemoteModel != null) {
			try {
				Class<?> clazz = _jiraChangeGroupRemoteModel.getClass();

				Method method = clazz.getMethod("setJiraChangeGroupId",
						long.class);

				method.invoke(_jiraChangeGroupRemoteModel, jiraChangeGroupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getJiraUserId() {
		return _jiraUserId;
	}

	@Override
	public void setJiraUserId(String jiraUserId) {
		_jiraUserId = jiraUserId;

		if (_jiraChangeGroupRemoteModel != null) {
			try {
				Class<?> clazz = _jiraChangeGroupRemoteModel.getClass();

				Method method = clazz.getMethod("setJiraUserId", String.class);

				method.invoke(_jiraChangeGroupRemoteModel, jiraUserId);
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

		if (_jiraChangeGroupRemoteModel != null) {
			try {
				Class<?> clazz = _jiraChangeGroupRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_jiraChangeGroupRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getJiraIssueId() {
		return _jiraIssueId;
	}

	@Override
	public void setJiraIssueId(long jiraIssueId) {
		_jiraIssueId = jiraIssueId;

		if (_jiraChangeGroupRemoteModel != null) {
			try {
				Class<?> clazz = _jiraChangeGroupRemoteModel.getClass();

				Method method = clazz.getMethod("setJiraIssueId", long.class);

				method.invoke(_jiraChangeGroupRemoteModel, jiraIssueId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getJIRAChangeGroupRemoteModel() {
		return _jiraChangeGroupRemoteModel;
	}

	public void setJIRAChangeGroupRemoteModel(
		BaseModel<?> jiraChangeGroupRemoteModel) {
		_jiraChangeGroupRemoteModel = jiraChangeGroupRemoteModel;
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

		Class<?> remoteModelClass = _jiraChangeGroupRemoteModel.getClass();

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

		Object returnValue = method.invoke(_jiraChangeGroupRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			JIRAChangeGroupLocalServiceUtil.addJIRAChangeGroup(this);
		}
		else {
			JIRAChangeGroupLocalServiceUtil.updateJIRAChangeGroup(this);
		}
	}

	@Override
	public JIRAChangeGroup toEscapedModel() {
		return (JIRAChangeGroup)ProxyUtil.newProxyInstance(JIRAChangeGroup.class.getClassLoader(),
			new Class[] { JIRAChangeGroup.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		JIRAChangeGroupClp clone = new JIRAChangeGroupClp();

		clone.setJiraChangeGroupId(getJiraChangeGroupId());
		clone.setJiraUserId(getJiraUserId());
		clone.setCreateDate(getCreateDate());
		clone.setJiraIssueId(getJiraIssueId());

		return clone;
	}

	@Override
	public int compareTo(JIRAChangeGroup jiraChangeGroup) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				jiraChangeGroup.getCreateDate());

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

		if (!(obj instanceof JIRAChangeGroupClp)) {
			return false;
		}

		JIRAChangeGroupClp jiraChangeGroup = (JIRAChangeGroupClp)obj;

		long primaryKey = jiraChangeGroup.getPrimaryKey();

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
		StringBundler sb = new StringBundler(9);

		sb.append("{jiraChangeGroupId=");
		sb.append(getJiraChangeGroupId());
		sb.append(", jiraUserId=");
		sb.append(getJiraUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", jiraIssueId=");
		sb.append(getJiraIssueId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.socialcoding.model.JIRAChangeGroup");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>jiraChangeGroupId</column-name><column-value><![CDATA[");
		sb.append(getJiraChangeGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jiraUserId</column-name><column-value><![CDATA[");
		sb.append(getJiraUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jiraIssueId</column-name><column-value><![CDATA[");
		sb.append(getJiraIssueId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _jiraChangeGroupId;
	private String _jiraUserId;
	private Date _createDate;
	private long _jiraIssueId;
	private BaseModel<?> _jiraChangeGroupRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}