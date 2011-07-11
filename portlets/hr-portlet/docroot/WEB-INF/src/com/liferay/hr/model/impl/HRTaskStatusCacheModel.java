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

import com.liferay.hr.model.HRTaskStatus;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRTaskStatus in entity cache.
 *
 * @author Wesley Gong
 * @see HRTaskStatus
 * @generated
 */
public class HRTaskStatusCacheModel implements CacheModel<HRTaskStatus> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{hrTaskStatusId=");
		sb.append(hrTaskStatusId);
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

	public HRTaskStatus toEntityModel() {
		HRTaskStatusImpl hrTaskStatusImpl = new HRTaskStatusImpl();

		hrTaskStatusImpl.setHrTaskStatusId(hrTaskStatusId);
		hrTaskStatusImpl.setGroupId(groupId);
		hrTaskStatusImpl.setCompanyId(companyId);
		hrTaskStatusImpl.setUserId(userId);

		if (userName == null) {
			hrTaskStatusImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrTaskStatusImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrTaskStatusImpl.setCreateDate(null);
		}
		else {
			hrTaskStatusImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrTaskStatusImpl.setModifiedDate(null);
		}
		else {
			hrTaskStatusImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (code == null) {
			hrTaskStatusImpl.setCode(StringPool.BLANK);
		}
		else {
			hrTaskStatusImpl.setCode(code);
		}

		if (name == null) {
			hrTaskStatusImpl.setName(StringPool.BLANK);
		}
		else {
			hrTaskStatusImpl.setName(name);
		}

		if (description == null) {
			hrTaskStatusImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrTaskStatusImpl.setDescription(description);
		}

		hrTaskStatusImpl.resetOriginalValues();

		return hrTaskStatusImpl;
	}

	public long hrTaskStatusId;
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