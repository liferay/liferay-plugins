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
 * This class is a wrapper for {@link HRAssetType}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRAssetType
 * @generated
 */
public class HRAssetTypeWrapper implements HRAssetType {
	public HRAssetTypeWrapper(HRAssetType hrAssetType) {
		_hrAssetType = hrAssetType;
	}

	public Class<?> getModelClass() {
		return HRAssetType.class;
	}

	public String getModelClassName() {
		return HRAssetType.class.getName();
	}

	/**
	* Returns the primary key of this h r asset type.
	*
	* @return the primary key of this h r asset type
	*/
	public long getPrimaryKey() {
		return _hrAssetType.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r asset type.
	*
	* @param primaryKey the primary key of this h r asset type
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrAssetType.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr asset type ID of this h r asset type.
	*
	* @return the hr asset type ID of this h r asset type
	*/
	public long getHrAssetTypeId() {
		return _hrAssetType.getHrAssetTypeId();
	}

	/**
	* Sets the hr asset type ID of this h r asset type.
	*
	* @param hrAssetTypeId the hr asset type ID of this h r asset type
	*/
	public void setHrAssetTypeId(long hrAssetTypeId) {
		_hrAssetType.setHrAssetTypeId(hrAssetTypeId);
	}

	/**
	* Returns the group ID of this h r asset type.
	*
	* @return the group ID of this h r asset type
	*/
	public long getGroupId() {
		return _hrAssetType.getGroupId();
	}

	/**
	* Sets the group ID of this h r asset type.
	*
	* @param groupId the group ID of this h r asset type
	*/
	public void setGroupId(long groupId) {
		_hrAssetType.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r asset type.
	*
	* @return the company ID of this h r asset type
	*/
	public long getCompanyId() {
		return _hrAssetType.getCompanyId();
	}

	/**
	* Sets the company ID of this h r asset type.
	*
	* @param companyId the company ID of this h r asset type
	*/
	public void setCompanyId(long companyId) {
		_hrAssetType.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r asset type.
	*
	* @return the user ID of this h r asset type
	*/
	public long getUserId() {
		return _hrAssetType.getUserId();
	}

	/**
	* Sets the user ID of this h r asset type.
	*
	* @param userId the user ID of this h r asset type
	*/
	public void setUserId(long userId) {
		_hrAssetType.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r asset type.
	*
	* @return the user uuid of this h r asset type
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrAssetType.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r asset type.
	*
	* @param userUuid the user uuid of this h r asset type
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrAssetType.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r asset type.
	*
	* @return the user name of this h r asset type
	*/
	public java.lang.String getUserName() {
		return _hrAssetType.getUserName();
	}

	/**
	* Sets the user name of this h r asset type.
	*
	* @param userName the user name of this h r asset type
	*/
	public void setUserName(java.lang.String userName) {
		_hrAssetType.setUserName(userName);
	}

	/**
	* Returns the create date of this h r asset type.
	*
	* @return the create date of this h r asset type
	*/
	public java.util.Date getCreateDate() {
		return _hrAssetType.getCreateDate();
	}

	/**
	* Sets the create date of this h r asset type.
	*
	* @param createDate the create date of this h r asset type
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrAssetType.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r asset type.
	*
	* @return the modified date of this h r asset type
	*/
	public java.util.Date getModifiedDate() {
		return _hrAssetType.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r asset type.
	*
	* @param modifiedDate the modified date of this h r asset type
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrAssetType.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this h r asset type.
	*
	* @return the name of this h r asset type
	*/
	public java.lang.String getName() {
		return _hrAssetType.getName();
	}

	/**
	* Sets the name of this h r asset type.
	*
	* @param name the name of this h r asset type
	*/
	public void setName(java.lang.String name) {
		_hrAssetType.setName(name);
	}

	/**
	* Returns the description of this h r asset type.
	*
	* @return the description of this h r asset type
	*/
	public java.lang.String getDescription() {
		return _hrAssetType.getDescription();
	}

	/**
	* Sets the description of this h r asset type.
	*
	* @param description the description of this h r asset type
	*/
	public void setDescription(java.lang.String description) {
		_hrAssetType.setDescription(description);
	}

	public boolean isNew() {
		return _hrAssetType.isNew();
	}

	public void setNew(boolean n) {
		_hrAssetType.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrAssetType.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrAssetType.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrAssetType.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrAssetType.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrAssetType.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrAssetType.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrAssetType.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrAssetType.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HRAssetTypeWrapper((HRAssetType)_hrAssetType.clone());
	}

	public int compareTo(com.liferay.hr.model.HRAssetType hrAssetType) {
		return _hrAssetType.compareTo(hrAssetType);
	}

	@Override
	public int hashCode() {
		return _hrAssetType.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.hr.model.HRAssetType> toCacheModel() {
		return _hrAssetType.toCacheModel();
	}

	public com.liferay.hr.model.HRAssetType toEscapedModel() {
		return new HRAssetTypeWrapper(_hrAssetType.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hrAssetType.toString();
	}

	public java.lang.String toXmlString() {
		return _hrAssetType.toXmlString();
	}

	public HRAssetType getWrappedHRAssetType() {
		return _hrAssetType;
	}

	public void resetOriginalValues() {
		_hrAssetType.resetOriginalValues();
	}

	private HRAssetType _hrAssetType;
}