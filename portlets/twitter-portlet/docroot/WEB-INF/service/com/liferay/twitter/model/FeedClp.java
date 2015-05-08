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

package com.liferay.twitter.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.UserLocalServiceUtil;

import com.liferay.twitter.service.ClpSerializer;
import com.liferay.twitter.service.FeedLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class FeedClp extends BaseModelImpl<Feed> implements Feed {
	public FeedClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Feed.class;
	}

	@Override
	public String getModelClassName() {
		return Feed.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _feedId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFeedId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _feedId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("feedId", getFeedId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("twitterUserId", getTwitterUserId());
		attributes.put("twitterScreenName", getTwitterScreenName());
		attributes.put("lastStatusId", getLastStatusId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long feedId = (Long)attributes.get("feedId");

		if (feedId != null) {
			setFeedId(feedId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long twitterUserId = (Long)attributes.get("twitterUserId");

		if (twitterUserId != null) {
			setTwitterUserId(twitterUserId);
		}

		String twitterScreenName = (String)attributes.get("twitterScreenName");

		if (twitterScreenName != null) {
			setTwitterScreenName(twitterScreenName);
		}

		Long lastStatusId = (Long)attributes.get("lastStatusId");

		if (lastStatusId != null) {
			setLastStatusId(lastStatusId);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getFeedId() {
		return _feedId;
	}

	@Override
	public void setFeedId(long feedId) {
		_feedId = feedId;

		if (_feedRemoteModel != null) {
			try {
				Class<?> clazz = _feedRemoteModel.getClass();

				Method method = clazz.getMethod("setFeedId", long.class);

				method.invoke(_feedRemoteModel, feedId);
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

		if (_feedRemoteModel != null) {
			try {
				Class<?> clazz = _feedRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_feedRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_feedRemoteModel != null) {
			try {
				Class<?> clazz = _feedRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_feedRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_feedRemoteModel != null) {
			try {
				Class<?> clazz = _feedRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_feedRemoteModel, userName);
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

		if (_feedRemoteModel != null) {
			try {
				Class<?> clazz = _feedRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_feedRemoteModel, createDate);
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

		if (_feedRemoteModel != null) {
			try {
				Class<?> clazz = _feedRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_feedRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTwitterUserId() {
		return _twitterUserId;
	}

	@Override
	public void setTwitterUserId(long twitterUserId) {
		_twitterUserId = twitterUserId;

		if (_feedRemoteModel != null) {
			try {
				Class<?> clazz = _feedRemoteModel.getClass();

				Method method = clazz.getMethod("setTwitterUserId", long.class);

				method.invoke(_feedRemoteModel, twitterUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTwitterUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getTwitterUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setTwitterUserUuid(String twitterUserUuid) {
	}

	@Override
	public String getTwitterScreenName() {
		return _twitterScreenName;
	}

	@Override
	public void setTwitterScreenName(String twitterScreenName) {
		_twitterScreenName = twitterScreenName;

		if (_feedRemoteModel != null) {
			try {
				Class<?> clazz = _feedRemoteModel.getClass();

				Method method = clazz.getMethod("setTwitterScreenName",
						String.class);

				method.invoke(_feedRemoteModel, twitterScreenName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getLastStatusId() {
		return _lastStatusId;
	}

	@Override
	public void setLastStatusId(long lastStatusId) {
		_lastStatusId = lastStatusId;

		if (_feedRemoteModel != null) {
			try {
				Class<?> clazz = _feedRemoteModel.getClass();

				Method method = clazz.getMethod("setLastStatusId", long.class);

				method.invoke(_feedRemoteModel, lastStatusId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getFeedRemoteModel() {
		return _feedRemoteModel;
	}

	public void setFeedRemoteModel(BaseModel<?> feedRemoteModel) {
		_feedRemoteModel = feedRemoteModel;
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

		Class<?> remoteModelClass = _feedRemoteModel.getClass();

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

		Object returnValue = method.invoke(_feedRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			FeedLocalServiceUtil.addFeed(this);
		}
		else {
			FeedLocalServiceUtil.updateFeed(this);
		}
	}

	@Override
	public Feed toEscapedModel() {
		return (Feed)ProxyUtil.newProxyInstance(Feed.class.getClassLoader(),
			new Class[] { Feed.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		FeedClp clone = new FeedClp();

		clone.setFeedId(getFeedId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setTwitterUserId(getTwitterUserId());
		clone.setTwitterScreenName(getTwitterScreenName());
		clone.setLastStatusId(getLastStatusId());

		return clone;
	}

	@Override
	public int compareTo(Feed feed) {
		long primaryKey = feed.getPrimaryKey();

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

		if (!(obj instanceof FeedClp)) {
			return false;
		}

		FeedClp feed = (FeedClp)obj;

		long primaryKey = feed.getPrimaryKey();

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

		sb.append("{feedId=");
		sb.append(getFeedId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", twitterUserId=");
		sb.append(getTwitterUserId());
		sb.append(", twitterScreenName=");
		sb.append(getTwitterScreenName());
		sb.append(", lastStatusId=");
		sb.append(getLastStatusId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.twitter.model.Feed");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>feedId</column-name><column-value><![CDATA[");
		sb.append(getFeedId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
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
			"<column><column-name>twitterUserId</column-name><column-value><![CDATA[");
		sb.append(getTwitterUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>twitterScreenName</column-name><column-value><![CDATA[");
		sb.append(getTwitterScreenName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastStatusId</column-name><column-value><![CDATA[");
		sb.append(getLastStatusId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _feedId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _twitterUserId;
	private String _twitterScreenName;
	private long _lastStatusId;
	private BaseModel<?> _feedRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}