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
 * This class is a wrapper for {@link HRAssetVendorId}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HRAssetVendorId
 * @generated
 */
public class HRAssetVendorIdWrapper implements HRAssetVendorId {
	public HRAssetVendorIdWrapper(HRAssetVendorId hrAssetVendorId) {
		_hrAssetVendorId = hrAssetVendorId;
	}

	public Class<?> getModelClass() {
		return HRAssetVendorId.class;
	}

	public String getModelClassName() {
		return HRAssetVendorId.class.getName();
	}

	/**
	* Gets the primary key of this h r asset vendor ID.
	*
	* @return the primary key of this h r asset vendor ID
	*/
	public long getPrimaryKey() {
		return _hrAssetVendorId.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r asset vendor ID
	*
	* @param pk the primary key of this h r asset vendor ID
	*/
	public void setPrimaryKey(long pk) {
		_hrAssetVendorId.setPrimaryKey(pk);
	}

	/**
	* Gets the hr asset vendor ID of this h r asset vendor ID.
	*
	* @return the hr asset vendor ID of this h r asset vendor ID
	*/
	public long getHrAssetVendorId() {
		return _hrAssetVendorId.getHrAssetVendorId();
	}

	/**
	* Sets the hr asset vendor ID of this h r asset vendor ID.
	*
	* @param hrAssetVendorId the hr asset vendor ID of this h r asset vendor ID
	*/
	public void setHrAssetVendorId(long hrAssetVendorId) {
		_hrAssetVendorId.setHrAssetVendorId(hrAssetVendorId);
	}

	/**
	* Gets the group ID of this h r asset vendor ID.
	*
	* @return the group ID of this h r asset vendor ID
	*/
	public long getGroupId() {
		return _hrAssetVendorId.getGroupId();
	}

	/**
	* Sets the group ID of this h r asset vendor ID.
	*
	* @param groupId the group ID of this h r asset vendor ID
	*/
	public void setGroupId(long groupId) {
		_hrAssetVendorId.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r asset vendor ID.
	*
	* @return the company ID of this h r asset vendor ID
	*/
	public long getCompanyId() {
		return _hrAssetVendorId.getCompanyId();
	}

	/**
	* Sets the company ID of this h r asset vendor ID.
	*
	* @param companyId the company ID of this h r asset vendor ID
	*/
	public void setCompanyId(long companyId) {
		_hrAssetVendorId.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r asset vendor ID.
	*
	* @return the user ID of this h r asset vendor ID
	*/
	public long getUserId() {
		return _hrAssetVendorId.getUserId();
	}

	/**
	* Sets the user ID of this h r asset vendor ID.
	*
	* @param userId the user ID of this h r asset vendor ID
	*/
	public void setUserId(long userId) {
		_hrAssetVendorId.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r asset vendor ID.
	*
	* @return the user uuid of this h r asset vendor ID
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrAssetVendorId.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r asset vendor ID.
	*
	* @param userUuid the user uuid of this h r asset vendor ID
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrAssetVendorId.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r asset vendor ID.
	*
	* @return the user name of this h r asset vendor ID
	*/
	public java.lang.String getUserName() {
		return _hrAssetVendorId.getUserName();
	}

	/**
	* Sets the user name of this h r asset vendor ID.
	*
	* @param userName the user name of this h r asset vendor ID
	*/
	public void setUserName(java.lang.String userName) {
		_hrAssetVendorId.setUserName(userName);
	}

	/**
	* Gets the create date of this h r asset vendor ID.
	*
	* @return the create date of this h r asset vendor ID
	*/
	public java.util.Date getCreateDate() {
		return _hrAssetVendorId.getCreateDate();
	}

	/**
	* Sets the create date of this h r asset vendor ID.
	*
	* @param createDate the create date of this h r asset vendor ID
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrAssetVendorId.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r asset vendor ID.
	*
	* @return the modified date of this h r asset vendor ID
	*/
	public java.util.Date getModifiedDate() {
		return _hrAssetVendorId.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r asset vendor ID.
	*
	* @param modifiedDate the modified date of this h r asset vendor ID
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrAssetVendorId.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the name of this h r asset vendor ID.
	*
	* @return the name of this h r asset vendor ID
	*/
	public java.lang.String getName() {
		return _hrAssetVendorId.getName();
	}

	/**
	* Sets the name of this h r asset vendor ID.
	*
	* @param name the name of this h r asset vendor ID
	*/
	public void setName(java.lang.String name) {
		_hrAssetVendorId.setName(name);
	}

	/**
	* Gets the description of this h r asset vendor ID.
	*
	* @return the description of this h r asset vendor ID
	*/
	public java.lang.String getDescription() {
		return _hrAssetVendorId.getDescription();
	}

	/**
	* Sets the description of this h r asset vendor ID.
	*
	* @param description the description of this h r asset vendor ID
	*/
	public void setDescription(java.lang.String description) {
		_hrAssetVendorId.setDescription(description);
	}

	public boolean isNew() {
		return _hrAssetVendorId.isNew();
	}

	public void setNew(boolean n) {
		_hrAssetVendorId.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrAssetVendorId.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrAssetVendorId.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrAssetVendorId.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrAssetVendorId.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrAssetVendorId.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrAssetVendorId.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrAssetVendorId.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRAssetVendorIdWrapper((HRAssetVendorId)_hrAssetVendorId.clone());
	}

	public int compareTo(HRAssetVendorId hrAssetVendorId) {
		return _hrAssetVendorId.compareTo(hrAssetVendorId);
	}

	public int hashCode() {
		return _hrAssetVendorId.hashCode();
	}

	public HRAssetVendorId toEscapedModel() {
		return new HRAssetVendorIdWrapper(_hrAssetVendorId.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrAssetVendorId.toString();
	}

	public java.lang.String toXmlString() {
		return _hrAssetVendorId.toXmlString();
	}

	public HRAssetVendorId getWrappedHRAssetVendorId() {
		return _hrAssetVendorId;
	}

	public void resetOriginalValues() {
		_hrAssetVendorId.resetOriginalValues();
	}

	private HRAssetVendorId _hrAssetVendorId;
}