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
 * This class is a wrapper for {@link HRAsset}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRAsset
 * @generated
 */
public class HRAssetWrapper implements HRAsset {
	public HRAssetWrapper(HRAsset hrAsset) {
		_hrAsset = hrAsset;
	}

	public Class<?> getModelClass() {
		return HRAsset.class;
	}

	public String getModelClassName() {
		return HRAsset.class.getName();
	}

	/**
	* Gets the primary key of this h r asset.
	*
	* @return the primary key of this h r asset
	*/
	public long getPrimaryKey() {
		return _hrAsset.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r asset
	*
	* @param pk the primary key of this h r asset
	*/
	public void setPrimaryKey(long pk) {
		_hrAsset.setPrimaryKey(pk);
	}

	/**
	* Gets the hr asset ID of this h r asset.
	*
	* @return the hr asset ID of this h r asset
	*/
	public long getHrAssetId() {
		return _hrAsset.getHrAssetId();
	}

	/**
	* Sets the hr asset ID of this h r asset.
	*
	* @param hrAssetId the hr asset ID of this h r asset
	*/
	public void setHrAssetId(long hrAssetId) {
		_hrAsset.setHrAssetId(hrAssetId);
	}

	/**
	* Gets the group ID of this h r asset.
	*
	* @return the group ID of this h r asset
	*/
	public long getGroupId() {
		return _hrAsset.getGroupId();
	}

	/**
	* Sets the group ID of this h r asset.
	*
	* @param groupId the group ID of this h r asset
	*/
	public void setGroupId(long groupId) {
		_hrAsset.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r asset.
	*
	* @return the company ID of this h r asset
	*/
	public long getCompanyId() {
		return _hrAsset.getCompanyId();
	}

	/**
	* Sets the company ID of this h r asset.
	*
	* @param companyId the company ID of this h r asset
	*/
	public void setCompanyId(long companyId) {
		_hrAsset.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r asset.
	*
	* @return the user ID of this h r asset
	*/
	public long getUserId() {
		return _hrAsset.getUserId();
	}

	/**
	* Sets the user ID of this h r asset.
	*
	* @param userId the user ID of this h r asset
	*/
	public void setUserId(long userId) {
		_hrAsset.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r asset.
	*
	* @return the user uuid of this h r asset
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrAsset.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r asset.
	*
	* @param userUuid the user uuid of this h r asset
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrAsset.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r asset.
	*
	* @return the user name of this h r asset
	*/
	public java.lang.String getUserName() {
		return _hrAsset.getUserName();
	}

	/**
	* Sets the user name of this h r asset.
	*
	* @param userName the user name of this h r asset
	*/
	public void setUserName(java.lang.String userName) {
		_hrAsset.setUserName(userName);
	}

	/**
	* Gets the create date of this h r asset.
	*
	* @return the create date of this h r asset
	*/
	public java.util.Date getCreateDate() {
		return _hrAsset.getCreateDate();
	}

	/**
	* Sets the create date of this h r asset.
	*
	* @param createDate the create date of this h r asset
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrAsset.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r asset.
	*
	* @return the modified date of this h r asset
	*/
	public java.util.Date getModifiedDate() {
		return _hrAsset.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r asset.
	*
	* @param modifiedDate the modified date of this h r asset
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrAsset.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the hr asset definition ID of this h r asset.
	*
	* @return the hr asset definition ID of this h r asset
	*/
	public long getHrAssetDefinitionId() {
		return _hrAsset.getHrAssetDefinitionId();
	}

	/**
	* Sets the hr asset definition ID of this h r asset.
	*
	* @param hrAssetDefinitionId the hr asset definition ID of this h r asset
	*/
	public void setHrAssetDefinitionId(long hrAssetDefinitionId) {
		_hrAsset.setHrAssetDefinitionId(hrAssetDefinitionId);
	}

	/**
	* Gets the hr asset type ID of this h r asset.
	*
	* @return the hr asset type ID of this h r asset
	*/
	public long getHrAssetTypeId() {
		return _hrAsset.getHrAssetTypeId();
	}

	/**
	* Sets the hr asset type ID of this h r asset.
	*
	* @param hrAssetTypeId the hr asset type ID of this h r asset
	*/
	public void setHrAssetTypeId(long hrAssetTypeId) {
		_hrAsset.setHrAssetTypeId(hrAssetTypeId);
	}

	/**
	* Gets the serial number of this h r asset.
	*
	* @return the serial number of this h r asset
	*/
	public java.lang.String getSerialNumber() {
		return _hrAsset.getSerialNumber();
	}

	/**
	* Sets the serial number of this h r asset.
	*
	* @param serialNumber the serial number of this h r asset
	*/
	public void setSerialNumber(java.lang.String serialNumber) {
		_hrAsset.setSerialNumber(serialNumber);
	}

	/**
	* Gets the inactive date of this h r asset.
	*
	* @return the inactive date of this h r asset
	*/
	public java.util.Date getInactiveDate() {
		return _hrAsset.getInactiveDate();
	}

	/**
	* Sets the inactive date of this h r asset.
	*
	* @param inactiveDate the inactive date of this h r asset
	*/
	public void setInactiveDate(java.util.Date inactiveDate) {
		_hrAsset.setInactiveDate(inactiveDate);
	}

	public boolean isNew() {
		return _hrAsset.isNew();
	}

	public void setNew(boolean n) {
		_hrAsset.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrAsset.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrAsset.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrAsset.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrAsset.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrAsset.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrAsset.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrAsset.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRAssetWrapper((HRAsset)_hrAsset.clone());
	}

	public int compareTo(com.liferay.hr.model.HRAsset hrAsset) {
		return _hrAsset.compareTo(hrAsset);
	}

	public int hashCode() {
		return _hrAsset.hashCode();
	}

	public com.liferay.hr.model.HRAsset toEscapedModel() {
		return new HRAssetWrapper(_hrAsset.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrAsset.toString();
	}

	public java.lang.String toXmlString() {
		return _hrAsset.toXmlString();
	}

	public HRAsset getWrappedHRAsset() {
		return _hrAsset;
	}

	public void resetOriginalValues() {
		_hrAsset.resetOriginalValues();
	}

	private HRAsset _hrAsset;
}