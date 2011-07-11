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

import com.liferay.hr.model.HRProjectBillingRate;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRProjectBillingRate in entity cache.
 *
 * @author Wesley Gong
 * @see HRProjectBillingRate
 * @generated
 */
public class HRProjectBillingRateCacheModel implements CacheModel<HRProjectBillingRate> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{hrProjectBillingRateId=");
		sb.append(hrProjectBillingRateId);
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
		sb.append(", hrProjectId=");
		sb.append(hrProjectId);
		sb.append(", hrProjectRoleId=");
		sb.append(hrProjectRoleId);
		sb.append(", rate=");
		sb.append(rate);
		sb.append("}");

		return sb.toString();
	}

	public HRProjectBillingRate toEntityModel() {
		HRProjectBillingRateImpl hrProjectBillingRateImpl = new HRProjectBillingRateImpl();

		hrProjectBillingRateImpl.setHrProjectBillingRateId(hrProjectBillingRateId);
		hrProjectBillingRateImpl.setGroupId(groupId);
		hrProjectBillingRateImpl.setCompanyId(companyId);
		hrProjectBillingRateImpl.setUserId(userId);

		if (userName == null) {
			hrProjectBillingRateImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrProjectBillingRateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrProjectBillingRateImpl.setCreateDate(null);
		}
		else {
			hrProjectBillingRateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrProjectBillingRateImpl.setModifiedDate(null);
		}
		else {
			hrProjectBillingRateImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrProjectBillingRateImpl.setHrProjectId(hrProjectId);
		hrProjectBillingRateImpl.setHrProjectRoleId(hrProjectRoleId);
		hrProjectBillingRateImpl.setRate(rate);

		hrProjectBillingRateImpl.resetOriginalValues();

		return hrProjectBillingRateImpl;
	}

	public long hrProjectBillingRateId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrProjectId;
	public long hrProjectRoleId;
	public double rate;
}