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

package com.liferay.asset.entry.set.model;

import com.liferay.asset.entry.set.service.AssetEntrySetLikeLocalServiceUtil;
import com.liferay.asset.entry.set.service.ClpSerializer;
import com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
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
public class AssetEntrySetLikeClp extends BaseModelImpl<AssetEntrySetLike>
	implements AssetEntrySetLike {
	public AssetEntrySetLikeClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return AssetEntrySetLike.class;
	}

	@Override
	public String getModelClassName() {
		return AssetEntrySetLike.class.getName();
	}

	@Override
	public AssetEntrySetLikePK getPrimaryKey() {
		return new AssetEntrySetLikePK(_assetEntrySetId, _classNameId, _classPK);
	}

	@Override
	public void setPrimaryKey(AssetEntrySetLikePK primaryKey) {
		setAssetEntrySetId(primaryKey.assetEntrySetId);
		setClassNameId(primaryKey.classNameId);
		setClassPK(primaryKey.classPK);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new AssetEntrySetLikePK(_assetEntrySetId, _classNameId, _classPK);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((AssetEntrySetLikePK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetEntrySetId", getAssetEntrySetId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetEntrySetId = (Long)attributes.get("assetEntrySetId");

		if (assetEntrySetId != null) {
			setAssetEntrySetId(assetEntrySetId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}
	}

	@Override
	public long getAssetEntrySetId() {
		return _assetEntrySetId;
	}

	@Override
	public void setAssetEntrySetId(long assetEntrySetId) {
		_assetEntrySetId = assetEntrySetId;

		if (_assetEntrySetLikeRemoteModel != null) {
			try {
				Class<?> clazz = _assetEntrySetLikeRemoteModel.getClass();

				Method method = clazz.getMethod("setAssetEntrySetId", long.class);

				method.invoke(_assetEntrySetLikeRemoteModel, assetEntrySetId);
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

		if (_assetEntrySetLikeRemoteModel != null) {
			try {
				Class<?> clazz = _assetEntrySetLikeRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_assetEntrySetLikeRemoteModel, classNameId);
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

		if (_assetEntrySetLikeRemoteModel != null) {
			try {
				Class<?> clazz = _assetEntrySetLikeRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_assetEntrySetLikeRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAssetEntrySetLikeRemoteModel() {
		return _assetEntrySetLikeRemoteModel;
	}

	public void setAssetEntrySetLikeRemoteModel(
		BaseModel<?> assetEntrySetLikeRemoteModel) {
		_assetEntrySetLikeRemoteModel = assetEntrySetLikeRemoteModel;
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

		Class<?> remoteModelClass = _assetEntrySetLikeRemoteModel.getClass();

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

		Object returnValue = method.invoke(_assetEntrySetLikeRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			AssetEntrySetLikeLocalServiceUtil.addAssetEntrySetLike(this);
		}
		else {
			AssetEntrySetLikeLocalServiceUtil.updateAssetEntrySetLike(this);
		}
	}

	@Override
	public AssetEntrySetLike toEscapedModel() {
		return (AssetEntrySetLike)ProxyUtil.newProxyInstance(AssetEntrySetLike.class.getClassLoader(),
			new Class[] { AssetEntrySetLike.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AssetEntrySetLikeClp clone = new AssetEntrySetLikeClp();

		clone.setAssetEntrySetId(getAssetEntrySetId());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());

		return clone;
	}

	@Override
	public int compareTo(AssetEntrySetLike assetEntrySetLike) {
		AssetEntrySetLikePK primaryKey = assetEntrySetLike.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntrySetLikeClp)) {
			return false;
		}

		AssetEntrySetLikeClp assetEntrySetLike = (AssetEntrySetLikeClp)obj;

		AssetEntrySetLikePK primaryKey = assetEntrySetLike.getPrimaryKey();

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
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{assetEntrySetId=");
		sb.append(getAssetEntrySetId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.asset.entry.set.model.AssetEntrySetLike");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>assetEntrySetId</column-name><column-value><![CDATA[");
		sb.append(getAssetEntrySetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _assetEntrySetId;
	private long _classNameId;
	private long _classPK;
	private BaseModel<?> _assetEntrySetLikeRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.asset.entry.set.service.ClpSerializer.class;
}