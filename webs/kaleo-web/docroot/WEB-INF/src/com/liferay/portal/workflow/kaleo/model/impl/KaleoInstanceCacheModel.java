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
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing KaleoInstance in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstance
 * @generated
 */
public class KaleoInstanceCacheModel implements CacheModel<KaleoInstance>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{kaleoInstanceId=");
		sb.append(kaleoInstanceId);
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
		sb.append(", kaleoDefinitionName=");
		sb.append(kaleoDefinitionName);
		sb.append(", kaleoDefinitionVersion=");
		sb.append(kaleoDefinitionVersion);
		sb.append(", rootKaleoInstanceTokenId=");
		sb.append(rootKaleoInstanceTokenId);
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", completed=");
		sb.append(completed);
		sb.append(", completionDate=");
		sb.append(completionDate);
		sb.append(", workflowContext=");
		sb.append(workflowContext);
		sb.append("}");

		return sb.toString();
	}

	public KaleoInstance toEntityModel() {
		KaleoInstanceImpl kaleoInstanceImpl = new KaleoInstanceImpl();

		kaleoInstanceImpl.setKaleoInstanceId(kaleoInstanceId);
		kaleoInstanceImpl.setGroupId(groupId);
		kaleoInstanceImpl.setCompanyId(companyId);
		kaleoInstanceImpl.setUserId(userId);

		if (userName == null) {
			kaleoInstanceImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoInstanceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoInstanceImpl.setCreateDate(null);
		}
		else {
			kaleoInstanceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoInstanceImpl.setModifiedDate(null);
		}
		else {
			kaleoInstanceImpl.setModifiedDate(new Date(modifiedDate));
		}

		kaleoInstanceImpl.setKaleoDefinitionId(kaleoDefinitionId);

		if (kaleoDefinitionName == null) {
			kaleoInstanceImpl.setKaleoDefinitionName(StringPool.BLANK);
		}
		else {
			kaleoInstanceImpl.setKaleoDefinitionName(kaleoDefinitionName);
		}

		kaleoInstanceImpl.setKaleoDefinitionVersion(kaleoDefinitionVersion);
		kaleoInstanceImpl.setRootKaleoInstanceTokenId(rootKaleoInstanceTokenId);

		if (className == null) {
			kaleoInstanceImpl.setClassName(StringPool.BLANK);
		}
		else {
			kaleoInstanceImpl.setClassName(className);
		}

		kaleoInstanceImpl.setClassPK(classPK);
		kaleoInstanceImpl.setCompleted(completed);

		if (completionDate == Long.MIN_VALUE) {
			kaleoInstanceImpl.setCompletionDate(null);
		}
		else {
			kaleoInstanceImpl.setCompletionDate(new Date(completionDate));
		}

		if (workflowContext == null) {
			kaleoInstanceImpl.setWorkflowContext(StringPool.BLANK);
		}
		else {
			kaleoInstanceImpl.setWorkflowContext(workflowContext);
		}

		kaleoInstanceImpl.resetOriginalValues();

		return kaleoInstanceImpl;
	}

	public long kaleoInstanceId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long kaleoDefinitionId;
	public String kaleoDefinitionName;
	public int kaleoDefinitionVersion;
	public long rootKaleoInstanceTokenId;
	public String className;
	public long classPK;
	public boolean completed;
	public long completionDate;
	public String workflowContext;
}