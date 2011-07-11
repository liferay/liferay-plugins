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

import com.liferay.hr.model.HROffice;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HROffice in entity cache.
 *
 * @author Wesley Gong
 * @see HROffice
 * @generated
 */
public class HROfficeCacheModel implements CacheModel<HROffice> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{hrOfficeId=");
		sb.append(hrOfficeId);
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

	public HROffice toEntityModel() {
		HROfficeImpl hrOfficeImpl = new HROfficeImpl();

		hrOfficeImpl.setHrOfficeId(hrOfficeId);
		hrOfficeImpl.setGroupId(groupId);
		hrOfficeImpl.setCompanyId(companyId);
		hrOfficeImpl.setUserId(userId);

		if (userName == null) {
			hrOfficeImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrOfficeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrOfficeImpl.setCreateDate(null);
		}
		else {
			hrOfficeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrOfficeImpl.setModifiedDate(null);
		}
		else {
			hrOfficeImpl.setModifiedDate(new Date(modifiedDate));
		}

		hrOfficeImpl.setOrganizationId(organizationId);

		hrOfficeImpl.resetOriginalValues();

		return hrOfficeImpl;
	}

	public long hrOfficeId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long organizationId;
}