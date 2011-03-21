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

package com.liferay.so.model;

/**
 * <p>
 * This class is a wrapper for {@link ProjectsEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProjectsEntry
 * @generated
 */
public class ProjectsEntryWrapper implements ProjectsEntry {
	public ProjectsEntryWrapper(ProjectsEntry projectsEntry) {
		_projectsEntry = projectsEntry;
	}

	public Class<?> getModelClass() {
		return ProjectsEntry.class;
	}

	public String getModelClassName() {
		return ProjectsEntry.class.getName();
	}

	/**
	* Gets the primary key of this projects entry.
	*
	* @return the primary key of this projects entry
	*/
	public long getPrimaryKey() {
		return _projectsEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this projects entry
	*
	* @param pk the primary key of this projects entry
	*/
	public void setPrimaryKey(long pk) {
		_projectsEntry.setPrimaryKey(pk);
	}

	/**
	* Gets the projects entry ID of this projects entry.
	*
	* @return the projects entry ID of this projects entry
	*/
	public long getProjectsEntryId() {
		return _projectsEntry.getProjectsEntryId();
	}

	/**
	* Sets the projects entry ID of this projects entry.
	*
	* @param projectsEntryId the projects entry ID of this projects entry
	*/
	public void setProjectsEntryId(long projectsEntryId) {
		_projectsEntry.setProjectsEntryId(projectsEntryId);
	}

	/**
	* Gets the company ID of this projects entry.
	*
	* @return the company ID of this projects entry
	*/
	public long getCompanyId() {
		return _projectsEntry.getCompanyId();
	}

	/**
	* Sets the company ID of this projects entry.
	*
	* @param companyId the company ID of this projects entry
	*/
	public void setCompanyId(long companyId) {
		_projectsEntry.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this projects entry.
	*
	* @return the user ID of this projects entry
	*/
	public long getUserId() {
		return _projectsEntry.getUserId();
	}

	/**
	* Sets the user ID of this projects entry.
	*
	* @param userId the user ID of this projects entry
	*/
	public void setUserId(long userId) {
		_projectsEntry.setUserId(userId);
	}

	/**
	* Gets the user uuid of this projects entry.
	*
	* @return the user uuid of this projects entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this projects entry.
	*
	* @param userUuid the user uuid of this projects entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_projectsEntry.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this projects entry.
	*
	* @return the user name of this projects entry
	*/
	public java.lang.String getUserName() {
		return _projectsEntry.getUserName();
	}

	/**
	* Sets the user name of this projects entry.
	*
	* @param userName the user name of this projects entry
	*/
	public void setUserName(java.lang.String userName) {
		_projectsEntry.setUserName(userName);
	}

	/**
	* Gets the create date of this projects entry.
	*
	* @return the create date of this projects entry
	*/
	public java.util.Date getCreateDate() {
		return _projectsEntry.getCreateDate();
	}

	/**
	* Sets the create date of this projects entry.
	*
	* @param createDate the create date of this projects entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_projectsEntry.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this projects entry.
	*
	* @return the modified date of this projects entry
	*/
	public java.util.Date getModifiedDate() {
		return _projectsEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this projects entry.
	*
	* @param modifiedDate the modified date of this projects entry
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_projectsEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the title of this projects entry.
	*
	* @return the title of this projects entry
	*/
	public java.lang.String getTitle() {
		return _projectsEntry.getTitle();
	}

	/**
	* Sets the title of this projects entry.
	*
	* @param title the title of this projects entry
	*/
	public void setTitle(java.lang.String title) {
		_projectsEntry.setTitle(title);
	}

	/**
	* Gets the description of this projects entry.
	*
	* @return the description of this projects entry
	*/
	public java.lang.String getDescription() {
		return _projectsEntry.getDescription();
	}

	/**
	* Sets the description of this projects entry.
	*
	* @param description the description of this projects entry
	*/
	public void setDescription(java.lang.String description) {
		_projectsEntry.setDescription(description);
	}

	/**
	* Gets the start date of this projects entry.
	*
	* @return the start date of this projects entry
	*/
	public java.util.Date getStartDate() {
		return _projectsEntry.getStartDate();
	}

	/**
	* Sets the start date of this projects entry.
	*
	* @param startDate the start date of this projects entry
	*/
	public void setStartDate(java.util.Date startDate) {
		_projectsEntry.setStartDate(startDate);
	}

	/**
	* Gets the end date of this projects entry.
	*
	* @return the end date of this projects entry
	*/
	public java.util.Date getEndDate() {
		return _projectsEntry.getEndDate();
	}

	/**
	* Sets the end date of this projects entry.
	*
	* @param endDate the end date of this projects entry
	*/
	public void setEndDate(java.util.Date endDate) {
		_projectsEntry.setEndDate(endDate);
	}

	/**
	* Gets the data of this projects entry.
	*
	* @return the data of this projects entry
	*/
	public java.lang.String getData() {
		return _projectsEntry.getData();
	}

	/**
	* Sets the data of this projects entry.
	*
	* @param data the data of this projects entry
	*/
	public void setData(java.lang.String data) {
		_projectsEntry.setData(data);
	}

	public boolean isNew() {
		return _projectsEntry.isNew();
	}

	public void setNew(boolean n) {
		_projectsEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _projectsEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_projectsEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _projectsEntry.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_projectsEntry.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _projectsEntry.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _projectsEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_projectsEntry.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new ProjectsEntryWrapper((ProjectsEntry)_projectsEntry.clone());
	}

	public int compareTo(com.liferay.so.model.ProjectsEntry projectsEntry) {
		return _projectsEntry.compareTo(projectsEntry);
	}

	public int hashCode() {
		return _projectsEntry.hashCode();
	}

	public com.liferay.so.model.ProjectsEntry toEscapedModel() {
		return new ProjectsEntryWrapper(_projectsEntry.toEscapedModel());
	}

	public java.lang.String toString() {
		return _projectsEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _projectsEntry.toXmlString();
	}

	public ProjectsEntry getWrappedProjectsEntry() {
		return _projectsEntry;
	}

	private ProjectsEntry _projectsEntry;
}