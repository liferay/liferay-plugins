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


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link JIRAIssue}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAIssue
 * @generated
 */
public class JIRAIssueWrapper implements JIRAIssue {
	public JIRAIssueWrapper(JIRAIssue jiraIssue) {
		_jiraIssue = jiraIssue;
	}

	public long getPrimaryKey() {
		return _jiraIssue.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_jiraIssue.setPrimaryKey(pk);
	}

	public long getJiraIssueId() {
		return _jiraIssue.getJiraIssueId();
	}

	public void setJiraIssueId(long jiraIssueId) {
		_jiraIssue.setJiraIssueId(jiraIssueId);
	}

	public java.util.Date getCreateDate() {
		return _jiraIssue.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_jiraIssue.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _jiraIssue.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_jiraIssue.setModifiedDate(modifiedDate);
	}

	public long getProjectId() {
		return _jiraIssue.getProjectId();
	}

	public void setProjectId(long projectId) {
		_jiraIssue.setProjectId(projectId);
	}

	public java.lang.String getKey() {
		return _jiraIssue.getKey();
	}

	public void setKey(java.lang.String key) {
		_jiraIssue.setKey(key);
	}

	public java.lang.String getSummary() {
		return _jiraIssue.getSummary();
	}

	public void setSummary(java.lang.String summary) {
		_jiraIssue.setSummary(summary);
	}

	public java.lang.String getDescription() {
		return _jiraIssue.getDescription();
	}

	public void setDescription(java.lang.String description) {
		_jiraIssue.setDescription(description);
	}

	public java.lang.String getReporterJiraUserId() {
		return _jiraIssue.getReporterJiraUserId();
	}

	public void setReporterJiraUserId(java.lang.String reporterJiraUserId) {
		_jiraIssue.setReporterJiraUserId(reporterJiraUserId);
	}

	public java.lang.String getAssigneeJiraUserId() {
		return _jiraIssue.getAssigneeJiraUserId();
	}

	public void setAssigneeJiraUserId(java.lang.String assigneeJiraUserId) {
		_jiraIssue.setAssigneeJiraUserId(assigneeJiraUserId);
	}

	public java.lang.String getResolution() {
		return _jiraIssue.getResolution();
	}

	public void setResolution(java.lang.String resolution) {
		_jiraIssue.setResolution(resolution);
	}

	public java.lang.String getStatus() {
		return _jiraIssue.getStatus();
	}

	public void setStatus(java.lang.String status) {
		_jiraIssue.setStatus(status);
	}

	public com.liferay.socialcoding.model.JIRAIssue toEscapedModel() {
		return _jiraIssue.toEscapedModel();
	}

	public boolean isNew() {
		return _jiraIssue.isNew();
	}

	public void setNew(boolean n) {
		_jiraIssue.setNew(n);
	}

	public boolean isCachedModel() {
		return _jiraIssue.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_jiraIssue.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _jiraIssue.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_jiraIssue.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraIssue.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraIssue.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraIssue.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _jiraIssue.clone();
	}

	public int compareTo(com.liferay.socialcoding.model.JIRAIssue jiraIssue) {
		return _jiraIssue.compareTo(jiraIssue);
	}

	public int hashCode() {
		return _jiraIssue.hashCode();
	}

	public java.lang.String toString() {
		return _jiraIssue.toString();
	}

	public java.lang.String toXmlString() {
		return _jiraIssue.toXmlString();
	}

	public JIRAIssue getWrappedJIRAIssue() {
		return _jiraIssue;
	}

	private JIRAIssue _jiraIssue;
}