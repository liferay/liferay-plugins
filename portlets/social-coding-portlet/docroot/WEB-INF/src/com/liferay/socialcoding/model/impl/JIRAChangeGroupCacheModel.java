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

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.socialcoding.model.JIRAChangeGroup;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing JIRAChangeGroup in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeGroup
 * @generated
 */
@ProviderType
public class JIRAChangeGroupCacheModel implements CacheModel<JIRAChangeGroup>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAChangeGroupCacheModel)) {
			return false;
		}

		JIRAChangeGroupCacheModel jiraChangeGroupCacheModel = (JIRAChangeGroupCacheModel)obj;

		if (jiraChangeGroupId == jiraChangeGroupCacheModel.jiraChangeGroupId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, jiraChangeGroupId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{jiraChangeGroupId=");
		sb.append(jiraChangeGroupId);
		sb.append(", jiraUserId=");
		sb.append(jiraUserId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", jiraIssueId=");
		sb.append(jiraIssueId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JIRAChangeGroup toEntityModel() {
		JIRAChangeGroupImpl jiraChangeGroupImpl = new JIRAChangeGroupImpl();

		jiraChangeGroupImpl.setJiraChangeGroupId(jiraChangeGroupId);

		if (jiraUserId == null) {
			jiraChangeGroupImpl.setJiraUserId(StringPool.BLANK);
		}
		else {
			jiraChangeGroupImpl.setJiraUserId(jiraUserId);
		}

		if (createDate == Long.MIN_VALUE) {
			jiraChangeGroupImpl.setCreateDate(null);
		}
		else {
			jiraChangeGroupImpl.setCreateDate(new Date(createDate));
		}

		jiraChangeGroupImpl.setJiraIssueId(jiraIssueId);

		jiraChangeGroupImpl.resetOriginalValues();

		return jiraChangeGroupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		jiraChangeGroupId = objectInput.readLong();
		jiraUserId = objectInput.readUTF();
		createDate = objectInput.readLong();

		jiraIssueId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(jiraChangeGroupId);

		if (jiraUserId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(jiraUserId);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(jiraIssueId);
	}

	public long jiraChangeGroupId;
	public String jiraUserId;
	public long createDate;
	public long jiraIssueId;
}