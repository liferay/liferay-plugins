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
 * This class is a wrapper for {@link HRExpenseType}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRExpenseType
 * @generated
 */
public class HRExpenseTypeWrapper implements HRExpenseType {
	public HRExpenseTypeWrapper(HRExpenseType hrExpenseType) {
		_hrExpenseType = hrExpenseType;
	}

	public Class<?> getModelClass() {
		return HRExpenseType.class;
	}

	public String getModelClassName() {
		return HRExpenseType.class.getName();
	}

	/**
	* Returns the primary key of this h r expense type.
	*
	* @return the primary key of this h r expense type
	*/
	public long getPrimaryKey() {
		return _hrExpenseType.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r expense type.
	*
	* @param primaryKey the primary key of this h r expense type
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrExpenseType.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr expense type ID of this h r expense type.
	*
	* @return the hr expense type ID of this h r expense type
	*/
	public long getHrExpenseTypeId() {
		return _hrExpenseType.getHrExpenseTypeId();
	}

	/**
	* Sets the hr expense type ID of this h r expense type.
	*
	* @param hrExpenseTypeId the hr expense type ID of this h r expense type
	*/
	public void setHrExpenseTypeId(long hrExpenseTypeId) {
		_hrExpenseType.setHrExpenseTypeId(hrExpenseTypeId);
	}

	/**
	* Returns the group ID of this h r expense type.
	*
	* @return the group ID of this h r expense type
	*/
	public long getGroupId() {
		return _hrExpenseType.getGroupId();
	}

	/**
	* Sets the group ID of this h r expense type.
	*
	* @param groupId the group ID of this h r expense type
	*/
	public void setGroupId(long groupId) {
		_hrExpenseType.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r expense type.
	*
	* @return the company ID of this h r expense type
	*/
	public long getCompanyId() {
		return _hrExpenseType.getCompanyId();
	}

	/**
	* Sets the company ID of this h r expense type.
	*
	* @param companyId the company ID of this h r expense type
	*/
	public void setCompanyId(long companyId) {
		_hrExpenseType.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r expense type.
	*
	* @return the user ID of this h r expense type
	*/
	public long getUserId() {
		return _hrExpenseType.getUserId();
	}

	/**
	* Sets the user ID of this h r expense type.
	*
	* @param userId the user ID of this h r expense type
	*/
	public void setUserId(long userId) {
		_hrExpenseType.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r expense type.
	*
	* @return the user uuid of this h r expense type
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseType.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r expense type.
	*
	* @param userUuid the user uuid of this h r expense type
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrExpenseType.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r expense type.
	*
	* @return the user name of this h r expense type
	*/
	public java.lang.String getUserName() {
		return _hrExpenseType.getUserName();
	}

	/**
	* Sets the user name of this h r expense type.
	*
	* @param userName the user name of this h r expense type
	*/
	public void setUserName(java.lang.String userName) {
		_hrExpenseType.setUserName(userName);
	}

	/**
	* Returns the create date of this h r expense type.
	*
	* @return the create date of this h r expense type
	*/
	public java.util.Date getCreateDate() {
		return _hrExpenseType.getCreateDate();
	}

	/**
	* Sets the create date of this h r expense type.
	*
	* @param createDate the create date of this h r expense type
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrExpenseType.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r expense type.
	*
	* @return the modified date of this h r expense type
	*/
	public java.util.Date getModifiedDate() {
		return _hrExpenseType.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r expense type.
	*
	* @param modifiedDate the modified date of this h r expense type
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrExpenseType.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this h r expense type.
	*
	* @return the name of this h r expense type
	*/
	public java.lang.String getName() {
		return _hrExpenseType.getName();
	}

	/**
	* Sets the name of this h r expense type.
	*
	* @param name the name of this h r expense type
	*/
	public void setName(java.lang.String name) {
		_hrExpenseType.setName(name);
	}

	/**
	* Returns the description of this h r expense type.
	*
	* @return the description of this h r expense type
	*/
	public java.lang.String getDescription() {
		return _hrExpenseType.getDescription();
	}

	/**
	* Sets the description of this h r expense type.
	*
	* @param description the description of this h r expense type
	*/
	public void setDescription(java.lang.String description) {
		_hrExpenseType.setDescription(description);
	}

	public boolean isNew() {
		return _hrExpenseType.isNew();
	}

	public void setNew(boolean n) {
		_hrExpenseType.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrExpenseType.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrExpenseType.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrExpenseType.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrExpenseType.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrExpenseType.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrExpenseType.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrExpenseType.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrExpenseType.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HRExpenseTypeWrapper((HRExpenseType)_hrExpenseType.clone());
	}

	public int compareTo(com.liferay.hr.model.HRExpenseType hrExpenseType) {
		return _hrExpenseType.compareTo(hrExpenseType);
	}

	@Override
	public int hashCode() {
		return _hrExpenseType.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.hr.model.HRExpenseType> toCacheModel() {
		return _hrExpenseType.toCacheModel();
	}

	public com.liferay.hr.model.HRExpenseType toEscapedModel() {
		return new HRExpenseTypeWrapper(_hrExpenseType.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hrExpenseType.toString();
	}

	public java.lang.String toXmlString() {
		return _hrExpenseType.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_hrExpenseType.persist();
	}

	public HRExpenseType getWrappedHRExpenseType() {
		return _hrExpenseType;
	}

	public void resetOriginalValues() {
		_hrExpenseType.resetOriginalValues();
	}

	private HRExpenseType _hrExpenseType;
}