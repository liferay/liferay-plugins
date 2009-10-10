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

/**
 * <a href="FeedWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class FeedWrapper implements Feed {
	public FeedWrapper(Feed feed) {
		_feed = feed;
	}

	public long getPrimaryKey() {
		return _feed.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_feed.setPrimaryKey(pk);
	}

	public long getFeedId() {
		return _feed.getFeedId();
	}

	public void setFeedId(long feedId) {
		_feed.setFeedId(feedId);
	}

	public long getTwitterUserId() {
		return _feed.getTwitterUserId();
	}

	public void setTwitterUserId(long twitterUserId) {
		_feed.setTwitterUserId(twitterUserId);
	}

	public java.lang.String getTwitterUserUuid()
		throws com.liferay.portal.SystemException {
		return _feed.getTwitterUserUuid();
	}

	public void setTwitterUserUuid(java.lang.String twitterUserUuid) {
		_feed.setTwitterUserUuid(twitterUserUuid);
	}

	public java.lang.String getTwitterScreenName() {
		return _feed.getTwitterScreenName();
	}

	public void setTwitterScreenName(java.lang.String twitterScreenName) {
		_feed.setTwitterScreenName(twitterScreenName);
	}

	public java.util.Date getCreateDate() {
		return _feed.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_feed.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _feed.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_feed.setModifiedDate(modifiedDate);
	}

	public long getLastStatusId() {
		return _feed.getLastStatusId();
	}

	public void setLastStatusId(long lastStatusId) {
		_feed.setLastStatusId(lastStatusId);
	}

	public com.liferay.twitter.model.Feed toEscapedModel() {
		return _feed.toEscapedModel();
	}

	public boolean isNew() {
		return _feed.isNew();
	}

	public boolean setNew(boolean n) {
		return _feed.setNew(n);
	}

	public boolean isCachedModel() {
		return _feed.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_feed.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _feed.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_feed.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _feed.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _feed.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_feed.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _feed.clone();
	}

	public int compareTo(com.liferay.twitter.model.Feed feed) {
		return _feed.compareTo(feed);
	}

	public int hashCode() {
		return _feed.hashCode();
	}

	public java.lang.String toString() {
		return _feed.toString();
	}

	public java.lang.String toXmlString() {
		return _feed.toXmlString();
	}

	public Feed getWrappedFeed() {
		return _feed;
	}

	private Feed _feed;
}