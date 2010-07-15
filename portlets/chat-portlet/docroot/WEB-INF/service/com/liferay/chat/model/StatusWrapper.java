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

package com.liferay.chat.model;


/**
 * <p>
 * This class is a wrapper for {@link Status}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Status
 * @generated
 */
public class StatusWrapper implements Status {
	public StatusWrapper(Status status) {
		_status = status;
	}

	public long getPrimaryKey() {
		return _status.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_status.setPrimaryKey(pk);
	}

	public long getStatusId() {
		return _status.getStatusId();
	}

	public void setStatusId(long statusId) {
		_status.setStatusId(statusId);
	}

	public long getUserId() {
		return _status.getUserId();
	}

	public void setUserId(long userId) {
		_status.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _status.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_status.setUserUuid(userUuid);
	}

	public long getModifiedDate() {
		return _status.getModifiedDate();
	}

	public void setModifiedDate(long modifiedDate) {
		_status.setModifiedDate(modifiedDate);
	}

	public boolean getOnline() {
		return _status.getOnline();
	}

	public boolean isOnline() {
		return _status.isOnline();
	}

	public void setOnline(boolean online) {
		_status.setOnline(online);
	}

	public boolean getAwake() {
		return _status.getAwake();
	}

	public boolean isAwake() {
		return _status.isAwake();
	}

	public void setAwake(boolean awake) {
		_status.setAwake(awake);
	}

	public java.lang.String getActivePanelId() {
		return _status.getActivePanelId();
	}

	public void setActivePanelId(java.lang.String activePanelId) {
		_status.setActivePanelId(activePanelId);
	}

	public java.lang.String getMessage() {
		return _status.getMessage();
	}

	public void setMessage(java.lang.String message) {
		_status.setMessage(message);
	}

	public boolean getPlaySound() {
		return _status.getPlaySound();
	}

	public boolean isPlaySound() {
		return _status.isPlaySound();
	}

	public void setPlaySound(boolean playSound) {
		_status.setPlaySound(playSound);
	}

	public com.liferay.chat.model.Status toEscapedModel() {
		return _status.toEscapedModel();
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

	public void setEscapedModel(boolean escapedModel) {
		_status.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _status.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _status.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_status.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _status.clone();
	}

	public int compareTo(com.liferay.chat.model.Status status) {
		return _status.compareTo(status);
	}

	public int hashCode() {
		return _status.hashCode();
	}

	public java.lang.String toString() {
		return _status.toString();
	}

	public java.lang.String toXmlString() {
		return _status.toXmlString();
	}

	public Status getWrappedStatus() {
		return _status;
	}

	private Status _status;
}