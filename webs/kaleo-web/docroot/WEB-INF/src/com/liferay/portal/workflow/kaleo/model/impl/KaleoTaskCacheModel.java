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

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing KaleoTask in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTask
 * @generated
 */
public class KaleoTaskCacheModel implements CacheModel<KaleoTask>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{kaleoTaskId=");
		sb.append(kaleoTaskId);
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
		sb.append(", kaleoDefinitionId=");
		sb.append(kaleoDefinitionId);
		sb.append(", kaleoNodeId=");
		sb.append(kaleoNodeId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	public KaleoTask toEntityModel() {
		KaleoTaskImpl kaleoTaskImpl = new KaleoTaskImpl();

		kaleoTaskImpl.setKaleoTaskId(kaleoTaskId);
		kaleoTaskImpl.setGroupId(groupId);
		kaleoTaskImpl.setCompanyId(companyId);
		kaleoTaskImpl.setUserId(userId);

		if (userName == null) {
			kaleoTaskImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoTaskImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoTaskImpl.setCreateDate(null);
		}
		else {
			kaleoTaskImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoTaskImpl.setModifiedDate(null);
		}
		else {
			kaleoTaskImpl.setModifiedDate(new Date(modifiedDate));
		}

		kaleoTaskImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTaskImpl.setKaleoNodeId(kaleoNodeId);

		if (name == null) {
			kaleoTaskImpl.setName(StringPool.BLANK);
		}
		else {
			kaleoTaskImpl.setName(name);
		}

		if (description == null) {
			kaleoTaskImpl.setDescription(StringPool.BLANK);
		}
		else {
			kaleoTaskImpl.setDescription(description);
		}

		kaleoTaskImpl.resetOriginalValues();

		return kaleoTaskImpl;
	}

	public long kaleoTaskId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long kaleoDefinitionId;
	public long kaleoNodeId;
	public String name;
	public String description;
}