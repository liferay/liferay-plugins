/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Status}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Status
 * @generated
 */
public class StatusWrapper implements Status, ModelWrapper<Status> {
	public StatusWrapper(Status status) {
		_status = status;
	}

	@Override
	public Class<?> getModelClass() {
		return Status.class;
	}

	@Override
	public String getModelClassName() {
		return Status.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("statusId", getStatusId());
		attributes.put("userId", getUserId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("online", getOnline());
		attributes.put("awake", getAwake());
		attributes.put("activePanelIds", getActivePanelIds());
		attributes.put("message", getMessage());
		attributes.put("playSound", getPlaySound());

		return attributes;
	}

	@Override
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

		String activePanelIds = (String)attributes.get("activePanelIds");

		if (activePanelIds != null) {
			setActivePanelIds(activePanelIds);
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
	@Override
	public long getPrimaryKey() {
		return _status.getPrimaryKey();
	}

	/**
	* Sets the primary key of this status.
	*
	* @param primaryKey the primary key of this status
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_status.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the status ID of this status.
	*
	* @return the status ID of this status
	*/
	@Override
	public long getStatusId() {
		return _status.getStatusId();
	}

	/**
	* Sets the status ID of this status.
	*
	* @param statusId the status ID of this status
	*/
	@Override
	public void setStatusId(long statusId) {
		_status.setStatusId(statusId);
	}

	/**
	* Returns the user ID of this status.
	*
	* @return the user ID of this status
	*/
	@Override
	public long getUserId() {
		return _status.getUserId();
	}

	/**
	* Sets the user ID of this status.
	*
	* @param userId the user ID of this status
	*/
	@Override
	public void setUserId(long userId) {
		_status.setUserId(userId);
	}

	/**
	* Returns the user uuid of this status.
	*
	* @return the user uuid of this status
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _status.getUserUuid();
	}

	/**
	* Sets the user uuid of this status.
	*
	* @param userUuid the user uuid of this status
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_status.setUserUuid(userUuid);
	}

	/**
	* Returns the modified date of this status.
	*
	* @return the modified date of this status
	*/
	@Override
	public long getModifiedDate() {
		return _status.getModifiedDate();
	}

	/**
	* Sets the modified date of this status.
	*
	* @param modifiedDate the modified date of this status
	*/
	@Override
	public void setModifiedDate(long modifiedDate) {
		_status.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the online of this status.
	*
	* @return the online of this status
	*/
	@Override
	public boolean getOnline() {
		return _status.getOnline();
	}

	/**
	* Returns <code>true</code> if this status is online.
	*
	* @return <code>true</code> if this status is online; <code>false</code> otherwise
	*/
	@Override
	public boolean isOnline() {
		return _status.isOnline();
	}

	/**
	* Sets whether this status is online.
	*
	* @param online the online of this status
	*/
	@Override
	public void setOnline(boolean online) {
		_status.setOnline(online);
	}

	/**
	* Returns the awake of this status.
	*
	* @return the awake of this status
	*/
	@Override
	public boolean getAwake() {
		return _status.getAwake();
	}

	/**
	* Returns <code>true</code> if this status is awake.
	*
	* @return <code>true</code> if this status is awake; <code>false</code> otherwise
	*/
	@Override
	public boolean isAwake() {
		return _status.isAwake();
	}

	/**
	* Sets whether this status is awake.
	*
	* @param awake the awake of this status
	*/
	@Override
	public void setAwake(boolean awake) {
		_status.setAwake(awake);
	}

	/**
	* Returns the active panel IDs of this status.
	*
	* @return the active panel IDs of this status
	*/
	@Override
	public java.lang.String getActivePanelIds() {
		return _status.getActivePanelIds();
	}

	/**
	* Sets the active panel IDs of this status.
	*
	* @param activePanelIds the active panel IDs of this status
	*/
	@Override
	public void setActivePanelIds(java.lang.String activePanelIds) {
		_status.setActivePanelIds(activePanelIds);
	}

	/**
	* Returns the message of this status.
	*
	* @return the message of this status
	*/
	@Override
	public java.lang.String getMessage() {
		return _status.getMessage();
	}

	/**
	* Sets the message of this status.
	*
	* @param message the message of this status
	*/
	@Override
	public void setMessage(java.lang.String message) {
		_status.setMessage(message);
	}

	/**
	* Returns the play sound of this status.
	*
	* @return the play sound of this status
	*/
	@Override
	public boolean getPlaySound() {
		return _status.getPlaySound();
	}

	/**
	* Returns <code>true</code> if this status is play sound.
	*
	* @return <code>true</code> if this status is play sound; <code>false</code> otherwise
	*/
	@Override
	public boolean isPlaySound() {
		return _status.isPlaySound();
	}

	/**
	* Sets whether this status is play sound.
	*
	* @param playSound the play sound of this status
	*/
	@Override
	public void setPlaySound(boolean playSound) {
		_status.setPlaySound(playSound);
	}

	@Override
	public boolean isNew() {
		return _status.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_status.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _status.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_status.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _status.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _status.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_status.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _status.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_status.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_status.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_status.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new StatusWrapper((Status)_status.clone());
	}

	@Override
	public int compareTo(com.liferay.chat.model.Status status) {
		return _status.compareTo(status);
	}

	@Override
	public int hashCode() {
		return _status.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.chat.model.Status> toCacheModel() {
		return _status.toCacheModel();
	}

	@Override
	public com.liferay.chat.model.Status toEscapedModel() {
		return new StatusWrapper(_status.toEscapedModel());
	}

	@Override
	public com.liferay.chat.model.Status toUnescapedModel() {
		return new StatusWrapper(_status.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _status.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _status.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_status.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StatusWrapper)) {
			return false;
		}

		StatusWrapper statusWrapper = (StatusWrapper)obj;

		if (Validator.equals(_status, statusWrapper._status)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public Status getWrappedStatus() {
		return _status;
	}

	@Override
	public Status getWrappedModel() {
		return _status;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _status.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _status.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_status.resetOriginalValues();
	}

	private Status _status;
}