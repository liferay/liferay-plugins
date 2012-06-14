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

package com.liferay.portal.oauth.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.oauth.model.OAuthApplication;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing OAuthApplication in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OAuthApplication
 * @generated
 */
public class OAuthApplicationCacheModel implements CacheModel<OAuthApplication>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{applicationId=");
		sb.append(applicationId);
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
		sb.append(", ownerId=");
		sb.append(ownerId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", website=");
		sb.append(website);
		sb.append(", consumerKey=");
		sb.append(consumerKey);
		sb.append(", consumerSecret=");
		sb.append(consumerSecret);
		sb.append(", callBackURL=");
		sb.append(callBackURL);
		sb.append(", accessLevel=");
		sb.append(accessLevel);
		sb.append("}");

		return sb.toString();
	}

	public OAuthApplication toEntityModel() {
		OAuthApplicationImpl oAuthApplicationImpl = new OAuthApplicationImpl();

		oAuthApplicationImpl.setApplicationId(applicationId);
		oAuthApplicationImpl.setCompanyId(companyId);
		oAuthApplicationImpl.setUserId(userId);

		if (userName == null) {
			oAuthApplicationImpl.setUserName(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			oAuthApplicationImpl.setCreateDate(null);
		}
		else {
			oAuthApplicationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			oAuthApplicationImpl.setModifiedDate(null);
		}
		else {
			oAuthApplicationImpl.setModifiedDate(new Date(modifiedDate));
		}

		oAuthApplicationImpl.setOwnerId(ownerId);

		if (name == null) {
			oAuthApplicationImpl.setName(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setName(name);
		}

		if (description == null) {
			oAuthApplicationImpl.setDescription(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setDescription(description);
		}

		if (website == null) {
			oAuthApplicationImpl.setWebsite(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setWebsite(website);
		}

		if (consumerKey == null) {
			oAuthApplicationImpl.setConsumerKey(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setConsumerKey(consumerKey);
		}

		if (consumerSecret == null) {
			oAuthApplicationImpl.setConsumerSecret(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setConsumerSecret(consumerSecret);
		}

		if (callBackURL == null) {
			oAuthApplicationImpl.setCallBackURL(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setCallBackURL(callBackURL);
		}

		oAuthApplicationImpl.setAccessLevel(accessLevel);

		oAuthApplicationImpl.resetOriginalValues();

		return oAuthApplicationImpl;
	}

	public long applicationId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long ownerId;
	public String name;
	public String description;
	public String website;
	public String consumerKey;
	public String consumerSecret;
	public String callBackURL;
	public int accessLevel;
}