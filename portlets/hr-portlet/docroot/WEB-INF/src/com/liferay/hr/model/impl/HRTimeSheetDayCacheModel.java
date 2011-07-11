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

import com.liferay.hr.model.HRTimeSheetDay;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRTimeSheetDay in entity cache.
 *
 * @author Wesley Gong
 * @see HRTimeSheetDay
 * @generated
 */
public class HRTimeSheetDayCacheModel implements CacheModel<HRTimeSheetDay> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{hrTimeSheetDayId=");
		sb.append(hrTimeSheetDayId);
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
		sb.append(", hrTimeSheetId=");
		sb.append(hrTimeSheetId);
		sb.append(", hrUserId=");
		sb.append(hrUserId);
		sb.append(", dayOfYear=");
		sb.append(dayOfYear);
		sb.append(", year=");
		sb.append(year);
		sb.append(", hours=");
		sb.append(hours);
		sb.append("}");

		return sb.toString();
	}

	public HRTimeSheetDay toEntityModel() {
		HRTimeSheetDayImpl hrTimeSheetDayImpl = new HRTimeSheetDayImpl();

		hrTimeSheetDayImpl.setHrTimeSheetDayId(hrTimeSheetDayId);
		hrTimeSheetDayImpl.setGroupId(groupId);
		hrTimeSheetDayImpl.setCompanyId(companyId);
		hrTimeSheetDayImpl.setUserId(userId);

		if (userName == null) {
			hrTimeSheetDayImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrTimeSheetDayImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrTimeSheetDayImpl.setCreateDate(null);
		}
		else {
			hrTimeSheetDayImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrTimeSheetDayImpl.setModifiedDate(null);
		}
		else {
			hrTimeSheetDayImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrTimeSheetDayImpl.setHrTimeSheetId(hrTimeSheetId);
		hrTimeSheetDayImpl.setHrUserId(hrUserId);
		hrTimeSheetDayImpl.setDayOfYear(dayOfYear);
		hrTimeSheetDayImpl.setYear(year);
		hrTimeSheetDayImpl.setHours(hours);

		hrTimeSheetDayImpl.resetOriginalValues();

		return hrTimeSheetDayImpl;
	}

	public long hrTimeSheetDayId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrTimeSheetId;
	public long hrUserId;
	public int dayOfYear;
	public int year;
	public double hours;
}