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

import com.liferay.hr.model.HRHoliday;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRHoliday in entity cache.
 *
 * @author Wesley Gong
 * @see HRHoliday
 * @generated
 */
public class HRHolidayCacheModel implements CacheModel<HRHoliday> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{hrHolidayId=");
		sb.append(hrHolidayId);
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
		sb.append(", dayOfYear=");
		sb.append(dayOfYear);
		sb.append(", year=");
		sb.append(year);
		sb.append(", paid=");
		sb.append(paid);
		sb.append("}");

		return sb.toString();
	}

	public HRHoliday toEntityModel() {
		HRHolidayImpl hrHolidayImpl = new HRHolidayImpl();

		hrHolidayImpl.setHrHolidayId(hrHolidayId);
		hrHolidayImpl.setGroupId(groupId);
		hrHolidayImpl.setCompanyId(companyId);
		hrHolidayImpl.setUserId(userId);

		if (userName == null) {
			hrHolidayImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrHolidayImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrHolidayImpl.setCreateDate(null);
		}
		else {
			hrHolidayImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrHolidayImpl.setModifiedDate(null);
		}
		else {
			hrHolidayImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			hrHolidayImpl.setName(StringPool.BLANK);
		}
		else {
			hrHolidayImpl.setName(name);
		}

		if (description == null) {
			hrHolidayImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrHolidayImpl.setDescription(description);
		}

		hrHolidayImpl.setDayOfYear(dayOfYear);
		hrHolidayImpl.setYear(year);
		hrHolidayImpl.setPaid(paid);

		hrHolidayImpl.resetOriginalValues();

		return hrHolidayImpl;
	}

	public long hrHolidayId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public int dayOfYear;
	public int year;
	public boolean paid;
}