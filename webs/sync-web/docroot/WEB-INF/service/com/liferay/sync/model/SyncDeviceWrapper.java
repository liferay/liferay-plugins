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

package com.liferay.sync.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SyncDevice}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDevice
 * @generated
 */
@ProviderType
public class SyncDeviceWrapper implements SyncDevice, ModelWrapper<SyncDevice> {
	public SyncDeviceWrapper(SyncDevice syncDevice) {
		_syncDevice = syncDevice;
	}

	@Override
	public Class<?> getModelClass() {
		return SyncDevice.class;
	}

	@Override
	public String getModelClassName() {
		return SyncDevice.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("syncDeviceId", getSyncDeviceId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("type", getType());
		attributes.put("buildNumber", getBuildNumber());
		attributes.put("featureSet", getFeatureSet());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long syncDeviceId = (Long)attributes.get("syncDeviceId");

		if (syncDeviceId != null) {
			setSyncDeviceId(syncDeviceId);
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

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long buildNumber = (Long)attributes.get("buildNumber");

		if (buildNumber != null) {
			setBuildNumber(buildNumber);
		}

		Integer featureSet = (Integer)attributes.get("featureSet");

		if (featureSet != null) {
			setFeatureSet(featureSet);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public void checkStatus()
		throws com.liferay.portal.kernel.exception.PortalException {
		_syncDevice.checkStatus();
	}

	@Override
	public java.lang.Object clone() {
		return new SyncDeviceWrapper((SyncDevice)_syncDevice.clone());
	}

	@Override
	public int compareTo(com.liferay.sync.model.SyncDevice syncDevice) {
		return _syncDevice.compareTo(syncDevice);
	}

	/**
	* Returns the build number of this sync device.
	*
	* @return the build number of this sync device
	*/
	@Override
	public long getBuildNumber() {
		return _syncDevice.getBuildNumber();
	}

	/**
	* Returns the company ID of this sync device.
	*
	* @return the company ID of this sync device
	*/
	@Override
	public long getCompanyId() {
		return _syncDevice.getCompanyId();
	}

	/**
	* Returns the create date of this sync device.
	*
	* @return the create date of this sync device
	*/
	@Override
	public Date getCreateDate() {
		return _syncDevice.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _syncDevice.getExpandoBridge();
	}

	/**
	* Returns the feature set of this sync device.
	*
	* @return the feature set of this sync device
	*/
	@Override
	public int getFeatureSet() {
		return _syncDevice.getFeatureSet();
	}

	/**
	* Returns the modified date of this sync device.
	*
	* @return the modified date of this sync device
	*/
	@Override
	public Date getModifiedDate() {
		return _syncDevice.getModifiedDate();
	}

	/**
	* Returns the primary key of this sync device.
	*
	* @return the primary key of this sync device
	*/
	@Override
	public long getPrimaryKey() {
		return _syncDevice.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _syncDevice.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this sync device.
	*
	* @return the status of this sync device
	*/
	@Override
	public int getStatus() {
		return _syncDevice.getStatus();
	}

	/**
	* Returns the sync device ID of this sync device.
	*
	* @return the sync device ID of this sync device
	*/
	@Override
	public long getSyncDeviceId() {
		return _syncDevice.getSyncDeviceId();
	}

	/**
	* Returns the type of this sync device.
	*
	* @return the type of this sync device
	*/
	@Override
	public java.lang.String getType() {
		return _syncDevice.getType();
	}

	/**
	* Returns the user ID of this sync device.
	*
	* @return the user ID of this sync device
	*/
	@Override
	public long getUserId() {
		return _syncDevice.getUserId();
	}

	/**
	* Returns the user name of this sync device.
	*
	* @return the user name of this sync device
	*/
	@Override
	public java.lang.String getUserName() {
		return _syncDevice.getUserName();
	}

	/**
	* Returns the user uuid of this sync device.
	*
	* @return the user uuid of this sync device
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _syncDevice.getUserUuid();
	}

	/**
	* Returns the uuid of this sync device.
	*
	* @return the uuid of this sync device
	*/
	@Override
	public java.lang.String getUuid() {
		return _syncDevice.getUuid();
	}

	@Override
	public boolean hasSetModifiedDate() {
		return _syncDevice.hasSetModifiedDate();
	}

	@Override
	public int hashCode() {
		return _syncDevice.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _syncDevice.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _syncDevice.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _syncDevice.isNew();
	}

	@Override
	public boolean isSupported() {
		return _syncDevice.isSupported();
	}

	@Override
	public void persist() {
		_syncDevice.persist();
	}

	/**
	* Sets the build number of this sync device.
	*
	* @param buildNumber the build number of this sync device
	*/
	@Override
	public void setBuildNumber(long buildNumber) {
		_syncDevice.setBuildNumber(buildNumber);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_syncDevice.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this sync device.
	*
	* @param companyId the company ID of this sync device
	*/
	@Override
	public void setCompanyId(long companyId) {
		_syncDevice.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this sync device.
	*
	* @param createDate the create date of this sync device
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_syncDevice.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_syncDevice.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_syncDevice.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_syncDevice.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the feature set of this sync device.
	*
	* @param featureSet the feature set of this sync device
	*/
	@Override
	public void setFeatureSet(int featureSet) {
		_syncDevice.setFeatureSet(featureSet);
	}

	/**
	* Sets the modified date of this sync device.
	*
	* @param modifiedDate the modified date of this sync device
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_syncDevice.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_syncDevice.setNew(n);
	}

	/**
	* Sets the primary key of this sync device.
	*
	* @param primaryKey the primary key of this sync device
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_syncDevice.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_syncDevice.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this sync device.
	*
	* @param status the status of this sync device
	*/
	@Override
	public void setStatus(int status) {
		_syncDevice.setStatus(status);
	}

	/**
	* Sets the sync device ID of this sync device.
	*
	* @param syncDeviceId the sync device ID of this sync device
	*/
	@Override
	public void setSyncDeviceId(long syncDeviceId) {
		_syncDevice.setSyncDeviceId(syncDeviceId);
	}

	/**
	* Sets the type of this sync device.
	*
	* @param type the type of this sync device
	*/
	@Override
	public void setType(java.lang.String type) {
		_syncDevice.setType(type);
	}

	/**
	* Sets the user ID of this sync device.
	*
	* @param userId the user ID of this sync device
	*/
	@Override
	public void setUserId(long userId) {
		_syncDevice.setUserId(userId);
	}

	/**
	* Sets the user name of this sync device.
	*
	* @param userName the user name of this sync device
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_syncDevice.setUserName(userName);
	}

	/**
	* Sets the user uuid of this sync device.
	*
	* @param userUuid the user uuid of this sync device
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_syncDevice.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this sync device.
	*
	* @param uuid the uuid of this sync device
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_syncDevice.setUuid(uuid);
	}

	@Override
	public boolean supports(int featureSet) {
		return _syncDevice.supports(featureSet);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<com.liferay.sync.model.SyncDevice> toCacheModel() {
		return _syncDevice.toCacheModel();
	}

	@Override
	public com.liferay.sync.model.SyncDevice toEscapedModel() {
		return new SyncDeviceWrapper(_syncDevice.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _syncDevice.toString();
	}

	@Override
	public com.liferay.sync.model.SyncDevice toUnescapedModel() {
		return new SyncDeviceWrapper(_syncDevice.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _syncDevice.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncDeviceWrapper)) {
			return false;
		}

		SyncDeviceWrapper syncDeviceWrapper = (SyncDeviceWrapper)obj;

		if (Validator.equals(_syncDevice, syncDeviceWrapper._syncDevice)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _syncDevice.getStagedModelType();
	}

	@Override
	public SyncDevice getWrappedModel() {
		return _syncDevice;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _syncDevice.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _syncDevice.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_syncDevice.resetOriginalValues();
	}

	private final SyncDevice _syncDevice;
}