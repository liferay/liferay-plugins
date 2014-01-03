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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link JIRAChangeItem}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeItem
 * @generated
 */
public class JIRAChangeItemWrapper implements JIRAChangeItem,
	ModelWrapper<JIRAChangeItem> {
	public JIRAChangeItemWrapper(JIRAChangeItem jiraChangeItem) {
		_jiraChangeItem = jiraChangeItem;
	}

	@Override
	public Class<?> getModelClass() {
		return JIRAChangeItem.class;
	}

	@Override
	public String getModelClassName() {
		return JIRAChangeItem.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraChangeItemId", getJiraChangeItemId());
		attributes.put("jiraChangeGroupId", getJiraChangeGroupId());
		attributes.put("field", getField());
		attributes.put("oldValue", getOldValue());
		attributes.put("oldString", getOldString());
		attributes.put("newValue", getNewValue());
		attributes.put("newString", getNewString());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraChangeItemId = (Long)attributes.get("jiraChangeItemId");

		if (jiraChangeItemId != null) {
			setJiraChangeItemId(jiraChangeItemId);
		}

		Long jiraChangeGroupId = (Long)attributes.get("jiraChangeGroupId");

		if (jiraChangeGroupId != null) {
			setJiraChangeGroupId(jiraChangeGroupId);
		}

		String field = (String)attributes.get("field");

		if (field != null) {
			setField(field);
		}

		String oldValue = (String)attributes.get("oldValue");

		if (oldValue != null) {
			setOldValue(oldValue);
		}

		String oldString = (String)attributes.get("oldString");

		if (oldString != null) {
			setOldString(oldString);
		}

		String newValue = (String)attributes.get("newValue");

		if (newValue != null) {
			setNewValue(newValue);
		}

		String newString = (String)attributes.get("newString");

		if (newString != null) {
			setNewString(newString);
		}
	}

	/**
	* Returns the primary key of this j i r a change item.
	*
	* @return the primary key of this j i r a change item
	*/
	@Override
	public long getPrimaryKey() {
		return _jiraChangeItem.getPrimaryKey();
	}

	/**
	* Sets the primary key of this j i r a change item.
	*
	* @param primaryKey the primary key of this j i r a change item
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_jiraChangeItem.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the jira change item ID of this j i r a change item.
	*
	* @return the jira change item ID of this j i r a change item
	*/
	@Override
	public long getJiraChangeItemId() {
		return _jiraChangeItem.getJiraChangeItemId();
	}

	/**
	* Sets the jira change item ID of this j i r a change item.
	*
	* @param jiraChangeItemId the jira change item ID of this j i r a change item
	*/
	@Override
	public void setJiraChangeItemId(long jiraChangeItemId) {
		_jiraChangeItem.setJiraChangeItemId(jiraChangeItemId);
	}

	/**
	* Returns the jira change group ID of this j i r a change item.
	*
	* @return the jira change group ID of this j i r a change item
	*/
	@Override
	public long getJiraChangeGroupId() {
		return _jiraChangeItem.getJiraChangeGroupId();
	}

	/**
	* Sets the jira change group ID of this j i r a change item.
	*
	* @param jiraChangeGroupId the jira change group ID of this j i r a change item
	*/
	@Override
	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeItem.setJiraChangeGroupId(jiraChangeGroupId);
	}

	/**
	* Returns the field of this j i r a change item.
	*
	* @return the field of this j i r a change item
	*/
	@Override
	public java.lang.String getField() {
		return _jiraChangeItem.getField();
	}

	/**
	* Sets the field of this j i r a change item.
	*
	* @param field the field of this j i r a change item
	*/
	@Override
	public void setField(java.lang.String field) {
		_jiraChangeItem.setField(field);
	}

	/**
	* Returns the old value of this j i r a change item.
	*
	* @return the old value of this j i r a change item
	*/
	@Override
	public java.lang.String getOldValue() {
		return _jiraChangeItem.getOldValue();
	}

	/**
	* Sets the old value of this j i r a change item.
	*
	* @param oldValue the old value of this j i r a change item
	*/
	@Override
	public void setOldValue(java.lang.String oldValue) {
		_jiraChangeItem.setOldValue(oldValue);
	}

	/**
	* Returns the old string of this j i r a change item.
	*
	* @return the old string of this j i r a change item
	*/
	@Override
	public java.lang.String getOldString() {
		return _jiraChangeItem.getOldString();
	}

	/**
	* Sets the old string of this j i r a change item.
	*
	* @param oldString the old string of this j i r a change item
	*/
	@Override
	public void setOldString(java.lang.String oldString) {
		_jiraChangeItem.setOldString(oldString);
	}

	/**
	* Returns the new value of this j i r a change item.
	*
	* @return the new value of this j i r a change item
	*/
	@Override
	public java.lang.String getNewValue() {
		return _jiraChangeItem.getNewValue();
	}

	/**
	* Sets the new value of this j i r a change item.
	*
	* @param newValue the new value of this j i r a change item
	*/
	@Override
	public void setNewValue(java.lang.String newValue) {
		_jiraChangeItem.setNewValue(newValue);
	}

	/**
	* Returns the new string of this j i r a change item.
	*
	* @return the new string of this j i r a change item
	*/
	@Override
	public java.lang.String getNewString() {
		return _jiraChangeItem.getNewString();
	}

	/**
	* Sets the new string of this j i r a change item.
	*
	* @param newString the new string of this j i r a change item
	*/
	@Override
	public void setNewString(java.lang.String newString) {
		_jiraChangeItem.setNewString(newString);
	}

	@Override
	public boolean isNew() {
		return _jiraChangeItem.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_jiraChangeItem.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _jiraChangeItem.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jiraChangeItem.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _jiraChangeItem.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraChangeItem.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_jiraChangeItem.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraChangeItem.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_jiraChangeItem.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_jiraChangeItem.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraChangeItem.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new JIRAChangeItemWrapper((JIRAChangeItem)_jiraChangeItem.clone());
	}

	@Override
	public int compareTo(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem) {
		return _jiraChangeItem.compareTo(jiraChangeItem);
	}

	@Override
	public int hashCode() {
		return _jiraChangeItem.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.socialcoding.model.JIRAChangeItem> toCacheModel() {
		return _jiraChangeItem.toCacheModel();
	}

	@Override
	public com.liferay.socialcoding.model.JIRAChangeItem toEscapedModel() {
		return new JIRAChangeItemWrapper(_jiraChangeItem.toEscapedModel());
	}

	@Override
	public com.liferay.socialcoding.model.JIRAChangeItem toUnescapedModel() {
		return new JIRAChangeItemWrapper(_jiraChangeItem.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _jiraChangeItem.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _jiraChangeItem.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_jiraChangeItem.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAChangeItemWrapper)) {
			return false;
		}

		JIRAChangeItemWrapper jiraChangeItemWrapper = (JIRAChangeItemWrapper)obj;

		if (Validator.equals(_jiraChangeItem,
					jiraChangeItemWrapper._jiraChangeItem)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public JIRAChangeItem getWrappedJIRAChangeItem() {
		return _jiraChangeItem;
	}

	@Override
	public JIRAChangeItem getWrappedModel() {
		return _jiraChangeItem;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _jiraChangeItem.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _jiraChangeItem.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_jiraChangeItem.resetOriginalValues();
	}

	private JIRAChangeItem _jiraChangeItem;
}