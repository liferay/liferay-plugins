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
 * This class is a wrapper for {@link Asset}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Asset
 * @generated
 */
@ProviderType
public class AssetWrapper implements Asset, ModelWrapper<Asset> {
	public AssetWrapper(Asset asset) {
		_asset = asset;
	}

	@Override
	public Class<?> getModelClass() {
		return Asset.class;
	}

	@Override
	public String getModelClassName() {
		return Asset.class.getName();
	}

	@Override
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

	@Override
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
	* Returns the active of this asset.
	*
	* @return the active of this asset
	*/
	@Override
	public boolean getActive() {
		return _asset.getActive();
	}

	/**
	* Returns <code>true</code> if this asset is active.
	*
	* @return <code>true</code> if this asset is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _asset.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _asset.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _asset.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _asset.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _asset.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<com.liferay.samplealloymvc.model.Asset> toCacheModel() {
		return _asset.toCacheModel();
	}

	@Override
	public com.liferay.samplealloymvc.model.Asset toEscapedModel() {
		return new AssetWrapper(_asset.toEscapedModel());
	}

	@Override
	public com.liferay.samplealloymvc.model.Asset toUnescapedModel() {
		return new AssetWrapper(_asset.toUnescapedModel());
	}

	@Override
	public int compareTo(com.liferay.samplealloymvc.model.Asset asset) {
		return _asset.compareTo(asset);
	}

	@Override
	public int hashCode() {
		return _asset.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _asset.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AssetWrapper((Asset)_asset.clone());
	}

	/**
	* Returns the serial number of this asset.
	*
	* @return the serial number of this asset
	*/
	@Override
	public java.lang.String getSerialNumber() {
		return _asset.getSerialNumber();
	}

	/**
	* Returns the user name of this asset.
	*
	* @return the user name of this asset
	*/
	@Override
	public java.lang.String getUserName() {
		return _asset.getUserName();
	}

	/**
	* Returns the user uuid of this asset.
	*
	* @return the user uuid of this asset
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _asset.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _asset.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _asset.toXmlString();
	}

	/**
	* Returns the create date of this asset.
	*
	* @return the create date of this asset
	*/
	@Override
	public Date getCreateDate() {
		return _asset.getCreateDate();
	}

	/**
	* Returns the inactive date of this asset.
	*
	* @return the inactive date of this asset
	*/
	@Override
	public Date getInactiveDate() {
		return _asset.getInactiveDate();
	}

	/**
	* Returns the modified date of this asset.
	*
	* @return the modified date of this asset
	*/
	@Override
	public Date getModifiedDate() {
		return _asset.getModifiedDate();
	}

	/**
	* Returns the asset ID of this asset.
	*
	* @return the asset ID of this asset
	*/
	@Override
	public long getAssetId() {
		return _asset.getAssetId();
	}

	/**
	* Returns the company ID of this asset.
	*
	* @return the company ID of this asset
	*/
	@Override
	public long getCompanyId() {
		return _asset.getCompanyId();
	}

	/**
	* Returns the definition ID of this asset.
	*
	* @return the definition ID of this asset
	*/
	@Override
	public long getDefinitionId() {
		return _asset.getDefinitionId();
	}

	/**
	* Returns the primary key of this asset.
	*
	* @return the primary key of this asset
	*/
	@Override
	public long getPrimaryKey() {
		return _asset.getPrimaryKey();
	}

	/**
	* Returns the user ID of this asset.
	*
	* @return the user ID of this asset
	*/
	@Override
	public long getUserId() {
		return _asset.getUserId();
	}

	@Override
	public void persist() {
		_asset.persist();
	}

	/**
	* Sets whether this asset is active.
	*
	* @param active the active of this asset
	*/
	@Override
	public void setActive(boolean active) {
		_asset.setActive(active);
	}

	/**
	* Sets the asset ID of this asset.
	*
	* @param assetId the asset ID of this asset
	*/
	@Override
	public void setAssetId(long assetId) {
		_asset.setAssetId(assetId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_asset.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this asset.
	*
	* @param companyId the company ID of this asset
	*/
	@Override
	public void setCompanyId(long companyId) {
		_asset.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this asset.
	*
	* @param createDate the create date of this asset
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_asset.setCreateDate(createDate);
	}

	/**
	* Sets the definition ID of this asset.
	*
	* @param definitionId the definition ID of this asset
	*/
	@Override
	public void setDefinitionId(long definitionId) {
		_asset.setDefinitionId(definitionId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_asset.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_asset.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_asset.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inactive date of this asset.
	*
	* @param inactiveDate the inactive date of this asset
	*/
	@Override
	public void setInactiveDate(Date inactiveDate) {
		_asset.setInactiveDate(inactiveDate);
	}

	/**
	* Sets the modified date of this asset.
	*
	* @param modifiedDate the modified date of this asset
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_asset.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_asset.setNew(n);
	}

	/**
	* Sets the primary key of this asset.
	*
	* @param primaryKey the primary key of this asset
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_asset.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_asset.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the serial number of this asset.
	*
	* @param serialNumber the serial number of this asset
	*/
	@Override
	public void setSerialNumber(java.lang.String serialNumber) {
		_asset.setSerialNumber(serialNumber);
	}

	/**
	* Sets the user ID of this asset.
	*
	* @param userId the user ID of this asset
	*/
	@Override
	public void setUserId(long userId) {
		_asset.setUserId(userId);
	}

	/**
	* Sets the user name of this asset.
	*
	* @param userName the user name of this asset
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_asset.setUserName(userName);
	}

	/**
	* Sets the user uuid of this asset.
	*
	* @param userUuid the user uuid of this asset
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_asset.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetWrapper)) {
			return false;
		}

		AssetWrapper assetWrapper = (AssetWrapper)obj;

		if (Validator.equals(_asset, assetWrapper._asset)) {
			return true;
		}

		return false;
	}

	@Override
	public Asset getWrappedModel() {
		return _asset;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _asset.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _asset.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_asset.resetOriginalValues();
	}

	private final Asset _asset;
}