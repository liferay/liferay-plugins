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

package com.liferay.portal.oauth.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class ApplicationUserSoap implements Serializable {
	public static ApplicationUserSoap toSoapModel(ApplicationUser model) {
		ApplicationUserSoap soapModel = new ApplicationUserSoap();

		soapModel.setOaauId(model.getOaauId());
		soapModel.setUserId(model.getUserId());
		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setAccessToken(model.getAccessToken());
		soapModel.setAccessSecret(model.getAccessSecret());
		soapModel.setAuthorized(model.getAuthorized());

		return soapModel;
	}

	public static ApplicationUserSoap[] toSoapModels(ApplicationUser[] models) {
		ApplicationUserSoap[] soapModels = new ApplicationUserSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ApplicationUserSoap[][] toSoapModels(
		ApplicationUser[][] models) {
		ApplicationUserSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ApplicationUserSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ApplicationUserSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ApplicationUserSoap[] toSoapModels(
		List<ApplicationUser> models) {
		List<ApplicationUserSoap> soapModels = new ArrayList<ApplicationUserSoap>(models.size());

		for (ApplicationUser model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ApplicationUserSoap[soapModels.size()]);
	}

	public ApplicationUserSoap() {
	}

	public long getPrimaryKey() {
		return _oaauId;
	}

	public void setPrimaryKey(long pk) {
		setOaauId(pk);
	}

	public long getOaauId() {
		return _oaauId;
	}

	public void setOaauId(long oaauId) {
		_oaauId = oaauId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
		_applicationId = applicationId;
	}

	public String getAccessToken() {
		return _accessToken;
	}

	public void setAccessToken(String accessToken) {
		_accessToken = accessToken;
	}

	public String getAccessSecret() {
		return _accessSecret;
	}

	public void setAccessSecret(String accessSecret) {
		_accessSecret = accessSecret;
	}

	public boolean getAuthorized() {
		return _authorized;
	}

	public boolean isAuthorized() {
		return _authorized;
	}

	public void setAuthorized(boolean authorized) {
		_authorized = authorized;
	}

	private long _oaauId;
	private long _userId;
	private long _applicationId;
	private String _accessToken;
	private String _accessSecret;
	private boolean _authorized;
}