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

package com.liferay.socialnetworking.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.socialnetworking.model.WallEntry;

import java.util.Date;

/**
 * The cache model class for representing WallEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see WallEntry
 * @generated
 */
public class WallEntryCacheModel implements CacheModel<WallEntry> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{wallEntryId=");
		sb.append(wallEntryId);
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
		sb.append(", comments=");
		sb.append(comments);
		sb.append("}");

		return sb.toString();
	}

	public WallEntry toEntityModel() {
		WallEntryImpl wallEntryImpl = new WallEntryImpl();

		wallEntryImpl.setWallEntryId(wallEntryId);
		wallEntryImpl.setGroupId(groupId);
		wallEntryImpl.setCompanyId(companyId);
		wallEntryImpl.setUserId(userId);

		if (userName == null) {
			wallEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			wallEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			wallEntryImpl.setCreateDate(null);
		}
		else {
			wallEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			wallEntryImpl.setModifiedDate(null);
		}
		else {
			wallEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (comments == null) {
			wallEntryImpl.setComments(StringPool.BLANK);
		}
		else {
			wallEntryImpl.setComments(comments);
		}

		wallEntryImpl.resetOriginalValues();

		return wallEntryImpl;
	}

	public long wallEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String comments;
}