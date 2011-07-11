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

import com.liferay.hr.model.HRBillability;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRBillability in entity cache.
 *
 * @author Wesley Gong
 * @see HRBillability
 * @generated
 */
public class HRBillabilityCacheModel implements CacheModel<HRBillability> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{hrBillabilityId=");
		sb.append(hrBillabilityId);
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
		sb.append(", code=");
		sb.append(code);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	public HRBillability toEntityModel() {
		HRBillabilityImpl hrBillabilityImpl = new HRBillabilityImpl();

		hrBillabilityImpl.setHrBillabilityId(hrBillabilityId);
		hrBillabilityImpl.setGroupId(groupId);
		hrBillabilityImpl.setCompanyId(companyId);
		hrBillabilityImpl.setUserId(userId);

		if (userName == null) {
			hrBillabilityImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrBillabilityImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrBillabilityImpl.setCreateDate(null);
		}
		else {
			hrBillabilityImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrBillabilityImpl.setModifiedDate(null);
		}
		else {
			hrBillabilityImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (code == null) {
			hrBillabilityImpl.setCode(StringPool.BLANK);
		}
		else {
			hrBillabilityImpl.setCode(code);
		}

		if (name == null) {
			hrBillabilityImpl.setName(StringPool.BLANK);
		}
		else {
			hrBillabilityImpl.setName(name);
		}

		if (description == null) {
			hrBillabilityImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrBillabilityImpl.setDescription(description);
		}

		hrBillabilityImpl.resetOriginalValues();

		return hrBillabilityImpl;
	}

	public long hrBillabilityId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String code;
	public String name;
	public String description;
}