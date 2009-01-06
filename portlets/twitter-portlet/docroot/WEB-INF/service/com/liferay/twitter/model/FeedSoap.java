/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="FeedSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FeedSoap implements Serializable {
	public static FeedSoap toSoapModel(Feed model) {
		FeedSoap soapModel = new FeedSoap();

		soapModel.setFeedId(model.getFeedId());
		soapModel.setTwitterUserId(model.getTwitterUserId());
		soapModel.setTwitterScreenName(model.getTwitterScreenName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLastStatusId(model.getLastStatusId());

		return soapModel;
	}

	public static FeedSoap[] toSoapModels(List<Feed> models) {
		List<FeedSoap> soapModels = new ArrayList<FeedSoap>(models.size());

		for (Feed model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FeedSoap[soapModels.size()]);
	}

	public FeedSoap() {
	}

	public long getPrimaryKey() {
		return _feedId;
	}

	public void setPrimaryKey(long pk) {
		setFeedId(pk);
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

	private long _feedId;
	private long _twitterUserId;
	private String _twitterScreenName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _lastStatusId;
}