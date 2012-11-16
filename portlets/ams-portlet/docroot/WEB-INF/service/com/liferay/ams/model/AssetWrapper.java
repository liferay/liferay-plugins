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
 * This class is a wrapper for {@link Asset}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Asset
 * @generated
 */
public class AssetWrapper implements Asset, ModelWrapper<Asset> {
	public AssetWrapper(Asset asset) {
		_asset = asset;
	}

	public Class<?> getModelClass() {
		return Asset.class;
	}

	public String getModelClassName() {
		return Asset.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetId", getAssetId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("definitionId", getDefinitionId());
		attributes.put("serialNumber", getSerialNumber());
		attributes.put("inactiveDate", getInactiveDate());
		attributes.put("active", getActive());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetId = (Long)attributes.get("assetId");

		if (assetId != null) {
			setAssetId(assetId);
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

		Long definitionId = (Long)attributes.get("definitionId");

		if (definitionId != null) {
			setDefinitionId(definitionId);
		}

		String serialNumber = (String)attributes.get("serialNumber");

		if (serialNumber != null) {
			setSerialNumber(serialNumber);
		}

		Date inactiveDate = (Date)attributes.get("inactiveDate");

		if (inactiveDate != null) {
			setInactiveDate(inactiveDate);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	/**
	* Returns the primary key of this asset.
	*
	* @return the primary key of this asset
	*/
	public long getPrimaryKey() {
		return _asset.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset.
	*
	* @param primaryKey the primary key of this asset
	*/
	public void setPrimaryKey(long primaryKey) {
		_asset.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset ID of this asset.
	*
	* @return the asset ID of this asset
	*/
	public long getAssetId() {
		return _asset.getAssetId();
	}

	/**
	* Sets the asset ID of this asset.
	*
	* @param assetId the asset ID of this asset
	*/
	public void setAssetId(long assetId) {
		_asset.setAssetId(assetId);
	}

	/**
	* Returns the company ID of this asset.
	*
	* @return the company ID of this asset
	*/
	public long getCompanyId() {
		return _asset.getCompanyId();
	}

	/**
	* Sets the company ID of this asset.
	*
	* @param companyId the company ID of this asset
	*/
	public void setCompanyId(long companyId) {
		_asset.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this asset.
	*
	* @return the user ID of this asset
	*/
	public long getUserId() {
		return _asset.getUserId();
	}

	/**
	* Sets the user ID of this asset.
	*
	* @param userId the user ID of this asset
	*/
	public void setUserId(long userId) {
		_asset.setUserId(userId);
	}

	/**
	* Returns the user uuid of this asset.
	*
	* @return the user uuid of this asset
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _asset.getUserUuid();
	}

	/**
	* Sets the user uuid of this asset.
	*
	* @param userUuid the user uuid of this asset
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_asset.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this asset.
	*
	* @return the user name of this asset
	*/
	public java.lang.String getUserName() {
		return _asset.getUserName();
	}

	/**
	* Sets the user name of this asset.
	*
	* @param userName the user name of this asset
	*/
	public void setUserName(java.lang.String userName) {
		_asset.setUserName(userName);
	}

	/**
	* Returns the create date of this asset.
	*
	* @return the create date of this asset
	*/
	public java.util.Date getCreateDate() {
		return _asset.getCreateDate();
	}

	/**
	* Sets the create date of this asset.
	*
	* @param createDate the create date of this asset
	*/
	public void setCreateDate(java.util.Date createDate) {
		_asset.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this asset.
	*
	* @return the modified date of this asset
	*/
	public java.util.Date getModifiedDate() {
		return _asset.getModifiedDate();
	}

	/**
	* Sets the modified date of this asset.
	*
	* @param modifiedDate the modified date of this asset
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_asset.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the definition ID of this asset.
	*
	* @return the definition ID of this asset
	*/
	public long getDefinitionId() {
		return _asset.getDefinitionId();
	}

	/**
	* Sets the definition ID of this asset.
	*
	* @param definitionId the definition ID of this asset
	*/
	public void setDefinitionId(long definitionId) {
		_asset.setDefinitionId(definitionId);
	}

	/**
	* Returns the serial number of this asset.
	*
	* @return the serial number of this asset
	*/
	public java.lang.String getSerialNumber() {
		return _asset.getSerialNumber();
	}

	/**
	* Sets the serial number of this asset.
	*
	* @param serialNumber the serial number of this asset
	*/
	public void setSerialNumber(java.lang.String serialNumber) {
		_asset.setSerialNumber(serialNumber);
	}

	/**
	* Returns the inactive date of this asset.
	*
	* @return the inactive date of this asset
	*/
	public java.util.Date getInactiveDate() {
		return _asset.getInactiveDate();
	}

	/**
	* Sets the inactive date of this asset.
	*
	* @param inactiveDate the inactive date of this asset
	*/
	public void setInactiveDate(java.util.Date inactiveDate) {
		_asset.setInactiveDate(inactiveDate);
	}

	/**
	* Returns the active of this asset.
	*
	* @return the active of this asset
	*/
	public boolean getActive() {
		return _asset.getActive();
	}

	/**
	* Returns <code>true</code> if this asset is active.
	*
	* @return <code>true</code> if this asset is active; <code>false</code> otherwise
	*/
	public boolean isActive() {
		return _asset.isActive();
	}

	/**
	* Sets whether this asset is active.
	*
	* @param active the active of this asset
	*/
	public void setActive(boolean active) {
		_asset.setActive(active);
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

	public java.io.Serializable getPrimaryKeyObj() {
		return _asset.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_asset.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _asset.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_asset.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetWrapper((Asset)_asset.clone());
	}

	public int compareTo(com.liferay.ams.model.Asset asset) {
		return _asset.compareTo(asset);
	}

	@Override
	public int hashCode() {
		return _asset.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.ams.model.Asset> toCacheModel() {
		return _asset.toCacheModel();
	}

	public com.liferay.ams.model.Asset toEscapedModel() {
		return new AssetWrapper(_asset.toEscapedModel());
	}

	public com.liferay.ams.model.Asset toUnescapedModel() {
		return new AssetWrapper(_asset.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _asset.toString();
	}

	public java.lang.String toXmlString() {
		return _asset.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_asset.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Asset getWrappedAsset() {
		return _asset;
	}

	public Asset getWrappedModel() {
		return _asset;
	}

	public void resetOriginalValues() {
		_asset.resetOriginalValues();
	}

	private Asset _asset;
}