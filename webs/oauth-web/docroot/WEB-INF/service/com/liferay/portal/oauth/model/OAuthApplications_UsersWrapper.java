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
 * This class is a wrapper for {@link OAuthApplications_Users}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OAuthApplications_Users
 * @generated
 */
public class OAuthApplications_UsersWrapper implements OAuthApplications_Users,
	ModelWrapper<OAuthApplications_Users> {
	public OAuthApplications_UsersWrapper(
		OAuthApplications_Users oAuthApplications_Users) {
		_oAuthApplications_Users = oAuthApplications_Users;
	}

	public Class<?> getModelClass() {
		return OAuthApplications_Users.class;
	}

	public String getModelClassName() {
		return OAuthApplications_Users.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oaauid", getOaauid());
		attributes.put("accessToken", getAccessToken());
		attributes.put("accessSecret", getAccessSecret());
		attributes.put("applicationId", getApplicationId());
		attributes.put("authorized", getAuthorized());
		attributes.put("userId", getUserId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long oaauid = (Long)attributes.get("oaauid");

		if (oaauid != null) {
			setOaauid(oaauid);
		}

		String accessToken = (String)attributes.get("accessToken");

		if (accessToken != null) {
			setAccessToken(accessToken);
		}

		String accessSecret = (String)attributes.get("accessSecret");

		if (accessSecret != null) {
			setAccessSecret(accessSecret);
		}

		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		Boolean authorized = (Boolean)attributes.get("authorized");

		if (authorized != null) {
			setAuthorized(authorized);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	/**
	* Returns the primary key of this o auth applications_ users.
	*
	* @return the primary key of this o auth applications_ users
	*/
	public long getPrimaryKey() {
		return _oAuthApplications_Users.getPrimaryKey();
	}

	/**
	* Sets the primary key of this o auth applications_ users.
	*
	* @param primaryKey the primary key of this o auth applications_ users
	*/
	public void setPrimaryKey(long primaryKey) {
		_oAuthApplications_Users.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the oaauid of this o auth applications_ users.
	*
	* @return the oaauid of this o auth applications_ users
	*/
	public long getOaauid() {
		return _oAuthApplications_Users.getOaauid();
	}

	/**
	* Sets the oaauid of this o auth applications_ users.
	*
	* @param oaauid the oaauid of this o auth applications_ users
	*/
	public void setOaauid(long oaauid) {
		_oAuthApplications_Users.setOaauid(oaauid);
	}

	/**
	* Returns the access token of this o auth applications_ users.
	*
	* @return the access token of this o auth applications_ users
	*/
	public java.lang.String getAccessToken() {
		return _oAuthApplications_Users.getAccessToken();
	}

	/**
	* Sets the access token of this o auth applications_ users.
	*
	* @param accessToken the access token of this o auth applications_ users
	*/
	public void setAccessToken(java.lang.String accessToken) {
		_oAuthApplications_Users.setAccessToken(accessToken);
	}

	/**
	* Returns the access secret of this o auth applications_ users.
	*
	* @return the access secret of this o auth applications_ users
	*/
	public java.lang.String getAccessSecret() {
		return _oAuthApplications_Users.getAccessSecret();
	}

	/**
	* Sets the access secret of this o auth applications_ users.
	*
	* @param accessSecret the access secret of this o auth applications_ users
	*/
	public void setAccessSecret(java.lang.String accessSecret) {
		_oAuthApplications_Users.setAccessSecret(accessSecret);
	}

	/**
	* Returns the application ID of this o auth applications_ users.
	*
	* @return the application ID of this o auth applications_ users
	*/
	public long getApplicationId() {
		return _oAuthApplications_Users.getApplicationId();
	}

	/**
	* Sets the application ID of this o auth applications_ users.
	*
	* @param applicationId the application ID of this o auth applications_ users
	*/
	public void setApplicationId(long applicationId) {
		_oAuthApplications_Users.setApplicationId(applicationId);
	}

	/**
	* Returns the authorized of this o auth applications_ users.
	*
	* @return the authorized of this o auth applications_ users
	*/
	public boolean getAuthorized() {
		return _oAuthApplications_Users.getAuthorized();
	}

	/**
	* Returns <code>true</code> if this o auth applications_ users is authorized.
	*
	* @return <code>true</code> if this o auth applications_ users is authorized; <code>false</code> otherwise
	*/
	public boolean isAuthorized() {
		return _oAuthApplications_Users.isAuthorized();
	}

	/**
	* Sets whether this o auth applications_ users is authorized.
	*
	* @param authorized the authorized of this o auth applications_ users
	*/
	public void setAuthorized(boolean authorized) {
		_oAuthApplications_Users.setAuthorized(authorized);
	}

	/**
	* Returns the user ID of this o auth applications_ users.
	*
	* @return the user ID of this o auth applications_ users
	*/
	public long getUserId() {
		return _oAuthApplications_Users.getUserId();
	}

	/**
	* Sets the user ID of this o auth applications_ users.
	*
	* @param userId the user ID of this o auth applications_ users
	*/
	public void setUserId(long userId) {
		_oAuthApplications_Users.setUserId(userId);
	}

	/**
	* Returns the user uuid of this o auth applications_ users.
	*
	* @return the user uuid of this o auth applications_ users
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplications_Users.getUserUuid();
	}

	/**
	* Sets the user uuid of this o auth applications_ users.
	*
	* @param userUuid the user uuid of this o auth applications_ users
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_oAuthApplications_Users.setUserUuid(userUuid);
	}

	public boolean isNew() {
		return _oAuthApplications_Users.isNew();
	}

	public void setNew(boolean n) {
		_oAuthApplications_Users.setNew(n);
	}

	public boolean isCachedModel() {
		return _oAuthApplications_Users.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_oAuthApplications_Users.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _oAuthApplications_Users.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _oAuthApplications_Users.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_oAuthApplications_Users.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _oAuthApplications_Users.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_oAuthApplications_Users.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OAuthApplications_UsersWrapper((OAuthApplications_Users)_oAuthApplications_Users.clone());
	}

	public int compareTo(
		com.liferay.portal.oauth.model.OAuthApplications_Users oAuthApplications_Users) {
		return _oAuthApplications_Users.compareTo(oAuthApplications_Users);
	}

	@Override
	public int hashCode() {
		return _oAuthApplications_Users.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.oauth.model.OAuthApplications_Users> toCacheModel() {
		return _oAuthApplications_Users.toCacheModel();
	}

	public com.liferay.portal.oauth.model.OAuthApplications_Users toEscapedModel() {
		return new OAuthApplications_UsersWrapper(_oAuthApplications_Users.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _oAuthApplications_Users.toString();
	}

	public java.lang.String toXmlString() {
		return _oAuthApplications_Users.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_oAuthApplications_Users.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public OAuthApplications_Users getWrappedOAuthApplications_Users() {
		return _oAuthApplications_Users;
	}

	public OAuthApplications_Users getWrappedModel() {
		return _oAuthApplications_Users;
	}

	public void resetOriginalValues() {
		_oAuthApplications_Users.resetOriginalValues();
	}

	private OAuthApplications_Users _oAuthApplications_Users;
}