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

package com.liferay.socialcoding.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.socialcoding.model.JIRAChangeGroup;

import java.util.Date;

/**
 * The cache model class for representing JIRAChangeGroup in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeGroup
 * @generated
 */
public class JIRAChangeGroupCacheModel implements CacheModel<JIRAChangeGroup> {
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

	public long jiraChangeGroupId;
	public String jiraUserId;
	public long createDate;
	public long jiraIssueId;
}