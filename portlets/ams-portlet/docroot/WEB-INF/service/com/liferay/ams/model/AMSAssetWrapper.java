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
 * <a href="AMSAssetSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link AMSAsset}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSAsset
 * @generated
 */
public class AMSAssetWrapper implements AMSAsset {
	public AMSAssetWrapper(AMSAsset amsAsset) {
		_amsAsset = amsAsset;
	}

	public long getPrimaryKey() {
		return _amsAsset.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_amsAsset.setPrimaryKey(pk);
	}

	public long getAmsAssetId() {
		return _amsAsset.getAmsAssetId();
	}

	public void setAmsAssetId(long amsAssetId) {
		_amsAsset.setAmsAssetId(amsAssetId);
	}

	public long getCompanyId() {
		return _amsAsset.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_amsAsset.setCompanyId(companyId);
	}

	public long getUserId() {
		return _amsAsset.getUserId();
	}

	public void setUserId(long userId) {
		_amsAsset.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsAsset.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_amsAsset.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _amsAsset.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_amsAsset.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _amsAsset.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_amsAsset.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _amsAsset.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_amsAsset.setModifiedDate(modifiedDate);
	}

	public long getAssetDefinitionId() {
		return _amsAsset.getAssetDefinitionId();
	}

	public void setAssetDefinitionId(long assetDefinitionId) {
		_amsAsset.setAssetDefinitionId(assetDefinitionId);
	}

	public java.lang.String getSerialNumber() {
		return _amsAsset.getSerialNumber();
	}

	public void setSerialNumber(java.lang.String serialNumber) {
		_amsAsset.setSerialNumber(serialNumber);
	}

	public java.util.Date getInactiveDate() {
		return _amsAsset.getInactiveDate();
	}

	public void setInactiveDate(java.util.Date inactiveDate) {
		_amsAsset.setInactiveDate(inactiveDate);
	}

	public boolean getActive() {
		return _amsAsset.getActive();
	}

	public boolean isActive() {
		return _amsAsset.isActive();
	}

	public void setActive(boolean active) {
		_amsAsset.setActive(active);
	}

	public AMSAsset toEscapedModel() {
		return _amsAsset.toEscapedModel();
	}

	public boolean isNew() {
		return _amsAsset.isNew();
	}

	public boolean setNew(boolean n) {
		return _amsAsset.setNew(n);
	}

	public boolean isCachedModel() {
		return _amsAsset.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_amsAsset.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _amsAsset.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_amsAsset.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _amsAsset.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _amsAsset.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_amsAsset.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _amsAsset.clone();
	}

	public int compareTo(AMSAsset amsAsset) {
		return _amsAsset.compareTo(amsAsset);
	}

	public int hashCode() {
		return _amsAsset.hashCode();
	}

	public java.lang.String toString() {
		return _amsAsset.toString();
	}

	public java.lang.String toXmlString() {
		return _amsAsset.toXmlString();
	}

	public AMSAsset getWrappedAMSAsset() {
		return _amsAsset;
	}

	private AMSAsset _amsAsset;
}