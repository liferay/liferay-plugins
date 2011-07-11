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

import com.liferay.hr.model.HRExpenseType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRExpenseType in entity cache.
 *
 * @author Wesley Gong
 * @see HRExpenseType
 * @generated
 */
public class HRExpenseTypeCacheModel implements CacheModel<HRExpenseType> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{hrExpenseTypeId=");
		sb.append(hrExpenseTypeId);
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

	public HRExpenseType toEntityModel() {
		HRExpenseTypeImpl hrExpenseTypeImpl = new HRExpenseTypeImpl();

		hrExpenseTypeImpl.setHrExpenseTypeId(hrExpenseTypeId);
		hrExpenseTypeImpl.setGroupId(groupId);
		hrExpenseTypeImpl.setCompanyId(companyId);
		hrExpenseTypeImpl.setUserId(userId);

		if (userName == null) {
			hrExpenseTypeImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrExpenseTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrExpenseTypeImpl.setCreateDate(null);
		}
		else {
			hrExpenseTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrExpenseTypeImpl.setModifiedDate(null);
		}
		else {
			hrExpenseTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			hrExpenseTypeImpl.setName(StringPool.BLANK);
		}
		else {
			hrExpenseTypeImpl.setName(name);
		}

		if (description == null) {
			hrExpenseTypeImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrExpenseTypeImpl.setDescription(description);
		}

		hrExpenseTypeImpl.resetOriginalValues();

		return hrExpenseTypeImpl;
	}

	public long hrExpenseTypeId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
}