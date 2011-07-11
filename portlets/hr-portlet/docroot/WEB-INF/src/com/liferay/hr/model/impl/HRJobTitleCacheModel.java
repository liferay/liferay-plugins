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

import com.liferay.hr.model.HRJobTitle;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRJobTitle in entity cache.
 *
 * @author Wesley Gong
 * @see HRJobTitle
 * @generated
 */
public class HRJobTitleCacheModel implements CacheModel<HRJobTitle> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{hrJobTitleId=");
		sb.append(hrJobTitleId);
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
		sb.append("}");

		return sb.toString();
	}

	public HRJobTitle toEntityModel() {
		HRJobTitleImpl hrJobTitleImpl = new HRJobTitleImpl();

		hrJobTitleImpl.setHrJobTitleId(hrJobTitleId);
		hrJobTitleImpl.setGroupId(groupId);
		hrJobTitleImpl.setCompanyId(companyId);
		hrJobTitleImpl.setUserId(userId);

		if (userName == null) {
			hrJobTitleImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrJobTitleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrJobTitleImpl.setCreateDate(null);
		}
		else {
			hrJobTitleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrJobTitleImpl.setModifiedDate(null);
		}
		else {
			hrJobTitleImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			hrJobTitleImpl.setName(StringPool.BLANK);
		}
		else {
			hrJobTitleImpl.setName(name);
		}

		if (description == null) {
			hrJobTitleImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrJobTitleImpl.setDescription(description);
		}

		hrJobTitleImpl.resetOriginalValues();

		return hrJobTitleImpl;
	}

	public long hrJobTitleId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
}