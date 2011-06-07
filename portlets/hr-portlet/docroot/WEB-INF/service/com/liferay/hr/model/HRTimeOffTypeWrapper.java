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
 * This class is a wrapper for {@link HRTimeOffType}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRTimeOffType
 * @generated
 */
public class HRTimeOffTypeWrapper implements HRTimeOffType {
	public HRTimeOffTypeWrapper(HRTimeOffType hrTimeOffType) {
		_hrTimeOffType = hrTimeOffType;
	}

	public Class<?> getModelClass() {
		return HRTimeOffType.class;
	}

	public String getModelClassName() {
		return HRTimeOffType.class.getName();
	}

	/**
	* Returns the primary key of this h r time off type.
	*
	* @return the primary key of this h r time off type
	*/
	public long getPrimaryKey() {
		return _hrTimeOffType.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r time off type.
	*
	* @param primaryKey the primary key of this h r time off type
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrTimeOffType.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr time off type ID of this h r time off type.
	*
	* @return the hr time off type ID of this h r time off type
	*/
	public long getHrTimeOffTypeId() {
		return _hrTimeOffType.getHrTimeOffTypeId();
	}

	/**
	* Sets the hr time off type ID of this h r time off type.
	*
	* @param hrTimeOffTypeId the hr time off type ID of this h r time off type
	*/
	public void setHrTimeOffTypeId(long hrTimeOffTypeId) {
		_hrTimeOffType.setHrTimeOffTypeId(hrTimeOffTypeId);
	}

	/**
	* Returns the group ID of this h r time off type.
	*
	* @return the group ID of this h r time off type
	*/
	public long getGroupId() {
		return _hrTimeOffType.getGroupId();
	}

	/**
	* Sets the group ID of this h r time off type.
	*
	* @param groupId the group ID of this h r time off type
	*/
	public void setGroupId(long groupId) {
		_hrTimeOffType.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r time off type.
	*
	* @return the company ID of this h r time off type
	*/
	public long getCompanyId() {
		return _hrTimeOffType.getCompanyId();
	}

	/**
	* Sets the company ID of this h r time off type.
	*
	* @param companyId the company ID of this h r time off type
	*/
	public void setCompanyId(long companyId) {
		_hrTimeOffType.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r time off type.
	*
	* @return the user ID of this h r time off type
	*/
	public long getUserId() {
		return _hrTimeOffType.getUserId();
	}

	/**
	* Sets the user ID of this h r time off type.
	*
	* @param userId the user ID of this h r time off type
	*/
	public void setUserId(long userId) {
		_hrTimeOffType.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r time off type.
	*
	* @return the user uuid of this h r time off type
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTimeOffType.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r time off type.
	*
	* @param userUuid the user uuid of this h r time off type
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrTimeOffType.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r time off type.
	*
	* @return the user name of this h r time off type
	*/
	public java.lang.String getUserName() {
		return _hrTimeOffType.getUserName();
	}

	/**
	* Sets the user name of this h r time off type.
	*
	* @param userName the user name of this h r time off type
	*/
	public void setUserName(java.lang.String userName) {
		_hrTimeOffType.setUserName(userName);
	}

	/**
	* Returns the create date of this h r time off type.
	*
	* @return the create date of this h r time off type
	*/
	public java.util.Date getCreateDate() {
		return _hrTimeOffType.getCreateDate();
	}

	/**
	* Sets the create date of this h r time off type.
	*
	* @param createDate the create date of this h r time off type
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrTimeOffType.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r time off type.
	*
	* @return the modified date of this h r time off type
	*/
	public java.util.Date getModifiedDate() {
		return _hrTimeOffType.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r time off type.
	*
	* @param modifiedDate the modified date of this h r time off type
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrTimeOffType.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this h r time off type.
	*
	* @return the name of this h r time off type
	*/
	public java.lang.String getName() {
		return _hrTimeOffType.getName();
	}

	/**
	* Sets the name of this h r time off type.
	*
	* @param name the name of this h r time off type
	*/
	public void setName(java.lang.String name) {
		_hrTimeOffType.setName(name);
	}

	/**
	* Returns the description of this h r time off type.
	*
	* @return the description of this h r time off type
	*/
	public java.lang.String getDescription() {
		return _hrTimeOffType.getDescription();
	}

	/**
	* Sets the description of this h r time off type.
	*
	* @param description the description of this h r time off type
	*/
	public void setDescription(java.lang.String description) {
		_hrTimeOffType.setDescription(description);
	}

	public boolean isNew() {
		return _hrTimeOffType.isNew();
	}

	public void setNew(boolean n) {
		_hrTimeOffType.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrTimeOffType.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrTimeOffType.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrTimeOffType.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrTimeOffType.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrTimeOffType.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrTimeOffType.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrTimeOffType.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrTimeOffType.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRTimeOffTypeWrapper((HRTimeOffType)_hrTimeOffType.clone());
	}

	public int compareTo(com.liferay.hr.model.HRTimeOffType hrTimeOffType) {
		return _hrTimeOffType.compareTo(hrTimeOffType);
	}

	public int hashCode() {
		return _hrTimeOffType.hashCode();
	}

	public com.liferay.hr.model.HRTimeOffType toEscapedModel() {
		return new HRTimeOffTypeWrapper(_hrTimeOffType.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrTimeOffType.toString();
	}

	public java.lang.String toXmlString() {
		return _hrTimeOffType.toXmlString();
	}

	public HRTimeOffType getWrappedHRTimeOffType() {
		return _hrTimeOffType;
	}

	public void resetOriginalValues() {
		_hrTimeOffType.resetOriginalValues();
	}

	private HRTimeOffType _hrTimeOffType;
}