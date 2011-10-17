/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OAuthToken}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OAuthToken
 * @generated
 */
public class OAuthTokenWrapper implements OAuthToken, ModelWrapper<OAuthToken> {
	public OAuthTokenWrapper(OAuthToken oAuthToken) {
		_oAuthToken = oAuthToken;
	}

	public Class<?> getModelClass() {
		return OAuthToken.class;
	}

	public String getModelClassName() {
		return OAuthToken.class.getName();
	}

	/**
	* Returns the primary key of this o auth token.
	*
	* @return the primary key of this o auth token
	*/
	public long getPrimaryKey() {
		return _oAuthToken.getPrimaryKey();
	}

	/**
	* Sets the primary key of this o auth token.
	*
	* @param primaryKey the primary key of this o auth token
	*/
	public void setPrimaryKey(long primaryKey) {
		_oAuthToken.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the o auth token ID of this o auth token.
	*
	* @return the o auth token ID of this o auth token
	*/
	public long getOAuthTokenId() {
		return _oAuthToken.getOAuthTokenId();
	}

	/**
	* Sets the o auth token ID of this o auth token.
	*
	* @param oAuthTokenId the o auth token ID of this o auth token
	*/
	public void setOAuthTokenId(long oAuthTokenId) {
		_oAuthToken.setOAuthTokenId(oAuthTokenId);
	}

	/**
	* Returns the company ID of this o auth token.
	*
	* @return the company ID of this o auth token
	*/
	public long getCompanyId() {
		return _oAuthToken.getCompanyId();
	}

	/**
	* Sets the company ID of this o auth token.
	*
	* @param companyId the company ID of this o auth token
	*/
	public void setCompanyId(long companyId) {
		_oAuthToken.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this o auth token.
	*
	* @return the user ID of this o auth token
	*/
	public long getUserId() {
		return _oAuthToken.getUserId();
	}

	/**
	* Sets the user ID of this o auth token.
	*
	* @param userId the user ID of this o auth token
	*/
	public void setUserId(long userId) {
		_oAuthToken.setUserId(userId);
	}

	/**
	* Returns the user uuid of this o auth token.
	*
	* @return the user uuid of this o auth token
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthToken.getUserUuid();
	}

	/**
	* Sets the user uuid of this o auth token.
	*
	* @param userUuid the user uuid of this o auth token
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_oAuthToken.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this o auth token.
	*
	* @return the user name of this o auth token
	*/
	public java.lang.String getUserName() {
		return _oAuthToken.getUserName();
	}

	/**
	* Sets the user name of this o auth token.
	*
	* @param userName the user name of this o auth token
	*/
	public void setUserName(java.lang.String userName) {
		_oAuthToken.setUserName(userName);
	}

	/**
	* Returns the create date of this o auth token.
	*
	* @return the create date of this o auth token
	*/
	public java.util.Date getCreateDate() {
		return _oAuthToken.getCreateDate();
	}

	/**
	* Sets the create date of this o auth token.
	*
	* @param createDate the create date of this o auth token
	*/
	public void setCreateDate(java.util.Date createDate) {
		_oAuthToken.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this o auth token.
	*
	* @return the modified date of this o auth token
	*/
	public java.util.Date getModifiedDate() {
		return _oAuthToken.getModifiedDate();
	}

	/**
	* Sets the modified date of this o auth token.
	*
	* @param modifiedDate the modified date of this o auth token
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_oAuthToken.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the gadget key of this o auth token.
	*
	* @return the gadget key of this o auth token
	*/
	public java.lang.String getGadgetKey() {
		return _oAuthToken.getGadgetKey();
	}

	/**
	* Sets the gadget key of this o auth token.
	*
	* @param gadgetKey the gadget key of this o auth token
	*/
	public void setGadgetKey(java.lang.String gadgetKey) {
		_oAuthToken.setGadgetKey(gadgetKey);
	}

	/**
	* Returns the service name of this o auth token.
	*
	* @return the service name of this o auth token
	*/
	public java.lang.String getServiceName() {
		return _oAuthToken.getServiceName();
	}

	/**
	* Sets the service name of this o auth token.
	*
	* @param serviceName the service name of this o auth token
	*/
	public void setServiceName(java.lang.String serviceName) {
		_oAuthToken.setServiceName(serviceName);
	}

	/**
	* Returns the module ID of this o auth token.
	*
	* @return the module ID of this o auth token
	*/
	public long getModuleId() {
		return _oAuthToken.getModuleId();
	}

	/**
	* Sets the module ID of this o auth token.
	*
	* @param moduleId the module ID of this o auth token
	*/
	public void setModuleId(long moduleId) {
		_oAuthToken.setModuleId(moduleId);
	}

	/**
	* Returns the access token of this o auth token.
	*
	* @return the access token of this o auth token
	*/
	public java.lang.String getAccessToken() {
		return _oAuthToken.getAccessToken();
	}

	/**
	* Sets the access token of this o auth token.
	*
	* @param accessToken the access token of this o auth token
	*/
	public void setAccessToken(java.lang.String accessToken) {
		_oAuthToken.setAccessToken(accessToken);
	}

	/**
	* Returns the token name of this o auth token.
	*
	* @return the token name of this o auth token
	*/
	public java.lang.String getTokenName() {
		return _oAuthToken.getTokenName();
	}

	/**
	* Sets the token name of this o auth token.
	*
	* @param tokenName the token name of this o auth token
	*/
	public void setTokenName(java.lang.String tokenName) {
		_oAuthToken.setTokenName(tokenName);
	}

	/**
	* Returns the token secret of this o auth token.
	*
	* @return the token secret of this o auth token
	*/
	public java.lang.String getTokenSecret() {
		return _oAuthToken.getTokenSecret();
	}

	/**
	* Sets the token secret of this o auth token.
	*
	* @param tokenSecret the token secret of this o auth token
	*/
	public void setTokenSecret(java.lang.String tokenSecret) {
		_oAuthToken.setTokenSecret(tokenSecret);
	}

	/**
	* Returns the session handle of this o auth token.
	*
	* @return the session handle of this o auth token
	*/
	public java.lang.String getSessionHandle() {
		return _oAuthToken.getSessionHandle();
	}

	/**
	* Sets the session handle of this o auth token.
	*
	* @param sessionHandle the session handle of this o auth token
	*/
	public void setSessionHandle(java.lang.String sessionHandle) {
		_oAuthToken.setSessionHandle(sessionHandle);
	}

	/**
	* Returns the expiration of this o auth token.
	*
	* @return the expiration of this o auth token
	*/
	public long getExpiration() {
		return _oAuthToken.getExpiration();
	}

	/**
	* Sets the expiration of this o auth token.
	*
	* @param expiration the expiration of this o auth token
	*/
	public void setExpiration(long expiration) {
		_oAuthToken.setExpiration(expiration);
	}

	public boolean isNew() {
		return _oAuthToken.isNew();
	}

	public void setNew(boolean n) {
		_oAuthToken.setNew(n);
	}

	public boolean isCachedModel() {
		return _oAuthToken.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_oAuthToken.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _oAuthToken.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _oAuthToken.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_oAuthToken.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _oAuthToken.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_oAuthToken.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OAuthTokenWrapper((OAuthToken)_oAuthToken.clone());
	}

	public int compareTo(com.liferay.opensocial.model.OAuthToken oAuthToken) {
		return _oAuthToken.compareTo(oAuthToken);
	}

	@Override
	public int hashCode() {
		return _oAuthToken.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.opensocial.model.OAuthToken> toCacheModel() {
		return _oAuthToken.toCacheModel();
	}

	public com.liferay.opensocial.model.OAuthToken toEscapedModel() {
		return new OAuthTokenWrapper(_oAuthToken.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _oAuthToken.toString();
	}

	public java.lang.String toXmlString() {
		return _oAuthToken.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_oAuthToken.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public OAuthToken getWrappedOAuthToken() {
		return _oAuthToken;
	}

	public OAuthToken getWrappedModel() {
		return _oAuthToken;
	}

	public void resetOriginalValues() {
		_oAuthToken.resetOriginalValues();
	}

	private OAuthToken _oAuthToken;
}