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
 * This class is a wrapper for {@link JIRAChangeItem}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAChangeItem
 * @generated
 */
public class JIRAChangeItemWrapper implements JIRAChangeItem {
	public JIRAChangeItemWrapper(JIRAChangeItem jiraChangeItem) {
		_jiraChangeItem = jiraChangeItem;
	}

	public long getPrimaryKey() {
		return _jiraChangeItem.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_jiraChangeItem.setPrimaryKey(pk);
	}

	public long getJiraChangeItemId() {
		return _jiraChangeItem.getJiraChangeItemId();
	}

	public void setJiraChangeItemId(long jiraChangeItemId) {
		_jiraChangeItem.setJiraChangeItemId(jiraChangeItemId);
	}

	public long getJiraChangeGroupId() {
		return _jiraChangeItem.getJiraChangeGroupId();
	}

	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeItem.setJiraChangeGroupId(jiraChangeGroupId);
	}

	public java.lang.String getField() {
		return _jiraChangeItem.getField();
	}

	public void setField(java.lang.String field) {
		_jiraChangeItem.setField(field);
	}

	public java.lang.String getOldValue() {
		return _jiraChangeItem.getOldValue();
	}

	public void setOldValue(java.lang.String oldValue) {
		_jiraChangeItem.setOldValue(oldValue);
	}

	public java.lang.String getOldString() {
		return _jiraChangeItem.getOldString();
	}

	public void setOldString(java.lang.String oldString) {
		_jiraChangeItem.setOldString(oldString);
	}

	public java.lang.String getNewValue() {
		return _jiraChangeItem.getNewValue();
	}

	public void setNewValue(java.lang.String newValue) {
		_jiraChangeItem.setNewValue(newValue);
	}

	public java.lang.String getNewString() {
		return _jiraChangeItem.getNewString();
	}

	public void setNewString(java.lang.String newString) {
		_jiraChangeItem.setNewString(newString);
	}

	public com.liferay.socialcoding.model.JIRAChangeItem toEscapedModel() {
		return _jiraChangeItem.toEscapedModel();
	}

	public boolean isNew() {
		return _jiraChangeItem.isNew();
	}

	public void setNew(boolean n) {
		_jiraChangeItem.setNew(n);
	}

	public boolean isCachedModel() {
		return _jiraChangeItem.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_jiraChangeItem.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _jiraChangeItem.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_jiraChangeItem.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraChangeItem.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraChangeItem.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraChangeItem.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _jiraChangeItem.clone();
	}

	public int compareTo(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem) {
		return _jiraChangeItem.compareTo(jiraChangeItem);
	}

	public int hashCode() {
		return _jiraChangeItem.hashCode();
	}

	public java.lang.String toString() {
		return _jiraChangeItem.toString();
	}

	public java.lang.String toXmlString() {
		return _jiraChangeItem.toXmlString();
	}

	public JIRAChangeItem getWrappedJIRAChangeItem() {
		return _jiraChangeItem;
	}

	private JIRAChangeItem _jiraChangeItem;
}