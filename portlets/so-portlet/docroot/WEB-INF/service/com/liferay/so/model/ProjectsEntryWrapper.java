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

package com.liferay.so.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProjectsEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProjectsEntry
 * @generated
 */
@ProviderType
public class ProjectsEntryWrapper implements ProjectsEntry,
	ModelWrapper<ProjectsEntry> {
	public ProjectsEntryWrapper(ProjectsEntry projectsEntry) {
		_projectsEntry = projectsEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return ProjectsEntry.class;
	}

	@Override
	public String getModelClassName() {
		return ProjectsEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("projectsEntryId", getProjectsEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("data", getData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long projectsEntryId = (Long)attributes.get("projectsEntryId");

		if (projectsEntryId != null) {
			setProjectsEntryId(projectsEntryId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String data = (String)attributes.get("data");

		if (data != null) {
			setData(data);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectsEntryWrapper((ProjectsEntry)_projectsEntry.clone());
	}

	@Override
	public int compareTo(com.liferay.so.model.ProjectsEntry projectsEntry) {
		return _projectsEntry.compareTo(projectsEntry);
	}

	/**
	* Returns the company ID of this projects entry.
	*
	* @return the company ID of this projects entry
	*/
	@Override
	public long getCompanyId() {
		return _projectsEntry.getCompanyId();
	}

	/**
	* Returns the create date of this projects entry.
	*
	* @return the create date of this projects entry
	*/
	@Override
	public Date getCreateDate() {
		return _projectsEntry.getCreateDate();
	}

	/**
	* Returns the data of this projects entry.
	*
	* @return the data of this projects entry
	*/
	@Override
	public java.lang.String getData() {
		return _projectsEntry.getData();
	}

	/**
	* Returns the description of this projects entry.
	*
	* @return the description of this projects entry
	*/
	@Override
	public java.lang.String getDescription() {
		return _projectsEntry.getDescription();
	}

	/**
	* Returns the end date of this projects entry.
	*
	* @return the end date of this projects entry
	*/
	@Override
	public Date getEndDate() {
		return _projectsEntry.getEndDate();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _projectsEntry.getExpandoBridge();
	}

	/**
	* Returns the modified date of this projects entry.
	*
	* @return the modified date of this projects entry
	*/
	@Override
	public Date getModifiedDate() {
		return _projectsEntry.getModifiedDate();
	}

	/**
	* Returns the primary key of this projects entry.
	*
	* @return the primary key of this projects entry
	*/
	@Override
	public long getPrimaryKey() {
		return _projectsEntry.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _projectsEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the projects entry ID of this projects entry.
	*
	* @return the projects entry ID of this projects entry
	*/
	@Override
	public long getProjectsEntryId() {
		return _projectsEntry.getProjectsEntryId();
	}

	/**
	* Returns the start date of this projects entry.
	*
	* @return the start date of this projects entry
	*/
	@Override
	public Date getStartDate() {
		return _projectsEntry.getStartDate();
	}

	/**
	* Returns the title of this projects entry.
	*
	* @return the title of this projects entry
	*/
	@Override
	public java.lang.String getTitle() {
		return _projectsEntry.getTitle();
	}

	/**
	* Returns the user ID of this projects entry.
	*
	* @return the user ID of this projects entry
	*/
	@Override
	public long getUserId() {
		return _projectsEntry.getUserId();
	}

	/**
	* Returns the user name of this projects entry.
	*
	* @return the user name of this projects entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _projectsEntry.getUserName();
	}

	/**
	* Returns the user uuid of this projects entry.
	*
	* @return the user uuid of this projects entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _projectsEntry.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _projectsEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _projectsEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _projectsEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _projectsEntry.isNew();
	}

	@Override
	public void persist() {
		_projectsEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_projectsEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this projects entry.
	*
	* @param companyId the company ID of this projects entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_projectsEntry.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this projects entry.
	*
	* @param createDate the create date of this projects entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_projectsEntry.setCreateDate(createDate);
	}

	/**
	* Sets the data of this projects entry.
	*
	* @param data the data of this projects entry
	*/
	@Override
	public void setData(java.lang.String data) {
		_projectsEntry.setData(data);
	}

	/**
	* Sets the description of this projects entry.
	*
	* @param description the description of this projects entry
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_projectsEntry.setDescription(description);
	}

	/**
	* Sets the end date of this projects entry.
	*
	* @param endDate the end date of this projects entry
	*/
	@Override
	public void setEndDate(Date endDate) {
		_projectsEntry.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_projectsEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_projectsEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_projectsEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this projects entry.
	*
	* @param modifiedDate the modified date of this projects entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_projectsEntry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_projectsEntry.setNew(n);
	}

	/**
	* Sets the primary key of this projects entry.
	*
	* @param primaryKey the primary key of this projects entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_projectsEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_projectsEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projects entry ID of this projects entry.
	*
	* @param projectsEntryId the projects entry ID of this projects entry
	*/
	@Override
	public void setProjectsEntryId(long projectsEntryId) {
		_projectsEntry.setProjectsEntryId(projectsEntryId);
	}

	/**
	* Sets the start date of this projects entry.
	*
	* @param startDate the start date of this projects entry
	*/
	@Override
	public void setStartDate(Date startDate) {
		_projectsEntry.setStartDate(startDate);
	}

	/**
	* Sets the title of this projects entry.
	*
	* @param title the title of this projects entry
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_projectsEntry.setTitle(title);
	}

	/**
	* Sets the user ID of this projects entry.
	*
	* @param userId the user ID of this projects entry
	*/
	@Override
	public void setUserId(long userId) {
		_projectsEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this projects entry.
	*
	* @param userName the user name of this projects entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_projectsEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this projects entry.
	*
	* @param userUuid the user uuid of this projects entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_projectsEntry.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.so.model.ProjectsEntry> toCacheModel() {
		return _projectsEntry.toCacheModel();
	}

	@Override
	public com.liferay.so.model.ProjectsEntry toEscapedModel() {
		return new ProjectsEntryWrapper(_projectsEntry.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _projectsEntry.toString();
	}

	@Override
	public com.liferay.so.model.ProjectsEntry toUnescapedModel() {
		return new ProjectsEntryWrapper(_projectsEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _projectsEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectsEntryWrapper)) {
			return false;
		}

		ProjectsEntryWrapper projectsEntryWrapper = (ProjectsEntryWrapper)obj;

		if (Validator.equals(_projectsEntry, projectsEntryWrapper._projectsEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public ProjectsEntry getWrappedModel() {
		return _projectsEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _projectsEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _projectsEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_projectsEntry.resetOriginalValues();
	}

	private final ProjectsEntry _projectsEntry;
}