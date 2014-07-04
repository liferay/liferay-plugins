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

package com.liferay.bbb.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BBBServer}.
 * </p>
 *
 * @author Shinn Lok
 * @see BBBServer
 * @generated
 */
public class BBBServerWrapper implements BBBServer, ModelWrapper<BBBServer> {
	public BBBServerWrapper(BBBServer bbbServer) {
		_bbbServer = bbbServer;
	}

	@Override
	public Class<?> getModelClass() {
		return BBBServer.class;
	}

	@Override
	public String getModelClassName() {
		return BBBServer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("bbbServerId", getBbbServerId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("url", getUrl());
		attributes.put("secret", getSecret());
		attributes.put("active", getActive());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long bbbServerId = (Long)attributes.get("bbbServerId");

		if (bbbServerId != null) {
			setBbbServerId(bbbServerId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String secret = (String)attributes.get("secret");

		if (secret != null) {
			setSecret(secret);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new BBBServerWrapper((BBBServer)_bbbServer.clone());
	}

	@Override
	public int compareTo(com.liferay.bbb.model.BBBServer bbbServer) {
		return _bbbServer.compareTo(bbbServer);
	}

	/**
	* Returns the active of this b b b server.
	*
	* @return the active of this b b b server
	*/
	@Override
	public boolean getActive() {
		return _bbbServer.getActive();
	}

	/**
	* Returns the bbb server ID of this b b b server.
	*
	* @return the bbb server ID of this b b b server
	*/
	@Override
	public long getBbbServerId() {
		return _bbbServer.getBbbServerId();
	}

	/**
	* Returns the company ID of this b b b server.
	*
	* @return the company ID of this b b b server
	*/
	@Override
	public long getCompanyId() {
		return _bbbServer.getCompanyId();
	}

	/**
	* Returns the create date of this b b b server.
	*
	* @return the create date of this b b b server
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _bbbServer.getCreateDate();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _bbbServer.getExpandoBridge();
	}

	/**
	* Returns the modified date of this b b b server.
	*
	* @return the modified date of this b b b server
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _bbbServer.getModifiedDate();
	}

	/**
	* Returns the name of this b b b server.
	*
	* @return the name of this b b b server
	*/
	@Override
	public java.lang.String getName() {
		return _bbbServer.getName();
	}

	/**
	* Returns the primary key of this b b b server.
	*
	* @return the primary key of this b b b server
	*/
	@Override
	public long getPrimaryKey() {
		return _bbbServer.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _bbbServer.getPrimaryKeyObj();
	}

	/**
	* Returns the secret of this b b b server.
	*
	* @return the secret of this b b b server
	*/
	@Override
	public java.lang.String getSecret() {
		return _bbbServer.getSecret();
	}

	/**
	* Returns the url of this b b b server.
	*
	* @return the url of this b b b server
	*/
	@Override
	public java.lang.String getUrl() {
		return _bbbServer.getUrl();
	}

	/**
	* Returns the user ID of this b b b server.
	*
	* @return the user ID of this b b b server
	*/
	@Override
	public long getUserId() {
		return _bbbServer.getUserId();
	}

	/**
	* Returns the user name of this b b b server.
	*
	* @return the user name of this b b b server
	*/
	@Override
	public java.lang.String getUserName() {
		return _bbbServer.getUserName();
	}

	/**
	* Returns the user uuid of this b b b server.
	*
	* @return the user uuid of this b b b server
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _bbbServer.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _bbbServer.hashCode();
	}

	/**
	* Returns <code>true</code> if this b b b server is active.
	*
	* @return <code>true</code> if this b b b server is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _bbbServer.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _bbbServer.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _bbbServer.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _bbbServer.isNew();
	}

	@Override
	public void persist() {
		_bbbServer.persist();
	}

	/**
	* Sets whether this b b b server is active.
	*
	* @param active the active of this b b b server
	*/
	@Override
	public void setActive(boolean active) {
		_bbbServer.setActive(active);
	}

	/**
	* Sets the bbb server ID of this b b b server.
	*
	* @param bbbServerId the bbb server ID of this b b b server
	*/
	@Override
	public void setBbbServerId(long bbbServerId) {
		_bbbServer.setBbbServerId(bbbServerId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_bbbServer.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this b b b server.
	*
	* @param companyId the company ID of this b b b server
	*/
	@Override
	public void setCompanyId(long companyId) {
		_bbbServer.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this b b b server.
	*
	* @param createDate the create date of this b b b server
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_bbbServer.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_bbbServer.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_bbbServer.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_bbbServer.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this b b b server.
	*
	* @param modifiedDate the modified date of this b b b server
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_bbbServer.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this b b b server.
	*
	* @param name the name of this b b b server
	*/
	@Override
	public void setName(java.lang.String name) {
		_bbbServer.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_bbbServer.setNew(n);
	}

	/**
	* Sets the primary key of this b b b server.
	*
	* @param primaryKey the primary key of this b b b server
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_bbbServer.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_bbbServer.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the secret of this b b b server.
	*
	* @param secret the secret of this b b b server
	*/
	@Override
	public void setSecret(java.lang.String secret) {
		_bbbServer.setSecret(secret);
	}

	/**
	* Sets the url of this b b b server.
	*
	* @param url the url of this b b b server
	*/
	@Override
	public void setUrl(java.lang.String url) {
		_bbbServer.setUrl(url);
	}

	/**
	* Sets the user ID of this b b b server.
	*
	* @param userId the user ID of this b b b server
	*/
	@Override
	public void setUserId(long userId) {
		_bbbServer.setUserId(userId);
	}

	/**
	* Sets the user name of this b b b server.
	*
	* @param userName the user name of this b b b server
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_bbbServer.setUserName(userName);
	}

	/**
	* Sets the user uuid of this b b b server.
	*
	* @param userUuid the user uuid of this b b b server
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_bbbServer.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.bbb.model.BBBServer> toCacheModel() {
		return _bbbServer.toCacheModel();
	}

	@Override
	public com.liferay.bbb.model.BBBServer toEscapedModel() {
		return new BBBServerWrapper(_bbbServer.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _bbbServer.toString();
	}

	@Override
	public com.liferay.bbb.model.BBBServer toUnescapedModel() {
		return new BBBServerWrapper(_bbbServer.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _bbbServer.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BBBServerWrapper)) {
			return false;
		}

		BBBServerWrapper bbbServerWrapper = (BBBServerWrapper)obj;

		if (Validator.equals(_bbbServer, bbbServerWrapper._bbbServer)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public BBBServer getWrappedBBBServer() {
		return _bbbServer;
	}

	@Override
	public BBBServer getWrappedModel() {
		return _bbbServer;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _bbbServer.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _bbbServer.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_bbbServer.resetOriginalValues();
	}

	private BBBServer _bbbServer;
}