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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.socialcoding.service.ClpSerializer;
import com.liferay.socialcoding.service.JIRAProjectLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class JIRAProjectClp extends BaseModelImpl<JIRAProject>
	implements JIRAProject {
	public JIRAProjectClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return JIRAProject.class;
	}

	@Override
	public String getModelClassName() {
		return JIRAProject.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _jiraProjectId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setJiraProjectId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jiraProjectId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraProjectId", getJiraProjectId());
		attributes.put("key", getKey());
		attributes.put("name", getName());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraProjectId = (Long)attributes.get("jiraProjectId");

		if (jiraProjectId != null) {
			setJiraProjectId(jiraProjectId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getJiraProjectId() {
		return _jiraProjectId;
	}

	@Override
	public void setJiraProjectId(long jiraProjectId) {
		_jiraProjectId = jiraProjectId;

		if (_jiraProjectRemoteModel != null) {
			try {
				Class<?> clazz = _jiraProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setJiraProjectId", long.class);

				method.invoke(_jiraProjectRemoteModel, jiraProjectId);
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

		if (_jiraProjectRemoteModel != null) {
			try {
				Class<?> clazz = _jiraProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setKey", String.class);

				method.invoke(_jiraProjectRemoteModel, key);
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

		if (_jiraProjectRemoteModel != null) {
			try {
				Class<?> clazz = _jiraProjectRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_jiraProjectRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getJIRAProjectRemoteModel() {
		return _jiraProjectRemoteModel;
	}

	public void setJIRAProjectRemoteModel(BaseModel<?> jiraProjectRemoteModel) {
		_jiraProjectRemoteModel = jiraProjectRemoteModel;
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

		Class<?> remoteModelClass = _jiraProjectRemoteModel.getClass();

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

		Object returnValue = method.invoke(_jiraProjectRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			JIRAProjectLocalServiceUtil.addJIRAProject(this);
		}
		else {
			JIRAProjectLocalServiceUtil.updateJIRAProject(this);
		}
	}

	@Override
	public JIRAProject toEscapedModel() {
		return (JIRAProject)ProxyUtil.newProxyInstance(JIRAProject.class.getClassLoader(),
			new Class[] { JIRAProject.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		JIRAProjectClp clone = new JIRAProjectClp();

		clone.setJiraProjectId(getJiraProjectId());
		clone.setKey(getKey());
		clone.setName(getName());

		return clone;
	}

	@Override
	public int compareTo(JIRAProject jiraProject) {
		long primaryKey = jiraProject.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAProjectClp)) {
			return false;
		}

		JIRAProjectClp jiraProject = (JIRAProjectClp)obj;

		long primaryKey = jiraProject.getPrimaryKey();

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
		StringBundler sb = new StringBundler(7);

		sb.append("{jiraProjectId=");
		sb.append(getJiraProjectId());
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", name=");
		sb.append(getName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.socialcoding.model.JIRAProject");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>jiraProjectId</column-name><column-value><![CDATA[");
		sb.append(getJiraProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _jiraProjectId;
	private String _key;
	private String _name;
	private BaseModel<?> _jiraProjectRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.socialcoding.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}