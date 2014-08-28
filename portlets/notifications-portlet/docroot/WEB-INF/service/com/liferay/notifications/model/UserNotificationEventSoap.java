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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserNotificationEventSoap implements Serializable {
	public static UserNotificationEventSoap toSoapModel(
		UserNotificationEvent model) {
		UserNotificationEventSoap soapModel = new UserNotificationEventSoap();

		soapModel.setNotificationEventId(model.getNotificationEventId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserNotificationEventId(model.getUserNotificationEventId());
		soapModel.setTimestamp(model.getTimestamp());
		soapModel.setDelivered(model.getDelivered());
		soapModel.setActionRequired(model.getActionRequired());
		soapModel.setArchived(model.getArchived());

		return soapModel;
	}

	public static UserNotificationEventSoap[] toSoapModels(
		UserNotificationEvent[] models) {
		UserNotificationEventSoap[] soapModels = new UserNotificationEventSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserNotificationEventSoap[][] toSoapModels(
		UserNotificationEvent[][] models) {
		UserNotificationEventSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserNotificationEventSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserNotificationEventSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserNotificationEventSoap[] toSoapModels(
		List<UserNotificationEvent> models) {
		List<UserNotificationEventSoap> soapModels = new ArrayList<UserNotificationEventSoap>(models.size());

		for (UserNotificationEvent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserNotificationEventSoap[soapModels.size()]);
	}

	public UserNotificationEventSoap() {
	}

	public long getPrimaryKey() {
		return _notificationEventId;
	}

	public void setPrimaryKey(long pk) {
		setNotificationEventId(pk);
	}

	public long getNotificationEventId() {
		return _notificationEventId;
	}

	public void setNotificationEventId(long notificationEventId) {
		_notificationEventId = notificationEventId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getUserNotificationEventId() {
		return _userNotificationEventId;
	}

	public void setUserNotificationEventId(long userNotificationEventId) {
		_userNotificationEventId = userNotificationEventId;
	}

	public long getTimestamp() {
		return _timestamp;
	}

	public void setTimestamp(long timestamp) {
		_timestamp = timestamp;
	}

	public boolean getDelivered() {
		return _delivered;
	}

	public boolean isDelivered() {
		return _delivered;
	}

	public void setDelivered(boolean delivered) {
		_delivered = delivered;
	}

	public boolean getActionRequired() {
		return _actionRequired;
	}

	public boolean isActionRequired() {
		return _actionRequired;
	}

	public void setActionRequired(boolean actionRequired) {
		_actionRequired = actionRequired;
	}

	public boolean getArchived() {
		return _archived;
	}

	public boolean isArchived() {
		return _archived;
	}

	public void setArchived(boolean archived) {
		_archived = archived;
	}

	private long _notificationEventId;
	private long _companyId;
	private long _userId;
	private long _userNotificationEventId;
	private long _timestamp;
	private boolean _delivered;
	private boolean _actionRequired;
	private boolean _archived;
}