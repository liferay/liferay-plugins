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
 * This class is a wrapper for {@link HRAssetDefinition}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRAssetDefinition
 * @generated
 */
public class HRAssetDefinitionWrapper implements HRAssetDefinition {
	public HRAssetDefinitionWrapper(HRAssetDefinition hrAssetDefinition) {
		_hrAssetDefinition = hrAssetDefinition;
	}

	public Class<?> getModelClass() {
		return HRAssetDefinition.class;
	}

	public String getModelClassName() {
		return HRAssetDefinition.class.getName();
	}

	/**
	* Returns the primary key of this h r asset definition.
	*
	* @return the primary key of this h r asset definition
	*/
	public long getPrimaryKey() {
		return _hrAssetDefinition.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r asset definition.
	*
	* @param primaryKey the primary key of this h r asset definition
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrAssetDefinition.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr asset definition ID of this h r asset definition.
	*
	* @return the hr asset definition ID of this h r asset definition
	*/
	public long getHrAssetDefinitionId() {
		return _hrAssetDefinition.getHrAssetDefinitionId();
	}

	/**
	* Sets the hr asset definition ID of this h r asset definition.
	*
	* @param hrAssetDefinitionId the hr asset definition ID of this h r asset definition
	*/
	public void setHrAssetDefinitionId(long hrAssetDefinitionId) {
		_hrAssetDefinition.setHrAssetDefinitionId(hrAssetDefinitionId);
	}

	/**
	* Returns the group ID of this h r asset definition.
	*
	* @return the group ID of this h r asset definition
	*/
	public long getGroupId() {
		return _hrAssetDefinition.getGroupId();
	}

	/**
	* Sets the group ID of this h r asset definition.
	*
	* @param groupId the group ID of this h r asset definition
	*/
	public void setGroupId(long groupId) {
		_hrAssetDefinition.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r asset definition.
	*
	* @return the company ID of this h r asset definition
	*/
	public long getCompanyId() {
		return _hrAssetDefinition.getCompanyId();
	}

	/**
	* Sets the company ID of this h r asset definition.
	*
	* @param companyId the company ID of this h r asset definition
	*/
	public void setCompanyId(long companyId) {
		_hrAssetDefinition.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r asset definition.
	*
	* @return the user ID of this h r asset definition
	*/
	public long getUserId() {
		return _hrAssetDefinition.getUserId();
	}

	/**
	* Sets the user ID of this h r asset definition.
	*
	* @param userId the user ID of this h r asset definition
	*/
	public void setUserId(long userId) {
		_hrAssetDefinition.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r asset definition.
	*
	* @return the user uuid of this h r asset definition
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrAssetDefinition.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r asset definition.
	*
	* @param userUuid the user uuid of this h r asset definition
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrAssetDefinition.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r asset definition.
	*
	* @return the user name of this h r asset definition
	*/
	public java.lang.String getUserName() {
		return _hrAssetDefinition.getUserName();
	}

	/**
	* Sets the user name of this h r asset definition.
	*
	* @param userName the user name of this h r asset definition
	*/
	public void setUserName(java.lang.String userName) {
		_hrAssetDefinition.setUserName(userName);
	}

	/**
	* Returns the create date of this h r asset definition.
	*
	* @return the create date of this h r asset definition
	*/
	public java.util.Date getCreateDate() {
		return _hrAssetDefinition.getCreateDate();
	}

	/**
	* Sets the create date of this h r asset definition.
	*
	* @param createDate the create date of this h r asset definition
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrAssetDefinition.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r asset definition.
	*
	* @return the modified date of this h r asset definition
	*/
	public java.util.Date getModifiedDate() {
		return _hrAssetDefinition.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r asset definition.
	*
	* @param modifiedDate the modified date of this h r asset definition
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrAssetDefinition.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the hr asset product ID of this h r asset definition.
	*
	* @return the hr asset product ID of this h r asset definition
	*/
	public java.lang.String getHrAssetProductId() {
		return _hrAssetDefinition.getHrAssetProductId();
	}

	/**
	* Sets the hr asset product ID of this h r asset definition.
	*
	* @param hrAssetProductId the hr asset product ID of this h r asset definition
	*/
	public void setHrAssetProductId(java.lang.String hrAssetProductId) {
		_hrAssetDefinition.setHrAssetProductId(hrAssetProductId);
	}

	/**
	* Returns the hr asset type ID of this h r asset definition.
	*
	* @return the hr asset type ID of this h r asset definition
	*/
	public long getHrAssetTypeId() {
		return _hrAssetDefinition.getHrAssetTypeId();
	}

	/**
	* Sets the hr asset type ID of this h r asset definition.
	*
	* @param hrAssetTypeId the hr asset type ID of this h r asset definition
	*/
	public void setHrAssetTypeId(long hrAssetTypeId) {
		_hrAssetDefinition.setHrAssetTypeId(hrAssetTypeId);
	}

	/**
	* Returns the hr asset vendor ID of this h r asset definition.
	*
	* @return the hr asset vendor ID of this h r asset definition
	*/
	public long getHrAssetVendorId() {
		return _hrAssetDefinition.getHrAssetVendorId();
	}

	/**
	* Sets the hr asset vendor ID of this h r asset definition.
	*
	* @param hrAssetVendorId the hr asset vendor ID of this h r asset definition
	*/
	public void setHrAssetVendorId(long hrAssetVendorId) {
		_hrAssetDefinition.setHrAssetVendorId(hrAssetVendorId);
	}

	/**
	* Returns the definition number of this h r asset definition.
	*
	* @return the definition number of this h r asset definition
	*/
	public java.lang.String getDefinitionNumber() {
		return _hrAssetDefinition.getDefinitionNumber();
	}

	/**
	* Sets the definition number of this h r asset definition.
	*
	* @param definitionNumber the definition number of this h r asset definition
	*/
	public void setDefinitionNumber(java.lang.String definitionNumber) {
		_hrAssetDefinition.setDefinitionNumber(definitionNumber);
	}

	/**
	* Returns the order ID of this h r asset definition.
	*
	* @return the order ID of this h r asset definition
	*/
	public java.util.Date getOrderId() {
		return _hrAssetDefinition.getOrderId();
	}

	/**
	* Sets the order ID of this h r asset definition.
	*
	* @param orderId the order ID of this h r asset definition
	*/
	public void setOrderId(java.util.Date orderId) {
		_hrAssetDefinition.setOrderId(orderId);
	}

	/**
	* Returns the order date of this h r asset definition.
	*
	* @return the order date of this h r asset definition
	*/
	public java.util.Date getOrderDate() {
		return _hrAssetDefinition.getOrderDate();
	}

	/**
	* Sets the order date of this h r asset definition.
	*
	* @param orderDate the order date of this h r asset definition
	*/
	public void setOrderDate(java.util.Date orderDate) {
		_hrAssetDefinition.setOrderDate(orderDate);
	}

	/**
	* Returns the quantity of this h r asset definition.
	*
	* @return the quantity of this h r asset definition
	*/
	public int getQuantity() {
		return _hrAssetDefinition.getQuantity();
	}

	/**
	* Sets the quantity of this h r asset definition.
	*
	* @param quantity the quantity of this h r asset definition
	*/
	public void setQuantity(int quantity) {
		_hrAssetDefinition.setQuantity(quantity);
	}

	/**
	* Returns the individual price of this h r asset definition.
	*
	* @return the individual price of this h r asset definition
	*/
	public double getIndividualPrice() {
		return _hrAssetDefinition.getIndividualPrice();
	}

	/**
	* Sets the individual price of this h r asset definition.
	*
	* @param individualPrice the individual price of this h r asset definition
	*/
	public void setIndividualPrice(double individualPrice) {
		_hrAssetDefinition.setIndividualPrice(individualPrice);
	}

	public boolean isNew() {
		return _hrAssetDefinition.isNew();
	}

	public void setNew(boolean n) {
		_hrAssetDefinition.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrAssetDefinition.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrAssetDefinition.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrAssetDefinition.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrAssetDefinition.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrAssetDefinition.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrAssetDefinition.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrAssetDefinition.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrAssetDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HRAssetDefinitionWrapper((HRAssetDefinition)_hrAssetDefinition.clone());
	}

	public int compareTo(
		com.liferay.hr.model.HRAssetDefinition hrAssetDefinition) {
		return _hrAssetDefinition.compareTo(hrAssetDefinition);
	}

	@Override
	public int hashCode() {
		return _hrAssetDefinition.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.hr.model.HRAssetDefinition> toCacheModel() {
		return _hrAssetDefinition.toCacheModel();
	}

	public com.liferay.hr.model.HRAssetDefinition toEscapedModel() {
		return new HRAssetDefinitionWrapper(_hrAssetDefinition.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hrAssetDefinition.toString();
	}

	public java.lang.String toXmlString() {
		return _hrAssetDefinition.toXmlString();
	}

	public HRAssetDefinition getWrappedHRAssetDefinition() {
		return _hrAssetDefinition;
	}

	public void resetOriginalValues() {
		_hrAssetDefinition.resetOriginalValues();
	}

	private HRAssetDefinition _hrAssetDefinition;
}