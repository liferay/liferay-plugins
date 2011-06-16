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
 * This class is a wrapper for {@link HRProjectStatus}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRProjectStatus
 * @generated
 */
public class HRProjectStatusWrapper implements HRProjectStatus {
	public HRProjectStatusWrapper(HRProjectStatus hrProjectStatus) {
		_hrProjectStatus = hrProjectStatus;
	}

	public Class<?> getModelClass() {
		return HRProjectStatus.class;
	}

	public String getModelClassName() {
		return HRProjectStatus.class.getName();
	}

	/**
	* Returns the primary key of this h r project status.
	*
	* @return the primary key of this h r project status
	*/
	public long getPrimaryKey() {
		return _hrProjectStatus.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r project status.
	*
	* @param primaryKey the primary key of this h r project status
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrProjectStatus.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr project status ID of this h r project status.
	*
	* @return the hr project status ID of this h r project status
	*/
	public long getHrProjectStatusId() {
		return _hrProjectStatus.getHrProjectStatusId();
	}

	/**
	* Sets the hr project status ID of this h r project status.
	*
	* @param hrProjectStatusId the hr project status ID of this h r project status
	*/
	public void setHrProjectStatusId(long hrProjectStatusId) {
		_hrProjectStatus.setHrProjectStatusId(hrProjectStatusId);
	}

	/**
	* Returns the group ID of this h r project status.
	*
	* @return the group ID of this h r project status
	*/
	public long getGroupId() {
		return _hrProjectStatus.getGroupId();
	}

	/**
	* Sets the group ID of this h r project status.
	*
	* @param groupId the group ID of this h r project status
	*/
	public void setGroupId(long groupId) {
		_hrProjectStatus.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r project status.
	*
	* @return the company ID of this h r project status
	*/
	public long getCompanyId() {
		return _hrProjectStatus.getCompanyId();
	}

	/**
	* Sets the company ID of this h r project status.
	*
	* @param companyId the company ID of this h r project status
	*/
	public void setCompanyId(long companyId) {
		_hrProjectStatus.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r project status.
	*
	* @return the user ID of this h r project status
	*/
	public long getUserId() {
		return _hrProjectStatus.getUserId();
	}

	/**
	* Sets the user ID of this h r project status.
	*
	* @param userId the user ID of this h r project status
	*/
	public void setUserId(long userId) {
		_hrProjectStatus.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r project status.
	*
	* @return the user uuid of this h r project status
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrProjectStatus.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r project status.
	*
	* @param userUuid the user uuid of this h r project status
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrProjectStatus.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r project status.
	*
	* @return the user name of this h r project status
	*/
	public java.lang.String getUserName() {
		return _hrProjectStatus.getUserName();
	}

	/**
	* Sets the user name of this h r project status.
	*
	* @param userName the user name of this h r project status
	*/
	public void setUserName(java.lang.String userName) {
		_hrProjectStatus.setUserName(userName);
	}

	/**
	* Returns the create date of this h r project status.
	*
	* @return the create date of this h r project status
	*/
	public java.util.Date getCreateDate() {
		return _hrProjectStatus.getCreateDate();
	}

	/**
	* Sets the create date of this h r project status.
	*
	* @param createDate the create date of this h r project status
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrProjectStatus.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r project status.
	*
	* @return the modified date of this h r project status
	*/
	public java.util.Date getModifiedDate() {
		return _hrProjectStatus.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r project status.
	*
	* @param modifiedDate the modified date of this h r project status
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrProjectStatus.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the code of this h r project status.
	*
	* @return the code of this h r project status
	*/
	public java.lang.String getCode() {
		return _hrProjectStatus.getCode();
	}

	/**
	* Sets the code of this h r project status.
	*
	* @param code the code of this h r project status
	*/
	public void setCode(java.lang.String code) {
		_hrProjectStatus.setCode(code);
	}

	/**
	* Returns the name of this h r project status.
	*
	* @return the name of this h r project status
	*/
	public java.lang.String getName() {
		return _hrProjectStatus.getName();
	}

	/**
	* Sets the name of this h r project status.
	*
	* @param name the name of this h r project status
	*/
	public void setName(java.lang.String name) {
		_hrProjectStatus.setName(name);
	}

	/**
	* Returns the description of this h r project status.
	*
	* @return the description of this h r project status
	*/
	public java.lang.String getDescription() {
		return _hrProjectStatus.getDescription();
	}

	/**
	* Sets the description of this h r project status.
	*
	* @param description the description of this h r project status
	*/
	public void setDescription(java.lang.String description) {
		_hrProjectStatus.setDescription(description);
	}

	public boolean isNew() {
		return _hrProjectStatus.isNew();
	}

	public void setNew(boolean n) {
		_hrProjectStatus.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrProjectStatus.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrProjectStatus.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrProjectStatus.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrProjectStatus.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrProjectStatus.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrProjectStatus.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrProjectStatus.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrProjectStatus.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HRProjectStatusWrapper((HRProjectStatus)_hrProjectStatus.clone());
	}

	public int compareTo(com.liferay.hr.model.HRProjectStatus hrProjectStatus) {
		return _hrProjectStatus.compareTo(hrProjectStatus);
	}

	@Override
	public int hashCode() {
		return _hrProjectStatus.hashCode();
	}

	public com.liferay.hr.model.HRProjectStatus toEscapedModel() {
		return new HRProjectStatusWrapper(_hrProjectStatus.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hrProjectStatus.toString();
	}

	public java.lang.String toXmlString() {
		return _hrProjectStatus.toXmlString();
	}

	public HRProjectStatus getWrappedHRProjectStatus() {
		return _hrProjectStatus;
	}

	public void resetOriginalValues() {
		_hrProjectStatus.resetOriginalValues();
	}

	private HRProjectStatus _hrProjectStatus;
}