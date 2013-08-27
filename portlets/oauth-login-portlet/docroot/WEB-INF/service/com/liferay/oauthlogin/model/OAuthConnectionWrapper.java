/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.oauthlogin.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OAuthConnection}.
 * </p>
 *
 * @author Andy Yang
 * @see OAuthConnection
 * @generated
 */
public class OAuthConnectionWrapper implements OAuthConnection,
	ModelWrapper<OAuthConnection> {
	public OAuthConnectionWrapper(OAuthConnection oAuthConnection) {
		_oAuthConnection = oAuthConnection;
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthConnection.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthConnection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oAuthConnectionId", getOAuthConnectionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("enabled", getEnabled());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("iconId", getIconId());
		attributes.put("oAuthVersion", getOAuthVersion());
		attributes.put("key", getKey());
		attributes.put("secret", getSecret());
		attributes.put("scope", getScope());
		attributes.put("authorizeURL", getAuthorizeURL());
		attributes.put("accessTokenURL", getAccessTokenURL());
		attributes.put("accessTokenVerb", getAccessTokenVerb());
		attributes.put("accessTokenExtractorType", getAccessTokenExtractorType());
		attributes.put("requestTokenURL", getRequestTokenURL());
		attributes.put("requestTokenVerb", getRequestTokenVerb());
		attributes.put("signatureServiceType", getSignatureServiceType());
		attributes.put("redirectURL", getRedirectURL());
		attributes.put("socialAccountIdURL", getSocialAccountIdURL());
		attributes.put("socialAccountIdURLVerb", getSocialAccountIdURLVerb());
		attributes.put("socialAccountIdField", getSocialAccountIdField());
		attributes.put("socialAccountIdType", getSocialAccountIdType());
		attributes.put("socialAccountIdScript", getSocialAccountIdScript());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long oAuthConnectionId = (Long)attributes.get("oAuthConnectionId");

		if (oAuthConnectionId != null) {
			setOAuthConnectionId(oAuthConnectionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long iconId = (Long)attributes.get("iconId");

		if (iconId != null) {
			setIconId(iconId);
		}

		Integer oAuthVersion = (Integer)attributes.get("oAuthVersion");

		if (oAuthVersion != null) {
			setOAuthVersion(oAuthVersion);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String secret = (String)attributes.get("secret");

		if (secret != null) {
			setSecret(secret);
		}

		String scope = (String)attributes.get("scope");

		if (scope != null) {
			setScope(scope);
		}

		String authorizeURL = (String)attributes.get("authorizeURL");

		if (authorizeURL != null) {
			setAuthorizeURL(authorizeURL);
		}

		String accessTokenURL = (String)attributes.get("accessTokenURL");

		if (accessTokenURL != null) {
			setAccessTokenURL(accessTokenURL);
		}

		Integer accessTokenVerb = (Integer)attributes.get("accessTokenVerb");

		if (accessTokenVerb != null) {
			setAccessTokenVerb(accessTokenVerb);
		}

		Integer accessTokenExtractorType = (Integer)attributes.get(
				"accessTokenExtractorType");

		if (accessTokenExtractorType != null) {
			setAccessTokenExtractorType(accessTokenExtractorType);
		}

		String requestTokenURL = (String)attributes.get("requestTokenURL");

		if (requestTokenURL != null) {
			setRequestTokenURL(requestTokenURL);
		}

		Integer requestTokenVerb = (Integer)attributes.get("requestTokenVerb");

		if (requestTokenVerb != null) {
			setRequestTokenVerb(requestTokenVerb);
		}

		Integer signatureServiceType = (Integer)attributes.get(
				"signatureServiceType");

		if (signatureServiceType != null) {
			setSignatureServiceType(signatureServiceType);
		}

		String redirectURL = (String)attributes.get("redirectURL");

		if (redirectURL != null) {
			setRedirectURL(redirectURL);
		}

		String socialAccountIdURL = (String)attributes.get("socialAccountIdURL");

		if (socialAccountIdURL != null) {
			setSocialAccountIdURL(socialAccountIdURL);
		}

		Integer socialAccountIdURLVerb = (Integer)attributes.get(
				"socialAccountIdURLVerb");

		if (socialAccountIdURLVerb != null) {
			setSocialAccountIdURLVerb(socialAccountIdURLVerb);
		}

		String socialAccountIdField = (String)attributes.get(
				"socialAccountIdField");

		if (socialAccountIdField != null) {
			setSocialAccountIdField(socialAccountIdField);
		}

		Integer socialAccountIdType = (Integer)attributes.get(
				"socialAccountIdType");

		if (socialAccountIdType != null) {
			setSocialAccountIdType(socialAccountIdType);
		}

		String socialAccountIdScript = (String)attributes.get(
				"socialAccountIdScript");

		if (socialAccountIdScript != null) {
			setSocialAccountIdScript(socialAccountIdScript);
		}
	}

	/**
	* Returns the primary key of this o auth connection.
	*
	* @return the primary key of this o auth connection
	*/
	@Override
	public long getPrimaryKey() {
		return _oAuthConnection.getPrimaryKey();
	}

	/**
	* Sets the primary key of this o auth connection.
	*
	* @param primaryKey the primary key of this o auth connection
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_oAuthConnection.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the o auth connection ID of this o auth connection.
	*
	* @return the o auth connection ID of this o auth connection
	*/
	@Override
	public long getOAuthConnectionId() {
		return _oAuthConnection.getOAuthConnectionId();
	}

	/**
	* Sets the o auth connection ID of this o auth connection.
	*
	* @param oAuthConnectionId the o auth connection ID of this o auth connection
	*/
	@Override
	public void setOAuthConnectionId(long oAuthConnectionId) {
		_oAuthConnection.setOAuthConnectionId(oAuthConnectionId);
	}

	/**
	* Returns the company ID of this o auth connection.
	*
	* @return the company ID of this o auth connection
	*/
	@Override
	public long getCompanyId() {
		return _oAuthConnection.getCompanyId();
	}

	/**
	* Sets the company ID of this o auth connection.
	*
	* @param companyId the company ID of this o auth connection
	*/
	@Override
	public void setCompanyId(long companyId) {
		_oAuthConnection.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this o auth connection.
	*
	* @return the user ID of this o auth connection
	*/
	@Override
	public long getUserId() {
		return _oAuthConnection.getUserId();
	}

	/**
	* Sets the user ID of this o auth connection.
	*
	* @param userId the user ID of this o auth connection
	*/
	@Override
	public void setUserId(long userId) {
		_oAuthConnection.setUserId(userId);
	}

	/**
	* Returns the user uuid of this o auth connection.
	*
	* @return the user uuid of this o auth connection
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConnection.getUserUuid();
	}

	/**
	* Sets the user uuid of this o auth connection.
	*
	* @param userUuid the user uuid of this o auth connection
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_oAuthConnection.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this o auth connection.
	*
	* @return the create date of this o auth connection
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _oAuthConnection.getCreateDate();
	}

	/**
	* Sets the create date of this o auth connection.
	*
	* @param createDate the create date of this o auth connection
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_oAuthConnection.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this o auth connection.
	*
	* @return the modified date of this o auth connection
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _oAuthConnection.getModifiedDate();
	}

	/**
	* Sets the modified date of this o auth connection.
	*
	* @param modifiedDate the modified date of this o auth connection
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_oAuthConnection.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the enabled of this o auth connection.
	*
	* @return the enabled of this o auth connection
	*/
	@Override
	public boolean getEnabled() {
		return _oAuthConnection.getEnabled();
	}

	/**
	* Returns <code>true</code> if this o auth connection is enabled.
	*
	* @return <code>true</code> if this o auth connection is enabled; <code>false</code> otherwise
	*/
	@Override
	public boolean isEnabled() {
		return _oAuthConnection.isEnabled();
	}

	/**
	* Sets whether this o auth connection is enabled.
	*
	* @param enabled the enabled of this o auth connection
	*/
	@Override
	public void setEnabled(boolean enabled) {
		_oAuthConnection.setEnabled(enabled);
	}

	/**
	* Returns the name of this o auth connection.
	*
	* @return the name of this o auth connection
	*/
	@Override
	public java.lang.String getName() {
		return _oAuthConnection.getName();
	}

	/**
	* Sets the name of this o auth connection.
	*
	* @param name the name of this o auth connection
	*/
	@Override
	public void setName(java.lang.String name) {
		_oAuthConnection.setName(name);
	}

	/**
	* Returns the description of this o auth connection.
	*
	* @return the description of this o auth connection
	*/
	@Override
	public java.lang.String getDescription() {
		return _oAuthConnection.getDescription();
	}

	/**
	* Sets the description of this o auth connection.
	*
	* @param description the description of this o auth connection
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_oAuthConnection.setDescription(description);
	}

	/**
	* Returns the icon ID of this o auth connection.
	*
	* @return the icon ID of this o auth connection
	*/
	@Override
	public long getIconId() {
		return _oAuthConnection.getIconId();
	}

	/**
	* Sets the icon ID of this o auth connection.
	*
	* @param iconId the icon ID of this o auth connection
	*/
	@Override
	public void setIconId(long iconId) {
		_oAuthConnection.setIconId(iconId);
	}

	/**
	* Returns the o auth version of this o auth connection.
	*
	* @return the o auth version of this o auth connection
	*/
	@Override
	public int getOAuthVersion() {
		return _oAuthConnection.getOAuthVersion();
	}

	/**
	* Sets the o auth version of this o auth connection.
	*
	* @param oAuthVersion the o auth version of this o auth connection
	*/
	@Override
	public void setOAuthVersion(int oAuthVersion) {
		_oAuthConnection.setOAuthVersion(oAuthVersion);
	}

	/**
	* Returns the key of this o auth connection.
	*
	* @return the key of this o auth connection
	*/
	@Override
	public java.lang.String getKey() {
		return _oAuthConnection.getKey();
	}

	/**
	* Sets the key of this o auth connection.
	*
	* @param key the key of this o auth connection
	*/
	@Override
	public void setKey(java.lang.String key) {
		_oAuthConnection.setKey(key);
	}

	/**
	* Returns the secret of this o auth connection.
	*
	* @return the secret of this o auth connection
	*/
	@Override
	public java.lang.String getSecret() {
		return _oAuthConnection.getSecret();
	}

	/**
	* Sets the secret of this o auth connection.
	*
	* @param secret the secret of this o auth connection
	*/
	@Override
	public void setSecret(java.lang.String secret) {
		_oAuthConnection.setSecret(secret);
	}

	/**
	* Returns the scope of this o auth connection.
	*
	* @return the scope of this o auth connection
	*/
	@Override
	public java.lang.String getScope() {
		return _oAuthConnection.getScope();
	}

	/**
	* Sets the scope of this o auth connection.
	*
	* @param scope the scope of this o auth connection
	*/
	@Override
	public void setScope(java.lang.String scope) {
		_oAuthConnection.setScope(scope);
	}

	/**
	* Returns the authorize u r l of this o auth connection.
	*
	* @return the authorize u r l of this o auth connection
	*/
	@Override
	public java.lang.String getAuthorizeURL() {
		return _oAuthConnection.getAuthorizeURL();
	}

	/**
	* Sets the authorize u r l of this o auth connection.
	*
	* @param authorizeURL the authorize u r l of this o auth connection
	*/
	@Override
	public void setAuthorizeURL(java.lang.String authorizeURL) {
		_oAuthConnection.setAuthorizeURL(authorizeURL);
	}

	/**
	* Returns the access token u r l of this o auth connection.
	*
	* @return the access token u r l of this o auth connection
	*/
	@Override
	public java.lang.String getAccessTokenURL() {
		return _oAuthConnection.getAccessTokenURL();
	}

	/**
	* Sets the access token u r l of this o auth connection.
	*
	* @param accessTokenURL the access token u r l of this o auth connection
	*/
	@Override
	public void setAccessTokenURL(java.lang.String accessTokenURL) {
		_oAuthConnection.setAccessTokenURL(accessTokenURL);
	}

	/**
	* Returns the access token verb of this o auth connection.
	*
	* @return the access token verb of this o auth connection
	*/
	@Override
	public int getAccessTokenVerb() {
		return _oAuthConnection.getAccessTokenVerb();
	}

	/**
	* Sets the access token verb of this o auth connection.
	*
	* @param accessTokenVerb the access token verb of this o auth connection
	*/
	@Override
	public void setAccessTokenVerb(int accessTokenVerb) {
		_oAuthConnection.setAccessTokenVerb(accessTokenVerb);
	}

	/**
	* Returns the access token extractor type of this o auth connection.
	*
	* @return the access token extractor type of this o auth connection
	*/
	@Override
	public int getAccessTokenExtractorType() {
		return _oAuthConnection.getAccessTokenExtractorType();
	}

	/**
	* Sets the access token extractor type of this o auth connection.
	*
	* @param accessTokenExtractorType the access token extractor type of this o auth connection
	*/
	@Override
	public void setAccessTokenExtractorType(int accessTokenExtractorType) {
		_oAuthConnection.setAccessTokenExtractorType(accessTokenExtractorType);
	}

	/**
	* Returns the request token u r l of this o auth connection.
	*
	* @return the request token u r l of this o auth connection
	*/
	@Override
	public java.lang.String getRequestTokenURL() {
		return _oAuthConnection.getRequestTokenURL();
	}

	/**
	* Sets the request token u r l of this o auth connection.
	*
	* @param requestTokenURL the request token u r l of this o auth connection
	*/
	@Override
	public void setRequestTokenURL(java.lang.String requestTokenURL) {
		_oAuthConnection.setRequestTokenURL(requestTokenURL);
	}

	/**
	* Returns the request token verb of this o auth connection.
	*
	* @return the request token verb of this o auth connection
	*/
	@Override
	public int getRequestTokenVerb() {
		return _oAuthConnection.getRequestTokenVerb();
	}

	/**
	* Sets the request token verb of this o auth connection.
	*
	* @param requestTokenVerb the request token verb of this o auth connection
	*/
	@Override
	public void setRequestTokenVerb(int requestTokenVerb) {
		_oAuthConnection.setRequestTokenVerb(requestTokenVerb);
	}

	/**
	* Returns the signature service type of this o auth connection.
	*
	* @return the signature service type of this o auth connection
	*/
	@Override
	public int getSignatureServiceType() {
		return _oAuthConnection.getSignatureServiceType();
	}

	/**
	* Sets the signature service type of this o auth connection.
	*
	* @param signatureServiceType the signature service type of this o auth connection
	*/
	@Override
	public void setSignatureServiceType(int signatureServiceType) {
		_oAuthConnection.setSignatureServiceType(signatureServiceType);
	}

	/**
	* Returns the redirect u r l of this o auth connection.
	*
	* @return the redirect u r l of this o auth connection
	*/
	@Override
	public java.lang.String getRedirectURL() {
		return _oAuthConnection.getRedirectURL();
	}

	/**
	* Sets the redirect u r l of this o auth connection.
	*
	* @param redirectURL the redirect u r l of this o auth connection
	*/
	@Override
	public void setRedirectURL(java.lang.String redirectURL) {
		_oAuthConnection.setRedirectURL(redirectURL);
	}

	/**
	* Returns the social account ID u r l of this o auth connection.
	*
	* @return the social account ID u r l of this o auth connection
	*/
	@Override
	public java.lang.String getSocialAccountIdURL() {
		return _oAuthConnection.getSocialAccountIdURL();
	}

	/**
	* Sets the social account ID u r l of this o auth connection.
	*
	* @param socialAccountIdURL the social account ID u r l of this o auth connection
	*/
	@Override
	public void setSocialAccountIdURL(java.lang.String socialAccountIdURL) {
		_oAuthConnection.setSocialAccountIdURL(socialAccountIdURL);
	}

	/**
	* Returns the social account ID u r l verb of this o auth connection.
	*
	* @return the social account ID u r l verb of this o auth connection
	*/
	@Override
	public int getSocialAccountIdURLVerb() {
		return _oAuthConnection.getSocialAccountIdURLVerb();
	}

	/**
	* Sets the social account ID u r l verb of this o auth connection.
	*
	* @param socialAccountIdURLVerb the social account ID u r l verb of this o auth connection
	*/
	@Override
	public void setSocialAccountIdURLVerb(int socialAccountIdURLVerb) {
		_oAuthConnection.setSocialAccountIdURLVerb(socialAccountIdURLVerb);
	}

	/**
	* Returns the social account ID field of this o auth connection.
	*
	* @return the social account ID field of this o auth connection
	*/
	@Override
	public java.lang.String getSocialAccountIdField() {
		return _oAuthConnection.getSocialAccountIdField();
	}

	/**
	* Sets the social account ID field of this o auth connection.
	*
	* @param socialAccountIdField the social account ID field of this o auth connection
	*/
	@Override
	public void setSocialAccountIdField(java.lang.String socialAccountIdField) {
		_oAuthConnection.setSocialAccountIdField(socialAccountIdField);
	}

	/**
	* Returns the social account ID type of this o auth connection.
	*
	* @return the social account ID type of this o auth connection
	*/
	@Override
	public int getSocialAccountIdType() {
		return _oAuthConnection.getSocialAccountIdType();
	}

	/**
	* Sets the social account ID type of this o auth connection.
	*
	* @param socialAccountIdType the social account ID type of this o auth connection
	*/
	@Override
	public void setSocialAccountIdType(int socialAccountIdType) {
		_oAuthConnection.setSocialAccountIdType(socialAccountIdType);
	}

	/**
	* Returns the social account ID script of this o auth connection.
	*
	* @return the social account ID script of this o auth connection
	*/
	@Override
	public java.lang.String getSocialAccountIdScript() {
		return _oAuthConnection.getSocialAccountIdScript();
	}

	/**
	* Sets the social account ID script of this o auth connection.
	*
	* @param socialAccountIdScript the social account ID script of this o auth connection
	*/
	@Override
	public void setSocialAccountIdScript(java.lang.String socialAccountIdScript) {
		_oAuthConnection.setSocialAccountIdScript(socialAccountIdScript);
	}

	@Override
	public boolean isNew() {
		return _oAuthConnection.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_oAuthConnection.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _oAuthConnection.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_oAuthConnection.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _oAuthConnection.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _oAuthConnection.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_oAuthConnection.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _oAuthConnection.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_oAuthConnection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_oAuthConnection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_oAuthConnection.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OAuthConnectionWrapper((OAuthConnection)_oAuthConnection.clone());
	}

	@Override
	public int compareTo(
		com.liferay.oauthlogin.model.OAuthConnection oAuthConnection) {
		return _oAuthConnection.compareTo(oAuthConnection);
	}

	@Override
	public int hashCode() {
		return _oAuthConnection.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.oauthlogin.model.OAuthConnection> toCacheModel() {
		return _oAuthConnection.toCacheModel();
	}

	@Override
	public com.liferay.oauthlogin.model.OAuthConnection toEscapedModel() {
		return new OAuthConnectionWrapper(_oAuthConnection.toEscapedModel());
	}

	@Override
	public com.liferay.oauthlogin.model.OAuthConnection toUnescapedModel() {
		return new OAuthConnectionWrapper(_oAuthConnection.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _oAuthConnection.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _oAuthConnection.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_oAuthConnection.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OAuthConnectionWrapper)) {
			return false;
		}

		OAuthConnectionWrapper oAuthConnectionWrapper = (OAuthConnectionWrapper)obj;

		if (Validator.equals(_oAuthConnection,
					oAuthConnectionWrapper._oAuthConnection)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public OAuthConnection getWrappedOAuthConnection() {
		return _oAuthConnection;
	}

	@Override
	public OAuthConnection getWrappedModel() {
		return _oAuthConnection;
	}

	@Override
	public void resetOriginalValues() {
		_oAuthConnection.resetOriginalValues();
	}

	private OAuthConnection _oAuthConnection;
}