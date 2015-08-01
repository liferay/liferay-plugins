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

package com.liferay.wsrp.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.exportimport.lar.StagedModelType;

import com.liferay.wsrp.service.ClpSerializer;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class WSRPConsumerPortletClp extends BaseModelImpl<WSRPConsumerPortlet>
	implements WSRPConsumerPortlet {
	public WSRPConsumerPortletClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return WSRPConsumerPortlet.class;
	}

	@Override
	public String getModelClassName() {
		return WSRPConsumerPortlet.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _wsrpConsumerPortletId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWsrpConsumerPortletId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _wsrpConsumerPortletId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("wsrpConsumerPortletId", getWsrpConsumerPortletId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("wsrpConsumerId", getWsrpConsumerId());
		attributes.put("name", getName());
		attributes.put("portletHandle", getPortletHandle());
		attributes.put("lastPublishDate", getLastPublishDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long wsrpConsumerPortletId = (Long)attributes.get(
				"wsrpConsumerPortletId");

		if (wsrpConsumerPortletId != null) {
			setWsrpConsumerPortletId(wsrpConsumerPortletId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long wsrpConsumerId = (Long)attributes.get("wsrpConsumerId");

		if (wsrpConsumerId != null) {
			setWsrpConsumerId(wsrpConsumerId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String portletHandle = (String)attributes.get("portletHandle");

		if (portletHandle != null) {
			setPortletHandle(portletHandle);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_wsrpConsumerPortletRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerPortletRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_wsrpConsumerPortletRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getWsrpConsumerPortletId() {
		return _wsrpConsumerPortletId;
	}

	@Override
	public void setWsrpConsumerPortletId(long wsrpConsumerPortletId) {
		_wsrpConsumerPortletId = wsrpConsumerPortletId;

		if (_wsrpConsumerPortletRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerPortletRemoteModel.getClass();

				Method method = clazz.getMethod("setWsrpConsumerPortletId",
						long.class);

				method.invoke(_wsrpConsumerPortletRemoteModel,
					wsrpConsumerPortletId);
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

		if (_wsrpConsumerPortletRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerPortletRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_wsrpConsumerPortletRemoteModel, companyId);
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

		if (_wsrpConsumerPortletRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerPortletRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_wsrpConsumerPortletRemoteModel, createDate);
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

		if (_wsrpConsumerPortletRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerPortletRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_wsrpConsumerPortletRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getWsrpConsumerId() {
		return _wsrpConsumerId;
	}

	@Override
	public void setWsrpConsumerId(long wsrpConsumerId) {
		_wsrpConsumerId = wsrpConsumerId;

		if (_wsrpConsumerPortletRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerPortletRemoteModel.getClass();

				Method method = clazz.getMethod("setWsrpConsumerId", long.class);

				method.invoke(_wsrpConsumerPortletRemoteModel, wsrpConsumerId);
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

		if (_wsrpConsumerPortletRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerPortletRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_wsrpConsumerPortletRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPortletHandle() {
		return _portletHandle;
	}

	@Override
	public void setPortletHandle(String portletHandle) {
		_portletHandle = portletHandle;

		if (_wsrpConsumerPortletRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerPortletRemoteModel.getClass();

				Method method = clazz.getMethod("setPortletHandle", String.class);

				method.invoke(_wsrpConsumerPortletRemoteModel, portletHandle);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;

		if (_wsrpConsumerPortletRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerPortletRemoteModel.getClass();

				Method method = clazz.getMethod("setLastPublishDate", Date.class);

				method.invoke(_wsrpConsumerPortletRemoteModel, lastPublishDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				WSRPConsumerPortlet.class.getName()));
	}

	public BaseModel<?> getWSRPConsumerPortletRemoteModel() {
		return _wsrpConsumerPortletRemoteModel;
	}

	public void setWSRPConsumerPortletRemoteModel(
		BaseModel<?> wsrpConsumerPortletRemoteModel) {
		_wsrpConsumerPortletRemoteModel = wsrpConsumerPortletRemoteModel;
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

		Class<?> remoteModelClass = _wsrpConsumerPortletRemoteModel.getClass();

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

		Object returnValue = method.invoke(_wsrpConsumerPortletRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			WSRPConsumerPortletLocalServiceUtil.addWSRPConsumerPortlet(this);
		}
		else {
			WSRPConsumerPortletLocalServiceUtil.updateWSRPConsumerPortlet(this);
		}
	}

	@Override
	public WSRPConsumerPortlet toEscapedModel() {
		return (WSRPConsumerPortlet)ProxyUtil.newProxyInstance(WSRPConsumerPortlet.class.getClassLoader(),
			new Class[] { WSRPConsumerPortlet.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WSRPConsumerPortletClp clone = new WSRPConsumerPortletClp();

		clone.setUuid(getUuid());
		clone.setWsrpConsumerPortletId(getWsrpConsumerPortletId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setWsrpConsumerId(getWsrpConsumerId());
		clone.setName(getName());
		clone.setPortletHandle(getPortletHandle());
		clone.setLastPublishDate(getLastPublishDate());

		return clone;
	}

	@Override
	public int compareTo(WSRPConsumerPortlet wsrpConsumerPortlet) {
		int value = 0;

		value = getName().compareTo(wsrpConsumerPortlet.getName());

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

		if (!(obj instanceof WSRPConsumerPortletClp)) {
			return false;
		}

		WSRPConsumerPortletClp wsrpConsumerPortlet = (WSRPConsumerPortletClp)obj;

		long primaryKey = wsrpConsumerPortlet.getPrimaryKey();

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
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", wsrpConsumerPortletId=");
		sb.append(getWsrpConsumerPortletId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", wsrpConsumerId=");
		sb.append(getWsrpConsumerId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", portletHandle=");
		sb.append(getPortletHandle());
		sb.append(", lastPublishDate=");
		sb.append(getLastPublishDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.wsrp.model.WSRPConsumerPortlet");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wsrpConsumerPortletId</column-name><column-value><![CDATA[");
		sb.append(getWsrpConsumerPortletId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
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
			"<column><column-name>wsrpConsumerId</column-name><column-value><![CDATA[");
		sb.append(getWsrpConsumerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portletHandle</column-name><column-value><![CDATA[");
		sb.append(getPortletHandle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastPublishDate</column-name><column-value><![CDATA[");
		sb.append(getLastPublishDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _wsrpConsumerPortletId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _wsrpConsumerId;
	private String _name;
	private String _portletHandle;
	private Date _lastPublishDate;
	private BaseModel<?> _wsrpConsumerPortletRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.wsrp.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}