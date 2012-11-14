/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.ams.model;

import com.liferay.ams.service.TypeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class TypeClp extends BaseModelImpl<Type> implements Type {
	public TypeClp() {
	}

	public Class<?> getModelClass() {
		return Type.class;
	}

	public String getModelClassName() {
		return Type.class.getName();
	}

	public long getPrimaryKey() {
		return _typeId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTypeId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_typeId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("typeId", getTypeId());
		attributes.put("groupId", getGroupId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	public long getTypeId() {
		return _typeId;
	}

	public void setTypeId(long typeId) {
		_typeId = typeId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public BaseModel<?> getTypeRemoteModel() {
		return _typeRemoteModel;
	}

	public void setTypeRemoteModel(BaseModel<?> typeRemoteModel) {
		_typeRemoteModel = typeRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TypeLocalServiceUtil.addType(this);
		}
		else {
			TypeLocalServiceUtil.updateType(this);
		}
	}

	@Override
	public Type toEscapedModel() {
		return (Type)ProxyUtil.newProxyInstance(Type.class.getClassLoader(),
			new Class[] { Type.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TypeClp clone = new TypeClp();

		clone.setTypeId(getTypeId());
		clone.setGroupId(getGroupId());
		clone.setName(getName());

		return clone;
	}

	public int compareTo(Type type) {
		int value = 0;

		value = getName().compareTo(type.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		TypeClp type = null;

		try {
			type = (TypeClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = type.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{typeId=");
		sb.append(getTypeId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", name=");
		sb.append(getName());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.ams.model.Type");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>typeId</column-name><column-value><![CDATA[");
		sb.append(getTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _typeId;
	private long _groupId;
	private String _name;
	private BaseModel<?> _typeRemoteModel;
}