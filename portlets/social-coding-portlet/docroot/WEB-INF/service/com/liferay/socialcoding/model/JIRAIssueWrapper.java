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

package com.liferay.socialcoding.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link JIRAIssue}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssue
 * @generated
 */
@ProviderType
public class JIRAIssueWrapper implements JIRAIssue, ModelWrapper<JIRAIssue> {
	public JIRAIssueWrapper(JIRAIssue jiraIssue) {
		_jiraIssue = jiraIssue;
	}

	@Override
	public Class<?> getModelClass() {
		return JIRAIssue.class;
	}

	@Override
	public String getModelClassName() {
		return JIRAIssue.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraIssueId", getJiraIssueId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("projectId", getProjectId());
		attributes.put("issueNumber", getIssueNumber());
		attributes.put("summary", getSummary());
		attributes.put("description", getDescription());
		attributes.put("reporterJiraUserId", getReporterJiraUserId());
		attributes.put("assigneeJiraUserId", getAssigneeJiraUserId());
		attributes.put("resolution", getResolution());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraIssueId = (Long)attributes.get("jiraIssueId");

		if (jiraIssueId != null) {
			setJiraIssueId(jiraIssueId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}

		Long issueNumber = (Long)attributes.get("issueNumber");

		if (issueNumber != null) {
			setIssueNumber(issueNumber);
		}

		String summary = (String)attributes.get("summary");

		if (summary != null) {
			setSummary(summary);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String reporterJiraUserId = (String)attributes.get("reporterJiraUserId");

		if (reporterJiraUserId != null) {
			setReporterJiraUserId(reporterJiraUserId);
		}

		String assigneeJiraUserId = (String)attributes.get("assigneeJiraUserId");

		if (assigneeJiraUserId != null) {
			setAssigneeJiraUserId(assigneeJiraUserId);
		}

		String resolution = (String)attributes.get("resolution");

		if (resolution != null) {
			setResolution(resolution);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new JIRAIssueWrapper((JIRAIssue)_jiraIssue.clone());
	}

	@Override
	public int compareTo(com.liferay.socialcoding.model.JIRAIssue jiraIssue) {
		return _jiraIssue.compareTo(jiraIssue);
	}

	/**
	* Returns the assignee jira user ID of this j i r a issue.
	*
	* @return the assignee jira user ID of this j i r a issue
	*/
	@Override
	public java.lang.String getAssigneeJiraUserId() {
		return _jiraIssue.getAssigneeJiraUserId();
	}

	/**
	* Returns the create date of this j i r a issue.
	*
	* @return the create date of this j i r a issue
	*/
	@Override
	public Date getCreateDate() {
		return _jiraIssue.getCreateDate();
	}

	/**
	* Returns the description of this j i r a issue.
	*
	* @return the description of this j i r a issue
	*/
	@Override
	public java.lang.String getDescription() {
		return _jiraIssue.getDescription();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraIssue.getExpandoBridge();
	}

	/**
	* Returns the issue number of this j i r a issue.
	*
	* @return the issue number of this j i r a issue
	*/
	@Override
	public long getIssueNumber() {
		return _jiraIssue.getIssueNumber();
	}

	/**
	* Returns the jira issue ID of this j i r a issue.
	*
	* @return the jira issue ID of this j i r a issue
	*/
	@Override
	public long getJiraIssueId() {
		return _jiraIssue.getJiraIssueId();
	}

	@Override
	public java.lang.String getKey() {
		return _jiraIssue.getKey();
	}

	/**
	* Returns the modified date of this j i r a issue.
	*
	* @return the modified date of this j i r a issue
	*/
	@Override
	public Date getModifiedDate() {
		return _jiraIssue.getModifiedDate();
	}

	/**
	* Returns the primary key of this j i r a issue.
	*
	* @return the primary key of this j i r a issue
	*/
	@Override
	public long getPrimaryKey() {
		return _jiraIssue.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraIssue.getPrimaryKeyObj();
	}

	/**
	* Returns the project ID of this j i r a issue.
	*
	* @return the project ID of this j i r a issue
	*/
	@Override
	public long getProjectId() {
		return _jiraIssue.getProjectId();
	}

	/**
	* Returns the reporter jira user ID of this j i r a issue.
	*
	* @return the reporter jira user ID of this j i r a issue
	*/
	@Override
	public java.lang.String getReporterJiraUserId() {
		return _jiraIssue.getReporterJiraUserId();
	}

	/**
	* Returns the resolution of this j i r a issue.
	*
	* @return the resolution of this j i r a issue
	*/
	@Override
	public java.lang.String getResolution() {
		return _jiraIssue.getResolution();
	}

	/**
	* Returns the status of this j i r a issue.
	*
	* @return the status of this j i r a issue
	*/
	@Override
	public java.lang.String getStatus() {
		return _jiraIssue.getStatus();
	}

	/**
	* Returns the summary of this j i r a issue.
	*
	* @return the summary of this j i r a issue
	*/
	@Override
	public java.lang.String getSummary() {
		return _jiraIssue.getSummary();
	}

	@Override
	public int hashCode() {
		return _jiraIssue.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _jiraIssue.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _jiraIssue.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _jiraIssue.isNew();
	}

	@Override
	public void persist() {
		_jiraIssue.persist();
	}

	/**
	* Sets the assignee jira user ID of this j i r a issue.
	*
	* @param assigneeJiraUserId the assignee jira user ID of this j i r a issue
	*/
	@Override
	public void setAssigneeJiraUserId(java.lang.String assigneeJiraUserId) {
		_jiraIssue.setAssigneeJiraUserId(assigneeJiraUserId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jiraIssue.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this j i r a issue.
	*
	* @param createDate the create date of this j i r a issue
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_jiraIssue.setCreateDate(createDate);
	}

	/**
	* Sets the description of this j i r a issue.
	*
	* @param description the description of this j i r a issue
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_jiraIssue.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_jiraIssue.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_jiraIssue.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraIssue.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the issue number of this j i r a issue.
	*
	* @param issueNumber the issue number of this j i r a issue
	*/
	@Override
	public void setIssueNumber(long issueNumber) {
		_jiraIssue.setIssueNumber(issueNumber);
	}

	/**
	* Sets the jira issue ID of this j i r a issue.
	*
	* @param jiraIssueId the jira issue ID of this j i r a issue
	*/
	@Override
	public void setJiraIssueId(long jiraIssueId) {
		_jiraIssue.setJiraIssueId(jiraIssueId);
	}

	/**
	* Sets the modified date of this j i r a issue.
	*
	* @param modifiedDate the modified date of this j i r a issue
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_jiraIssue.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_jiraIssue.setNew(n);
	}

	/**
	* Sets the primary key of this j i r a issue.
	*
	* @param primaryKey the primary key of this j i r a issue
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_jiraIssue.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_jiraIssue.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the project ID of this j i r a issue.
	*
	* @param projectId the project ID of this j i r a issue
	*/
	@Override
	public void setProjectId(long projectId) {
		_jiraIssue.setProjectId(projectId);
	}

	/**
	* Sets the reporter jira user ID of this j i r a issue.
	*
	* @param reporterJiraUserId the reporter jira user ID of this j i r a issue
	*/
	@Override
	public void setReporterJiraUserId(java.lang.String reporterJiraUserId) {
		_jiraIssue.setReporterJiraUserId(reporterJiraUserId);
	}

	/**
	* Sets the resolution of this j i r a issue.
	*
	* @param resolution the resolution of this j i r a issue
	*/
	@Override
	public void setResolution(java.lang.String resolution) {
		_jiraIssue.setResolution(resolution);
	}

	/**
	* Sets the status of this j i r a issue.
	*
	* @param status the status of this j i r a issue
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_jiraIssue.setStatus(status);
	}

	/**
	* Sets the summary of this j i r a issue.
	*
	* @param summary the summary of this j i r a issue
	*/
	@Override
	public void setSummary(java.lang.String summary) {
		_jiraIssue.setSummary(summary);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.socialcoding.model.JIRAIssue> toCacheModel() {
		return _jiraIssue.toCacheModel();
	}

	@Override
	public com.liferay.socialcoding.model.JIRAIssue toEscapedModel() {
		return new JIRAIssueWrapper(_jiraIssue.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _jiraIssue.toString();
	}

	@Override
	public com.liferay.socialcoding.model.JIRAIssue toUnescapedModel() {
		return new JIRAIssueWrapper(_jiraIssue.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _jiraIssue.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAIssueWrapper)) {
			return false;
		}

		JIRAIssueWrapper jiraIssueWrapper = (JIRAIssueWrapper)obj;

		if (Validator.equals(_jiraIssue, jiraIssueWrapper._jiraIssue)) {
			return true;
		}

		return false;
	}

	@Override
	public JIRAIssue getWrappedModel() {
		return _jiraIssue;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _jiraIssue.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _jiraIssue.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_jiraIssue.resetOriginalValues();
	}

	private final JIRAIssue _jiraIssue;
}