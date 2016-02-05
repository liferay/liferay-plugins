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

package com.liferay.socialcoding.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.socialcoding.service.ClpSerializer;
import com.liferay.socialcoding.service.SVNRevisionLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class SVNRevisionClp extends BaseModelImpl<SVNRevision>
	implements SVNRevision {
	public SVNRevisionClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SVNRevision.class;
	}

	@Override
	public String getModelClassName() {
		return SVNRevision.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _svnRevisionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSvnRevisionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _svnRevisionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("svnRevisionId", getSvnRevisionId());
		attributes.put("svnUserId", getSvnUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("svnRepositoryId", getSvnRepositoryId());
		attributes.put("revisionNumber", getRevisionNumber());
		attributes.put("comments", getComments());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long svnRevisionId = (Long)attributes.get("svnRevisionId");

		if (svnRevisionId != null) {
			setSvnRevisionId(svnRevisionId);
		}

		String svnUserId = (String)attributes.get("svnUserId");

		if (svnUserId != null) {
			setSvnUserId(svnUserId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long svnRepositoryId = (Long)attributes.get("svnRepositoryId");

		if (svnRepositoryId != null) {
			setSvnRepositoryId(svnRepositoryId);
		}

		Long revisionNumber = (Long)attributes.get("revisionNumber");

		if (revisionNumber != null) {
			setRevisionNumber(revisionNumber);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getSvnRevisionId() {
		return _svnRevisionId;
	}

	@Override
	public void setSvnRevisionId(long svnRevisionId) {
		_svnRevisionId = svnRevisionId;

		if (_svnRevisionRemoteModel != null) {
			try {
				Class<?> clazz = _svnRevisionRemoteModel.getClass();

				Method method = clazz.getMethod("setSvnRevisionId", long.class);

				method.invoke(_svnRevisionRemoteModel, svnRevisionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSvnUserId() {
		return _svnUserId;
	}

	@Override
	public void setSvnUserId(String svnUserId) {
		_svnUserId = svnUserId;

		if (_svnRevisionRemoteModel != null) {
			try {
				Class<?> clazz = _svnRevisionRemoteModel.getClass();

				Method method = clazz.getMethod("setSvnUserId", String.class);

				method.invoke(_svnRevisionRemoteModel, svnUserId);
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

		if (_svnRevisionRemoteModel != null) {
			try {
				Class<?> clazz = _svnRevisionRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_svnRevisionRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSvnRepositoryId() {
		return _svnRepositoryId;
	}

	@Override
	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRepositoryId = svnRepositoryId;

		if (_svnRevisionRemoteModel != null) {
			try {
				Class<?> clazz = _svnRevisionRemoteModel.getClass();

				Method method = clazz.getMethod("setSvnRepositoryId", long.class);

				method.invoke(_svnRevisionRemoteModel, svnRepositoryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getRevisionNumber() {
		return _revisionNumber;
	}

	@Override
	public void setRevisionNumber(long revisionNumber) {
		_revisionNumber = revisionNumber;

		if (_svnRevisionRemoteModel != null) {
			try {
				Class<?> clazz = _svnRevisionRemoteModel.getClass();

				Method method = clazz.getMethod("setRevisionNumber", long.class);

				method.invoke(_svnRevisionRemoteModel, revisionNumber);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getComments() {
		return _comments;
	}

	@Override
	public void setComments(String comments) {
		_comments = comments;

		if (_svnRevisionRemoteModel != null) {
			try {
				Class<?> clazz = _svnRevisionRemoteModel.getClass();

				Method method = clazz.getMethod("setComments", String.class);

				method.invoke(_svnRevisionRemoteModel, comments);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public java.lang.Object[] getJIRAIssueAndComments() {
		try {
			String methodName = "getJIRAIssueAndComments";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.Object[] returnObj = (java.lang.Object[])invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getWebRevisionNumberURL() {
		try {
			String methodName = "getWebRevisionNumberURL";

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

	@Override
	public com.liferay.socialcoding.model.SVNRepository getSVNRepository() {
		try {
			String methodName = "getSVNRepository";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.socialcoding.model.SVNRepository returnObj = (com.liferay.socialcoding.model.SVNRepository)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getSVNRevisionRemoteModel() {
		return _svnRevisionRemoteModel;
	}

	public void setSVNRevisionRemoteModel(BaseModel<?> svnRevisionRemoteModel) {
		_svnRevisionRemoteModel = svnRevisionRemoteModel;
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

		Class<?> remoteModelClass = _svnRevisionRemoteModel.getClass();

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

		Object returnValue = method.invoke(_svnRevisionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			SVNRevisionLocalServiceUtil.addSVNRevision(this);
		}
		else {
			SVNRevisionLocalServiceUtil.updateSVNRevision(this);
		}
	}

	@Override
	public SVNRevision toEscapedModel() {
		return (SVNRevision)ProxyUtil.newProxyInstance(SVNRevision.class.getClassLoader(),
			new Class[] { SVNRevision.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SVNRevisionClp clone = new SVNRevisionClp();

		clone.setSvnRevisionId(getSvnRevisionId());
		clone.setSvnUserId(getSvnUserId());
		clone.setCreateDate(getCreateDate());
		clone.setSvnRepositoryId(getSvnRepositoryId());
		clone.setRevisionNumber(getRevisionNumber());
		clone.setComments(getComments());

		return clone;
	}

	@Override
	public int compareTo(SVNRevision svnRevision) {
		int value = 0;

		if (getRevisionNumber() < svnRevision.getRevisionNumber()) {
			value = -1;
		}
		else if (getRevisionNumber() > svnRevision.getRevisionNumber()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

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

		if (!(obj instanceof SVNRevisionClp)) {
			return false;
		}

		SVNRevisionClp svnRevision = (SVNRevisionClp)obj;

		long primaryKey = svnRevision.getPrimaryKey();

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
		StringBundler sb = new StringBundler(13);

		sb.append("{svnRevisionId=");
		sb.append(getSvnRevisionId());
		sb.append(", svnUserId=");
		sb.append(getSvnUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", svnRepositoryId=");
		sb.append(getSvnRepositoryId());
		sb.append(", revisionNumber=");
		sb.append(getRevisionNumber());
		sb.append(", comments=");
		sb.append(getComments());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.socialcoding.model.SVNRevision");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>svnRevisionId</column-name><column-value><![CDATA[");
		sb.append(getSvnRevisionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>svnUserId</column-name><column-value><![CDATA[");
		sb.append(getSvnUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>svnRepositoryId</column-name><column-value><![CDATA[");
		sb.append(getSvnRepositoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>revisionNumber</column-name><column-value><![CDATA[");
		sb.append(getRevisionNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comments</column-name><column-value><![CDATA[");
		sb.append(getComments());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _svnRevisionId;
	private String _svnUserId;
	private Date _createDate;
	private long _svnRepositoryId;
	private long _revisionNumber;
	private String _comments;
	private BaseModel<?> _svnRevisionRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.socialcoding.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}