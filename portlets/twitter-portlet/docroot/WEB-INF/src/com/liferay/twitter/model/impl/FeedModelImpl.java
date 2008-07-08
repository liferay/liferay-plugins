/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.twitter.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.twitter.model.Feed;
import com.liferay.twitter.model.FeedSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="FeedModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FeedModelImpl extends BaseModelImpl {
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
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.twitter.model.Feed"),
			true);

	public static Feed toModel(FeedSoap soapModel) {
		Feed model = new FeedImpl();

		model.setFeedId(soapModel.getFeedId());
		model.setTwitterUserId(soapModel.getTwitterUserId());
		model.setTwitterScreenName(soapModel.getTwitterScreenName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setLastStatusId(soapModel.getLastStatusId());

		return model;
	}

	public static List<Feed> toModels(FeedSoap[] soapModels) {
		List<Feed> models = new ArrayList<Feed>(soapModels.length);

		for (FeedSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

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
		if (feedId != _feedId) {
			_feedId = feedId;
		}
	}

	public long getTwitterUserId() {
		return _twitterUserId;
	}

	public void setTwitterUserId(long twitterUserId) {
		if (twitterUserId != _twitterUserId) {
			_twitterUserId = twitterUserId;
		}
	}

	public String getTwitterScreenName() {
		return GetterUtil.getString(_twitterScreenName);
	}

	public void setTwitterScreenName(String twitterScreenName) {
		if (((twitterScreenName == null) && (_twitterScreenName != null)) ||
				((twitterScreenName != null) && (_twitterScreenName == null)) ||
				((twitterScreenName != null) && (_twitterScreenName != null) &&
				!twitterScreenName.equals(_twitterScreenName))) {
			_twitterScreenName = twitterScreenName;
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		if (((createDate == null) && (_createDate != null)) ||
				((createDate != null) && (_createDate == null)) ||
				((createDate != null) && (_createDate != null) &&
				!createDate.equals(_createDate))) {
			_createDate = createDate;
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (((modifiedDate == null) && (_modifiedDate != null)) ||
				((modifiedDate != null) && (_modifiedDate == null)) ||
				((modifiedDate != null) && (_modifiedDate != null) &&
				!modifiedDate.equals(_modifiedDate))) {
			_modifiedDate = modifiedDate;
		}
	}

	public long getLastStatusId() {
		return _lastStatusId;
	}

	public void setLastStatusId(long lastStatusId) {
		if (lastStatusId != _lastStatusId) {
			_lastStatusId = lastStatusId;
		}
	}

	public Feed toEscapedModel() {
		if (isEscapedModel()) {
			return (Feed)this;
		}
		else {
			Feed model = new FeedImpl();

			model.setEscapedModel(true);

			model.setFeedId(getFeedId());
			model.setTwitterUserId(getTwitterUserId());
			model.setTwitterScreenName(HtmlUtil.escape(getTwitterScreenName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setLastStatusId(getLastStatusId());

			model = (Feed)Proxy.newProxyInstance(Feed.class.getClassLoader(),
					new Class[] { Feed.class }, new ReadOnlyBeanHandler(model));

			return model;
		}
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

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		FeedImpl feed = (FeedImpl)obj;

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

		FeedImpl feed = null;

		try {
			feed = (FeedImpl)obj;
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

	private long _feedId;
	private long _twitterUserId;
	private String _twitterScreenName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _lastStatusId;
}