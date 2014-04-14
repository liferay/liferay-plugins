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

package com.liferay.pushnotifications.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PushNotificationsDevice}.
 * </p>
 *
 * @author Bruno Farache
 * @see PushNotificationsDevice
 * @generated
 */
public class PushNotificationsDeviceWrapper implements PushNotificationsDevice,
	ModelWrapper<PushNotificationsDevice> {
	public PushNotificationsDeviceWrapper(
		PushNotificationsDevice pushNotificationsDevice) {
		_pushNotificationsDevice = pushNotificationsDevice;
	}

	@Override
	public Class<?> getModelClass() {
		return PushNotificationsDevice.class;
	}

	@Override
	public String getModelClassName() {
		return PushNotificationsDevice.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("pushNotificationsDeviceId",
			getPushNotificationsDeviceId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("platform", getPlatform());
		attributes.put("token", getToken());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long pushNotificationsDeviceId = (Long)attributes.get(
				"pushNotificationsDeviceId");

		if (pushNotificationsDeviceId != null) {
			setPushNotificationsDeviceId(pushNotificationsDeviceId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String platform = (String)attributes.get("platform");

		if (platform != null) {
			setPlatform(platform);
		}

		String token = (String)attributes.get("token");

		if (token != null) {
			setToken(token);
		}
	}

	/**
	* Returns the primary key of this push notifications device.
	*
	* @return the primary key of this push notifications device
	*/
	@Override
	public long getPrimaryKey() {
		return _pushNotificationsDevice.getPrimaryKey();
	}

	/**
	* Sets the primary key of this push notifications device.
	*
	* @param primaryKey the primary key of this push notifications device
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_pushNotificationsDevice.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the push notifications device ID of this push notifications device.
	*
	* @return the push notifications device ID of this push notifications device
	*/
	@Override
	public long getPushNotificationsDeviceId() {
		return _pushNotificationsDevice.getPushNotificationsDeviceId();
	}

	/**
	* Sets the push notifications device ID of this push notifications device.
	*
	* @param pushNotificationsDeviceId the push notifications device ID of this push notifications device
	*/
	@Override
	public void setPushNotificationsDeviceId(long pushNotificationsDeviceId) {
		_pushNotificationsDevice.setPushNotificationsDeviceId(pushNotificationsDeviceId);
	}

	/**
	* Returns the user ID of this push notifications device.
	*
	* @return the user ID of this push notifications device
	*/
	@Override
	public long getUserId() {
		return _pushNotificationsDevice.getUserId();
	}

	/**
	* Sets the user ID of this push notifications device.
	*
	* @param userId the user ID of this push notifications device
	*/
	@Override
	public void setUserId(long userId) {
		_pushNotificationsDevice.setUserId(userId);
	}

	/**
	* Returns the user uuid of this push notifications device.
	*
	* @return the user uuid of this push notifications device
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsDevice.getUserUuid();
	}

	/**
	* Sets the user uuid of this push notifications device.
	*
	* @param userUuid the user uuid of this push notifications device
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_pushNotificationsDevice.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this push notifications device.
	*
	* @return the create date of this push notifications device
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _pushNotificationsDevice.getCreateDate();
	}

	/**
	* Sets the create date of this push notifications device.
	*
	* @param createDate the create date of this push notifications device
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_pushNotificationsDevice.setCreateDate(createDate);
	}

	/**
	* Returns the platform of this push notifications device.
	*
	* @return the platform of this push notifications device
	*/
	@Override
	public java.lang.String getPlatform() {
		return _pushNotificationsDevice.getPlatform();
	}

	/**
	* Sets the platform of this push notifications device.
	*
	* @param platform the platform of this push notifications device
	*/
	@Override
	public void setPlatform(java.lang.String platform) {
		_pushNotificationsDevice.setPlatform(platform);
	}

	/**
	* Returns the token of this push notifications device.
	*
	* @return the token of this push notifications device
	*/
	@Override
	public java.lang.String getToken() {
		return _pushNotificationsDevice.getToken();
	}

	/**
	* Sets the token of this push notifications device.
	*
	* @param token the token of this push notifications device
	*/
	@Override
	public void setToken(java.lang.String token) {
		_pushNotificationsDevice.setToken(token);
	}

	@Override
	public boolean isNew() {
		return _pushNotificationsDevice.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_pushNotificationsDevice.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _pushNotificationsDevice.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_pushNotificationsDevice.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _pushNotificationsDevice.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _pushNotificationsDevice.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_pushNotificationsDevice.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _pushNotificationsDevice.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_pushNotificationsDevice.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_pushNotificationsDevice.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_pushNotificationsDevice.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new PushNotificationsDeviceWrapper((PushNotificationsDevice)_pushNotificationsDevice.clone());
	}

	@Override
	public int compareTo(
		com.liferay.pushnotifications.model.PushNotificationsDevice pushNotificationsDevice) {
		return _pushNotificationsDevice.compareTo(pushNotificationsDevice);
	}

	@Override
	public int hashCode() {
		return _pushNotificationsDevice.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.pushnotifications.model.PushNotificationsDevice> toCacheModel() {
		return _pushNotificationsDevice.toCacheModel();
	}

	@Override
	public com.liferay.pushnotifications.model.PushNotificationsDevice toEscapedModel() {
		return new PushNotificationsDeviceWrapper(_pushNotificationsDevice.toEscapedModel());
	}

	@Override
	public com.liferay.pushnotifications.model.PushNotificationsDevice toUnescapedModel() {
		return new PushNotificationsDeviceWrapper(_pushNotificationsDevice.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _pushNotificationsDevice.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _pushNotificationsDevice.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_pushNotificationsDevice.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PushNotificationsDeviceWrapper)) {
			return false;
		}

		PushNotificationsDeviceWrapper pushNotificationsDeviceWrapper = (PushNotificationsDeviceWrapper)obj;

		if (Validator.equals(_pushNotificationsDevice,
					pushNotificationsDeviceWrapper._pushNotificationsDevice)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public PushNotificationsDevice getWrappedPushNotificationsDevice() {
		return _pushNotificationsDevice;
	}

	@Override
	public PushNotificationsDevice getWrappedModel() {
		return _pushNotificationsDevice;
	}

	@Override
	public void resetOriginalValues() {
		_pushNotificationsDevice.resetOriginalValues();
	}

	private PushNotificationsDevice _pushNotificationsDevice;
}