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

import com.liferay.hr.model.HREmploymentType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HREmploymentType in entity cache.
 *
 * @author Wesley Gong
 * @see HREmploymentType
 * @generated
 */
public class HREmploymentTypeCacheModel implements CacheModel<HREmploymentType> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{hrEmploymentTypeId=");
		sb.append(hrEmploymentTypeId);
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

	public HREmploymentType toEntityModel() {
		HREmploymentTypeImpl hrEmploymentTypeImpl = new HREmploymentTypeImpl();

		hrEmploymentTypeImpl.setHrEmploymentTypeId(hrEmploymentTypeId);
		hrEmploymentTypeImpl.setGroupId(groupId);
		hrEmploymentTypeImpl.setCompanyId(companyId);
		hrEmploymentTypeImpl.setUserId(userId);

		if (userName == null) {
			hrEmploymentTypeImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrEmploymentTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrEmploymentTypeImpl.setCreateDate(null);
		}
		else {
			hrEmploymentTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrEmploymentTypeImpl.setModifiedDate(null);
		}
		else {
			hrEmploymentTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (code == null) {
			hrEmploymentTypeImpl.setCode(StringPool.BLANK);
		}
		else {
			hrEmploymentTypeImpl.setCode(code);
		}

		if (name == null) {
			hrEmploymentTypeImpl.setName(StringPool.BLANK);
		}
		else {
			hrEmploymentTypeImpl.setName(name);
		}

		if (description == null) {
			hrEmploymentTypeImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrEmploymentTypeImpl.setDescription(description);
		}

		hrEmploymentTypeImpl.resetOriginalValues();

		return hrEmploymentTypeImpl;
	}

	public long hrEmploymentTypeId;
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