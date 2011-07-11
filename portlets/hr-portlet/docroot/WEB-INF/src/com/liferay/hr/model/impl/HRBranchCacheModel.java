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

import com.liferay.hr.model.HRBranch;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRBranch in entity cache.
 *
 * @author Wesley Gong
 * @see HRBranch
 * @generated
 */
public class HRBranchCacheModel implements CacheModel<HRBranch> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{hrBranchId=");
		sb.append(hrBranchId);
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
		sb.append(", organizationId=");
		sb.append(organizationId);
		sb.append("}");

		return sb.toString();
	}

	public HRBranch toEntityModel() {
		HRBranchImpl hrBranchImpl = new HRBranchImpl();

		hrBranchImpl.setHrBranchId(hrBranchId);
		hrBranchImpl.setGroupId(groupId);
		hrBranchImpl.setCompanyId(companyId);
		hrBranchImpl.setUserId(userId);

		if (userName == null) {
			hrBranchImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrBranchImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrBranchImpl.setCreateDate(null);
		}
		else {
			hrBranchImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrBranchImpl.setModifiedDate(null);
		}
		else {
			hrBranchImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrBranchImpl.setOrganizationId(organizationId);

		hrBranchImpl.resetOriginalValues();

		return hrBranchImpl;
	}

	public long hrBranchId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long organizationId;
}