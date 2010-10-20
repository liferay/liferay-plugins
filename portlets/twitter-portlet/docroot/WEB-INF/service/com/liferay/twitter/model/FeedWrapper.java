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

	/**
	* Gets the primary key of this feed.
	*
	* @return the primary key of this feed
	*/
	public long getPrimaryKey() {
		return _feed.getPrimaryKey();
	}

	/**
	* Sets the primary key of this feed
	*
	* @param pk the primary key of this feed
	*/
	public void setPrimaryKey(long pk) {
		_feed.setPrimaryKey(pk);
	}

	/**
	* Gets the feed id of this feed.
	*
	* @return the feed id of this feed
	*/
	public long getFeedId() {
		return _feed.getFeedId();
	}

	/**
	* Sets the feed id of this feed.
	*
	* @param feedId the feed id of this feed
	*/
	public void setFeedId(long feedId) {
		_feed.setFeedId(feedId);
	}

	/**
	* Gets the company id of this feed.
	*
	* @return the company id of this feed
	*/
	public long getCompanyId() {
		return _feed.getCompanyId();
	}

	/**
	* Sets the company id of this feed.
	*
	* @param companyId the company id of this feed
	*/
	public void setCompanyId(long companyId) {
		_feed.setCompanyId(companyId);
	}

	/**
	* Gets the twitter user id of this feed.
	*
	* @return the twitter user id of this feed
	*/
	public long getTwitterUserId() {
		return _feed.getTwitterUserId();
	}

	/**
	* Sets the twitter user id of this feed.
	*
	* @param twitterUserId the twitter user id of this feed
	*/
	public void setTwitterUserId(long twitterUserId) {
		_feed.setTwitterUserId(twitterUserId);
	}

	/**
	* Gets the twitter user uuid of this feed.
	*
	* @return the twitter user uuid of this feed
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getTwitterUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feed.getTwitterUserUuid();
	}

	/**
	* Sets the twitter user uuid of this feed.
	*
	* @param twitterUserUuid the twitter user uuid of this feed
	*/
	public void setTwitterUserUuid(java.lang.String twitterUserUuid) {
		_feed.setTwitterUserUuid(twitterUserUuid);
	}

	/**
	* Gets the twitter screen name of this feed.
	*
	* @return the twitter screen name of this feed
	*/
	public java.lang.String getTwitterScreenName() {
		return _feed.getTwitterScreenName();
	}

	/**
	* Sets the twitter screen name of this feed.
	*
	* @param twitterScreenName the twitter screen name of this feed
	*/
	public void setTwitterScreenName(java.lang.String twitterScreenName) {
		_feed.setTwitterScreenName(twitterScreenName);
	}

	/**
	* Gets the create date of this feed.
	*
	* @return the create date of this feed
	*/
	public java.util.Date getCreateDate() {
		return _feed.getCreateDate();
	}

	/**
	* Sets the create date of this feed.
	*
	* @param createDate the create date of this feed
	*/
	public void setCreateDate(java.util.Date createDate) {
		_feed.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this feed.
	*
	* @return the modified date of this feed
	*/
	public java.util.Date getModifiedDate() {
		return _feed.getModifiedDate();
	}

	/**
	* Sets the modified date of this feed.
	*
	* @param modifiedDate the modified date of this feed
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_feed.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the last status id of this feed.
	*
	* @return the last status id of this feed
	*/
	public long getLastStatusId() {
		return _feed.getLastStatusId();
	}

	/**
	* Sets the last status id of this feed.
	*
	* @param lastStatusId the last status id of this feed
	*/
	public void setLastStatusId(long lastStatusId) {
		_feed.setLastStatusId(lastStatusId);
	}

	public boolean isNew() {
		return _feed.isNew();
	}

	public void setNew(boolean n) {
		_feed.setNew(n);
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

	public com.liferay.twitter.model.Feed toEscapedModel() {
		return _feed.toEscapedModel();
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