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

import com.liferay.hr.model.HRUserProject;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRUserProject in entity cache.
 *
 * @author Wesley Gong
 * @see HRUserProject
 * @generated
 */
public class HRUserProjectCacheModel implements CacheModel<HRUserProject> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{hrUserProjectId=");
		sb.append(hrUserProjectId);
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
		sb.append(", hrProjectBillingRateId=");
		sb.append(hrProjectBillingRateId);
		sb.append(", hrProjectId=");
		sb.append(hrProjectId);
		sb.append(", hrProjectRoleId=");
		sb.append(hrProjectRoleId);
		sb.append(", hrUserId=");
		sb.append(hrUserId);
		sb.append(", actualRate=");
		sb.append(actualRate);
		sb.append("}");

		return sb.toString();
	}

	public HRUserProject toEntityModel() {
		HRUserProjectImpl hrUserProjectImpl = new HRUserProjectImpl();

		hrUserProjectImpl.setHrUserProjectId(hrUserProjectId);
		hrUserProjectImpl.setGroupId(groupId);
		hrUserProjectImpl.setCompanyId(companyId);
		hrUserProjectImpl.setUserId(userId);

		if (userName == null) {
			hrUserProjectImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrUserProjectImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrUserProjectImpl.setCreateDate(null);
		}
		else {
			hrUserProjectImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrUserProjectImpl.setModifiedDate(null);
		}
		else {
			hrUserProjectImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrUserProjectImpl.setHrProjectBillingRateId(hrProjectBillingRateId);
		hrUserProjectImpl.setHrProjectId(hrProjectId);
		hrUserProjectImpl.setHrProjectRoleId(hrProjectRoleId);
		hrUserProjectImpl.setHrUserId(hrUserId);
		hrUserProjectImpl.setActualRate(actualRate);

		hrUserProjectImpl.resetOriginalValues();

		return hrUserProjectImpl;
	}

	public long hrUserProjectId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long hrProjectBillingRateId;
	public long hrProjectId;
	public long hrProjectRoleId;
	public long hrUserId;
	public double actualRate;
}