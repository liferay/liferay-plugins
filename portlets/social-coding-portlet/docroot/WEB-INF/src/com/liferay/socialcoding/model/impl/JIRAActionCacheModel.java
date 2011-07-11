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

import com.liferay.socialcoding.model.JIRAAction;

import java.util.Date;

/**
 * The cache model class for representing JIRAAction in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAAction
 * @generated
 */
public class JIRAActionCacheModel implements CacheModel<JIRAAction> {
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

	public long jiraActionId;
	public String jiraUserId;
	public long createDate;
	public long modifiedDate;
	public long jiraIssueId;
	public String type;
	public String body;
	public String jiraGroupName;
}