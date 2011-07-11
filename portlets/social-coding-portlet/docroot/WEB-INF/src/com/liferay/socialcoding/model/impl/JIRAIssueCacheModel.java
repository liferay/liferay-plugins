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

import com.liferay.socialcoding.model.JIRAIssue;

import java.util.Date;

/**
 * The cache model class for representing JIRAIssue in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssue
 * @generated
 */
public class JIRAIssueCacheModel implements CacheModel<JIRAIssue> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{jiraIssueId=");
		sb.append(jiraIssueId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", summary=");
		sb.append(summary);
		sb.append(", description=");
		sb.append(description);
		sb.append(", reporterJiraUserId=");
		sb.append(reporterJiraUserId);
		sb.append(", assigneeJiraUserId=");
		sb.append(assigneeJiraUserId);
		sb.append(", resolution=");
		sb.append(resolution);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	public JIRAIssue toEntityModel() {
		JIRAIssueImpl jiraIssueImpl = new JIRAIssueImpl();

		jiraIssueImpl.setJiraIssueId(jiraIssueId);

		if (createDate == Long.MIN_VALUE) {
			jiraIssueImpl.setCreateDate(null);
		}
		else {
			jiraIssueImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			jiraIssueImpl.setModifiedDate(null);
		}
		else {
			jiraIssueImpl.setModifiedDate(new Date(modifiedDate));
		}

		jiraIssueImpl.setProjectId(projectId);

		if (key == null) {
			jiraIssueImpl.setKey(StringPool.BLANK);
		}
		else {
			jiraIssueImpl.setKey(key);
		}

		if (summary == null) {
			jiraIssueImpl.setSummary(StringPool.BLANK);
		}
		else {
			jiraIssueImpl.setSummary(summary);
		}

		if (description == null) {
			jiraIssueImpl.setDescription(StringPool.BLANK);
		}
		else {
			jiraIssueImpl.setDescription(description);
		}

		if (reporterJiraUserId == null) {
			jiraIssueImpl.setReporterJiraUserId(StringPool.BLANK);
		}
		else {
			jiraIssueImpl.setReporterJiraUserId(reporterJiraUserId);
		}

		if (assigneeJiraUserId == null) {
			jiraIssueImpl.setAssigneeJiraUserId(StringPool.BLANK);
		}
		else {
			jiraIssueImpl.setAssigneeJiraUserId(assigneeJiraUserId);
		}

		if (resolution == null) {
			jiraIssueImpl.setResolution(StringPool.BLANK);
		}
		else {
			jiraIssueImpl.setResolution(resolution);
		}

		if (status == null) {
			jiraIssueImpl.setStatus(StringPool.BLANK);
		}
		else {
			jiraIssueImpl.setStatus(status);
		}

		jiraIssueImpl.resetOriginalValues();

		return jiraIssueImpl;
	}

	public long jiraIssueId;
	public long createDate;
	public long modifiedDate;
	public long projectId;
	public String key;
	public String summary;
	public String description;
	public String reporterJiraUserId;
	public String assigneeJiraUserId;
	public String resolution;
	public String status;
}