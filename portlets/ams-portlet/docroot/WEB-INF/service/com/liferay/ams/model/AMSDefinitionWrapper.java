/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.ams.model;


/**
 * <a href="AMSDefinitionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link AMSDefinition}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSDefinition
 * @generated
 */
public class AMSDefinitionWrapper implements AMSDefinition {
	public AMSDefinitionWrapper(AMSDefinition amsDefinition) {
		_amsDefinition = amsDefinition;
	}

	public long getPrimaryKey() {
		return _amsDefinition.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_amsDefinition.setPrimaryKey(pk);
	}

	public long getAssetDefinitionId() {
		return _amsDefinition.getAssetDefinitionId();
	}

	public void setAssetDefinitionId(long assetDefinitionId) {
		_amsDefinition.setAssetDefinitionId(assetDefinitionId);
	}

	public long getGroupId() {
		return _amsDefinition.getGroupId();
	}

	public void setGroupId(long groupId) {
		_amsDefinition.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _amsDefinition.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_amsDefinition.setCompanyId(companyId);
	}

	public long getUserId() {
		return _amsDefinition.getUserId();
	}

	public void setUserId(long userId) {
		_amsDefinition.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsDefinition.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_amsDefinition.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _amsDefinition.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_amsDefinition.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _amsDefinition.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_amsDefinition.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _amsDefinition.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_amsDefinition.setModifiedDate(modifiedDate);
	}

	public long getAmsTypeId() {
		return _amsDefinition.getAmsTypeId();
	}

	public void setAmsTypeId(long amsTypeId) {
		_amsDefinition.setAmsTypeId(amsTypeId);
	}

	public java.lang.String getManufacturer() {
		return _amsDefinition.getManufacturer();
	}

	public void setManufacturer(java.lang.String manufacturer) {
		_amsDefinition.setManufacturer(manufacturer);
	}

	public java.lang.String getModel() {
		return _amsDefinition.getModel();
	}

	public void setModel(java.lang.String model) {
		_amsDefinition.setModel(model);
	}

	public java.util.Date getOrderDate() {
		return _amsDefinition.getOrderDate();
	}

	public void setOrderDate(java.util.Date orderDate) {
		_amsDefinition.setOrderDate(orderDate);
	}

	public int getQuantity() {
		return _amsDefinition.getQuantity();
	}

	public void setQuantity(int quantity) {
		_amsDefinition.setQuantity(quantity);
	}

	public double getPrice() {
		return _amsDefinition.getPrice();
	}

	public void setPrice(double price) {
		_amsDefinition.setPrice(price);
	}

	public AMSDefinition toEscapedModel() {
		return _amsDefinition.toEscapedModel();
	}

	public boolean isNew() {
		return _amsDefinition.isNew();
	}

	public boolean setNew(boolean n) {
		return _amsDefinition.setNew(n);
	}

	public boolean isCachedModel() {
		return _amsDefinition.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_amsDefinition.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _amsDefinition.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_amsDefinition.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _amsDefinition.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _amsDefinition.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_amsDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _amsDefinition.clone();
	}

	public int compareTo(AMSDefinition amsDefinition) {
		return _amsDefinition.compareTo(amsDefinition);
	}

	public int hashCode() {
		return _amsDefinition.hashCode();
	}

	public java.lang.String toString() {
		return _amsDefinition.toString();
	}

	public java.lang.String toXmlString() {
		return _amsDefinition.toXmlString();
	}

	public AMSDefinition getWrappedAMSDefinition() {
		return _amsDefinition;
	}

	private AMSDefinition _amsDefinition;
}