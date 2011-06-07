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
 * This class is a wrapper for {@link HRTask}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRTask
 * @generated
 */
public class HRTaskWrapper implements HRTask {
	public HRTaskWrapper(HRTask hrTask) {
		_hrTask = hrTask;
	}

	public Class<?> getModelClass() {
		return HRTask.class;
	}

	public String getModelClassName() {
		return HRTask.class.getName();
	}

	/**
	* Returns the primary key of this h r task.
	*
	* @return the primary key of this h r task
	*/
	public long getPrimaryKey() {
		return _hrTask.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r task.
	*
	* @param primaryKey the primary key of this h r task
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrTask.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr task ID of this h r task.
	*
	* @return the hr task ID of this h r task
	*/
	public long getHrTaskId() {
		return _hrTask.getHrTaskId();
	}

	/**
	* Sets the hr task ID of this h r task.
	*
	* @param hrTaskId the hr task ID of this h r task
	*/
	public void setHrTaskId(long hrTaskId) {
		_hrTask.setHrTaskId(hrTaskId);
	}

	/**
	* Returns the group ID of this h r task.
	*
	* @return the group ID of this h r task
	*/
	public long getGroupId() {
		return _hrTask.getGroupId();
	}

	/**
	* Sets the group ID of this h r task.
	*
	* @param groupId the group ID of this h r task
	*/
	public void setGroupId(long groupId) {
		_hrTask.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r task.
	*
	* @return the company ID of this h r task
	*/
	public long getCompanyId() {
		return _hrTask.getCompanyId();
	}

	/**
	* Sets the company ID of this h r task.
	*
	* @param companyId the company ID of this h r task
	*/
	public void setCompanyId(long companyId) {
		_hrTask.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r task.
	*
	* @return the user ID of this h r task
	*/
	public long getUserId() {
		return _hrTask.getUserId();
	}

	/**
	* Sets the user ID of this h r task.
	*
	* @param userId the user ID of this h r task
	*/
	public void setUserId(long userId) {
		_hrTask.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r task.
	*
	* @return the user uuid of this h r task
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTask.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r task.
	*
	* @param userUuid the user uuid of this h r task
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrTask.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r task.
	*
	* @return the user name of this h r task
	*/
	public java.lang.String getUserName() {
		return _hrTask.getUserName();
	}

	/**
	* Sets the user name of this h r task.
	*
	* @param userName the user name of this h r task
	*/
	public void setUserName(java.lang.String userName) {
		_hrTask.setUserName(userName);
	}

	/**
	* Returns the create date of this h r task.
	*
	* @return the create date of this h r task
	*/
	public java.util.Date getCreateDate() {
		return _hrTask.getCreateDate();
	}

	/**
	* Sets the create date of this h r task.
	*
	* @param createDate the create date of this h r task
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrTask.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r task.
	*
	* @return the modified date of this h r task
	*/
	public java.util.Date getModifiedDate() {
		return _hrTask.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r task.
	*
	* @param modifiedDate the modified date of this h r task
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrTask.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the hr billability ID of this h r task.
	*
	* @return the hr billability ID of this h r task
	*/
	public long getHrBillabilityId() {
		return _hrTask.getHrBillabilityId();
	}

	/**
	* Sets the hr billability ID of this h r task.
	*
	* @param hrBillabilityId the hr billability ID of this h r task
	*/
	public void setHrBillabilityId(long hrBillabilityId) {
		_hrTask.setHrBillabilityId(hrBillabilityId);
	}

	/**
	* Returns the hr project ID of this h r task.
	*
	* @return the hr project ID of this h r task
	*/
	public long getHrProjectId() {
		return _hrTask.getHrProjectId();
	}

	/**
	* Sets the hr project ID of this h r task.
	*
	* @param hrProjectId the hr project ID of this h r task
	*/
	public void setHrProjectId(long hrProjectId) {
		_hrTask.setHrProjectId(hrProjectId);
	}

	/**
	* Returns the hr task status ID of this h r task.
	*
	* @return the hr task status ID of this h r task
	*/
	public long getHrTaskStatusId() {
		return _hrTask.getHrTaskStatusId();
	}

	/**
	* Sets the hr task status ID of this h r task.
	*
	* @param hrTaskStatusId the hr task status ID of this h r task
	*/
	public void setHrTaskStatusId(long hrTaskStatusId) {
		_hrTask.setHrTaskStatusId(hrTaskStatusId);
	}

	/**
	* Returns the parent h r task ID of this h r task.
	*
	* @return the parent h r task ID of this h r task
	*/
	public long getParentHRTaskId() {
		return _hrTask.getParentHRTaskId();
	}

	/**
	* Sets the parent h r task ID of this h r task.
	*
	* @param parentHRTaskId the parent h r task ID of this h r task
	*/
	public void setParentHRTaskId(long parentHRTaskId) {
		_hrTask.setParentHRTaskId(parentHRTaskId);
	}

	/**
	* Returns the name of this h r task.
	*
	* @return the name of this h r task
	*/
	public java.lang.String getName() {
		return _hrTask.getName();
	}

	/**
	* Sets the name of this h r task.
	*
	* @param name the name of this h r task
	*/
	public void setName(java.lang.String name) {
		_hrTask.setName(name);
	}

	/**
	* Returns the description of this h r task.
	*
	* @return the description of this h r task
	*/
	public java.lang.String getDescription() {
		return _hrTask.getDescription();
	}

	/**
	* Sets the description of this h r task.
	*
	* @param description the description of this h r task
	*/
	public void setDescription(java.lang.String description) {
		_hrTask.setDescription(description);
	}

	/**
	* Returns the estimated start date of this h r task.
	*
	* @return the estimated start date of this h r task
	*/
	public java.util.Date getEstimatedStartDate() {
		return _hrTask.getEstimatedStartDate();
	}

	/**
	* Sets the estimated start date of this h r task.
	*
	* @param estimatedStartDate the estimated start date of this h r task
	*/
	public void setEstimatedStartDate(java.util.Date estimatedStartDate) {
		_hrTask.setEstimatedStartDate(estimatedStartDate);
	}

	/**
	* Returns the estimated end date of this h r task.
	*
	* @return the estimated end date of this h r task
	*/
	public java.util.Date getEstimatedEndDate() {
		return _hrTask.getEstimatedEndDate();
	}

	/**
	* Sets the estimated end date of this h r task.
	*
	* @param estimatedEndDate the estimated end date of this h r task
	*/
	public void setEstimatedEndDate(java.util.Date estimatedEndDate) {
		_hrTask.setEstimatedEndDate(estimatedEndDate);
	}

	/**
	* Returns the estimated hours of this h r task.
	*
	* @return the estimated hours of this h r task
	*/
	public double getEstimatedHours() {
		return _hrTask.getEstimatedHours();
	}

	/**
	* Sets the estimated hours of this h r task.
	*
	* @param estimatedHours the estimated hours of this h r task
	*/
	public void setEstimatedHours(double estimatedHours) {
		_hrTask.setEstimatedHours(estimatedHours);
	}

	/**
	* Returns the estimated hours cost of this h r task.
	*
	* @return the estimated hours cost of this h r task
	*/
	public double getEstimatedHoursCost() {
		return _hrTask.getEstimatedHoursCost();
	}

	/**
	* Sets the estimated hours cost of this h r task.
	*
	* @param estimatedHoursCost the estimated hours cost of this h r task
	*/
	public void setEstimatedHoursCost(double estimatedHoursCost) {
		_hrTask.setEstimatedHoursCost(estimatedHoursCost);
	}

	/**
	* Returns the estimated hours cost currency code of this h r task.
	*
	* @return the estimated hours cost currency code of this h r task
	*/
	public java.lang.String getEstimatedHoursCostCurrencyCode() {
		return _hrTask.getEstimatedHoursCostCurrencyCode();
	}

	/**
	* Sets the estimated hours cost currency code of this h r task.
	*
	* @param estimatedHoursCostCurrencyCode the estimated hours cost currency code of this h r task
	*/
	public void setEstimatedHoursCostCurrencyCode(
		java.lang.String estimatedHoursCostCurrencyCode) {
		_hrTask.setEstimatedHoursCostCurrencyCode(estimatedHoursCostCurrencyCode);
	}

	/**
	* Returns the estimated expenses of this h r task.
	*
	* @return the estimated expenses of this h r task
	*/
	public double getEstimatedExpenses() {
		return _hrTask.getEstimatedExpenses();
	}

	/**
	* Sets the estimated expenses of this h r task.
	*
	* @param estimatedExpenses the estimated expenses of this h r task
	*/
	public void setEstimatedExpenses(double estimatedExpenses) {
		_hrTask.setEstimatedExpenses(estimatedExpenses);
	}

	/**
	* Returns the estimated expenses currency code of this h r task.
	*
	* @return the estimated expenses currency code of this h r task
	*/
	public java.lang.String getEstimatedExpensesCurrencyCode() {
		return _hrTask.getEstimatedExpensesCurrencyCode();
	}

	/**
	* Sets the estimated expenses currency code of this h r task.
	*
	* @param estimatedExpensesCurrencyCode the estimated expenses currency code of this h r task
	*/
	public void setEstimatedExpensesCurrencyCode(
		java.lang.String estimatedExpensesCurrencyCode) {
		_hrTask.setEstimatedExpensesCurrencyCode(estimatedExpensesCurrencyCode);
	}

	/**
	* Returns the actual start date of this h r task.
	*
	* @return the actual start date of this h r task
	*/
	public java.util.Date getActualStartDate() {
		return _hrTask.getActualStartDate();
	}

	/**
	* Sets the actual start date of this h r task.
	*
	* @param actualStartDate the actual start date of this h r task
	*/
	public void setActualStartDate(java.util.Date actualStartDate) {
		_hrTask.setActualStartDate(actualStartDate);
	}

	/**
	* Returns the actual end date of this h r task.
	*
	* @return the actual end date of this h r task
	*/
	public java.util.Date getActualEndDate() {
		return _hrTask.getActualEndDate();
	}

	/**
	* Sets the actual end date of this h r task.
	*
	* @param actualEndDate the actual end date of this h r task
	*/
	public void setActualEndDate(java.util.Date actualEndDate) {
		_hrTask.setActualEndDate(actualEndDate);
	}

	/**
	* Returns the actual hours of this h r task.
	*
	* @return the actual hours of this h r task
	*/
	public double getActualHours() {
		return _hrTask.getActualHours();
	}

	/**
	* Sets the actual hours of this h r task.
	*
	* @param actualHours the actual hours of this h r task
	*/
	public void setActualHours(double actualHours) {
		_hrTask.setActualHours(actualHours);
	}

	/**
	* Returns the actual hours cost of this h r task.
	*
	* @return the actual hours cost of this h r task
	*/
	public double getActualHoursCost() {
		return _hrTask.getActualHoursCost();
	}

	/**
	* Sets the actual hours cost of this h r task.
	*
	* @param actualHoursCost the actual hours cost of this h r task
	*/
	public void setActualHoursCost(double actualHoursCost) {
		_hrTask.setActualHoursCost(actualHoursCost);
	}

	/**
	* Returns the actual hours cost currency code of this h r task.
	*
	* @return the actual hours cost currency code of this h r task
	*/
	public java.lang.String getActualHoursCostCurrencyCode() {
		return _hrTask.getActualHoursCostCurrencyCode();
	}

	/**
	* Sets the actual hours cost currency code of this h r task.
	*
	* @param actualHoursCostCurrencyCode the actual hours cost currency code of this h r task
	*/
	public void setActualHoursCostCurrencyCode(
		java.lang.String actualHoursCostCurrencyCode) {
		_hrTask.setActualHoursCostCurrencyCode(actualHoursCostCurrencyCode);
	}

	/**
	* Returns the actual expenses of this h r task.
	*
	* @return the actual expenses of this h r task
	*/
	public double getActualExpenses() {
		return _hrTask.getActualExpenses();
	}

	/**
	* Sets the actual expenses of this h r task.
	*
	* @param actualExpenses the actual expenses of this h r task
	*/
	public void setActualExpenses(double actualExpenses) {
		_hrTask.setActualExpenses(actualExpenses);
	}

	/**
	* Returns the actual expenses currency code of this h r task.
	*
	* @return the actual expenses currency code of this h r task
	*/
	public java.lang.String getActualExpensesCurrencyCode() {
		return _hrTask.getActualExpensesCurrencyCode();
	}

	/**
	* Sets the actual expenses currency code of this h r task.
	*
	* @param actualExpensesCurrencyCode the actual expenses currency code of this h r task
	*/
	public void setActualExpensesCurrencyCode(
		java.lang.String actualExpensesCurrencyCode) {
		_hrTask.setActualExpensesCurrencyCode(actualExpensesCurrencyCode);
	}

	public boolean isNew() {
		return _hrTask.isNew();
	}

	public void setNew(boolean n) {
		_hrTask.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrTask.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrTask.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrTask.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrTask.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrTask.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrTask.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrTask.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrTask.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRTaskWrapper((HRTask)_hrTask.clone());
	}

	public int compareTo(com.liferay.hr.model.HRTask hrTask) {
		return _hrTask.compareTo(hrTask);
	}

	public int hashCode() {
		return _hrTask.hashCode();
	}

	public com.liferay.hr.model.HRTask toEscapedModel() {
		return new HRTaskWrapper(_hrTask.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrTask.toString();
	}

	public java.lang.String toXmlString() {
		return _hrTask.toXmlString();
	}

	public HRTask getWrappedHRTask() {
		return _hrTask;
	}

	public void resetOriginalValues() {
		_hrTask.resetOriginalValues();
	}

	private HRTask _hrTask;
}