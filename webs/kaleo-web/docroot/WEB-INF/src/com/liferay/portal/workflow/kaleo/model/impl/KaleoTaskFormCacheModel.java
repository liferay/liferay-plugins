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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskForm;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing KaleoTaskForm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskForm
 * @generated
 */
public class KaleoTaskFormCacheModel implements CacheModel<KaleoTaskForm>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{kaleoTaskFormId=");
		sb.append(kaleoTaskFormId);
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
		sb.append(", kaleoTaskId=");
		sb.append(kaleoTaskId);
		sb.append(", description=");
		sb.append(description);
		sb.append(", formTemplateId=");
		sb.append(formTemplateId);
		sb.append("}");

		return sb.toString();
	}

	public KaleoTaskForm toEntityModel() {
		KaleoTaskFormImpl kaleoTaskFormImpl = new KaleoTaskFormImpl();

		kaleoTaskFormImpl.setKaleoTaskFormId(kaleoTaskFormId);
		kaleoTaskFormImpl.setGroupId(groupId);
		kaleoTaskFormImpl.setCompanyId(companyId);
		kaleoTaskFormImpl.setUserId(userId);

		if (userName == null) {
			kaleoTaskFormImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoTaskFormImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoTaskFormImpl.setCreateDate(null);
		}
		else {
			kaleoTaskFormImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoTaskFormImpl.setModifiedDate(null);
		}
		else {
			kaleoTaskFormImpl.setModifiedDate(new Date(modifiedDate));
		}

		kaleoTaskFormImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTaskFormImpl.setKaleoTaskId(kaleoTaskId);

		if (description == null) {
			kaleoTaskFormImpl.setDescription(StringPool.BLANK);
		}
		else {
			kaleoTaskFormImpl.setDescription(description);
		}

		kaleoTaskFormImpl.setFormTemplateId(formTemplateId);

		kaleoTaskFormImpl.resetOriginalValues();

		return kaleoTaskFormImpl;
	}

	public long kaleoTaskFormId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long kaleoDefinitionId;
	public long kaleoTaskId;
	public String description;
	public long formTemplateId;
}