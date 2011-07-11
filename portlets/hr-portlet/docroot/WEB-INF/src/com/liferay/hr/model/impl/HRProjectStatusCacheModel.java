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

import com.liferay.hr.model.HRProjectStatus;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRProjectStatus in entity cache.
 *
 * @author Wesley Gong
 * @see HRProjectStatus
 * @generated
 */
public class HRProjectStatusCacheModel implements CacheModel<HRProjectStatus> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{hrProjectStatusId=");
		sb.append(hrProjectStatusId);
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

	public HRProjectStatus toEntityModel() {
		HRProjectStatusImpl hrProjectStatusImpl = new HRProjectStatusImpl();

		hrProjectStatusImpl.setHrProjectStatusId(hrProjectStatusId);
		hrProjectStatusImpl.setGroupId(groupId);
		hrProjectStatusImpl.setCompanyId(companyId);
		hrProjectStatusImpl.setUserId(userId);

		if (userName == null) {
			hrProjectStatusImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrProjectStatusImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrProjectStatusImpl.setCreateDate(null);
		}
		else {
			hrProjectStatusImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrProjectStatusImpl.setModifiedDate(null);
		}
		else {
			hrProjectStatusImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (code == null) {
			hrProjectStatusImpl.setCode(StringPool.BLANK);
		}
		else {
			hrProjectStatusImpl.setCode(code);
		}

		if (name == null) {
			hrProjectStatusImpl.setName(StringPool.BLANK);
		}
		else {
			hrProjectStatusImpl.setName(name);
		}

		if (description == null) {
			hrProjectStatusImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrProjectStatusImpl.setDescription(description);
		}

		hrProjectStatusImpl.resetOriginalValues();

		return hrProjectStatusImpl;
	}

	public long hrProjectStatusId;
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