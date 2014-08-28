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

package com.liferay.notifications.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserNotificationEvent}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserNotificationEvent
 * @generated
 */
public class UserNotificationEventWrapper implements UserNotificationEvent,
	ModelWrapper<UserNotificationEvent> {
	public UserNotificationEventWrapper(
		UserNotificationEvent userNotificationEvent) {
		_userNotificationEvent = userNotificationEvent;
	}

	@Override
	public Class<?> getModelClass() {
		return UserNotificationEvent.class;
	}

	@Override
	public String getModelClassName() {
		return UserNotificationEvent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("notificationEventId", getNotificationEventId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userNotificationEventId", getUserNotificationEventId());
		attributes.put("timestamp", getTimestamp());
		attributes.put("delivered", getDelivered());
		attributes.put("actionRequired", getActionRequired());
		attributes.put("archived", getArchived());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long notificationEventId = (Long)attributes.get("notificationEventId");

		if (notificationEventId != null) {
			setNotificationEventId(notificationEventId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long userNotificationEventId = (Long)attributes.get(
				"userNotificationEventId");

		if (userNotificationEventId != null) {
			setUserNotificationEventId(userNotificationEventId);
		}

		Long timestamp = (Long)attributes.get("timestamp");

		if (timestamp != null) {
			setTimestamp(timestamp);
		}

		Boolean delivered = (Boolean)attributes.get("delivered");

		if (delivered != null) {
			setDelivered(delivered);
		}

		Boolean actionRequired = (Boolean)attributes.get("actionRequired");

		if (actionRequired != null) {
			setActionRequired(actionRequired);
		}

		Boolean archived = (Boolean)attributes.get("archived");

		if (archived != null) {
			setArchived(archived);
		}
	}

	/**
	* Returns the primary key of this user notification event.
	*
	* @return the primary key of this user notification event
	*/
	@Override
	public long getPrimaryKey() {
		return _userNotificationEvent.getPrimaryKey();
	}

	/**
	* Sets the primary key of this user notification event.
	*
	* @param primaryKey the primary key of this user notification event
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_userNotificationEvent.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the notification event ID of this user notification event.
	*
	* @return the notification event ID of this user notification event
	*/
	@Override
	public long getNotificationEventId() {
		return _userNotificationEvent.getNotificationEventId();
	}

	/**
	* Sets the notification event ID of this user notification event.
	*
	* @param notificationEventId the notification event ID of this user notification event
	*/
	@Override
	public void setNotificationEventId(long notificationEventId) {
		_userNotificationEvent.setNotificationEventId(notificationEventId);
	}

	/**
	* Returns the company ID of this user notification event.
	*
	* @return the company ID of this user notification event
	*/
	@Override
	public long getCompanyId() {
		return _userNotificationEvent.getCompanyId();
	}

	/**
	* Sets the company ID of this user notification event.
	*
	* @param companyId the company ID of this user notification event
	*/
	@Override
	public void setCompanyId(long companyId) {
		_userNotificationEvent.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this user notification event.
	*
	* @return the user ID of this user notification event
	*/
	@Override
	public long getUserId() {
		return _userNotificationEvent.getUserId();
	}

	/**
	* Sets the user ID of this user notification event.
	*
	* @param userId the user ID of this user notification event
	*/
	@Override
	public void setUserId(long userId) {
		_userNotificationEvent.setUserId(userId);
	}

	/**
	* Returns the user uuid of this user notification event.
	*
	* @return the user uuid of this user notification event
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEvent.getUserUuid();
	}

	/**
	* Sets the user uuid of this user notification event.
	*
	* @param userUuid the user uuid of this user notification event
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_userNotificationEvent.setUserUuid(userUuid);
	}

	/**
	* Returns the user notification event ID of this user notification event.
	*
	* @return the user notification event ID of this user notification event
	*/
	@Override
	public long getUserNotificationEventId() {
		return _userNotificationEvent.getUserNotificationEventId();
	}

	/**
	* Sets the user notification event ID of this user notification event.
	*
	* @param userNotificationEventId the user notification event ID of this user notification event
	*/
	@Override
	public void setUserNotificationEventId(long userNotificationEventId) {
		_userNotificationEvent.setUserNotificationEventId(userNotificationEventId);
	}

	/**
	* Returns the timestamp of this user notification event.
	*
	* @return the timestamp of this user notification event
	*/
	@Override
	public long getTimestamp() {
		return _userNotificationEvent.getTimestamp();
	}

	/**
	* Sets the timestamp of this user notification event.
	*
	* @param timestamp the timestamp of this user notification event
	*/
	@Override
	public void setTimestamp(long timestamp) {
		_userNotificationEvent.setTimestamp(timestamp);
	}

	/**
	* Returns the delivered of this user notification event.
	*
	* @return the delivered of this user notification event
	*/
	@Override
	public boolean getDelivered() {
		return _userNotificationEvent.getDelivered();
	}

	/**
	* Returns <code>true</code> if this user notification event is delivered.
	*
	* @return <code>true</code> if this user notification event is delivered; <code>false</code> otherwise
	*/
	@Override
	public boolean isDelivered() {
		return _userNotificationEvent.isDelivered();
	}

	/**
	* Sets whether this user notification event is delivered.
	*
	* @param delivered the delivered of this user notification event
	*/
	@Override
	public void setDelivered(boolean delivered) {
		_userNotificationEvent.setDelivered(delivered);
	}

	/**
	* Returns the action required of this user notification event.
	*
	* @return the action required of this user notification event
	*/
	@Override
	public boolean getActionRequired() {
		return _userNotificationEvent.getActionRequired();
	}

	/**
	* Returns <code>true</code> if this user notification event is action required.
	*
	* @return <code>true</code> if this user notification event is action required; <code>false</code> otherwise
	*/
	@Override
	public boolean isActionRequired() {
		return _userNotificationEvent.isActionRequired();
	}

	/**
	* Sets whether this user notification event is action required.
	*
	* @param actionRequired the action required of this user notification event
	*/
	@Override
	public void setActionRequired(boolean actionRequired) {
		_userNotificationEvent.setActionRequired(actionRequired);
	}

	/**
	* Returns the archived of this user notification event.
	*
	* @return the archived of this user notification event
	*/
	@Override
	public boolean getArchived() {
		return _userNotificationEvent.getArchived();
	}

	/**
	* Returns <code>true</code> if this user notification event is archived.
	*
	* @return <code>true</code> if this user notification event is archived; <code>false</code> otherwise
	*/
	@Override
	public boolean isArchived() {
		return _userNotificationEvent.isArchived();
	}

	/**
	* Sets whether this user notification event is archived.
	*
	* @param archived the archived of this user notification event
	*/
	@Override
	public void setArchived(boolean archived) {
		_userNotificationEvent.setArchived(archived);
	}

	@Override
	public boolean isNew() {
		return _userNotificationEvent.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_userNotificationEvent.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _userNotificationEvent.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_userNotificationEvent.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _userNotificationEvent.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _userNotificationEvent.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_userNotificationEvent.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _userNotificationEvent.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_userNotificationEvent.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_userNotificationEvent.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_userNotificationEvent.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new UserNotificationEventWrapper((UserNotificationEvent)_userNotificationEvent.clone());
	}

	@Override
	public int compareTo(
		com.liferay.notifications.model.UserNotificationEvent userNotificationEvent) {
		return _userNotificationEvent.compareTo(userNotificationEvent);
	}

	@Override
	public int hashCode() {
		return _userNotificationEvent.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.notifications.model.UserNotificationEvent> toCacheModel() {
		return _userNotificationEvent.toCacheModel();
	}

	@Override
	public com.liferay.notifications.model.UserNotificationEvent toEscapedModel() {
		return new UserNotificationEventWrapper(_userNotificationEvent.toEscapedModel());
	}

	@Override
	public com.liferay.notifications.model.UserNotificationEvent toUnescapedModel() {
		return new UserNotificationEventWrapper(_userNotificationEvent.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _userNotificationEvent.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _userNotificationEvent.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_userNotificationEvent.persist();
	}

	@Override
	public long getDeliverBy()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEvent.getDeliverBy();
	}

	@Override
	public java.lang.String getPayload()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEvent.getPayload();
	}

	@Override
	public java.lang.String getType()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEvent.getType();
	}

	@Override
	public com.liferay.portal.model.UserNotificationEvent getUserNotificationEvent()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEvent.getUserNotificationEvent();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserNotificationEventWrapper)) {
			return false;
		}

		UserNotificationEventWrapper userNotificationEventWrapper = (UserNotificationEventWrapper)obj;

		if (Validator.equals(_userNotificationEvent,
					userNotificationEventWrapper._userNotificationEvent)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public UserNotificationEvent getWrappedUserNotificationEvent() {
		return _userNotificationEvent;
	}

	@Override
	public UserNotificationEvent getWrappedModel() {
		return _userNotificationEvent;
	}

	@Override
	public void resetOriginalValues() {
		_userNotificationEvent.resetOriginalValues();
	}

	private UserNotificationEvent _userNotificationEvent;
}