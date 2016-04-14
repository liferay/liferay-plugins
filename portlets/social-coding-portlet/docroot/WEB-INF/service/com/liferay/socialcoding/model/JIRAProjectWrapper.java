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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link JIRAProject}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProject
 * @generated
 */
@ProviderType
public class JIRAProjectWrapper implements JIRAProject,
	ModelWrapper<JIRAProject> {
	public JIRAProjectWrapper(JIRAProject jiraProject) {
		_jiraProject = jiraProject;
	}

	@Override
	public Class<?> getModelClass() {
		return JIRAProject.class;
	}

	@Override
	public String getModelClassName() {
		return JIRAProject.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraProjectId", getJiraProjectId());
		attributes.put("key", getKey());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraProjectId = (Long)attributes.get("jiraProjectId");

		if (jiraProjectId != null) {
			setJiraProjectId(jiraProjectId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _jiraProject.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _jiraProject.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _jiraProject.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _jiraProject.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<com.liferay.socialcoding.model.JIRAProject> toCacheModel() {
		return _jiraProject.toCacheModel();
	}

	@Override
	public com.liferay.socialcoding.model.JIRAProject toEscapedModel() {
		return new JIRAProjectWrapper(_jiraProject.toEscapedModel());
	}

	@Override
	public com.liferay.socialcoding.model.JIRAProject toUnescapedModel() {
		return new JIRAProjectWrapper(_jiraProject.toUnescapedModel());
	}

	@Override
	public int compareTo(com.liferay.socialcoding.model.JIRAProject jiraProject) {
		return _jiraProject.compareTo(jiraProject);
	}

	@Override
	public int hashCode() {
		return _jiraProject.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jiraProject.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new JIRAProjectWrapper((JIRAProject)_jiraProject.clone());
	}

	/**
	* Returns the key of this j i r a project.
	*
	* @return the key of this j i r a project
	*/
	@Override
	public java.lang.String getKey() {
		return _jiraProject.getKey();
	}

	/**
	* Returns the name of this j i r a project.
	*
	* @return the name of this j i r a project
	*/
	@Override
	public java.lang.String getName() {
		return _jiraProject.getName();
	}

	@Override
	public java.lang.String toString() {
		return _jiraProject.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _jiraProject.toXmlString();
	}

	/**
	* Returns the jira project ID of this j i r a project.
	*
	* @return the jira project ID of this j i r a project
	*/
	@Override
	public long getJiraProjectId() {
		return _jiraProject.getJiraProjectId();
	}

	/**
	* Returns the primary key of this j i r a project.
	*
	* @return the primary key of this j i r a project
	*/
	@Override
	public long getPrimaryKey() {
		return _jiraProject.getPrimaryKey();
	}

	@Override
	public void persist() {
		_jiraProject.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jiraProject.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_jiraProject.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_jiraProject.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_jiraProject.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the jira project ID of this j i r a project.
	*
	* @param jiraProjectId the jira project ID of this j i r a project
	*/
	@Override
	public void setJiraProjectId(long jiraProjectId) {
		_jiraProject.setJiraProjectId(jiraProjectId);
	}

	/**
	* Sets the key of this j i r a project.
	*
	* @param key the key of this j i r a project
	*/
	@Override
	public void setKey(java.lang.String key) {
		_jiraProject.setKey(key);
	}

	/**
	* Sets the name of this j i r a project.
	*
	* @param name the name of this j i r a project
	*/
	@Override
	public void setName(java.lang.String name) {
		_jiraProject.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_jiraProject.setNew(n);
	}

	/**
	* Sets the primary key of this j i r a project.
	*
	* @param primaryKey the primary key of this j i r a project
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_jiraProject.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_jiraProject.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAProjectWrapper)) {
			return false;
		}

		JIRAProjectWrapper jiraProjectWrapper = (JIRAProjectWrapper)obj;

		if (Validator.equals(_jiraProject, jiraProjectWrapper._jiraProject)) {
			return true;
		}

		return false;
	}

	@Override
	public JIRAProject getWrappedModel() {
		return _jiraProject;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _jiraProject.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _jiraProject.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_jiraProject.resetOriginalValues();
	}

	private final JIRAProject _jiraProject;
}