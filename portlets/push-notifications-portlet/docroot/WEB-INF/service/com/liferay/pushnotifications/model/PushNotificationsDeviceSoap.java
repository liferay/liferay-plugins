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

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.pushnotifications.service.http.PushNotificationsDeviceServiceSoap}.
 *
 * @author Bruno Farache
 * @see com.liferay.pushnotifications.service.http.PushNotificationsDeviceServiceSoap
 * @generated
 */
@ProviderType
public class PushNotificationsDeviceSoap implements Serializable {
	public static PushNotificationsDeviceSoap toSoapModel(
		PushNotificationsDevice model) {
		PushNotificationsDeviceSoap soapModel = new PushNotificationsDeviceSoap();

		soapModel.setPushNotificationsDeviceId(model.getPushNotificationsDeviceId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setPlatform(model.getPlatform());
		soapModel.setToken(model.getToken());

		return soapModel;
	}

	public static PushNotificationsDeviceSoap[] toSoapModels(
		PushNotificationsDevice[] models) {
		PushNotificationsDeviceSoap[] soapModels = new PushNotificationsDeviceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PushNotificationsDeviceSoap[][] toSoapModels(
		PushNotificationsDevice[][] models) {
		PushNotificationsDeviceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PushNotificationsDeviceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PushNotificationsDeviceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PushNotificationsDeviceSoap[] toSoapModels(
		List<PushNotificationsDevice> models) {
		List<PushNotificationsDeviceSoap> soapModels = new ArrayList<PushNotificationsDeviceSoap>(models.size());

		for (PushNotificationsDevice model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PushNotificationsDeviceSoap[soapModels.size()]);
	}

	public PushNotificationsDeviceSoap() {
	}

	public long getPrimaryKey() {
		return _pushNotificationsDeviceId;
	}

	public void setPrimaryKey(long pk) {
		setPushNotificationsDeviceId(pk);
	}

	public long getPushNotificationsDeviceId() {
		return _pushNotificationsDeviceId;
	}

	public void setPushNotificationsDeviceId(long pushNotificationsDeviceId) {
		_pushNotificationsDeviceId = pushNotificationsDeviceId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getPlatform() {
		return _platform;
	}

	public void setPlatform(String platform) {
		_platform = platform;
	}

	public String getToken() {
		return _token;
	}

	public void setToken(String token) {
		_token = token;
	}

	private long _pushNotificationsDeviceId;
	private long _userId;
	private Date _createDate;
	private String _platform;
	private String _token;
}