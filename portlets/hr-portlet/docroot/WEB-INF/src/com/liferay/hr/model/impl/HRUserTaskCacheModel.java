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

import com.liferay.hr.model.HRUserTask;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRUserTask in entity cache.
 *
 * @author Wesley Gong
 * @see HRUserTask
 * @generated
 */
public class HRUserTaskCacheModel implements CacheModel<HRUserTask> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{hrUserTaskId=");
		sb.append(hrUserTaskId);
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
		sb.append(", hrBillabilityId=");
		sb.append(hrBillabilityId);
		sb.append(", hrTaskId=");
		sb.append(hrTaskId);
		sb.append(", hrTimesheetId=");
		sb.append(hrTimesheetId);
		sb.append(", hrUserId=");
		sb.append(hrUserId);
		sb.append("}");

		return sb.toString();
	}

	public HRUserTask toEntityModel() {
		HRUserTaskImpl hrUserTaskImpl = new HRUserTaskImpl();

		hrUserTaskImpl.setHrUserTaskId(hrUserTaskId);
		hrUserTaskImpl.setGroupId(groupId);
		hrUserTaskImpl.setCompanyId(companyId);
		hrUserTaskImpl.setUserId(userId);

		if (userName == null) {
			hrUserTaskImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrUserTaskImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrUserTaskImpl.setCreateDate(null);
		}
		else {
			hrUserTaskImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrUserTaskImpl.setModifiedDate(null);
		}
		else {
			hrUserTaskImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrUserTaskImpl.setHrBillabilityId(hrBillabilityId);
		hrUserTaskImpl.setHrTaskId(hrTaskId);
		hrUserTaskImpl.setHrTimesheetId(hrTimesheetId);
		hrUserTaskImpl.setHrUserId(hrUserId);

		hrUserTaskImpl.resetOriginalValues();

		return hrUserTaskImpl;
	}

	public long hrUserTaskId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrBillabilityId;
	public long hrTaskId;
	public long hrTimesheetId;
	public long hrUserId;
}