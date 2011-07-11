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

import com.liferay.hr.model.HRExpenseCurrency;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRExpenseCurrency in entity cache.
 *
 * @author Wesley Gong
 * @see HRExpenseCurrency
 * @generated
 */
public class HRExpenseCurrencyCacheModel implements CacheModel<HRExpenseCurrency> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{hrExpenseCurrencyId=");
		sb.append(hrExpenseCurrencyId);
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

	public HRExpenseCurrency toEntityModel() {
		HRExpenseCurrencyImpl hrExpenseCurrencyImpl = new HRExpenseCurrencyImpl();

		hrExpenseCurrencyImpl.setHrExpenseCurrencyId(hrExpenseCurrencyId);
		hrExpenseCurrencyImpl.setGroupId(groupId);
		hrExpenseCurrencyImpl.setCompanyId(companyId);
		hrExpenseCurrencyImpl.setUserId(userId);

		if (userName == null) {
			hrExpenseCurrencyImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrExpenseCurrencyImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrExpenseCurrencyImpl.setCreateDate(null);
		}
		else {
			hrExpenseCurrencyImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrExpenseCurrencyImpl.setModifiedDate(null);
		}
		else {
			hrExpenseCurrencyImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (code == null) {
			hrExpenseCurrencyImpl.setCode(StringPool.BLANK);
		}
		else {
			hrExpenseCurrencyImpl.setCode(code);
		}

		if (name == null) {
			hrExpenseCurrencyImpl.setName(StringPool.BLANK);
		}
		else {
			hrExpenseCurrencyImpl.setName(name);
		}

		if (description == null) {
			hrExpenseCurrencyImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrExpenseCurrencyImpl.setDescription(description);
		}

		hrExpenseCurrencyImpl.resetOriginalValues();

		return hrExpenseCurrencyImpl;
	}

	public long hrExpenseCurrencyId;
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