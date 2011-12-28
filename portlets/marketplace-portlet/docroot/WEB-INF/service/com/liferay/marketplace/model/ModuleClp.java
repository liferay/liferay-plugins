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

package com.liferay.marketplace.model;

import com.liferay.marketplace.service.ModuleLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * @author Ryan Park
 */
public class ModuleClp extends BaseModelImpl<Module> implements Module {
	public ModuleClp() {
	}

	public Class<?> getModelClass() {
		return Module.class;
	}

	public String getModelClassName() {
		return Module.class.getName();
	}

	public long getPrimaryKey() {
		return _moduleId;
	}

	public void setPrimaryKey(long primaryKey) {
		setModuleId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_moduleId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getModuleId() {
		return _moduleId;
	}

	public void setModuleId(long moduleId) {
		_moduleId = moduleId;
	}

	public long getAppId() {
		return _appId;
	}

	public void setAppId(long appId) {
		_appId = appId;
	}

	public String getContextName() {
		return _contextName;
	}

	public void setContextName(String contextName) {
		_contextName = contextName;
	}

	public void persist() throws SystemException {
		ModuleLocalServiceUtil.updateModule(this);
	}

	@Override
	public Module toEscapedModel() {
		return (Module)Proxy.newProxyInstance(Module.class.getClassLoader(),
			new Class[] { Module.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ModuleClp clone = new ModuleClp();

		clone.setUuid(getUuid());
		clone.setModuleId(getModuleId());
		clone.setAppId(getAppId());
		clone.setContextName(getContextName());

		return clone;
	}

	public int compareTo(Module module) {
		long primaryKey = module.getPrimaryKey();

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
		if (obj == null) {
			return false;
		}

		ModuleClp module = null;

		try {
			module = (ModuleClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = module.getPrimaryKey();

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
		StringBundler sb = new StringBundler(9);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", moduleId=");
		sb.append(getModuleId());
		sb.append(", appId=");
		sb.append(getAppId());
		sb.append(", contextName=");
		sb.append(getContextName());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.marketplace.model.Module");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>moduleId</column-name><column-value><![CDATA[");
		sb.append(getModuleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>appId</column-name><column-value><![CDATA[");
		sb.append(getAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contextName</column-name><column-value><![CDATA[");
		sb.append(getContextName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _moduleId;
	private long _appId;
	private String _contextName;
}