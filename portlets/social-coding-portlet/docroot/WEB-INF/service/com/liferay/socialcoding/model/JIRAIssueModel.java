/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.socialcoding.model;

import com.liferay.portal.kernel.annotation.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * This interface is a model that represents the jiraissue table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAIssue
 * @see       com.liferay.socialcoding.model.impl.JIRAIssueImpl
 * @see       com.liferay.socialcoding.model.impl.JIRAIssueModelImpl
 * @generated
 */
public interface JIRAIssueModel extends BaseModel<JIRAIssue> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getJiraIssueId();

	public void setJiraIssueId(long jiraIssueId);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getModifiedDate();

	public void setModifiedDate(Date modifiedDate);

	public long getProjectId();

	public void setProjectId(long projectId);

	@AutoEscape
	public String getKey();

	public void setKey(String key);

	@AutoEscape
	public String getSummary();

	public void setSummary(String summary);

	@AutoEscape
	public String getDescription();

	public void setDescription(String description);

	@AutoEscape
	public String getReporterJiraUserId();

	public void setReporterJiraUserId(String reporterJiraUserId);

	@AutoEscape
	public String getAssigneeJiraUserId();

	public void setAssigneeJiraUserId(String assigneeJiraUserId);

	@AutoEscape
	public String getResolution();

	public void setResolution(String resolution);

	@AutoEscape
	public String getStatus();

	public void setStatus(String status);

	public JIRAIssue toEscapedModel();

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(JIRAIssue jiraIssue);

	public int hashCode();

	public String toString();

	public String toXmlString();
}