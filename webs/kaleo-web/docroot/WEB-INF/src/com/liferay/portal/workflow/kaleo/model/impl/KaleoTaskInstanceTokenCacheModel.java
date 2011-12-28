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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing KaleoTaskInstanceToken in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskInstanceToken
 * @generated
 */
public class KaleoTaskInstanceTokenCacheModel implements CacheModel<KaleoTaskInstanceToken>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{kaleoTaskInstanceTokenId=");
		sb.append(kaleoTaskInstanceTokenId);
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
		sb.append(", kaleoTaskId=");
		sb.append(kaleoTaskId);
		sb.append(", kaleoTaskName=");
		sb.append(kaleoTaskName);
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", completionUserId=");
		sb.append(completionUserId);
		sb.append(", completed=");
		sb.append(completed);
		sb.append(", completionDate=");
		sb.append(completionDate);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append(", workflowContext=");
		sb.append(workflowContext);
		sb.append("}");

		return sb.toString();
	}

	public KaleoTaskInstanceToken toEntityModel() {
		KaleoTaskInstanceTokenImpl kaleoTaskInstanceTokenImpl = new KaleoTaskInstanceTokenImpl();

		kaleoTaskInstanceTokenImpl.setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
		kaleoTaskInstanceTokenImpl.setGroupId(groupId);
		kaleoTaskInstanceTokenImpl.setCompanyId(companyId);
		kaleoTaskInstanceTokenImpl.setUserId(userId);

		if (userName == null) {
			kaleoTaskInstanceTokenImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoTaskInstanceTokenImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoTaskInstanceTokenImpl.setCreateDate(null);
		}
		else {
			kaleoTaskInstanceTokenImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoTaskInstanceTokenImpl.setModifiedDate(null);
		}
		else {
			kaleoTaskInstanceTokenImpl.setModifiedDate(new Date(modifiedDate));
		}

		kaleoTaskInstanceTokenImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTaskInstanceTokenImpl.setKaleoInstanceId(kaleoInstanceId);
		kaleoTaskInstanceTokenImpl.setKaleoInstanceTokenId(kaleoInstanceTokenId);
		kaleoTaskInstanceTokenImpl.setKaleoTaskId(kaleoTaskId);

		if (kaleoTaskName == null) {
			kaleoTaskInstanceTokenImpl.setKaleoTaskName(StringPool.BLANK);
		}
		else {
			kaleoTaskInstanceTokenImpl.setKaleoTaskName(kaleoTaskName);
		}

		if (className == null) {
			kaleoTaskInstanceTokenImpl.setClassName(StringPool.BLANK);
		}
		else {
			kaleoTaskInstanceTokenImpl.setClassName(className);
		}

		kaleoTaskInstanceTokenImpl.setClassPK(classPK);
		kaleoTaskInstanceTokenImpl.setCompletionUserId(completionUserId);
		kaleoTaskInstanceTokenImpl.setCompleted(completed);

		if (completionDate == Long.MIN_VALUE) {
			kaleoTaskInstanceTokenImpl.setCompletionDate(null);
		}
		else {
			kaleoTaskInstanceTokenImpl.setCompletionDate(new Date(
					completionDate));
		}

		if (dueDate == Long.MIN_VALUE) {
			kaleoTaskInstanceTokenImpl.setDueDate(null);
		}
		else {
			kaleoTaskInstanceTokenImpl.setDueDate(new Date(dueDate));
		}

		if (workflowContext == null) {
			kaleoTaskInstanceTokenImpl.setWorkflowContext(StringPool.BLANK);
		}
		else {
			kaleoTaskInstanceTokenImpl.setWorkflowContext(workflowContext);
		}

		kaleoTaskInstanceTokenImpl.resetOriginalValues();

		return kaleoTaskInstanceTokenImpl;
	}

	public long kaleoTaskInstanceTokenId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long kaleoDefinitionId;
	public long kaleoInstanceId;
	public long kaleoInstanceTokenId;
	public long kaleoTaskId;
	public String kaleoTaskName;
	public String className;
	public long classPK;
	public long completionUserId;
	public boolean completed;
	public long completionDate;
	public long dueDate;
	public String workflowContext;
}