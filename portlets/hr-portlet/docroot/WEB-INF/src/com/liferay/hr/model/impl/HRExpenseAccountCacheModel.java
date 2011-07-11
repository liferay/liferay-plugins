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

import com.liferay.hr.model.HRExpenseAccount;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRExpenseAccount in entity cache.
 *
 * @author Wesley Gong
 * @see HRExpenseAccount
 * @generated
 */
public class HRExpenseAccountCacheModel implements CacheModel<HRExpenseAccount> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{hrExpenseAccountId=");
		sb.append(hrExpenseAccountId);
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

	public HRExpenseAccount toEntityModel() {
		HRExpenseAccountImpl hrExpenseAccountImpl = new HRExpenseAccountImpl();

		hrExpenseAccountImpl.setHrExpenseAccountId(hrExpenseAccountId);
		hrExpenseAccountImpl.setGroupId(groupId);
		hrExpenseAccountImpl.setCompanyId(companyId);
		hrExpenseAccountImpl.setUserId(userId);

		if (userName == null) {
			hrExpenseAccountImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrExpenseAccountImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrExpenseAccountImpl.setCreateDate(null);
		}
		else {
			hrExpenseAccountImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrExpenseAccountImpl.setModifiedDate(null);
		}
		else {
			hrExpenseAccountImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			hrExpenseAccountImpl.setName(StringPool.BLANK);
		}
		else {
			hrExpenseAccountImpl.setName(name);
		}

		if (description == null) {
			hrExpenseAccountImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrExpenseAccountImpl.setDescription(description);
		}

		hrExpenseAccountImpl.resetOriginalValues();

		return hrExpenseAccountImpl;
	}

	public long hrExpenseAccountId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
}