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

package com.liferay.hr.model;

/**
 * <p>
 * This class is a wrapper for {@link HRProject}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRProject
 * @generated
 */
public class HRProjectWrapper implements HRProject {
	public HRProjectWrapper(HRProject hrProject) {
		_hrProject = hrProject;
	}

	public Class<?> getModelClass() {
		return HRProject.class;
	}

	public String getModelClassName() {
		return HRProject.class.getName();
	}

	/**
	* Gets the primary key of this h r project.
	*
	* @return the primary key of this h r project
	*/
	public long getPrimaryKey() {
		return _hrProject.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r project
	*
	* @param primaryKey the primary key of this h r project
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrProject.setPrimaryKey(primaryKey);
	}

	/**
	* Gets the hr project ID of this h r project.
	*
	* @return the hr project ID of this h r project
	*/
	public long getHrProjectId() {
		return _hrProject.getHrProjectId();
	}

	/**
	* Sets the hr project ID of this h r project.
	*
	* @param hrProjectId the hr project ID of this h r project
	*/
	public void setHrProjectId(long hrProjectId) {
		_hrProject.setHrProjectId(hrProjectId);
	}

	/**
	* Gets the group ID of this h r project.
	*
	* @return the group ID of this h r project
	*/
	public long getGroupId() {
		return _hrProject.getGroupId();
	}

