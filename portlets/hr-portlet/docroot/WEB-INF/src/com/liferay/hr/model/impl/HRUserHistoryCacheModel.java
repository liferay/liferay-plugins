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

import com.liferay.hr.model.HRUserHistory;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRUserHistory in entity cache.
 *
 * @author Wesley Gong
 * @see HRUserHistory
 * @generated
 */
public class HRUserHistoryCacheModel implements CacheModel<HRUserHistory> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{hrUserHistoryId=");
		sb.append(hrUserHistoryId);
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
		sb.append(", hrEmploymentTypeId=");
		sb.append(hrEmploymentTypeId);
		sb.append(", hrJobTitleId=");
		sb.append(hrJobTitleId);
		sb.append(", hrOfficeId=");
		sb.append(hrOfficeId);
		sb.append(", hrTerminationTypeId=");
		sb.append(hrTerminationTypeId);
		sb.append(", hrWageTypeId=");
		sb.append(hrWageTypeId);
		sb.append(", hireDate=");
		sb.append(hireDate);
		sb.append(", terminationDate=");
		sb.append(terminationDate);
		sb.append(", wageAmount=");
		sb.append(wageAmount);
		sb.append(", wageCurrencyCode=");
		sb.append(wageCurrencyCode);
		sb.append(", benefitsExempt=");
		sb.append(benefitsExempt);
		sb.append(", overtimeExempt=");
		sb.append(overtimeExempt);
		sb.append("}");

		return sb.toString();
	}

	public HRUserHistory toEntityModel() {
		HRUserHistoryImpl hrUserHistoryImpl = new HRUserHistoryImpl();

		hrUserHistoryImpl.setHrUserHistoryId(hrUserHistoryId);
		hrUserHistoryImpl.setGroupId(groupId);
		hrUserHistoryImpl.setCompanyId(companyId);
		hrUserHistoryImpl.setUserId(userId);

		if (userName == null) {
			hrUserHistoryImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrUserHistoryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrUserHistoryImpl.setCreateDate(null);
		}
		else {
			hrUserHistoryImpl.setCreateDate(new Date(createDate));
		}

		hrUserHistoryImpl.setHrEmploymentTypeId(hrEmploymentTypeId);
		hrUserHistoryImpl.setHrJobTitleId(hrJobTitleId);
		hrUserHistoryImpl.setHrOfficeId(hrOfficeId);
		hrUserHistoryImpl.setHrTerminationTypeId(hrTerminationTypeId);
		hrUserHistoryImpl.setHrWageTypeId(hrWageTypeId);

		if (hireDate == Long.MIN_VALUE) {
			hrUserHistoryImpl.setHireDate(null);
		}
		else {
			hrUserHistoryImpl.setHireDate(new Date(hireDate));
		}

		if (terminationDate == Long.MIN_VALUE) {
			hrUserHistoryImpl.setTerminationDate(null);
		}
		else {
			hrUserHistoryImpl.setTerminationDate(new Date(terminationDate));
		}

		hrUserHistoryImpl.setWageAmount(wageAmount);

		if (wageCurrencyCode == null) {
			hrUserHistoryImpl.setWageCurrencyCode(StringPool.BLANK);
		}
		else {
			hrUserHistoryImpl.setWageCurrencyCode(wageCurrencyCode);
		}

		hrUserHistoryImpl.setBenefitsExempt(benefitsExempt);
		hrUserHistoryImpl.setOvertimeExempt(overtimeExempt);

		hrUserHistoryImpl.resetOriginalValues();

		return hrUserHistoryImpl;
	}

	public long hrUserHistoryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long hrEmploymentTypeId;
	public long hrJobTitleId;
	public long hrOfficeId;
	public long hrTerminationTypeId;
	public long hrWageTypeId;
	public long hireDate;
	public long terminationDate;
	public double wageAmount;
	public String wageCurrencyCode;
	public boolean benefitsExempt;
	public boolean overtimeExempt;
}