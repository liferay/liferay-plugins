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
 * This class is a wrapper for {@link HRAssetModelId}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HRAssetModelId
 * @generated
 */
public class HRAssetModelIdWrapper implements HRAssetModelId {
	public HRAssetModelIdWrapper(HRAssetModelId hrAssetModelId) {
		_hrAssetModelId = hrAssetModelId;
	}

	public Class<?> getModelClass() {
		return HRAssetModelId.class;
	}

	public String getModelClassName() {
		return HRAssetModelId.class.getName();
	}

	/**
	* Gets the primary key of this h r asset model ID.
	*
	* @return the primary key of this h r asset model ID
	*/
	public long getPrimaryKey() {
		return _hrAssetModelId.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r asset model ID
	*
	* @param pk the primary key of this h r asset model ID
	*/
	public void setPrimaryKey(long pk) {
		_hrAssetModelId.setPrimaryKey(pk);
	}

	/**
	* Gets the hr asset model ID of this h r asset model ID.
	*
	* @return the hr asset model ID of this h r asset model ID
	*/
	public long getHrAssetModelId() {
		return _hrAssetModelId.getHrAssetModelId();
	}

	/**
	* Sets the hr asset model ID of this h r asset model ID.
	*
	* @param hrAssetModelId the hr asset model ID of this h r asset model ID
	*/
	public void setHrAssetModelId(long hrAssetModelId) {
		_hrAssetModelId.setHrAssetModelId(hrAssetModelId);
	}

	/**
	* Gets the group ID of this h r asset model ID.
	*
	* @return the group ID of this h r asset model ID
	*/
	public long getGroupId() {
		return _hrAssetModelId.getGroupId();
	}

	/**
	* Sets the group ID of this h r asset model ID.
	*
	* @param groupId the group ID of this h r asset model ID
	*/
	public void setGroupId(long groupId) {
		_hrAssetModelId.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r asset model ID.
	*
	* @return the company ID of this h r asset model ID
	*/
	public long getCompanyId() {
		return _hrAssetModelId.getCompanyId();
	}

	/**
	* Sets the company ID of this h r asset model ID.
	*
	* @param companyId the company ID of this h r asset model ID
	*/
	public void setCompanyId(long companyId) {
		_hrAssetModelId.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r asset model ID.
	*
	* @return the user ID of this h r asset model ID
	*/
	public long getUserId() {
		return _hrAssetModelId.getUserId();
	}

	/**
	* Sets the user ID of this h r asset model ID.
	*
	* @param userId the user ID of this h r asset model ID
	*/
	public void setUserId(long userId) {
		_hrAssetModelId.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r asset model ID.
	*
	* @return the user uuid of this h r asset model ID
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrAssetModelId.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r asset model ID.
	*
	* @param userUuid the user uuid of this h r asset model ID
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrAssetModelId.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r asset model ID.
	*
	* @return the user name of this h r asset model ID
	*/
	public java.lang.String getUserName() {
		return _hrAssetModelId.getUserName();
	}

	/**
	* Sets the user name of this h r asset model ID.
	*
	* @param userName the user name of this h r asset model ID
	*/
	public void setUserName(java.lang.String userName) {
		_hrAssetModelId.setUserName(userName);
	}

	/**
	* Gets the create date of this h r asset model ID.
	*
	* @return the create date of this h r asset model ID
	*/
	public java.util.Date getCreateDate() {
		return _hrAssetModelId.getCreateDate();
	}

	/**
	* Sets the create date of this h r asset model ID.
	*
	* @param createDate the create date of this h r asset model ID
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrAssetModelId.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r asset model ID.
	*
	* @return the modified date of this h r asset model ID
	*/
	public java.util.Date getModifiedDate() {
		return _hrAssetModelId.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r asset model ID.
	*
	* @param modifiedDate the modified date of this h r asset model ID
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrAssetModelId.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the hr asset vendor ID of this h r asset model ID.
	*
	* @return the hr asset vendor ID of this h r asset model ID
	*/
	public long getHrAssetVendorId() {
		return _hrAssetModelId.getHrAssetVendorId();
	}

	/**
	* Sets the hr asset vendor ID of this h r asset model ID.
	*
	* @param hrAssetVendorId the hr asset vendor ID of this h r asset model ID
	*/
	public void setHrAssetVendorId(long hrAssetVendorId) {
		_hrAssetModelId.setHrAssetVendorId(hrAssetVendorId);
	}

	/**
	* Gets the name of this h r asset model ID.
	*
	* @return the name of this h r asset model ID
	*/
	public java.lang.String getName() {
		return _hrAssetModelId.getName();
	}

	/**
	* Sets the name of this h r asset model ID.
	*
	* @param name the name of this h r asset model ID
	*/
	public void setName(java.lang.String name) {
		_hrAssetModelId.setName(name);
	}

	/**
	* Gets the description of this h r asset model ID.
	*
	* @return the description of this h r asset model ID
	*/
	public java.lang.String getDescription() {
		return _hrAssetModelId.getDescription();
	}

	/**
	* Sets the description of this h r asset model ID.
	*
	* @param description the description of this h r asset model ID
	*/
	public void setDescription(java.lang.String description) {
		_hrAssetModelId.setDescription(description);
	}

	public boolean isNew() {
		return _hrAssetModelId.isNew();
	}

	public void setNew(boolean n) {
		_hrAssetModelId.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrAssetModelId.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrAssetModelId.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrAssetModelId.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrAssetModelId.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrAssetModelId.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrAssetModelId.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrAssetModelId.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRAssetModelIdWrapper((HRAssetModelId)_hrAssetModelId.clone());
	}

	public int compareTo(HRAssetModelId hrAssetModelId) {
		return _hrAssetModelId.compareTo(hrAssetModelId);
	}

	public int hashCode() {
		return _hrAssetModelId.hashCode();
	}

	public HRAssetModelId toEscapedModel() {
		return new HRAssetModelIdWrapper(_hrAssetModelId.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrAssetModelId.toString();
	}

	public java.lang.String toXmlString() {
		return _hrAssetModelId.toXmlString();
	}

	public HRAssetModelId getWrappedHRAssetModelId() {
		return _hrAssetModelId;
	}

	public void resetOriginalValues() {
		_hrAssetModelId.resetOriginalValues();
	}

	private HRAssetModelId _hrAssetModelId;
}