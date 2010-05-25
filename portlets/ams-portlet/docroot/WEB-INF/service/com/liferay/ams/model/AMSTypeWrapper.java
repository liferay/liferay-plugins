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
 * <a href="AMSTypeSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link AMSType}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSType
 * @generated
 */
public class AMSTypeWrapper implements AMSType {
	public AMSTypeWrapper(AMSType amsType) {
		_amsType = amsType;
	}

	public long getPrimaryKey() {
		return _amsType.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_amsType.setPrimaryKey(pk);
	}

	public long getAmsTypeId() {
		return _amsType.getAmsTypeId();
	}

	public void setAmsTypeId(long amsTypeId) {
		_amsType.setAmsTypeId(amsTypeId);
	}

	public long getGroupId() {
		return _amsType.getGroupId();
	}

	public void setGroupId(long groupId) {
		_amsType.setGroupId(groupId);
	}

	public java.lang.String getName() {
		return _amsType.getName();
	}

	public void setName(java.lang.String name) {
		_amsType.setName(name);
	}

	public AMSType toEscapedModel() {
		return _amsType.toEscapedModel();
	}

	public boolean isNew() {
		return _amsType.isNew();
	}

	public boolean setNew(boolean n) {
		return _amsType.setNew(n);
	}

	public boolean isCachedModel() {
		return _amsType.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_amsType.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _amsType.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_amsType.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _amsType.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _amsType.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_amsType.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _amsType.clone();
	}

	public int compareTo(AMSType amsType) {
		return _amsType.compareTo(amsType);
	}

	public int hashCode() {
		return _amsType.hashCode();
	}

	public java.lang.String toString() {
		return _amsType.toString();
	}

	public java.lang.String toXmlString() {
		return _amsType.toXmlString();
	}

	public AMSType getWrappedAMSType() {
		return _amsType;
	}

	private AMSType _amsType;
}