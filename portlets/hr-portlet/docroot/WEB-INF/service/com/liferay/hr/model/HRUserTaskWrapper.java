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
 * This class is a wrapper for {@link HRUserTask}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HRUserTask
 * @generated
 */
public class HRUserTaskWrapper implements HRUserTask {
	public HRUserTaskWrapper(HRUserTask hrUserTask) {
		_hrUserTask = hrUserTask;
	}

	public Class<?> getModelClass() {
		return HRUserTask.class;
	}

	public String getModelClassName() {
		return HRUserTask.class.getName();
	}

	/**
	* Gets the primary key of this h r user task.
	*
	* @return the primary key of this h r user task
	*/
	public long getPrimaryKey() {
		return _hrUserTask.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r user task
	*
	* @param pk the primary key of this h r user task
	*/
	public void setPrimaryKey(long pk) {
		_hrUserTask.setPrimaryKey(pk);
	}

	/**
	* Gets the hr user task ID of this h r user task.
	*
	* @return the hr user task ID of this h r user task
	*/
	public long getHrUserTaskId() {
		return _hrUserTask.getHrUserTaskId();
	}

	/**
	* Sets the hr user task ID of this h r user task.
	*
	* @param hrUserTaskId the hr user task ID of this h r user task
	*/
	public void setHrUserTaskId(long hrUserTaskId) {
		_hrUserTask.setHrUserTaskId(hrUserTaskId);
	}

	/**
	* Gets the group ID of this h r user task.
	*
	* @return the group ID of this h r user task
	*/
	public long getGroupId() {
		return _hrUserTask.getGroupId();
	}

	/**
	* Sets the group ID of this h r user task.
	*
	* @param groupId the group ID of this h r user task
	*/
	public void setGroupId(long groupId) {
		_hrUserTask.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r user task.
	*
	* @return the company ID of this h r user task
	*/
	public long getCompanyId() {
		return _hrUserTask.getCompanyId();
	}

	/**
	* Sets the company ID of this h r user task.
	*
	* @param companyId the company ID of this h r user task
	*/
	public void setCompanyId(long companyId) {
		_hrUserTask.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r user task.
	*
	* @return the user ID of this h r user task
	*/
	public long getUserId() {
		return _hrUserTask.getUserId();
	}

	/**
	* Sets the user ID of this h r user task.
	*
	* @param userId the user ID of this h r user task
	*/
	public void setUserId(long userId) {
		_hrUserTask.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r user task.
	*
	* @return the user uuid of this h r user task
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrUserTask.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r user task.
	*
	* @param userUuid the user uuid of this h r user task
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrUserTask.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r user task.
	*
	* @return the user name of this h r user task
	*/
	public java.lang.String getUserName() {
		return _hrUserTask.getUserName();
	}

	/**
	* Sets the user name of this h r user task.
	*
	* @param userName the user name of this h r user task
	*/
	public void setUserName(java.lang.String userName) {
		_hrUserTask.setUserName(userName);
	}

	/**
	* Gets the create date of this h r user task.
	*
	* @return the create date of this h r user task
	*/
	public java.util.Date getCreateDate() {
		return _hrUserTask.getCreateDate();
	}

	/**
	* Sets the create date of this h r user task.
	*
	* @param createDate the create date of this h r user task
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrUserTask.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r user task.
	*
	* @return the modified date of this h r user task
	*/
	public java.util.Date getModifiedDate() {
		return _hrUserTask.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r user task.
	*
	* @param modifiedDate the modified date of this h r user task
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrUserTask.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the hr billability ID of this h r user task.
	*
	* @return the hr billability ID of this h r user task
	*/
	public long getHrBillabilityId() {
		return _hrUserTask.getHrBillabilityId();
	}

	/**
	* Sets the hr billability ID of this h r user task.
	*
	* @param hrBillabilityId the hr billability ID of this h r user task
	*/
	public void setHrBillabilityId(long hrBillabilityId) {
		_hrUserTask.setHrBillabilityId(hrBillabilityId);
	}

	/**
	* Gets the hr task ID of this h r user task.
	*
	* @return the hr task ID of this h r user task
	*/
	public long getHrTaskId() {
		return _hrUserTask.getHrTaskId();
	}

	/**
	* Sets the hr task ID of this h r user task.
	*
	* @param hrTaskId the hr task ID of this h r user task
	*/
	public void setHrTaskId(long hrTaskId) {
		_hrUserTask.setHrTaskId(hrTaskId);
	}

	/**
	* Gets the hr timesheet ID of this h r user task.
	*
	* @return the hr timesheet ID of this h r user task
	*/
	public long getHrTimesheetId() {
		return _hrUserTask.getHrTimesheetId();
	}

	/**
	* Sets the hr timesheet ID of this h r user task.
	*
	* @param hrTimesheetId the hr timesheet ID of this h r user task
	*/
	public void setHrTimesheetId(long hrTimesheetId) {
		_hrUserTask.setHrTimesheetId(hrTimesheetId);
	}

	/**
	* Gets the hr user ID of this h r user task.
	*
	* @return the hr user ID of this h r user task
	*/
	public long getHrUserId() {
		return _hrUserTask.getHrUserId();
	}

	/**
	* Sets the hr user ID of this h r user task.
	*
	* @param hrUserId the hr user ID of this h r user task
	*/
	public void setHrUserId(long hrUserId) {
		_hrUserTask.setHrUserId(hrUserId);
	}

	/**
	* Gets the hr user uuid of this h r user task.
	*
	* @return the hr user uuid of this h r user task
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getHrUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrUserTask.getHrUserUuid();
	}

	/**
	* Sets the hr user uuid of this h r user task.
	*
	* @param hrUserUuid the hr user uuid of this h r user task
	*/
	public void setHrUserUuid(java.lang.String hrUserUuid) {
		_hrUserTask.setHrUserUuid(hrUserUuid);
	}

	public boolean isNew() {
		return _hrUserTask.isNew();
	}

	public void setNew(boolean n) {
		_hrUserTask.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrUserTask.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrUserTask.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrUserTask.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrUserTask.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrUserTask.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrUserTask.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrUserTask.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRUserTaskWrapper((HRUserTask)_hrUserTask.clone());
	}

	public int compareTo(com.liferay.hr.model.HRUserTask hrUserTask) {
		return _hrUserTask.compareTo(hrUserTask);
	}

	public int hashCode() {
		return _hrUserTask.hashCode();
	}

	public com.liferay.hr.model.HRUserTask toEscapedModel() {
		return new HRUserTaskWrapper(_hrUserTask.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrUserTask.toString();
	}

	public java.lang.String toXmlString() {
		return _hrUserTask.toXmlString();
	}

	public HRUserTask getWrappedHRUserTask() {
		return _hrUserTask;
	}

	public void resetOriginalValues() {
		_hrUserTask.resetOriginalValues();
	}

	private HRUserTask _hrUserTask;
}