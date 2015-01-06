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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoTaskAssignmentInstance in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentInstance
 * @generated
 */
@ProviderType
public class KaleoTaskAssignmentInstanceCacheModel implements CacheModel<KaleoTaskAssignmentInstance>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTaskAssignmentInstanceCacheModel)) {
			return false;
		}

		KaleoTaskAssignmentInstanceCacheModel kaleoTaskAssignmentInstanceCacheModel =
			(KaleoTaskAssignmentInstanceCacheModel)obj;

		if (kaleoTaskAssignmentInstanceId == kaleoTaskAssignmentInstanceCacheModel.kaleoTaskAssignmentInstanceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kaleoTaskAssignmentInstanceId);
	}

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

	@Override
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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kaleoTaskAssignmentInstanceId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		kaleoDefinitionId = objectInput.readLong();
		kaleoInstanceId = objectInput.readLong();
		kaleoInstanceTokenId = objectInput.readLong();
		kaleoTaskInstanceTokenId = objectInput.readLong();
		kaleoTaskId = objectInput.readLong();
		kaleoTaskName = objectInput.readUTF();
		assigneeClassName = objectInput.readUTF();
		assigneeClassPK = objectInput.readLong();
		completed = objectInput.readBoolean();
		completionDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(kaleoTaskAssignmentInstanceId);
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
		objectOutput.writeLong(kaleoTaskInstanceTokenId);
		objectOutput.writeLong(kaleoTaskId);

		if (kaleoTaskName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(kaleoTaskName);
		}

		if (assigneeClassName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(assigneeClassName);
		}

		objectOutput.writeLong(assigneeClassPK);
		objectOutput.writeBoolean(completed);
		objectOutput.writeLong(completionDate);
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