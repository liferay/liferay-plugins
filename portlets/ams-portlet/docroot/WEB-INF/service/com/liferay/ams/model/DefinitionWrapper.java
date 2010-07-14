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
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link Definition}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Definition
 * @generated
 */
public class DefinitionWrapper implements Definition {
	public DefinitionWrapper(Definition definition) {
		_definition = definition;
	}

	public long getPrimaryKey() {
		return _definition.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_definition.setPrimaryKey(pk);
	}

	public long getDefinitionId() {
		return _definition.getDefinitionId();
	}

	public void setDefinitionId(long definitionId) {
		_definition.setDefinitionId(definitionId);
	}

	public long getGroupId() {
		return _definition.getGroupId();
	}

	public void setGroupId(long groupId) {
		_definition.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _definition.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_definition.setCompanyId(companyId);
	}

	public long getUserId() {
		return _definition.getUserId();
	}

	public void setUserId(long userId) {
		_definition.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _definition.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_definition.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _definition.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_definition.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _definition.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_definition.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _definition.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_definition.setModifiedDate(modifiedDate);
	}

	public long getTypeId() {
		return _definition.getTypeId();
	}

	public void setTypeId(long typeId) {
		_definition.setTypeId(typeId);
	}

	public java.lang.String getManufacturer() {
		return _definition.getManufacturer();
	}

	public void setManufacturer(java.lang.String manufacturer) {
		_definition.setManufacturer(manufacturer);
	}

	public java.lang.String getModel() {
		return _definition.getModel();
	}

	public void setModel(java.lang.String model) {
		_definition.setModel(model);
	}

	public java.util.Date getOrderDate() {
		return _definition.getOrderDate();
	}

	public void setOrderDate(java.util.Date orderDate) {
		_definition.setOrderDate(orderDate);
	}

	public int getQuantity() {
		return _definition.getQuantity();
	}

	public void setQuantity(int quantity) {
		_definition.setQuantity(quantity);
	}

	public double getPrice() {
		return _definition.getPrice();
	}

	public void setPrice(double price) {
		_definition.setPrice(price);
	}

	public com.liferay.ams.model.Definition toEscapedModel() {
		return _definition.toEscapedModel();
	}

	public boolean isNew() {
		return _definition.isNew();
	}

	public void setNew(boolean n) {
		_definition.setNew(n);
	}

	public boolean isCachedModel() {
		return _definition.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_definition.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _definition.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_definition.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _definition.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _definition.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_definition.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _definition.clone();
	}

	public int compareTo(com.liferay.ams.model.Definition definition) {
		return _definition.compareTo(definition);
	}

	public int hashCode() {
		return _definition.hashCode();
	}

	public java.lang.String toString() {
		return _definition.toString();
	}

	public java.lang.String toXmlString() {
		return _definition.toXmlString();
	}

	public Definition getWrappedDefinition() {
		return _definition;
	}

	private Definition _definition;
}