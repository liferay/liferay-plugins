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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class ApplicationSoap implements Serializable {
	public static ApplicationSoap toSoapModel(Application model) {
		ApplicationSoap soapModel = new ApplicationSoap();

		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setWebsite(model.getWebsite());
		soapModel.setCallBackURL(model.getCallBackURL());
		soapModel.setAccessLevel(model.getAccessLevel());
		soapModel.setConsumerKey(model.getConsumerKey());
		soapModel.setConsumerSecret(model.getConsumerSecret());
		soapModel.setLogoId(model.getLogoId());

		return soapModel;
	}

	public static ApplicationSoap[] toSoapModels(Application[] models) {
		ApplicationSoap[] soapModels = new ApplicationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ApplicationSoap[][] toSoapModels(Application[][] models) {
		ApplicationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ApplicationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ApplicationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ApplicationSoap[] toSoapModels(List<Application> models) {
		List<ApplicationSoap> soapModels = new ArrayList<ApplicationSoap>(models.size());

		for (Application model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ApplicationSoap[soapModels.size()]);
	}

	public ApplicationSoap() {
	}

	public long getPrimaryKey() {
		return _applicationId;
	}

	public void setPrimaryKey(long pk) {
		setApplicationId(pk);
	}

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
		_applicationId = applicationId;
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

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getWebsite() {
		return _website;
	}

	public void setWebsite(String website) {
		_website = website;
	}

	public String getCallBackURL() {
		return _callBackURL;
	}

	public void setCallBackURL(String callBackURL) {
		_callBackURL = callBackURL;
	}

	public int getAccessLevel() {
		return _accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		_accessLevel = accessLevel;
	}

	public String getConsumerKey() {
		return _consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		_consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return _consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		_consumerSecret = consumerSecret;
	}

	public long getLogoId() {
		return _logoId;
	}

	public void setLogoId(long logoId) {
		_logoId = logoId;
	}

	private long _applicationId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private String _website;
	private String _callBackURL;
	private int _accessLevel;
	private String _consumerKey;
	private String _consumerSecret;
	private long _logoId;
}