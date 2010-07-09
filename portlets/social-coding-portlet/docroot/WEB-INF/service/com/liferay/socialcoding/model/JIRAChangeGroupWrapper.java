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
 * This class is a wrapper for {@link JIRAChangeGroup}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAChangeGroup
 * @generated
 */
public class JIRAChangeGroupWrapper implements JIRAChangeGroup {
	public JIRAChangeGroupWrapper(JIRAChangeGroup jiraChangeGroup) {
		_jiraChangeGroup = jiraChangeGroup;
	}

	public long getPrimaryKey() {
		return _jiraChangeGroup.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_jiraChangeGroup.setPrimaryKey(pk);
	}

	public long getJiraChangeGroupId() {
		return _jiraChangeGroup.getJiraChangeGroupId();
	}

	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeGroup.setJiraChangeGroupId(jiraChangeGroupId);
	}

	public java.lang.String getJiraUserId() {
		return _jiraChangeGroup.getJiraUserId();
	}

	public void setJiraUserId(java.lang.String jiraUserId) {
		_jiraChangeGroup.setJiraUserId(jiraUserId);
	}

	public java.util.Date getCreateDate() {
		return _jiraChangeGroup.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_jiraChangeGroup.setCreateDate(createDate);
	}

	public long getJiraIssueId() {
		return _jiraChangeGroup.getJiraIssueId();
	}

	public void setJiraIssueId(long jiraIssueId) {
		_jiraChangeGroup.setJiraIssueId(jiraIssueId);
	}

	public com.liferay.socialcoding.model.JIRAChangeGroup toEscapedModel() {
		return _jiraChangeGroup.toEscapedModel();
	}

	public boolean isNew() {
		return _jiraChangeGroup.isNew();
	}

	public boolean setNew(boolean n) {
		return _jiraChangeGroup.setNew(n);
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

	public void setEscapedModel(boolean escapedModel) {
		_jiraChangeGroup.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraChangeGroup.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraChangeGroup.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraChangeGroup.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _jiraChangeGroup.clone();
	}

	public int compareTo(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup) {
		return _jiraChangeGroup.compareTo(jiraChangeGroup);
	}

	public int hashCode() {
		return _jiraChangeGroup.hashCode();
	}

	public java.lang.String toString() {
		return _jiraChangeGroup.toString();
	}

	public java.lang.String toXmlString() {
		return _jiraChangeGroup.toXmlString();
	}

	public JIRAChangeGroup getWrappedJIRAChangeGroup() {
		return _jiraChangeGroup;
	}

	private JIRAChangeGroup _jiraChangeGroup;
}