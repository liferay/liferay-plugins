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

import com.liferay.hr.model.HRAssetCheckout;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRAssetCheckout in entity cache.
 *
 * @author Wesley Gong
 * @see HRAssetCheckout
 * @generated
 */
public class HRAssetCheckoutCacheModel implements CacheModel<HRAssetCheckout> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{hrAssetCheckoutId=");
		sb.append(hrAssetCheckoutId);
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
		sb.append(", hrAssetId=");
		sb.append(hrAssetId);
		sb.append(", hrUserId=");
		sb.append(hrUserId);
		sb.append(", checkoutDate=");
		sb.append(checkoutDate);
		sb.append(", expectedCheckInDate=");
		sb.append(expectedCheckInDate);
		sb.append(", actualCheckInDate=");
		sb.append(actualCheckInDate);
		sb.append("}");

		return sb.toString();
	}

	public HRAssetCheckout toEntityModel() {
		HRAssetCheckoutImpl hrAssetCheckoutImpl = new HRAssetCheckoutImpl();

		hrAssetCheckoutImpl.setHrAssetCheckoutId(hrAssetCheckoutId);
		hrAssetCheckoutImpl.setGroupId(groupId);
		hrAssetCheckoutImpl.setCompanyId(companyId);
		hrAssetCheckoutImpl.setUserId(userId);

		if (userName == null) {
			hrAssetCheckoutImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrAssetCheckoutImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrAssetCheckoutImpl.setCreateDate(null);
		}
		else {
			hrAssetCheckoutImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrAssetCheckoutImpl.setModifiedDate(null);
		}
		else {
			hrAssetCheckoutImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrAssetCheckoutImpl.setHrAssetId(hrAssetId);
		hrAssetCheckoutImpl.setHrUserId(hrUserId);

		if (checkoutDate == Long.MIN_VALUE) {
			hrAssetCheckoutImpl.setCheckoutDate(null);
		}
		else {
			hrAssetCheckoutImpl.setCheckoutDate(new Date(checkoutDate));
		}

		if (expectedCheckInDate == Long.MIN_VALUE) {
			hrAssetCheckoutImpl.setExpectedCheckInDate(null);
		}
		else {
			hrAssetCheckoutImpl.setExpectedCheckInDate(new Date(
					expectedCheckInDate));
		}

		if (actualCheckInDate == Long.MIN_VALUE) {
			hrAssetCheckoutImpl.setActualCheckInDate(null);
		}
		else {
			hrAssetCheckoutImpl.setActualCheckInDate(new Date(actualCheckInDate));
		}

		hrAssetCheckoutImpl.resetOriginalValues();

		return hrAssetCheckoutImpl;
	}

	public long hrAssetCheckoutId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrAssetId;
	public long hrUserId;
	public long checkoutDate;
	public long expectedCheckInDate;
	public long actualCheckInDate;
}