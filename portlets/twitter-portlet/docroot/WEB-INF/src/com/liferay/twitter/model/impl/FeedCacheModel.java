/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.twitter.model.Feed;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Feed in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Feed
 * @generated
 */
@ProviderType
public class FeedCacheModel implements CacheModel<Feed>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FeedCacheModel)) {
			return false;
		}

		FeedCacheModel feedCacheModel = (FeedCacheModel)obj;

		if (feedId == feedCacheModel.feedId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, feedId);
	}

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

	@Override
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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		feedId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		twitterUserId = objectInput.readLong();
		twitterScreenName = objectInput.readUTF();
		lastStatusId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(feedId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(twitterUserId);

		if (twitterScreenName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(twitterScreenName);
		}

		objectOutput.writeLong(lastStatusId);
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