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

package com.liferay.testblob.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.testblob.service.ClpSerializer;
import com.liferay.testblob.service.TestBlobEntryLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.sql.Blob;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class TestBlobEntryClp extends BaseModelImpl<TestBlobEntry>
	implements TestBlobEntry {
	public TestBlobEntryClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return TestBlobEntry.class;
	}

	@Override
	public String getModelClassName() {
		return TestBlobEntry.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _testBlobEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTestBlobEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testBlobEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("testBlobEntryId", getTestBlobEntryId());
		attributes.put("blobField", getBlobField());

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

		Long testBlobEntryId = (Long)attributes.get("testBlobEntryId");

		if (testBlobEntryId != null) {
			setTestBlobEntryId(testBlobEntryId);
		}

		Blob blobField = (Blob)attributes.get("blobField");

		if (blobField != null) {
			setBlobField(blobField);
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

		if (_testBlobEntryRemoteModel != null) {
			try {
				Class<?> clazz = _testBlobEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_testBlobEntryRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTestBlobEntryId() {
		return _testBlobEntryId;
	}

	@Override
	public void setTestBlobEntryId(long testBlobEntryId) {
		_testBlobEntryId = testBlobEntryId;

		if (_testBlobEntryRemoteModel != null) {
			try {
				Class<?> clazz = _testBlobEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setTestBlobEntryId", long.class);

				method.invoke(_testBlobEntryRemoteModel, testBlobEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Blob getBlobField() {
		return _blobField;
	}

	@Override
	public void setBlobField(Blob blobField) {
		_blobField = blobField;

		if (_testBlobEntryRemoteModel != null) {
			try {
				Class<?> clazz = _testBlobEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setBlobField", Blob.class);

				method.invoke(_testBlobEntryRemoteModel, blobField);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getTestBlobEntryRemoteModel() {
		return _testBlobEntryRemoteModel;
	}

	public void setTestBlobEntryRemoteModel(
		BaseModel<?> testBlobEntryRemoteModel) {
		_testBlobEntryRemoteModel = testBlobEntryRemoteModel;
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

		Class<?> remoteModelClass = _testBlobEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_testBlobEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			TestBlobEntryLocalServiceUtil.addTestBlobEntry(this);
		}
		else {
			TestBlobEntryLocalServiceUtil.updateTestBlobEntry(this);
		}
	}

	@Override
	public TestBlobEntry toEscapedModel() {
		return (TestBlobEntry)ProxyUtil.newProxyInstance(TestBlobEntry.class.getClassLoader(),
			new Class[] { TestBlobEntry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TestBlobEntryClp clone = new TestBlobEntryClp();

		clone.setUuid(getUuid());
		clone.setTestBlobEntryId(getTestBlobEntryId());
		clone.setBlobField(getBlobField());

		return clone;
	}

	@Override
	public int compareTo(TestBlobEntry testBlobEntry) {
		long primaryKey = testBlobEntry.getPrimaryKey();

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

		if (!(obj instanceof TestBlobEntryClp)) {
			return false;
		}

		TestBlobEntryClp testBlobEntry = (TestBlobEntryClp)obj;

		long primaryKey = testBlobEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(7);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", testBlobEntryId=");
		sb.append(getTestBlobEntryId());
		sb.append(", blobField=");
		sb.append(getBlobField());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.testblob.model.TestBlobEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>testBlobEntryId</column-name><column-value><![CDATA[");
		sb.append(getTestBlobEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>blobField</column-name><column-value><![CDATA[");
		sb.append(getBlobField());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _testBlobEntryId;
	private Blob _blobField;
	private BaseModel<?> _testBlobEntryRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.testblob.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}