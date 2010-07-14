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
 * This class is a wrapper for {@link JIRAAction}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAAction
 * @generated
 */
public class JIRAActionWrapper implements JIRAAction {
	public JIRAActionWrapper(JIRAAction jiraAction) {
		_jiraAction = jiraAction;
	}

	public long getPrimaryKey() {
		return _jiraAction.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_jiraAction.setPrimaryKey(pk);
	}

	public long getJiraActionId() {
		return _jiraAction.getJiraActionId();
	}

	public void setJiraActionId(long jiraActionId) {
		_jiraAction.setJiraActionId(jiraActionId);
	}

	public java.lang.String getJiraUserId() {
		return _jiraAction.getJiraUserId();
	}

	public void setJiraUserId(java.lang.String jiraUserId) {
		_jiraAction.setJiraUserId(jiraUserId);
	}

	public java.util.Date getCreateDate() {
		return _jiraAction.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_jiraAction.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _jiraAction.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_jiraAction.setModifiedDate(modifiedDate);
	}

	public long getJiraIssueId() {
		return _jiraAction.getJiraIssueId();
	}

	public void setJiraIssueId(long jiraIssueId) {
		_jiraAction.setJiraIssueId(jiraIssueId);
	}

	public java.lang.String getType() {
		return _jiraAction.getType();
	}

	public void setType(java.lang.String type) {
		_jiraAction.setType(type);
	}

	public java.lang.String getBody() {
		return _jiraAction.getBody();
	}

	public void setBody(java.lang.String body) {
		_jiraAction.setBody(body);
	}

	public java.lang.String getJiraGroupName() {
		return _jiraAction.getJiraGroupName();
	}

	public void setJiraGroupName(java.lang.String jiraGroupName) {
		_jiraAction.setJiraGroupName(jiraGroupName);
	}

	public com.liferay.socialcoding.model.JIRAAction toEscapedModel() {
		return _jiraAction.toEscapedModel();
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

	public void setEscapedModel(boolean escapedModel) {
		_jiraAction.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraAction.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraAction.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraAction.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _jiraAction.clone();
	}

	public int compareTo(com.liferay.socialcoding.model.JIRAAction jiraAction) {
		return _jiraAction.compareTo(jiraAction);
	}

	public int hashCode() {
		return _jiraAction.hashCode();
	}

	public java.lang.String toString() {
		return _jiraAction.toString();
	}

	public java.lang.String toXmlString() {
		return _jiraAction.toXmlString();
	}

	public JIRAAction getWrappedJIRAAction() {
		return _jiraAction;
	}

	private JIRAAction _jiraAction;
}