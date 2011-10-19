/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.twitter.model.Feed;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Feed in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Feed
 * @generated
 */
public class FeedCacheModel implements CacheModel<Feed>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{feedId=");
		sb.append(feedId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", twitterUserId=");
		sb.append(twitterUserId);
		sb.append(", twitterScreenName=");
		sb.append(twitterScreenName);
		sb.append(", lastStatusId=");
		sb.append(lastStatusId);
		sb.append("}");

		return sb.toString();
	}

	public Feed toEntityModel() {
		FeedImpl feedImpl = new FeedImpl();

		feedImpl.setFeedId(feedId);
		feedImpl.setCompanyId(companyId);
		feedImpl.setUserId(userId);

		if (userName == null) {
			feedImpl.setUserName(StringPool.BLANK);
		}
		else {
			feedImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			feedImpl.setCreateDate(null);
		}
		else {
			feedImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			feedImpl.setModifiedDate(null);
		}
		else {
			feedImpl.setModifiedDate(new Date(modifiedDate));
		}

		feedImpl.setTwitterUserId(twitterUserId);

		if (twitterScreenName == null) {
			feedImpl.setTwitterScreenName(StringPool.BLANK);
		}
		else {
			feedImpl.setTwitterScreenName(twitterScreenName);
		}

		feedImpl.setLastStatusId(lastStatusId);

		feedImpl.resetOriginalValues();

		return feedImpl;
	}

	public long feedId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long twitterUserId;
	public String twitterScreenName;
	public long lastStatusId;
}