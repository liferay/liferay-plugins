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

package com.liferay.opensocial.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OAuthToken}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthToken
 * @generated
 */
@ProviderType
public class OAuthTokenWrapper implements OAuthToken, ModelWrapper<OAuthToken> {
	public OAuthTokenWrapper(OAuthToken oAuthToken) {
		_oAuthToken = oAuthToken;
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthToken.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthToken.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oAuthTokenId", getOAuthTokenId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("gadgetKey", getGadgetKey());
		attributes.put("serviceName", getServiceName());
		attributes.put("moduleId", getModuleId());
		attributes.put("accessToken", getAccessToken());
		attributes.put("tokenName", getTokenName());
		attributes.put("tokenSecret", getTokenSecret());
		attributes.put("sessionHandle", getSessionHandle());
		attributes.put("expiration", getExpiration());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long oAuthTokenId = (Long)attributes.get("oAuthTokenId");

		if (oAuthTokenId != null) {
			setOAuthTokenId(oAuthTokenId);
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

		String gadgetKey = (String)attributes.get("gadgetKey");

		if (gadgetKey != null) {
			setGadgetKey(gadgetKey);
		}

		String serviceName = (String)attributes.get("serviceName");

		if (serviceName != null) {
			setServiceName(serviceName);
		}

		Long moduleId = (Long)attributes.get("moduleId");

		if (moduleId != null) {
			setModuleId(moduleId);
		}

		String accessToken = (String)attributes.get("accessToken");

		if (accessToken != null) {
			setAccessToken(accessToken);
		}

		String tokenName = (String)attributes.get("tokenName");

		if (tokenName != null) {
			setTokenName(tokenName);
		}

		String tokenSecret = (String)attributes.get("tokenSecret");

		if (tokenSecret != null) {
			setTokenSecret(tokenSecret);
		}

		String sessionHandle = (String)attributes.get("sessionHandle");

		if (sessionHandle != null) {
			setSessionHandle(sessionHandle);
		}

		Long expiration = (Long)attributes.get("expiration");

		if (expiration != null) {
			setExpiration(expiration);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new OAuthTokenWrapper((OAuthToken)_oAuthToken.clone());
	}

	@Override
	public int compareTo(com.liferay.opensocial.model.OAuthToken oAuthToken) {
		return _oAuthToken.compareTo(oAuthToken);
	}

	/**
	* Returns the access token of this o auth token.
	*
	* @return the access token of this o auth token
	*/
	@Override
	public java.lang.String getAccessToken() {
		return _oAuthToken.getAccessToken();
	}

	/**
	* Returns the company ID of this o auth token.
	*
	* @return the company ID of this o auth token
	*/
	@Override
	public long getCompanyId() {
		return _oAuthToken.getCompanyId();
	}

	/**
	* Returns the create date of this o auth token.
	*
	* @return the create date of this o auth token
	*/
	@Override
	public Date getCreateDate() {
		return _oAuthToken.getCreateDate();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _oAuthToken.getExpandoBridge();
	}

	/**
	* Returns the expiration of this o auth token.
	*
	* @return the expiration of this o auth token
	*/
	@Override
	public long getExpiration() {
		return _oAuthToken.getExpiration();
	}

	/**
	* Returns the gadget key of this o auth token.
	*
	* @return the gadget key of this o auth token
	*/
	@Override
	public java.lang.String getGadgetKey() {
		return _oAuthToken.getGadgetKey();
	}

	/**
	* Returns the modified date of this o auth token.
	*
	* @return the modified date of this o auth token
	*/
	@Override
	public Date getModifiedDate() {
		return _oAuthToken.getModifiedDate();
	}

	/**
	* Returns the module ID of this o auth token.
	*
	* @return the module ID of this o auth token
	*/
	@Override
	public long getModuleId() {
		return _oAuthToken.getModuleId();
	}

	/**
	* Returns the o auth token ID of this o auth token.
	*
	* @return the o auth token ID of this o auth token
	*/
	@Override
	public long getOAuthTokenId() {
		return _oAuthToken.getOAuthTokenId();
	}

	/**
	* Returns the primary key of this o auth token.
	*
	* @return the primary key of this o auth token
	*/
	@Override
	public long getPrimaryKey() {
		return _oAuthToken.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _oAuthToken.getPrimaryKeyObj();
	}

	/**
	* Returns the service name of this o auth token.
	*
	* @return the service name of this o auth token
	*/
	@Override
	public java.lang.String getServiceName() {
		return _oAuthToken.getServiceName();
	}

	/**
	* Returns the session handle of this o auth token.
	*
	* @return the session handle of this o auth token
	*/
	@Override
	public java.lang.String getSessionHandle() {
		return _oAuthToken.getSessionHandle();
	}

	/**
	* Returns the token name of this o auth token.
	*
	* @return the token name of this o auth token
	*/
	@Override
	public java.lang.String getTokenName() {
		return _oAuthToken.getTokenName();
	}

	/**
	* Returns the token secret of this o auth token.
	*
	* @return the token secret of this o auth token
	*/
	@Override
	public java.lang.String getTokenSecret() {
		return _oAuthToken.getTokenSecret();
	}

	/**
	* Returns the user ID of this o auth token.
	*
	* @return the user ID of this o auth token
	*/
	@Override
	public long getUserId() {
		return _oAuthToken.getUserId();
	}

	/**
	* Returns the user name of this o auth token.
	*
	* @return the user name of this o auth token
	*/
	@Override
	public java.lang.String getUserName() {
		return _oAuthToken.getUserName();
	}

	/**
	* Returns the user uuid of this o auth token.
	*
	* @return the user uuid of this o auth token
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _oAuthToken.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _oAuthToken.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _oAuthToken.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _oAuthToken.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _oAuthToken.isNew();
	}

	@Override
	public void persist() {
		_oAuthToken.persist();
	}

	/**
	* Sets the access token of this o auth token.
	*
	* @param accessToken the access token of this o auth token
	*/
	@Override
	public void setAccessToken(java.lang.String accessToken) {
		_oAuthToken.setAccessToken(accessToken);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_oAuthToken.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this o auth token.
	*
	* @param companyId the company ID of this o auth token
	*/
	@Override
	public void setCompanyId(long companyId) {
		_oAuthToken.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this o auth token.
	*
	* @param createDate the create date of this o auth token
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_oAuthToken.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_oAuthToken.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_oAuthToken.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_oAuthToken.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expiration of this o auth token.
	*
	* @param expiration the expiration of this o auth token
	*/
	@Override
	public void setExpiration(long expiration) {
		_oAuthToken.setExpiration(expiration);
	}

	/**
	* Sets the gadget key of this o auth token.
	*
	* @param gadgetKey the gadget key of this o auth token
	*/
	@Override
	public void setGadgetKey(java.lang.String gadgetKey) {
		_oAuthToken.setGadgetKey(gadgetKey);
	}

	/**
	* Sets the modified date of this o auth token.
	*
	* @param modifiedDate the modified date of this o auth token
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_oAuthToken.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the module ID of this o auth token.
	*
	* @param moduleId the module ID of this o auth token
	*/
	@Override
	public void setModuleId(long moduleId) {
		_oAuthToken.setModuleId(moduleId);
	}

	@Override
	public void setNew(boolean n) {
		_oAuthToken.setNew(n);
	}

	/**
	* Sets the o auth token ID of this o auth token.
	*
	* @param oAuthTokenId the o auth token ID of this o auth token
	*/
	@Override
	public void setOAuthTokenId(long oAuthTokenId) {
		_oAuthToken.setOAuthTokenId(oAuthTokenId);
	}

	/**
	* Sets the primary key of this o auth token.
	*
	* @param primaryKey the primary key of this o auth token
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_oAuthToken.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_oAuthToken.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service name of this o auth token.
	*
	* @param serviceName the service name of this o auth token
	*/
	@Override
	public void setServiceName(java.lang.String serviceName) {
		_oAuthToken.setServiceName(serviceName);
	}

	/**
	* Sets the session handle of this o auth token.
	*
	* @param sessionHandle the session handle of this o auth token
	*/
	@Override
	public void setSessionHandle(java.lang.String sessionHandle) {
		_oAuthToken.setSessionHandle(sessionHandle);
	}

	/**
	* Sets the token name of this o auth token.
	*
	* @param tokenName the token name of this o auth token
	*/
	@Override
	public void setTokenName(java.lang.String tokenName) {
		_oAuthToken.setTokenName(tokenName);
	}

	/**
	* Sets the token secret of this o auth token.
	*
	* @param tokenSecret the token secret of this o auth token
	*/
	@Override
	public void setTokenSecret(java.lang.String tokenSecret) {
		_oAuthToken.setTokenSecret(tokenSecret);
	}

	/**
	* Sets the user ID of this o auth token.
	*
	* @param userId the user ID of this o auth token
	*/
	@Override
	public void setUserId(long userId) {
		_oAuthToken.setUserId(userId);
	}

	/**
	* Sets the user name of this o auth token.
	*
	* @param userName the user name of this o auth token
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_oAuthToken.setUserName(userName);
	}

	/**
	* Sets the user uuid of this o auth token.
	*
	* @param userUuid the user uuid of this o auth token
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_oAuthToken.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.opensocial.model.OAuthToken> toCacheModel() {
		return _oAuthToken.toCacheModel();
	}

	@Override
	public com.liferay.opensocial.model.OAuthToken toEscapedModel() {
		return new OAuthTokenWrapper(_oAuthToken.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _oAuthToken.toString();
	}

	@Override
	public com.liferay.opensocial.model.OAuthToken toUnescapedModel() {
		return new OAuthTokenWrapper(_oAuthToken.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _oAuthToken.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OAuthTokenWrapper)) {
			return false;
		}

		OAuthTokenWrapper oAuthTokenWrapper = (OAuthTokenWrapper)obj;

		if (Validator.equals(_oAuthToken, oAuthTokenWrapper._oAuthToken)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public OAuthToken getWrappedOAuthToken() {
		return _oAuthToken;
	}

	@Override
	public OAuthToken getWrappedModel() {
		return _oAuthToken;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _oAuthToken.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _oAuthToken.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_oAuthToken.resetOriginalValues();
	}

	private final OAuthToken _oAuthToken;
}