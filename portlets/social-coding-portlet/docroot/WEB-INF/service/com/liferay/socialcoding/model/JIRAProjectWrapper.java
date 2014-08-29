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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link JIRAProject}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAProject
 * @generated
 */
public class JIRAProjectWrapper implements JIRAProject,
	ModelWrapper<JIRAProject> {
	public JIRAProjectWrapper(JIRAProject jiraProject) {
		_jiraProject = jiraProject;
	}

	public Class<?> getModelClass() {
		return JIRAProject.class;
	}

	public String getModelClassName() {
		return JIRAProject.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraProjectId", getJiraProjectId());
		attributes.put("key", getKey());
		attributes.put("name", getName());

		return attributes;
	}

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

	/**
	* Returns the primary key of this j i r a project.
	*
	* @return the primary key of this j i r a project
	*/
	public long getPrimaryKey() {
		return _jiraProject.getPrimaryKey();
	}

	/**
	* Sets the primary key of this j i r a project.
	*
	* @param primaryKey the primary key of this j i r a project
	*/
	public void setPrimaryKey(long primaryKey) {
		_jiraProject.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the jira project ID of this j i r a project.
	*
	* @return the jira project ID of this j i r a project
	*/
	public long getJiraProjectId() {
		return _jiraProject.getJiraProjectId();
	}

	/**
	* Sets the jira project ID of this j i r a project.
	*
	* @param jiraProjectId the jira project ID of this j i r a project
	*/
	public void setJiraProjectId(long jiraProjectId) {
		_jiraProject.setJiraProjectId(jiraProjectId);
	}

	/**
	* Returns the key of this j i r a project.
	*
	* @return the key of this j i r a project
	*/
	public java.lang.String getKey() {
		return _jiraProject.getKey();
	}

	/**
	* Sets the key of this j i r a project.
	*
	* @param key the key of this j i r a project
	*/
	public void setKey(java.lang.String key) {
		_jiraProject.setKey(key);
	}

	/**
	* Returns the name of this j i r a project.
	*
	* @return the name of this j i r a project
	*/
	public java.lang.String getName() {
		return _jiraProject.getName();
	}

	/**
	* Sets the name of this j i r a project.
	*
	* @param name the name of this j i r a project
	*/
	public void setName(java.lang.String name) {
		_jiraProject.setName(name);
	}

	public boolean isNew() {
		return _jiraProject.isNew();
	}

	public void setNew(boolean n) {
		_jiraProject.setNew(n);
	}

	public boolean isCachedModel() {
		return _jiraProject.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_jiraProject.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _jiraProject.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _jiraProject.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_jiraProject.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _jiraProject.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_jiraProject.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new JIRAProjectWrapper((JIRAProject)_jiraProject.clone());
	}

	public int compareTo(com.liferay.socialcoding.model.JIRAProject jiraProject) {
		return _jiraProject.compareTo(jiraProject);
	}

	@Override
	public int hashCode() {
		return _jiraProject.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.socialcoding.model.JIRAProject> toCacheModel() {
		return _jiraProject.toCacheModel();
	}

	public com.liferay.socialcoding.model.JIRAProject toEscapedModel() {
		return new JIRAProjectWrapper(_jiraProject.toEscapedModel());
	}

	public com.liferay.socialcoding.model.JIRAProject toUnescapedModel() {
		return new JIRAProjectWrapper(_jiraProject.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _jiraProject.toString();
	}

	public java.lang.String toXmlString() {
		return _jiraProject.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_jiraProject.persist();
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

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public JIRAProject getWrappedJIRAProject() {
		return _jiraProject;
	}

	public JIRAProject getWrappedModel() {
		return _jiraProject;
	}

	public void resetOriginalValues() {
		_jiraProject.resetOriginalValues();
	}

	private JIRAProject _jiraProject;
}