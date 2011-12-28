/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing KaleoTaskAssignmentInstance in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentInstance
 * @generated
 */
public class KaleoTaskAssignmentInstanceCacheModel implements CacheModel<KaleoTaskAssignmentInstance>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{kaleoTaskAssignmentInstanceId=");
		sb.append(kaleoTaskAssignmentInstanceId);
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
		sb.append(", kaleoInstanceId=");
		sb.append(kaleoInstanceId);
		sb.append(", kaleoInstanceTokenId=");
		sb.append(kaleoInstanceTokenId);
		sb.append(", kaleoTaskInstanceTokenId=");
		sb.append(kaleoTaskInstanceTokenId);
		sb.append(", kaleoTaskId=");
		sb.append(kaleoTaskId);
		sb.append(", kaleoTaskName=");
		sb.append(kaleoTaskName);
		sb.append(", assigneeClassName=");
		sb.append(assigneeClassName);
		sb.append(", assigneeClassPK=");
		sb.append(assigneeClassPK);
		sb.append(", completed=");
		sb.append(completed);
		sb.append(", completionDate=");
		sb.append(completionDate);
		sb.append("}");

		return sb.toString();
	}

	public KaleoTaskAssignmentInstance toEntityModel() {
		KaleoTaskAssignmentInstanceImpl kaleoTaskAssignmentInstanceImpl = new KaleoTaskAssignmentInstanceImpl();

		kaleoTaskAssignmentInstanceImpl.setKaleoTaskAssignmentInstanceId(kaleoTaskAssignmentInstanceId);
		kaleoTaskAssignmentInstanceImpl.setGroupId(groupId);
		kaleoTaskAssignmentInstanceImpl.setCompanyId(companyId);
		kaleoTaskAssignmentInstanceImpl.setUserId(userId);

		if (userName == null) {
			kaleoTaskAssignmentInstanceImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoTaskAssignmentInstanceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoTaskAssignmentInstanceImpl.setCreateDate(null);
		}
		else {
			kaleoTaskAssignmentInstanceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoTaskAssignmentInstanceImpl.setModifiedDate(null);
		}
		else {
			kaleoTaskAssignmentInstanceImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		kaleoTaskAssignmentInstanceImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTaskAssignmentInstanceImpl.setKaleoInstanceId(kaleoInstanceId);
		kaleoTaskAssignmentInstanceImpl.setKaleoInstanceTokenId(kaleoInstanceTokenId);
		kaleoTaskAssignmentInstanceImpl.setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
		kaleoTaskAssignmentInstanceImpl.setKaleoTaskId(kaleoTaskId);

		if (kaleoTaskName == null) {
			kaleoTaskAssignmentInstanceImpl.setKaleoTaskName(StringPool.BLANK);
		}
		else {
			kaleoTaskAssignmentInstanceImpl.setKaleoTaskName(kaleoTaskName);
		}

		if (assigneeClassName == null) {
			kaleoTaskAssignmentInstanceImpl.setAssigneeClassName(StringPool.BLANK);
		}
		else {
			kaleoTaskAssignmentInstanceImpl.setAssigneeClassName(assigneeClassName);
		}

		kaleoTaskAssignmentInstanceImpl.setAssigneeClassPK(assigneeClassPK);
		kaleoTaskAssignmentInstanceImpl.setCompleted(completed);

		if (completionDate == Long.MIN_VALUE) {
			kaleoTaskAssignmentInstanceImpl.setCompletionDate(null);
		}
		else {
			kaleoTaskAssignmentInstanceImpl.setCompletionDate(new Date(
					completionDate));
		}

		kaleoTaskAssignmentInstanceImpl.resetOriginalValues();

		return kaleoTaskAssignmentInstanceImpl;
	}

	public long kaleoTaskAssignmentInstanceId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long kaleoDefinitionId;
	public long kaleoInstanceId;
	public long kaleoInstanceTokenId;
	public long kaleoTaskInstanceTokenId;
	public long kaleoTaskId;
	public String kaleoTaskName;
	public String assigneeClassName;
	public long assigneeClassPK;
	public boolean completed;
	public long completionDate;
}