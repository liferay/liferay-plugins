/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.samplealloymvc.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Definition}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Definition
 * @generated
 */
@ProviderType
public class DefinitionWrapper implements Definition, ModelWrapper<Definition> {
	public DefinitionWrapper(Definition definition) {
		_definition = definition;
	}

	@Override
	public Class<?> getModelClass() {
		return Definition.class;
	}

	@Override
	public String getModelClassName() {
		return Definition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("definitionId", getDefinitionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("typeId", getTypeId());
		attributes.put("manufacturer", getManufacturer());
		attributes.put("model", getModel());
		attributes.put("orderDate", getOrderDate());
		attributes.put("quantity", getQuantity());
		attributes.put("price", getPrice());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long definitionId = (Long)attributes.get("definitionId");

		if (definitionId != null) {
			setDefinitionId(definitionId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		String manufacturer = (String)attributes.get("manufacturer");

		if (manufacturer != null) {
			setManufacturer(manufacturer);
		}

		String model = (String)attributes.get("model");

		if (model != null) {
			setModel(model);
		}

		Date orderDate = (Date)attributes.get("orderDate");

		if (orderDate != null) {
			setOrderDate(orderDate);
		}

		Integer quantity = (Integer)attributes.get("quantity");

		if (quantity != null) {
			setQuantity(quantity);
		}

		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _definition.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _definition.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _definition.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _definition.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<com.liferay.samplealloymvc.model.Definition> toCacheModel() {
		return _definition.toCacheModel();
	}

	@Override
	public com.liferay.samplealloymvc.model.Definition toEscapedModel() {
		return new DefinitionWrapper(_definition.toEscapedModel());
	}

	@Override
	public com.liferay.samplealloymvc.model.Definition toUnescapedModel() {
		return new DefinitionWrapper(_definition.toUnescapedModel());
	}

	/**
	* Returns the price of this definition.
	*
	* @return the price of this definition
	*/
	@Override
	public double getPrice() {
		return _definition.getPrice();
	}

	@Override
	public int compareTo(com.liferay.samplealloymvc.model.Definition definition) {
		return _definition.compareTo(definition);
	}

	/**
	* Returns the quantity of this definition.
	*
	* @return the quantity of this definition
	*/
	@Override
	public int getQuantity() {
		return _definition.getQuantity();
	}

	@Override
	public int hashCode() {
		return _definition.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _definition.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DefinitionWrapper((Definition)_definition.clone());
	}

	/**
	* Returns the manufacturer of this definition.
	*
	* @return the manufacturer of this definition
	*/
	@Override
	public java.lang.String getManufacturer() {
		return _definition.getManufacturer();
	}

	/**
	* Returns the model of this definition.
	*
	* @return the model of this definition
	*/
	@Override
	public java.lang.String getModel() {
		return _definition.getModel();
	}

	/**
	* Returns the user name of this definition.
	*
	* @return the user name of this definition
	*/
	@Override
	public java.lang.String getUserName() {
		return _definition.getUserName();
	}

	/**
	* Returns the user uuid of this definition.
	*
	* @return the user uuid of this definition
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _definition.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _definition.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _definition.toXmlString();
	}

	/**
	* Returns the create date of this definition.
	*
	* @return the create date of this definition
	*/
	@Override
	public Date getCreateDate() {
		return _definition.getCreateDate();
	}

	/**
	* Returns the modified date of this definition.
	*
	* @return the modified date of this definition
	*/
	@Override
	public Date getModifiedDate() {
		return _definition.getModifiedDate();
	}

	/**
	* Returns the order date of this definition.
	*
	* @return the order date of this definition
	*/
	@Override
	public Date getOrderDate() {
		return _definition.getOrderDate();
	}

	/**
	* Returns the company ID of this definition.
	*
	* @return the company ID of this definition
	*/
	@Override
	public long getCompanyId() {
		return _definition.getCompanyId();
	}

	/**
	* Returns the definition ID of this definition.
	*
	* @return the definition ID of this definition
	*/
	@Override
	public long getDefinitionId() {
		return _definition.getDefinitionId();
	}

	/**
	* Returns the group ID of this definition.
	*
	* @return the group ID of this definition
	*/
	@Override
	public long getGroupId() {
		return _definition.getGroupId();
	}

	/**
	* Returns the primary key of this definition.
	*
	* @return the primary key of this definition
	*/
	@Override
	public long getPrimaryKey() {
		return _definition.getPrimaryKey();
	}

	/**
	* Returns the type ID of this definition.
	*
	* @return the type ID of this definition
	*/
	@Override
	public long getTypeId() {
		return _definition.getTypeId();
	}

	/**
	* Returns the user ID of this definition.
	*
	* @return the user ID of this definition
	*/
	@Override
	public long getUserId() {
		return _definition.getUserId();
	}

	@Override
	public void persist() {
		_definition.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_definition.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this definition.
	*
	* @param companyId the company ID of this definition
	*/
	@Override
	public void setCompanyId(long companyId) {
		_definition.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this definition.
	*
	* @param createDate the create date of this definition
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_definition.setCreateDate(createDate);
	}

	/**
	* Sets the definition ID of this definition.
	*
	* @param definitionId the definition ID of this definition
	*/
	@Override
	public void setDefinitionId(long definitionId) {
		_definition.setDefinitionId(definitionId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_definition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_definition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_definition.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this definition.
	*
	* @param groupId the group ID of this definition
	*/
	@Override
	public void setGroupId(long groupId) {
		_definition.setGroupId(groupId);
	}

	/**
	* Sets the manufacturer of this definition.
	*
	* @param manufacturer the manufacturer of this definition
	*/
	@Override
	public void setManufacturer(java.lang.String manufacturer) {
		_definition.setManufacturer(manufacturer);
	}

	/**
	* Sets the model of this definition.
	*
	* @param model the model of this definition
	*/
	@Override
	public void setModel(java.lang.String model) {
		_definition.setModel(model);
	}

	/**
	* Sets the modified date of this definition.
	*
	* @param modifiedDate the modified date of this definition
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_definition.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_definition.setNew(n);
	}

	/**
	* Sets the order date of this definition.
	*
	* @param orderDate the order date of this definition
	*/
	@Override
	public void setOrderDate(Date orderDate) {
		_definition.setOrderDate(orderDate);
	}

	/**
	* Sets the price of this definition.
	*
	* @param price the price of this definition
	*/
	@Override
	public void setPrice(double price) {
		_definition.setPrice(price);
	}

	/**
	* Sets the primary key of this definition.
	*
	* @param primaryKey the primary key of this definition
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_definition.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_definition.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the quantity of this definition.
	*
	* @param quantity the quantity of this definition
	*/
	@Override
	public void setQuantity(int quantity) {
		_definition.setQuantity(quantity);
	}

	/**
	* Sets the type ID of this definition.
	*
	* @param typeId the type ID of this definition
	*/
	@Override
	public void setTypeId(long typeId) {
		_definition.setTypeId(typeId);
	}

	/**
	* Sets the user ID of this definition.
	*
	* @param userId the user ID of this definition
	*/
	@Override
	public void setUserId(long userId) {
		_definition.setUserId(userId);
	}

	/**
	* Sets the user name of this definition.
	*
	* @param userName the user name of this definition
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_definition.setUserName(userName);
	}

	/**
	* Sets the user uuid of this definition.
	*
	* @param userUuid the user uuid of this definition
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_definition.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DefinitionWrapper)) {
			return false;
		}

		DefinitionWrapper definitionWrapper = (DefinitionWrapper)obj;

		if (Validator.equals(_definition, definitionWrapper._definition)) {
			return true;
		}

		return false;
	}

	@Override
	public Definition getWrappedModel() {
		return _definition;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _definition.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _definition.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_definition.resetOriginalValues();
	}

	private final Definition _definition;
}