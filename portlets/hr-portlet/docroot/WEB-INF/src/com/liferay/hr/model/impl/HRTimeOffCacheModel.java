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

import com.liferay.hr.model.HRTimeOff;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRTimeOff in entity cache.
 *
 * @author Wesley Gong
 * @see HRTimeOff
 * @generated
 */
public class HRTimeOffCacheModel implements CacheModel<HRTimeOff> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{hrTimeOffId=");
		sb.append(hrTimeOffId);
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
		sb.append(", hrTimeSheetId=");
		sb.append(hrTimeSheetId);
		sb.append(", hrUserId=");
		sb.append(hrUserId);
		sb.append(", startDayOfYear=");
		sb.append(startDayOfYear);
		sb.append(", endDayOfYear=");
		sb.append(endDayOfYear);
		sb.append(", year=");
		sb.append(year);
		sb.append(", totalHours=");
		sb.append(totalHours);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	public HRTimeOff toEntityModel() {
		HRTimeOffImpl hrTimeOffImpl = new HRTimeOffImpl();

		hrTimeOffImpl.setHrTimeOffId(hrTimeOffId);
		hrTimeOffImpl.setGroupId(groupId);
		hrTimeOffImpl.setCompanyId(companyId);
		hrTimeOffImpl.setUserId(userId);

		if (userName == null) {
			hrTimeOffImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrTimeOffImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrTimeOffImpl.setCreateDate(null);
		}
		else {
			hrTimeOffImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrTimeOffImpl.setModifiedDate(null);
		}
		else {
			hrTimeOffImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrTimeOffImpl.setHrTimeOffTypeId(hrTimeOffTypeId);
		hrTimeOffImpl.setHrTimeSheetId(hrTimeSheetId);
		hrTimeOffImpl.setHrUserId(hrUserId);
		hrTimeOffImpl.setStartDayOfYear(startDayOfYear);
		hrTimeOffImpl.setEndDayOfYear(endDayOfYear);
		hrTimeOffImpl.setYear(year);
		hrTimeOffImpl.setTotalHours(totalHours);
		hrTimeOffImpl.setStatus(status);
		hrTimeOffImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			hrTimeOffImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			hrTimeOffImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			hrTimeOffImpl.setStatusDate(null);
		}
		else {
			hrTimeOffImpl.setStatusDate(new Date(statusDate));
		}

		hrTimeOffImpl.resetOriginalValues();

		return hrTimeOffImpl;
	}

	public long hrTimeOffId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrTimeOffTypeId;
	public long hrTimeSheetId;
	public long hrUserId;
	public int startDayOfYear;
	public int endDayOfYear;
	public int year;
	public double totalHours;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}