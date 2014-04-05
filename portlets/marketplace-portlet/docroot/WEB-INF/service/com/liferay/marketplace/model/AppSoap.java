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

package com.liferay.marketplace.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.marketplace.service.http.AppServiceSoap}.
 *
 * @author Ryan Park
 * @see com.liferay.marketplace.service.http.AppServiceSoap
 * @generated
 */
public class AppSoap implements Serializable {
	public static AppSoap toSoapModel(App model) {
		AppSoap soapModel = new AppSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAppId(model.getAppId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRemoteAppId(model.getRemoteAppId());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setCategory(model.getCategory());
		soapModel.setIconURL(model.getIconURL());
		soapModel.setVersion(model.getVersion());

		return soapModel;
	}

	public static AppSoap[] toSoapModels(App[] models) {
		AppSoap[] soapModels = new AppSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AppSoap[][] toSoapModels(App[][] models) {
		AppSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AppSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AppSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AppSoap[] toSoapModels(List<App> models) {
		List<AppSoap> soapModels = new ArrayList<AppSoap>(models.size());

		for (App model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AppSoap[soapModels.size()]);
	}

	public AppSoap() {
	}

	public long getPrimaryKey() {
		return _appId;
	}

	public void setPrimaryKey(long pk) {
		setAppId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAppId() {
		return _appId;
	}

	public void setAppId(long appId) {
		_appId = appId;
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

	public long getRemoteAppId() {
		return _remoteAppId;
	}

	public void setRemoteAppId(long remoteAppId) {
		_remoteAppId = remoteAppId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getCategory() {
		return _category;
	}

	public void setCategory(String category) {
		_category = category;
	}

	public String getIconURL() {
		return _iconURL;
	}

	public void setIconURL(String iconURL) {
		_iconURL = iconURL;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	private String _uuid;
	private long _appId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _remoteAppId;
	private String _title;
	private String _description;
	private String _category;
	private String _iconURL;
	private String _version;
}