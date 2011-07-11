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

import com.liferay.hr.model.HRTimeSheetHoursPerDay;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRTimeSheetHoursPerDay in entity cache.
 *
 * @author Wesley Gong
 * @see HRTimeSheetHoursPerDay
 * @generated
 */
public class HRTimeSheetHoursPerDayCacheModel implements CacheModel<HRTimeSheetHoursPerDay> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{hrTimeSheetHoursPerDayId=");
		sb.append(hrTimeSheetHoursPerDayId);
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
		sb.append(", hrOfficeId=");
		sb.append(hrOfficeId);
		sb.append(", hoursPerDay=");
		sb.append(hoursPerDay);
		sb.append("}");

		return sb.toString();
	}

	public HRTimeSheetHoursPerDay toEntityModel() {
		HRTimeSheetHoursPerDayImpl hrTimeSheetHoursPerDayImpl = new HRTimeSheetHoursPerDayImpl();

		hrTimeSheetHoursPerDayImpl.setHrTimeSheetHoursPerDayId(hrTimeSheetHoursPerDayId);
		hrTimeSheetHoursPerDayImpl.setGroupId(groupId);
		hrTimeSheetHoursPerDayImpl.setCompanyId(companyId);
		hrTimeSheetHoursPerDayImpl.setUserId(userId);

		if (userName == null) {
			hrTimeSheetHoursPerDayImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrTimeSheetHoursPerDayImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrTimeSheetHoursPerDayImpl.setCreateDate(null);
		}
		else {
			hrTimeSheetHoursPerDayImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrTimeSheetHoursPerDayImpl.setModifiedDate(null);
		}
		else {
			hrTimeSheetHoursPerDayImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrTimeSheetHoursPerDayImpl.setHrOfficeId(hrOfficeId);
		hrTimeSheetHoursPerDayImpl.setHoursPerDay(hoursPerDay);

		hrTimeSheetHoursPerDayImpl.resetOriginalValues();

		return hrTimeSheetHoursPerDayImpl;
	}

	public long hrTimeSheetHoursPerDayId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrOfficeId;
	public double hoursPerDay;
}