	/**
	* Sets the group ID of this h r project.
	*
	* @param groupId the group ID of this h r project
	*/
	public void setGroupId(long groupId) {
		_hrProject.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r project.
	*
	* @return the company ID of this h r project
	*/
	public long getCompanyId() {
		return _hrProject.getCompanyId();
	}

	/**
	* Sets the company ID of this h r project.
	*
	* @param companyId the company ID of this h r project
	*/
	public void setCompanyId(long companyId) {
		_hrProject.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r project.
	*
	* @return the user ID of this h r project
	*/
	public long getUserId() {
		return _hrProject.getUserId();
	}

	/**
	* Sets the user ID of this h r project.
	*
	* @param userId the user ID of this h r project
	*/
	public void setUserId(long userId) {
		_hrProject.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r project.
	*
	* @return the user uuid of this h r project
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrProject.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r project.
	*
	* @param userUuid the user uuid of this h r project
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrProject.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r project.
	*
	* @return the user name of this h r project
	*/
	public java.lang.String getUserName() {
		return _hrProject.getUserName();
	}

	/**
	* Sets the user name of this h r project.
	*
	* @param userName the user name of this h r project
	*/
	public void setUserName(java.lang.String userName) {
		_hrProject.setUserName(userName);
	}

	/**
	* Gets the create date of this h r project.
	*
	* @return the create date of this h r project
	*/
	public java.util.Date getCreateDate() {
		return _hrProject.getCreateDate();
	}

	/**
	* Sets the create date of this h r project.
	*
	* @param createDate the create date of this h r project
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrProject.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r project.
	*
	* @return the modified date of this h r project
	*/
	public java.util.Date getModifiedDate() {
		return _hrProject.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r project.
	*
	* @param modifiedDate the modified date of this h r project
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrProject.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the hr client ID of this h r project.
	*
	* @return the hr client ID of this h r project
	*/
	public long getHrClientId() {
		return _hrProject.getHrClientId();
	}

	/**
	* Sets the hr client ID of this h r project.
	*
	* @param hrClientId the hr client ID of this h r project
	*/
	public void setHrClientId(long hrClientId) {
		_hrProject.setHrClientId(hrClientId);
	}

	/**
	* Gets the hr project status ID of this h r project.
	*
	* @return the hr project status ID of this h r project
	*/
	public long getHrProjectStatusId() {
		return _hrProject.getHrProjectStatusId();
	}

	/**
	* Sets the hr project status ID of this h r project.
	*
	* @param hrProjectStatusId the hr project status ID of this h r project
	*/
	public void setHrProjectStatusId(long hrProjectStatusId) {
		_hrProject.setHrProjectStatusId(hrProjectStatusId);
	}

	/**
	* Gets the name of this h r project.
	*
	* @return the name of this h r project
	*/
	public java.lang.String getName() {
		return _hrProject.getName();
	}

	/**
	* Sets the name of this h r project.
	*
	* @param name the name of this h r project
	*/
	public void setName(java.lang.String name) {
		_hrProject.setName(name);
	}

	/**
	* Gets the description of this h r project.
	*
	* @return the description of this h r project
	*/
	public java.lang.String getDescription() {
		return _hrProject.getDescription();
	}

	/**
	* Sets the description of this h r project.
	*
	* @param description the description of this h r project
	*/
	public void setDescription(java.lang.String description) {
		_hrProject.setDescription(description);
	}

	/**
	* Gets the estimated start date of this h r project.
	*
	* @return the estimated start date of this h r project
	*/
	public java.util.Date getEstimatedStartDate() {
		return _hrProject.getEstimatedStartDate();
	}

	/**
	* Sets the estimated start date of this h r project.
	*
	* @param estimatedStartDate the estimated start date of this h r project
	*/
	public void setEstimatedStartDate(java.util.Date estimatedStartDate) {
		_hrProject.setEstimatedStartDate(estimatedStartDate);
	}

	/**
	* Gets the estimated end date of this h r project.
	*
	* @return the estimated end date of this h r project
	*/
	public java.util.Date getEstimatedEndDate() {
		return _hrProject.getEstimatedEndDate();
	}

	/**
	* Sets the estimated end date of this h r project.
	*
	* @param estimatedEndDate the estimated end date of this h r project
	*/
	public void setEstimatedEndDate(java.util.Date estimatedEndDate) {
		_hrProject.setEstimatedEndDate(estimatedEndDate);
	}

	/**
	* Gets the estimated hours of this h r project.
	*
	* @return the estimated hours of this h r project
	*/
	public double getEstimatedHours() {
		return _hrProject.getEstimatedHours();
	}

	/**
	* Sets the estimated hours of this h r project.
	*
	* @param estimatedHours the estimated hours of this h r project
	*/
	public void setEstimatedHours(double estimatedHours) {
		_hrProject.setEstimatedHours(estimatedHours);
	}

	/**
	* Gets the estimated hours cost of this h r project.
	*
	* @return the estimated hours cost of this h r project
	*/
	public double getEstimatedHoursCost() {
		return _hrProject.getEstimatedHoursCost();
	}

	/**
	* Sets the estimated hours cost of this h r project.
	*
	* @param estimatedHoursCost the estimated hours cost of this h r project
	*/
	public void setEstimatedHoursCost(double estimatedHoursCost) {
		_hrProject.setEstimatedHoursCost(estimatedHoursCost);
	}

	/**
	* Gets the estimated hours cost currency code of this h r project.
	*
	* @return the estimated hours cost currency code of this h r project
	*/
	public java.lang.String getEstimatedHoursCostCurrencyCode() {
		return _hrProject.getEstimatedHoursCostCurrencyCode();
	}

	/**
	* Sets the estimated hours cost currency code of this h r project.
	*
	* @param estimatedHoursCostCurrencyCode the estimated hours cost currency code of this h r project
	*/
	public void setEstimatedHoursCostCurrencyCode(
		java.lang.String estimatedHoursCostCurrencyCode) {
		_hrProject.setEstimatedHoursCostCurrencyCode(estimatedHoursCostCurrencyCode);
	}

	/**
	* Gets the estimated expenses of this h r project.
	*
	* @return the estimated expenses of this h r project
	*/
	public double getEstimatedExpenses() {
		return _hrProject.getEstimatedExpenses();
	}

	/**
	* Sets the estimated expenses of this h r project.
	*
	* @param estimatedExpenses the estimated expenses of this h r project
	*/
	public void setEstimatedExpenses(double estimatedExpenses) {
		_hrProject.setEstimatedExpenses(estimatedExpenses);
	}

	/**
	* Gets the estimated expenses currency code of this h r project.
	*
	* @return the estimated expenses currency code of this h r project
	*/
	public java.lang.String getEstimatedExpensesCurrencyCode() {
		return _hrProject.getEstimatedExpensesCurrencyCode();
	}

	/**
	* Sets the estimated expenses currency code of this h r project.
	*
	* @param estimatedExpensesCurrencyCode the estimated expenses currency code of this h r project
	*/
	public void setEstimatedExpensesCurrencyCode(
		java.lang.String estimatedExpensesCurrencyCode) {
		_hrProject.setEstimatedExpensesCurrencyCode(estimatedExpensesCurrencyCode);
	}

	/**
	* Gets the actual start date of this h r project.
	*
	* @return the actual start date of this h r project
	*/
	public java.util.Date getActualStartDate() {
		return _hrProject.getActualStartDate();
	}

	/**
	* Sets the actual start date of this h r project.
	*
	* @param actualStartDate the actual start date of this h r project
	*/
	public void setActualStartDate(java.util.Date actualStartDate) {
		_hrProject.setActualStartDate(actualStartDate);
	}

	/**
	* Gets the actual end date of this h r project.
	*
	* @return the actual end date of this h r project
	*/
	public java.util.Date getActualEndDate() {
		return _hrProject.getActualEndDate();
	}

	/**
	* Sets the actual end date of this h r project.
	*
	* @param actualEndDate the actual end date of this h r project
	*/
	public void setActualEndDate(java.util.Date actualEndDate) {
		_hrProject.setActualEndDate(actualEndDate);
	}

	/**
	* Gets the actual hours of this h r project.
	*
	* @return the actual hours of this h r project
	*/
	public double getActualHours() {
		return _hrProject.getActualHours();
	}

	/**
	* Sets the actual hours of this h r project.
	*
	* @param actualHours the actual hours of this h r project
	*/
	public void setActualHours(double actualHours) {
		_hrProject.setActualHours(actualHours);
	}

	/**
	* Gets the actual hours cost of this h r project.
	*
	* @return the actual hours cost of this h r project
	*/
	public double getActualHoursCost() {
		return _hrProject.getActualHoursCost();
	}

	/**
	* Sets the actual hours cost of this h r project.
	*
	* @param actualHoursCost the actual hours cost of this h r project
	*/
	public void setActualHoursCost(double actualHoursCost) {
		_hrProject.setActualHoursCost(actualHoursCost);
	}

	/**
	* Gets the actual hours cost currency code of this h r project.
	*
	* @return the actual hours cost currency code of this h r project
	*/
	public java.lang.String getActualHoursCostCurrencyCode() {
		return _hrProject.getActualHoursCostCurrencyCode();
	}

	/**
	* Sets the actual hours cost currency code of this h r project.
	*
	* @param actualHoursCostCurrencyCode the actual hours cost currency code of this h r project
	*/
	public void setActualHoursCostCurrencyCode(
		java.lang.String actualHoursCostCurrencyCode) {
		_hrProject.setActualHoursCostCurrencyCode(actualHoursCostCurrencyCode);
	}

	/**
	* Gets the actual expenses of this h r project.
	*
	* @return the actual expenses of this h r project
	*/
	public double getActualExpenses() {
		return _hrProject.getActualExpenses();
	}

	/**
	* Sets the actual expenses of this h r project.
	*
	* @param actualExpenses the actual expenses of this h r project
	*/
	public void setActualExpenses(double actualExpenses) {
		_hrProject.setActualExpenses(actualExpenses);
	}

	/**
	* Gets the actual expenses currency code of this h r project.
	*
	* @return the actual expenses currency code of this h r project
	*/
	public double getActualExpensesCurrencyCode() {
		return _hrProject.getActualExpensesCurrencyCode();
	}

	/**
	* Sets the actual expenses currency code of this h r project.
	*
	* @param actualExpensesCurrencyCode the actual expenses currency code of this h r project
	*/
	public void setActualExpensesCurrencyCode(double actualExpensesCurrencyCode) {
		_hrProject.setActualExpensesCurrencyCode(actualExpensesCurrencyCode);
	}

	public boolean isNew() {
		return _hrProject.isNew();
	}

	public void setNew(boolean n) {
		_hrProject.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrProject.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrProject.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrProject.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrProject.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrProject.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrProject.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrProject.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrProject.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRProjectWrapper((HRProject)_hrProject.clone());
	}

	public int compareTo(com.liferay.hr.model.HRProject hrProject) {
		return _hrProject.compareTo(hrProject);
	}

	public int hashCode() {
		return _hrProject.hashCode();
	}

	public com.liferay.hr.model.HRProject toEscapedModel() {
		return new HRProjectWrapper(_hrProject.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrProject.toString();
	}

	public java.lang.String toXmlString() {
		return _hrProject.toXmlString();
	}

	public HRProject getWrappedHRProject() {
		return _hrProject;
	}

	public void resetOriginalValues() {
		_hrProject.resetOriginalValues();
	}

	private HRProject _hrProject;
}