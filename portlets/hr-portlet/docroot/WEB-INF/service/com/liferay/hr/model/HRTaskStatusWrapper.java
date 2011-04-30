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
 * This class is a wrapper for {@link HRTaskStatus}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HRTaskStatus
 * @generated
 */
public class HRTaskStatusWrapper implements HRTaskStatus {
	public HRTaskStatusWrapper(HRTaskStatus hrTaskStatus) {
		_hrTaskStatus = hrTaskStatus;
	}

	public Class<?> getModelClass() {
		return HRTaskStatus.class;
	}

	public String getModelClassName() {
		return HRTaskStatus.class.getName();
	}

	/**
	* Gets the primary key of this h r task status.
	*
	* @return the primary key of this h r task status
	*/
	public long getPrimaryKey() {
		return _hrTaskStatus.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r task status
	*
	* @param pk the primary key of this h r task status
	*/
	public void setPrimaryKey(long pk) {
		_hrTaskStatus.setPrimaryKey(pk);
	}

	/**
	* Gets the hr task status ID of this h r task status.
	*
	* @return the hr task status ID of this h r task status
	*/
	public long getHrTaskStatusId() {
		return _hrTaskStatus.getHrTaskStatusId();
	}

	/**
	* Sets the hr task status ID of this h r task status.
	*
	* @param hrTaskStatusId the hr task status ID of this h r task status
	*/
	public void setHrTaskStatusId(long hrTaskStatusId) {
		_hrTaskStatus.setHrTaskStatusId(hrTaskStatusId);
	}

	/**
	* Gets the group ID of this h r task status.
	*
	* @return the group ID of this h r task status
	*/
	public long getGroupId() {
		return _hrTaskStatus.getGroupId();
	}

	/**
	* Sets the group ID of this h r task status.
	*
	* @param groupId the group ID of this h r task status
	*/
	public void setGroupId(long groupId) {
		_hrTaskStatus.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r task status.
	*
	* @return the company ID of this h r task status
	*/
	public long getCompanyId() {
		return _hrTaskStatus.getCompanyId();
	}

	/**
	* Sets the company ID of this h r task status.
	*
	* @param companyId the company ID of this h r task status
	*/
	public void setCompanyId(long companyId) {
		_hrTaskStatus.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r task status.
	*
	* @return the user ID of this h r task status
	*/
	public long getUserId() {
		return _hrTaskStatus.getUserId();
	}

	/**
	* Sets the user ID of this h r task status.
	*
	* @param userId the user ID of this h r task status
	*/
	public void setUserId(long userId) {
		_hrTaskStatus.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r task status.
	*
	* @return the user uuid of this h r task status
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTaskStatus.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r task status.
	*
	* @param userUuid the user uuid of this h r task status
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrTaskStatus.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r task status.
	*
	* @return the user name of this h r task status
	*/
	public java.lang.String getUserName() {
		return _hrTaskStatus.getUserName();
	}

	/**
	* Sets the user name of this h r task status.
	*
	* @param userName the user name of this h r task status
	*/
	public void setUserName(java.lang.String userName) {
		_hrTaskStatus.setUserName(userName);
	}

	/**
	* Gets the create date of this h r task status.
	*
	* @return the create date of this h r task status
	*/
	public java.util.Date getCreateDate() {
		return _hrTaskStatus.getCreateDate();
	}

	/**
	* Sets the create date of this h r task status.
	*
	* @param createDate the create date of this h r task status
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrTaskStatus.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r task status.
	*
	* @return the modified date of this h r task status
	*/
	public java.util.Date getModifiedDate() {
		return _hrTaskStatus.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r task status.
	*
	* @param modifiedDate the modified date of this h r task status
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrTaskStatus.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the code of this h r task status.
	*
	* @return the code of this h r task status
	*/
	public java.lang.String getCode() {
		return _hrTaskStatus.getCode();
	}

	/**
	* Sets the code of this h r task status.
	*
	* @param code the code of this h r task status
	*/
	public void setCode(java.lang.String code) {
		_hrTaskStatus.setCode(code);
	}

	/**
	* Gets the name of this h r task status.
	*
	* @return the name of this h r task status
	*/
	public java.lang.String getName() {
		return _hrTaskStatus.getName();
	}

	/**
	* Sets the name of this h r task status.
	*
	* @param name the name of this h r task status
	*/
	public void setName(java.lang.String name) {
		_hrTaskStatus.setName(name);
	}

	/**
	* Gets the description of this h r task status.
	*
	* @return the description of this h r task status
	*/
	public java.lang.String getDescription() {
		return _hrTaskStatus.getDescription();
	}

	/**
	* Sets the description of this h r task status.
	*
	* @param description the description of this h r task status
	*/
	public void setDescription(java.lang.String description) {
		_hrTaskStatus.setDescription(description);
	}

	public boolean isNew() {
		return _hrTaskStatus.isNew();
	}

	public void setNew(boolean n) {
		_hrTaskStatus.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrTaskStatus.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrTaskStatus.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrTaskStatus.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrTaskStatus.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrTaskStatus.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrTaskStatus.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrTaskStatus.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRTaskStatusWrapper((HRTaskStatus)_hrTaskStatus.clone());
	}

	public int compareTo(com.liferay.hr.model.HRTaskStatus hrTaskStatus) {
		return _hrTaskStatus.compareTo(hrTaskStatus);
	}

	public int hashCode() {
		return _hrTaskStatus.hashCode();
	}

	public com.liferay.hr.model.HRTaskStatus toEscapedModel() {
		return new HRTaskStatusWrapper(_hrTaskStatus.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrTaskStatus.toString();
	}

	public java.lang.String toXmlString() {
		return _hrTaskStatus.toXmlString();
	}

	public HRTaskStatus getWrappedHRTaskStatus() {
		return _hrTaskStatus;
	}

	public void resetOriginalValues() {
		_hrTaskStatus.resetOriginalValues();
	}

	private HRTaskStatus _hrTaskStatus;
}