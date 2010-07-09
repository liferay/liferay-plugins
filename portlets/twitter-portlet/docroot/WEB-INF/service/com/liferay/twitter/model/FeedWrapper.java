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

package com.liferay.twitter.model;


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link Feed}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Feed
 * @generated
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
		throws com.liferay.portal.kernel.exception.SystemException {
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