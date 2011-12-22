/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.twitter.service.FeedLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class FeedClp extends BaseModelImpl<Feed> implements Feed {
	public FeedClp() {
	}

	public Class<?> getModelClass() {
		return Feed.class;
	}

	public String getModelClassName() {
		return Feed.class.getName();
	}

	public long getPrimaryKey() {
		return _feedId;
	}

	public void setPrimaryKey(long primaryKey) {
		setFeedId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_feedId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getFeedId() {
		return _feedId;
	}

	public void setFeedId(long feedId) {
		_feedId = feedId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getTwitterUserId() {
		return _twitterUserId;
	}

	public void setTwitterUserId(long twitterUserId) {
		_twitterUserId = twitterUserId;
	}

	public String getTwitterUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getTwitterUserId(), "uuid",
			_twitterUserUuid);
	}

	public void setTwitterUserUuid(String twitterUserUuid) {
		_twitterUserUuid = twitterUserUuid;
	}

	public String getTwitterScreenName() {
		return _twitterScreenName;
	}

	public void setTwitterScreenName(String twitterScreenName) {
		_twitterScreenName = twitterScreenName;
	}

	public long getLastStatusId() {
		return _lastStatusId;
	}

	public void setLastStatusId(long lastStatusId) {
		_lastStatusId = lastStatusId;
	}

	public void persist() throws SystemException {
		FeedLocalServiceUtil.updateFeed(this);
	}

	@Override
	public Feed toEscapedModel() {
		return (Feed)Proxy.newProxyInstance(Feed.class.getClassLoader(),
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
		if (obj == null) {
			return false;
		}

		FeedClp feed = null;

		try {
			feed = (FeedClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = feed.getPrimaryKey();

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
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _twitterUserId;
	private String _twitterUserUuid;
	private String _twitterScreenName;
	private long _lastStatusId;
}