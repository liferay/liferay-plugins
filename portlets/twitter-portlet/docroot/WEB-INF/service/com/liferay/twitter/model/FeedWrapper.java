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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Feed}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Feed
 * @generated
 */
public class FeedWrapper implements Feed, ModelWrapper<Feed> {
	public FeedWrapper(Feed feed) {
		_feed = feed;
	}

	public Class<?> getModelClass() {
		return Feed.class;
	}

	public String getModelClassName() {
		return Feed.class.getName();
	}

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

		return attributes;
	}

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
	}

	/**
	* Returns the primary key of this feed.
	*
	* @return the primary key of this feed
	*/
	public long getPrimaryKey() {
		return _feed.getPrimaryKey();
	}

	/**
	* Sets the primary key of this feed.
	*
	* @param primaryKey the primary key of this feed
	*/
	public void setPrimaryKey(long primaryKey) {
		_feed.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the feed ID of this feed.
	*
	* @return the feed ID of this feed
	*/
	public long getFeedId() {
		return _feed.getFeedId();
	}

	/**
	* Sets the feed ID of this feed.
	*
	* @param feedId the feed ID of this feed
	*/
	public void setFeedId(long feedId) {
		_feed.setFeedId(feedId);
	}

	/**
	* Returns the company ID of this feed.
	*
	* @return the company ID of this feed
	*/
	public long getCompanyId() {
		return _feed.getCompanyId();
	}

	/**
	* Sets the company ID of this feed.
	*
	* @param companyId the company ID of this feed
	*/
	public void setCompanyId(long companyId) {
		_feed.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this feed.
	*
	* @return the user ID of this feed
	*/
	public long getUserId() {
		return _feed.getUserId();
	}

	/**
	* Sets the user ID of this feed.
	*
	* @param userId the user ID of this feed
	*/
	public void setUserId(long userId) {
		_feed.setUserId(userId);
	}

	/**
	* Returns the user uuid of this feed.
	*
	* @return the user uuid of this feed
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feed.getUserUuid();
	}

	/**
	* Sets the user uuid of this feed.
	*
	* @param userUuid the user uuid of this feed
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_feed.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this feed.
	*
	* @return the user name of this feed
	*/
	public java.lang.String getUserName() {
		return _feed.getUserName();
	}

	/**
	* Sets the user name of this feed.
	*
	* @param userName the user name of this feed
	*/
	public void setUserName(java.lang.String userName) {
		_feed.setUserName(userName);
	}

	/**
	* Returns the create date of this feed.
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
	* Returns the modified date of this feed.
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
	* Returns the twitter user ID of this feed.
	*
	* @return the twitter user ID of this feed
	*/
	public long getTwitterUserId() {
		return _feed.getTwitterUserId();
	}

	/**
	* Sets the twitter user ID of this feed.
	*
	* @param twitterUserId the twitter user ID of this feed
	*/
	public void setTwitterUserId(long twitterUserId) {
		_feed.setTwitterUserId(twitterUserId);
	}

	/**
	* Returns the twitter user uuid of this feed.
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
	* Returns the twitter screen name of this feed.
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
	* Returns the last status ID of this feed.
	*
	* @return the last status ID of this feed
	*/
	public long getLastStatusId() {
		return _feed.getLastStatusId();
	}

	/**
	* Sets the last status ID of this feed.
	*
	* @param lastStatusId the last status ID of this feed
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

	public java.io.Serializable getPrimaryKeyObj() {
		return _feed.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_feed.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _feed.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_feed.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new FeedWrapper((Feed)_feed.clone());
	}

	public int compareTo(com.liferay.twitter.model.Feed feed) {
		return _feed.compareTo(feed);
	}

	@Override
	public int hashCode() {
		return _feed.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.twitter.model.Feed> toCacheModel() {
		return _feed.toCacheModel();
	}

	public com.liferay.twitter.model.Feed toEscapedModel() {
		return new FeedWrapper(_feed.toEscapedModel());
	}

	public com.liferay.twitter.model.Feed toUnescapedModel() {
		return new FeedWrapper(_feed.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _feed.toString();
	}

	public java.lang.String toXmlString() {
		return _feed.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_feed.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Feed getWrappedFeed() {
		return _feed;
	}

	public Feed getWrappedModel() {
		return _feed;
	}

	public void resetOriginalValues() {
		_feed.resetOriginalValues();
	}

	private Feed _feed;
}