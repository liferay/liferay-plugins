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

package com.liferay.ams.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Type}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Type
 * @generated
 */
public class TypeWrapper implements Type, ModelWrapper<Type> {
	public TypeWrapper(Type type) {
		_type = type;
	}

	public Class<?> getModelClass() {
		return Type.class;
	}

	public String getModelClassName() {
		return Type.class.getName();
	}

	/**
	* Returns the primary key of this type.
	*
	* @return the primary key of this type
	*/
	public long getPrimaryKey() {
		return _type.getPrimaryKey();
	}

	/**
	* Sets the primary key of this type.
	*
	* @param primaryKey the primary key of this type
	*/
	public void setPrimaryKey(long primaryKey) {
		_type.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the type ID of this type.
	*
	* @return the type ID of this type
	*/
	public long getTypeId() {
		return _type.getTypeId();
	}

	/**
	* Sets the type ID of this type.
	*
	* @param typeId the type ID of this type
	*/
	public void setTypeId(long typeId) {
		_type.setTypeId(typeId);
	}

	/**
	* Returns the group ID of this type.
	*
	* @return the group ID of this type
	*/
	public long getGroupId() {
		return _type.getGroupId();
	}

	/**
	* Sets the group ID of this type.
	*
	* @param groupId the group ID of this type
	*/
	public void setGroupId(long groupId) {
		_type.setGroupId(groupId);
	}

	/**
	* Returns the name of this type.
	*
	* @return the name of this type
	*/
	public java.lang.String getName() {
		return _type.getName();
	}

	/**
	* Sets the name of this type.
	*
	* @param name the name of this type
	*/
	public void setName(java.lang.String name) {
		_type.setName(name);
	}

	public boolean isNew() {
		return _type.isNew();
	}

	public void setNew(boolean n) {
		_type.setNew(n);
	}

	public boolean isCachedModel() {
		return _type.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_type.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _type.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _type.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_type.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _type.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_type.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TypeWrapper((Type)_type.clone());
	}

	public int compareTo(com.liferay.ams.model.Type type) {
		return _type.compareTo(type);
	}

	@Override
	public int hashCode() {
		return _type.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.ams.model.Type> toCacheModel() {
		return _type.toCacheModel();
	}

	public com.liferay.ams.model.Type toEscapedModel() {
		return new TypeWrapper(_type.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _type.toString();
	}

	public java.lang.String toXmlString() {
		return _type.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_type.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Type getWrappedType() {
		return _type;
	}

	public Type getWrappedModel() {
		return _type;
	}

	public void resetOriginalValues() {
		_type.resetOriginalValues();
	}

	private Type _type;
}