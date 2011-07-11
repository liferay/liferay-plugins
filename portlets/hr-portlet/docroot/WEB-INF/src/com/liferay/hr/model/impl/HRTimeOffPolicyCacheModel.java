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

import com.liferay.hr.model.HRTimeOffPolicy;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRTimeOffPolicy in entity cache.
 *
 * @author Wesley Gong
 * @see HRTimeOffPolicy
 * @generated
 */
public class HRTimeOffPolicyCacheModel implements CacheModel<HRTimeOffPolicy> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{hrTimeOffPolicyId=");
		sb.append(hrTimeOffPolicyId);
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
		sb.append(", hrTimeOffTypeId=");
		sb.append(hrTimeOffTypeId);
		sb.append(", hrUserId=");
		sb.append(hrUserId);
		sb.append(", accrueHRTimeOffFrequencyTypeId=");
		sb.append(accrueHRTimeOffFrequencyTypeId);
		sb.append(", resetHRTimeOffFrequencyTypeId=");
		sb.append(resetHRTimeOffFrequencyTypeId);
		sb.append(", effectiveDate=");
		sb.append(effectiveDate);
		sb.append(", inactive=");
		sb.append(inactive);
		sb.append(", hoursAllowed=");
		sb.append(hoursAllowed);
		sb.append(", hoursBaseAmount=");
		sb.append(hoursBaseAmount);
		sb.append(", hoursToAccrue=");
		sb.append(hoursToAccrue);
		sb.append(", carryOverHoursAllowed=");
		sb.append(carryOverHoursAllowed);
		sb.append("}");

		return sb.toString();
	}

	public HRTimeOffPolicy toEntityModel() {
		HRTimeOffPolicyImpl hrTimeOffPolicyImpl = new HRTimeOffPolicyImpl();

		hrTimeOffPolicyImpl.setHrTimeOffPolicyId(hrTimeOffPolicyId);
		hrTimeOffPolicyImpl.setGroupId(groupId);
		hrTimeOffPolicyImpl.setCompanyId(companyId);
		hrTimeOffPolicyImpl.setUserId(userId);

		if (userName == null) {
			hrTimeOffPolicyImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrTimeOffPolicyImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrTimeOffPolicyImpl.setCreateDate(null);
		}
		else {
			hrTimeOffPolicyImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrTimeOffPolicyImpl.setModifiedDate(null);
		}
		else {
			hrTimeOffPolicyImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrTimeOffPolicyImpl.setHrTimeOffTypeId(hrTimeOffTypeId);
		hrTimeOffPolicyImpl.setHrUserId(hrUserId);
		hrTimeOffPolicyImpl.setAccrueHRTimeOffFrequencyTypeId(accrueHRTimeOffFrequencyTypeId);
		hrTimeOffPolicyImpl.setResetHRTimeOffFrequencyTypeId(resetHRTimeOffFrequencyTypeId);

		if (effectiveDate == Long.MIN_VALUE) {
			hrTimeOffPolicyImpl.setEffectiveDate(null);
		}
		else {
			hrTimeOffPolicyImpl.setEffectiveDate(new Date(effectiveDate));
		}

		hrTimeOffPolicyImpl.setInactive(inactive);
		hrTimeOffPolicyImpl.setHoursAllowed(hoursAllowed);
		hrTimeOffPolicyImpl.setHoursBaseAmount(hoursBaseAmount);
		hrTimeOffPolicyImpl.setHoursToAccrue(hoursToAccrue);
		hrTimeOffPolicyImpl.setCarryOverHoursAllowed(carryOverHoursAllowed);

		hrTimeOffPolicyImpl.resetOriginalValues();

		return hrTimeOffPolicyImpl;
	}

	public long hrTimeOffPolicyId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrTimeOffTypeId;
	public long hrUserId;
	public long accrueHRTimeOffFrequencyTypeId;
	public long resetHRTimeOffFrequencyTypeId;
	public long effectiveDate;
	public boolean inactive;
	public double hoursAllowed;
	public double hoursBaseAmount;
	public double hoursToAccrue;
	public double carryOverHoursAllowed;
}