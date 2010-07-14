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
 * This class is a wrapper for {@link Asset}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Asset
 * @generated
 */
public class AssetWrapper implements Asset {
	public AssetWrapper(Asset asset) {
		_asset = asset;
	}

	public long getPrimaryKey() {
		return _asset.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_asset.setPrimaryKey(pk);
	}

	public long getAssetId() {
		return _asset.getAssetId();
	}

	public void setAssetId(long assetId) {
		_asset.setAssetId(assetId);
	}

	public long getCompanyId() {
		return _asset.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_asset.setCompanyId(companyId);
	}

	public long getUserId() {
		return _asset.getUserId();
	}

	public void setUserId(long userId) {
		_asset.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _asset.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_asset.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _asset.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_asset.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _asset.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_asset.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _asset.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_asset.setModifiedDate(modifiedDate);
	}

	public long getDefinitionId() {
		return _asset.getDefinitionId();
	}

	public void setDefinitionId(long definitionId) {
		_asset.setDefinitionId(definitionId);
	}

	public java.lang.String getSerialNumber() {
		return _asset.getSerialNumber();
	}

	public void setSerialNumber(java.lang.String serialNumber) {
		_asset.setSerialNumber(serialNumber);
	}

	public java.util.Date getInactiveDate() {
		return _asset.getInactiveDate();
	}

	public void setInactiveDate(java.util.Date inactiveDate) {
		_asset.setInactiveDate(inactiveDate);
	}

	public boolean getActive() {
		return _asset.getActive();
	}

	public boolean isActive() {
		return _asset.isActive();
	}

	public void setActive(boolean active) {
		_asset.setActive(active);
	}

	public com.liferay.ams.model.Asset toEscapedModel() {
		return _asset.toEscapedModel();
	}

	public boolean isNew() {
		return _asset.isNew();
	}

	public void setNew(boolean n) {
		_asset.setNew(n);
	}

	public boolean isCachedModel() {
		return _asset.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_asset.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _asset.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_asset.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _asset.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _asset.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_asset.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _asset.clone();
	}

	public int compareTo(com.liferay.ams.model.Asset asset) {
		return _asset.compareTo(asset);
	}

	public int hashCode() {
		return _asset.hashCode();
	}

	public java.lang.String toString() {
		return _asset.toString();
	}

	public java.lang.String toXmlString() {
		return _asset.toXmlString();
	}

	public Asset getWrappedAsset() {
		return _asset;
	}

	private Asset _asset;
}