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
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.wsrp.service.ClpSerializer;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class WSRPConsumerClp extends BaseModelImpl<WSRPConsumer>
	implements WSRPConsumer {
	public WSRPConsumerClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return WSRPConsumer.class;
	}

	@Override
	public String getModelClassName() {
		return WSRPConsumer.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _wsrpConsumerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWsrpConsumerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _wsrpConsumerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("wsrpConsumerId", getWsrpConsumerId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("url", getUrl());
		attributes.put("wsdl", getWsdl());
		attributes.put("registrationContextString",
			getRegistrationContextString());
		attributes.put("registrationPropertiesString",
			getRegistrationPropertiesString());
		attributes.put("forwardCookies", getForwardCookies());
		attributes.put("forwardHeaders", getForwardHeaders());
		attributes.put("markupCharacterSets", getMarkupCharacterSets());

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

		Long wsrpConsumerId = (Long)attributes.get("wsrpConsumerId");

		if (wsrpConsumerId != null) {
			setWsrpConsumerId(wsrpConsumerId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String wsdl = (String)attributes.get("wsdl");

		if (wsdl != null) {
			setWsdl(wsdl);
		}

		String registrationContextString = (String)attributes.get(
				"registrationContextString");

		if (registrationContextString != null) {
			setRegistrationContextString(registrationContextString);
		}

		String registrationPropertiesString = (String)attributes.get(
				"registrationPropertiesString");

		if (registrationPropertiesString != null) {
			setRegistrationPropertiesString(registrationPropertiesString);
		}

		String forwardCookies = (String)attributes.get("forwardCookies");

		if (forwardCookies != null) {
			setForwardCookies(forwardCookies);
		}

		String forwardHeaders = (String)attributes.get("forwardHeaders");

		if (forwardHeaders != null) {
			setForwardHeaders(forwardHeaders);
		}

		String markupCharacterSets = (String)attributes.get(
				"markupCharacterSets");

		if (markupCharacterSets != null) {
			setMarkupCharacterSets(markupCharacterSets);
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

		if (_wsrpConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_wsrpConsumerRemoteModel, uuid);
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

		if (_wsrpConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setWsrpConsumerId", long.class);

				method.invoke(_wsrpConsumerRemoteModel, wsrpConsumerId);
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

		if (_wsrpConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_wsrpConsumerRemoteModel, companyId);
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

		if (_wsrpConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_wsrpConsumerRemoteModel, createDate);
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

		if (_wsrpConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_wsrpConsumerRemoteModel, modifiedDate);
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

		if (_wsrpConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_wsrpConsumerRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUrl() {
		return _url;
	}

	@Override
	public void setUrl(String url) {
		_url = url;

		if (_wsrpConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setUrl", String.class);

				method.invoke(_wsrpConsumerRemoteModel, url);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getWsdl() {
		return _wsdl;
	}

	@Override
	public void setWsdl(String wsdl) {
		_wsdl = wsdl;

		if (_wsrpConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setWsdl", String.class);

				method.invoke(_wsrpConsumerRemoteModel, wsdl);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRegistrationContextString() {
		return _registrationContextString;
	}

	@Override
	public void setRegistrationContextString(String registrationContextString) {
		_registrationContextString = registrationContextString;

		if (_wsrpConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setRegistrationContextString",
						String.class);

				method.invoke(_wsrpConsumerRemoteModel,
					registrationContextString);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRegistrationPropertiesString() {
		return _registrationPropertiesString;
	}

	@Override
	public void setRegistrationPropertiesString(
		String registrationPropertiesString) {
		_registrationPropertiesString = registrationPropertiesString;

		if (_wsrpConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setRegistrationPropertiesString",
						String.class);

				method.invoke(_wsrpConsumerRemoteModel,
					registrationPropertiesString);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getForwardCookies() {
		return _forwardCookies;
	}

	@Override
	public void setForwardCookies(String forwardCookies) {
		_forwardCookies = forwardCookies;

		if (_wsrpConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setForwardCookies",
						String.class);

				method.invoke(_wsrpConsumerRemoteModel, forwardCookies);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getForwardHeaders() {
		return _forwardHeaders;
	}

	@Override
	public void setForwardHeaders(String forwardHeaders) {
		_forwardHeaders = forwardHeaders;

		if (_wsrpConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setForwardHeaders",
						String.class);

				method.invoke(_wsrpConsumerRemoteModel, forwardHeaders);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getMarkupCharacterSets() {
		return _markupCharacterSets;
	}

	@Override
	public void setMarkupCharacterSets(String markupCharacterSets) {
		_markupCharacterSets = markupCharacterSets;

		if (_wsrpConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _wsrpConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setMarkupCharacterSets",
						String.class);

				method.invoke(_wsrpConsumerRemoteModel, markupCharacterSets);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setRegistrationContext(
		oasis.names.tc.wsrp.v2.types.RegistrationContext registrationContext) {
		try {
			String methodName = "setRegistrationContext";

			Class<?>[] parameterTypes = new Class<?>[] {
					oasis.names.tc.wsrp.v2.types.RegistrationContext.class
				};

			Object[] parameterValues = new Object[] { registrationContext };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public com.liferay.portal.kernel.util.UnicodeProperties getRegistrationProperties() {
		try {
			String methodName = "getRegistrationProperties";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.kernel.util.UnicodeProperties returnObj = (com.liferay.portal.kernel.util.UnicodeProperties)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public void setRegistrationProperties(
		com.liferay.portal.kernel.util.UnicodeProperties registrationProperties) {
		try {
			String methodName = "setRegistrationProperties";

			Class<?>[] parameterTypes = new Class<?>[] {
					com.liferay.portal.kernel.util.UnicodeProperties.class
				};

			Object[] parameterValues = new Object[] { registrationProperties };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public oasis.names.tc.wsrp.v2.types.RegistrationContext getRegistrationContext() {
		try {
			String methodName = "getRegistrationContext";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			oasis.names.tc.wsrp.v2.types.RegistrationContext returnObj = (oasis.names.tc.wsrp.v2.types.RegistrationContext)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				WSRPConsumer.class.getName()));
	}

	public BaseModel<?> getWSRPConsumerRemoteModel() {
		return _wsrpConsumerRemoteModel;
	}

	public void setWSRPConsumerRemoteModel(BaseModel<?> wsrpConsumerRemoteModel) {
		_wsrpConsumerRemoteModel = wsrpConsumerRemoteModel;
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

		Class<?> remoteModelClass = _wsrpConsumerRemoteModel.getClass();

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

		Object returnValue = method.invoke(_wsrpConsumerRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			WSRPConsumerLocalServiceUtil.addWSRPConsumer(this);
		}
		else {
			WSRPConsumerLocalServiceUtil.updateWSRPConsumer(this);
		}
	}

	@Override
	public WSRPConsumer toEscapedModel() {
		return (WSRPConsumer)ProxyUtil.newProxyInstance(WSRPConsumer.class.getClassLoader(),
			new Class[] { WSRPConsumer.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WSRPConsumerClp clone = new WSRPConsumerClp();

		clone.setUuid(getUuid());
		clone.setWsrpConsumerId(getWsrpConsumerId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setUrl(getUrl());
		clone.setWsdl(getWsdl());
		clone.setRegistrationContextString(getRegistrationContextString());
		clone.setRegistrationPropertiesString(getRegistrationPropertiesString());
		clone.setForwardCookies(getForwardCookies());
		clone.setForwardHeaders(getForwardHeaders());
		clone.setMarkupCharacterSets(getMarkupCharacterSets());

		return clone;
	}

	@Override
	public int compareTo(WSRPConsumer wsrpConsumer) {
		int value = 0;

		value = getName().compareTo(wsrpConsumer.getName());

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

		if (!(obj instanceof WSRPConsumerClp)) {
			return false;
		}

		WSRPConsumerClp wsrpConsumer = (WSRPConsumerClp)obj;

		long primaryKey = wsrpConsumer.getPrimaryKey();

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
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", wsrpConsumerId=");
		sb.append(getWsrpConsumerId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", wsdl=");
		sb.append(getWsdl());
		sb.append(", registrationContextString=");
		sb.append(getRegistrationContextString());
		sb.append(", registrationPropertiesString=");
		sb.append(getRegistrationPropertiesString());
		sb.append(", forwardCookies=");
		sb.append(getForwardCookies());
		sb.append(", forwardHeaders=");
		sb.append(getForwardHeaders());
		sb.append(", markupCharacterSets=");
		sb.append(getMarkupCharacterSets());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.wsrp.model.WSRPConsumer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wsrpConsumerId</column-name><column-value><![CDATA[");
		sb.append(getWsrpConsumerId());
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
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wsdl</column-name><column-value><![CDATA[");
		sb.append(getWsdl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationContextString</column-name><column-value><![CDATA[");
		sb.append(getRegistrationContextString());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationPropertiesString</column-name><column-value><![CDATA[");
		sb.append(getRegistrationPropertiesString());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>forwardCookies</column-name><column-value><![CDATA[");
		sb.append(getForwardCookies());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>forwardHeaders</column-name><column-value><![CDATA[");
		sb.append(getForwardHeaders());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>markupCharacterSets</column-name><column-value><![CDATA[");
		sb.append(getMarkupCharacterSets());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _wsrpConsumerId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _url;
	private String _wsdl;
	private String _registrationContextString;
	private String _registrationPropertiesString;
	private String _forwardCookies;
	private String _forwardHeaders;
	private String _markupCharacterSets;
	private BaseModel<?> _wsrpConsumerRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}