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
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.socialcoding.service.ClpSerializer;
import com.liferay.socialcoding.service.JIRAChangeItemLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class JIRAChangeItemClp extends BaseModelImpl<JIRAChangeItem>
	implements JIRAChangeItem {
	public JIRAChangeItemClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return JIRAChangeItem.class;
	}

	@Override
	public String getModelClassName() {
		return JIRAChangeItem.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _jiraChangeItemId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setJiraChangeItemId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jiraChangeItemId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraChangeItemId", getJiraChangeItemId());
		attributes.put("jiraChangeGroupId", getJiraChangeGroupId());
		attributes.put("field", getField());
		attributes.put("oldValue", getOldValue());
		attributes.put("oldString", getOldString());
		attributes.put("newValue", getNewValue());
		attributes.put("newString", getNewString());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraChangeItemId = (Long)attributes.get("jiraChangeItemId");

		if (jiraChangeItemId != null) {
			setJiraChangeItemId(jiraChangeItemId);
		}

		Long jiraChangeGroupId = (Long)attributes.get("jiraChangeGroupId");

		if (jiraChangeGroupId != null) {
			setJiraChangeGroupId(jiraChangeGroupId);
		}

		String field = (String)attributes.get("field");

		if (field != null) {
			setField(field);
		}

		String oldValue = (String)attributes.get("oldValue");

		if (oldValue != null) {
			setOldValue(oldValue);
		}

		String oldString = (String)attributes.get("oldString");

		if (oldString != null) {
			setOldString(oldString);
		}

		String newValue = (String)attributes.get("newValue");

		if (newValue != null) {
			setNewValue(newValue);
		}

		String newString = (String)attributes.get("newString");

		if (newString != null) {
			setNewString(newString);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getJiraChangeItemId() {
		return _jiraChangeItemId;
	}

	@Override
	public void setJiraChangeItemId(long jiraChangeItemId) {
		_jiraChangeItemId = jiraChangeItemId;

		if (_jiraChangeItemRemoteModel != null) {
			try {
				Class<?> clazz = _jiraChangeItemRemoteModel.getClass();

				Method method = clazz.getMethod("setJiraChangeItemId",
						long.class);

				method.invoke(_jiraChangeItemRemoteModel, jiraChangeItemId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getJiraChangeGroupId() {
		return _jiraChangeGroupId;
	}

	@Override
	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeGroupId = jiraChangeGroupId;

		if (_jiraChangeItemRemoteModel != null) {
			try {
				Class<?> clazz = _jiraChangeItemRemoteModel.getClass();

				Method method = clazz.getMethod("setJiraChangeGroupId",
						long.class);

				method.invoke(_jiraChangeItemRemoteModel, jiraChangeGroupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getField() {
		return _field;
	}

	@Override
	public void setField(String field) {
		_field = field;

		if (_jiraChangeItemRemoteModel != null) {
			try {
				Class<?> clazz = _jiraChangeItemRemoteModel.getClass();

				Method method = clazz.getMethod("setField", String.class);

				method.invoke(_jiraChangeItemRemoteModel, field);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getOldValue() {
		return _oldValue;
	}

	@Override
	public void setOldValue(String oldValue) {
		_oldValue = oldValue;

		if (_jiraChangeItemRemoteModel != null) {
			try {
				Class<?> clazz = _jiraChangeItemRemoteModel.getClass();

				Method method = clazz.getMethod("setOldValue", String.class);

				method.invoke(_jiraChangeItemRemoteModel, oldValue);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getOldString() {
		return _oldString;
	}

	@Override
	public void setOldString(String oldString) {
		_oldString = oldString;

		if (_jiraChangeItemRemoteModel != null) {
			try {
				Class<?> clazz = _jiraChangeItemRemoteModel.getClass();

				Method method = clazz.getMethod("setOldString", String.class);

				method.invoke(_jiraChangeItemRemoteModel, oldString);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getNewValue() {
		return _newValue;
	}

	@Override
	public void setNewValue(String newValue) {
		_newValue = newValue;

		if (_jiraChangeItemRemoteModel != null) {
			try {
				Class<?> clazz = _jiraChangeItemRemoteModel.getClass();

				Method method = clazz.getMethod("setNewValue", String.class);

				method.invoke(_jiraChangeItemRemoteModel, newValue);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getNewString() {
		return _newString;
	}

	@Override
	public void setNewString(String newString) {
		_newString = newString;

		if (_jiraChangeItemRemoteModel != null) {
			try {
				Class<?> clazz = _jiraChangeItemRemoteModel.getClass();

				Method method = clazz.getMethod("setNewString", String.class);

				method.invoke(_jiraChangeItemRemoteModel, newString);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getJIRAChangeItemRemoteModel() {
		return _jiraChangeItemRemoteModel;
	}

	public void setJIRAChangeItemRemoteModel(
		BaseModel<?> jiraChangeItemRemoteModel) {
		_jiraChangeItemRemoteModel = jiraChangeItemRemoteModel;
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

		Class<?> remoteModelClass = _jiraChangeItemRemoteModel.getClass();

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

		Object returnValue = method.invoke(_jiraChangeItemRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			JIRAChangeItemLocalServiceUtil.addJIRAChangeItem(this);
		}
		else {
			JIRAChangeItemLocalServiceUtil.updateJIRAChangeItem(this);
		}
	}

	@Override
	public JIRAChangeItem toEscapedModel() {
		return (JIRAChangeItem)ProxyUtil.newProxyInstance(JIRAChangeItem.class.getClassLoader(),
			new Class[] { JIRAChangeItem.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		JIRAChangeItemClp clone = new JIRAChangeItemClp();

		clone.setJiraChangeItemId(getJiraChangeItemId());
		clone.setJiraChangeGroupId(getJiraChangeGroupId());
		clone.setField(getField());
		clone.setOldValue(getOldValue());
		clone.setOldString(getOldString());
		clone.setNewValue(getNewValue());
		clone.setNewString(getNewString());

		return clone;
	}

	@Override
	public int compareTo(JIRAChangeItem jiraChangeItem) {
		long primaryKey = jiraChangeItem.getPrimaryKey();

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

		if (!(obj instanceof JIRAChangeItemClp)) {
			return false;
		}

		JIRAChangeItemClp jiraChangeItem = (JIRAChangeItemClp)obj;

		long primaryKey = jiraChangeItem.getPrimaryKey();

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
		StringBundler sb = new StringBundler(15);

		sb.append("{jiraChangeItemId=");
		sb.append(getJiraChangeItemId());
		sb.append(", jiraChangeGroupId=");
		sb.append(getJiraChangeGroupId());
		sb.append(", field=");
		sb.append(getField());
		sb.append(", oldValue=");
		sb.append(getOldValue());
		sb.append(", oldString=");
		sb.append(getOldString());
		sb.append(", newValue=");
		sb.append(getNewValue());
		sb.append(", newString=");
		sb.append(getNewString());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.socialcoding.model.JIRAChangeItem");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>jiraChangeItemId</column-name><column-value><![CDATA[");
		sb.append(getJiraChangeItemId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jiraChangeGroupId</column-name><column-value><![CDATA[");
		sb.append(getJiraChangeGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>field</column-name><column-value><![CDATA[");
		sb.append(getField());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>oldValue</column-name><column-value><![CDATA[");
		sb.append(getOldValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>oldString</column-name><column-value><![CDATA[");
		sb.append(getOldString());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>newValue</column-name><column-value><![CDATA[");
		sb.append(getNewValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>newString</column-name><column-value><![CDATA[");
		sb.append(getNewString());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _jiraChangeItemId;
	private long _jiraChangeGroupId;
	private String _field;
	private String _oldValue;
	private String _oldString;
	private String _newValue;
	private String _newString;
	private BaseModel<?> _jiraChangeItemRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.socialcoding.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}