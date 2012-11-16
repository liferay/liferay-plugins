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

package com.liferay.chat.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Status}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Status
 * @generated
 */
public class StatusWrapper implements Status, ModelWrapper<Status> {
	public StatusWrapper(Status status) {
		_status = status;
	}

	public Class<?> getModelClass() {
		return Status.class;
	}

	public String getModelClassName() {
		return Status.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("statusId", getStatusId());
		attributes.put("userId", getUserId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("online", getOnline());
		attributes.put("awake", getAwake());
		attributes.put("activePanelId", getActivePanelId());
		attributes.put("message", getMessage());
		attributes.put("playSound", getPlaySound());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long statusId = (Long)attributes.get("statusId");

		if (statusId != null) {
			setStatusId(statusId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long modifiedDate = (Long)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean online = (Boolean)attributes.get("online");

		if (online != null) {
			setOnline(online);
		}

		Boolean awake = (Boolean)attributes.get("awake");

		if (awake != null) {
			setAwake(awake);
		}

		String activePanelId = (String)attributes.get("activePanelId");

		if (activePanelId != null) {
			setActivePanelId(activePanelId);
		}

		String message = (String)attributes.get("message");

		if (message != null) {
			setMessage(message);
		}

		Boolean playSound = (Boolean)attributes.get("playSound");

		if (playSound != null) {
			setPlaySound(playSound);
		}
	}

	/**
	* Returns the primary key of this status.
	*
	* @return the primary key of this status
	*/
	public long getPrimaryKey() {
		return _status.getPrimaryKey();
	}

	/**
	* Sets the primary key of this status.
	*
	* @param primaryKey the primary key of this status
	*/
	public void setPrimaryKey(long primaryKey) {
		_status.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the status ID of this status.
	*
	* @return the status ID of this status
	*/
	public long getStatusId() {
		return _status.getStatusId();
	}

	/**
	* Sets the status ID of this status.
	*
	* @param statusId the status ID of this status
	*/
	public void setStatusId(long statusId) {
		_status.setStatusId(statusId);
	}

	/**
	* Returns the user ID of this status.
	*
	* @return the user ID of this status
	*/
	public long getUserId() {
		return _status.getUserId();
	}

	/**
	* Sets the user ID of this status.
	*
	* @param userId the user ID of this status
	*/
	public void setUserId(long userId) {
		_status.setUserId(userId);
	}

	/**
	* Returns the user uuid of this status.
	*
	* @return the user uuid of this status
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _status.getUserUuid();
	}

	/**
	* Sets the user uuid of this status.
	*
	* @param userUuid the user uuid of this status
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_status.setUserUuid(userUuid);
	}

	/**
	* Returns the modified date of this status.
	*
	* @return the modified date of this status
	*/
	public long getModifiedDate() {
		return _status.getModifiedDate();
	}

	/**
	* Sets the modified date of this status.
	*
	* @param modifiedDate the modified date of this status
	*/
	public void setModifiedDate(long modifiedDate) {
		_status.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the online of this status.
	*
	* @return the online of this status
	*/
	public boolean getOnline() {
		return _status.getOnline();
	}

	/**
	* Returns <code>true</code> if this status is online.
	*
	* @return <code>true</code> if this status is online; <code>false</code> otherwise
	*/
	public boolean isOnline() {
		return _status.isOnline();
	}

	/**
	* Sets whether this status is online.
	*
	* @param online the online of this status
	*/
	public void setOnline(boolean online) {
		_status.setOnline(online);
	}

	/**
	* Returns the awake of this status.
	*
	* @return the awake of this status
	*/
	public boolean getAwake() {
		return _status.getAwake();
	}

	/**
	* Returns <code>true</code> if this status is awake.
	*
	* @return <code>true</code> if this status is awake; <code>false</code> otherwise
	*/
	public boolean isAwake() {
		return _status.isAwake();
	}

	/**
	* Sets whether this status is awake.
	*
	* @param awake the awake of this status
	*/
	public void setAwake(boolean awake) {
		_status.setAwake(awake);
	}

	/**
	* Returns the active panel ID of this status.
	*
	* @return the active panel ID of this status
	*/
	public java.lang.String getActivePanelId() {
		return _status.getActivePanelId();
	}

	/**
	* Sets the active panel ID of this status.
	*
	* @param activePanelId the active panel ID of this status
	*/
	public void setActivePanelId(java.lang.String activePanelId) {
		_status.setActivePanelId(activePanelId);
	}

	/**
	* Returns the message of this status.
	*
	* @return the message of this status
	*/
	public java.lang.String getMessage() {
		return _status.getMessage();
	}

	/**
	* Sets the message of this status.
	*
	* @param message the message of this status
	*/
	public void setMessage(java.lang.String message) {
		_status.setMessage(message);
	}

	/**
	* Returns the play sound of this status.
	*
	* @return the play sound of this status
	*/
	public boolean getPlaySound() {
		return _status.getPlaySound();
	}

	/**
	* Returns <code>true</code> if this status is play sound.
	*
	* @return <code>true</code> if this status is play sound; <code>false</code> otherwise
	*/
	public boolean isPlaySound() {
		return _status.isPlaySound();
	}

	/**
	* Sets whether this status is play sound.
	*
	* @param playSound the play sound of this status
	*/
	public void setPlaySound(boolean playSound) {
		_status.setPlaySound(playSound);
	}

	public boolean isNew() {
		return _status.isNew();
	}

	public void setNew(boolean n) {
		_status.setNew(n);
	}

	public boolean isCachedModel() {
		return _status.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_status.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _status.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _status.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_status.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _status.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_status.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new StatusWrapper((Status)_status.clone());
	}

	public int compareTo(com.liferay.chat.model.Status status) {
		return _status.compareTo(status);
	}

	@Override
	public int hashCode() {
		return _status.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.chat.model.Status> toCacheModel() {
		return _status.toCacheModel();
	}

	public com.liferay.chat.model.Status toEscapedModel() {
		return new StatusWrapper(_status.toEscapedModel());
	}

	public com.liferay.chat.model.Status toUnescapedModel() {
		return new StatusWrapper(_status.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _status.toString();
	}

	public java.lang.String toXmlString() {
		return _status.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_status.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Status getWrappedStatus() {
		return _status;
	}

	public Status getWrappedModel() {
		return _status;
	}

	public void resetOriginalValues() {
		_status.resetOriginalValues();
	}

	private Status _status;
}