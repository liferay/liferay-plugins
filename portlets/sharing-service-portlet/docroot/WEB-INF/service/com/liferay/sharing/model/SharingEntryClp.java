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

package com.liferay.sharing.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.sharing.service.ClpSerializer;
import com.liferay.sharing.service.SharingEntryLocalServiceUtil;
import com.liferay.sharing.service.persistence.SharingEntryPK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class SharingEntryClp extends BaseModelImpl<SharingEntry>
	implements SharingEntry {
	public SharingEntryClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SharingEntry.class;
	}

	@Override
	public String getModelClassName() {
		return SharingEntry.class.getName();
	}

	@Override
	public SharingEntryPK getPrimaryKey() {
		return new SharingEntryPK(_classNameId, _classPK, _sharingClassNameId,
			_sharingClassPK);
	}

	@Override
	public void setPrimaryKey(SharingEntryPK primaryKey) {
		setClassNameId(primaryKey.classNameId);
		setClassPK(primaryKey.classPK);
		setSharingClassNameId(primaryKey.sharingClassNameId);
		setSharingClassPK(primaryKey.sharingClassPK);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new SharingEntryPK(_classNameId, _classPK, _sharingClassNameId,
			_sharingClassPK);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((SharingEntryPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("sharingClassNameId", getSharingClassNameId());
		attributes.put("sharingClassPK", getSharingClassPK());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long sharingClassNameId = (Long)attributes.get("sharingClassNameId");

		if (sharingClassNameId != null) {
			setSharingClassNameId(sharingClassNameId);
		}

		Long sharingClassPK = (Long)attributes.get("sharingClassPK");

		if (sharingClassPK != null) {
			setSharingClassPK(sharingClassPK);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
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

		if (_sharingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _sharingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_sharingEntryRemoteModel, classNameId);
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

		if (_sharingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _sharingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_sharingEntryRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSharingClassNameId() {
		return _sharingClassNameId;
	}

	@Override
	public void setSharingClassNameId(long sharingClassNameId) {
		_sharingClassNameId = sharingClassNameId;

		if (_sharingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _sharingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSharingClassNameId",
						long.class);

				method.invoke(_sharingEntryRemoteModel, sharingClassNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSharingClassPK() {
		return _sharingClassPK;
	}

	@Override
	public void setSharingClassPK(long sharingClassPK) {
		_sharingClassPK = sharingClassPK;

		if (_sharingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _sharingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSharingClassPK", long.class);

				method.invoke(_sharingEntryRemoteModel, sharingClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSharingEntryRemoteModel() {
		return _sharingEntryRemoteModel;
	}

	public void setSharingEntryRemoteModel(BaseModel<?> sharingEntryRemoteModel) {
		_sharingEntryRemoteModel = sharingEntryRemoteModel;
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

		Class<?> remoteModelClass = _sharingEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_sharingEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			SharingEntryLocalServiceUtil.addSharingEntry(this);
		}
		else {
			SharingEntryLocalServiceUtil.updateSharingEntry(this);
		}
	}

	@Override
	public SharingEntry toEscapedModel() {
		return (SharingEntry)ProxyUtil.newProxyInstance(SharingEntry.class.getClassLoader(),
			new Class[] { SharingEntry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SharingEntryClp clone = new SharingEntryClp();

		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setSharingClassNameId(getSharingClassNameId());
		clone.setSharingClassPK(getSharingClassPK());

		return clone;
	}

	@Override
	public int compareTo(SharingEntry sharingEntry) {
		SharingEntryPK primaryKey = sharingEntry.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SharingEntryClp)) {
			return false;
		}

		SharingEntryClp sharingEntry = (SharingEntryClp)obj;

		SharingEntryPK primaryKey = sharingEntry.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
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
		return getPrimaryKey().hashCode();
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

		sb.append("{classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", sharingClassNameId=");
		sb.append(getSharingClassNameId());
		sb.append(", sharingClassPK=");
		sb.append(getSharingClassPK());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.sharing.model.SharingEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sharingClassNameId</column-name><column-value><![CDATA[");
		sb.append(getSharingClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sharingClassPK</column-name><column-value><![CDATA[");
		sb.append(getSharingClassPK());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _classNameId;
	private long _classPK;
	private long _sharingClassNameId;
	private long _sharingClassPK;
	private BaseModel<?> _sharingEntryRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.sharing.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}