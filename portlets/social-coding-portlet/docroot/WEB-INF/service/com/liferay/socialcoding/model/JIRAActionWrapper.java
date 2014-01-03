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
 * This class is a wrapper for {@link JIRAAction}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAAction
 * @generated
 */
public class JIRAActionWrapper implements JIRAAction, ModelWrapper<JIRAAction> {
	public JIRAActionWrapper(JIRAAction jiraAction) {
		_jiraAction = jiraAction;
	}

	@Override
	public Class<?> getModelClass() {
		return JIRAAction.class;
	}

	@Override
	public String getModelClassName() {
		return JIRAAction.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraActionId", getJiraActionId());
		attributes.put("jiraUserId", getJiraUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("jiraIssueId", getJiraIssueId());
		attributes.put("type", getType());
		attributes.put("body", getBody());
		attributes.put("jiraGroupName", getJiraGroupName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraActionId = (Long)attributes.get("jiraActionId");

		if (jiraActionId != null) {
			setJiraActionId(jiraActionId);
		}

		String jiraUserId = (String)attributes.get("jiraUserId");

		if (jiraUserId != null) {
			setJiraUserId(jiraUserId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long jiraIssueId = (Long)attributes.get("jiraIssueId");

		if (jiraIssueId != null) {
			setJiraIssueId(jiraIssueId);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String body = (String)attributes.get("body");

		if (body != null) {
			setBody(body);
		}

		String jiraGroupName = (String)attributes.get("jiraGroupName");

		if (jiraGroupName != null) {
			setJiraGroupName(jiraGroupName);
		}
	}

	/**
	* Returns the primary key of this j i r a action.
	*
	* @return the primary key of this j i r a action
	*/
	@Override
	public long getPrimaryKey() {
		return _jiraAction.getPrimaryKey();
	}

	/**
	* Sets the primary key of this j i r a action.
	*
	* @param primaryKey the primary key of this j i r a action
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_jiraAction.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the jira action ID of this j i r a action.
	*
	* @return the jira action ID of this j i r a action
	*/
	@Override
	public long getJiraActionId() {
		return _jiraAction.getJiraActionId();
	}

	/**
	* Sets the jira action ID of this j i r a action.
	*
	* @param jiraActionId the jira action ID of this j i r a action
	*/
	@Override
	public void setJiraActionId(long jiraActionId) {
		_jiraAction.setJiraActionId(jiraActionId);
	}

	/**
	* Returns the jira user ID of this j i r a action.
	*
	* @return the jira user ID of this j i r a action
	*/
	@Override
	public java.lang.String getJiraUserId() {
		return _jiraAction.getJiraUserId();
	}

	/**
	* Sets the jira user ID of this j i r a action.
	*
	* @param jiraUserId the jira user ID of this j i r a action
	*/
	@Override
	public void setJiraUserId(java.lang.String jiraUserId) {
		_jiraAction.setJiraUserId(jiraUserId);
	}

	/**
	* Returns the create date of this j i r a action.
	*
	* @return the create date of this j i r a action
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _jiraAction.getCreateDate();
	}

	/**
	* Sets the create date of this j i r a action.
	*
	* @param createDate the create date of this j i r a action
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_jiraAction.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this j i r a action.
	*
	* @return the modified date of this j i r a action
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _jiraAction.getModifiedDate();
	}

	/**
	* Sets the modified date of this j i r a action.
	*
	* @param modifiedDate the modified date of this j i r a action
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_jiraAction.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the jira issue ID of this j i r a action.
	*
	* @return the jira issue ID of this j i r a action
	*/
	@Override
	public long getJiraIssueId() {
		return _jiraAction.getJiraIssueId();
	}

	/**
	* Sets the jira issue ID of this j i r a action.
	*
	* @param jiraIssueId the jira issue ID of this j i r a action
	*/
	@Override
	public void setJiraIssueId(long jiraIssueId) {
		_jiraAction.setJiraIssueId(jiraIssueId);
	}

	/**
	* Returns the type of this j i r a action.
	*
	* @return the type of this j i r a action
	*/
	@Override
	public java.lang.String getType() {
		return _jiraAction.getType();
	}

	/**
	* Sets the type of this j i r a action.
	*
	* @param type the type of this j i r a action
	*/
	@Override
	public void setType(java.lang.String type) {
		_jiraAction.setType(type);
	}

	/**
	* Returns the body of this j i r a action.
	*
	* @return the body of this j i r a action
	*/
	@Override
	public java.lang.String getBody() {
		return _jiraAction.getBody();
	}

	/**
	* Sets the body of this j i r a action.
	*
	* @param body the body of this j i r a action
	*/
	@Override
	public void setBody(java.lang.String body) {
		_jiraAction.setBody(body);
	}

	/**
	* Returns the jira group name of this j i r a action.
	*
	* @return the jira group name of this j i r a action
	*/
	@Override
	public java.lang.String getJiraGroupName() {
		return _jiraAction.getJiraGroupName();
	}

	/**
	* Sets the jira group name of this j i r a action.
	*
	* @param jiraGroupName the jira group name of this j i r a action
	*/
	@Override
	public void setJiraGroupName(java.lang.String jiraGroupName) {
		_jiraAction.setJiraGroupName(jiraGroupName);
	}

	@Override
	public boolean isNew() {
		return _jiraAction.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_jiraAction.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _jiraAction.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jiraAction.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _jiraAction.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraAction.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_jiraAction.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraAction.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_jiraAction.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_jiraAction.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraAction.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new JIRAActionWrapper((JIRAAction)_jiraAction.clone());
	}

	@Override
	public int compareTo(com.liferay.socialcoding.model.JIRAAction jiraAction) {
		return _jiraAction.compareTo(jiraAction);
	}

	@Override
	public int hashCode() {
		return _jiraAction.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.socialcoding.model.JIRAAction> toCacheModel() {
		return _jiraAction.toCacheModel();
	}

	@Override
	public com.liferay.socialcoding.model.JIRAAction toEscapedModel() {
		return new JIRAActionWrapper(_jiraAction.toEscapedModel());
	}

	@Override
	public com.liferay.socialcoding.model.JIRAAction toUnescapedModel() {
		return new JIRAActionWrapper(_jiraAction.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _jiraAction.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _jiraAction.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_jiraAction.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAActionWrapper)) {
			return false;
		}

		JIRAActionWrapper jiraActionWrapper = (JIRAActionWrapper)obj;

		if (Validator.equals(_jiraAction, jiraActionWrapper._jiraAction)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public JIRAAction getWrappedJIRAAction() {
		return _jiraAction;
	}

	@Override
	public JIRAAction getWrappedModel() {
		return _jiraAction;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _jiraAction.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _jiraAction.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_jiraAction.resetOriginalValues();
	}

	private JIRAAction _jiraAction;
}