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

package com.liferay.socialcoding.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link JIRAAction}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAAction
 * @generated
 */
public class JIRAActionWrapper implements JIRAAction, ModelWrapper<JIRAAction> {
	public JIRAActionWrapper(JIRAAction jiraAction) {
		_jiraAction = jiraAction;
	}

	public Class<?> getModelClass() {
		return JIRAAction.class;
	}

	public String getModelClassName() {
		return JIRAAction.class.getName();
	}

	/**
	* Returns the primary key of this j i r a action.
	*
	* @return the primary key of this j i r a action
	*/
	public long getPrimaryKey() {
		return _jiraAction.getPrimaryKey();
	}

	/**
	* Sets the primary key of this j i r a action.
	*
	* @param primaryKey the primary key of this j i r a action
	*/
	public void setPrimaryKey(long primaryKey) {
		_jiraAction.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the jira action ID of this j i r a action.
	*
	* @return the jira action ID of this j i r a action
	*/
	public long getJiraActionId() {
		return _jiraAction.getJiraActionId();
	}

	/**
	* Sets the jira action ID of this j i r a action.
	*
	* @param jiraActionId the jira action ID of this j i r a action
	*/
	public void setJiraActionId(long jiraActionId) {
		_jiraAction.setJiraActionId(jiraActionId);
	}

	/**
	* Returns the jira user ID of this j i r a action.
	*
	* @return the jira user ID of this j i r a action
	*/
	public java.lang.String getJiraUserId() {
		return _jiraAction.getJiraUserId();
	}

	/**
	* Sets the jira user ID of this j i r a action.
	*
	* @param jiraUserId the jira user ID of this j i r a action
	*/
	public void setJiraUserId(java.lang.String jiraUserId) {
		_jiraAction.setJiraUserId(jiraUserId);
	}

	/**
	* Returns the create date of this j i r a action.
	*
	* @return the create date of this j i r a action
	*/
	public java.util.Date getCreateDate() {
		return _jiraAction.getCreateDate();
	}

	/**
	* Sets the create date of this j i r a action.
	*
	* @param createDate the create date of this j i r a action
	*/
	public void setCreateDate(java.util.Date createDate) {
		_jiraAction.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this j i r a action.
	*
	* @return the modified date of this j i r a action
	*/
	public java.util.Date getModifiedDate() {
		return _jiraAction.getModifiedDate();
	}

	/**
	* Sets the modified date of this j i r a action.
	*
	* @param modifiedDate the modified date of this j i r a action
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_jiraAction.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the jira issue ID of this j i r a action.
	*
	* @return the jira issue ID of this j i r a action
	*/
	public long getJiraIssueId() {
		return _jiraAction.getJiraIssueId();
	}

	/**
	* Sets the jira issue ID of this j i r a action.
	*
	* @param jiraIssueId the jira issue ID of this j i r a action
	*/
	public void setJiraIssueId(long jiraIssueId) {
		_jiraAction.setJiraIssueId(jiraIssueId);
	}

	/**
	* Returns the type of this j i r a action.
	*
	* @return the type of this j i r a action
	*/
	public java.lang.String getType() {
		return _jiraAction.getType();
	}

	/**
	* Sets the type of this j i r a action.
	*
	* @param type the type of this j i r a action
	*/
	public void setType(java.lang.String type) {
		_jiraAction.setType(type);
	}

	/**
	* Returns the body of this j i r a action.
	*
	* @return the body of this j i r a action
	*/
	public java.lang.String getBody() {
		return _jiraAction.getBody();
	}

	/**
	* Sets the body of this j i r a action.
	*
	* @param body the body of this j i r a action
	*/
	public void setBody(java.lang.String body) {
		_jiraAction.setBody(body);
	}

	/**
	* Returns the jira group name of this j i r a action.
	*
	* @return the jira group name of this j i r a action
	*/
	public java.lang.String getJiraGroupName() {
		return _jiraAction.getJiraGroupName();
	}

	/**
	* Sets the jira group name of this j i r a action.
	*
	* @param jiraGroupName the jira group name of this j i r a action
	*/
	public void setJiraGroupName(java.lang.String jiraGroupName) {
		_jiraAction.setJiraGroupName(jiraGroupName);
	}

	public boolean isNew() {
		return _jiraAction.isNew();
	}

	public void setNew(boolean n) {
		_jiraAction.setNew(n);
	}

	public boolean isCachedModel() {
		return _jiraAction.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_jiraAction.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _jiraAction.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraAction.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_jiraAction.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraAction.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraAction.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new JIRAActionWrapper((JIRAAction)_jiraAction.clone());
	}

	public int compareTo(com.liferay.socialcoding.model.JIRAAction jiraAction) {
		return _jiraAction.compareTo(jiraAction);
	}

	@Override
	public int hashCode() {
		return _jiraAction.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.socialcoding.model.JIRAAction> toCacheModel() {
		return _jiraAction.toCacheModel();
	}

	public com.liferay.socialcoding.model.JIRAAction toEscapedModel() {
		return new JIRAActionWrapper(_jiraAction.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _jiraAction.toString();
	}

	public java.lang.String toXmlString() {
		return _jiraAction.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_jiraAction.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public JIRAAction getWrappedJIRAAction() {
		return _jiraAction;
	}

	public JIRAAction getWrappedModel() {
		return _jiraAction;
	}

	public void resetOriginalValues() {
		_jiraAction.resetOriginalValues();
	}

	private JIRAAction _jiraAction;
}