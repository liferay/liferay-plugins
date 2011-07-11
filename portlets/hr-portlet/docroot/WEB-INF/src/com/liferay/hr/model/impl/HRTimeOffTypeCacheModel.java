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

import com.liferay.hr.model.HRTimeOffType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRTimeOffType in entity cache.
 *
 * @author Wesley Gong
 * @see HRTimeOffType
 * @generated
 */
public class HRTimeOffTypeCacheModel implements CacheModel<HRTimeOffType> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{hrTimeOffTypeId=");
		sb.append(hrTimeOffTypeId);
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

	public HRTimeOffType toEntityModel() {
		HRTimeOffTypeImpl hrTimeOffTypeImpl = new HRTimeOffTypeImpl();

		hrTimeOffTypeImpl.setHrTimeOffTypeId(hrTimeOffTypeId);
		hrTimeOffTypeImpl.setGroupId(groupId);
		hrTimeOffTypeImpl.setCompanyId(companyId);
		hrTimeOffTypeImpl.setUserId(userId);

		if (userName == null) {
			hrTimeOffTypeImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrTimeOffTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrTimeOffTypeImpl.setCreateDate(null);
		}
		else {
			hrTimeOffTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrTimeOffTypeImpl.setModifiedDate(null);
		}
		else {
			hrTimeOffTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			hrTimeOffTypeImpl.setName(StringPool.BLANK);
		}
		else {
			hrTimeOffTypeImpl.setName(name);
		}

		if (description == null) {
			hrTimeOffTypeImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrTimeOffTypeImpl.setDescription(description);
		}

		hrTimeOffTypeImpl.resetOriginalValues();

		return hrTimeOffTypeImpl;
	}

	public long hrTimeOffTypeId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
}