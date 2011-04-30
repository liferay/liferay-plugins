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
 * This class is a wrapper for {@link HREmploymentType}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HREmploymentType
 * @generated
 */
public class HREmploymentTypeWrapper implements HREmploymentType {
	public HREmploymentTypeWrapper(HREmploymentType hrEmploymentType) {
		_hrEmploymentType = hrEmploymentType;
	}

	public Class<?> getModelClass() {
		return HREmploymentType.class;
	}

	public String getModelClassName() {
		return HREmploymentType.class.getName();
	}

	/**
	* Gets the primary key of this h r employment type.
	*
	* @return the primary key of this h r employment type
	*/
	public long getPrimaryKey() {
		return _hrEmploymentType.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r employment type
	*
	* @param pk the primary key of this h r employment type
	*/
	public void setPrimaryKey(long pk) {
		_hrEmploymentType.setPrimaryKey(pk);
	}

	/**
	* Gets the hr employment type ID of this h r employment type.
	*
	* @return the hr employment type ID of this h r employment type
	*/
	public long getHrEmploymentTypeId() {
		return _hrEmploymentType.getHrEmploymentTypeId();
	}

	/**
	* Sets the hr employment type ID of this h r employment type.
	*
	* @param hrEmploymentTypeId the hr employment type ID of this h r employment type
	*/
	public void setHrEmploymentTypeId(long hrEmploymentTypeId) {
		_hrEmploymentType.setHrEmploymentTypeId(hrEmploymentTypeId);
	}

	/**
	* Gets the group ID of this h r employment type.
	*
	* @return the group ID of this h r employment type
	*/
	public long getGroupId() {
		return _hrEmploymentType.getGroupId();
	}

	/**
	* Sets the group ID of this h r employment type.
	*
	* @param groupId the group ID of this h r employment type
	*/
	public void setGroupId(long groupId) {
		_hrEmploymentType.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r employment type.
	*
	* @return the company ID of this h r employment type
	*/
	public long getCompanyId() {
		return _hrEmploymentType.getCompanyId();
	}

	/**
	* Sets the company ID of this h r employment type.
	*
	* @param companyId the company ID of this h r employment type
	*/
	public void setCompanyId(long companyId) {
		_hrEmploymentType.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r employment type.
	*
	* @return the user ID of this h r employment type
	*/
	public long getUserId() {
		return _hrEmploymentType.getUserId();
	}

	/**
	* Sets the user ID of this h r employment type.
	*
	* @param userId the user ID of this h r employment type
	*/
	public void setUserId(long userId) {
		_hrEmploymentType.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r employment type.
	*
	* @return the user uuid of this h r employment type
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrEmploymentType.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r employment type.
	*
	* @param userUuid the user uuid of this h r employment type
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrEmploymentType.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r employment type.
	*
	* @return the user name of this h r employment type
	*/
	public java.lang.String getUserName() {
		return _hrEmploymentType.getUserName();
	}

	/**
	* Sets the user name of this h r employment type.
	*
	* @param userName the user name of this h r employment type
	*/
	public void setUserName(java.lang.String userName) {
		_hrEmploymentType.setUserName(userName);
	}

	/**
	* Gets the create date of this h r employment type.
	*
	* @return the create date of this h r employment type
	*/
	public java.util.Date getCreateDate() {
		return _hrEmploymentType.getCreateDate();
	}

	/**
	* Sets the create date of this h r employment type.
	*
	* @param createDate the create date of this h r employment type
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrEmploymentType.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r employment type.
	*
	* @return the modified date of this h r employment type
	*/
	public java.util.Date getModifiedDate() {
		return _hrEmploymentType.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r employment type.
	*
	* @param modifiedDate the modified date of this h r employment type
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrEmploymentType.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the code of this h r employment type.
	*
	* @return the code of this h r employment type
	*/
	public java.lang.String getCode() {
		return _hrEmploymentType.getCode();
	}

	/**
	* Sets the code of this h r employment type.
	*
	* @param code the code of this h r employment type
	*/
	public void setCode(java.lang.String code) {
		_hrEmploymentType.setCode(code);
	}

	/**
	* Gets the name of this h r employment type.
	*
	* @return the name of this h r employment type
	*/
	public java.lang.String getName() {
		return _hrEmploymentType.getName();
	}

	/**
	* Sets the name of this h r employment type.
	*
	* @param name the name of this h r employment type
	*/
	public void setName(java.lang.String name) {
		_hrEmploymentType.setName(name);
	}

	/**
	* Gets the description of this h r employment type.
	*
	* @return the description of this h r employment type
	*/
	public java.lang.String getDescription() {
		return _hrEmploymentType.getDescription();
	}

	/**
	* Sets the description of this h r employment type.
	*
	* @param description the description of this h r employment type
	*/
	public void setDescription(java.lang.String description) {
		_hrEmploymentType.setDescription(description);
	}

	public boolean isNew() {
		return _hrEmploymentType.isNew();
	}

	public void setNew(boolean n) {
		_hrEmploymentType.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrEmploymentType.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrEmploymentType.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrEmploymentType.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrEmploymentType.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrEmploymentType.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrEmploymentType.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrEmploymentType.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HREmploymentTypeWrapper((HREmploymentType)_hrEmploymentType.clone());
	}

	public int compareTo(com.liferay.hr.model.HREmploymentType hrEmploymentType) {
		return _hrEmploymentType.compareTo(hrEmploymentType);
	}

	public int hashCode() {
		return _hrEmploymentType.hashCode();
	}

	public com.liferay.hr.model.HREmploymentType toEscapedModel() {
		return new HREmploymentTypeWrapper(_hrEmploymentType.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrEmploymentType.toString();
	}

	public java.lang.String toXmlString() {
		return _hrEmploymentType.toXmlString();
	}

	public HREmploymentType getWrappedHREmploymentType() {
		return _hrEmploymentType;
	}

	public void resetOriginalValues() {
		_hrEmploymentType.resetOriginalValues();
	}

	private HREmploymentType _hrEmploymentType;
}