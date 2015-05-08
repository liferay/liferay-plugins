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

package com.liferay.socialcoding.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.socialcoding.model.JIRAAction;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing JIRAAction in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAAction
 * @generated
 */
@ProviderType
public class JIRAActionCacheModel implements CacheModel<JIRAAction>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAActionCacheModel)) {
			return false;
		}

		JIRAActionCacheModel jiraActionCacheModel = (JIRAActionCacheModel)obj;

		if (jiraActionId == jiraActionCacheModel.jiraActionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, jiraActionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{jiraActionId=");
		sb.append(jiraActionId);
		sb.append(", jiraUserId=");
		sb.append(jiraUserId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", jiraIssueId=");
		sb.append(jiraIssueId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", body=");
		sb.append(body);
		sb.append(", jiraGroupName=");
		sb.append(jiraGroupName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JIRAAction toEntityModel() {
		JIRAActionImpl jiraActionImpl = new JIRAActionImpl();

		jiraActionImpl.setJiraActionId(jiraActionId);

		if (jiraUserId == null) {
			jiraActionImpl.setJiraUserId(StringPool.BLANK);
		}
		else {
			jiraActionImpl.setJiraUserId(jiraUserId);
		}

		if (createDate == Long.MIN_VALUE) {
			jiraActionImpl.setCreateDate(null);
		}
		else {
			jiraActionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			jiraActionImpl.setModifiedDate(null);
		}
		else {
			jiraActionImpl.setModifiedDate(new Date(modifiedDate));
		}

		jiraActionImpl.setJiraIssueId(jiraIssueId);

		if (type == null) {
			jiraActionImpl.setType(StringPool.BLANK);
		}
		else {
			jiraActionImpl.setType(type);
		}

		if (body == null) {
			jiraActionImpl.setBody(StringPool.BLANK);
		}
		else {
			jiraActionImpl.setBody(body);
		}

		if (jiraGroupName == null) {
			jiraActionImpl.setJiraGroupName(StringPool.BLANK);
		}
		else {
			jiraActionImpl.setJiraGroupName(jiraGroupName);
		}

		jiraActionImpl.resetOriginalValues();

		return jiraActionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		jiraActionId = objectInput.readLong();
		jiraUserId = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		jiraIssueId = objectInput.readLong();
		type = objectInput.readUTF();
		body = objectInput.readUTF();
		jiraGroupName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(jiraActionId);

		if (jiraUserId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(jiraUserId);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(jiraIssueId);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (body == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(body);
		}

		if (jiraGroupName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(jiraGroupName);
		}
	}

	public long jiraActionId;
	public String jiraUserId;
	public long createDate;
	public long modifiedDate;
	public long jiraIssueId;
	public String type;
	public String body;
	public String jiraGroupName;
}