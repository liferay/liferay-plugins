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

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ApplicationUser}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ApplicationUser
 * @generated
 */
public class ApplicationUserWrapper implements ApplicationUser,
	ModelWrapper<ApplicationUser> {
	public ApplicationUserWrapper(ApplicationUser applicationUser) {
		_applicationUser = applicationUser;
	}

	public Class<?> getModelClass() {
		return ApplicationUser.class;
	}

	public String getModelClassName() {
		return ApplicationUser.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oaauId", getOaauId());
		attributes.put("userId", getUserId());
		attributes.put("applicationId", getApplicationId());
		attributes.put("accessToken", getAccessToken());
		attributes.put("accessSecret", getAccessSecret());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long oaauId = (Long)attributes.get("oaauId");

		if (oaauId != null) {
			setOaauId(oaauId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		String accessToken = (String)attributes.get("accessToken");

		if (accessToken != null) {
			setAccessToken(accessToken);
		}

		String accessSecret = (String)attributes.get("accessSecret");

		if (accessSecret != null) {
			setAccessSecret(accessSecret);
		}
	}

	/**
	* Returns the primary key of this application user.
	*
	* @return the primary key of this application user
	*/
	public long getPrimaryKey() {
		return _applicationUser.getPrimaryKey();
	}

	/**
	* Sets the primary key of this application user.
	*
	* @param primaryKey the primary key of this application user
	*/
	public void setPrimaryKey(long primaryKey) {
		_applicationUser.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the oaau ID of this application user.
	*
	* @return the oaau ID of this application user
	*/
	public long getOaauId() {
		return _applicationUser.getOaauId();
	}

	/**
	* Sets the oaau ID of this application user.
	*
	* @param oaauId the oaau ID of this application user
	*/
	public void setOaauId(long oaauId) {
		_applicationUser.setOaauId(oaauId);
	}

	/**
	* Returns the user ID of this application user.
	*
	* @return the user ID of this application user
	*/
	public long getUserId() {
		return _applicationUser.getUserId();
	}

	/**
	* Sets the user ID of this application user.
	*
	* @param userId the user ID of this application user
	*/
	public void setUserId(long userId) {
		_applicationUser.setUserId(userId);
	}

	/**
	* Returns the user uuid of this application user.
	*
	* @return the user uuid of this application user
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _applicationUser.getUserUuid();
	}

	/**
	* Sets the user uuid of this application user.
	*
	* @param userUuid the user uuid of this application user
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_applicationUser.setUserUuid(userUuid);
	}

	/**
	* Returns the application ID of this application user.
	*
	* @return the application ID of this application user
	*/
	public long getApplicationId() {
		return _applicationUser.getApplicationId();
	}

	/**
	* Sets the application ID of this application user.
	*
	* @param applicationId the application ID of this application user
	*/
	public void setApplicationId(long applicationId) {
		_applicationUser.setApplicationId(applicationId);
	}

	/**
	* Returns the access token of this application user.
	*
	* @return the access token of this application user
	*/
	public java.lang.String getAccessToken() {
		return _applicationUser.getAccessToken();
	}

	/**
	* Sets the access token of this application user.
	*
	* @param accessToken the access token of this application user
	*/
	public void setAccessToken(java.lang.String accessToken) {
		_applicationUser.setAccessToken(accessToken);
	}

	/**
	* Returns the access secret of this application user.
	*
	* @return the access secret of this application user
	*/
	public java.lang.String getAccessSecret() {
		return _applicationUser.getAccessSecret();
	}

	/**
	* Sets the access secret of this application user.
	*
	* @param accessSecret the access secret of this application user
	*/
	public void setAccessSecret(java.lang.String accessSecret) {
		_applicationUser.setAccessSecret(accessSecret);
	}

	public boolean isNew() {
		return _applicationUser.isNew();
	}

	public void setNew(boolean n) {
		_applicationUser.setNew(n);
	}

	public boolean isCachedModel() {
		return _applicationUser.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_applicationUser.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _applicationUser.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _applicationUser.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_applicationUser.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _applicationUser.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_applicationUser.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ApplicationUserWrapper((ApplicationUser)_applicationUser.clone());
	}

	public int compareTo(
		com.liferay.portal.oauth.model.ApplicationUser applicationUser) {
		return _applicationUser.compareTo(applicationUser);
	}

	@Override
	public int hashCode() {
		return _applicationUser.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.oauth.model.ApplicationUser> toCacheModel() {
		return _applicationUser.toCacheModel();
	}

	public com.liferay.portal.oauth.model.ApplicationUser toEscapedModel() {
		return new ApplicationUserWrapper(_applicationUser.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _applicationUser.toString();
	}

	public java.lang.String toXmlString() {
		return _applicationUser.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_applicationUser.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public ApplicationUser getWrappedApplicationUser() {
		return _applicationUser;
	}

	public ApplicationUser getWrappedModel() {
		return _applicationUser;
	}

	public void resetOriginalValues() {
		_applicationUser.resetOriginalValues();
	}

	private ApplicationUser _applicationUser;
}