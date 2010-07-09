/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.twitter.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import com.liferay.twitter.model.Feed;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.Date;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Twitter_Feed table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FeedImpl
 * @see       com.liferay.twitter.model.Feed
 * @see       com.liferay.twitter.model.FeedModel
 * @generated
 */
public class FeedModelImpl extends BaseModelImpl<Feed> {
	public static final String TABLE_NAME = "Twitter_Feed";
	public static final Object[][] TABLE_COLUMNS = {
			{ "feedId", new Integer(Types.BIGINT) },
			{ "twitterUserId", new Integer(Types.BIGINT) },
			{ "twitterScreenName", new Integer(Types.VARCHAR) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "lastStatusId", new Integer(Types.BIGINT) }
		};
	public static final String TABLE_SQL_CREATE = "create table Twitter_Feed (feedId LONG not null primary key,twitterUserId LONG,twitterScreenName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,lastStatusId LONG)";
	public static final String TABLE_SQL_DROP = "drop table Twitter_Feed";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.twitter.model.Feed"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.twitter.model.Feed"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.twitter.model.Feed"));

	public FeedModelImpl() {
	}

	public long getPrimaryKey() {
		return _feedId;
	}

	public void setPrimaryKey(long pk) {
		setFeedId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_feedId);
	}

	public long getFeedId() {
		return _feedId;
	}

	public void setFeedId(long feedId) {
		_feedId = feedId;
	}

	public long getTwitterUserId() {
		return _twitterUserId;
	}

	public void setTwitterUserId(long twitterUserId) {
		_twitterUserId = twitterUserId;

		if (!_setOriginalTwitterUserId) {
			_setOriginalTwitterUserId = true;

			_originalTwitterUserId = twitterUserId;
		}
	}

	public String getTwitterUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getTwitterUserId(), "uuid",
			_twitterUserUuid);
	}

	public void setTwitterUserUuid(String twitterUserUuid) {
		_twitterUserUuid = twitterUserUuid;
	}

	public long getOriginalTwitterUserId() {
		return _originalTwitterUserId;
	}

	public String getTwitterScreenName() {
		if (_twitterScreenName == null) {
			return StringPool.BLANK;
		}
		else {
			return _twitterScreenName;
		}
	}

	public void setTwitterScreenName(String twitterScreenName) {
		_twitterScreenName = twitterScreenName;

		if (_originalTwitterScreenName == null) {
			_originalTwitterScreenName = twitterScreenName;
		}
	}

	public String getOriginalTwitterScreenName() {
		return GetterUtil.getString(_originalTwitterScreenName);
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

	public long getLastStatusId() {
		return _lastStatusId;
	}

	public void setLastStatusId(long lastStatusId) {
		_lastStatusId = lastStatusId;
	}

	public Feed toEscapedModel() {
		if (isEscapedModel()) {
			return (Feed)this;
		}
		else {
			return (Feed)Proxy.newProxyInstance(Feed.class.getClassLoader(),
				new Class[] { Feed.class }, new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(0,
					Feed.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		FeedImpl clone = new FeedImpl();

		clone.setFeedId(getFeedId());
		clone.setTwitterUserId(getTwitterUserId());
		clone.setTwitterScreenName(getTwitterScreenName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setLastStatusId(getLastStatusId());

		return clone;
	}

	public int compareTo(Feed feed) {
		long pk = feed.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Feed feed = null;

		try {
			feed = (Feed)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = feed.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{feedId=");
		sb.append(getFeedId());
		sb.append(", twitterUserId=");
		sb.append(getTwitterUserId());
		sb.append(", twitterScreenName=");
		sb.append(getTwitterScreenName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", lastStatusId=");
		sb.append(getLastStatusId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.twitter.model.Feed");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>feedId</column-name><column-value><![CDATA[");
		sb.append(getFeedId());
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
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastStatusId</column-name><column-value><![CDATA[");
		sb.append(getLastStatusId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _feedId;
	private long _twitterUserId;
	private String _twitterUserUuid;
	private long _originalTwitterUserId;
	private boolean _setOriginalTwitterUserId;
	private String _twitterScreenName;
	private String _originalTwitterScreenName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _lastStatusId;
	private transient ExpandoBridge _expandoBridge;
}