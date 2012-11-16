/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link JIRAChangeGroup}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAChangeGroup
 * @generated
 */
public class JIRAChangeGroupWrapper implements JIRAChangeGroup,
	ModelWrapper<JIRAChangeGroup> {
	public JIRAChangeGroupWrapper(JIRAChangeGroup jiraChangeGroup) {
		_jiraChangeGroup = jiraChangeGroup;
	}

	public Class<?> getModelClass() {
		return JIRAChangeGroup.class;
	}

	public String getModelClassName() {
		return JIRAChangeGroup.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraChangeGroupId", getJiraChangeGroupId());
		attributes.put("jiraUserId", getJiraUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("jiraIssueId", getJiraIssueId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraChangeGroupId = (Long)attributes.get("jiraChangeGroupId");

		if (jiraChangeGroupId != null) {
			setJiraChangeGroupId(jiraChangeGroupId);
		}

		String jiraUserId = (String)attributes.get("jiraUserId");

		if (jiraUserId != null) {
			setJiraUserId(jiraUserId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long jiraIssueId = (Long)attributes.get("jiraIssueId");

		if (jiraIssueId != null) {
			setJiraIssueId(jiraIssueId);
		}
	}

	/**
	* Returns the primary key of this j i r a change group.
	*
	* @return the primary key of this j i r a change group
	*/
	public long getPrimaryKey() {
		return _jiraChangeGroup.getPrimaryKey();
	}

	/**
	* Sets the primary key of this j i r a change group.
	*
	* @param primaryKey the primary key of this j i r a change group
	*/
	public void setPrimaryKey(long primaryKey) {
		_jiraChangeGroup.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the jira change group ID of this j i r a change group.
	*
	* @return the jira change group ID of this j i r a change group
	*/
	public long getJiraChangeGroupId() {
		return _jiraChangeGroup.getJiraChangeGroupId();
	}

	/**
	* Sets the jira change group ID of this j i r a change group.
	*
	* @param jiraChangeGroupId the jira change group ID of this j i r a change group
	*/
	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeGroup.setJiraChangeGroupId(jiraChangeGroupId);
	}

	/**
	* Returns the jira user ID of this j i r a change group.
	*
	* @return the jira user ID of this j i r a change group
	*/
	public java.lang.String getJiraUserId() {
		return _jiraChangeGroup.getJiraUserId();
	}

	/**
	* Sets the jira user ID of this j i r a change group.
	*
	* @param jiraUserId the jira user ID of this j i r a change group
	*/
	public void setJiraUserId(java.lang.String jiraUserId) {
		_jiraChangeGroup.setJiraUserId(jiraUserId);
	}

	/**
	* Returns the create date of this j i r a change group.
	*
	* @return the create date of this j i r a change group
	*/
	public java.util.Date getCreateDate() {
		return _jiraChangeGroup.getCreateDate();
	}

	/**
	* Sets the create date of this j i r a change group.
	*
	* @param createDate the create date of this j i r a change group
	*/
	public void setCreateDate(java.util.Date createDate) {
		_jiraChangeGroup.setCreateDate(createDate);
	}

	/**
	* Returns the jira issue ID of this j i r a change group.
	*
	* @return the jira issue ID of this j i r a change group
	*/
	public long getJiraIssueId() {
		return _jiraChangeGroup.getJiraIssueId();
	}

	/**
	* Sets the jira issue ID of this j i r a change group.
	*
	* @param jiraIssueId the jira issue ID of this j i r a change group
	*/
	public void setJiraIssueId(long jiraIssueId) {
		_jiraChangeGroup.setJiraIssueId(jiraIssueId);
	}

	public boolean isNew() {
		return _jiraChangeGroup.isNew();
	}

	public void setNew(boolean n) {
		_jiraChangeGroup.setNew(n);
	}

	public boolean isCachedModel() {
		return _jiraChangeGroup.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_jiraChangeGroup.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _jiraChangeGroup.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraChangeGroup.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_jiraChangeGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraChangeGroup.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraChangeGroup.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new JIRAChangeGroupWrapper((JIRAChangeGroup)_jiraChangeGroup.clone());
	}

	public int compareTo(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup) {
		return _jiraChangeGroup.compareTo(jiraChangeGroup);
	}

	@Override
	public int hashCode() {
		return _jiraChangeGroup.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.socialcoding.model.JIRAChangeGroup> toCacheModel() {
		return _jiraChangeGroup.toCacheModel();
	}

	public com.liferay.socialcoding.model.JIRAChangeGroup toEscapedModel() {
		return new JIRAChangeGroupWrapper(_jiraChangeGroup.toEscapedModel());
	}

	public com.liferay.socialcoding.model.JIRAChangeGroup toUnescapedModel() {
		return new JIRAChangeGroupWrapper(_jiraChangeGroup.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _jiraChangeGroup.toString();
	}

	public java.lang.String toXmlString() {
		return _jiraChangeGroup.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_jiraChangeGroup.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public JIRAChangeGroup getWrappedJIRAChangeGroup() {
		return _jiraChangeGroup;
	}

	public JIRAChangeGroup getWrappedModel() {
		return _jiraChangeGroup;
	}

	public void resetOriginalValues() {
		_jiraChangeGroup.resetOriginalValues();
	}

	private JIRAChangeGroup _jiraChangeGroup;
}