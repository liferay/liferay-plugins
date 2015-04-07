/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoTaskInstanceToken in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskInstanceToken
 * @generated
 */
@ProviderType
public class KaleoTaskInstanceTokenCacheModel implements CacheModel<KaleoTaskInstanceToken>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTaskInstanceTokenCacheModel)) {
			return false;
		}

		KaleoTaskInstanceTokenCacheModel kaleoTaskInstanceTokenCacheModel = (KaleoTaskInstanceTokenCacheModel)obj;

		if (kaleoTaskInstanceTokenId == kaleoTaskInstanceTokenCacheModel.kaleoTaskInstanceTokenId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kaleoTaskInstanceTokenId);
	}

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

	@Override
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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kaleoTaskInstanceTokenId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		kaleoDefinitionId = objectInput.readLong();
		kaleoInstanceId = objectInput.readLong();
		kaleoInstanceTokenId = objectInput.readLong();
		kaleoTaskId = objectInput.readLong();
		kaleoTaskName = objectInput.readUTF();
		className = objectInput.readUTF();
		classPK = objectInput.readLong();
		completionUserId = objectInput.readLong();
		completed = objectInput.readBoolean();
		completionDate = objectInput.readLong();
		dueDate = objectInput.readLong();
		workflowContext = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(kaleoTaskInstanceTokenId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(kaleoDefinitionId);
		objectOutput.writeLong(kaleoInstanceId);
		objectOutput.writeLong(kaleoInstanceTokenId);
		objectOutput.writeLong(kaleoTaskId);

		if (kaleoTaskName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(kaleoTaskName);
		}

		if (className == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(className);
		}

		objectOutput.writeLong(classPK);
		objectOutput.writeLong(completionUserId);
		objectOutput.writeBoolean(completed);
		objectOutput.writeLong(completionDate);
		objectOutput.writeLong(dueDate);

		if (workflowContext == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(workflowContext);
		}
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