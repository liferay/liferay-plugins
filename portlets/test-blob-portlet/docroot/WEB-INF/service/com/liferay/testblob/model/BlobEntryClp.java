/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.testblob.service.BlobEntryLocalServiceUtil;
import com.liferay.testblob.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.sql.Blob;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class BlobEntryClp extends BaseModelImpl<BlobEntry> implements BlobEntry {
	public BlobEntryClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return BlobEntry.class;
	}

	@Override
	public String getModelClassName() {
		return BlobEntry.class.getName();
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
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_blobEntryRemoteModel != null) {
			try {
				Class<?> clazz = _blobEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_blobEntryRemoteModel, uuid);
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

		if (_blobEntryRemoteModel != null) {
			try {
				Class<?> clazz = _blobEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setTestBlobEntryId", long.class);

				method.invoke(_blobEntryRemoteModel, testBlobEntryId);
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

		if (_blobEntryRemoteModel != null) {
			try {
				Class<?> clazz = _blobEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setBlobField", Blob.class);

				method.invoke(_blobEntryRemoteModel, blobField);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getBlobEntryRemoteModel() {
		return _blobEntryRemoteModel;
	}

	public void setBlobEntryRemoteModel(BaseModel<?> blobEntryRemoteModel) {
		_blobEntryRemoteModel = blobEntryRemoteModel;
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

		Class<?> remoteModelClass = _blobEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_blobEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			BlobEntryLocalServiceUtil.addBlobEntry(this);
		}
		else {
			BlobEntryLocalServiceUtil.updateBlobEntry(this);
		}
	}

	@Override
	public BlobEntry toEscapedModel() {
		return (BlobEntry)ProxyUtil.newProxyInstance(BlobEntry.class.getClassLoader(),
			new Class[] { BlobEntry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		BlobEntryClp clone = new BlobEntryClp();

		clone.setUuid(getUuid());
		clone.setTestBlobEntryId(getTestBlobEntryId());
		clone.setBlobField(getBlobField());

		return clone;
	}

	@Override
	public int compareTo(BlobEntry blobEntry) {
		long primaryKey = blobEntry.getPrimaryKey();

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

		if (!(obj instanceof BlobEntryClp)) {
			return false;
		}

		BlobEntryClp blobEntry = (BlobEntryClp)obj;

		long primaryKey = blobEntry.getPrimaryKey();

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
		sb.append("com.liferay.testblob.model.BlobEntry");
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
	private BaseModel<?> _blobEntryRemoteModel;
}