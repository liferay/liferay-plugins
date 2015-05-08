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

package com.liferay.testtransaction.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.testtransaction.service.BarLocalServiceUtil;
import com.liferay.testtransaction.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class BarClp extends BaseModelImpl<Bar> implements Bar {
	public BarClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Bar.class;
	}

	@Override
	public String getModelClassName() {
		return Bar.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _barId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBarId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _barId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("barId", getBarId());
		attributes.put("text", getText());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long barId = (Long)attributes.get("barId");

		if (barId != null) {
			setBarId(barId);
		}

		String text = (String)attributes.get("text");

		if (text != null) {
			setText(text);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getBarId() {
		return _barId;
	}

	@Override
	public void setBarId(long barId) {
		_barId = barId;

		if (_barRemoteModel != null) {
			try {
				Class<?> clazz = _barRemoteModel.getClass();

				Method method = clazz.getMethod("setBarId", long.class);

				method.invoke(_barRemoteModel, barId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getText() {
		return _text;
	}

	@Override
	public void setText(String text) {
		_text = text;

		if (_barRemoteModel != null) {
			try {
				Class<?> clazz = _barRemoteModel.getClass();

				Method method = clazz.getMethod("setText", String.class);

				method.invoke(_barRemoteModel, text);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getBarRemoteModel() {
		return _barRemoteModel;
	}

	public void setBarRemoteModel(BaseModel<?> barRemoteModel) {
		_barRemoteModel = barRemoteModel;
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

		Class<?> remoteModelClass = _barRemoteModel.getClass();

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

		Object returnValue = method.invoke(_barRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			BarLocalServiceUtil.addBar(this);
		}
		else {
			BarLocalServiceUtil.updateBar(this);
		}
	}

	@Override
	public Bar toEscapedModel() {
		return (Bar)ProxyUtil.newProxyInstance(Bar.class.getClassLoader(),
			new Class[] { Bar.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		BarClp clone = new BarClp();

		clone.setBarId(getBarId());
		clone.setText(getText());

		return clone;
	}

	@Override
	public int compareTo(Bar bar) {
		int value = 0;

		value = getText().compareTo(bar.getText());

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

		if (!(obj instanceof BarClp)) {
			return false;
		}

		BarClp bar = (BarClp)obj;

		long primaryKey = bar.getPrimaryKey();

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
		StringBundler sb = new StringBundler(5);

		sb.append("{barId=");
		sb.append(getBarId());
		sb.append(", text=");
		sb.append(getText());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(10);

		sb.append("<model><model-name>");
		sb.append("com.liferay.testtransaction.model.Bar");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>barId</column-name><column-value><![CDATA[");
		sb.append(getBarId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>text</column-name><column-value><![CDATA[");
		sb.append(getText());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _barId;
	private String _text;
	private BaseModel<?> _barRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}