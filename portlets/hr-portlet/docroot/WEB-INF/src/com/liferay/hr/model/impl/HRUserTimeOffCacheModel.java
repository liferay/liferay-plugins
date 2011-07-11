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

import com.liferay.hr.model.HRUserTimeOff;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRUserTimeOff in entity cache.
 *
 * @author Wesley Gong
 * @see HRUserTimeOff
 * @generated
 */
public class HRUserTimeOffCacheModel implements CacheModel<HRUserTimeOff> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{hrUserTimeOffId=");
		sb.append(hrUserTimeOffId);
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
		sb.append(", year=");
		sb.append(year);
		sb.append(", hoursAllowed=");
		sb.append(hoursAllowed);
		sb.append(", hoursAccrued=");
		sb.append(hoursAccrued);
		sb.append(", hoursCarriedOver=");
		sb.append(hoursCarriedOver);
		sb.append(", hoursUsed=");
		sb.append(hoursUsed);
		sb.append("}");

		return sb.toString();
	}

	public HRUserTimeOff toEntityModel() {
		HRUserTimeOffImpl hrUserTimeOffImpl = new HRUserTimeOffImpl();

		hrUserTimeOffImpl.setHrUserTimeOffId(hrUserTimeOffId);
		hrUserTimeOffImpl.setGroupId(groupId);
		hrUserTimeOffImpl.setCompanyId(companyId);
		hrUserTimeOffImpl.setUserId(userId);

		if (userName == null) {
			hrUserTimeOffImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrUserTimeOffImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrUserTimeOffImpl.setCreateDate(null);
		}
		else {
			hrUserTimeOffImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrUserTimeOffImpl.setModifiedDate(null);
		}
		else {
			hrUserTimeOffImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrUserTimeOffImpl.setHrTimeOffTypeId(hrTimeOffTypeId);
		hrUserTimeOffImpl.setHrUserId(hrUserId);
		hrUserTimeOffImpl.setYear(year);
		hrUserTimeOffImpl.setHoursAllowed(hoursAllowed);
		hrUserTimeOffImpl.setHoursAccrued(hoursAccrued);
		hrUserTimeOffImpl.setHoursCarriedOver(hoursCarriedOver);
		hrUserTimeOffImpl.setHoursUsed(hoursUsed);

		hrUserTimeOffImpl.resetOriginalValues();

		return hrUserTimeOffImpl;
	}

	public long hrUserTimeOffId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrTimeOffTypeId;
	public long hrUserId;
	public int year;
	public double hoursAllowed;
	public double hoursAccrued;
	public double hoursCarriedOver;
	public double hoursUsed;
}