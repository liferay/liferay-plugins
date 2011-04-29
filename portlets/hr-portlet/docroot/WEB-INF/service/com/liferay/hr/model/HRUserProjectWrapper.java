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
 * This class is a wrapper for {@link HRUserProject}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HRUserProject
 * @generated
 */
public class HRUserProjectWrapper implements HRUserProject {
	public HRUserProjectWrapper(HRUserProject hrUserProject) {
		_hrUserProject = hrUserProject;
	}

	public Class<?> getModelClass() {
		return HRUserProject.class;
	}

	public String getModelClassName() {
		return HRUserProject.class.getName();
	}

	/**
	* Gets the primary key of this h r user project.
	*
	* @return the primary key of this h r user project
	*/
	public long getPrimaryKey() {
		return _hrUserProject.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r user project
	*
	* @param pk the primary key of this h r user project
	*/
	public void setPrimaryKey(long pk) {
		_hrUserProject.setPrimaryKey(pk);
	}

	/**
	* Gets the hr user project ID of this h r user project.
	*
	* @return the hr user project ID of this h r user project
	*/
	public long getHrUserProjectId() {
		return _hrUserProject.getHrUserProjectId();
	}

	/**
	* Sets the hr user project ID of this h r user project.
	*
	* @param hrUserProjectId the hr user project ID of this h r user project
	*/
	public void setHrUserProjectId(long hrUserProjectId) {
		_hrUserProject.setHrUserProjectId(hrUserProjectId);
	}

	/**
	* Gets the group ID of this h r user project.
	*
	* @return the group ID of this h r user project
	*/
	public long getGroupId() {
		return _hrUserProject.getGroupId();
	}

	/**
	* Sets the group ID of this h r user project.
	*
	* @param groupId the group ID of this h r user project
	*/
	public void setGroupId(long groupId) {
		_hrUserProject.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r user project.
	*
	* @return the company ID of this h r user project
	*/
	public long getCompanyId() {
		return _hrUserProject.getCompanyId();
	}

	/**
	* Sets the company ID of this h r user project.
	*
	* @param companyId the company ID of this h r user project
	*/
	public void setCompanyId(long companyId) {
		_hrUserProject.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r user project.
	*
	* @return the user ID of this h r user project
	*/
	public long getUserId() {
		return _hrUserProject.getUserId();
	}

	/**
	* Sets the user ID of this h r user project.
	*
	* @param userId the user ID of this h r user project
	*/
	public void setUserId(long userId) {
		_hrUserProject.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r user project.
	*
	* @return the user uuid of this h r user project
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrUserProject.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r user project.
	*
	* @param userUuid the user uuid of this h r user project
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrUserProject.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r user project.
	*
	* @return the user name of this h r user project
	*/
	public java.lang.String getUserName() {
		return _hrUserProject.getUserName();
	}

	/**
	* Sets the user name of this h r user project.
	*
	* @param userName the user name of this h r user project
	*/
	public void setUserName(java.lang.String userName) {
		_hrUserProject.setUserName(userName);
	}

	/**
	* Gets the create date of this h r user project.
	*
	* @return the create date of this h r user project
	*/
	public java.util.Date getCreateDate() {
		return _hrUserProject.getCreateDate();
	}

	/**
	* Sets the create date of this h r user project.
	*
	* @param createDate the create date of this h r user project
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrUserProject.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r user project.
	*
	* @return the modified date of this h r user project
	*/
	public java.util.Date getModifiedDate() {
		return _hrUserProject.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r user project.
	*
	* @param modifiedDate the modified date of this h r user project
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrUserProject.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the hr project billing rate ID of this h r user project.
	*
	* @return the hr project billing rate ID of this h r user project
	*/
	public long getHrProjectBillingRateId() {
		return _hrUserProject.getHrProjectBillingRateId();
	}

	/**
	* Sets the hr project billing rate ID of this h r user project.
	*
	* @param hrProjectBillingRateId the hr project billing rate ID of this h r user project
	*/
	public void setHrProjectBillingRateId(long hrProjectBillingRateId) {
		_hrUserProject.setHrProjectBillingRateId(hrProjectBillingRateId);
	}

	/**
	* Gets the hr project ID of this h r user project.
	*
	* @return the hr project ID of this h r user project
	*/
	public long getHrProjectId() {
		return _hrUserProject.getHrProjectId();
	}

	/**
	* Sets the hr project ID of this h r user project.
	*
	* @param hrProjectId the hr project ID of this h r user project
	*/
	public void setHrProjectId(long hrProjectId) {
		_hrUserProject.setHrProjectId(hrProjectId);
	}

	/**
	* Gets the hr project role ID of this h r user project.
	*
	* @return the hr project role ID of this h r user project
	*/
	public long getHrProjectRoleId() {
		return _hrUserProject.getHrProjectRoleId();
	}

	/**
	* Sets the hr project role ID of this h r user project.
	*
	* @param hrProjectRoleId the hr project role ID of this h r user project
	*/
	public void setHrProjectRoleId(long hrProjectRoleId) {
		_hrUserProject.setHrProjectRoleId(hrProjectRoleId);
	}

	/**
	* Gets the hr user ID of this h r user project.
	*
	* @return the hr user ID of this h r user project
	*/
	public long getHrUserId() {
		return _hrUserProject.getHrUserId();
	}

	/**
	* Sets the hr user ID of this h r user project.
	*
	* @param hrUserId the hr user ID of this h r user project
	*/
	public void setHrUserId(long hrUserId) {
		_hrUserProject.setHrUserId(hrUserId);
	}

	/**
	* Gets the hr user uuid of this h r user project.
	*
	* @return the hr user uuid of this h r user project
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getHrUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrUserProject.getHrUserUuid();
	}

	/**
	* Sets the hr user uuid of this h r user project.
	*
	* @param hrUserUuid the hr user uuid of this h r user project
	*/
	public void setHrUserUuid(java.lang.String hrUserUuid) {
		_hrUserProject.setHrUserUuid(hrUserUuid);
	}

	/**
	* Gets the actual rate of this h r user project.
	*
	* @return the actual rate of this h r user project
	*/
	public double getActualRate() {
		return _hrUserProject.getActualRate();
	}

	/**
	* Sets the actual rate of this h r user project.
	*
	* @param actualRate the actual rate of this h r user project
	*/
	public void setActualRate(double actualRate) {
		_hrUserProject.setActualRate(actualRate);
	}

	public boolean isNew() {
		return _hrUserProject.isNew();
	}

	public void setNew(boolean n) {
		_hrUserProject.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrUserProject.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrUserProject.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrUserProject.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrUserProject.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrUserProject.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrUserProject.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrUserProject.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRUserProjectWrapper((HRUserProject)_hrUserProject.clone());
	}

	public int compareTo(HRUserProject hrUserProject) {
		return _hrUserProject.compareTo(hrUserProject);
	}

	public int hashCode() {
		return _hrUserProject.hashCode();
	}

	public HRUserProject toEscapedModel() {
		return new HRUserProjectWrapper(_hrUserProject.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrUserProject.toString();
	}

	public java.lang.String toXmlString() {
		return _hrUserProject.toXmlString();
	}

	public HRUserProject getWrappedHRUserProject() {
		return _hrUserProject;
	}

	public void resetOriginalValues() {
		_hrUserProject.resetOriginalValues();
	}

	private HRUserProject _hrUserProject;
}