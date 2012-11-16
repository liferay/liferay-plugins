/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Definition}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Definition
 * @generated
 */
public class DefinitionWrapper implements Definition, ModelWrapper<Definition> {
	public DefinitionWrapper(Definition definition) {
		_definition = definition;
	}

	public Class<?> getModelClass() {
		return Definition.class;
	}

	public String getModelClassName() {
		return Definition.class.getName();
	}

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

	/**
	* Returns the primary key of this definition.
	*
	* @return the primary key of this definition
	*/
	public long getPrimaryKey() {
		return _definition.getPrimaryKey();
	}

	/**
	* Sets the primary key of this definition.
	*
	* @param primaryKey the primary key of this definition
	*/
	public void setPrimaryKey(long primaryKey) {
		_definition.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the definition ID of this definition.
	*
	* @return the definition ID of this definition
	*/
	public long getDefinitionId() {
		return _definition.getDefinitionId();
	}

	/**
	* Sets the definition ID of this definition.
	*
	* @param definitionId the definition ID of this definition
	*/
	public void setDefinitionId(long definitionId) {
		_definition.setDefinitionId(definitionId);
	}

	/**
	* Returns the group ID of this definition.
	*
	* @return the group ID of this definition
	*/
	public long getGroupId() {
		return _definition.getGroupId();
	}

	/**
	* Sets the group ID of this definition.
	*
	* @param groupId the group ID of this definition
	*/
	public void setGroupId(long groupId) {
		_definition.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this definition.
	*
	* @return the company ID of this definition
	*/
	public long getCompanyId() {
		return _definition.getCompanyId();
	}

	/**
	* Sets the company ID of this definition.
	*
	* @param companyId the company ID of this definition
	*/
	public void setCompanyId(long companyId) {
		_definition.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this definition.
	*
	* @return the user ID of this definition
	*/
	public long getUserId() {
		return _definition.getUserId();
	}

	/**
	* Sets the user ID of this definition.
	*
	* @param userId the user ID of this definition
	*/
	public void setUserId(long userId) {
		_definition.setUserId(userId);
	}

	/**
	* Returns the user uuid of this definition.
	*
	* @return the user uuid of this definition
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _definition.getUserUuid();
	}

	/**
	* Sets the user uuid of this definition.
	*
	* @param userUuid the user uuid of this definition
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_definition.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this definition.
	*
	* @return the user name of this definition
	*/
	public java.lang.String getUserName() {
		return _definition.getUserName();
	}

	/**
	* Sets the user name of this definition.
	*
	* @param userName the user name of this definition
	*/
	public void setUserName(java.lang.String userName) {
		_definition.setUserName(userName);
	}

	/**
	* Returns the create date of this definition.
	*
	* @return the create date of this definition
	*/
	public java.util.Date getCreateDate() {
		return _definition.getCreateDate();
	}

	/**
	* Sets the create date of this definition.
	*
	* @param createDate the create date of this definition
	*/
	public void setCreateDate(java.util.Date createDate) {
		_definition.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this definition.
	*
	* @return the modified date of this definition
	*/
	public java.util.Date getModifiedDate() {
		return _definition.getModifiedDate();
	}

	/**
	* Sets the modified date of this definition.
	*
	* @param modifiedDate the modified date of this definition
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_definition.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the type ID of this definition.
	*
	* @return the type ID of this definition
	*/
	public long getTypeId() {
		return _definition.getTypeId();
	}

	/**
	* Sets the type ID of this definition.
	*
	* @param typeId the type ID of this definition
	*/
	public void setTypeId(long typeId) {
		_definition.setTypeId(typeId);
	}

	/**
	* Returns the manufacturer of this definition.
	*
	* @return the manufacturer of this definition
	*/
	public java.lang.String getManufacturer() {
		return _definition.getManufacturer();
	}

	/**
	* Sets the manufacturer of this definition.
	*
	* @param manufacturer the manufacturer of this definition
	*/
	public void setManufacturer(java.lang.String manufacturer) {
		_definition.setManufacturer(manufacturer);
	}

	/**
	* Returns the model of this definition.
	*
	* @return the model of this definition
	*/
	public java.lang.String getModel() {
		return _definition.getModel();
	}

	/**
	* Sets the model of this definition.
	*
	* @param model the model of this definition
	*/
	public void setModel(java.lang.String model) {
		_definition.setModel(model);
	}

	/**
	* Returns the order date of this definition.
	*
	* @return the order date of this definition
	*/
	public java.util.Date getOrderDate() {
		return _definition.getOrderDate();
	}

	/**
	* Sets the order date of this definition.
	*
	* @param orderDate the order date of this definition
	*/
	public void setOrderDate(java.util.Date orderDate) {
		_definition.setOrderDate(orderDate);
	}

	/**
	* Returns the quantity of this definition.
	*
	* @return the quantity of this definition
	*/
	public int getQuantity() {
		return _definition.getQuantity();
	}

	/**
	* Sets the quantity of this definition.
	*
	* @param quantity the quantity of this definition
	*/
	public void setQuantity(int quantity) {
		_definition.setQuantity(quantity);
	}

	/**
	* Returns the price of this definition.
	*
	* @return the price of this definition
	*/
	public double getPrice() {
		return _definition.getPrice();
	}

	/**
	* Sets the price of this definition.
	*
	* @param price the price of this definition
	*/
	public void setPrice(double price) {
		_definition.setPrice(price);
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

	public java.io.Serializable getPrimaryKeyObj() {
		return _definition.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_definition.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _definition.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_definition.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DefinitionWrapper((Definition)_definition.clone());
	}

	public int compareTo(com.liferay.ams.model.Definition definition) {
		return _definition.compareTo(definition);
	}

	@Override
	public int hashCode() {
		return _definition.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.ams.model.Definition> toCacheModel() {
		return _definition.toCacheModel();
	}

	public com.liferay.ams.model.Definition toEscapedModel() {
		return new DefinitionWrapper(_definition.toEscapedModel());
	}

	public com.liferay.ams.model.Definition toUnescapedModel() {
		return new DefinitionWrapper(_definition.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _definition.toString();
	}

	public java.lang.String toXmlString() {
		return _definition.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_definition.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Definition getWrappedDefinition() {
		return _definition;
	}

	public Definition getWrappedModel() {
		return _definition;
	}

	public void resetOriginalValues() {
		_definition.resetOriginalValues();
	}

	private Definition _definition;
}