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

package com.liferay.hr.model.impl;

import com.liferay.hr.model.HRClient;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRClient in entity cache.
 *
 * @author Wesley Gong
 * @see HRClient
 * @generated
 */
public class HRClientCacheModel implements CacheModel<HRClient> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{hrClientId=");
		sb.append(hrClientId);
		sb.append(", groupId=");
		sb.append(groupId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	public HRClient toEntityModel() {
		HRClientImpl hrClientImpl = new HRClientImpl();

		hrClientImpl.setHrClientId(hrClientId);
		hrClientImpl.setGroupId(groupId);
		hrClientImpl.setCompanyId(companyId);
		hrClientImpl.setUserId(userId);

		if (userName == null) {
			hrClientImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrClientImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrClientImpl.setCreateDate(null);
		}
		else {
			hrClientImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrClientImpl.setModifiedDate(null);
		}
		else {
			hrClientImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			hrClientImpl.setName(StringPool.BLANK);
		}
		else {
			hrClientImpl.setName(name);
		}

		if (description == null) {
			hrClientImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrClientImpl.setDescription(description);
		}

		hrClientImpl.resetOriginalValues();

		return hrClientImpl;
	}

	public long hrClientId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
}