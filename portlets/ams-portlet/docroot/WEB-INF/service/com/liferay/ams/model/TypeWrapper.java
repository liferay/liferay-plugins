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

package com.liferay.ams.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Type}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Type
 * @generated
 */
@ProviderType
public class TypeWrapper implements Type, ModelWrapper<Type> {
	public TypeWrapper(Type type) {
		_type = type;
	}

	@Override
	public Class<?> getModelClass() {
		return Type.class;
	}

	@Override
	public String getModelClassName() {
		return Type.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("typeId", getTypeId());
		attributes.put("groupId", getGroupId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new TypeWrapper((Type)_type.clone());
	}

	@Override
	public int compareTo(com.liferay.ams.model.Type type) {
		return _type.compareTo(type);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _type.getExpandoBridge();
	}

	/**
	* Returns the group ID of this type.
	*
	* @return the group ID of this type
	*/
	@Override
	public long getGroupId() {
		return _type.getGroupId();
	}

	/**
	* Returns the name of this type.
	*
	* @return the name of this type
	*/
	@Override
	public java.lang.String getName() {
		return _type.getName();
	}

	/**
	* Returns the primary key of this type.
	*
	* @return the primary key of this type
	*/
	@Override
	public long getPrimaryKey() {
		return _type.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _type.getPrimaryKeyObj();
	}

	/**
	* Returns the type ID of this type.
	*
	* @return the type ID of this type
	*/
	@Override
	public long getTypeId() {
		return _type.getTypeId();
	}

	@Override
	public int hashCode() {
		return _type.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _type.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _type.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _type.isNew();
	}

	@Override
	public void persist() {
		_type.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_type.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_type.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_type.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_type.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this type.
	*
	* @param groupId the group ID of this type
	*/
	@Override
	public void setGroupId(long groupId) {
		_type.setGroupId(groupId);
	}

	/**
	* Sets the name of this type.
	*
	* @param name the name of this type
	*/
	@Override
	public void setName(java.lang.String name) {
		_type.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_type.setNew(n);
	}

	/**
	* Sets the primary key of this type.
	*
	* @param primaryKey the primary key of this type
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_type.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_type.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type ID of this type.
	*
	* @param typeId the type ID of this type
	*/
	@Override
	public void setTypeId(long typeId) {
		_type.setTypeId(typeId);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.ams.model.Type> toCacheModel() {
		return _type.toCacheModel();
	}

	@Override
	public com.liferay.ams.model.Type toEscapedModel() {
		return new TypeWrapper(_type.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _type.toString();
	}

	@Override
	public com.liferay.ams.model.Type toUnescapedModel() {
		return new TypeWrapper(_type.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _type.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TypeWrapper)) {
			return false;
		}

		TypeWrapper typeWrapper = (TypeWrapper)obj;

		if (Validator.equals(_type, typeWrapper._type)) {
			return true;
		}

		return false;
	}

	@Override
	public Type getWrappedModel() {
		return _type;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _type.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _type.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_type.resetOriginalValues();
	}

	private final Type _type;
}