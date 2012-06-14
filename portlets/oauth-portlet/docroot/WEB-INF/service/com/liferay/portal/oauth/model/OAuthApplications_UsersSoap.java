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
public class OAuthApplications_UsersSoap implements Serializable {
	public static OAuthApplications_UsersSoap toSoapModel(
		OAuthApplications_Users model) {
		OAuthApplications_UsersSoap soapModel = new OAuthApplications_UsersSoap();

		soapModel.setOaauid(model.getOaauid());
		soapModel.setAccessToken(model.getAccessToken());
		soapModel.setAccessSecret(model.getAccessSecret());
		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setAuthorized(model.getAuthorized());
		soapModel.setUserId(model.getUserId());

		return soapModel;
	}

	public static OAuthApplications_UsersSoap[] toSoapModels(
		OAuthApplications_Users[] models) {
		OAuthApplications_UsersSoap[] soapModels = new OAuthApplications_UsersSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OAuthApplications_UsersSoap[][] toSoapModels(
		OAuthApplications_Users[][] models) {
		OAuthApplications_UsersSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OAuthApplications_UsersSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OAuthApplications_UsersSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OAuthApplications_UsersSoap[] toSoapModels(
		List<OAuthApplications_Users> models) {
		List<OAuthApplications_UsersSoap> soapModels = new ArrayList<OAuthApplications_UsersSoap>(models.size());

		for (OAuthApplications_Users model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OAuthApplications_UsersSoap[soapModels.size()]);
	}

	public OAuthApplications_UsersSoap() {
	}

	public long getPrimaryKey() {
		return _oaauid;
	}

	public void setPrimaryKey(long pk) {
		setOaauid(pk);
	}

	public long getOaauid() {
		return _oaauid;
	}

	public void setOaauid(long oaauid) {
		_oaauid = oaauid;
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

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
		_applicationId = applicationId;
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

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private long _oaauid;
	private String _accessToken;
	private String _accessSecret;
	private long _applicationId;
	private boolean _authorized;
	private long _userId;
}