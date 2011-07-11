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

import com.liferay.hr.model.HRWageType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRWageType in entity cache.
 *
 * @author Wesley Gong
 * @see HRWageType
 * @generated
 */
public class HRWageTypeCacheModel implements CacheModel<HRWageType> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{hrWageTypeId=");
		sb.append(hrWageTypeId);
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
		sb.append(", code=");
		sb.append(code);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	public HRWageType toEntityModel() {
		HRWageTypeImpl hrWageTypeImpl = new HRWageTypeImpl();

		hrWageTypeImpl.setHrWageTypeId(hrWageTypeId);
		hrWageTypeImpl.setGroupId(groupId);
		hrWageTypeImpl.setCompanyId(companyId);
		hrWageTypeImpl.setUserId(userId);

		if (userName == null) {
			hrWageTypeImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrWageTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrWageTypeImpl.setCreateDate(null);
		}
		else {
			hrWageTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrWageTypeImpl.setModifiedDate(null);
		}
		else {
			hrWageTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (code == null) {
			hrWageTypeImpl.setCode(StringPool.BLANK);
		}
		else {
			hrWageTypeImpl.setCode(code);
		}

		if (name == null) {
			hrWageTypeImpl.setName(StringPool.BLANK);
		}
		else {
			hrWageTypeImpl.setName(name);
		}

		if (description == null) {
			hrWageTypeImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrWageTypeImpl.setDescription(description);
		}

		hrWageTypeImpl.resetOriginalValues();

		return hrWageTypeImpl;
	}

	public long hrWageTypeId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String code;
	public String name;
	public String description;
}