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

package com.liferay.asset.sharing.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.sharing.service.AssetSharingEntryLocalServiceUtil;
import com.liferay.asset.sharing.service.ClpSerializer;
import com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK;

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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class AssetSharingEntryClp extends BaseModelImpl<AssetSharingEntry>
	implements AssetSharingEntry {
	public AssetSharingEntryClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return AssetSharingEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AssetSharingEntry.class.getName();
	}

	@Override
	public AssetSharingEntryPK getPrimaryKey() {
		return new AssetSharingEntryPK(_classNameId, _classPK,
			_sharedToClassNameId, _sharedToClassPK);
	}

	@Override
	public void setPrimaryKey(AssetSharingEntryPK primaryKey) {
		setClassNameId(primaryKey.classNameId);
		setClassPK(primaryKey.classPK);
		setSharedToClassNameId(primaryKey.sharedToClassNameId);
		setSharedToClassPK(primaryKey.sharedToClassPK);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new AssetSharingEntryPK(_classNameId, _classPK,
			_sharedToClassNameId, _sharedToClassPK);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((AssetSharingEntryPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("sharedToClassNameId", getSharedToClassNameId());
		attributes.put("sharedToClassPK", getSharedToClassPK());

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

		Long sharedToClassNameId = (Long)attributes.get("sharedToClassNameId");

		if (sharedToClassNameId != null) {
			setSharedToClassNameId(sharedToClassNameId);
		}

		Long sharedToClassPK = (Long)attributes.get("sharedToClassPK");

		if (sharedToClassPK != null) {
			setSharedToClassPK(sharedToClassPK);
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

		if (_assetSharingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _assetSharingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_assetSharingEntryRemoteModel, classNameId);
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

		if (_assetSharingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _assetSharingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_assetSharingEntryRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSharedToClassNameId() {
		return _sharedToClassNameId;
	}

	@Override
	public void setSharedToClassNameId(long sharedToClassNameId) {
		_sharedToClassNameId = sharedToClassNameId;

		if (_assetSharingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _assetSharingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSharedToClassNameId",
						long.class);

				method.invoke(_assetSharingEntryRemoteModel, sharedToClassNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSharedToClassPK() {
		return _sharedToClassPK;
	}

	@Override
	public void setSharedToClassPK(long sharedToClassPK) {
		_sharedToClassPK = sharedToClassPK;

		if (_assetSharingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _assetSharingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSharedToClassPK", long.class);

				method.invoke(_assetSharingEntryRemoteModel, sharedToClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAssetSharingEntryRemoteModel() {
		return _assetSharingEntryRemoteModel;
	}

	public void setAssetSharingEntryRemoteModel(
		BaseModel<?> assetSharingEntryRemoteModel) {
		_assetSharingEntryRemoteModel = assetSharingEntryRemoteModel;
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

		Class<?> remoteModelClass = _assetSharingEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetSharingEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			AssetSharingEntryLocalServiceUtil.addAssetSharingEntry(this);
		}
		else {
			AssetSharingEntryLocalServiceUtil.updateAssetSharingEntry(this);
		}
	}

	@Override
	public AssetSharingEntry toEscapedModel() {
		return (AssetSharingEntry)ProxyUtil.newProxyInstance(AssetSharingEntry.class.getClassLoader(),
			new Class[] { AssetSharingEntry.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AssetSharingEntryClp clone = new AssetSharingEntryClp();

		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setSharedToClassNameId(getSharedToClassNameId());
		clone.setSharedToClassPK(getSharedToClassPK());

		return clone;
	}

	@Override
	public int compareTo(AssetSharingEntry assetSharingEntry) {
		AssetSharingEntryPK primaryKey = assetSharingEntry.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetSharingEntryClp)) {
			return false;
		}

		AssetSharingEntryClp assetSharingEntry = (AssetSharingEntryClp)obj;

		AssetSharingEntryPK primaryKey = assetSharingEntry.getPrimaryKey();

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
		sb.append(", sharedToClassNameId=");
		sb.append(getSharedToClassNameId());
		sb.append(", sharedToClassPK=");
		sb.append(getSharedToClassPK());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.asset.sharing.model.AssetSharingEntry");
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
			"<column><column-name>sharedToClassNameId</column-name><column-value><![CDATA[");
		sb.append(getSharedToClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sharedToClassPK</column-name><column-value><![CDATA[");
		sb.append(getSharedToClassPK());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _classNameId;
	private long _classPK;
	private long _sharedToClassNameId;
	private long _sharedToClassPK;
	private BaseModel<?> _assetSharingEntryRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.asset.sharing.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}