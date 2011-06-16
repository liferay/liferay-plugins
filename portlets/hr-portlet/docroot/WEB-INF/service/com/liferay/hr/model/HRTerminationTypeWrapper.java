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
 * This class is a wrapper for {@link HRTerminationType}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRTerminationType
 * @generated
 */
public class HRTerminationTypeWrapper implements HRTerminationType {
	public HRTerminationTypeWrapper(HRTerminationType hrTerminationType) {
		_hrTerminationType = hrTerminationType;
	}

	public Class<?> getModelClass() {
		return HRTerminationType.class;
	}

	public String getModelClassName() {
		return HRTerminationType.class.getName();
	}

	/**
	* Returns the primary key of this h r termination type.
	*
	* @return the primary key of this h r termination type
	*/
	public long getPrimaryKey() {
		return _hrTerminationType.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r termination type.
	*
	* @param primaryKey the primary key of this h r termination type
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrTerminationType.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr termination type ID of this h r termination type.
	*
	* @return the hr termination type ID of this h r termination type
	*/
	public long getHrTerminationTypeId() {
		return _hrTerminationType.getHrTerminationTypeId();
	}

	/**
	* Sets the hr termination type ID of this h r termination type.
	*
	* @param hrTerminationTypeId the hr termination type ID of this h r termination type
	*/
	public void setHrTerminationTypeId(long hrTerminationTypeId) {
		_hrTerminationType.setHrTerminationTypeId(hrTerminationTypeId);
	}

	/**
	* Returns the group ID of this h r termination type.
	*
	* @return the group ID of this h r termination type
	*/
	public long getGroupId() {
		return _hrTerminationType.getGroupId();
	}

	/**
	* Sets the group ID of this h r termination type.
	*
	* @param groupId the group ID of this h r termination type
	*/
	public void setGroupId(long groupId) {
		_hrTerminationType.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r termination type.
	*
	* @return the company ID of this h r termination type
	*/
	public long getCompanyId() {
		return _hrTerminationType.getCompanyId();
	}

	/**
	* Sets the company ID of this h r termination type.
	*
	* @param companyId the company ID of this h r termination type
	*/
	public void setCompanyId(long companyId) {
		_hrTerminationType.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r termination type.
	*
	* @return the user ID of this h r termination type
	*/
	public long getUserId() {
		return _hrTerminationType.getUserId();
	}

	/**
	* Sets the user ID of this h r termination type.
	*
	* @param userId the user ID of this h r termination type
	*/
	public void setUserId(long userId) {
		_hrTerminationType.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r termination type.
	*
	* @return the user uuid of this h r termination type
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTerminationType.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r termination type.
	*
	* @param userUuid the user uuid of this h r termination type
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrTerminationType.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r termination type.
	*
	* @return the user name of this h r termination type
	*/
	public java.lang.String getUserName() {
		return _hrTerminationType.getUserName();
	}

	/**
	* Sets the user name of this h r termination type.
	*
	* @param userName the user name of this h r termination type
	*/
	public void setUserName(java.lang.String userName) {
		_hrTerminationType.setUserName(userName);
	}

	/**
	* Returns the create date of this h r termination type.
	*
	* @return the create date of this h r termination type
	*/
	public java.util.Date getCreateDate() {
		return _hrTerminationType.getCreateDate();
	}

	/**
	* Sets the create date of this h r termination type.
	*
	* @param createDate the create date of this h r termination type
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrTerminationType.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r termination type.
	*
	* @return the modified date of this h r termination type
	*/
	public java.util.Date getModifiedDate() {
		return _hrTerminationType.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r termination type.
	*
	* @param modifiedDate the modified date of this h r termination type
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrTerminationType.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the code of this h r termination type.
	*
	* @return the code of this h r termination type
	*/
	public java.lang.String getCode() {
		return _hrTerminationType.getCode();
	}

	/**
	* Sets the code of this h r termination type.
	*
	* @param code the code of this h r termination type
	*/
	public void setCode(java.lang.String code) {
		_hrTerminationType.setCode(code);
	}

	/**
	* Returns the name of this h r termination type.
	*
	* @return the name of this h r termination type
	*/
	public java.lang.String getName() {
		return _hrTerminationType.getName();
	}

	/**
	* Sets the name of this h r termination type.
	*
	* @param name the name of this h r termination type
	*/
	public void setName(java.lang.String name) {
		_hrTerminationType.setName(name);
	}

	/**
	* Returns the description of this h r termination type.
	*
	* @return the description of this h r termination type
	*/
	public java.lang.String getDescription() {
		return _hrTerminationType.getDescription();
	}

	/**
	* Sets the description of this h r termination type.
	*
	* @param description the description of this h r termination type
	*/
	public void setDescription(java.lang.String description) {
		_hrTerminationType.setDescription(description);
	}

	public boolean isNew() {
		return _hrTerminationType.isNew();
	}

	public void setNew(boolean n) {
		_hrTerminationType.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrTerminationType.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrTerminationType.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrTerminationType.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrTerminationType.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrTerminationType.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrTerminationType.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrTerminationType.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrTerminationType.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HRTerminationTypeWrapper((HRTerminationType)_hrTerminationType.clone());
	}

	public int compareTo(
		com.liferay.hr.model.HRTerminationType hrTerminationType) {
		return _hrTerminationType.compareTo(hrTerminationType);
	}

	@Override
	public int hashCode() {
		return _hrTerminationType.hashCode();
	}

	public com.liferay.hr.model.HRTerminationType toEscapedModel() {
		return new HRTerminationTypeWrapper(_hrTerminationType.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hrTerminationType.toString();
	}

	public java.lang.String toXmlString() {
		return _hrTerminationType.toXmlString();
	}

	public HRTerminationType getWrappedHRTerminationType() {
		return _hrTerminationType;
	}

	public void resetOriginalValues() {
		_hrTerminationType.resetOriginalValues();
	}

	private HRTerminationType _hrTerminationType;
}