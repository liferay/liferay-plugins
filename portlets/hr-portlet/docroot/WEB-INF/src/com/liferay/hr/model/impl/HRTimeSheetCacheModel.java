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

import com.liferay.hr.model.HRTimeSheet;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRTimeSheet in entity cache.
 *
 * @author Wesley Gong
 * @see HRTimeSheet
 * @generated
 */
public class HRTimeSheetCacheModel implements CacheModel<HRTimeSheet> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{hrTimeSheetId=");
		sb.append(hrTimeSheetId);
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
		sb.append(", hrUserId=");
		sb.append(hrUserId);
		sb.append(", startDayOfYear=");
		sb.append(startDayOfYear);
		sb.append(", endDayOfYear=");
		sb.append(endDayOfYear);
		sb.append(", year=");
		sb.append(year);
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

	public HRTimeSheet toEntityModel() {
		HRTimeSheetImpl hrTimeSheetImpl = new HRTimeSheetImpl();

		hrTimeSheetImpl.setHrTimeSheetId(hrTimeSheetId);
		hrTimeSheetImpl.setGroupId(groupId);
		hrTimeSheetImpl.setCompanyId(companyId);
		hrTimeSheetImpl.setUserId(userId);

		if (userName == null) {
			hrTimeSheetImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrTimeSheetImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrTimeSheetImpl.setCreateDate(null);
		}
		else {
			hrTimeSheetImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrTimeSheetImpl.setModifiedDate(null);
		}
		else {
			hrTimeSheetImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrTimeSheetImpl.setHrUserId(hrUserId);
		hrTimeSheetImpl.setStartDayOfYear(startDayOfYear);
		hrTimeSheetImpl.setEndDayOfYear(endDayOfYear);
		hrTimeSheetImpl.setYear(year);
		hrTimeSheetImpl.setStatus(status);
		hrTimeSheetImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			hrTimeSheetImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			hrTimeSheetImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			hrTimeSheetImpl.setStatusDate(null);
		}
		else {
			hrTimeSheetImpl.setStatusDate(new Date(statusDate));
		}

		hrTimeSheetImpl.resetOriginalValues();

		return hrTimeSheetImpl;
	}

	public long hrTimeSheetId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrUserId;
	public int startDayOfYear;
	public int endDayOfYear;
	public int year;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}