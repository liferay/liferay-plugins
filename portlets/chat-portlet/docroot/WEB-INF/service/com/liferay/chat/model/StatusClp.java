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

import com.liferay.chat.service.StatusLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class StatusClp extends BaseModelImpl<Status> implements Status {
	public StatusClp() {
	}

	public Class<?> getModelClass() {
		return Status.class;
	}

	public String getModelClassName() {
		return Status.class.getName();
	}

	public long getPrimaryKey() {
		return _statusId;
	}

	public void setPrimaryKey(long primaryKey) {
		setStatusId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_statusId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
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

	public long getStatusId() {
		return _statusId;
	}

	public void setStatusId(long statusId) {
		_statusId = statusId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public long getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public boolean getOnline() {
		return _online;
	}

	public boolean isOnline() {
		return _online;
	}

	public void setOnline(boolean online) {
		_online = online;
	}

	public boolean getAwake() {
		return _awake;
	}

	public boolean isAwake() {
		return _awake;
	}

	public void setAwake(boolean awake) {
		_awake = awake;
	}

	public String getActivePanelId() {
		return _activePanelId;
	}

	public void setActivePanelId(String activePanelId) {
		_activePanelId = activePanelId;
	}

	public String getMessage() {
		return _message;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public boolean getPlaySound() {
		return _playSound;
	}

	public boolean isPlaySound() {
		return _playSound;
	}

	public void setPlaySound(boolean playSound) {
		_playSound = playSound;
	}

	public BaseModel<?> getStatusRemoteModel() {
		return _statusRemoteModel;
	}

	public void setStatusRemoteModel(BaseModel<?> statusRemoteModel) {
		_statusRemoteModel = statusRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			StatusLocalServiceUtil.addStatus(this);
		}
		else {
			StatusLocalServiceUtil.updateStatus(this);
		}
	}

	@Override
	public Status toEscapedModel() {
		return (Status)ProxyUtil.newProxyInstance(Status.class.getClassLoader(),
			new Class[] { Status.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		StatusClp clone = new StatusClp();

		clone.setStatusId(getStatusId());
		clone.setUserId(getUserId());
		clone.setModifiedDate(getModifiedDate());
		clone.setOnline(getOnline());
		clone.setAwake(getAwake());
		clone.setActivePanelId(getActivePanelId());
		clone.setMessage(getMessage());
		clone.setPlaySound(getPlaySound());

		return clone;
	}

	public int compareTo(Status status) {
		long primaryKey = status.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		StatusClp status = null;

		try {
			status = (StatusClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = status.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{statusId=");
		sb.append(getStatusId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", online=");
		sb.append(getOnline());
		sb.append(", awake=");
		sb.append(getAwake());
		sb.append(", activePanelId=");
		sb.append(getActivePanelId());
		sb.append(", message=");
		sb.append(getMessage());
		sb.append(", playSound=");
		sb.append(getPlaySound());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.chat.model.Status");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>statusId</column-name><column-value><![CDATA[");
		sb.append(getStatusId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>online</column-name><column-value><![CDATA[");
		sb.append(getOnline());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>awake</column-name><column-value><![CDATA[");
		sb.append(getAwake());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>activePanelId</column-name><column-value><![CDATA[");
		sb.append(getActivePanelId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>message</column-name><column-value><![CDATA[");
		sb.append(getMessage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>playSound</column-name><column-value><![CDATA[");
		sb.append(getPlaySound());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _statusId;
	private long _userId;
	private String _userUuid;
	private long _modifiedDate;
	private boolean _online;
	private boolean _awake;
	private String _activePanelId;
	private String _message;
	private boolean _playSound;
	private BaseModel<?> _statusRemoteModel;
}