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

package com.liferay.meeting.webex.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link WebExAccount}.
 * </p>
 *
 * @author Anant Singh
 * @see WebExAccount
 * @generated
 */
@ProviderType
public class WebExAccountWrapper implements WebExAccount,
	ModelWrapper<WebExAccount> {
	public WebExAccountWrapper(WebExAccount webExAccount) {
		_webExAccount = webExAccount;
	}

	@Override
	public Class<?> getModelClass() {
		return WebExAccount.class;
	}

	@Override
	public String getModelClassName() {
		return WebExAccount.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("webExAccountId", getWebExAccountId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("webExSiteId", getWebExSiteId());
		attributes.put("login", getLogin());
		attributes.put("password", getPassword());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long webExAccountId = (Long)attributes.get("webExAccountId");

		if (webExAccountId != null) {
			setWebExAccountId(webExAccountId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		Long webExSiteId = (Long)attributes.get("webExSiteId");

		if (webExSiteId != null) {
			setWebExSiteId(webExSiteId);
		}

		String login = (String)attributes.get("login");

		if (login != null) {
			setLogin(login);
		}

		String password = (String)attributes.get("password");

		if (password != null) {
			setPassword(password);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _webExAccount.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _webExAccount.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _webExAccount.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _webExAccount.getExpandoBridge();
	}

	@Override
	public com.liferay.meeting.MeetingContext getMeetingContext()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _webExAccount.getMeetingContext();
	}

	@Override
	public com.liferay.meeting.webex.model.WebExAccount toEscapedModel() {
		return new WebExAccountWrapper(_webExAccount.toEscapedModel());
	}

	@Override
	public com.liferay.meeting.webex.model.WebExAccount toUnescapedModel() {
		return new WebExAccountWrapper(_webExAccount.toUnescapedModel());
	}

	@Override
	public com.liferay.meeting.webex.model.WebExSite getWebExSite()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _webExAccount.getWebExSite();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<com.liferay.meeting.webex.model.WebExAccount> toCacheModel() {
		return _webExAccount.toCacheModel();
	}

	@Override
	public int compareTo(
		com.liferay.meeting.webex.model.WebExAccount webExAccount) {
		return _webExAccount.compareTo(webExAccount);
	}

	@Override
	public int hashCode() {
		return _webExAccount.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _webExAccount.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new WebExAccountWrapper((WebExAccount)_webExAccount.clone());
	}

	/**
	* Returns the login of this web ex account.
	*
	* @return the login of this web ex account
	*/
	@Override
	public java.lang.String getLogin() {
		return _webExAccount.getLogin();
	}

	/**
	* Returns the password of this web ex account.
	*
	* @return the password of this web ex account
	*/
	@Override
	public java.lang.String getPassword() {
		return _webExAccount.getPassword();
	}

	/**
	* Returns the user name of this web ex account.
	*
	* @return the user name of this web ex account
	*/
	@Override
	public java.lang.String getUserName() {
		return _webExAccount.getUserName();
	}

	/**
	* Returns the user uuid of this web ex account.
	*
	* @return the user uuid of this web ex account
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _webExAccount.getUserUuid();
	}

	/**
	* Returns the uuid of this web ex account.
	*
	* @return the uuid of this web ex account
	*/
	@Override
	public java.lang.String getUuid() {
		return _webExAccount.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _webExAccount.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _webExAccount.toXmlString();
	}

	/**
	* Returns the create date of this web ex account.
	*
	* @return the create date of this web ex account
	*/
	@Override
	public Date getCreateDate() {
		return _webExAccount.getCreateDate();
	}

	/**
	* Returns the modified date of this web ex account.
	*
	* @return the modified date of this web ex account
	*/
	@Override
	public Date getModifiedDate() {
		return _webExAccount.getModifiedDate();
	}

	/**
	* Returns the company ID of this web ex account.
	*
	* @return the company ID of this web ex account
	*/
	@Override
	public long getCompanyId() {
		return _webExAccount.getCompanyId();
	}

	/**
	* Returns the group ID of this web ex account.
	*
	* @return the group ID of this web ex account
	*/
	@Override
	public long getGroupId() {
		return _webExAccount.getGroupId();
	}

	/**
	* Returns the primary key of this web ex account.
	*
	* @return the primary key of this web ex account
	*/
	@Override
	public long getPrimaryKey() {
		return _webExAccount.getPrimaryKey();
	}

	/**
	* Returns the user ID of this web ex account.
	*
	* @return the user ID of this web ex account
	*/
	@Override
	public long getUserId() {
		return _webExAccount.getUserId();
	}

	/**
	* Returns the web ex account ID of this web ex account.
	*
	* @return the web ex account ID of this web ex account
	*/
	@Override
	public long getWebExAccountId() {
		return _webExAccount.getWebExAccountId();
	}

	/**
	* Returns the web ex site ID of this web ex account.
	*
	* @return the web ex site ID of this web ex account
	*/
	@Override
	public long getWebExSiteId() {
		return _webExAccount.getWebExSiteId();
	}

	@Override
	public void persist() {
		_webExAccount.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_webExAccount.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this web ex account.
	*
	* @param companyId the company ID of this web ex account
	*/
	@Override
	public void setCompanyId(long companyId) {
		_webExAccount.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this web ex account.
	*
	* @param createDate the create date of this web ex account
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_webExAccount.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_webExAccount.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_webExAccount.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_webExAccount.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this web ex account.
	*
	* @param groupId the group ID of this web ex account
	*/
	@Override
	public void setGroupId(long groupId) {
		_webExAccount.setGroupId(groupId);
	}

	/**
	* Sets the login of this web ex account.
	*
	* @param login the login of this web ex account
	*/
	@Override
	public void setLogin(java.lang.String login) {
		_webExAccount.setLogin(login);
	}

	/**
	* Sets the modified date of this web ex account.
	*
	* @param modifiedDate the modified date of this web ex account
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_webExAccount.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_webExAccount.setNew(n);
	}

	/**
	* Sets the password of this web ex account.
	*
	* @param password the password of this web ex account
	*/
	@Override
	public void setPassword(java.lang.String password) {
		_webExAccount.setPassword(password);
	}

	/**
	* Sets the primary key of this web ex account.
	*
	* @param primaryKey the primary key of this web ex account
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_webExAccount.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_webExAccount.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this web ex account.
	*
	* @param userId the user ID of this web ex account
	*/
	@Override
	public void setUserId(long userId) {
		_webExAccount.setUserId(userId);
	}

	/**
	* Sets the user name of this web ex account.
	*
	* @param userName the user name of this web ex account
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_webExAccount.setUserName(userName);
	}

	/**
	* Sets the user uuid of this web ex account.
	*
	* @param userUuid the user uuid of this web ex account
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_webExAccount.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this web ex account.
	*
	* @param uuid the uuid of this web ex account
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_webExAccount.setUuid(uuid);
	}

	/**
	* Sets the web ex account ID of this web ex account.
	*
	* @param webExAccountId the web ex account ID of this web ex account
	*/
	@Override
	public void setWebExAccountId(long webExAccountId) {
		_webExAccount.setWebExAccountId(webExAccountId);
	}

	/**
	* Sets the web ex site ID of this web ex account.
	*
	* @param webExSiteId the web ex site ID of this web ex account
	*/
	@Override
	public void setWebExSiteId(long webExSiteId) {
		_webExAccount.setWebExSiteId(webExSiteId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WebExAccountWrapper)) {
			return false;
		}

		WebExAccountWrapper webExAccountWrapper = (WebExAccountWrapper)obj;

		if (Validator.equals(_webExAccount, webExAccountWrapper._webExAccount)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _webExAccount.getStagedModelType();
	}

	@Override
	public WebExAccount getWrappedModel() {
		return _webExAccount;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _webExAccount.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _webExAccount.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_webExAccount.resetOriginalValues();
	}

	private final WebExAccount _webExAccount;
}