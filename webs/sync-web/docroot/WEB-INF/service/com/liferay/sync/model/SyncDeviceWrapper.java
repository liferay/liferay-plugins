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

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

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

	/**
	* Returns the primary key of this sync device.
	*
	* @return the primary key of this sync device
	*/
	@Override
	public long getPrimaryKey() {
		return _syncDevice.getPrimaryKey();
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

	/**
	* Returns the uuid of this sync device.
	*
	* @return the uuid of this sync device
	*/
	@Override
	public java.lang.String getUuid() {
		return _syncDevice.getUuid();
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
	* Sets the sync device ID of this sync device.
	*
	* @param syncDeviceId the sync device ID of this sync device
	*/
	@Override
	public void setSyncDeviceId(long syncDeviceId) {
		_syncDevice.setSyncDeviceId(syncDeviceId);
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
	* Sets the company ID of this sync device.
	*
	* @param companyId the company ID of this sync device
	*/
	@Override
	public void setCompanyId(long companyId) {
		_syncDevice.setCompanyId(companyId);
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
	* Sets the user ID of this sync device.
	*
	* @param userId the user ID of this sync device
	*/
	@Override
	public void setUserId(long userId) {
		_syncDevice.setUserId(userId);
	}

	/**
	* Returns the user uuid of this sync device.
	*
	* @return the user uuid of this sync device
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDevice.getUserUuid();
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
	* Returns the user name of this sync device.
	*
	* @return the user name of this sync device
	*/
	@Override
	public java.lang.String getUserName() {
		return _syncDevice.getUserName();
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
	* Returns the create date of this sync device.
	*
	* @return the create date of this sync device
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _syncDevice.getCreateDate();
	}

	/**
	* Sets the create date of this sync device.
	*
	* @param createDate the create date of this sync device
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_syncDevice.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this sync device.
	*
	* @return the modified date of this sync device
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _syncDevice.getModifiedDate();
	}

	/**
	* Sets the modified date of this sync device.
	*
	* @param modifiedDate the modified date of this sync device
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_syncDevice.setModifiedDate(modifiedDate);
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
	* Sets the type of this sync device.
	*
	* @param type the type of this sync device
	*/
	@Override
	public void setType(java.lang.String type) {
		_syncDevice.setType(type);
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
	* Sets the build number of this sync device.
	*
	* @param buildNumber the build number of this sync device
	*/
	@Override
	public void setBuildNumber(long buildNumber) {
		_syncDevice.setBuildNumber(buildNumber);
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
	* Sets the feature set of this sync device.
	*
	* @param featureSet the feature set of this sync device
	*/
	@Override
	public void setFeatureSet(int featureSet) {
		_syncDevice.setFeatureSet(featureSet);
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
	* Sets the status of this sync device.
	*
	* @param status the status of this sync device
	*/
	@Override
	public void setStatus(int status) {
		_syncDevice.setStatus(status);
	}

	@Override
	public boolean isNew() {
		return _syncDevice.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_syncDevice.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _syncDevice.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_syncDevice.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _syncDevice.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _syncDevice.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_syncDevice.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _syncDevice.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_syncDevice.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_syncDevice.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_syncDevice.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SyncDeviceWrapper((SyncDevice)_syncDevice.clone());
	}

	@Override
	public int compareTo(com.liferay.sync.model.SyncDevice syncDevice) {
		return _syncDevice.compareTo(syncDevice);
	}

	@Override
	public int hashCode() {
		return _syncDevice.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.sync.model.SyncDevice> toCacheModel() {
		return _syncDevice.toCacheModel();
	}

	@Override
	public com.liferay.sync.model.SyncDevice toEscapedModel() {
		return new SyncDeviceWrapper(_syncDevice.toEscapedModel());
	}

	@Override
	public com.liferay.sync.model.SyncDevice toUnescapedModel() {
		return new SyncDeviceWrapper(_syncDevice.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _syncDevice.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _syncDevice.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_syncDevice.persist();
	}

	@Override
	public void checkStatus()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_syncDevice.checkStatus();
	}

	@Override
	public boolean isSupported()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDevice.isSupported();
	}

	@Override
	public boolean supports(int featureSet) {
		return _syncDevice.supports(featureSet);
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

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SyncDevice getWrappedSyncDevice() {
		return _syncDevice;
	}

	@Override
	public SyncDevice getWrappedModel() {
		return _syncDevice;
	}

	@Override
	public void resetOriginalValues() {
		_syncDevice.resetOriginalValues();
	}

	private SyncDevice _syncDevice;
}