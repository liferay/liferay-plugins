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
 * This class is a wrapper for {@link HRAssetProduct}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRAssetProduct
 * @generated
 */
public class HRAssetProductWrapper implements HRAssetProduct {
	public HRAssetProductWrapper(HRAssetProduct hrAssetProduct) {
		_hrAssetProduct = hrAssetProduct;
	}

	public Class<?> getModelClass() {
		return HRAssetProduct.class;
	}

	public String getModelClassName() {
		return HRAssetProduct.class.getName();
	}

	/**
	* Gets the primary key of this h r asset product.
	*
	* @return the primary key of this h r asset product
	*/
	public long getPrimaryKey() {
		return _hrAssetProduct.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r asset product
	*
	* @param pk the primary key of this h r asset product
	*/
	public void setPrimaryKey(long pk) {
		_hrAssetProduct.setPrimaryKey(pk);
	}

	/**
	* Gets the hr asset product ID of this h r asset product.
	*
	* @return the hr asset product ID of this h r asset product
	*/
	public long getHrAssetProductId() {
		return _hrAssetProduct.getHrAssetProductId();
	}

	/**
	* Sets the hr asset product ID of this h r asset product.
	*
	* @param hrAssetProductId the hr asset product ID of this h r asset product
	*/
	public void setHrAssetProductId(long hrAssetProductId) {
		_hrAssetProduct.setHrAssetProductId(hrAssetProductId);
	}

	/**
	* Gets the group ID of this h r asset product.
	*
	* @return the group ID of this h r asset product
	*/
	public long getGroupId() {
		return _hrAssetProduct.getGroupId();
	}

	/**
	* Sets the group ID of this h r asset product.
	*
	* @param groupId the group ID of this h r asset product
	*/
	public void setGroupId(long groupId) {
		_hrAssetProduct.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r asset product.
	*
	* @return the company ID of this h r asset product
	*/
	public long getCompanyId() {
		return _hrAssetProduct.getCompanyId();
	}

	/**
	* Sets the company ID of this h r asset product.
	*
	* @param companyId the company ID of this h r asset product
	*/
	public void setCompanyId(long companyId) {
		_hrAssetProduct.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r asset product.
	*
	* @return the user ID of this h r asset product
	*/
	public long getUserId() {
		return _hrAssetProduct.getUserId();
	}

	/**
	* Sets the user ID of this h r asset product.
	*
	* @param userId the user ID of this h r asset product
	*/
	public void setUserId(long userId) {
		_hrAssetProduct.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r asset product.
	*
	* @return the user uuid of this h r asset product
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrAssetProduct.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r asset product.
	*
	* @param userUuid the user uuid of this h r asset product
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrAssetProduct.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r asset product.
	*
	* @return the user name of this h r asset product
	*/
	public java.lang.String getUserName() {
		return _hrAssetProduct.getUserName();
	}

	/**
	* Sets the user name of this h r asset product.
	*
	* @param userName the user name of this h r asset product
	*/
	public void setUserName(java.lang.String userName) {
		_hrAssetProduct.setUserName(userName);
	}

	/**
	* Gets the create date of this h r asset product.
	*
	* @return the create date of this h r asset product
	*/
	public java.util.Date getCreateDate() {
		return _hrAssetProduct.getCreateDate();
	}

	/**
	* Sets the create date of this h r asset product.
	*
	* @param createDate the create date of this h r asset product
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrAssetProduct.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r asset product.
	*
	* @return the modified date of this h r asset product
	*/
	public java.util.Date getModifiedDate() {
		return _hrAssetProduct.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r asset product.
	*
	* @param modifiedDate the modified date of this h r asset product
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrAssetProduct.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the hr asset vendor ID of this h r asset product.
	*
	* @return the hr asset vendor ID of this h r asset product
	*/
	public long getHrAssetVendorId() {
		return _hrAssetProduct.getHrAssetVendorId();
	}

	/**
	* Sets the hr asset vendor ID of this h r asset product.
	*
	* @param hrAssetVendorId the hr asset vendor ID of this h r asset product
	*/
	public void setHrAssetVendorId(long hrAssetVendorId) {
		_hrAssetProduct.setHrAssetVendorId(hrAssetVendorId);
	}

	/**
	* Gets the name of this h r asset product.
	*
	* @return the name of this h r asset product
	*/
	public java.lang.String getName() {
		return _hrAssetProduct.getName();
	}

	/**
	* Sets the name of this h r asset product.
	*
	* @param name the name of this h r asset product
	*/
	public void setName(java.lang.String name) {
		_hrAssetProduct.setName(name);
	}

	/**
	* Gets the description of this h r asset product.
	*
	* @return the description of this h r asset product
	*/
	public java.lang.String getDescription() {
		return _hrAssetProduct.getDescription();
	}

	/**
	* Sets the description of this h r asset product.
	*
	* @param description the description of this h r asset product
	*/
	public void setDescription(java.lang.String description) {
		_hrAssetProduct.setDescription(description);
	}

	public boolean isNew() {
		return _hrAssetProduct.isNew();
	}

	public void setNew(boolean n) {
		_hrAssetProduct.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrAssetProduct.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrAssetProduct.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrAssetProduct.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrAssetProduct.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrAssetProduct.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrAssetProduct.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrAssetProduct.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRAssetProductWrapper((HRAssetProduct)_hrAssetProduct.clone());
	}

	public int compareTo(com.liferay.hr.model.HRAssetProduct hrAssetProduct) {
		return _hrAssetProduct.compareTo(hrAssetProduct);
	}

	public int hashCode() {
		return _hrAssetProduct.hashCode();
	}

	public com.liferay.hr.model.HRAssetProduct toEscapedModel() {
		return new HRAssetProductWrapper(_hrAssetProduct.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrAssetProduct.toString();
	}

	public java.lang.String toXmlString() {
		return _hrAssetProduct.toXmlString();
	}

	public HRAssetProduct getWrappedHRAssetProduct() {
		return _hrAssetProduct;
	}

	public void resetOriginalValues() {
		_hrAssetProduct.resetOriginalValues();
	}

	private HRAssetProduct _hrAssetProduct;
}