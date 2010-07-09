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
 * This class is a wrapper for {@link Type}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Type
 * @generated
 */
public class TypeWrapper implements Type {
	public TypeWrapper(Type type) {
		_type = type;
	}

	public long getPrimaryKey() {
		return _type.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_type.setPrimaryKey(pk);
	}

	public long getTypeId() {
		return _type.getTypeId();
	}

	public void setTypeId(long typeId) {
		_type.setTypeId(typeId);
	}

	public long getGroupId() {
		return _type.getGroupId();
	}

	public void setGroupId(long groupId) {
		_type.setGroupId(groupId);
	}

	public java.lang.String getName() {
		return _type.getName();
	}

	public void setName(java.lang.String name) {
		_type.setName(name);
	}

	public com.liferay.ams.model.Type toEscapedModel() {
		return _type.toEscapedModel();
	}

	public boolean isNew() {
		return _type.isNew();
	}

	public boolean setNew(boolean n) {
		return _type.setNew(n);
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

	public void setEscapedModel(boolean escapedModel) {
		_type.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _type.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _type.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_type.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _type.clone();
	}

	public int compareTo(com.liferay.ams.model.Type type) {
		return _type.compareTo(type);
	}

	public int hashCode() {
		return _type.hashCode();
	}

	public java.lang.String toString() {
		return _type.toString();
	}

	public java.lang.String toXmlString() {
		return _type.toXmlString();
	}

	public Type getWrappedType() {
		return _type;
	}

	private Type _type;
}