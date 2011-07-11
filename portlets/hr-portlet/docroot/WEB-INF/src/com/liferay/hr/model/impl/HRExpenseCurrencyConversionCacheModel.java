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

import com.liferay.hr.model.HRExpenseCurrencyConversion;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRExpenseCurrencyConversion in entity cache.
 *
 * @author Wesley Gong
 * @see HRExpenseCurrencyConversion
 * @generated
 */
public class HRExpenseCurrencyConversionCacheModel implements CacheModel<HRExpenseCurrencyConversion> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{hrExpenseCurrencyConversionId=");
		sb.append(hrExpenseCurrencyConversionId);
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
		sb.append(", fromHRExpenseCurrencyId=");
		sb.append(fromHRExpenseCurrencyId);
		sb.append(", toHRExpenseCurrencyId=");
		sb.append(toHRExpenseCurrencyId);
		sb.append(", conversionDate=");
		sb.append(conversionDate);
		sb.append(", conversionValue=");
		sb.append(conversionValue);
		sb.append("}");

		return sb.toString();
	}

	public HRExpenseCurrencyConversion toEntityModel() {
		HRExpenseCurrencyConversionImpl hrExpenseCurrencyConversionImpl = new HRExpenseCurrencyConversionImpl();

		hrExpenseCurrencyConversionImpl.setHrExpenseCurrencyConversionId(hrExpenseCurrencyConversionId);
		hrExpenseCurrencyConversionImpl.setGroupId(groupId);
		hrExpenseCurrencyConversionImpl.setCompanyId(companyId);
		hrExpenseCurrencyConversionImpl.setUserId(userId);

		if (userName == null) {
			hrExpenseCurrencyConversionImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrExpenseCurrencyConversionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrExpenseCurrencyConversionImpl.setCreateDate(null);
		}
		else {
			hrExpenseCurrencyConversionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrExpenseCurrencyConversionImpl.setModifiedDate(null);
		}
		else {
			hrExpenseCurrencyConversionImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		hrExpenseCurrencyConversionImpl.setFromHRExpenseCurrencyId(fromHRExpenseCurrencyId);
		hrExpenseCurrencyConversionImpl.setToHRExpenseCurrencyId(toHRExpenseCurrencyId);

		if (conversionDate == Long.MIN_VALUE) {
			hrExpenseCurrencyConversionImpl.setConversionDate(null);
		}
		else {
			hrExpenseCurrencyConversionImpl.setConversionDate(new Date(
					conversionDate));
		}

		hrExpenseCurrencyConversionImpl.setConversionValue(conversionValue);

		hrExpenseCurrencyConversionImpl.resetOriginalValues();

		return hrExpenseCurrencyConversionImpl;
	}

	public long hrExpenseCurrencyConversionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long fromHRExpenseCurrencyId;
	public long toHRExpenseCurrencyId;
	public long conversionDate;
	public double conversionValue;
}