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

package com.liferay.opensocial.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.opensocial.service.ClpSerializer;
import com.liferay.opensocial.service.OAuthConsumerLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class OAuthConsumerClp extends BaseModelImpl<OAuthConsumer>
	implements OAuthConsumer {
	public OAuthConsumerClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthConsumer.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthConsumer.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _oAuthConsumerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOAuthConsumerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _oAuthConsumerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oAuthConsumerId", getOAuthConsumerId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("gadgetKey", getGadgetKey());
		attributes.put("serviceName", getServiceName());
		attributes.put("consumerKey", getConsumerKey());
		attributes.put("consumerSecret", getConsumerSecret());
		attributes.put("keyType", getKeyType());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long oAuthConsumerId = (Long)attributes.get("oAuthConsumerId");

		if (oAuthConsumerId != null) {
			setOAuthConsumerId(oAuthConsumerId);
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

		String gadgetKey = (String)attributes.get("gadgetKey");

		if (gadgetKey != null) {
			setGadgetKey(gadgetKey);
		}

		String serviceName = (String)attributes.get("serviceName");

		if (serviceName != null) {
			setServiceName(serviceName);
		}

		String consumerKey = (String)attributes.get("consumerKey");

		if (consumerKey != null) {
			setConsumerKey(consumerKey);
		}

		String consumerSecret = (String)attributes.get("consumerSecret");

		if (consumerSecret != null) {
			setConsumerSecret(consumerSecret);
		}

		String keyType = (String)attributes.get("keyType");

		if (keyType != null) {
			setKeyType(keyType);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getOAuthConsumerId() {
		return _oAuthConsumerId;
	}

	@Override
	public void setOAuthConsumerId(long oAuthConsumerId) {
		_oAuthConsumerId = oAuthConsumerId;

		if (_oAuthConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setOAuthConsumerId", long.class);

				method.invoke(_oAuthConsumerRemoteModel, oAuthConsumerId);
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

		if (_oAuthConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_oAuthConsumerRemoteModel, companyId);
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

		if (_oAuthConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_oAuthConsumerRemoteModel, createDate);
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

		if (_oAuthConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_oAuthConsumerRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getGadgetKey() {
		return _gadgetKey;
	}

	@Override
	public void setGadgetKey(String gadgetKey) {
		_gadgetKey = gadgetKey;

		if (_oAuthConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setGadgetKey", String.class);

				method.invoke(_oAuthConsumerRemoteModel, gadgetKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getServiceName() {
		return _serviceName;
	}

	@Override
	public void setServiceName(String serviceName) {
		_serviceName = serviceName;

		if (_oAuthConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setServiceName", String.class);

				method.invoke(_oAuthConsumerRemoteModel, serviceName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getConsumerKey() {
		return _consumerKey;
	}

	@Override
	public void setConsumerKey(String consumerKey) {
		_consumerKey = consumerKey;

		if (_oAuthConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setConsumerKey", String.class);

				method.invoke(_oAuthConsumerRemoteModel, consumerKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getConsumerSecret() {
		return _consumerSecret;
	}

	@Override
	public void setConsumerSecret(String consumerSecret) {
		_consumerSecret = consumerSecret;

		if (_oAuthConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setConsumerSecret",
						String.class);

				method.invoke(_oAuthConsumerRemoteModel, consumerSecret);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKeyType() {
		return _keyType;
	}

	@Override
	public void setKeyType(String keyType) {
		_keyType = keyType;

		if (_oAuthConsumerRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthConsumerRemoteModel.getClass();

				Method method = clazz.getMethod("setKeyType", String.class);

				method.invoke(_oAuthConsumerRemoteModel, keyType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setKeyName(java.lang.String keyName) {
		try {
			String methodName = "setKeyName";

			Class<?>[] parameterTypes = new Class<?>[] { java.lang.String.class };

			Object[] parameterValues = new Object[] { keyName };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getKeyName() {
		try {
			String methodName = "getKeyName";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getOAuthConsumerRemoteModel() {
		return _oAuthConsumerRemoteModel;
	}

	public void setOAuthConsumerRemoteModel(
		BaseModel<?> oAuthConsumerRemoteModel) {
		_oAuthConsumerRemoteModel = oAuthConsumerRemoteModel;
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

		Class<?> remoteModelClass = _oAuthConsumerRemoteModel.getClass();

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

		Object returnValue = method.invoke(_oAuthConsumerRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			OAuthConsumerLocalServiceUtil.addOAuthConsumer(this);
		}
		else {
			OAuthConsumerLocalServiceUtil.updateOAuthConsumer(this);
		}
	}

	@Override
	public OAuthConsumer toEscapedModel() {
		return (OAuthConsumer)ProxyUtil.newProxyInstance(OAuthConsumer.class.getClassLoader(),
			new Class[] { OAuthConsumer.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		OAuthConsumerClp clone = new OAuthConsumerClp();

		clone.setOAuthConsumerId(getOAuthConsumerId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setGadgetKey(getGadgetKey());
		clone.setServiceName(getServiceName());
		clone.setConsumerKey(getConsumerKey());
		clone.setConsumerSecret(getConsumerSecret());
		clone.setKeyType(getKeyType());

		return clone;
	}

	@Override
	public int compareTo(OAuthConsumer oAuthConsumer) {
		int value = 0;

		value = getServiceName().compareTo(oAuthConsumer.getServiceName());

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

		if (!(obj instanceof OAuthConsumerClp)) {
			return false;
		}

		OAuthConsumerClp oAuthConsumer = (OAuthConsumerClp)obj;

		long primaryKey = oAuthConsumer.getPrimaryKey();

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

		sb.append("{oAuthConsumerId=");
		sb.append(getOAuthConsumerId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", gadgetKey=");
		sb.append(getGadgetKey());
		sb.append(", serviceName=");
		sb.append(getServiceName());
		sb.append(", consumerKey=");
		sb.append(getConsumerKey());
		sb.append(", consumerSecret=");
		sb.append(getConsumerSecret());
		sb.append(", keyType=");
		sb.append(getKeyType());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.opensocial.model.OAuthConsumer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>oAuthConsumerId</column-name><column-value><![CDATA[");
		sb.append(getOAuthConsumerId());
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
			"<column><column-name>gadgetKey</column-name><column-value><![CDATA[");
		sb.append(getGadgetKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serviceName</column-name><column-value><![CDATA[");
		sb.append(getServiceName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>consumerKey</column-name><column-value><![CDATA[");
		sb.append(getConsumerKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>consumerSecret</column-name><column-value><![CDATA[");
		sb.append(getConsumerSecret());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>keyType</column-name><column-value><![CDATA[");
		sb.append(getKeyType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _oAuthConsumerId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _gadgetKey;
	private String _serviceName;
	private String _consumerKey;
	private String _consumerSecret;
	private String _keyType;
	private BaseModel<?> _oAuthConsumerRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}