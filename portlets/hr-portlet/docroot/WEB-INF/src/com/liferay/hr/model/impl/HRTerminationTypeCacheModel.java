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

import com.liferay.hr.model.HRTerminationType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing HRTerminationType in entity cache.
 *
 * @author Wesley Gong
 * @see HRTerminationType
 * @generated
 */
public class HRTerminationTypeCacheModel implements CacheModel<HRTerminationType> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{hrTerminationTypeId=");
		sb.append(hrTerminationTypeId);
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

	public HRTerminationType toEntityModel() {
		HRTerminationTypeImpl hrTerminationTypeImpl = new HRTerminationTypeImpl();

		hrTerminationTypeImpl.setHrTerminationTypeId(hrTerminationTypeId);
		hrTerminationTypeImpl.setGroupId(groupId);
		hrTerminationTypeImpl.setCompanyId(companyId);
		hrTerminationTypeImpl.setUserId(userId);

		if (userName == null) {
			hrTerminationTypeImpl.setUserName(StringPool.BLANK);
		}
		else {
			hrTerminationTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			hrTerminationTypeImpl.setCreateDate(null);
		}
		else {
			hrTerminationTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			hrTerminationTypeImpl.setModifiedDate(null);
		}
		else {
			hrTerminationTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (code == null) {
			hrTerminationTypeImpl.setCode(StringPool.BLANK);
		}
		else {
			hrTerminationTypeImpl.setCode(code);
		}

		if (name == null) {
			hrTerminationTypeImpl.setName(StringPool.BLANK);
		}
		else {
			hrTerminationTypeImpl.setName(name);
		}

		if (description == null) {
			hrTerminationTypeImpl.setDescription(StringPool.BLANK);
		}
		else {
			hrTerminationTypeImpl.setDescription(description);
		}

		hrTerminationTypeImpl.resetOriginalValues();

		return hrTerminationTypeImpl;
	}

	public long hrTerminationTypeId;
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