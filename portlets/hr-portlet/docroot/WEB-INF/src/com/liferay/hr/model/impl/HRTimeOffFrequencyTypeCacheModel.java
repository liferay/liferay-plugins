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

import com.liferay.hr.model.HRTimeOffFrequencyType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRTimeOffFrequencyType in entity cache.
 *
 * @author Wesley Gong
 * @see HRTimeOffFrequencyType
 * @generated
 */
public class HRTimeOffFrequencyTypeCacheModel implements CacheModel<HRTimeOffFrequencyType> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{hrTimeOffFrequencyTypeId=");
		sb.append(hrTimeOffFrequencyTypeId);
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

	public HRTimeOffFrequencyType toEntityModel() {
		HRTimeOffFrequencyTypeImpl hrTimeOffFrequencyTypeImpl = new HRTimeOffFrequencyTypeImpl();

		hrTimeOffFrequencyTypeImpl.setHrTimeOffFrequencyTypeId(hrTimeOffFrequencyTypeId);
		hrTimeOffFrequencyTypeImpl.setGroupId(groupId);
		hrTimeOffFrequencyTypeImpl.setCompanyId(companyId);
		hrTimeOffFrequencyTypeImpl.setUserId(userId);

		if (userName == null) {
			hrTimeOffFrequencyTypeImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrTimeOffFrequencyTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrTimeOffFrequencyTypeImpl.setCreateDate(null);
		}
		else {
			hrTimeOffFrequencyTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrTimeOffFrequencyTypeImpl.setModifiedDate(null);
		}
		else {
			hrTimeOffFrequencyTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (code == null) {
			hrTimeOffFrequencyTypeImpl.setCode(StringPool.BLANK);
		}
		else {
			hrTimeOffFrequencyTypeImpl.setCode(code);
		}

		if (name == null) {
			hrTimeOffFrequencyTypeImpl.setName(StringPool.BLANK);
		}
		else {
			hrTimeOffFrequencyTypeImpl.setName(name);
		}

		if (description == null) {
			hrTimeOffFrequencyTypeImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrTimeOffFrequencyTypeImpl.setDescription(description);
		}

		hrTimeOffFrequencyTypeImpl.resetOriginalValues();

		return hrTimeOffFrequencyTypeImpl;
	}

	public long hrTimeOffFrequencyTypeId;
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