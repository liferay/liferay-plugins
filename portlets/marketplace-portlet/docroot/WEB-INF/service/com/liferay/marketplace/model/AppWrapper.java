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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link App}.
 * </p>
 *
 * @author Ryan Park
 * @see App
 * @generated
 */
@ProviderType
public class AppWrapper implements App, ModelWrapper<App> {
	public AppWrapper(App app) {
		_app = app;
	}

	@Override
	public Class<?> getModelClass() {
		return App.class;
	}

	@Override
	public String getModelClassName() {
		return App.class.getName();
	}

	@Override
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
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("category", getCategory());
		attributes.put("iconURL", getIconURL());
		attributes.put("version", getVersion());

		return attributes;
	}

	@Override
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String category = (String)attributes.get("category");

		if (category != null) {
			setCategory(category);
		}

		String iconURL = (String)attributes.get("iconURL");

		if (iconURL != null) {
			setIconURL(iconURL);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}
	}

	@Override
	public java.lang.String[] addContextName(java.lang.String contextName) {
		return _app.addContextName(contextName);
	}

	@Override
	public java.lang.Object clone() {
		return new AppWrapper((App)_app.clone());
	}

	@Override
	public int compareTo(com.liferay.marketplace.model.App app) {
		return _app.compareTo(app);
	}

	/**
	* Returns the app ID of this app.
	*
	* @return the app ID of this app
	*/
	@Override
	public long getAppId() {
		return _app.getAppId();
	}

	/**
	* Returns the category of this app.
	*
	* @return the category of this app
	*/
	@Override
	public java.lang.String getCategory() {
		return _app.getCategory();
	}

	/**
	* Returns the company ID of this app.
	*
	* @return the company ID of this app
	*/
	@Override
	public long getCompanyId() {
		return _app.getCompanyId();
	}

	@Override
	public java.lang.String[] getContextNames() {
		return _app.getContextNames();
	}

	/**
	* Returns the create date of this app.
	*
	* @return the create date of this app
	*/
	@Override
	public Date getCreateDate() {
		return _app.getCreateDate();
	}

	/**
	* Returns the description of this app.
	*
	* @return the description of this app
	*/
	@Override
	public java.lang.String getDescription() {
		return _app.getDescription();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _app.getExpandoBridge();
	}

	@Override
	public java.lang.String getFileDir() {
		return _app.getFileDir();
	}

	@Override
	public java.lang.String getFileName() {
		return _app.getFileName();
	}

	@Override
	public java.lang.String getFilePath() {
		return _app.getFilePath();
	}

	/**
	* Returns the icon u r l of this app.
	*
	* @return the icon u r l of this app
	*/
	@Override
	public java.lang.String getIconURL() {
		return _app.getIconURL();
	}

	/**
	* Returns the modified date of this app.
	*
	* @return the modified date of this app
	*/
	@Override
	public Date getModifiedDate() {
		return _app.getModifiedDate();
	}

	/**
	* Returns the primary key of this app.
	*
	* @return the primary key of this app
	*/
	@Override
	public long getPrimaryKey() {
		return _app.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _app.getPrimaryKeyObj();
	}

	/**
	* Returns the remote app ID of this app.
	*
	* @return the remote app ID of this app
	*/
	@Override
	public long getRemoteAppId() {
		return _app.getRemoteAppId();
	}

	/**
	* Returns the title of this app.
	*
	* @return the title of this app
	*/
	@Override
	public java.lang.String getTitle() {
		return _app.getTitle();
	}

	/**
	* Returns the user ID of this app.
	*
	* @return the user ID of this app
	*/
	@Override
	public long getUserId() {
		return _app.getUserId();
	}

	/**
	* Returns the user name of this app.
	*
	* @return the user name of this app
	*/
	@Override
	public java.lang.String getUserName() {
		return _app.getUserName();
	}

	/**
	* Returns the user uuid of this app.
	*
	* @return the user uuid of this app
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _app.getUserUuid();
	}

	/**
	* Returns the uuid of this app.
	*
	* @return the uuid of this app
	*/
	@Override
	public java.lang.String getUuid() {
		return _app.getUuid();
	}

	/**
	* Returns the version of this app.
	*
	* @return the version of this app
	*/
	@Override
	public java.lang.String getVersion() {
		return _app.getVersion();
	}

	@Override
	public int hashCode() {
		return _app.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _app.isCachedModel();
	}

	@Override
	public boolean isDownloaded()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _app.isDownloaded();
	}

	@Override
	public boolean isEscapedModel() {
		return _app.isEscapedModel();
	}

	@Override
	public boolean isInstalled() {
		return _app.isInstalled();
	}

	@Override
	public boolean isNew() {
		return _app.isNew();
	}

	@Override
	public void persist() {
		_app.persist();
	}

	/**
	* Sets the app ID of this app.
	*
	* @param appId the app ID of this app
	*/
	@Override
	public void setAppId(long appId) {
		_app.setAppId(appId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_app.setCachedModel(cachedModel);
	}

	/**
	* Sets the category of this app.
	*
	* @param category the category of this app
	*/
	@Override
	public void setCategory(java.lang.String category) {
		_app.setCategory(category);
	}

	/**
	* Sets the company ID of this app.
	*
	* @param companyId the company ID of this app
	*/
	@Override
	public void setCompanyId(long companyId) {
		_app.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this app.
	*
	* @param createDate the create date of this app
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_app.setCreateDate(createDate);
	}

	/**
	* Sets the description of this app.
	*
	* @param description the description of this app
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_app.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_app.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_app.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_app.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the icon u r l of this app.
	*
	* @param iconURL the icon u r l of this app
	*/
	@Override
	public void setIconURL(java.lang.String iconURL) {
		_app.setIconURL(iconURL);
	}

	/**
	* Sets the modified date of this app.
	*
	* @param modifiedDate the modified date of this app
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_app.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_app.setNew(n);
	}

	/**
	* Sets the primary key of this app.
	*
	* @param primaryKey the primary key of this app
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_app.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_app.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the remote app ID of this app.
	*
	* @param remoteAppId the remote app ID of this app
	*/
	@Override
	public void setRemoteAppId(long remoteAppId) {
		_app.setRemoteAppId(remoteAppId);
	}

	/**
	* Sets the title of this app.
	*
	* @param title the title of this app
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_app.setTitle(title);
	}

	/**
	* Sets the user ID of this app.
	*
	* @param userId the user ID of this app
	*/
	@Override
	public void setUserId(long userId) {
		_app.setUserId(userId);
	}

	/**
	* Sets the user name of this app.
	*
	* @param userName the user name of this app
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_app.setUserName(userName);
	}

	/**
	* Sets the user uuid of this app.
	*
	* @param userUuid the user uuid of this app
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_app.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this app.
	*
	* @param uuid the uuid of this app
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_app.setUuid(uuid);
	}

	/**
	* Sets the version of this app.
	*
	* @param version the version of this app
	*/
	@Override
	public void setVersion(java.lang.String version) {
		_app.setVersion(version);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.marketplace.model.App> toCacheModel() {
		return _app.toCacheModel();
	}

	@Override
	public com.liferay.marketplace.model.App toEscapedModel() {
		return new AppWrapper(_app.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _app.toString();
	}

	@Override
	public com.liferay.marketplace.model.App toUnescapedModel() {
		return new AppWrapper(_app.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _app.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AppWrapper)) {
			return false;
		}

		AppWrapper appWrapper = (AppWrapper)obj;

		if (Validator.equals(_app, appWrapper._app)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _app.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public App getWrappedApp() {
		return _app;
	}

	@Override
	public App getWrappedModel() {
		return _app;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _app.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _app.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_app.resetOriginalValues();
	}

	private final App _app;
}