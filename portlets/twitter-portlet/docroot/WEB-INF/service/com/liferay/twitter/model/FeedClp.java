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

package com.liferay.twitter.model;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * <a href="FeedClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FeedClp extends BaseModelImpl implements Feed {
	public FeedClp() {
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
	}

	public String getTwitterScreenName() {
		return _twitterScreenName;
	}

	public void setTwitterScreenName(String twitterScreenName) {
		_twitterScreenName = twitterScreenName;
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
			Feed model = new FeedClp();

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
		FeedClp clone = new FeedClp();

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

		FeedClp feed = (FeedClp)obj;

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

		FeedClp feed = null;

		try {
			feed = (FeedClp)obj;
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