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

package com.liferay.marketplace.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link App}.
 * </p>
 *
 * @author    Ryan Park
 * @see       App
 * @generated
 */
public class AppWrapper implements App, ModelWrapper<App> {
	public AppWrapper(App app) {
		_app = app;
	}

	public Class<?> getModelClass() {
		return App.class;
	}

	public String getModelClassName() {
		return App.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("appId", getAppId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("remoteAppId", getRemoteAppId());
		attributes.put("version", getVersion());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long appId = (Long)attributes.get("appId");

		if (appId != null) {
			setAppId(appId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long remoteAppId = (Long)attributes.get("remoteAppId");

		if (remoteAppId != null) {
			setRemoteAppId(remoteAppId);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}
	}

	/**
	* Returns the primary key of this app.
	*
	* @return the primary key of this app
	*/
	public long getPrimaryKey() {
		return _app.getPrimaryKey();
	}

	/**
	* Sets the primary key of this app.
	*
	* @param primaryKey the primary key of this app
	*/
	public void setPrimaryKey(long primaryKey) {
		_app.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this app.
	*
	* @return the uuid of this app
	*/
	public java.lang.String getUuid() {
		return _app.getUuid();
	}

	/**
	* Sets the uuid of this app.
	*
	* @param uuid the uuid of this app
	*/
	public void setUuid(java.lang.String uuid) {
		_app.setUuid(uuid);
	}

	/**
	* Returns the app ID of this app.
	*
	* @return the app ID of this app
	*/
	public long getAppId() {
		return _app.getAppId();
	}

	/**
	* Sets the app ID of this app.
	*
	* @param appId the app ID of this app
	*/
	public void setAppId(long appId) {
		_app.setAppId(appId);
	}

	/**
	* Returns the company ID of this app.
	*
	* @return the company ID of this app
	*/
	public long getCompanyId() {
		return _app.getCompanyId();
	}

	/**
	* Sets the company ID of this app.
	*
	* @param companyId the company ID of this app
	*/
	public void setCompanyId(long companyId) {
		_app.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this app.
	*
	* @return the user ID of this app
	*/
	public long getUserId() {
		return _app.getUserId();
	}

	/**
	* Sets the user ID of this app.
	*
	* @param userId the user ID of this app
	*/
	public void setUserId(long userId) {
		_app.setUserId(userId);
	}

	/**
	* Returns the user uuid of this app.
	*
	* @return the user uuid of this app
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _app.getUserUuid();
	}

	/**
	* Sets the user uuid of this app.
	*
	* @param userUuid the user uuid of this app
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_app.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this app.
	*
	* @return the user name of this app
	*/
	public java.lang.String getUserName() {
		return _app.getUserName();
	}

	/**
	* Sets the user name of this app.
	*
	* @param userName the user name of this app
	*/
	public void setUserName(java.lang.String userName) {
		_app.setUserName(userName);
	}

	/**
	* Returns the create date of this app.
	*
	* @return the create date of this app
	*/
	public java.util.Date getCreateDate() {
		return _app.getCreateDate();
	}

	/**
	* Sets the create date of this app.
	*
	* @param createDate the create date of this app
	*/
	public void setCreateDate(java.util.Date createDate) {
		_app.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this app.
	*
	* @return the modified date of this app
	*/
	public java.util.Date getModifiedDate() {
		return _app.getModifiedDate();
	}

	/**
	* Sets the modified date of this app.
	*
	* @param modifiedDate the modified date of this app
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_app.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the remote app ID of this app.
	*
	* @return the remote app ID of this app
	*/
	public long getRemoteAppId() {
		return _app.getRemoteAppId();
	}

	/**
	* Sets the remote app ID of this app.
	*
	* @param remoteAppId the remote app ID of this app
	*/
	public void setRemoteAppId(long remoteAppId) {
		_app.setRemoteAppId(remoteAppId);
	}

	/**
	* Returns the version of this app.
	*
	* @return the version of this app
	*/
	public java.lang.String getVersion() {
		return _app.getVersion();
	}

	/**
	* Sets the version of this app.
	*
	* @param version the version of this app
	*/
	public void setVersion(java.lang.String version) {
		_app.setVersion(version);
	}

	public boolean isNew() {
		return _app.isNew();
	}

	public void setNew(boolean n) {
		_app.setNew(n);
	}

	public boolean isCachedModel() {
		return _app.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_app.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _app.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _app.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_app.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _app.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_app.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AppWrapper((App)_app.clone());
	}

	public int compareTo(com.liferay.marketplace.model.App app) {
		return _app.compareTo(app);
	}

	@Override
	public int hashCode() {
		return _app.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.marketplace.model.App> toCacheModel() {
		return _app.toCacheModel();
	}

	public com.liferay.marketplace.model.App toEscapedModel() {
		return new AppWrapper(_app.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _app.toString();
	}

	public java.lang.String toXmlString() {
		return _app.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_app.persist();
	}

	public java.lang.String getFileDir() {
		return _app.getFileDir();
	}

	public java.lang.String getFileName() {
		return _app.getFileName();
	}

	public java.lang.String getFilePath() {
		return _app.getFilePath();
	}

	public boolean isDownloaded()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _app.isDownloaded();
	}

	public boolean isInstalled()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _app.isInstalled();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public App getWrappedApp() {
		return _app;
	}

	public App getWrappedModel() {
		return _app;
	}

	public void resetOriginalValues() {
		_app.resetOriginalValues();
	}

	private App _app;
}