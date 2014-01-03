/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link JIRAChangeGroup}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeGroup
 * @generated
 */
public class JIRAChangeGroupWrapper implements JIRAChangeGroup,
	ModelWrapper<JIRAChangeGroup> {
	public JIRAChangeGroupWrapper(JIRAChangeGroup jiraChangeGroup) {
		_jiraChangeGroup = jiraChangeGroup;
	}

	@Override
	public Class<?> getModelClass() {
		return JIRAChangeGroup.class;
	}

	@Override
	public String getModelClassName() {
		return JIRAChangeGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraChangeGroupId", getJiraChangeGroupId());
		attributes.put("jiraUserId", getJiraUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("jiraIssueId", getJiraIssueId());

		return attributes;
	}

	@Override
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
	@Override
	public long getPrimaryKey() {
		return _jiraChangeGroup.getPrimaryKey();
	}

	/**
	* Sets the primary key of this j i r a change group.
	*
	* @param primaryKey the primary key of this j i r a change group
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_jiraChangeGroup.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the jira change group ID of this j i r a change group.
	*
	* @return the jira change group ID of this j i r a change group
	*/
	@Override
	public long getJiraChangeGroupId() {
		return _jiraChangeGroup.getJiraChangeGroupId();
	}

	/**
	* Sets the jira change group ID of this j i r a change group.
	*
	* @param jiraChangeGroupId the jira change group ID of this j i r a change group
	*/
	@Override
	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeGroup.setJiraChangeGroupId(jiraChangeGroupId);
	}

	/**
	* Returns the jira user ID of this j i r a change group.
	*
	* @return the jira user ID of this j i r a change group
	*/
	@Override
	public java.lang.String getJiraUserId() {
		return _jiraChangeGroup.getJiraUserId();
	}

	/**
	* Sets the jira user ID of this j i r a change group.
	*
	* @param jiraUserId the jira user ID of this j i r a change group
	*/
	@Override
	public void setJiraUserId(java.lang.String jiraUserId) {
		_jiraChangeGroup.setJiraUserId(jiraUserId);
	}

	/**
	* Returns the create date of this j i r a change group.
	*
	* @return the create date of this j i r a change group
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _jiraChangeGroup.getCreateDate();
	}

	/**
	* Sets the create date of this j i r a change group.
	*
	* @param createDate the create date of this j i r a change group
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_jiraChangeGroup.setCreateDate(createDate);
	}

	/**
	* Returns the jira issue ID of this j i r a change group.
	*
	* @return the jira issue ID of this j i r a change group
	*/
	@Override
	public long getJiraIssueId() {
		return _jiraChangeGroup.getJiraIssueId();
	}

	/**
	* Sets the jira issue ID of this j i r a change group.
	*
	* @param jiraIssueId the jira issue ID of this j i r a change group
	*/
	@Override
	public void setJiraIssueId(long jiraIssueId) {
		_jiraChangeGroup.setJiraIssueId(jiraIssueId);
	}

	@Override
	public boolean isNew() {
		return _jiraChangeGroup.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_jiraChangeGroup.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _jiraChangeGroup.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jiraChangeGroup.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _jiraChangeGroup.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraChangeGroup.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_jiraChangeGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraChangeGroup.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_jiraChangeGroup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_jiraChangeGroup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraChangeGroup.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new JIRAChangeGroupWrapper((JIRAChangeGroup)_jiraChangeGroup.clone());
	}

	@Override
	public int compareTo(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup) {
		return _jiraChangeGroup.compareTo(jiraChangeGroup);
	}

	@Override
	public int hashCode() {
		return _jiraChangeGroup.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.socialcoding.model.JIRAChangeGroup> toCacheModel() {
		return _jiraChangeGroup.toCacheModel();
	}

	@Override
	public com.liferay.socialcoding.model.JIRAChangeGroup toEscapedModel() {
		return new JIRAChangeGroupWrapper(_jiraChangeGroup.toEscapedModel());
	}

	@Override
	public com.liferay.socialcoding.model.JIRAChangeGroup toUnescapedModel() {
		return new JIRAChangeGroupWrapper(_jiraChangeGroup.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _jiraChangeGroup.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _jiraChangeGroup.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_jiraChangeGroup.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAChangeGroupWrapper)) {
			return false;
		}

		JIRAChangeGroupWrapper jiraChangeGroupWrapper = (JIRAChangeGroupWrapper)obj;

		if (Validator.equals(_jiraChangeGroup,
					jiraChangeGroupWrapper._jiraChangeGroup)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public JIRAChangeGroup getWrappedJIRAChangeGroup() {
		return _jiraChangeGroup;
	}

	@Override
	public JIRAChangeGroup getWrappedModel() {
		return _jiraChangeGroup;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _jiraChangeGroup.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _jiraChangeGroup.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_jiraChangeGroup.resetOriginalValues();
	}

	private JIRAChangeGroup _jiraChangeGroup;
}