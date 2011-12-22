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

package com.liferay.privatemessaging.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import com.liferay.privatemessaging.model.UserThread;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing UserThread in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserThread
 * @generated
 */
public class UserThreadCacheModel implements CacheModel<UserThread>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{userThreadId=");
		sb.append(userThreadId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", mbThreadId=");
		sb.append(mbThreadId);
		sb.append(", topMBMessageId=");
		sb.append(topMBMessageId);
		sb.append(", read=");
		sb.append(read);
		sb.append(", deleted=");
		sb.append(deleted);
		sb.append("}");

		return sb.toString();
	}

	public UserThread toEntityModel() {
		UserThreadImpl userThreadImpl = new UserThreadImpl();

		userThreadImpl.setUserThreadId(userThreadId);
		userThreadImpl.setCompanyId(companyId);
		userThreadImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			userThreadImpl.setCreateDate(null);
		}
		else {
			userThreadImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			userThreadImpl.setModifiedDate(null);
		}
		else {
			userThreadImpl.setModifiedDate(new Date(modifiedDate));
		}

		userThreadImpl.setMbThreadId(mbThreadId);
		userThreadImpl.setTopMBMessageId(topMBMessageId);
		userThreadImpl.setRead(read);
		userThreadImpl.setDeleted(deleted);

		userThreadImpl.resetOriginalValues();

		return userThreadImpl;
	}

	public long userThreadId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long mbThreadId;
	public long topMBMessageId;
	public boolean read;
	public boolean deleted;
}