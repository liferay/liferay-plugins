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

package com.liferay.share.model;

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

import com.liferay.share.service.ClpSerializer;
import com.liferay.share.service.EntitySocialFeedLocalServiceUtil;
import com.liferay.share.service.persistence.EntitySocialFeedPK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class EntitySocialFeedClp extends BaseModelImpl<EntitySocialFeed>
	implements EntitySocialFeed {
	public EntitySocialFeedClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return EntitySocialFeed.class;
	}

	@Override
	public String getModelClassName() {
		return EntitySocialFeed.class.getName();
	}

	@Override
	public EntitySocialFeedPK getPrimaryKey() {
		return new EntitySocialFeedPK(_classNameId, _classPK, _feedClassNameId,
			_feedClassPK);
	}

	@Override
	public void setPrimaryKey(EntitySocialFeedPK primaryKey) {
		setClassNameId(primaryKey.classNameId);
		setClassPK(primaryKey.classPK);
		setFeedClassNameId(primaryKey.feedClassNameId);
		setFeedClassPK(primaryKey.feedClassPK);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new EntitySocialFeedPK(_classNameId, _classPK, _feedClassNameId,
			_feedClassPK);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((EntitySocialFeedPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("feedClassNameId", getFeedClassNameId());
		attributes.put("feedClassPK", getFeedClassPK());

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

		Long feedClassNameId = (Long)attributes.get("feedClassNameId");

		if (feedClassNameId != null) {
			setFeedClassNameId(feedClassNameId);
		}

		Long feedClassPK = (Long)attributes.get("feedClassPK");

		if (feedClassPK != null) {
			setFeedClassPK(feedClassPK);
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

		if (_entitySocialFeedRemoteModel != null) {
			try {
				Class<?> clazz = _entitySocialFeedRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_entitySocialFeedRemoteModel, classNameId);
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

		if (_entitySocialFeedRemoteModel != null) {
			try {
				Class<?> clazz = _entitySocialFeedRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_entitySocialFeedRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getFeedClassNameId() {
		return _feedClassNameId;
	}

	@Override
	public void setFeedClassNameId(long feedClassNameId) {
		_feedClassNameId = feedClassNameId;

		if (_entitySocialFeedRemoteModel != null) {
			try {
				Class<?> clazz = _entitySocialFeedRemoteModel.getClass();

				Method method = clazz.getMethod("setFeedClassNameId", long.class);

				method.invoke(_entitySocialFeedRemoteModel, feedClassNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getFeedClassPK() {
		return _feedClassPK;
	}

	@Override
	public void setFeedClassPK(long feedClassPK) {
		_feedClassPK = feedClassPK;

		if (_entitySocialFeedRemoteModel != null) {
			try {
				Class<?> clazz = _entitySocialFeedRemoteModel.getClass();

				Method method = clazz.getMethod("setFeedClassPK", long.class);

				method.invoke(_entitySocialFeedRemoteModel, feedClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getEntitySocialFeedRemoteModel() {
		return _entitySocialFeedRemoteModel;
	}

	public void setEntitySocialFeedRemoteModel(
		BaseModel<?> entitySocialFeedRemoteModel) {
		_entitySocialFeedRemoteModel = entitySocialFeedRemoteModel;
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

		Class<?> remoteModelClass = _entitySocialFeedRemoteModel.getClass();

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

		Object returnValue = method.invoke(_entitySocialFeedRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			EntitySocialFeedLocalServiceUtil.addEntitySocialFeed(this);
		}
		else {
			EntitySocialFeedLocalServiceUtil.updateEntitySocialFeed(this);
		}
	}

	@Override
	public EntitySocialFeed toEscapedModel() {
		return (EntitySocialFeed)ProxyUtil.newProxyInstance(EntitySocialFeed.class.getClassLoader(),
			new Class[] { EntitySocialFeed.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		EntitySocialFeedClp clone = new EntitySocialFeedClp();

		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setFeedClassNameId(getFeedClassNameId());
		clone.setFeedClassPK(getFeedClassPK());

		return clone;
	}

	@Override
	public int compareTo(EntitySocialFeed entitySocialFeed) {
		EntitySocialFeedPK primaryKey = entitySocialFeed.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntitySocialFeedClp)) {
			return false;
		}

		EntitySocialFeedClp entitySocialFeed = (EntitySocialFeedClp)obj;

		EntitySocialFeedPK primaryKey = entitySocialFeed.getPrimaryKey();

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
		sb.append(", feedClassNameId=");
		sb.append(getFeedClassNameId());
		sb.append(", feedClassPK=");
		sb.append(getFeedClassPK());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.share.model.EntitySocialFeed");
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
			"<column><column-name>feedClassNameId</column-name><column-value><![CDATA[");
		sb.append(getFeedClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>feedClassPK</column-name><column-value><![CDATA[");
		sb.append(getFeedClassPK());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _classNameId;
	private long _classPK;
	private long _feedClassNameId;
	private long _feedClassPK;
	private BaseModel<?> _entitySocialFeedRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.share.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}