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

package com.liferay.akismet.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.akismet.service.AkismetDataLocalServiceUtil;
import com.liferay.akismet.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class AkismetDataClp extends BaseModelImpl<AkismetData>
	implements AkismetData {
	public AkismetDataClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return AkismetData.class;
	}

	@Override
	public String getModelClassName() {
		return AkismetData.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _akismetDataId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAkismetDataId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _akismetDataId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("akismetDataId", getAkismetDataId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("type", getType());
		attributes.put("permalink", getPermalink());
		attributes.put("referrer", getReferrer());
		attributes.put("userAgent", getUserAgent());
		attributes.put("userIP", getUserIP());
		attributes.put("userURL", getUserURL());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long akismetDataId = (Long)attributes.get("akismetDataId");

		if (akismetDataId != null) {
			setAkismetDataId(akismetDataId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String permalink = (String)attributes.get("permalink");

		if (permalink != null) {
			setPermalink(permalink);
		}

		String referrer = (String)attributes.get("referrer");

		if (referrer != null) {
			setReferrer(referrer);
		}

		String userAgent = (String)attributes.get("userAgent");

		if (userAgent != null) {
			setUserAgent(userAgent);
		}

		String userIP = (String)attributes.get("userIP");

		if (userIP != null) {
			setUserIP(userIP);
		}

		String userURL = (String)attributes.get("userURL");

		if (userURL != null) {
			setUserURL(userURL);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getAkismetDataId() {
		return _akismetDataId;
	}

	@Override
	public void setAkismetDataId(long akismetDataId) {
		_akismetDataId = akismetDataId;

		if (_akismetDataRemoteModel != null) {
			try {
				Class<?> clazz = _akismetDataRemoteModel.getClass();

				Method method = clazz.getMethod("setAkismetDataId", long.class);

				method.invoke(_akismetDataRemoteModel, akismetDataId);
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

		if (_akismetDataRemoteModel != null) {
			try {
				Class<?> clazz = _akismetDataRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_akismetDataRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;

		if (_akismetDataRemoteModel != null) {
			try {
				Class<?> clazz = _akismetDataRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_akismetDataRemoteModel, classNameId);
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

		if (_akismetDataRemoteModel != null) {
			try {
				Class<?> clazz = _akismetDataRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_akismetDataRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getType() {
		return _type;
	}

	@Override
	public void setType(String type) {
		_type = type;

		if (_akismetDataRemoteModel != null) {
			try {
				Class<?> clazz = _akismetDataRemoteModel.getClass();

				Method method = clazz.getMethod("setType", String.class);

				method.invoke(_akismetDataRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPermalink() {
		return _permalink;
	}

	@Override
	public void setPermalink(String permalink) {
		_permalink = permalink;

		if (_akismetDataRemoteModel != null) {
			try {
				Class<?> clazz = _akismetDataRemoteModel.getClass();

				Method method = clazz.getMethod("setPermalink", String.class);

				method.invoke(_akismetDataRemoteModel, permalink);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getReferrer() {
		return _referrer;
	}

	@Override
	public void setReferrer(String referrer) {
		_referrer = referrer;

		if (_akismetDataRemoteModel != null) {
			try {
				Class<?> clazz = _akismetDataRemoteModel.getClass();

				Method method = clazz.getMethod("setReferrer", String.class);

				method.invoke(_akismetDataRemoteModel, referrer);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserAgent() {
		return _userAgent;
	}

	@Override
	public void setUserAgent(String userAgent) {
		_userAgent = userAgent;

		if (_akismetDataRemoteModel != null) {
			try {
				Class<?> clazz = _akismetDataRemoteModel.getClass();

				Method method = clazz.getMethod("setUserAgent", String.class);

				method.invoke(_akismetDataRemoteModel, userAgent);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserIP() {
		return _userIP;
	}

	@Override
	public void setUserIP(String userIP) {
		_userIP = userIP;

		if (_akismetDataRemoteModel != null) {
			try {
				Class<?> clazz = _akismetDataRemoteModel.getClass();

				Method method = clazz.getMethod("setUserIP", String.class);

				method.invoke(_akismetDataRemoteModel, userIP);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserURL() {
		return _userURL;
	}

	@Override
	public void setUserURL(String userURL) {
		_userURL = userURL;

		if (_akismetDataRemoteModel != null) {
			try {
				Class<?> clazz = _akismetDataRemoteModel.getClass();

				Method method = clazz.getMethod("setUserURL", String.class);

				method.invoke(_akismetDataRemoteModel, userURL);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAkismetDataRemoteModel() {
		return _akismetDataRemoteModel;
	}

	public void setAkismetDataRemoteModel(BaseModel<?> akismetDataRemoteModel) {
		_akismetDataRemoteModel = akismetDataRemoteModel;
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

		Class<?> remoteModelClass = _akismetDataRemoteModel.getClass();

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

		Object returnValue = method.invoke(_akismetDataRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			AkismetDataLocalServiceUtil.addAkismetData(this);
		}
		else {
			AkismetDataLocalServiceUtil.updateAkismetData(this);
		}
	}

	@Override
	public AkismetData toEscapedModel() {
		return (AkismetData)ProxyUtil.newProxyInstance(AkismetData.class.getClassLoader(),
			new Class[] { AkismetData.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AkismetDataClp clone = new AkismetDataClp();

		clone.setAkismetDataId(getAkismetDataId());
		clone.setModifiedDate(getModifiedDate());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setType(getType());
		clone.setPermalink(getPermalink());
		clone.setReferrer(getReferrer());
		clone.setUserAgent(getUserAgent());
		clone.setUserIP(getUserIP());
		clone.setUserURL(getUserURL());

		return clone;
	}

	@Override
	public int compareTo(AkismetData akismetData) {
		long primaryKey = akismetData.getPrimaryKey();

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

		if (!(obj instanceof AkismetDataClp)) {
			return false;
		}

		AkismetDataClp akismetData = (AkismetDataClp)obj;

		long primaryKey = akismetData.getPrimaryKey();

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
		StringBundler sb = new StringBundler(21);

		sb.append("{akismetDataId=");
		sb.append(getAkismetDataId());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", permalink=");
		sb.append(getPermalink());
		sb.append(", referrer=");
		sb.append(getReferrer());
		sb.append(", userAgent=");
		sb.append(getUserAgent());
		sb.append(", userIP=");
		sb.append(getUserIP());
		sb.append(", userURL=");
		sb.append(getUserURL());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.akismet.model.AkismetData");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>akismetDataId</column-name><column-value><![CDATA[");
		sb.append(getAkismetDataId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>permalink</column-name><column-value><![CDATA[");
		sb.append(getPermalink());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>referrer</column-name><column-value><![CDATA[");
		sb.append(getReferrer());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userAgent</column-name><column-value><![CDATA[");
		sb.append(getUserAgent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userIP</column-name><column-value><![CDATA[");
		sb.append(getUserIP());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userURL</column-name><column-value><![CDATA[");
		sb.append(getUserURL());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _akismetDataId;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private String _type;
	private String _permalink;
	private String _referrer;
	private String _userAgent;
	private String _userIP;
	private String _userURL;
	private BaseModel<?> _akismetDataRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}